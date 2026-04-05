# Схема базы данных GlmReaderAndroid

**Дата:** 4 апреля 2026 г., 02:30 MSK
**Версия:** 2 (с учетом инклинометра и настроек)

---

## Принцип

- **БД** — нормализованная, хранит сырые данные и настройки логики.
- **Экспорт XLSX** — берет `measurement_type` (уже определенный логикой/пользователем) и показывает его в колонке "Вид".

---

## Таблица: `measurements`

| Столбец | Тип | Описание | Пример |
|---------|-----|----------|--------|
| `id` | TEXT (UUID) PK | Уникальный ID замера | `a1b2...` |
| `sort_order` | INT | Порядок отображения | `0` |
| `measurement_type` | INT | **Итоговый тип** (для экспорта) | `10` (IndHeight) |
| `ref_edge` | INT | Точка отсчёта (0-3) | `0` |
| `result_mm` | REAL | **Основной результат** (мм) | `1444.6` |
| `comp1_mm` | REAL | Компонент 1 (a) | `1850.4` |
| `comp2_mm` | REAL | Компонент 2 (b) | `51.0` |
| `comp3_mm` | REAL | Компонент 3 (c) | `NULL` |
| `angle_deg` | REAL | **Показание инклинометра** | `51.0` |
| `laser_on` | INT | Лазер (0/1) | `1` |
| `timestamp` | LONG | Время замера | `1712246400000` |
| `created_at` | LONG | Время создания | `1712246400000` |
| `updated_at` | LONG | Время изменения | `1712246400000` |
| `is_deleted` | INT | Soft Delete (0/1) | `0` |
| `project_id` | TEXT (UUID) FK | Проект | `proj_001` |
| `group_id` | TEXT (UUID) FK | Группа | `grp_001` |
| `object_id` | TEXT | Объект | `D_кух_01` |
| `device_name` | TEXT | Рулетка | `GLM 50 C` |
| `protocol_version` | INT | Версия протокола | `1` |
| `ble_packet_hex` | TEXT | Сырой пакет HEX | `C0 55...` |
| `metadata_json` | TEXT | Доп. метаданные | `{}` |

> **Логика инклинометра:** При получении пакета поле `angle_deg` заполняется сырым значением. Приложение проверяет настройки и обновляет `measurement_type`. Именно `measurement_type` попадает в экспорт.

---

## Таблица: `settings`

Хранит настройки приложения, включая пороги инклинометра.

| Столбец | Тип | Описание | Пример значения |
|---------|-----|----------|-----------------|
| `key` | TEXT PK | Ключ настройки | `inclino_mode` |
| `value` | TEXT | Значение | `auto` |
| `updated_at` | LONG | Время изменения | `1712246400000` |

**Ключи настроек:**

| Ключ | Описание | Варианты | По умолчанию |
|------|----------|----------|--------------|
| `inclino_mode` | Режим перехода | `auto`, `ask`, `voice`, `manual` | `ask` |
| `inclino_threshold` | Порог угла (°) | Число (float) | `5.0` |
| `theme_mode` | Тема интерфейса | `light`, `dark`, `system` | `system` |
| `export_include_headers` | Заголовки в XLSX | `0`, `1` | `1` |
| `default_project_id` | Проект по умолч. | UUID | `NULL` |

---

## Таблица: `notes`

| Столбец | Тип | Описание |
|---------|-----|----------|
| `id` | TEXT (UUID) PK | ID заметки |
| `measurement_id` | TEXT (UUID) FK | Привязка к замеру |
| `version` | INT | Версия |
| `text` | TEXT | Текст |
| `created_at` | LONG | Время создания |
| `is_primary` | INT | Основная (0/1) |
| `is_voice` | INT | Голосовая (0/1) |
| `media_path` | TEXT | Путь к файлу |

---

## Таблица: `projects`

| Столбец | Тип | Описание |
|---------|-----|----------|
| `id` | TEXT (UUID) PK | ID проекта |
| `name` | TEXT | Название |
| `description` | TEXT | Описание |
| `created_at` | LONG | Создание |
| `updated_at` | LONG | Изменение |
| `is_active` | INT | Активен (0/1) |

---

## Таблица: `groups`

