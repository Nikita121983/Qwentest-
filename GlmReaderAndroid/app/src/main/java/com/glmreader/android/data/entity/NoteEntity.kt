package com.glmreader.android.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    indices = [Index(value = ["measurementUuid"])]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val measurementUuid: String,
    val version: Int = 1,
    val text: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isPrimary: Boolean = true,
    val isVoice: Boolean = false,
    val mediaPath: String? = null
)
