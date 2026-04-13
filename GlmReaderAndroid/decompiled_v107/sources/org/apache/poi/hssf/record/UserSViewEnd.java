package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class UserSViewEnd extends StandardRecord {
    public static final short sid = 427;
    private byte[] _rawData;

    public UserSViewEnd(UserSViewEnd other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[]) other._rawData.clone();
    }

    public UserSViewEnd(byte[] data) {
        this._rawData = data;
    }

    public UserSViewEnd(RecordInputStream in) {
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

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public UserSViewEnd copy() {
        return new UserSViewEnd(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.USER_SVIEW_END;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rawData", new Supplier() { // from class: org.apache.poi.hssf.record.UserSViewEnd$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return UserSViewEnd.this.m2397xc0fbba4d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UserSViewEnd, reason: not valid java name */
    public /* synthetic */ Object m2397xc0fbba4d() {
        return this._rawData;
    }
}
