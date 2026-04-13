package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RefModeRecord extends StandardRecord {
    public static final short USE_A1_MODE = 1;
    public static final short USE_R1C1_MODE = 0;
    public static final short sid = 15;
    private short field_1_mode;

    public RefModeRecord() {
    }

    public RefModeRecord(RefModeRecord other) {
        this.field_1_mode = other.field_1_mode;
    }

    public RefModeRecord(RecordInputStream in) {
        this.field_1_mode = in.readShort();
    }

    public void setMode(short mode) {
        this.field_1_mode = mode;
    }

    public short getMode() {
        return this.field_1_mode;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getMode());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 15;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public RefModeRecord copy() {
        return new RefModeRecord();
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.REF_MODE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("mode", new Supplier() { // from class: org.apache.poi.hssf.record.RefModeRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(RefModeRecord.this.getMode());
            }
        });
    }
}
