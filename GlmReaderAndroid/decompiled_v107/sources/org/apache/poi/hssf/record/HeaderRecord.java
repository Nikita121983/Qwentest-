package org.apache.poi.hssf.record;

/* loaded from: classes10.dex */
public final class HeaderRecord extends HeaderFooterBase {
    public static final short sid = 20;

    public HeaderRecord(String text) {
        super(text);
    }

    public HeaderRecord(HeaderRecord other) {
        super(other);
    }

    public HeaderRecord(RecordInputStream in) {
        super(in);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 20;
    }

    @Override // org.apache.poi.hssf.record.HeaderFooterBase, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public HeaderRecord copy() {
        return new HeaderRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HEADER;
    }
}
