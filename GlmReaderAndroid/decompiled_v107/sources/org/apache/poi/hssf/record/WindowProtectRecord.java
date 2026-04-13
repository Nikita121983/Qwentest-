package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class WindowProtectRecord extends StandardRecord {
    private static final BitField settingsProtectedFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 25;
    private int _options;

    public WindowProtectRecord(int options) {
        this._options = options;
    }

    public WindowProtectRecord(WindowProtectRecord other) {
        super(other);
        this._options = other._options;
    }

    public WindowProtectRecord(RecordInputStream in) {
        this(in.readUShort());
    }

    public WindowProtectRecord(boolean protect) {
        this(0);
        setProtect(protect);
    }

    public void setProtect(boolean protect) {
        this._options = settingsProtectedFlag.setBoolean(this._options, protect);
    }

    public boolean getProtect() {
        return settingsProtectedFlag.isSet(this._options);
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
        return (short) 25;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public WindowProtectRecord copy() {
        return new WindowProtectRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WINDOW_PROTECT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new Supplier() { // from class: org.apache.poi.hssf.record.WindowProtectRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return WindowProtectRecord.this.m2398xae75cbb1();
            }
        }, "protect", new Supplier() { // from class: org.apache.poi.hssf.record.WindowProtectRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(WindowProtectRecord.this.getProtect());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-WindowProtectRecord, reason: not valid java name */
    public /* synthetic */ Object m2398xae75cbb1() {
        return Integer.valueOf(this._options);
    }
}
