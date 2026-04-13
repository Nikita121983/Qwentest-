package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class AutoFilterInfoRecord extends StandardRecord {
    public static final short sid = 157;
    private short _cEntries;

    public AutoFilterInfoRecord() {
    }

    public AutoFilterInfoRecord(AutoFilterInfoRecord other) {
        super(other);
        this._cEntries = other._cEntries;
    }

    public AutoFilterInfoRecord(RecordInputStream in) {
        this._cEntries = in.readShort();
    }

    public void setNumEntries(short num) {
        this._cEntries = num;
    }

    public short getNumEntries() {
        return this._cEntries;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._cEntries);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 157;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public AutoFilterInfoRecord copy() {
        return new AutoFilterInfoRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.AUTO_FILTER_INFO;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numEntries", new Supplier() { // from class: org.apache.poi.hssf.record.AutoFilterInfoRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(AutoFilterInfoRecord.this.getNumEntries());
            }
        });
    }
}
