package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CalcCountRecord extends StandardRecord {
    public static final short sid = 12;
    private short field_1_iterations;

    public CalcCountRecord() {
    }

    public CalcCountRecord(CalcCountRecord other) {
        super(other);
        this.field_1_iterations = other.field_1_iterations;
    }

    public CalcCountRecord(RecordInputStream in) {
        this.field_1_iterations = in.readShort();
    }

    public void setIterations(short iterations) {
        this.field_1_iterations = iterations;
    }

    public short getIterations() {
        return this.field_1_iterations;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getIterations());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 12;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CalcCountRecord copy() {
        return new CalcCountRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CALC_COUNT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("iterations", new Supplier() { // from class: org.apache.poi.hssf.record.CalcCountRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CalcCountRecord.this.getIterations());
            }
        });
    }
}
