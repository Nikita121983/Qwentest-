# Медиа и автоматика — Дополнение к спецификации

**Версия:** 1.0
**Дата:** 2 апреля 2026 г.
**Статус:** Черновик
**Дополнение к:** 03_DETAILED_SPEC.md

---

## ЧАСТЬ A: МЕДИА (ФОТО/ВИДЕО/АУДИО)

### A.1. Полная классификация медиа

| Тип | Формат | Макс. размер | Длительность | Назначение |
|-----|--------|--------------|--------------|------------|
| **Аудио** | MP3/OGG | 10 MB | 60 сек | Голосовые заметки |
| **Фото** | JPG/HEIC | 5 MB | — | Фото дефектов, узлов |
| **Видео** | MP4/WebM | 50 MB | 30 сек | Видеообзор помещения |

### A.2. Сценарии использования медиа

#### A.2.1. Аудио (голосовые заметки)

**Сценарий 1: Быстрая заметка к замеру**
```
1. Замер → 900 мм (автоматически сохраняется)
2. Кнопка "🎤" (удерживать)
3. Говоришь: "Левая петля сломана, заменить"
4. Отпускаешь → запись сохраняется
5. Асинхронно: распознавание → применение словаря → БД
```

**Сценарий 2: Общая заметка к объекту**
```
1. Выбор объекта (Д_кух_01)
2. Кнопка "🎤 Заметка к объекту"
3. Говоришь: "Все двери требуют регулировки"
4. Сохраняется в object.notes, не в measurement
```

**Сценарий 3: Пост-обработка**
```
1. Вечером: просмотр всех замеров
2. Фильтр: "без заметок"
3. Дозапись к выбранным замерам
4. Распознавание в фоне
```

#### A.2.2. Фото

**Сценарий 1: Фото дефекта**
```
1. Замер → 900 мм
2. Кнопка "📷"
3. Делаешь фото петли
4. (Опционально) Голос: "петля сломана"
5. Фото + аудио привязываются к точке p1
```

**Сценарий 2: Фото с обводкой**
```
1. Делаешь фото
2. Рисуешь круг/стрелку на дефекте
3. Сохраняется с аннотацией
4. Привязка к замеру
```

**Сценарий 3: Панорама помещения**
```
1. Кнопка "📷 Панорама"
2. Снимаешь 360° помещения
3. Привязка к объекту (комнате)
4. Экспорт в отчёт
```

#### A.2.3. Видео

**Сценарий 1: Видеообзор**
```
1. Кнопка "🎥"
2. Запись 15-30 сек
3. Голосовое описание в фоне
4. Распознавание аудио из видео
5. Привязка к объекту
```

**Сценарий 2: Таймлапс замера**
```
1. Штатив
2. Запись процесса замера
3. Для обучения/контроля
4. Не привязывается к конкретному замеру
```

---

### A.3. Хранение медиа

#### A.3.1. Структура папок

```
/Project_Kitchen/
├── /Measurements/
│   ├── measurement_001/
│   │   ├── measurement.json
│   │   ├── /audio/
│   │   │   ├── audio_001.mp3
│   │   │   └── audio_001.transcript.json
│   │   ├── /photo/
│   │   │   ├── photo_001.jpg
│   │   │   ├── photo_001.annotated.jpg
│   │   │   └── photo_001.meta.json
│   │   └── /video/
│   │       └── video_001.mp4
│   │
│   └── measurement_002/
│       └── ...
│
├── /Objects/
│   ├── D_кух_01/
│   │   ├── object.json
│   │   └── /media/
│   │       ├── overview_photo.jpg
│   │       └── overview_video.mp4
│   └── ...
│
├── /PDF/
│   └── floor_plan.pdf
│
└── project.json
```

#### A.3.2. Метаданные медиа

