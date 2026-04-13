package com.glmreader.android.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

/**
 * Универсальный RFCOMM менеджер — НЕ знает о протоколе Bosch.
 * Отвечает ТОЛЬКО за:
 * - Подключение через insecure RFCOMM socket (без bonding)
 * - Чтение данных из InputStream → callback onDataReceived
 * - Запись данных в OutputStream
 * - Корректное закрытие соединения
 *
 * Аналог BluetoothConnection из Measuring Master.
 */
class BleConnectionManager(private val context: Context) {

    // Сокет и потоки
    private var rfcommSocket: BluetoothSocket? = null
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    // Поток чтения
    private var readThread: Thread? = null
    @Volatile private var isReading = false

    // Буфер приёма — потоко-безопасный через synchronized
    private val readBuffer = ByteArrayOutputStream()
    private val bufferLock = Object()

    // SPP UUID (стандартный, как в Measuring Master)
    private val SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")

    // Callbacks
    var onConnected: (() -> Unit)? = null
    var onDisconnected: ((String?) -> Unit)? = null // reason
    var onDataReceived: ((ByteArray) -> Unit)? = null // сырые байты
    var onRawChunk: ((ByteArray, Int) -> Unit)? = null // для debug: chunk + total bytes

    // Состояние
    @Volatile var isConnected = false
        private set
    @Volatile var isConnecting = false
        private set
    var connectedDeviceName: String? = null
        private set
    var connectedDeviceMac: String? = null
        private set

    // Счётчики для отладки
    @Volatile var totalBytesRead = 0
        private set
    @Volatile var totalBytesWritten = 0
        private set

    /**
     * Подключение к устройству по MAC-адресу.
     * Использует createInsecureRfcommSocketToServiceRecord — БЕЗ bonding.
     * Как в MM: BTDeviceManagerImpl.connect() — проверяет isConnected перед подключением.
     */
    @SuppressLint("MissingPermission")
    @Synchronized
    fun connect(macAddress: String) {
        Log.d("BleConn", "connect() called for $macAddress. isConnected=$isConnected, isConnecting=$isConnecting")
        if (isConnected) {
            Log.w("BleConn", "Already connected")
            return
        }
        if (isConnecting) {
            Log.w("BleConn", "Already connecting, skipping duplicate call")
            return
        }

        isConnecting = true
        Thread {
            try {
                val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
                val bluetoothAdapter = bluetoothManager.adapter
                val device = bluetoothAdapter.getRemoteDevice(macAddress)

                Log.d("BleConn", "Connecting to $macAddress (insecure RFCOMM, no bonding)")

                // Insecure socket — как Measuring Master
                rfcommSocket = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
                rfcommSocket?.connect()

                Log.d("BleConn", "✅ RFCOMM Connected: ${device.name}")

                // Инициализация
                isConnected = true
                isConnecting = false
                connectedDeviceName = device.name
                connectedDeviceMac = macAddress
                inputStream = rfcommSocket?.inputStream
                outputStream = rfcommSocket?.outputStream
                totalBytesRead = 0
                totalBytesWritten = 0

                // Запуск чтения
                isReading = true
                readThread = Thread(::readLoop, "BleReadThread")
                readThread?.start()

                onConnected?.invoke()

            } catch (e: Exception) {
                Log.e("BleConn", "❌ Connection failed: ${e.message}")
                isConnected = false
                isConnecting = false
                onDisconnected?.invoke(e.message)
            }
        }.start()
    }

    /**
     * Запись байт в OutputStream.
     * Вызывает onRawChunk для отладки.
     */
    @Synchronized
    fun write(data: ByteArray): Boolean {
        if (!isConnected || outputStream == null) {
            Log.w("BleConn", "Write failed: not connected")
            return false
        }
        return try {
            outputStream?.write(data)
            outputStream?.flush()
            totalBytesWritten += data.size
            Log.d("BleConn", "📤 Wrote ${data.size} bytes: ${data.joinToString(" ") { "%02X".format(it) }}")
            true
        } catch (e: Exception) {
            Log.e("BleConn", "Write error: ${e.message}")
            false
        }
    }

