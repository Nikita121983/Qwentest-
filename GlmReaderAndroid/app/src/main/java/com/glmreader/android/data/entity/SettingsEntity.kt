package com.glmreader.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
    val key: String,
    val value: String = "",
    val updatedAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val KEY_INCLINO_MODE = "inclino_mode" // auto, ask, voice, manual
        const val KEY_INCLINO_THRESHOLD = "inclino_threshold" // e.g. "5.0"
        const val KEY_THEME_MODE = "theme_mode"
        const val KEY_EXPORT_HEADERS = "export_include_headers"
    }
}
