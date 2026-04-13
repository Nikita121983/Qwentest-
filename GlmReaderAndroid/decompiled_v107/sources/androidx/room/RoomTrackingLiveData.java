package androidx.room;

import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: RoomTrackingLiveData.android.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u001a\u001a\u00020\u001bH\u0082@¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001bH\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00018\u0000H¦@¢\u0006\u0002\u0010\u001cJ\b\u0010\u001f\u001a\u00020\u001bH\u0014J\b\u0010 \u001a\u00020\u001bH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0002!\"¨\u0006#"}, d2 = {"Landroidx/room/RoomTrackingLiveData;", "T", "Landroidx/lifecycle/LiveData;", "database", "Landroidx/room/RoomDatabase;", "container", "Landroidx/room/InvalidationLiveDataContainer;", "inTransaction", "", "tableNames", "", "", "<init>", "(Landroidx/room/RoomDatabase;Landroidx/room/InvalidationLiveDataContainer;Z[Ljava/lang/String;)V", "getDatabase", "()Landroidx/room/RoomDatabase;", "getInTransaction", "()Z", "observer", "Landroidx/room/InvalidationTracker$Observer;", "invalid", "Ljava/util/concurrent/atomic/AtomicBoolean;", "computing", "registeredObserver", "launchContext", "Lkotlin/coroutines/CoroutineContext;", "refresh", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidated", "compute", "onActive", "onInactive", "Landroidx/room/RoomCallableTrackingLiveData;", "Landroidx/room/RoomLambdaTrackingLiveData;", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class RoomTrackingLiveData<T> extends LiveData<T> {
    private final AtomicBoolean computing;
    private final InvalidationLiveDataContainer container;
    private final RoomDatabase database;
    private final boolean inTransaction;
    private final AtomicBoolean invalid;
    private final CoroutineContext launchContext;
    private final InvalidationTracker.Observer observer;
    private final AtomicBoolean registeredObserver;

    public /* synthetic */ RoomTrackingLiveData(RoomDatabase roomDatabase, InvalidationLiveDataContainer invalidationLiveDataContainer, boolean z, String[] strArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(roomDatabase, invalidationLiveDataContainer, z, strArr);
    }

    public abstract Object compute(Continuation<? super T> continuation);

    /* JADX INFO: Access modifiers changed from: protected */
    public final RoomDatabase getDatabase() {
        return this.database;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getInTransaction() {
        return this.inTransaction;
    }

    private RoomTrackingLiveData(RoomDatabase database, InvalidationLiveDataContainer container, boolean inTransaction, String[] tableNames) {
        EmptyCoroutineContext emptyCoroutineContext;
        this.database = database;
        this.container = container;
        this.inTransaction = inTransaction;
        this.observer = new RoomTrackingLiveData$observer$1(tableNames, this);
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.registeredObserver = new AtomicBoolean(false);
        if (this.database.inCompatibilityMode$room_runtime_release()) {
            if (this.inTransaction) {
                emptyCoroutineContext = this.database.getTransactionContext$room_runtime_release();
            } else {
                emptyCoroutineContext = this.database.getQueryContext();
            }
        } else {
            emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        this.launchContext = emptyCoroutineContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007e -> B:15:0x0084). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0060 -> B:27:0x00ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0063 -> B:16:0x0067). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refresh(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.room.RoomTrackingLiveData$refresh$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.room.RoomTrackingLiveData$refresh$1 r0 = (androidx.room.RoomTrackingLiveData$refresh$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.room.RoomTrackingLiveData$refresh$1 r0 = new androidx.room.RoomTrackingLiveData$refresh$1
            r0.<init>(r10, r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            r5 = 0
            switch(r3) {
                case 0: goto L41;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2e:
            int r3 = r0.I$0
            java.lang.Object r6 = r0.L$0
            androidx.room.RoomTrackingLiveData r6 = (androidx.room.RoomTrackingLiveData) r6
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r7 = r6
            r6 = r3
            r3 = r2
            r2 = r1
            goto L84
        L3c:
            r2 = move-exception
            goto La5
        L3f:
            r2 = move-exception
            goto L8c
        L41:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r10
            java.util.concurrent.atomic.AtomicBoolean r6 = r3.registeredObserver
            boolean r6 = r6.compareAndSet(r5, r4)
            if (r6 == 0) goto L58
            androidx.room.RoomDatabase r6 = r3.database
            androidx.room.InvalidationTracker r6 = r6.getInvalidationTracker()
            androidx.room.InvalidationTracker$Observer r7 = r3.observer
            r6.addWeakObserver(r7)
        L58:
        L59:
            r6 = 0
            java.util.concurrent.atomic.AtomicBoolean r7 = r3.computing
            boolean r7 = r7.compareAndSet(r5, r4)
            if (r7 == 0) goto Lab
        L63:
            r7 = 0
            r9 = r6
            r6 = r3
            r3 = r9
        L67:
            java.util.concurrent.atomic.AtomicBoolean r8 = r6.invalid     // Catch: java.lang.Throwable -> L3c
            boolean r8 = r8.compareAndSet(r4, r5)     // Catch: java.lang.Throwable -> L3c
            if (r8 == 0) goto L97
            r3 = 1
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r0.I$0 = r3     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r0.label = r4     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            java.lang.Object r7 = r6.compute(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            if (r7 != r2) goto L7e
            return r2
        L7e:
            r9 = r2
            r2 = r1
            r1 = r7
            r7 = r6
            r6 = r3
            r3 = r9
        L84:
            r9 = r7
            r7 = r1
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r9
            goto L67
        L8c:
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L3c
            java.lang.String r4 = "Exception while computing database live data."
            r7 = r2
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch: java.lang.Throwable -> L3c
            r3.<init>(r4, r7)     // Catch: java.lang.Throwable -> L3c
            throw r3     // Catch: java.lang.Throwable -> L3c
        L97:
            if (r3 == 0) goto L9c
            r6.postValue(r7)     // Catch: java.lang.Throwable -> L3c
        L9c:
            java.util.concurrent.atomic.AtomicBoolean r7 = r6.computing
            r7.set(r5)
            r9 = r6
            r6 = r3
            r3 = r9
            goto Lab
        La5:
            java.util.concurrent.atomic.AtomicBoolean r3 = r6.computing
            r3.set(r5)
            throw r2
        Lab:
            if (r6 == 0) goto Lb5
            java.util.concurrent.atomic.AtomicBoolean r7 = r3.invalid
            boolean r7 = r7.get()
            if (r7 != 0) goto L59
        Lb5:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomTrackingLiveData.refresh(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidated() {
        boolean isActive = hasActiveObservers();
        if (this.invalid.compareAndSet(false, true) && isActive) {
            BuildersKt__Builders_commonKt.launch$default(this.database.getCoroutineScope(), this.launchContext, null, new RoomTrackingLiveData$invalidated$1(this, null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        super.onActive();
        this.container.onActive(this);
        BuildersKt__Builders_commonKt.launch$default(this.database.getCoroutineScope(), this.launchContext, null, new RoomTrackingLiveData$onActive$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        super.onInactive();
        this.container.onInactive(this);
    }
}
