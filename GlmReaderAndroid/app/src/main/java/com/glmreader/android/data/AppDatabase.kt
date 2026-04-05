package com.glmreader.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glmreader.android.data.dao.*
import com.glmreader.android.data.entity.*

@Database(
    entities = [
        MeasurementEntity::class,
        NoteEntity::class,
        SettingsEntity::class,
        ProjectEntity::class,
        GroupEntity::class,
        ObjectEntity::class
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
    abstract fun noteDao(): NoteDao
    abstract fun settingsDao(): SettingsDao
    abstract fun projectDao(): ProjectDao
    abstract fun groupDao(): GroupDao
    abstract fun objectDao(): ObjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "glm_reader_database"
                )
                    .fallbackToDestructiveMigration() // For prototype only
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
