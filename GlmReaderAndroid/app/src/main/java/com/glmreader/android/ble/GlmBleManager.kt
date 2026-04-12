package com.glmreader.android.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import com.glmreader.android.protocol.BlePacketParser
import java.util.concurrent.LinkedBlockingDeque

/**
 * GlmBleManager — протокольный менеджер Bosch GLM.
 * Использует BleConnectionManager для транспорта (RFCOMM).
 *
 * Архитектура:
 * - BleConnectionManager: сокеты, чтение/запись, буфер (потоко-безопасный)
 * - GlmBleManager: state machine, очередь команд, CRC, парсинг
 *
 * State machine (как в MM/MO):
 * SLAVE_LISTENING → MASTER_READY → MASTER_SENDING → MASTER_RECEIVING → MASTER_READY
 */
class GlmBleManager(context: Context) {

    // Транспорт
    private val connectionManager = BleConnectionManager(context)
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as android.bluetooth.BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter

    // Очередь команд (как в MO/MM SendThread)
    private val commandQueue = LinkedBlockingDeque<QueuedCommand>()
    private var sendThread: Thread? = null

    // State machine — СИНХРОНИЗИРОВАНО через stateLock
    enum class ProtocolState {
        SLAVE_LISTENING,    // Ожидание данных от рулетки
        MASTER_READY,       // Готовы отправлять команду
        MASTER_SENDING,     // Отправляем команду
        MASTER_RECEIVING    // Ждём ответ
    }

    private val stateLock = Object()  // Монитор для синхронизации
    @Volatile private var protocolState = ProtocolState.SLAVE_LISTENING

    // Timeout timer
    @Volatile private var timeoutPending = false
    private val commandTimeoutMs = 500L

    // Connection retry
    private var connectAttempt = 0

    // Command data class
    private data class QueuedCommand(val payload: ByteArray, val name: String)

    // Callbacks
    var onDeviceFound: ((String, String) -> Unit)? = null
    var onDataReceived: ((ByteArray) -> Unit)? = null
    var onParsedMeasurement: ((BlePacketParser.ParsedMeasurement) -> Unit)? = null
    var onConnectionStateChanged: ((Boolean) -> Unit)? = null

    // Debug callbacks
    var onStateChange: ((ProtocolState) -> Unit)? = null
    var onRawTx: ((String, String) -> Unit)? = null // direction, hex
    var onRawChunk: ((ByteArray, Int) -> Unit)? = null // chunk, total

    // Scanning
    var isScanning = false
        private set
    val isConnected: Boolean get() = connectionManager.isConnected
    val connectedDeviceName: String? get() = connectionManager.connectedDeviceName
    val connectedDeviceMac: String? get() = connectionManager.connectedDeviceMac
    val protocolStateName: String get() = protocolState.name
    val queueSize: Int get() = commandQueue.size

