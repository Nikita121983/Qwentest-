package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class HCenterRecord extends StandardRecord {
    public static final short sid = 131;
    private short field_1_hcenter;

    public HCenterRecord() {
    }

    public HCenterRecord(HCenterRecord other) {
        super(other);
        this.field_1_hcenter = other.field_1_hcenter;
    }

    public HCenterRecord(RecordInputStream in) {
        this.field_1_hcenter = in.readShort();
    }

    public void setHCenter(boolean z) {
        this.field_1_hcenter = z ? (short) 1 : (short) 0;
    }

    public boolean getHCenter() {
        return this.field_1_hcenter == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_hcenter);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 131;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public HCenterRecord copy() {
        return new HCenterRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.H_CENTER;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("hcenter", new Supplier() { // from class: org.apache.poi.hssf.record.HCenterRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(HCenterRecord.this.getHCenter());
            }
        });
    }
}
