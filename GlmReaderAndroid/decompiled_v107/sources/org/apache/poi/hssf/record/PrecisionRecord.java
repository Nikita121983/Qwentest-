package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class PrecisionRecord extends StandardRecord {
    public static final short sid = 14;
    private short field_1_precision;

    public PrecisionRecord() {
    }

    public PrecisionRecord(PrecisionRecord other) {
        super(other);
        this.field_1_precision = other.field_1_precision;
    }

    public PrecisionRecord(RecordInputStream in) {
        this.field_1_precision = in.readShort();
    }

    public void setFullPrecision(boolean z) {
        this.field_1_precision = z ? (short) 1 : (short) 0;
    }

    public boolean getFullPrecision() {
        return this.field_1_precision == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_precision);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 14;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public PrecisionRecord copy() {
        return new PrecisionRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRECISION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("precision", new Supplier() { // from class: org.apache.poi.hssf.record.PrecisionRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(PrecisionRecord.this.getFullPrecision());
            }
        });
    }
}
