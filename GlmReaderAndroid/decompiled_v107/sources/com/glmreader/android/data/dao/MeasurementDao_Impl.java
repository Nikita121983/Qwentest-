package com.glmreader.android.data.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.glmreader.android.data.entity.MeasurementEntity;
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

/* compiled from: MeasurementDao_Impl.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\u0014H\u0016J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u001e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/glmreader/android/data/dao/MeasurementDao_Impl;", "Lcom/glmreader/android/data/dao/MeasurementDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfMeasurementEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "__deleteAdapterOfMeasurementEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfMeasurementEntity", "insert", "", "measurement", "(Lcom/glmreader/android/data/entity/MeasurementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getAllActive", "Lkotlinx/coroutines/flow/Flow;", "", "getByUuid", "uuid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "softDelete", "timestamp", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSortOrder", "newOrder", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class MeasurementDao_Impl implements MeasurementDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<MeasurementEntity> __deleteAdapterOfMeasurementEntity;
    private final EntityInsertAdapter<MeasurementEntity> __insertAdapterOfMeasurementEntity;
    private final EntityDeleteOrUpdateAdapter<MeasurementEntity> __updateAdapterOfMeasurementEntity;

    public MeasurementDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfMeasurementEntity = new EntityInsertAdapter<MeasurementEntity>() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `measurements` (`id`,`uuid`,`sortOrder`,`measurementType`,`refEdge`,`resultValue`,`comp1Value`,`comp2Value`,`comp3Value`,`angleDeg`,`laserOn`,`timestamp`,`createdAt`,`updatedAt`,`isDeleted`,`projectId`,`groupId`,`objectId`,`deviceName`,`protocolVersion`,`blePacketHex`,`seqNum`,`measId`,`deviceStatus`,`metadataJson`,`isAutoDetected`,`calculatedValue`,`calculationType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, MeasurementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo120bindLong(3, entity.getSortOrder());
                statement.mo120bindLong(4, entity.getMeasurementType());
                statement.mo120bindLong(5, entity.getRefEdge());
                statement.mo119bindDouble(6, entity.getResultValue());
                statement.mo119bindDouble(7, entity.getComp1Value());
                statement.mo119bindDouble(8, entity.getComp2Value());
                statement.mo119bindDouble(9, entity.getComp3Value());
                statement.mo119bindDouble(10, entity.getAngleDeg());
                statement.mo120bindLong(11, entity.getLaserOn() ? 1L : 0L);
                statement.mo120bindLong(12, entity.getTimestamp());
                statement.mo120bindLong(13, entity.getCreatedAt());
                statement.mo120bindLong(14, entity.getUpdatedAt());
                statement.mo120bindLong(15, entity.isDeleted() ? 1L : 0L);
                String projectId = entity.getProjectId();
                if (projectId == null) {
                    statement.mo121bindNull(16);
                } else {
                    statement.mo122bindText(16, projectId);
                }
                String groupId = entity.getGroupId();
                if (groupId == null) {
                    statement.mo121bindNull(17);
                } else {
                    statement.mo122bindText(17, groupId);
                }
                String objectId = entity.getObjectId();
                if (objectId == null) {
                    statement.mo121bindNull(18);
                } else {
                    statement.mo122bindText(18, objectId);
                }
                statement.mo122bindText(19, entity.getDeviceName());
                statement.mo120bindLong(20, entity.getProtocolVersion());
                String blePacketHex = entity.getBlePacketHex();
                if (blePacketHex == null) {
                    statement.mo121bindNull(21);
                } else {
                    statement.mo122bindText(21, blePacketHex);
                }
                if (entity.getSeqNum() != null) {
                    statement.mo120bindLong(22, r8.intValue());
                } else {
                    statement.mo121bindNull(22);
                }
                if (entity.getMeasId() != null) {
                    statement.mo120bindLong(23, r9.intValue());
                } else {
                    statement.mo121bindNull(23);
                }
                if (entity.getDeviceStatus() != null) {
                    statement.mo120bindLong(24, r10.intValue());
                } else {
                    statement.mo121bindNull(24);
                }
                String metadataJson = entity.getMetadataJson();
                if (metadataJson == null) {
                    statement.mo121bindNull(25);
                } else {
                    statement.mo122bindText(25, metadataJson);
                }
                statement.mo120bindLong(26, entity.isAutoDetected() ? 1L : 0L);
                Double calculatedValue = entity.getCalculatedValue();
                if (calculatedValue != null) {
                    statement.mo119bindDouble(27, calculatedValue.doubleValue());
                } else {
                    statement.mo121bindNull(27);
                }
                String calculationType = entity.getCalculationType();
                if (calculationType == null) {
                    statement.mo121bindNull(28);
                } else {
                    statement.mo122bindText(28, calculationType);
                }
            }
        };
        this.__deleteAdapterOfMeasurementEntity = new EntityDeleteOrUpdateAdapter<MeasurementEntity>() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `measurements` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, MeasurementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfMeasurementEntity = new EntityDeleteOrUpdateAdapter<MeasurementEntity>() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `measurements` SET `id` = ?,`uuid` = ?,`sortOrder` = ?,`measurementType` = ?,`refEdge` = ?,`resultValue` = ?,`comp1Value` = ?,`comp2Value` = ?,`comp3Value` = ?,`angleDeg` = ?,`laserOn` = ?,`timestamp` = ?,`createdAt` = ?,`updatedAt` = ?,`isDeleted` = ?,`projectId` = ?,`groupId` = ?,`objectId` = ?,`deviceName` = ?,`protocolVersion` = ?,`blePacketHex` = ?,`seqNum` = ?,`measId` = ?,`deviceStatus` = ?,`metadataJson` = ?,`isAutoDetected` = ?,`calculatedValue` = ?,`calculationType` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, MeasurementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo120bindLong(1, entity.getId());
                statement.mo122bindText(2, entity.getUuid());
                statement.mo120bindLong(3, entity.getSortOrder());
                statement.mo120bindLong(4, entity.getMeasurementType());
                statement.mo120bindLong(5, entity.getRefEdge());
                statement.mo119bindDouble(6, entity.getResultValue());
                statement.mo119bindDouble(7, entity.getComp1Value());
                statement.mo119bindDouble(8, entity.getComp2Value());
                statement.mo119bindDouble(9, entity.getComp3Value());
                statement.mo119bindDouble(10, entity.getAngleDeg());
                statement.mo120bindLong(11, entity.getLaserOn() ? 1L : 0L);
                statement.mo120bindLong(12, entity.getTimestamp());
                statement.mo120bindLong(13, entity.getCreatedAt());
                statement.mo120bindLong(14, entity.getUpdatedAt());
                statement.mo120bindLong(15, entity.isDeleted() ? 1L : 0L);
                String projectId = entity.getProjectId();
                if (projectId == null) {
                    statement.mo121bindNull(16);
                } else {
                    statement.mo122bindText(16, projectId);
                }
                String groupId = entity.getGroupId();
                if (groupId == null) {
                    statement.mo121bindNull(17);
                } else {
                    statement.mo122bindText(17, groupId);
                }
                String objectId = entity.getObjectId();
                if (objectId == null) {
                    statement.mo121bindNull(18);
                } else {
                    statement.mo122bindText(18, objectId);
                }
                statement.mo122bindText(19, entity.getDeviceName());
                statement.mo120bindLong(20, entity.getProtocolVersion());
                String blePacketHex = entity.getBlePacketHex();
                if (blePacketHex == null) {
                    statement.mo121bindNull(21);
                } else {
                    statement.mo122bindText(21, blePacketHex);
                }
                if (entity.getSeqNum() != null) {
                    statement.mo120bindLong(22, r8.intValue());
                } else {
                    statement.mo121bindNull(22);
                }
                if (entity.getMeasId() != null) {
                    statement.mo120bindLong(23, r9.intValue());
                } else {
                    statement.mo121bindNull(23);
                }
                if (entity.getDeviceStatus() != null) {
                    statement.mo120bindLong(24, r10.intValue());
                } else {
                    statement.mo121bindNull(24);
                }
                String metadataJson = entity.getMetadataJson();
                if (metadataJson == null) {
                    statement.mo121bindNull(25);
                } else {
                    statement.mo122bindText(25, metadataJson);
                }
                statement.mo120bindLong(26, entity.isAutoDetected() ? 1L : 0L);
                Double calculatedValue = entity.getCalculatedValue();
                if (calculatedValue != null) {
                    statement.mo119bindDouble(27, calculatedValue.doubleValue());
                } else {
                    statement.mo121bindNull(27);
                }
                String calculationType = entity.getCalculationType();
                if (calculationType == null) {
                    statement.mo121bindNull(28);
                } else {
                    statement.mo122bindText(28, calculationType);
                }
                statement.mo120bindLong(29, entity.getId());
            }
        };
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object insert(final MeasurementEntity measurement, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long insert$lambda$0;
                insert$lambda$0 = MeasurementDao_Impl.insert$lambda$0(MeasurementDao_Impl.this, measurement, (SQLiteConnection) obj);
                return Long.valueOf(insert$lambda$0);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long insert$lambda$0(MeasurementDao_Impl this$0, MeasurementEntity $measurement, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        long _result = this$0.__insertAdapterOfMeasurementEntity.insertAndReturnId(_connection, $measurement);
        return _result;
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object delete(final MeasurementEntity measurement, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit delete$lambda$1;
                delete$lambda$1 = MeasurementDao_Impl.delete$lambda$1(MeasurementDao_Impl.this, measurement, (SQLiteConnection) obj);
                return delete$lambda$1;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$1(MeasurementDao_Impl this$0, MeasurementEntity $measurement, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__deleteAdapterOfMeasurementEntity.handle(_connection, $measurement);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object update(final MeasurementEntity measurement, Continuation<? super Unit> continuation) {
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit update$lambda$2;
                update$lambda$2 = MeasurementDao_Impl.update$lambda$2(MeasurementDao_Impl.this, measurement, (SQLiteConnection) obj);
                return update$lambda$2;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit update$lambda$2(MeasurementDao_Impl this$0, MeasurementEntity $measurement, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        this$0.__updateAdapterOfMeasurementEntity.handle(_connection, $measurement);
        return Unit.INSTANCE;
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Flow<List<MeasurementEntity>> getAllActive() {
        final String _sql = "SELECT * FROM measurements WHERE isDeleted = 0 ORDER BY sortOrder ASC, timestamp DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"measurements"}, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                List allActive$lambda$3;
                allActive$lambda$3 = MeasurementDao_Impl.getAllActive$lambda$3(_sql, (SQLiteConnection) obj);
                return allActive$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAllActive$lambda$3(String $_sql, SQLiteConnection _connection) {
        String _tmpProjectId;
        String _tmpGroupId;
        String _tmpObjectId;
        String _tmpBlePacketHex;
        int _tmpProtocolVersion;
        Integer _tmpSeqNum;
        int _columnIndexOfSeqNum;
        Integer _tmpMeasId;
        int _columnIndexOfUuid;
        int _columnIndexOfSortOrder;
        Integer _tmpDeviceStatus;
        String _tmpMetadataJson;
        Double _tmpCalculatedValue;
        String _tmpCalculationType;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfMetadataJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfCalculatedValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfMeasurementType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "measurementType");
            int _columnIndexOfRefEdge = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "refEdge");
            int _columnIndexOfResultValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "resultValue");
            int _columnIndexOfComp1Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp1Value");
            int _columnIndexOfComp2Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp2Value");
            int _columnIndexOfComp3Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp3Value");
            int _columnIndexOfAngleDeg = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "angleDeg");
            int _columnIndexOfLaserOn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "laserOn");
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _tmp_2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _tmp = _columnIndexOfIsDeleted;
            int _columnIndexOfProtocolVersion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectId");
            int _columnIndexOfGroupId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "groupId");
            int _columnIndexOfGroupId2 = _columnIndexOfGroupId;
            int _columnIndexOfObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "objectId");
            int _columnIndexOfObjectId2 = _columnIndexOfObjectId;
            int _columnIndexOfDeviceName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceName");
            int _columnIndexOfDeviceName2 = _columnIndexOfDeviceName;
            int _columnIndexOfProtocolVersion2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "protocolVersion");
            int _columnIndexOfIsDeleted2 = _columnIndexOfProtocolVersion2;
            int _columnIndexOfBlePacketHex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "blePacketHex");
            int _columnIndexOfBlePacketHex2 = _columnIndexOfBlePacketHex;
            int _columnIndexOfSeqNum2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "seqNum");
            int _columnIndexOfSeqNum3 = _columnIndexOfSeqNum2;
            int _columnIndexOfMeasId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "measId");
            int _columnIndexOfSeqNum4 = _columnIndexOfMeasId;
            int _columnIndexOfUuid2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceStatus");
            int _columnIndexOfMetadataJson2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "metadataJson");
            int _columnIndexOfIsAutoDetected = _columnIndexOfMetadataJson2;
            int _columnIndexOfIsAutoDetected2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAutoDetected");
            int _columnIndexOfMeasId2 = _columnIndexOfIsAutoDetected2;
            int _columnIndexOfCalculatedValue2 = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "calculatedValue");
            int _columnIndexOfCalculatedValue3 = _columnIndexOfCalculatedValue2;
            int _columnIndexOfCalculationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "calculationType");
            List _result = new ArrayList();
            while (_stmt.step()) {
                int _columnIndexOfUpdatedAt = _tmp_2;
                int _columnIndexOfCalculationType2 = _columnIndexOfCalculationType;
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpUuid = _stmt.getText(_columnIndexOfMetadataJson);
                int _columnIndexOfId2 = _columnIndexOfId;
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfCalculatedValue);
                int _tmpMeasurementType = (int) _stmt.getLong(_columnIndexOfMeasurementType);
                int _tmpRefEdge = (int) _stmt.getLong(_columnIndexOfRefEdge);
                double _tmpResultValue = _stmt.getDouble(_columnIndexOfResultValue);
                double _tmpComp1Value = _stmt.getDouble(_columnIndexOfComp1Value);
                double _tmpComp2Value = _stmt.getDouble(_columnIndexOfComp2Value);
                double _tmpComp3Value = _stmt.getDouble(_columnIndexOfComp3Value);
                double _tmpAngleDeg = _stmt.getDouble(_columnIndexOfAngleDeg);
                int _tmp2 = (int) _stmt.getLong(_columnIndexOfLaserOn);
                boolean _tmpLaserOn = _tmp2 != 0;
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                int _columnIndexOfIsDeleted3 = _tmp;
                boolean _tmpIsDeleted = ((int) _stmt.getLong(_columnIndexOfIsDeleted3)) != 0;
                int _tmp_1 = _columnIndexOfProtocolVersion;
                if (_stmt.isNull(_tmp_1)) {
                    _tmpProjectId = null;
                } else {
                    _tmpProjectId = _stmt.getText(_tmp_1);
                }
                int _columnIndexOfProjectId = _columnIndexOfGroupId2;
                if (_stmt.isNull(_columnIndexOfProjectId)) {
                    _tmpGroupId = null;
                } else {
                    String _tmpGroupId2 = _stmt.getText(_columnIndexOfProjectId);
                    _tmpGroupId = _tmpGroupId2;
                }
                _columnIndexOfGroupId2 = _columnIndexOfProjectId;
                int _columnIndexOfGroupId3 = _columnIndexOfObjectId2;
                if (_stmt.isNull(_columnIndexOfGroupId3)) {
                    _tmpObjectId = null;
                } else {
                    String _tmpObjectId2 = _stmt.getText(_columnIndexOfGroupId3);
                    _tmpObjectId = _tmpObjectId2;
                }
                _columnIndexOfObjectId2 = _columnIndexOfGroupId3;
                int _columnIndexOfObjectId3 = _columnIndexOfDeviceName2;
                String _tmpDeviceName = _stmt.getText(_columnIndexOfObjectId3);
                _columnIndexOfDeviceName2 = _columnIndexOfObjectId3;
                String _tmpProjectId2 = _tmpProjectId;
                int _columnIndexOfDeviceName3 = _columnIndexOfIsDeleted2;
                int _tmpProtocolVersion2 = (int) _stmt.getLong(_columnIndexOfDeviceName3);
                int _columnIndexOfProtocolVersion3 = _columnIndexOfBlePacketHex2;
                if (_stmt.isNull(_columnIndexOfProtocolVersion3)) {
                    _tmpBlePacketHex = null;
                } else {
                    String _tmpBlePacketHex2 = _stmt.getText(_columnIndexOfProtocolVersion3);
                    _tmpBlePacketHex = _tmpBlePacketHex2;
                }
                _columnIndexOfBlePacketHex2 = _columnIndexOfProtocolVersion3;
                int _columnIndexOfBlePacketHex3 = _columnIndexOfSeqNum3;
                if (_stmt.isNull(_columnIndexOfBlePacketHex3)) {
                    _tmpProtocolVersion = _tmpProtocolVersion2;
                    _tmpSeqNum = null;
                } else {
                    _tmpProtocolVersion = _tmpProtocolVersion2;
                    _tmpSeqNum = Integer.valueOf((int) _stmt.getLong(_columnIndexOfBlePacketHex3));
                }
                int _columnIndexOfMeasId3 = _columnIndexOfSeqNum4;
                if (_stmt.isNull(_columnIndexOfMeasId3)) {
                    _columnIndexOfSeqNum = _columnIndexOfBlePacketHex3;
                    _tmpMeasId = null;
                } else {
                    _columnIndexOfSeqNum = _columnIndexOfBlePacketHex3;
                    _tmpMeasId = Integer.valueOf((int) _stmt.getLong(_columnIndexOfMeasId3));
                }
                int _columnIndexOfDeviceStatus = _columnIndexOfUuid2;
                if (_stmt.isNull(_columnIndexOfDeviceStatus)) {
                    _columnIndexOfUuid = _columnIndexOfMetadataJson;
                    _columnIndexOfSortOrder = _columnIndexOfCalculatedValue;
                    _tmpDeviceStatus = null;
                } else {
                    _columnIndexOfUuid = _columnIndexOfMetadataJson;
                    _columnIndexOfSortOrder = _columnIndexOfCalculatedValue;
                    Integer _tmpDeviceStatus2 = Integer.valueOf((int) _stmt.getLong(_columnIndexOfDeviceStatus));
                    _tmpDeviceStatus = _tmpDeviceStatus2;
                }
                int _columnIndexOfMetadataJson3 = _columnIndexOfIsAutoDetected;
                if (_stmt.isNull(_columnIndexOfMetadataJson3)) {
                    _tmpMetadataJson = null;
                } else {
                    String _tmpMetadataJson2 = _stmt.getText(_columnIndexOfMetadataJson3);
                    _tmpMetadataJson = _tmpMetadataJson2;
                }
                int _columnIndexOfIsAutoDetected3 = _columnIndexOfMeasId2;
                int _tmp_22 = (int) _stmt.getLong(_columnIndexOfIsAutoDetected3);
                boolean _tmpIsAutoDetected = _tmp_22 != 0;
                int _columnIndexOfCalculatedValue4 = _columnIndexOfCalculatedValue3;
                if (_stmt.isNull(_columnIndexOfCalculatedValue4)) {
                    _tmpCalculatedValue = null;
                } else {
                    Double _tmpCalculatedValue2 = Double.valueOf(_stmt.getDouble(_columnIndexOfCalculatedValue4));
                    _tmpCalculatedValue = _tmpCalculatedValue2;
                }
                if (_stmt.isNull(_columnIndexOfCalculationType2)) {
                    _tmpCalculationType = null;
                } else {
                    String _tmpCalculationType2 = _stmt.getText(_columnIndexOfCalculationType2);
                    _tmpCalculationType = _tmpCalculationType2;
                }
                MeasurementEntity _item = new MeasurementEntity(_tmpId, _tmpUuid, _tmpSortOrder, _tmpMeasurementType, _tmpRefEdge, _tmpResultValue, _tmpComp1Value, _tmpComp2Value, _tmpComp3Value, _tmpAngleDeg, _tmpLaserOn, _tmpTimestamp, _tmpCreatedAt, _tmpUpdatedAt, _tmpIsDeleted, _tmpProjectId2, _tmpGroupId, _tmpObjectId, _tmpDeviceName, _tmpProtocolVersion, _tmpBlePacketHex, _tmpSeqNum, _tmpMeasId, _tmpDeviceStatus, _tmpMetadataJson, _tmpIsAutoDetected, _tmpCalculatedValue, _tmpCalculationType);
                List _result2 = _result;
                _result2.add(_item);
                _result = _result2;
                _columnIndexOfCalculatedValue3 = _columnIndexOfCalculatedValue4;
                _tmp = _columnIndexOfIsDeleted3;
                _columnIndexOfSeqNum3 = _columnIndexOfSeqNum;
                _columnIndexOfSeqNum4 = _columnIndexOfMeasId3;
                _columnIndexOfId = _columnIndexOfId2;
                _columnIndexOfCalculationType = _columnIndexOfCalculationType2;
                _tmp_2 = _columnIndexOfUpdatedAt;
                _columnIndexOfCalculatedValue = _columnIndexOfSortOrder;
                _columnIndexOfIsDeleted2 = _columnIndexOfDeviceName3;
                _columnIndexOfMeasId2 = _columnIndexOfIsAutoDetected3;
                _columnIndexOfProtocolVersion = _tmp_1;
                _columnIndexOfIsAutoDetected = _columnIndexOfMetadataJson3;
                _columnIndexOfMetadataJson = _columnIndexOfUuid;
                _columnIndexOfUuid2 = _columnIndexOfDeviceStatus;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object getByUuid(final String uuid, Continuation<? super MeasurementEntity> continuation) {
        final String _sql = "SELECT * FROM measurements WHERE uuid = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                MeasurementEntity byUuid$lambda$4;
                byUuid$lambda$4 = MeasurementDao_Impl.getByUuid$lambda$4(_sql, uuid, (SQLiteConnection) obj);
                return byUuid$lambda$4;
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasurementEntity getByUuid$lambda$4(String $_sql, String $uuid, SQLiteConnection _connection) {
        MeasurementEntity _result;
        String _tmpProjectId;
        String _tmpGroupId;
        String _tmpObjectId;
        String _tmpBlePacketHex;
        int _tmpProtocolVersion;
        Integer _tmpSeqNum;
        Integer _tmpMeasId;
        Integer _tmpDeviceStatus;
        String _tmpMetadataJson;
        Double _tmpCalculatedValue;
        String _tmpCalculationType;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo122bindText(1, $uuid);
            int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            int _columnIndexOfUuid = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "uuid");
            int _columnIndexOfSortOrder = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "sortOrder");
            int _columnIndexOfMeasurementType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "measurementType");
            int _columnIndexOfRefEdge = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "refEdge");
            int _columnIndexOfResultValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "resultValue");
            int _columnIndexOfComp1Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp1Value");
            int _columnIndexOfComp2Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp2Value");
            int _columnIndexOfComp3Value = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "comp3Value");
            int _columnIndexOfAngleDeg = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "angleDeg");
            int _columnIndexOfLaserOn = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "laserOn");
            int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
            int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
            int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
            int _columnIndexOfIsDeleted = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isDeleted");
            int _columnIndexOfProjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "projectId");
            int _columnIndexOfGroupId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "groupId");
            int _columnIndexOfObjectId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "objectId");
            int _columnIndexOfDeviceName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceName");
            int _columnIndexOfProtocolVersion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "protocolVersion");
            int _columnIndexOfBlePacketHex = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "blePacketHex");
            int _columnIndexOfSeqNum = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "seqNum");
            int _columnIndexOfMeasId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "measId");
            int _columnIndexOfDeviceStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "deviceStatus");
            int _columnIndexOfMetadataJson = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "metadataJson");
            int _columnIndexOfIsAutoDetected = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAutoDetected");
            int _columnIndexOfCalculatedValue = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "calculatedValue");
            int _columnIndexOfCalculationType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "calculationType");
            if (_stmt.step()) {
                int _tmpId = (int) _stmt.getLong(_columnIndexOfId);
                String _tmpUuid = _stmt.getText(_columnIndexOfUuid);
                int _tmpSortOrder = (int) _stmt.getLong(_columnIndexOfSortOrder);
                int _tmpMeasurementType = (int) _stmt.getLong(_columnIndexOfMeasurementType);
                int _tmpRefEdge = (int) _stmt.getLong(_columnIndexOfRefEdge);
                double _tmpResultValue = _stmt.getDouble(_columnIndexOfResultValue);
                double _tmpComp1Value = _stmt.getDouble(_columnIndexOfComp1Value);
                double _tmpComp2Value = _stmt.getDouble(_columnIndexOfComp2Value);
                double _tmpComp3Value = _stmt.getDouble(_columnIndexOfComp3Value);
                double _tmpAngleDeg = _stmt.getDouble(_columnIndexOfAngleDeg);
                int _tmp = (int) _stmt.getLong(_columnIndexOfLaserOn);
                boolean _tmpLaserOn = _tmp != 0;
                long _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
                long _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
                long _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
                int _tmp_1 = (int) _stmt.getLong(_columnIndexOfIsDeleted);
                boolean _tmpIsDeleted = _tmp_1 != 0;
                if (_stmt.isNull(_columnIndexOfProjectId)) {
                    _tmpProjectId = null;
                } else {
                    String _tmpProjectId2 = _stmt.getText(_columnIndexOfProjectId);
                    _tmpProjectId = _tmpProjectId2;
                }
                if (_stmt.isNull(_columnIndexOfGroupId)) {
                    _tmpGroupId = null;
                } else {
                    String _tmpGroupId2 = _stmt.getText(_columnIndexOfGroupId);
                    _tmpGroupId = _tmpGroupId2;
                }
                if (_stmt.isNull(_columnIndexOfObjectId)) {
                    _tmpObjectId = null;
                } else {
                    String _tmpObjectId2 = _stmt.getText(_columnIndexOfObjectId);
                    _tmpObjectId = _tmpObjectId2;
                }
                String _tmpDeviceName = _stmt.getText(_columnIndexOfDeviceName);
                int _tmpProtocolVersion2 = (int) _stmt.getLong(_columnIndexOfProtocolVersion);
                if (_stmt.isNull(_columnIndexOfBlePacketHex)) {
                    _tmpBlePacketHex = null;
                } else {
                    String _tmpBlePacketHex2 = _stmt.getText(_columnIndexOfBlePacketHex);
                    _tmpBlePacketHex = _tmpBlePacketHex2;
                }
                if (_stmt.isNull(_columnIndexOfSeqNum)) {
                    _tmpProtocolVersion = _tmpProtocolVersion2;
                    _tmpSeqNum = null;
                } else {
                    _tmpProtocolVersion = _tmpProtocolVersion2;
                    _tmpSeqNum = Integer.valueOf((int) _stmt.getLong(_columnIndexOfSeqNum));
                }
                if (!_stmt.isNull(_columnIndexOfMeasId)) {
                    _tmpMeasId = Integer.valueOf((int) _stmt.getLong(_columnIndexOfMeasId));
                } else {
                    _tmpMeasId = null;
                }
                if (!_stmt.isNull(_columnIndexOfDeviceStatus)) {
                    Integer _tmpDeviceStatus2 = Integer.valueOf((int) _stmt.getLong(_columnIndexOfDeviceStatus));
                    _tmpDeviceStatus = _tmpDeviceStatus2;
                } else {
                    _tmpDeviceStatus = null;
                }
                if (_stmt.isNull(_columnIndexOfMetadataJson)) {
                    _tmpMetadataJson = null;
                } else {
                    String _tmpMetadataJson2 = _stmt.getText(_columnIndexOfMetadataJson);
                    _tmpMetadataJson = _tmpMetadataJson2;
                }
                int _tmp_2 = (int) _stmt.getLong(_columnIndexOfIsAutoDetected);
                boolean _tmpIsAutoDetected = _tmp_2 != 0;
                if (_stmt.isNull(_columnIndexOfCalculatedValue)) {
                    _tmpCalculatedValue = null;
                } else {
                    Double _tmpCalculatedValue2 = Double.valueOf(_stmt.getDouble(_columnIndexOfCalculatedValue));
                    _tmpCalculatedValue = _tmpCalculatedValue2;
                }
                if (_stmt.isNull(_columnIndexOfCalculationType)) {
                    _tmpCalculationType = null;
                } else {
                    String _tmpCalculationType2 = _stmt.getText(_columnIndexOfCalculationType);
                    _tmpCalculationType = _tmpCalculationType2;
                }
                _result = new MeasurementEntity(_tmpId, _tmpUuid, _tmpSortOrder, _tmpMeasurementType, _tmpRefEdge, _tmpResultValue, _tmpComp1Value, _tmpComp2Value, _tmpComp3Value, _tmpAngleDeg, _tmpLaserOn, _tmpTimestamp, _tmpCreatedAt, _tmpUpdatedAt, _tmpIsDeleted, _tmpProjectId, _tmpGroupId, _tmpObjectId, _tmpDeviceName, _tmpProtocolVersion, _tmpBlePacketHex, _tmpSeqNum, _tmpMeasId, _tmpDeviceStatus, _tmpMetadataJson, _tmpIsAutoDetected, _tmpCalculatedValue, _tmpCalculationType);
            } else {
                _result = null;
            }
            return _result;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object getActiveCount(Continuation<? super Integer> continuation) {
        final String _sql = "SELECT COUNT(*) FROM measurements WHERE isDeleted = 0";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int activeCount$lambda$5;
                activeCount$lambda$5 = MeasurementDao_Impl.getActiveCount$lambda$5(_sql, (SQLiteConnection) obj);
                return Integer.valueOf(activeCount$lambda$5);
            }
        }, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getActiveCount$lambda$5(String $_sql, SQLiteConnection _connection) {
        int _tmp;
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            if (_stmt.step()) {
                _tmp = (int) _stmt.getLong(0);
            } else {
                _tmp = 0;
            }
            return _tmp;
        } finally {
            _stmt.close();
        }
    }

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object softDelete(final String uuid, final long timestamp, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE measurements SET isDeleted = 1, updatedAt = ? WHERE uuid = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit softDelete$lambda$6;
                softDelete$lambda$6 = MeasurementDao_Impl.softDelete$lambda$6(_sql, timestamp, uuid, (SQLiteConnection) obj);
                return softDelete$lambda$6;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit softDelete$lambda$6(String $_sql, long $timestamp, String $uuid, SQLiteConnection _connection) {
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

    @Override // com.glmreader.android.data.dao.MeasurementDao
    public Object updateSortOrder(final String uuid, final int newOrder, Continuation<? super Unit> continuation) {
        final String _sql = "UPDATE measurements SET sortOrder = ? WHERE uuid = ?";
        Object performSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.glmreader.android.data.dao.MeasurementDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit updateSortOrder$lambda$7;
                updateSortOrder$lambda$7 = MeasurementDao_Impl.updateSortOrder$lambda$7(_sql, newOrder, uuid, (SQLiteConnection) obj);
                return updateSortOrder$lambda$7;
            }
        }, continuation);
        return performSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? performSuspending : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateSortOrder$lambda$7(String $_sql, int $newOrder, String $uuid, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement _stmt = _connection.prepare($_sql);
        try {
            _stmt.mo120bindLong(1, $newOrder);
            _stmt.mo122bindText(2, $uuid);
            _stmt.step();
            _stmt.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            _stmt.close();
            throw th;
        }
    }

    /* compiled from: MeasurementDao_Impl.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/glmreader/android/data/dao/MeasurementDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
