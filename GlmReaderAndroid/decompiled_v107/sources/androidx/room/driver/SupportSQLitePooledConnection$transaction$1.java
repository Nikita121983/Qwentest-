package androidx.room.driver;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SupportSQLiteConnectionPool.android.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.driver.SupportSQLitePooledConnection", f = "SupportSQLiteConnectionPool.android.kt", i = {0, 0}, l = {83}, m = "transaction", n = {"this", "db"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class SupportSQLitePooledConnection$transaction$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SupportSQLitePooledConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SupportSQLitePooledConnection$transaction$1(SupportSQLitePooledConnection supportSQLitePooledConnection, Continuation<? super SupportSQLitePooledConnection$transaction$1> continuation) {
        super(continuation);
        this.this$0 = supportSQLitePooledConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transaction(null, null, this);
    }
}
