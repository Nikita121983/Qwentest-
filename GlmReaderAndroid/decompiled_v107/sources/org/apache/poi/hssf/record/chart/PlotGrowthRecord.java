package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class PlotGrowthRecord extends StandardRecord {
    public static final short sid = 4196;
    private int field_1_horizontalScale;
    private int field_2_verticalScale;

    public PlotGrowthRecord() {
    }

    public PlotGrowthRecord(PlotGrowthRecord other) {
        this.field_1_horizontalScale = other.field_1_horizontalScale;
        this.field_2_verticalScale = other.field_2_verticalScale;
    }

    public PlotGrowthRecord(RecordInputStream in) {
        this.field_1_horizontalScale = in.readInt();
        this.field_2_verticalScale = in.readInt();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_horizontalScale);
        out.writeInt(this.field_2_verticalScale);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public PlotGrowthRecord copy() {
        return new PlotGrowthRecord(this);
    }

    public int getHorizontalScale() {
        return this.field_1_horizontalScale;
    }

    public void setHorizontalScale(int field_1_horizontalScale) {
        this.field_1_horizontalScale = field_1_horizontalScale;
    }

    public int getVerticalScale() {
        return this.field_2_verticalScale;
    }

    public void setVerticalScale(int field_2_verticalScale) {
        this.field_2_verticalScale = field_2_verticalScale;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PLOT_GROWTH;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("horizontalScale", new Supplier() { // from class: org.apache.poi.hssf.record.chart.PlotGrowthRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(PlotGrowthRecord.this.getHorizontalScale());
            }
        }, "verticalScale", new Supplier() { // from class: org.apache.poi.hssf.record.chart.PlotGrowthRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(PlotGrowthRecord.this.getVerticalScale());
            }
        });
    }
}
