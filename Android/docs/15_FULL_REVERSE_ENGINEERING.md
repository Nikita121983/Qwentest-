# Bosch Measuring Master 1.9.4 — Полный анализ

---

## 1. Коммуникация с рулеткой (Bluetooth/BLE)

### Протокол

Приложение использует **собственный протокол Bosch MTProtocol** поверх BLE:

```
com.bosch.mtprotocol/
├── MtProtocol.java          ← Интерфейс протокола
├── MtConnection.java        ← Абстракция соединения
├── MtMessage.java           ← Базовое сообщение
├── glm100C/                 ← Протокол для GLM 100C
│   ├── MtProtocolBLEImpl    ← BLE реализация
│   ├── MtProtocolImpl       ← Классическая BT реализация
│   └── message/
│       ├── EDCInputMessage   ← Входящие данные измерения
│       ├── EDCOutputMessage  ← Команды рулетке
│       ├── SyncInputMessage  ← Синхронизация истории
│       ├── SyncOutputMessage ← Запрос истории
│       ├── SettingsMessage   ← Настройки устройства
│       └── SimpleMessage     ← Простые команды
├── linelaser/               ← Линейные лазеры
├── thermo/                  ← Тепловизоры (GIS)
└── type/field/              ← Типы полей (BitField и др.)
```

### Типы сообщений

| Сообщение | Направление | Назначение |
|-----------|-------------|------------|
| `EDCOutputMessage` | → рулетка | Команда: syncControl=1 (вкл. автосинхронизацию) |
| `EDCInputMessage` | ← рулетка | Результат измерения (devMode = тип замера) |
| `SyncOutputMessage` | → рулетка | Запрос истории: indexFrom, indexTo |
| `SyncInputMessage` | ← рулетка | Данные истории: measListIndex, mode, timestamp |
| `SyncListOutputMessage` | → рулетка | Пакетный запрос истории |
| `SyncListInputMessage` | ← рулетка | Пакет данных истории |
| `GetSettingsMessage` | → рулетка | Запрос настроек |
| `SettingsMessage` | ← рулетка | Настройки: единицы, язык, подсветка |
| `EDCDoRemoteTriggerButtonMessage` | → рулетка | Дистанционный спуск |

### Режимы измерений (devMode)

```java
// Из EDCInputMessage:
devMode 1  → Distance (расстояние)
devMode 2  → Area (площадь)
devMode 3  → Volume (объём)
devMode 4  → Angle (угол)
devMode 5  → Indirect Height (косвенная высота, 1 точка)
devMode 6  → Indirect Height (2 точки)
devMode 7  → Indirect Length (косвенная длина, 2 точки)
devMode 8  → Double Indirect Height (3 точки)
devMode 9  → Double Indirect Height (3 точки, другой тип)
devMode 10 → Wall Area (площадь стен)
devMode 12 → Indirect Height (1 точка, альтернативный)
devMode 13 → Indirect Length (1 точка)
devMode 14 → Indirect Height (2 точки, альтернативный)
devMode 15 → Indirect Length (2 точки, альтернативный)
devMode 24 → Indirect Height (Pythagoras)
devMode 25 → Indirect Length (Pythagoras, 2 точки)
devMode 26 → Indirect Length (Pythagoras, 3 точки)
devMode 57 → Timestamp (временная метка)
devMode 58 → Запрос конкретного элемента истории
```

### Процесс подключения

```
1. BLE Scan → IBleDeviceScanner.startScan()
2. Обнаружение устройства → MTDeviceParserImpl.parseDevice()
   - Парсит advertising packet (байты 5-10 = MAC, 14-29 = UUID)
   - Байт 11: bit3=connectable, bit2=eloWakeUp, bit1=locked, bit0=backupLow
   - Байты 33-42 = bareToolNr (модель устройства)
   - Байты 44-47 = serialNr
   - Байт 43 = mainSupplyCharge (заряд батареи)
3. Подключение → BLEConnection / BluetoothConnection
4. Инициализация → GLMDeviceController.init()
   - Создаёт MtProtocolBLEImpl или MtProtocolImpl
   - Включает autoSync: EDCOutputMessage.syncControl=1
5. Приём измерений → onEvent(MtProtocolReceiveMessageEvent)
```

