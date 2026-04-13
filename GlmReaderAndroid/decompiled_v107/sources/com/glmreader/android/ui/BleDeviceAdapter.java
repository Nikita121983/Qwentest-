package com.glmreader.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.glmreader.android.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BleDeviceAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\fJ\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/glmreader/android/ui/BleDeviceAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/glmreader/android/ui/BleDeviceAdapter$ViewHolder;", "devices", "", "Lcom/glmreader/android/ui/BleDeviceItem;", "onDeviceSelected", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "selectedPosition", "", "value", "selectedDevice", "getSelectedDevice", "()Lcom/glmreader/android/ui/BleDeviceItem;", "setSelectedPosition", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "holder", "getItemCount", "ViewHolder", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BleDeviceAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<BleDeviceItem> devices;
    private final Function1<BleDeviceItem, Unit> onDeviceSelected;
    private BleDeviceItem selectedDevice;
    private int selectedPosition;

    /* JADX WARN: Multi-variable type inference failed */
    public BleDeviceAdapter(List<BleDeviceItem> devices, Function1<? super BleDeviceItem, Unit> onDeviceSelected) {
        Intrinsics.checkNotNullParameter(devices, "devices");
        Intrinsics.checkNotNullParameter(onDeviceSelected, "onDeviceSelected");
        this.devices = devices;
        this.onDeviceSelected = onDeviceSelected;
        this.selectedPosition = -1;
    }

    public final BleDeviceItem getSelectedDevice() {
        return this.selectedDevice;
    }

    /* compiled from: BleDeviceAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/glmreader/android/ui/BleDeviceAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "tvName", "Landroid/widget/TextView;", "getTvName", "()Landroid/widget/TextView;", "tvMac", "getTvMac", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMac;
        private final TextView tvName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.tvDeviceName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.tvName = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.tvDeviceMac);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.tvMac = (TextView) findViewById2;
        }

        public final TextView getTvName() {
            return this.tvName;
        }

        public final TextView getTvMac() {
            return this.tvMac;
        }
    }

    public final void setSelectedPosition(int position) {
        int oldPosition = this.selectedPosition;
        this.selectedPosition = position;
        this.selectedDevice = (position < 0 || position >= this.devices.size()) ? null : this.devices.get(position);
        notifyItemChanged(oldPosition);
        notifyItemChanged(this.selectedPosition);
        this.onDeviceSelected.invoke(this.selectedDevice);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ble_device, parent, false);
        Intrinsics.checkNotNull(view);
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BleDeviceItem device = this.devices.get(position);
        TextView tvName = holder.getTvName();
        String name = device.getName();
        if (name.length() == 0) {
            name = "Unknown Device";
        }
        tvName.setText(name);
        holder.getTvMac().setText(device.getMac());
        holder.itemView.setSelected(position == this.selectedPosition);
        int selectionColor = ContextCompat.getColor(holder.itemView.getContext(), position == this.selectedPosition ? R.color.selection_highlight : android.R.color.transparent);
        holder.itemView.setBackgroundColor(selectionColor);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.BleDeviceAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BleDeviceAdapter.this.setSelectedPosition(position);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.devices.size();
    }
}
