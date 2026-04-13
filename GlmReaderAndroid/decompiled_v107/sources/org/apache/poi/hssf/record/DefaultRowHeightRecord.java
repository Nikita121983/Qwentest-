package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DefaultRowHeightRecord extends StandardRecord {
    public static final short DEFAULT_ROW_HEIGHT = 255;
    public static final short sid = 549;
    private short field_1_option_flags;
    private short field_2_row_height;

    public DefaultRowHeightRecord() {
        this.field_1_option_flags = (short) 0;
        this.field_2_row_height = (short) 255;
    }

    public DefaultRowHeightRecord(DefaultRowHeightRecord other) {
        super(other);
        this.field_1_option_flags = other.field_1_option_flags;
        this.field_2_row_height = other.field_2_row_height;
    }

    public DefaultRowHeightRecord(RecordInputStream in) {
        this.field_1_option_flags = in.readShort();
        this.field_2_row_height = in.readShort();
    }

    public void setOptionFlags(short flags) {
        this.field_1_option_flags = flags;
    }

    public void setRowHeight(short height) {
        this.field_2_row_height = height;
    }

    public short getOptionFlags() {
        return this.field_1_option_flags;
    }

    public short getRowHeight() {
        return this.field_2_row_height;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getOptionFlags());
        out.writeShort(getRowHeight());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DefaultRowHeightRecord copy() {
        return new DefaultRowHeightRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DEFAULT_ROW_HEIGHT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("optionFlags", new Supplier() { // from class: org.apache.poi.hssf.record.DefaultRowHeightRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DefaultRowHeightRecord.this.getOptionFlags());
            }
        }, "rowHeight", new Supplier() { // from class: org.apache.poi.hssf.record.DefaultRowHeightRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DefaultRowHeightRecord.this.getRowHeight());
            }
        });
    }
}
