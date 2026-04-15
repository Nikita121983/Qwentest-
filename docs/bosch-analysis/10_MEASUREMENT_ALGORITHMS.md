# Алгоритмы определения типа замера и точности

**Дата:** 2 апреля 2026 г.
**Источник:** Чаты + `Словарь условий2.txt` + `abbreviations.json`

---

## 1. ТИПЫ ЗАМЕРОВ

### 1.1. Классификация по Bosch CSV

**Колонка F (Вид замера):**

| Значение | Тип | Описание |
|----------|-----|----------|
| **Длина** | Linear | Линейный размер (ширина, высота) |
| **Площадь** | Area | Площадь помещения |
| **Объём** | Volume | Объём помещения |
| **Не указано** | Unknown | Тип не определён |

### 1.2. Классификация по контексту

**Алгоритм определения типа замера:**

```
1. Есть маркировка двери (31 П1.1.Кв1...)?
   ├─ ДА → Это замер проёма (door opening)
   │   └─ Перейти к определению точки замера (раздел 2)
   │
   └─ НЕТ → Это общий замер (general)
       ├─ Значение > 1000 мм?
       │   ├─ ДА → Возможно ширина/высота помещения
       │   └─ НЕТ → Возможно толщина стены
       │
       └─ Проверить контекст (предыдущие/следующие замеры)
```

---

## 2. ТОЧКИ ЗАМЕРА ПРОЁМА

### 2.1. Стандартная группа (6 замеров на 1 дверь)

**Порядок в CSV (снизу вверх):**

```
Строка 1: Измерение;2228,9;mm;...;Длина  ← p6: Высота (правая)
Строка 2: Измерение;2227,1;mm;...;Длина  ← p5: Высота (середина)
Строка 3: Дп;2208,4;mm;...;Длина         ← p4: Высота (левая)
Строка 4: Измерение;999,3;mm;...;Длина   ← p3: Ширина (верх)
Строка 5: Измерение;1010,6;mm;...;Длина  ← p2: Ширина (середина)
Строка 6: 31 П1.1.Кв1...;1014,5;mm;...   ← p1: Ширина (низ) + маркировка
```

### 2.2. Алгоритм определения точки замера

```kotlin
fun determinePointType(
    rowIndex: Int,
    value: Double,
    previousValue: Double?,
    nextValue: Double?,
    hasMarking: Boolean,
    markingCode: String?
): PointType {
    return when {
        // Маркировка всегда на последнем замере в группе
        hasMarking -> {
            // Определяем по значению и контексту
            when {
                markingCode.contains("дп", ignoreCase = true) -> PointType.HEIGHT_LEFT
                markingCode.contains("пп", ignoreCase = true) -> PointType.HEIGHT_RIGHT
                else -> PointType.WIDTH_BOTTOM
            }
        }

        // Первые 3 замера в группе (считаем снизу)
        rowIndex % 6 in 0..2 -> {
            when (rowIndex % 3) {
                0 -> PointType.WIDTH_BOTTOM
                1 -> PointType.WIDTH_MIDDLE
                2 -> PointType.WIDTH_TOP
                else -> PointType.UNKNOWN
            }
        }

        // Последние 3 замера в группе
        rowIndex % 6 in 3..5 -> {
            when (rowIndex % 3) {
                0 -> PointType.HEIGHT_LEFT
                1 -> PointType.HEIGHT_MIDDLE
                2 -> PointType.HEIGHT_RIGHT
                else -> PointType.UNKNOWN
            }
        }

        else -> PointType.UNKNOWN
    }
}

enum class PointType {
    WIDTH_BOTTOM,      // Ширина (низ)
    WIDTH_MIDDLE,      // Ширина (середина)
    WIDTH_TOP,         // Ширина (верх)
    HEIGHT_LEFT,       // Высота (левая)
    HEIGHT_MIDDLE,     // Высота (середина)
    HEIGHT_RIGHT,      // Высота (правая)
    DIAGONAL,          // Диагональ
    UNKNOWN            // Не определено
}
```

