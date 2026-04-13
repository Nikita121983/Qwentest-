package com.glmreader.android.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.glmreader.android.R;
import com.glmreader.android.ble.GlmBleManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: BleDialogHelper.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/glmreader/android/ui/BleDialogHelper;", "", "<init>", "()V", "show", "", "context", "Landroid/content/Context;", "bleManager", "Lcom/glmreader/android/ble/GlmBleManager;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BleDialogHelper {
    public static final BleDialogHelper INSTANCE = new BleDialogHelper();

    private BleDialogHelper() {
    }

    /* JADX WARN: Type inference failed for: r0v24, types: [T, com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda5] */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, java.lang.String] */
    public final void show(final Context context, final GlmBleManager bleManager) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bleManager, "bleManager");
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_ble_connection, (ViewGroup) null);
        final TextView tvStatus = (TextView) view.findViewById(R.id.tvBleStatus);
        final Button btnToggle = (Button) view.findViewById(R.id.btnToggleBle);
        final Button btnScan = (Button) view.findViewById(R.id.btnScan);
        final Button btnConnect = (Button) view.findViewById(R.id.btnConnect);
        final TextView tvConnStatus = (TextView) view.findViewById(R.id.tvConnectionStatus);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recyclerDevices);
        final Button btnForget = (Button) view.findViewById(R.id.btnForget);
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(BluetoothManager.class);
        final BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        final SharedPreferences prefs = ((Activity) context).getSharedPreferences("ble_prefs", 0);
        final Ref.ObjectRef lastMac = new Ref.ObjectRef();
        lastMac.element = prefs.getString("last_device_mac", null);
        final Ref.ObjectRef lastName = new Ref.ObjectRef();
        lastName.element = prefs.getString("last_device_name", null);
        final List foundDevices = new ArrayList();
        final Ref.ObjectRef updateUi = new Ref.ObjectRef();
        updateUi.element = new Function0() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit show$lambda$0;
                show$lambda$0 = BleDialogHelper.show$lambda$0(Ref.ObjectRef.this, lastName, bluetoothAdapter, tvStatus, context, btnToggle, btnScan, bleManager, tvConnStatus, btnConnect, btnForget);
                return show$lambda$0;
            }
        };
        final BleDeviceAdapter deviceAdapter = new BleDeviceAdapter(foundDevices, new Function1() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit show$lambda$2;
                show$lambda$2 = BleDialogHelper.show$lambda$2(context, lastMac, lastName, prefs, tvConnStatus, updateUi, (BleDeviceItem) obj);
                return show$lambda$2;
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(deviceAdapter);
        Function1 connObserver = new Function1() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit show$lambda$4;
                show$lambda$4 = BleDialogHelper.show$lambda$4(context, updateUi, ((Boolean) obj).booleanValue());
                return show$lambda$4;
            }
        };
        bleManager.observeConnectionState(connObserver);
        Function2 deviceObserver = new Function2() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit show$lambda$7;
                show$lambda$7 = BleDialogHelper.show$lambda$7(context, foundDevices, deviceAdapter, updateUi, (String) obj, (String) obj2);
                return show$lambda$7;
            }
        };
        bleManager.observeDeviceFound(deviceObserver);
        btnToggle.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BleDialogHelper.show$lambda$8(context, view2);
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BleDialogHelper.show$lambda$9(GlmBleManager.this, foundDevices, lastMac, lastName, deviceAdapter, updateUi, view2);
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BleDialogHelper.show$lambda$10(GlmBleManager.this, context, lastMac, lastName, updateUi, view2);
            }
        });
        btnForget.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BleDialogHelper.show$lambda$11(GlmBleManager.this, prefs, lastMac, lastName, foundDevices, deviceAdapter, updateUi, context, view2);
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Connect to Ruler").setView(view).setNegativeButton("Close", new DialogInterface.OnClickListener() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                GlmBleManager.this.stopScan();
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(dialog, "create(...)");
        dialog.show();
        if (updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
            function0 = null;
        } else {
            function0 = (Function0) updateUi.element;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit show$lambda$0(Ref.ObjectRef $lastMac, Ref.ObjectRef $lastName, BluetoothAdapter $bluetoothAdapter, TextView $tvStatus, Context $context, Button $btnToggle, Button $btnScan, GlmBleManager $bleManager, TextView $tvConnStatus, Button $btnConnect, Button $btnForget) {
        String curMac = (String) $lastMac.element;
        String curName = (String) $lastName.element;
        boolean isBleOn = $bluetoothAdapter.isEnabled();
        $tvStatus.setText(isBleOn ? "Bluetooth ON" : "Bluetooth OFF");
        $tvStatus.setTextColor(ContextCompat.getColor($context, isBleOn ? R.color.status_success : R.color.status_error));
        $btnToggle.setText(isBleOn ? "Settings" : "Enable BT");
        $btnScan.setEnabled(isBleOn);
        if ($bleManager.isConnected()) {
            $tvConnStatus.setText("✅ Connected: " + $bleManager.getConnectedDeviceName());
            $tvConnStatus.setTextColor(ContextCompat.getColor($context, R.color.status_success));
            $btnConnect.setText("Disconnect");
            $btnConnect.setEnabled(true);
            $btnScan.setEnabled(false);
            $btnScan.setText("Stop");
            $btnForget.setEnabled(true);
        } else if ($bleManager.getIsScanning()) {
            $tvConnStatus.setText("Scanning...");
            $tvConnStatus.setTextColor(ContextCompat.getColor($context, R.color.status_info));
            $btnConnect.setText("Connect");
            $btnConnect.setEnabled(curMac != null);
            $btnScan.setText("Stop");
            $btnForget.setEnabled(false);
        } else {
            $tvConnStatus.setText(curMac != null ? "Saved: " + curName : "Not connected");
            $tvConnStatus.setTextColor(ContextCompat.getColor($context, R.color.text_secondary));
            $btnConnect.setText("Connect");
            $btnConnect.setEnabled(curMac != null);
            $btnScan.setText("Scan");
            $btnForget.setEnabled(curMac != null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$2(Context $context, final Ref.ObjectRef $lastMac, final Ref.ObjectRef $lastName, final SharedPreferences $prefs, final TextView $tvConnStatus, final Ref.ObjectRef $updateUi, final BleDeviceItem selected) {
        Intrinsics.checkNotNull($context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) $context).runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BleDialogHelper.show$lambda$2$lambda$1(BleDeviceItem.this, $lastMac, $lastName, $prefs, $tvConnStatus, $updateUi);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v6, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.lang.String] */
    public static final void show$lambda$2$lambda$1(BleDeviceItem $selected, Ref.ObjectRef $lastMac, Ref.ObjectRef $lastName, SharedPreferences $prefs, TextView $tvConnStatus, Ref.ObjectRef $updateUi) {
        Function0 function0;
        if ($selected != null) {
            $lastMac.element = $selected.getMac();
            $lastName.element = $selected.getName();
            $prefs.edit().putString("last_device_mac", $selected.getMac()).putString("last_device_name", $selected.getName()).apply();
            $tvConnStatus.setText("Selected: " + $selected.getName());
        }
        if ($updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
            function0 = null;
        } else {
            function0 = (Function0) $updateUi.element;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$4(Context $context, final Ref.ObjectRef $updateUi, boolean connected) {
        Intrinsics.checkNotNull($context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) $context).runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BleDialogHelper.show$lambda$4$lambda$3(Ref.ObjectRef.this);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$4$lambda$3(Ref.ObjectRef $updateUi) {
        Function0 function0;
        if ($updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
            function0 = null;
        } else {
            function0 = (Function0) $updateUi.element;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$7(Context $context, final List $foundDevices, final BleDeviceAdapter $deviceAdapter, final Ref.ObjectRef $updateUi, final String mac, final String name) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNull($context, "null cannot be cast to non-null type android.app.Activity");
        ((Activity) $context).runOnUiThread(new Runnable() { // from class: com.glmreader.android.ui.BleDialogHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BleDialogHelper.show$lambda$7$lambda$6($foundDevices, name, mac, $deviceAdapter, $updateUi);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$7$lambda$6(List $foundDevices, String $name, String $mac, BleDeviceAdapter $deviceAdapter, Ref.ObjectRef $updateUi) {
        Function0 function0;
        List list = $foundDevices;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (Intrinsics.areEqual(((BleDeviceItem) it.next()).getMac(), $mac)) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            $foundDevices.add(new BleDeviceItem($name, $mac, false));
            $deviceAdapter.notifyDataSetChanged();
            if ($updateUi.element == 0) {
                Intrinsics.throwUninitializedPropertyAccessException("updateUi");
                function0 = null;
            } else {
                function0 = (Function0) $updateUi.element;
            }
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$8(Context $context, View it) {
        ((Activity) $context).startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void show$lambda$9(GlmBleManager $bleManager, List $foundDevices, Ref.ObjectRef $lastMac, Ref.ObjectRef $lastName, BleDeviceAdapter $deviceAdapter, Ref.ObjectRef $updateUi, View it) {
        Function0 function0;
        if ($bleManager.getIsScanning()) {
            $bleManager.stopScan();
        } else {
            $foundDevices.clear();
            String curMac = (String) $lastMac.element;
            String curName = (String) $lastName.element;
            if (curMac != null && curName != null) {
                $foundDevices.add(new BleDeviceItem(curName, curMac, true));
            }
            $deviceAdapter.notifyDataSetChanged();
            $bleManager.startScan();
        }
        if ($updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
            function0 = null;
        } else {
            function0 = (Function0) $updateUi.element;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void show$lambda$10(GlmBleManager $bleManager, Context $context, Ref.ObjectRef $lastMac, Ref.ObjectRef $lastName, Ref.ObjectRef $updateUi, View it) {
        Function0 function0;
        if ($bleManager.isConnected()) {
            $bleManager.disconnect();
            $bleManager.stopAutoConnect();
            Toast.makeText($context, "Отключено. Автоподключение остановлено", 1).show();
        } else {
            String savedMac = (String) $lastMac.element;
            String savedName = (String) $lastName.element;
            if (savedMac != null) {
                $bleManager.connectAndAutoConnect(savedMac, savedName);
                Toast.makeText($context, "Подключение + автоподключение...", 1).show();
            }
        }
        if ($updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
            function0 = null;
        } else {
            function0 = (Function0) $updateUi.element;
        }
        function0.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$11(GlmBleManager $bleManager, SharedPreferences $prefs, Ref.ObjectRef $lastMac, Ref.ObjectRef $lastName, List $foundDevices, BleDeviceAdapter $deviceAdapter, Ref.ObjectRef $updateUi, Context $context, View it) {
        if ($bleManager.isConnected()) {
            $bleManager.disconnect();
        }
        $bleManager.stopAutoConnect();
        $prefs.edit().remove("last_device_mac").remove("last_device_name").apply();
        Function0 function0 = null;
        $lastMac.element = null;
        $lastName.element = null;
        $foundDevices.clear();
        $deviceAdapter.notifyDataSetChanged();
        if ($updateUi.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("updateUi");
        } else {
            function0 = (Function0) $updateUi.element;
        }
        function0.invoke();
        Toast.makeText($context, "Устройство забыто", 0).show();
    }
}
