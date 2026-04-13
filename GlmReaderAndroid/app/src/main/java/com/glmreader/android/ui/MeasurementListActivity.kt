package com.glmreader.android.ui

import android.Manifest
import android.animation.ObjectAnimator
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.DecelerateInterpolator
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
    private lateinit var panelRemoteContent: LinearLayout
    private lateinit var btnTogglePanel: LinearLayout
    private lateinit var ivPanelArrow: ImageView
    private lateinit var refCubeContainer: FrameLayout
    private lateinit var ivRefIcon: ImageView
    private lateinit var tvRefLabel: TextView
    private lateinit var typeCubeContainer: FrameLayout
    private lateinit var ivTypeIcon: ImageView
    private lateinit var tvTypeLabel: TextView
    private lateinit var btnEnableLaser: Button

    // Selection Mode Views
    private lateinit var selectionModeBar: LinearLayout
    private lateinit var tvSelectAll: TextView
    private lateinit var tvInterval: TextView
    private lateinit var tvDeselect: TextView

    // List Header
    private lateinit var listHeader: LinearLayout
    private lateinit var ivDownload: ImageView

    // FAB
    private lateinit var fabScrollTop: com.google.android.material.floatingactionbutton.FloatingActionButton

    // Ref and Type modes
    private var currentRefIndex = 0
    private var currentTypeIndex = 0
    private var isPanelExpanded = true

    // Toolbar и фон списка
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    // Цвета toolbar по refEdge (из colors.xml)
    private val toolbarRefEdgeColors = intArrayOf(
        R.color.toolbar_rear,     // 0 — Задняя
        R.color.toolbar_front,    // 1 — Передняя
        R.color.toolbar_tripod,   // 2 — Штатив
        R.color.toolbar_pin       // 3 — Pin
    )
    private var currentToolbarColor = 0 // индекс текущего цвета

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) bleManager.startScan()
        else Toast.makeText(this, "Нет прав на BLE", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement_list)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Начальный цвет toolbar (по умолчанию — Задняя грань)
        updateToolbarColor(0)

        currentProjectId = intent.getStringExtra("project_uuid")
        currentProjectName = intent.getStringExtra("project_name") ?: "Все замеры"
        supportActionBar?.title = currentProjectName

        recyclerView = findViewById(R.id.recyclerView)
        tvEmpty = TextView(this).apply {
            text = "Нет измерений"
            setTextColor(ContextCompat.getColor(this@MeasurementListActivity, R.color.text_secondary))
            textSize = 18f
            gravity = android.view.Gravity.CENTER
            visibility = View.GONE
        }
        xlsxExporter = XlsxExporter(this)

        // Setup Remote Control Panel
        panelRemote = findViewById(R.id.panelRemote)
        panelRemoteContent = findViewById(R.id.panelRemoteContent)
        btnTogglePanel = findViewById(R.id.btnTogglePanel)
        ivPanelArrow = findViewById(R.id.ivPanelArrow)
        refCubeContainer = findViewById(R.id.refCubeContainer)
        ivRefIcon = findViewById(R.id.ivRefIcon)
        tvRefLabel = findViewById(R.id.tvRefLabel)
        typeCubeContainer = findViewById(R.id.typeCubeContainer)
        ivTypeIcon = findViewById(R.id.ivTypeIcon)
        tvTypeLabel = findViewById(R.id.tvTypeLabel)
        btnEnableLaser = findViewById(R.id.btnEnableLaser)

        // Selection Mode Views
        selectionModeBar = findViewById(R.id.selectionModeBar)
        tvSelectAll = findViewById(R.id.tvSelectAll)
        tvInterval = findViewById(R.id.tvInterval)
        tvDeselect = findViewById(R.id.tvDeselect)

        // List Header
        listHeader = findViewById(R.id.listHeader)
        ivDownload = findViewById(R.id.ivDownload)

        // FAB
        fabScrollTop = findViewById(R.id.fabScrollTop)

        // Toggle Panel
        btnTogglePanel.setOnClickListener {
            isPanelExpanded = !isPanelExpanded
            panelRemoteContent.visibility = if (isPanelExpanded) View.VISIBLE else View.GONE
            ObjectAnimator.ofFloat(ivPanelArrow, "rotation", if (isPanelExpanded) 0f else 180f).apply {
                duration = 300
                start()
            }
        }

        // Ref Cube Flip
        refCubeContainer.setOnClickListener {
            flipRefCube()
        }

        // Type Cube Flip
        typeCubeContainer.setOnClickListener {
            flipTypeCube()
        }

        // Enable Laser
        btnEnableLaser.setOnClickListener {
            val isOn = btnEnableLaser.text.toString().contains("Включить")
            btnEnableLaser.text = if (isOn) "Выключить" else "Включить"
            // TODO: Send laser command
        }

        // Selection Mode Buttons
        tvSelectAll.setOnClickListener {
            adapter.selectAll()
        }
        tvInterval.setOnClickListener {
            Toast.makeText(this, "Интервал (в разработке)", Toast.LENGTH_SHORT).show()
        }
        tvDeselect.setOnClickListener {
            adapter.clearSelection()
        }

        // Long Press on Download
        var isLongPressing = false
        ivDownload.setOnLongClickListener {
            adapter.toggleSelectionMode(true)
            isLongPressing = true
            true
        }
        ivDownload.setOnTouchListener { _, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP && isLongPressing) {
                isLongPressing = false
            }
            false
        }

        // Measurement Adapter
        adapter = MeasurementAdapter(
            mutableListOf(),
            { uuid -> viewModel.deleteMeasurement(uuid) },
            { selectedUuids ->
                runOnUiThread {
                    if (selectedUuids.isEmpty()) {
                        adapter.toggleSelectionMode(false)
                        supportActionBar?.title = currentProjectName
                    } else {
                        supportActionBar?.title = "Выбрано: ${selectedUuids.size}"
                    }
                }
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Scroll to Top FAB
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(-1)) {
                    fabScrollTop.visibility = View.GONE
                } else {
                    fabScrollTop.visibility = View.VISIBLE
                }
            }
        })

        fabScrollTop.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }

        lifecycleScope.launch {
            viewModel.measurements.collectLatest { measurements ->
                adapter.updateData(measurements)
                tvEmpty.visibility = if (measurements.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        // BleManager из Application — НЕ рвётся при выходе из Activity
        val app = application as com.glmreader.android.GlmReaderApplication
        bleManager = app.getOrCreateBleManager(this)
        setupBleCallbacks()
        checkPermissionsAndScan()
    }

    private fun flipRefCube() {
        val refNames = arrayOf("Задняя", "Передняя", "Штатив")
        val refIcons = arrayOf(
            R.drawable.ic_ref_rear,
            R.drawable.ic_ref_front,
            R.drawable.ic_ref_tripod
        )
        currentRefIndex = (currentRefIndex + 1) % refNames.size

        // Animation
        val animOut = ObjectAnimator.ofFloat(refCubeContainer, "rotationY", 0f, -90f).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
            addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    ivRefIcon.setImageResource(refIcons[currentRefIndex])
                    tvRefLabel.text = refNames[currentRefIndex]
                    ObjectAnimator.ofFloat(refCubeContainer, "rotationY", 90f, 0f).apply {
                        duration = 200
                        interpolator = DecelerateInterpolator()
                        start()
                    }
                }
            })
        }
        animOut.start()

        // Send command
        if (bleManager.isConnected) {
            bleManager.setReferencePoint(currentRefIndex)
        }
    }

    private fun flipTypeCube() {
        val typeNames = arrayOf(
            "Прямой", "Непрерыв.", "Косв. выс.", "Косв. длина",
            "Двойная", "Мин/Макс", "Угол", "Площадь", "Стена", "Объём", "Разбивка", "Трапеция"
        )
        val typeIcons = arrayOf(
            R.drawable.ic_distance, R.drawable.ic_continuous, R.drawable.ic_indirect_height,
            R.drawable.ic_indirect_length, R.drawable.ic_double_indirect, R.drawable.ic_minmax,
            R.drawable.ic_angle, R.drawable.ic_area, R.drawable.ic_wall, R.drawable.ic_volume,
            R.drawable.ic_stakeout, R.drawable.ic_trapezoid
        )
        currentTypeIndex = (currentTypeIndex + 1) % typeNames.size

        // Animation
        val animOut = ObjectAnimator.ofFloat(typeCubeContainer, "rotationY", 0f, -90f).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
            addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    ivTypeIcon.setImageResource(typeIcons[currentTypeIndex])
                    tvTypeLabel.text = typeNames[currentTypeIndex]
                    ObjectAnimator.ofFloat(typeCubeContainer, "rotationY", 90f, 0f).apply {
                        duration = 200
                        interpolator = DecelerateInterpolator()
                        start()
                    }
                }
            })
        }
        animOut.start()

        // Send command
        if (bleManager.isConnected) {
            val modeValues = arrayOf(1, 2, 10, 11, 12, 3, 8, 4, 14, 7, 15, 26)
            bleManager.setMeasurementType(modeValues[currentTypeIndex])
        }
    }

    private fun setupBleCallbacks() {
        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread {
                // Панель ВСЕГДА видна, кнопки активны/неактивны по подключению
                panelRemote.visibility = View.VISIBLE
                btnEnableLaser.isEnabled = connected
                refCubeContainer.isEnabled = connected
                typeCubeContainer.isEnabled = connected
            }
        }

        // Подписка на смену точки отсчёта (из пакетов от рулетки)
        bleManager.onRefEdgeChanged = { refEdge ->
            runOnUiThread {
                updateToolbarColor(refEdge)
                updateRefCube(refEdge)
            }
        }

        bleManager.onDataReceived = { bytes ->
            // Try parsing
            val parsed = BlePacketParser.parse(bytes)
            if (parsed != null) {
                viewModel.onBleMeasurement(parsed, currentProjectId)
            }
        }
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
        R.id.action_ble_debug -> {
            startActivity(Intent(this, BleDebugActivity::class.java))
            true
        }
        R.id.action_export -> {
            exportToXlsx()
            true
        }
        R.id.action_settings -> {
            startActivity(Intent(this, SettingsActivity::class.java))
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
            try {
                val filePath = xlsxExporter.export(measurements)
                runOnUiThread {
                    Toast.makeText(this@MeasurementListActivity,
                        if (filePath != null) "Saved: $filePath" else "Error exporting",
                        Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@MeasurementListActivity, "Export error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // НЕ отключаем BLE — BleManager живёт на уровне Application
    }

    // BLE Dialog — делегируем helper'у
    private fun showBleDialog() {
        BleDialogHelper.show(this, bleManager)
    }

    // ==================== TOOLBAR COLOR ====================

    /**
     * Обновить цвет toolbar по точке отсчёта (refEdge 0-3).
     * Анимация ObjectAnimator.ofArgb 0.4s.
     */
    private fun updateToolbarColor(refEdge: Int) {
        val colorRes = toolbarRefEdgeColors.getOrElse(refEdge) { toolbarRefEdgeColors[0] }
        val newColor = ContextCompat.getColor(this, colorRes)
        currentToolbarColor = refEdge

        ObjectAnimator.ofArgb(toolbar, "backgroundColor", newColor).apply {
            duration = 400
            start()
        }

        // Полоска сворачивания — тот же цвет
        ObjectAnimator.ofArgb(btnTogglePanel, "backgroundColor", newColor).apply {
            duration = 400
            start()
        }
    }

    /**
     * Обновить кубик точки отсчёта из данных рулетки (без flip).
     */
    private fun updateRefCube(refEdge: Int) {
        val refNames = arrayOf("Задняя", "Передняя", "Штатив", "Pin")
        val refIcons = arrayOf(
            R.drawable.ic_ref_rear,
            R.drawable.ic_ref_front,
            R.drawable.ic_ref_tripod,
            R.drawable.ic_ref_pin
        )
        currentRefIndex = refEdge.coerceIn(0, refNames.size - 1)
        ivRefIcon.setImageResource(refIcons[currentRefIndex])
        tvRefLabel.text = refNames[currentRefIndex]
    }
}
