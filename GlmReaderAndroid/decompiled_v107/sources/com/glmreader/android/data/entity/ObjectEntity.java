package com.glmreader.android.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/glmreader/android/data/entity/ObjectEntity;", "", "id", "", "uuid", "", "groupId", "name", "sortOrder", "createdAt", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJ)V", "getId", "()I", "getUuid", "()Ljava/lang/String;", "getGroupId", "getName", "getSortOrder", "getCreatedAt", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ObjectEntity {
    private final long createdAt;
    private final String groupId;
    private final int id;
    private final String name;
    private final int sortOrder;
    private final String uuid;

    public static /* synthetic */ ObjectEntity copy$default(ObjectEntity objectEntity, int i, String str, String str2, String str3, int i2, long j, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = objectEntity.id;
        }
        if ((i3 & 2) != 0) {
            str = objectEntity.uuid;
        }
        if ((i3 & 4) != 0) {
            str2 = objectEntity.groupId;
        }
        if ((i3 & 8) != 0) {
            str3 = objectEntity.name;
        }
        if ((i3 & 16) != 0) {
            i2 = objectEntity.sortOrder;
        }
        if ((i3 & 32) != 0) {
            j = objectEntity.createdAt;
        }
        long j2 = j;
        int i4 = i2;
        String str4 = str2;
        return objectEntity.copy(i, str, str4, str3, i4, j2);
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
    public final String getGroupId() {
        return this.groupId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final int getSortOrder() {
        return this.sortOrder;
    }

    /* renamed from: component6, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final ObjectEntity copy(int id, String uuid, String groupId, String name, int sortOrder, long createdAt) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(groupId, "groupId");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ObjectEntity(id, uuid, groupId, name, sortOrder, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObjectEntity)) {
            return false;
        }
        ObjectEntity objectEntity = (ObjectEntity) other;
        return this.id == objectEntity.id && Intrinsics.areEqual(this.uuid, objectEntity.uuid) && Intrinsics.areEqual(this.groupId, objectEntity.groupId) && Intrinsics.areEqual(this.name, objectEntity.name) && this.sortOrder == objectEntity.sortOrder && this.createdAt == objectEntity.createdAt;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.id) * 31) + this.uuid.hashCode()) * 31) + this.groupId.hashCode()) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.sortOrder)) * 31) + Long.hashCode(this.createdAt);
    }

    public String toString() {
        return "ObjectEntity(id=" + this.id + ", uuid=" + this.uuid + ", groupId=" + this.groupId + ", name=" + this.name + ", sortOrder=" + this.sortOrder + ", createdAt=" + this.createdAt + ")";
    }

    public ObjectEntity(int id, String uuid, String groupId, String name, int sortOrder, long createdAt) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(groupId, "groupId");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.uuid = uuid;
        this.groupId = groupId;
        this.name = name;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ObjectEntity(int r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, int r6, long r7, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r1 = this;
            r10 = r9 & 1
            r0 = 0
            if (r10 == 0) goto L6
            r2 = r0
        L6:
            r10 = r9 & 2
            if (r10 == 0) goto L17
            java.util.UUID r3 = java.util.UUID.randomUUID()
            java.lang.String r3 = r3.toString()
            java.lang.String r10 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
        L17:
            r10 = r9 & 8
            if (r10 == 0) goto L1d
            java.lang.String r5 = "Новый объект"
        L1d:
            r10 = r9 & 16
            if (r10 == 0) goto L22
            r6 = r0
        L22:
            r9 = r9 & 32
            if (r9 == 0) goto L2c
            long r7 = java.lang.System.currentTimeMillis()
            r8 = r7
            goto L2d
        L2c:
            r8 = r7
        L2d:
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glmreader.android.data.entity.ObjectEntity.<init>(int, java.lang.String, java.lang.String, java.lang.String, int, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getId() {
        return this.id;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final String getName() {
        return this.name;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
