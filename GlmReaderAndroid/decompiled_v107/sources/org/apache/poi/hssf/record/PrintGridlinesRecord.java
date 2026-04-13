package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class PrintGridlinesRecord extends StandardRecord {
    public static final short sid = 43;
    private short field_1_print_gridlines;

    public PrintGridlinesRecord() {
    }

    public PrintGridlinesRecord(PrintGridlinesRecord other) {
        super(other);
        this.field_1_print_gridlines = other.field_1_print_gridlines;
    }

    public PrintGridlinesRecord(RecordInputStream in) {
        this.field_1_print_gridlines = in.readShort();
    }

    public void setPrintGridlines(boolean z) {
        this.field_1_print_gridlines = z ? (short) 1 : (short) 0;
    }

    public boolean getPrintGridlines() {
        return this.field_1_print_gridlines == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_print_gridlines);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 43;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public PrintGridlinesRecord copy() {
        return new PrintGridlinesRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRINT_GRIDLINES;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("printGridlines", new Supplier() { // from class: org.apache.poi.hssf.record.PrintGridlinesRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(PrintGridlinesRecord.this.getPrintGridlines());
            }
        });
    }
}
