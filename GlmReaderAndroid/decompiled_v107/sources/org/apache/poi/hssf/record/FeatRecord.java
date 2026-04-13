package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.common.FeatFormulaErr2;
import org.apache.poi.hssf.record.common.FeatProtection;
import org.apache.poi.hssf.record.common.FeatSmartTag;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.SharedFeature;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FeatRecord extends StandardRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) FeatRecord.class);
    public static final short sid = 2152;
    public static final short v11_sid = 2162;
    public static final short v12_sid = 2168;
    private long cbFeatData;
    private CellRangeAddress[] cellRefs;
    private final FtrHeader futureHeader;
    private int isf_sharedFeatureType;
    private byte reserved1;
    private long reserved2;
    private int reserved3;
    private SharedFeature sharedFeature;

    public FeatRecord() {
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType(sid);
    }

    public FeatRecord(FeatRecord other) {
        super(other);
        this.futureHeader = other.futureHeader.copy();
        this.isf_sharedFeatureType = other.isf_sharedFeatureType;
        this.reserved1 = other.reserved1;
        this.reserved2 = other.reserved2;
        this.cbFeatData = other.cbFeatData;
        this.reserved3 = other.reserved3;
        this.cellRefs = other.cellRefs == null ? null : (CellRangeAddress[]) Stream.of((Object[]) other.cellRefs).map(new FeatRecord$$ExternalSyntheticLambda8()).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return FeatRecord.lambda$new$0(i);
            }
        });
        this.sharedFeature = other.sharedFeature != null ? other.sharedFeature.copy() : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CellRangeAddress[] lambda$new$0(int x$0) {
        return new CellRangeAddress[x$0];
    }

    public FeatRecord(RecordInputStream in) {
        this.futureHeader = new FtrHeader(in);
        this.isf_sharedFeatureType = in.readShort();
        this.reserved1 = in.readByte();
        this.reserved2 = in.readInt();
        int cref = in.readUShort();
        this.cbFeatData = in.readInt();
        this.reserved3 = in.readShort();
        this.cellRefs = new CellRangeAddress[cref];
        for (int i = 0; i < this.cellRefs.length; i++) {
            this.cellRefs[i] = new CellRangeAddress(in);
        }
        int i2 = this.isf_sharedFeatureType;
        switch (i2) {
            case 2:
                this.sharedFeature = new FeatProtection(in);
                return;
            case 3:
                this.sharedFeature = new FeatFormulaErr2(in);
                return;
            case 4:
                this.sharedFeature = new FeatSmartTag(in);
                return;
            default:
                LOG.atError().log("Unknown Shared Feature {} found!", Unbox.box(this.isf_sharedFeatureType));
                return;
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this.futureHeader.serialize(out);
        out.writeShort(this.isf_sharedFeatureType);
        out.writeByte(this.reserved1);
        out.writeInt((int) this.reserved2);
        out.writeShort(this.cellRefs.length);
        out.writeInt((int) this.cbFeatData);
        out.writeShort(this.reserved3);
        for (CellRangeAddress cellRef : this.cellRefs) {
            cellRef.serialize(out);
        }
        if (this.sharedFeature != null) {
            this.sharedFeature.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.cellRefs.length * 8) + 27 + (this.sharedFeature == null ? 0 : this.sharedFeature.getDataSize());
    }

    public int getIsf_sharedFeatureType() {
        return this.isf_sharedFeatureType;
    }

    public long getCbFeatData() {
        return this.cbFeatData;
    }

    public void setCbFeatData(long cbFeatData) {
        this.cbFeatData = cbFeatData;
    }

    public CellRangeAddress[] getCellRefs() {
        return this.cellRefs;
    }

    public void setCellRefs(CellRangeAddress[] cellRefs) {
        this.cellRefs = cellRefs;
    }

    public SharedFeature getSharedFeature() {
        return this.sharedFeature;
    }

    public void setSharedFeature(SharedFeature feature) {
        this.sharedFeature = feature;
        if (feature instanceof FeatProtection) {
            this.isf_sharedFeatureType = 2;
        }
        if (feature instanceof FeatFormulaErr2) {
            this.isf_sharedFeatureType = 3;
        }
        if (feature instanceof FeatSmartTag) {
            this.isf_sharedFeatureType = 4;
        }
        if (this.isf_sharedFeatureType == 3) {
            this.cbFeatData = this.sharedFeature.getDataSize();
        } else {
            this.cbFeatData = 0L;
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FeatRecord copy() {
        return new FeatRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FEAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("futureHeader", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.m2302x8eb4e083();
            }
        }, "isf_sharedFeatureType", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FeatRecord.this.getIsf_sharedFeatureType());
            }
        }, "reserved1", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.m2303x57b5d7c4();
            }
        }, "reserved2", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.m2304x20b6cf05();
            }
        }, "cbFeatData", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Long.valueOf(FeatRecord.this.getCbFeatData());
            }
        }, "reserved3", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.m2305xe9b7c646();
            }
        }, "cellRefs", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.getCellRefs();
            }
        }, "sharedFeature", new Supplier() { // from class: org.apache.poi.hssf.record.FeatRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return FeatRecord.this.getSharedFeature();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FeatRecord, reason: not valid java name */
    public /* synthetic */ Object m2302x8eb4e083() {
        return this.futureHeader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FeatRecord, reason: not valid java name */
    public /* synthetic */ Object m2303x57b5d7c4() {
        return Byte.valueOf(this.reserved1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-FeatRecord, reason: not valid java name */
    public /* synthetic */ Object m2304x20b6cf05() {
        return Long.valueOf(this.reserved2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-FeatRecord, reason: not valid java name */
    public /* synthetic */ Object m2305xe9b7c646() {
        return Integer.valueOf(this.reserved3);
    }
}
