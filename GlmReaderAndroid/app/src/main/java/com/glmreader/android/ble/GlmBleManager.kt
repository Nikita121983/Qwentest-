package com.glmreader.android.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import com.glmreader.android.protocol.BlePacketParser
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

class GlmBleManager(context: Context) {

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter

    private var rfcommSocket: BluetoothSocket? = null
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null
    private var readThread: Thread? = null
    private val readBuffer = ByteArrayOutputStream()
    private var isReading = false
    private var rawBytesCount = 0

    var isScanning = false
        private set
    var isConnected = false
        private set
    var connectedDeviceName: String? = null
        private set
    var connectedDeviceMac: String? = null

    var onDeviceFound: ((String, String) -> Unit)? = null
    var onDataReceived: ((ByteArray) -> Unit)? = null
    var onParsedMeasurement: ((BlePacketParser.ParsedMeasurement) -> Unit)? = null
    var onConnectionStateChanged: ((Boolean) -> Unit)? = null

    private val scanCallbackLegacy = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
        val name = device.name ?: "Unknown"
        val mac = device.address
        if (name.contains("Bosch", ignoreCase = true) || name.contains("GLM", ignoreCase = true)) {
            Log.w("BLE_SCAN", ">>> BOSCH FOUND: $name ($mac)")
            onDeviceFound?.invoke(mac, name)
        }
    }

    @SuppressLint("MissingPermission", "Deprecated")
    fun startScan() {
        if (!bluetoothAdapter.isEnabled) {
            Log.w("BLE", "Bluetooth not enabled")
            return
        }
        if (isScanning) stopScan()

        isScanning = true
        try {
            bluetoothAdapter.startLeScan(scanCallbackLegacy)
            Log.d("BLE", "Scan started (Bosch filter active)")
        } catch (e: Exception) {
            Log.e("BLE", "Scan failed", e)
            isScanning = false
        }
    }

    @SuppressLint("Deprecated")
    fun stopScan() {
        if (!isScanning) return
        isScanning = false
        try {
            bluetoothAdapter.stopLeScan(scanCallbackLegacy)
        } catch (e: Exception) {
            Log.w("BLE", "Stop scan error", e)
        }
        Log.d("BLE", "Scan stopped")
    }

    @SuppressLint("MissingPermission")
    fun connect(macAddress: String) {
        Log.d("BLE", "=== Connecting to $macAddress (WITH BONDING) ===")
        stopScan()

        Thread {
            try {
                val device = bluetoothAdapter.getRemoteDevice(macAddress)

                // 1. Подключение сокета
                val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
                rfcommSocket = device.createRfcommSocketToServiceRecord(uuid)

                Log.d("BLE", "Connecting socket...")
                rfcommSocket?.connect()
                Log.d("BLE", "✅ RFCOMM Connected!")

                isConnected = true
                connectedDeviceName = device.name
                connectedDeviceMac = macAddress
                onConnectionStateChanged?.invoke(true)

                // 2. Принудительное сопряжение (Bonding)
                if (device.bondState != BluetoothDevice.BOND_BONDED) {
                    Log.d("BLE", "Not bonded. Creating bond...")
                    device.createBond()
                    // Ждем завершения сопряжения (до 10 сек)
                    var waitCount = 0
                    while (device.bondState != BluetoothDevice.BOND_BONDED && waitCount < 20) {
                        Thread.sleep(500)
                        waitCount++
                        Log.d("BLE", "Waiting for bond... state: ${device.bondState}")
                    }
                    if (device.bondState == BluetoothDevice.BOND_BONDED) {
                        Log.d("BLE", "✅ Bonding Successful!")
                    } else {
                        Log.w("BLE", "⚠️ Bonding failed or timed out. Trying anyway...")
                    }
                } else {
                    Log.d("BLE", "Already bonded.")
                }

                // 3. Инициализация потока данных
                inputStream = rfcommSocket?.inputStream
                outputStream = rfcommSocket?.outputStream

                Thread.sleep(500)
                sendInit()

                Thread.sleep(500)
                sendGetSettings() // Сразу запрашиваем данные

                // 4. Запуск чтения
                isReading = true
                readThread = Thread(::readLoop)
                readThread?.start()

            } catch (e: Exception) {
                Log.e("BLE", "❌ Connection failed: ${e.message}")
                isConnected = false
                onConnectionStateChanged?.invoke(false)
            }
        }.start()
    }

    // Делаем методы публичными для доступа из UI
    fun sendInit() {
        // Init / AutoSync ON
        sendCommand(byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x40.toByte(), 0x00.toByte()), "Init")
    }

    fun sendGetSettings() {
        // Get Settings / Request Data (как кнопка замера в старой версии)
        sendCommand(byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x3B.toByte(), 0x00.toByte()), "Get Settings/Data")
    }

    fun sendTrigger() {
        sendCommand(byteArrayOf(0xC0.toByte(), 0x56.toByte(), 0x01.toByte(), 0x00.toByte()), "Trigger")
    }

    fun sendSyncHistory(index: Int) {
        sendCommand(byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBA.toByte(), index.toByte()), "Sync History")
    }

    // Управление рулеткой
    fun setMeasurementType(mode: Int) {
        // C0 55 02 BC [mode] [CRC]
        sendCommand(byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBC.toByte(), mode.toByte()), "Set Mode $mode")
    }

    fun setReferencePoint(ref: Int) {
        // C0 55 02 BE [ref] [CRC]
        sendCommand(byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBE.toByte(), ref.toByte()), "Set Ref $ref")
    }

    fun sendRawCommand(payload: ByteArray, name: String) {
        sendCommand(payload, name)
    }

    private fun sendCommand(payload: ByteArray, name: String) {
        try {
            val crc = BlePacketParser.calcCrc8(payload)
            val full = payload + crc.toByte()

            outputStream?.write(full)
            outputStream?.flush()
            Log.d("BLE", "Sent $name: ${full.joinToString(" ") { "%02X".format(it) }}")
        } catch (e: Exception) {
            Log.e("BLE", "❌ Failed to send $name", e)
        }
    }

    private fun readLoop() {
        val tempBuf = ByteArray(1024)
        try {
            Log.d("BLE", "Reading thread started")
            while (isReading && isConnected && inputStream != null) {
                val count = inputStream?.read(tempBuf) ?: -1
                if (count > 0) {
                    rawBytesCount += count
                    val chunk = tempBuf.copyOf(count)
                    readBuffer.write(chunk)

                    Log.d("BLE_RAW", "Received: $rawBytesCount bytes. Chunk: ${chunk.joinToString(" ") { "%02X".format(it) }}")

                    processBuffer()
                } else if (count == -1) {
                    Log.w("BLE", "Stream closed")
                    break
                }
            }
        } catch (e: Exception) {
            Log.e("BLE", "Read error: ${e.message}")
        } finally {
            if (isConnected) disconnect()
        }
    }

    private fun processBuffer() {
        val data = readBuffer.toByteArray()
        if (data.size < 4) return

        var startIdx = -1
        for (i in 0 until data.size - 1) {
            if (data[i] == 0xC0.toByte() && data[i+1] == 0x55.toByte()) {
                startIdx = i
                break
            }
        }

        if (startIdx == -1) {
            if (readBuffer.size() > 2048) readBuffer.reset()
            return
        }

        if (startIdx > 0) {
            val validData = data.copyOfRange(startIdx, data.size)
            readBuffer.reset()
            readBuffer.write(validData)
        }

        val packetData = readBuffer.toByteArray()
        if (packetData.size >= 4) {
            val payloadLen = packetData[2].toInt() and 0xFF
            val expectedSize = 3 + payloadLen + 1

            if (packetData.size >= expectedSize) {
                val fullPacket = packetData.copyOf(expectedSize)
                readBuffer.reset()
                if (packetData.size > expectedSize) {
                    readBuffer.write(packetData.copyOfRange(expectedSize, packetData.size))
                }

                onDataReceived?.invoke(fullPacket)

                val parsed = BlePacketParser.parse(fullPacket)
                if (parsed != null) {
                    Log.d("BLE", "✅ Parsed: ${parsed.devMode}, ${parsed.resultValue}m, ${parsed.comp2Value}°")
                    onParsedMeasurement?.invoke(parsed)
                }
            }
        }
    }

    fun disconnect() {
        Log.d("BLE", "Disconnecting...")
        val wasConnected = isConnected
        isConnected = false
        isReading = false

        try {
            readThread?.interrupt()
            rfcommSocket?.close()
            inputStream?.close()
            readThread?.join(1000)
        } catch (e: Exception) {}

        readBuffer.reset()
        rfcommSocket = null
        inputStream = null
        outputStream = null

        if (wasConnected) onConnectionStateChanged?.invoke(false)
        Log.d("BLE", "Disconnected")
    }
}
