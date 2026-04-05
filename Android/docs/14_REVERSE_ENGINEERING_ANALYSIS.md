# Анализ Bosch Measuring Master 1.9.4 — Чего избегать

## Общая информация

| Параметр | Значение |
|----------|----------|
| **Пакет** | `com.bosch.measuringmaster` |
| **Версия** | 1.9.4 (build 169) |
| **minSdk** | 18 (Android 4.3) |
| **targetSdk** | 29 (Android 10) |
| **Java файлов** | ~684 (основной код) + ~3000 (библиотеки) |

---

## 🔴 Критические проблемы архитектуры

### 1. Singleton Session с глобальным состоянием

```java
public class Session {
    private static Session mSession;  // Singleton
    // Всё через SharedPreferences
}
```

**Проблема:** Глобальное состояние, сложно тестировать, нет DI.

**Как у нас:** Использовать DI (Hilt/Koin для Android, встроенный для других платформ).

---

### 2. Хранение данных — SharedPreferences + JSON файлы

```java
// Session.java — всё в SharedPreferences
PreferenceManager.getDefaultSharedPreferences(context)
    .getLong(ConstantsUtils.KEY_GLM_LAST_EXPORT_DATE, 0L);

// ProjectModel.java — сериализация в JSON
public JSONObject toJSON() { ... }
public static ProjectModel fromJSON(JSONObject jSONObject) { ... }
```

**Проблема:**
- Нет транзакций, нет целостности данных
- JSON файлы на файловой системе — медленно при больших объёмах
- Нет миграций схемы
- Риск потери данных при сбое записи

**Как у нас:** Room (SQLite) или другая БД с миграциями и транзакциями.

---

### 3. Модель данных без валидации

```java
public class Measurement implements Serializable {
    private Float value1;
    private Float value2;
    private Float value3;
    private int type1, type2, type3;
    private int operator;  // 0=NONE, 1=PLUS, 2=MINUS...
    // Нет валидации, всё public через getters/setters
}
```

**Проблема:**
- `Float` вместо `float` — null может сломать расчёты
- Магические числа для операторов (0, 1, 2...7)
- Нет валидации при установке значений
- `Serializable` — медленный, устаревший

**Как у нас:** Value objects, sealed classes для типов, валидация на уровне модели.

---

### 4. Раздутый ProjectModel — God Object

```java
public class ProjectModel {
    private final List<PlanModel> plans = new ArrayList();
    private final List<PictureModel> pictureList = new ArrayList();
    private final List<WallModel> walls = new ArrayList();
    private final List<ThermoModel> thermoList = new ArrayList();
    private final List<CalculatorModel> calculatorList = new ArrayList();
    private final List<NoteModel> todoList = new ArrayList();
    private final List<NoteModel> noteList = new ArrayList();
    // +700 строк кода, +50 полей
}
```

**Проблема:**
- Один класс управляет всем (планы, стены, фото, заметки, todo, калькуляторы)
- Сложно тестировать и поддерживать
- Нарушение Single Responsibility Principle

**Как у нас:** Разделить на отдельные агрегаты/репозитории.

---

### 5. 25+ Activity без навигационного компонента

| Activity | Назначение |
|----------|-----------|
| SplashScreenActivity | Запуск |
| MainActivity | Главный экран |
| BluetoothConnectionActivity | Подключение к дальномеру |
| PlanActivity | План помещения |
| PictureActivity | Фото |
| ThermoActivity | Тепловизор |
| WallViewActivity | Стены |
| CalculatorActivity | Калькулятор |
| NoteActivity | Заметки |
| ToDoActivity | Задачи |
| ExportActivity | Экспорт |
| SettingsActivity | Настройки |
| ...ещё 13+ | Разные |

**Проблема:**
- Нет Navigation Component
- `launchMode="singleTop"` везде — костыли для навигации
- Нет типизированной навигации (аргументы через intent extras)

**Как у нас:** Navigation Component (Android) или роутер для других платформ.

---

### 6. Background Services для экспорта

```xml
<service android:name="com.bosch.measuringmaster.service.project.PDFExportService"/>
<service android:name="com.bosch.measuringmaster.ui.xls.ProjectXLSExportService"/>
<service android:name="com.bosch.measuringmaster.service.project.ExportLogoService"/>
<!-- ещё 10+ сервисов -->
```

**Проблема:**
- 15+ отдельных Service для каждой операции экспорта
- Нет WorkManager — сервисы могут быть убиты системой
- Нет прогресса и отмены операций

**Как у нас:** WorkManager с прогрессом, отменой и повторными попытками.

---

### 7. Избыточные permissions

```xml
<uses-permission android:name="android.permission.READ_LOGS"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.BROADCAST_STICKY"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

**Проблема:**
- `READ_LOGS` — не нужно для приложения замеров, подозрительно
- `READ_PHONE_STATE` — устаревшее, нужно только для старых Android
- `ACCESS_COARSE_LOCATION` — нужно только для Bluetooth скана на старых Android

**Как у нас:** Минимальные permissions, запрашивать по необходимости.

---

### 8. Нет разделения на слои

```
com.bosch.measuringmaster/
├── model/          ← Данные + логика вместе
├── ui/             ← Activity без ViewModel
├── service/        ← Background tasks
├── bluetooth/      ← Прямая работа с BLE
├── utils/          ← God utils классы
└── parser/         ← Парсеры устройств
```

**Проблема:** Нет чёткого разделения на data/domain/presentation.

**Как у нас:** Clean Architecture или хотя бы MVVM.

---

## 🟡 Средние проблемы

| Проблема | Описание |
|----------|----------|
| **Identifier-based связи** | `planIdentifiers: List<String>` вместо прямых связей |
| **Date как Long** | `timestamp1`, `timestamp2` — легко перепутать |
| **Нет обработки ошибок** | `catch (JSONException e) { return null; }` — молчаливый фолл |
| **`largeHeap="true"`** | Костыль для утечек памяти |
| **`requestLegacyExternalStorage="true"`** | Устаревший подход к файлам |
| **Gson + JSONObject** | Два подхода к JSON в одном классе |

---

## ✅ Что сделано хорошо

| Решение | Описание |
|---------|----------|
| **BLE интерфейс** | `IBleDeviceScanner` — абстракция для Bluetooth |
| **JSON сериализация** | Есть `toJSON()` / `fromJSON()` — можно мигрировать |
| **Модель измерения** | Поддержка операторов (+, -, *, /, sin, cos, sqrt) |
| **Checkbox фичи** | `checkboxDetailedPlan`, `checkboxWall` — модульность |

---

## 📋 Итоговый чеклист для нашего приложения

### Избегать:
- [ ] Singleton для состояния → DI
- [ ] SharedPreferences для данных → Room/БД
- [ ] God Object → разделение ответственности
- [ ] Магические числа → enum/sealed class
- [ ] 25+ Activity без навигации → Navigation Component
- [ ] Services для фоновых задач → WorkManager
- [ ] Избыточные permissions → минимум
- [ ] Молчаливый catch ошибок → логирование + UI

### Использовать:
- [ ] Clean Architecture / MVVM
- [ ] Room с миграциями
- [ ] Value objects с валидацией
- [ ] WorkManager для экспорта
- [ ] Navigation Component
- [ ] Hilt/Koin для DI

---

*Дата анализа: 3 апреля 2026 г.*
*Инструмент: jadx 1.5.0*