### Дистанционное управление

```java
// GLMDeviceController.sendCommand()
EDCDoRemoteTriggerButtonMessage → дистанционный спуск рулетки

// Настройки рулетки из приложения:
SettingsMessage:
  - spiritLevelEnabled (уровень)
  - dispRotationEnabled (поворот дисплея)
  - speakerEnabled (звук)
  - laserPointerEnabled (лазер)
  - backlightMode (подсветка)
  - angleUnit (единицы угла)
  - measurementUnit (единицы измерения)
```

### Синхронизация истории

```
1. startHistorySync()
   → Для EDC-устройств: GetSettingsMessage → запрос lastUsedListIndex
   → Для GLM 100C: requestHistoryItem(0, 0)

2. requestHistoryItem(index, indicator)
   → EDCOutputMessage: devMode=58, syncControl=1, remoteCtrlData={index, indicator}
   → SyncListOutputMessage: indexFrom, indexTo (для GLM 100C)

3. Приём данных:
   → EDCInputMessage (каждое измерение)
   → SyncInputMessage (метаданные)
   → handleEDCMessage → saveEDCMessage → DistanceMeasurement

4. onGLMMeasurementFinished() → syncFinished()
```

**Ключевая проблема:** `Thread.sleep(50L)` между запросами — синхронная блокировка!

---

## 2. Система измерений

### Модель DistanceMeasurement

```java
DistanceMeasurement {
    // Идентификация
    long id
    String projectId              ← Привязка к проекту
    Long deviceId                 ← Какое устройство
    Map<String, List<String>> projectIdentifierMap  ← M:N связь с проектами

    // Временные метки
    Date createdDate
    Date modifiedDate
    Long timestamp1, timestamp2, timestamp3  ← Время каждого значения

    // До 3 значений измерений
    Float value1, value2, value3
    int type1, type2, type3       ← Тип каждого (DistanceMeasurementType)
    String name1, name2, name3    ← Имена
    boolean value1Manual, value2Manual, value3Manual  ← Ручной ввод?

    // Оператор между значениями
    int operator                   ← 0=NONE, 1=+, 2=-, 3=/, 4=*, 5=sin, 6=cos, 7=sqrt

    // Результат
    Float resultValue
    int resultType                 ← 1=distance, 2=area, 3=volume, 4=angle
    String resultName
    Long resultTimestamp
    boolean resultManual           ← Результат введён вручную?
}
```

### Типы измерений (MeasurementType enum)

| Код | Тип | Описание |
|-----|-----|----------|
| 1 | Distance | Расстояние (одно значение) |
| 2 | Area | Площадь (2 значения: длина × ширина) |
| 3 | Volume | Объём (3 значения: длина × ширина × высота) |
| 4 | Angle | Угол |
| 5 | Rail | Направляющая |
| 6 | MinMax | Мин/Макс |
| 7 | IndirectHeight | Косвенная высота |
| 8 | IndirectLength | Косвенная длина |
| 9 | DoubleIndirectHeight | Двойная косвенная высота |
| 10 | WallArea | Площадь стен |

### Режим «Список измерений»

**Где:** `MeasurementListActivity` (не декомпилирован полностью, но структура ясна)

**Что хранится:**
- Каждое измерение привязано к `ProjectModel` через `projectIdentifierMap`
- `ProjectModel` содержит списки идентификаторов:
  - `planIdentifiers`, `pictureIdentifiers`, `wallIdentifiers`
  - `thermoIdentifiers`, `calculatorIdentifiers`
  - `todoIdentifiers`, `noteIdentifiers`, `thermalIdentifiers`

**Когда:**
- `createdDate` — дата создания замера
- `modifiedDate` — дата последнего изменения
- `resultTimestamp` — время получения результата с рулетки
- **Проблема:** `timestamp1/2/3` — это Long (epoch), но при экспорте используется время создания файла, а не замера!

