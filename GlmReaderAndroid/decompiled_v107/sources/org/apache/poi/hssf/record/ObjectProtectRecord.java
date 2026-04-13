package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ObjectProtectRecord extends StandardRecord {
    public static final short sid = 99;
    private short field_1_protect;

    public ObjectProtectRecord() {
    }

    public ObjectProtectRecord(ObjectProtectRecord other) {
        super(other);
        this.field_1_protect = other.field_1_protect;
    }

    public ObjectProtectRecord(RecordInputStream in) {
        this.field_1_protect = in.readShort();
    }

    public void setProtect(boolean z) {
        this.field_1_protect = z ? (short) 1 : (short) 0;
    }

    public boolean getProtect() {
        return this.field_1_protect == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_protect);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 99;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ObjectProtectRecord copy() {
        return new ObjectProtectRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.OBJECT_PROTECT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("protect", new Supplier() { // from class: org.apache.poi.hssf.record.ObjectProtectRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(ObjectProtectRecord.this.getProtect());
            }
        });
    }
}
