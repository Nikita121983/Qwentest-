package androidx.room;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ThreadContextElement;
import kotlinx.coroutines.ThreadContextElementKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: RoomDatabase.android.kt */
@Metadata(d1 = {"\u0000T\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0086@¢\u0006\u0002\u0010\u0007\u001a6\u0010\b\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0080@¢\u0006\u0002\u0010\u0007\u001aK\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\r¢\u0006\u0002\b\u000fH\u0082@¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\b\u0015\u001a;\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017*\u00020\u00022\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u001b\"\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0007¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"withTransaction", "R", "Landroidx/room/RoomDatabase;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransactionContext", "startTransactionCoroutine", "context", "Lkotlin/coroutines/CoroutineContext;", "transactionBlock", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ExtensionFunctionType;", "startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt", "(Landroidx/room/RoomDatabase;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTransactionContext", "dispatcher", "Lkotlin/coroutines/ContinuationInterceptor;", "createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt", "invalidationTrackerFlow", "Lkotlinx/coroutines/flow/Flow;", "", "", "tables", "", "emitInitialState", "", "(Landroidx/room/RoomDatabase;[Ljava/lang/String;Z)Lkotlinx/coroutines/flow/Flow;", "room-runtime_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/room/RoomDatabaseKt")
/* loaded from: classes.dex */
public final /* synthetic */ class RoomDatabaseKt__RoomDatabase_androidKt {
    public static final <R> Object withTransaction(RoomDatabase $this$withTransaction, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        return RoomDatabaseKt.withTransactionContext($this$withTransaction, new RoomDatabaseKt__RoomDatabase_androidKt$withTransaction$2($this$withTransaction, function1, null), continuation);
    }

    public static final <R> Object withTransactionContext(RoomDatabase $this$withTransactionContext, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        Function2 transactionBlock = new RoomDatabaseKt__RoomDatabase_androidKt$withTransactionContext$transactionBlock$1(function1, null);
        TransactionElement transactionElement = (TransactionElement) continuation.get$context().get(TransactionElement.INSTANCE);
        ContinuationInterceptor transactionDispatcher = transactionElement != null ? transactionElement.getTransactionDispatcher() : null;
        if (transactionDispatcher != null) {
            return BuildersKt.withContext(transactionDispatcher, transactionBlock, continuation);
        }
        return startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt($this$withTransactionContext, continuation.get$context(), transactionBlock, continuation);
    }

    public static final <R> Object startTransactionCoroutine$RoomDatabaseKt__RoomDatabase_androidKt(final RoomDatabase $this$startTransactionCoroutine, final CoroutineContext context, final Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        final CancellableContinuationImpl continuation2 = cancellable$iv;
        try {
            $this$startTransactionCoroutine.getTransactionExecutor().execute(new Runnable() { // from class: androidx.room.RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        BuildersKt.runBlocking(CoroutineContext.this.minusKey(ContinuationInterceptor.INSTANCE), new AnonymousClass1($this$startTransactionCoroutine, continuation2, function2, null));
                    } catch (Throwable ex) {
                        continuation2.cancel(ex);
                    }
                }

                /* compiled from: RoomDatabase.android.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1$1", f = "RoomDatabase.android.kt", i = {}, l = {2048}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.room.RoomDatabaseKt__RoomDatabase_androidKt$startTransactionCoroutine$2$1$1, reason: invalid class name */
                /* loaded from: classes.dex */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CancellableContinuation<R> $continuation;
                    final /* synthetic */ RoomDatabase $this_startTransactionCoroutine;
                    final /* synthetic */ Function2<CoroutineScope, Continuation<? super R>, Object> $transactionBlock;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass1(RoomDatabase roomDatabase, CancellableContinuation<? super R> cancellableContinuation, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$this_startTransactionCoroutine = roomDatabase;
                        this.$continuation = cancellableContinuation;
                        this.$transactionBlock = function2;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_startTransactionCoroutine, this.$continuation, this.$transactionBlock, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        CoroutineContext transactionContext;
                        Continuation continuation;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                CoroutineScope $this$runBlocking = (CoroutineScope) this.L$0;
                                CoroutineContext.Element element = $this$runBlocking.getCoroutineContext().get(ContinuationInterceptor.INSTANCE);
                                Intrinsics.checkNotNull(element);
                                ContinuationInterceptor dispatcher = (ContinuationInterceptor) element;
                                transactionContext = RoomDatabaseKt__RoomDatabase_androidKt.createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt(this.$this_startTransactionCoroutine, dispatcher);
                                continuation = this.$continuation;
                                Result.Companion companion = Result.INSTANCE;
                                this.L$0 = continuation;
                                this.label = 1;
                                Object withContext = BuildersKt.withContext(transactionContext, this.$transactionBlock, this);
                                if (withContext != coroutine_suspended) {
                                    $result = withContext;
                                    break;
                                } else {
                                    return coroutine_suspended;
                                }
                            case 1:
                                Continuation continuation2 = (Continuation) this.L$0;
                                ResultKt.throwOnFailure($result);
                                continuation = continuation2;
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        continuation.resumeWith(Result.m396constructorimpl($result));
                        return Unit.INSTANCE;
                    }
                }
            });
        } catch (RejectedExecutionException ex) {
            continuation2.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", ex));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final CoroutineContext createTransactionContext$RoomDatabaseKt__RoomDatabase_androidKt(RoomDatabase $this$createTransactionContext, ContinuationInterceptor dispatcher) {
        TransactionElement transactionElement = new TransactionElement(dispatcher);
        ThreadContextElement threadLocalElement = ThreadContextElementKt.asContextElement($this$createTransactionContext.getSuspendingTransactionId(), Integer.valueOf(System.identityHashCode(transactionElement)));
        return dispatcher.plus(transactionElement).plus(threadLocalElement);
    }

    public static /* synthetic */ Flow invalidationTrackerFlow$default(RoomDatabase roomDatabase, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return RoomDatabaseKt.invalidationTrackerFlow(roomDatabase, strArr, z);
    }

    @Deprecated(message = "Replaced by equivalent API in InvalidationTracker.", replaceWith = @ReplaceWith(expression = "this.invalidationTracker.createFlow(*tables)", imports = {}))
    public static final Flow<Set<String>> invalidationTrackerFlow(RoomDatabase $this$invalidationTrackerFlow, String[] tables, boolean emitInitialState) {
        Intrinsics.checkNotNullParameter($this$invalidationTrackerFlow, "<this>");
        Intrinsics.checkNotNullParameter(tables, "tables");
        return $this$invalidationTrackerFlow.getInvalidationTracker().createFlow((String[]) Arrays.copyOf(tables, tables.length), emitInitialState);
    }
}
