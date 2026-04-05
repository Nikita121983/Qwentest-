# Анализ причин пропажи данных в Bosch MeasureOn (и Measuring Master)

**Дата анализа:** 3 апреля 2026 г., 23:30 MSK
**Источник:** JADX декомпиляция APK `com.bosch.ptmt.measron` (v2.0.1)
**Ключевые файлы:**
- `MeasurementRepositoryImpl.kt`
- `MeasurementGroupRepositoryImpl.kt`
- `CloudSyncScheduler.kt`
- `ManualSyncViewModel.kt`
- `GenerateXLS.java`

---

## 🔴 Причина #1: Хранение замеров в отдельных JSON файлах

В отличие от проектов (которые хранятся в Room), **замеры хранятся как отдельные JSON файлы** в файловой системе.

```kotlin
// MeasurementRepositoryImpl.kt
this.folder = FileUtil.getProjectFolderWithContext(context, ConstantsUtils.FOLDER_MEASUREMENT_LIST)

override fun saveMeasurement(measurement: MTMeasurement, isFromBLE: Boolean): Result<Unit, Exception> {
    val json = gson.toJson(measurement)
    val file = getFile(measurement.uuid) // {uuid}.json
    file.writeText(json) // БЕЗ транзакций, без backup
}

override fun getAllMeasurements(): Result<List<MTMeasurement>, Exception> {
    return Ok(folder.listFiles()?.mapNotNull { file ->
        gson.fromJson<MTMeasurement>(file.readText(), MTMeasurement::class.java)
    } ?: emptyList())
}
```

**Риски:**
- ❌ Нет транзакций: если приложение упадёт во время `writeText` → файл повреждён/пуст → замер потерян.
- ❌ Нет индекса: `getAllMeasurements` сканирует всю папку и парсит каждый файл. При 1000+ замерах → лаги/краш.
- ❌ Ручное удаление: пользователь или файловый менеджер может случайно удалить `.json` файл.

---

## 🔴 Причина #2: Удаление группы = Безвозвратное удаление замеров

В MeasureOn замеры автоматически группируются в **Сессии** (`MeasurementGroupModel`). При удалении группы замеры **удаляются физически**, минуя Корзину.

```kotlin
// MeasurementGroupRepositoryImpl.kt
override fun deleteMeasurementGroup(groupUUID: String) {
    val group = getMeasurementGroup(groupUUID) ?: return
    val measurementRefs = group.references[MMModelType.MEASUREMENT] ?: emptyList()

    if (getFile(groupUUID).delete()) {
        for (uuid in measurementRefs) {
            this.measurementRepository.deleteMeasurement(uuid) // ← УДАЛЯЕТ JSON ФАЙЛ
        }
    }
}

// MeasurementRepositoryImpl.kt
override fun deleteMeasurement(uuid: String): Result<Unit, Exception> {
    val file = getFile(uuid)
    if (file.exists()) file.delete() // ← БЕЗВОЗВРАТНОЕ УДАЛЕНИЕ
    return Ok(Unit)
}
```

**Сценарий потери:**
1. Пользователь сделал 50 замеров, они попали в "Сессию 1".
2. Пользователь удалил "Сессию 1" (думая, что удаляет только папку/группу).
3. Цикл проходит по всем UUID и вызывает `file.delete()`.
4. **Все 50 замеров удалены навсегда.** Корзина не затрагивается.

---

## 🔴 Причина #3: Cloud Sync конфликты

В MeasureOn реализована облачная синхронизация (`CloudSyncScheduler`). При конфликте версий (Local vs Cloud) возможна перезапись данных.

```kotlin
// ManualSyncViewModel.kt
fun onConflictOptionChanged(resolution: MergeConflictResolution) {
    // Пользователь выбирает: Local, Cloud, или Merge
}

fun continueCloudSync() {
    // Запускает синхронизацию, которая может перезаписать локальные замеры
    // если Cloud считается "актуальной версией"
}
```

