package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class UnitsRecord extends StandardRecord {
    public static final short sid = 4097;
    private short field_1_units;

    public UnitsRecord() {
    }

    public UnitsRecord(UnitsRecord other) {
        super(other);
        this.field_1_units = other.field_1_units;
    }

    public UnitsRecord(RecordInputStream in) {
        this.field_1_units = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_units);
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
    public UnitsRecord copy() {
        return new UnitsRecord(this);
    }

    public short getUnits() {
        return this.field_1_units;
    }

    public void setUnits(short field_1_units) {
        this.field_1_units = field_1_units;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.UNITS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("units", new Supplier() { // from class: org.apache.poi.hssf.record.chart.UnitsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(UnitsRecord.this.getUnits());
            }
        });
    }
}
