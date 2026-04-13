package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class UncalcedRecord extends StandardRecord {
    public static final short sid = 94;
    private short _reserved;

    public UncalcedRecord() {
        this._reserved = (short) 0;
    }

    public UncalcedRecord(UncalcedRecord other) {
        super(other);
        this._reserved = other._reserved;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 94;
    }

    public UncalcedRecord(RecordInputStream in) {
        this._reserved = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._reserved);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    public static int getStaticRecordSize() {
        return 6;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public UncalcedRecord copy() {
        return new UncalcedRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.UNCALCED;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.UncalcedRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return UncalcedRecord.this.m2392x26a9a9dd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UncalcedRecord, reason: not valid java name */
    public /* synthetic */ Object m2392x26a9a9dd() {
        return Short.valueOf(this._reserved);
    }
}
