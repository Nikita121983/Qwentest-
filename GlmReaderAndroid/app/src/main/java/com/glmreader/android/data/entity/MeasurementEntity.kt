package com.glmreader.android.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "measurements",
    indices = [Index(value = ["uuid"], unique = true)]
)
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uuid: String = java.util.UUID.randomUUID().toString(),
    val sortOrder: Int = 0,
    val measurementType: Int = 1, // devMode: 1=Direct, 10=IndHeight...
    val refEdge: Int = 0, // 0=Rear, 1=Tripod...
    val resultValue: Double = 0.0,
    val comp1Value: Double = 0.0,
    val comp2Value: Double = 0.0,
    val comp3Value: Double = 0.0,
    val angleDeg: Double = 0.0,
    val laserOn: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isDeleted: Boolean = false,
    val projectId: String? = null,
    val groupId: String? = null,
    val objectId: String? = null,
    val deviceName: String = "Unknown",
    val protocolVersion: Int = 1,
    val blePacketHex: String? = null,
    val metadataJson: String? = null,
    // Inclino fields
    val isAutoDetected: Boolean = true,
    val calculatedValue: Double? = null,
    val calculationType: String? = null // "direct", "height", "width"
)
