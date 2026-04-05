package com.glmreader.android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.glmreader.android.data.AppDatabase
import com.glmreader.android.data.entity.ProjectEntity
import com.glmreader.android.data.repository.ProjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel для ProjectListActivity.
 */
class ProjectListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    val projectRepository = ProjectRepository(
        database.projectDao(),
        database.groupDao(),
        database.objectDao()
    )

    /** Реактивный список проектов */
    val projects: StateFlow<List<ProjectEntity>> =
        projectRepository.getAllProjects()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    /** Создать новый проект */
    fun createProject(name: String, description: String = "") {
        viewModelScope.launch {
            projectRepository.createProject(name, description)
        }
    }

    /** Открыть проект (переход к списку измерений) */
    fun openProject(project: ProjectEntity) {
        // Навигация обрабатывается в Activity
    }

    /** Архивировать проект */
    fun archiveProject(uuid: String) {
        viewModelScope.launch {
            projectRepository.archiveProject(uuid)
        }
    }
}
