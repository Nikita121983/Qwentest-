package com.glmreader.android;

import androidx.appcompat.app.AppCompatDelegate;
import com.glmreader.android.data.AppDatabase;
import com.glmreader.android.data.entity.SettingsEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GlmReaderApplication.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.glmreader.android.GlmReaderApplication$onCreate$1", f = "GlmReaderApplication.kt", i = {0}, l = {26}, m = "invokeSuspend", n = {"db"}, s = {"L$0"})
/* loaded from: classes6.dex */
final class GlmReaderApplication$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ GlmReaderApplication this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlmReaderApplication$onCreate$1(GlmReaderApplication glmReaderApplication, Continuation<? super GlmReaderApplication$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = glmReaderApplication;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GlmReaderApplication$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GlmReaderApplication$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object setting;
        String mode;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int nightMode = 1;
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AppDatabase db = AppDatabase.INSTANCE.getDatabase(this.this$0);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(db);
                    this.label = 1;
                    setting = db.settingsDao().getSetting(SettingsEntity.KEY_THEME_MODE, this);
                    if (setting != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    setting = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SettingsEntity settingsEntity = (SettingsEntity) setting;
            if (settingsEntity == null || (mode = settingsEntity.getValue()) == null) {
                mode = "light";
            }
            if (Intrinsics.areEqual(mode, "dark")) {
                nightMode = 2;
            } else if (Intrinsics.areEqual(mode, "system")) {
                nightMode = -1;
            }
            AppCompatDelegate.setDefaultNightMode(nightMode);
        } catch (Exception e) {
            AppCompatDelegate.setDefaultNightMode(-1);
        }
        return Unit.INSTANCE;
    }
}
