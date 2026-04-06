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
import java.util.concurrent.LinkedBlockingDeque

class GlmBleManager(context: Context) {

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter

    // RFCOMM
    private var rfcommSocket: BluetoothSocket? = null
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    // Read loop
    private var readThread: Thread? = null
    private val readBuffer = ByteArrayOutputStream()
    private var isReading = false
    private var rawBytesCount = 0

    // SendThread — очередь команд (как в MO/MM)
    private var sendThread: Thread? = null
    private val commandQueue = LinkedBlockingDeque<QueuedCommand>()
    @Volatile private var isReadyToSend = false  // Аналог MASTER_READY
    @Volatile private var isSending = false      // Аналог SENDING

    // Timeout timer (500ms как в MO/MM)
    private var timeoutTimer: Thread? = null
    private val commandTimeoutMs = 500L

    // Connection retry
    private var connectAttempt = 0

    // SPP UUID (стандартный, как в MM)
    private val SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")

    // Command data class
    private data class QueuedCommand(val payload: ByteArray, val name: String)

    // State machine states (упрощённая версия из MO)
    enum class ProtocolState {
        SLAVE_LISTENING,    // Ожидание данных от рулетки
        MASTER_READY,       // Готовы отправлять команду
        MASTER_SENDING,     // Отправляем команду
        MASTER_RECEIVING    // Ждём ответ
    }
    @Volatile private var protocolState = ProtocolState.SLAVE_LISTENING

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
        Log.d("BLE", "=== Connecting to $macAddress (INSECURE RFCOMM, no bonding) ===")
        stopScan()
        connectAttempt = 0
        connectToDevice(macAddress)
    }

    private fun connectToDevice(macAddress: String) {
        connectAttempt++
        val maxAttempts = 3
        Log.d("BLE", "Connection attempt $connectAttempt/$maxAttempts")

        Thread {
            try {
                val device = bluetoothAdapter.getRemoteDevice(macAddress)

                // 1. Insecure RFCOMM socket (как Measuring Master) — БЕЗ bonding
                rfcommSocket = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)

                Log.d("BLE", "Connecting insecure socket...")
                rfcommSocket?.connect()
                Log.d("BLE", "✅ RFCOMM Connected!")

                isConnected = true
                connectedDeviceName = device.name
                connectedDeviceMac = macAddress
                onConnectionStateChanged?.invoke(true)

                // 2. Инициализация потоков
                inputStream = rfcommSocket?.inputStream
                outputStream = rfcommSocket?.outputStream

                // 3. Запуск чтения ДО отправки команд
                isReading = true
                readThread = Thread(::readLoop)
                readThread?.start()

                // Рулетка в SLAVE_LISTENING, мы тоже готовы принимать
                protocolState = ProtocolState.SLAVE_LISTENING
                isReadyToSend = true

                // Небольшая пауза чтобы рулетка успела перейти в SLAVE_LISTENING
                Thread.sleep(200)

                // 4. Init — AutoSync ON (как turnAutoSyncOn() в MO/MM)
                enqueueCommand(
                    byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x40.toByte(), 0x00.toByte()),
                    "Init"
                )

                // Пауза между командами (как Thread.sleep(50L) в MM)
                Thread.sleep(100)

                // 5. Get Settings
                enqueueCommand(
                    byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x3B.toByte(), 0x00.toByte()),
                    "Get Settings"
                )

            } catch (e: Exception) {
                Log.e("BLE", "❌ Connection attempt $connectAttempt failed: ${e.message}")

                if (connectAttempt < maxAttempts) {
                    val backoff = connectAttempt * 1000L
                    Log.d("BLE", "Retrying in ${backoff}ms...")
                    Thread.sleep(backoff)
                    connectToDevice(macAddress)
                } else {
                    Log.e("BLE", "❌ All $maxAttempts connection attempts failed")
                    isConnected = false
                    onConnectionStateChanged?.invoke(false)
                }
            }
        }.start()
    }

    // Делаем методы публичными для доступа из UI
    fun sendInit() {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x40.toByte(), 0x00.toByte()),
            "Init"
        )
    }

    fun sendGetSettings() {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x3B.toByte(), 0x00.toByte()),
            "Get Settings"
        )
    }

    fun sendTrigger() {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x56.toByte(), 0x01.toByte(), 0x00.toByte()),
            "Trigger"
        )
    }

    fun sendSyncHistory(index: Int) {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBA.toByte(), index.toByte()),
            "Sync History $index"
        )
    }

    // Управление рулеткой
    fun setMeasurementType(mode: Int) {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBC.toByte(), mode.toByte()),
            "Set Mode $mode"
        )
    }

    fun setReferencePoint(ref: Int) {
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0xBE.toByte(), ref.toByte()),
            "Set Ref $ref"
        )
    }

    fun sendRawCommand(payload: ByteArray, name: String) {
        enqueueCommand(payload, name)
    }

    /**
     * Поставить команду в очередь.
     * SendThread заберёт её когда рулетка будет готова.
     */
    private fun enqueueCommand(payload: ByteArray, name: String) {
        if (!isConnected) {
            Log.w("BLE", "Not connected, command dropped: $name")
            return
        }
        commandQueue.offerLast(QueuedCommand(payload, name))
        // Если SendThread ещё не запущен — запускаем
        if (sendThread?.isAlive != true) {
            startSendThread()
        }
    }

    /**
     * SendThread — вытаскивает команды из очереди и отправляет по одной.
     * Аналог SendThread из MtProtocolBLEImpl (MO) и MtProtocolImpl (MM).
     */
    private fun startSendThread() {
        sendThread = Thread {
            Log.d("BLE", "SendThread started")
            while (!Thread.interrupted() && isConnected) {
                try {
                    val cmd = commandQueue.pollFirst() ?: break  // Очередь пуста — выходим

                    // Ждём пока рулетка готова (STATE_MASTER_READY)
                    while (!isReadyToSend && isConnected && !Thread.interrupted()) {
                        Thread.sleep(10)
                    }
                    if (!isConnected) break

                    // Переходим в состояние отправки
                    isReadyToSend = false
                    isSending = true
                    protocolState = ProtocolState.MASTER_SENDING

                    // Вычисляем CRC и отправляем
                    val crc = BlePacketParser.calcCrc8(cmd.payload)
                    val fullPacket = cmd.payload + crc.toByte()

                    outputStream?.write(fullPacket)
                    outputStream?.flush()
                    Log.d("BLE", "📤 Sent ${cmd.name}: ${fullPacket.joinToString(" ") { "%02X".format(it) }}")

                    // Запускаем таймер ожидания ответа
                    startTimeoutTimer()

                    // Переходим в ожидание ответа
                    isSending = false
                    protocolState = ProtocolState.MASTER_RECEIVING

                    // Ждём пока придёт ответ или timeout
                    val waitStart = System.currentTimeMillis()
                    while (protocolState == ProtocolState.MASTER_RECEIVING &&
                           isConnected &&
                           System.currentTimeMillis() - waitStart < commandTimeoutMs * 2) {
                        Thread.sleep(10)
                    }

                    stopTimeoutTimer()
                    isReadyToSend = true
                    protocolState = ProtocolState.SLAVE_LISTENING

                } catch (e: Exception) {
                    Log.e("BLE", "❌ SendThread error: ${e.message}")
                    isReadyToSend = true
                    protocolState = ProtocolState.SLAVE_LISTENING
                    Thread.sleep(200)  // Пауза перед следующей попыткой (как в MO)
                }
            }
            Log.d("BLE", "SendThread stopped")
        }.apply { start() }
    }

    /**
     * Таймер команд (500ms как в MO/MM).
     * Если ответ не пришёл — считаем timeout и продолжаем.
     */
    private fun startTimeoutTimer() {
        stopTimeoutTimer()
        timeoutTimer = Thread {
            try {
                Thread.sleep(commandTimeoutMs)
                Log.w("BLE", "⏱️ Command timeout (${commandTimeoutMs}ms)")
            } catch (e: InterruptedException) {
                // Timer was interrupted (e.g. on disconnect), ignore
            }
        }.apply { start() }
    }

    private fun stopTimeoutTimer() {
        timeoutTimer?.interrupt()
        timeoutTimer = null
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

                // Проверяем: это heartbeat или измерение?
                if (isHeartbeatPacket(fullPacket)) {
                    handleHeartbeat(fullPacket)
                    // Heartbeat — это ответ рулетки, сигнализируем что готовы к следующей команде
                    if (protocolState == ProtocolState.MASTER_RECEIVING) {
                        protocolState = ProtocolState.MASTER_READY
                        isReadyToSend = true
                    }
                } else {
                    // Обычное измерение
                    onDataReceived?.invoke(fullPacket)

                    val parsed = BlePacketParser.parse(fullPacket)
                    if (parsed != null) {
                        Log.d("BLE", "✅ Parsed: devMode=${parsed.devMode}, ${parsed.resultValue}m, ${parsed.comp2Value}°")
                        onParsedMeasurement?.invoke(parsed)
                    }

                    // Пришёл ответ — сигнализируем что можно слать следующую команду
                    if (protocolState == ProtocolState.MASTER_RECEIVING) {
                        protocolState = ProtocolState.MASTER_READY
                        isReadyToSend = true
                        stopTimeoutTimer()
                    }
                }
            }
        }
    }

    /**
     * Проверка: это heartbeat пакет (C0 55 02 F1)?
     * Heartbeat рулетка шлёт периодически — DevModeRef=0xF1 = devMode=60, syncCtrl=1
     */
    private fun isHeartbeatPacket(data: ByteArray): Boolean {
        if (data.size < 5) return false
        // C0 55 02 F1 ...
        return data[0] == 0xC0.toByte() &&
               data[1] == 0x55.toByte() &&
               data[2] == 0x02.toByte() &&
               (data[3].toInt() and 0xFF) == 0xF1
    }

    /**
     * Обработка heartbeat пакета.
     * Формат: C0 55 02 F1 [devStatus] [angle] [CRC]
     * devStatus — режим рулетки, angle — текущий угол
     */
    private fun handleHeartbeat(data: ByteArray) {
        if (data.size >= 6) {
            val devStatus = data[4].toInt() and 0xFF
            val angle = data[5].toInt() and 0xFF
            Log.d("BLE", "💓 Heartbeat: devStatus=$devStatus, angle=$angle")
        } else {
            Log.d("BLE", "💓 Heartbeat received (short)")
        }
    }

    fun disconnect() {
        Log.d("BLE", "Disconnecting...")
        val wasConnected = isConnected
        isConnected = false
        isReading = false

        // Останавливаем SendThread
        sendThread?.interrupt()
        commandQueue.clear()

        // Останавливаем таймер
        stopTimeoutTimer()

        try {
            readThread?.interrupt()
            rfcommSocket?.close()
            inputStream?.close()
            readThread?.join(1000)
            sendThread?.join(500)
        } catch (e: Exception) {}

        readBuffer.reset()
        rfcommSocket = null
        inputStream = null
        outputStream = null
        sendThread = null
        readThread = null

        // Сбрасываем состояние
        protocolState = ProtocolState.SLAVE_LISTENING
        isReadyToSend = false
        isSending = false

        if (wasConnected) onConnectionStateChanged?.invoke(false)
        Log.d("BLE", "Disconnected")
    }
}
