package com.glmreader.android

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.glmreader.android.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GlmReaderApplication : Application() {

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
}
