package com.glmreader.android.ui

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.glmreader.android.R
import com.glmreader.android.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Экран настроек.
 */
class SettingsActivity : AppCompatActivity() {

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var tvThresholdValue: TextView
    private lateinit var seekBarThreshold: SeekBar
    private lateinit var switchAutoSwitch: SwitchMaterial
    private lateinit var radioGroupTheme: android.widget.RadioGroup

    // Флаг для предотвращения зацикливания при смене темы
    private var isThemeChanging = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        tvThresholdValue = findViewById(R.id.tvThresholdValue)
        seekBarThreshold = findViewById(R.id.seekBarThreshold)
        switchAutoSwitch = findViewById(R.id.switchAutoSwitch)
        radioGroupTheme = findViewById(R.id.radioGroupTheme)

        // Наблюдаем за настройками
        lifecycleScope.launch {
            viewModel.inclinoThreshold.collectLatest { threshold ->
                seekBarThreshold.progress = threshold.toInt()
                tvThresholdValue.text = "${threshold.toInt()}°"
            }
        }

        lifecycleScope.launch {
            viewModel.inclinoAutoSwitch.collectLatest { enabled ->
                switchAutoSwitch.isChecked = enabled
            }
        }

        lifecycleScope.launch {
            viewModel.themeMode.collectLatest { mode ->
                // Пропускаем обновление если тема только что изменена пользователем
                if (isThemeChanging) return@collectLatest

                val radioButtonId = when (mode) {
                    "light" -> R.id.radioLight
                    "dark" -> R.id.radioDark
                    else -> R.id.radioSystem
                }

                // Временно отключаем слушатель, чтобы check() не триггерил логику смены темы
                radioGroupTheme.setOnCheckedChangeListener(null)
                radioGroupTheme.check(radioButtonId)

                // Восстанавливаем слушатель
                setupThemeListener()
            }
        }

        // SeekBar — порог инклинометра
        seekBarThreshold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    tvThresholdValue.text = "${progress}°"
                    viewModel.setInclinoThreshold(progress.toDouble())
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Switch — автопереключение
        switchAutoSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setInclinoAutoSwitch(isChecked)
        }

        // RadioGroup — тема
        setupThemeListener()
    }

    private fun setupThemeListener() {
        radioGroupTheme.setOnCheckedChangeListener { _, checkedId ->
            if (isThemeChanging) return@setOnCheckedChangeListener
            isThemeChanging = true

            val mode = when (checkedId) {
                R.id.radioLight -> "light"
                R.id.radioDark -> "dark"
                R.id.radioSystem -> "system"
                else -> "light"
            }
            viewModel.setThemeMode(mode)

            // Применяем тему глобально - это пересоздаст activity
            val nightMode = when (mode) {
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                "system" -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                else -> AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)

            // Флаг снимется после пересоздания activity
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
