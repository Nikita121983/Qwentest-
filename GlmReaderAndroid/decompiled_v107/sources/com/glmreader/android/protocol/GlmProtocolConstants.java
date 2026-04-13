package com.glmreader.android.protocol;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlmProtocolConstants.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/glmreader/android/protocol/GlmProtocolConstants;", "", "<init>", "()V", "SERVICE_UUID", "Ljava/util/UUID;", "getSERVICE_UUID", "()Ljava/util/UUID;", "CHARACTERISTIC_UUID", "getCHARACTERISTIC_UUID", "CMD_INIT", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GlmProtocolConstants {
    private static final UUID CHARACTERISTIC_UUID;
    public static final String CMD_INIT = "C0 55 02 40 00";
    public static final GlmProtocolConstants INSTANCE = new GlmProtocolConstants();
    private static final UUID SERVICE_UUID;

    private GlmProtocolConstants() {
    }

    static {
        UUID fromString = UUID.fromString("02A6C0D0-0451-4000-B000-FB3210111989");
        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(...)");
        SERVICE_UUID = fromString;
        UUID fromString2 = UUID.fromString("02A6C0D1-0451-4000-B000-FB3210111989");
        Intrinsics.checkNotNullExpressionValue(fromString2, "fromString(...)");
        CHARACTERISTIC_UUID = fromString2;
    }

    public final UUID getSERVICE_UUID() {
        return SERVICE_UUID;
    }

    public final UUID getCHARACTERISTIC_UUID() {
        return CHARACTERISTIC_UUID;
    }
}
