package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class HeaderFooterRecord extends StandardRecord {
    private static final byte[] BLANK_GUID = new byte[16];
    public static final short sid = 2204;
    private byte[] _rawData;

    public HeaderFooterRecord(byte[] data) {
        this._rawData = data;
    }

    public HeaderFooterRecord(HeaderFooterRecord other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[]) other._rawData.clone();
    }

    public HeaderFooterRecord(RecordInputStream in) {
        this._rawData = in.readRemainder();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.write(this._rawData);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this._rawData.length;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public byte[] getGuid() {
        return Arrays.copyOfRange(this._rawData, 12, 28);
    }

    public boolean isCurrentSheet() {
        return Arrays.equals(getGuid(), BLANK_GUID);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public HeaderFooterRecord copy() {
        return new HeaderFooterRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HEADER_FOOTER;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rawData", new Supplier() { // from class: org.apache.poi.hssf.record.HeaderFooterRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return HeaderFooterRecord.this.m2313x553d4318();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-HeaderFooterRecord, reason: not valid java name */
    public /* synthetic */ Object m2313x553d4318() {
        return this._rawData;
    }
}
