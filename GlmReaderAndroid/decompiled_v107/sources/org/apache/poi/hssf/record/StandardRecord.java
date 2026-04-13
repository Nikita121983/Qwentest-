package org.apache.poi.hssf.record;

import java.io.IOException;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class StandardRecord extends Record {
    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract StandardRecord copy();

    protected abstract int getDataSize();

    protected abstract void serialize(LittleEndianOutput littleEndianOutput);

    /* JADX INFO: Access modifiers changed from: protected */
    public StandardRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StandardRecord(StandardRecord other) {
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public final int getRecordSize() {
        return getDataSize() + 4;
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public final int serialize(int offset, byte[] data) {
        int dataSize = getDataSize();
        int recSize = dataSize + 4;
        try {
            LittleEndianByteArrayOutputStream out = new LittleEndianByteArrayOutputStream(data, offset, recSize);
            try {
                out.writeShort(getSid());
                out.writeShort(dataSize);
                serialize(out);
                if (out.getWriteIndex() - offset != recSize) {
                    throw new IllegalStateException("Error in serialization of (" + getClass().getName() + "): Incorrect number of bytes written - expected " + recSize + " but got " + (out.getWriteIndex() - offset));
                }
                out.close();
                return recSize;
            } finally {
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
