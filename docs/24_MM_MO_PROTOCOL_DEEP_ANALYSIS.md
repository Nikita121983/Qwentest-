# Глубокий анализ протокола MM и MO — Qwentest-4r6

**Дата:** 6 апреля 2026 г.
**Статус:** ✅ Анализ завершён
**Задача:** Qwentest-4r6

---

## 1. Цель анализа

Сравнить декомпилированный код Measuring Master (MM) 1.9.4 и MeasureOn (MO) 2.0.1 с текущей реализацией GlmReaderAndroid, чтобы найти причины:
- Нестабильного подключения
- Не работающих точек отсчёта
- Не переключающихся типов измерений
- Потери данных замера

---

## 2. Источники

### Декомпилированные исходники
| Путь | Содержание |
|------|-----------|
| `Android/.gitignore-files/sources/MeasureOn_decompiled/` | MO — ~20700 Java файлов (JADX) |
| `Android/.gitignore-files/sources/decompiled-full/` | MM — ~3500 Java файлов (JADX) |

### Ключевые файлы MO (проанализированы полностью)
| Файл | Назначение |
|------|-----------|
| `com/bosch/ptmt/measron/bluetooth/impl/BLEConnection.java` | BLE GATT подключение (475 строк) |
| `com/bosch/ptmt/measron/bluetooth/impl/BTDeviceManagerBLEImpl.java` | Менеджер устройств, scan → connect → init (748 строк) |
| `com/bosch/ptmt/measron/bluetooth/impl/GLMDeviceController.java` | Контроллер устройства, init, sync, обработка сообщений (610 строк) |
| `com/bosch/ptmt/measron/utils/RemoteControlCommandsUtils.java` | Команды смены типа/точки отсчёта |
| `com/bosch/mtprotocol/glm100C/MtProtocolBLEImpl.java` | Реализация BLE протокола, SendThread, state machine |
| `com/bosch/mtprotocol/glm100C/message/edc/EDCOutputMessage.java` | Исходящее EDC-сообщение |
| `com/bosch/mtprotocol/glm100C/message/edc/EDCFrameFactory.java` | Создание фрейма из EDCOutputMessage |
| `com/bosch/mtprotocol/glm100C/frame/MtFrameByteWriter.java` | Сериализация фрейма в байты с CRC |
| `com/bosch/mtprotocol/util/Crc.java` | CRC8/CRC16/CRC32 |

### Ключевые файлы MM (проанализированы)
| Файл | Назначение |
|------|-----------|
| `com/bosch/measuringmaster/bluetooth/impl/BluetoothConnection.java` | RFCOMM подключение |
| `com/bosch/measuringmaster/bluetooth/impl/BTDeviceManagerImpl.java` | Менеджер устройств (RFCOMM) |
| `com/bosch/measuringmaster/bluetooth/impl/GLMDeviceController.java` | Контроллер устройства |
| `com/bosch/measuringmaster/bluetooth/impl/BLEConnection.java` | BLE подключение (MM тоже поддерживает BLE) |

### Текущий код GlmReaderAndroid
| Файл | Содержание |
|------|-----------|
| `GlmReaderAndroid/.../ble/GlmBleManager.kt` | Подключение и отправка команд |
| `GlmReaderAndroid/.../protocol/BlePacketParser.kt` | Парсинг входящих пакетов |

---

## 3. Транспортный уровень — ГЛАВНОЕ РАСХОЖДЕНИЕ

### MO использует BLE GATT
```
device.connectGatt(context, false, callback, TRANSPORT_LE)
→ onServicesDiscovered()
→ найти сервис 02A6C0D0-... или 0000fde8-...
→ найти characteristic 02A6C0D1-... или 02A6C0D2-...
→ descriptor.setValue(ENABLE_INDICATION_VALUE)
→ writeDescriptor()
→ onDescriptorWrite(status=0) → STATE_CONNECTED
→ ТОЛЬКО ПОТОМ: init протокола
```

### MM использует RFCOMM (Classic Bluetooth)
```
device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
→ socket.connect()
→ inputStream/outputStream
→ init протокола (без bonding!)
```

