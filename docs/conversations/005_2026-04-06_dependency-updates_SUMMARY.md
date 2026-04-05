# 005_2026-04-06_dependency-updates — Резюме

**Дата:** 6 апреля 2026 г.
**Статус:** ✅ Завершено (BUILD SUCCESSFUL)

---

## 📋 Краткое содержание

Обновление всех зависимостей GlmReaderAndroid до последних совместимых версий.
Исправление ошибок компиляции после обновления.
Успешная сборка APK (16.5 МБ).

---

## 🔑 Ключевые решения

| Компонент | Было | Стало | Обоснование |
|-----------|------|-------|-------------|
| Kotlin | 1.9.22 | 2.2.0 | Последняя стабильная (июнь 2025) |
| KSP | 1.9.22-1.0.17 | 2.2.0-2.0.2 | Совместима с Kotlin 2.2.0 |
| Android Gradle Plugin | 8.13.2 | 8.9.0 | Стабильная версия |
| Room | 2.6.1 | 2.7.1 | Исправлена ошибка KSP |
| Coroutines | 1.8.1 | 1.10.2 | Последняя стабильная |
| compileSdk/targetSdk | 34 | 35 | Требуется для новых библиотек |
| core-ktx | 1.13.1 | 1.16.0 | Новая версия |
| appcompat | 1.7.0 | 1.7.1 | Патч |
| lifecycle | 2.7.0 | 2.9.2 | Новая версия |
| activity-ktx | 1.8.2 | 1.10.1 | Новая версия |

---

## 🐛 Исправленные ошибки

1. **KSP "Unused parameter: defaultValue"** — исчезла после обновления Room до 2.7.1
2. **@JvmOverloads на interface** — удалена аннотацию из MeasurementDao.updateSortOrder()
3. **Unresolved reference MeasurementType** — добавлен импорт в InclinoLogic.kt
4. **private measurements в Adapter** — добавлен публичный метод getMeasurements()
5. **Unresolved reference action_ble** — добавлен item в menu_main.xml
6. **Drawable иконки с <circle>** — заменены на <path> для minSdk 26
7. **Insert возврат Unit** — MeasurementDao.insert() теперь возвращает Long

---

## 📝 Изменения

### Файлы обновлены (коммит 281daf2):
- `GlmReaderAndroid/build.gradle.kts` — Kotlin 2.2.0, AGP 8.9.0
- `GlmReaderAndroid/app/build.gradle.kts` — все зависимости
- `GlmReaderAndroid/app/src/main/java/.../dao/MeasurementDao.kt` — insert(): Long
- `GlmReaderAndroid/app/src/main/java/.../dao/SettingsDao.kt` — упрощен
- `GlmReaderAndroid/app/src/main/java/.../repository/MeasurementRepository.kt` — insert(): Unit
- `GlmReaderAndroid/app/src/main/java/.../protocol/InclinoLogic.kt` — импорт MeasurementType
- `GlmReaderAndroid/app/src/main/java/.../ui/MeasurementAdapter.kt` — getMeasurements()
- `GlmReaderAndroid/app/src/main/java/.../ui/MeasurementListActivity.kt` — getMeasurements()
- `GlmReaderAndroid/app/src/main/java/.../ui/SettingsActivity.kt` — тема DayNight
- `GlmReaderAndroid/app/src/main/java/.../ui/viewmodel/SettingsViewModel.kt` — StateFlow
- `GlmReaderAndroid/app/src/main/res/values/themes.xml` — DayNight + popup
- `GlmReaderAndroid/app/src/main/res/menu/menu_main.xml` — action_ble
- `GlmReaderAndroid/app/src/main/res/drawable/*.xml` — исправлены иконки

---

## 📊 Результат

- **BUILD:** SUCCESSFUL в 1m 20s
- **APK:** app-debug.apk (16,541,995 байт / 16.5 МБ)
- **Задача:** Qwentest-ut2 закрыта
- **Коммит:** 281daf2 запушен в remote

---

## 💡 Уроки

1. **KSP баги** часто решаются обновлением до совместимых версий Kotlin+KSP+Room
2. **@JvmOverloads** нельзя использовать на методах интерфейса
3. **Drawable <circle>** не поддерживается до API 24 — использовать <path>
4. **Компилятор Kotlin** может кэшировать старые версии файлов — нужен полный сброс кэша
5. **Gradle daemon** нужно останавливать при смене версий Kotlin/KSP

---

*Диалог завершен. Задача Qwentest-ut2 закрыта, изменения запушены.*
