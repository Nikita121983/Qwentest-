# Руководство для контрибьюторов

Спасибо за интерес к проекту! Это руководство поможет вам начать.

---

## 🚀 Быстрый старт

### 1. Форк и клонирование

```bash
# Форкните репозиторий на GitHub
# Затем клонируйте:
git clone https://github.com/YOUR_USERNAME/Qwentest.git
cd Qwentest
```

### 2. Установка зависимостей

```bash
# Python
python -m pip install -r requirements-dev.txt

# Pre-commit хуки
pre-commit install
```

### 3. Запуск тестов

```bash
# Все тесты
pytest tests/ -v

# С покрытием
pytest --cov=src --cov-report=html
```

---

## 📋 Как внести изменения

### 1. Создайте ветку

```bash
git checkout -b feature/my-feature
# или
git checkout -b fix/my-bugfix
```

### 2. Внесите изменения

Следуйте правилам из `docs/CODE_RULES.md`.

### 3. Запустите проверки

```bash
# Линтеры
ruff check src/
black --check src/
mypy src/

# Тесты
pytest tests/ -v --cov=src --cov-fail-under=80

# Pre-commit (автоматически перед коммитом)
pre-commit run --all-files
```

### 4. Закоммитьте

```bash
git add .
git commit -m "feat: добавить новую функцию"
```

**Формат сообщений:**
- `feat:` — новая функция
- `fix:` — исправление ошибки
- `docs:` — документация
- `style:` — форматирование
- `refactor:` — рефакторинг
- `test:` — тесты
- `chore:` — прочее

### 5. Отправьте на GitHub

```bash
git push origin feature/my-feature
```

### 6. Создайте Pull Request

1. Откройте ваш форк на GitHub
2. Нажмите "New Pull Request"
3. Заполните шаблон PR
4. Отправьте на ревью

---

## 📖 Правила кода

### Python

- Type hints обязательно
- Docstring в формате Google style
- Файлы ≤400 строк
- Функции ≤50 строк
- Параметры ≤5

### Тесты

- Покрытие ≥80%
- Тесты на граничные случаи
- Названия: `test_функция_условие()`

---

## 🐛 Баг-репорты

Создайте Issue с шаблоном "Bug Report".

**Укажите:**
- Версию программы
- ОС
- Шаги воспроизведения
- Логи/скриншоты

---

## 💡 Предложения функций

Создайте Issue с шаблоном "Feature Request".

**Укажите:**
- Какую проблему решает
- Как должно работать
- Приоритет

---

## 📞 Контакты

- Вопросы: создайте Issue с меткой "question"
- Обсуждения: GitHub Discussions

---

*Руководство создано: 2 апреля 2026 г.*
