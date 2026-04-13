package com.glmreader.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.glmreader.android.R;
import com.glmreader.android.data.entity.MeasurementEntity;
import com.glmreader.android.protocol.BlePacketParser;
import com.glmreader.android.protocol.InclinoLogic;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MeasurementAdapter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001(BG\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u001c\b\u0002\u0010\n\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\rJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\u0014\u0010\u0016\u001a\u00020\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\u0006\u0010\u0012\u001a\u00020\u0013J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u000bJ\u0006\u0010\u0019\u001a\u00020\tJ\u0006\u0010\u001a\u001a\u00020\tJ\u0006\u0010\u001b\u001a\u00020\tJ\u000e\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0013J\u0018\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0002H\u0002J\u0018\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020 H\u0016J\u0018\u0010&\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010'\u001a\u00020 H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/glmreader/android/ui/MeasurementAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/glmreader/android/ui/MeasurementAdapter$ViewHolder;", "measurements", "", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "onDelete", "Lkotlin/Function1;", "", "", "onSelectionChanged", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "selectionMode", "", "isSelectionMode", "", "getMeasurements", "", "updateData", "newData", "getSelectedUuids", "clearSelection", "selectAll", "deleteSelected", "toggleSelectionMode", "enabled", "toggleSelection", "position", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "getItemCount", "ViewHolder", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MeasurementAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final SimpleDateFormat dateFormat;
    private boolean isSelectionMode;
    private final List<MeasurementEntity> measurements;
    private final Function1<String, Unit> onDelete;
    private final Function1<Set<String>, Unit> onSelectionChanged;
    private final Set<String> selectionMode;

    public /* synthetic */ MeasurementAdapter(List list, Function1 function1, Function1 function12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, function1, (i & 4) != 0 ? null : function12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MeasurementAdapter(List<MeasurementEntity> measurements, Function1<? super String, Unit> onDelete, Function1<? super Set<String>, Unit> function1) {
        Intrinsics.checkNotNullParameter(measurements, "measurements");
        Intrinsics.checkNotNullParameter(onDelete, "onDelete");
        this.measurements = measurements;
        this.onDelete = onDelete;
        this.onSelectionChanged = function1;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", new Locale("ru"));
        this.selectionMode = new LinkedHashSet();
    }

    public final List<MeasurementEntity> getMeasurements() {
        return CollectionsKt.toList(this.measurements);
    }

    public final void updateData(List<MeasurementEntity> newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        this.measurements.clear();
        this.measurements.addAll(newData);
        notifyDataSetChanged();
    }

    /* renamed from: isSelectionMode, reason: from getter */
    public final boolean getIsSelectionMode() {
        return this.isSelectionMode;
    }

    public final Set<String> getSelectedUuids() {
        return CollectionsKt.toSet(this.selectionMode);
    }

    public final void clearSelection() {
        this.selectionMode.clear();
        notifyDataSetChanged();
        Function1<Set<String>, Unit> function1 = this.onSelectionChanged;
        if (function1 != null) {
            function1.invoke(SetsKt.emptySet());
        }
    }

    public final void selectAll() {
        this.selectionMode.clear();
        Set<String> set = this.selectionMode;
        Iterable iterable = this.measurements;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((MeasurementEntity) it.next()).getUuid());
        }
        set.addAll((List) arrayList);
        notifyDataSetChanged();
        Function1<Set<String>, Unit> function1 = this.onSelectionChanged;
        if (function1 != null) {
            function1.invoke(CollectionsKt.toSet(this.selectionMode));
        }
    }

    public final void deleteSelected() {
        Iterator it = this.selectionMode.iterator();
        while (it.hasNext()) {
            this.onDelete.invoke((String) it.next());
        }
        clearSelection();
    }

    public final void toggleSelectionMode(boolean enabled) {
        this.isSelectionMode = enabled;
        if (!enabled) {
            this.selectionMode.clear();
        }
        notifyDataSetChanged();
        Function1<Set<String>, Unit> function1 = this.onSelectionChanged;
        if (function1 != null) {
            function1.invoke(SetsKt.emptySet());
        }
    }

    private final void toggleSelection(int position, ViewHolder holder) {
        String uuid = this.measurements.get(position).getUuid();
        if (this.selectionMode.contains(uuid)) {
            this.selectionMode.remove(uuid);
        } else {
            this.selectionMode.add(uuid);
        }
        holder.getCbSelected().setChecked(this.selectionMode.contains(uuid));
        Function1<Set<String>, Unit> function1 = this.onSelectionChanged;
        if (function1 != null) {
            function1.invoke(CollectionsKt.toSet(this.selectionMode));
        }
    }

    /* compiled from: MeasurementAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/glmreader/android/ui/MeasurementAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "tvName", "Landroid/widget/TextView;", "getTvName", "()Landroid/widget/TextView;", "tvDateTime", "getTvDateTime", "ivTypeIcon", "Landroid/widget/ImageView;", "getIvTypeIcon", "()Landroid/widget/ImageView;", "tvValue", "getTvValue", "cbSelected", "Landroid/widget/CheckBox;", "getCbSelected", "()Landroid/widget/CheckBox;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cbSelected;
        private final ImageView ivTypeIcon;
        private final TextView tvDateTime;
        private final TextView tvName;
        private final TextView tvValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.tvName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.tvName = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.tvDateTime);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.tvDateTime = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.ivTypeIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.ivTypeIcon = (ImageView) findViewById3;
            View findViewById4 = view.findViewById(R.id.tvValue);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.tvValue = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.cbSelected);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.cbSelected = (CheckBox) findViewById5;
        }

        public final TextView getTvName() {
            return this.tvName;
        }

        public final TextView getTvDateTime() {
            return this.tvDateTime;
        }

        public final ImageView getIvTypeIcon() {
            return this.ivTypeIcon;
        }

        public final TextView getTvValue() {
            return this.tvValue;
        }

        public final CheckBox getCbSelected() {
            return this.cbSelected;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_measurement, parent, false);
        Intrinsics.checkNotNull(view);
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String format;
        int i;
        Intrinsics.checkNotNullParameter(holder, "holder");
        MeasurementEntity m = this.measurements.get(position);
        BlePacketParser.MeasurementType type = BlePacketParser.MeasurementType.INSTANCE.fromDevMode(m.getMeasurementType());
        InclinoLogic.determineType$default(InclinoLogic.INSTANCE, m.getAngleDeg(), m.getResultValue(), 0.0d, 4, null);
        holder.getTvName().setText(type.getDisplayName());
        holder.getTvDateTime().setText(this.dateFormat.format(new Date(m.getTimestamp())));
        TextView tvValue = holder.getTvValue();
        switch (m.getMeasurementType()) {
            case 4:
                format = String.format("%.2f м²", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                break;
            case 5:
            case 6:
            default:
                format = String.format("%.3f м", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                break;
            case 7:
                format = String.format("%.2f м³", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                break;
            case 8:
                format = m.getAngleDeg() + "°";
                break;
        }
        tvValue.setText(format);
        ImageView ivTypeIcon = holder.getIvTypeIcon();
        switch (m.getMeasurementType()) {
            case 1:
                i = R.drawable.ic_distance;
                break;
            case 2:
                i = R.drawable.ic_continuous;
                break;
            case 3:
            case 5:
            case 6:
            case 9:
            default:
                i = R.drawable.ic_distance;
                break;
            case 4:
                i = R.drawable.ic_area;
                break;
            case 7:
                i = R.drawable.ic_volume;
                break;
            case 8:
                i = R.drawable.ic_angle;
                break;
            case 10:
                i = R.drawable.ic_indirect_height;
                break;
            case 11:
                i = R.drawable.ic_indirect_length;
                break;
            case 12:
            case 13:
                i = R.drawable.ic_double_indirect;
                break;
        }
        ivTypeIcon.setImageResource(i);
        holder.getCbSelected().setVisibility(this.isSelectionMode ? 0 : 8);
        holder.getCbSelected().setChecked(this.selectionMode.contains(m.getUuid()));
        int selectionColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.selection_highlight);
        holder.itemView.setBackgroundColor(this.selectionMode.contains(m.getUuid()) ? selectionColor : 0);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.MeasurementAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeasurementAdapter.onBindViewHolder$lambda$2(MeasurementAdapter.this, position, holder, view);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.glmreader.android.ui.MeasurementAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean onBindViewHolder$lambda$3;
                onBindViewHolder$lambda$3 = MeasurementAdapter.onBindViewHolder$lambda$3(MeasurementAdapter.this, position, holder, view);
                return onBindViewHolder$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(MeasurementAdapter this$0, int $position, ViewHolder $holder, View it) {
        if (this$0.isSelectionMode) {
            this$0.toggleSelection($position, $holder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$3(MeasurementAdapter this$0, int $position, ViewHolder $holder, View it) {
        if (!this$0.isSelectionMode) {
            this$0.toggleSelectionMode(true);
            this$0.toggleSelection($position, $holder);
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.measurements.size();
    }
}
