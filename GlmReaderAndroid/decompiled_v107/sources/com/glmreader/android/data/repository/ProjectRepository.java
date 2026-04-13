package com.glmreader.android.data.repository;

import com.glmreader.android.data.dao.GroupDao;
import com.glmreader.android.data.dao.ObjectDao;
import com.glmreader.android.data.dao.ProjectDao;
import com.glmreader.android.data.entity.GroupEntity;
import com.glmreader.android.data.entity.ObjectEntity;
import com.glmreader.android.data.entity.ProjectEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: ProjectRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\u0016\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\f0\u000b2\u0006\u0010 \u001a\u00020\u0011J\u0018\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\u001e\u0010\"\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010%J\u001a\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\f0\u000b2\u0006\u0010)\u001a\u00020\u0011J\u0018\u0010*\u001a\u0004\u0018\u00010(2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\u001e\u0010+\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020(H\u0086@¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020\u00192\u0006\u0010-\u001a\u00020(H\u0086@¢\u0006\u0002\u0010.R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/glmreader/android/data/repository/ProjectRepository;", "", "projectDao", "Lcom/glmreader/android/data/dao/ProjectDao;", "groupDao", "Lcom/glmreader/android/data/dao/GroupDao;", "objectDao", "Lcom/glmreader/android/data/dao/ObjectDao;", "<init>", "(Lcom/glmreader/android/data/dao/ProjectDao;Lcom/glmreader/android/data/dao/GroupDao;Lcom/glmreader/android/data/dao/ObjectDao;)V", "getAllProjects", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/glmreader/android/data/entity/ProjectEntity;", "getAllProjectsIncludingArchived", "getProjectByUuid", "uuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createProject", "", "name", "description", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProject", "", "project", "(Lcom/glmreader/android/data/entity/ProjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "archiveProject", "deleteProject", "getGroupsForProject", "Lcom/glmreader/android/data/entity/GroupEntity;", "projectId", "getGroupByUuid", "createGroup", "updateGroup", "group", "(Lcom/glmreader/android/data/entity/GroupEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGroup", "getObjectsForGroup", "Lcom/glmreader/android/data/entity/ObjectEntity;", "groupId", "getObjectByUuid", "createObject", "updateObject", "obj", "(Lcom/glmreader/android/data/entity/ObjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteObject", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProjectRepository {
    private final GroupDao groupDao;
    private final ObjectDao objectDao;
    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao, GroupDao groupDao, ObjectDao objectDao) {
        Intrinsics.checkNotNullParameter(projectDao, "projectDao");
        Intrinsics.checkNotNullParameter(groupDao, "groupDao");
        Intrinsics.checkNotNullParameter(objectDao, "objectDao");
        this.projectDao = projectDao;
        this.groupDao = groupDao;
        this.objectDao = objectDao;
    }

    public final Flow<List<ProjectEntity>> getAllProjects() {
        return this.projectDao.getAllActive();
    }

    public final Flow<List<ProjectEntity>> getAllProjectsIncludingArchived() {
        return this.projectDao.getAll();
    }

    public final Object getProjectByUuid(String uuid, Continuation<? super ProjectEntity> continuation) {
        return this.projectDao.getByUuid(uuid, continuation);
    }

    public static /* synthetic */ Object createProject$default(ProjectRepository projectRepository, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return projectRepository.createProject(str, str2, continuation);
    }

    public final Object createProject(String name, String description, Continuation<? super Long> continuation) {
        return this.projectDao.insert(new ProjectEntity(0, null, name, description, 0, 0L, 0L, false, 243, null), continuation);
    }

    public final Object updateProject(ProjectEntity project, Continuation<? super Unit> continuation) {
        Object update = this.projectDao.update(project, continuation);
        return update == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? update : Unit.INSTANCE;
    }

    public final Object archiveProject(String uuid, Continuation<? super Unit> continuation) {
        Object archive$default = ProjectDao.archive$default(this.projectDao, uuid, 0L, continuation, 2, null);
        return archive$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? archive$default : Unit.INSTANCE;
    }

    public final Object deleteProject(ProjectEntity project, Continuation<? super Unit> continuation) {
        Object delete = this.projectDao.delete(project, continuation);
        return delete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? delete : Unit.INSTANCE;
    }

    public final Flow<List<GroupEntity>> getGroupsForProject(String projectId) {
        Intrinsics.checkNotNullParameter(projectId, "projectId");
        return this.groupDao.getByProjectId(projectId);
    }

    public final Object getGroupByUuid(String uuid, Continuation<? super GroupEntity> continuation) {
        return this.groupDao.getByUuid(uuid, continuation);
    }

    public final Object createGroup(String projectId, String name, Continuation<? super Long> continuation) {
        return this.groupDao.insert(new GroupEntity(0, null, projectId, name, 0, 0L, 51, null), continuation);
    }

    public final Object updateGroup(GroupEntity group, Continuation<? super Unit> continuation) {
        Object update = this.groupDao.update(group, continuation);
        return update == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? update : Unit.INSTANCE;
    }

    public final Object deleteGroup(GroupEntity group, Continuation<? super Unit> continuation) {
        Object delete = this.groupDao.delete(group, continuation);
        return delete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? delete : Unit.INSTANCE;
    }

    public final Flow<List<ObjectEntity>> getObjectsForGroup(String groupId) {
        Intrinsics.checkNotNullParameter(groupId, "groupId");
        return this.objectDao.getByGroupId(groupId);
    }

    public final Object getObjectByUuid(String uuid, Continuation<? super ObjectEntity> continuation) {
        return this.objectDao.getByUuid(uuid, continuation);
    }

    public final Object createObject(String groupId, String name, Continuation<? super Long> continuation) {
        return this.objectDao.insert(new ObjectEntity(0, null, groupId, name, 0, 0L, 51, null), continuation);
    }

    public final Object updateObject(ObjectEntity obj, Continuation<? super Unit> continuation) {
        Object update = this.objectDao.update(obj, continuation);
        return update == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? update : Unit.INSTANCE;
    }

    public final Object deleteObject(ObjectEntity obj, Continuation<? super Unit> continuation) {
        Object delete = this.objectDao.delete(obj, continuation);
        return delete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? delete : Unit.INSTANCE;
    }
}
