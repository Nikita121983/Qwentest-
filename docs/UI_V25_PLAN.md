# Полный план изменений — v25 + кастомные схемы

**Дата:** 12 апреля 2026 г.
**Цель:** UI соответствует mockup_ui_v25.html + кастомные цветовые схемы и иконки

---

## ЧАСТЬ 1: UI — соответствие мокапу v25

### 1.1 Toolbar — динамический цвет по refEdge
**Файлы:** `activity_measurement_list.xml`, `MeasurementListActivity.kt`, `colors.xml`

- Toolbar: убрать `android:background="@color/toolbar_background"` → задавать программно
- Полоска сворачивания (`btnTogglePanel`): `android:background` → наследует от toolbar
- При `onRefEdgeChanged`: менять цвет toolbar + полоски
  - rear (Задняя) = `#2B5878`
  - front (Передняя) = `#2e7d32`
  - tripod (Штатив) = `#c62828`
  - pin = `#FF9800`
- Анимация: `ObjectAnimator.ofArgb()` 0.4s

### 1.2 Фон списка — динамический цвет по типу
**Файлы:** `activity_measurement_list.xml`, `MeasurementListActivity.kt`

- Убрать `android:background="@color/background"` у CoordinatorLayout
- Добавить `android:id="@+id/listBackground"` вокруг RecyclerView
- При `onMeasurementTypeChanged`: менять фон списка (12 цветов, transition 0.4s)

### 1.3 Строка замера — layout как в v25
**Файлы:** `item_measurement.xml`, `MeasurementAdapter.kt`

- **Строка 1:** name-wrap (белый `rgba(255,255,255,0.85)`) ... type-icon + value (НЕ сжимается)
- **Строка 2:** datetime под name-wrap
- name: `ellipsize=end`, flex:1, НЕ накладывается на value
- value: `whiteSpace=nowrap`, flex-shrink=0, даже на 100+ метров

### 1.4 Панель управления — всегда видна
**Файлы:** `activity_measurement_list.xml`, `MeasurementListActivity.kt`

- `panelRemote`: `android:visibility="visible"` (НЕ gone)
- При подключении: кнопки активны
- Без подключения: кнопки disabled
- Анимация сворачивания: maxHeight transition 0.3s

### 1.5 Кубики — flip анимация как в v20
**Файлы:** `activity_measurement_list.xml`, `MeasurementListActivity.kt`

- refCubeContainer + typeCubeContainer: flip 0.4s cubic-bezier
- При смене типа → меняется фон списка
- При смене точки → меняется цвет toolbar + полоски
- 12 типов, 4 точки отсчёта (включая Pin)

### 1.6 Иконки типа в списке
**Файлы:** `MeasurementAdapter.kt`, `item_measurement.xml`

- Рядом с value — иконка типа
- 12 иконок

---

## ЧАСТЬ 2: GlmBleManager — извлечение refEdge/measurementType

### 2.1 Текущее состояние (ПЕРЕД исправлением)
**Файл:** `GlmBleManager.kt` — строки 72-79

```kotlin
// ОБЪЯВЛЕНО, но НИКОГДА НЕ ВЫЗЫВАЕТСЯ:
@Volatile var currentRefEdge = 0
@Volatile var currentMeasurementType = 0
var onRefEdgeChanged: ((Int) -> Unit)? = null
var onMeasurementTypeChanged: ((Int) -> Unit)? = null
```

В `onPacketReceived()` (строка 340) — пакет передаётся в listeners БЕЗ извлечения refEdge/devMode.

### 2.2 GlmBleManager.onPacketReceived — извлечение из КАЖДОГО пакета
**Файл:** `GlmBleManager.kt` — метод `onPacketReceived`, ПОСЛЕ CRC проверки

Вставить после `if (receivedCrc != calculatedCrc) return`:

```kotlin
// Извлекаем refEdge и devMode из КАЖДОГО пакета (байт 3: [refEdge:2][devMode:6])
val devModeRef = fullPacket[3].toInt() and 0xFF
val refEdge = devModeRef and 0x03
val devMode = (devModeRef shr 2) and 0x3F

// Обновляем состояние refEdge из КАЖДОГО пакета
if (currentRefEdge != refEdge) {
    currentRefEdge = refEdge
    onRefEdgeChanged?.invoke(refEdge)
}

// Обновляем тип измерения ТОЛЬКО из реальных измерений (НЕ из ответов на команды)
if (devMode != 0 && devMode != 60 && devMode != 62) {
    if (currentMeasurementType != devMode) {
        currentMeasurementType = devMode
        onMeasurementTypeChanged?.invoke(devMode)
    }
}
```

### 2.3 BlePacketParser — фильтр devMode=62
**Файл:** `BlePacketParser.kt` — строка ~124

```kotlin
if (devMode == 0 || devMode == 60 || devMode == 62) return null
```

### 2.4 MeasurementListActivity — подписки
**Файл:** `MeasurementListActivity.kt`

