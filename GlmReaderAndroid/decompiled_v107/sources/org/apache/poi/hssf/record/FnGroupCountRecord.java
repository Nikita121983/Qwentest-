package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FnGroupCountRecord extends StandardRecord {
    public static final short COUNT = 14;
    public static final short sid = 156;
    private short field_1_count;

    public FnGroupCountRecord() {
    }

    public FnGroupCountRecord(FnGroupCountRecord other) {
        super(other);
        this.field_1_count = other.field_1_count;
    }

    public FnGroupCountRecord(RecordInputStream in) {
        this.field_1_count = in.readShort();
    }

    public void setCount(short count) {
        this.field_1_count = count;
    }

    public short getCount() {
        return this.field_1_count;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getCount());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 156;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FnGroupCountRecord copy() {
        return new FnGroupCountRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FN_GROUP_COUNT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("count", new Supplier() { // from class: org.apache.poi.hssf.record.FnGroupCountRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FnGroupCountRecord.this.getCount());
            }
        });
    }
}
