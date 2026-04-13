package com.glmreader.android.data.repository;

import com.glmreader.android.data.dao.MeasurementDao;
import com.glmreader.android.data.entity.MeasurementEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: MeasurementRepository.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007J\u0018\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/glmreader/android/data/repository/MeasurementRepository;", "", "measurementDao", "Lcom/glmreader/android/data/dao/MeasurementDao;", "<init>", "(Lcom/glmreader/android/data/dao/MeasurementDao;)V", "getAllActive", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "getByUuid", "uuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "measurement", "(Lcom/glmreader/android/data/entity/MeasurementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "softDelete", "hardDelete", "updateSortOrder", "newOrder", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MeasurementRepository {
    private final MeasurementDao measurementDao;

    public MeasurementRepository(MeasurementDao measurementDao) {
        Intrinsics.checkNotNullParameter(measurementDao, "measurementDao");
        this.measurementDao = measurementDao;
    }

    public final Flow<List<MeasurementEntity>> getAllActive() {
        return this.measurementDao.getAllActive();
    }

    public final Object getByUuid(String uuid, Continuation<? super MeasurementEntity> continuation) {
        return this.measurementDao.getByUuid(uuid, continuation);
    }

    public final Object insert(MeasurementEntity measurement, Continuation<? super Long> continuation) {
        return this.measurementDao.insert(measurement, continuation);
    }

    public final Object update(MeasurementEntity measurement, Continuation<? super Unit> continuation) {
        Object update = this.measurementDao.update(measurement, continuation);
        return update == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? update : Unit.INSTANCE;
    }

    public final Object softDelete(String uuid, Continuation<? super Unit> continuation) {
        Object softDelete$default = MeasurementDao.softDelete$default(this.measurementDao, uuid, 0L, continuation, 2, null);
        return softDelete$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? softDelete$default : Unit.INSTANCE;
    }

    public final Object hardDelete(MeasurementEntity measurement, Continuation<? super Unit> continuation) {
        Object delete = this.measurementDao.delete(measurement, continuation);
        return delete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? delete : Unit.INSTANCE;
    }

    public final Object updateSortOrder(String uuid, int newOrder, Continuation<? super Unit> continuation) {
        Object updateSortOrder = this.measurementDao.updateSortOrder(uuid, newOrder, continuation);
        return updateSortOrder == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? updateSortOrder : Unit.INSTANCE;
    }

    public final Object getActiveCount(Continuation<? super Integer> continuation) {
        return this.measurementDao.getActiveCount(continuation);
    }
}
