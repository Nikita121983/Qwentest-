package com.glmreader.android.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModelKt;
import com.glmreader.android.data.AppDatabase;
import com.glmreader.android.data.entity.MeasurementEntity;
import com.glmreader.android.data.repository.MeasurementRepository;
import com.glmreader.android.data.repository.ProjectRepository;
import com.glmreader.android.protocol.BlePacketParser;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: MeasurementViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/glmreader/android/ui/viewmodel/MeasurementViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "database", "Lcom/glmreader/android/data/AppDatabase;", "measurementRepository", "Lcom/glmreader/android/data/repository/MeasurementRepository;", "getMeasurementRepository", "()Lcom/glmreader/android/data/repository/MeasurementRepository;", "projectRepository", "Lcom/glmreader/android/data/repository/ProjectRepository;", "getProjectRepository", "()Lcom/glmreader/android/data/repository/ProjectRepository;", "measurements", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "getMeasurements", "()Lkotlinx/coroutines/flow/StateFlow;", "onBleMeasurement", "", "parsed", "Lcom/glmreader/android/protocol/BlePacketParser$ParsedMeasurement;", "projectId", "", "deleteMeasurement", "uuid", "getActiveCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MeasurementViewModel extends AndroidViewModel {
    private final AppDatabase database;
    private final MeasurementRepository measurementRepository;
    private final StateFlow<List<MeasurementEntity>> measurements;
    private final ProjectRepository projectRepository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MeasurementViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.database = AppDatabase.INSTANCE.getDatabase(application);
        this.measurementRepository = new MeasurementRepository(this.database.measurementDao());
        this.projectRepository = new ProjectRepository(this.database.projectDao(), this.database.groupDao(), this.database.objectDao());
        this.measurements = FlowKt.stateIn(this.measurementRepository.getAllActive(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
    }

    public final MeasurementRepository getMeasurementRepository() {
        return this.measurementRepository;
    }

    public final ProjectRepository getProjectRepository() {
        return this.projectRepository;
    }

    public final StateFlow<List<MeasurementEntity>> getMeasurements() {
        return this.measurements;
    }

    public static /* synthetic */ void onBleMeasurement$default(MeasurementViewModel measurementViewModel, BlePacketParser.ParsedMeasurement parsedMeasurement, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        measurementViewModel.onBleMeasurement(parsedMeasurement, str);
    }

    public final void onBleMeasurement(BlePacketParser.ParsedMeasurement parsed, String projectId) {
        Intrinsics.checkNotNullParameter(parsed, "parsed");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MeasurementViewModel$onBleMeasurement$1(parsed, projectId, this, null), 3, null);
    }

    public final void deleteMeasurement(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MeasurementViewModel$deleteMeasurement$1(this, uuid, null), 3, null);
    }

    public final Object getActiveCount(Continuation<? super Integer> continuation) {
        return this.measurementRepository.getActiveCount(continuation);
    }
}
