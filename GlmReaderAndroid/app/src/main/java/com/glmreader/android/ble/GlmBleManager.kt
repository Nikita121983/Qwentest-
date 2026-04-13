package com.glmreader.android.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import com.glmreader.android.protocol.BlePacketParser
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.LinkedBlockingDeque

/**
 * GlmBleManager — протокольный менеджер Bosch GLM.
 * Использует BleConnectionManager для транспорта (RFCOMM).
 *
 * Архитектура:
 * - BleConnectionManager: сокеты, чтение/запись, буфер (потоко-безопасный)
 * - GlmBleManager: state machine, очередь команд, CRC, парсинг
 * - BroadcastReceiver: реакция на системные события BT (как в MM)
 *
 * Observer pattern (как в MM/MO) — подписчики НЕ затирают друг друга.
 */
class GlmBleManager(private val context: Context) {

    // Транспорт
    private val connectionManager = BleConnectionManager(context)
    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as android.bluetooth.BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter

    // BroadcastReceiver — как в MM BTDeviceManagerImpl (реакция на системные события BT)
    private val btReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    Log.d("BLE_BT", "ACL_DISCONNECTED: ${device?.address}")
                    // Если отключилось наше устройство — перезапускаем автоподключение
                    if (device?.address == lastKnownDeviceMac) {
                        Log.d("BLE_BT", "Our device disconnected, will reconnect in 2s")
                    }
                }
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    Log.d("BLE_BT", "Bluetooth state changed: $state")
                    if (state == BluetoothAdapter.STATE_ON && autoConnectRunning && lastKnownDeviceMac != null) {
                        Log.d("BLE_BT", "Bluetooth turned ON, restarting auto-connect")
                        startAutoConnect(lastKnownDeviceMac!!, lastKnownDeviceName)
                    }
                }
                BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    val bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR)
                    // Как в MM: если спаривание завершено — автоматически подключаемся
                    if (bondState == BluetoothDevice.BOND_BONDED && device?.address == lastKnownDeviceMac) {
                        Log.d("BLE_BT", "Bonded to our device, connecting...")
                        device?.address?.let { connect(it) }
                    }
                }
            }
        }
    }
    private var btReceiverRegistered = false

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

    // Command data class
    private data class QueuedCommand(val payload: ByteArray, val name: String)

    // AutoConnectThread — как в MM/MO (каждые 2 сек попытка подключения)
    private var autoConnectThread: Thread? = null
    @Volatile private var autoConnectRunning = false
    private val autoConnectDelayMs = 2000L
    var lastKnownDeviceMac: String? = null
        private set
    var lastKnownDeviceName: String? = null
        private set

    // ==================== OBSERVERS (не затираются!) ====================

    private val deviceFoundListeners = CopyOnWriteArrayList<(String, String) -> Unit>()
    private val dataReceivedListeners = CopyOnWriteArrayList<(ByteArray) -> Unit>()
    private val parsedMeasurementListeners = CopyOnWriteArrayList<(BlePacketParser.ParsedMeasurement) -> Unit>()
    private val connectionStateListeners = CopyOnWriteArrayList<(Boolean) -> Unit>()
    private val stateChangeListeners = CopyOnWriteArrayList<(ProtocolState) -> Unit>()
    private val rawTxListeners = CopyOnWriteArrayList<(String, String) -> Unit>()
    private val rawChunkListeners = CopyOnWriteArrayList<(ByteArray, Int) -> Unit>()

    /**
     * Зарегистрировать BroadcastReceiver для системных событий BT.
     * Вызывается автоматически в init блоке.
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun registerBtReceiver() {
        if (btReceiverRegistered) return
        try {
            val filter = IntentFilter().apply {
                addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
                addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
                addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
                addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
            }
            val appContext = context.applicationContext ?: context
            // Для Android 13+ нужно явно указывать флаг экспорта для системных бродкастов
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                appContext.registerReceiver(btReceiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                appContext.registerReceiver(btReceiver, filter)
            }
            btReceiverRegistered = true
            Log.d("BLE_BT", "BroadcastReceiver registered")
        } catch (e: Exception) {
            Log.e("BLE_BT", "Failed to register BroadcastReceiver: ${e.message}")
        }
    }

    /**
     * От unregister BroadcastReceiver.
     * Вызывать из Activity.onPause() или Application.onTerminate().
     */
    fun unregisterBtReceiver() {
        if (!btReceiverRegistered) return
        try {
            context.unregisterReceiver(btReceiver)
            btReceiverRegistered = false
            Log.d("BLE_BT", "BroadcastReceiver unregistered")
        } catch (e: Exception) {
            Log.e("BLE_BT", "Failed to unregister BroadcastReceiver: ${e.message}")
        }
    }

    // Состояние рулетки (обновляется из ответов devMode=60, 62)
    @Volatile var currentRefEdge = 0
        private set
    @Volatile var currentMeasurementType = 0
        private set

    // Callbacks для UI обратной связи
    var onRefEdgeChanged: ((Int) -> Unit)? = null
    var onMeasurementTypeChanged: ((Int) -> Unit)? = null

    // Legacy API для обратной совместимости
    // ВАЖНО: НЕ затираем observers — deprecated, но не ломающий
    @Deprecated("Use observeConnectionState()", level = DeprecationLevel.WARNING)
    var onConnectionStateChanged: ((Boolean) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) connectionStateListeners.add(value)
        }

    @Deprecated("Use observeDataReceived()", level = DeprecationLevel.WARNING)
    var onDataReceived: ((ByteArray) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) dataReceivedListeners.add(value)
        }

    @Deprecated("Use observeParsedMeasurement()", level = DeprecationLevel.WARNING)
    var onParsedMeasurement: ((BlePacketParser.ParsedMeasurement) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) parsedMeasurementListeners.add(value)
        }

    @Deprecated("Use observeStateChange()", level = DeprecationLevel.WARNING)
    var onStateChange: ((ProtocolState) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) stateChangeListeners.add(value)
        }

    @Deprecated("Use observeRawTx()", level = DeprecationLevel.WARNING)
    var onRawTx: ((String, String) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) rawTxListeners.add(value)
        }

    @Deprecated("Use observeRawChunk()", level = DeprecationLevel.WARNING)
    var onRawChunk: ((ByteArray, Int) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) rawChunkListeners.add(value)
        }

    @Deprecated("Use observeDeviceFound()", level = DeprecationLevel.WARNING)
    var onDeviceFound: ((String, String) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) deviceFoundListeners.add(value)
        }

    // Observer методы — подписка БЕЗ затирания
    fun observeDeviceFound(listener: (String, String) -> Unit) = deviceFoundListeners.add(listener)
    fun observeDataReceived(listener: (ByteArray) -> Unit) = dataReceivedListeners.add(listener)
    fun observeParsedMeasurement(listener: (BlePacketParser.ParsedMeasurement) -> Unit) = parsedMeasurementListeners.add(listener)
    fun observeConnectionState(listener: (Boolean) -> Unit) = connectionStateListeners.add(listener)
    fun observeStateChange(listener: (ProtocolState) -> Unit) = stateChangeListeners.add(listener)
    fun observeRawTx(listener: (String, String) -> Unit) = rawTxListeners.add(listener)
    fun observeRawChunk(listener: (ByteArray, Int) -> Unit) = rawChunkListeners.add(listener)

    // Отписка
    fun removeDeviceFound(listener: (String, String) -> Unit) = deviceFoundListeners.remove(listener)
    fun removeDataReceived(listener: (ByteArray) -> Unit) = dataReceivedListeners.remove(listener)
    fun removeParsedMeasurement(listener: (BlePacketParser.ParsedMeasurement) -> Unit) = parsedMeasurementListeners.remove(listener)
    fun removeConnectionState(listener: (Boolean) -> Unit) = connectionStateListeners.remove(listener)
    fun removeStateChange(listener: (ProtocolState) -> Unit) = stateChangeListeners.remove(listener)
    fun removeRawTx(listener: (String, String) -> Unit) = rawTxListeners.remove(listener)
    fun removeRawChunk(listener: (ByteArray, Int) -> Unit) = rawChunkListeners.remove(listener)

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
            deviceFoundListeners.forEach { it(mac, name) }
        }
    }

    init {
        // Подписываемся на события транспорта
        connectionManager.onConnected = { onTransportConnected() }
        connectionManager.onDisconnected = { reason -> onTransportDisconnected(reason) }
        connectionManager.onDataReceived = { packet -> onPacketReceived(packet) }
        connectionManager.onRawChunk = { chunk, total ->
            rawChunkListeners.forEach { it(chunk, total) }
        }

        // Регистрируем BroadcastReceiver для системных событий BT (как в MM)
        registerBtReceiver()
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

    /**
     * Подключение к устройству.
     * Как в MM BTDeviceManagerImpl.connect():
     * 1. Сохраняем MAC ДО попытки подключения
     * 2. Останавливаем autoConnect на время попытки
     * 3. Если через 5 сек не подключились — перезапускаем autoConnect
     */
    @SuppressLint("MissingPermission")
    fun connect(macAddress: String, deviceName: String? = null) {
        Log.d("BLE", "=== Connecting to $macAddress (INSECURE RFCOMM, no bonding) ===")

        // Как в MM: сохраняем адрес ДО попытки подключения
        lastKnownDeviceMac = macAddress
        lastKnownDeviceName = deviceName

        stopScan()
        stopAutoConnect()

        // Запускаем попытку подключения
        connectionManager.connect(macAddress)

        // Как в MM: если через 5 сек не подключились — перезапускаем autoConnect
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            if (!isConnected && lastKnownDeviceMac != null) {
                Log.w("BLE", "Connection failed after 5s, restarting auto-connect")
                startAutoConnect(lastKnownDeviceMac!!, lastKnownDeviceName)
            }
        }, 5000L)
    }

    /** Транспорт подключился — начинаем инициализацию протокола */
    private fun onTransportConnected() {
        Log.d("BLE", "✅ Transport connected!")
        saveLastKnownDevice()
        connectionStateListeners.forEach { it(true) }

        // Сразу MASTER_READY — мы готовы отправлять команды
        transitionTo(ProtocolState.MASTER_READY)

        // Init — AutoSync ON (как turnAutoSyncOn() в MO/MM)
        // В MM заголовок EDC формируется так: (devMode << 2) | (keypadBypass << 1) | syncControl
        // devMode=0, syncControl=1 => Байт = 0x01.
        // (Раньше было 0x40, что означало devMode=16 — отсюда и глючило!)
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), 0x01.toByte(), 0x00.toByte()),
            "Init (AutoSync ON)"
        )
    }

    /**
     * Сохранить устройство для автоподключения (как lastKnownDeviceForReconnection в MO).
     */
    private fun saveLastKnownDevice() {
        lastKnownDeviceMac = connectionManager.connectedDeviceMac
        lastKnownDeviceName = connectionManager.connectedDeviceName
        Log.d("BLE", "Saved last known device: $lastKnownDeviceName ($lastKnownDeviceMac)")
    }

    private fun onTransportDisconnected(reason: String?) {
        Log.d("BLE", "Transport disconnected: ${reason ?: "normal"}")
        connectionStateListeners.forEach { it(false) }
        commandQueue.clear()
        timeoutPending = false
        sendThread = null
        protocolState = ProtocolState.SLAVE_LISTENING

        // Как в MM: ПРИ ОСТАНОВКЕ потока autoConnect — перезапускаем его
        // (поток может "залипнуть" в isConnected=true после закрытия сокета)
        if (lastKnownDeviceMac != null) {
            Log.d("BLE_AUTO", "Device disconnected, RESTARTING auto-connect to $lastKnownDeviceMac")
            stopAutoConnect()  // Убиваем "залипший" поток
            startAutoConnect(lastKnownDeviceMac!!, lastKnownDeviceName)  // Запускаем новый
        }
    }

    // ==================== AUTO CONNECT (как AutoConnectThread в MM/MO) ====================

    /**
     * Запустить автоподключение — каждые 2 сек попытка подключиться.
     * Как в MM: AutoConnectThread — ОДИН поток, НЕ перезапускается при disconnect.
     */
    fun startAutoConnect(macAddress: String, deviceName: String? = null) {
        Log.d("BLE_AUTO", "startAutoConnect: $macAddress ($deviceName)")
        lastKnownDeviceMac = macAddress
        lastKnownDeviceName = deviceName

        // Если поток уже запущен — просто обновляем данные и возвращаемся
        if (autoConnectRunning) {
            Log.d("BLE_AUTO", "AutoConnect already running (thread alive=${autoConnectThread?.isAlive}), just updating device info")
            return
        }

        autoConnectRunning = true

        autoConnectThread = Thread {
            Log.d("BLE_AUTO", "AutoConnectThread started loop")
            while (autoConnectRunning && !Thread.interrupted()) {
                try {
                    if (connectionManager.isConnected) {
                        Log.d("BLE_AUTO", "Already connected, sleeping...")
                        Thread.sleep(autoConnectDelayMs)
                    } else {
                        Log.d("BLE_AUTO", "Not connected, trying to connect $macAddress")
                        connectionManager.connect(macAddress)
                        Log.d("BLE_AUTO", "connect() returned, sleeping...")
                        Thread.sleep(autoConnectDelayMs)
                    }
                } catch (e: InterruptedException) {
                    Log.d("BLE_AUTO", "AutoConnectThread interrupted")
                    return@Thread
                }
            }
            Log.d("BLE_AUTO", "AutoConnectThread stopped")
        }.apply {
            priority = Thread.MIN_PRIORITY
            start()
        }
    }

    /** Остановить автоподключение */
    fun stopAutoConnect() {
        autoConnectRunning = false
        autoConnectThread?.interrupt()
        try { autoConnectThread?.join(500) } catch (_: Exception) {}
        autoConnectThread = null
        Log.d("BLE_AUTO", "AutoConnect stopped")
    }

    /**
     * Переключиться на другое устройство
     */
    fun connectAndAutoConnect(macAddress: String, deviceName: String? = null) {
        Log.d("BLE_AUTO", "connectAndAutoConnect: $macAddress ($deviceName)")
        lastKnownDeviceMac = macAddress
        lastKnownDeviceName = deviceName
        connect(macAddress, deviceName)
    }

    // ==================== PACKET RECEIVED ====================

    /** Пришёл полный пакет от BleConnectionManager (уже с CRC) */
    private fun onPacketReceived(fullPacket: ByteArray) {
        val hex = fullPacket.joinToString(" ") { "%02X".format(it) }
        rawTxListeners.forEach { it("RX", hex) }

        // CRC валидация
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

        // Извлекаем refEdge и devMode из КАЖДОГО пакета (байт 3: [refEdge:2][devMode:6])
        if (fullPacket.size >= 4) {
            val devModeRef = fullPacket[3].toInt() and 0xFF
            val refEdge = devModeRef and 0x03
            val devMode = (devModeRef shr 2) and 0x3F

            // Обновляем состояние refEdge из КАЖДОГО пакета
            if (currentRefEdge != refEdge) {
                currentRefEdge = refEdge
                Log.d("BLE", "📐 refEdge changed → $refEdge")
                onRefEdgeChanged?.invoke(refEdge)
            }

            // Обновляем тип измерения (игнорируем служебные devMode=0,60,62)
            if (devMode != 0 && devMode != 60 && devMode != 62) {
                if (currentMeasurementType != devMode) {
                    currentMeasurementType = devMode
                    Log.d("BLE", "📏 measurementType changed → $devMode")
                    onMeasurementTypeChanged?.invoke(devMode)
                }
            }
        }

        // Heartbeat или измерение?
        if (isHeartbeatPacket(fullPacket)) {
            handleHeartbeat(fullPacket)
            if (protocolState == ProtocolState.MASTER_RECEIVING) {
                transitionTo(ProtocolState.MASTER_READY)
            }
        } else {
            // Измерение
            dataReceivedListeners.forEach { it(fullPacket) }

            val parsed = BlePacketParser.parseWithCrc(fullPacket)
            if (parsed != null) {
                Log.d("BLE", "✅ Parsed: devMode=${parsed.devMode}, ${parsed.resultValue}m, ${parsed.comp2Value}°")
                parsedMeasurementListeners.forEach { it(parsed) }
            } else {
                Log.w("BLE", "⚠️ Parse returned null (likely heartbeat or status packet)")
            }

            if (protocolState == ProtocolState.MASTER_RECEIVING) {
                transitionTo(ProtocolState.MASTER_READY)
            }
        }
    }

    // ==================== STATE MACHINE ====================

    private fun transitionTo(newState: ProtocolState) {
        synchronized(stateLock) {
            val oldState = protocolState
            protocolState = newState
            Log.d("BLE_STATE", "$oldState → $newState")
            stateChangeListeners.forEach { it(newState) }
            stateLock.notifyAll()
        }
    }

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

    fun enqueueCommand(payload: ByteArray, name: String) {
        if (!isConnected) {
            Log.w("BLE", "Not connected, command dropped: $name")
            return
        }
        commandQueue.offerLast(QueuedCommand(payload, name))
        Log.d("BLE", "📋 Queued: $name (queue size: ${commandQueue.size})")

        if (sendThread?.isAlive != true) {
            startSendThread()
        }
    }

    private fun startSendThread() {
        sendThread = Thread {
            Log.d("BLE", "SendThread started")
            while (!Thread.interrupted() && isConnected) {
                try {
                    val cmd = commandQueue.pollFirst() ?: break

                    val ready = waitForState(ProtocolState.MASTER_READY, 2000)
                    if (!ready || !isConnected) {
                        Log.w("BLE", "⚠️ SendThread: not ready or disconnected, dropping: ${cmd.name}")
                        break
                    }

                    transitionTo(ProtocolState.MASTER_SENDING)

                    val crc = BlePacketParser.calcCrc8(cmd.payload)
                    val fullPacket = cmd.payload + crc.toByte()
                    val hex = fullPacket.joinToString(" ") { "%02X".format(it) }

                    val writeOk = connectionManager.write(fullPacket)
                    rawTxListeners.forEach { it("TX", hex) }

                    if (!writeOk) {
                        Log.e("BLE", "❌ Write failed for ${cmd.name}")
                        transitionTo(ProtocolState.MASTER_READY)
                        Thread.sleep(200)
                        continue
                    }

                    transitionTo(ProtocolState.MASTER_RECEIVING)
                    val responseReceived = waitForState(ProtocolState.MASTER_READY, commandTimeoutMs * 2)

                    if (!responseReceived) {
                        Log.w("BLE", "⏱️ Command timeout: ${cmd.name} (no response in ${commandTimeoutMs * 2}ms)")
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

    /**
     * Сформировать байт заголовка EDC (как в MM EDCFrameFactory).
     * Структура: [devMode:6][keypadBypass:1][syncControl:1]
     * Формула: (devMode << 2) | (keypadBypass << 1) | syncControl
     */
    private fun makeEdcHeader(devMode: Int, keypadBypass: Int = 0, syncControl: Int = 1): Byte {
        return ((devMode shl 2) or (keypadBypass shl 1) or syncControl).toByte()
    }

    fun sendInit() {
        // devMode=0, syncControl=1 -> header 0x01 (Раньше было 0x40!)
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(0), 0x00.toByte()),
            "Init"
        )
    }

    fun sendGetSettings() {
        // В MM такого нет, но пусть будет devMode=0, sync=0
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(0, 0, 0), 0x00.toByte()),
            "Get Settings"
        )
    }

    fun sendTrigger() {
        // В MM это просто кнопка, devMode=0, sync=1
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(0), 0x00.toByte()),
            "Trigger"
        )
    }

    fun sendSyncHistory(index: Int) {
        // devMode=58 (MODE_GET_LIST_ITEM), sync=1
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(58), index.toByte()),
            "Sync History $index"
        )
    }

    fun setMeasurementType(mode: Int) {
        // devMode=60 (MODE_SET_DEV_MODE), sync=1
        Log.d("BLE_CMD", "🎯 Set Measurement Type: mode=$mode")
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(60), mode.toByte()),
            "Set Mode $mode"
        )
    }

    fun setReferencePoint(ref: Int) {
        // devMode=62 (MODE_SET_DISTANCE_REFERENCE), sync=1
        Log.d("BLE_CMD", "📍 Set Reference Point: ref=$ref")
        enqueueCommand(
            byteArrayOf(0xC0.toByte(), 0x55.toByte(), 0x02.toByte(), makeEdcHeader(62), ref.toByte()),
            "Set Ref $ref"
        )
    }

    fun sendRawCommand(payload: ByteArray, name: String) {
        enqueueCommand(payload, name)
    }

    // ==================== HEARTBEAT ====================

    private fun isHeartbeatPacket(data: ByteArray): Boolean {
        if (data.size < 5) return false
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
