package com.glmreader.android.ui;

import android.widget.Toast;
import com.glmreader.android.data.entity.MeasurementEntity;
import com.glmreader.android.export.XlsxExporter;
import java.util.List;
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
/* compiled from: MeasurementListActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.glmreader.android.ui.MeasurementListActivity$exportToXlsx$1", f = "MeasurementListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MeasurementListActivity$exportToXlsx$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MeasurementEntity> $measurements;
    int label;
    final /* synthetic */ MeasurementListActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MeasurementListActivity$exportToXlsx$1(MeasurementListActivity measurementListActivity, List<MeasurementEntity> list, Continuation<? super MeasurementListActivity$exportToXlsx$1> continuation) {
        super(2, continuation);
        this.this$0 = measurementListActivity;
        this.$measurements = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeasurementListActivity$exportToXlsx$1(this.this$0, this.$measurements, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeasurementListActivity$exportToXlsx$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        XlsxExporter xlsxExporter;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    xlsxExporter = this.this$0.xlsxExporter;
                    if (xlsxExporter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("xlsxExporter");
                        xlsxExporter = null;
                    }
                    final String filePath = xlsxExporter.export(this.$measurements);
                    MeasurementListActivity measurementListActivity = this.this$0;
                    final MeasurementListActivity measurementListActivity2 = this.this$0;
                    measurementListActivity.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$exportToXlsx$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            MeasurementListActivity$exportToXlsx$1.invokeSuspend$lambda$0(MeasurementListActivity.this, filePath);
                        }
                    });
                } catch (Exception e) {
                    MeasurementListActivity measurementListActivity3 = this.this$0;
                    final MeasurementListActivity measurementListActivity4 = this.this$0;
                    measurementListActivity3.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$exportToXlsx$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MeasurementListActivity$exportToXlsx$1.invokeSuspend$lambda$1(MeasurementListActivity.this, e);
                        }
                    });
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(MeasurementListActivity this$0, String $filePath) {
        Toast.makeText(this$0, $filePath != null ? "Saved: " + $filePath : "Error exporting", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(MeasurementListActivity this$0, Exception $e) {
        Toast.makeText(this$0, "Export error: " + $e.getMessage(), 1).show();
    }
}
