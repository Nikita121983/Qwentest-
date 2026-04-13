package com.glmreader.android.protocol

import com.glmreader.android.data.entity.MeasurementEntity
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Парсер входящих BLE-пакетов Bosch GLM 50C.
 *
 * Формат пакета (Device → Host):
 *   [0]    FrameMode    (0xC0 = LONG)
 *   [1]    Command      (0x55 = EDC)
 *   [2]    PayloadLen   (минимум 0x10)
 *   [3]    DevModeRef   [refEdge:2][devMode:6]
 *   [4]    DevStatus    [laser:1][temp:1][batt:1][units:1][status:4]
 *   [5]    SeqNum       (0-255)
 *   [6]    Константа    (0x71)
 *   [7-10] Result       (float32 LE)
 *   [11-14] Comp1      (float32 LE)
 *   [15-18] Comp2      (float32 LE) — угол в градусах
 *   [19-20] MeasID     (uint16 LE, опционально)
 */
object BlePacketParser {

    /** Результат парсинга BLE-пакета */
    data class ParsedMeasurement(
        val devMode: Int,
        val refEdge: Int,
        val resultValue: Double,
        val comp1Value: Double,
        val comp2Value: Double, // угол в градусах
        val laserOn: Boolean,
        val tempWarning: Boolean,
        val battLow: Boolean,
        val isMetric: Boolean,
        val deviceStatus: Int,
        val seqNum: Int,
        val measId: Int?,
        val rawHex: String
    ) {
        /** Преобразование в MeasurementEntity */
        fun toEntity(
            projectId: String? = null,
            groupId: String? = null,
            objectId: String? = null,
            deviceName: String = "GLM 50 C"
        ) = MeasurementEntity(
            measurementType = devMode,
            refEdge = refEdge,
            resultValue = resultValue,
            comp1Value = comp1Value,
            comp2Value = comp2Value,
            angleDeg = comp2Value,
            laserOn = laserOn,
            projectId = projectId,
            groupId = groupId,
            objectId = objectId,
            deviceName = deviceName,
            blePacketHex = rawHex
        )
    }

    /** Типы измерений по devMode */
    enum class MeasurementType(val devMode: Int, val displayName: String) {
        DIRECT(1, "Измерение"),
        CONTINUOUS(2, "Непрерывный"),
        MIN_MAX(3, "Min/Max"),
        ADD_SUB(4, "Сложить/Вычесть"),
        AREA(5, "Площадь"),
        VOLUME(6, "Объём"),
        INDIRECT_HEIGHT(10, "Косвенная высота"),
        INDIRECT_LENGTH(11, "Косвенная длина"),
        DOUBLE_INDIRECT(12, "Двойная косвенная"),
        PARTIAL_AREA(13, "Частичная площадь"),
        STAKE_OUT(14, "Разбивка"),
        ANGLE(15, "Угол"),
        UNKNOWN(-1, "Неизвестный");

        companion object {
            fun fromDevMode(devMode: Int): MeasurementType =
                values().find { it.devMode == devMode } ?: UNKNOWN
        }
    }

    /** Точка отсчёта */
    enum class RefEdge(val value: Int, val displayName: String) {
        REAR(0, "Задняя грань"),
        TRIPOD(1, "Ось штатива"),
        FRONT(2, "Передняя грань"),
        PIN(3, "Pin");

        companion object {
            fun fromValue(value: Int): RefEdge =
                values().find { it.value == value } ?: REAR
        }
    }