**Риски:**
- ⚠️ Если `CloudSyncScheduler` решает, что "Облако новее", локальные замеры **перезаписываются** без явного подтверждения.
- ⚠️ Конфликты разрешаются на уровне проектов, но замеры привязаны к проектам через `references`. Ошибка в связях → замеры "висячие" или удалённые.
- ⚠️ При плохом интернете или обрыве сессии частичная синхронизация → несогласованность БД и файловой системы.

---

## 🔴 Причина #4: Фильтрация при экспорте

Экспорт XLS в MeasureOn (`GenerateXLS`) берёт данные из `mtMeasurementList`, который зависит от **текущей выбранной группы или проекта**.

```java
// GenerateXLS.java
private boolean renderXLSMeasurementList() {
    List<MTMeasurement> list = this.mtMeasurementList; // ← Зависит от контекста вызова
    if (list != null && !list.isEmpty()) {
        list.sort(MTMeasurement.getComparator(2));
        for (int i = 0; i < list.size(); i++) {
            // ... заполнение XLSDataHolder ...
        }
    }
    triggerXlsService(getTheHeaderDetails());
    return true;
}
```

**Риски:**
- ❌ Если пользователь находится в "Сессии 2", а замеры были в "Сессии 1" → они **не попадут в экспорт**.
- ❌ Нет опции "Экспортировать всё" или "Экспортировать по диапазону дат".
- ❌ Время в экспорте берётся из `createdDate` замера, но если замер был импортирован/синхронизирован → дата может измениться.

---

## 📊 Сравнительная таблица: MM vs Mo vs GlmReader

| Параметр | Measuring Master | MeasureOn | GlmReader (план) |
|----------|------------------|-----------|------------------|
| **Хранилище** | Один JSON файл | Отдельные JSON файлы | **SQLite + транзакции** |
| **Удаление** | Удаляет из списка | `file.delete()` (безвозвратно) | **Soft Delete** (пометка + Корзина) |
| **Корзина** | Нет | Есть (только для Проектов) | **Да (для всего)** |
| **Синхронизация** | Нет | Cloud Sync (риск перезаписи) | **Локальный Raw-лог** |
| **Группировка** | Нет | Sessions/Days (риск при удалении) | **Ручная/Автоматическая** |
| **Экспорт** | Весь список | Зависит от контекста | **Независимый, Raw-лог** |
| **Надёжность** | 🟡 Низкая (один файл) | 🟡 Низкая (удаление групп) | 🟢 **Высокая (БД)** |

---

## ✅ Архитектурные решения для GlmReader

Чтобы полностью исключить эти риски, в GlmReader будет реализовано:

1. **SQLite (Room/Android или Dapper/WinForms)**
   - Все замеры, группы и проекты в одной БД.
   - Транзакции (`BEGIN TRANSACTION` / `COMMIT`).
   - Foreign Keys с `ON DELETE SET NULL` или `CASCADE`.

2. **Soft Delete (Корзина)**
   - Замеры не удаляются физически. Ставится флаг `is_deleted = 1`.
   - Очистка корзины только по явной команде пользователя.

3. **Raw Measurement Log**
   - Отдельная таблица `raw_log` с сырыми данными из BLE: `[timestamp, raw_bytes, crc]`
   - Никогда не модифицируется, только `INSERT`.
   - Позволяет восстановить замеры даже при сбое парсера.

4. **Независимый экспорт**
   - Экспорт работает напрямую с БД, не зависит от UI-контекста.
   - Фильтры по дате, типу, проекту, статусу.
   - Всегда экспортируется **актуальное состояние БД**.

5. **Автосохранение после каждого замера**
   - `INSERT INTO measurements (...) VALUES (...)` сразу после получения пакета.
   - Нет "буфера", который можно потерять при краше.

---

*Документ создан на основе анализа кода MeasureOn 2.0.1 (decompiled) и Measuring Master 1.9.4.*