```kotlin
bleManager.onRefEdgeChanged = { refEdge ->
    runOnUiThread { updateToolbarColor(refEdge) }
}
bleManager.onMeasurementTypeChanged = { devMode ->
    runOnUiThread { updateListBackground(devMode) }
}

// Заменить observeDataReceived на observeParsedMeasurement
bleManager.observeParsedMeasurement { parsed ->
    viewModel.onBleMeasurement(parsed, currentProjectId)
}
```

---

## ЧАСТЬ 3: Кастомные цветовые схемы (P1)

### 3.1-3.5 Data model, встроенные, Settings UI, CustomColorSchemeActivity, Экспорт/Импорт JSON

---

## ЧАСТЬ 4: Кастомные иконки (P2)

### 4.1-4.4 Data model, IconSelectorDialog, Экспорт/Импорт ZIP, Settings UI

---

## ЧАСТЬ 5: Debug окно — обновление

### 5.1 Лог сохраняется между переключениями окон
- Лог хранить в `GlmReaderApplication` (singleton)
- При `onDestroy` — лог НЕ очищается

### 5.2 Кнопка "Clear Log" → "Save/Open Log"
- Сохранить лог в файл `GLM_Debug_log_YYYYMMDD_HHMMSS.txt`
- Вызвать `Intent.ACTION_VIEW` для открытия

### 5.3 Расшифровка протокола в логе
- `RX: C0 55 10 01 ... (Пр: 1033,1 мм)`
- `TX: C0 56 01 00 1E (Trigger)`
- `RX: C0 55 02 F1 ... (💓 Heartbeat)`

### 5.4 Импорт таблицы значений для неизвестного протокола
- CSV/JSON mapping: `hex_prefix,description`

### 5.5 Показ refEdge и measurementType
- Строка: `Ref: Задняя | Type: Прямой (1)`

### 5.6 Кнопки Set Ref / Set Type
- Set Ref (0-3), Set Type (0-26)

---

## HEX mapping для расшифровки

| Байт 3 (DevModeRef) | refEdge | devMode | Значение |
|---------------------|---------|---------|----------|
| `0x01` | 1 (Передняя) | 0 | Init ACK |
| `0x04` | 0 (Задняя) | 1 | Прямое измерение |
| `0x15` | 1 (Передняя) | 5 | Площадь |
| `0x18` | 0 (Задняя) | 6 | Объём |
| `0x20` | 0 (Задняя) | 8 | Угол |
| `0x28` | 0 (Задняя) | 10 | Косвенная высота |
| `0x2C` | 0 (Задняя) | 11 | Косвенная длина |
| `0x30` | 0 (Задняя) | 12 | Двойная косвенная |
| `0x70-0x73` | 0-3 | 60 | Set Type ACK |
| `0x78-0x7B` | 0-3 | 62 | Set Ref ACK |
| `0xF0-0xF3` | 0-3 | 60 | Init ACK / Settings |
| `0xF1` | 1 | 60 | Heartbeat |

---

## Приоритет выполнения

| Шаг | Что | Приоритет |
|-----|-----|-----------|
| **1** | 2.2 GlmBleManager извлечение refEdge/type + 2.3 фильтр devMode=62 | 🔴 P0 |
| **2** | 1.1 Toolbar цвет по refEdge | 🔴 P0 |
| **3** | 1.2 Фон списка по типу | 🔴 P0 |
| **4** | 1.3 Строка замера layout | 🔴 P0 |
| **5** | 1.4 Панель всегда видна | 🔴 P0 |
| **6** | 1.5 Кубики flip анимация | 🔴 P0 |
| **7** | 1.6 Иконки типа в списке | 🔴 P0 |
| **8** | 5.1-5.6 Debug окно обновление | 🟡 P1 |
| **9** | 3.1-3.5 Цветовые схемы | 🟡 P1 |
| **10** | 4.1-4.4 Кастомные иконки | 🟢 P2 |

---

## Файлы для создания
`models/ColorScheme.kt`, `data/dao/ColorSchemeDao.kt`, `utils/ColorSchemeExporter.kt`,
`CustomColorSchemeActivity.kt`, `activity_custom_color_scheme.xml`,
`models/CustomIcon.kt`, `data/dao/CustomIconDao.kt`, `utils/IconExporter.kt`,
`IconSelectorDialog.kt`, `dialog_icon_selector.xml`,
`res/drawable/ic_type_*.xml` (12 шт), `res/drawable/ic_ref_*.xml` (4 шт)

## Файлы для изменения
`GlmBleManager.kt` (извлечение refEdge/type, callbacks),
`BlePacketParser.kt` (фильтр devMode=62),
`MeasurementListActivity.kt` (цвета, подписки, flip),
`activity_measurement_list.xml` (layout панели, фон, toolbar),
`item_measurement.xml` (name-wrap + value + icon),
`MeasurementAdapter.kt` (иконки типов),
`BleDebugActivity.kt` (показ refEdge/type, HEX лог, кнопки),
`SettingsActivity.kt` + `activity_settings.xml` (схемы, иконки),
`colors.xml` (все цвета),
`AppDatabase.kt` (миграции)

---

*План обновлён: 12 апреля 2026 г.*