### GlmReaderAndroid — ГИБРИД С ПРОБЛЕМОЙ
```
device.createRfcommSocketToServiceRecord(SPP_UUID)  // ← RFCOMM как MM
→ socket.connect()
→ device.createBond()  // ← ← ← ПРОБЛЕМА! MM НЕ делает bonding!
→ Thread.sleep(500)
→ sendInit()
```

### Ключевое отличие MM vs наш код

| Параметр | MM RFCOMM | MO BLE GATT | GlmReaderAndroid |
|----------|-----------|-------------|------------------|
| Метод подключения | `createInsecureRfcommSocketToServiceRecord()` | `connectGatt()` | `createRfcommSocketToServiceRecord()` |
| Bonding | **НЕ делает** | Требуется до подключения | **Делаем ПОСЛЕ** ❌ |
| UUID | SPP `00001101-...` | N/A | SPP `00001101-...` ✅ |
| ADR-010 Channel 5 | N/A | N/A | **Не используем** ❌ |

**Вывод:** MM использует `createInsecureRfcommSocketToServiceRecord` — это insecure socket БЕЗ bonding. Мы используем `createRfcommSocketToServiceRecord` (secure) + bonding после подключения. Это может вызывать разрывы.

---

## 4. Последовательность инициализации

### MO — полная цепочка
```
1. BTDeviceManagerBLEImpl.connect(device)
2. BLEConnection.openConnection()
   → setState(STATE_CONNECTING)
   → device.connectGatt(context, false, callback, TRANSPORT_LE)
   → Timeout check через 1000ms
3. onConnectionStateChange(newState=CONNECTED)
   → discoverServices()
4. onServicesDiscovered()
   → Найти сервис (MIRACULIX или MIRACULIX2)
   → Найти characteristic
   → setCharacteristicIndication() или setCharacteristicNotification()
5. onDescriptorWrite(status=0)
   → setState(STATE_CONNECTED)
6. BTDeviceManagerBLEImpl.onConnectionStateChanged(STATE_CONNECTED)
   → Через 100ms: setupDeviceController()
7. GLMDeviceController.init(connection, bluetoothDevice)
   → protocol = new MtProtocolBLEImpl()
   → protocol.addObserver(this)
   → protocol.setTimeout(500)
   → protocol.initialize(connection) → EVENT_INITIALIZE_SLAVE → SLAVE_LISTENING
   → turnAutoSyncOn()
8. turnAutoSyncOn()
   → EDCOutputMessage: devMode=0, syncControl=1, keypadBypass=0
   → protocol.sendMessage()
```

### MM — полная цепочка (RFCOMM)
```
1. BTDeviceManagerImpl.connect(device)
2. BluetoothConnection.openConnection()
   → device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
   → socket.connect()
3. initCurrentDevice()
4. GLMDeviceController.init(connection, bluetoothDevice)
   → protocol = new MtProtocolImpl() (RFCOMM версия)
   → protocol.addObserver(this)
   → protocol.setTimeout(500)
   → protocol.initialize(connection)
   → turnAutoSyncOn()
```

### GlmReaderAndroid — текущая
```
1. device.createRfcommSocketToServiceRecord(SPP_UUID)
2. socket.connect()
3. device.createBond()  // ← ← ← ПРОБЛЕМА
4. Thread.sleep(500)
5. sendInit()  // EDCOutputMessage devMode=0, syncControl=1 ✅
6. Thread.sleep(500)
7. sendGetSettings()  // GetSettingsMessage ✅
8. readThread.start()
```

### Расхождения

| Шаг | MO | MM | GlmReaderAndroid | Статус |
|-----|----|----|-----------------|--------|
| Подключение | connectGatt() | createInsecureRfcommSocket() | createRfcommSocketToServiceRecord() | ⚠️ Secure vs Insecure |
| Bonding | До (системное) | **НЕТ** | **После** ❌ | ❌ |
| Sleep после connect | 100ms (MO) | Нет | **500ms** | ⚠️ |
| Init команда | EDCOutputMessage devMode=0, syncCtrl=1 | Такая же | Такая же | ✅ |
| Вторая команда | Нет | GetSettings | **GetSettings** | ✅ |
| Read loop | GATT callback | ReceiveThread | Thread + InputStream | ⚠️ |

---

## 5. Протокол — отправка команд

