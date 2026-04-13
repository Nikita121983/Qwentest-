package com.glmreader.android.data;

import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.glmreader.android.data.dao.GroupDao;
import com.glmreader.android.data.dao.GroupDao_Impl;
import com.glmreader.android.data.dao.MeasurementDao;
import com.glmreader.android.data.dao.MeasurementDao_Impl;
import com.glmreader.android.data.dao.NoteDao;
import com.glmreader.android.data.dao.NoteDao_Impl;
import com.glmreader.android.data.dao.ObjectDao;
import com.glmreader.android.data.dao.ObjectDao_Impl;
import com.glmreader.android.data.dao.ProjectDao;
import com.glmreader.android.data.dao.ProjectDao_Impl;
import com.glmreader.android.data.dao.SettingsDao;
import com.glmreader.android.data.dao.SettingsDao_Impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: AppDatabase_Impl.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\"\u0010\u0017\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u001a0\u0018H\u0014J\u0016\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u00190\u001cH\u0016J*\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001a2\u001a\u0010 \u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u0019\u0012\u0004\u0012\u00020\u001d0\u0018H\u0016J\b\u0010!\u001a\u00020\u0006H\u0016J\b\u0010\"\u001a\u00020\bH\u0016J\b\u0010#\u001a\u00020\nH\u0016J\b\u0010$\u001a\u00020\fH\u0016J\b\u0010%\u001a\u00020\u000eH\u0016J\b\u0010&\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/glmreader/android/data/AppDatabase_Impl;", "Lcom/glmreader/android/data/AppDatabase;", "<init>", "()V", "_measurementDao", "Lkotlin/Lazy;", "Lcom/glmreader/android/data/dao/MeasurementDao;", "_noteDao", "Lcom/glmreader/android/data/dao/NoteDao;", "_settingsDao", "Lcom/glmreader/android/data/dao/SettingsDao;", "_projectDao", "Lcom/glmreader/android/data/dao/ProjectDao;", "_groupDao", "Lcom/glmreader/android/data/dao/GroupDao;", "_objectDao", "Lcom/glmreader/android/data/dao/ObjectDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "measurementDao", "noteDao", "settingsDao", "projectDao", "groupDao", "objectDao", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class AppDatabase_Impl extends AppDatabase {
    private final Lazy<MeasurementDao> _measurementDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            MeasurementDao_Impl _measurementDao$lambda$0;
            _measurementDao$lambda$0 = AppDatabase_Impl._measurementDao$lambda$0(AppDatabase_Impl.this);
            return _measurementDao$lambda$0;
        }
    });
    private final Lazy<NoteDao> _noteDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            NoteDao_Impl _noteDao$lambda$1;
            _noteDao$lambda$1 = AppDatabase_Impl._noteDao$lambda$1(AppDatabase_Impl.this);
            return _noteDao$lambda$1;
        }
    });
    private final Lazy<SettingsDao> _settingsDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            SettingsDao_Impl _settingsDao$lambda$2;
            _settingsDao$lambda$2 = AppDatabase_Impl._settingsDao$lambda$2(AppDatabase_Impl.this);
            return _settingsDao$lambda$2;
        }
    });
    private final Lazy<ProjectDao> _projectDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ProjectDao_Impl _projectDao$lambda$3;
            _projectDao$lambda$3 = AppDatabase_Impl._projectDao$lambda$3(AppDatabase_Impl.this);
            return _projectDao$lambda$3;
        }
    });
    private final Lazy<GroupDao> _groupDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            GroupDao_Impl _groupDao$lambda$4;
            _groupDao$lambda$4 = AppDatabase_Impl._groupDao$lambda$4(AppDatabase_Impl.this);
            return _groupDao$lambda$4;
        }
    });
    private final Lazy<ObjectDao> _objectDao = LazyKt.lazy(new Function0() { // from class: com.glmreader.android.data.AppDatabase_Impl$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ObjectDao_Impl _objectDao$lambda$5;
            _objectDao$lambda$5 = AppDatabase_Impl._objectDao$lambda$5(AppDatabase_Impl.this);
            return _objectDao$lambda$5;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasurementDao_Impl _measurementDao$lambda$0(AppDatabase_Impl this$0) {
        return new MeasurementDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NoteDao_Impl _noteDao$lambda$1(AppDatabase_Impl this$0) {
        return new NoteDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SettingsDao_Impl _settingsDao$lambda$2(AppDatabase_Impl this$0) {
        return new SettingsDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ProjectDao_Impl _projectDao$lambda$3(AppDatabase_Impl this$0) {
        return new ProjectDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GroupDao_Impl _groupDao$lambda$4(AppDatabase_Impl this$0) {
        return new GroupDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObjectDao_Impl _objectDao$lambda$5(AppDatabase_Impl this$0) {
        return new ObjectDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        RoomOpenDelegate _openDelegate = new RoomOpenDelegate() { // from class: com.glmreader.android.data.AppDatabase_Impl$createOpenDelegate$_openDelegate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3, "9c83d6bff7c590b997c453ea64e90c18", "ae3d86c55ee55505d2c79ee506dc855a");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `measurements` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, `measurementType` INTEGER NOT NULL, `refEdge` INTEGER NOT NULL, `resultValue` REAL NOT NULL, `comp1Value` REAL NOT NULL, `comp2Value` REAL NOT NULL, `comp3Value` REAL NOT NULL, `angleDeg` REAL NOT NULL, `laserOn` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `isDeleted` INTEGER NOT NULL, `projectId` TEXT, `groupId` TEXT, `objectId` TEXT, `deviceName` TEXT NOT NULL, `protocolVersion` INTEGER NOT NULL, `blePacketHex` TEXT, `seqNum` INTEGER, `measId` INTEGER, `deviceStatus` INTEGER, `metadataJson` TEXT, `isAutoDetected` INTEGER NOT NULL, `calculatedValue` REAL, `calculationType` TEXT)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_measurements_uuid` ON `measurements` (`uuid`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `notes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `measurementUuid` TEXT NOT NULL, `version` INTEGER NOT NULL, `text` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `isPrimary` INTEGER NOT NULL, `isVoice` INTEGER NOT NULL, `mediaPath` TEXT)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_notes_measurementUuid` ON `notes` (`measurementUuid`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `settings` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`key`))");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `projects` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `isArchived` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_projects_uuid` ON `projects` (`uuid`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `groups` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `projectId` TEXT NOT NULL, `name` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`projectId`) REFERENCES `projects`(`uuid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_groups_uuid` ON `groups` (`uuid`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_groups_projectId` ON `groups` (`projectId`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `objects` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `uuid` TEXT NOT NULL, `groupId` TEXT NOT NULL, `name` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`groupId`) REFERENCES `groups`(`uuid`) ON UPDATE NO ACTION ON DELETE CASCADE )");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_objects_uuid` ON `objects` (`uuid`)");
                SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_objects_groupId` ON `objects` (`groupId`)");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9c83d6bff7c590b997c453ea64e90c18')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `measurements`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `notes`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `settings`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `projects`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `groups`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `objects`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
                AppDatabase_Impl.this.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Map _columnsMeasurements = new LinkedHashMap();
                _columnsMeasurements.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsMeasurements.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, 1));
                _columnsMeasurements.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("measurementType", new TableInfo.Column("measurementType", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("refEdge", new TableInfo.Column("refEdge", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("resultValue", new TableInfo.Column("resultValue", "REAL", true, 0, null, 1));
                _columnsMeasurements.put("comp1Value", new TableInfo.Column("comp1Value", "REAL", true, 0, null, 1));
                _columnsMeasurements.put("comp2Value", new TableInfo.Column("comp2Value", "REAL", true, 0, null, 1));
                _columnsMeasurements.put("comp3Value", new TableInfo.Column("comp3Value", "REAL", true, 0, null, 1));
                _columnsMeasurements.put("angleDeg", new TableInfo.Column("angleDeg", "REAL", true, 0, null, 1));
                _columnsMeasurements.put("laserOn", new TableInfo.Column("laserOn", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("projectId", new TableInfo.Column("projectId", "TEXT", false, 0, null, 1));
                _columnsMeasurements.put("groupId", new TableInfo.Column("groupId", "TEXT", false, 0, null, 1));
                _columnsMeasurements.put("objectId", new TableInfo.Column("objectId", "TEXT", false, 0, null, 1));
                _columnsMeasurements.put("deviceName", new TableInfo.Column("deviceName", "TEXT", true, 0, null, 1));
                _columnsMeasurements.put("protocolVersion", new TableInfo.Column("protocolVersion", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("blePacketHex", new TableInfo.Column("blePacketHex", "TEXT", false, 0, null, 1));
                _columnsMeasurements.put("seqNum", new TableInfo.Column("seqNum", "INTEGER", false, 0, null, 1));
                _columnsMeasurements.put("measId", new TableInfo.Column("measId", "INTEGER", false, 0, null, 1));
                _columnsMeasurements.put("deviceStatus", new TableInfo.Column("deviceStatus", "INTEGER", false, 0, null, 1));
                _columnsMeasurements.put("metadataJson", new TableInfo.Column("metadataJson", "TEXT", false, 0, null, 1));
                _columnsMeasurements.put("isAutoDetected", new TableInfo.Column("isAutoDetected", "INTEGER", true, 0, null, 1));
                _columnsMeasurements.put("calculatedValue", new TableInfo.Column("calculatedValue", "REAL", false, 0, null, 1));
                _columnsMeasurements.put("calculationType", new TableInfo.Column("calculationType", "TEXT", false, 0, null, 1));
                Set _foreignKeysMeasurements = new LinkedHashSet();
                Set _indicesMeasurements = new LinkedHashSet();
                _indicesMeasurements.add(new TableInfo.Index("index_measurements_uuid", true, CollectionsKt.listOf("uuid"), CollectionsKt.listOf("ASC")));
                TableInfo _infoMeasurements = new TableInfo("measurements", _columnsMeasurements, _foreignKeysMeasurements, _indicesMeasurements);
                TableInfo _existingMeasurements = TableInfo.INSTANCE.read(connection, "measurements");
                if (!_infoMeasurements.equals(_existingMeasurements)) {
                    return new RoomOpenDelegate.ValidationResult(false, "measurements(com.glmreader.android.data.entity.MeasurementEntity).\n Expected:\n" + _infoMeasurements + "\n Found:\n" + _existingMeasurements);
                }
                Map _columnsNotes = new LinkedHashMap();
                _columnsNotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsNotes.put("measurementUuid", new TableInfo.Column("measurementUuid", "TEXT", true, 0, null, 1));
                _columnsNotes.put("version", new TableInfo.Column("version", "INTEGER", true, 0, null, 1));
                _columnsNotes.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, 1));
                _columnsNotes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                _columnsNotes.put("isPrimary", new TableInfo.Column("isPrimary", "INTEGER", true, 0, null, 1));
                _columnsNotes.put("isVoice", new TableInfo.Column("isVoice", "INTEGER", true, 0, null, 1));
                _columnsNotes.put("mediaPath", new TableInfo.Column("mediaPath", "TEXT", false, 0, null, 1));
                Set _foreignKeysNotes = new LinkedHashSet();
                Set _indicesNotes = new LinkedHashSet();
                _indicesNotes.add(new TableInfo.Index("index_notes_measurementUuid", false, CollectionsKt.listOf("measurementUuid"), CollectionsKt.listOf("ASC")));
                TableInfo _infoNotes = new TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes);
                TableInfo _existingNotes = TableInfo.INSTANCE.read(connection, "notes");
                if (!_infoNotes.equals(_existingNotes)) {
                    return new RoomOpenDelegate.ValidationResult(false, "notes(com.glmreader.android.data.entity.NoteEntity).\n Expected:\n" + _infoNotes + "\n Found:\n" + _existingNotes);
                }
                Map _columnsSettings = new LinkedHashMap();
                _columnsSettings.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, 1));
                _columnsSettings.put("value", new TableInfo.Column("value", "TEXT", true, 0, null, 1));
                _columnsSettings.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysSettings = new LinkedHashSet();
                Set _indicesSettings = new LinkedHashSet();
                TableInfo _infoSettings = new TableInfo("settings", _columnsSettings, _foreignKeysSettings, _indicesSettings);
                TableInfo _existingSettings = TableInfo.INSTANCE.read(connection, "settings");
                if (!_infoSettings.equals(_existingSettings)) {
                    return new RoomOpenDelegate.ValidationResult(false, "settings(com.glmreader.android.data.entity.SettingsEntity).\n Expected:\n" + _infoSettings + "\n Found:\n" + _existingSettings);
                }
                Map _columnsProjects = new LinkedHashMap();
                _columnsProjects.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsProjects.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, 1));
                _columnsProjects.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                _columnsProjects.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, 1));
                _columnsProjects.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, 1));
                _columnsProjects.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                _columnsProjects.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, 1));
                _columnsProjects.put("isArchived", new TableInfo.Column("isArchived", "INTEGER", true, 0, null, 1));
                Set _foreignKeysProjects = new LinkedHashSet();
                Set _indicesProjects = new LinkedHashSet();
                _indicesProjects.add(new TableInfo.Index("index_projects_uuid", true, CollectionsKt.listOf("uuid"), CollectionsKt.listOf("ASC")));
                TableInfo _infoProjects = new TableInfo("projects", _columnsProjects, _foreignKeysProjects, _indicesProjects);
                TableInfo _existingProjects = TableInfo.INSTANCE.read(connection, "projects");
                if (!_infoProjects.equals(_existingProjects)) {
                    return new RoomOpenDelegate.ValidationResult(false, "projects(com.glmreader.android.data.entity.ProjectEntity).\n Expected:\n" + _infoProjects + "\n Found:\n" + _existingProjects);
                }
                Map _columnsGroups = new LinkedHashMap();
                _columnsGroups.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsGroups.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, 1));
                _columnsGroups.put("projectId", new TableInfo.Column("projectId", "TEXT", true, 0, null, 1));
                _columnsGroups.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                _columnsGroups.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, 1));
                _columnsGroups.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysGroups = new LinkedHashSet();
                _foreignKeysGroups.add(new TableInfo.ForeignKey("projects", "CASCADE", "NO ACTION", CollectionsKt.listOf("projectId"), CollectionsKt.listOf("uuid")));
                Set _indicesGroups = new LinkedHashSet();
                _indicesGroups.add(new TableInfo.Index("index_groups_uuid", true, CollectionsKt.listOf("uuid"), CollectionsKt.listOf("ASC")));
                _indicesGroups.add(new TableInfo.Index("index_groups_projectId", false, CollectionsKt.listOf("projectId"), CollectionsKt.listOf("ASC")));
                TableInfo _infoGroups = new TableInfo("groups", _columnsGroups, _foreignKeysGroups, _indicesGroups);
                TableInfo _existingGroups = TableInfo.INSTANCE.read(connection, "groups");
                if (!_infoGroups.equals(_existingGroups)) {
                    return new RoomOpenDelegate.ValidationResult(false, "groups(com.glmreader.android.data.entity.GroupEntity).\n Expected:\n" + _infoGroups + "\n Found:\n" + _existingGroups);
                }
                Map _columnsObjects = new LinkedHashMap();
                _columnsObjects.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsObjects.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 0, null, 1));
                _columnsObjects.put("groupId", new TableInfo.Column("groupId", "TEXT", true, 0, null, 1));
                _columnsObjects.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
                _columnsObjects.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, 1));
                _columnsObjects.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
                Set _foreignKeysObjects = new LinkedHashSet();
                _foreignKeysObjects.add(new TableInfo.ForeignKey("groups", "CASCADE", "NO ACTION", CollectionsKt.listOf("groupId"), CollectionsKt.listOf("uuid")));
                Set _indicesObjects = new LinkedHashSet();
                _indicesObjects.add(new TableInfo.Index("index_objects_uuid", true, CollectionsKt.listOf("uuid"), CollectionsKt.listOf("ASC")));
                _indicesObjects.add(new TableInfo.Index("index_objects_groupId", false, CollectionsKt.listOf("groupId"), CollectionsKt.listOf("ASC")));
                TableInfo _infoObjects = new TableInfo("objects", _columnsObjects, _foreignKeysObjects, _indicesObjects);
                TableInfo _existingObjects = TableInfo.INSTANCE.read(connection, "objects");
                if (!_infoObjects.equals(_existingObjects)) {
                    return new RoomOpenDelegate.ValidationResult(false, "objects(com.glmreader.android.data.entity.ObjectEntity).\n Expected:\n" + _infoObjects + "\n Found:\n" + _existingObjects);
                }
                return new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
        return _openDelegate;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        Map _shadowTablesMap = new LinkedHashMap();
        Map _viewTables = new LinkedHashMap();
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "measurements", "notes", "settings", "projects", "groups", "objects");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(true, "measurements", "notes", "settings", "projects", "groups", "objects");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Map _typeConvertersMap = new LinkedHashMap();
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(MeasurementDao.class), MeasurementDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(NoteDao.class), NoteDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(SettingsDao.class), SettingsDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ProjectDao.class), ProjectDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(GroupDao.class), GroupDao_Impl.INSTANCE.getRequiredConverters());
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(ObjectDao.class), ObjectDao_Impl.INSTANCE.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Set _autoMigrationSpecsSet = new LinkedHashSet();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        List _autoMigrations = new ArrayList();
        return _autoMigrations;
    }

    @Override // com.glmreader.android.data.AppDatabase
    public MeasurementDao measurementDao() {
        return this._measurementDao.getValue();
    }

    @Override // com.glmreader.android.data.AppDatabase
    public NoteDao noteDao() {
        return this._noteDao.getValue();
    }

    @Override // com.glmreader.android.data.AppDatabase
    public SettingsDao settingsDao() {
        return this._settingsDao.getValue();
    }

    @Override // com.glmreader.android.data.AppDatabase
    public ProjectDao projectDao() {
        return this._projectDao.getValue();
    }

    @Override // com.glmreader.android.data.AppDatabase
    public GroupDao groupDao() {
        return this._groupDao.getValue();
    }

    @Override // com.glmreader.android.data.AppDatabase
    public ObjectDao objectDao() {
        return this._objectDao.getValue();
    }
}
