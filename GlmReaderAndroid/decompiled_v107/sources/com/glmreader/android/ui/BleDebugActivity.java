package com.glmreader.android.ui;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.glmreader.android.GlmReaderApplication;
import com.glmreader.android.R;
import com.glmreader.android.ble.GlmBleManager;
import com.glmreader.android.protocol.BlePacketParser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BleDebugActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\b\u0010$\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020!H\u0002J\b\u0010'\u001a\u00020\u001dH\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020!H\u0002J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020!H\u0002J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\u001dH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/glmreader/android/ui/BleDebugActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "bleManager", "Lcom/glmreader/android/ble/GlmBleManager;", "tvConnStatus", "Landroid/widget/TextView;", "tvState", "tvQueue", "tvRx", "tvTx", "tvMeasurements", "tvLog", "scrollLog", "Landroid/widget/ScrollView;", "etRawCommand", "Landroid/widget/EditText;", "btnSendRaw", "Landroid/widget/Button;", "btnInit", "btnTrigger", "btnGetSettings", "btnClearLog", "measurementCount", "", "dateFormat", "Ljava/text/SimpleDateFormat;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "getBuildInfo", "", "initViews", "initBleManager", "refreshUiState", "parseBytes", "text", "startUiUpdateTimer", "setupClickListeners", "parseHexCommand", "", "hex", "appendLog", "message", "onSupportNavigateUp", "", "onDestroy", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BleDebugActivity extends AppCompatActivity {
    private GlmBleManager bleManager;
    private Button btnClearLog;
    private Button btnGetSettings;
    private Button btnInit;
    private Button btnSendRaw;
    private Button btnTrigger;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private EditText etRawCommand;
    private int measurementCount;
    private ScrollView scrollLog;
    private TextView tvConnStatus;
    private TextView tvLog;
    private TextView tvMeasurements;
    private TextView tvQueue;
    private TextView tvRx;
    private TextView tvState;
    private TextView tvTx;

    /* compiled from: BleDebugActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GlmBleManager.ProtocolState.values().length];
            try {
                iArr[GlmBleManager.ProtocolState.SLAVE_LISTENING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[GlmBleManager.ProtocolState.MASTER_READY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[GlmBleManager.ProtocolState.MASTER_SENDING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[GlmBleManager.ProtocolState.MASTER_RECEIVING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_debug);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Toolbar toolbar = (Toolbar) findViewById;
        toolbar.setTitle("BLE Debug");
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        initViews();
        initBleManager();
        setupClickListeners();
        appendLog("BLE Debug Activity started");
        appendLog("Build: " + getBuildInfo());
    }

    private final String getBuildInfo() {
        PackageInfo pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        String verName = pkgInfo.versionName;
        if (verName == null) {
            verName = "?";
        }
        int verCode = pkgInfo.versionCode;
        return "v" + verName + " (" + verCode + ") / 2026-04-12";
    }

    private final void initViews() {
        View findViewById = findViewById(R.id.tvConnStatus);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.tvConnStatus = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tvState);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tvState = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.tvQueue);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tvQueue = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.tvRx);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.tvRx = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.tvTx);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tvTx = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.tvMeasurements);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tvMeasurements = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.tvLog);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tvLog = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.scrollLog);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.scrollLog = (ScrollView) findViewById8;
        View findViewById9 = findViewById(R.id.etRawCommand);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.etRawCommand = (EditText) findViewById9;
        View findViewById10 = findViewById(R.id.btnSendRaw);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.btnSendRaw = (Button) findViewById10;
        View findViewById11 = findViewById(R.id.btnInit);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.btnInit = (Button) findViewById11;
        View findViewById12 = findViewById(R.id.btnTrigger);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.btnTrigger = (Button) findViewById12;
        View findViewById13 = findViewById(R.id.btnGetSettings);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.btnGetSettings = (Button) findViewById13;
        View findViewById14 = findViewById(R.id.btnClearLog);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.btnClearLog = (Button) findViewById14;
    }

    private final void initBleManager() {
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.glmreader.android.GlmReaderApplication");
        GlmReaderApplication app = (GlmReaderApplication) application;
        this.bleManager = app.getOrCreateBleManager(this);
        refreshUiState();
        GlmBleManager glmBleManager = this.bleManager;
        GlmBleManager glmBleManager2 = null;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.observeConnectionState(new Function1() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initBleManager$lambda$1;
                initBleManager$lambda$1 = BleDebugActivity.initBleManager$lambda$1(BleDebugActivity.this, ((Boolean) obj).booleanValue());
                return initBleManager$lambda$1;
            }
        });
        GlmBleManager glmBleManager3 = this.bleManager;
        if (glmBleManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager3 = null;
        }
        glmBleManager3.observeStateChange(new Function1() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initBleManager$lambda$3;
                initBleManager$lambda$3 = BleDebugActivity.initBleManager$lambda$3(BleDebugActivity.this, (GlmBleManager.ProtocolState) obj);
                return initBleManager$lambda$3;
            }
        });
        GlmBleManager glmBleManager4 = this.bleManager;
        if (glmBleManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager4 = null;
        }
        glmBleManager4.observeRawTx(new Function2() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit initBleManager$lambda$5;
                initBleManager$lambda$5 = BleDebugActivity.initBleManager$lambda$5(BleDebugActivity.this, (String) obj, (String) obj2);
                return initBleManager$lambda$5;
            }
        });
        GlmBleManager glmBleManager5 = this.bleManager;
        if (glmBleManager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
        } else {
            glmBleManager2 = glmBleManager5;
        }
        glmBleManager2.observeParsedMeasurement(new Function1() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initBleManager$lambda$7;
                initBleManager$lambda$7 = BleDebugActivity.initBleManager$lambda$7(BleDebugActivity.this, (BlePacketParser.ParsedMeasurement) obj);
                return initBleManager$lambda$7;
            }
        });
        startUiUpdateTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initBleManager$lambda$1(final BleDebugActivity this$0, boolean z) {
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                BleDebugActivity.this.refreshUiState();
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initBleManager$lambda$3(final BleDebugActivity this$0, final GlmBleManager.ProtocolState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BleDebugActivity.initBleManager$lambda$3$lambda$2(BleDebugActivity.this, state);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBleManager$lambda$3$lambda$2(BleDebugActivity this$0, GlmBleManager.ProtocolState $state) {
        String color;
        TextView textView = this$0.tvState;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvState");
            textView = null;
        }
        textView.setText("State: " + $state.name());
        switch (WhenMappings.$EnumSwitchMapping$0[$state.ordinal()]) {
            case 1:
                color = "#FFC107";
                break;
            case 2:
                color = "#4CAF50";
                break;
            case 3:
                color = "#2196F3";
                break;
            case 4:
                color = "#FF9800";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        TextView textView3 = this$0.tvState;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvState");
        } else {
            textView2 = textView3;
        }
        textView2.setTextColor(Color.parseColor(color));
        this$0.appendLog("STATE → " + $state.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initBleManager$lambda$5(final BleDebugActivity this$0, final String direction, final String hex) {
        Intrinsics.checkNotNullParameter(direction, "direction");
        Intrinsics.checkNotNullParameter(hex, "hex");
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BleDebugActivity.initBleManager$lambda$5$lambda$4(BleDebugActivity.this, direction, hex);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBleManager$lambda$5$lambda$4(BleDebugActivity this$0, String $direction, String $hex) {
        this$0.appendLog($direction + ": " + $hex);
        TextView textView = null;
        if (Intrinsics.areEqual($direction, "TX")) {
            int bytes = StringsKt.split$default((CharSequence) $hex, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null).size();
            TextView textView2 = this$0.tvTx;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvTx");
                textView2 = null;
            }
            int total = this$0.parseBytes(textView2.getText().toString()) + bytes;
            TextView textView3 = this$0.tvTx;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvTx");
            } else {
                textView = textView3;
            }
            textView.setText("TX: " + total + " B");
            return;
        }
        int bytes2 = StringsKt.split$default((CharSequence) $hex, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null).size();
        TextView textView4 = this$0.tvRx;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvRx");
            textView4 = null;
        }
        int total2 = this$0.parseBytes(textView4.getText().toString()) + bytes2;
        TextView textView5 = this$0.tvRx;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvRx");
        } else {
            textView = textView5;
        }
        textView.setText("RX: " + total2 + " B");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initBleManager$lambda$7(final BleDebugActivity this$0, final BlePacketParser.ParsedMeasurement parsed) {
        Intrinsics.checkNotNullParameter(parsed, "parsed");
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                BleDebugActivity.initBleManager$lambda$7$lambda$6(BleDebugActivity.this, parsed);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBleManager$lambda$7$lambda$6(BleDebugActivity this$0, BlePacketParser.ParsedMeasurement $parsed) {
        this$0.measurementCount++;
        TextView textView = this$0.tvMeasurements;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMeasurements");
            textView = null;
        }
        textView.setText("M: " + this$0.measurementCount);
        this$0.appendLog("✅ MEAS: devMode=" + $parsed.getDevMode() + " result=" + $parsed.getResultValue() + "m angle=" + $parsed.getComp2Value() + "°");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshUiState() {
        GlmBleManager glmBleManager = this.bleManager;
        GlmBleManager glmBleManager2 = null;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        if (glmBleManager.isConnected()) {
            TextView textView = this.tvConnStatus;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvConnStatus");
                textView = null;
            }
            GlmBleManager glmBleManager3 = this.bleManager;
            if (glmBleManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                glmBleManager3 = null;
            }
            textView.setText("✅ Connected: " + glmBleManager3.getConnectedDeviceName());
            TextView textView2 = this.tvConnStatus;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvConnStatus");
                textView2 = null;
            }
            textView2.setTextColor(getColor(R.color.status_success));
        } else {
            TextView textView3 = this.tvConnStatus;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvConnStatus");
                textView3 = null;
            }
            textView3.setText("❌ Not connected");
            TextView textView4 = this.tvConnStatus;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvConnStatus");
                textView4 = null;
            }
            textView4.setTextColor(getColor(R.color.status_error));
        }
        TextView textView5 = this.tvState;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvState");
            textView5 = null;
        }
        GlmBleManager glmBleManager4 = this.bleManager;
        if (glmBleManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager4 = null;
        }
        textView5.setText("State: " + glmBleManager4.getProtocolStateName());
        TextView textView6 = this.tvQueue;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvQueue");
            textView6 = null;
        }
        GlmBleManager glmBleManager5 = this.bleManager;
        if (glmBleManager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
        } else {
            glmBleManager2 = glmBleManager5;
        }
        textView6.setText("Q:" + glmBleManager2.getQueueSize());
    }

    private final int parseBytes(String text) {
        try {
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(text, "RX: ", "", false, 4, (Object) null), "TX: ", "", false, 4, (Object) null), " B", "", false, 4, (Object) null));
            if (intOrNull != null) {
                return intOrNull.intValue();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private final void startUiUpdateTimer() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$startUiUpdateTimer$runnable$1
            @Override // java.lang.Runnable
            public void run() {
                TextView textView;
                GlmBleManager glmBleManager;
                GlmBleManager glmBleManager2;
                GlmBleManager glmBleManager3;
                TextView textView2;
                GlmBleManager glmBleManager4;
                if (BleDebugActivity.this.isFinishing()) {
                    return;
                }
                textView = BleDebugActivity.this.tvQueue;
                GlmBleManager glmBleManager5 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvQueue");
                    textView = null;
                }
                glmBleManager = BleDebugActivity.this.bleManager;
                if (glmBleManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    glmBleManager = null;
                }
                textView.setText("Q:" + glmBleManager.getQueueSize());
                glmBleManager2 = BleDebugActivity.this.bleManager;
                if (glmBleManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    glmBleManager2 = null;
                }
                if (glmBleManager2.isConnected()) {
                    glmBleManager3 = BleDebugActivity.this.bleManager;
                    if (glmBleManager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                        glmBleManager3 = null;
                    }
                    glmBleManager3.getDebugInfo();
                    textView2 = BleDebugActivity.this.tvState;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tvState");
                        textView2 = null;
                    }
                    glmBleManager4 = BleDebugActivity.this.bleManager;
                    if (glmBleManager4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                    } else {
                        glmBleManager5 = glmBleManager4;
                    }
                    textView2.setText("State: " + glmBleManager5.getProtocolStateName());
                }
                handler.postDelayed(this, 500L);
            }
        });
    }

    private final void setupClickListeners() {
        Button button = this.btnSendRaw;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSendRaw");
            button = null;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDebugActivity.setupClickListeners$lambda$8(BleDebugActivity.this, view);
            }
        });
        Button button3 = this.btnInit;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnInit");
            button3 = null;
        }
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDebugActivity.setupClickListeners$lambda$9(BleDebugActivity.this, view);
            }
        });
        Button button4 = this.btnTrigger;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnTrigger");
            button4 = null;
        }
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDebugActivity.setupClickListeners$lambda$10(BleDebugActivity.this, view);
            }
        });
        Button button5 = this.btnGetSettings;
        if (button5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnGetSettings");
            button5 = null;
        }
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDebugActivity.setupClickListeners$lambda$11(BleDebugActivity.this, view);
            }
        });
        Button button6 = this.btnClearLog;
        if (button6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnClearLog");
        } else {
            button2 = button6;
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDebugActivity.setupClickListeners$lambda$12(BleDebugActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupClickListeners$lambda$8(BleDebugActivity this$0, View it) {
        byte[] bytes;
        EditText editText = this$0.etRawCommand;
        EditText editText2 = null;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etRawCommand");
            editText = null;
        }
        String hex = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
        if (!(hex.length() == 0) && (bytes = this$0.parseHexCommand(hex)) != null) {
            GlmBleManager glmBleManager = this$0.bleManager;
            if (glmBleManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                glmBleManager = null;
            }
            glmBleManager.sendRawCommand(bytes, "Raw: " + hex);
            EditText editText3 = this$0.etRawCommand;
            if (editText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etRawCommand");
            } else {
                editText2 = editText3;
            }
            editText2.getText().clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupClickListeners$lambda$9(BleDebugActivity this$0, View it) {
        GlmBleManager glmBleManager = this$0.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.sendInit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupClickListeners$lambda$10(BleDebugActivity this$0, View it) {
        GlmBleManager glmBleManager = this$0.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.sendTrigger();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupClickListeners$lambda$11(BleDebugActivity this$0, View it) {
        GlmBleManager glmBleManager = this$0.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.sendGetSettings();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupClickListeners$lambda$12(BleDebugActivity this$0, View it) {
        TextView textView = this$0.tvLog;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvLog");
            textView = null;
        }
        textView.setText("");
        this$0.measurementCount = 0;
        TextView textView3 = this$0.tvMeasurements;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMeasurements");
        } else {
            textView2 = textView3;
        }
        textView2.setText("M: 0");
        this$0.appendLog("Log cleared");
    }

    private final byte[] parseHexCommand(String hex) {
        try {
            Iterable split = new Regex("\\s+").split(hex, 0);
            Collection arrayList = new ArrayList();
            for (Object obj : split) {
                if (((String) obj).length() > 0) {
                    arrayList.add(obj);
                }
            }
            Iterable parts = (List) arrayList;
            Iterable iterable = parts;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList2.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
            }
            return CollectionsKt.toByteArray((List) arrayList2);
        } catch (Exception e) {
            appendLog("❌ Invalid HEX: " + e.getMessage());
            return null;
        }
    }

    private final void appendLog(String message) {
        String timestamp = this.dateFormat.format(new Date());
        String line = CollectionUtils.DEFAULT_TOSTRING_PREFIX + timestamp + "] " + message + StringUtils.LF;
        TextView textView = this.tvLog;
        ScrollView scrollView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvLog");
            textView = null;
        }
        textView.append(line);
        ScrollView scrollView2 = this.scrollLog;
        if (scrollView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollLog");
        } else {
            scrollView = scrollView2;
        }
        scrollView.post(new Runnable() { // from class: com.glmreader.android.ui.BleDebugActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                BleDebugActivity.appendLog$lambda$15(BleDebugActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void appendLog$lambda$15(BleDebugActivity this$0) {
        ScrollView scrollView = this$0.scrollLog;
        if (scrollView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollLog");
            scrollView = null;
        }
        scrollView.fullScroll(130);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
