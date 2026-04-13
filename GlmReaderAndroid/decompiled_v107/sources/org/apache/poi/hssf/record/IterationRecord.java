package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class IterationRecord extends StandardRecord {
    private static final BitField iterationOn = BitFieldFactory.getInstance(1);
    public static final short sid = 17;
    private int _flags;

    public IterationRecord(IterationRecord other) {
        super(other);
        this._flags = other._flags;
    }

    public IterationRecord(boolean iterateOn) {
        this._flags = iterationOn.setBoolean(0, iterateOn);
    }

    public IterationRecord(RecordInputStream in) {
        this._flags = in.readShort();
    }

    public void setIteration(boolean iterate) {
        this._flags = iterationOn.setBoolean(this._flags, iterate);
    }

    public boolean getIteration() {
        return iterationOn.isSet(this._flags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._flags);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 17;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public IterationRecord copy() {
        return new IterationRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ITERATION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", new Supplier() { // from class: org.apache.poi.hssf.record.IterationRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return IterationRecord.this.m2317x66ebefcf();
            }
        }, "iteration", new Supplier() { // from class: org.apache.poi.hssf.record.IterationRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(IterationRecord.this.getIteration());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-IterationRecord, reason: not valid java name */
    public /* synthetic */ Object m2317x66ebefcf() {
        return Integer.valueOf(this._flags);
    }
}
