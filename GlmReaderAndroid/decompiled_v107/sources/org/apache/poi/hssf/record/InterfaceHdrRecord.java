package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class InterfaceHdrRecord extends StandardRecord {
    public static final int CODEPAGE = 1200;
    public static final short sid = 225;
    private final int _codepage;

    public InterfaceHdrRecord(InterfaceHdrRecord other) {
        super(other);
        this._codepage = other._codepage;
    }

    public InterfaceHdrRecord(int codePage) {
        this._codepage = codePage;
    }

    public InterfaceHdrRecord(RecordInputStream in) {
        this._codepage = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._codepage);
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
    public InterfaceHdrRecord copy() {
        return new InterfaceHdrRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.INTERFACE_HDR;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("codePage", new Supplier() { // from class: org.apache.poi.hssf.record.InterfaceHdrRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return InterfaceHdrRecord.this.m2316xb81727cd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-InterfaceHdrRecord, reason: not valid java name */
    public /* synthetic */ Object m2316xb81727cd() {
        return Integer.valueOf(this._codepage);
    }
}