### MO — SendThread с очередью
```java
// MtProtocolBLEImpl.SendThread.run()
while (!interrupted) {
    MtMessage msg = outgoingDeque.takeFirst();  // Блокирующее ожидание
    while (state != MASTER_READY) { wait(); }   // Ждём готовности
    stateMachine.processEvent(EVENT_SEND_START);
    MtBaseFrame frame = frameFactory.createFrame(msg);
    byte[] bytes = MtFrameByteWriter(frame).write(buffer);
    connection.write(bytes);
    flagIsBLEWriteFinished = false;
    while (!flagIsBLEWriteFinished) { wait(); } // Ждём подтверждения записи!
    stateMachine.processEvent(EVENT_SEND_FINISH);
}
```

### GlmReaderAndroid — прямая отправка
```kotlin
private fun sendCommand(payload: ByteArray, name: String) {
    val crc = BlePacketParser.calcCrc8(payload)
    val full = payload + crc.toByte()
    outputStream?.write(full)     // ← Отправили и забыли
    outputStream?.flush()
    // Нет ожидания подтверждения!
    // Нет state machine!
}
```

### Расхождения

| Параметр | MO | MM | GlmReaderAndroid | Статус |
|----------|----|----|-----------------|--------|
| Очередь команд | LinkedBlockingDeque | SendThread + ReceiveThread | **Нет** ❌ |
| Ожидание готовности | wait() пока MASTER_READY | FSM state machine | **Нет** ❌ |
| Подтверждение записи | flagIsBLEWriteFinished | Write callback | **Нет** ❌ |
| Timeout на ответ | 500ms MtTimer | 500ms | **Нет** ❌ |
| Retry при ошибке | offerFirst + sleep(200ms) + timeout | AutoConnectThread | **Нет** ❌ |

---

## 6. Команды — побайтовое сравнение

### Init / AutoSync ON
```
MO: EDCOutputMessage { devMode=0, syncControl=1, keypadBypass=0, remoteCtrlData=0 }
    → EDCFrameFactory.createFrame():
      FrameMode=0xC0, Command=0x55
      Headers byte: [syncControl:1][keypadBypass:1][devMode:6] = [1][0][000000] = 0x40
      RemoteCtrl byte: 0x00
    → MtFrameByteWriter.write(): CRC8([C0,55,02,40,00]) = вычисляется
    → Итог: C0 55 02 40 00 [CRC8]

GlmReader: C0 55 02 40 00 → calcCrc8() → [CRC8]

✅ СОВПАДАЕТ
```

### Set Measurement Type (devMode=60)
```
MO: EDCOutputMessage { devMode=60, syncControl=1, keypadBypass=0, remoteCtrlData=EDCMode }
    → Headers: [1][0][111100] = [1][0][60] = 0b10111100 = 0xBC
    → RemoteCtrl: EDCMode (0-26)
    → Итог: C0 55 02 BC [EDCMode] [CRC8]

GlmReader: C0 55 02 BC [mode] → calcCrc8() → [CRC8]

✅ СОВПАДАЕТ
```

### Set Reference Point (devMode=62)
```
MO: EDCOutputMessage { devMode=62, syncControl=1, keypadBypass=0, remoteCtrlData=refLevel }
    → Headers: [1][0][111110] = 0xBE
    → RemoteCtrl: refLevel (0-3)
    → Итог: C0 55 02 BE [refLevel] [CRC8]

GlmReader: C0 55 02 BE [ref] → calcCrc8() → [CRC8]

✅ СОВПАДАЕТ
```

### Remote Trigger
```
MO: EDCDoRemoteTriggerButtonMessage → EDCDoRemoteFrameFactory
    → FrameMode=0xC0, Command=0x56, PayloadLen=0x01, Payload=0x00
    → CRC8([C0,56,01,00]) = 0x1E
    → Итог: C0 56 01 00 1E

GlmReader: C0 56 01 00 → calcCrc8() → [CRC8]

✅ СОВПАДАЕТ (если CRC=0x1E)
```

### Вывод по командам
**Все команды побайтово совпадают.** Проблема НЕ в содержимом команд.

---

## 7. CRC8 — верификация

### MO Crc.java
```java
calcCrc8(byte[] data) → init=0xAA, poly=-90 (0xA6), побитовый XOR
```

### GlmReaderAndroid BlePacketParser
```kotlin
calcCrc8(data: ByteArray) → init=0xAA, poly=0xA6, побитовый XOR
```

