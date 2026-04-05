package com.glmreader.android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.glmreader.android.data.AppDatabase
import com.glmreader.android.data.entity.MeasurementEntity
import com.glmreader.android.data.repository.MeasurementRepository
import com.glmreader.android.data.repository.ProjectRepository
import com.glmreader.android.protocol.BlePacketParser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel для MeasurementListActivity.
 * Управляет состоянием UI через StateFlow, отделяет логику от Activity.
 */
class MeasurementViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    val measurementRepository = MeasurementRepository(database.measurementDao())
    val projectRepository = ProjectRepository(
        database.projectDao(),
        database.groupDao(),
        database.objectDao()
    )

    /** Реактивный список измерений */
    val measurements: StateFlow<List<MeasurementEntity>> =
        measurementRepository.getAllActive()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    /** Обработка распарсенного BLE пакета */
    fun onBleMeasurement(parsed: BlePacketParser.ParsedMeasurement, projectId: String? = null) {
        viewModelScope.launch {
            val entity = parsed.toEntity(
                projectId = projectId,
                deviceName = "GLM 50 C"
            )
            measurementRepository.insert(entity)
        }
    }

    /** Удаление измерения */
    fun deleteMeasurement(uuid: String) {
        viewModelScope.launch {
            measurementRepository.softDelete(uuid)
        }
    }

    /** Получить количество измерений (для отладки) */
    suspend fun getActiveCount(): Int =
        measurementRepository.getActiveCount()
}