**audio_001.transcript.json:**
```json
{
  "media_id": "audio_001",
  "measurement_id": "m_001",
  "point_id": "p1",
  "file": "audio_001.mp3",
  "duration": 5.2,
  "size_bytes": 83200,
  "format": "mp3",
  "sample_rate": 16000,
  "channels": 1,

  "transcript": {
    "status": "completed",
    "raw": "левая петля сломана нужно заменить",
    "processed": "ЛП слом нужно зам",
    "confidence": 0.94,
    "language": "ru",
    "model": "whisper-base",
    "processed_at": "2026-04-02T14:30:15.000Z",
    "processing_time_ms": 2340
  },

  "created_at": "2026-04-02T14:30:00.000Z",
  "created_by": "user_001",
  "device": "Samsung Galaxy S21"
}
```

**photo_001.meta.json:**
```json
{
  "media_id": "photo_001",
  "measurement_id": "m_001",
  "point_id": "p1",
  "file": "photo_001.jpg",
  "size_bytes": 2450000,
  "format": "jpg",
  "resolution": {"width": 4000, "height": 3000},

  "exif": {
    "datetime": "2026-04-02T14:30:10.000Z",
    "gps": {"lat": 55.7558, "lon": 37.6173},
    "camera": "Samsung Galaxy S21",
    "flash": false,
    "orientation": 1
  },

  "annotations": [
    {
      "type": "circle",
      "x": 0.45,
      "y": 0.62,
      "radius": 0.08,
      "color": "#FF0000",
      "label": "Дефект петли"
    }
  ],

  "caption": {
    "raw": "сломанная петля",
    "processed": "слом петля"
  },

  "created_at": "2026-04-02T14:30:10.000Z"
}
```

---

### A.4. Автоматическое распознавание

#### A.4.1. Поток обработки аудио

```
┌─────────────────────────────────────────────────────────────────┐
│              ПОТОК РАСПОЗНАВАНИЯ АУДИО                          │
└─────────────────────────────────────────────────────────────────┘

┌──────────────┐
│   Запись     │
│   (MP3)      │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Валидация  │  ← Проверка: файл не пустой, формат верный
└──────┬───────┘
       │ ✅
       ▼
┌──────────────┐
│   Очередь    │  ← FIFO очередь (Channel в Kotlin)
│   (FIFO)     │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Whisper    │  ← whisper.cpp (локально на телефоне)
│   (CPU/GPU)  │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Текст      │  ← "левая петля сломана нужно заменить"
│   (raw)      │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Словарь    │  ← Применение сокращений
│   (NLP)      │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Текст      │  ← "ЛП слом нужно зам"
│   (processed)│
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   БД         │  ← UPDATE media SET transcript = ...
│   (update)   │
└──────┬───────┘
       │
       ▼
┌──────────────┐
│   Уведомление│  ← "Распознавание завершено"
│   (UI)       │
└──────────────┘
```

#### A.4.2. Реализация очереди (детально)

