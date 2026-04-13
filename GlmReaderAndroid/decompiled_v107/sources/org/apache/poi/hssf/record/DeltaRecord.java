package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DeltaRecord extends StandardRecord {
    public static final double DEFAULT_VALUE = 0.001d;
    public static final short sid = 16;
    private double field_1_max_change;

    public DeltaRecord(double maxChange) {
        this.field_1_max_change = maxChange;
    }

    public DeltaRecord(DeltaRecord other) {
        super(other);
        this.field_1_max_change = other.field_1_max_change;
    }

    public DeltaRecord(RecordInputStream in) {
        this.field_1_max_change = in.readDouble();
    }

    public double getMaxChange() {
        return this.field_1_max_change;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeDouble(getMaxChange());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 16;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DeltaRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DELTA;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("maxChange", new Supplier() { // from class: org.apache.poi.hssf.record.DeltaRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(DeltaRecord.this.getMaxChange());
            }
        });
    }
}
