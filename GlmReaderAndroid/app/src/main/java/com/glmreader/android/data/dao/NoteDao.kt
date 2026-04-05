package com.glmreader.android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glmreader.android.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE measurementUuid = :measurementUuid ORDER BY version DESC")
    suspend fun getNotesForMeasurement(measurementUuid: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Query("UPDATE notes SET isPrimary = 0 WHERE measurementUuid = :measurementUuid")
    suspend fun demoteAllNotes(measurementUuid: String)
}