```kotlin
@Singleton
class TranscriptionQueue @Inject constructor(
    private val whisperService: WhisperService,
    private val abbreviationService: AbbreviationService,
    private val mediaRepository: MediaRepository,
    private val notificationService: NotificationService
) {
    // Канал для очереди задач
    private val queue = Channel<TranscriptionTask>(Channel.UNLIMITED)

    // Статусы задач
    private val taskStatus = MutableStateFlow<Map<String, TaskStatus>>(emptyMap())

    init {
        // Запускаем воркера в фоне
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            queue.consumeAsFlow().collect { task ->
                processTask(task)
            }
        }

        // Периодическая очистка завершённых задач
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(60_000) // Каждую минуту
                cleanupCompletedTasks()
            }
        }
    }

    /**
     * Добавить задачу в очередь
     */
    suspend fun enqueue(mediaId: String, audioFile: File): Result<Unit> {
        // Проверяем файл
        if (!audioFile.exists()) {
            return Result.failure(Error.FileNotFound)
        }

        if (audioFile.length() == 0L) {
            return Result.failure(Error.EmptyFile)
        }

        // Обновляем статус
        mediaRepository.updateTranscriptStatus(mediaId, "pending")
        updateTaskStatus(mediaId, TaskStatus.PENDING)

        // Добавляем в очередь
        queue.send(TranscriptionTask(mediaId, audioFile))

        return Result.success(Unit)
    }

    /**
     * Обработка одной задачи
     */
    private suspend fun processTask(task: TranscriptionTask) {
        val startTime = System.currentTimeMillis()

        try {
            // 1. Обновляем статус
            updateTaskStatus(task.mediaId, TaskStatus.PROCESSING)
            mediaRepository.updateTranscriptStatus(task.mediaId, "processing")

            // 2. Распознавание (Whisper)
            val rawText = whisperService.transcribe(task.audioFile)

            // 3. Применяем словарь сокращений
            val processedText = abbreviationService.apply(rawText)

            // 4. Сохраняем в БД
            mediaRepository.saveTranscript(
                mediaId = task.mediaId,
                raw = rawText,
                processed = processedText,
                status = "completed",
                confidence = whisperService.lastConfidence,
                processingTimeMs = System.currentTimeMillis() - startTime
            )

            // 5. Обновляем статус
            updateTaskStatus(task.mediaId, TaskStatus.COMPLETED)

            // 6. Уведомление
            notificationService.showTranscriptionComplete(task.mediaId)

        } catch (e: WhisperException) {
            handleTranscriptionError(task.mediaId, e, "Whisper error")
        } catch (e: Exception) {
            handleTranscriptionError(task.mediaId, e, "Unknown error")
        }
    }

    private suspend fun handleTranscriptionError(
        mediaId: String,
        e: Exception,
        context: String
    ) {
        mediaRepository.updateTranscriptStatus(
            mediaId = mediaId,
            status = "failed",
            error = "${context}: ${e.message}"
        )
        updateTaskStatus(mediaId, TaskStatus.FAILED)
        notificationService.showTranscriptionFailed(mediaId, e.message)
    }

    /**
     * Отмена задачи
     */
    suspend fun cancel(mediaId: String) {
        // Удаляем из очереди (если ещё не начала выполняться)
        // Помечаем как отменённую в БД
        mediaRepository.updateTranscriptStatus(mediaId, "cancelled")
    }

    /**
     * Получить статус задачи
     */
    fun getStatus(mediaId: String): StateFlow<TaskStatus?> {
        return taskStatus.map { statuses -> statuses[mediaId] }
    }
}

data class TranscriptionTask(
    val mediaId: String,
    val audioFile: File,
    val createdAt: Long = System.currentTimeMillis()
)

enum class TaskStatus {
    PENDING,      // В очереди
    PROCESSING,   // Распознавание
    COMPLETED,    // Готово
    FAILED,       // Ошибка
    CANCELLED     // Отменено
}
```

#### A.4.3. Словарь сокращений (детально)

**База сокращений (SQLite):**
```sql
CREATE TABLE Abbreviations (
    abbrev_id INTEGER PRIMARY KEY AUTOINCREMENT,
    full_term TEXT NOT NULL,
    short_term TEXT NOT NULL,
    category TEXT NOT NULL, -- 'parts', 'defects', 'rooms', 'objects', 'actions'
    subcategory TEXT,
    active BOOLEAN DEFAULT TRUE,
    priority INTEGER DEFAULT 0,
    case_sensitive BOOLEAN DEFAULT FALSE,
    whole_word_only BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Индексы
CREATE INDEX idx_abbreviations_category ON Abbreviations(category);
CREATE INDEX idx_abbreviations_active ON Abbreviations(active);
CREATE INDEX idx_abbreviations_priority ON Abbreviations(priority);

-- Примеры данных
INSERT INTO Abbreviations (full_term, short_term, category, priority) VALUES
-- Детали дверей
('левая петля', 'ЛП', 'parts', 10),
('правая петля', 'ПП', 'parts', 10),
('ручка', 'Р', 'parts', 10),
('замок', 'З', 'parts', 10),
('доводчик', 'Дов', 'parts', 10),
('уплотнитель', 'Упл', 'parts', 10),

-- Дефекты
('сломана', 'слом', 'defects', 20),
('царапина', 'царап', 'defects', 20),
('вмятина', 'вмят', 'defects', 20),
('трещина', 'трещ', 'defects', 20),
('скол', 'ск', 'defects', 20),
('коррозия', 'корр', 'defects', 20),

-- Помещения
('кухня', 'кух', 'rooms', 30),
('ванная', 'ван', 'rooms', 30),
('спальня', 'сп', 'rooms', 30),
('гостиная', 'гост', 'rooms', 30),
('коридор', 'кор', 'rooms', 30),
('туалет', 'с/у', 'rooms', 30),

-- Объекты
('дверь', 'Д', 'objects', 40),
('окно', 'ОК', 'objects', 40),
('ворота', 'В', 'objects', 40),
('люк', 'Л', 'objects', 40),

-- Действия
('заменить', 'зам', 'actions', 50),
('отрегулировать', 'рег', 'actions', 50),
('покрасить', 'кр', 'actions', 50),
('установить', 'уст', 'actions', 50);
```

