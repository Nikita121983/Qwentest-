package com.glmreader.android.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProjectEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\nHÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\t\u0010\"\u001a\u00020\rHÆ\u0003JY\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010$\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001a¨\u0006("}, d2 = {"Lcom/glmreader/android/data/entity/ProjectEntity;", "", "id", "", "uuid", "", "name", "description", "sortOrder", "createdAt", "", "updatedAt", "isArchived", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJJZ)V", "getId", "()I", "getUuid", "()Ljava/lang/String;", "getName", "getDescription", "getSortOrder", "getCreatedAt", "()J", "getUpdatedAt", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ProjectEntity {
    private final long createdAt;
    private final String description;
    private final int id;
    private final boolean isArchived;
    private final String name;
    private final int sortOrder;
    private final long updatedAt;
    private final String uuid;

    public ProjectEntity() {
        this(0, null, null, null, 0, 0L, 0L, false, 255, null);
    }

    public static /* synthetic */ ProjectEntity copy$default(ProjectEntity projectEntity, int i, String str, String str2, String str3, int i2, long j, long j2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = projectEntity.id;
        }
        if ((i3 & 2) != 0) {
            str = projectEntity.uuid;
        }
        if ((i3 & 4) != 0) {
            str2 = projectEntity.name;
        }
        if ((i3 & 8) != 0) {
            str3 = projectEntity.description;
        }
        if ((i3 & 16) != 0) {
            i2 = projectEntity.sortOrder;
        }
        if ((i3 & 32) != 0) {
            j = projectEntity.createdAt;
        }
        if ((i3 & 64) != 0) {
            j2 = projectEntity.updatedAt;
        }
        if ((i3 & 128) != 0) {
            z = projectEntity.isArchived;
        }
        boolean z2 = z;
        long j3 = j2;
        long j4 = j;
        int i4 = i2;
        String str4 = str2;
        return projectEntity.copy(i, str, str4, str3, i4, j4, j3, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component5, reason: from getter */
    public final int getSortOrder() {
        return this.sortOrder;
    }

    /* renamed from: component6, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component7, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getIsArchived() {
        return this.isArchived;
    }

    public final ProjectEntity copy(int id, String uuid, String name, String description, int sortOrder, long createdAt, long updatedAt, boolean isArchived) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        return new ProjectEntity(id, uuid, name, description, sortOrder, createdAt, updatedAt, isArchived);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProjectEntity)) {
            return false;
        }
        ProjectEntity projectEntity = (ProjectEntity) other;
        return this.id == projectEntity.id && Intrinsics.areEqual(this.uuid, projectEntity.uuid) && Intrinsics.areEqual(this.name, projectEntity.name) && Intrinsics.areEqual(this.description, projectEntity.description) && this.sortOrder == projectEntity.sortOrder && this.createdAt == projectEntity.createdAt && this.updatedAt == projectEntity.updatedAt && this.isArchived == projectEntity.isArchived;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.id) * 31) + this.uuid.hashCode()) * 31) + this.name.hashCode()) * 31) + this.description.hashCode()) * 31) + Integer.hashCode(this.sortOrder)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt)) * 31) + Boolean.hashCode(this.isArchived);
    }

    public String toString() {
        return "ProjectEntity(id=" + this.id + ", uuid=" + this.uuid + ", name=" + this.name + ", description=" + this.description + ", sortOrder=" + this.sortOrder + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", isArchived=" + this.isArchived + ")";
    }

    public ProjectEntity(int id, String uuid, String name, String description, int sortOrder, long createdAt, long updatedAt, boolean isArchived) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isArchived = isArchived;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ProjectEntity(int r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, int r6, long r7, long r9, boolean r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r1 = this;
            r13 = r12 & 1
            r0 = 0
            if (r13 == 0) goto L6
            r2 = r0
        L6:
            r13 = r12 & 2
            if (r13 == 0) goto L17
            java.util.UUID r3 = java.util.UUID.randomUUID()
            java.lang.String r3 = r3.toString()
            java.lang.String r13 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r13)
        L17:
            r13 = r12 & 4
            if (r13 == 0) goto L1d
            java.lang.String r4 = "Новый проект"
        L1d:
            r13 = r12 & 8
            if (r13 == 0) goto L23
            java.lang.String r5 = ""
        L23:
            r13 = r12 & 16
            if (r13 == 0) goto L28
            r6 = r0
        L28:
            r13 = r12 & 32
            if (r13 == 0) goto L30
            long r7 = java.lang.System.currentTimeMillis()
        L30:
            r13 = r12 & 64
            if (r13 == 0) goto L38
            long r9 = java.lang.System.currentTimeMillis()
        L38:
            r12 = r12 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L3e
            r12 = r0
            goto L3f
        L3e:
            r12 = r11
        L3f:
            r10 = r9
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r10, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glmreader.android.data.entity.ProjectEntity.<init>(int, java.lang.String, java.lang.String, java.lang.String, int, long, long, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getId() {
        return this.id;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public final boolean isArchived() {
        return this.isArchived;
    }
}
