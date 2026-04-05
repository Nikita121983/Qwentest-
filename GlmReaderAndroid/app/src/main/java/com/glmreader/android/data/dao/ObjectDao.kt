package com.glmreader.android.data.dao

import androidx.room.*
import com.glmreader.android.data.entity.ObjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjectDao {
    @Query("SELECT * FROM objects WHERE groupId = :groupId ORDER BY sortOrder ASC, createdAt DESC")
    fun getByGroupId(groupId: String): Flow<List<ObjectEntity>>

    @Query("SELECT * FROM objects WHERE uuid = :uuid LIMIT 1")
    suspend fun getByUuid(uuid: String): ObjectEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: ObjectEntity): Long

    @Update
    suspend fun update(obj: ObjectEntity)

    @Delete
    suspend fun delete(obj: ObjectEntity)

    @Query("DELETE FROM objects WHERE groupId = :groupId")
    suspend fun deleteAllForGroup(groupId: String)
}
