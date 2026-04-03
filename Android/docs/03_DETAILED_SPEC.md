# Приложение для замеров — Детальная спецификация

**Версия:** 3.0
**Дата:** 2 апреля 2026 г.
**Статус:** Черновик
**Объём:** Полная спецификация

---

## ЧАСТЬ 1: ОБЩИЕ СВЕДЕНИЯ

### 1.1. Назначение системы

Система предназначена для автоматизации процесса профессиональных замеров в строительстве и ремонте (двери, окна, отделка).

**Целевая аудитория:**
- Замерщики дверей/окон
- Прорабы
- Техники-обмерщики
- Дизайнеры интерьеров

### 1.2. Область применения

- Обмер помещений перед установкой дверей/окон
- Фиксация дефектов (царапины, сколы, неровности)
- Ведение журнала замеров по объекту
- Экспорт данных для смет и отчётов

### 1.3. Глоссарий

| Термин | Определение | Пример |
|--------|-------------|--------|
| **Замер** | Акт измерения линейного размера | 900 мм |
| **Точка замера** | Конкретное место в пределах замера | Верх/низ/середина |
| **Объект** | Единица обмера (дверь, окно, комната) | Д_кух, ОК_зал |
| **Медиа** | Аудио/фото/видео, привязанные к замеру | audio_001.mp3 |
| **Транскрипт** | Текстовая расшифровка аудио | "левая петля сломана" |
| **Сокращение** | Стандартизированное обозначение | ЛП = левая петля |
| **Проект** | Папка со всеми данными объекта | Project_Kitchen/ |

---

## ЧАСТЬ 2: ПРОБЛЕМЫ СУЩЕСТВУЮЩИХ РЕШЕНИЙ

### 2.1. Детальный анализ Bosch Measuring Master

#### 2.1.1. Архитектура хранения

```
┌─────────────────────────────────────────┐
│         UI Buffer (список)              │
│  ┌─────────────────────────────────┐    │
│  │ Измерение 1 — 2450 мм — 12:44   │    │
│  │ Измерение 2 — 1200 мм — 12:45   │    │
│  │ Измерение 3 — 900 мм — 12:46    │    │
│  └─────────────────────────────────┘    │
│              │                          │
│              ▼                          │
│  ┌─────────────────────────────────┐    │
│  │    Project Database (SQLite)    │    │
│  │  (сохраняется при экспорте)     │    │
│  └─────────────────────────────────┘    │
└─────────────────────────────────────────┘
```

**Проблема:** UI буфер ≠ Project Database

#### 2.1.2. Сценарий потери данных

```
1. Пользователь делает замер → появляется в UI буфере
2. Не экспортирует сразу
3. Делает ещё 10 замеров
4. Приложение закрывается (сбой/память)
5. UI буфер очищается
6. Замеры 2-11 потеряны (не попали в БД)
```

**Корневая причина:** Отсутствие автосохранения в БД

#### 2.1.3. Проблема времени в экспорте

**В приложении:**
```
Измерение 1 | 2450 мм | 12:44
Измерение 2 | 1200 мм | 12:45
Измерение 3 | 900 мм  | 12:46
```

**В Excel экспорте:**
```
A           | B     | C
Измерение 1 | 2450  | 13:50  ← время экспорта!
Измерение 2 | 1200  | 13:50  ← одинаковое!
Измерение 3 | 900   | 13:50  ← порядок потерян
```

**Корневая причина:** Экспорт берёт timestamp создания файла, не замера

### 2.2. Детальный анализ Bosch MeasureOn

#### 2.2.1. Изменения в архитектуре

| Measuring Master | MeasureOn |
|------------------|-----------|
| Список замеров | ❌ Убран |
| Прямой экспорт | ⚠️ Через проект |
| Простой UI | Сложный UI с планом |

#### 2.2.2. Почему не подходит

**Требуется:**
```
Замер → Список → Обработка → Экспорт
```

**Предлагает:**
```
Замер → План → Привязка к стене → Экспорт среза
```

**Вывод:** Противоположные парадигмы

### 2.3. Детальный анализ Magicplan

#### 2.3.1. Сильные стороны

- ✅ Стабильное хранение
- ✅ Нормальный экспорт (XLSX/PDF/DXF)
- ✅ Структура данных (помещение → элементы)

#### 2.3.2. Слабые стороны

- ❌ Подписка ($10-20/мес)
- ❌ Упор на чертёж, не на замеры
- ❌ Нет raw-лога (сырых данных)
- ❌ Сложнее для быстрых замеров

### 2.4. Детальный анализ Leica DISTO Plan

#### 2.4.1. Сильные стороны

- ✅ Стабильнее Bosch
- ✅ Лучший экспорт
- ✅ Поддержка профессионального workflow

#### 2.4.2. Слабые стороны

- ❌ Требуется рулетка Leica (от $200)
- ❌ Закрытый протокол BLE
- ❌ Дороже аналогов

### 2.5. Сводная таблица проблем