**✅ Полное совпадение.**

---

## 8. Приём данных

### MO — GATT callback → MtFrameByteReader
```
onCharacteristicChanged(data)
→ MtProtocolBLEImpl.onBLECharacteristicChanged(data)
→ startTimeoutTimer()
→ stateMachine: SLAVE_LISTENING → SLAVE_RECEIVING
→ frameReader.append(byte) побайтово
→ if (isFrameRcvComplete()) → checkMessageComplete()
→ if (comStatus == 0) → messageFactory.createMessage(frame)
→ checkTransactionCompleted()
→ notifyObservers(MtProtocolReceiveMessageEvent)
→ GLMDeviceController.onEvent() → handleEDCMessage()
```

### MM — ReceiveThread (для RFCOMM)
```
ReceiveThread.run():
→ inputStream.read(buffer)
→ frameReader.append(byte) побайтово
→ if (isFrameRcvComplete()) → checkMessageComplete()
→ notifyObservers()
```

### GlmReaderAndroid — Thread + processBuffer
```
readLoop():
→ inputStream.read(tempBuf, 1024)
→ readBuffer.write(chunk)
→ processBuffer():
  → Найти 0xC0 0x55 в буфере
  → Прочитать payloadLen из байта [2]
  → expectedSize = 3 + payloadLen + 1
  → Если буфер >= expectedSize → взять пакет
  → onDataReceived(fullPacket)
  → BlePacketParser.parse(fullPacket) ← CRC НЕ проверяется!
```

### Расхождения

| Параметр | MO | MM | GlmReaderAndroid | Статус |
|----------|----|----|-----------------|--------|
| Побайтовый парсинг | MtFrameByteReader (FSM) | MtFrameByteReader (FSM) | Ручной поиск 0xC0 0x55 | ⚠️ |
| CRC валидация | checkMessageComplete() | checkMessageComplete() | **Не проверяется** ❌ |
| Heartbeat обработка | DevModeRef=0xF1 | DevModeRef=0xF1 | **Не обрабатывается** ❌ |
| Multi-frame пакет | Новые MtFrameByteReader | Новые MtFrameByteReader | ❌ Может сломаться |

---

## 9. Diagnosis — почему не работают тип/точка отсчёта

### Команды правильные ✅ — значит проблема в доставке

**Вероятные причины:**

1. **Рулетка не в режиме приёма команд** — без state machine мы можем отправлять когда рулетка в SLAVE_LISTENING (ожидает данные, не команды). Нужно переключить в MASTER_READY.

2. **Нет подтверждения записи** — outputStream.write() возвращает управление сразу, но рулетка может ещё не приняла данные. В MO ждёт `flagIsBLEWriteFinished`.

3. **Команды отправляются слишком быстро** — Thread.sleep(500) между Init и GetSettings. В MO между командами истории — `Thread.sleep(50L)`. Но между Init и следующей командой — нет sleep, только state machine transition.

4. **Bonding разрывает сессию** — MM использует insecure socket БЕЗ bonding. Мы делаем bonding после подключения — это может вызвать disconnect.

---

## 10. Итоговый план исправлений

### Критические (P0)
1. **Убрать bonding для RFCOMM** — MM не делает bonding. Заменить на `createInsecureRfcommSocketToServiceRecord()`.
2. **Добавить SendThread** — очередь команд + ожидание отправки по одной.
3. **Добавить state machine** — SLAVE_LISTENING → MASTER_READY → SENDING → RECEIVING.

### Важные (P1)
4. **Добавить timeout timer** — 500ms как в MO/MM.
5. **Добавить обработку heartbeat** — пакет `C0 55 02 F1` — это ответ рулетки, не ошибка.
6. **Добавить retry-логику** — при ошибке подключения 2-3 повторные попытки.

### Желательные (P2)
7. **Попробовать Channel 5 через рефлексию** — ADR-010 рекомендует, но MM использует SPP UUID.
8. **Добавить CRC валидацию в парсер** — `parseWithCrc()` вместо `parse()`.
9. **Улучшить processBuffer()** — побайтовый FSM как MtFrameByteReader.

---

*Документ создан: 6 апреля 2026 г.*
*На основе анализа: MeasureOn 2.0.1 (BLE GATT) и Measuring Master 1.9.4 (RFCOMM)*
