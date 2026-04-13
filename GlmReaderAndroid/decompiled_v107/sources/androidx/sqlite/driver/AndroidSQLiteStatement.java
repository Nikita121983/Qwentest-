package androidx.sqlite.driver;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.driver.AndroidSQLiteStatement;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidSQLiteStatement.android.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00132\u00020\u0001:\u0003\u0013\u0014\u0015B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u0016\u0017¨\u0006\u0018"}, d2 = {"Landroidx/sqlite/driver/AndroidSQLiteStatement;", "Landroidx/sqlite/SQLiteStatement;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "sql", "", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "getSql", "()Ljava/lang/String;", "isClosed", "", "()Z", "setClosed", "(Z)V", "throwIfClosed", "", "Companion", "SelectAndroidSQLiteStatement", "OtherAndroidSQLiteStatement", "Landroidx/sqlite/driver/AndroidSQLiteStatement$OtherAndroidSQLiteStatement;", "Landroidx/sqlite/driver/AndroidSQLiteStatement$SelectAndroidSQLiteStatement;", "sqlite-framework_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AndroidSQLiteStatement implements SQLiteStatement {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final SQLiteDatabase db;
    private boolean isClosed;
    private final String sql;

    public /* synthetic */ AndroidSQLiteStatement(SQLiteDatabase sQLiteDatabase, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(sQLiteDatabase, str);
    }

    private AndroidSQLiteStatement(SQLiteDatabase db, String sql) {
        this.db = db;
        this.sql = sql;
    }

    protected final SQLiteDatabase getDb() {
        return this.db;
    }

    protected final String getSql() {
        return this.sql;
    }

    /* renamed from: isClosed, reason: from getter */
    protected final boolean getIsClosed() {
        return this.isClosed;
    }

    protected final void setClosed(boolean z) {
        this.isClosed = z;
    }

    protected final void throwIfClosed() {
        if (this.isClosed) {
            SQLite.throwSQLiteException(21, "statement is closed");
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: AndroidSQLiteStatement.android.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\f"}, d2 = {"Landroidx/sqlite/driver/AndroidSQLiteStatement$Companion;", "", "<init>", "()V", "create", "Landroidx/sqlite/driver/AndroidSQLiteStatement;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "sql", "", "isRowStatement", "", "sqlite-framework_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AndroidSQLiteStatement create(SQLiteDatabase db, String sql) {
            Intrinsics.checkNotNullParameter(db, "db");
            Intrinsics.checkNotNullParameter(sql, "sql");
            if (isRowStatement(sql)) {
                return new SelectAndroidSQLiteStatement(db, sql);
            }
            return new OtherAndroidSQLiteStatement(db, sql);
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x004a A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final boolean isRowStatement(java.lang.String r5) {
            /*
                r4 = this;
                r0 = r5
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                java.lang.CharSequence r0 = kotlin.text.StringsKt.trim(r0)
                java.lang.String r0 = r0.toString()
                int r1 = r0.length()
                r2 = 0
                r3 = 3
                if (r1 >= r3) goto L14
                return r2
            L14:
                java.lang.String r1 = r0.substring(r2, r3)
                java.lang.String r3 = "substring(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                java.util.Locale r3 = java.util.Locale.ROOT
                java.lang.String r1 = r1.toUpperCase(r3)
                java.lang.String r3 = "toUpperCase(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                int r3 = r1.hashCode()
                switch(r3) {
                    case 79487: goto L42;
                    case 81978: goto L39;
                    case 85954: goto L30;
                    default: goto L2f;
                }
            L2f:
                goto L4c
            L30:
                java.lang.String r3 = "WIT"
                boolean r1 = r1.equals(r3)
                if (r1 != 0) goto L4a
                goto L2f
            L39:
                java.lang.String r3 = "SEL"
                boolean r1 = r1.equals(r3)
                if (r1 != 0) goto L4a
                goto L2f
            L42:
                java.lang.String r3 = "PRA"
                boolean r1 = r1.equals(r3)
                if (r1 == 0) goto L2f
            L4a:
                r2 = 1
                goto L4d
            L4c:
            L4d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.driver.AndroidSQLiteStatement.Companion.isRowStatement(java.lang.String):boolean");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AndroidSQLiteStatement.android.kt */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 72\u00020\u0001:\u00017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010'\u001a\u00020\u0019H\u0016J\u0010\u0010(\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010*\u001a\u00020&H\u0016J\b\u0010+\u001a\u00020\u0017H\u0016J\b\u0010,\u001a\u00020\u0017H\u0016J\b\u0010-\u001a\u00020\u0017H\u0016J\u0018\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u00100\u001a\u00020\u0017H\u0002J\u0010\u00101\u001a\u00020\u00172\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020\u0015H\u0002J\u0018\u00105\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0018\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/sqlite/driver/AndroidSQLiteStatement$SelectAndroidSQLiteStatement;", "Landroidx/sqlite/driver/AndroidSQLiteStatement;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "sql", "", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V", "bindingTypes", "", "longBindings", "", "doubleBindings", "", "stringBindings", "", "[Ljava/lang/String;", "blobBindings", "", "[[B", "cursor", "Landroid/database/Cursor;", "bindBlob", "", "index", "", "value", "bindDouble", "", "bindLong", "", "bindText", "bindNull", "getBlob", "getDouble", "getLong", "getText", "isNull", "", "getColumnCount", "getColumnName", "getColumnType", "step", "reset", "clearBindings", "close", "ensureCapacity", "columnType", "ensureCursor", "bindTo", "query", "Landroid/database/sqlite/SQLiteProgram;", "throwIfNoRow", "throwIfInvalidColumn", "c", "Companion", "sqlite-framework_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class SelectAndroidSQLiteStatement extends AndroidSQLiteStatement {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private int[] bindingTypes;
        private byte[][] blobBindings;
        private Cursor cursor;
        private double[] doubleBindings;
        private long[] longBindings;
        private String[] stringBindings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SelectAndroidSQLiteStatement(SQLiteDatabase db, String sql) {
            super(db, sql, null);
            Intrinsics.checkNotNullParameter(db, "db");
            Intrinsics.checkNotNullParameter(sql, "sql");
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0];
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindBlob */
        public void mo118bindBlob(int index, byte[] value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throwIfClosed();
            ensureCapacity(4, index);
            this.bindingTypes[index] = 4;
            this.blobBindings[index] = value;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindDouble */
        public void mo119bindDouble(int index, double value) {
            throwIfClosed();
            ensureCapacity(2, index);
            this.bindingTypes[index] = 2;
            this.doubleBindings[index] = value;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindLong */
        public void mo120bindLong(int index, long value) {
            throwIfClosed();
            ensureCapacity(1, index);
            this.bindingTypes[index] = 1;
            this.longBindings[index] = value;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindText */
        public void mo122bindText(int index, String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throwIfClosed();
            ensureCapacity(3, index);
            this.bindingTypes[index] = 3;
            this.stringBindings[index] = value;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindNull */
        public void mo121bindNull(int index) {
            throwIfClosed();
            ensureCapacity(5, index);
            this.bindingTypes[index] = 5;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int index) {
            throwIfClosed();
            Cursor c = throwIfNoRow();
            throwIfInvalidColumn(c, index);
            byte[] blob = c.getBlob(index);
            Intrinsics.checkNotNullExpressionValue(blob, "getBlob(...)");
            return blob;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public double getDouble(int index) {
            throwIfClosed();
            Cursor c = throwIfNoRow();
            throwIfInvalidColumn(c, index);
            return c.getDouble(index);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int index) {
            throwIfClosed();
            Cursor c = throwIfNoRow();
            throwIfInvalidColumn(c, index);
            return c.getLong(index);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int index) {
            throwIfClosed();
            Cursor c = throwIfNoRow();
            throwIfInvalidColumn(c, index);
            String string = c.getString(index);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int index) {
            throwIfClosed();
            Cursor c = throwIfNoRow();
            throwIfInvalidColumn(c, index);
            return c.isNull(index);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            throwIfClosed();
            ensureCursor();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor.getColumnCount();
            }
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int index) {
            throwIfClosed();
            ensureCursor();
            Cursor c = this.cursor;
            if (c == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            throwIfInvalidColumn(c, index);
            String columnName = c.getColumnName(index);
            Intrinsics.checkNotNullExpressionValue(columnName, "getColumnName(...)");
            return columnName;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnType(int index) {
            throwIfClosed();
            ensureCursor();
            Cursor c = this.cursor;
            if (c == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            throwIfInvalidColumn(c, index);
            return INSTANCE.getDataType(c, index);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            throwIfClosed();
            ensureCursor();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor.moveToNext();
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            throwIfClosed();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                cursor.close();
            }
            this.cursor = null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: clearBindings */
        public void mo123clearBindings() {
            throwIfClosed();
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0];
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            if (!getIsClosed()) {
                reset();
            }
            setClosed(true);
        }

        private final void ensureCapacity(int columnType, int index) {
            int requiredSize = index + 1;
            if (this.bindingTypes.length < requiredSize) {
                int[] copyOf = Arrays.copyOf(this.bindingTypes, requiredSize);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                this.bindingTypes = copyOf;
            }
            switch (columnType) {
                case 1:
                    if (this.longBindings.length < requiredSize) {
                        long[] copyOf2 = Arrays.copyOf(this.longBindings, requiredSize);
                        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(...)");
                        this.longBindings = copyOf2;
                        return;
                    }
                    return;
                case 2:
                    if (this.doubleBindings.length < requiredSize) {
                        double[] copyOf3 = Arrays.copyOf(this.doubleBindings, requiredSize);
                        Intrinsics.checkNotNullExpressionValue(copyOf3, "copyOf(...)");
                        this.doubleBindings = copyOf3;
                        return;
                    }
                    return;
                case 3:
                    if (this.stringBindings.length < requiredSize) {
                        Object[] copyOf4 = Arrays.copyOf(this.stringBindings, requiredSize);
                        Intrinsics.checkNotNullExpressionValue(copyOf4, "copyOf(...)");
                        this.stringBindings = (String[]) copyOf4;
                        return;
                    }
                    return;
                case 4:
                    if (this.blobBindings.length < requiredSize) {
                        Object[] copyOf5 = Arrays.copyOf(this.blobBindings, requiredSize);
                        Intrinsics.checkNotNullExpressionValue(copyOf5, "copyOf(...)");
                        this.blobBindings = (byte[][]) copyOf5;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private final void ensureCursor() {
            if (this.cursor == null) {
                this.cursor = getDb().rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: androidx.sqlite.driver.AndroidSQLiteStatement$SelectAndroidSQLiteStatement$$ExternalSyntheticLambda0
                    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                        Cursor ensureCursor$lambda$0;
                        ensureCursor$lambda$0 = AndroidSQLiteStatement.SelectAndroidSQLiteStatement.ensureCursor$lambda$0(AndroidSQLiteStatement.SelectAndroidSQLiteStatement.this, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                        return ensureCursor$lambda$0;
                    }
                }, getSql(), new String[0], null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Cursor ensureCursor$lambda$0(SelectAndroidSQLiteStatement this$0, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
            Intrinsics.checkNotNull(query);
            this$0.bindTo(query);
            return new SQLiteCursor(masterQuery, editTable, query);
        }

        private final void bindTo(SQLiteProgram query) {
            int length = this.bindingTypes.length;
            for (int index = 1; index < length; index++) {
                switch (this.bindingTypes[index]) {
                    case 1:
                        query.bindLong(index, this.longBindings[index]);
                        break;
                    case 2:
                        query.bindDouble(index, this.doubleBindings[index]);
                        break;
                    case 3:
                        query.bindString(index, this.stringBindings[index]);
                        break;
                    case 4:
                        query.bindBlob(index, this.blobBindings[index]);
                        break;
                    case 5:
                        query.bindNull(index);
                        break;
                }
            }
        }

        private final Cursor throwIfNoRow() {
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor;
            }
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        private final void throwIfInvalidColumn(Cursor c, int index) {
            if (index < 0 || index >= c.getColumnCount()) {
                SQLite.throwSQLiteException(25, "column index out of range");
                throw new KotlinNothingValueException();
            }
        }

        /* compiled from: AndroidSQLiteStatement.android.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005H\u0002¨\u0006\b"}, d2 = {"Landroidx/sqlite/driver/AndroidSQLiteStatement$SelectAndroidSQLiteStatement$Companion;", "", "<init>", "()V", "getDataType", "", "Landroid/database/Cursor;", "index", "sqlite-framework_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* loaded from: classes.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int getDataType(Cursor $this$getDataType, int index) {
                int fieldType = $this$getDataType.getType(index);
                switch ($this$getDataType.getType(index)) {
                    case 0:
                        return 5;
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    case 3:
                        return 3;
                    case 4:
                        return 4;
                    default:
                        throw new IllegalStateException(("Unknown field type: " + fieldType).toString());
                }
            }
        }
    }

    /* compiled from: AndroidSQLiteStatement.android.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\fH\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\b\u0010#\u001a\u00020\fH\u0016R\u0012\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/sqlite/driver/AndroidSQLiteStatement$OtherAndroidSQLiteStatement;", "Landroidx/sqlite/driver/AndroidSQLiteStatement;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "sql", "", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;)V", "delegate", "Landroid/database/sqlite/SQLiteStatement;", "Landroidx/sqlite/driver/FrameworkStatement;", "bindBlob", "", "index", "", "value", "", "bindDouble", "", "bindLong", "", "bindText", "bindNull", "getBlob", "getDouble", "getLong", "getText", "isNull", "", "getColumnCount", "getColumnName", "getColumnType", "step", "reset", "clearBindings", "close", "sqlite-framework_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    private static final class OtherAndroidSQLiteStatement extends AndroidSQLiteStatement {
        private final android.database.sqlite.SQLiteStatement delegate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OtherAndroidSQLiteStatement(SQLiteDatabase db, String sql) {
            super(db, sql, null);
            Intrinsics.checkNotNullParameter(db, "db");
            Intrinsics.checkNotNullParameter(sql, "sql");
            android.database.sqlite.SQLiteStatement compileStatement = db.compileStatement(sql);
            Intrinsics.checkNotNullExpressionValue(compileStatement, "compileStatement(...)");
            this.delegate = compileStatement;
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindBlob */
        public void mo118bindBlob(int index, byte[] value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throwIfClosed();
            this.delegate.bindBlob(index, value);
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindDouble */
        public void mo119bindDouble(int index, double value) {
            throwIfClosed();
            this.delegate.bindDouble(index, value);
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindLong */
        public void mo120bindLong(int index, long value) {
            throwIfClosed();
            this.delegate.bindLong(index, value);
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindText */
        public void mo122bindText(int index, String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            throwIfClosed();
            this.delegate.bindString(index, value);
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: bindNull */
        public void mo121bindNull(int index) {
            throwIfClosed();
            this.delegate.bindNull(index);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public double getDouble(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            throwIfClosed();
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnType(int index) {
            throwIfClosed();
            SQLite.throwSQLiteException(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            throwIfClosed();
            this.delegate.execute();
            return false;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
        }

        @Override // androidx.sqlite.SQLiteStatement
        /* renamed from: clearBindings */
        public void mo123clearBindings() {
            throwIfClosed();
            this.delegate.clearBindings();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
            setClosed(true);
        }
    }
}