| Столбец | Тип | Описание |
|---------|-----|----------|
| `id` | TEXT (UUID) PK | ID группы |
| `project_id` | TEXT (UUID) FK | Проект |
| `name` | TEXT | Название |
| `created_at` | LONG | Создание |
| `updated_at` | LONG | Изменение |
| `is_default` | INT | По умолч. (0/1) |
| `sort_order` | INT | Порядок |

---

## Таблица: `raw_log`

| Столбец | Тип | Описание |
|---------|-----|----------|
| `id` | INTEGER PK AUTO | ID |
| `timestamp` | LONG | Время |
| `raw_bytes_hex` | TEXT | HEX пакета |
| `packet_len` | INT | Длина |
| `parsed_ok` | INT | Успех (0/1) |
| `error_msg` | TEXT | Ошибка |
| `measurement_id` | TEXT (UUID) FK | Связь с замером |

---

## Таблица: `objects`

| Столбец | Тип | Описание |
|---------|-----|----------|
| `id` | TEXT (UUID) PK | ID объекта |
| `project_id` | TEXT (UUID) FK | Проект |
| `name` | TEXT | Название (`D_кух_01`) |
| `type` | TEXT | Тип (`door`, `window`) |
| `created_at` | LONG | Время создания |

---

## Формат XLSX экспорта (Запрос)

Этот запрос формирует **одну строку экспорта** из нормализованной БД.

```sql
SELECT
    m.id AS "ID",
    m.sort_order AS "№",

    -- Колонка "Вид" зависит от итогового measurement_type
    CASE m.measurement_type
        WHEN 1 THEN 'Прямой замер'
        WHEN 10 THEN 'Косвенная высота'
        WHEN 11 THEN 'Косвенная длина'
        WHEN 7 THEN 'Объём'
        WHEN 4 THEN 'Площадь'
        WHEN 8 THEN 'Угол'
        WHEN 12 THEN 'Двойная косвенная'
        ELSE 'Тип ' || m.measurement_type
    END AS "Вид",

    -- Берем только основную версию заметки
    n.text AS "Подпись",

    m.result_mm AS "Результат (мм)",
    m.comp1_mm AS "Компонент A (мм)",
    m.comp2_mm AS "Компонент B (мм)",
    m.comp3_mm AS "Компонент C (мм)",

    -- Инклинометр выводится отдельно
    m.angle_deg AS "Инклинометр (°)",

    -- Точка отсчёта маппится из числа
    CASE m.ref_edge
        WHEN 0 THEN 'Задняя грань'
        WHEN 1 THEN 'Ось штатива'
        WHEN 2 THEN 'Передняя грань'
        WHEN 3 THEN 'Pin'
        ELSE ''
    END AS "Точка отсчёта",

    CASE m.laser_on WHEN 1 THEN 'Вкл' ELSE 'Выкл' END AS "Лазер",
    datetime(m.timestamp/1000, 'unixepoch', 'localtime') AS "Время замера",

    m.object_id AS "Объект",
    g.name AS "Группа"

FROM measurements m
LEFT JOIN notes n ON m.id = n.measurement_id AND n.is_primary = 1
LEFT JOIN groups g ON m.group_id = g.id
WHERE m.is_deleted = 0
ORDER BY m.sort_order ASC;
```

---

## Индексы

```sql
-- Быстрый список замеров
CREATE INDEX idx_measurements_sort ON measurements(sort_order);

-- Экспорт по дате
CREATE INDEX idx_measurements_timestamp ON measurements(timestamp DESC);

-- Фильтр по проекту
CREATE INDEX idx_measurements_project ON measurements(project_id);

-- Фильтр по группе
CREATE INDEX idx_measurements_group ON measurements(group_id);

-- Поиск заметок
CREATE INDEX idx_notes_measurement ON notes(measurement_id, version);
CREATE INDEX idx_notes_primary ON notes(measurement_id, is_primary);

-- Сырые логи
CREATE INDEX idx_raw_log_timestamp ON raw_log(timestamp DESC);

-- Объекты
CREATE INDEX idx_objects_project ON objects(project_id);
```

---

*Документ создан: 4 апреля 2026 г., 02:30 MSK*
