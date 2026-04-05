# Архив диалогов Qwentest

Индекс всех диалогов проекта Qwentest.

---

## 📍 Место хранения архива

**Текущее:** Локально в проекте
**Путь:** `C:\Users\Nik\SyncthingServiceAcct\Qwentest\docs\conversations\`

> Чтобы изменить место хранения, см. `ARCHIVE_LOCATION.md`

---

## Формат файлов

Каждый диалог = **два файла**:

| Суффикс | Кто заполняет | Что содержит |
|---------|---------------|--------------|
| `_FULL.md` | Пользователь | Полная копия из интерфейса |
| `_SUMMARY.md` | Qwen Code | Резюме с решениями |

---

## Диалоги

| # | Дата | Тема | Файлы |
|---|------|------|-------|
| 001 | 2026-04-02 | Настройка Qwen Code | [FULL](001_2026-04-02_initial-setup_FULL.md), [SUMMARY](001_2026-04-02_initial-setup_SUMMARY.md) |
| 002 | 2026-04-03 | Reverse engineering GLM протокола | [FULL](002_2026-04-03_reverse-engineering-glm-protocol_FULL.md), [SUMMARY](002_2026-04-03_reverse-engineering-glm-protocol_SUMMARY.md) |
| 003 | 2026-04-03 | GlmReader разработка | [FULL](003_2026-04-03_glm-reader-development_FULL.md), [SUMMARY](003_2026-04-03_glm-reader-development_SUMMARY.md) |

---

## Формат именования файлов

```
###_YYYY-MM-DD_description_FULL.md
###_YYYY-MM-DD_description_SUMMARY.md
```

- `###` — трёхзначный номер диалога (001, 002, 003...)
- `YYYY-MM-DD` — дата диалога
- `description` — краткое описание темами через дефис

---

## Как вести архив

1. **Каждый диалог** сохраняется в два файла в этой директории
2. **Нумерация** последовательная, начиная с 001
3. **В конце диалога** обновлять этот индекс
4. **Важные решения** из диалогов дублировать в `../DECISIONS.md`

---

## Глобальный индекс

Этот проект также зарегистрирован в глобальном индексе:
- **Путь:** `~/.qwen/conversations/INDEX.md`
- **Ссылка:** [Глобальный индекс](file://C:\Users\Nik\.qwen\conversations\INDEX.md)

---

*Обновлено: 2 апреля 2026 г.*
