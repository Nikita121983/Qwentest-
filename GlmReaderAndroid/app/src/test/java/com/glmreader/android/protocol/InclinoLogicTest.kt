package com.glmreader.android.protocol

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.abs

class InclinoLogicTest {

    @Test
    fun `direct measurement when angle within threshold`() {
        val result = InclinoLogic.determineType(angleDeg = 2.3, comp1Value = 1.5)
        assertEquals(InclinoLogic.CalculationType.DIRECT, result.calculationType)
        assertNull(result.calculatedValue)
        assertTrue(result.isAutoDetected)
    }

    @Test
    fun `height calculation when angle positive above threshold`() {
        val result = InclinoLogic.determineType(angleDeg = 30.0, comp1Value = 2.0)
        assertEquals(InclinoLogic.CalculationType.HEIGHT, result.calculationType)
        assertNotNull(result.calculatedValue)
        // 2.0 * sin(30°) = 1.0
        assertEquals(1.0, result.calculatedValue!!, 0.01)
    }

    @Test
    fun `width calculation when angle negative below threshold`() {
        val result = InclinoLogic.determineType(angleDeg = -30.0, comp1Value = 2.0)
        assertEquals(InclinoLogic.CalculationType.WIDTH, result.calculationType)
        assertNotNull(result.calculatedValue)
        // 2.0 * cos(30°) = 1.732
        assertEquals(1.732, result.calculatedValue!!, 0.01)
    }

    @Test
    fun `double height calculation`() {
        val result = InclinoLogic.calculateDouble(
            a1 = 1.0, b1 = 30.0,
            a2 = 1.0, b2 = 45.0,
            isHeight = true
        )
        // 1.0*sin(30) + 1.0*sin(45) = 0.5 + 0.707 = 1.207
        assertEquals(1.207, result, 0.01)
    }

    @Test
    fun `custom threshold works`() {
        val result = InclinoLogic.determineType(angleDeg = 8.0, comp1Value = 1.0, threshold = 10.0)
        assertEquals(InclinoLogic.CalculationType.DIRECT, result.calculationType)
    }

    @Test
    fun `getMarkerIcon returns correct icons`() {
        assertEquals("\uD83D\uDCCF", InclinoLogic.getMarkerIcon(BlePacketParser.MeasurementType.DIRECT))
        assertEquals("\uD83D\uDCC8", InclinoLogic.getMarkerIcon(BlePacketParser.MeasurementType.INDIRECT_HEIGHT))
        assertEquals("\uD83D\uDCC9", InclinoLogic.getMarkerIcon(BlePacketParser.MeasurementType.INDIRECT_LENGTH))
    }

    @Test
    fun `calculation description for height`() {
        val desc = InclinoLogic.getCalculationDescription(
            InclinoLogic.CalculationType.HEIGHT,
            a = 1.8504, angle = 51.0, result = 1.4383
        )
        assertTrue(desc.contains("sin"))
    }
}
