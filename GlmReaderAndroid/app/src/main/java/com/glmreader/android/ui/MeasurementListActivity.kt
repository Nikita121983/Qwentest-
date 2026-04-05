package com.glmreader.android.ui

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.glmreader.android.R
import com.glmreader.android.ble.GlmBleManager
import com.glmreader.android.data.AppDatabase
import com.glmreader.android.data.entity.MeasurementEntity
import com.glmreader.android.export.XlsxExporter
import com.glmreader.android.protocol.BlePacketParser
import com.glmreader.android.protocol.InclinoLogic
import com.glmreader.android.ui.viewmodel.MeasurementViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import com.glmreader.android.ui.BleDeviceAdapter
import com.glmreader.android.ui.BleDeviceItem
import androidx.appcompat.app.AlertDialog

class MeasurementListActivity : AppCompatActivity() {

    private val viewModel: MeasurementViewModel by viewModels()
    private lateinit var bleManager: GlmBleManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var panelRemote: LinearLayout
    private lateinit var btnEnable: Button
    private lateinit var adapter: MeasurementAdapter
    private lateinit var fabScrollTop: FloatingActionButton
    private lateinit var fabScrollBottom: FloatingActionButton
    private lateinit var fabExport: FloatingActionButton
    private lateinit var xlsxExporter: XlsxExporter

