package com.glmreader.android.ui.viewmodel;

import com.glmreader.android.data.dao.SettingsDao;
import com.glmreader.android.data.entity.SettingsEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.glmreader.android.ui.viewmodel.SettingsViewModel$setInclinoThreshold$1", f = "SettingsViewModel.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class SettingsViewModel$setInclinoThreshold$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $degrees;
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$setInclinoThreshold$1(SettingsViewModel settingsViewModel, double d, Continuation<? super SettingsViewModel$setInclinoThreshold$1> continuation) {
        super(2, continuation);
        this.this$0 = settingsViewModel;
        this.$degrees = d;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SettingsViewModel$setInclinoThreshold$1(this.this$0, this.$degrees, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SettingsViewModel$setInclinoThreshold$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SettingsDao settingsDao;
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                settingsDao = this.this$0.settingsDao;
                this.label = 1;
                if (settingsDao.upsert(new SettingsEntity(SettingsEntity.KEY_INCLINO_THRESHOLD, String.valueOf(this.$degrees), 0L, 4, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableStateFlow = this.this$0._inclinoThreshold;
        mutableStateFlow.setValue(Boxing.boxDouble(this.$degrees));
        return Unit.INSTANCE;
    }
}
