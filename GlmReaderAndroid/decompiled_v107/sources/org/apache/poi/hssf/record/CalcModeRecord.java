package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CalcModeRecord extends StandardRecord {
    public static final short AUTOMATIC = 1;
    public static final short AUTOMATIC_EXCEPT_TABLES = -1;
    public static final short MANUAL = 0;
    public static final short sid = 13;
    private short field_1_calcmode;

    public CalcModeRecord() {
    }

    public CalcModeRecord(CalcModeRecord other) {
        super(other);
        this.field_1_calcmode = other.field_1_calcmode;
    }

    public CalcModeRecord(RecordInputStream in) {
        this.field_1_calcmode = in.readShort();
    }

    public void setCalcMode(short calcmode) {
        this.field_1_calcmode = calcmode;
    }

    public short getCalcMode() {
        return this.field_1_calcmode;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getCalcMode());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 13;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CalcModeRecord copy() {
        return new CalcModeRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CALC_MODE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("calcMode", new Supplier() { // from class: org.apache.poi.hssf.record.CalcModeRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CalcModeRecord.this.getCalcMode());
            }
        });
    }
}
