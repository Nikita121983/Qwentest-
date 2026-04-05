package com.glmreader.android.data.dao

import androidx.room.*
import com.glmreader.android.data.entity.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM groups WHERE projectId = :projectId ORDER BY sortOrder ASC, createdAt DESC")
    fun getByProjectId(projectId: String): Flow<List<GroupEntity>>

    @Query("SELECT * FROM groups WHERE uuid = :uuid LIMIT 1")
    suspend fun getByUuid(uuid: String): GroupEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: GroupEntity): Long

    @Update
    suspend fun update(group: GroupEntity)

    @Delete
    suspend fun delete(group: GroupEntity)

    @Query("DELETE FROM groups WHERE projectId = :projectId")
    suspend fun deleteAllForProject(projectId: String)
}
