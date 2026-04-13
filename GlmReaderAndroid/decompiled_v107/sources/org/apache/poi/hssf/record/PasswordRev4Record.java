package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class PasswordRev4Record extends StandardRecord {
    public static final short sid = 444;
    private int field_1_password;

    public PasswordRev4Record(int pw) {
        this.field_1_password = pw;
    }

    public PasswordRev4Record(PasswordRev4Record other) {
        super(other);
        this.field_1_password = other.field_1_password;
    }

    public PasswordRev4Record(RecordInputStream in) {
        this.field_1_password = in.readShort();
    }

    public void setPassword(short pw) {
        this.field_1_password = pw;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_password);
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
    public PasswordRev4Record copy() {
        return new PasswordRev4Record(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PASSWORD_REV_4;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("password", new Supplier() { // from class: org.apache.poi.hssf.record.PasswordRev4Record$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return PasswordRev4Record.this.m2360x13e2d6bc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-PasswordRev4Record, reason: not valid java name */
    public /* synthetic */ Object m2360x13e2d6bc() {
        return Integer.valueOf(this.field_1_password);
    }
}