| Проблема | Bosch MM | Bosch MO | Magicplan | Leica | **Решение** |
|----------|----------|----------|-----------|-------|-------------|
| Пропажа замеров | 🔴 | 🟡 | ✅ | ✅ | Автосохранение |
| Время в экспорте | 🔴 | 🟡 | ✅ | ✅ | Timestamp замера |
| Raw-лог данных | 🟡 | 🔴 | 🔴 | 🟡 | JSON + SQLite |
| Оффлайн распознавание | 🔴 | 🔴 | 🔴 | 🔴 | Whisper локально |
| Привязка к точке | 🔴 | 🔴 | 🔴 | 🔴 | Points таблица |
| Работа с PDF | 🟡 | 🟡 | 🟡 | 🟡 | Xodo интеграция |
| Цена | ✅ | ✅ | 🔴 | 🔴 | Бесплатно |

---

## ЧАСТЬ 3: ЛОГИКА ОБРАБОТКИ ИЗМЕРЕНИЙ

### 3.1. Полный поток данных

```
┌─────────────────────────────────────────────────────────────────┐
│                    ПОТОК ДАННЫХ ЗАМЕРА                          │
└─────────────────────────────────────────────────────────────────┘

┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   Рулетка    │ ──▶ │  Приложение  │ ──▶ │  Валидация   │
│   (BLE)      │     │  (Android)   │     │  данных      │
└──────────────┘     └──────────────┘     └──────────────┘
                            │                    │
                            ▼                    ▼
                   ┌──────────────┐     ┌──────────────┐
                   │   Голос      │     │  Сохранение  │
                   │   (запись)   │     │  (SQLite+JSON)│
                   └──────────────┘     └──────────────┘
                            │                    │
                            ▼                    ▼
                   ┌──────────────┐     ┌──────────────┐
                   │   Очередь    │     │   Экспорт    │
                   │   (распозн.) │     │   (XLSX/CSV) │
                   └──────────────┘     └──────────────┘
                            │
                            ▼
                   ┌──────────────┐
                   │   Словарь    │
                   │   (сокращ.)  │
                   └──────────────┘
                            │
                            ▼
                   ┌──────────────┐
                   │   БД         │
                   │   (update)   │
                   └──────────────┘
```

### 3.2. Детальная структура замера

#### 3.2.1. JSON представление

```json
{
  "$schema": "measurement.v1.json",
  "measurement_id": "m_20260402_143000_001",
  "project_id": "proj_kitchen_2026",
  "object_id": "D_кух_01",
  "object_type": "door",
  "object_subtype": "interior",

  "timestamp": "2026-04-02T14:30:00.000Z",
  "timezone": "Europe/Moscow",
  "device": {
    "model": "Bosch GLM 50 C",
    "firmware": "1.2.3",
    "connection": "BLE"
  },

  "points": [
    {
      "point_id": "p1",
      "name": "Ширина (верх)",
      "code": "W_TOP",
      "value": 900,
      "unit": "mm",
      "accuracy": "±1mm",
      "notes_raw": "левая петля сломана",
      "notes_processed": "ЛП слом",
      "audio": [
        {
          "file": "audio_001.mp3",
          "duration": 5.2,
          "transcript_status": "completed",
          "transcript_timestamp": "2026-04-02T14:30:15.000Z"
        }
      ],
      "photos": [
        {
          "file": "photo_001.jpg",
          "caption": "Дефект петли",
          "timestamp": "2026-04-02T14:30:10.000Z"
        }
      ],
      "flags": ["defect", "requires_attention"]
    },
    {
      "point_id": "p2",
      "name": "Ширина (низ)",
      "code": "W_BOTTOM",
      "value": 905,
      "unit": "mm"
    },
    {
      "point_id": "p3",
      "name": "Высота (левая)",
      "code": "H_LEFT",
      "value": 2100,
      "unit": "mm"
    }
  ],

  "pdf_ref": {
    "file": "floor_plan.pdf",
    "page": 1,
    "rect": {
      "x": 0.32,
      "y": 0.48,
      "w": 0.05,
      "h": 0.08
    },
    "annotation_id": "annot_001",
    "annotation_color": "#FF0000"
  },

  "metadata": {
    "created_by": "user_001",
    "created_at": "2026-04-02T14:30:00.000Z",
    "modified_at": "2026-04-02T14:35:00.000Z",
    "version": 1,
    "export": true,
    "exported_at": null,
    "tags": ["kitchen", "door", "defect"]
  }
}
```

#### 3.2.2. SQLite представление

