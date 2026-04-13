package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NumberRecord extends CellRecord {
    public static final short sid = 515;
    private double field_4_value;

    public NumberRecord() {
    }

    public NumberRecord(NumberRecord other) {
        super(other);
        this.field_4_value = other.field_4_value;
    }

    public NumberRecord(RecordInputStream in) {
        super(in);
        this.field_4_value = in.readDouble();
    }

    public void setValue(double value) {
        this.field_4_value = value;
    }

    public double getValue() {
        return this.field_4_value;
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected String getRecordName() {
        return "NUMBER";
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected void serializeValue(LittleEndianOutput out) {
        out.writeDouble(getValue());
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected int getValueDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public NumberRecord copy() {
        return new NumberRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NUMBER;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.NumberRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return NumberRecord.this.m2343x2a9e659();
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.NumberRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(NumberRecord.this.getValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NumberRecord, reason: not valid java name */
    public /* synthetic */ Object m2343x2a9e659() {
        return super.getGenericProperties();
    }
}
