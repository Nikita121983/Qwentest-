package com.glmreader.android.data.dao;

import com.glmreader.android.data.entity.NoteEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: NoteDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H§@¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/glmreader/android/data/dao/NoteDao;", "", "getNotesForMeasurement", "", "Lcom/glmreader/android/data/entity/NoteEntity;", "measurementUuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "note", "(Lcom/glmreader/android/data/entity/NoteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "demoteAllNotes", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface NoteDao {
    Object demoteAllNotes(String str, Continuation<? super Unit> continuation);

    Object getNotesForMeasurement(String str, Continuation<? super List<NoteEntity>> continuation);

    Object insert(NoteEntity noteEntity, Continuation<? super Unit> continuation);
}