**Алгоритм применения (Kotlin):**
```kotlin
@Singleton
class AbbreviationService @Inject constructor(
    private val abbreviationDao: AbbreviationDao,
    private val preferences: UserPreferences
) {
    // Кэш для производительности
    private val abbreviationCache = MutableStateFlow<List<Abbreviation>>(emptyList())

    init {
        // Загружаем сокращения при старте
        CoroutineScope(Dispatchers.IO).launch {
            abbreviationDao.getAllActive()
                .collect { abbreviations ->
                    abbreviationCache.value = abbreviations.sortedBy { it.priority }
                }
        }
    }

    /**
     * Применить сокращения к тексту
     */
    suspend fun apply(text: String): String {
        var result = text
        val abbreviations = abbreviationCache.value

        // Группируем по категориям (порядок важен!)
        val grouped = abbreviations.groupBy { it.category }

        // Применяем по порядку категорий
        val categoryOrder = listOf("parts", "defects", "rooms", "objects", "actions")

        for (category in categoryOrder) {
            val categoryAbbrevs = grouped[category] ?: continue

            for (abbrev in categoryAbbrevs) {
                result = applyAbbreviation(result, abbrev)
            }
        }

        // Очищаем лишние пробелы
        result = result.replace(Regex("\\s+"), " ").trim()

        return result
    }

    private fun applyAbbreviation(text: String, abbrev: Abbreviation): String {
        return if (abbrev.wholeWordOnly) {
            // Замена только целых слов
            val pattern = Regex("\\b${Regex.escape(abbrev.fullTerm)}\\b",
                RegexOption.IGNORE_CASE.takeIf { !abbrev.caseSensitive } ?: emptySet())
            text.replace(pattern, abbrev.shortTerm)
        } else {
            // Замена любых вхождений
            val pattern = Regex(Regex.escape(abbrev.fullTerm),
                RegexOption.IGNORE_CASE.takeIf { !abbrev.caseSensitive } ?: emptySet())
            text.replace(pattern, abbrev.shortTerm)
        }
    }

    /**
     * Добавить новое сокращение (пользовательское)
     */
    suspend fun addCustomAbbreviation(fullTerm: String, shortTerm: String, category: String) {
        abbreviationDao.insert(
            Abbreviation(
                fullTerm = fullTerm,
                shortTerm = shortTerm,
                category = category,
                priority = 100, // Пользовательские после системных
                active = true
            )
        )
    }

    /**
     * Обучить на основе частых замен
     */
    suspend fun learnFromUsage(rawText: String, processedText: String) {
        // Анализируем разницу
        // Предлагаем новые сокращения
        // (ML-компонент для авто-обучения)
    }
}

data class Abbreviation(
    val id: Long,
    val fullTerm: String,
    val shortTerm: String,
    val category: String,
    val subcategory: String?,
    val active: Boolean,
    val priority: Int,
    val caseSensitive: Boolean,
    val wholeWordOnly: Boolean
)
```

#### A.4.4. Автоматическое присвоение подписей (Computer Vision)

