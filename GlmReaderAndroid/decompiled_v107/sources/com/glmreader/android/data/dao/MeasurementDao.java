package com.glmreader.android.data.dao;

import com.glmreader.android.data.entity.MeasurementEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: MeasurementDao.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH§@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\tJ\u0016\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\tJ\u000e\u0010\u0018\u001a\u00020\u0012H§@¢\u0006\u0002\u0010\u0019¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lcom/glmreader/android/data/dao/MeasurementDao;", "", "getAllActive", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "insert", "", "measurement", "(Lcom/glmreader/android/data/entity/MeasurementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "softDelete", "", "uuid", "", "timestamp", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSortOrder", "newOrder", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByUuid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "delete", "getActiveCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface MeasurementDao {
    Object delete(MeasurementEntity measurementEntity, Continuation<? super Unit> continuation);

    Object getActiveCount(Continuation<? super Integer> continuation);

    Flow<List<MeasurementEntity>> getAllActive();

    Object getByUuid(String str, Continuation<? super MeasurementEntity> continuation);

    Object insert(MeasurementEntity measurementEntity, Continuation<? super Long> continuation);

    Object softDelete(String str, long j, Continuation<? super Unit> continuation);

    Object update(MeasurementEntity measurementEntity, Continuation<? super Unit> continuation);

    Object updateSortOrder(String str, int i, Continuation<? super Unit> continuation);

    /* compiled from: MeasurementDao.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object softDelete$default(MeasurementDao measurementDao, String str, long j, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: softDelete");
        }
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return measurementDao.softDelete(str, j, continuation);
    }
}
