package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class AxisRecord extends StandardRecord {
    public static final short AXIS_TYPE_CATEGORY_OR_X_AXIS = 0;
    public static final short AXIS_TYPE_SERIES_AXIS = 2;
    public static final short AXIS_TYPE_VALUE_AXIS = 1;
    public static final short sid = 4125;
    private short field_1_axisType;
    private int field_2_reserved1;
    private int field_3_reserved2;
    private int field_4_reserved3;
    private int field_5_reserved4;

    public AxisRecord() {
    }

    public AxisRecord(AxisRecord other) {
        super(other);
        this.field_1_axisType = other.field_1_axisType;
        this.field_2_reserved1 = other.field_2_reserved1;
        this.field_3_reserved2 = other.field_3_reserved2;
        this.field_4_reserved3 = other.field_4_reserved3;
        this.field_5_reserved4 = other.field_5_reserved4;
    }

    public AxisRecord(RecordInputStream in) {
        this.field_1_axisType = in.readShort();
        this.field_2_reserved1 = in.readInt();
        this.field_3_reserved2 = in.readInt();
        this.field_4_reserved3 = in.readInt();
        this.field_5_reserved4 = in.readInt();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_axisType);
        out.writeInt(this.field_2_reserved1);
        out.writeInt(this.field_3_reserved2);
        out.writeInt(this.field_4_reserved3);
        out.writeInt(this.field_5_reserved4);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 18;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public short getAxisType() {
        return this.field_1_axisType;
    }

    public void setAxisType(short field_1_axisType) {
        this.field_1_axisType = field_1_axisType;
    }

    public int getReserved1() {
        return this.field_2_reserved1;
    }

    public void setReserved1(int field_2_reserved1) {
        this.field_2_reserved1 = field_2_reserved1;
    }

    public int getReserved2() {
        return this.field_3_reserved2;
    }

    public void setReserved2(int field_3_reserved2) {
        this.field_3_reserved2 = field_3_reserved2;
    }

    public int getReserved3() {
        return this.field_4_reserved3;
    }

    public void setReserved3(int field_4_reserved3) {
        this.field_4_reserved3 = field_4_reserved3;
    }

    public int getReserved4() {
        return this.field_5_reserved4;
    }

    public void setReserved4(int field_5_reserved4) {
        this.field_5_reserved4 = field_5_reserved4;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public AxisRecord copy() {
        return new AxisRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("axisType", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(AxisRecord.this.getAxisType());
            }
        }, "reserved1", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(AxisRecord.this.getReserved1());
            }
        }, "reserved2", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(AxisRecord.this.getReserved2());
            }
        }, "reserved3", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(AxisRecord.this.getReserved3());
            }
        }, "reserved4", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(AxisRecord.this.getReserved4());
            }
        });
    }
}