    // Legacy scan callback
    private val scanCallbackLegacy = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
        val name = device.name ?: "Unknown"
        val mac = device.address
        if (name.contains("Bosch", ignoreCase = true) || name.contains("GLM", ignoreCase = true)) {
            Log.w("BLE_SCAN", ">>> BOSCH FOUND: $name ($mac)")
            onDeviceFound?.invoke(mac, name)
        }
    }

    init {
        // Подписываемся на события транспорта
        connectionManager.onConnected = { onTransportConnected() }
        connectionManager.onDisconnected = { reason -> onTransportDisconnected(reason) }
        connectionManager.onDataReceived = { packet -> onPacketReceived(packet) }
        connectionManager.onRawChunk = { chunk, total ->
            onRawChunk?.invoke(chunk, total)
        }
    }

    // ==================== SCANNING ====================

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

    // ==================== CONNECTION ====================

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

        // Подписываемся на события connectionManager
        connectionManager.connect(macAddress)
    }

    /** Транспорт подключился — начинаем инициализацию протокола */
    private fun onTransportConnected() {
        Log.d("BLE", "✅ Transport connected!")
        onConnectionStateChanged?.invoke(true)

        // State: SLAVE_LISTENING
        transitionTo(ProtocolState.SLAVE_LISTENING)

        // Пауза чтобы рулетка успела перейти в SLAVE_LISTENING
        Thread.sleep(200)

        // Init — AutoSync ON (как turnAutoSyncOn() в MO/MM)
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x40.toByte(), 0x00.toByte()),
            "Init"
        )

        // Пауза между командами (как Thread.sleep(50L) в MM)
        Thread.sleep(100)

        // Get Settings
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x3B.toByte(), 0x00.toByte()),
            "Get Settings"
        )
    }

    private fun onTransportDisconnected(reason: String?) {
        Log.d("BLE", "Transport disconnected: ${reason ?: "normal"}")
        onConnectionStateChanged?.invoke(false)
        commandQueue.clear()
        timeoutPending = false
        sendThread = null
        transitionTo(ProtocolState.SLAVE_LISTENING)
    }

    // ==================== PACKET RECEIVED ====================

    /** Пришёл полный пакет от BleConnectionManager (уже с CRC) */
    private fun onPacketReceived(fullPacket: ByteArray) {
        val hex = fullPacket.joinToString(" ") { "%02X".format(it) }
        onRawTx?.invoke("RX", hex)

        // CRC валидация (Шаг 4)
        if (fullPacket.size < 2) {
            Log.w("BLE", "Packet too short: ${fullPacket.size} bytes")
            return
        }

        val receivedCrc = fullPacket.last().toInt() and 0xFF
        val dataForCrc = fullPacket.copyOf(fullPacket.size - 1)
        val calculatedCrc = BlePacketParser.calcCrc8(dataForCrc)

        if (receivedCrc != calculatedCrc) {
            Log.w("BLE", "⚠️ CRC mismatch: expected=$receivedCrc, got=$calculatedCrc, packet discarded")
            return
        }

        // Heartbeat или измерение?
        if (isHeartbeatPacket(fullPacket)) {
            handleHeartbeat(fullPacket)
            // Heartbeat — это keep-alive, НЕ переключает state!
            // Только если мы в MASTER_RECEIVING — значит это ответ на команду
            if (protocolState == ProtocolState.MASTER_RECEIVING) {
                transitionTo(ProtocolState.MASTER_READY)
            }
        } else {
            // Измерение
            onDataReceived?.invoke(fullPacket)

            val parsed = BlePacketParser.parseWithCrc(fullPacket)
            if (parsed != null) {
                Log.d("BLE", "✅ Parsed: devMode=${parsed.devMode}, ${parsed.resultValue}m, ${parsed.comp2Value}°")
                onParsedMeasurement?.invoke(parsed)
            } else {
                Log.w("BLE", "⚠️ Parse returned null (likely heartbeat or status packet)")
            }

            // Пришёл ответ на команду — разрешаем следующую
            if (protocolState == ProtocolState.MASTER_RECEIVING) {
                transitionTo(ProtocolState.MASTER_READY)
            }
        }
    }

    // ==================== STATE MACHINE ====================

    /**
     * Переход в новое состояние — СИНХРОНИЗИРОВАНО.
     * Вызывает notifyAll() чтобы разбудить ждущие потоки.
     */
    private fun transitionTo(newState: ProtocolState) {
        synchronized(stateLock) {
            val oldState = protocolState
            protocolState = newState
            Log.d("BLE_STATE", "$oldState → $newState")
            onStateChange?.invoke(newState)
            stateLock.notifyAll()  // Разбудить SendThread
        }
    }

    /**
     * Ждать определённое состояние — СИНХРОНИЗИРОВАНО.
     * Использует wait() с timeout вместо polling.
     */
    private fun waitForState(targetState: ProtocolState, timeoutMs: Long): Boolean {
        synchronized(stateLock) {
            val endTime = System.currentTimeMillis() + timeoutMs
            while (protocolState != targetState) {
                val remaining = endTime - System.currentTimeMillis()
                if (remaining <= 0) {
                    Log.w("BLE", "⏱️ waitForState timeout: wanted $targetState, got $protocolState")
                    return false
                }
                try {
                    stateLock.wait(remaining)
                } catch (e: InterruptedException) {
                    return false
                }
            }
            return true
        }
    }

    // ==================== COMMAND QUEUE ====================

    /** Поставить команду в очередь */
    fun enqueueCommand(payload: ByteArray, name: String) {
        if (!isConnected) {
            Log.w("BLE", "Not connected, command dropped: $name")
            return
        }
        commandQueue.offerLast(QueuedCommand(payload, name))
        Log.d("BLE", "📋 Queued: $name (queue size: ${commandQueue.size})")

        // Если SendThread ещё не запущен — запускаем
        if (sendThread?.isAlive != true) {
            startSendThread()
        }
    }

    /**
     * SendThread — вытаскивает команды из очереди и отправляет по одной.
     * Ждёт MASTER_READY через wait()/notifyAll().
     */
    private fun startSendThread() {
        sendThread = Thread {
            Log.d("BLE", "SendThread started")
            while (!Thread.interrupted() && isConnected) {
                try {
                    val cmd = commandQueue.pollFirst() ?: break  // Очередь пуста — выходим

                    // Ждём MASTER_READY через wait()/notifyAll() (не polling!)
                    val ready = waitForState(ProtocolState.MASTER_READY, 2000)
                    if (!ready || !isConnected) {
                        Log.w("BLE", "⚠️ SendThread: not ready or disconnected, dropping: ${cmd.name}")
                        break
                    }

                    // Переходим в состояние отправки
                    transitionTo(ProtocolState.MASTER_SENDING)

                    // Вычисляем CRC и отправляем
                    val crc = BlePacketParser.calcCrc8(cmd.payload)
                    val fullPacket = cmd.payload + crc.toByte()
                    val hex = fullPacket.joinToString(" ") { "%02X".format(it) }

                    val writeOk = connectionManager.write(fullPacket)
                    onRawTx?.invoke("TX", hex)

                    if (!writeOk) {
                        Log.e("BLE", "❌ Write failed for ${cmd.name}")
                        transitionTo(ProtocolState.MASTER_READY)
                        Thread.sleep(200)
                        continue
                    }

                    // Ждём ответ
                    transitionTo(ProtocolState.MASTER_RECEIVING)
                    val responseReceived = waitForState(ProtocolState.MASTER_READY, commandTimeoutMs * 2)

                    if (!responseReceived) {
                        Log.w("BLE", "⏱️ Command timeout: ${cmd.name} (no response in ${commandTimeoutMs * 2}ms)")
                        // Продолжаем — может рулетка занята
                        transitionTo(ProtocolState.MASTER_READY)
                    } else {
                        Log.d("BLE", "✅ Response received for ${cmd.name}")
                    }

                } catch (e: Exception) {
                    Log.e("BLE", "❌ SendThread error: ${e.message}")
                    transitionTo(ProtocolState.MASTER_READY)
                    Thread.sleep(200)
                }
            }
            Log.d("BLE", "SendThread stopped")
        }.apply { start() }
    }

    // ==================== PUBLIC COMMANDS ====================

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

    // ==================== HEARTBEAT ====================

    private fun isHeartbeatPacket(data: ByteArray): Boolean {
        if (data.size < 5) return false
        // C0 55 02 F1 ... (PayloadLen=2, DevModeRef=0xF1)
        return data[0] == 0xC0.toByte() &&
               data[1] == 0x55.toByte() &&
               data[2] == 0x02.toByte() &&
               (data[3].toInt() and 0xFF) == 0xF1
    }

    private fun handleHeartbeat(data: ByteArray) {
        if (data.size >= 6) {
            val devStatus = data[4].toInt() and 0xFF
            val angle = data[5].toInt() and 0xFF
            Log.d("BLE", "💓 Heartbeat: devStatus=$devStatus, angle=$angle")
        } else {
            Log.d("BLE", "💓 Heartbeat received (short)")
        }
    }

    // ==================== DISCONNECT ====================

    fun disconnect() {
        Log.d("BLE", "Disconnecting...")
        connectionManager.disconnect()
    }

    // ==================== DEBUG INFO ====================

    fun getDebugInfo(): String {
        return "State: $protocolState | Queue: ${commandQueue.size} | " +
               connectionManager.getStatusInfo()
    }
}
