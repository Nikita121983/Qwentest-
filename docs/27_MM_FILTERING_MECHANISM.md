# Механизм фильтрации пакетов в Measuring Master / MeasureOn

**Дата:** 13 апреля 2026 г.
**Источник:** Исходный код MM/MO (декомпиляция)

---

## Ключевая находка: MM НЕ фильтрует по devMode

В `GLMDeviceController.handleEDCMessage()` **нет никакой фильтрации по devMode**. Все входящие пакеты (включая devMode 0, 60, 62) обрабатываются одинаково:

```java
// GLMDeviceController.java, строка ~262
if (this.initSyncRequest) {
    this.initSyncRequest = false;
} else {
    saveEDCMessage(eDCInputMessage);
}
```

### Что происходит с каждым пакетом:

| Пакет | devMode | Обновляет refEdge/type | Сохраняется в БД? |
|-------|---------|------------------------|-------------------|
| Init ACK | 0 | ✅ Да | ❌ Нет (initSyncRequest=true) |
| Реальный замер | 1-26 | ✅ Да | ✅ Да |
| Set Type ACK | 60 | ✅ Да | ✅ Да (но result=0) |
| Set Ref ACK | 62 | ✅ Да | ✅ Да (но result=0) |

---

## Фильтрация по result == 0 (НЕ по devMode!)

В `saveSyncMessage()` (строка 641) MM фильтрует **по значению результата**, а НЕ по devMode:

```java
// GLMDeviceController.java, строка 641
if (syncInputMessage.getResult() == 0.0f) {
    Log.d(TAG, "Ignore syncMessage with empty result: " + syncInputMessage);
    return;
}
```

Это означает:
- Пакеты с `result == 0` (Init ACK, Set Type ACK, Set Ref ACK) **НЕ сохраняются**
- Но **refEdge и measurementType извлекаются** и обновляются для КАЖДОГО пакета
- Реальные замеры (result != 0) сохраняются вместе со своими refEdge и devMode

---

## Реализация в GlmReaderAndroid

### GlmBleManager.kt — onPacketReceived()

```kotlin
// 1. Извлекаем refEdge/devMode из КАЖДОГО пакета (байт 3: [refEdge:2][devMode:6])
val devModeRef = fullPacket[3].toInt() and 0xFF
val refEdge = devModeRef and 0x03
val devMode = (devModeRef shr 2) and 0x3F

// 2. Обновляем UI состояние (как MM: setReferenceLevel, setLastMeasurementMode)
if (currentRefEdge != refEdge) {
    currentRefEdge = refEdge
    onRefEdgeChanged?.invoke(refEdge)
}
if (devMode != 0 && devMode != 60 && devMode != 62) {
    if (currentMeasurementType != devMode) {
        currentMeasurementType = devMode
        onMeasurementTypeChanged?.invoke(devMode)
    }
}

// 3. Парсим пакет
val parsed = BlePacketParser.parseWithCrc(fullPacket)
if (parsed != null) {
    // 4. Как в MM: фильтруем по result == 0, а НЕ по devMode
    // saveSyncMessage: "Ignore syncMessage with empty result"
    if (parsed.resultValue != 0.0) {
        dataReceivedListeners.forEach { it(fullPacket) }
        parsedMeasurementListeners.forEach { it(parsed) }
    }
}
```

### BlePacketParser.kt — НЕТ фильтрации по devMode

```kotlin
val devModeRef = rawBytes[3].toInt() and 0xFF
val refEdge = devModeRef and 0x03
val devMode = (devModeRef shr 2) and 0x3F

// В MM/MO нет фильтра по devMode — всё обрабатывается через initSyncRequest
// и фильтрацию по result == 0
```

---

## Важные выводы

1. **НЕ фильтровать по devMode** — MM обрабатывает ВСЕ devMode одинаково
2. **Фильтровать по result == 0** — это единственный критерий отбрасывания в MM
3. **refEdge/devMode сохраняются для КАЖДОГО замера** — они извлекаются из каждого пакета
4. **initSyncRequest** — первый пакет при подключении не сохраняется (ответ на Init/AutoSync)

---

## Ошибочные подходы (НЕ делать)

| Что | Почему не работает |
|-----|-------------------|
| `if (devMode == 62) return null` | refEdge/type НЕ обновляются для UI |
| `if (devMode == 0 || 60 || 62) return null` | Фильтр в неправильном месте, refEdge/type теряются |
| `initSyncRequest` для всех пакетов | MM использует это ТОЛЬКО для первого пакета |

## Правильный подход

Фильтр по `resultValue != 0.0` в GlmBleManager.onPacketReceived(), ПОСЛЕ извлечения refEdge/devMode.
