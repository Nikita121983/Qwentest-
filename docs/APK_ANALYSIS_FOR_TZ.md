# Анализ APK по ТЗ MeasuringAssistant

**Дата анализа:** 4 апреля 2026 г., 00:00 MSK
**ТЗ:** `Android/docs/02_TECHNICAL_SPEC.md` + `Android/docs/05_UNIFIED_SPEC.md`
**APK 1:** Measuring Master 1.9.4 (`com.bosch.measuringmaster`)
**APK 2:** MeasureOn 2.0.1 (`com.bosch.ptmt.measron`)

---

## 1. Сравнение функционала ТЗ vs Реальность

### 1.1. BLE подключение к Bosch GLM 50 C

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| Подключение по BLE | ✅ Требуется | ✅ RFCOMM (Classic BT) | ✅ BLE (GATT) | ✅ Оба работают |
| Инициализация соединения | ✅ | ✅ `C0 55 02 01` | ✅ `EDCOutputMessage.syncControl=1` | ✅ Реализовано |
| Приём измерений | ✅ | ✅ EDCInputMessage | ✅ EDCInputMessage | ✅ Идентично |
| Запуск измерения | ✅ | ✅ Measure команда | ✅ Remote Trigger | ✅ Реализовано |
| Переключение типа | ✅ | ✅ devMode=60 | ✅ devMode=60 | ✅ Идентично |
| Смена точки отсчёта | ✅ | ✅ devMode=62 | ✅ devMode=62 | ✅ Идентично |

**🔴 КРИТИЧНО ДЛЯ ТЗ:**
- ТЗ указывал UUID `0000fff0/fff1/fff2` — это **НЕВЕРНО**. Реальный UUID: `02A6C0D0/02A6C0D1`
- Measuring Master использует **RFCOMM** (Bluetooth Classic), НЕ BLE GATT
- MeasureOn использует **BLE GATT** — это правильный подход для ТЗ

### 1.2. Хранение данных

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| SQLite + JSON | ✅ | ❌ Только JSON | ✅ Room (SQLite) + JSON | ⚠️ Mo лучше |
| Автосохранение замера | ✅ | ⚠️ JSON файл | ✅ Room + JSON | ⚠️ MM ненадёжен |
| Уникальные ID | ✅ UUID | ❌ Нет (порядковый номер) | ✅ UUID | ✅ Mo лучше |
| Папка проекта | ✅ `/Project_XYZ/` | ✅ `/MeasuringMaster/projects/` | ✅ Room DB + files | ✅ Оба OK |

**🔴 БАГИ MM:**
- Замеры хранятся в ОДНОМ JSON файле проекта → краш = потеря ВСЕХ замеров
- Нет транзакций → повреждение файла при прерывании записи
- Дедупликация по значению → одинаковые замеры отбрасываются

**✅ РЕШЕНИЯ Mo:**
- Замеры в отдельных JSON файлах → краш = потеря одного замера
- Room для проектов → транзакции, целостность
- **НО:** Удаление группы удаляет файлы замеров навсегда (без корзины)

### 1.3. Время замера

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| Timestamp замера | ✅ ISO 8601 | ❌ Время экспорта | ✅ createdDate | ✅ Mo правильно |
| Время в экспорте | ✅ = время замера | ❌ = время файла | ✅ createdDate | ✅ Mo правильно |
| Часовой пояс | ✅ Europe/Moscow | ❌ Нет | ✅ Системный | ⚠️ Не явный |

**🔴 БАГ MM:** Время в XLS экспорте = время создания файла, не время замера!

### 1.4. Экспорт

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| XLSX экспорт | ✅ | ✅ Apache POI | ✅ Apache POI | ✅ Оба OK |
| CSV экспорт | ✅ | ✅ | ❌ Только XLS | ❌ Mo нет CSV |
| Фильтр на экспорт | ✅ export=true | ❌ Нет | ❌ Нет | ❌ Нет в обоих |
| Хронологический порядок | ✅ | ⚠️ Зависит от контекста | ⚠️ Зависит от группы | ❌ Оба ненадёжны |

**🔴 БАГИ:**
- Экспорт в Mo зависит от **текущей выбранной группы** → замеры из других групп НЕ попадут
- MM экспортирует **весь список**, но без фильтров
- Ни одно приложение не позволяет выбрать замеры для экспорта

### 1.5. Голосовые заметки

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| Запись аудио | ✅ | ❌ Нет | ❌ Нет | ❌ Нет в обоих |
| Whisper оффлайн | ✅ whisper.cpp | ❌ Нет | ❌ Нет | ❌ Нет |
| Словарь сокращений | ✅ | ❌ Нет | ❌ Нет | ❌ Нет |
| Привязка к замеру | ✅ | ❌ Нет | ❌ Нет | ❌ Нет |

