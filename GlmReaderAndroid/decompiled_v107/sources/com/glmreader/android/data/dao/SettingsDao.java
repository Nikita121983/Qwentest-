package com.glmreader.android.data.dao;

import com.glmreader.android.data.entity.SettingsEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: SettingsDao.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\nJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH§@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/glmreader/android/data/dao/SettingsDao;", "", "getSetting", "Lcom/glmreader/android/data/entity/SettingsEntity;", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "setting", "(Lcom/glmreader/android/data/entity/SettingsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "getAllSettings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface SettingsDao {
    Object delete(String str, Continuation<? super Unit> continuation);

    Object getAllSettings(Continuation<? super List<SettingsEntity>> continuation);

    Object getSetting(String str, Continuation<? super SettingsEntity> continuation);

    Object update(SettingsEntity settingsEntity, Continuation<? super Unit> continuation);

    Object upsert(SettingsEntity settingsEntity, Continuation<? super Unit> continuation);
}