```sql
-- Таблица проектов
CREATE TABLE Projects (
    project_id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    address TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME,
    status TEXT DEFAULT 'active'
);

-- Таблица объектов (двери, окна, комнаты)
CREATE TABLE Objects (
    object_id TEXT PRIMARY KEY,
    project_id TEXT,
    name TEXT,
    type TEXT, -- 'door', 'window', 'room'
    subtype TEXT, -- 'interior', 'exterior', etc.
    floor INTEGER,
    pdf_ref TEXT, -- JSON с координатами
    FOREIGN KEY(project_id) REFERENCES Projects(project_id)
);

-- Таблица замеров
CREATE TABLE Measurements (
    measurement_id TEXT PRIMARY KEY,
    project_id TEXT,
    object_id TEXT,
    timestamp DATETIME NOT NULL,
    device_model TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    export BOOLEAN DEFAULT TRUE,
    exported_at DATETIME,
    FOREIGN KEY(project_id) REFERENCES Projects(project_id),
    FOREIGN KEY(object_id) REFERENCES Objects(object_id)
);

-- Таблица точек замера
CREATE TABLE Points (
    point_id TEXT PRIMARY KEY,
    measurement_id TEXT,
    name TEXT,
    code TEXT,
    value REAL NOT NULL,
    unit TEXT DEFAULT 'mm',
    accuracy TEXT,
    notes_raw TEXT,
    notes_processed TEXT,
    sort_order INTEGER,
    FOREIGN KEY(measurement_id) REFERENCES Measurements(measurement_id)
);

-- Таблица медиафайлов
CREATE TABLE Media (
    media_id TEXT PRIMARY KEY,
    measurement_id TEXT,
    point_id TEXT,
    media_type TEXT, -- 'audio', 'photo', 'video'
    file_path TEXT NOT NULL,
    file_size INTEGER,
    duration REAL, -- для аудио/видео
    caption TEXT,
    timestamp DATETIME,
    transcript_status TEXT, -- 'pending', 'completed', 'failed'
    transcript_raw TEXT,
    transcript_processed TEXT,
    FOREIGN KEY(measurement_id) REFERENCES Measurements(measurement_id),
    FOREIGN KEY(point_id) REFERENCES Points(point_id)
);

-- Таблица словаря сокращений
CREATE TABLE Abbreviations (
    abbrev_id INTEGER PRIMARY KEY,
    full_term TEXT NOT NULL,
    short_term TEXT NOT NULL,
    category TEXT, -- 'defect', 'part', 'room', etc.
    active BOOLEAN DEFAULT TRUE
);

-- Индексы для производительности
CREATE INDEX idx_measurements_project ON Measurements(project_id);
CREATE INDEX idx_measurements_object ON Measurements(object_id);
CREATE INDEX idx_measurements_timestamp ON Measurements(timestamp);
CREATE INDEX idx_points_measurement ON Points(measurement_id);
CREATE INDEX idx_media_measurement ON Media(measurement_id);
CREATE INDEX idx_media_point ON Media(point_id);
```

### 3.3. Детальная логика привязки заметок

#### 3.3.1. Режимы привязки

**Режим 1: Авто (к последнему замеру)**

```
Поток:
1. Замер → measurement_id = m_001
2. Кнопка "Записать заметку"
3. Аудио → m_001 (последний)
4. Распознавание → notes_raw/processed → m_001
```

**UX:**
- Быстро (1 тап)
- Для потока замеров
- Риск: не та дверь

---

**Режим 2: Выбор точки**

```
Поток:
1. Замер → measurement_id = m_001, точки p1/p2/p3
2. Выбор точки (тап на p1)
3. Кнопка "Записать заметку"
4. Аудио → m_001.p1
5. Распознавание → notes_raw/processed → m_001.p1
```

**UX:**
- Точно (конкретная точка)
- Для сложных замеров
- Дольше (2 тапа)

---

**Режим 3: Регулировка (пост-обработка)**

```
Поток:
1. Замер → m_001
2. Запись → аудио (временная привязка m_001)
3. Распознавание → готово
4. Пользователь видит: "Привязано к p1"
5. Кнопка "Изменить" → выбор p2
6. Привязка обновлена: m_001.p2
```

**UX:**
- Гибко (можно исправить)
- Для пост-обработки
- Требует проверки

#### 3.3.2. Алгоритм привязки

```kotlin
sealed class AttachmentMode {
    object LastMeasurement : AttachmentMode()
    data class SelectedPoint(val pointId: String) : AttachmentMode()
    object ManualAdjustment : AttachmentMode()
}

class NoteAttachmentManager(
    private val measurementRepository: MeasurementRepository
) {
    suspend fun attachNote(
        audioFile: File,
        mode: AttachmentMode
    ): Result<MediaEntity> {
        return when (mode) {
            is AttachmentMode.LastMeasurement -> {
                val lastMeasurement = measurementRepository.getLast()
                    ?: return Result.failure(Error.NoMeasurements)

                MediaEntity(
                    measurementId = lastMeasurement.id,
                    pointId = null, // ко всему замеру
                    audioFile = audioFile
                )
            }

            is AttachmentMode.SelectedPoint -> {
                MediaEntity(
                    measurementId = mode.measurementId,
                    pointId = mode.pointId,
                    audioFile = audioFile
                )
            }

            is AttachmentMode.ManualAdjustment -> {
                // Показываем UI для выбора
                val selectedPoint = showPointPicker()
                MediaEntity(
                    measurementId = selectedPoint.measurementId,
                    pointId = selectedPoint.id,
                    audioFile = audioFile
                )
            }
        }.let { media ->
            measurementRepository.saveMedia(media)
            Result.success(media)
        }
    }
}
```

### 3.4. Детальная обработка аудио

