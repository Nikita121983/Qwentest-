package androidx.room.util;

import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StatementUtil.android.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"columnIndexOf", "", "Landroidx/sqlite/SQLiteStatement;", "name", "", "findColumnIndexBySuffix", "findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt", "room-runtime_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/room/util/SQLiteStatementUtil")
/* loaded from: classes.dex */
public final /* synthetic */ class SQLiteStatementUtil__StatementUtil_androidKt {
    public static final int columnIndexOf(SQLiteStatement $this$columnIndexOf, String name) {
        Intrinsics.checkNotNullParameter($this$columnIndexOf, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        int index = SQLiteStatementUtil.columnIndexOfCommon($this$columnIndexOf, name);
        if (index >= 0) {
            return index;
        }
        int index2 = SQLiteStatementUtil.columnIndexOfCommon($this$columnIndexOf, '`' + name + '`');
        if (index2 >= 0) {
            return index2;
        }
        return findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt($this$columnIndexOf, name);
    }

    private static final int findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt(SQLiteStatement $this$findColumnIndexBySuffix, String name) {
        return -1;
    }
}
