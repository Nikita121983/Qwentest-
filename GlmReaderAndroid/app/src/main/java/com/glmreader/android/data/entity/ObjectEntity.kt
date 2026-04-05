package com.glmreader.android.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "objects",
    indices = [
        Index(value = ["uuid"], unique = true),
        Index(value = ["groupId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["uuid"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ObjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uuid: String = java.util.UUID.randomUUID().toString(),
    val groupId: String,
    val name: String = "Новый объект",
    val sortOrder: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
