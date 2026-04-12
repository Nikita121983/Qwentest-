package com.glmreader.android

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.glmreader.android.ble.GlmBleManager
import com.glmreader.android.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GlmReaderApplication : Application() {

    // BLE Manager — ЖИВЁТ НА УРОВНЕ APPLICATION (не рвётся при смене Activity)
    @Volatile
    private var bleManager: GlmBleManager? = null

    override fun onCreate() {
        super.onCreate()

        // Применяем тему СРАЗУ при старте (синхронно, чтобы не было мерцания)
        runBlocking(Dispatchers.IO) {
            try {
                val db = AppDatabase.getDatabase(this@GlmReaderApplication)
                val mode = db.settingsDao().getSetting("theme_mode")?.value ?: "light"
                val nightMode = when (mode) {
                    "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                    "system" -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    else -> AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(nightMode)
            } catch (e: Exception) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    /**
     * Получить или создать GlmBleManager.
     * Один экземпляр на всё время жизни Application.
     */
    @Synchronized
    fun getOrCreateBleManager(context: Context): GlmBleManager {
        if (bleManager == null) {
            bleManager = GlmBleManager(context.applicationContext)
        }
        return bleManager!!
    }

    /**
     * Получить существующий BleManager (или null если ещё не создан).
     */
    fun getBleManager(): GlmBleManager? = bleManager

    /**
     * Отключить BLE при закрытии приложения.
     */
    fun disconnectBle() {
        bleManager?.disconnect()
        bleManager = null
    }

    override fun onTerminate() {
        super.onTerminate()
        disconnectBle()
    }
}