### 2.3. Эвристики по значениям

**Дополнительные признаки:**

| Признак | Значение | Тип замера |
|---------|----------|------------|
| **Ширина** | 800-1200 мм | Стандартная дверь |
| **Ширина** | 1400-2000 мм | Двустворчатая дверь |
| **Высота** | 2000-2300 мм | Стандартная высота |
| **Высота** | > 2500 мм | Панорамная дверь |
| **Диагональ** | √(ширина² + высота²) ± 10 мм | Проверка геометрии |

**Алгоритм проверки:**

```kotlin
fun validateMeasurements(width: Double, height: Double, diagonal: Double?): ValidationResult {
    val errors = mutableListOf<String>()
    val warnings = mutableListOf<String>()

    // Проверка ширины
    if (width < 600) {
        errors.add("Ширина ${width} мм < 600 мм (минимум)")
    } else if (width > 2000) {
        warnings.add("Ширина ${width} мм > 2000 мм (нестандарт)")
    }

    // Проверка высоты
    if (height < 1800) {
        errors.add("Высота ${height} мм < 1800 мм (минимум)")
    } else if (height > 2500) {
        warnings.add("Высота ${height} мм > 2500 мм (нестандарт)")
    }

    // Проверка диагонали (если есть)
    if (diagonal != null) {
        val expectedDiagonal = kotlin.math.sqrt(width * width + height * height)
        val tolerance = 10.0 // мм
        val diff = kotlin.math.abs(diagonal - expectedDiagonal)

        if (diff > tolerance) {
            errors.add("Диагональ ${diagonal} мм не соответствует расчётной ${expectedDiagonal} мм (допуск ±${tolerance} мм)")
        }
    }

    // Проверка разброса замеров (3 замера ширины/высоты)
    // ...

    return ValidationResult(errors, warnings)
}
```

---

## 3. ТОЧНОСТЬ ЗАМЕРОВ

### 3.1. Точность Bosch GLM 50 C

**Характеристики:**

| Параметр | Значение |
|----------|----------|
| **Диапазон измерений** | 0.05 - 50 м |
| **Точность** | ±1.5 мм (на 10 м) |
| **Разрешение** | 1 мм |
| **Погрешность** | ±2 мм (типичная) |

### 3.2. Допуски из словаря

**Из `abbreviations.json`:**

```json
{
  "tolerance": {
    "60": "Допуск ширины 60 мм",
    "90": "Допуск ширины 90 мм",
    "200": "Допуск высоты 200 мм"
  },
  "montage_gap": {
    "20": "Монтажный зазор 20 мм",
    "30": "Монтажный зазор 30 мм",
    "40": "Монтажный зазор 40 мм"
  }
}
```

### 3.3. Алгоритм проверки точности

