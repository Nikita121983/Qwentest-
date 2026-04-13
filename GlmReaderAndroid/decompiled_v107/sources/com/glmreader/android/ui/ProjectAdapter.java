package com.glmreader.android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.glmreader.android.R;
import com.glmreader.android.data.entity.ProjectEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProjectAdapter.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rJ\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/glmreader/android/ui/ProjectAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/glmreader/android/ui/ProjectAdapter$ViewHolder;", "projects", "", "Lcom/glmreader/android/data/entity/ProjectEntity;", "onProjectClick", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "updateData", "newData", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "ViewHolder", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProjectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Function1<ProjectEntity, Unit> onProjectClick;
    private final List<ProjectEntity> projects;

    /* JADX WARN: Multi-variable type inference failed */
    public ProjectAdapter(List<ProjectEntity> projects, Function1<? super ProjectEntity, Unit> onProjectClick) {
        Intrinsics.checkNotNullParameter(projects, "projects");
        Intrinsics.checkNotNullParameter(onProjectClick, "onProjectClick");
        this.projects = projects;
        this.onProjectClick = onProjectClick;
    }

    /* compiled from: ProjectAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/glmreader/android/ui/ProjectAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "tvProjectName", "Landroid/widget/TextView;", "getTvProjectName", "()Landroid/widget/TextView;", "tvProjectDescription", "getTvProjectDescription", "tvMeasurementCount", "getTvMeasurementCount", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvMeasurementCount;
        private final TextView tvProjectDescription;
        private final TextView tvProjectName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.tvProjectName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.tvProjectName = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.tvProjectDescription);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.tvProjectDescription = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.tvMeasurementCount);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.tvMeasurementCount = (TextView) findViewById3;
        }

        public final TextView getTvProjectName() {
            return this.tvProjectName;
        }

        public final TextView getTvProjectDescription() {
            return this.tvProjectDescription;
        }

        public final TextView getTvMeasurementCount() {
            return this.tvMeasurementCount;
        }
    }

    public final void updateData(List<ProjectEntity> newData) {
        Intrinsics.checkNotNullParameter(newData, "newData");
        this.projects.clear();
        this.projects.addAll(newData);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        Intrinsics.checkNotNull(view);
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final ProjectEntity project = this.projects.get(position);
        holder.getTvProjectName().setText(project.getName());
        TextView tvProjectDescription = holder.getTvProjectDescription();
        String description = project.getDescription();
        if (description.length() == 0) {
            description = "Нет описания";
        }
        tvProjectDescription.setText(description);
        holder.getTvMeasurementCount().setText("Нажмите для открытия");
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.glmreader.android.ui.ProjectAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProjectAdapter.onBindViewHolder$lambda$1(ProjectAdapter.this, project, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(ProjectAdapter this$0, ProjectEntity $project, View it) {
        this$0.onProjectClick.invoke($project);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.projects.size();
    }
}
