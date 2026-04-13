package com.glmreader.android.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NoteEntity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J[\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013¨\u0006'"}, d2 = {"Lcom/glmreader/android/data/entity/NoteEntity;", "", "id", "", "measurementUuid", "", "version", "text", "createdAt", "", "isPrimary", "", "isVoice", "mediaPath", "<init>", "(ILjava/lang/String;ILjava/lang/String;JZZLjava/lang/String;)V", "getId", "()I", "getMeasurementUuid", "()Ljava/lang/String;", "getVersion", "getText", "getCreatedAt", "()J", "()Z", "getMediaPath", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class NoteEntity {
    private final long createdAt;
    private final int id;
    private final boolean isPrimary;
    private final boolean isVoice;
    private final String measurementUuid;
    private final String mediaPath;
    private final String text;
    private final int version;

    public static /* synthetic */ NoteEntity copy$default(NoteEntity noteEntity, int i, String str, int i2, String str2, long j, boolean z, boolean z2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = noteEntity.id;
        }
        if ((i3 & 2) != 0) {
            str = noteEntity.measurementUuid;
        }
        if ((i3 & 4) != 0) {
            i2 = noteEntity.version;
        }
        if ((i3 & 8) != 0) {
            str2 = noteEntity.text;
        }
        if ((i3 & 16) != 0) {
            j = noteEntity.createdAt;
        }
        if ((i3 & 32) != 0) {
            z = noteEntity.isPrimary;
        }
        if ((i3 & 64) != 0) {
            z2 = noteEntity.isVoice;
        }
        if ((i3 & 128) != 0) {
            str3 = noteEntity.mediaPath;
        }
        String str4 = str3;
        boolean z3 = z;
        long j2 = j;
        int i4 = i2;
        String str5 = str2;
        return noteEntity.copy(i, str, i4, str5, j2, z3, z2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMeasurementUuid() {
        return this.measurementUuid;
    }

    /* renamed from: component3, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* renamed from: component4, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component5, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsPrimary() {
        return this.isPrimary;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getIsVoice() {
        return this.isVoice;
    }

    /* renamed from: component8, reason: from getter */
    public final String getMediaPath() {
        return this.mediaPath;
    }

    public final NoteEntity copy(int id, String measurementUuid, int version, String text, long createdAt, boolean isPrimary, boolean isVoice, String mediaPath) {
        Intrinsics.checkNotNullParameter(measurementUuid, "measurementUuid");
        Intrinsics.checkNotNullParameter(text, "text");
        return new NoteEntity(id, measurementUuid, version, text, createdAt, isPrimary, isVoice, mediaPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NoteEntity)) {
            return false;
        }
        NoteEntity noteEntity = (NoteEntity) other;
        return this.id == noteEntity.id && Intrinsics.areEqual(this.measurementUuid, noteEntity.measurementUuid) && this.version == noteEntity.version && Intrinsics.areEqual(this.text, noteEntity.text) && this.createdAt == noteEntity.createdAt && this.isPrimary == noteEntity.isPrimary && this.isVoice == noteEntity.isVoice && Intrinsics.areEqual(this.mediaPath, noteEntity.mediaPath);
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.id) * 31) + this.measurementUuid.hashCode()) * 31) + Integer.hashCode(this.version)) * 31) + this.text.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Boolean.hashCode(this.isPrimary)) * 31) + Boolean.hashCode(this.isVoice)) * 31) + (this.mediaPath == null ? 0 : this.mediaPath.hashCode());
    }

    public String toString() {
        return "NoteEntity(id=" + this.id + ", measurementUuid=" + this.measurementUuid + ", version=" + this.version + ", text=" + this.text + ", createdAt=" + this.createdAt + ", isPrimary=" + this.isPrimary + ", isVoice=" + this.isVoice + ", mediaPath=" + this.mediaPath + ")";
    }

    public NoteEntity(int id, String measurementUuid, int version, String text, long createdAt, boolean isPrimary, boolean isVoice, String mediaPath) {
        Intrinsics.checkNotNullParameter(measurementUuid, "measurementUuid");
        Intrinsics.checkNotNullParameter(text, "text");
        this.id = id;
        this.measurementUuid = measurementUuid;
        this.version = version;
        this.text = text;
        this.createdAt = createdAt;
        this.isPrimary = isPrimary;
        this.isVoice = isVoice;
        this.mediaPath = mediaPath;
    }

    public /* synthetic */ NoteEntity(int i, String str, int i2, String str2, long j, boolean z, boolean z2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, str, (i3 & 4) != 0 ? 1 : i2, (i3 & 8) != 0 ? "" : str2, (i3 & 16) != 0 ? System.currentTimeMillis() : j, (i3 & 32) != 0 ? true : z, (i3 & 64) != 0 ? false : z2, (i3 & 128) != 0 ? null : str3);
    }

    public final int getId() {
        return this.id;
    }

    public final String getMeasurementUuid() {
        return this.measurementUuid;
    }

    public final int getVersion() {
        return this.version;
    }

    public final String getText() {
        return this.text;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final boolean isPrimary() {
        return this.isPrimary;
    }

    public final boolean isVoice() {
        return this.isVoice;
    }

    public final String getMediaPath() {
        return this.mediaPath;
    }
}
