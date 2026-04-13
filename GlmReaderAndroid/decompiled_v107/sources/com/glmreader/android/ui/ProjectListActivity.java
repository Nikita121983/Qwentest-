package com.glmreader.android.ui;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
import com.glmreader.android.data.entity.ProjectEntity;
import com.glmreader.android.ui.viewmodel.ProjectListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* compiled from: ProjectListActivity.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u0013H\u0002J\b\u0010(\u001a\u00020\u0013H\u0002J\b\u0010)\u001a\u00020\u0013H\u0014R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/glmreader/android/ui/ProjectListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "viewModel", "Lcom/glmreader/android/ui/viewmodel/ProjectListViewModel;", "getViewModel", "()Lcom/glmreader/android/ui/viewmodel/ProjectListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "tvEmpty", "Landroid/widget/TextView;", "adapter", "Lcom/glmreader/android/ui/ProjectAdapter;", "bleManager", "Lcom/glmreader/android/ble/GlmBleManager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showCreateProjectDialog", "openProject", "project", "Lcom/glmreader/android/data/entity/ProjectEntity;", "setupBleStatus", "checkBlePermissions", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "showBleDialog", "startAutoConnectIfSaved", "onDestroy", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProjectListActivity extends AppCompatActivity {
    private ProjectAdapter adapter;
    private GlmBleManager bleManager;
    private RecyclerView recyclerView;
    private final ActivityResultLauncher<String[]> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda3
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            ProjectListActivity.requestPermissionLauncher$lambda$9(ProjectListActivity.this, (Map) obj);
        }
    });
    private TextView tvEmpty;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public ProjectListActivity() {
        final ProjectListActivity projectListActivity = this;
        final Function0 function0 = null;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(ProjectListViewModel.class), new Function0<ViewModelStore>() { // from class: com.glmreader.android.ui.ProjectListActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.glmreader.android.ui.ProjectListActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.glmreader.android.ui.ProjectListActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? projectListActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ProjectListViewModel getViewModel() {
        return (ProjectListViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        View findViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Toolbar toolbar = (Toolbar) findViewById;
        setSupportActionBar(toolbar);
        PackageInfo pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        String verName = pkgInfo.versionName;
        if (verName == null) {
            verName = "?";
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Мои проекты v" + verName + PackagingURIHelper.FORWARD_SLASH_STRING + "2026-04-12");
        }
        View findViewById2 = findViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.recyclerView = (RecyclerView) findViewById2;
        View findViewById3 = findViewById(R.id.tvEmpty);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tvEmpty = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.fabAddProject);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        FloatingActionButton fabAddProject = (FloatingActionButton) findViewById4;
        View findViewById5 = findViewById(R.id.tvBuildInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        TextView tvBuildInfo = (TextView) findViewById5;
        tvBuildInfo.setText("Build: v" + verName + " / 2026-04-12");
        this.adapter = new ProjectAdapter(new ArrayList(), new Function1() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit onCreate$lambda$0;
                onCreate$lambda$0 = ProjectListActivity.onCreate$lambda$0(ProjectListActivity.this, (ProjectEntity) obj);
                return onCreate$lambda$0;
            }
        });
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        ProjectAdapter projectAdapter = this.adapter;
        if (projectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            projectAdapter = null;
        }
        recyclerView.setAdapter(projectAdapter);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new ProjectListActivity$onCreate$2(this, null), 3, null);
        fabAddProject.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectListActivity.this.showCreateProjectDialog();
            }
        });
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.glmreader.android.GlmReaderApplication");
        GlmReaderApplication app = (GlmReaderApplication) application;
        this.bleManager = app.getOrCreateBleManager(this);
        setupBleStatus();
        checkBlePermissions();
        startAutoConnectIfSaved();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0(ProjectListActivity this$0, ProjectEntity project) {
        Intrinsics.checkNotNullParameter(project, "project");
        this$0.openProject(project);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCreateProjectDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("Название проекта");
        new AlertDialog.Builder(this).setTitle("Новый проект").setView(editText).setPositiveButton("Создать", new DialogInterface.OnClickListener() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ProjectListActivity.showCreateProjectDialog$lambda$3(editText, this, dialogInterface, i);
            }
        }).setNegativeButton("Отмена", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCreateProjectDialog$lambda$3(EditText $editText, ProjectListActivity this$0, DialogInterface dialogInterface, int i) {
        String name = StringsKt.trim((CharSequence) $editText.getText().toString()).toString();
        if (name.length() > 0) {
            ProjectListViewModel.createProject$default(this$0.getViewModel(), name, null, 2, null);
        }
    }

    private final void openProject(ProjectEntity project) {
        Intent intent = new Intent(this, (Class<?>) MeasurementListActivity.class);
        intent.putExtra("project_uuid", project.getUuid());
        intent.putExtra("project_name", project.getName());
        startActivity(intent);
    }

    private final void setupBleStatus() {
        GlmBleManager glmBleManager = this.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.setOnConnectionStateChanged(new Function1() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit unit;
                unit = ProjectListActivity.setupBleStatus$lambda$6(ProjectListActivity.this, ((Boolean) obj).booleanValue());
                return unit;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setupBleStatus$lambda$6(final ProjectListActivity this$0, boolean connected) {
        this$0.runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.ProjectListActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                ProjectListActivity.this.invalidateOptionsMenu();
            }
        });
        return Unit.INSTANCE;
    }

    private final void checkBlePermissions() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$9(ProjectListActivity this$0, Map permissions) {
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
        if (itemId == R.id.action_settings) {
            startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final void showBleDialog() {
        BleDialogHelper bleDialogHelper = BleDialogHelper.INSTANCE;
        ProjectListActivity projectListActivity = this;
        GlmBleManager glmBleManager = this.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        bleDialogHelper.show(projectListActivity, glmBleManager);
    }

    private final void startAutoConnectIfSaved() {
        SharedPreferences prefs = getSharedPreferences("ble_prefs", 0);
        GlmBleManager glmBleManager = null;
        String savedMac = prefs.getString("last_device_mac", null);
        String savedName = prefs.getString("last_device_name", null);
        if (savedMac != null) {
            Log.d("ProjectList", "Auto-connecting to saved device: " + savedMac + " (" + savedName + ")");
            GlmBleManager glmBleManager2 = this.bleManager;
            if (glmBleManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            } else {
                glmBleManager = glmBleManager2;
            }
            glmBleManager.startAutoConnect(savedMac, savedName);
            return;
        }
        Log.d("ProjectList", "No saved device — auto-connect not started");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        GlmBleManager glmBleManager = this.bleManager;
        if (glmBleManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleManager");
            glmBleManager = null;
        }
        glmBleManager.stopAutoConnect();
    }
}
