package com.glmreader.android.data.dao;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.glmreader.android.data.entity.NoteEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: NoteDao_Impl.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/glmreader/android/data/dao/NoteDao_Impl;", "Lcom/glmreader/android/data/dao/NoteDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfNoteEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/glmreader/android/data/entity/NoteEntity;", "insert", "", "note", "(Lcom/glmreader/android/data/entity/NoteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotesForMeasurement", "", "measurementUuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "demoteAllNotes", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class NoteDao_Impl implements NoteDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityInsertAdapter<NoteEntity> __insertAdapterOfNoteEntity;

    public NoteDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfNoteEntity = new EntityInsertAdapter<NoteEntity>() { // from class: com.glmreader.android.data.dao.NoteDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `notes` (`id`,`measurementUuid`,`version`,`text`,`createdAt`,`isPrimary`,`isVoice`,`mediaPath`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, NoteEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getMeasurementUuid());
                statement.mo120bindLong(3, entity.getVersion());
                statement.mo122bindText(4, entity.getText());
                statement.mo120bindLong(5, entity.getCreatedAt());
                statement.mo120bindLong(6, entity.isPrimary() ? 1L : 0L);
                statement.mo120bindLong(7, entity.isVoice() ? 1L : 0L);
                String mediaPath = entity.getMediaPath();
                if (mediaPath == null) {
                    statement.mo121bindNull(8);
                } else {
                    statement.mo122bindText(8, mediaPath);
                }
            }
        };
    }

    @Override // com.glmreader.android.data.dao.NoteDao
    public Object insert(final NoteEntity note, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.NoteDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit insert$lambda$0;
                insert$lambda$0 = NoteDao_Impl.insert$lambda$0(NoteDao_Impl.this, note, (SQLiteConnection) obj);
                return insert$lambda$0;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$0(NoteDao_Impl this$0, NoteEntity $note, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfNoteEntity.insert(_connection, (SQLiteConnection) $note);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.NoteDao
    public Object getNotesForMeasurement(final String measurementUuid, Continuation<? super List<NoteEntity>> continuation) {
        final String _sql = "SELECT * FROM notes WHERE measurementUuid = ? ORDER BY version DESC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.NoteDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List notesForMeasurement$lambda$1;
                notesForMeasurement$lambda$1 = NoteDao_Impl.getNotesForMeasurement$lambda$1(_sql, measurementUuid, (SQLiteConnection) obj);
                return notesForMeasurement$lambda$1;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getNotesForMeasurement$lambda$1(String $_sql, String $measurementUuid, SQLiteConnection _connection) {
        String _tmpMediaPath;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _tmp = 1;
        try {
            _stmt.mo122bindText(1, $measurementUuid);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfMeasurementUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "measurementUuid");
            int _columnIndexOfVersion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "version");
            int _columnIndexOfText = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "text");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfIsPrimary = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isPrimary");
            int _columnIndexOfIsVoice = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isVoice");
            int _columnIndexOfMediaPath = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mediaPath");
            List _result = new ArrayList();
            while (_stmt.step()) {
                int _argIndex = _tmp;
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpMeasurementUuid = _stmt.getText(_columnIndexOfMeasurementUuid);
                int _tmpVersion = (int) _stmt.getLong(_columnIndexOfVersion);
                String _tmpText = _stmt.getText(_columnIndexOfText);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsPrimary);
                boolean _tmpIsPrimary = _tmp2 != 0;
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsVoice);
                boolean _tmpIsVoice = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfMediaPath)) {
                    _tmpMediaPath = null;
                } else {
                    String _tmpMediaPath2 = _stmt.getText(_columnIndexOfMediaPath);
                    _tmpMediaPath = _tmpMediaPath2;
                }
                NoteEntity _item = new NoteEntity(_tmpId, _tmpMeasurementUuid, _tmpVersion, _tmpText, _tmpCreatedAt, _tmpIsPrimary, _tmpIsVoice, _tmpMediaPath);
                _result.add(_item);
                _tmp = _argIndex;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.NoteDao
    public Object demoteAllNotes(final String measurementUuid, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE notes SET isPrimary = 0 WHERE measurementUuid = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.NoteDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit demoteAllNotes$lambda$2;
                demoteAllNotes$lambda$2 = NoteDao_Impl.demoteAllNotes$lambda$2(_sql, measurementUuid, (SQLiteConnection) obj);
                return demoteAllNotes$lambda$2;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit demoteAllNotes$lambda$2(String $_sql, String $measurementUuid, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $measurementUuid);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: NoteDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/glmreader/android/data/dao/NoteDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
