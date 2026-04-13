package com.glmreader.android.data.entity;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MeasurementEntity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\bX\b\u0087\b\u0018\u00002\u00020\u0001B³\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010 \u001a\u00020\u0010\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b#\u0010$J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\nHÆ\u0003J\t\u0010N\u001a\u00020\nHÆ\u0003J\t\u0010O\u001a\u00020\nHÆ\u0003J\t\u0010P\u001a\u00020\nHÆ\u0003J\t\u0010Q\u001a\u00020\nHÆ\u0003J\t\u0010R\u001a\u00020\u0010HÆ\u0003J\t\u0010S\u001a\u00020\u0012HÆ\u0003J\t\u0010T\u001a\u00020\u0012HÆ\u0003J\t\u0010U\u001a\u00020\u0012HÆ\u0003J\t\u0010V\u001a\u00020\u0010HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010Z\u001a\u00020\u0005HÆ\u0003J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010a\u001a\u00020\u0010HÆ\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010EJ\u000b\u0010c\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jº\u0002\u0010d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00102\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010 \u001a\u00020\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010eJ\u0013\u0010f\u001a\u00020\u00102\b\u0010g\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010h\u001a\u00020\u0003HÖ\u0001J\t\u0010i\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b0\u0010-R\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b1\u0010-R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0011\u0010\u0014\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u0011\u0010\u0015\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u00103R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010(R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010(R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010(R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010(R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010&R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010(R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\b>\u0010?R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bA\u0010?R\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bB\u0010?R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010(R\u0011\u0010 \u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u00103R\u0015\u0010!\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010F\u001a\u0004\bD\u0010ER\u0013\u0010\"\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010(¨\u0006j"}, d2 = {"Lcom/glmreader/android/data/entity/MeasurementEntity;", "", "id", "", "uuid", "", "sortOrder", "measurementType", "refEdge", "resultValue", "", "comp1Value", "comp2Value", "comp3Value", "angleDeg", "laserOn", "", "timestamp", "", "createdAt", "updatedAt", "isDeleted", "projectId", "groupId", "objectId", "deviceName", "protocolVersion", "blePacketHex", "seqNum", "measId", "deviceStatus", "metadataJson", "isAutoDetected", "calculatedValue", "calculationType", "<init>", "(ILjava/lang/String;IIIDDDDDZJJJZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Double;Ljava/lang/String;)V", "getId", "()I", "getUuid", "()Ljava/lang/String;", "getSortOrder", "getMeasurementType", "getRefEdge", "getResultValue", "()D", "getComp1Value", "getComp2Value", "getComp3Value", "getAngleDeg", "getLaserOn", "()Z", "getTimestamp", "()J", "getCreatedAt", "getUpdatedAt", "getProjectId", "getGroupId", "getObjectId", "getDeviceName", "getProtocolVersion", "getBlePacketHex", "getSeqNum", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMeasId", "getDeviceStatus", "getMetadataJson", "getCalculatedValue", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getCalculationType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "copy", "(ILjava/lang/String;IIIDDDDDZJJJZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Double;Ljava/lang/String;)Lcom/glmreader/android/data/entity/MeasurementEntity;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MeasurementEntity {
    private final double angleDeg;
    private final String blePacketHex;
    private final Double calculatedValue;
    private final String calculationType;
    private final double comp1Value;
    private final double comp2Value;
    private final double comp3Value;
    private final long createdAt;
    private final String deviceName;
    private final Integer deviceStatus;
    private final String groupId;
    private final int id;
    private final boolean isAutoDetected;
    private final boolean isDeleted;
    private final boolean laserOn;
    private final Integer measId;
    private final int measurementType;
    private final String metadataJson;
    private final String objectId;
    private final String projectId;
    private final int protocolVersion;
    private final int refEdge;
    private final double resultValue;
    private final Integer seqNum;
    private final int sortOrder;
    private final long timestamp;
    private final long updatedAt;
    private final String uuid;

    public MeasurementEntity() {
        this(0, null, 0, 0, 0, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, false, 0L, 0L, 0L, false, null, null, null, null, 0, null, null, null, null, null, false, null, null, 268435455, null);
    }

    public static /* synthetic */ MeasurementEntity copy$default(MeasurementEntity measurementEntity, int i, String str, int i2, int i3, int i4, double d, double d2, double d3, double d4, double d5, boolean z, long j, long j2, long j3, boolean z2, String str2, String str3, String str4, String str5, int i5, String str6, Integer num, Integer num2, Integer num3, String str7, boolean z3, Double d6, String str8, int i6, Object obj) {
        String str9;
        Double d7;
        int i7 = (i6 & 1) != 0 ? measurementEntity.id : i;
        String str10 = (i6 & 2) != 0 ? measurementEntity.uuid : str;
        int i8 = (i6 & 4) != 0 ? measurementEntity.sortOrder : i2;
        int i9 = (i6 & 8) != 0 ? measurementEntity.measurementType : i3;
        int i10 = (i6 & 16) != 0 ? measurementEntity.refEdge : i4;
        double d8 = (i6 & 32) != 0 ? measurementEntity.resultValue : d;
        double d9 = (i6 & 64) != 0 ? measurementEntity.comp1Value : d2;
        double d10 = (i6 & 128) != 0 ? measurementEntity.comp2Value : d3;
        double d11 = (i6 & 256) != 0 ? measurementEntity.comp3Value : d4;
        int i11 = i7;
        String str11 = str10;
        double d12 = (i6 & 512) != 0 ? measurementEntity.angleDeg : d5;
        boolean z4 = (i6 & 1024) != 0 ? measurementEntity.laserOn : z;
        double d13 = d12;
        long j4 = (i6 & 2048) != 0 ? measurementEntity.timestamp : j;
        long j5 = (i6 & 4096) != 0 ? measurementEntity.createdAt : j2;
        long j6 = (i6 & 8192) != 0 ? measurementEntity.updatedAt : j3;
        boolean z5 = (i6 & 16384) != 0 ? measurementEntity.isDeleted : z2;
        String str12 = (32768 & i6) != 0 ? measurementEntity.projectId : str2;
        String str13 = (i6 & 65536) != 0 ? measurementEntity.groupId : str3;
        String str14 = (i6 & 131072) != 0 ? measurementEntity.objectId : str4;
        String str15 = (i6 & 262144) != 0 ? measurementEntity.deviceName : str5;
        int i12 = (i6 & 524288) != 0 ? measurementEntity.protocolVersion : i5;
        String str16 = (i6 & 1048576) != 0 ? measurementEntity.blePacketHex : str6;
        Integer num4 = (i6 & 2097152) != 0 ? measurementEntity.seqNum : num;
        Integer num5 = (i6 & 4194304) != 0 ? measurementEntity.measId : num2;
        Integer num6 = (i6 & 8388608) != 0 ? measurementEntity.deviceStatus : num3;
        String str17 = (i6 & 16777216) != 0 ? measurementEntity.metadataJson : str7;
        boolean z6 = (i6 & 33554432) != 0 ? measurementEntity.isAutoDetected : z3;
        Double d14 = (i6 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? measurementEntity.calculatedValue : d6;
        if ((i6 & 134217728) != 0) {
            d7 = d14;
            str9 = measurementEntity.calculationType;
        } else {
            str9 = str8;
            d7 = d14;
        }
        return measurementEntity.copy(i11, str11, i8, i9, i10, d8, d9, d10, d11, d13, z4, j4, j5, j6, z5, str12, str13, str14, str15, i12, str16, num4, num5, num6, str17, z6, d7, str9);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final double getAngleDeg() {
        return this.angleDeg;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getLaserOn() {
        return this.laserOn;
    }

    /* renamed from: component12, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component13, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component14, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component15, reason: from getter */
    public final boolean getIsDeleted() {
        return this.isDeleted;
    }

    /* renamed from: component16, reason: from getter */
    public final String getProjectId() {
        return this.projectId;
    }

    /* renamed from: component17, reason: from getter */
    public final String getGroupId() {
        return this.groupId;
    }

    /* renamed from: component18, reason: from getter */
    public final String getObjectId() {
        return this.objectId;
    }

    /* renamed from: component19, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    /* renamed from: component20, reason: from getter */
    public final int getProtocolVersion() {
        return this.protocolVersion;
    }

    /* renamed from: component21, reason: from getter */
    public final String getBlePacketHex() {
        return this.blePacketHex;
    }

    /* renamed from: component22, reason: from getter */
    public final Integer getSeqNum() {
        return this.seqNum;
    }

    /* renamed from: component23, reason: from getter */
    public final Integer getMeasId() {
        return this.measId;
    }

    /* renamed from: component24, reason: from getter */
    public final Integer getDeviceStatus() {
        return this.deviceStatus;
    }

    /* renamed from: component25, reason: from getter */
    public final String getMetadataJson() {
        return this.metadataJson;
    }

    /* renamed from: component26, reason: from getter */
    public final boolean getIsAutoDetected() {
        return this.isAutoDetected;
    }

    /* renamed from: component27, reason: from getter */
    public final Double getCalculatedValue() {
        return this.calculatedValue;
    }

    /* renamed from: component28, reason: from getter */
    public final String getCalculationType() {
        return this.calculationType;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSortOrder() {
        return this.sortOrder;
    }

    /* renamed from: component4, reason: from getter */
    public final int getMeasurementType() {
        return this.measurementType;
    }

    /* renamed from: component5, reason: from getter */
    public final int getRefEdge() {
        return this.refEdge;
    }

    /* renamed from: component6, reason: from getter */
    public final double getResultValue() {
        return this.resultValue;
    }

    /* renamed from: component7, reason: from getter */
    public final double getComp1Value() {
        return this.comp1Value;
    }

    /* renamed from: component8, reason: from getter */
    public final double getComp2Value() {
        return this.comp2Value;
    }

    /* renamed from: component9, reason: from getter */
    public final double getComp3Value() {
        return this.comp3Value;
    }

    public final MeasurementEntity copy(int id, String uuid, int sortOrder, int measurementType, int refEdge, double resultValue, double comp1Value, double comp2Value, double comp3Value, double angleDeg, boolean laserOn, long timestamp, long createdAt, long updatedAt, boolean isDeleted, String projectId, String groupId, String objectId, String deviceName, int protocolVersion, String blePacketHex, Integer seqNum, Integer measId, Integer deviceStatus, String metadataJson, boolean isAutoDetected, Double calculatedValue, String calculationType) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        return new MeasurementEntity(id, uuid, sortOrder, measurementType, refEdge, resultValue, comp1Value, comp2Value, comp3Value, angleDeg, laserOn, timestamp, createdAt, updatedAt, isDeleted, projectId, groupId, objectId, deviceName, protocolVersion, blePacketHex, seqNum, measId, deviceStatus, metadataJson, isAutoDetected, calculatedValue, calculationType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MeasurementEntity)) {
            return false;
        }
        MeasurementEntity measurementEntity = (MeasurementEntity) other;
        return this.id == measurementEntity.id && Intrinsics.areEqual(this.uuid, measurementEntity.uuid) && this.sortOrder == measurementEntity.sortOrder && this.measurementType == measurementEntity.measurementType && this.refEdge == measurementEntity.refEdge && Double.compare(this.resultValue, measurementEntity.resultValue) == 0 && Double.compare(this.comp1Value, measurementEntity.comp1Value) == 0 && Double.compare(this.comp2Value, measurementEntity.comp2Value) == 0 && Double.compare(this.comp3Value, measurementEntity.comp3Value) == 0 && Double.compare(this.angleDeg, measurementEntity.angleDeg) == 0 && this.laserOn == measurementEntity.laserOn && this.timestamp == measurementEntity.timestamp && this.createdAt == measurementEntity.createdAt && this.updatedAt == measurementEntity.updatedAt && this.isDeleted == measurementEntity.isDeleted && Intrinsics.areEqual(this.projectId, measurementEntity.projectId) && Intrinsics.areEqual(this.groupId, measurementEntity.groupId) && Intrinsics.areEqual(this.objectId, measurementEntity.objectId) && Intrinsics.areEqual(this.deviceName, measurementEntity.deviceName) && this.protocolVersion == measurementEntity.protocolVersion && Intrinsics.areEqual(this.blePacketHex, measurementEntity.blePacketHex) && Intrinsics.areEqual(this.seqNum, measurementEntity.seqNum) && Intrinsics.areEqual(this.measId, measurementEntity.measId) && Intrinsics.areEqual(this.deviceStatus, measurementEntity.deviceStatus) && Intrinsics.areEqual(this.metadataJson, measurementEntity.metadataJson) && this.isAutoDetected == measurementEntity.isAutoDetected && Intrinsics.areEqual((Object) this.calculatedValue, (Object) measurementEntity.calculatedValue) && Intrinsics.areEqual(this.calculationType, measurementEntity.calculationType);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((Integer.hashCode(this.id) * 31) + this.uuid.hashCode()) * 31) + Integer.hashCode(this.sortOrder)) * 31) + Integer.hashCode(this.measurementType)) * 31) + Integer.hashCode(this.refEdge)) * 31) + Double.hashCode(this.resultValue)) * 31) + Double.hashCode(this.comp1Value)) * 31) + Double.hashCode(this.comp2Value)) * 31) + Double.hashCode(this.comp3Value)) * 31) + Double.hashCode(this.angleDeg)) * 31) + Boolean.hashCode(this.laserOn)) * 31) + Long.hashCode(this.timestamp)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt)) * 31) + Boolean.hashCode(this.isDeleted)) * 31) + (this.projectId == null ? 0 : this.projectId.hashCode())) * 31) + (this.groupId == null ? 0 : this.groupId.hashCode())) * 31) + (this.objectId == null ? 0 : this.objectId.hashCode())) * 31) + this.deviceName.hashCode()) * 31) + Integer.hashCode(this.protocolVersion)) * 31) + (this.blePacketHex == null ? 0 : this.blePacketHex.hashCode())) * 31) + (this.seqNum == null ? 0 : this.seqNum.hashCode())) * 31) + (this.measId == null ? 0 : this.measId.hashCode())) * 31) + (this.deviceStatus == null ? 0 : this.deviceStatus.hashCode())) * 31) + (this.metadataJson == null ? 0 : this.metadataJson.hashCode())) * 31) + Boolean.hashCode(this.isAutoDetected)) * 31) + (this.calculatedValue == null ? 0 : this.calculatedValue.hashCode())) * 31) + (this.calculationType != null ? this.calculationType.hashCode() : 0);
    }

    public String toString() {
        return "MeasurementEntity(id=" + this.id + ", uuid=" + this.uuid + ", sortOrder=" + this.sortOrder + ", measurementType=" + this.measurementType + ", refEdge=" + this.refEdge + ", resultValue=" + this.resultValue + ", comp1Value=" + this.comp1Value + ", comp2Value=" + this.comp2Value + ", comp3Value=" + this.comp3Value + ", angleDeg=" + this.angleDeg + ", laserOn=" + this.laserOn + ", timestamp=" + this.timestamp + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", isDeleted=" + this.isDeleted + ", projectId=" + this.projectId + ", groupId=" + this.groupId + ", objectId=" + this.objectId + ", deviceName=" + this.deviceName + ", protocolVersion=" + this.protocolVersion + ", blePacketHex=" + this.blePacketHex + ", seqNum=" + this.seqNum + ", measId=" + this.measId + ", deviceStatus=" + this.deviceStatus + ", metadataJson=" + this.metadataJson + ", isAutoDetected=" + this.isAutoDetected + ", calculatedValue=" + this.calculatedValue + ", calculationType=" + this.calculationType + ")";
    }

    public MeasurementEntity(int id, String uuid, int sortOrder, int measurementType, int refEdge, double resultValue, double comp1Value, double comp2Value, double comp3Value, double angleDeg, boolean laserOn, long timestamp, long createdAt, long updatedAt, boolean isDeleted, String projectId, String groupId, String objectId, String deviceName, int protocolVersion, String blePacketHex, Integer seqNum, Integer measId, Integer deviceStatus, String metadataJson, boolean isAutoDetected, Double calculatedValue, String calculationType) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        this.id = id;
        this.uuid = uuid;
        this.sortOrder = sortOrder;
        this.measurementType = measurementType;
        this.refEdge = refEdge;
        this.resultValue = resultValue;
        this.comp1Value = comp1Value;
        this.comp2Value = comp2Value;
        this.comp3Value = comp3Value;
        this.angleDeg = angleDeg;
        this.laserOn = laserOn;
        this.timestamp = timestamp;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.projectId = projectId;
        this.groupId = groupId;
        this.objectId = objectId;
        this.deviceName = deviceName;
        this.protocolVersion = protocolVersion;
        this.blePacketHex = blePacketHex;
        this.seqNum = seqNum;
        this.measId = measId;
        this.deviceStatus = deviceStatus;
        this.metadataJson = metadataJson;
        this.isAutoDetected = isAutoDetected;
        this.calculatedValue = calculatedValue;
        this.calculationType = calculationType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ MeasurementEntity(int r39, java.lang.String r40, int r41, int r42, int r43, double r44, double r46, double r48, double r50, double r52, boolean r54, long r55, long r57, long r59, boolean r61, java.lang.String r62, java.lang.String r63, java.lang.String r64, java.lang.String r65, int r66, java.lang.String r67, java.lang.Integer r68, java.lang.Integer r69, java.lang.Integer r70, java.lang.String r71, boolean r72, java.lang.Double r73, java.lang.String r74, int r75, kotlin.jvm.internal.DefaultConstructorMarker r76) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glmreader.android.data.entity.MeasurementEntity.<init>(int, java.lang.String, int, int, int, double, double, double, double, double, boolean, long, long, long, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, boolean, java.lang.Double, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getId() {
        return this.id;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final int getMeasurementType() {
        return this.measurementType;
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

    public final double getComp3Value() {
        return this.comp3Value;
    }

    public final double getAngleDeg() {
        return this.angleDeg;
    }

    public final boolean getLaserOn() {
        return this.laserOn;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public final boolean isDeleted() {
        return this.isDeleted;
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final String getObjectId() {
        return this.objectId;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final int getProtocolVersion() {
        return this.protocolVersion;
    }

    public final String getBlePacketHex() {
        return this.blePacketHex;
    }

    public final Integer getSeqNum() {
        return this.seqNum;
    }

    public final Integer getMeasId() {
        return this.measId;
    }

    public final Integer getDeviceStatus() {
        return this.deviceStatus;
    }

    public final String getMetadataJson() {
        return this.metadataJson;
    }

    public final boolean isAutoDetected() {
        return this.isAutoDetected;
    }

    public final Double getCalculatedValue() {
        return this.calculatedValue;
    }

    public final String getCalculationType() {
        return this.calculationType;
    }
}
