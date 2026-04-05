# План реорганизации папки Qwentest

**Дата создания:** 4 апреля 2026 г.
**Цель:** Навести порядок в структуре проекта, отделить активные файлы от архивных.

---

## 1. Текущие проблемы структуры

### 1.1. Корень папки (38 файлов)
Слишком много временных и аналитических файлов:
- `analyze_*.py` (14 файлов) — скрипты анализа HCI логов (выполнены, результат в документации)
- `find_*.py` (7 файлов) — скрипты поиска паттернов (выполнены)
- `compare_*.py` (4 файла) — скрипты сравнения логов
- `parse_*.py` (2 файла) — парсеры протокола
- `test_crc8.py`, `check.txt` — временные тесты
- `Текстовый документ.txt` — лог отладки (перенести в docs или удалить)
- `bugreport*.zip` (8 файлов) + `bugreport*/` (6 папок) — сырые дампы
- `btsnoop*.log` (6 файлов) + `btsnooz*.log` (2 файла) — HCI логи
- `jadx.zip`, `platform-tools.zip` — архивы инструментов
- `cosine.xls` — старый экспорт

### 1.2. Папка `docs`
В целом порядок хороший. Нужно добавить:
- `NEXT_SESSION_PLAN.md` (создать)
- Удалить `FOLDER_STRUCTURE_TEMP.txt` после анализа

### 1.3. Папки проектов
- `GlmReader/` — Windows приложение (неактивно, BLE проблема)
- `GlmReaderAndroid/` — **Активный проект** (в разработке)
- `Android/` — Ресурсы для анализа (APK, декомпиляция, спецификации ТЗ)
- `csharp/`, `python/`, `shared/` — Пустые или устаревшие шаблоны

---

## 2. Предлагаемая новая структура

```
Qwentest/
│
├── docs/                           # Документация (текущая + новая)
│   ├── conversations/              # Архив диалогов
│   ├── REORGANIZATION_PLAN.md      # Этот файл
│   ├── NEXT_SESSION_PLAN.md        # План на следующую сессию
│   └── ... (остальные .md)
│
├── GlmReaderAndroid/               # АКТИВНЫЙ ПРОЕКТ (Android)
│   ├── app/
│   ├── docs/
│   └── ...
│
├── GlmReader/                      # Windows проект (заморожен)
│
├── Android/                        # Ресурсы для анализа
│   ├── .gitignore-files/           # APK, декомпиляция, словари
│   ├── docs/                       # Спеки ТЗ, анализ MM/MO
│   └── MeasuringAssistant/         # Старые наброски
│
├── archives/                       # НОВАЯ: Архивные данные
│   ├── bugreports/                 # Переместить все bugreport*.zip и bugreport*/
│   ├── hci_logs/                   # Переместить все btsnoop*.log, btsnooz*.log
│   └── tools/                      # Переместить jadx.zip, platform-tools.zip, platform-tools/
│
├── scripts/                        # Скрипты анализа (уже есть)
│   ├── validate_workflows.py
│   └── analysis/                   # НОВАЯ: Переместить analyze_*, find_*, compare_*, parse_*
│       ├── analyze_*.py (14 файлов)
│       ├── find_*.py (7 файлов)
│       ├── compare_*.py (4 файла)
│       └── parse_*.py (2 файла)
│
├── .github/                        # GitHub конфиги (оставить)
├── .qwen/                          # Настройки Qwen (оставить)
├── README.md                       # Главный readme (обновить)
└── .gitignore                      # Обновить правила
```

---

## 3. План перемещений (по шагам)

### Шаг 1: Создать новые папки
```cmd
mkdir "C:\Users\Nik\SyncthingServiceAcct\Qwentest\archives"
mkdir "C:\Users\Nik\SyncthingServiceAcct\Qwentest\archives\bugreports"
mkdir "C:\Users\Nik\SyncthingServiceAcct\Qwentest\archives\hci_logs"
mkdir "C:\Users\Nik\SyncthingServiceAcct\Qwentest\archives\tools"
mkdir "C:\Users\Nik\SyncthingServiceAcct\Qwentest\scripts\analysis"
```

### Шаг 2: Переместить HCI логи и багрепорты
```cmd
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop*.log" "archives\hci_logs\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnooz*.log" "archives\hci_logs\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport*.zip" "archives\bugreports\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport\" "archives\bugreports\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport2\" "archives\bugreports\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport_app_control\" "archives\bugreports\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport_app_control2\" "archives\bugreports\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\bugreport_cosine\" "archives\bugreports\"
```

### Шаг 3: Переместить инструменты
```cmd
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\jadx.zip" "archives\tools\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\platform-tools.zip" "archives\tools\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\platform-tools\" "archives\tools\"
```

### Шаг 4: Переместить скрипты анализа
```cmd
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\analyze_*.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\find_*.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\compare_*.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\parse_*.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\deep_*.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\correlate_b4.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\final_checksum_analysis.py" "scripts\analysis\"
move "C:\Users\Nik\SyncthingServiceAcct\Qwentest\test_crc8.py" "scripts\analysis\"
```

### Шаг 5: Удалить/Архивировать временные файлы
- `check.txt` — удалить (пустой или тестовый)
- `Текстовый документ.txt` — проверить содержимое, если это лог — переместить в `docs/` или удалить
- `cosine.xls` — переместить в `Android/.gitignore-files/` (если нужен) или удалить
- `FOLDER_STRUCTURE_TEMP.txt` — удалить после подтверждения
- `jadx/` (папка) — если распакована и не используется, удалить

### Шаг 6: Обновить .gitignore
Добавить правила, чтобы архивы и кэши не попадали в Git:
```gitignore
# Архивы багрепортов и логов
archives/

# Временные файлы
check.txt
Текстовый документ.txt
*.tmp
*.log

# Кэши
.pytest_cache/
.ruff_cache/
__pycache__/

# Распакованные инструменты
jadx/
platform-tools/
```

### Шаг 7: Обновить README.md
Добавить описание структуры:
```markdown
## Структура проекта

- `GlmReaderAndroid/` — **Активная разработка** (Android приложение)
- `GlmReader/` — Windows версия (заморожена до решения проблемы BLE на Windows)
- `Android/` — Ресурсы для реверс-инжиниринга (APK, декомпиляция, ТЗ)
- `docs/` — Документация, спецификации протокола, архитектурные решения
- `archives/` — Архивные данные (HCI логи, багрепорты, инструменты)
- `scripts/analysis/` — Скрипты анализа HCI логов (выполнены, сохранены для истории)
```

---

## 4. Ожидаемый результат

После реорганизации в корне останется:
- **Папки:** `docs/`, `GlmReaderAndroid/`, `GlmReader/`, `Android/`, `archives/`, `scripts/`, `.github/`, `.qwen/`
- **Файлы:** `README.md`, `.gitignore`, конфигурационные файлы (`.lingma.json`, `.pre-commit-config.yaml` и т.д.)

Корень станет чистым и понятным. Все активные проекты вынесены на верхний уровень, архивы спрятаны в `archives/`.
