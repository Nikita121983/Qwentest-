package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FontIndexRecord extends StandardRecord {
    public static final short sid = 4134;
    private short field_1_fontIndex;

    public FontIndexRecord() {
    }

    public FontIndexRecord(FontIndexRecord other) {
        super(other);
        this.field_1_fontIndex = other.field_1_fontIndex;
    }

    public FontIndexRecord(RecordInputStream in) {
        this.field_1_fontIndex = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_fontIndex);
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
    public FontIndexRecord copy() {
        return new FontIndexRecord(this);
    }

    public short getFontIndex() {
        return this.field_1_fontIndex;
    }

    public void setFontIndex(short field_1_fontIndex) {
        this.field_1_fontIndex = field_1_fontIndex;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FONT_INDEX;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("fontIdex", new Supplier() { // from class: org.apache.poi.hssf.record.chart.FontIndexRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontIndexRecord.this.getFontIndex());
            }
        });
    }
}