    private var currentProjectId: String? = null
    private var currentProjectName: String = "Все замеры"
    private var isUserAtBottom = true

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            bleManager.startScan()
        } else {
            Toast.makeText(this, "Нет прав на BLE", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_measurement_list)

            setSupportActionBar(findViewById(R.id.toolbar))

            // Получаем projectId из Intent
            currentProjectId = intent.getStringExtra("project_uuid")
            currentProjectName = intent.getStringExtra("project_name") ?: "Все замеры"
            supportActionBar?.title = currentProjectName

            recyclerView = findViewById(R.id.recyclerView)
            tvEmpty = findViewById(R.id.tvEmpty)
            panelRemote = findViewById(R.id.panelRemote)
            btnEnable = findViewById(R.id.btnEnable)
            fabScrollTop = findViewById(R.id.fabScrollTop)
            fabScrollBottom = findViewById(R.id.fabScrollBottom)
            fabExport = findViewById(R.id.fabExport)
            xlsxExporter = XlsxExporter(this)

            // Адаптер
            adapter = MeasurementAdapter(
                mutableListOf(),
                { uuid -> viewModel.deleteMeasurement(uuid) },
                { selectedUuids ->
                    // Обновить title при изменении selection
                    runOnUiThread {
                        if (selectedUuids.isNotEmpty()) {
                            supportActionBar?.title = "Выбрано: ${selectedUuids.size}"
                        } else {
                            supportActionBar?.title = currentProjectName
                        }
                    }
                }
            )
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            // Наблюдаем за StateFlow из ViewModel
            lifecycleScope.launch {
                viewModel.measurements.collectLatest { measurements ->
                    adapter.updateData(measurements)
                    tvEmpty.visibility = if (measurements.isEmpty()) View.VISIBLE else View.GONE

                    // Smart Scroll: если пользователь внизу — прокрутить вниз
                    if (isUserAtBottom && measurements.isNotEmpty()) {
                        recyclerView.post {
                            recyclerView.scrollToPosition(measurements.size - 1)
                        }
                    }
                }
            }

            // Scroll listener для показа/скрытия FABs
            recyclerView.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(rv, dx, dy)
                    val layoutManager = rv.layoutManager as LinearLayoutManager
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val total = layoutManager.itemCount
                    isUserAtBottom = (total - lastVisible) <= 2

                    fabScrollTop.visibility = if (lastVisible > 5) View.VISIBLE else View.GONE
                    fabScrollBottom.visibility = if (!isUserAtBottom && total > 5) View.VISIBLE else View.GONE
                }
            })

            // FAB: В начало
            fabScrollTop.setOnClickListener {
                recyclerView.smoothScrollToPosition(0)
            }

            // FAB: В конец
            fabScrollBottom.setOnClickListener {
                recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
            }

            // FAB: Экспорт
            fabExport.setOnClickListener {
                exportToXlsx()
            }

            bleManager = GlmBleManager(this)
            Log.d("MainActivity", "BLE Manager created")
            setupBleCallbacks()
            Log.d("MainActivity", "Callbacks setup done")

            checkPermissionsAndScan()
            Log.d("MainActivity", "checkPermissionsAndScan called")

            // Кнопка Включить (лазер/замер)
            btnEnable.setOnClickListener {
                Log.d("MainActivity", "btnEnable clicked")
                // TODO: отправить команду Remote Trigger на рулетку
            }

        } catch (e: Exception) {
            Log.e("MainActivity", "onCreate CRASH", e)
            Toast.makeText(this, "Ошибка: ${e.javaClass.simpleName}: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun setupBleCallbacks() {
        // Убрали автоматическое подключение - теперь пользователь выбирает устройство в диалоге
        bleManager.onDeviceFound = { mac, name ->
            Log.d("BLE", "Found: $name ($mac)")
            // Не подключаемся автоматически, просто логируем
        }

        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread {
                if (connected) {
                    // Показать панель дистанционного измерения
                    panelRemote.visibility = View.VISIBLE
                    btnEnable.text = "Выключить"
                    Toast.makeText(this, "Подключено!", Toast.LENGTH_SHORT).show()
                } else {
                    // Скрыть панель
                    panelRemote.visibility = View.GONE
                    Toast.makeText(this, "Отключено", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bleManager.onDataReceived = { bytes ->
            val hex = bytes.joinToString(" ") { "%02X".format(it) }
            Log.d("BLE", "DATA: $hex")
        }

        bleManager.onParsedMeasurement = { parsed ->
            Log.d("BLE", "Parsed: type=${parsed.devMode}, result=${parsed.resultValue}m, angle=${parsed.comp2Value}°")

            val inclino = InclinoLogic.determineType(
                angleDeg = parsed.comp2Value,
                comp1Value = parsed.resultValue
            )

            viewModel.onBleMeasurement(parsed, currentProjectId)

            Log.d("BLE", "Saved via ViewModel: ${inclino.displayName}")
        }
    }

    private fun checkPermissionsAndScan() {
        try {
            Log.d("MainActivity", "checkPermissionsAndScan start")
            val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
            } else {
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            Log.d("MainActivity", "Permissions: ${permissions.joinToString()}")

            val allGranted = permissions.all {
                ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
            }
            Log.d("MainActivity", "All permissions granted: $allGranted")

            if (allGranted) {
                bleManager.startScan()
            } else {
                Log.d("MainActivity", "Requesting permissions")
                requestPermissionLauncher.launch(permissions)
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "checkPermissions error", e)
            Toast.makeText(this, "Ошибка разрешений: ${e.javaClass.simpleName}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_ble -> {
                showBleDialog()
                true
            }
            R.id.action_export -> {
                if (adapter.isSelectionMode()) {
                    adapter.deleteSelected()
                } else {
                    exportToXlsx()
                }
                true
            }
            R.id.action_settings -> {
                if (adapter.isSelectionMode()) {
                    adapter.selectAll()
                } else {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
                if (filePath != null) {
                    Toast.makeText(this@MeasurementListActivity, "Сохранено: $filePath", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MeasurementListActivity, "Ошибка экспорта", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bleManager.disconnect()
    }

    private fun showBleDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_ble_connection, null)
        val tvStatus = view.findViewById<TextView>(R.id.tvBleStatus)
        val btnToggle = view.findViewById<Button>(R.id.btnToggleBle)
        val btnScan = view.findViewById<Button>(R.id.btnScan)
        val btnConnect = view.findViewById<Button>(R.id.btnConnect)
        val tvConnStatus = view.findViewById<TextView>(R.id.tvConnectionStatus)
        val recycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerDevices)

        val bluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter = bluetoothManager.adapter

        fun updateUi() {
            val isBleOn = bluetoothAdapter.isEnabled
            val tvStatus = view.findViewById<TextView>(R.id.tvBleStatus)
            val btnToggle = view.findViewById<Button>(R.id.btnToggleBle)
            val btnScan = view.findViewById<Button>(R.id.btnScan)
            val tvConnStatus = view.findViewById<TextView>(R.id.tvConnectionStatus)

            tvStatus.text = if (isBleOn) "Bluetooth включен" else "Bluetooth выключен"
            tvStatus.setTextColor(if (isBleOn) getColor(android.R.color.holo_green_dark) else getColor(android.R.color.holo_red_dark))
            btnToggle.text = if (isBleOn) "Выключить" else "Включить"
            btnScan.isEnabled = isBleOn

            if (bleManager.isConnected) {
                tvConnStatus.text = "Подключено к ${bleManager.connectedDeviceName ?: "Устройству"}"
                tvConnStatus.setTextColor(getColor(android.R.color.holo_green_dark))
                btnScan.text = "Остановить"
            } else if (bleManager.isScanning) {
                tvConnStatus.text = "Сканирование..."
                tvConnStatus.setTextColor(getColor(android.R.color.holo_blue_dark))
                btnScan.text = "Стоп"
            } else {
                tvConnStatus.text = "Не подключено"
                tvConnStatus.setTextColor(getColor(android.R.color.darker_gray))
                btnScan.text = "Сканировать"
            }
        }

        // Список для динамического отображения найденных устройств
        val foundDevices = mutableListOf<BleDeviceItem>()
        val deviceAdapter = BleDeviceAdapter(foundDevices) { selectedDevice ->
            // При выборе устройства в списке
            runOnUiThread {
                btnConnect.isEnabled = selectedDevice != null
                if (selectedDevice != null) {
                    tvConnStatus.text = "Выбрано: ${selectedDevice.name}"
                }
            }
        }
        recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recycler.adapter = deviceAdapter

        // Подписываемся на поиск устройств для обновления списка
        // ВАЖНО: Сохраняем ссылку на callback для последующей очистки
        val scanCallback: (String, String) -> Unit = { mac, name ->
            runOnUiThread {
                Log.d("BLE_DIALOG", "Found in dialog: $name ($mac)")

                // Добавляем устройство, если его еще нет
                if (foundDevices.none { it.mac == mac }) {
                    foundDevices.add(BleDeviceItem(name, mac, false))
                    deviceAdapter.notifyDataSetChanged()
                    Log.d("BLE_DIALOG", "Added to list: $name")
                }
            }
        }

        // Устанавливаем callback ПЕРЕД открытием диалога
        bleManager.onDeviceFound = scanCallback

        btnToggle.setOnClickListener {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, 1)
        }

        btnScan.setOnClickListener {
            if (bleManager.isScanning) {
                bleManager.stopScan()
            } else {
                foundDevices.clear() // Очищаем список при новом сканировании
                deviceAdapter.notifyDataSetChanged()
                btnConnect.isEnabled = false
                bleManager.startScan()
            }
            updateUi()
        }

        btnConnect.setOnClickListener {
            val selected = deviceAdapter.selectedDevice
            if (selected != null) {
                bleManager.connect(selected.mac)
                updateUi()
            }
        }

        // Callbacks для обновления UI
        val originalConnCallback = bleManager.onConnectionStateChanged
        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread { updateUi() }
            originalConnCallback?.invoke(connected)
        }

        val dialog = com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
            .setTitle("Подключение к рулетке")
            .setView(view)
            .setNegativeButton("Закрыть") { _, _ ->
                bleManager.stopScan()
                // НЕ сбрасываем onDeviceFound - он нужен для основного потока
                // Просто восстанавливаем logging-only callback
                bleManager.onDeviceFound = { mac, name ->
                    Log.d("BLE", "Found: $name ($mac)")
                }
            }
            .create()

        dialog.show()
        updateUi()
    }
}
