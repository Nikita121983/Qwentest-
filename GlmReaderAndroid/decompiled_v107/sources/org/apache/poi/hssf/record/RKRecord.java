package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.RKUtil;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RKRecord extends CellRecord {
    public static final short RK_IEEE_NUMBER = 0;
    public static final short RK_IEEE_NUMBER_TIMES_100 = 1;
    public static final short RK_INTEGER = 2;
    public static final short RK_INTEGER_TIMES_100 = 3;
    public static final short sid = 638;
    private final int field_4_rk_number;

    public RKRecord(RKRecord other) {
        super(other);
        this.field_4_rk_number = other.field_4_rk_number;
    }

    public RKRecord(RecordInputStream in) {
        super(in);
        this.field_4_rk_number = in.readInt();
    }

    public double getRKNumber() {
        return RKUtil.decodeNumber(this.field_4_rk_number);
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected String getRecordName() {
        return "RK";
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected void serializeValue(LittleEndianOutput out) {
        out.writeInt(this.field_4_rk_number);
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected int getValueDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public RKRecord copy() {
        return new RKRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.RK;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.RKRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return RKRecord.this.m2363xf0d744a9();
            }
        }, "rkNumber", new Supplier() { // from class: org.apache.poi.hssf.record.RKRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(RKRecord.this.getRKNumber());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RKRecord, reason: not valid java name */
    public /* synthetic */ Object m2363xf0d744a9() {
        return super.getGenericProperties();
    }
}
