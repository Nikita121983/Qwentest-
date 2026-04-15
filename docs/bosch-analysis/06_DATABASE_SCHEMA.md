# Схема базы данных (SQLite)

**Путь:** `/data/data/com.measuringassistant/databases/app.db`

---

## Таблицы

### Projects

```sql
CREATE TABLE Projects (
    project_id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    abbreviations_path TEXT,  -- Путь к словарю сокращений
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME,
    status TEXT DEFAULT 'active',  -- 'active', 'archived', 'deleted'
    sync_path TEXT  -- Путь для Syncthing
);
```

### Measurements

```sql
CREATE TABLE Measurements (
    measurement_id TEXT PRIMARY KEY,
    project_id TEXT NOT NULL,
    object_id TEXT,
    object_type TEXT,  -- 'door', 'window', 'wall', 'room'
    timestamp DATETIME NOT NULL,  -- Время замера (критично!)
    device_model TEXT,  -- 'Bosch GLM 50 C'
    device_connection TEXT,  -- 'BLE', 'manual'
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME,
    export BOOLEAN DEFAULT TRUE,  -- Флаг на экспорт
    exported_at DATETIME,
    pdf_page INTEGER,
    pdf_rect_x REAL,
    pdf_rect_y REAL,
    pdf_rect_w REAL,
    pdf_rect_h REAL,
    FOREIGN KEY(project_id) REFERENCES Projects(project_id),
    FOREIGN KEY(object_id) REFERENCES Objects(object_id)
);

-- Индексы
CREATE INDEX idx_measurements_project ON Measurements(project_id);
CREATE INDEX idx_measurements_object ON Measurements(object_id);
CREATE INDEX idx_measurements_timestamp ON Measurements(timestamp);
CREATE INDEX idx_measurements_export ON Measurements(export);
```

### Objects

```sql
CREATE TABLE Objects (
    object_id TEXT PRIMARY KEY,
    project_id TEXT NOT NULL,
    name TEXT,  -- 'Д_кух_01', 'ОК_зал'
    type TEXT NOT NULL,  -- 'door', 'window', 'room'
    subtype TEXT,  -- 'interior', 'exterior', 'bathroom'
    floor INTEGER,
    room_name TEXT,  -- 'Кухня', 'Ванная'
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(project_id) REFERENCES Projects(project_id)
);

-- Индексы
CREATE INDEX idx_objects_project ON Objects(project_id);
CREATE INDEX idx_objects_type ON Objects(type);
```

### Points

```sql
CREATE TABLE Points (
    point_id TEXT PRIMARY KEY,
    measurement_id TEXT NOT NULL,
    name TEXT,  -- 'Ширина (верх)', 'Высота (левая)'
    code TEXT,  -- 'W_TOP', 'H_LEFT'
    value REAL NOT NULL,  -- Значение в мм
    unit TEXT DEFAULT 'mm',
    accuracy TEXT,  -- '±1mm'
    notes_raw TEXT,  -- Исходный текст из аудио
    notes_processed TEXT,  -- После применения словаря
    sort_order INTEGER DEFAULT 0,  -- Порядок точек
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(measurement_id) REFERENCES Measurements(measurement_id)
);

-- Индексы
CREATE INDEX idx_points_measurement ON Points(measurement_id);
CREATE INDEX idx_points_notes ON Points(notes_processed);
```

### Media

```sql
CREATE TABLE Media (
    media_id TEXT PRIMARY KEY,
    measurement_id TEXT,
    point_id TEXT,
    media_type TEXT NOT NULL,  -- 'audio', 'photo', 'video'
    file_path TEXT NOT NULL,  -- Полный путь к файлу
    file_size INTEGER,  -- Размер в байтах
    duration REAL,  -- Для аудио/видео (секунды)
    caption TEXT,  -- Описание
    timestamp DATETIME,  -- Время создания медиа
    transcript_status TEXT,  -- 'pending', 'processing', 'completed', 'failed'
    transcript_raw TEXT,  -- Исходная расшифровка
    transcript_processed TEXT,  -- После словаря
    transcript_confidence REAL,  -- Точность распознавания (0-1)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(measurement_id) REFERENCES Measurements(measurement_id),
    FOREIGN KEY(point_id) REFERENCES Points(point_id)
);

-- Индексы
CREATE INDEX idx_media_measurement ON Media(measurement_id);
CREATE INDEX idx_media_point ON Media(point_id);
CREATE INDEX idx_media_type ON Media(media_type);
CREATE INDEX idx_media_transcript_status ON Media(transcript_status);
```