    /**
     * Поток чтения — читает чанки из InputStream и передаёт через callback.
     * Весь доступ к readBuffer — внутри synchronized(bufferLock).
     */
    private fun readLoop() {
        val tempBuf = ByteArray(1024)
        try {
            Log.d("BleConn", "Read thread started")
            while (isReading && isConnected && inputStream != null) {
                val count = inputStream?.read(tempBuf) ?: -1
                if (count > 0) {
                    synchronized(bufferLock) {
                        totalBytesRead += count
                        val chunk = tempBuf.copyOf(count)
                        readBuffer.write(chunk)

                        // Для отладки: отдаём сырой чанк
                        onRawChunk?.invoke(chunk, totalBytesRead)

                        // Обрабатываем буфер
                        processBuffer()
                    }
                } else if (count == -1) {
                    Log.w("BleConn", "Stream closed (EOF)")
                    break
                }
            }
        } catch (e: Exception) {
            Log.e("BleConn", "Read error: ${e.message}")
        } finally {
            // ВАЖНО: При выходе из цикла (ошибка или EOF) обновляем статус,
            // иначе AutoConnectThread будет думать, что мы подключены, и спать вечно.
            if (isConnected) {
                isConnected = false
                isConnecting = false
                Log.w("BleConn", "ReadLoop finished, disconnected.")
                onDisconnected?.invoke("Stream error")
            }
        }
    }

    /**
     * Обработка буфера — ищет пакеты C0 55 и передаёт их через onDataReceived.
     * ДОЛЖНА вызываться только внутри synchronized(bufferLock).
     */
    private fun processBuffer() {
        val data = readBuffer.toByteArray()
        if (data.size < 4) return

        // Ищем начало пакета C0 55
        var startIdx = -1
        for (i in 0 until data.size - 1) {
            if (data[i] == 0xC0.toByte() && data[i + 1] == 0x55.toByte()) {
                startIdx = i
                break
            }
        }

        // Нет заголовка — очищаем буфер если слишком большой
        if (startIdx == -1) {
            if (readBuffer.size() > 2048) {
                Log.w("BleConn", "No header found, clearing buffer (${readBuffer.size()} bytes)")
                readBuffer.reset()
            }
            return
        }

        // Обрезаем мусор до заголовка
        if (startIdx > 0) {
            Log.d("BleConn", "Skipping $startIdx bytes before header")
            val validData = data.copyOfRange(startIdx, data.size)
            readBuffer.reset()
            readBuffer.write(validData)
        }

        // Читаем длину payload
        val packetData = readBuffer.toByteArray()
        if (packetData.size >= 4) {
            val payloadLen = packetData[2].toInt() and 0xFF
            val expectedSize = 3 + payloadLen + 1 // header(3) + payload + CRC(1)

            if (packetData.size >= expectedSize) {
                // Полный пакет готов
                val fullPacket = packetData.copyOf(expectedSize)
                readBuffer.reset()

                // Оставшиеся байты — в буфер для следующего пакета
                if (packetData.size > expectedSize) {
                    readBuffer.write(packetData.copyOfRange(expectedSize, packetData.size))
                }

                Log.d("BleConn", "📦 Packet ready: ${expectedSize} bytes")
                onDataReceived?.invoke(fullPacket)
            }
        }
    }

    /**
     * Закрытие соединения.
     * Корректно останавливает readThread и закрывает сокет.
     */
    fun disconnect() {
        Log.d("BleConn", "Disconnecting...")
        val wasConnected = isConnected
        isConnected = false
        isReading = false

        try {
            readThread?.interrupt()
            readThread?.join(1000)
            rfcommSocket?.close()
            inputStream?.close()
            outputStream?.close()
        } catch (e: Exception) {
            Log.w("BleConn", "Disconnect error: ${e.message}")
        } finally {
            synchronized(bufferLock) {
                readBuffer.reset()
            }
            rfcommSocket = null
            inputStream = null
            outputStream = null
            readThread = null
        }

        if (wasConnected) {
            onDisconnected?.invoke(null) // null = normal disconnect
        }
        Log.d("BleConn", "Disconnected")
    }

    /** Статус для отладки */
    fun getStatusInfo(): String {
        return "Connected: $isConnected | Device: $connectedDeviceName | " +
                "MAC: $connectedDeviceMac | RX: $totalBytesRead | TX: $totalBytesWritten | " +
                "ReadThread: ${readThread?.isAlive}"
    }
}