**🟡 ВЫВОД:** Ни одно приложение Bosch не поддерживает голосовые заметки. Это полностью новая функция.

### 1.6. PDF интеграция

| Функция | ТЗ | Measuring Master | MeasureOn | Вывод |
|---------|----|-----------------|-----------|-------|
| Xodo Intent | ✅ | ❌ Нет | ❌ Нет | ❌ Нет |
| Чтение аннотаций | ✅ | ❌ Нет | ❌ Нет | ❌ Нет |
| Привязка к зоне | ✅ | ❌ Нет | ⚠️ Canvas (геометрия) | ⚠️ Только Mo |

**🟡 ВЫВОД:** PDF-интеграция — полностью новая функция. MeasureOn имеет Canvas (геометрию), но это не то же самое, что аннотации в PDF.

---

## 2. Идеи для заимствования

### 2.1. ✅ Взять из MeasureOn

| Идея | Описание | Где в коде | Применение в GlmReader |
|------|----------|-----------|----------------------|
| **Room Database** | SQLite с транзакциями | `CloudDatabase.kt` | ✅ Основная БД |
| **UUID для замеров** | Уникальные идентификаторы | `MTMeasurement.uuid` | ✅ `measurement_id` |
| **Группы замеров** | Sessions/Days/TimeIntervals | `MeasurementGroupModel` | ✅ Группировка по дням |
| **Cloud Sync** | Синхронизация с сервером | `CloudSyncScheduler.kt` | ⚠️ Syncthing вместо облака |
| **Merge Conflict UI** | Разрешение конфликтов | `ManualSyncViewModel.kt` | ✅ Для Syncthing конфликтов |
| **Soft Delete** | Корзина для проектов | `ProjectDeleteDao` | ✅ Для замеров тоже |
| **Feature Flags** | Pro/Free функции | `FeatureManager.kt` | ❌ Не нужно (open-source) |
| **EU Data Act** | Отправка данных | `EUDataActRepository` | ❌ Не нужно |

### 2.2. ✅ Взять из Measuring Master

| Идея | Описание | Где в коде | Применение в GlmReader |
|------|----------|-----------|----------------------|
| **Простой список замеров** | Плоский список без геометрии | `MeasurementListActivity` | ✅ Основной UI |
| **Подписи к замерам** | Именование замеров | `editMeasurementName()` | ✅ Заметки к замерам |
| **Экспорт всего списка** | Без фильтрации по группам | `MeasurementXLSExportService` | ✅ Raw-лог экспорт |
| **Bluetooth Classic fallback** | RFCOMM как запасной | `BluetoothConnection.java` | ❌ Только BLE |

### 2.3. ❌ НЕ брать (антипаттерны)

| Антипаттерн | Проблема | Где | Решение в GlmReader |
|-------------|----------|-----|-------------------|
| **JSON без транзакций** | Потеря при краше | MM: `MeasurementRepositoryImpl` | ✅ SQLite |
| **Удаление группы = удаление замеров** | Безвозвратная потеря | Mo: `deleteMeasurementGroup()` | ✅ Soft Delete |
| **Один JSON файл на проект** | Краш = потеря всего | MM: `toJSON()` | ✅ Отдельные записи в БД |
| **Дедупликация по значению** | Одинаковые замеры отбрасываются | MM: `addMeasurement()` | ✅ UUID уникальность |
| **Экспорт зависит от UI-контекста** | Замеры из других групп не попадают | Mo: `GenerateXLS.java` | ✅ Независимый экспорт |
| **Cloud Sync перезапись** | Облако перезаписывает локальное | Mo: `CloudSyncScheduler` | ✅ Syncthing (двусторонний) |
| **ConcurrentModificationException** | Игнорируется в catch | MM: `removeProjectReference()` | ✅ Правильная синхронизация |
| **fallbackToDestructiveMigration** | Room удаляет БД при миграции | Mo: `CloudDatabase.java` | ✅ Правильные миграции |

---

## 3. Архитектурные решения для GlmReader

### 3.1. Хранение данных

**Решение:** SQLite + Raw Log

