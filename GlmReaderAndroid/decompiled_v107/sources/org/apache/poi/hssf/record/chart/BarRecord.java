package org.apache.poi.hssf.record.chart;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class BarRecord extends StandardRecord {
    public static final short sid = 4119;
    private short field_1_barSpace;
    private short field_2_categorySpace;
    private short field_3_formatFlags;
    private static final BitField horizontal = BitFieldFactory.getInstance(1);
    private static final BitField stacked = BitFieldFactory.getInstance(2);
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(4);
    private static final BitField shadow = BitFieldFactory.getInstance(8);

    public BarRecord() {
    }

    public BarRecord(BarRecord other) {
        super(other);
        this.field_1_barSpace = other.field_1_barSpace;
        this.field_2_categorySpace = other.field_2_categorySpace;
        this.field_3_formatFlags = other.field_3_formatFlags;
    }

    public BarRecord(RecordInputStream in) {
        this.field_1_barSpace = in.readShort();
        this.field_2_categorySpace = in.readShort();
        this.field_3_formatFlags = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_barSpace);
        out.writeShort(this.field_2_categorySpace);
        out.writeShort(this.field_3_formatFlags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 6;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public short getBarSpace() {
        return this.field_1_barSpace;
    }

    public void setBarSpace(short field_1_barSpace) {
        this.field_1_barSpace = field_1_barSpace;
    }

    public short getCategorySpace() {
        return this.field_2_categorySpace;
    }

    public void setCategorySpace(short field_2_categorySpace) {
        this.field_2_categorySpace = field_2_categorySpace;
    }

    public short getFormatFlags() {
        return this.field_3_formatFlags;
    }

    public void setFormatFlags(short field_3_formatFlags) {
        this.field_3_formatFlags = field_3_formatFlags;
    }

    public void setHorizontal(boolean value) {
        this.field_3_formatFlags = horizontal.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isHorizontal() {
        return horizontal.isSet(this.field_3_formatFlags);
    }

    public void setStacked(boolean value) {
        this.field_3_formatFlags = stacked.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_3_formatFlags);
    }

    public void setDisplayAsPercentage(boolean value) {
        this.field_3_formatFlags = displayAsPercentage.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_3_formatFlags);
    }

    public void setShadow(boolean value) {
        this.field_3_formatFlags = shadow.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_3_formatFlags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BarRecord copy() {
        return new BarRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BAR;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("barSpace", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(BarRecord.this.getBarSpace());
            }
        });
        m.put("categorySpace", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(BarRecord.this.getCategorySpace());
            }
        });
        m.put("formatFlags", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(BarRecord.this.getFormatFlags());
            }
        });
        m.put("horizontal", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BarRecord.this.isHorizontal());
            }
        });
        m.put("stacked", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BarRecord.this.isStacked());
            }
        });
        m.put("displayAsPercentage", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BarRecord.this.isDisplayAsPercentage());
            }
        });
        m.put("shadow", new Supplier() { // from class: org.apache.poi.hssf.record.chart.BarRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BarRecord.this.isShadow());
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
