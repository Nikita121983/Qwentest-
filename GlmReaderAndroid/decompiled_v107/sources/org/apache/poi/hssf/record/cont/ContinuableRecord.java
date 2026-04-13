package org.apache.poi.hssf.record.cont;

import java.io.IOException;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;

/* loaded from: classes10.dex */
public abstract class ContinuableRecord extends Record {
    protected abstract void serialize(ContinuableRecordOutput continuableRecordOutput);

    /* JADX INFO: Access modifiers changed from: protected */
    public ContinuableRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContinuableRecord(ContinuableRecord other) {
        super(other);
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public final int getRecordSize() {
        ContinuableRecordOutput out = ContinuableRecordOutput.createForCountingOnly();
        serialize(out);
        out.terminate();
        return out.getTotalSize();
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public final int serialize(int offset, byte[] data) {
        try {
            LittleEndianByteArrayOutputStream leo = new LittleEndianByteArrayOutputStream(data, offset);
            try {
                ContinuableRecordOutput out = new ContinuableRecordOutput(leo, getSid());
                serialize(out);
                out.terminate();
                int totalSize = out.getTotalSize();
                leo.close();
                return totalSize;
            } finally {
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
