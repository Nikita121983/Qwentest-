package com.glmreader.android.ui

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glmreader.android.R
import com.glmreader.android.ble.GlmBleManager

/**
 * Утилита для показа BLE диалога подключения.
 * Переиспользуется в ProjectListActivity и MeasurementListActivity.
 * НЕ затирает callback'и — использует observer pattern.
 */
object BleDialogHelper {

    fun show(context: Context, bleManager: GlmBleManager) {
        val view = (context as android.app.Activity).layoutInflater.inflate(R.layout.dialog_ble_connection, null)
        val tvStatus = view.findViewById<TextView>(R.id.tvBleStatus)
        val btnToggle = view.findViewById<Button>(R.id.btnToggleBle)
        val btnScan = view.findViewById<Button>(R.id.btnScan)
        val btnConnect = view.findViewById<Button>(R.id.btnConnect)
        val tvConnStatus = view.findViewById<TextView>(R.id.tvConnectionStatus)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDevices)
        val btnForget = view.findViewById<Button>(R.id.btnForget)

        val bluetoothManager = context.getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter = bluetoothManager.adapter
        val prefs = context.getSharedPreferences("ble_prefs", Context.MODE_PRIVATE)

        var lastMac = prefs.getString("last_device_mac", null)
        var lastName = prefs.getString("last_device_name", null)

        val foundDevices = mutableListOf<BleDeviceItem>()

        // Forward declaration — updateUi uses curMac/curName snapshots
        lateinit var updateUi: () -> Unit
        updateUi = {
            val curMac = lastMac
            val curName = lastName
            val isBleOn = bluetoothAdapter.isEnabled
            tvStatus.text = if (isBleOn) "Bluetooth ON" else "Bluetooth OFF"
            tvStatus.setTextColor(ContextCompat.getColor(context, if (isBleOn) R.color.status_success else R.color.status_error))

            btnToggle.text = if (isBleOn) "Settings" else "Enable BT"
            btnScan.isEnabled = isBleOn

            if (bleManager.isConnected) {
                tvConnStatus.text = "✅ Connected: ${bleManager.connectedDeviceName}"
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.status_success))
                btnConnect.text = "Disconnect"
                btnConnect.isEnabled = true
                btnScan.isEnabled = false
                btnScan.text = "Stop"
                btnForget.isEnabled = true  // Можно забыть даже когда подключён
            } else if (bleManager.isScanning) {
                tvConnStatus.text = "Scanning..."
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.status_info))
                btnConnect.text = "Connect"
                btnConnect.isEnabled = curMac != null
                btnScan.text = "Stop"
                btnForget.isEnabled = false
            } else {
                tvConnStatus.text = if (curMac != null) "Saved: $curName" else "Not connected"
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.text_secondary))
                btnConnect.text = "Connect"
                btnConnect.isEnabled = curMac != null
                btnScan.text = "Scan"
                btnForget.isEnabled = curMac != null
            }
        }

        val deviceAdapter = BleDeviceAdapter(foundDevices) { selected ->
            (context as android.app.Activity).runOnUiThread {
                if (selected != null) {
                    lastMac = selected.mac
                    lastName = selected.name
                    prefs.edit()
                        .putString("last_device_mac", selected.mac)
                        .putString("last_device_name", selected.name)
                        .apply()
                    tvConnStatus.text = "Selected: ${selected.name}"
                }
                updateUi()
            }
        }
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = deviceAdapter

        // Observer для подключения — НЕ затирает другие
        val connObserver: (Boolean) -> Unit = { connected ->
            (context as android.app.Activity).runOnUiThread { updateUi() }
        }
        bleManager.observeConnectionState(connObserver)

        val deviceObserver: (String, String) -> Unit = { mac, name ->
            (context as android.app.Activity).runOnUiThread {
                if (foundDevices.none { it.mac == mac }) {
                    foundDevices.add(BleDeviceItem(name, mac, false))
                    deviceAdapter.notifyDataSetChanged()
                    updateUi()
                }
            }
        }
        bleManager.observeDeviceFound(deviceObserver)

        // Toggle — открыть настройки BT (единственный способ выключить)
        btnToggle.setOnClickListener {
            context.startActivity(Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS))
        }

        btnScan.setOnClickListener {
            if (bleManager.isScanning) {
                bleManager.stopScan()
            } else {
                foundDevices.clear()
                val curMac = lastMac
                val curName = lastName
                if (curMac != null && curName != null) {
                    foundDevices.add(BleDeviceItem(curName, curMac, true))
                }
                deviceAdapter.notifyDataSetChanged()
                bleManager.startScan()
            }
            updateUi()
        }

        // Connect / Disconnect toggle
        btnConnect.setOnClickListener {
            if (bleManager.isConnected) {
                bleManager.disconnect()
                Toast.makeText(context, "Отключено", Toast.LENGTH_SHORT).show()
            } else {
                val savedMac = lastMac
                if (savedMac != null) {
                    bleManager.connect(savedMac)
                    Toast.makeText(context, "Подключение...", Toast.LENGTH_SHORT).show()
                }
            }
            updateUi()
        }

        btnForget.setOnClickListener {
            // Если подключён — сначала отключаем
            if (bleManager.isConnected) {
                bleManager.disconnect()
            }
            prefs.edit().remove("last_device_mac").remove("last_device_name").apply()
            lastMac = null
            lastName = null
            foundDevices.clear()
            deviceAdapter.notifyDataSetChanged()
            updateUi()
            Toast.makeText(context, "Устройство забыто", Toast.LENGTH_SHORT).show()
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("Connect to Ruler")
            .setView(view)
            .setNegativeButton("Close") { _, _ ->
                bleManager.stopScan()
                // Отписываемся при закрытии диалога
                // (на самом деле можно не отписываться, но для чистоты)
            }
            .create()

        dialog.show()
        updateUi()
    }
}
