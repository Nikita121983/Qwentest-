package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class LabelSSTRecord extends CellRecord {
    public static final short sid = 253;
    private int field_4_sst_index;

    public LabelSSTRecord() {
    }

    public LabelSSTRecord(LabelSSTRecord other) {
        super(other);
        this.field_4_sst_index = other.field_4_sst_index;
    }

    public LabelSSTRecord(RecordInputStream in) {
        super(in);
        this.field_4_sst_index = in.readInt();
    }

    public void setSSTIndex(int index) {
        this.field_4_sst_index = index;
    }

    public int getSSTIndex() {
        return this.field_4_sst_index;
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected String getRecordName() {
        return "LABELSST";
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected void serializeValue(LittleEndianOutput out) {
        out.writeInt(getSSTIndex());
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected int getValueDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public LabelSSTRecord copy() {
        return new LabelSSTRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL_SST;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.LabelSSTRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return LabelSSTRecord.this.m2318x84979890();
            }
        }, "sstIndex", new Supplier() { // from class: org.apache.poi.hssf.record.LabelSSTRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(LabelSSTRecord.this.getSSTIndex());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LabelSSTRecord, reason: not valid java name */
    public /* synthetic */ Object m2318x84979890() {
        return super.getGenericProperties();
    }
}
