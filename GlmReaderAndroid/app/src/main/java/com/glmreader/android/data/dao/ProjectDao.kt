package com.glmreader.android.data.dao

import androidx.room.*
import com.glmreader.android.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects WHERE isArchived = 0 ORDER BY sortOrder ASC, createdAt DESC")
    fun getAllActive(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects ORDER BY sortOrder ASC, createdAt DESC")
    fun getAll(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE uuid = :uuid LIMIT 1")
    suspend fun getByUuid(uuid: String): ProjectEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(project: ProjectEntity): Long

    @Update
    suspend fun update(project: ProjectEntity)

    @Query("UPDATE projects SET isArchived = 1, updatedAt = :timestamp WHERE uuid = :uuid")
    suspend fun archive(uuid: String, timestamp: Long = System.currentTimeMillis())

    @Delete
    suspend fun delete(project: ProjectEntity)
}
