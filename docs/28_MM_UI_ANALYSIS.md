# Полный анализ UI Measuring Master — экран BLE измерений

> На основе декомпилированного кода Measuring Master (GLMDeviceController, RemoteMeasurementListFragment, MeasurementExpandableListAdapter, RetrieveCustomerData) + **XML layout файлы**

---

## 1. Структура экрана BLE измерений

### 1.1 XML Layout (fragment_remote_measurement_list.xml)

```
RelativeLayout (root)
├── layout_remote_measure_text
│   ├── remote_measure — TextView "REMOTE MEASURE"
│   └── layout_switch_laser
│       ├── rl_measure_icon
│       │   ├── icon_reference (75x75dp) + icon_reference_dropdown (50x40dp)
│       │   └── icon_measure (65x65dp) + icon_measure_dropdown (50x40dp)
│       └── btn_switch_laser — "Включить лазер"
├── layout_measurement
│   ├── TextView "MEASUREMENT"
│   └── icon_download
├── layout_empty_measurement (пустой список)
├── measurementLayout
│   └── measurement_group_list — ExpandableListView
└── hint_measuring_text (подсказка внизу)
```

### 1.2 Основной экран (RemoteMeasurementListFragment)

Экран — это `DialogFragment` в полноэкранном режиме (`Theme.Black.NoTitleBar.Fullscreen`), содержащий:

**Верхняя панель (popup-кнопки):**
```
[Download]  [Measure Icon 65x65] [dropdown 50x40]  [Ref Icon 75x75] [dropdown 50x40]
```

- **icon_reference** (75x75dp) — иконка точки отсчёта (`ic_measuremode_below` по умолчанию)
- **icon_reference_dropdown** (50x40dp, `@drawable/dd-blue-active`) — dropdown выбора точки
- **icon_measure** (65x65dp) — иконка типа измерения (`ic_distance1` по умолчанию)
- **icon_measure_dropdown** (50x40dp, `@drawable/dd-blue-active`) — dropdown выбора типа

**Кнопка лазера:**
```
[Laser ON / Measure]  — btnLaserOn
```
- Когда лазер выключен: текст "Switch on laser" + иконка `ic_switchonlaser_white_tablet`
- Когда лазер включён: текст "Measure" (без иконки)
- Для режима Angle (mode=4): всегда "Measure" без иконки

**Список измерений:**
```
[ExpandableListView] — measurementExpandableView
  Каждая группа = одно измерение (DistanceMeasurement)
  Раскрытие группы = дочерний вид с компонентами (a, b, c)

[TextView] — noMeasurementText (показывается когда список пуст)
```

**Подсказка:**
- `hintMeasuremntText` — появляется при подключении устройства на 5 секунд, затем исчезает

### 1.2 Popup выбора режима измерения (bg_measurements_popup_window)

Popup открывается по нажатию на `icon_measure_dropdown`. Содержит **LinearLayout** с ImageView-кнопками:

| ImageView | Режим | Tag (mode) | Иконка |
|-----------|-------|------------|--------|
| `img_lenght` | Прямое измерение (Line) | 1 | `ic_distance1` |
| `img_area` | Площадь (Area) | 2 | `ic_area` |
| `img_volume` | Объём (Volume) | 3 | `ic_volume` |
| `img_angle` | Угол (Angle) | 4 | `ic_angle` |
| `img_min_max` | Min/Max | 6 | `ic_minmax` |
| `img_indir_height` | Косвенное — высота | 7 | `ic_distance3` |
| `img_indir_lenght` | Косвенное — длина | 8 | `ic_distance2` |
| `img_double_indriect` | Двойное косвенное | 9 | `ic_distance4` |
| `img_wall_area` | Площадь стен (Wall Area) | 10 | `ic_wallarea` |
| `img_trapezoid` | Трапеция | 14 | `ic_trapezoid` |

