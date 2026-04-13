package com.glmreader.android.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModelKt;
import com.glmreader.android.data.AppDatabase;
import com.glmreader.android.data.entity.ProjectEntity;
import com.glmreader.android.data.repository.ProjectRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: ProjectListViewModel.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u000fJ\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/glmreader/android/ui/viewmodel/ProjectListViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "database", "Lcom/glmreader/android/data/AppDatabase;", "projectRepository", "Lcom/glmreader/android/data/repository/ProjectRepository;", "getProjectRepository", "()Lcom/glmreader/android/data/repository/ProjectRepository;", "projects", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/glmreader/android/data/entity/ProjectEntity;", "getProjects", "()Lkotlinx/coroutines/flow/StateFlow;", "createProject", "", "name", "", "description", "openProject", "project", "archiveProject", "uuid", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ProjectListViewModel extends AndroidViewModel {
    private final AppDatabase database;
    private final ProjectRepository projectRepository;
    private final StateFlow<List<ProjectEntity>> projects;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProjectListViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.database = AppDatabase.INSTANCE.getDatabase(application);
        this.projectRepository = new ProjectRepository(this.database.projectDao(), this.database.groupDao(), this.database.objectDao());
        this.projects = FlowKt.stateIn(this.projectRepository.getAllProjects(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
    }

    public final ProjectRepository getProjectRepository() {
        return this.projectRepository;
    }

    public final StateFlow<List<ProjectEntity>> getProjects() {
        return this.projects;
    }

    public static /* synthetic */ void createProject$default(ProjectListViewModel projectListViewModel, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        projectListViewModel.createProject(str, str2);
    }

    public final void createProject(String name, String description) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ProjectListViewModel$createProject$1(this, name, description, null), 3, null);
    }

    public final void openProject(ProjectEntity project) {
        Intrinsics.checkNotNullParameter(project, "project");
    }

    public final void archiveProject(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ProjectListViewModel$archiveProject$1(this, uuid, null), 3, null);
    }
}