#### 3.4.1. Поток распознавания

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   Запись     │ ──▶ │   Очередь    │ ──▶ │   Whisper    │
│   (MP3)      │     │   (FIFO)     │     │   (локально) │
└──────────────┘     └──────────────┘     └──────────────┘
                            │                    │
                            ▼                    ▼
                   ┌──────────────┐     ┌──────────────┐
                   │   Статус     │     │   Текст      │
                   │   (pending)  │     │   (raw)      │
                   └──────────────┘     └──────────────┘
                                              │
                                              ▼
                                     ┌──────────────┐
                                     │   Словарь    │
                                     │   (replace)  │
                                     └──────────────┘
                                              │
                                              ▼
                                     ┌──────────────┐
                                     │   Текст      │
                                     │   (processed)│
                                     └──────────────┘
                                              │
                                              ▼
                                     ┌──────────────┐
                                     │   БД         │
                                     │   (update)   │
                                     └──────────────┘
```

#### 3.4.2. Реализация очереди

```kotlin
class TranscriptionQueue @Inject constructor(
    private val whisperService: WhisperService,
    private val abbreviationService: AbbreviationService,
    private val mediaRepository: MediaRepository
) {
    private val queue = Channel<TranscriptionTask>(Channel.UNLIMITED)

    init {
        // Запускаем воркера
        CoroutineScope(Dispatchers.IO).launch {
            queue.consumeAsFlow().collect { task ->
                processTask(task)
            }
        }
    }

    suspend fun enqueue(mediaId: String, audioFile: File) {
        queue.send(TranscriptionTask(mediaId, audioFile))
    }

    private suspend fun processTask(task: TranscriptionTask) {
        try {
            // Обновляем статус
            mediaRepository.updateStatus(task.mediaId, "processing")

            // Распознавание
            val rawText = whisperService.transcribe(task.audioFile)

            // Применяем словарь
            val processedText = abbreviationService.apply(rawText)

            // Сохраняем в БД
            mediaRepository.saveTranscript(
                mediaId = task.mediaId,
                raw = rawText,
                processed = processedText,
                status = "completed"
            )
        } catch (e: Exception) {
            mediaRepository.updateStatus(task.mediaId, "failed")
        }
    }
}

