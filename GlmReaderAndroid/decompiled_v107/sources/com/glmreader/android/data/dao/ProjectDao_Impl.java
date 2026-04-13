package com.glmreader.android.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.glmreader.android.data.entity.ProjectEntity;
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

/* compiled from: ProjectDao_Impl.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\u0014H\u0016J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\u0014H\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@¢\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/glmreader/android/data/dao/ProjectDao_Impl;", "Lcom/glmreader/android/data/dao/ProjectDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfProjectEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/glmreader/android/data/entity/ProjectEntity;", "__deleteAdapterOfProjectEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfProjectEntity", "insert", "", "project", "(Lcom/glmreader/android/data/entity/ProjectEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getAllActive", "Lkotlinx/coroutines/flow/Flow;", "", "getAll", "getByUuid", "uuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "archive", "timestamp", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ProjectDao_Impl implements ProjectDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<ProjectEntity> __deleteAdapterOfProjectEntity;
    private final EntityInsertAdapter<ProjectEntity> __insertAdapterOfProjectEntity;
    private final EntityDeleteOrUpdateAdapter<ProjectEntity> __updateAdapterOfProjectEntity;

    public ProjectDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfProjectEntity = new EntityInsertAdapter<ProjectEntity>() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `projects` (`id`,`uuid`,`name`,`description`,`sortOrder`,`createdAt`,`updatedAt`,`isArchived`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, ProjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo122bindText(3, entity.getName());
                statement.mo122bindText(4, entity.getDescription());
                statement.mo120bindLong(5, entity.getSortOrder());
                statement.mo120bindLong(6, entity.getCreatedAt());
                statement.mo120bindLong(7, entity.getUpdatedAt());
                statement.mo120bindLong(8, entity.isArchived() ? 1L : 0L);
            }
        };
        this.__deleteAdapterOfProjectEntity = new EntityDeleteOrUpdateAdapter<ProjectEntity>() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `projects` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ProjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfProjectEntity = new EntityDeleteOrUpdateAdapter<ProjectEntity>() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `projects` SET `id` = ?,`uuid` = ?,`name` = ?,`description` = ?,`sortOrder` = ?,`createdAt` = ?,`updatedAt` = ?,`isArchived` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, ProjectEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo122bindText(3, entity.getName());
                statement.mo122bindText(4, entity.getDescription());
                statement.mo120bindLong(5, entity.getSortOrder());
                statement.mo120bindLong(6, entity.getCreatedAt());
                statement.mo120bindLong(7, entity.getUpdatedAt());
                statement.mo120bindLong(8, entity.isArchived() ? 1L : 0L);
                statement.mo120bindLong(9, entity.getId());
            }
        };
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Object insert(final ProjectEntity project, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long insert$lambda$0;
                insert$lambda$0 = ProjectDao_Impl.insert$lambda$0(ProjectDao_Impl.this, project, (SQLiteConnection) obj);
                return Long.valueOf(insert$lambda$0);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long insert$lambda$0(ProjectDao_Impl this$0, ProjectEntity $project, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfProjectEntity.insertAndReturnId(_connection, $project);
        return _result;
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Object delete(final ProjectEntity project, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit delete$lambda$1;
                delete$lambda$1 = ProjectDao_Impl.delete$lambda$1(ProjectDao_Impl.this, project, (SQLiteConnection) obj);
                return delete$lambda$1;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$1(ProjectDao_Impl this$0, ProjectEntity $project, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfProjectEntity.handle(_connection, $project);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Object update(final ProjectEntity project, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit update$lambda$2;
                update$lambda$2 = ProjectDao_Impl.update$lambda$2(ProjectDao_Impl.this, project, (SQLiteConnection) obj);
                return update$lambda$2;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit update$lambda$2(ProjectDao_Impl this$0, ProjectEntity $project, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfProjectEntity.handle(_connection, $project);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Flow<List<ProjectEntity>> getAllActive() {
        final String _sql = "SELECT * FROM projects WHERE isArchived = 0 ORDER BY sortOrder ASC, createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"projects"}, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List allActive$lambda$3;
                allActive$lambda$3 = ProjectDao_Impl.getAllActive$lambda$3(_sql, (SQLiteConnection) obj);
                return allActive$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAllActive$lambda$3(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
            List _result = new ArrayList();
            while (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_tmp);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                int _columnIndexOfId = _tmp;
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsArchived);
                boolean _tmpIsArchived = _tmp2 != 0;
                ProjectEntity _item = new ProjectEntity(_tmpId, _tmpUuid, _tmpName, _tmpDescription, _tmpSortOrder, _tmpCreatedAt, _tmpUpdatedAt, _tmpIsArchived);
                _result.add(_item);
                _tmp = _columnIndexOfId;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Flow<List<ProjectEntity>> getAll() {
        final String _sql = "SELECT * FROM projects ORDER BY sortOrder ASC, createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"projects"}, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List all$lambda$4;
                all$lambda$4 = ProjectDao_Impl.getAll$lambda$4(_sql, (SQLiteConnection) obj);
                return all$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAll$lambda$4(String $_sql, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _tmp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
            List _result = new ArrayList();
            while (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_tmp);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                int _columnIndexOfId = _tmp;
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfIsArchived);
                boolean _tmpIsArchived = _tmp2 != 0;
                ProjectEntity _item = new ProjectEntity(_tmpId, _tmpUuid, _tmpName, _tmpDescription, _tmpSortOrder, _tmpCreatedAt, _tmpUpdatedAt, _tmpIsArchived);
                _result.add(_item);
                _tmp = _columnIndexOfId;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Object getByUuid(final String uuid, Continuation<? super ProjectEntity> continuation) {
        final String _sql = "SELECT * FROM projects WHERE uuid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ProjectEntity byUuid$lambda$5;
                byUuid$lambda$5 = ProjectDao_Impl.getByUuid$lambda$5(_sql, uuid, (SQLiteConnection) obj);
                return byUuid$lambda$5;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProjectEntity getByUuid$lambda$5(String $_sql, String $uuid, SQLiteConnection _connection) {
        ProjectEntity _result;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $uuid);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
            int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            int _columnIndexOfIsArchived = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isArchived");
            if (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                String _tmpName = _stmt.getText(_columnIndexOfName);
                String _tmpDescription = _stmt.getText(_columnIndexOfDescription);
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                int _tmp = (int) _stmt.getLong(_columnIndexOfIsArchived);
                boolean _tmpIsArchived = _tmp != 0;
                _result = new ProjectEntity(_tmpId, _tmpUuid, _tmpName, _tmpDescription, _tmpSortOrder, _tmpCreatedAt, _tmpUpdatedAt, _tmpIsArchived);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.ProjectDao
    public Object archive(final String uuid, final long timestamp, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE projects SET isArchived = 1, updatedAt = ? WHERE uuid = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.ProjectDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit archive$lambda$6;
                archive$lambda$6 = ProjectDao_Impl.archive$lambda$6(_sql, timestamp, uuid, (SQLiteConnection) obj);
                return archive$lambda$6;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit archive$lambda$6(String $_sql, long $timestamp, String $uuid, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo120bindLong(1, $timestamp);
            _stmt.mo122bindText(2, $uuid);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: ProjectDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/glmreader/android/data/dao/ProjectDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