**Сортировка:**
```java
DistanceMeasurement.getComparator(int mode):
  mode 1 → SORT_BY_NAME
  mode 2 → SORT_BY_CREATED (createdDate desc/asc)
  mode 3 → SORT_BY_MODIFIED (modifiedDate desc/asc)
```

---

## 3. Хранение данных

### Структура на файловой системе

```
/storage/emulated/0/MeasuringMaster/
├── projects.json                    ← Список всех проектов
├── {project_identifier}/
│   ├── project.json                 ← ProjectModel в JSON
│   ├── plans/
│   │   └── {plan_identifier}/
│   │       ├── plan.json
│   │       └── plan.pdf             ← PDF план
│   ├── pictures/
│   │   └── {picture_identifier}/
│   │       ├── picture.jpg
│   │       └── picture.json
│   ├── audio/
│   │   └── {note_identifier}.m4a   ← Голосовые заметки
│   └── exports/
│       ├── project_export.pdf
│       └── measurements.xlsx
```

### Сериализация

```java
// ProjectModel → JSON
public JSONObject toJSON() {
    // Все поля → JSONObject
    // Списки → JSONArray идентификаторов
    // Date → RFC3339 строка
}

// ProjectModel ← JSON
public static ProjectModel fromJSON(JSONObject json) {
    // Парсинг каждого поля
    // Массивы идентификаторов → List<String>
    // lastImported → Gson.fromJson(HashMap<String, Date>)
}
```

**Проблемы:**
- Нет транзакций — если запись прервётся, файл повреждён
- Нет миграций — новая версия приложения может не прочитать старый JSON
- `Serializable` для моделей — медленно, несовместимо между версиями Java
- `ConcurrentModificationException` ловится и игнорируется — потеря данных

---

## 4. Экспорт данных

### Форматы экспорта

| Формат | Сервис | Библиотека |
|--------|--------|------------|
| **PDF** | `PDFExportService` | pdfjet |
| **XLSX** | `ProjectXLSExportService` | Apache POI |
| **XLS (замеры)** | `MeasurementXLSExportService` | Apache POI |
| **GLM PDF** | `GLMPicturePdfExportService` | pdfjet |
| **Thermal PDF** | `GTCThermalPdfExportService` | pdfjet |
| **Detailed Plan PDF** | `DetailedPlanPdfExportService` | pdfjet |
| **Wall PDF** | `WallPdfExportService` | pdfjet |
| **Material Calc PDF** | `MaterialCalculationPdfExportService` | pdfjet |

### Структура XLS экспорта замеров

```
Столбцы:
1. Значение замера (value1, value2, value3)
2. Дата (createdDate)
3. Время (createdDate time)
4. Тип измерения (MeasurementType)
5. Единица измерения (m, m², m³, °)
6. Имя результата (resultName)

Подзамеры (sub-measurements) выделяются серым фоном
```

### Процесс экспорта

```
1. ExportActivity → выбирает формат
2. Запускает IntentService (например, PDFExportService)
3. Service читает ProjectModel из памяти
4. Генерирует файл в фоне
5. Broadcast ACTION_PROJECT_CSV_CREATED / ERROR
6. Activity получает результат и показывает файл
```

**Проблемы:**
- `IntentService` устарел (deprecated в API 30)
- Нет WorkManager — система может убить сервис
- Нет прогресс-бара для пользователя
- Нет повторных попыток при ошибке
- 15+ отдельных сервисов вместо одного

---

## 5. Режимы приложения — Краткий бриф

### Основные режимы

