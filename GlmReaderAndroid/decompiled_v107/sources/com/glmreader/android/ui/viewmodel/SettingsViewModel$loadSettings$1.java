package com.glmreader.android.ui.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.glmreader.android.ui.viewmodel.SettingsViewModel$loadSettings$1", f = "SettingsViewModel.kt", i = {1, 2, 2}, l = {36, 39, 42}, m = "invokeSuspend", n = {"thresholdEntity", "thresholdEntity", "autoSwitchEntity"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class SettingsViewModel$loadSettings$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$loadSettings$1(SettingsViewModel settingsViewModel, Continuation<? super SettingsViewModel$loadSettings$1> continuation) {
        super(2, continuation);
        this.this$0 = settingsViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SettingsViewModel$loadSettings$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SettingsViewModel$loadSettings$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0083 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L2d;
                case 1: goto L28;
                case 2: goto L1f;
                case 3: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L11:
            java.lang.Object r0 = r6.L$1
            com.glmreader.android.data.entity.SettingsEntity r0 = (com.glmreader.android.data.entity.SettingsEntity) r0
            java.lang.Object r1 = r6.L$0
            com.glmreader.android.data.entity.SettingsEntity r1 = (com.glmreader.android.data.entity.SettingsEntity) r1
            kotlin.ResultKt.throwOnFailure(r7)
            r3 = r7
            goto Lc3
        L1f:
            java.lang.Object r1 = r6.L$0
            com.glmreader.android.data.entity.SettingsEntity r1 = (com.glmreader.android.data.entity.SettingsEntity) r1
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r7
            goto L84
        L28:
            kotlin.ResultKt.throwOnFailure(r7)
            r1 = r7
            goto L45
        L2d:
            kotlin.ResultKt.throwOnFailure(r7)
            com.glmreader.android.ui.viewmodel.SettingsViewModel r1 = r6.this$0
            com.glmreader.android.data.dao.SettingsDao r1 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$getSettingsDao$p(r1)
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 1
            r6.label = r3
            java.lang.String r3 = "inclino_threshold"
            java.lang.Object r1 = r1.getSetting(r3, r2)
            if (r1 != r0) goto L45
            return r0
        L45:
            com.glmreader.android.data.entity.SettingsEntity r1 = (com.glmreader.android.data.entity.SettingsEntity) r1
            com.glmreader.android.ui.viewmodel.SettingsViewModel r2 = r6.this$0
            kotlinx.coroutines.flow.MutableStateFlow r2 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$get_inclinoThreshold$p(r2)
            if (r1 == 0) goto L60
            java.lang.String r3 = r1.getValue()
            if (r3 == 0) goto L60
            java.lang.Double r3 = kotlin.text.StringsKt.toDoubleOrNull(r3)
            if (r3 == 0) goto L60
            double r3 = r3.doubleValue()
            goto L62
        L60:
            r3 = 4617315517961601024(0x4014000000000000, double:5.0)
        L62:
            java.lang.Double r3 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r3)
            r2.setValue(r3)
            com.glmreader.android.ui.viewmodel.SettingsViewModel r2 = r6.this$0
            com.glmreader.android.data.dao.SettingsDao r2 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$getSettingsDao$p(r2)
            r3 = r6
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r6.L$0 = r4
            r4 = 2
            r6.label = r4
            java.lang.String r4 = "inclino_auto_switch"
            java.lang.Object r2 = r2.getSetting(r4, r3)
            if (r2 != r0) goto L84
            return r0
        L84:
            com.glmreader.android.data.entity.SettingsEntity r2 = (com.glmreader.android.data.entity.SettingsEntity) r2
            com.glmreader.android.ui.viewmodel.SettingsViewModel r3 = r6.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$get_inclinoAutoSwitch$p(r3)
            if (r2 == 0) goto L93
            java.lang.String r4 = r2.getValue()
            goto L94
        L93:
            r4 = 0
        L94:
            java.lang.String r5 = "1"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            r3.setValue(r4)
            com.glmreader.android.ui.viewmodel.SettingsViewModel r3 = r6.this$0
            com.glmreader.android.data.dao.SettingsDao r3 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$getSettingsDao$p(r3)
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r6.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r6.L$1 = r5
            r5 = 3
            r6.label = r5
            java.lang.String r5 = "theme_mode"
            java.lang.Object r3 = r3.getSetting(r5, r4)
            if (r3 != r0) goto Lc2
            return r0
        Lc2:
            r0 = r2
        Lc3:
            r2 = r3
            com.glmreader.android.data.entity.SettingsEntity r2 = (com.glmreader.android.data.entity.SettingsEntity) r2
            com.glmreader.android.ui.viewmodel.SettingsViewModel r3 = r6.this$0
            kotlinx.coroutines.flow.MutableStateFlow r3 = com.glmreader.android.ui.viewmodel.SettingsViewModel.access$get_themeMode$p(r3)
            if (r2 == 0) goto Ld4
            java.lang.String r4 = r2.getValue()
            if (r4 != 0) goto Ld6
        Ld4:
            java.lang.String r4 = "light"
        Ld6:
            r3.setValue(r4)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glmreader.android.ui.viewmodel.SettingsViewModel$loadSettings$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
