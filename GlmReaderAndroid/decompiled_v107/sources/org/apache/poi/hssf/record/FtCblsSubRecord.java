package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class FtCblsSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 20;
    public static final short sid = 12;
    private final byte[] reserved;

    public FtCblsSubRecord() {
        this.reserved = new byte[20];
    }

    public FtCblsSubRecord(FtCblsSubRecord other) {
        super(other);
        this.reserved = (byte[]) other.reserved.clone();
    }

    public FtCblsSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FtCblsSubRecord(LittleEndianInput in, int size, int cmoOt) {
        if (size == 20) {
            byte[] buf = IOUtils.safelyAllocate(size, 20);
            in.readFully(buf);
            this.reserved = buf;
            return;
        }
        throw new RecordFormatException("Unexpected size (" + size + ")");
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(12);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return (short) 12;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public FtCblsSubRecord copy() {
        return new FtCblsSubRecord(this);
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.FT_CBLS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.FtCblsSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FtCblsSubRecord.this.m2311xd8fd773e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FtCblsSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2311xd8fd773e() {
        return this.reserved;
    }
}