**Для фото:**
```kotlin
class PhotoAnalysisService @Inject constructor(
    private val mlKitService: MLKitService,
    private val mediaRepository: MediaRepository
) {
    /**
     * Автоматический анализ фото
     */
    suspend fun analyze(photoFile: File, measurementId: String): Result<PhotoAnalysis> {
        return try {
            // 1. Распознавание объектов (дверь, окно, стена)
            val objects = mlKitService.detectObjects(photoFile)

            // 2. Распознавание текста (OCR)
            val text = mlKitService.detectText(photoFile)

            // 3. Классификация дефектов
            val defects = mlKitService.detectDefects(photoFile)

            // 4. Генерируем подпись
            val caption = generateCaption(objects, text, defects)

            // 5. Сохраняем в БД
            mediaRepository.savePhotoAnalysis(
                measurementId = measurementId,
                objects = objects,
                text = text,
                defects = defects,
                caption = caption
            )

            Result.success(PhotoAnalysis(objects, text, defects, caption))

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun generateCaption(
        objects: List<DetectedObject>,
        text: List<String>,
        defects: List<Defect>
    ): String {
        val parts = mutableListOf<String>()

        // Объекты
        if (objects.any { it.label == "door" }) {
            parts.add("Дверь")
        }

        // Дефекты
        for (defect in defects) {
            parts.add("${defect.type}: ${defect.confidence}%")
        }

        // Текст
        if (text.isNotEmpty()) {
            parts.add("Текст: ${text.joinToString(", ")}")
        }

        return parts.joinToString(" | ")
    }
}

data class PhotoAnalysis(
    val objects: List<DetectedObject>,
    val text: List<String>,
    val defects: List<Defect>,
    val caption: String
)

data class DetectedObject(
    val label: String,
    val confidence: Float,
    val boundingBox: Rect
)

data class Defect(
    val type: String, // "scratch", "dent", "crack"
    val confidence: Float,
    val location: Point
)
```

---

## ЧАСТЬ B: АВТОМАТИЗАЦИЯ

### B.1. Автоматическое создание замера

**Сценарий:**
```
1. Рулетка отправляет измерение по BLE
2. Приложение получает: 900 мм
3. Автоматически создаётся:
   - measurement_id
   - timestamp
   - device_info
4. Пользователь видит: "Новый замер: 900 мм"
5. Может добавить:
   - Название (Д_кух_01)
   - Точки (p1, p2, p3)
   - Голосовую заметку
```

**Код:**
```kotlin
class MeasurementAutoCreator @Inject constructor(
    private val bleService: BleService,
    private val measurementRepository: MeasurementRepository,
    private val context: Context
) {
    private val _newMeasurements = MutableStateFlow<MeasurementEntity>(emptyList())
    val newMeasurements: StateFlow<List<MeasurementEntity>> = _newMeasurements

    init {
        // Слушаем измерения от рулетки
        CoroutineScope(Dispatchers.IO).launch {
            bleService.measurementFlow.collect { measurementData ->
                val entity = MeasurementEntity(
                    id = generateId(),
                    timestamp = measurementData.timestamp,
                    deviceModel = bleService.deviceModel,
                    points = listOf(
                        PointEntity(
                            id = "p1",
                            name = "Измерение",
                            value = measurementData.value,
                            unit = measurementData.unit
                        )
                    ),
                    autoCreated = true
                )

                measurementRepository.insert(entity)
                _newMeasurements.value = _newMeasurements.value + entity

                // Уведомление
                notificationService.showNewMeasurement(entity)
            }
        }
    }

    private fun generateId(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            .format(Date())
        return "m_${timestamp}_${Random.nextInt(1000)}"
    }
}
```

### B.2. Автоматическая привязка к PDF

**Сценарий:**
```
1. Пользователь открывает PDF план
2. Тапает на дверь (координаты x=0.32, y=0.48)
3. Приложение находит ближайший замер
4. Привязывает замер к координатам
5. Сохраняет в measurement.pdf_ref
```

