package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FontBasisRecord extends StandardRecord {
    public static final short sid = 4192;
    private short field_1_xBasis;
    private short field_2_yBasis;
    private short field_3_heightBasis;
    private short field_4_scale;
    private short field_5_indexToFontTable;

    public FontBasisRecord() {
    }

    public FontBasisRecord(FontBasisRecord other) {
        super(other);
        this.field_1_xBasis = other.field_1_xBasis;
        this.field_2_yBasis = other.field_2_yBasis;
        this.field_3_heightBasis = other.field_3_heightBasis;
        this.field_4_scale = other.field_4_scale;
        this.field_5_indexToFontTable = other.field_5_indexToFontTable;
    }

    public FontBasisRecord(RecordInputStream in) {
        this.field_1_xBasis = in.readShort();
        this.field_2_yBasis = in.readShort();
        this.field_3_heightBasis = in.readShort();
        this.field_4_scale = in.readShort();
        this.field_5_indexToFontTable = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_xBasis);
        out.writeShort(this.field_2_yBasis);
        out.writeShort(this.field_3_heightBasis);
        out.writeShort(this.field_4_scale);
        out.writeShort(this.field_5_indexToFontTable);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 10;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FontBasisRecord copy() {
        return new FontBasisRecord(this);
    }

    public short getXBasis() {
        return this.field_1_xBasis;
    }

    public void setXBasis(short field_1_xBasis) {
        this.field_1_xBasis = field_1_xBasis;
    }

    public short getYBasis() {
        return this.field_2_yBasis;
    }

    public void setYBasis(short field_2_yBasis) {
        this.field_2_yBasis = field_2_yBasis;
    }

    public short getHeightBasis() {
        return this.field_3_heightBasis;
    }

    public void setHeightBasis(short field_3_heightBasis) {
        this.field_3_heightBasis = field_3_heightBasis;
    }

    public short getScale() {
        return this.field_4_scale;
    }

    public void setScale(short field_4_scale) {
        this.field_4_scale = field_4_scale;
    }

    public short getIndexToFontTable() {
        return this.field_5_indexToFontTable;
    }

    public void setIndexToFontTable(short field_5_indexToFontTable) {
        this.field_5_indexToFontTable = field_5_indexToFontTable;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FONT_BASIS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("xBasis", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontBasisRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontBasisRecord.this.getXBasis());
            }
        }, "yBasis", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontBasisRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontBasisRecord.this.getYBasis());
            }
        }, "heightBasis", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontBasisRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontBasisRecord.this.getHeightBasis());
            }
        }, "scale", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontBasisRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontBasisRecord.this.getScale());
            }
        }, "indexToFontTable", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontBasisRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontBasisRecord.this.getIndexToFontTable());
            }
        });
    }
}
