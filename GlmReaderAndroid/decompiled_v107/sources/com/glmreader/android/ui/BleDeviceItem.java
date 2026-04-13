package com.glmreader.android.ui;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BleDeviceAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/glmreader/android/ui/BleDeviceItem;", "", "name", "", "mac", "isSaved", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "getMac", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BleDeviceItem {
    private final boolean isSaved;
    private final String mac;
    private final String name;

    public static /* synthetic */ BleDeviceItem copy$default(BleDeviceItem bleDeviceItem, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bleDeviceItem.name;
        }
        if ((i & 2) != 0) {
            str2 = bleDeviceItem.mac;
        }
        if ((i & 4) != 0) {
            z = bleDeviceItem.isSaved;
        }
        return bleDeviceItem.copy(str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSaved() {
        return this.isSaved;
    }

    public final BleDeviceItem copy(String name, String mac, boolean isSaved) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mac, "mac");
        return new BleDeviceItem(name, mac, isSaved);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BleDeviceItem)) {
            return false;
        }
        BleDeviceItem bleDeviceItem = (BleDeviceItem) other;
        return Intrinsics.areEqual(this.name, bleDeviceItem.name) && Intrinsics.areEqual(this.mac, bleDeviceItem.mac) && this.isSaved == bleDeviceItem.isSaved;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.mac.hashCode()) * 31) + Boolean.hashCode(this.isSaved);
    }

    public String toString() {
        return "BleDeviceItem(name=" + this.name + ", mac=" + this.mac + ", isSaved=" + this.isSaved + ")";
    }

    public BleDeviceItem(String name, String mac, boolean isSaved) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(mac, "mac");
        this.name = name;
        this.mac = mac;
        this.isSaved = isSaved;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isSaved() {
        return this.isSaved;
    }
}
