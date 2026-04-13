package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SaveRecalcRecord extends StandardRecord {
    public static final short sid = 95;
    private short field_1_recalc;

    public SaveRecalcRecord() {
    }

    public SaveRecalcRecord(SaveRecalcRecord other) {
        super(other);
        this.field_1_recalc = other.field_1_recalc;
    }

    public SaveRecalcRecord(RecordInputStream in) {
        this.field_1_recalc = in.readShort();
    }

    public void setRecalc(boolean z) {
        this.field_1_recalc = z ? (short) 1 : (short) 0;
    }

    public boolean getRecalc() {
        return this.field_1_recalc == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_recalc);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 95;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SaveRecalcRecord copy() {
        return new SaveRecalcRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SAVE_RECALC;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recalc", new Supplier() { // from class: org.apache.poi.hssf.record.SaveRecalcRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(SaveRecalcRecord.this.getRecalc());
            }
        });
    }
}
