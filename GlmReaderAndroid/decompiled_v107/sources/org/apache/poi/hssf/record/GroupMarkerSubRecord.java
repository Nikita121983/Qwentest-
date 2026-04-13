package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class GroupMarkerSubRecord extends SubRecord {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short sid = 6;
    private byte[] reserved;

    public GroupMarkerSubRecord() {
        this.reserved = EMPTY_BYTE_ARRAY;
    }

    public GroupMarkerSubRecord(GroupMarkerSubRecord other) {
        super(other);
        this.reserved = (byte[]) other.reserved.clone();
    }

    public GroupMarkerSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GroupMarkerSubRecord(LittleEndianInput in, int size, int cmoOt) {
        byte[] buf = IOUtils.safelyAllocate(size, HSSFWorkbook.getMaxRecordLength());
        in.readFully(buf);
        this.reserved = buf;
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(6);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return (short) 6;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public GroupMarkerSubRecord copy() {
        return new GroupMarkerSubRecord(this);
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.GROUP_MARKER;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.GroupMarkerSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return GroupMarkerSubRecord.this.m2312x5806577();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-GroupMarkerSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2312x5806577() {
        return this.reserved;
    }
}