### Abbreviations (кэш из файла)

```sql
CREATE TABLE Abbreviations (
    abbrev_id INTEGER PRIMARY KEY AUTOINCREMENT,
    full_term TEXT NOT NULL,
    short_term TEXT NOT NULL,
    category TEXT NOT NULL,  -- 'parts', 'defects', 'rooms', 'objects'
    subcategory TEXT,
    priority INTEGER DEFAULT 0,
    case_sensitive BOOLEAN DEFAULT FALSE,
    whole_word_only BOOLEAN DEFAULT TRUE,
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Индексы
CREATE INDEX idx_abbreviations_category ON Abbreviations(category);
CREATE INDEX idx_abbreviations_active ON Abbreviations(active);
CREATE INDEX idx_abbreviations_priority ON Abbreviations(priority);
```

### TranscriptionQueue (очередь задач)

```sql
CREATE TABLE TranscriptionQueue (
    task_id TEXT PRIMARY KEY,
    media_id TEXT NOT NULL,
    audio_file_path TEXT NOT NULL,
    status TEXT NOT NULL,  -- 'pending', 'processing', 'completed', 'failed'
    error_message TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    started_at DATETIME,
    completed_at DATETIME,
    processing_time_ms INTEGER,
    FOREIGN KEY(media_id) REFERENCES Media(media_id)
);

-- Индексы
CREATE INDEX idx_queue_status ON TranscriptionQueue(status);
CREATE INDEX idx_queue_created ON TranscriptionQueue(created_at);
```

---

## Представления (Views)

### v_Measurements_With_Points

```sql
CREATE VIEW v_Measurements_With_Points AS
SELECT
    m.measurement_id,
    m.object_id,
    m.timestamp,
    p.point_id,
    p.name AS point_name,
    p.value,
    p.unit,
    p.notes_raw,
    p.notes_processed,
    m.export
FROM Measurements m
LEFT JOIN Points p ON m.measurement_id = p.measurement_id
ORDER BY m.timestamp ASC, p.sort_order ASC;
```

### v_Measurements_With_Media

```sql
CREATE VIEW v_Measurements_With_Media AS
SELECT
    m.measurement_id,
    m.object_id,
    m.timestamp,
    media.media_id,
    media.media_type,
    media.file_path,
    media.transcript_status,
    media.transcript_processed
FROM Measurements m
LEFT JOIN Media media ON m.measurement_id = media.measurement_id
ORDER BY m.timestamp ASC;
```

---

## Триггеры

### tr_UpdateModifiedAt

```sql
CREATE TRIGGER tr_UpdateModifiedAt_Measurements
AFTER UPDATE ON Measurements
BEGIN
    UPDATE Measurements SET modified_at = CURRENT_TIMESTAMP
    WHERE measurement_id = NEW.measurement_id;
END;
```

### tr_AfterInsertMedia

```sql
CREATE TRIGGER tr_AfterInsertMedia
AFTER INSERT ON Media
WHEN NEW.media_type = 'audio'
BEGIN
    -- Добавить задачу в очередь распознавания
    INSERT INTO TranscriptionQueue (task_id, media_id, audio_file_path, status)
    VALUES (
        'task_' || datetime('now') || '_' || NEW.media_id,
        NEW.media_id,
        NEW.file_path,
        'pending'
    );
END;
```

---

## Примеры запросов

### Получить все замеры за сегодня

```sql
SELECT * FROM Measurements
WHERE date(timestamp) = date('now')
ORDER BY timestamp ASC;
```

### Получить замеры с распознанными заметками

```sql
SELECT
    m.measurement_id,
    m.object_id,
    p.notes_processed,
    media.transcript_processed
FROM Measurements m
JOIN Points p ON m.measurement_id = p.measurement_id
JOIN Media media ON m.measurement_id = media.measurement_id
WHERE media.transcript_status = 'completed'
  AND date(m.timestamp) = date('now');
```

### Получить замеры на экспорт

```sql
SELECT * FROM Measurements
WHERE export = TRUE
  AND exported_at IS NULL
ORDER BY timestamp ASC;
```

---

*Схема создана: 2 апреля 2026 г.*
