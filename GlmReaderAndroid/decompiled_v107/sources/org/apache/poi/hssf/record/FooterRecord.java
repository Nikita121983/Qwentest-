package org.apache.poi.hssf.record;

/* loaded from: classes10.dex */
public final class FooterRecord extends HeaderFooterBase {
    public static final short sid = 21;

    public FooterRecord(String text) {
        super(text);
    }

    public FooterRecord(FooterRecord other) {
        super(other);
    }

    public FooterRecord(RecordInputStream in) {
        super(in);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 21;
    }

    @Override // org.apache.poi.hssf.record.HeaderFooterBase, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FooterRecord copy() {
        return new FooterRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FOOTER;
    }
}
