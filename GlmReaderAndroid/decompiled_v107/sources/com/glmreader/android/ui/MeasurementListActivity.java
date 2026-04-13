package com.glmreader.android.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.glmreader.android.GlmReaderApplication;
import com.glmreader.android.R;
import com.glmreader.android.ble.GlmBleManager;
import com.glmreader.android.export.XlsxExporter;
import com.glmreader.android.protocol.BlePacketParser;
import com.glmreader.android.ui.viewmodel.MeasurementViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: MeasurementListActivity.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001XB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0014J\b\u0010B\u001a\u00020?H\u0002J\b\u0010C\u001a\u00020?H\u0002J\b\u0010D\u001a\u00020?H\u0002J\b\u0010E\u001a\u00020?H\u0002J\u0010\u0010F\u001a\u00020?2\u0006\u0010G\u001a\u00020/H\u0002J\b\u0010H\u001a\u00020?H\u0002J\u0012\u0010I\u001a\u0002022\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\u0010\u0010L\u001a\u0002022\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020?H\u0002J\b\u0010P\u001a\u00020?H\u0014J\b\u0010Q\u001a\u00020?H\u0002J\u0010\u0010R\u001a\u00020?2\u0006\u0010S\u001a\u00020/H\u0002J\u0010\u0010T\u001a\u00020?2\u0006\u0010S\u001a\u00020/H\u0002J\u0010\u0010U\u001a\u00020?2\u0006\u0010G\u001a\u00020/H\u0002J\u0010\u0010V\u001a\u00020W2\u0006\u0010G\u001a\u00020/H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150=0<X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lcom/glmreader/android/ui/MeasurementListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "viewModel", "Lcom/glmreader/android/ui/viewmodel/MeasurementViewModel;", "getViewModel", "()Lcom/glmreader/android/ui/viewmodel/MeasurementViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bleManager", "Lcom/glmreader/android/ble/GlmBleManager;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "tvEmpty", "Landroid/widget/TextView;", "adapter", "Lcom/glmreader/android/ui/MeasurementAdapter;", "xlsxExporter", "Lcom/glmreader/android/export/XlsxExporter;", "currentProjectId", "", "currentProjectName", "panelRemote", "Landroid/widget/LinearLayout;", "panelRemoteContent", "btnTogglePanel", "ivPanelArrow", "Landroid/widget/ImageView;", "refCubeContainer", "Landroid/widget/FrameLayout;", "ivRefIcon", "tvRefLabel", "typeCubeContainer", "ivTypeIcon", "tvTypeLabel", "btnEnableLaser", "Landroid/widget/Button;", "selectionModeBar", "tvSelectAll", "tvInterval", "tvDeselect", "listHeader", "ivDownload", "fabScrollTop", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "currentRefIndex", "", "currentTypeIndex", "isPanelExpanded", "", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "listBackground", "Landroid/view/View;", "toolbarRefEdgeColors", "", "currentToolbarColor", "listBgColors", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "applyInitialStateFromRuler", "flipRefCube", "flipTypeCube", "setupBleCallbacks", "updateListBackground", "devMode", "checkPermissionsAndScan", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "exportToXlsx", "onDestroy", "showBleDialog", "updateToolbarColor", "refEdge", "updateRefCube", "updateTypeIcon", "getTypeInfo", "Lcom/glmreader/android/ui/MeasurementListActivity$TypeInfo;", "TypeInfo", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MeasurementListActivity extends AppCompatActivity {
    private MeasurementAdapter adapter;
    private GlmBleManager bleManager;
    private Button btnEnableLaser;
    private LinearLayout btnTogglePanel;
    private String currentProjectId;
    private int currentRefIndex;
    private int currentToolbarColor;
    private int currentTypeIndex;
    private FloatingActionButton fabScrollTop;
    private ImageView ivDownload;
    private ImageView ivPanelArrow;
    private ImageView ivRefIcon;
    private ImageView ivTypeIcon;
    private View listBackground;
    private LinearLayout listHeader;
    private LinearLayout panelRemote;
    private LinearLayout panelRemoteContent;
    private RecyclerView recyclerView;
    private FrameLayout refCubeContainer;
    private LinearLayout selectionModeBar;
    private Toolbar toolbar;
    private TextView tvDeselect;
    private TextView tvEmpty;
    private TextView tvInterval;
    private TextView tvRefLabel;
    private TextView tvSelectAll;
    private TextView tvTypeLabel;
    private FrameLayout typeCubeContainer;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private XlsxExporter xlsxExporter;
    private String currentProjectName = "Все замеры";
    private boolean isPanelExpanded = true;
    private final int[] toolbarRefEdgeColors = {R.color.toolbar_rear, R.color.toolbar_tripod, R.color.toolbar_front, R.color.toolbar_pin};
    private final int[] listBgColors = {R.color.bg_direct, R.color.bg_continuous, R.color.bg_minmax, R.color.bg_indirect, R.color.bg_double_indirect, R.color.bg_partial_area, R.color.bg_stakeout, R.color.bg_angle, R.color.bg_wall, R.color.bg_volume, R.color.bg_area, R.color.bg_unknown};
    private final ActivityResultLauncher<String[]> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda7
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            MeasurementListActivity.requestPermissionLauncher$lambda$1(MeasurementListActivity.this, (Map) obj);
        }
    });

    public MeasurementListActivity() {
        final MeasurementListActivity measurementListActivity = this;
        final Function0 function0 = null;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(MeasurementViewModel.class), new Function0<ViewModelStore>() { // from class: com.glmreader.android.ui.MeasurementListActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.glmreader.android.ui.MeasurementListActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.glmreader.android.ui.MeasurementListActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? measurementListActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MeasurementViewModel getViewModel() {
        return (MeasurementViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$1(MeasurementListActivity this$0, Map permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Iterable values = permissions.values();
        boolean z = true;
        if (!(values instanceof Collection) || !((Collection) values).isEmpty()) {
            Iterator it = values.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!((Boolean) it.next()).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        if (!z) {
            Toast.makeText(this$0, "Нет прав на BLE", 0).show();
            return;
        }
        GlmBleManager glmBleManager = this$0.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.startScan();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_list);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.toolbar = (Toolbar) findViewById;
        Toolbar toolbar = this.toolbar;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setSubtitle("v1.0.7");
        }
        this.currentProjectId = getIntent().getStringExtra("project_uuid");
        String stringExtra = getIntent().getStringExtra("project_name");
        if (stringExtra == null) {
            stringExtra = "Все замеры";
        }
        this.currentProjectName = stringExtra;
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle(this.currentProjectName);
        }
        View findViewById2 = findViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.recyclerView = (RecyclerView) findViewById2;
        View findViewById3 = findViewById(R.id.listBackground);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.listBackground = findViewById3;
        TextView textView = new TextView(this);
        textView.setText("Нет измерений");
        textView.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        textView.setTextSize(18.0f);
        textView.setGravity(17);
        textView.setVisibility(8);
        this.tvEmpty = textView;
        this.xlsxExporter = new XlsxExporter(this);
        View findViewById4 = findViewById(R.id.panelRemote);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.panelRemote = (LinearLayout) findViewById4;
        View findViewById5 = findViewById(R.id.panelRemoteContent);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.panelRemoteContent = (LinearLayout) findViewById5;
        View findViewById6 = findViewById(R.id.btnTogglePanel);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.btnTogglePanel = (LinearLayout) findViewById6;
        View findViewById7 = findViewById(R.id.ivPanelArrow);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.ivPanelArrow = (ImageView) findViewById7;
        View findViewById8 = findViewById(R.id.refCubeContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.refCubeContainer = (FrameLayout) findViewById8;
        View findViewById9 = findViewById(R.id.ivRefIcon);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.ivRefIcon = (ImageView) findViewById9;
        View findViewById10 = findViewById(R.id.tvRefLabel);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.tvRefLabel = (TextView) findViewById10;
        View findViewById11 = findViewById(R.id.typeCubeContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.typeCubeContainer = (FrameLayout) findViewById11;
        View findViewById12 = findViewById(R.id.ivTypeIcon);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.ivTypeIcon = (ImageView) findViewById12;
        View findViewById13 = findViewById(R.id.tvTypeLabel);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.tvTypeLabel = (TextView) findViewById13;
        View findViewById14 = findViewById(R.id.btnEnableLaser);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.btnEnableLaser = (Button) findViewById14;
        View findViewById15 = findViewById(R.id.selectionModeBar);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.selectionModeBar = (LinearLayout) findViewById15;
        View findViewById16 = findViewById(R.id.tvSelectAll);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.tvSelectAll = (TextView) findViewById16;
        View findViewById17 = findViewById(R.id.tvInterval);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.tvInterval = (TextView) findViewById17;
        View findViewById18 = findViewById(R.id.tvDeselect);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.tvDeselect = (TextView) findViewById18;
        View findViewById19 = findViewById(R.id.listHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.listHeader = (LinearLayout) findViewById19;
        View findViewById20 = findViewById(R.id.ivDownload);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.ivDownload = (ImageView) findViewById20;
        View findViewById21 = findViewById(R.id.fabScrollTop);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(...)");
        this.fabScrollTop = (FloatingActionButton) findViewById21;
        updateToolbarColor(0);
        LinearLayout linearLayout = this.btnTogglePanel;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnTogglePanel");
            linearLayout = null;
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$4(MeasurementListActivity.this, view);
            }
        });
        FrameLayout frameLayout = this.refCubeContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refCubeContainer");
            frameLayout = null;
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.this.flipRefCube();
            }
        });
        FrameLayout frameLayout2 = this.typeCubeContainer;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeCubeContainer");
            frameLayout2 = null;
        }
        frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.this.flipTypeCube();
            }
        });
        Button button = this.btnEnableLaser;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnEnableLaser");
            button = null;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$7(MeasurementListActivity.this, view);
            }
        });
        TextView textView2 = this.tvSelectAll;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSelectAll");
            textView2 = null;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$8(MeasurementListActivity.this, view);
            }
        });
        TextView textView3 = this.tvInterval;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvInterval");
            textView3 = null;
        }
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$9(MeasurementListActivity.this, view);
            }
        });
        TextView textView4 = this.tvDeselect;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDeselect");
            textView4 = null;
        }
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$10(MeasurementListActivity.this, view);
            }
        });
        final Ref.BooleanRef isLongPressing = new Ref.BooleanRef();
        ImageView imageView = this.ivDownload;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivDownload");
            imageView = null;
        }
        imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean onCreate$lambda$11;
                onCreate$lambda$11 = MeasurementListActivity.onCreate$lambda$11(MeasurementListActivity.this, isLongPressing, view);
                return onCreate$lambda$11;
            }
        });
        ImageView imageView2 = this.ivDownload;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivDownload");
            imageView2 = null;
        }
        imageView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean onCreate$lambda$12;
                onCreate$lambda$12 = MeasurementListActivity.onCreate$lambda$12(Ref.BooleanRef.this, view, motionEvent);
                return onCreate$lambda$12;
            }
        });
        this.adapter = new MeasurementAdapter(new ArrayList(), new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$13;
                onCreate$lambda$13 = MeasurementListActivity.onCreate$lambda$13(MeasurementListActivity.this, (String) obj);
                return onCreate$lambda$13;
            }
        }, new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$15;
                onCreate$lambda$15 = MeasurementListActivity.onCreate$lambda$15(MeasurementListActivity.this, (Set) obj);
                return onCreate$lambda$15;
            }
        });
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        MeasurementAdapter measurementAdapter = this.adapter;
        if (measurementAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            measurementAdapter = null;
        }
        recyclerView.setAdapter(measurementAdapter);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView3 = null;
        }
        recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$onCreate$13
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView4, int dx, int dy) {
                FloatingActionButton floatingActionButton;
                FloatingActionButton floatingActionButton2;
                Intrinsics.checkNotNullParameter(recyclerView4, "recyclerView");
                FloatingActionButton floatingActionButton3 = null;
                if (!recyclerView4.canScrollVertically(-1)) {
                    floatingActionButton2 = MeasurementListActivity.this.fabScrollTop;
                    if (floatingActionButton2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("fabScrollTop");
                    } else {
                        floatingActionButton3 = floatingActionButton2;
                    }
                    floatingActionButton3.setVisibility(8);
                    return;
                }
                floatingActionButton = MeasurementListActivity.this.fabScrollTop;
                if (floatingActionButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fabScrollTop");
                } else {
                    floatingActionButton3 = floatingActionButton;
                }
                floatingActionButton3.setVisibility(0);
            }
        });
        FloatingActionButton floatingActionButton = this.fabScrollTop;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabScrollTop");
            floatingActionButton = null;
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementListActivity.onCreate$lambda$16(MeasurementListActivity.this, view);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MeasurementListActivity$onCreate$15(this, null), 3, null);
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.glmreader.android.GlmReaderApplication");
        GlmReaderApplication app = (GlmReaderApplication) application;
        this.bleManager = app.getOrCreateBleManager(this);
        setupBleCallbacks();
        checkPermissionsAndScan();
        applyInitialStateFromRuler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(MeasurementListActivity this$0, View it) {
        this$0.isPanelExpanded = !this$0.isPanelExpanded;
        LinearLayout linearLayout = this$0.panelRemoteContent;
        ImageView imageView = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("panelRemoteContent");
            linearLayout = null;
        }
        linearLayout.setVisibility(this$0.isPanelExpanded ? 0 : 8);
        ImageView imageView2 = this$0.ivPanelArrow;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivPanelArrow");
        } else {
            imageView = imageView2;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "rotation", this$0.isPanelExpanded ? 0.0f : 180.0f);
        ofFloat.setDuration(300L);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(MeasurementListActivity this$0, View it) {
        Button button = this$0.btnEnableLaser;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnEnableLaser");
            button = null;
        }
        boolean isOn = StringsKt.contains$default((CharSequence) button.getText().toString(), (CharSequence) "Включить", false, 2, (Object) null);
        Button button3 = this$0.btnEnableLaser;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnEnableLaser");
        } else {
            button2 = button3;
        }
        button2.setText(isOn ? "Выключить" : "Включить");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(MeasurementListActivity this$0, View it) {
        MeasurementAdapter measurementAdapter = this$0.adapter;
        if (measurementAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            measurementAdapter = null;
        }
        measurementAdapter.selectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(MeasurementListActivity this$0, View it) {
        Toast.makeText(this$0, "Интервал (в разработке)", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(MeasurementListActivity this$0, View it) {
        MeasurementAdapter measurementAdapter = this$0.adapter;
        if (measurementAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            measurementAdapter = null;
        }
        measurementAdapter.clearSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$11(MeasurementListActivity this$0, Ref.BooleanRef $isLongPressing, View it) {
        MeasurementAdapter measurementAdapter = this$0.adapter;
        if (measurementAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            measurementAdapter = null;
        }
        measurementAdapter.toggleSelectionMode(true);
        $isLongPressing.element = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$12(Ref.BooleanRef $isLongPressing, View view, MotionEvent event) {
        if (event.getAction() == 1 && $isLongPressing.element) {
            $isLongPressing.element = false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$13(MeasurementListActivity this$0, String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this$0.getViewModel().deleteMeasurement(uuid);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$15(final MeasurementListActivity this$0, final Set selectedUuids) {
        Intrinsics.checkNotNullParameter(selectedUuids, "selectedUuids");
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                MeasurementListActivity.onCreate$lambda$15$lambda$14(selectedUuids, this$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$15$lambda$14(Set $selectedUuids, MeasurementListActivity this$0) {
        if ($selectedUuids.isEmpty()) {
            MeasurementAdapter measurementAdapter = this$0.adapter;
            if (measurementAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                measurementAdapter = null;
            }
            measurementAdapter.toggleSelectionMode(false);
            ActionBar supportActionBar = this$0.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(this$0.currentProjectName);
                return;
            }
            return;
        }
        ActionBar supportActionBar2 = this$0.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle("Выбрано: " + $selectedUuids.size());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$16(MeasurementListActivity this$0, View it) {
        RecyclerView recyclerView = this$0.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        recyclerView.smoothScrollToPosition(0);
    }

    private final void applyInitialStateFromRuler() {
        GlmBleManager glmBleManager = this.bleManager;
        GlmBleManager glmBleManager2 = null;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        if (glmBleManager.isConnected()) {
            GlmBleManager glmBleManager3 = this.bleManager;
            if (glmBleManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                glmBleManager3 = null;
            }
            int currentRef = glmBleManager3.getCurrentRefEdge();
            GlmBleManager glmBleManager4 = this.bleManager;
            if (glmBleManager4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            } else {
                glmBleManager2 = glmBleManager4;
            }
            int currentType = glmBleManager2.getCurrentMeasurementType();
            Log.d("UI_INIT", "Applying initial state: refEdge=" + currentRef + ", type=" + currentType);
            updateToolbarColor(currentRef);
            updateRefCube(currentRef);
            updateTypeIcon(currentType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flipRefCube() {
        final String[] refNames = {"Задняя", "Штатив", "Передняя"};
        final Integer[] refIcons = {Integer.valueOf(R.drawable.ic_ref_rear), Integer.valueOf(R.drawable.ic_ref_tripod), Integer.valueOf(R.drawable.ic_ref_front)};
        this.currentRefIndex = (this.currentRefIndex + 1) % refNames.length;
        Log.d("CMD_DEBUG", "Sending Set Ref: refLevel=" + this.currentRefIndex + " (" + refNames[this.currentRefIndex] + ")");
        FrameLayout frameLayout = this.refCubeContainer;
        GlmBleManager glmBleManager = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refCubeContainer");
            frameLayout = null;
        }
        ObjectAnimator animOut = ObjectAnimator.ofFloat(frameLayout, "rotationY", 0.0f, -90.0f);
        animOut.setDuration(200L);
        animOut.setInterpolator(new DecelerateInterpolator());
        animOut.addListener(new AnimatorListenerAdapter() { // from class: com.glmreader.android.ui.MeasurementListActivity$flipRefCube$animOut$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                ImageView imageView;
                int i;
                TextView textView;
                int i2;
                FrameLayout frameLayout2;
                Intrinsics.checkNotNullParameter(animation, "animation");
                imageView = MeasurementListActivity.this.ivRefIcon;
                FrameLayout frameLayout3 = null;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ivRefIcon");
                    imageView = null;
                }
                Integer[] numArr = refIcons;
                i = MeasurementListActivity.this.currentRefIndex;
                imageView.setImageResource(numArr[i].intValue());
                textView = MeasurementListActivity.this.tvRefLabel;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvRefLabel");
                    textView = null;
                }
                String[] strArr = refNames;
                i2 = MeasurementListActivity.this.currentRefIndex;
                textView.setText(strArr[i2]);
                frameLayout2 = MeasurementListActivity.this.refCubeContainer;
                if (frameLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("refCubeContainer");
                } else {
                    frameLayout3 = frameLayout2;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(frameLayout3, "rotationY", 90.0f, 0.0f);
                ofFloat.setDuration(200L);
                ofFloat.setInterpolator(new DecelerateInterpolator());
                ofFloat.start();
            }
        });
        animOut.start();
        GlmBleManager glmBleManager2 = this.bleManager;
        if (glmBleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager2 = null;
        }
        if (glmBleManager2.isConnected()) {
            GlmBleManager glmBleManager3 = this.bleManager;
            if (glmBleManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            } else {
                glmBleManager = glmBleManager3;
            }
            glmBleManager.setReferencePoint(this.currentRefIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flipTypeCube() {
        final String[] typeNames = {"Прямой", "Непрерыв.", "Косв. выс.", "Косв. длина", "Двойная", "Мин/Макс", "Угол", "Площадь", "Стена", "Объём", "Разбивка", "Трапеция"};
        final Integer[] typeIcons = {Integer.valueOf(R.drawable.ic_distance), Integer.valueOf(R.drawable.ic_continuous), Integer.valueOf(R.drawable.ic_indirect_height), Integer.valueOf(R.drawable.ic_indirect_length), Integer.valueOf(R.drawable.ic_double_indirect), Integer.valueOf(R.drawable.ic_minmax), Integer.valueOf(R.drawable.ic_angle), Integer.valueOf(R.drawable.ic_area), Integer.valueOf(R.drawable.ic_wall), Integer.valueOf(R.drawable.ic_volume), Integer.valueOf(R.drawable.ic_stakeout), Integer.valueOf(R.drawable.ic_trapezoid)};
        Integer[] modeValues = {1, 2, 10, 11, 12, 3, 15, 5, 14, 6, 15, 26};
        this.currentTypeIndex = (this.currentTypeIndex + 1) % typeNames.length;
        Log.d("CMD_DEBUG", "Sending Set Type: mode=" + modeValues[this.currentTypeIndex] + " (" + typeNames[this.currentTypeIndex] + ")");
        FrameLayout frameLayout = this.typeCubeContainer;
        GlmBleManager glmBleManager = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeCubeContainer");
            frameLayout = null;
        }
        ObjectAnimator animOut = ObjectAnimator.ofFloat(frameLayout, "rotationY", 0.0f, -90.0f);
        animOut.setDuration(200L);
        animOut.setInterpolator(new DecelerateInterpolator());
        animOut.addListener(new AnimatorListenerAdapter() { // from class: com.glmreader.android.ui.MeasurementListActivity$flipTypeCube$animOut$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                ImageView imageView;
                int i;
                TextView textView;
                int i2;
                FrameLayout frameLayout2;
                Intrinsics.checkNotNullParameter(animation, "animation");
                imageView = MeasurementListActivity.this.ivTypeIcon;
                FrameLayout frameLayout3 = null;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ivTypeIcon");
                    imageView = null;
                }
                Integer[] numArr = typeIcons;
                i = MeasurementListActivity.this.currentTypeIndex;
                imageView.setImageResource(numArr[i].intValue());
                textView = MeasurementListActivity.this.tvTypeLabel;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvTypeLabel");
                    textView = null;
                }
                String[] strArr = typeNames;
                i2 = MeasurementListActivity.this.currentTypeIndex;
                textView.setText(strArr[i2]);
                frameLayout2 = MeasurementListActivity.this.typeCubeContainer;
                if (frameLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("typeCubeContainer");
                } else {
                    frameLayout3 = frameLayout2;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(frameLayout3, "rotationY", 90.0f, 0.0f);
                ofFloat.setDuration(200L);
                ofFloat.setInterpolator(new DecelerateInterpolator());
                ofFloat.start();
            }
        });
        animOut.start();
        GlmBleManager glmBleManager2 = this.bleManager;
        if (glmBleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager2 = null;
        }
        if (glmBleManager2.isConnected()) {
            GlmBleManager glmBleManager3 = this.bleManager;
            if (glmBleManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            } else {
                glmBleManager = glmBleManager3;
            }
            glmBleManager.setMeasurementType(modeValues[this.currentTypeIndex].intValue());
        }
    }

    private final void setupBleCallbacks() {
        GlmBleManager glmBleManager = this.bleManager;
        GlmBleManager glmBleManager2 = null;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.setOnConnectionStateChanged(new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = MeasurementListActivity.setupBleCallbacks$lambda$20(MeasurementListActivity.this, ((Boolean) obj).booleanValue());
                return unit;
            }
        });
        GlmBleManager glmBleManager3 = this.bleManager;
        if (glmBleManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager3 = null;
        }
        glmBleManager3.setOnRefEdgeChanged(new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = MeasurementListActivity.setupBleCallbacks$lambda$22(MeasurementListActivity.this, ((Integer) obj).intValue());
                return unit;
            }
        });
        GlmBleManager glmBleManager4 = this.bleManager;
        if (glmBleManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager4 = null;
        }
        glmBleManager4.setOnMeasurementTypeChanged(new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = MeasurementListActivity.setupBleCallbacks$lambda$24(MeasurementListActivity.this, ((Integer) obj).intValue());
                return unit;
            }
        });
        GlmBleManager glmBleManager5 = this.bleManager;
        if (glmBleManager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
        } else {
            glmBleManager2 = glmBleManager5;
        }
        glmBleManager2.setOnDataReceived(new Function1() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = MeasurementListActivity.setupBleCallbacks$lambda$25(MeasurementListActivity.this, (byte[]) obj);
                return unit;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupBleCallbacks$lambda$20(final MeasurementListActivity this$0, final boolean connected) {
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                MeasurementListActivity.setupBleCallbacks$lambda$20$lambda$19(MeasurementListActivity.this, connected);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupBleCallbacks$lambda$20$lambda$19(MeasurementListActivity this$0, boolean $connected) {
        LinearLayout linearLayout = this$0.panelRemote;
        FrameLayout frameLayout = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("panelRemote");
            linearLayout = null;
        }
        linearLayout.setVisibility(0);
        Button button = this$0.btnEnableLaser;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnEnableLaser");
            button = null;
        }
        button.setEnabled($connected);
        FrameLayout frameLayout2 = this$0.refCubeContainer;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refCubeContainer");
            frameLayout2 = null;
        }
        frameLayout2.setEnabled($connected);
        FrameLayout frameLayout3 = this$0.typeCubeContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeCubeContainer");
        } else {
            frameLayout = frameLayout3;
        }
        frameLayout.setEnabled($connected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupBleCallbacks$lambda$22(final MeasurementListActivity this$0, final int refEdge) {
        Log.d("UI_CALLBACK", "🎨 onRefEdgeChanged: refEdge=" + refEdge);
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                MeasurementListActivity.setupBleCallbacks$lambda$22$lambda$21(MeasurementListActivity.this, refEdge);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupBleCallbacks$lambda$22$lambda$21(MeasurementListActivity this$0, int $refEdge) {
        this$0.updateToolbarColor($refEdge);
        this$0.updateRefCube($refEdge);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupBleCallbacks$lambda$24(final MeasurementListActivity this$0, final int devMode) {
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.MeasurementListActivity$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                MeasurementListActivity.setupBleCallbacks$lambda$24$lambda$23(MeasurementListActivity.this, devMode);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupBleCallbacks$lambda$24$lambda$23(MeasurementListActivity this$0, int $devMode) {
        this$0.updateListBackground($devMode);
        this$0.updateTypeIcon($devMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupBleCallbacks$lambda$25(MeasurementListActivity this$0, byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        BlePacketParser.ParsedMeasurement parsed = BlePacketParser.INSTANCE.parse(bytes);
        if (parsed != null) {
            this$0.getViewModel().onBleMeasurement(parsed, this$0.currentProjectId);
        }
        return Unit.INSTANCE;
    }

    private final void updateListBackground(int devMode) {
        int colorRes;
        switch (devMode) {
            case 1:
                colorRes = R.color.bg_direct;
                break;
            case 2:
                colorRes = R.color.bg_continuous;
                break;
            case 3:
                colorRes = R.color.bg_minmax;
                break;
            case 4:
            case 7:
            case 8:
            case 9:
            case 16:
            default:
                colorRes = R.color.bg_unknown;
                break;
            case 5:
                colorRes = R.color.bg_area;
                break;
            case 6:
                colorRes = R.color.bg_volume;
                break;
            case 10:
                colorRes = R.color.bg_indirect_height;
                break;
            case 11:
                colorRes = R.color.bg_indirect_length;
                break;
            case 12:
                colorRes = R.color.bg_double_indirect;
                break;
            case 13:
                colorRes = R.color.bg_partial_area;
                break;
            case 14:
                colorRes = R.color.bg_stakeout;
                break;
            case 15:
                colorRes = R.color.bg_angle;
                break;
            case 17:
                colorRes = R.color.bg_trapezoid;
                break;
        }
        int bgColor = ContextCompat.getColor(this, colorRes);
        Log.d("BG_DEBUG", "Updating list bg: devMode=" + devMode + " -> colorRes=" + colorRes);
        View view = this.listBackground;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listBackground");
            view = null;
        }
        ObjectAnimator ofArgb = ObjectAnimator.ofArgb(view, "backgroundColor", bgColor);
        ofArgb.setDuration(400L);
        ofArgb.start();
    }

    private final void checkPermissionsAndScan() {
        String[] permissions;
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 31) {
            permissions = new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", "android.permission.ACCESS_FINE_LOCATION"};
        } else {
            permissions = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
        }
        String[] strArr = permissions;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                if ((ContextCompat.checkSelfPermission(this, strArr[i]) == 0 ? 1 : null) == null) {
                    break;
                } else {
                    i++;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            GlmBleManager glmBleManager = this.bleManager;
            if (glmBleManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
                glmBleManager = null;
            }
            glmBleManager.startScan();
            return;
        }
        this.requestPermissionLauncher.launch(permissions);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.action_ble) {
            showBleDialog();
            return true;
        }
        if (itemId == R.id.action_ble_debug) {
            startActivity(new Intent(this, (Class<?>) BleDebugActivity.class));
            return true;
        }
        if (itemId == R.id.action_export) {
            exportToXlsx();
            return true;
        }
        if (itemId == R.id.action_settings) {
            startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final void exportToXlsx() {
        MeasurementAdapter measurementAdapter = this.adapter;
        if (measurementAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            measurementAdapter = null;
        }
        List measurements = measurementAdapter.getMeasurements();
        if (!measurements.isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MeasurementListActivity$exportToXlsx$1(this, measurements, null), 3, null);
        } else {
            Toast.makeText(this, "Нет данных для экспорта", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private final void showBleDialog() {
        BleDialogHelper bleDialogHelper = BleDialogHelper.INSTANCE;
        MeasurementListActivity measurementListActivity = this;
        GlmBleManager glmBleManager = this.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        bleDialogHelper.show(measurementListActivity, glmBleManager);
    }

    private final void updateToolbarColor(int refEdge) {
        String colorName;
        int[] iArr = this.toolbarRefEdgeColors;
        int colorRes = refEdge >= 0 && refEdge < iArr.length ? iArr[refEdge] : this.toolbarRefEdgeColors[0];
        int newColor = ContextCompat.getColor(this, colorRes);
        this.currentToolbarColor = refEdge;
        switch (refEdge) {
            case 0:
                colorName = "Rear (Blue #1565c0)";
                break;
            case 1:
                colorName = "Tripod (Red #c62828)";
                break;
            case 2:
                colorName = "Front (Green #2e7d32)";
                break;
            case 3:
                colorName = "Pin (Orange #FF9800)";
                break;
            default:
                colorName = "Unknown";
                break;
        }
        Log.d("COLOR_DEBUG", "Applying refEdge=" + refEdge + " -> " + colorName);
        Toolbar toolbar = this.toolbar;
        LinearLayout linearLayout = null;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        ObjectAnimator ofArgb = ObjectAnimator.ofArgb(toolbar, "backgroundColor", newColor);
        ofArgb.setDuration(400L);
        ofArgb.start();
        LinearLayout linearLayout2 = this.btnTogglePanel;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnTogglePanel");
        } else {
            linearLayout = linearLayout2;
        }
        ObjectAnimator ofArgb2 = ObjectAnimator.ofArgb(linearLayout, "backgroundColor", newColor);
        ofArgb2.setDuration(400L);
        ofArgb2.start();
    }

    private final void updateRefCube(int refEdge) {
        String[] refNames = {"Задняя", "Штатив", "Передняя", "Pin"};
        Integer[] refIcons = {Integer.valueOf(R.drawable.ic_ref_rear), Integer.valueOf(R.drawable.ic_ref_tripod), Integer.valueOf(R.drawable.ic_ref_front), Integer.valueOf(R.drawable.ic_ref_pin)};
        int idx = RangesKt.coerceIn(refEdge, 0, 3);
        Log.d("REF_DEBUG", "Updating ref cube: refEdge=" + refEdge + " -> " + refNames[idx]);
        this.currentRefIndex = idx;
        ImageView imageView = this.ivRefIcon;
        TextView textView = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivRefIcon");
            imageView = null;
        }
        imageView.setImageResource(refIcons[idx].intValue());
        TextView textView2 = this.tvRefLabel;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvRefLabel");
        } else {
            textView = textView2;
        }
        textView.setText(refNames[idx]);
    }

    private final void updateTypeIcon(int devMode) {
        TypeInfo typeInfo = getTypeInfo(devMode);
        Log.d("TYPE_DEBUG", "Updating type cube: devMode=" + devMode + " -> " + typeInfo.getName());
        this.currentTypeIndex = typeInfo.getIndex();
        ImageView imageView = this.ivTypeIcon;
        TextView textView = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivTypeIcon");
            imageView = null;
        }
        imageView.setImageResource(typeInfo.getIconRes());
        TextView textView2 = this.tvTypeLabel;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvTypeLabel");
        } else {
            textView = textView2;
        }
        textView.setText(typeInfo.getName());
    }

    /* compiled from: MeasurementListActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/glmreader/android/ui/MeasurementListActivity$TypeInfo;", "", "index", "", "name", "", "iconRes", "<init>", "(ILjava/lang/String;I)V", "getIndex", "()I", "getName", "()Ljava/lang/String;", "getIconRes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final /* data */ class TypeInfo {
        private final int iconRes;
        private final int index;
        private final String name;

        public static /* synthetic */ TypeInfo copy$default(TypeInfo typeInfo, int i, String str, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = typeInfo.index;
            }
            if ((i3 & 2) != 0) {
                str = typeInfo.name;
            }
            if ((i3 & 4) != 0) {
                i2 = typeInfo.iconRes;
            }
            return typeInfo.copy(i, str, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component3, reason: from getter */
        public final int getIconRes() {
            return this.iconRes;
        }

        public final TypeInfo copy(int index, String name, int iconRes) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new TypeInfo(index, name, iconRes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeInfo)) {
                return false;
            }
            TypeInfo typeInfo = (TypeInfo) other;
            return this.index == typeInfo.index && Intrinsics.areEqual(this.name, typeInfo.name) && this.iconRes == typeInfo.iconRes;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.index) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.iconRes);
        }

        public String toString() {
            return "TypeInfo(index=" + this.index + ", name=" + this.name + ", iconRes=" + this.iconRes + ")";
        }

        public TypeInfo(int index, String name, int iconRes) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.index = index;
            this.name = name;
            this.iconRes = iconRes;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final int getIndex() {
            return this.index;
        }

        public final String getName() {
            return this.name;
        }
    }

    private final TypeInfo getTypeInfo(int devMode) {
        switch (devMode) {
            case 1:
                return new TypeInfo(0, "Прямой", R.drawable.ic_distance);
            case 2:
                return new TypeInfo(1, "Непрерыв.", R.drawable.ic_continuous);
            case 3:
                return new TypeInfo(2, "Min/Max", R.drawable.ic_minmax);
            case 4:
                return new TypeInfo(3, "Сложить", R.drawable.ic_distance);
            case 5:
                return new TypeInfo(4, "Площадь", R.drawable.ic_area);
            case 6:
                return new TypeInfo(5, "Объём", R.drawable.ic_volume);
            case 10:
                return new TypeInfo(6, "Косв. выс.", R.drawable.ic_indirect_height);
            case 11:
                return new TypeInfo(7, "Косв. длина", R.drawable.ic_indirect_length);
            case 12:
                return new TypeInfo(8, "Двойная", R.drawable.ic_double_indirect);
            case 13:
                return new TypeInfo(9, "Част. площ.", R.drawable.ic_area);
            case 14:
                return new TypeInfo(10, "Разбивка", R.drawable.ic_stakeout);
            case 15:
                return new TypeInfo(11, "Угол", R.drawable.ic_angle);
            case 17:
                return new TypeInfo(12, "Трапеция", R.drawable.ic_trapezoid);
            case 24:
                return new TypeInfo(13, "Пифагор 3т", R.drawable.ic_indirect_height);
            case 60:
                return new TypeInfo(0, "Прямой", R.drawable.ic_distance);
            default:
                return new TypeInfo(0, "Неизвест.", R.drawable.ic_distance);
        }
    }
}
