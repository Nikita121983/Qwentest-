package com.glmreader.android;

import android.app.Application;
import android.content.Context;
import com.glmreader.android.ble.GlmBleManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: GlmReaderApplication.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\f\u001a\u00020\u0007J\b\u0010\r\u001a\u00020\u0007H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/glmreader/android/GlmReaderApplication;", "Landroid/app/Application;", "<init>", "()V", "bleManager", "Lcom/glmreader/android/ble/GlmBleManager;", "onCreate", "", "getOrCreateBleManager", "context", "Landroid/content/Context;", "getBleManager", "disconnectBle", "onTerminate", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class GlmReaderApplication extends Application {
    private volatile GlmBleManager bleManager;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        BuildersKt.runBlocking(Dispatchers.getIO(), new GlmReaderApplication$onCreate$1(this, null));
    }

    public final synchronized GlmBleManager getOrCreateBleManager(Context context) {
        GlmBleManager glmBleManager;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.bleManager == null) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            this.bleManager = new GlmBleManager(applicationContext);
        }
        glmBleManager = this.bleManager;
        Intrinsics.checkNotNull(glmBleManager);
        return glmBleManager;
    }

    public final GlmBleManager getBleManager() {
        return this.bleManager;
    }

    public final void disconnectBle() {
        GlmBleManager glmBleManager = this.bleManager;
        if (glmBleManager != null) {
            glmBleManager.disconnect();
        }
        this.bleManager = null;
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        disconnectBle();
    }
}
