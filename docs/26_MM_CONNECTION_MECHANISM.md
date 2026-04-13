# Механизм подключения Measuring Master и MeasureOn — полный разбор

**Дата:** 12 апреля 2026 г.  
**Источники:** 
- MM: `Android/.gitignore-files/sources/decompiled/sources/com/bosch/measuringmaster/bluetooth/impl/`
- MO: `Android/.gitignore-files/sources/MeasureOn_decompiled/sources/com/bosch/ptmt/measron/bluetooth/impl/`

---

## 1. Архитектура подключения MM

```
┌─────────────────────────────────────────────────────────┐
│                  Приложение (UI)                         │
├─────────────────────────────────────────────────────────┤
│              BTDeviceManagerImpl                         │
│  - BroadcastReceiver (системные события BT)              │
│  - AutoConnectThread (автоподключение)                   │
│  - BluetoothConnection (RFCOMM сокет)                    │
│  - Listener pattern (CopyOnWriteArrayList)               │
├─────────────────────────────────────────────────────────┤
│              GLMDeviceController                         │
│  - MtProtocolImpl (протокол RFCOMM)                     │
│  - MtProtocolBLEImpl (протокол BLE GATT)                │
│  - Observer pattern (addObserver)                        │
│  - timeout = 500ms                                       │
├─────────────────────────────────────────────────────────┤
│              MtConnection (интерфейс)                    │
│  - openConnection() / closeConnection()                  │
│  - read() / write()                                      │
│  - isOpen()                                              │
└─────────────────────────────────────────────────────────┘
```

---

## 2. Автоматическое подключение — ГЛАВНАЯ ФИЧА

### 2.1 BroadcastReceiver слушает 6 системных событий

```java
// BTDeviceManagerImpl.register()
context.registerReceiver(this, new IntentFilter("android.bluetooth.device.action.FOUND"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.device.action.ACL_CONNECTED"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED"));
context.registerReceiver(this, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
```

### 2.2 Реакция на события

| Событие | Действие MM |
|---------|-------------|
| `ACL_CONNECTED` | Запоминает устройство как `currentDevice`, уведомляет слушателей |
| `ACL_DISCONNECTED` | Сбрасывает `currentDevice`, вызывает `updateDeviceList()` |
| `STATE_CHANGED` (BT включился) | Обновляет статус, **запускает автоподключение** |
| `BOND_STATE_CHANGED` (bonded, state=12) | `connect(device)` — подключается! |
| `DEVICE_FOUND` (scan) | Добавляет в `deviceList` если имя содержит "Bosch" |
| `DISCOVERY_FINISHED` | `updateDeviceList()` — переносит discovered в основной список |

### 2.3 AutoConnectThread — бесконечное переподключение

```java
// AutoConnectThread.run()
while (running) {
    if (reconnect) {
        connect();  // btDeviceManager.connect(device)
    }
    sleep(2000);  // Каждые 2 секунды попытка
}

// connect():
if (!btDeviceManager.isConnected() && btDeviceManager.isBluetoothEnabled() && reconnect) {
    btDeviceManager.connect(device);
}
```

**Ключевое:** MM **автоматически подключается** к сохранённому устройству:
- Когда BT включился → запускает AutoConnectThread
- Когда устройство отключилось → через 5 секунд запускает AutoConnectThread снова
- Когда bonded → сразу подключается

---

## 3. Процесс подключения — пошагово

```
1. Пользователь выбирает устройство (или auto-connect)
   ↓
2. BTDeviceManagerImpl.connect(device):
   - disconnect()  // Закрывает предыдущее
   - cancelDiscovery()
   - new BluetoothConnection(device)
   - connection.addObserver(this)
   - connection.openConnection()
   - stopAutoConnect()  // Останавливает автоподключение
   ↓
3. BluetoothConnection.openConnection():
   - setState(1)  // STATE_CONNECTING
   - new ConnectThread(device) → start()
   ↓
4. ConnectThread.run():
   - socket = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
   - socket.connect()  // Блокирующий вызов
   - if success → connected(socket, device)
   ↓
5. BluetoothConnection.connected():
   - bluetoothSocket = socket
   - setState(2)  // STATE_CONNECTED
   - notifyObservers()
   ↓
6. BTDeviceManagerImpl.onConnectionStateChanged():
   - setupDeviceController()
   ↓
7. setupDeviceController():
   - if GLM device:
     - glmDeviceController = new GLMDeviceController(context, handler)
     - glmDeviceController.init(connection, device)
   ↓
8. GLMDeviceController.init():
   - protocol = new MtProtocolImpl()  // или MtProtocolBLEImpl()
   - protocol.addObserver(this)
   - protocol.setTimeout(500)
   - protocol.initialize(mtConnection)
   - turnAutoSyncOn()
   ↓
9. turnAutoSyncOn():
   - EDCOutputMessage: devMode=0, syncControl=1
   - protocol.sendMessage()
   ↓
10. Рулетка начинает слать данные
```

