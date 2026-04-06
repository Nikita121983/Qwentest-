package com.glmreader.android.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R
import com.glmreader.android.ble.GlmBleManager
import com.glmreader.android.export.XlsxExporter
import com.glmreader.android.protocol.BlePacketParser
import com.glmreader.android.protocol.InclinoLogic
import com.glmreader.android.ui.viewmodel.MeasurementViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MeasurementListActivity : AppCompatActivity() {

    private val viewModel: MeasurementViewModel by viewModels()
    private lateinit var bleManager: GlmBleManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: MeasurementAdapter
    private lateinit var xlsxExporter: XlsxExporter

    private var currentProjectId: String? = null
    private var currentProjectName: String = "Все замеры"

    // Remote Control Views
    private lateinit var panelRemote: LinearLayout
    private lateinit var btnTrigger: Button
    private lateinit var btnGetData: Button
    private lateinit var btnInit: Button
    private lateinit var btnSendCustom: Button
    private lateinit var spinnerCommands: Spinner
    private lateinit var spinnerModes: Spinner
    private lateinit var spinnerRefs: Spinner
    private lateinit var tvResponseLog: TextView

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) bleManager.startScan()
        else Toast.makeText(this, "Нет прав на BLE", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        currentProjectId = intent.getStringExtra("project_uuid")
        currentProjectName = intent.getStringExtra("project_name") ?: "Все замеры"
        supportActionBar?.title = currentProjectName

        recyclerView = findViewById(R.id.recyclerView)
        tvEmpty = findViewById(R.id.tvEmpty)
        xlsxExporter = XlsxExporter(this)

        // Setup Remote Control Panel
        panelRemote = findViewById(R.id.panelRemote)
        btnTrigger = findViewById(R.id.btnTrigger)
        btnGetData = findViewById(R.id.btnGetData)
        btnInit = findViewById(R.id.btnInit)
        btnSendCustom = findViewById(R.id.btnSendCustom)
        spinnerCommands = findViewById(R.id.spinnerCommands)
        spinnerModes = findViewById(R.id.spinnerModes)
        spinnerRefs = findViewById(R.id.spinnerRefs)
        tvResponseLog = findViewById(R.id.tvResponseLog)

        // Debug Commands Spinner
        val commandNames = resources.getStringArray(R.array.glm_commands)
        val cmdAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, commandNames)
        cmdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCommands.adapter = cmdAdapter

        // Modes Spinner (Типы измерений)
        val modeNames = resources.getStringArray(R.array.glm_modes)
        val modeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modeNames)
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerModes.adapter = modeAdapter

        spinnerModes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val modes = resources.getIntArray(R.array.glm_modes_values)
                if (bleManager.isConnected && position in modes.indices) {
                    bleManager.setMeasurementType(modes[position])
                    appendLog("TX", "Set Mode: ${modeNames[position]}")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // References Spinner (Точки отсчета)
        val refNames = resources.getStringArray(R.array.glm_refs)
        val refAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, refNames)
        refAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRefs.adapter = refAdapter

        spinnerRefs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val refs = resources.getIntArray(R.array.glm_refs_values)
                if (bleManager.isConnected && position in refs.indices) {
                    bleManager.setReferencePoint(refs[position])
                    appendLog("TX", "Set Ref: ${refNames[position]}")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Measurement Adapter
        adapter = MeasurementAdapter(
            mutableListOf(),
            { uuid -> viewModel.deleteMeasurement(uuid) },
            { selectedUuids ->
                runOnUiThread {
                    supportActionBar?.title = if (selectedUuids.isNotEmpty()) "Выбрано: ${selectedUuids.size}" else currentProjectName
                }
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.measurements.collectLatest { measurements ->
                adapter.updateData(measurements)
                tvEmpty.visibility = if (measurements.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        bleManager = GlmBleManager(this)
        setupBleCallbacks()
        checkPermissionsAndScan()

        // Remote Control Clicks
        btnTrigger.setOnClickListener { sendCmd("Trigger") { bleManager.sendTrigger() } }
        btnGetData.setOnClickListener { sendCmd("Get Data") { bleManager.sendGetSettings() } }
        btnInit.setOnClickListener { sendCmd("Init") { bleManager.sendInit() } }
        btnSendCustom.setOnClickListener {
            val pos = spinnerCommands.selectedItemPosition
            val hexCommands = resources.getStringArray(R.array.glm_commands_hex)
            if (pos in hexCommands.indices) {
                val bytes = hexCommands[pos].replace(" ", "").chunked(2).map { it.toInt(16).toByte() }.toByteArray()
                bleManager.sendRawCommand(bytes, commandNames[pos])
                appendLog("TX", commandNames[pos])
            }
        }
        findViewById<Button>(R.id.btnClearLog).setOnClickListener { tvResponseLog.text = "" }
    }

    private fun setupBleCallbacks() {
        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread {
                panelRemote.visibility = if (connected) View.VISIBLE else View.GONE
                appendLog(if (connected) "✅ Connected" else "❌ Disconnected", bleManager.connectedDeviceName ?: "")
            }
        }

        bleManager.onDataReceived = { bytes ->
            val hex = bytes.joinToString(" ") { "%02X".format(it) }
            runOnUiThread {
                appendLog("RX", hex)
            }
            // Try parsing
            val parsed = BlePacketParser.parse(bytes)
            if (parsed != null) {
                val inclino = InclinoLogic.determineType(parsed.comp2Value, parsed.resultValue)
                runOnUiThread {
                    appendLog("PARSED", "${inclino.displayName} = ${parsed.resultValue}m (Angle: ${parsed.comp2Value}°)")
                }
                viewModel.onBleMeasurement(parsed, currentProjectId)
            }
        }
    }

    private fun sendCmd(name: String, action: () -> Unit) {
        if (!bleManager.isConnected) {
            appendLog("⚠️", "Not connected!")
            return
        }
        action()
        appendLog("TX", name)
    }

    private fun appendLog(type: String, msg: String) {
        val prefix = when (type) {
            "TX" -> "📤"
            "RX" -> "📥"
            "PARSED" -> "📊"
            else -> "ℹ️"
        }
        tvResponseLog.text = "$prefix [$type] $msg\n${tvResponseLog.text}"
    }

    private fun checkPermissionsAndScan() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
            bleManager.startScan()
        } else {
            requestPermissionLauncher.launch(permissions)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_ble -> {
            showBleDialog()
            true
        }
        R.id.action_export -> {
            if (adapter.isSelectionMode()) adapter.deleteSelected() else exportToXlsx()
            true
        }
        R.id.action_settings -> {
            if (adapter.isSelectionMode()) adapter.selectAll() else startActivity(Intent(this, SettingsActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun exportToXlsx() {
        val measurements = adapter.getMeasurements()
        if (measurements.isEmpty()) {
            Toast.makeText(this, "Нет данных для экспорта", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            val filePath = xlsxExporter.export(measurements)
            runOnUiThread {
                Toast.makeText(this@MeasurementListActivity, if (filePath != null) "Saved: $filePath" else "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bleManager.disconnect()
    }

    // BLE Dialog (Standard List)
    private fun showBleDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_ble_connection, null)
        val tvStatus = view.findViewById<TextView>(R.id.tvBleStatus)
        val btnToggle = view.findViewById<Button>(R.id.btnToggleBle)
        val btnScan = view.findViewById<Button>(R.id.btnScan)
        val btnConnect = view.findViewById<Button>(R.id.btnConnect)
        val tvConnStatus = view.findViewById<TextView>(R.id.tvConnectionStatus)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDevices)
        val btnForget = view.findViewById<Button>(R.id.btnForget)

        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter = bluetoothManager.adapter
        val prefs = getSharedPreferences("ble_prefs", MODE_PRIVATE)

        // Local copies for lambda capture
        var lastMac = prefs.getString("last_device_mac", null)
        var lastName = prefs.getString("last_device_name", null)

        val foundDevices = mutableListOf<BleDeviceItem>()
        val deviceAdapter = BleDeviceAdapter(foundDevices) { selected ->
            runOnUiThread {
                btnConnect.isEnabled = selected != null || lastMac != null
                if (selected != null) tvConnStatus.text = "Selected: ${selected.name}"
            }
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = deviceAdapter

        if (lastMac != null && lastName != null) {
            foundDevices.add(BleDeviceItem(lastName, lastMac, true))
            deviceAdapter.notifyDataSetChanged()
        }

        fun updateUi() {
            val curMac = lastMac
            val curName = lastName
            val isBleOn = bluetoothAdapter.isEnabled
            tvStatus.text = if (isBleOn) "Bluetooth ON" else "Bluetooth OFF"
            tvStatus.setTextColor(ContextCompat.getColor(this, if (isBleOn) R.color.status_success else R.color.status_error))
            btnToggle.text = if (isBleOn) "Turn Off" else "Turn On"
            btnScan.isEnabled = isBleOn

            if (bleManager.isConnected) {
                tvConnStatus.text = "Connected to ${bleManager.connectedDeviceName}"
                tvConnStatus.setTextColor(ContextCompat.getColor(this, R.color.status_success))
                btnScan.text = "Stop"
                btnConnect.isEnabled = true
                btnForget.isEnabled = false
            } else if (bleManager.isScanning) {
                tvConnStatus.text = "Scanning..."
                tvConnStatus.setTextColor(ContextCompat.getColor(this, R.color.status_info))
                btnScan.text = "Stop"
                btnConnect.isEnabled = false
                btnForget.isEnabled = false
            } else {
                tvConnStatus.text = if (curMac != null) "Saved: $curName" else "Not connected"
                tvConnStatus.setTextColor(ContextCompat.getColor(this, R.color.text_secondary))
                btnScan.text = "Scan"
                btnConnect.isEnabled = curMac != null
                btnForget.isEnabled = curMac != null
            }
        }

        bleManager.onDeviceFound = { mac, name ->
            runOnUiThread {
                if (foundDevices.none { it.mac == mac }) {
                    foundDevices.add(BleDeviceItem(name, mac, false))
                    deviceAdapter.notifyDataSetChanged()
                }
            }
        }

        btnToggle.setOnClickListener {
            startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1)
            view.postDelayed({ updateUi() }, 1000)
        }

        btnScan.setOnClickListener {
            if (bleManager.isScanning) bleManager.stopScan()
            else {
                foundDevices.clear()
                val curMac = lastMac
                val curName = lastName
                if (curMac != null && curName != null) foundDevices.add(BleDeviceItem(curName, curMac, true))
                deviceAdapter.notifyDataSetChanged()
                bleManager.startScan()
            }
            updateUi()
        }

        btnConnect.setOnClickListener {
            val savedMac = lastMac
            if (savedMac != null) {
                bleManager.connect(savedMac)
                updateUi()
            }
        }

        btnForget.setOnClickListener {
            prefs.edit().remove("last_device_mac").remove("last_device_name").apply()
            lastMac = null
            lastName = null
            foundDevices.clear()
            deviceAdapter.notifyDataSetChanged()
            updateUi()
        }

        val origConn = bleManager.onConnectionStateChanged
        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread { updateUi() }
            origConn?.invoke(connected)
        }

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Connect to Ruler")
            .setView(view)
            .setNegativeButton("Close") { _, _ ->
                bleManager.stopScan()
                bleManager.onDeviceFound = { _, _ -> }
                bleManager.onConnectionStateChanged = origConn
            }
            .create()

        dialog.show()
        updateUi()
    }
}
