package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RecalcIdRecord extends StandardRecord {
    public static final short sid = 449;
    private int _engineId;
    private final int _reserved0;

    public RecalcIdRecord() {
        this._reserved0 = 0;
        this._engineId = 0;
    }

    public RecalcIdRecord(RecalcIdRecord other) {
        this._reserved0 = other._reserved0;
        this._engineId = other._engineId;
    }

    public RecalcIdRecord(RecordInputStream in) {
        in.readUShort();
        this._reserved0 = in.readUShort();
        this._engineId = in.readInt();
    }

    public boolean isNeeded() {
        return true;
    }

    public void setEngineId(int val) {
        this._engineId = val;
    }

    public int getEngineId() {
        return this._engineId;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(449);
        out.writeShort(this._reserved0);
        out.writeInt(this._engineId);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public RecalcIdRecord copy() {
        return new RecalcIdRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.RECALC_ID;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new Supplier() { // from class: org.apache.poi.hssf.record.RecalcIdRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return RecalcIdRecord.this.m2364xc1d42a13();
            }
        }, "engineId", new Supplier() { // from class: org.apache.poi.hssf.record.RecalcIdRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(RecalcIdRecord.this.getEngineId());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RecalcIdRecord, reason: not valid java name */
    public /* synthetic */ Object m2364xc1d42a13() {
        return Integer.valueOf(this._reserved0);
    }
}