**Код:**
```kotlin
class PdfAutoAttachment @Inject constructor(
    private val measurementRepository: MeasurementRepository,
    private val pdfService: PdfService
) {
    /**
     * Привязать замер к точке на PDF
     */
    suspend fun attachToPdf(
        measurementId: String,
        pdfFile: File,
        page: Int,
        tapX: Float,
        tapY: Float
    ): Result<Unit> {
        // Находим ближайшую аннотацию (прямоугольник двери)
        val annotations = pdfService.parseAnnotations(pdfFile)
        val nearestAnnotation = findNearestAnnotation(annotations, page, tapX, tapY)
            ?: return Result.failure(Error.NoAnnotationFound)

        // Сохраняем привязку
        measurementRepository.updatePdfRef(
            measurementId = measurementId,
            pdfRef = PdfRef(
                file = pdfFile.name,
                page = page,
                rect = nearestAnnotation.rect,
                annotationId = nearestAnnotation.id
            )
        )

        return Result.success(Unit)
    }

    private fun findNearestAnnotation(
        annotations: List<PdfAnnotation>,
        page: Int,
        tapX: Float,
        tapY: Float
    ): PdfAnnotation? {
        return annotations
            .filter { it.page == page }
            .filter { it.type == AnnotationType.SQUARE }
            .minByOrNull { annotation ->
                distance(tapX, tapY, annotation.rect.centerX(), annotation.rect.centerY())
            }
    }

    private fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return kotlin.math.sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    }
}
```

### B.3. Автоматический экспорт по расписанию

**Сценарий:**
```
1. Пользователь настраивает: "Экспорт каждый день в 18:00"
2. В 18:00 приложение:
   - Находит все замеры за день с export=true
   - Генерирует XLSX
   - Сохраняет в /Reports/
   - (Опционально) Отправляет на email/в облако
3. Уведомление: "Экспорт завершён: 15 замеров"
```

**Код:**
```kotlin
@Singleton
class AutoExportService @Inject constructor(
    private val context: Context,
    private val exportService: ExportService,
    private val measurementRepository: MeasurementRepository
) {
    private val workManager = WorkManager.getInstance(context)

    fun scheduleDailyExport(hour: Int = 18, minute: Int = 0) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED) // Оффлайн
            .setRequiresBatteryNotLow(true)
            .build()

        val exportRequest = PeriodicWorkRequestBuilder<ExportWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(calculateInitialDelay(hour, minute), TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "daily_export",
            ExistingPeriodicWorkPolicy.REPLACE,
            exportRequest
        )
    }

    private fun calculateInitialDelay(hour: Int, minute: Int): Long {
        val now = Calendar.getInstance()
        val exportTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        return if (now.before(exportTime)) {
            exportTime.timeInMillis - now.timeInMillis
        } else {
            // Завтра
            exportTime.add(Calendar.DAY_OF_YEAR, 1)
            exportTime.timeInMillis - now.timeInMillis
        }
    }
}

class ExportWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val exportService = ExportService(applicationContext)
            val measurements = getTodayMeasurements()

            val outputFile = File(
                applicationContext.getExternalFilesDir("Reports"),
                "export_${LocalDate.now()}.xlsx"
            )

            exportService.export(measurements, outputFile)

            // Уведомление
            showNotification(outputFile, measurements.size)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun getTodayMeasurements(): List<MeasurementEntity> {
        val today = LocalDate.now()
        val from = today.atStartOfDay()
        val to = today.atTime(23, 59, 59)

        return measurementRepository.getByDateRange(from, to)
            .filter { it.export }
    }
}
```

---

## ЧАСТЬ C: UX ДЛЯ МЕДИА

### C.1. Индикаторы статуса

| Статус | Иконка | Цвет | Описание |
|--------|--------|------|----------|
| **Ожидание** | ⏳ | Жёлтый | В очереди на распознавание |
| **Обработка** | 🔄 | Синий | Распознавание идёт |
| **Готово** | ✅ | Зелёный | Распознано, применён словарь |
| **Ошибка** | ❌ | Красный | Не удалось распознать |
| **Отменено** | 🚫 | Серый | Пользователь отменил |

### C.2. Уведомления

