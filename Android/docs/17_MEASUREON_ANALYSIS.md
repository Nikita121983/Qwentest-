# MeasureOn 2.0.1 — Анализ отличий от Measuring Master

## Общая информация

| Параметр | Measuring Master | MeasureOn |
|----------|-----------------|-----------|
| **Версия** | 1.9.4 (build 169) | 2.0.1 |
| **Пакет** | `com.bosch.measuringmaster` | `com.bosch.ptmt.measron` |
| **Java файлов** | ~3500 | ~20700 |
| **Ошибки jadx** | 6 | 141 |
| **Архитектура** | Activity + Services | MVVM + Hilt + Kotlin |
| **Хранение** | JSON файлы | Room DB + Cloud Sync |
| **Подписка** | Нет | Да (Subscription) |

---

## 1. BLE UUID — MeasureOn

### Сервисы и характеристики

| Имя | UUID | Назначение |
|-----|------|-----------|
| **MIRACULIX_SERVICE** | `02A6C0D0-0451-4000-B000-FB3210111989` | Сервис GLM (старый) |
| **MIRACULIX_CHAR_LEGACY** | `02A6C0D1-0451-4000-B000-FB3210111989` | Характеристика (совпадает с ketan репо!) |
| **MIRACULIX2_SERVICE** | `0000fde8-0000-1000-8000-00805f9b34fb` | Сервис GLM (новый, BLE standard) |
| **MIRACULIX2_CHAR** | `02A6C0D2-0451-4000-B000-FB3210111989` | Характеристика (новый) |
| **MIRX2_INFO_SERVICE** | `02A6C1A0-0451-4000-B000-FB3210111989` | Сервис информации об устройстве |
| **LINK_ENCRYPTED_TEXT** | `02a6c1a7-0451-4000-b000-fb3210111989` | Зашифрованный текст (имя устройства?) |
| **DESCRIPTOR_UUID** | `00002902-0000-1000-8000-00805f9b34fb` | CCCD дескриптор (notifications) |

### Ключевое отличие

**Measuring Master** использует **RFCOMM (Bluetooth Classic)**:
- Порт `0x0005` для GLM 50C
- Команды: `C0 40 00 EE`

**MeasureOn** использует **BLE (GATT)**:
- Характеристика `02A6C0D1-...` для уведомлений
- Подписка на измерения через `start_notify()`

---

## 2. Модель измерения — MTMeasurement

### MeasureOn

```java
public class MTMeasurement implements DataItem, Serializable {
    String uuid;                    // UUID.randomUUID()
    String name;                    // Имя замера
    String createdDate;             // RFC3339
    String modifiedDate;            // RFC3339
    Double value;                   // Результат
    MTBaseUnit unit;                // Единицы
    MTMeasurementType measurementType;
    MTMeasurementMode measurementMode;
    MTDeviceOperatorMode deviceOperatorMode;
    MTDeviceReference deviceReference;
    Map<String, MTMeasurement> subMeasurements;  // Подзамеры!
    String connectedBTDeviceName;   // Имя рулетки
    String modelType = "measurement";
}
```

### Отличия от Measuring Master

| Поле | Measuring Master | MeasureOn |
|------|-----------------|-----------|
| **ID** | `long id` | `String uuid` (UUID.randomUUID) |
| **Значения** | `value1, value2, value3` | `Double value` + `subMeasurements` |
| **Операторы** | `int operator` (0-7) | `MTDeviceOperatorMode` enum |
| **Время** | `Date createdDate` | `String createdDate` (RFC3339) |
| **Привязка** | `Map<String, List<String>> projectIdentifierMap` | Привязка к Sketch/Wall/Door |
| **Имя рулетки** | `Long deviceId` | `String connectedBTDeviceName` |

---

## 3. Хранение данных

### Measuring Master
- JSON файлы на файловой системе
- `projects.json` + `{project_id}/project.json`
- Нет миграций, нет транзакций

### MeasureOn
- **Room Database** (SQLite)
- **Cloud Sync** (Bosch Cloud)
- **Hilt DI** (Dagger)
- **WorkManager** для фоновых задач
- Подписки (Subscription) — платные функции

---

## 4. Почему MeasureOn не подходит

| Проблема | Описание |
|----------|----------|
| **Нет raw-лога** | Замеры сразу привязываются к геометрии (Sketch/Wall) |
| **Подписка** | Платные функции для экспорта |
| **Сложность** | 20K+ файлов, MVVM + Hilt + Kotlin |
| **Cloud Sync** | Зависимость от Bosch Cloud |
| **Нет списка замеров** | Концепция "лога" убрана |

---

## 5. Что использовать для нашего приложения

### Протокол: BLE (как MeasureOn)

**Причина:** GLM 50C поддерживает оба протокола, но BLE:
- Новее (MeasureOn использует его)
- Работает на Windows через `Windows.Devices.Bluetooth`
- UUID известны:
  - Service: `02A6C0D0-0451-4000-B000-FB3210111989`
  - Characteristic: `02A6C0D1-0451-4000-B000-FB3210111989`

### Модель данных: гибрид

```csharp
public record Measurement(
    Guid Id,
    string Name,
    double Value,           // Основной результат
    DateTime Timestamp,     // Время замера (не экспорта!)
    MeasurementType Type,   // Distance, Area, Volume, Angle
    string? DeviceName,     // Имя рулетки
    List<SubMeasurement> SubMeasurements,  // Подзамеры
    string? ProjectId,      // Привязка к проекту
    string? Note            // Заметка
);

public record SubMeasurement(
    string Name,
    double Value,
    DateTime Timestamp
);
```

### Хранение: SQLite + JSON backup

- **SQLite** — основная БД (Room аналог)
- **JSON backup** — для восстановления
- **Миграции** — версионирование схемы

---

*Дата: 3 апреля 2026 г.*
*APK: MeasureOn 2.0.1, com.bosch.ptmt.measron*
