package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class GutsRecord extends StandardRecord {
    public static final short sid = 128;
    private short field_1_left_row_gutter;
    private short field_2_top_col_gutter;
    private short field_3_row_level_max;
    private short field_4_col_level_max;

    public GutsRecord() {
    }

    public GutsRecord(GutsRecord other) {
        super(other);
        this.field_1_left_row_gutter = other.field_1_left_row_gutter;
        this.field_2_top_col_gutter = other.field_2_top_col_gutter;
        this.field_3_row_level_max = other.field_3_row_level_max;
        this.field_4_col_level_max = other.field_4_col_level_max;
    }

    public GutsRecord(RecordInputStream in) {
        this.field_1_left_row_gutter = in.readShort();
        this.field_2_top_col_gutter = in.readShort();
        this.field_3_row_level_max = in.readShort();
        this.field_4_col_level_max = in.readShort();
    }

    public void setLeftRowGutter(short gut) {
        this.field_1_left_row_gutter = gut;
    }

    public void setTopColGutter(short gut) {
        this.field_2_top_col_gutter = gut;
    }

    public void setRowLevelMax(short max) {
        this.field_3_row_level_max = max;
    }

    public void setColLevelMax(short max) {
        this.field_4_col_level_max = max;
    }

    public short getLeftRowGutter() {
        return this.field_1_left_row_gutter;
    }

    public short getTopColGutter() {
        return this.field_2_top_col_gutter;
    }

    public short getRowLevelMax() {
        return this.field_3_row_level_max;
    }

    public short getColLevelMax() {
        return this.field_4_col_level_max;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getLeftRowGutter());
        out.writeShort(getTopColGutter());
        out.writeShort(getRowLevelMax());
        out.writeShort(getColLevelMax());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 128;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public GutsRecord copy() {
        return new GutsRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.GUTS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("leftGutter", new Supplier() { // from class: org.apache.poi.hssf.record.GutsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(GutsRecord.this.getLeftRowGutter());
            }
        }, "topGutter", new Supplier() { // from class: org.apache.poi.hssf.record.GutsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(GutsRecord.this.getTopColGutter());
            }
        }, "rowLevelMax", new Supplier() { // from class: org.apache.poi.hssf.record.GutsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(GutsRecord.this.getRowLevelMax());
            }
        }, "colLevelMax", new Supplier() { // from class: org.apache.poi.hssf.record.GutsRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(GutsRecord.this.getColLevelMax());
            }
        });
    }
}
