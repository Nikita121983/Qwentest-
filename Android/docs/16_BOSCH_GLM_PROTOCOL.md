# Bosch GLM 50C/100C — Протокол подключения

## Источники

| Источник | URL |
|----------|-----|
| ketan/Bosch-GLM50C-Rangefinder | https://github.com/ketan/Bosch-GLM50C-Rangefinder |
| philipptrenz/BOSCH-GLM-rangefinder | https://github.com/philipptrenz/BOSCH-GLM-rangefinder |
| lz1asl/CaveSurvey#150 | https://github.com/lz1asl/CaveSurvey/issues/150 |
| EEVBlog | https://www.eevblog.com/forum/projects/hacking-the-bosch-glm-20-laser-measuring-tape/msg1331649/ |

---

## 1. Два разных протокола

### Протокол 1: Bluetooth Classic (RFCOMM) — GLM 50C/100C

**Используется:** Bosch Measuring Master, MeasureOn
**Транспорт:** Bluetooth Classic (SPP/RFCOMM)
**Библиотека:** pybluez (Python), System.IO.Ports (C#)

#### Команды

| Команда | Байты | Описание |
|---------|-------|----------|
| `measure` | `C0 40 00 EE` | Запрос измерения |
| `laser_on` | `C0 41 00 96` | Включить лазер |
| `laser_off` | `C0 42 00 1E` | Выключить лазер |
| `backlight_on` | `C0 47 00 20` | Включить подсветку |
| `backlight_off` | `C0 48 00 62` | Выключить подсветку |

#### Формат пакетов

**Отправка (host → device):**
```
[start_byte][command][length]([data])[checksum]
```

**Получение (device → host):**
```
[status][length][data...][checksum]
```

**Статусы:**
| Байт | Значение |
|------|----------|
| 0 | `ok` |
| 1 | communication timeout |
| 3 | checksum error |
| 4 | unknown command |
| 5 | invalid access level |
| 8 | hardware error |
| 10 | device not ready |

**Парсинг ответа на измерение:**
```python
# Ответ: 00 04 13 0E 00 00 32
status = data[0]       # 0x00 = ok
length = data[1]       # 0x04 = 4 байта данных
distance = struct.unpack("<H", data[2:4])[0]  # 0x0E13 = 3603 мм
```

**Порты RFCOMM:**
- GLM 50C: порт `0x0005`
- GLM 100C: порт `0x0001`

---

### Протокол 2: BLE (GATT) — GLM 50C (альтернативный)

**Используется:** ketan/Bosch-GLM50C-Rangefinder (BLE версия)
**Транспорт:** Bluetooth Low Energy
**Библиотека:** bleak (Python), Windows.Devices.Bluetooth (C#)

#### UUID характеристики

```
Characteristic UUID: 02a6c0d1-0451-4000-b000-fb3210111989
```

#### Формат данных

**Команда инициализации:**
```python
await client.write_gatt_char(charUUID, bytearray([0xc0, 0x55, 0x02, 0x01, 0x00, 0x1a]), True)
```

**Уведомления (измерения):**
```
Префикс: C0 55 10 06
Данные: байты 7-10 = расстояние (float32 little-endian, в метрах)
```

**Парсинг:**
```python
if data.startswith(b'\xc0\x55\x10\x06'):
    distance_meters = struct.unpack('<f', data[7:11])[0]
    distance_mm = round(distance_meters * 1000)
```

---

## 2. Сравнение подходов

| Параметр | RFCOMM (Classic) | BLE (GATT) |
|----------|-----------------|------------|
| **Подключение** | Pair → RFCOMM порт | Connect → Notify |
| **Команды** | Отправка → ответ | Write → Notification |
| **Режим** | Запрос-ответ | Подписка на уведомления |
| **Windows** | System.IO.Ports | Windows.Devices.Bluetooth |
| **Сложность** | Проще | Сложнее |
| **Документация** | Больше (philipptrenz) | Меньше (ketan) |

---

## 3. Что использовать для Windows приложения

### Рекомендация: RFCOMM (Bluetooth Classic)

**Причины:**
1. Больше документации
2. Проще реализация
3. Работает с GLM 50C и 100C
4. C# имеет встроенную поддержку через `Windows.Devices.Bluetooth.Rfcomm`

### Архитектура

```
[Windows Forms UI]
       ↓
[GLMController.cs] ← отправка команд, парсинг ответов
       ↓
[RfcommConnection.cs] ← RFCOMM соединение
       ↓
[Bosch GLM 50C]
```

### Ключевые классы C#

```csharp
// RfcommConnection.cs
// - Поиск устройств по имени "GLM"
// - Подключение к RFCOMM порту
// - Отправка/приём байтов

// GLMController.cs
// - SendCommand(Command.Measure)
// - ParseResponse(byte[] data) → float distance
// - LaserOn/Off, BacklightOn/Off

// Measurement.cs
// - record Measurement(float Value, DateTime Timestamp, string Name)
```

---

## 4. Известные проблемы

### GLM 50C — два режима Bluetooth

Устройство поддерживает **оба** протокола:
- Bluetooth Classic (для Measuring Master)
- BLE (для MeasureOn?)

**Решение:** Попробовать RFCOMM первым. Если не работает — BLE.

### MAC-адрес устройства

Для быстрого подключения нужно знать MAC-адрес.
**Решение:** Сканирование при первом подключении → сохранение.

### Две рулетки

У пользователя 2 рулетки GLM 50C.
**Решение:** Подключение по MAC-адресу, различение по имени.

---

## 5. Пример кода C# (RFCOMM)

```csharp
using Windows.Devices.Bluetooth;
using Windows.Devices.Bluetooth.Rfcomm;
using Windows.Networking.Sockets;
using Windows.Storage.Streams;

public class GlmRfcommConnection
{
    private StreamSocket _socket;
    private DataReader _reader;
    private DataWriter _writer;

    public async Task<bool> ConnectAsync(string macAddress)
    {
        var device = await BluetoothDevice.FromBluetoothAddressAsync(ParseMac(macAddress));
        var services = await device.GetRfcommServicesAsync();
        var service = services.Services[0];

        _socket = new StreamSocket();
        await _socket.ConnectAsync(service.ConnectionHostName, service.ConnectionServiceName);

        _reader = new DataReader(_socket.InputStream);
        _writer = new DataWriter(_socket.OutputStream);
        return true;
    }

    public async Task<float> MeasureAsync()
    {
        // C0 40 00 EE
        _writer.WriteByte(0xC0);
        _writer.WriteByte(0x40);
        _writer.WriteByte(0x00);
        _writer.WriteByte(0xEE);
        await _writer.StoreAsync();

        // Read response
        await _reader.LoadAsync(7);
        var status = _reader.ReadByte();
        var length = _reader.ReadByte();
        var d1 = _reader.ReadByte();
        var d2 = _reader.ReadByte();

        if (status != 0) return -1;

        return BitConverter.ToUInt16(new[] { d1, d2 }, 0); // мм
    }

    private ulong ParseMac(string mac) => ...;
}
```

---

*Дата: 3 апреля 2026 г.*