| Режим | Activity | Назначение | Данные |
|-------|----------|-----------|--------|
| **Главный экран** | MainActivity | Список проектов, создание/удаление | `List<ProjectModel>` |
| **План** | PlanActivity | 2D план помещения, привязка замеров к стенам | `PlanModel`, `WallModel` |
| **Быстрый эскиз** | PlanActivity (quickSketch) | Рисование от руки | `PlanModel.isQuickSketch()` |
| **Фото** | PictureActivity | Съёмка, привязка к замерам | `PictureModel` |
| **Тепловизор** | ThermoActivity | Термограммы с тепловизора Bosch | `ThermoModel` |
| **Стены** | WallViewActivity | Детальный просмотр/редактирование стен | `WallModel`, `WallLineModel` |
| **Калькулятор** | CalculatorActivity | Вычисления: +, -, *, /, sin, cos, sqrt | `CalculatorModel` |
| **Заметки** | NoteActivity | Текст + аудио заметки | `NoteModel` |
| **Задачи** | ToDoActivity | Список задач (тот же NoteModel!) | `NoteModel` |
| **Экспорт** | ExportActivity | Выбор формата экспорта | Все данные проекта |
| **Настройки** | SettingsActivity | Единицы, язык, Bluetooth | `SharedPreferences` |
| **Bluetooth** | BluetoothConnectionActivity | Подключение к GLM 50C/100C/120 | `GLMDeviceController` |
| **Список замеров** | MeasurementListActivity | Все замеры проекта | `List<DistanceMeasurement>` |

### Специфичные режимы

| Режим | Activity | Назначение |
|-------|----------|-----------|
| **PictureActivity** | PictureActivity | Просмотр фото с плана |
| **ThermoActivity** | ThermoActivity | Тепловизионные замеры |
| **WallViewActivity** | WallViewActivity | Визуализация стен с размерами |
| **CalculatorActivity** | CalculatorActivity | Мат. операции над замерами |
| **MaterialCalculatorActivity** | MaterialCalculatorActivity | Расчёт материалов |
| **CropActivity** | CropActivity | Обрезка фото/лого |
| **NoteActivity** | NoteActivity | Создание заметок с аудио |
| **ToDoActivity** | ToDoActivity | Задачи с вложениями |
| **PlanActivity** | PlanActivity | Работа с PDF планом |

### Навигация

```
SplashScreenActivity
    ↓
MainActivity (список проектов)
    ↓
┌─────────────────────────────────────────────┐
│  PlanActivity    │  PictureActivity         │
│  WallViewActivity│  ThermoActivity          │
│  CalculatorActivity │  MaterialCalculator   │
│  NoteActivity    │  ToDoActivity            │
│  ExportActivity  │  SettingsActivity        │
│  BluetoothConnectionActivity                │
│  MeasurementListActivity                    │
└─────────────────────────────────────────────┘
    ↓
Все Activity → launchMode="singleTop"
```

---

## 6. Критические выводы для нашего приложения

### 🔴 Избегать

| Проблема | В Bosch | Как у нас |
|----------|---------|-----------|
| **Протокол** | Собственный MTProtocol, obfuscated | Использовать открытый протокол или документированный |
| **Хранение** | JSON файлы на FS | Room/SQLite с миграциями |
| **God Object** | ProjectModel (700+ строк, 50+ полей) | Разделение на агрегаты |
| **Фоновые задачи** | 15+ IntentService | WorkManager |
| **Навигация** | 25+ Activity, singleTop | Navigation Component / роутер |
| **Timestamp** | Время экспорта вместо замера | Хранить время замера отдельно |
| **Синхронизация BLE** | Thread.sleep(50ms) | Корутины/async без блокировок |
| **Ошибки** | catch → return null | Логирование + UI + retry |
| **NoteModel для всего** | Заметки + задачи в одном классе | Разные модели |

### ✅ Использовать

| Решение | Описание |
|---------|----------|
| **BLE абстракция** | `IBleDeviceScanner` — хороший интерфейс |
| **Типы измерений** | Enum с кодами — удобно для UI |
| **Операторы** | +, -, *, /, sin, cos, sqrt — полный набор |
| **M:N связь** | Measurement ↔ Projects через map |
| **JSON сериализация** | toJSON/fromJSON — для миграции и API |
| **Checkbox фичи** | Модульность проекта |

---

*Дата анализа: 3 апреля 2026 г.*
*Инструмент: jadx 1.5.0*
*APK: Measuring Master 1.9.4 (build 169), Bosch*
