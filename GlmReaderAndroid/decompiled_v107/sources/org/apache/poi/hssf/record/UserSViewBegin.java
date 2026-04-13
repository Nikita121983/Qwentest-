package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class UserSViewBegin extends StandardRecord {
    public static final short sid = 426;
    private byte[] _rawData;

    public UserSViewBegin(UserSViewBegin other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[]) other._rawData.clone();
    }

    public UserSViewBegin(byte[] data) {
        this._rawData = data;
    }

    public UserSViewBegin(RecordInputStream in) {
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
        return Arrays.copyOf(this._rawData, 16);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public UserSViewBegin copy() {
        return new UserSViewBegin(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.USER_SVIEW_BEGIN;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("guid", new Supplier() { // from class: org.apache.poi.hssf.record.UserSViewBegin$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return UserSViewBegin.this.getGuid();
            }
        }, "rawData", new Supplier() { // from class: org.apache.poi.hssf.record.UserSViewBegin$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return UserSViewBegin.this.m2396x70c8159b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-UserSViewBegin, reason: not valid java name */
    public /* synthetic */ Object m2396x70c8159b() {
        return this._rawData;
    }
}
