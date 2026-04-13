package androidx.room;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InvalidationTracker.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001fR\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/room/ObservedTableStates;", "", "size", "", "<init>", "(I)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "tableObserversCount", "", "tableObservedState", "", "needsSync", "", "getTablesToSync", "", "Landroidx/room/ObservedTableStates$ObserveOp;", "getTablesToSync$room_runtime_release", "()[Landroidx/room/ObservedTableStates$ObserveOp;", "onObserverAdded", "tableIds", "", "onObserverAdded$room_runtime_release", "onObserverRemoved", "onObserverRemoved$room_runtime_release", "resetTriggerState", "", "resetTriggerState$room_runtime_release", "forceNeedSync", "forceNeedSync$room_runtime_release", "ObserveOp", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ObservedTableStates {
    private final ReentrantLock lock = new ReentrantLock();
    private boolean needsSync;
    private final boolean[] tableObservedState;
    private final long[] tableObserversCount;

    public ObservedTableStates(int size) {
        this.tableObserversCount = new long[size];
        this.tableObservedState = new boolean[size];
    }

    public final ObserveOp[] getTablesToSync$room_runtime_release() {
        ObserveOp observeOp;
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            if (!this.needsSync) {
                return null;
            }
            this.needsSync = false;
            boolean addOrRemove = false;
            int length = this.tableObserversCount.length;
            ObserveOp[] ops = new ObserveOp[length];
            for (int i = 0; i < length; i++) {
                boolean newState = this.tableObserversCount[i] > 0;
                if (newState != this.tableObservedState[i]) {
                    addOrRemove = true;
                    this.tableObservedState[i] = newState;
                    observeOp = newState ? ObserveOp.ADD : ObserveOp.REMOVE;
                } else {
                    observeOp = ObserveOp.NO_OP;
                }
                ops[i] = observeOp;
            }
            return addOrRemove ? ops : null;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final boolean onObserverAdded$room_runtime_release(int[] tableIds) {
        Intrinsics.checkNotNullParameter(tableIds, "tableIds");
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        boolean shouldSync = false;
        try {
            for (int element$iv : tableIds) {
                long previousCount = this.tableObserversCount[element$iv];
                this.tableObserversCount[element$iv] = previousCount + 1;
                if (previousCount == 0) {
                    this.needsSync = true;
                    shouldSync = true;
                }
            }
            return shouldSync;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final boolean onObserverRemoved$room_runtime_release(int[] tableIds) {
        Intrinsics.checkNotNullParameter(tableIds, "tableIds");
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        boolean shouldSync = false;
        try {
            for (int element$iv : tableIds) {
                long previousCount = this.tableObserversCount[element$iv];
                this.tableObserversCount[element$iv] = previousCount - 1;
                if (previousCount == 1) {
                    this.needsSync = true;
                    shouldSync = true;
                }
            }
            return shouldSync;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final void resetTriggerState$room_runtime_release() {
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            ArraysKt.fill$default(this.tableObservedState, false, 0, 0, 6, (Object) null);
            this.needsSync = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    public final void forceNeedSync$room_runtime_release() {
        ReentrantLock $this$withLock$iv = this.lock;
        $this$withLock$iv.lock();
        try {
            this.needsSync = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$withLock$iv.unlock();
        }
    }

    /* compiled from: InvalidationTracker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/room/ObservedTableStates$ObserveOp;", "", "<init>", "(Ljava/lang/String;I)V", "NO_OP", "ADD", "REMOVE", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes.dex */
    public enum ObserveOp {
        NO_OP,
        ADD,
        REMOVE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ObserveOp> getEntries() {
            return $ENTRIES;
        }
    }
}
