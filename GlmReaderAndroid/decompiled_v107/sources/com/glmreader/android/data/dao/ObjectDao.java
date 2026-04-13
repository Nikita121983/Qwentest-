package com.glmreader.android.data.dao;

import com.glmreader.android.data.entity.ObjectEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: ObjectDao.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\n¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/glmreader/android/data/dao/ObjectDao;", "", "getByGroupId", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/glmreader/android/data/entity/ObjectEntity;", "groupId", "", "getByUuid", "uuid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "obj", "(Lcom/glmreader/android/data/entity/ObjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "delete", "deleteAllForGroup", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface ObjectDao {
    Object delete(ObjectEntity objectEntity, Continuation<? super Unit> continuation);

    Object deleteAllForGroup(String str, Continuation<? super Unit> continuation);

    Flow<List<ObjectEntity>> getByGroupId(String groupId);

    Object getByUuid(String str, Continuation<? super ObjectEntity> continuation);

    Object insert(ObjectEntity objectEntity, Continuation<? super Long> continuation);

    Object update(ObjectEntity objectEntity, Continuation<? super Unit> continuation);
}
