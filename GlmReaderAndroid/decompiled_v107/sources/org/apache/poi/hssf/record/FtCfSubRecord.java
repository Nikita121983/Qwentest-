package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class FtCfSubRecord extends SubRecord {
    public static final short BITMAP_BIT = 9;
    public static final short METAFILE_BIT = 2;
    public static final short UNSPECIFIED_BIT = -1;
    public static final short length = 2;
    public static final short sid = 7;
    private short flags;

    public FtCfSubRecord() {
    }

    public FtCfSubRecord(FtCfSubRecord other) {
        super(other);
        this.flags = other.flags;
    }

    public FtCfSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FtCfSubRecord(LittleEndianInput in, int size, int cmoOt) {
        if (size != 2) {
            throw new RecordFormatException("Unexpected size (" + size + ")");
        }
        this.flags = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(7);
        out.writeShort(2);
        out.writeShort(this.flags);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return (short) 7;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public FtCfSubRecord copy() {
        return new FtCfSubRecord(this);
    }

    public short getFlags() {
        return this.flags;
    }

    public void setFlags(short flags) {
        this.flags = flags;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.FT_CF;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.FtCfSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FtCfSubRecord.this.getFlags());
            }
        }, new int[]{2, 9, -1}, new String[]{"METAFILE", "BITMAP", "UNSPECIFIED"}));
    }
}
