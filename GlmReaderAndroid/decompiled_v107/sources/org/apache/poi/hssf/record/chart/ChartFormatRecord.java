package org.apache.poi.hssf.record.chart;

import androidx.core.os.EnvironmentCompat;
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
public final class ChartFormatRecord extends StandardRecord {
    public static final short sid = 4116;
    private static final BitField varyDisplayPattern = BitFieldFactory.getInstance(1);
    private int field1_x_position;
    private int field2_y_position;
    private int field3_width;
    private int field4_height;
    private int field5_grbit;
    private int field6_unknown;

    public ChartFormatRecord() {
    }

    public ChartFormatRecord(ChartFormatRecord other) {
        super(other);
        this.field1_x_position = other.field1_x_position;
        this.field2_y_position = other.field2_y_position;
        this.field3_width = other.field3_width;
        this.field4_height = other.field4_height;
        this.field5_grbit = other.field5_grbit;
        this.field6_unknown = other.field6_unknown;
    }

    public ChartFormatRecord(RecordInputStream in) {
        this.field1_x_position = in.readInt();
        this.field2_y_position = in.readInt();
        this.field3_width = in.readInt();
        this.field4_height = in.readInt();
        this.field5_grbit = in.readUShort();
        this.field6_unknown = in.readUShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(getXPosition());
        out.writeInt(getYPosition());
        out.writeInt(getWidth());
        out.writeInt(getHeight());
        out.writeShort(this.field5_grbit);
        out.writeShort(this.field6_unknown);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 20;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public int getXPosition() {
        return this.field1_x_position;
    }

    public void setXPosition(int xPosition) {
        this.field1_x_position = xPosition;
    }

    public int getYPosition() {
        return this.field2_y_position;
    }

    public void setYPosition(int yPosition) {
        this.field2_y_position = yPosition;
    }

    public int getWidth() {
        return this.field3_width;
    }

    public void setWidth(int width) {
        this.field3_width = width;
    }

    public int getHeight() {
        return this.field4_height;
    }

    public void setHeight(int height) {
        this.field4_height = height;
    }

    public boolean getVaryDisplayPattern() {
        return varyDisplayPattern.isSet(this.field5_grbit);
    }

    public void setVaryDisplayPattern(boolean value) {
        this.field5_grbit = varyDisplayPattern.setBoolean(this.field5_grbit, value);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartFormatRecord copy() {
        return new ChartFormatRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_FORMAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("x", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartFormatRecord.this.getXPosition());
            }
        }, "y", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartFormatRecord.this.getYPosition());
            }
        }, "width", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartFormatRecord.this.getWidth());
            }
        }, "height", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ChartFormatRecord.this.getHeight());
            }
        }, "grbit", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFormatRecord.this.m2424x9919bf38();
            }
        }, "varyDisplayPattern", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ChartFormatRecord.this.getVaryDisplayPattern());
            }
        }, EnvironmentCompat.MEDIA_UNKNOWN, new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFormatRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFormatRecord.this.m2425xc6f25997();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartFormatRecord, reason: not valid java name */
    public /* synthetic */ Object m2424x9919bf38() {
        return Integer.valueOf(this.field5_grbit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartFormatRecord, reason: not valid java name */
    public /* synthetic */ Object m2425xc6f25997() {
        return Integer.valueOf(this.field6_unknown);
    }
}
