package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NumberFormatIndexRecord extends StandardRecord {
    public static final short sid = 4174;
    private short field_1_formatIndex;

    public NumberFormatIndexRecord() {
    }

    public NumberFormatIndexRecord(NumberFormatIndexRecord other) {
        super(other);
        this.field_1_formatIndex = other.field_1_formatIndex;
    }

    public NumberFormatIndexRecord(RecordInputStream in) {
        this.field_1_formatIndex = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_formatIndex);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public NumberFormatIndexRecord copy() {
        return new NumberFormatIndexRecord(this);
    }

    public short getFormatIndex() {
        return this.field_1_formatIndex;
    }

    public void setFormatIndex(short field_1_formatIndex) {
        this.field_1_formatIndex = field_1_formatIndex;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NUMBER_FORMAT_INDEX;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formatIndex", new Supplier() { // from class: org.apache.poi.hssf.record.chart.NumberFormatIndexRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(NumberFormatIndexRecord.this.getFormatIndex());
            }
        });
    }
}