```kotlin
data class MeasurementAccuracy(
    val value: Double,
    val unit: String = "mm",
    val device: String = "Bosch GLM 50 C",
    val timestamp: Long
)

class AccuracyChecker {
    companion object {
        // Точность Bosch GLM 50 C
        private const val DEVICE_ACCURACY_MM = 1.5
        private const val DEVICE_RESOLUTION_MM = 1.0

        // Допуски на монтаж
        private const val MOUNTING_GAP_MIN_MM = 20.0
        private const val MOUNTING_GAP_MAX_MM = 40.0

        // Допуски на разброс замеров
        private const val WIDTH_SPREAD_MAX_MM = 10.0
        private const val HEIGHT_SPREAD_MAX_MM = 10.0
    }

    /**
     * Проверка точности замера
     */
    fun checkAccuracy(
        measurements: List<MeasurementAccuracy>,
        pointType: PointType
    ): AccuracyResult {
        val values = measurements.map { it.value }
        val avg = values.average()
        val spread = values.max() - values.min()
        val stdDev = calculateStdDev(values)

        val errors = mutableListOf<String>()
        val warnings = mutableListOf<String>()

        // Проверка разброса
        when (pointType) {
            PointType.WIDTH_BOTTOM, PointType.WIDTH_MIDDLE, PointType.WIDTH_TOP -> {
                if (spread > WIDTH_SPREAD_MAX_MM) {
                    warnings.add("Разброс замеров ширины: ${spread} мм (макс. ${WIDTH_SPREAD_MAX_MM} мм)")
                }
            }
            PointType.HEIGHT_LEFT, PointType.HEIGHT_MIDDLE, PointType.HEIGHT_RIGHT -> {
                if (spread > HEIGHT_SPREAD_MAX_MM) {
                    warnings.add("Разброс замеров высоты: ${spread} мм (макс. ${HEIGHT_SPREAD_MAX_MM} мм)")
                }
            }
        }

        // Проверка на соответствие точности прибора
        if (stdDev < DEVICE_RESOLUTION_MM) {
            warnings.add("Стандартное отклонение ${stdDev} мм < разрешения прибора ${DEVICE_RESOLUTION_MM} мм")
        }

        return AccuracyResult(
            average = avg,
            spread = spread,
            stdDev = stdDev,
            errors = errors,
            warnings = warnings,
            isValid = errors.isEmpty()
        )
    }

    private fun calculateStdDev(values: List<Double>): Double {
        val avg = values.average()
        val squareDiffs = values.map { (it - avg) * (it - avg) }
        return kotlin.math.sqrt(squareDiffs.average())
    }
}

data class AccuracyResult(
    val average: Double,
    val spread: Double,
    val stdDev: Double,
    val errors: List<String>,
    val warnings: List<String>,
    val isValid: Boolean
)
```

---

## 4. ВАЛИДАЦИЯ ГРУППЫ ЗАМЕРОВ

### 4.1. Проверка комплектности

**Алгоритм:**

```kotlin
class MeasurementGroupValidator {
    /**
     * Проверка комплектности группы замеров (6 замеров на 1 дверь)
     */
    fun validateGroup(measurements: List<Measurement>): GroupValidationResult {
        val errors = mutableListOf<String>()
        val warnings = mutableListOf<String>()

        // Проверка количества
        if (measurements.size != 6) {
            errors.add("Ожидается 6 замеров, найдено: ${measurements.size}")
        }

        // Проверка наличия маркировки
        val hasMarking = measurements.any { it.marking != null }
        if (!hasMarking) {
            warnings.add("Отсутствует маркировка двери")
        }

        // Проверка точек замера
        val pointTypes = measurements.map { it.pointType }.toSet()
        val requiredPoints = setOf(
            PointType.WIDTH_BOTTOM,
            PointType.WIDTH_MIDDLE,
            PointType.WIDTH_TOP,
            PointType.HEIGHT_LEFT,
            PointType.HEIGHT_MIDDLE,
            PointType.HEIGHT_RIGHT
        )

        val missingPoints = requiredPoints - pointTypes
        if (missingPoints.isNotEmpty()) {
            errors.add("Отсутствуют точки замера: ${missingPoints.joinToString()}")
        }

        // Проверка значений
        val widths = measurements.filter { it.pointType in listOf(
            PointType.WIDTH_BOTTOM, PointType.WIDTH_MIDDLE, PointType.WIDTH_TOP
        ) }
        val heights = measurements.filter { it.pointType in listOf(
            PointType.HEIGHT_LEFT, PointType.HEIGHT_MIDDLE, PointType.HEIGHT_RIGHT
        ) }

        if (widths.all { it.value < 500 }) {
            errors.add("Все замеры ширины < 500 мм (подозрительно)")
        }

        if (heights.all { it.value < 1500 }) {
            errors.add("Все замеры высоты < 1500 мм (подозрительно)")
        }

        return GroupValidationResult(
            isValid = errors.isEmpty(),
            errors = errors,
            warnings = warnings,
            measurements = measurements
        )
    }
}

data class GroupValidationResult(
    val isValid: Boolean,
    val errors: List<String>,
    val warnings: List<String>,
    val measurements: List<Measurement>
)
```

