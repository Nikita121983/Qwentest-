package com.glmreader.android.data.repository

import com.glmreader.android.data.dao.MeasurementDao
import com.glmreader.android.data.entity.MeasurementEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository для измерений. Абстракция над DAO для отделения бизнес-логики от БД.
 */
class MeasurementRepository(
    private val measurementDao: MeasurementDao
) {
    /** Поток всех активных измерений */
    fun getAllActive(): Flow<List<MeasurementEntity>> =
        measurementDao.getAllActive()

    /** Получить измерение по UUID */
    suspend fun getByUuid(uuid: String): MeasurementEntity? =
        measurementDao.getByUuid(uuid)

    /** Вставить измерение */
    suspend fun insert(measurement: MeasurementEntity) =
        measurementDao.insert(measurement)

    /** Обновить измерение */
    suspend fun update(measurement: MeasurementEntity) =
        measurementDao.update(measurement)

    /** Мягкое удаление (isDeleted = true) */
    suspend fun softDelete(uuid: String) =
        measurementDao.softDelete(uuid)

    /** Жёсткое удаление */
    suspend fun hardDelete(measurement: MeasurementEntity) =
        measurementDao.delete(measurement)

    /** Обновить порядок сортировки */
    suspend fun updateSortOrder(uuid: String, newOrder: Int) =
        measurementDao.updateSortOrder(uuid, newOrder)

    /** Получить количество активных измерений */
    suspend fun getActiveCount(): Int =
        measurementDao.getActiveCount()
}
