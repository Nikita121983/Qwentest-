package com.glmreader.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.glmreader.android.R;
import com.glmreader.android.ui.viewmodel.SettingsViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: SettingsActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/glmreader/android/ui/SettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "viewModel", "Lcom/glmreader/android/ui/viewmodel/SettingsViewModel;", "getViewModel", "()Lcom/glmreader/android/ui/viewmodel/SettingsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "tvThresholdValue", "Landroid/widget/TextView;", "seekBarThreshold", "Landroid/widget/SeekBar;", "switchAutoSwitch", "Lcom/google/android/material/switchmaterial/SwitchMaterial;", "radioGroupTheme", "Landroid/widget/RadioGroup;", "isThemeChanging", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupThemeListener", "onSupportNavigateUp", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsActivity extends AppCompatActivity {
    private boolean isThemeChanging;
    private RadioGroup radioGroupTheme;
    private SeekBar seekBarThreshold;
    private SwitchMaterial switchAutoSwitch;
    private TextView tvThresholdValue;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public SettingsActivity() {
        final SettingsActivity settingsActivity = this;
        final Function0 function0 = null;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(SettingsViewModel.class), new Function0<ViewModelStore>() { // from class: com.glmreader.android.ui.SettingsActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.glmreader.android.ui.SettingsActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.glmreader.android.ui.SettingsActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? settingsActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SettingsViewModel getViewModel() {
        return (SettingsViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Toolbar toolbar = (Toolbar) findViewById;
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeButtonEnabled(true);
        }
        View findViewById2 = findViewById(R.id.tvThresholdValue);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tvThresholdValue = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.seekBarThreshold);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.seekBarThreshold = (SeekBar) findViewById3;
        View findViewById4 = findViewById(R.id.switchAutoSwitch);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.switchAutoSwitch = (SwitchMaterial) findViewById4;
        View findViewById5 = findViewById(R.id.radioGroupTheme);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.radioGroupTheme = (RadioGroup) findViewById5;
        SwitchMaterial switchMaterial = null;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SettingsActivity$onCreate$3(this, null), 3, null);
        SeekBar seekBar = this.seekBarThreshold;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seekBarThreshold");
            seekBar = null;
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.glmreader.android.ui.SettingsActivity$onCreate$4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                TextView textView;
                SettingsViewModel viewModel;
                if (fromUser) {
                    textView = SettingsActivity.this.tvThresholdValue;
                    if (textView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tvThresholdValue");
                        textView = null;
                    }
                    textView.setText(progress + "°");
                    viewModel = SettingsActivity.this.getViewModel();
                    viewModel.setInclinoThreshold(progress);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        SwitchMaterial switchMaterial2 = this.switchAutoSwitch;
        if (switchMaterial2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchAutoSwitch");
        } else {
            switchMaterial = switchMaterial2;
        }
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.glmreader.android.ui.SettingsActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$0(SettingsActivity.this, compoundButton, z);
            }
        });
        setupThemeListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SettingsActivity this$0, CompoundButton compoundButton, boolean isChecked) {
        this$0.getViewModel().setInclinoAutoSwitch(isChecked);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupThemeListener() {
        RadioGroup radioGroup = this.radioGroupTheme;
        if (radioGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("radioGroupTheme");
            radioGroup = null;
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.glmreader.android.ui.SettingsActivity$$ExternalSyntheticLambda0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                SettingsActivity.setupThemeListener$lambda$1(SettingsActivity.this, radioGroup2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupThemeListener$lambda$1(SettingsActivity this$0, RadioGroup radioGroup, int checkedId) {
        if (this$0.isThemeChanging) {
            return;
        }
        int nightMode = 1;
        this$0.isThemeChanging = true;
        String mode = "light";
        if (checkedId != R.id.radioLight) {
            if (checkedId == R.id.radioDark) {
                mode = "dark";
            } else if (checkedId == R.id.radioSystem) {
                mode = "system";
            }
        }
        this$0.getViewModel().setThemeMode(mode);
        if (Intrinsics.areEqual(mode, "dark")) {
            nightMode = 2;
        } else if (Intrinsics.areEqual(mode, "system")) {
            nightMode = -1;
        }
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }
}
