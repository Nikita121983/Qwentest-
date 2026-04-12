package com.glmreader.android.ui

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
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
                btnConnect.isEnabled = lastMac != null
            }
        }
        recycler.layoutManager = LinearLayoutManager(context)
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
            tvStatus.setTextColor(ContextCompat.getColor(context, if (isBleOn) R.color.status_success else R.color.status_error))
            btnToggle.text = if (isBleOn) "Turn Off" else "Turn On"
            btnScan.isEnabled = isBleOn

            if (bleManager.isConnected) {
                tvConnStatus.text = "Connected to ${bleManager.connectedDeviceName}"
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.status_success))
                btnScan.text = "Stop"
                btnConnect.isEnabled = true
                btnForget.isEnabled = false
            } else if (bleManager.isScanning) {
                tvConnStatus.text = "Scanning..."
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.status_info))
                btnScan.text = "Stop"
                btnConnect.isEnabled = false
                btnForget.isEnabled = false
            } else {
                tvConnStatus.text = if (curMac != null) "Saved: $curName" else "Not connected"
                tvConnStatus.setTextColor(ContextCompat.getColor(context, R.color.text_secondary))
                btnScan.text = "Scan"
                btnConnect.isEnabled = curMac != null
                btnForget.isEnabled = curMac != null
            }
        }

        bleManager.onDeviceFound = { mac, name ->
            (context as android.app.Activity).runOnUiThread {
                if (foundDevices.none { it.mac == mac }) {
                    foundDevices.add(BleDeviceItem(name, mac, false))
                    deviceAdapter.notifyDataSetChanged()
                }
            }
        }

        btnToggle.setOnClickListener {
            context.startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1)
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
            (context as android.app.Activity).runOnUiThread { updateUi() }
            origConn?.invoke(connected)
        }

        val dialog = AlertDialog.Builder(context)
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