**Ограничения по устройствам:**
- Для **GLM50** и **GLM100**: `img_trapezoid` — `VISIBLE` (visibility=4 / INVISIBLE)
- Для **GLM50**: `img_refmode_pin` скрыт (`visibility=8` / GONE)

**Отключение кнопок в зависимости от popMode:**
- **popMode = Line (1)**: отключены wall_area, area, volume, angle (alpha=0.4)
- **popMode = Angle (4)**: отключены ВСЕ кроме angle; icon_measure_dropdown тоже отключён
- **popMode = Area (2)**: отключены length, indirects, minmax, trapezoid, angle
- **popMode = Volume (3)**: отключены length, indirects, minmax, trapezoid, angle

### 1.3 Popup выбора точки отсчёта

Popup открывается по нажатию на `icon_reference_dropdown`. Содержит 4 кнопки:

| ImageView | Точка отсчёта | Tag (refLevel) | Иконка |
|-----------|---------------|-----------------|--------|
| `img_refmode_above` | Верх (from front end) | 0 | `ic_measuremode_above` |
| `img_refmode_middle` | Средний (from tripod) | 1 | `ic_measuremode_middle` |
| `img_refmode_below` | Низ (from rear end) | 2 | `ic_measuremode_below` |
| `img_refmode_distance` | От предыдущего (distance reference) | 3 | `ic_measuremode_distance` |

### 1.4 Layout ориентация

- Портретная блокировка: `setRequestedOrientation(1)` (SENSOR_PORTRAIT)
- Для планшетов: orientation не блокируется
- Screen orientation при download: `setRequestedOrientation(14)` (SENSOR_LANDSCAPE)

---

## 2. Обработка входящих пакетов (devMode 0, 60, 62)

### 2.1 Общая схема

Все входящие сообщения обрабатываются в `GLMDeviceController.onEvent()` → `handleEDCMessage()`:

```
EDCInputMessage → handleEDCMessage() → MTSyncContainer → ACTION_SYNC_CONTAINER_RECEIVED broadcast
```

### 2.2 DevMode = 0 (обычное измерение / idle)

**В `handleEDCMessage()`:**
- `devMode == 0` — это **результат обычного измерения** (line/distance)
- `MTSyncContainer` создаётся из `EDCInputMessage`
- Вызывается `setReferenceLevel(eDCInputMessage.getRefEdge())`
- Отправляется broadcast `ACTION_SYNC_CONTAINER_RECEIVED`
- Если `initSyncRequest == false` → вызывается `saveEDCMessage()` → создаётся `DistanceMeasurement`

**В `RemoteMeasurementListFragment` (BroadcastReceiver):**
```java
if (ACTION_SYNC_CONTAINER_RECEIVED.equals(intent.getAction())) {
    MTSyncContainer container = intent.getSerializableExtra(EXTRA_SYNC_CONTAINER);
    if (container == null || !isSendCommand) return;
    
    setReferenceLevel(container.getDistReference());
    // mode 62, 0, 60 — НЕ обрабатываются как режим измерения
    if (container.getMode() != 62 && container.getMode() != 0 && container.getMode() != 60) {
        if (container.getMode() == 22) {
            Toast: "Please turn GLM for inclination";
        } else {
            setMeasurementMode(container.getMode());
        }
    }
    setLaserOn(container.getLaserOn() == 1);
}
```

**Ключевой момент:** devMode 0 и 60 и 62 — это **служебные** статусные сообщения, они НЕ меняют `measurementType` в UI. Реальный режим измерения приходит в этих пакетах как `result` (для devMode 60) или как отдельное сообщение.

### 2.3 DevMode = 60 (ответ на изменение режима)

**В `MTSyncContainer` конструкторе:**
```java
if (devMode == 60) {
    float result = eDCInputMessage.getResult();
    if (result == floor(result)) {
        setMode(EDCInputMessage.turnEDCModeToSyncMode((int) result));
    } else {
        setMode(EDCInputMessage.turnEDCModeToSyncMode(mtcCastFromFloatToInt(result)));
    }
}
```

