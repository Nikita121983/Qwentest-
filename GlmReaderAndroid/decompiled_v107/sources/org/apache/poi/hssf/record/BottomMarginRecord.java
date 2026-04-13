package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class BottomMarginRecord extends StandardRecord implements Margin {
    public static final short sid = 41;
    private double field_1_margin;

    public BottomMarginRecord() {
    }

    public BottomMarginRecord(BottomMarginRecord other) {
        super(other);
        this.field_1_margin = other.field_1_margin;
    }

    public BottomMarginRecord(RecordInputStream in) {
        this.field_1_margin = in.readDouble();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeDouble(this.field_1_margin);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 41;
    }

    @Override // org.apache.poi.hssf.record.Margin
    public double getMargin() {
        return this.field_1_margin;
    }

    @Override // org.apache.poi.hssf.record.Margin
    public void setMargin(double field_1_margin) {
        this.field_1_margin = field_1_margin;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BottomMarginRecord copy() {
        return new BottomMarginRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOTTOM_MARGIN;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("margin", new Supplier() { // from class: org.apache.poi.hssf.record.BottomMarginRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(BottomMarginRecord.this.getMargin());
            }
        });
    }
}
