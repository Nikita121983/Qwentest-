package com.glmreader.android.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.ParcelUuid
import android.util.Log
import com.glmreader.android.protocol.BlePacketParser
import java.util.UUID

class GlmBleManager(context: Context) {

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter = bluetoothManager.adapter
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

    private var gatt: BluetoothGatt? = null

    var isScanning = false
        private set
    var isConnected = false
        private set
    var connectedDeviceName: String? = null
        private set

    var onDeviceFound: ((String, String) -> Unit)? = null
    var onDataReceived: ((ByteArray) -> Unit)? = null
    var onParsedMeasurement: ((BlePacketParser.ParsedMeasurement) -> Unit)? = null
    var onConnectionStateChanged: ((Boolean) -> Unit)? = null

    companion object {
        val SERVICE_UUID: UUID = UUID.fromString("02A6C0D0-0451-4000-B000-FB3210111989")
        val CHAR_UUID: UUID = UUID.fromString("02A6C0D1-0451-4000-B000-FB3210111989")
        val CCCD_UUID: UUID = UUID.fromString("00002902-0000-1000-8000-00805F9B34FB")
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val device = result.device
            val name = device.name ?: "Unknown"
            val mac = device.address

            // Логируем все найденные устройства, особенно Bosch
            Log.d("BLE_SCAN", "Found: $name | $mac")
            if (name.contains("Bosch", ignoreCase = true) || name.contains("GLM", ignoreCase = true)) {
                Log.w("BLE_SCAN", ">>> BOSCH DEVICE FOUND: $name ($mac)")
            }

            onDeviceFound?.invoke(mac, name)
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e("BLE", "Scan failed: $errorCode")
            isScanning = false
            // Можно добавить callback для ошибки сканирования
        }
    }

    @SuppressLint("MissingPermission")
    fun startScan() {
        if (isScanning || !bluetoothAdapter.isEnabled) return
        isScanning = true
        Log.d("BLE", "Scan started (no filter)")

        // Убираем фильтр полностью - сканируем все устройства
        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        try {
            // Передаем null вместо списка фильтров - это сканирует ВСЕ устройства
            bluetoothLeScanner.startScan(
                null, // no filters - scan everything
                scanSettings,
                scanCallback
            )
            Log.d("BLE", "Scanner started successfully")
        } catch (e: Exception) {
            Log.e("BLE", "startScan exception", e)
            isScanning = false
        }
    }

    fun stopScan() {
        if (!isScanning) return
        isScanning = false
        try {
            bluetoothLeScanner.stopScan(scanCallback)
        } catch (e: Exception) {
            Log.w("BLE", "Stop scan error", e)
        }
        Log.d("BLE", "Scan stopped")
    }

    @SuppressLint("MissingPermission")
    fun connect(macAddress: String) {
        stopScan()
        try {
            val device: BluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress)
            gatt = device.connectGatt(null, false, gattCallback)
            Log.d("BLE", "Connecting to $macAddress")
        } catch (e: Exception) {
            Log.e("BLE", "Connect error", e)
            onConnectionStateChanged?.invoke(false)
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            Log.d("BLE", "onConnectionStateChange: status=$status, newState=$newState")
            if (newState == android.bluetooth.BluetoothProfile.STATE_CONNECTED) {
                Log.d("BLE", "Connected!")
                isConnected = true
                connectedDeviceName = gatt.device.name
                onConnectionStateChanged?.invoke(true)
                try {
                    gatt.discoverServices()
                } catch (e: Exception) {
                    Log.e("BLE", "discoverServices error", e)
                }
            } else if (newState == android.bluetooth.BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("BLE", "Disconnected")
                isConnected = false
                onConnectionStateChanged?.invoke(false)
            }
        }

        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            Log.d("BLE", "onServicesDiscovered: status=$status")
            try {
                val service = gatt.getService(SERVICE_UUID)
                if (service != null) {
                    Log.d("BLE", "Service found")
                    val characteristic = service.getCharacteristic(CHAR_UUID)
                    if (characteristic != null) {
                        Log.d("BLE", "Characteristic found")
                        gatt.setCharacteristicNotification(characteristic, true)
                        val descriptor = characteristic.getDescriptor(CCCD_UUID)
                        if (descriptor != null) {
                            descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                            gatt.writeDescriptor(descriptor)
                            Log.d("BLE", "Notifications enabled")
                        } else {
                            Log.e("BLE", "CCCD descriptor not found")
                        }
                    } else {
                        Log.e("BLE", "Characteristic not found")
                    }
                } else {
                    Log.e("BLE", "Service not found")
                }
            } catch (e: Exception) {
                Log.e("BLE", "onServicesDiscovered error", e)
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray
        ) {
            val hexDump = value.joinToString(" ") { "%02X".format(it) }
            Log.d("BLE", "Received ${value.size} bytes: $hexDump")

            onDataReceived?.invoke(value)

            // Попытка распарсить пакет
            val parsed = BlePacketParser.parse(value)
            if (parsed != null) {
                Log.d("BLE", "Parsed: type=${parsed.devMode}, result=${parsed.resultValue}m, angle=${parsed.comp2Value}°")
                onParsedMeasurement?.invoke(parsed)
            } else {
                Log.d("BLE", "Failed to parse packet (too short or invalid header)")
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun disconnect() {
        try {
            gatt?.close()
        } catch (e: Exception) {
            Log.w("BLE", "Disconnect error", e)
        }
        gatt = null
        isConnected = false
        onConnectionStateChanged?.invoke(false)
        Log.d("BLE", "Disconnected")
    }
}