DevMode 60 — это **подтверждение от устройства** что режим изменён. `result` содержит номер нового режима (1=line, 2=area, 3=volume, 4=angle, и т.д.), который конвертируется через `turnEDCModeToSyncMode()`.

**В `RemoteControlCommandsUtils.sendCommand()`:**
```java
// Для EDC-устройств (GLM50C):
if (isMode) {  // изменение режима измерения
    eDCOutputMessage.setDevMode(60);
    eDCOutputMessage.setRemoteCtrlData(EDCInputMessage.turnSyncModeToEDCMode(mode));
}
```

### 2.4 DevMode = 62 (ответ на изменение refEdge)

**В `MTSyncContainer` конструкторе:**
```java
if (devMode == 62) {
    setMode(62);
}
```

DevMode 62 — это **подтверждение от устройства** что точка отсчёта изменена. Режим в UI НЕ меняется (mode=62 фильтруется).

**В `RemoteControlCommandsUtils.sendCommand()`:**
```java
else if (isRef) {  // изменение точки отсчёта
    eDCOutputMessage.setDevMode(62);
    eDCOutputMessage.setRemoteCtrlData(refEdge);  // 0,1,2,3
}
```

### 2.5 Отправка команд (RemoteControlCommandsUtils)

```java
public static void sendCommand(Activity activity, int cmd, boolean isMode, boolean isRef,
                                int mode, int refEdge, int angleMode) {
    // Для EDC-устройств (GLM50C):
    if (cmd == 0) {
        // Кнопка лазера — EDCDoRemoteTriggerButtonMessage(buttonNumber=0)
        EDCDoRemoteTriggerButtonMessage msg = new EDCDoRemoteTriggerButtonMessage();
        msg.setButtonNumber(0);
    } else {
        EDCOutputMessage msg = new EDCOutputMessage();
        if (isMode) {
            msg.setDevMode(60);
            msg.setRemoteCtrlData(EDCInputMessage.turnSyncModeToEDCMode(mode));
        } else if (isRef) {
            msg.setDevMode(62);
            msg.setRemoteCtrlData(refEdge);
        } else {
            msg.setDevMode(0);
            msg.setRemoteCtrlData(0);
        }
        msg.setKeypadBypass(0);
        msg.setSyncControl(1);
    }

    // Для GLM100 (не-EDC):
    SyncOutputMessage msg = new SyncOutputMessage();
    msg.setMode(mode);
    if (mode == 4) msg.setAngleReference(angleMode);
    else msg.setDistReference(refEdge);
    msg.setSyncControl(1);
    msg.setSwitchMode(cmd);
}
```

---

## 3. Отображение refEdge и measurementType в UI

### 3.1 measurementType (режим измерения)

**Установка:** `setMeasurementMode(int i)` → `RemoteControlCommandsUtils.setMeasurementMode(i, icon_measure, btnLaserOn)`

| mode | Иконка | Текст кнопки лазера |
|------|--------|---------------------|
| 1 (Line) | `ic_distance1` | "Measure" / "Switch on laser" |
| 2 (Area) | `ic_area` | "Measure" / "Switch on laser" |
| 3 (Volume) | `ic_volume` | "Measure" / "Switch on laser" |
| 4 (Angle) | `ic_angle` | **всегда "Measure"** |
| 6 (Min/Max) | `ic_minmax` | "Measure" / "Switch on laser" |
| 7 (Indirect height) | `ic_distance3` | "Measure" / "Switch on laser" |
| 8 (Indirect length) | `ic_distance2` | "Measure" / "Switch on laser" |
| 9 (Double indirect) | `ic_distance4` | "Measure" / "Switch on laser" |
| 10 (Wall area) | `ic_wallarea` | "Measure" / "Switch on laser" |
| 14 (Trapezoid) | `ic_trapezoid` | "Measure" / "Switch on laser" |

**Padding:** `icon_measure.setPadding(15, 15, 0, 0)` — для всех иконок

