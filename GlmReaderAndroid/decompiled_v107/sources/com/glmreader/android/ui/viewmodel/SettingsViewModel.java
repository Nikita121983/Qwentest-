package com.glmreader.android.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import com.glmreader.android.data.AppDatabase;
import com.glmreader.android.data.dao.SettingsDao;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0012J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006!"}, d2 = {"Lcom/glmreader/android/ui/viewmodel/SettingsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "database", "Lcom/glmreader/android/data/AppDatabase;", "settingsDao", "Lcom/glmreader/android/data/dao/SettingsDao;", "_inclinoThreshold", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "inclinoThreshold", "Lkotlinx/coroutines/flow/StateFlow;", "getInclinoThreshold", "()Lkotlinx/coroutines/flow/StateFlow;", "_inclinoAutoSwitch", "", "inclinoAutoSwitch", "getInclinoAutoSwitch", "_themeMode", "", "themeMode", "getThemeMode", "loadSettings", "", "setInclinoThreshold", "degrees", "setInclinoAutoSwitch", "enabled", "setThemeMode", "mode", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SettingsViewModel extends AndroidViewModel {
    private final MutableStateFlow<Boolean> _inclinoAutoSwitch;
    private final MutableStateFlow<Double> _inclinoThreshold;
    private final MutableStateFlow<String> _themeMode;
    private final AppDatabase database;
    private final StateFlow<Boolean> inclinoAutoSwitch;
    private final StateFlow<Double> inclinoThreshold;
    private final SettingsDao settingsDao;
    private final StateFlow<String> themeMode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.database = AppDatabase.INSTANCE.getDatabase(application);
        this.settingsDao = this.database.settingsDao();
        this._inclinoThreshold = StateFlowKt.MutableStateFlow(Double.valueOf(5.0d));
        this.inclinoThreshold = FlowKt.asStateFlow(this._inclinoThreshold);
        this._inclinoAutoSwitch = StateFlowKt.MutableStateFlow(true);
        this.inclinoAutoSwitch = FlowKt.asStateFlow(this._inclinoAutoSwitch);
        this._themeMode = StateFlowKt.MutableStateFlow("light");
        this.themeMode = FlowKt.asStateFlow(this._themeMode);
        loadSettings();
    }

    public final StateFlow<Double> getInclinoThreshold() {
        return this.inclinoThreshold;
    }

    public final StateFlow<Boolean> getInclinoAutoSwitch() {
        return this.inclinoAutoSwitch;
    }

    public final StateFlow<String> getThemeMode() {
        return this.themeMode;
    }

    private final void loadSettings() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$loadSettings$1(this, null), 3, null);
    }

    public final void setInclinoThreshold(double degrees) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setInclinoThreshold$1(this, degrees, null), 3, null);
    }

    public final void setInclinoAutoSwitch(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setInclinoAutoSwitch$1(this, enabled, null), 3, null);
    }

    public final void setThemeMode(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new SettingsViewModel$setThemeMode$1(this, mode, null), 3, null);
    }
}