```
┌─────────────────────────────────────────────────┐
│                    GlmReader DB                  │
│                                                  │
│  ┌──────────────┐  ┌──────────────┐             │
│  │ measurements │  │  raw_log     │             │
│  │──────────────│  │──────────────│             │
│  │ id (UUID)    │  │ id (AUTO)    │             │
│  │ type (int)   │  │ timestamp    │             │
│  │ result (float)│ │ raw_bytes    │             │
│  │ comp1 (float)│  │ packet_len   │             │
│  │ comp2 (float)│  │ parsed_ok    │             │
│  │ angle (float)│  │ ref_edge     │             │
│  │ ref_edge (int)│ │ laser_on     │             │
│  │ laser_on (int)│ └──────────────┘             │
│  │ timestamp    │                               │
│  │ is_deleted (int)│                            │
│  └──────────────┘                               │
│                                                  │
│  ┌──────────────┐  ┌──────────────┐             │
│  │   groups     │  │  settings    │             │
│  │──────────────│  │──────────────│             │
│  │ id (UUID)    │  │ key          │             │
│  │ name (string)│  │ value        │             │
│  │ created_at   │  │ updated_at   │             │
│  │ is_default   │  └──────────────┘             │
│  └──────────────┘                               │
└─────────────────────────────────────────────────┘
```

**Преимущества:**
- ✅ `raw_log` — никогда не модифицируется, только INSERT. Восстановление при сбое парсера.
- ✅ `is_deleted` — Soft Delete, корзина с таймером очистки.
- ✅ Транзакции — `BEGIN`/`COMMIT` при сохранении.
- ✅ Миграции — `ALTER TABLE` при обновлении схемы.

### 3.2. BLE протокол

**Решение:** Использовать полностью расшифрованный протокол из APK

- Init: `C0 55 02 40 00 [CRC8]` (devMode=0, syncCtrl=1)
- Measure: `C0 56 01 00 [CRC8]` (Remote Trigger, команда 86)
- Set Type: `C0 55 02 BC [edcMode] [CRC8]` (devMode=60)
- Set Ref: `C0 55 02 BE [refLevel] [CRC8]` (devMode=62)
- Sync: `C0 55 02 BA [index|indicator] [CRC8]` (devMode=58)

**CRC8:** poly=0xA6, Init=0xAA

### 3.3. Экспорт

**Решение:** Независимый от UI, с фильтрами

```sql
-- Экспорт всех замеров (без фильтра)
SELECT * FROM measurements WHERE is_deleted = 0 ORDER BY timestamp ASC

-- Экспорт по дате
SELECT * FROM measurements
WHERE is_deleted = 0
  AND timestamp BETWEEN '2026-04-01' AND '2026-04-03'
ORDER BY timestamp ASC

-- Экспорт группы
SELECT m.* FROM measurements m
JOIN measurement_groups mg ON m.group_id = mg.id
WHERE mg.id = 'xxx' AND m.is_deleted = 0
ORDER BY m.timestamp ASC
```

**Форматы:** CSV, XLSX, JSON

---

## 4. Итоговая таблица соответствия ТЗ

| Требование ТЗ | Реализовано в APK? | Применимо к GlmReader? | Приоритет |
|---------------|-------------------|----------------------|-----------|
| BLE подключение к GLM 50C | ✅ Mo | ✅ Полностью | 🔴 Критично |
| Приём измерений без потерь | ⚠️ Mo (JSON) | ✅ SQLite | 🔴 Критично |
| Автосохранение замера | ⚠️ Mo | ✅ SQLite + транзакции | 🔴 Критично |
| Время замера (не экспорта) | ✅ Mo | ✅ timestamp | 🔴 Критично |
| Экспорт XLSX/CSV | ✅ MM (XLS), ❌ Mo (только XLS) | ✅ Оба формата | 🟠 Высокий |
| Фильтр на экспорт | ❌ Нет | ✅ SQL WHERE | 🟡 Средний |
| Raw-лог всех пакетов | ❌ Нет | ✅ Новая таблица | 🟡 Средний |
| Soft Delete / Корзина | ⚠️ Mo (только проекты) | ✅ Для всего | 🟡 Средний |
| Голосовые заметки | ❌ Нет | ❌ Не в GlmReader | ⬜ TBD |
| PDF интеграция | ❌ Нет | ❌ Не в GlmReader | ⬜ TBD |
| Словарь сокращений | ❌ Нет | ❌ Не в GlmReader | ⬜ TBD |
| Syncthing синхронизация | ❌ Нет | ✅ Файловая БД | ⬜ TBD |
| Windows приложение | ❌ Нет | ✅ Отдельный проект | ⬜ TBD |

---

*Документ создан: 4 апреля 2026 г., 00:00 MSK*
*На основе анализа: Measuring Master 1.9.4, MeasureOn 2.0.1, ТЗ MeasuringAssistant*
