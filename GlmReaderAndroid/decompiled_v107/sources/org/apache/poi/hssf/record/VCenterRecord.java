package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class VCenterRecord extends StandardRecord {
    public static final short sid = 132;
    private int field_1_vcenter;

    public VCenterRecord() {
    }

    public VCenterRecord(VCenterRecord other) {
        super(other);
        this.field_1_vcenter = other.field_1_vcenter;
    }

    public VCenterRecord(RecordInputStream in) {
        this.field_1_vcenter = in.readShort();
    }

    public void setVCenter(boolean z) {
        this.field_1_vcenter = z ? 1 : 0;
    }

    public boolean getVCenter() {
        return this.field_1_vcenter == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_vcenter);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 132;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public VCenterRecord copy() {
        return new VCenterRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.V_CENTER;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("vcenter", new Supplier() { // from class: org.apache.poi.hssf.record.VCenterRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(VCenterRecord.this.getVCenter());
            }
        });
    }
}
