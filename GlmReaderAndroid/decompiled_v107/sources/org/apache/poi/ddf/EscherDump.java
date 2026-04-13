package org.apache.poi.ddf;

import java.io.PrintStream;

/* loaded from: classes10.dex */
public final class EscherDump {
    public void dump(byte[] data, int offset, int size, PrintStream out) {
        EscherRecordFactory recordFactory = new DefaultEscherRecordFactory();
        int pos = offset;
        while (pos < offset + size) {
            EscherRecord r = recordFactory.createRecord(data, pos);
            int bytesRead = r.fillFields(data, pos, recordFactory);
            out.println(r);
            pos += bytesRead;
        }
    }

    public void dump(int recordSize, byte[] data, PrintStream out) {
        dump(data, 0, recordSize, out);
    }
}
