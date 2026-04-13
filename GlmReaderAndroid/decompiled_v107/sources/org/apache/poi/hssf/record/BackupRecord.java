package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class BackupRecord extends StandardRecord {
    public static final short sid = 64;
    private short field_1_backup;

    public BackupRecord() {
    }

    public BackupRecord(BackupRecord other) {
        super(other);
        this.field_1_backup = other.field_1_backup;
    }

    public BackupRecord(RecordInputStream in) {
        this.field_1_backup = in.readShort();
    }

    public void setBackup(short backup) {
        this.field_1_backup = backup;
    }

    public short getBackup() {
        return this.field_1_backup;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getBackup());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 64;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BackupRecord copy() {
        return new BackupRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BACKUP;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("backup", new Supplier() { // from class: org.apache.poi.hssf.record.BackupRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(BackupRecord.this.getBackup());
            }
        });
    }
}
