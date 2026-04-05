package com.glmreader.android.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "groups",
    indices = [
        Index(value = ["uuid"], unique = true),
        Index(value = ["projectId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["uuid"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uuid: String = java.util.UUID.randomUUID().toString(),
    val projectId: String,
    val name: String = "Новая группа",
    val sortOrder: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
