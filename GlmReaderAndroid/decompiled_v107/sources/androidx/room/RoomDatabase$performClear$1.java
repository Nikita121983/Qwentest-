package androidx.room;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RoomDatabase.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1", f = "RoomDatabase.android.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_Y}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class RoomDatabase$performClear$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $hasForeignKeys;
    final /* synthetic */ String[] $tableNames;
    int label;
    final /* synthetic */ RoomDatabase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomDatabase$performClear$1(RoomDatabase roomDatabase, boolean z, String[] strArr, Continuation<? super RoomDatabase$performClear$1> continuation) {
        super(2, continuation);
        this.this$0 = roomDatabase;
        this.$hasForeignKeys = z;
        this.$tableNames = strArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RoomDatabase$performClear$1(this.this$0, this.$hasForeignKeys, this.$tableNames, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RoomDatabase$performClear$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "connection", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 2, 3, 4}, l = {508, 509, 511, 517, 518, 519}, m = "invokeSuspend", n = {"connection", "connection", "connection", "connection", "connection"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
    /* renamed from: androidx.room.RoomDatabase$performClear$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<Transactor, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hasForeignKeys;
        final /* synthetic */ String[] $tableNames;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ RoomDatabase this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RoomDatabase roomDatabase, boolean z, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = roomDatabase;
            this.$hasForeignKeys = z;
            this.$tableNames = strArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$hasForeignKeys, this.$tableNames, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Transactor transactor, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(transactor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:13:0x00e2 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00ac A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00ad  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x009d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0080  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instructions count: 258
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase$performClear$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: RoomDatabase.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/room/TransactionScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 1}, l = {InputDeviceCompat.SOURCE_DPAD, 515}, m = "invokeSuspend", n = {"$this$withTransaction", "$this$withTransaction", "$this$forEach$iv"}, s = {"L$0", "L$0", "L$1"})
        /* renamed from: androidx.room.RoomDatabase$performClear$1$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C00051 extends SuspendLambda implements Function2<TransactionScope<Unit>, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $hasForeignKeys;
            final /* synthetic */ String[] $tableNames;
            int I$0;
            int I$1;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00051(boolean z, String[] strArr, Continuation<? super C00051> continuation) {
                super(2, continuation);
                this.$hasForeignKeys = z;
                this.$tableNames = strArr;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00051 c00051 = new C00051(this.$hasForeignKeys, this.$tableNames, continuation);
                c00051.L$0 = obj;
                return c00051;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(TransactionScope<Unit> transactionScope, Continuation<? super Unit> continuation) {
                return ((C00051) create(transactionScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
            /* JADX WARN: Removed duplicated region for block: B:13:0x009f  */
            /* JADX WARN: Removed duplicated region for block: B:9:0x005e  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x008f -> B:7:0x0095). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                /*
                    r13 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r13.label
                    r2 = 1
                    switch(r1) {
                        case 0: goto L32;
                        case 1: goto L2a;
                        case 2: goto L12;
                        default: goto La;
                    }
                La:
                    java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r14.<init>(r0)
                    throw r14
                L12:
                    r1 = 0
                    r3 = 0
                    int r4 = r13.I$1
                    int r5 = r13.I$0
                    java.lang.Object r6 = r13.L$1
                    java.lang.String[] r6 = (java.lang.String[]) r6
                    java.lang.Object r7 = r13.L$0
                    androidx.room.TransactionScope r7 = (androidx.room.TransactionScope) r7
                    kotlin.ResultKt.throwOnFailure(r14)
                    r8 = r7
                    r7 = r6
                    r6 = r5
                    r5 = r4
                    r4 = r13
                    goto L95
                L2a:
                    java.lang.Object r1 = r13.L$0
                    androidx.room.TransactionScope r1 = (androidx.room.TransactionScope) r1
                    kotlin.ResultKt.throwOnFailure(r14)
                    goto L50
                L32:
                    kotlin.ResultKt.throwOnFailure(r14)
                    java.lang.Object r1 = r13.L$0
                    androidx.room.TransactionScope r1 = (androidx.room.TransactionScope) r1
                    boolean r3 = r13.$hasForeignKeys
                    if (r3 == 0) goto L51
                    r3 = r1
                    androidx.room.PooledConnection r3 = (androidx.room.PooledConnection) r3
                    r4 = r13
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r13.L$0 = r1
                    r13.label = r2
                    java.lang.String r5 = "PRAGMA defer_foreign_keys = TRUE"
                    java.lang.Object r3 = androidx.room.TransactorKt.execSQL(r3, r5, r4)
                    if (r3 != r0) goto L50
                    return r0
                L50:
                L51:
                    java.lang.String[] r3 = r13.$tableNames
                    r4 = 0
                    int r5 = r3.length
                    r6 = 0
                    r7 = r1
                    r1 = r4
                    r4 = r5
                    r5 = r6
                    r6 = r3
                    r3 = r13
                L5c:
                    if (r5 >= r4) goto L9f
                    r8 = r6[r5]
                    r9 = 0
                    r10 = r7
                    androidx.room.PooledConnection r10 = (androidx.room.PooledConnection) r10
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder
                    r11.<init>()
                    java.lang.String r12 = "DELETE FROM `"
                    java.lang.StringBuilder r11 = r11.append(r12)
                    java.lang.StringBuilder r11 = r11.append(r8)
                    r12 = 96
                    java.lang.StringBuilder r11 = r11.append(r12)
                    java.lang.String r11 = r11.toString()
                    r3.L$0 = r7
                    r3.L$1 = r6
                    r3.I$0 = r5
                    r3.I$1 = r4
                    r12 = 2
                    r3.label = r12
                    java.lang.Object r8 = androidx.room.TransactorKt.execSQL(r10, r11, r3)
                    if (r8 != r0) goto L8f
                    return r0
                L8f:
                    r8 = r7
                    r7 = r6
                    r6 = r5
                    r5 = r4
                    r4 = r3
                    r3 = r9
                L95:
                    int r3 = r6 + 1
                    r6 = r5
                    r5 = r3
                    r3 = r4
                    r4 = r6
                    r6 = r7
                    r7 = r8
                    goto L5c
                L9f:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase$performClear$1.AnonymousClass1.C00051.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        RoomConnectionManager roomConnectionManager;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                roomConnectionManager = this.this$0.connectionManager;
                if (roomConnectionManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
                    roomConnectionManager = null;
                }
                this.label = 1;
                if (roomConnectionManager.useConnection(false, new AnonymousClass1(this.this$0, this.$hasForeignKeys, this.$tableNames, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