    /**
     * Парсинг BLE-пакета в структурированные данные.
     * @param rawBytes сырые байты из BLE уведомления
     * @return ParsedMeasurement или null если пакет невалидный
     */
    fun parse(rawBytes: ByteArray): ParsedMeasurement? {
        if (rawBytes.size < 11) return null // Минимум: Header(3) + Result(4) + Status(4)

        // Валидация заголовка
        if (rawBytes[0].toInt() and 0xFF != 0xC0) return null // FrameMode != LONG
        if (rawBytes[1].toInt() and 0xFF != 0x55) return null // Command != EDC

        // Пропускаем heartbeat пакеты (PayloadLen == 2)
        val payloadLen = rawBytes[2].toInt() and 0xFF
        if (payloadLen == 2) return null // Heartbeat, не измерение

        // Разрешаем любые значения в байтах [2] и [6], главное чтобы были данные
        // Это нужно для разных прошивок рулетки

        // DevModeRef: [refEdge:2][devMode:6]
        val devModeRef = rawBytes[3].toInt() and 0xFF
        val refEdge = devModeRef and 0x03          // биты 0-1
        val devMode = (devModeRef shr 2) and 0x3F  // биты 2-7

        // ФИЛЬТР: Игнорируем пакеты статуса (devMode=0) и ответов на команды (devMode=60, devMode=62)
        // Они создают записи "Неизвестный 0,000 м"
        if (devMode == 0 || devMode == 60 || devMode == 62) return null

        // DevStatus: [laser:1][temp:1][batt:1][units:1][status:4]
        val devStatus = rawBytes[4].toInt() and 0xFF
        val laserOn = (devStatus and 0x01) != 0
        val tempWarning = (devStatus and 0x02) != 0
        val battLow = (devStatus and 0x04) != 0
        val isMetric = (devStatus and 0x08) == 0 // 0 = метрическая, 1 = имперская
        val deviceStatus = (devStatus shr 4) and 0x0F

        // SeqNum
        val seqNum = rawBytes[5].toInt() and 0xFF

        // Результат (float32 LE)
        val resultValue = bytesToFloat(rawBytes, 7)

        // Компоненты
        val comp1Value = bytesToFloat(rawBytes, 11)
        val comp2Value = bytesToFloat(rawBytes, 15) // угол в градусах

        // MeasID (опционально, если пакет достаточно длинный)
        val measId = if (rawBytes.size >= 21) {
            bytesToUShort(rawBytes, 19)
        } else null

        val rawHex = rawBytes.joinToString(" ") { "%02X".format(it) }

        return ParsedMeasurement(
            devMode = devMode,
            refEdge = refEdge,
            resultValue = resultValue.toDouble(),
            comp1Value = comp1Value.toDouble(),
            comp2Value = comp2Value.toDouble(),
            laserOn = laserOn,
            tempWarning = tempWarning,
            battLow = battLow,
            isMetric = isMetric,
            deviceStatus = deviceStatus,
            seqNum = seqNum,
            measId = measId,
            rawHex = rawHex
        )
    }

    /**
     * Парсинг с валидацией CRC8.
     * CRC8 вычисляется по байтам [0..payloadEnd] с poly=0xA6, init=0xAA.
     */
    fun parseWithCrc(rawBytes: ByteArray): ParsedMeasurement? {
        if (rawBytes.size < 20) return null // Минимум + CRC байт

        val crcIndex = rawBytes.size - 1
        val expectedCrc = rawBytes[crcIndex].toInt() and 0xFF
        val dataForCrc = rawBytes.copyOfRange(0, crcIndex)
        val calculatedCrc = calcCrc8(dataForCrc)

        if (calculatedCrc != expectedCrc) {
            android.util.Log.w("BlePacketParser", "CRC mismatch: expected=$expectedCrc, got=$calculatedCrc")
            return null
        }

        return parse(rawBytes.copyOfRange(0, crcIndex))
    }

    /**
     * Расчёт CRC8 по алгоритму из Crc.java (APK MeasureOn).
     * Полином: 0xA6, Init: 0xAA, побитовый XOR.
     */
    fun calcCrc8(data: ByteArray): Int {
        var crc = 0xAA // EN_CRC8_INITIAL_VALUE
        val poly = 0xA6

        for (byte in data) {
            for (i in 0 until 8) {
                val bit1 = (crc and 0x80) != 0
                val bit2 = ((byte.toInt() ushr (7 - i)) and 1) == 1
                crc = if (bit1 != bit2) {
                    ((crc shl 1) xor poly) and 0xFF
                } else {
                    (crc shl 1) and 0xFF
                }
            }
        }

        return crc
    }

    // --- Утилиты ---

    private fun bytesToFloat(bytes: ByteArray, offset: Int): Float {
        return ByteBuffer.wrap(bytes, offset, 4)
            .order(ByteOrder.LITTLE_ENDIAN)
            .float
    }

    private fun bytesToUShort(bytes: ByteArray, offset: Int): Int {
        return ByteBuffer.wrap(bytes, offset, 2)
            .order(ByteOrder.LITTLE_ENDIAN)
            .short.toInt() and 0xFFFF
    }

    /** Форматирование результата для отображения */
    fun formatResult(value: Double, type: MeasurementType): String {
        return when (type) {
            MeasurementType.AREA -> "%.2f м²".format(value)
            MeasurementType.VOLUME -> "%.2f м³".format(value)
            else -> "%.3f м".format(value)
        }
    }
}
