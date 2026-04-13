package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RefreshAllRecord extends StandardRecord {
    private static final BitField refreshFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 439;
    private int _options;

    private RefreshAllRecord(int options) {
        this._options = options;
    }

    private RefreshAllRecord(RefreshAllRecord other) {
        super(other);
        this._options = other._options;
    }

    public RefreshAllRecord(RecordInputStream in) {
        this(in.readUShort());
    }

    public RefreshAllRecord(boolean refreshAll) {
        this(0);
        setRefreshAll(refreshAll);
    }

    public void setRefreshAll(boolean refreshAll) {
        this._options = refreshFlag.setBoolean(this._options, refreshAll);
    }

    public boolean getRefreshAll() {
        return refreshFlag.isSet(this._options);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._options);
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
    public RefreshAllRecord copy() {
        return new RefreshAllRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.REFRESH_ALL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new Supplier() { // from class: org.apache.poi.hssf.record.RefreshAllRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return RefreshAllRecord.this.m2365x634c2e96();
            }
        }, "refreshAll", new Supplier() { // from class: org.apache.poi.hssf.record.RefreshAllRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(RefreshAllRecord.this.getRefreshAll());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-RefreshAllRecord, reason: not valid java name */
    public /* synthetic */ Object m2365x634c2e96() {
        return Integer.valueOf(this._options);
    }
}
