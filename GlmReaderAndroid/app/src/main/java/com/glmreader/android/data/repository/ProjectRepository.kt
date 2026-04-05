package com.glmreader.android.data.repository

import com.glmreader.android.data.dao.GroupDao
import com.glmreader.android.data.dao.ObjectDao
import com.glmreader.android.data.dao.ProjectDao
import com.glmreader.android.data.entity.GroupEntity
import com.glmreader.android.data.entity.ObjectEntity
import com.glmreader.android.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository для проектов, групп и объектов.
 */
class ProjectRepository(
    private val projectDao: ProjectDao,
    private val groupDao: GroupDao,
    private val objectDao: ObjectDao
) {
    // --- Projects ---

    fun getAllProjects(): Flow<List<ProjectEntity>> =
        projectDao.getAllActive()

    fun getAllProjectsIncludingArchived(): Flow<List<ProjectEntity>> =
        projectDao.getAll()

    suspend fun getProjectByUuid(uuid: String): ProjectEntity? =
        projectDao.getByUuid(uuid)

    suspend fun createProject(name: String, description: String = ""): Long =
        projectDao.insert(ProjectEntity(name = name, description = description))

    suspend fun updateProject(project: ProjectEntity) =
        projectDao.update(project)

    suspend fun archiveProject(uuid: String) =
        projectDao.archive(uuid)

    suspend fun deleteProject(project: ProjectEntity) =
        projectDao.delete(project)

    // --- Groups ---

    fun getGroupsForProject(projectId: String): Flow<List<GroupEntity>> =
        groupDao.getByProjectId(projectId)

    suspend fun getGroupByUuid(uuid: String): GroupEntity? =
        groupDao.getByUuid(uuid)

    suspend fun createGroup(projectId: String, name: String): Long =
        groupDao.insert(GroupEntity(projectId = projectId, name = name))

    suspend fun updateGroup(group: GroupEntity) =
        groupDao.update(group)

    suspend fun deleteGroup(group: GroupEntity) =
        groupDao.delete(group)

    // --- Objects ---

    fun getObjectsForGroup(groupId: String): Flow<List<ObjectEntity>> =
        objectDao.getByGroupId(groupId)

    suspend fun getObjectByUuid(uuid: String): ObjectEntity? =
        objectDao.getByUuid(uuid)

    suspend fun createObject(groupId: String, name: String): Long =
        objectDao.insert(ObjectEntity(groupId = groupId, name = name))

    suspend fun updateObject(obj: ObjectEntity) =
        objectDao.update(obj)

    suspend fun deleteObject(obj: ObjectEntity) =
        objectDao.delete(obj)
}
