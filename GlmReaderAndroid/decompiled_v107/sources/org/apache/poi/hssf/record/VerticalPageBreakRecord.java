package org.apache.poi.hssf.record;

/* loaded from: classes10.dex */
public final class VerticalPageBreakRecord extends PageBreakRecord {
    public static final short sid = 26;

    public VerticalPageBreakRecord() {
    }

    public VerticalPageBreakRecord(VerticalPageBreakRecord other) {
        super(other);
    }

    public VerticalPageBreakRecord(RecordInputStream in) {
        super(in);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 26;
    }

    @Override // org.apache.poi.hssf.record.PageBreakRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public VerticalPageBreakRecord copy() {
        return new VerticalPageBreakRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VERTICAL_PAGE_BREAK;
    }
}
