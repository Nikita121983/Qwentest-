package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SCLRecord extends StandardRecord {
    public static final short sid = 160;
    private short field_1_numerator;
    private short field_2_denominator;

    public SCLRecord() {
    }

    public SCLRecord(SCLRecord other) {
        super(other);
        this.field_1_numerator = other.field_1_numerator;
        this.field_2_denominator = other.field_2_denominator;
    }

    public SCLRecord(RecordInputStream in) {
        this.field_1_numerator = in.readShort();
        this.field_2_denominator = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_numerator);
        out.writeShort(this.field_2_denominator);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 160;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SCLRecord copy() {
        return new SCLRecord(this);
    }

    public short getNumerator() {
        return this.field_1_numerator;
    }

    public void setNumerator(short field_1_numerator) {
        this.field_1_numerator = field_1_numerator;
    }

    public short getDenominator() {
        return this.field_2_denominator;
    }

    public void setDenominator(short field_2_denominator) {
        this.field_2_denominator = field_2_denominator;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SCL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numerator", new Supplier() { // from class: org.apache.poi.hssf.record.SCLRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(SCLRecord.this.getNumerator());
            }
        }, "denominator", new Supplier() { // from class: org.apache.poi.hssf.record.SCLRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(SCLRecord.this.getDenominator());
            }
        });
    }
}
