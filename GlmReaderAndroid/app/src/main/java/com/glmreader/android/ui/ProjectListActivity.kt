package com.glmreader.android.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.GlmReaderApplication
import com.glmreader.android.R
import com.glmreader.android.ble.GlmBleManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import com.glmreader.android.data.entity.ProjectEntity
import com.glmreader.android.ui.viewmodel.ProjectListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Экран списка проектов. Первый экран приложения.
 */
class ProjectListActivity : AppCompatActivity() {

    private val viewModel: ProjectListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var adapter: ProjectAdapter

    // BLE — ЖИВЁТ НА УРОВНЕ APPLICATION
    private lateinit var bleManager: GlmBleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Версия и дата сборки в title
        val pkgInfo = packageManager.getPackageInfo(packageName, 0)
        val verName = pkgInfo.versionName ?: "?"
        val buildDate = "2026-04-12"
        supportActionBar?.title = "Мои проекты v$verName/$buildDate"

        recyclerView = findViewById(R.id.recyclerView)
        tvEmpty = findViewById(R.id.tvEmpty)
        val fabAddProject: FloatingActionButton = findViewById(R.id.fabAddProject)
        val tvBuildInfo: TextView = findViewById(R.id.tvBuildInfo)

        // Версия и дата сборки
        tvBuildInfo.text = "Build: v$verName / $buildDate"

        // Адаптер
        adapter = ProjectAdapter(mutableListOf()) { project ->
            openProject(project)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Наблюдаем за проектами
        lifecycleScope.launch {
            viewModel.projects.collectLatest { projects ->
                adapter.updateData(projects)
                tvEmpty.visibility = if (projects.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        // FAB — создать проект
        fabAddProject.setOnClickListener {
            showCreateProjectDialog()
        }

        // BLE из Application — подключение держится между Activity
        val app = application as GlmReaderApplication
        bleManager = app.getOrCreateBleManager(this)
        setupBleStatus()
        checkBlePermissions()

        // BroadcastReceiver регистрируется автоматически в GlmBleManager.init()

        // Автоподключение к сохранённому устройству (как в MM/MO)
        startAutoConnectIfSaved()
    }

    override fun onResume() {
        super.onResume()
        // Убедимся, что ресивер зарегистрирован (на случай если приложение было убито)
        bleManager.registerBtReceiver()
    }

    // НЕ вызываем unregisterBtReceiver здесь, чтобы автоподключение работало в фоне

    private fun showCreateProjectDialog() {
        val editText = EditText(this).apply {
            hint = "Название проекта"
        }

        AlertDialog.Builder(this)
            .setTitle("Новый проект")
            .setView(editText)
            .setPositiveButton("Создать") { _, _ ->
                val name = editText.text.toString().trim()
                if (name.isNotEmpty()) {
                    viewModel.createProject(name)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun openProject(project: ProjectEntity) {
        // Переход к списку измерений с projectId
        val intent = Intent(this, MeasurementListActivity::class.java).apply {
            putExtra("project_uuid", project.uuid)
            putExtra("project_name", project.name)
        }
        startActivity(intent)
    }

    // ==================== BLE ====================

    private fun setupBleStatus() {
        bleManager.onConnectionStateChanged = { connected ->
            runOnUiThread {
                invalidateOptionsMenu()
            }
        }
    }

    private fun checkBlePermissions() {
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.values.all { it }) bleManager.startScan()
        else Toast.makeText(this, "Нет прав на BLE", Toast.LENGTH_SHORT).show()
    }

    // ==================== MENU ====================

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
        R.id.action_settings -> {
            startActivity(Intent(this, SettingsActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showBleDialog() {
        BleDialogHelper.show(this, bleManager)
    }

    // ==================== AUTO CONNECT ====================

    /**
     * Автоподключение к сохранённому устройству при старте (как в MM/MO).
     */
    private fun startAutoConnectIfSaved() {
        val prefs = getSharedPreferences("ble_prefs", MODE_PRIVATE)
        val savedMac = prefs.getString("last_device_mac", null)
        val savedName = prefs.getString("last_device_name", null)

        if (savedMac != null) {
            android.util.Log.d("ProjectList", "Auto-connecting to saved device: $savedMac ($savedName)")
            bleManager.startAutoConnect(savedMac, savedName)
        } else {
            android.util.Log.d("ProjectList", "No saved device — auto-connect not started")
        }
    }
    // НЕ останавливаем автоподключение в onDestroy — пусть работает в фоне
}
