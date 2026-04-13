package androidx.room.coroutines;

import androidx.collection.CircularArray;
import androidx.sqlite.SQLiteConnection;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnectionPoolImpl.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u001c\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0016J\u0006\u0010\"\u001a\u00020\u001fJ\u0012\u0010#\u001a\u00020\u001f2\n\u0010$\u001a\u00060%j\u0002`&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00060\u000ej\u0002`\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/room/coroutines/Pool;", "", "capacity", "", "connectionFactory", "Lkotlin/Function0;", "Landroidx/sqlite/SQLiteConnection;", "<init>", "(ILkotlin/jvm/functions/Function0;)V", "getCapacity", "()I", "getConnectionFactory", "()Lkotlin/jvm/functions/Function0;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "size", "isClosed", "", "connections", "", "Landroidx/room/coroutines/ConnectionWithLock;", "[Landroidx/room/coroutines/ConnectionWithLock;", "connectionPermits", "Lkotlinx/coroutines/sync/Semaphore;", "availableConnections", "Landroidx/collection/CircularArray;", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryOpenNewConnectionLocked", "", "recycle", "connection", "close", ArchiveStreamFactory.DUMP, "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Pool {
    private final CircularArray<ConnectionWithLock> availableConnections;
    private final int capacity;
    private final Function0<SQLiteConnection> connectionFactory;
    private final Semaphore connectionPermits;
    private final ConnectionWithLock[] connections;
    private boolean isClosed;
    private final ReentrantLock lock;
    private int size;

    /* JADX WARN: Multi-variable type inference failed */
    public Pool(int capacity, Function0<? extends SQLiteConnection> connectionFactory) {
        Intrinsics.checkNotNullParameter(connectionFactory, "connectionFactory");
        this.capacity = capacity;
        this.connectionFactory = connectionFactory;
        this.lock = new ReentrantLock();
        this.connections = new ConnectionWithLock[this.capacity];
        this.connectionPermits = SemaphoreKt.Semaphore$default(this.capacity, 0, 2, null);
        this.availableConnections = new CircularArray<>(this.capacity);
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final Function0<SQLiteConnection> getConnectionFactory() {
        return this.connectionFactory;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0054 A[Catch: all -> 0x007a, TryCatch #0 {all -> 0x007a, blocks: (B:16:0x0050, B:18:0x0054, B:20:0x005c, B:21:0x005f, B:24:0x006d, B:25:0x0079), top: B:15:0x0050, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006d A[Catch: all -> 0x007a, TRY_ENTER, TryCatch #0 {all -> 0x007a, blocks: (B:16:0x0050, B:18:0x0054, B:20:0x005c, B:21:0x005f, B:24:0x006d, B:25:0x0079), top: B:15:0x0050, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object acquire(kotlin.coroutines.Continuation<? super androidx.room.coroutines.ConnectionWithLock> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.room.coroutines.Pool$acquire$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.room.coroutines.Pool$acquire$1 r0 = (androidx.room.coroutines.Pool$acquire$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.room.coroutines.Pool$acquire$1 r0 = new androidx.room.coroutines.Pool$acquire$1
            r0.<init>(r8, r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$0
            androidx.room.coroutines.Pool r2 = (androidx.room.coroutines.Pool) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L47
        L34:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r8
            kotlinx.coroutines.sync.Semaphore r4 = r3.connectionPermits
            r0.L$0 = r3
            r5 = 1
            r0.label = r5
            java.lang.Object r4 = r4.acquire(r0)
            if (r4 != r2) goto L46
            return r2
        L46:
            r2 = r3
        L47:
            java.util.concurrent.locks.ReentrantLock r3 = r2.lock     // Catch: java.lang.Throwable -> L7f
            r4 = 0
            r3.lock()     // Catch: java.lang.Throwable -> L7f
            r5 = 0
            boolean r6 = r2.isClosed     // Catch: java.lang.Throwable -> L7a
            if (r6 != 0) goto L6d
            androidx.collection.CircularArray<androidx.room.coroutines.ConnectionWithLock> r6 = r2.availableConnections     // Catch: java.lang.Throwable -> L7a
            boolean r6 = r6.isEmpty()     // Catch: java.lang.Throwable -> L7a
            if (r6 == 0) goto L5f
            r2.tryOpenNewConnectionLocked()     // Catch: java.lang.Throwable -> L7a
        L5f:
            androidx.collection.CircularArray<androidx.room.coroutines.ConnectionWithLock> r6 = r2.availableConnections     // Catch: java.lang.Throwable -> L7a
            java.lang.Object r6 = r6.popFirst()     // Catch: java.lang.Throwable -> L7a
            androidx.room.coroutines.ConnectionWithLock r6 = (androidx.room.coroutines.ConnectionWithLock) r6     // Catch: java.lang.Throwable -> L7a
            r3.unlock()     // Catch: java.lang.Throwable -> L7f
            return r6
        L6d:
            java.lang.String r6 = "Connection pool is closed"
            r7 = 21
            androidx.sqlite.SQLite.throwSQLiteException(r7, r6)     // Catch: java.lang.Throwable -> L7a
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L7a
            r6.<init>()     // Catch: java.lang.Throwable -> L7a
            throw r6     // Catch: java.lang.Throwable -> L7a
        L7a:
            r5 = move-exception
            r3.unlock()     // Catch: java.lang.Throwable -> L7f
            throw r5     // Catch: java.lang.Throwable -> L7f
        L7f:
            r3 = move-exception
            kotlinx.coroutines.sync.Semaphore r4 = r2.connectionPermits
            r4.release()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.Pool.acquire(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void tryOpenNewConnectionLocked() {
        if (this.size >= this.capacity) {
            return;
        }
        ConnectionWithLock connectionWithLock = new ConnectionWithLock(this.connectionFactory.invoke(), null, 2, 0 == true ? 1 : 0);
        ConnectionWithLock[] connectionWithLockArr = this.connections;
        int i = this.size;
        this.size = i + 1;
        connectionWithLockArr[i] = connectionWithLock;
        this.availableConnections.addLast(connectionWithLock);
    }

    public final void recycle(ConnectionWithLock connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            this.availableConnections.addLast(connection);
            Unit unit = Unit.INSTANCE;
            $this$withLock$iv.unlock();
            this.connectionPermits.release();
        } catch (Throwable th) {
            $this$withLock$iv.unlock();
            throw th;
        }
    }

    public final void close() {
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            this.isClosed = true;
            for (ConnectionWithLock connectionWithLock : this.connections) {
                if (connectionWithLock != null) {
                    connectionWithLock.close();
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final void dump(StringBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            List $this$dump_u24lambda_u246_u24lambda_u244 = CollectionsKt.createListBuilder();
            int size = this.availableConnections.size();
            for (int i = 0; i < size; i++) {
                $this$dump_u24lambda_u246_u24lambda_u244.add(this.availableConnections.get(i));
            }
            List availableQueue = CollectionsKt.build($this$dump_u24lambda_u246_u24lambda_u244);
            builder.append('\t' + super.toString() + " (");
            builder.append("capacity=" + this.capacity + ", ");
            builder.append("permits=" + this.connectionPermits.getAvailablePermits() + ", ");
            builder.append("queue=(size=" + availableQueue.size() + ")[" + CollectionsKt.joinToString$default(availableQueue, null, null, null, 0, null, null, 63, null) + "], ");
            builder.append(")").append('\n');
            ConnectionWithLock[] connectionWithLockArr = this.connections;
            int index$iv = 0;
            int length = connectionWithLockArr.length;
            int i2 = 0;
            while (i2 < length) {
                ConnectionWithLock connectionWithLock = connectionWithLockArr[i2];
                int index$iv2 = index$iv + 1;
                ConnectionWithLock[] connectionWithLockArr2 = connectionWithLockArr;
                builder.append("\t\t[" + (index$iv + 1) + "] - " + (connectionWithLock != null ? connectionWithLock.toString() : null)).append('\n');
                if (connectionWithLock != null) {
                    connectionWithLock.dump(builder);
                }
                i2++;
                index$iv = index$iv2;
                connectionWithLockArr = connectionWithLockArr2;
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }
}
