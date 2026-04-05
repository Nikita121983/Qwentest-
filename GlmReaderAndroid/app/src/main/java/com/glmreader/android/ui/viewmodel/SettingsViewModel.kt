package com.glmreader.android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.glmreader.android.data.AppDatabase
import com.glmreader.android.data.entity.SettingsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для SettingsActivity.
 */
class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val settingsDao = database.settingsDao()

    private val _inclinoThreshold = MutableStateFlow(5.0)
    val inclinoThreshold: StateFlow<Double> = _inclinoThreshold.asStateFlow()

    private val _inclinoAutoSwitch = MutableStateFlow(true)
    val inclinoAutoSwitch: StateFlow<Boolean> = _inclinoAutoSwitch.asStateFlow()

    private val _themeMode = MutableStateFlow("light")
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            val thresholdEntity = settingsDao.getSetting("inclino_threshold")
            _inclinoThreshold.value = thresholdEntity?.value?.toDoubleOrNull() ?: 5.0

            val autoSwitchEntity = settingsDao.getSetting("inclino_auto_switch")
            _inclinoAutoSwitch.value = autoSwitchEntity?.value == "1"

            val themeEntity = settingsDao.getSetting("theme_mode")
            _themeMode.value = themeEntity?.value ?: "light"
        }
    }

    /** Обновить порог */
    fun setInclinoThreshold(degrees: Double) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "inclino_threshold", value = degrees.toString()))
            _inclinoThreshold.value = degrees
        }
    }

    /** Обновить автопереключение */
    fun setInclinoAutoSwitch(enabled: Boolean) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "inclino_auto_switch", value = if (enabled) "1" else "0"))
            _inclinoAutoSwitch.value = enabled
        }
    }

    /** Обновить тему */
    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            settingsDao.upsert(SettingsEntity(key = "theme_mode", value = mode))
            _themeMode.value = mode
        }
    }
}