### 9. Восстановление после отключения:
```
ACL_DISCONNECTED broadcast → startAutoConnect() → AutoConnectThread
→ Каждые 2 секунды: if (!isConnected()) connect()
→ Через 5 секунд после неудачного подключения: restart auto connect
```

---

## 4. Ключевые детали механизма

### 4.1 Socket

```java
// BluetoothConnection.ConnectThread
bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(
    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
);
```

- **Insecure** — без bonding
- **SPP UUID** — стандартный Serial Port Profile
- **ConnectThread** имеет приоритет 1 (минимальный)

### 4.2 Observer pattern

```java
// BluetoothConnection
private final List<MtAsyncConnection.MTAsyncConnectionObserver> observers = new ArrayList();

void addObserver(observer) { observers.add(observer); }
void removeObserver(observer) { observers.remove(observer); }
void notifyObservers() {
    for (observer : observers) observer.onConnectionStateChanged(this);
}
```

### 4.3 Thread management

| Поток | Назначение | Приоритет |
|-------|-----------|-----------|
| ConnectThread | Подключение сокета | 1 (минимальный) |
| AutoConnectThread | Автопереподключение | default |
| ReadThread (в MtProtocolImpl) | Чтение из сокета | default |
| SendThread (в MtProtocolImpl) | Очередь команд | default |

### 4.4 State machine подключения

```
STATE_DISCONNECTED (0) → STATE_CONNECTING (1) → STATE_CONNECTED (2)
                                                          ↓
                                                  ACL_DISCONNECTED
                                                          ↓
                                                  STATE_DISCONNECTED (0)
                                                          ↓
                                                  AutoConnectThread → STATE_CONNECTING
```

---

## 5. MM vs MO — одинаковый механизм

| Аспект | MM (RFCOMM) | MO (BLE GATT) | Совпадает? |
|--------|-------------|---------------|------------|
| AutoConnectThread | ✅ 2 сек цикл | ✅ 2 сек цикл | ✅ Идентично |
| BroadcastReceiver | ✅ 6 событий | ✅ 5 событий | ✅ Почти |
| Singleton | ❌ newInstance() | ✅ getInstance() | ⚠️ |
| lastKnownDevice | ❌ | ✅ сохраняется | ⚠️ |
| GLMDeviceController.init() | ✅ | ✅ | ✅ |
| turnAutoSyncOn() | ✅ | ✅ | ✅ |

**ВЫВОД:** MM и MO используют ОДИН И ТОТ ЖЕ механизм подключения. Разница только в транспорте.

---

## 6. Сравнение с нашей реализацией

| Аспект | MM/MO | Наш код | Совпадает? |
|--------|-------|---------|------------|
| Socket | `createInsecureRfcommSocketToServiceRecord()` | ✅ То же | ✅ |
| Bonding | НЕ делает | ✅ НЕ делаем | ✅ |
| Observer pattern | CopyOnWriteArrayList/Set | ✅ CopyOnWriteArrayList | ✅ |
| Автоподключение | ✅ AutoConnectThread | ❌ Нет | ❌ |
| BroadcastReceiver | ✅ Системные события BT | ❌ Нет | ❌ |
| ACL_DISCONNECTED | → AutoConnect | → Ничего | ❌ |
| lastKnownDevice | MO: ✅ | ❌ Нет | ❌ |

---

## 7. Выбранный вариант — автоподключение при открытом приложении

> AutoConnectThread запускается когда приложение открыто.
> Каждые 2 секунды пытается подключиться к сохранённому устройству.
> При закрытии приложения (onDestroy MainActivity) — stopAutoConnect().
> BroadcastReceiver слушает ACL_DISCONNECTED → restart auto-connect.

---

## 8. Почему MM/MO подключаются автоматически

1. При старте → `startAutoConnect(savedMac, savedName)`
2. `AutoConnectThread` цикл каждые 2 сек: `connect()`
3. Рулетка включилась → `connect()` succeeds
4. `ACL_CONNECTED` broadcast → подтверждение
5. Отключился → снова `AutoConnectThread`

---

*Документ создан: 12 апреля 2026 г.*
*На основе: Measuring Master 1.9.4 + MeasureOn 2.0.1 декомпилированные исходники*
