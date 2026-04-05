package com.glmreader.android.protocol

import org.junit.Assert.*
import org.junit.Test

class BlePacketParserTest {

    @Test
    fun `parse direct measurement`() {
        // C0 55 10 06 08 01 71 [result 4 bytes] [comp1 4 bytes] [comp2 4 bytes]
        // devModeRef=0x06 (devMode=1 Direct, refEdge=0 Rear)
        // result=1.5m, comp1=0, comp2=2.3°
        val packet = byteArrayOf(
            0xC0.toByte(), 0x55.toByte(), 0x10.toByte(), 0x06.toByte(), // header
            0x08.toByte(), 0x01.toByte(), 0x71.toByte(), // status, seq, const
            0x00.toByte(), 0x00.toByte(), 0xC0.toByte(), 0x3F.toByte(), // result=1.5f LE
            0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), // comp1=0
            0x33.toByte(), 0x33.toByte(), 0x13.toByte(), 0x40.toByte()  // comp2=2.3f LE
        )

        val result = BlePacketParser.parse(packet)

        assertNotNull(result)
        assertEquals(1, result!!.devMode)
        assertEquals(0, result.refEdge)
        assertEquals(1.5, result.resultValue, 0.01)
        assertEquals(2.3, result.comp2Value, 0.1)
        assertFalse(result.laserOn)
    }

    @Test
    fun `calcCrc8 matches spec examples`() {
        // [C0, 55, 02, 01, 00] → CRC = 0x1A
        val data = byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x01.toByte(), 0x00.toByte())
        val crc = BlePacketParser.calcCrc8(data)
        assertEquals(0x1A, crc)
    }

    @Test
    fun `parse returns null for short packet`() {
        val shortPacket = byteArrayOf(0xC0.toByte(), 0x55.toByte())
        assertNull(BlePacketParser.parse(shortPacket))
    }

    @Test
    fun `parse returns null for invalid header`() {
        val badPacket = byteArrayOf(
            0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte(),
            0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte(),
            0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
            0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
            0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()
        )
        assertNull(BlePacketParser.parse(badPacket))
    }
}
