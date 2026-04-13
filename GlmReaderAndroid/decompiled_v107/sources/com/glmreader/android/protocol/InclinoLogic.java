package com.glmreader.android.protocol;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.glmreader.android.protocol.BlePacketParser;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InclinoLogic.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005J.\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J&\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/glmreader/android/protocol/InclinoLogic;", "", "<init>", "()V", "DEFAULT_THRESHOLD_DEG", "", "determineType", "Lcom/glmreader/android/protocol/InclinoLogic$InclineResult;", "angleDeg", "comp1Value", "threshold", "calculateDouble", "a1", "b1", "a2", "b2", "isHeight", "", "getMarkerIcon", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "getCalculationDescription", "calculationType", "Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;", "a", "angle", "result", "InclineResult", "CalculationType", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class InclinoLogic {
    public static final double DEFAULT_THRESHOLD_DEG = 5.0d;
    public static final InclinoLogic INSTANCE = new InclinoLogic();

    /* compiled from: InclinoLogic.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[BlePacketParser.MeasurementType.values().length];
            try {
                iArr[BlePacketParser.MeasurementType.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.INDIRECT_HEIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.INDIRECT_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.AREA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.VOLUME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CalculationType.values().length];
            try {
                iArr2[CalculationType.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[CalculationType.HEIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[CalculationType.WIDTH.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[CalculationType.DOUBLE_HEIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[CalculationType.DOUBLE_WIDTH.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private InclinoLogic() {
    }

    /* compiled from: InclinoLogic.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003JL\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006'"}, d2 = {"Lcom/glmreader/android/protocol/InclinoLogic$InclineResult;", "", "calculatedType", "Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "calculatedValue", "", "calculationType", "Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;", "isAutoDetected", "", "markerIcon", "", "displayName", "<init>", "(Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;Ljava/lang/Double;Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;ZLjava/lang/String;Ljava/lang/String;)V", "getCalculatedType", "()Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "getCalculatedValue", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getCalculationType", "()Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;", "()Z", "getMarkerIcon", "()Ljava/lang/String;", "getDisplayName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;Ljava/lang/Double;Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;ZLjava/lang/String;Ljava/lang/String;)Lcom/glmreader/android/protocol/InclinoLogic$InclineResult;", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* data */ class InclineResult {
        private final BlePacketParser.MeasurementType calculatedType;
        private final Double calculatedValue;
        private final CalculationType calculationType;
        private final String displayName;
        private final boolean isAutoDetected;
        private final String markerIcon;

        public static /* synthetic */ InclineResult copy$default(InclineResult inclineResult, BlePacketParser.MeasurementType measurementType, Double d, CalculationType calculationType, boolean z, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                measurementType = inclineResult.calculatedType;
            }
            if ((i & 2) != 0) {
                d = inclineResult.calculatedValue;
            }
            if ((i & 4) != 0) {
                calculationType = inclineResult.calculationType;
            }
            if ((i & 8) != 0) {
                z = inclineResult.isAutoDetected;
            }
            if ((i & 16) != 0) {
                str = inclineResult.markerIcon;
            }
            if ((i & 32) != 0) {
                str2 = inclineResult.displayName;
            }
            String str3 = str;
            String str4 = str2;
            return inclineResult.copy(measurementType, d, calculationType, z, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final BlePacketParser.MeasurementType getCalculatedType() {
            return this.calculatedType;
        }

        /* renamed from: component2, reason: from getter */
        public final Double getCalculatedValue() {
            return this.calculatedValue;
        }

        /* renamed from: component3, reason: from getter */
        public final CalculationType getCalculationType() {
            return this.calculationType;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsAutoDetected() {
            return this.isAutoDetected;
        }

        /* renamed from: component5, reason: from getter */
        public final String getMarkerIcon() {
            return this.markerIcon;
        }

        /* renamed from: component6, reason: from getter */
        public final String getDisplayName() {
            return this.displayName;
        }

        public final InclineResult copy(BlePacketParser.MeasurementType calculatedType, Double calculatedValue, CalculationType calculationType, boolean isAutoDetected, String markerIcon, String displayName) {
            Intrinsics.checkNotNullParameter(calculatedType, "calculatedType");
            Intrinsics.checkNotNullParameter(calculationType, "calculationType");
            Intrinsics.checkNotNullParameter(markerIcon, "markerIcon");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            return new InclineResult(calculatedType, calculatedValue, calculationType, isAutoDetected, markerIcon, displayName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InclineResult)) {
                return false;
            }
            InclineResult inclineResult = (InclineResult) other;
            return this.calculatedType == inclineResult.calculatedType && Intrinsics.areEqual((Object) this.calculatedValue, (Object) inclineResult.calculatedValue) && this.calculationType == inclineResult.calculationType && this.isAutoDetected == inclineResult.isAutoDetected && Intrinsics.areEqual(this.markerIcon, inclineResult.markerIcon) && Intrinsics.areEqual(this.displayName, inclineResult.displayName);
        }

        public int hashCode() {
            return (((((((((this.calculatedType.hashCode() * 31) + (this.calculatedValue == null ? 0 : this.calculatedValue.hashCode())) * 31) + this.calculationType.hashCode()) * 31) + Boolean.hashCode(this.isAutoDetected)) * 31) + this.markerIcon.hashCode()) * 31) + this.displayName.hashCode();
        }

        public String toString() {
            return "InclineResult(calculatedType=" + this.calculatedType + ", calculatedValue=" + this.calculatedValue + ", calculationType=" + this.calculationType + ", isAutoDetected=" + this.isAutoDetected + ", markerIcon=" + this.markerIcon + ", displayName=" + this.displayName + ")";
        }

        public InclineResult(BlePacketParser.MeasurementType calculatedType, Double calculatedValue, CalculationType calculationType, boolean isAutoDetected, String markerIcon, String displayName) {
            Intrinsics.checkNotNullParameter(calculatedType, "calculatedType");
            Intrinsics.checkNotNullParameter(calculationType, "calculationType");
            Intrinsics.checkNotNullParameter(markerIcon, "markerIcon");
            Intrinsics.checkNotNullParameter(displayName, "displayName");
            this.calculatedType = calculatedType;
            this.calculatedValue = calculatedValue;
            this.calculationType = calculationType;
            this.isAutoDetected = isAutoDetected;
            this.markerIcon = markerIcon;
            this.displayName = displayName;
        }

        public final BlePacketParser.MeasurementType getCalculatedType() {
            return this.calculatedType;
        }

        public final Double getCalculatedValue() {
            return this.calculatedValue;
        }

        public final CalculationType getCalculationType() {
            return this.calculationType;
        }

        public final boolean isAutoDetected() {
            return this.isAutoDetected;
        }

        public final String getMarkerIcon() {
            return this.markerIcon;
        }

        public final String getDisplayName() {
            return this.displayName;
        }
    }

    /* compiled from: InclinoLogic.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/glmreader/android/protocol/InclinoLogic$CalculationType;", "", "dbValue", "", "displayName", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDbValue", "()Ljava/lang/String;", "getDisplayName", "DIRECT", "HEIGHT", "WIDTH", "DOUBLE_HEIGHT", "DOUBLE_WIDTH", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public enum CalculationType {
        DIRECT("direct", "Прямой замер"),
        HEIGHT("height", "Косвенная высота"),
        WIDTH("width", "Косвенная ширина"),
        DOUBLE_HEIGHT("double_height", "Двойная высота"),
        DOUBLE_WIDTH("double_width", "Двойная ширина");

        private final String dbValue;
        private final String displayName;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<CalculationType> getEntries() {
            return $ENTRIES;
        }

        CalculationType(String dbValue, String displayName) {
            this.dbValue = dbValue;
            this.displayName = displayName;
        }

        public final String getDbValue() {
            return this.dbValue;
        }

        public final String getDisplayName() {
            return this.displayName;
        }
    }

    public static /* synthetic */ InclineResult determineType$default(InclinoLogic inclinoLogic, double d, double d2, double d3, int i, Object obj) {
        double d4;
        if ((i & 4) == 0) {
            d4 = d3;
        } else {
            d4 = 5.0d;
        }
        return inclinoLogic.determineType(d, d2, d4);
    }

    public final InclineResult determineType(double angleDeg, double comp1Value, double threshold) {
        double absAngle = Math.abs(angleDeg);
        if (absAngle <= threshold) {
            return new InclineResult(BlePacketParser.MeasurementType.DIRECT, null, CalculationType.DIRECT, true, "📏", "Прямой замер");
        }
        if (angleDeg > threshold) {
            double height = Math.sin(Math.toRadians(angleDeg)) * comp1Value;
            return new InclineResult(BlePacketParser.MeasurementType.INDIRECT_HEIGHT, Double.valueOf(height), CalculationType.HEIGHT, true, "📏️\u200d⬆️", "Косвенная высота");
        }
        double width = Math.cos(Math.toRadians(absAngle)) * comp1Value;
        return new InclineResult(BlePacketParser.MeasurementType.INDIRECT_LENGTH, Double.valueOf(width), CalculationType.WIDTH, true, "📏️\u200d➡️", "Косвенная ширина");
    }

    public final double calculateDouble(double a1, double b1, double a2, double b2, boolean isHeight) {
        if (isHeight) {
            return (Math.sin(Math.toRadians(b1)) * a1) + (Math.sin(Math.toRadians(b2)) * a2);
        }
        return (Math.cos(Math.toRadians(Math.abs(b1))) * a1) + (Math.cos(Math.toRadians(Math.abs(b2))) * a2);
    }

    public final String getMarkerIcon(BlePacketParser.MeasurementType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
            default:
                return "📏";
            case 2:
                return "📈";
            case 3:
                return "📉";
            case 4:
                return "🟦";
            case 5:
                return "🯊";
        }
    }

    public final String getCalculationDescription(CalculationType calculationType, double a, double angle, double result) {
        Intrinsics.checkNotNullParameter(calculationType, "calculationType");
        switch (WhenMappings.$EnumSwitchMapping$1[calculationType.ordinal()]) {
            case 1:
                String format = String.format("%.3f м", Arrays.copyOf(new Object[]{Double.valueOf(a)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
            case 2:
                String format2 = String.format("%.1f × sin(%.1f°) = %.3f м", Arrays.copyOf(new Object[]{Double.valueOf(a), Double.valueOf(angle), Double.valueOf(result)}, 3));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                return format2;
            case 3:
                String format3 = String.format("%.1f × cos(%.1f°) = %.3f м", Arrays.copyOf(new Object[]{Double.valueOf(a), Double.valueOf(Math.abs(angle)), Double.valueOf(result)}, 3));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                return format3;
            case 4:
                String format4 = String.format("a1×sin(b1) + a2×sin(b2) = %.3f м", Arrays.copyOf(new Object[]{Double.valueOf(result)}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
                return format4;
            case 5:
                String format5 = String.format("a1×cos(b1) + a2×cos(b2) = %.3f м", Arrays.copyOf(new Object[]{Double.valueOf(result)}, 1));
                Intrinsics.checkNotNullExpressionValue(format5, "format(...)");
                return format5;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
