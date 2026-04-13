package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class AreaRecord extends StandardRecord {
    public static final short sid = 4122;
    private short field_1_formatFlags;
    private static final BitField stacked = BitFieldFactory.getInstance(1);
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(2);
    private static final BitField shadow = BitFieldFactory.getInstance(4);

    public AreaRecord() {
    }

    public AreaRecord(AreaRecord other) {
        super(other);
        this.field_1_formatFlags = other.field_1_formatFlags;
    }

    public AreaRecord(RecordInputStream in) {
        this.field_1_formatFlags = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_formatFlags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public short getFormatFlags() {
        return this.field_1_formatFlags;
    }

    public void setFormatFlags(short field_1_formatFlags) {
        this.field_1_formatFlags = field_1_formatFlags;
    }

    public void setStacked(boolean value) {
        this.field_1_formatFlags = stacked.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_1_formatFlags);
    }

    public void setDisplayAsPercentage(boolean value) {
        this.field_1_formatFlags = displayAsPercentage.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_1_formatFlags);
    }

    public void setShadow(boolean value) {
        this.field_1_formatFlags = shadow.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_1_formatFlags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public AreaRecord copy() {
        return new AreaRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AREA;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formatFlags", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AreaRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(AreaRecord.this.getFormatFlags());
            }
        }, "stacked", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AreaRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(AreaRecord.this.isStacked());
            }
        }, "displayAsPercentage", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AreaRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(AreaRecord.this.isDisplayAsPercentage());
            }
        }, "shadow", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AreaRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(AreaRecord.this.isShadow());
            }
        });
    }
}
