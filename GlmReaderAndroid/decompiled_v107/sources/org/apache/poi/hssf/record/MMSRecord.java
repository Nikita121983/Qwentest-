package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class MMSRecord extends StandardRecord {
    public static final short sid = 193;
    private byte field_1_addMenuCount;
    private byte field_2_delMenuCount;

    public MMSRecord() {
    }

    public MMSRecord(MMSRecord other) {
        this.field_1_addMenuCount = other.field_1_addMenuCount;
        this.field_2_delMenuCount = other.field_2_delMenuCount;
    }

    public MMSRecord(RecordInputStream in) {
        if (in.remaining() == 0) {
            return;
        }
        this.field_1_addMenuCount = in.readByte();
        this.field_2_delMenuCount = in.readByte();
    }

    public void setAddMenuCount(byte am) {
        this.field_1_addMenuCount = am;
    }

    public void setDelMenuCount(byte dm) {
        this.field_2_delMenuCount = dm;
    }

    public byte getAddMenuCount() {
        return this.field_1_addMenuCount;
    }

    public byte getDelMenuCount() {
        return this.field_2_delMenuCount;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeByte(getAddMenuCount());
        out.writeByte(getDelMenuCount());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 193;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public MMSRecord copy() {
        return new MMSRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MMS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("addMenuCount", new Supplier() { // from class: org.apache.poi.hssf.record.MMSRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(MMSRecord.this.getAddMenuCount());
            }
        }, "delMenuCount", new Supplier() { // from class: org.apache.poi.hssf.record.MMSRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(MMSRecord.this.getDelMenuCount());
            }
        });
    }
}
