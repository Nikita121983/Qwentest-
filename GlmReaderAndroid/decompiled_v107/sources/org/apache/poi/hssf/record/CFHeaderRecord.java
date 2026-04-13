package org.apache.poi.hssf.record;

import org.apache.poi.ss.util.CellRangeAddress;

/* loaded from: classes10.dex */
public final class CFHeaderRecord extends CFHeaderBase {
    public static final short sid = 432;

    public CFHeaderRecord() {
        createEmpty();
    }

    public CFHeaderRecord(CFHeaderRecord other) {
        super(other);
    }

    public CFHeaderRecord(CellRangeAddress[] regions, int nRules) {
        super(regions, nRules);
    }

    public CFHeaderRecord(RecordInputStream in) {
        read(in);
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase
    protected String getRecordName() {
        return "CFHEADER";
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CFHeaderRecord copy() {
        return new CFHeaderRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_HEADER;
    }
}
