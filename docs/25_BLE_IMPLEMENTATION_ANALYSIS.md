# Полный анализ BLE реализации — проблемы и план исправлений

**Дата:** 12 апреля 2026 г.
**Статус:** ⚠️ Критические проблемы обнаружены
**Автор:** Qwen Code

---

## 1. Главная проблема — ГИБРИДНАЯ СОЛЯНКА

### 1.1 Два РАЗНЫХ подхода к подключению в одном проекте

| Файл | Транспорт | Подход | Статус |
|------|-----------|--------|--------|
| `GlmReaderAndroid/.../GlmBleManager.kt` | **RFCOMM** (Classic BT) | Insecure socket, потоки | ✅ Работает |
| `GlmReader/Core/BleManager.cs` | **BLE GATT** | Advertisement + Characteristic | ✅ Работает |
| `ble_connection_check.txt` логи | **BLE GATT** | connectGatt, services discovery | ⚠️ Старая версия |

### 1.2 Критическое расхождение

**Android (текущий GlmBleManager.kt):**
```kotlin
// RFCOMM — как Measuring Master
device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
→ socket.connect()
→ inputStream / outputStream
→ SendThread с очередью команд
→ State machine: SLAVE_LISTENING → MASTER_READY → ...
```

**C# (BleManager.cs):**
```csharp
// BLE GATT — как MeasureOn
BluetoothLEAdvertisementWatcher → scan
BluetoothLEDevice.FromBluetoothAddressAsync → connect
GattCharacteristic → notify
→ WriteCommandAsync() → WriteValueAsync(Notify)
```

**Проблема:** Это ДВА РАЗНЫХ протокола, которые НЕ СОВМЕСТИМЫ на уровне подключения:
- RFCOMM = Bluetooth Classic (Serial Port Profile)
- BLE GATT = Bluetooth Low Energy (Generic Attribute Profile)

---

## 2. Анализ Android (GlmBleManager.kt) — текущая реализация

### 2.1 Что сделано ПРАВИЛЬНО ✅

| Элемент | Статус | Комментарий |
|---------|--------|-------------|
| `createInsecureRfcommSocketToServiceRecord()` | ✅ | Как в Measuring Master — БЕЗ bonding |
| SendThread с LinkedBlockingDeque | ✅ | Как в MO/MM — очередь команд |
| State machine (SLAVE_LISTENING, MASTER_READY...) | ✅ | Как в MO/MM |
| Timeout timer 500ms | ✅ | Как в MO/MM |
| Init команда `C0 55 02 40 00` | ✅ | Побайтово совпадает |
| Heartbeat обработка `C0 55 02 F1` | ✅ | Heartbeat распознаётся |
| CRC8 расчёт | ✅ | Poly=0xA6, Init=0xAA |
| Retry при подключении (3 попытки) | ✅ | С экспоненциальной задержкой |

### 2.2 Проблемы ⚠️ и ❌

| # | Проблема | Приоритет | Влияние |
|---|----------|-----------|---------|
| **1** | **Нет синхронизации состояния** `isReadyToSend` и `protocolState` | 🔴 P0 | Команды могут отправляться когда рулетка не готова |
| **2** | **`startTimeoutTimer()` запускает НОВЫЙ поток** каждый раз | 🔴 P0 | Утечка потоков, race conditions |
| **3** | **Нет блокировки write** — outputStream.write() без подтверждения | 🟡 P1 | Данные могут теряться |
| **4** | **processBuffer() не атомарный** — может читать пока SendThread пишет | 🟡 P1 | Консистентность буфера |
| **5** | **`readThread?.interrupt()`** может вызвать `IOException` | 🟢 P2 | Закрытие соединения с ошибкой |
| **6** | **Нет CRC валидации** при приёме | 🟢 P2 | Могут проходить битые пакеты |
| **7** | **`rawBytesCount` без volatile** | 🟢 P2 | Race condition на многопоточном доступе |

---

## 3. Детальный разбор проблем

### 3.1 Проблема #1 — Рассинхронизация состояний

```kotlin
// В enqueueCommand():
isReadyToSend = true  // Установили ДО запуска SendThread

// В SendThread:
while (!isReadyToSend && isConnected) { Thread.sleep(10) }
isReadyToSend = false  // Сбросили
// ... отправили ...
isReadyToSend = true   // Установили ПОСЛЕ

// В readLoop/heartbeat:
if (protocolState == ProtocolState.MASTER_RECEIVING) {
    protocolState = ProtocolState.MASTER_READY
    isReadyToSend = true  // ← ЕЩЁ РАЗ устанавливаем!
}
```

**Проблема:** Два разных потока (`SendThread` и `readLoop`) пишут в `isReadyToSend` **без синхронизации**. Это race condition.