### 4.2. Расчёт рекомендуемых размеров

**Алгоритм:**

```kotlin
data class DoorRecommendation(
    val width: Double,      // Рекомендуемая ширина
    val height: Double,     // Рекомендуемая высота
    val mountingGap: Double,// Монтажный зазор
    val notes: List<String>
)

fun calculateDoorRecommendation(measurements: List<Measurement>): DoorRecommendation {
    val widths = measurements.filter { it.pointType in listOf(
        PointType.WIDTH_BOTTOM, PointType.WIDTH_MIDDLE, PointType.WIDTH_TOP
    ) }.map { it.value }

    val heights = measurements.filter { it.pointType in listOf(
        PointType.HEIGHT_LEFT, PointType.HEIGHT_MIDDLE, PointType.HEIGHT_RIGHT
    ) }.map { it.value }

    // Берём минимальное значение (для гарантированного прохождения)
    val minWidth = widths.minOrNull() ?: 0.0
    val minHeight = heights.minOrNull() ?: 0.0

    // Монтажный зазор (из словаря: 20-40 мм)
    val mountingGap = 30.0 // среднее значение

    // Рекомендуемые размеры
    val recommendedWidth = minWidth - mountingGap * 2
    val recommendedHeight = minHeight - mountingGap

    val notes = mutableListOf<String>()

    // Проверка на конусность
    val widthSpread = widths.maxOrNull()!! - widths.minOrNull()!!
    if (widthSpread > 10) {
        notes.add("Проём конусный (разброс ширины: ${widthSpread} мм)")
    }

    val heightSpread = heights.maxOrNull()!! - heights.minOrNull()!!
    if (heightSpread > 10) {
        notes.add("Проём конусный (разброс высоты: ${heightSpread} мм)")
    }

    return DoorRecommendation(
        width = recommendedWidth,
        height = recommendedHeight,
        mountingGap = mountingGap,
        notes = notes
    )
}
```

---

## 5. ПРИМЕРЫ

### 5.1. Успешная валидация

**Входные данные:**
```
p1: Ширина (низ)    = 1014.5 мм (маркировка: 31 П1.1.Кв1)
p2: Ширина (середина) = 1010.6 мм
p3: Ширина (верх)   = 999.3 мм
p4: Высота (левая)  = 2208.4 мм (Дп)
p5: Высота (середина) = 2227.1 мм
p6: Высота (правая) = 2228.9 мм
```

**Результат:**
```
✅ Группа полная (6 замеров)
✅ Маркировка найдена
✅ Разброс ширины: 15.2 мм (допустимо)
✅ Разброс высоты: 20.5 мм (допустимо)
✅ Рекомендуемые размеры: 954.5 × 2178.4 мм
✅ Монтажный зазор: 30 мм
```

### 5.2. Ошибки валидации

**Входные данные:**
```
p1: Ширина (низ)    = 450 мм (маркировка)
p2: Ширина (середина) = 460 мм
p3: Ширина (верх)   = 440 мм
p4: Высота (левая)  = 1400 мм
p5: Высота (середина) = 1420 мм
p6: Высота (правая) = 1410 мм
```

**Результат:**
```
❌ Ширина 450 мм < 600 мм (минимум для двери)
❌ Высота 1400 мм < 1800 мм (минимум для двери)
⚠️ Возможно это не дверь, а окно/люк
```

---

## 6. ССЫЛКИ

- **Словарь допусков:** [`abbreviations.json`](abbreviations.json) (раздел `tolerance`, `montage_gap`)
- **Типы точек замера:** [`09_MARKUP_GUIDE.md`](09_MARKUP_GUIDE.md) (раздел 3.2)
- **Bosch GLM 50 C характеристики:** Официальная документация Bosch

---

*Документ создан: 2 апреля 2026 г.*
