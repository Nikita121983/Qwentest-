package com.glmreader.android.ui.viewmodel;

import com.glmreader.android.data.entity.MeasurementEntity;
import com.glmreader.android.protocol.BlePacketParser;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MeasurementViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.glmreader.android.ui.viewmodel.MeasurementViewModel$onBleMeasurement$1", f = "MeasurementViewModel.kt", i = {0}, l = {46}, m = "invokeSuspend", n = {"entity"}, s = {"L$0"})
/* loaded from: classes5.dex */
final class MeasurementViewModel$onBleMeasurement$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BlePacketParser.ParsedMeasurement $parsed;
    final /* synthetic */ String $projectId;
    Object L$0;
    int label;
    final /* synthetic */ MeasurementViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MeasurementViewModel$onBleMeasurement$1(BlePacketParser.ParsedMeasurement parsedMeasurement, String str, MeasurementViewModel measurementViewModel, Continuation<? super MeasurementViewModel$onBleMeasurement$1> continuation) {
        super(2, continuation);
        this.$parsed = parsedMeasurement;
        this.$projectId = str;
        this.this$0 = measurementViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeasurementViewModel$onBleMeasurement$1(this.$parsed, this.$projectId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeasurementViewModel$onBleMeasurement$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MeasurementEntity entity = BlePacketParser.ParsedMeasurement.toEntity$default(this.$parsed, this.$projectId, null, null, "GLM 50 C", 6, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(entity);
                this.label = 1;
                if (this.this$0.getMeasurementRepository().insert(entity, this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