**Сценарий сбоя:**
1. SendThread: `isReadyToSend = false`, отправляет команду
2. readLoop: получил heartbeat → `isReadyToSend = true`
3. `enqueueCommand()` видит `isReadyToSend = true` → запускает НОВУЮ команду
4. SendThread ещё в `MASTER_RECEIVING` → отправляет новую команду ПОВТОРНО
5. Рулетка получает 2 команды → игнорирует обе

### 3.2 Проблема #2 — Утечка потоков таймера

```kotlin
private fun startTimeoutTimer() {
    stopTimeoutTimer()  // Прерывает предыдущий
    timeoutTimer = Thread {
        Thread.sleep(commandTimeoutMs)  // 500ms
        Log.w("BLE", "⏱️ Command timeout")
    }.apply { start() }
}
```

**Проблема:** `stopTimeoutTimer()` делает `interrupt()`, но если таймер уже завершил работу — `timeoutTimer = null`, а `startTimeoutTimer()` вызван снова → новый поток.

**Более того:** Каждая команда → новый поток таймера. При 10 командах = 10 созданных/уничтоженных потоков. Это неэффективно и может вызывать race conditions.

### 3.3 Проблема #3 — Нет подтверждения записи

```kotlin
outputStream?.write(fullPacket)
outputStream?.flush()
Log.d("BLE", "📤 Sent ${cmd.name}")
// Нет ожидания подтверждения от рулетки!
```

**В MO (MeasureOn):**
```java
connection.write(bytes);
flagIsBLEWriteFinished = false;
while (!flagIsBLEWriteFinished) { wait(); }  // Ждёт callback!
```

**В RFCOMM:** нет callback на запись. Но это не значит что можно не ждать ответа — рулетка должна ответить пакетом измерения или heartbeat.

### 3.4 Проблема #4 — Неконсистентный буфер

```kotlin
// readLoop пишет:
readBuffer.write(chunk)

// processBuffer читает:
val data = readBuffer.toByteArray()
// ... modifies readBuffer ...
readBuffer.reset()
readBuffer.write(...)
```

`ByteArrayOutputStream` **НЕ потоко-безопасен**. Если `readLoop` пишет пока `processBuffer` читает — данные могут быть повреждены.

### 3.5 Проблема #6 — Нет CRC валидации при приёме

```kotlin
// processBuffer():
val fullPacket = packetData.copyOf(expectedSize)
// ...
val parsed = BlePacketParser.parse(fullPacket)  // ← CRC НЕ ПРОВЕРЯЕТСЯ
```

`BlePacketParser.parse()` НЕ проверяет CRC. Есть `parseWithCrc()` но он НЕ используется.

---

## 4. Сравнение с оригиналами MM/MO

### 4.1 Measuring Master (RFCOMM) — как работает

```
BluetoothConnection.openConnection()
  → createInsecureRfcommSocketToServiceRecord(SPP_UUID)
  → socket.connect()
  → inputStream / outputStream

GLMDeviceController.init()
  → MtProtocolImpl(connection)
  → protocol.addObserver(this)   // Наблюдатель!
  → protocol.initialize(connection)
  → EVENT_INITIALIZE_SLAVE
  → SLAVE_LISTENING

SendThread (MtProtocolImpl):
  → while (!interrupted)
  → msg = outgoingDeque.takeFirst()  // Блокирующее ожидание
  → while (state != MASTER_READY) { wait() }  // Монитор!
  → stateMachine.processEvent(EVENT_SEND_START)
  → frame = frameFactory.createFrame(msg)
  → bytes = MtFrameByteWriter(frame).write(buffer)
  → connection.write(bytes)
  → stateMachine.processEvent(EVENT_SEND_FINISH)

ReceiveThread:
  → while (!interrupted)
  → count = inputStream.read(buffer)
  → frameReader.append(byte)  // Побайтовый FSM!
  → if (isFrameRcvComplete())
  → checkMessageComplete()   // CRC проверка!
  → notifyObservers(event)   → GLMDeviceController.onEvent()
```

**Ключевые элементы:**
1. **Observer pattern** — `protocol.addObserver(this)`
2. **wait()/notify()** — синхронизация через мониторы
3. **State machine** — FSM для каждого состояния
4. **Побайтовый FSM** — `MtFrameByteReader.append(byte)`
5. **CRC валидация** — `checkMessageComplete()`

### 4.2 MeasureOn (BLE GATT) — как работает