data class TranscriptionTask(
    val mediaId: String,
    val audioFile: File
)
```

#### 3.4.3. Словарь сокращений

**База сокращений:**
```json
{
  "categories": {
    "parts": {
      "левая петля": "ЛП",
      "правая петля": "ПП",
      "ручка": "Р",
      "замок": "З",
      "доводчик": "Дов"
    },
    "defects": {
      "сломана": "слом",
      "царапина": "царап",
      "вмятина": "вмят",
      "трещина": "трещ",
      "скол": "ск"
    },
    "rooms": {
      "кухня": "кух",
      "ванная": "ван",
      "спальня": "сп",
      "гостиная": "гост",
      "коридор": "кор"
    },
    "objects": {
      "дверь": "Д",
      "окно": "ОК",
      "ворота": "В",
      "люк": "Л"
    }
  },
  "rules": {
    "order": ["parts", "defects", "rooms", "objects"],
    "case_sensitive": false,
    "whole_words_only": true
  }
}
```

**Алгоритм применения:**
```kotlin
class AbbreviationService @Inject constructor(
    private val abbreviationRepository: AbbreviationRepository
) {
    suspend fun apply(text: String): String {
        var result = text
        val abbreviations = abbreviationRepository.getAllActive()

        // Сортируем по категориям (порядок важен)
        val sorted = abbreviations.sortedBy { abbr ->
            when (abbr.category) {
                "parts" -> 0
                "defects" -> 1
                "rooms" -> 2
                "objects" -> 3
                else -> 4
            }
        }

        // Применяем замены
        for (abbr in sorted) {
            result = result.replace(
                abbr.fullTerm,
                abbr.shortTerm,
                ignoreCase = true
            )
        }

        return result
    }
}
```

### 3.5. Детальная логика экспорта

#### 3.5.1. Фильтрация перед экспортом

```kotlin
class ExportFilter(
    private val measurementRepository: MeasurementRepository
) {
    data class ExportCriteria(
        val projectId: String,
        val dateFrom: Date? = null,
        val dateTo: Date? = null,
        val objectTypes: List<String> = emptyList(),
        val onlyWithExportFlag: Boolean = true,
        val onlyWithDefects: Boolean = false
    )

    suspend fun getMeasurements(criteria: ExportCriteria): List<MeasurementEntity> {
        return measurementRepository.query(
            where = buildQuery(criteria),
            orderBy = "timestamp ASC"
        )
    }

    private fun buildQuery(criteria: ExportCriteria): String {
        val conditions = mutableListOf<String>()

        conditions.add("project_id = '${criteria.projectId}'")

        if (criteria.dateFrom != null) {
            conditions.add("timestamp >= '${criteria.dateFrom}'")
        }

        if (criteria.dateTo != null) {
            conditions.add("timestamp <= '${criteria.dateTo}'")
        }

        if (criteria.objectTypes.isNotEmpty()) {
            val types = criteria.objectTypes.joinToString("','")
            conditions.add("object_type IN ('$types')")
        }

        if (criteria.onlyWithExportFlag) {
            conditions.add("export = TRUE")
        }

        if (criteria.onlyWithDefects) {
            conditions.add("flags LIKE '%defect%'")
        }

        return conditions.joinToString(" AND ")
    }
}
```

#### 3.5.2. Генерация XLSX

```kotlin
class XlsxExportService @Inject constructor(
    private val exportFilter: ExportFilter
) {
    suspend fun export(criteria: ExportCriteria, outputFile: File): Result<File> {
        return try {
            val measurements = exportFilter.getMeasurements(criteria)

            Workbook().use { workbook ->
                val sheet = workbook.createSheet("Замеры")

                // Заголовки
                val headerRow = sheet.createRow(0)
                headerRow.createCell(0).setCellValue("ID замера")
                headerRow.createCell(1).setCellValue("Объект")
                headerRow.createCell(2).setCellValue("Точка")
                headerRow.createCell(3).setCellValue("Значение")
                headerRow.createCell(4).setCellValue("Ед.изм")
                headerRow.createCell(5).setCellValue("Время замера")
                headerRow.createCell(6).setCellValue("Заметки (raw)")
                headerRow.createCell(7).setCellValue("Заметки (обр.)")
                headerRow.createCell(8).setCellValue("Медиа")

                // Данные
                var rowNum = 1
                for (measurement in measurements) {
                    for (point in measurement.points) {
                        val row = sheet.createRow(rowNum++)
                        row.createCell(0).setCellValue(measurement.id)
                        row.createCell(1).setCellValue(measurement.objectId)
                        row.createCell(2).setCellValue(point.name)
                        row.createCell(3).setCellValue(point.value)
                        row.createCell(4).setCellValue(point.unit)
                        row.createCell(5).setCellValue(formatTimestamp(measurement.timestamp))
                        row.createCell(6).setCellValue(point.notesRaw ?: "")
                        row.createCell(7).setCellValue(point.notesProcessed ?: "")
                        row.createCell(8).setCellValue(formatMedia(point.media))
                    }
                }

                // Автоширина колонок
                for (i in 0..8) {
                    sheet.autoSizeColumn(i)
                }

                // Сохраняем
                FileOutputStream(outputFile).use { out ->
                    workbook.write(out)
                }
            }

            Result.success(outputFile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun formatTimestamp(timestamp: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(timestamp)
    }

    private fun formatMedia(media: List<MediaEntity>): String {
        return media.joinToString(";") { "${it.type}:${it.fileName}" }
    }
}
```

#### 3.5.3. Пример экспорта

**Входные данные (БД):**
```
measurement_id: m_001
object_id: D_кух_01
timestamp: 2026-04-02 14:30:00

points:
  - p1: 900 мм, "левая петля сломана" → "ЛП слом"
  - p2: 905 мм
  - p3: 2100 мм

media:
  - audio_001.mp3 (5.2 сек)
  - photo_001.jpg
```

**Выходные данные (XLSX):**
```
| ID замера | Объект    | Точка          | Значение | Ед.изм | Время замера       | Заметки (raw)        | Заметки (обр.) | Медиа              |
|-----------|-----------|----------------|----------|--------|--------------------|----------------------|----------------|--------------------|
| m_001     | D_кух_01  | Ширина (верх)  | 900      | мм     | 2026-04-02 14:30:00| левая петля сломана  | ЛП слом        | audio:audio_001.mp3|
| m_001     | D_кух_01  | Ширина (низ)   | 905      | мм     | 2026-04-02 14:30:00|                      |                |                    |
| m_001     | D_кух_01  | Высота (левая) | 2100     | мм     | 2026-04-02 14:30:00|                      |                |                    |
```

**Ключевое отличие от Bosch:**
- ✅ Время = время замера (14:30:00), не экспорта
- ✅ Порядок = хронологический
- ✅ Все точки видны
- ✅ Заметки привязаны к точкам

---

## ЧАСТЬ 4: АРХИТЕКТУРА ПРИЛОЖЕНИЯ

### 4.1. Android (Kotlin)

#### 4.1.1. Слои архитектуры

```
┌─────────────────────────────────────────────────────────┐
│              PRESENTATION LAYER                         │
│  ┌─────────────────────────────────────────────────┐   │
│  │  UI (Jetpack Compose)                           │   │
│  │  - MeasurementListScreen                        │   │
│  │  - MeasurementDetailScreen                      │   │
│  │  - PdfViewerScreen                              │   │
│  │  - ExportScreen                                 │   │
│  └─────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────┐   │
│  │  ViewModels                                     │   │
│  │  - MeasurementViewModel                         │   │
│  │  - TranscriptionViewModel                       │   │
│  │  - ExportViewModel                              │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              DOMAIN LAYER                               │
│  ┌─────────────────────────────────────────────────┐   │
│  │  Use Cases                                      │   │
│  │  - GetMeasurementsUseCase                       │   │
│  │  - SaveMeasurementUseCase                       │   │
│  │  - TranscribeAudioUseCase                       │   │
│  │  - ExportMeasurementsUseCase                    │   │
│  └─────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────┐   │
│  │  Models (Entities)                              │   │
│  │  - MeasurementEntity                            │   │
│  │  - PointEntity                                  │   │
│  │  - MediaEntity                                  │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              DATA LAYER                                 │
│  ┌─────────────────────────────────────────────────┐   │
│  │  Repositories                                   │   │
│  │  - MeasurementRepository                        │   │
│  │  - MediaRepository                              │   │
│  │  - ProjectRepository                            │   │
│  └─────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────┐   │
│  │  Services                                       │   │
│  │  - BleService (Bosch GLM 50 C)                  │   │
│  │  - WhisperService (оффлайн распознавание)       │   │
│  │  - XodoService (PDF интеграция)                 │   │
│  │  - ExportService (XLSX/CSV)                     │   │
│  └─────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────┐   │
│  │  Local Data Sources                             │   │
│  │  - SQLite Database (Room)                       │   │
│  │  - JSON Files                                   │   │
│  │  - File Storage                                 │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
```

#### 4.1.2. Модульная структура

```
app/
├── build.gradle.kts
├── src/main/
│   ├── AndroidManifest.xml
│   │
│   ├── java/com/measuringassistant/
│   │   │
│   │   ├── ui/                          # Presentation layer
│   │   │   ├── screens/
│   │   │   │   ├── MeasurementListScreen.kt
│   │   │   │   ├── MeasurementDetailScreen.kt
│   │   │   │   ├── PdfViewerScreen.kt
│   │   │   │   └── ExportScreen.kt
│   │   │   ├── viewmodels/
│   │   │   │   ├── MeasurementViewModel.kt
│   │   │   │   ├── TranscriptionViewModel.kt
│   │   │   │   └── ExportViewModel.kt
│   │   │   └── components/
│   │   │       ├── MeasurementCard.kt
│   │   │       ├── AudioRecorderButton.kt
│   │   │       └── PdfOverlay.kt
│   │   │
│   │   ├── domain/                      # Domain layer
│   │   │   ├── models/
│   │   │   │   ├── MeasurementEntity.kt
│   │   │   │   ├── PointEntity.kt
│   │   │   │   └── MediaEntity.kt
│   │   │   └── usecases/
│   │   │       ├── GetMeasurementsUseCase.kt
│   │   │       ├── SaveMeasurementUseCase.kt
│   │   │       ├── TranscribeAudioUseCase.kt
│   │   │       └── ExportMeasurementsUseCase.kt
│   │   │
│   │   └── data/                        # Data layer
│   │       ├── repositories/
│   │       │   ├── MeasurementRepository.kt
│   │       │   ├── MediaRepository.kt
│   │       │   └── ProjectRepository.kt
│   │       │
│   │       ├── services/
│   │       │   ├── BleService.kt        # Bosch GLM 50 C
│   │       │   ├── WhisperService.kt    # Распознавание
│   │       │   ├── XodoService.kt       # PDF
│   │       │   └── ExportService.kt     # XLSX/CSV
│   │       │
│   │       ├── local/
│   │       │   ├── database/
│   │       │   │   ├── AppDatabase.kt
│   │       │   │   ├── MeasurementDao.kt
│   │       │   │   └── ...
│   │       │   ├── json/
│   │       │   │   └── JsonStorage.kt
│   │       │   └── storage/
│   │       │       └── FileStorage.kt
│   │       │
│   │       └── di/                      # Dependency Injection
│   │           └── AppModule.kt
│   │
│   └── res/
│       ├── values/
│       ├── drawable/
│       └── ...
│
└── build.gradle.kts
```

### 4.2. Windows (C#)

#### 4.2.1. Назначение

Windows-приложение для:
- Пост-обработки данных с Android
- Массового экспорта
- Печати отчётов
- Интеграции с CAD (SolidWorks)

#### 4.2.2. Архитектура

```
┌─────────────────────────────────────────────────────────┐
│              WPF / Avalonia UI                          │
│  ┌─────────────────────────────────────────────────┐   │
│  │  - ProjectBrowser                               │   │
│  │  - MeasurementEditor                            │   │
│  │  - ExportWizard                                 │   │
│  │  - ReportViewer                                 │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              Business Logic                             │
│  ┌─────────────────────────────────────────────────┐   │
│  │  - DataProcessor                                │   │
│  │  - ExportGenerator                              │   │
│  │  - ReportBuilder                                │   │
│  │  - CadIntegrator (SolidWorks)                   │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              Data Access                                │
│  ┌─────────────────────────────────────────────────┐   │
│  │  - SQLite Reader (та же БД)                     │   │
│  │  - JSON Parser                                  │   │
│  │  - File Sync                                    │   │
│  └─────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────┘
```

#### 4.2.3. Интеграция с SolidWorks

```csharp
public class SolidWorksIntegrator
{
    private readonly SldWorks _swApp;

    public async Task ImportMeasurementsAsync(
        string projectId,
        List<MeasurementEntity> measurements
    )
    {
        // 1. Открываем или создаём деталь
        var part = _swApp.NewDocument3(...);

        // 2. Для каждого замера
        foreach (var measurement in measurements)
        {
            // 3. Создаём эскиз
            var sketch = part.SketchManager.InsertSketch();

            // 4. Рисуем прямоугольник (дверь/окно)
            var rectangle = sketch.CreateCenterRectangle(
                measurement.Width / 2.0,
                measurement.Height / 2.0,
                0
            );

            // 5. Добавляем размеры
            sketch.AddDimension2(
                rectangle.GetEdge4(0),
                measurement.Width
            );

            // 6. Добавляем заметки
            if (measurement.NotesProcessed != null)
            {
                part.AddNote3(
                    measurement.NotesProcessed,
                    measurement.Position.X,
                    measurement.Position.Y,
                    0
                );
            }
        }

        // 7. Сохраняем
        part.SaveAs3(...);
    }
}
```

---

## ЧАСТЬ 5: ИНТЕГРАЦИИ

### 5.1. Bosch GLM 50 C

#### 5.1.1. Протокол BLE

**Сервисы:**
```
UUID сервиса: 0000fff0-0000-1000-8000-00805f9b34fb
UUID характеристики (RX): 0000fff1-0000-1000-8000-00805f9b34fb
UUID характеристики (TX): 0000fff2-0000-1000-8000-00805f9b34fb
```

**Формат пакета:**
```
Запрос измерения: C0 40 00 EE
Ответ: 00 04 13 0E 00 00 32

Расшифровка:
- 00 04: длина
- 13: тип (измерение)
- 0E 00 00 32: значение (0x3200000E = 2098 мм)
```

#### 5.1.2. Подключение (Kotlin)

```kotlin
class BleService @Inject constructor(
    private val context: Context,
    private val bluetoothManager: BluetoothManager
) : BluetoothGattCallback() {

    private var gatt: BluetoothGatt? = null
    private val measurementCallback = Channel<MeasurementData>()

    suspend fun connect(deviceAddress: String): Result<Unit> {
        return suspendCoroutine { cont ->
            val device = bluetoothManager.adapter.getRemoteDevice(deviceAddress)
            gatt = device.connectGatt(context, false, this)

            // Таймаут подключения
            CoroutineScope(Dispatchers.IO).launch {
                delay(5000)
                if (gatt == null) {
                    cont.resumeWith(Result.failure(Error.ConnectionTimeout))
                }
            }
        }
    }

    override fun onConnectionStateChange(
        gatt: BluetoothGatt,
        status: Int,
        newState: Int
    ) {
        if (newState == BluetoothProfile.STATE_CONNECTED) {
            gatt.discoverServices()
        }
    }

    override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
        val service = gatt.getService(SERVICE_UUID)
        val txChar = service.getCharacteristic(TX_UUID)

        // Подписываемся на уведомления
        gatt.setCharacteristicNotification(txChar, true)
    }

    override fun onCharacteristicChanged(
        gatt: BluetoothGatt,
        characteristic: BluetoothGattCharacteristic
    ) {
        val data = characteristic.value
        val measurement = parseMeasurementData(data)

        CoroutineScope(Dispatchers.IO).launch {
            measurementCallback.send(measurement)
        }
    }

    private fun parseMeasurementData(data: ByteArray): MeasurementData {
        // Парсим формат Bosch
        val value = ((data[3].toInt() and 0xFF) shl 24) or
                    ((data[4].toInt() and 0xFF) shl 16) or
                    ((data[5].toInt() and 0xFF) shl 8) or
                    (data[6].toInt() and 0xFF)

        return MeasurementData(
            value = value,
            unit = "mm",
            timestamp = System.currentTimeMillis()
        )
    }
}
```

### 5.2. Xodo PDF

#### 5.2.1. Интеграция через Intent

```kotlin
class XodoService @Inject constructor(
    private val context: Context
) {
    fun openPdfForAnnotation(projectId: String, pdfFile: File) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(
                FileProvider.getUriForFile(
                    context,
                    "com.measuringassistant.provider",
                    pdfFile
                ),
                "application/pdf"
            )
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            setPackage("com.xodo.pdf.reader")
        }

        context.startActivity(intent)
    }
}
```

#### 5.2.2. Чтение аннотаций

```kotlin
class PdfAnnotationParser {
    fun parseSquareAnnotations(pdfFile: File): List<PdfAnnotation> {
        val document = PdfDocument(pdfFile)
        val annotations = mutableListOf<PdfAnnotation>()

        for (page in 0 until document.pageCount) {
            val pageAnnotations = document.getPage(page).annotations
            for (annotation in pageAnnotations) {
                if (annotation.type == AnnotationType.SQUARE) {
                    annotations.add(
                        PdfAnnotation(
                            page = page,
                            rect = annotation.rect,
                            color = annotation.color,
                            contents = annotation.contents
                        )
                    )
                }
            }
        }

        return annotations
    }
}

