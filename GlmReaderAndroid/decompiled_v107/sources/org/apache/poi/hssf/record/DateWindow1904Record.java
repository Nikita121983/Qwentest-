package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DateWindow1904Record extends StandardRecord {
    public static final short sid = 34;
    private short field_1_window;

    public DateWindow1904Record() {
    }

    public DateWindow1904Record(DateWindow1904Record other) {
        super(other);
        this.field_1_window = other.field_1_window;
    }

    public DateWindow1904Record(RecordInputStream in) {
        this.field_1_window = in.readShort();
    }

    public void setWindowing(short window) {
        this.field_1_window = window;
    }

    public short getWindowing() {
        return this.field_1_window;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getWindowing());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 34;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DateWindow1904Record copy() {
        return new DateWindow1904Record(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATE_WINDOW_1904;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("is1904", new Supplier() { // from class: org.apache.poi.hssf.record.DateWindow1904Record$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DateWindow1904Record.this.getWindowing());
            }
        });
    }
}