```
BTDeviceManagerBLEImpl.connect(device)
  → BLEConnection.openConnection()
  → device.connectGatt(context, false, callback, TRANSPORT_LE)

BluetoothGattCallback:
  → onConnectionStateChange(newState=CONNECTED)
  → discoverServices()
  → onServicesDiscovered()
  → найти MIRACULIX_SERVICE / MIRACULIX2_SERVICE
  → найти характеристику
  → setCharacteristicIndication() / setCharacteristicNotification()
  → onDescriptorWrite(status=0)
  → setState(STATE_CONNECTED)
  → setupDeviceController()

GLMDeviceController.init()
  → MtProtocolBLEImpl(connection)
  → protocol.addObserver(this)
  → protocol.initialize(connection)
  → EVENT_INITIALIZE_SLAVE
  → SLAVE_LISTENING
  → turnAutoSyncOn()

SendThread (MtProtocolBLEImpl):
  → msg = outgoingDeque.takeFirst()
  → while (state != MASTER_READY) { wait() }
  → stateMachine.processEvent(EVENT_SEND_START)
  → frame = EDCFrameFactory.createFrame(msg)
  → bytes = MtFrameByteWriter(frame).write()
  → connection.write(bytes)
  → flagIsBLEWriteFinished = false
  → while (!flagIsBLEWriteFinished) { wait() }  // ЖДЁТ BLE callback!
  → stateMachine.processEvent(EVENT_SEND_FINISH)

onCharacteristicChanged(data):
  → onBLECharacteristicChanged(data)
  → stateMachine: SLAVE_LISTENING → SLAVE_RECEIVING
  → frameReader.append(byte)  // Побайтовый FSM
  → if (isFrameRcvComplete())
  → checkMessageComplete()   // CRC
  → notifyObservers()
```

### 4.3 Наш GlmBleManager.kt — что упущено

| Элемент | MM | MO | GlmBleManager | Статус |
|---------|----|----|---------------|--------|
| Observer pattern | ✅ | ✅ | ❌ Callbacks | ❌ |
| wait()/notify() | ✅ | ✅ | ❌ `while (sleep)` | ❌ |
| Побайтовый FSM | ✅ | ✅ | ❌ Bulk parsing | ⚠️ |
| CRC при приёме | ✅ | ✅ | ❌ Не используется | ❌ |
| Write confirm | ✅ | ✅ | ❌ Fire-and-forget | ❌ |
| Transaction ID | ✅ | ✅ | ❌ Нет | ❌ |
| Multi-frame | ✅ | ✅ | ❌ Нет поддержки | ❌ |

---

## 5. Корневые причины проблем

### 5.1 "Не работают точки отсчёта"

**Причина:** Команда `C0 55 02 BE [ref] [CRC]` отправляется когда рулетка в `SLAVE_LISTENING`.

```kotlin
// setReferencePoint() вызывается → enqueueCommand()
// Но рулетка может быть в SLAVE_LISTENING (ждёт измерения)
// а не MASTER_READY (ждёт команду)
```

**В MO:** между переключением типа/точки и измерением — пауза + state machine transition.

**В нашем коде:** `Thread.sleep(100)` между командами — недостаточно.

### 5.2 "Не переключаются типы измерений"

**Причина:** Та же — команда отправляется в неправильный момент.

```kotlin
// setMeasurementType(60) → enqueueCommand()
// SendThread: while (!isReadyToSend) { sleep(10) }
// isReadyToSend=true СРАЗУ после connect + после heartbeat
// Но рулетка ещё не перешла в режим приёма команд!
```

### 5.3 "Потеря данных замера"

**Причины:**
1. Нет CRC валидации → битые пакеты проходят
2. Bulk parsing вместо побайтового FSM → может пропустить начало пакета
3. Race condition на `readBuffer` → данные повреждаются

---

## 6. План исправлений

### 6.1 Критические (P0) — переписать

#### Шаг 1: Синхронизация состояний

```kotlin
// Заменить volatile на synchronized блок
@Volatile private var protocolState = ProtocolState.SLAVE_LISTENING

private val stateLock = Object()  // Монитор для синхронизации

private fun transitionTo(newState: ProtocolState) {
    synchronized(stateLock) {
        protocolState = newState
        stateLock.notifyAll()  // Разбудить ждущие потоки
    }
}

private fun waitForState(targetState: ProtocolState, timeoutMs: Long): Boolean {
    val end = System.currentTimeMillis() + timeoutMs
    synchronized(stateLock) {
        while (protocolState != targetState && System.currentTimeMillis() < end) {
            stateLock.wait(end - System.currentTimeMillis())
        }
        return protocolState == targetState
    }
}
```

#### Шаг 2: Заменить таймер на Handler/Coroutine

```kotlin
// Вместо Thread.sleep():
private var timeoutJob: Job? = null

private fun startTimeoutTimer() {
    timeoutJob?.cancel()
    timeoutJob = CoroutineScope(Dispatchers.IO).launch {
        delay(commandTimeoutMs)
        Log.w("BLE", "⏱️ Command timeout")
        onCommandTimeout()
    }
}
```

#### Шаг 3: CRC валидация при приёме

