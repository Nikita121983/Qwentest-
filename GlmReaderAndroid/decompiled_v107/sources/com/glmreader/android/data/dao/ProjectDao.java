package com.glmreader.android.data.dao;

import com.glmreader.android.data.entity.ProjectEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: ProjectDao.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ \u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\fH§@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000e¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/glmreader/android/data/dao/ProjectDao;", "", "getAllActive", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/glmreader/android/data/entity/ProjectEntity;", "getAll", "getByUuid", "uuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "project", "(Lcom/glmreader/android/data/entity/ProjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "archive", "timestamp", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface ProjectDao {
    Object archive(String str, long j, Continuation<? super Unit> continuation);

    Object delete(ProjectEntity projectEntity, Continuation<? super Unit> continuation);

    Flow<List<ProjectEntity>> getAll();

    Flow<List<ProjectEntity>> getAllActive();

    Object getByUuid(String str, Continuation<? super ProjectEntity> continuation);

    Object insert(ProjectEntity projectEntity, Continuation<? super Long> continuation);

    Object update(ProjectEntity projectEntity, Continuation<? super Unit> continuation);

    /* compiled from: ProjectDao.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object archive$default(ProjectDao projectDao, String str, long j, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: archive");
        }
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return projectDao.archive(str, j, continuation);
    }
}