```kotlin
class MediaNotificationService @Inject constructor(
    private val context: Context
) {
    fun showTranscriptionStart(mediaId: String) {
        notify(mediaId, 1001) {
            setContentTitle("Распознавание...")
            setContentText("Обработка голосовой заметки")
            setSmallIcon(R.drawable.ic_mic)
            setProgress(100, 0, true) // Indeterminate
        }
    }

    fun showTranscriptionProgress(mediaId: String, progress: Int) {
        notify(mediaId, 1001) {
            setContentTitle("Распознавание...")
            setContentText("Обработка голосовой заметки")
            setSmallIcon(R.drawable.ic_mic)
            setProgress(100, progress, false)
        }
    }

    fun showTranscriptionComplete(mediaId: String, transcript: String) {
        notify(mediaId, 1001) {
            setContentTitle("✅ Готово")
            setContentText("Распознано: $transcript")
            setSmallIcon(R.drawable.ic_check)
        }
    }

    fun showTranscriptionFailed(mediaId: String, error: String) {
        notify(mediaId, 1001) {
            setContentTitle("❌ Ошибка")
            setContentText("Не удалось распознать: $error")
            setSmallIcon(R.drawable.ic_error)
        }
    }
}
```

### C.3. Просмотр медиа в приложении

**Экран замера:**
```kotlin
@Composable
fun MeasurementDetailScreen(
    viewModel: MeasurementViewModel,
    measurementId: String
) {
    val measurement by viewModel.getMeasurement(measurementId)
        .collectAsState(initial = null)

    if (measurement == null) return

    Column {
        // Заголовок
        Text(measurement!!.objectId)
        Text(measurement!!.timestamp.format())

        // Точки замера
        measurement!!.points.forEach { point ->
            PointCard(
                point = point,
                onRecordNote = { viewModel.startRecording(measurementId, point.id) },
                onTakePhoto = { viewModel.takePhoto(measurementId, point.id) }
            )

            // Медиа точки
            point.media.forEach { media ->
                MediaCard(media = media)
            }
        }

        // Кнопки действий
        Row {
            Button(onRecordNote = { viewModel.startRecording(measurementId, null) }) {
                Icon(Icons.Default.Mic)
                Text("Заметка")
            }

            Button(onTakePhoto = { viewModel.takePhoto(measurementId, null) }) {
                Icon(Icons.Default.CameraAlt)
                Text("Фото")
            }
        }
    }
}

@Composable
fun MediaCard(media: MediaEntity) {
    Card {
        Row {
            when (media.type) {
                "audio" -> {
                    IconButton(onClick = { playAudio(media.file) }) {
                        Icon(Icons.Default.PlayArrow)
                    }
                    Text("${media.duration}s")

                    // Статус распознавания
                    when (media.transcriptStatus) {
                        "pending" -> CircularProgressIndicator()
                        "completed" -> Text(media.transcriptProcessed)
                        "failed" -> Text("Ошибка", color = Color.Red)
                    }
                }

                "photo" -> {
                    AsyncImage(
                        model = media.file,
                        contentDescription = "Фото замера"
                    )
                    Text(media.caption ?: "")
                }

                "video" -> {
                    // Video player
                }
            }
        }
    }
}
```

---

## ПРИЛОЖЕНИЯ

### D.1. Чек-лист реализации медиа

- [ ] Запись аудио (MP3/OGG)
- [ ] Whisper.cpp интеграция
- [ ] Очередь задач (Channel)
- [ ] Словарь сокращений (SQLite)
- [ ] Фото (камера)
- [ ] Видео (камера)
- [ ] Метаданные (JSON)
- [ ] Просмотр в UI
- [ ] Уведомления о статусе
- [ ] Экспорт с медиа

### D.2. Метрики качества медиа

| Метрика | Цель | Критично |
|---------|------|----------|
| **Время распознавания** | ≤30 сек | >60 сек |
| **Точность распознавания** | ≥95% | <90% |
| **Размер аудио (1 мин)** | ≤1 MB | >2 MB |
| **Качество фото** | ≥2 MP | <1 MP |
| **Качество видео** | 720p | <480p |

---

*Документ создан: 2 апреля 2026 г.*
*Дополнение к: 03_DETAILED_SPEC.md*
