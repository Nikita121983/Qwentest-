package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class AxisUsedRecord extends StandardRecord {
    public static final short sid = 4166;
    private short field_1_numAxis;

    public AxisUsedRecord() {
    }

    public AxisUsedRecord(AxisUsedRecord other) {
        super(other);
        this.field_1_numAxis = other.field_1_numAxis;
    }

    public AxisUsedRecord(RecordInputStream in) {
        this.field_1_numAxis = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_numAxis);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public short getNumAxis() {
        return this.field_1_numAxis;
    }

    public void setNumAxis(short field_1_numAxis) {
        this.field_1_numAxis = field_1_numAxis;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public AxisUsedRecord copy() {
        return new AxisUsedRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AXIS_USED;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numAxis", new Supplier() { // from class: org.apache.poi.hssf.record.chart.AxisUsedRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(AxisUsedRecord.this.getNumAxis());
            }
        });
    }
}
