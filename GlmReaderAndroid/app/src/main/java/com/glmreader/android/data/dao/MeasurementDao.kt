package com.glmreader.android.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.glmreader.android.data.entity.MeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM measurements WHERE isDeleted = 0 ORDER BY sortOrder ASC, timestamp DESC")
    fun getAllActive(): Flow<List<MeasurementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(measurement: MeasurementEntity): Long

    @Query("UPDATE measurements SET isDeleted = 1, updatedAt = :timestamp WHERE uuid = :uuid")
    suspend fun softDelete(uuid: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE measurements SET sortOrder = :newOrder WHERE uuid = :uuid")
    suspend fun updateSortOrder(uuid: String, newOrder: Int)

    @Query("SELECT * FROM measurements WHERE uuid = :uuid LIMIT 1")
    suspend fun getByUuid(uuid: String): MeasurementEntity?

    @Update
    suspend fun update(measurement: MeasurementEntity)

    @Delete
    suspend fun delete(measurement: MeasurementEntity)

    @Query("SELECT COUNT(*) FROM measurements WHERE isDeleted = 0")
    suspend fun getActiveCount(): Int
}
