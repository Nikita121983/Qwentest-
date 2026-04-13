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
public final class DatRecord extends StandardRecord {
    public static final short sid = 4195;
    private short field_1_options;
    private static final BitField horizontalBorder = BitFieldFactory.getInstance(1);
    private static final BitField verticalBorder = BitFieldFactory.getInstance(2);
    private static final BitField border = BitFieldFactory.getInstance(4);
    private static final BitField showSeriesKey = BitFieldFactory.getInstance(8);

    public DatRecord() {
    }

    public DatRecord(DatRecord other) {
        super(other);
        this.field_1_options = other.field_1_options;
    }

    public DatRecord(RecordInputStream in) {
        this.field_1_options = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_options);
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
    public DatRecord copy() {
        return new DatRecord(this);
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public void setOptions(short field_1_options) {
        this.field_1_options = field_1_options;
    }

    public void setHorizontalBorder(boolean value) {
        this.field_1_options = horizontalBorder.setShortBoolean(this.field_1_options, value);
    }

    public boolean isHorizontalBorder() {
        return horizontalBorder.isSet(this.field_1_options);
    }

    public void setVerticalBorder(boolean value) {
        this.field_1_options = verticalBorder.setShortBoolean(this.field_1_options, value);
    }

    public boolean isVerticalBorder() {
        return verticalBorder.isSet(this.field_1_options);
    }

    public void setBorder(boolean value) {
        this.field_1_options = border.setShortBoolean(this.field_1_options, value);
    }

    public boolean isBorder() {
        return border.isSet(this.field_1_options);
    }

    public void setShowSeriesKey(boolean value) {
        this.field_1_options = showSeriesKey.setShortBoolean(this.field_1_options, value);
    }

    public boolean isShowSeriesKey() {
        return showSeriesKey.isSet(this.field_1_options);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DatRecord.this.getOptions());
            }
        }, "horizontalBorder", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(DatRecord.this.isHorizontalBorder());
            }
        }, "verticalBorder", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(DatRecord.this.isVerticalBorder());
            }
        }, "border", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DatRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(DatRecord.this.isBorder());
            }
        }, "showSeriesKey", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DatRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(DatRecord.this.isShowSeriesKey());
            }
        });
    }
}
