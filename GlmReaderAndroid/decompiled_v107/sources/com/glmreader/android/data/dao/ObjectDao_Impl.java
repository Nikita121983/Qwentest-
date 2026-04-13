package com.glmreader.android.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.glmreader.android.data.entity.ObjectEntity;
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
import kotlinx.coroutines.flow.Flow;

/* compiled from: ObjectDao_Impl.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u0017H\u0096@¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0096@¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/glmreader/android/data/dao/ObjectDao_Impl;", "Lcom/glmreader/android/data/dao/ObjectDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfObjectEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/glmreader/android/data/entity/ObjectEntity;", "__deleteAdapterOfObjectEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfObjectEntity", "insert", "", "obj", "(Lcom/glmreader/android/data/entity/ObjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getByGroupId", "Lkotlinx/coroutines/flow/Flow;", "", "groupId", "", "getByUuid", "uuid", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllForGroup", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ObjectDao_Impl implements ObjectDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<ObjectEntity> __deleteAdapterOfObjectEntity;
    private final EntityInsertAdapter<ObjectEntity> __insertAdapterOfObjectEntity;
    private final EntityDeleteOrUpdateAdapter<ObjectEntity> __updateAdapterOfObjectEntity;

    public ObjectDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfObjectEntity = new EntityInsertAdapter<ObjectEntity>() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `objects` (`id`,`uuid`,`groupId`,`name`,`sortOrder`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, ObjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo122bindText(3, entity.getGroupId());
                statement.mo122bindText(4, entity.getName());
                statement.mo120bindLong(5, entity.getSortOrder());
                statement.mo120bindLong(6, entity.getCreatedAt());
            }
        };
        this.__deleteAdapterOfObjectEntity = new EntityDeleteOrUpdateAdapter<ObjectEntity>() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `objects` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ObjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfObjectEntity = new EntityDeleteOrUpdateAdapter<ObjectEntity>() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `objects` SET `id` = ?,`uuid` = ?,`groupId` = ?,`name` = ?,`sortOrder` = ?,`createdAt` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ObjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo122bindText(3, entity.getGroupId());
                statement.mo122bindText(4, entity.getName());
                statement.mo120bindLong(5, entity.getSortOrder());
                statement.mo120bindLong(6, entity.getCreatedAt());
                statement.mo120bindLong(7, entity.getId());
            }
        };
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Object insert(final ObjectEntity obj, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                long insert$lambda$0;
                insert$lambda$0 = ObjectDao_Impl.insert$lambda$0(ObjectDao_Impl.this, obj, (SQLiteConnection) obj2);
                return Long.valueOf(insert$lambda$0);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long insert$lambda$0(ObjectDao_Impl this$0, ObjectEntity $obj, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfObjectEntity.insertAndReturnId(_connection, $obj);
        return _result;
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Object delete(final ObjectEntity obj, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Unit delete$lambda$1;
                delete$lambda$1 = ObjectDao_Impl.delete$lambda$1(ObjectDao_Impl.this, obj, (SQLiteConnection) obj2);
                return delete$lambda$1;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$1(ObjectDao_Impl this$0, ObjectEntity $obj, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfObjectEntity.handle(_connection, $obj);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Object update(final ObjectEntity obj, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Unit update$lambda$2;
                update$lambda$2 = ObjectDao_Impl.update$lambda$2(ObjectDao_Impl.this, obj, (SQLiteConnection) obj2);
                return update$lambda$2;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit update$lambda$2(ObjectDao_Impl this$0, ObjectEntity $obj, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfObjectEntity.handle(_connection, $obj);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Flow<List<ObjectEntity>> getByGroupId(final String groupId) {
        Intrinsics.checkNotNullParameter(groupId, "groupId");
        final String _sql = "SELECT * FROM objects WHERE groupId = ? ORDER BY sortOrder ASC, createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"objects"}, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List byGroupId$lambda$3;
                byGroupId$lambda$3 = ObjectDao_Impl.getByGroupId$lambda$3(_sql, groupId, (SQLiteConnection) obj);
                return byGroupId$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getByGroupId$lambda$3(String $_sql, String $groupId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        int _argIndex = 1;
        try {
            _stmt.mo122bindText(1, $groupId);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfGroupId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "groupId");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            List _result = new ArrayList();
            while (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                String _tmpGroupId = _stmt.getText(_columnIndexOfGroupId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _argIndex2 = _argIndex;
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                ObjectEntity _item = new ObjectEntity(_tmpId, _tmpUuid, _tmpGroupId, _tmpName, _tmpSortOrder, _tmpCreatedAt);
                _result.add(_item);
                _argIndex = _argIndex2;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Object getByUuid(final String uuid, Continuation<? super ObjectEntity> continuation) {
        final String _sql = "SELECT * FROM objects WHERE uuid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ObjectEntity byUuid$lambda$4;
                byUuid$lambda$4 = ObjectDao_Impl.getByUuid$lambda$4(_sql, uuid, (SQLiteConnection) obj);
                return byUuid$lambda$4;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObjectEntity getByUuid$lambda$4(String $_sql, String $uuid, SQLiteConnection _connection) {
        ObjectEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $uuid);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfGroupId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "groupId");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            if (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                String _tmpGroupId = _stmt.getText(_columnIndexOfGroupId);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                _result = new ObjectEntity(_tmpId, _tmpUuid, _tmpGroupId, _tmpName, _tmpSortOrder, _tmpCreatedAt);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.ObjectDao
    public Object deleteAllForGroup(final String groupId, Continuation<? super Unit> continuation) {
        final String _sql = "DELETE FROM objects WHERE groupId = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ObjectDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit deleteAllForGroup$lambda$5;
                deleteAllForGroup$lambda$5 = ObjectDao_Impl.deleteAllForGroup$lambda$5(_sql, groupId, (SQLiteConnection) obj);
                return deleteAllForGroup$lambda$5;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteAllForGroup$lambda$5(String $_sql, String $groupId, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $groupId);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: ObjectDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/glmreader/android/data/dao/ObjectDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
