package com.glmreader.android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.glmreader.android.data.AppDatabase
import com.glmreader.android.data.entity.SettingsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * ViewModel для SettingsActivity.
 */
class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val settingsDao = database.settingsDao()

    /** Порог инклинометра (по умолчанию 5°) */
    val inclinoThreshold: Flow<Double> = settingsDao.getSettingFlow("inclino_threshold", "5")
        .map { it.toDoubleOrNull() ?: 5.0 }

    /** Автопереключение типа */
    val inclinoAutoSwitch: Flow<Boolean> = settingsDao.getSettingFlow("inclino_auto_switch", "1")
        .map { it == "1" }

    /** Режим темы */
    val themeMode: Flow<String> = settingsDao.getSettingFlow("theme_mode", "light")

    /** Обновить порог */
    fun setInclinoThreshold(degrees: Double) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "inclino_threshold", value = degrees.toString()))
        }
    }

    /** Обновить автопереключение */
    fun setInclinoAutoSwitch(enabled: Boolean) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "inclino_auto_switch", value = if (enabled) "1" else "0"))
        }
    }

    /** Обновить тему */
    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "theme_mode", value = mode))
        }
    }
}
