package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FeatHdrRecord extends StandardRecord {
    public static final int SHAREDFEATURES_ISFFACTOID = 4;
    public static final int SHAREDFEATURES_ISFFEC2 = 3;
    public static final int SHAREDFEATURES_ISFLIST = 5;
    public static final int SHAREDFEATURES_ISFPROTECTION = 2;
    public static final short sid = 2151;
    private long cbHdrData;
    private final FtrHeader futureHeader;
    private int isf_sharedFeatureType;
    private byte reserved;
    private byte[] rgbHdrData;

    public FeatHdrRecord() {
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType(sid);
    }

    public FeatHdrRecord(FeatHdrRecord other) {
        super(other);
        this.futureHeader = other.futureHeader.copy();
        this.isf_sharedFeatureType = other.isf_sharedFeatureType;
        this.reserved = other.reserved;
        this.cbHdrData = other.cbHdrData;
        this.rgbHdrData = other.rgbHdrData == null ? null : (byte[]) other.rgbHdrData.clone();
    }

    public FeatHdrRecord(RecordInputStream in) {
        this.futureHeader = new FtrHeader(in);
        this.isf_sharedFeatureType = in.readShort();
        this.reserved = in.readByte();
        this.cbHdrData = in.readInt();
        this.rgbHdrData = in.readRemainder();
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this.futureHeader.serialize(out);
        out.writeShort(this.isf_sharedFeatureType);
        out.writeByte(this.reserved);
        out.writeInt((int) this.cbHdrData);
        out.write(this.rgbHdrData);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this.rgbHdrData.length + 19;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FeatHdrRecord copy() {
        return new FeatHdrRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FEAT_HDR;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("futureHeader", new Supplier() { // from class: org.apache.poi.hssf.record.FeatHdrRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatHdrRecord.this.m2297x9832ec56();
            }
        }, "isf_sharedFeatureType", new Supplier() { // from class: org.apache.poi.hssf.record.FeatHdrRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatHdrRecord.this.m2298x9f982175();
            }
        }, "reserved", new Supplier() { // from class: org.apache.poi.hssf.record.FeatHdrRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatHdrRecord.this.m2299xa6fd5694();
            }
        }, "cbHdrData", new Supplier() { // from class: org.apache.poi.hssf.record.FeatHdrRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatHdrRecord.this.m2300xae628bb3();
            }
        }, "rgbHdrData", new Supplier() { // from class: org.apache.poi.hssf.record.FeatHdrRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatHdrRecord.this.m2301xb5c7c0d2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FeatHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2297x9832ec56() {
        return this.futureHeader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FeatHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2298x9f982175() {
        return Integer.valueOf(this.isf_sharedFeatureType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FeatHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2299xa6fd5694() {
        return Byte.valueOf(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-FeatHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2300xae628bb3() {
        return Long.valueOf(this.cbHdrData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-FeatHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2301xb5c7c0d2() {
        return this.rgbHdrData;
    }
}
