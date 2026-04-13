package com.glmreader.android.data.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsEntity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/glmreader/android/data/entity/SettingsEntity;", "", "key", "", "value", "updatedAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getKey", "()Ljava/lang/String;", "getValue", "getUpdatedAt", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SettingsEntity {
    public static final String KEY_EXPORT_HEADERS = "export_include_headers";
    public static final String KEY_INCLINO_MODE = "inclino_mode";
    public static final String KEY_INCLINO_THRESHOLD = "inclino_threshold";
    public static final String KEY_THEME_MODE = "theme_mode";
    private final String key;
    private final long updatedAt;
    private final String value;

    public static /* synthetic */ SettingsEntity copy$default(SettingsEntity settingsEntity, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = settingsEntity.key;
        }
        if ((i & 2) != 0) {
            str2 = settingsEntity.value;
        }
        if ((i & 4) != 0) {
            j = settingsEntity.updatedAt;
        }
        return settingsEntity.copy(str, str2, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* renamed from: component3, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public final SettingsEntity copy(String key, String value, long updatedAt) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return new SettingsEntity(key, value, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsEntity)) {
            return false;
        }
        SettingsEntity settingsEntity = (SettingsEntity) other;
        return Intrinsics.areEqual(this.key, settingsEntity.key) && Intrinsics.areEqual(this.value, settingsEntity.value) && this.updatedAt == settingsEntity.updatedAt;
    }

    public int hashCode() {
        return (((this.key.hashCode() * 31) + this.value.hashCode()) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "SettingsEntity(key=" + this.key + ", value=" + this.value + ", updatedAt=" + this.updatedAt + ")";
    }

    public SettingsEntity(String key, String value, long updatedAt) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.key = key;
        this.value = value;
        this.updatedAt = updatedAt;
    }

    public /* synthetic */ SettingsEntity(String str, String str2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? System.currentTimeMillis() : j);
    }

    public final String getKey() {
        return this.key;
    }

    public final String getValue() {
        return this.value;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