### 3.2 referenceLevel (точка отсчёта)

**Установка:** `setReferenceLevel(int i)` → `RemoteControlCommandsUtils.setReferenceLevel(i, icon_reference)`

| refEdge | Иконка | Описание |
|---------|--------|----------|
| 0 | `ic_measuremode_above` | От переднего края (верх) |
| 1 | `ic_measuremode_middle` | От штатива (середина) |
| 2 | `ic_measuremode_below` | От заднего края (низ) |
| 3 | `ic_measuremode_distance` | От предыдущего измерения |

**Padding:** `icon_reference.setPadding(15, 15, 0, 0)`

---

## 4. Иконки для типов измерений и точек отсчёта

### 4.1 Иконки в списке измерений (MeasurementExpandableListAdapter)

Иконка измерения определяется по `resultType`:

| resultType | Иконка | Описание |
|------------|--------|----------|
| 1 | `ic_distance1` | Прямое измерение (Line) |
| 2 | `ic_area` | Площадь |
| 3 | `ic_volume` | Объём |
| 4 | `ic_angle` | Угол |
| 6 | `ic_minmax` | Min/Max |
| 7 | `ic_distance3` | Косвенное — высота |
| 8 | `ic_distance2` | Косвенное — длина |
| 9 | `ic_distance4` | Двойное косвенное |
| 10 | `ic_wallarea` | Площадь стен |
| 14 | `ic_trapezoid` | Трапеция |

**По умолчанию** (если не распознан): `ic_distance1`

### 4.2 Иконка раскрытия группы

- Раскрыта: `ic_arrow_up`
- Свёрнута: `ic_arrow_down`
- Для operator=0 И (angle || line || manual result): иконка раскрытия **скрыта** (visibility=4 / INVISIBLE)

### 4.3 Иконка удаления

- `icon_delete_measurement` — кнопка удаления измерения в группе

---

## 5. Работа кнопок

### 5.1 Кнопка Laser (btnLaserOn)

```
onClick → isSendCommand = true → sendCommand(0)
```

- `cmd = 0` → `EDCDoRemoteTriggerButtonMessage(buttonNumber=0)` — нажатие кнопки измерения на устройстве
- После получения devMode=0 (результат) лазер автоматически выключается
- Текст кнопки обновляется через `setLaserOn(boolean)`:
  - `true` → "Measure" (лазер был включён, теперь ждём результат)
  - `false` → "Switch on laser" + иконка (лазер выключен)

### 5.2 Кнопка Set Type (выбор режима измерения)

```
icon_measure_dropdown.onClick → popGlmMeasurements(false)
```

- Открывается popup с 9 кнопками режимов
- Каждая кнопка → `setImageMeasureMode(mode, iconRes, true)` → `onMeasOptionSelected(mode)`
- `onMeasOptionSelected()`:
  - `measurementType = mode`
  - `isMode = true, isSendCommand = true`
  - `sendCommand(1)` → devMode=60 с новым режимом
  - Popup закрывается

### 5.3 Кнопка Set Ref (выбор точки отсчёта)

```
icon_reference_dropdown.onClick → popGlmMeasurements(true)
```

- Открывается popup с 4 кнопками точек отсчёта
- Каждая кнопка → `setImageMeasureMode(refEdge, iconRes, false)` → `onReferenceLevelSelected(refEdge)`
- `onReferenceLevelSelected()`:
  - `referenceLevel = refEdge`
  - `isRef = true, isSendCommand = true`
  - `sendCommand(1)` → devMode=62 с новым refEdge
  - Popup закрывается

### 5.4 Кнопка Download (загрузка истории)

```
imgDownload.onClick → downLoadListener.onClick()
```

**Для GLM100:**
- Проверка `getHistorySize() > 0`
- Показывается `DownloadDialogFragment`
- `startHistorySync()` → `SyncListOutputMessage(indexFrom, indexTo)`

