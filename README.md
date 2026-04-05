# GlmReader — Клиент для Bosch GLM 50C

Приложение для работы с лазерной рулеткой **Bosch GLM 50C** через Bluetooth LE.
Замена официальным приложениям Bosch (Measuring Master, MeasureOn), имеющим критические баги:
- Потеря данных при краше
- Некорректные временные метки в экспорте
- Безвозвратное удаление замеров вместе с группами

**Статус:** 🟡 Активная разработка (Android) | ❄️ Заморожено (Windows)

Протокол BLE полностью расшифрован — спецификация: [`docs/21_GLM_PROTOCOL_FULL_SPEC.md`](docs/21_GLM_PROTOCOL_FULL_SPEC.md)

---

## 📁 Структура проекта

| Директория | Описание | Статус |
|------------|----------|--------|
| `GlmReaderAndroid/` | Android приложение (Kotlin, Room, Nordic BLE) | 🟡 Активная разработка |
| `GlmReader/` | Windows приложение (C# .NET 8 WinForms) | ❄️ Заморожено (BLE проблема Windows) |
| `Android/` | Ресурсы реверс-инжиниринга (APK, декомпиляция, ТЗ) | 📚 Архив анализа |
| `docs/` | Документация: спецификация протокола, ADR, планы | ✅ Актуально |
| `archives/` | Архивные данные: HCI логи, багрепорты, инструменты | 📦 Архив |
| `scripts/analysis/` | Скрипты анализа HCI логов (выполнены) | 📜 История |
| `python/` | Шаблонный Python проект | 🔧 Заглушка |
| `csharp/` | Шаблонный .NET проект | 🔧 Заглушка |
| `shared/` | Общие ресурсы | 🔧 Заглушка |

---

## 🚀 Быстрый старт

### Android (GlmReaderAndroid)

```bash
cd GlmReaderAndroid

# Открыть в Android Studio
# Min SDK: 26, Target SDK: 34, Java 17
```

### Windows (GlmReader) — заморожено

```bash
cd GlmReader
# .NET 8, C# 12, WinForms
# Проблема: Windows BLE API не видит сервис GLM без спаривания
```

### Трекер задач (beads)

```bash
# Просмотр задач
bd list
bd ready
bd graph

# Создание задачи
bd create "Описание задачи" -p 1 -t feature
```

---

## 📡 Протокол Bosch GLM 50C

Полностью расшифрован через реверс-инжиниринг:
- 3 HCI лога + JADX анализ двух APK (Measuring Master 1.9.4, MeasureOn 2.0.1)
- Сопоставление с TXT экспортами — 12/12 совпадений
- CRC8 (poly=0xA6, Init=0xAA), 27 devMode-режимов
- Команды: Init, Measure, SyncHistory, SetReferencePoint, SetMeasurementType, SyncList, GetSettings

📖 Полная спецификация: [`docs/21_GLM_PROTOCOL_FULL_SPEC.md`](docs/21_GLM_PROTOCOL_FULL_SPEC.md)

---

## 📚 Документация

| Документ | Описание |
|----------|----------|
| [`docs/21_GLM_PROTOCOL_FULL_SPEC.md`](docs/21_GLM_PROTOCOL_FULL_SPEC.md) | Полная спецификация BLE протокола |
| [`docs/REORGANIZATION_PLAN.md`](docs/REORGANIZATION_PLAN.md) | План реорганизации структуры |
| [`docs/NEXT_SESSION_PLAN.md`](docs/NEXT_SESSION_PLAN.md) | План на следующую сессию |
| [`docs/conversations/INDEX.md`](docs/conversations/INDEX.md) | Индекс архива диалогов |
| [`docs/DECISIONS.md`](docs/DECISIONS.md) | Архитектурные решения (ADR) |
| [`CONTRIBUTING.md`](CONTRIBUTING.md) | Руководство контрибьютора |
| [`CHANGELOG.md`](CHANGELOG.md) | История изменений |

---

## 🔧 Требования

- **Android Studio** — для GlmReaderAndroid (Kotlin, minSdk 26)
- **.NET 8 SDK** — для GlmReader (C# 12, WinForms)
- **bd (beads)** — трекер задач, установлен в `~/.local/bin/`
- **Dolt v1.85.0** — сервер базы данных задач
