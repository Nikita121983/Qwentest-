package com.glmreader.android.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.glmreader.android.data.entity.SettingsEntity;
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

/* compiled from: SettingsDao_Impl.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0096@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/glmreader/android/data/dao/SettingsDao_Impl;", "Lcom/glmreader/android/data/dao/SettingsDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfSettingsEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/glmreader/android/data/entity/SettingsEntity;", "__updateAdapterOfSettingsEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "upsert", "", "setting", "(Lcom/glmreader/android/data/entity/SettingsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "getSetting", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSettings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class SettingsDao_Impl implements SettingsDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityInsertAdapter<SettingsEntity> __insertAdapterOfSettingsEntity;
    private final EntityDeleteOrUpdateAdapter<SettingsEntity> __updateAdapterOfSettingsEntity;

    public SettingsDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfSettingsEntity = new EntityInsertAdapter<SettingsEntity>() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `settings` (`key`,`value`,`updatedAt`) VALUES (?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, SettingsEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo122bindText(1, entity.getKey());
                statement.mo122bindText(2, entity.getValue());
                statement.mo120bindLong(3, entity.getUpdatedAt());
            }
        };
        this.__updateAdapterOfSettingsEntity = new EntityDeleteOrUpdateAdapter<SettingsEntity>() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `settings` SET `key` = ?,`value` = ?,`updatedAt` = ? WHERE `key` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, SettingsEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo122bindText(1, entity.getKey());
                statement.mo122bindText(2, entity.getValue());
                statement.mo120bindLong(3, entity.getUpdatedAt());
                statement.mo122bindText(4, entity.getKey());
            }
        };
    }

    @Override // com.glmreader.android.data.dao.SettingsDao
    public Object upsert(final SettingsEntity setting, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit upsert$lambda$0;
                upsert$lambda$0 = SettingsDao_Impl.upsert$lambda$0(SettingsDao_Impl.this, setting, (SQLiteConnection) obj);
                return upsert$lambda$0;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit upsert$lambda$0(SettingsDao_Impl this$0, SettingsEntity $setting, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__insertAdapterOfSettingsEntity.insert(_connection, (SQLiteConnection) $setting);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.SettingsDao
    public Object update(final SettingsEntity setting, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit update$lambda$1;
                update$lambda$1 = SettingsDao_Impl.update$lambda$1(SettingsDao_Impl.this, setting, (SQLiteConnection) obj);
                return update$lambda$1;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit update$lambda$1(SettingsDao_Impl this$0, SettingsEntity $setting, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfSettingsEntity.handle(_connection, $setting);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.SettingsDao
    public Object getSetting(final String key, Continuation<? super SettingsEntity> continuation) {
        final String _sql = "SELECT * FROM settings WHERE key = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SettingsEntity setting$lambda$2;
                setting$lambda$2 = SettingsDao_Impl.getSetting$lambda$2(_sql, key, (SQLiteConnection) obj);
                return setting$lambda$2;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SettingsEntity getSetting$lambda$2(String $_sql, String $key, SQLiteConnection _connection) {
        SettingsEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $key);
            int _columnIndexOfKey = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "key");
            int _columnIndexOfValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "value");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            if (_stmt.step()) {
                String _tmpKey = _stmt.getText(_columnIndexOfKey);
                String _tmpValue = _stmt.getText(_columnIndexOfValue);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                _result = new SettingsEntity(_tmpKey, _tmpValue, _tmpUpdatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.SettingsDao
    public Object getAllSettings(Continuation<? super List<SettingsEntity>> continuation) {
        final String _sql = "SELECT * FROM settings";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List allSettings$lambda$3;
                allSettings$lambda$3 = SettingsDao_Impl.getAllSettings$lambda$3(_sql, (SQLiteConnection) obj);
                return allSettings$lambda$3;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAllSettings$lambda$3(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfKey = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "key");
            int _columnIndexOfValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "value");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            List _result = new ArrayList();
            while (_stmt.step()) {
                String _tmpKey = _stmt.getText(_columnIndexOfKey);
                String _tmpValue = _stmt.getText(_columnIndexOfValue);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                SettingsEntity _item = new SettingsEntity(_tmpKey, _tmpValue, _tmpUpdatedAt);
                _result.add(_item);
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.SettingsDao
    public Object delete(final String key, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM settings WHERE key = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.SettingsDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit delete$lambda$4;
                delete$lambda$4 = SettingsDao_Impl.delete$lambda$4(_sql, key, (SQLiteConnection) obj);
                return delete$lambda$4;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$4(String $_sql, String $key, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $key);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: SettingsDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/glmreader/android/data/dao/SettingsDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