data class PdfAnnotation(
    val page: Int,
    val rect: RectF,
    val color: Int,
    val contents: String?
)
```

### 5.3. Whisper (оффлайн)

#### 5.3.1. Интеграция whisper.cpp

```kotlin
class WhisperService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val whisperContext: Long

    init {
        // Инициализация whisper.cpp
        whisperContext = whisper_init_from_file(
            "models/whisper-base.bin"
        )
    }

    suspend fun transcribe(audioFile: File): String {
        return withContext(Dispatchers.Default) {
            val params = whisper_full_params().apply {
                strategy = WHISPER_SAMPLING_GREEDY
                print_progress = false
                translate = false
                no_context = true
            }

            val result = whisper_full(
                whisperContext,
                params,
                audioFile.absolutePath,
                null,
                null
            )

            if (result != 0) {
                throw WhisperException("Transcription failed: $result")
            }

            // Собираем текст из сегментов
            buildString {
                for (i in 0 until whisper_full_n_segments(whisperContext)) {
                    append(whisper_full_get_text(whisperContext, i))
                    append(" ")
                }
            }.trim()
        }
    }

    fun release() {
        whisper_free(whisperContext)
    }
}
```

---

## ЧАСТЬ 6: ЭТАПЫ РАЗРАБОТКИ

### 6.1. Детальный план

| Этап | Длительность | Задачи | Результат |
|------|--------------|--------|-----------|
| **1. BLE + замеры** | 2 недели | - Подключение к Bosch GLM 50 C<br>- Приём измерений<br>- Сохранение в JSON/SQLite | Рабочий прототип приёма замеров |
| **2. Голос** | 2 недели | - Запись аудио<br>- Whisper интеграция<br>- Словарь сокращений | Распознавание заметок |
| **3. PDF** | 2 недели | - Xodo интеграция<br>- Чтение аннотаций<br>- Overlay поверх PDF | Привязка к плану |
| **4. Экспорт** | 1 неделя | - XLSX генерация<br>- CSV экспорт<br>- Фильтры | Нормальный экспорт |
| **5. Windows** | 2 недели | - WPF/Avalonia UI<br>- Чтение БД<br>- SolidWorks интеграция | Пост-обработка |
| **6. Тесты** | 1 неделя | - Реальные объекты<br>- Багфикс<br>- Оптимизация | Готовый продукт |

### 6.2. Критерии приёмки

#### 6.2.1. Android

- [ ] Подключение к Bosch GLM 50 C за ≤10 сек
- [ ] Приём измерений без потерь (100%)
- [ ] Голосовые заметки с распознаванием (оффлайн)
- [ ] Привязка к точке замера (p1/p2/p3)
- [ ] Экспорт XLSX с правильным временем замера
- [ ] Оффлайн работа (без интернета)
- [ ] Время распознавания аудио ≤30 сек

#### 6.2.2. Windows

- [ ] Чтение той же БД (SQLite)
- [ ] Массовый экспорт (≥1000 замеров)
- [ ] Печать отчётов (A4)
- [ ] Интеграция с SolidWorks (импорт геометрии)

---

## ЧАСТЬ 7: ТЕСТИРОВАНИЕ

### 7.1. Сценарии тестирования

#### 7.1.1. Поток замеров

```
1. Открыть приложение
2. Создать новый проект
3. Подключить рулетку
4. Сделать 10 замеров подряд
5. Проверить: все 10 в БД
6. Экспорт в XLSX
7. Проверить: время = время замера
```

#### 7.1.2. Голосовые заметки

```
1. Сделать замер
2. Записать заметку ("левая петля сломана")
3. Подождать 30 сек
4. Проверить: notes_processed = "ЛП слом"
5. Проверить: аудио в папке measurement
```

#### 7.1.3. PDF аннотации

```
1. Открыть PDF в Xodo
2. Нарисовать прямоугольник на двери
3. Вернуться в приложение
4. Проверить: аннотация найдена
5. Тап на зону → выбор замера
```

### 7.2. Метрики качества

| Метрика | Цель | Критично |
|---------|------|----------|
| **Потеря замеров** | 0% | >0% |
| **Время подключения BLE** | ≤10 сек | >30 сек |
| **Время распознавания** | ≤30 сек | >60 сек |
| **Точность OCR** | ≥95% | <90% |
| **Размер APK** | ≤50 MB | >100 MB |

---

## ЧАСТЬ 8: РИСКИ

### 8.1. Технические риски

| Риск | Вероятность | Влияние | Митигация |
|------|-------------|---------|-----------|
| **BLE протокол закрыт** | 🟡 Средняя | 🔴 Высокое | Реверс APK / MITM |
| **Whisper медленный** | 🟡 Средняя | 🟡 Среднее | Vosk (легче) / Сервер |
| **Xodo нет SDK** | 🟢 Высокая | 🟡 Среднее | Свой PDF viewer |
| **SQLite блокировки** | 🟡 Средняя | 🟡 Среднее | Room с транзакциями |

### 8.2. Бизнес-риски

| Риск | Вероятность | Влияние | Митигация |
|------|-------------|---------|-----------|
| **Bosch сменит протокол** | 🟡 Средняя | 🔴 Высокое | Абстракция BLE |
| **Конкуренты выпустят аналог** | 🟡 Средняя | 🟡 Среднее | Фокус на UX |
| **Не хватит ресурсов** | 🟡 Средняя | 🔴 Высокое | MVP сначала |

---

## ПРИЛОЖЕНИЯ

### A. Ссылки на ресурсы

- Bosch GLM 50 C BLE примеры:
  - https://github.com/.../Bosch-GLM50C-Rangefinder
  - https://github.com/.../BOSCH-GLM-rangefinder

- Whisper.cpp Android:
  - https://github.com/ggerganov/whisper.cpp

- Xodo PDF:
  - https://www.xodo.com/

### B. Примеры файлов

- Пример measurement.json (см. раздел 3.2.1)
- Пример SQLite схемы (см. раздел 3.2.2)
- Пример XLSX экспорта (см. раздел 3.5.3)

### C. Контакты

- Разработчик: [Твоё имя]
- Email: [Твой email]
- GitHub: [Твой репозиторий]

---

*Документ создан: 2 апреля 2026 г.*
*Последнее обновление: 2 апреля 2026 г.*
