package com.glmreader.android.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.glmreader.android.GlmReaderApplication
import com.glmreader.android.R
import com.glmreader.android.ble.GlmBleManager
import com.glmreader.android.protocol.BlePacketParser
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Окно отладки BLE — показывает сырые данные, состояния, очереди.
 * Универсальное — работает с любым BLE/RFCOMM устройством.
 */
class BleDebugActivity : AppCompatActivity() {

    private lateinit var bleManager: GlmBleManager

    // Views
    private lateinit var tvConnStatus: TextView
    private lateinit var tvState: TextView
    private lateinit var tvQueue: TextView
    private lateinit var tvRx: TextView
    private lateinit var tvTx: TextView
    private lateinit var tvMeasurements: TextView
    private lateinit var tvLog: TextView
    private lateinit var scrollLog: ScrollView
    private lateinit var etRawCommand: EditText
    private lateinit var btnSendRaw: Button
    private lateinit var btnInit: Button
    private lateinit var btnTrigger: Button
    private lateinit var btnGetSettings: Button
    private lateinit var btnClearLog: Button

    private var measurementCount = 0
    private val dateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble_debug)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "BLE Debug"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initBleManager()
        setupClickListeners()
        appendLog("BLE Debug Activity started")
        appendLog("Build: ${getBuildInfo()}")
    }

    private fun getBuildInfo(): String {
        val pkgInfo = packageManager.getPackageInfo(packageName, 0)
        val verName = pkgInfo.versionName ?: "?"
        val verCode = pkgInfo.versionCode
        val buildTime = "2026-04-12"
        return "v$verName ($verCode) / $buildTime"
    }

    private fun initViews() {
        tvConnStatus = findViewById(R.id.tvConnStatus)
        tvState = findViewById(R.id.tvState)
        tvQueue = findViewById(R.id.tvQueue)
        tvRx = findViewById(R.id.tvRx)
        tvTx = findViewById(R.id.tvTx)
        tvMeasurements = findViewById(R.id.tvMeasurements)
        tvLog = findViewById(R.id.tvLog)
        scrollLog = findViewById(R.id.scrollLog)
        etRawCommand = findViewById(R.id.etRawCommand)
        btnSendRaw = findViewById(R.id.btnSendRaw)
        btnInit = findViewById(R.id.btnInit)
        btnTrigger = findViewById(R.id.btnTrigger)
        btnGetSettings = findViewById(R.id.btnGetSettings)
        btnClearLog = findViewById(R.id.btnClearLog)
    }

    private fun initBleManager() {
        val app = application as GlmReaderApplication
        bleManager = app.getOrCreateBleManager(this)

        // Показываем ТЕКУЩЕЕ состояние при открытии
        refreshUiState()

        // Подписываемся на изменения
        bleManager.observeConnectionState { _ ->
            runOnUiThread { refreshUiState() }
        }

        bleManager.observeStateChange { state ->
            runOnUiThread {
                tvState.text = "State: ${state.name}"
                val color = when (state) {
                    GlmBleManager.ProtocolState.SLAVE_LISTENING -> "#FFC107"
                    GlmBleManager.ProtocolState.MASTER_READY -> "#4CAF50"
                    GlmBleManager.ProtocolState.MASTER_SENDING -> "#2196F3"
                    GlmBleManager.ProtocolState.MASTER_RECEIVING -> "#FF9800"
                }
                tvState.setTextColor(android.graphics.Color.parseColor(color))
                appendLog("STATE → ${state.name}")
            }
        }

        bleManager.observeRawTx { direction, hex ->
            runOnUiThread {
                appendLog("$direction: $hex")
                if (direction == "TX") {
                    val bytes = hex.split(" ").size
                    val total = parseBytes(tvTx.text.toString()) + bytes
                    tvTx.text = "TX: ${total} B"
                } else {
                    val bytes = hex.split(" ").size
                    val total = parseBytes(tvRx.text.toString()) + bytes
                    tvRx.text = "RX: ${total} B"
                }
            }
        }

        bleManager.observeParsedMeasurement { parsed ->
            runOnUiThread {
                measurementCount++
                tvMeasurements.text = "M: $measurementCount"
                appendLog("✅ MEAS: devMode=${parsed.devMode} result=${parsed.resultValue}m angle=${parsed.comp2Value}°")
            }
        }

        startUiUpdateTimer()
    }

    private fun refreshUiState() {
        if (bleManager.isConnected) {
            tvConnStatus.text = "✅ Connected: ${bleManager.connectedDeviceName}"
            tvConnStatus.setTextColor(getColor(R.color.status_success))
        } else {
            tvConnStatus.text = "❌ Not connected"
            tvConnStatus.setTextColor(getColor(R.color.status_error))
        }
        tvState.text = "State: ${bleManager.protocolStateName}"
        tvQueue.text = "Q:${bleManager.queueSize}"
    }

    private fun parseBytes(text: String): Int {
        return try {
            text.replace("RX: ", "").replace("TX: ", "").replace(" B", "").toIntOrNull() ?: 0
        } catch (e: Exception) { 0 }
    }

    private fun startUiUpdateTimer() {
        val handler = android.os.Handler(android.os.Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (isFinishing) return
                tvQueue.text = "Q:${bleManager.queueSize}"
                if (bleManager.isConnected) {
                    val info = bleManager.getDebugInfo()
                    // Обновляем state если не через callback
                    tvState.text = "State: ${bleManager.protocolStateName}"
                }
                handler.postDelayed(this, 500)
            }
        }
        handler.post(runnable)
    }

    private fun setupClickListeners() {
        btnSendRaw.setOnClickListener {
            val hex = etRawCommand.text.toString().trim()
            if (hex.isEmpty()) return@setOnClickListener

            val bytes = parseHexCommand(hex)
            if (bytes != null) {
                bleManager.sendRawCommand(bytes, "Raw: $hex")
                etRawCommand.text.clear()
            }
        }

        btnInit.setOnClickListener { bleManager.sendInit() }
        btnTrigger.setOnClickListener { bleManager.sendTrigger() }
        btnGetSettings.setOnClickListener { bleManager.sendGetSettings() }

        btnClearLog.setOnClickListener {
            tvLog.text = ""
            measurementCount = 0
            tvMeasurements.text = "M: 0"
            appendLog("Log cleared")
        }
    }

    private fun parseHexCommand(hex: String): ByteArray? {
        return try {
            val parts = hex.split("\\s+".toRegex()).filter { it.isNotEmpty() }
            parts.map { it.toInt(16).toByte() }.toByteArray()
        } catch (e: Exception) {
            appendLog("❌ Invalid HEX: ${e.message}")
            null
        }
    }

    private fun appendLog(message: String) {
        val timestamp = dateFormat.format(Date())
        val line = "[$timestamp] $message\n"
        tvLog.append(line)

        // Auto-scroll
        scrollLog.post {
            scrollLog.fullScroll(ScrollView.FOCUS_DOWN)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        // НЕ отключаем BLE — это делает Application
    }
}