```kotlin
// В processBuffer():
val fullPacket = packetData.copyOf(expectedSize)
val receivedCrc = fullPacket.last().toInt() and 0xFF
val calculatedCrc = BlePacketParser.calcCrc8(fullPacket.copyOf(fullPacket.size - 1))

if (receivedCrc != calculatedCrc) {
    Log.w("BLE", "CRC mismatch: expected=$receivedCrc, got=$calculatedCrc")
    readBuffer.reset()
    return
}

// CRC OK — парсим
val parsed = BlePacketParser.parse(fullPacket)
```

#### Шаг 4: Потоко-безопасный буфер

```kotlin
// Заменить ByteArrayOutputStream на synchronized доступ
private val bufferLock = Object()

private fun processBuffer() {
    synchronized(bufferLock) {
        val data = readBuffer.toByteArray()
        // ... обработка ...
        readBuffer.reset()
        // ... запись оставшихся байт ...
    }
}

// В readLoop:
synchronized(bufferLock) {
    readBuffer.write(chunk)
}
```

### 6.2 Важные (P1) — улучшить

#### Шаг 5: Дождаться ответа после команды

```kotlin
// В SendThread после отправки:
val responseReceived = waitForResponse(commandTimeoutMs)
if (!responseReceived) {
    Log.w("BLE", "⏱️ No response for ${cmd.name}")
    // Retry или drop
}

private fun waitForResponse(timeoutMs: Long): Boolean {
    val end = System.currentTimeMillis() + timeoutMs
    while (System.currentTimeMillis() < end) {
        if (protocolState == ProtocolState.MASTER_READY) return true
        Thread.sleep(10)
    }
    return false
}
```

#### Шаг 6: Обработка heartbeat как keep-alive

```kotlin
// Heartbeat = рулетка жива, но НЕ ответ на команду
private fun handleHeartbeat(data: ByteArray) {
    val devStatus = data[4].toInt() and 0xFF
    val angle = data[5].toInt() and 0xFF
    Log.d("BLE", "💓 Heartbeat: devStatus=$devStatus, angle=$angle")

    // Heartbeat НЕ переключает state в MASTER_READY!
    // Только измерение = ответ на команду
}
```

### 6.3 Желательные (P2) — добавить

#### Шаг 7: Transaction ID

```kotlin
private var transactionId = 0

// В команде:
val cmd = QueuedCommand(payload, name, ++transactionId)

// В ответе: проверить что seqNum совпадает
```

#### Шаг 8: Побайтовый FSM

```kotlin
enum class ParseState { WAIT_START, WAIT_CMD, WAIT_LEN, WAIT_PAYLOAD, WAIT_CRC }
private var parseState = ParseState.WAIT_START
private var payloadBuffer = ByteArrayOutputStream()

fun appendByte(byte: Byte) {
    when (parseState) {
        WAIT_START -> if (byte == 0xC0.toByte()) parseState = WAIT_CMD
        WAIT_CMD -> if (byte == 0x55.toByte()) parseState = WAIT_LEN else parseState = WAIT_START
        WAIT_LEN -> { payloadLen = byte.toInt(); parseState = WAIT_PAYLOAD }
        WAIT_PAYLOAD -> { ... }
    }
}
```

---

## 7. Итог

### 7.1 Что подтвердилось

| Подозрение | Статус | Детали |
|------------|--------|--------|
| "Сборная солянка" | ✅ ПОДТВЕРДИЛОСЬ | RFCOMM + partial MO state machine + кастомный таймер |
| "Неточная реализация" | ✅ ПОДТВЕРДИЛОСЬ | Нет wait()/notify(), нет Observer, нет CRC при приёме |
| "Проблемы из-за этого" | ✅ ПОДТВЕРДИЛОСЬ | Race conditions → потеря команд, потеря измерений |

### 7.2 Что работает СЕЙЧАС

- ✅ Подключение (RFCOMM insecure socket)
- ✅ Init команда (AutoSync ON)
- ✅ Получение измерений (bulk parsing)
- ✅ Heartbeat обработка
- ✅ CRC расчёт для отправки

### 7.3 Что НЕ работает

- ❌ Переключение типов измерений (race condition на state)
- ❌ Точки отсчёта (команда в неправильный момент)
- ❌ Надёжность получения данных (нет CRC валидации, race condition на буфере)
- ⚠️ Утечка потоков таймера

### 7.4 Рекомендация

**Первым приоритетом** исправить P0 проблемы (синхронизация + CRC + буфер).
**Вторым приоритетом** исправить P1 (ожидание ответа + heartbeat).
**Третьим приоритетом** добавить P2 (transaction ID + FSM).

**Оценка усилий:** ~4-6 часов работы.

---

*Документ создан: 12 апреля 2026 г.*
*На основе анализа: GlmBleManager.kt (Android), BleManager.cs (C#), MM 1.9.4, MO 2.0.1*