**Для EDC (GLM50C):**
- `getSettings()` → `ACTION_COUNT_OF_HISTORY_ITEMS`
- `requestHistoryItem(index, indicator)` → `EDCOutputMessage(devMode=58)`

---

## 6. Сохранение и отображение данных

### 6.1 Сохранение измерений

**Путь данных:**
```
Устройство → EDCInputMessage → GLMDeviceController.handleEDCMessage()
  → MTSyncContainer → broadcast ACTION_SYNC_CONTAINER_RECEIVED
  → saveEDCMessage() → DistanceMeasurement → MeasurementManager
  → broadcast ACTION_MEASUREMENT_CREATED
```

**DistanceMeasurement создаётся с полями:**
- `resultValue` — результат (м)
- `resultType` — режим (1=line, 2=area, 3=volume, 4=angle...)
- `resultTimestamp` — время
- `value1, value2, value3` — компоненты
- `type1, type2, type3` — типы компонентов (1=distance, 4=angle)
- `name1, name2, name3` — имена ("a", "b", "c")
- `resultName` — имя результата (из ресурсов)
- `operator` — 0=из устройства, 2=ручной ввод

### 6.2 Отображение в списке (MeasurementExpandableListAdapter)

**Групповой элемент (getGroupView):**
```
[icon_dimension] [measurement_group_name (editable)] [icon_delete]
                  [measurement_text — результат]
                              [date_text — дата/время]
                                        [measurement_expand_view]
```

**Дочерний элемент (getChildView):**
```
[edit_text_measurement1] [text_measurement1] [date_text_child1] [divider]
[edit_text_measurement2] [text_measurement2] [date_text_child2] [divider]
[edit_text_measurement3] [text_measurement3] [date_text_child3]
```

- Компоненты показываются в зависимости от `operator` и `resultType`
- Для Area (2) и Volume (3): показываются все 3 компонента
- Для Wall Area (10): 3 компонента (длина, высота 1, высота 2)
- Для Indirect (7,8): 2 компонента (distance, angle)
- Для Double Indirect (9): 3 компонента (2x distance, angle)
- Для Angle (4) и Line (1) с operator=0: дочерний вид **не показывается**

### 6.3 Форматирование значений

- **Distance:** `value * 1000.0f` → формат с единицами (м/футы/дюймы)
- **Area:** `value * 1000.0f * 1000.0f` → м²/фут² + супрерскрипт "²"
- **Volume:** `value * 1000.0f * 1000.0f * 1000.0f` → м³ + "³"
- **Angle:** `AppSettings.formatAngle(Degree, value)` — градусы
- **Timestamp:** `AppSettings.formatTimestamp(context, date)`

### 6.4 Редактирование

- Имя измерения (`measurement_group_name`) — редактируемый EditText, сохраняется по IME action (done) или потере фокуса
- Имена компонентов (`edit_text_measurement1/2/3`) — редактируемые, по умолчанию "a", "b", "c"
- Удаление: `icon_delete_measurement` → `onDeleteMeasurement(list, position)`

### 6.5 Сортировка

Список сортируется по `createdDate` **убывающе** (новые сверху):
```java
Collections.sort(list, (a, b) -> b.getCreatedDate().compareTo(a.getCreatedDate()));
```

---

## 7. Ключевые выводы для реализации

1. **Три основных devMode**: 0 = результат измерения, 60 = подтверждение смены режима, 62 = подтверждение смены refEdge
2. **refEdge НЕ меняется при devMode 0/60/62** в UI — только при явном ответе от устройства
3. **measurementType НЕ меняется при devMode 0/60/62** — фильтруется в BroadcastReceiver
4. **Popup-окна** закрываются автоматически после выбора элемента
5. **Кнопка лазера** — это триггер измерения (buttonNumber=0), НЕ включение лазера напрямую
6. **Список** — ExpandableListView с группами (результат) и детьми (компоненты)
7. **Данные** сохраняются через `MeasurementManager`, который рассылает уведомления всем слушателям
