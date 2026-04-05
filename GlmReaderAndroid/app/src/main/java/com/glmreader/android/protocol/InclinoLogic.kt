package com.glmreader.android.protocol

import com.glmreader.android.protocol.BlePacketParser.MeasurementType
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * Логика инклинометра для Bosch GLM 50C.
 *
 * Определяет тип измерения по углу наклона и рассчитывает размеры:
 * - Прямой замер: |angle| ≤ threshold
 * - Косвенная высота: angle > threshold → a × sin(b)
 * - Косвенная ширина: angle < -threshold → a × cos(b)
 *
 * Спецификация: docs/INCLINO_LOGIC.md
 */
object InclinoLogic {

    /** Результат определения типа замера */
    data class InclineResult(
        val calculatedType: MeasurementType,
        val calculatedValue: Double?, // рассчитанное значение (мм)
        val calculationType: CalculationType,
        val isAutoDetected: Boolean,
        val markerIcon: String, // 📏, 📐↑, 📐→
        val displayName: String
    )

    /** Тип расчёта */
    enum class CalculationType(val dbValue: String, val displayName: String) {
        DIRECT("direct", "Прямой замер"),
        HEIGHT("height", "Косвенная высота"),
        WIDTH("width", "Косвенная ширина"),
        DOUBLE_HEIGHT("double_height", "Двойная высота"),
        DOUBLE_WIDTH("double_width", "Двойная ширина");
    }

    /** Порог по умолчанию (градусы) */
    const val DEFAULT_THRESHOLD_DEG = 5.0

    /**
     * Определение типа замера по углу наклона.
     *
     * @param angleDeg угол из BLE пакета (градусы)
     * @param comp1Value расстояние a (метры из пакета)
     * @param threshold порог прямого замера (по умолчанию 5°)
     * @return InclineResult с рассчитанным типом и значением
     */
    fun determineType(
        angleDeg: Double,
        comp1Value: Double,
        threshold: Double = DEFAULT_THRESHOLD_DEG
    ): InclineResult {
        val absAngle = abs(angleDeg)

        return when {
            absAngle <= threshold -> {
                // Прямой замер
                InclineResult(
                    calculatedType = MeasurementType.DIRECT,
                    calculatedValue = null,
                    calculationType = CalculationType.DIRECT,
                    isAutoDetected = true,
                    markerIcon = "\uD83D\uDCCF", // 📏
                    displayName = "Прямой замер"
                )
            }
            angleDeg > threshold -> {
                // Косвенная высота: a × sin(b)
                val height = comp1Value * sin(Math.toRadians(angleDeg))
                InclineResult(
                    calculatedType = MeasurementType.INDIRECT_HEIGHT,
                    calculatedValue = height,
                    calculationType = CalculationType.HEIGHT,
                    isAutoDetected = true,
                    markerIcon = "\uD83D\uDCCF\uFE0F\u200D\u2B06\uFE0F", // 📐↑ (приближённо)
                    displayName = "Косвенная высота"
                )
            }
            else -> { // angleDeg < -threshold
                // Косвенная ширина: a × cos(|b|)
                val width = comp1Value * cos(Math.toRadians(absAngle))
                InclineResult(
                    calculatedType = MeasurementType.INDIRECT_LENGTH,
                    calculatedValue = width,
                    calculationType = CalculationType.WIDTH,
                    isAutoDetected = true,
                    markerIcon = "\uD83D\uDCCF\uFE0F\u200D\u27A1\uFE0F", // 📐→ (приближённо)
                    displayName = "Косвенная ширина"
                )
            }
        }
    }

    /**
     * Расчёт для двойного косвенного замера (2 измерения).
     *
     * @param a1 расстояние первого замера (м)
     * @param b1 угол первого замера (°)
     * @param a2 расстояние второго замера (м)
     * @param b2 угол второго замера (°)
     * @param isHeight true = высота, false = ширина
     */
    fun calculateDouble(
        a1: Double,
        b1: Double,
        a2: Double,
        b2: Double,
        isHeight: Boolean
    ): Double {
        return if (isHeight) {
            a1 * sin(Math.toRadians(b1)) + a2 * sin(Math.toRadians(b2))
        } else {
            a1 * cos(Math.toRadians(abs(b1))) + a2 * cos(Math.toRadians(abs(b2)))
        }
    }

    /**
     * Получить иконку для отображения в списке.
     */
    fun getMarkerIcon(type: MeasurementType): String {
        return when (type) {
            MeasurementType.DIRECT -> "\uD83D\uDCCF" // 📏
            MeasurementType.INDIRECT_HEIGHT -> "\uD83D\uDCC8" // 📈 (замена 📐↑)
            MeasurementType.INDIRECT_LENGTH -> "\uD83D\uDCC9" // 📉 (замена 📐→)
            MeasurementType.AREA -> "\uD83D\uDFE6" // 🟦
            MeasurementType.VOLUME -> "\uD83E\uDFCA" // 🧊
            else -> "\uD83D\uDCCF"
        }
    }

    /**
     * Получить краткое описание расчёта.
     */
    fun getCalculationDescription(
        calculationType: CalculationType,
        a: Double,
        angle: Double,
        result: Double
    ): String {
        return when (calculationType) {
            CalculationType.DIRECT -> "%.3f м".format(a)
            CalculationType.HEIGHT -> "%.1f × sin(%.1f°) = %.3f м".format(a, angle, result)
            CalculationType.WIDTH -> "%.1f × cos(%.1f°) = %.3f м".format(a, abs(angle), result)
            CalculationType.DOUBLE_HEIGHT -> "a1×sin(b1) + a2×sin(b2) = %.3f м".format(result)
            CalculationType.DOUBLE_WIDTH -> "a1×cos(b1) + a2×cos(b2) = %.3f м".format(result)
        }
    }
}
