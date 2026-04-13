package androidx.room.concurrent;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CloseBarrier.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0005"}, d2 = {"ifNotClosed", "", "Landroidx/room/concurrent/CloseBarrier;", "action", "Lkotlin/Function0;", "room-runtime_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CloseBarrierKt {
    public static final void ifNotClosed(CloseBarrier $this$ifNotClosed, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter($this$ifNotClosed, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        if ($this$ifNotClosed.block$room_runtime_release()) {
            try {
                action.invoke();
            } finally {
                $this$ifNotClosed.unblock$room_runtime_release();
            }
        }
    }
}
