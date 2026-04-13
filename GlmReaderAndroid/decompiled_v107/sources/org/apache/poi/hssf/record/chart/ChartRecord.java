package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartRecord extends StandardRecord {
    public static final short sid = 4098;
    private int field_1_x;
    private int field_2_y;
    private int field_3_width;
    private int field_4_height;

    public ChartRecord() {
    }

    public ChartRecord(ChartRecord other) {
        super(other);
        this.field_1_x = other.field_1_x;
        this.field_2_y = other.field_2_y;
        this.field_3_width = other.field_3_width;
        this.field_4_height = other.field_4_height;
    }

    public ChartRecord(RecordInputStream in) {
        this.field_1_x = in.readInt();
        this.field_2_y = in.readInt();
        this.field_3_width = in.readInt();
        this.field_4_height = in.readInt();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_x);
        out.writeInt(this.field_2_y);
        out.writeInt(this.field_3_width);
        out.writeInt(this.field_4_height);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 16;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartRecord copy() {
        return new ChartRecord(this);
    }

    public int getX() {
        return this.field_1_x;
    }

    public void setX(int x) {
        this.field_1_x = x;
    }

    public int getY() {
        return this.field_2_y;
    }

    public void setY(int y) {
        this.field_2_y = y;
    }

    public int getWidth() {
        return this.field_3_width;
    }

    public void setWidth(int width) {
        this.field_3_width = width;
    }

    public int getHeight() {
        return this.field_4_height;
    }

    public void setHeight(int height) {
        this.field_4_height = height;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("x", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartRecord.this.getX());
            }
        }, "y", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartRecord.this.getY());
            }
        }, "width", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartRecord.this.getWidth());
            }
        }, "height", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartRecord.this.getHeight());
            }
        });
    }
}
