package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DrawingRecord extends StandardRecord {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short sid = 236;
    private byte[] contd;
    private byte[] recordData;

    public DrawingRecord() {
        this.recordData = EMPTY_BYTE_ARRAY;
    }

    public DrawingRecord(DrawingRecord other) {
        super(other);
        this.recordData = other.recordData == null ? null : (byte[]) other.recordData.clone();
        this.contd = other.contd != null ? (byte[]) other.contd.clone() : null;
    }

    public DrawingRecord(RecordInputStream in) {
        this.recordData = in.readRemainder();
    }

    public DrawingRecord(byte[] data) {
        this.recordData = (byte[]) data.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public void processContinueRecord(byte[] record) {
        this.contd = record;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.write(this.recordData);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this.recordData.length;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 236;
    }

    public byte[] getRecordData() {
        return this.recordData;
    }

    public void setData(byte[] thedata) {
        if (thedata == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.recordData = thedata;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DrawingRecord copy() {
        return new DrawingRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordData", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingRecord.this.getRecordData();
            }
        }, "contd", new Supplier() { // from class: org.apache.poi.hssf.record.DrawingRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DrawingRecord.this.m2278xe1c81c90();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingRecord, reason: not valid java name */
    public /* synthetic */ Object m2278xe1c81c90() {
        return this.contd;
    }
}
