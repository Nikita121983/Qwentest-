package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DefaultColWidthRecord extends StandardRecord {
    public static final int DEFAULT_COLUMN_WIDTH = 8;
    public static final short sid = 85;
    private int field_1_col_width;

    public DefaultColWidthRecord() {
        this.field_1_col_width = 8;
    }

    public DefaultColWidthRecord(DefaultColWidthRecord other) {
        super(other);
        this.field_1_col_width = other.field_1_col_width;
    }

    public DefaultColWidthRecord(RecordInputStream in) {
        this.field_1_col_width = in.readUShort();
    }

    public void setColWidth(int width) {
        this.field_1_col_width = width;
    }

    public int getColWidth() {
        return this.field_1_col_width;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getColWidth());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 85;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DefaultColWidthRecord copy() {
        return new DefaultColWidthRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DEFAULT_COL_WIDTH;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("colWidth", new Supplier() { // from class: org.apache.poi.hssf.record.DefaultColWidthRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DefaultColWidthRecord.this.getColWidth());
            }
        });
    }
}
