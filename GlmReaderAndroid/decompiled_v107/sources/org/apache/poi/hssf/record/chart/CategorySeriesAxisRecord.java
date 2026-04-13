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
public final class CategorySeriesAxisRecord extends StandardRecord {
    public static final short sid = 4128;
    private short field_1_crossingPoint;
    private short field_2_labelFrequency;
    private short field_3_tickMarkFrequency;
    private short field_4_options;
    private static final BitField valueAxisCrossing = BitFieldFactory.getInstance(1);
    private static final BitField crossesFarRight = BitFieldFactory.getInstance(2);
    private static final BitField reversed = BitFieldFactory.getInstance(4);

    public CategorySeriesAxisRecord() {
    }

    public CategorySeriesAxisRecord(CategorySeriesAxisRecord other) {
        super(other);
        this.field_1_crossingPoint = other.field_1_crossingPoint;
        this.field_2_labelFrequency = other.field_2_labelFrequency;
        this.field_3_tickMarkFrequency = other.field_3_tickMarkFrequency;
        this.field_4_options = other.field_4_options;
    }

    public CategorySeriesAxisRecord(RecordInputStream in) {
        this.field_1_crossingPoint = in.readShort();
        this.field_2_labelFrequency = in.readShort();
        this.field_3_tickMarkFrequency = in.readShort();
        this.field_4_options = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_crossingPoint);
        out.writeShort(this.field_2_labelFrequency);
        out.writeShort(this.field_3_tickMarkFrequency);
        out.writeShort(this.field_4_options);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public short getCrossingPoint() {
        return this.field_1_crossingPoint;
    }

    public void setCrossingPoint(short field_1_crossingPoint) {
        this.field_1_crossingPoint = field_1_crossingPoint;
    }

    public short getLabelFrequency() {
        return this.field_2_labelFrequency;
    }

    public void setLabelFrequency(short field_2_labelFrequency) {
        this.field_2_labelFrequency = field_2_labelFrequency;
    }

    public short getTickMarkFrequency() {
        return this.field_3_tickMarkFrequency;
    }

    public void setTickMarkFrequency(short field_3_tickMarkFrequency) {
        this.field_3_tickMarkFrequency = field_3_tickMarkFrequency;
    }

    public short getOptions() {
        return this.field_4_options;
    }

    public void setOptions(short field_4_options) {
        this.field_4_options = field_4_options;
    }

    public void setValueAxisCrossing(boolean value) {
        this.field_4_options = valueAxisCrossing.setShortBoolean(this.field_4_options, value);
    }

    public boolean isValueAxisCrossing() {
        return valueAxisCrossing.isSet(this.field_4_options);
    }

    public void setCrossesFarRight(boolean value) {
        this.field_4_options = crossesFarRight.setShortBoolean(this.field_4_options, value);
    }

    public boolean isCrossesFarRight() {
        return crossesFarRight.isSet(this.field_4_options);
    }

    public void setReversed(boolean value) {
        this.field_4_options = reversed.setShortBoolean(this.field_4_options, value);
    }

    public boolean isReversed() {
        return reversed.isSet(this.field_4_options);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CategorySeriesAxisRecord copy() {
        return new CategorySeriesAxisRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CATEGORY_SERIES_AXIS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("crossingPoint", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CategorySeriesAxisRecord.this.getCrossingPoint());
            }
        }, "labelFrequency", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CategorySeriesAxisRecord.this.getLabelFrequency());
            }
        }, "tickMarkFrequency", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CategorySeriesAxisRecord.this.getTickMarkFrequency());
            }
        }, "options", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CategorySeriesAxisRecord.this.getOptions());
            }
        }, "valueAxisCrossing", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CategorySeriesAxisRecord.this.isValueAxisCrossing());
            }
        }, "crossesFarRight", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CategorySeriesAxisRecord.this.isCrossesFarRight());
            }
        }, "reversed", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CategorySeriesAxisRecord.this.isReversed());
            }
        });
    }
}
