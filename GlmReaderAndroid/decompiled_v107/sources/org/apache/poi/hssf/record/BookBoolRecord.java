package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class BookBoolRecord extends StandardRecord {
    public static final short sid = 218;
    private short field_1_save_link_values;

    public BookBoolRecord() {
    }

    public BookBoolRecord(BookBoolRecord other) {
        super(other);
        this.field_1_save_link_values = other.field_1_save_link_values;
    }

    public BookBoolRecord(RecordInputStream in) {
        this.field_1_save_link_values = in.readShort();
    }

    public void setSaveLinkValues(short flag) {
        this.field_1_save_link_values = flag;
    }

    public short getSaveLinkValues() {
        return this.field_1_save_link_values;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_save_link_values);
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
    public BookBoolRecord copy() {
        return new BookBoolRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOOK_BOOL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("saveLinkValues", new Supplier() { // from class: org.apache.poi.hssf.record.BookBoolRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(BookBoolRecord.this.getSaveLinkValues());
            }
        });
    }
}
