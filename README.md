# Qwentest

Проект для разработки на Python и C# с полной настройкой Qwen Code.

[![CI/CD](https://github.com/YOUR_USERNAME/Qwentest/actions/workflows/ci.yml/badge.svg)](https://github.com/YOUR_USERNAME/Qwentest/actions/workflows/ci.yml)
[![Codecov](https://codecov.io/gh/YOUR_USERNAME/Qwentest/branch/main/graph/badge.svg)](https://codecov.io/gh/YOUR_USERNAME/Qwentest)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## 🚀 Быстрый старт

### Клонирование

```bash
git clone https://github.com/YOUR_USERNAME/Qwentest.git
cd Qwentest
```

### Установка

```bash
# Python зависимости
pip install -r requirements-dev.txt

# Pre-commit хуки
pre-commit install
```

```
Qwentest/
├── .qwen/               # Конфигурация Qwen Code
├── .qwenignore          # Игнорируемые файлы для Qwen
├── python/              # Python проекты
│   ├── pyproject.toml   # Конфигурация проекта
│   ├── requirements-dev.txt
│   ├── main.py
│   └── __init__.py
├── csharp/              # C#/.NET проекты
│   ├── Qwentest.CSharp.sln
│   ├── Qwentest.CSharp.csproj
│   ├── Program.cs
│   ├── global.json
│   └── Directory.Build.props
├── shared/              # Общие ресурсы
└── docs/                # Документация
```

## Быстрый старт

### Python

```bash
cd python

# Установка зависимостей (uv)
uv pip install -r requirements-dev.txt

# Или через pip
pip install -r requirements-dev.txt

# Запуск линтера
ruff check .

# Форматирование
black .

# Тесты
pytest
```

### C#

```bash
cd csharp

# Восстановление пакетов
dotnet restore

# Сборка
dotnet build

# Запуск
dotnet run

# Тесты
dotnet test

# Форматирование
dotnet format
```

## Qwen Code Настройка

### Доступные команды

- `/review <файл>` — ревью кода
- `/qc-helper <вопрос>` — справка по Qwen Code

### Агенты

Используйте агентов для сложных задач:
- **Explore** — поиск по кодовой базе
- **General-purpose** — многоступенчатые задачи

### Память

- Глобальная: `~/.qwen/QWEN.md`
- Проектная: `.qwen/QWEN.md`

### Архив диалогов

Все диалоги с Qwen Code сохраняются в `docs/conversations/`.

**Формат:** Каждый диалог = два файла:
- `###_YYYY-MM-DD_topic_FULL.md` ← вставляешь полную копию из интерфейса
- `###_YYYY-MM-DD_topic_SUMMARY.md` ← я создаю резюме с решениями

**Место хранения:** Локально в проекте (можно изменить в `ARCHIVE_LOCATION.md`)

**Индексы:**
- Локальный: [`docs/conversations/INDEX.md`](docs/conversations/INDEX.md)
- Глобальный: `~/.qwen/conversations/INDEX.md`

---

## Требования

- **Python**: 3.10+
- **.NET**: 8.0+
- **Qwen Code**: последняя версия

---

## 🐙 GitHub интеграция

### Настройка CI/CD

1. **Создайте репозиторий на GitHub:**
   ```bash
   git init
   git remote add origin https://github.com/YOUR_USERNAME/Qwentest.git
   git add .
   git commit -m "Initial commit"
   git push -u origin main
   ```

2. **GitHub Actions настроен автоматически:**
   - `ci.yml` — тесты и сборка при push/PR
   - `validate-pr.yml` — валидация Pull Request

3. **Pre-commit хуки:**
   ```bash
   pip install pre-commit
   pre-commit install
   ```

4. **Codecov (покрытие тестов):**
   - Зарегистрируйтесь на https://codecov.io/
   - Добавьте репозиторий
   - Получите токен и добавьте в Secrets: `CODECOV_TOKEN`

### Шаблоны

- **Issue templates:** Bug Report, Feature Request
- **Pull Request template:** автоматический чеклист
- **CODEOWNERS:** автоматические ревьюверы

### Ветки

| Ветка | Назначение |
|-------|------------|
| `main` | Стабильная версия |
| `develop` | Разработка |
| `feature/*` | Новые функции |
| `fix/*` | Исправления |

---

## 📚 Документация

| Документ | Описание |
|----------|----------|
| [`docs/PROJECT_CHECKLIST.md`](docs/PROJECT_CHECKLIST.md) | Чеклист планирования |
| [`docs/CODE_RULES.md`](docs/CODE_RULES.md) | Правила кода |
| [`docs/TZ_TEMPLATE.md`](docs/TZ_TEMPLATE.md) | Шаблон ТЗ |
| [`docs/ARCHITECTURE_TEMPLATE.md`](docs/ARCHITECTURE_TEMPLATE.md) | Архитектура проекта |
| [`docs/CODE_REVIEW_CHECKLIST.md`](docs/CODE_REVIEW_CHECKLIST.md) | Чеклист ревью |
| [`CONTRIBUTING.md`](CONTRIBUTING.md) | Руководство контрибьютора |
| [`CHANGELOG.md`](CHANGELOG.md) | История изменений |
