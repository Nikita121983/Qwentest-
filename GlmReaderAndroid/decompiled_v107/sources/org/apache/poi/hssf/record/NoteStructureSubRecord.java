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
public final class NoteStructureSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 22;
    public static final short sid = 13;
    private final byte[] reserved;

    public NoteStructureSubRecord() {
        this.reserved = new byte[22];
    }

    public NoteStructureSubRecord(NoteStructureSubRecord other) {
        super(other);
        this.reserved = (byte[]) other.reserved.clone();
    }

    public NoteStructureSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    public NoteStructureSubRecord(LittleEndianInput in, int size, int cmoOt) {
        if (size == 22) {
            byte[] buf = IOUtils.safelyAllocate(size, 22);
            in.readFully(buf);
            this.reserved = buf;
            return;
        }
        throw new RecordFormatException("Unexpected size (" + size + ")");
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(13);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return (short) 13;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public NoteStructureSubRecord copy() {
        return new NoteStructureSubRecord(this);
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.NOTE_STRUCTURE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.hssf.record.NoteStructureSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return NoteStructureSubRecord.this.m2342x746600af();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NoteStructureSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2342x746600af() {
        return this.reserved;
    }
}
