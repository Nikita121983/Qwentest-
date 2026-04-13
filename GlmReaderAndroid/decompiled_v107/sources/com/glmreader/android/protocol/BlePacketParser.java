package com.glmreader.android.protocol;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.glmreader.android.data.entity.MeasurementEntity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BlePacketParser.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016¨\u0006\u001a"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser;", "", "<init>", "()V", "parse", "Lcom/glmreader/android/protocol/BlePacketParser$ParsedMeasurement;", "rawBytes", "", "parseWithCrc", "calcCrc8", "", "data", "bytesToFloat", "", "bytes", TypedValues.CycleType.S_WAVE_OFFSET, "bytesToUShort", "formatResult", "", "value", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "ParsedMeasurement", "MeasurementType", "RefEdge", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class BlePacketParser {
    public static final BlePacketParser INSTANCE = new BlePacketParser();

    /* compiled from: BlePacketParser.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MeasurementType.values().length];
            try {
                iArr[MeasurementType.AREA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MeasurementType.VOLUME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BlePacketParser() {
    }

    /* compiled from: BlePacketParser.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J4\u0010'\u001a\u00020(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010,\u001a\u00020\u0012J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003J\t\u00102\u001a\u00020\nHÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\nHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010#J\t\u00109\u001a\u00020\u0012HÆ\u0003J\u0092\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010;J\u0013\u0010<\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020\u0003HÖ\u0001J\t\u0010?\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006@"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser$ParsedMeasurement;", "", "devMode", "", "refEdge", "resultValue", "", "comp1Value", "comp2Value", "laserOn", "", "tempWarning", "battLow", "isMetric", "deviceStatus", "seqNum", "measId", "rawHex", "", "<init>", "(IIDDDZZZZIILjava/lang/Integer;Ljava/lang/String;)V", "getDevMode", "()I", "getRefEdge", "getResultValue", "()D", "getComp1Value", "getComp2Value", "getLaserOn", "()Z", "getTempWarning", "getBattLow", "getDeviceStatus", "getSeqNum", "getMeasId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRawHex", "()Ljava/lang/String;", "toEntity", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "projectId", "groupId", "objectId", "deviceName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(IIDDDZZZZIILjava/lang/Integer;Ljava/lang/String;)Lcom/glmreader/android/protocol/BlePacketParser$ParsedMeasurement;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* data */ class ParsedMeasurement {
        private final boolean battLow;
        private final double comp1Value;
        private final double comp2Value;
        private final int devMode;
        private final int deviceStatus;
        private final boolean isMetric;
        private final boolean laserOn;
        private final Integer measId;
        private final String rawHex;
        private final int refEdge;
        private final double resultValue;
        private final int seqNum;
        private final boolean tempWarning;

        public static /* synthetic */ ParsedMeasurement copy$default(ParsedMeasurement parsedMeasurement, int i, int i2, double d, double d2, double d3, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4, Integer num, String str, int i5, Object obj) {
            int i6 = (i5 & 1) != 0 ? parsedMeasurement.devMode : i;
            return parsedMeasurement.copy(i6, (i5 & 2) != 0 ? parsedMeasurement.refEdge : i2, (i5 & 4) != 0 ? parsedMeasurement.resultValue : d, (i5 & 8) != 0 ? parsedMeasurement.comp1Value : d2, (i5 & 16) != 0 ? parsedMeasurement.comp2Value : d3, (i5 & 32) != 0 ? parsedMeasurement.laserOn : z, (i5 & 64) != 0 ? parsedMeasurement.tempWarning : z2, (i5 & 128) != 0 ? parsedMeasurement.battLow : z3, (i5 & 256) != 0 ? parsedMeasurement.isMetric : z4, (i5 & 512) != 0 ? parsedMeasurement.deviceStatus : i3, (i5 & 1024) != 0 ? parsedMeasurement.seqNum : i4, (i5 & 2048) != 0 ? parsedMeasurement.measId : num, (i5 & 4096) != 0 ? parsedMeasurement.rawHex : str);
        }

        /* renamed from: component1, reason: from getter */
        public final int getDevMode() {
            return this.devMode;
        }

        /* renamed from: component10, reason: from getter */
        public final int getDeviceStatus() {
            return this.deviceStatus;
        }

        /* renamed from: component11, reason: from getter */
        public final int getSeqNum() {
            return this.seqNum;
        }

        /* renamed from: component12, reason: from getter */
        public final Integer getMeasId() {
            return this.measId;
        }

        /* renamed from: component13, reason: from getter */
        public final String getRawHex() {
            return this.rawHex;
        }

        /* renamed from: component2, reason: from getter */
        public final int getRefEdge() {
            return this.refEdge;
        }

        /* renamed from: component3, reason: from getter */
        public final double getResultValue() {
            return this.resultValue;
        }

        /* renamed from: component4, reason: from getter */
        public final double getComp1Value() {
            return this.comp1Value;
        }

        /* renamed from: component5, reason: from getter */
        public final double getComp2Value() {
            return this.comp2Value;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getLaserOn() {
            return this.laserOn;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getTempWarning() {
            return this.tempWarning;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getBattLow() {
            return this.battLow;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getIsMetric() {
            return this.isMetric;
        }

        public final ParsedMeasurement copy(int devMode, int refEdge, double resultValue, double comp1Value, double comp2Value, boolean laserOn, boolean tempWarning, boolean battLow, boolean isMetric, int deviceStatus, int seqNum, Integer measId, String rawHex) {
            Intrinsics.checkNotNullParameter(rawHex, "rawHex");
            return new ParsedMeasurement(devMode, refEdge, resultValue, comp1Value, comp2Value, laserOn, tempWarning, battLow, isMetric, deviceStatus, seqNum, measId, rawHex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedMeasurement)) {
                return false;
            }
            ParsedMeasurement parsedMeasurement = (ParsedMeasurement) other;
            return this.devMode == parsedMeasurement.devMode && this.refEdge == parsedMeasurement.refEdge && Double.compare(this.resultValue, parsedMeasurement.resultValue) == 0 && Double.compare(this.comp1Value, parsedMeasurement.comp1Value) == 0 && Double.compare(this.comp2Value, parsedMeasurement.comp2Value) == 0 && this.laserOn == parsedMeasurement.laserOn && this.tempWarning == parsedMeasurement.tempWarning && this.battLow == parsedMeasurement.battLow && this.isMetric == parsedMeasurement.isMetric && this.deviceStatus == parsedMeasurement.deviceStatus && this.seqNum == parsedMeasurement.seqNum && Intrinsics.areEqual(this.measId, parsedMeasurement.measId) && Intrinsics.areEqual(this.rawHex, parsedMeasurement.rawHex);
        }

        public int hashCode() {
            return (((((((((((((((((((((((Integer.hashCode(this.devMode) * 31) + Integer.hashCode(this.refEdge)) * 31) + Double.hashCode(this.resultValue)) * 31) + Double.hashCode(this.comp1Value)) * 31) + Double.hashCode(this.comp2Value)) * 31) + Boolean.hashCode(this.laserOn)) * 31) + Boolean.hashCode(this.tempWarning)) * 31) + Boolean.hashCode(this.battLow)) * 31) + Boolean.hashCode(this.isMetric)) * 31) + Integer.hashCode(this.deviceStatus)) * 31) + Integer.hashCode(this.seqNum)) * 31) + (this.measId == null ? 0 : this.measId.hashCode())) * 31) + this.rawHex.hashCode();
        }

        public String toString() {
            return "ParsedMeasurement(devMode=" + this.devMode + ", refEdge=" + this.refEdge + ", resultValue=" + this.resultValue + ", comp1Value=" + this.comp1Value + ", comp2Value=" + this.comp2Value + ", laserOn=" + this.laserOn + ", tempWarning=" + this.tempWarning + ", battLow=" + this.battLow + ", isMetric=" + this.isMetric + ", deviceStatus=" + this.deviceStatus + ", seqNum=" + this.seqNum + ", measId=" + this.measId + ", rawHex=" + this.rawHex + ")";
        }

        public ParsedMeasurement(int devMode, int refEdge, double resultValue, double comp1Value, double comp2Value, boolean laserOn, boolean tempWarning, boolean battLow, boolean isMetric, int deviceStatus, int seqNum, Integer measId, String rawHex) {
            Intrinsics.checkNotNullParameter(rawHex, "rawHex");
            this.devMode = devMode;
            this.refEdge = refEdge;
            this.resultValue = resultValue;
            this.comp1Value = comp1Value;
            this.comp2Value = comp2Value;
            this.laserOn = laserOn;
            this.tempWarning = tempWarning;
            this.battLow = battLow;
            this.isMetric = isMetric;
            this.deviceStatus = deviceStatus;
            this.seqNum = seqNum;
            this.measId = measId;
            this.rawHex = rawHex;
        }

        public final int getDevMode() {
            return this.devMode;
        }

        public final int getRefEdge() {
            return this.refEdge;
        }

        public final double getResultValue() {
            return this.resultValue;
        }

        public final double getComp1Value() {
            return this.comp1Value;
        }

        public final double getComp2Value() {
            return this.comp2Value;
        }

        public final boolean getLaserOn() {
            return this.laserOn;
        }

        public final boolean getTempWarning() {
            return this.tempWarning;
        }

        public final boolean getBattLow() {
            return this.battLow;
        }

        public final boolean isMetric() {
            return this.isMetric;
        }

        public final int getDeviceStatus() {
            return this.deviceStatus;
        }

        public final int getSeqNum() {
            return this.seqNum;
        }

        public final Integer getMeasId() {
            return this.measId;
        }

        public final String getRawHex() {
            return this.rawHex;
        }

        public static /* synthetic */ MeasurementEntity toEntity$default(ParsedMeasurement parsedMeasurement, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            if ((i & 8) != 0) {
                str4 = "GLM 50 C";
            }
            return parsedMeasurement.toEntity(str, str2, str3, str4);
        }

        public final MeasurementEntity toEntity(String projectId, String groupId, String objectId, String deviceName) {
            Intrinsics.checkNotNullParameter(deviceName, "deviceName");
            int i = this.devMode;
            int i2 = this.refEdge;
            double d = this.resultValue;
            double d2 = this.comp1Value;
            double d3 = this.comp2Value;
            double d4 = this.comp2Value;
            boolean z = this.laserOn;
            int i3 = this.seqNum;
            return new MeasurementEntity(0, null, 0, i, i2, d, d2, d3, 0.0d, d4, z, 0L, 0L, 0L, false, projectId, groupId, objectId, deviceName, 0, this.rawHex, Integer.valueOf(i3), this.measId, Integer.valueOf(this.deviceStatus), null, false, null, null, 252213511, null);
        }
    }

    /* compiled from: BlePacketParser.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u001a"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "", "devMode", "", "displayName", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getDevMode", "()I", "getDisplayName", "()Ljava/lang/String;", "DIRECT", "CONTINUOUS", "MIN_MAX", "ADD_SUB", "AREA", "VOLUME", "INDIRECT_HEIGHT", "INDIRECT_LENGTH", "DOUBLE_INDIRECT", "PARTIAL_AREA", "STAKE_OUT", "ANGLE", "UNKNOWN", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public enum MeasurementType {
        DIRECT(1, "Измерение"),
        CONTINUOUS(2, "Непрерывный"),
        MIN_MAX(3, "Min/Max"),
        ADD_SUB(4, "Сложить/Вычесть"),
        AREA(5, "Площадь"),
        VOLUME(6, "Объём"),
        INDIRECT_HEIGHT(10, "Косвенная высота"),
        INDIRECT_LENGTH(11, "Косвенная длина"),
        DOUBLE_INDIRECT(12, "Двойная косвенная"),
        PARTIAL_AREA(13, "Частичная площадь"),
        STAKE_OUT(14, "Разбивка"),
        ANGLE(15, "Угол"),
        UNKNOWN(-1, "Неизвестный");

        private final int devMode;
        private final String displayName;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        public static EnumEntries<MeasurementType> getEntries() {
            return $ENTRIES;
        }

        MeasurementType(int devMode, String displayName) {
            this.devMode = devMode;
            this.displayName = displayName;
        }

        public final int getDevMode() {
            return this.devMode;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        /* compiled from: BlePacketParser.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType$Companion;", "", "<init>", "()V", "fromDevMode", "Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "devMode", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        /* loaded from: classes5.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final MeasurementType fromDevMode(int devMode) {
                MeasurementType measurementType;
                MeasurementType[] values = MeasurementType.values();
                int length = values.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        measurementType = null;
                        break;
                    }
                    measurementType = values[i];
                    if (measurementType.getDevMode() == devMode) {
                        break;
                    }
                    i++;
                }
                return measurementType == null ? MeasurementType.UNKNOWN : measurementType;
            }
        }
    }

    /* compiled from: BlePacketParser.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser$RefEdge;", "", "value", "", "displayName", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDisplayName", "()Ljava/lang/String;", "REAR", "TRIPOD", "FRONT", "PIN", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public enum RefEdge {
        REAR(0, "Задняя грань"),
        TRIPOD(1, "Ось штатива"),
        FRONT(2, "Передняя грань"),
        PIN(3, "Pin");

        private final String displayName;
        private final int value;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        public static EnumEntries<RefEdge> getEntries() {
            return $ENTRIES;
        }

        RefEdge(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }

        public final String getDisplayName() {
            return this.displayName;
        }

        public final int getValue() {
            return this.value;
        }

        /* compiled from: BlePacketParser.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/glmreader/android/protocol/BlePacketParser$RefEdge$Companion;", "", "<init>", "()V", "fromValue", "Lcom/glmreader/android/protocol/BlePacketParser$RefEdge;", "value", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
        /* loaded from: classes5.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final RefEdge fromValue(int value) {
                RefEdge refEdge;
                RefEdge[] values = RefEdge.values();
                int length = values.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        refEdge = null;
                        break;
                    }
                    refEdge = values[i];
                    if (refEdge.getValue() == value) {
                        break;
                    }
                    i++;
                }
                return refEdge == null ? RefEdge.REAR : refEdge;
            }
        }
    }

    public final ParsedMeasurement parse(byte[] rawBytes) {
        boolean laserOn;
        boolean tempWarning;
        boolean battLow;
        boolean isMetric;
        Integer measId;
        Intrinsics.checkNotNullParameter(rawBytes, "rawBytes");
        if (rawBytes.length < 11 || (rawBytes[0] & UByte.MAX_VALUE) != 192 || (rawBytes[1] & UByte.MAX_VALUE) != 85) {
            return null;
        }
        int payloadLen = rawBytes[2] & 255;
        if (payloadLen == 2) {
            return null;
        }
        int devModeRef = rawBytes[3] & UByte.MAX_VALUE;
        int refEdge = devModeRef & 3;
        int devMode = (devModeRef >> 2) & 63;
        int devStatus = rawBytes[4] & UByte.MAX_VALUE;
        if ((devStatus & 1) == 0) {
            laserOn = false;
        } else {
            laserOn = true;
        }
        if ((devStatus & 2) == 0) {
            tempWarning = false;
        } else {
            tempWarning = true;
        }
        if ((devStatus & 4) == 0) {
            battLow = false;
        } else {
            battLow = true;
        }
        if ((devStatus & 8) != 0) {
            isMetric = false;
        } else {
            isMetric = true;
        }
        int deviceStatus = (devStatus >> 4) & 15;
        int seqNum = rawBytes[5] & UByte.MAX_VALUE;
        float resultValue = bytesToFloat(rawBytes, 7);
        float comp1Value = bytesToFloat(rawBytes, 11);
        float comp2Value = bytesToFloat(rawBytes, 15);
        if (rawBytes.length >= 21) {
            measId = Integer.valueOf(bytesToUShort(rawBytes, 19));
        } else {
            measId = null;
        }
        String rawHex = ArraysKt.joinToString$default(rawBytes, (CharSequence) StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.glmreader.android.protocol.BlePacketParser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence parse$lambda$0;
                parse$lambda$0 = BlePacketParser.parse$lambda$0(((Byte) obj).byteValue());
                return parse$lambda$0;
            }
        }, 30, (Object) null);
        return new ParsedMeasurement(devMode, refEdge, resultValue, comp1Value, comp2Value, laserOn, tempWarning, battLow, isMetric, deviceStatus, seqNum, measId, rawHex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence parse$lambda$0(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final ParsedMeasurement parseWithCrc(byte[] rawBytes) {
        Intrinsics.checkNotNullParameter(rawBytes, "rawBytes");
        if (rawBytes.length < 20) {
            return null;
        }
        int crcIndex = rawBytes.length - 1;
        int expectedCrc = rawBytes[crcIndex] & UByte.MAX_VALUE;
        byte[] dataForCrc = ArraysKt.copyOfRange(rawBytes, 0, crcIndex);
        int calculatedCrc = calcCrc8(dataForCrc);
        if (calculatedCrc != expectedCrc) {
            Log.w("BlePacketParser", "CRC mismatch: expected=" + expectedCrc + ", got=" + calculatedCrc);
            return null;
        }
        return parse(ArraysKt.copyOfRange(rawBytes, 0, crcIndex));
    }

    public final int calcCrc8(byte[] data) {
        int i;
        Intrinsics.checkNotNullParameter(data, "data");
        int crc = 170;
        for (byte b : data) {
            for (int i2 = 0; i2 < 8; i2++) {
                boolean bit1 = (crc & 128) != 0;
                boolean bit2 = ((b >>> (7 - i2)) & 1) == 1;
                if (bit1 != bit2) {
                    i = (crc << 1) ^ 166;
                } else {
                    i = crc << 1;
                }
                crc = i & 255;
            }
        }
        return crc;
    }

    private final float bytesToFloat(byte[] bytes, int offset) {
        return ByteBuffer.wrap(bytes, offset, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    private final int bytesToUShort(byte[] bytes, int offset) {
        return ByteBuffer.wrap(bytes, offset, 2).order(ByteOrder.LITTLE_ENDIAN).getShort() & 65535;
    }

    public final String formatResult(double value, MeasurementType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                String format = String.format("%.2f м²", Arrays.copyOf(new Object[]{Double.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
            case 2:
                String format2 = String.format("%.2f м³", Arrays.copyOf(new Object[]{Double.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                return format2;
            default:
                String format3 = String.format("%.3f м", Arrays.copyOf(new Object[]{Double.valueOf(value)}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                return format3;
        }
    }
}
