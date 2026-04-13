package org.apache.poi.xssf.binary;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInputStream;

@Internal
/* loaded from: classes10.dex */
public abstract class XSSFBParser {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static int MAX_RECORD_LENGTH = 1000000;
    private final LittleEndianInputStream is;
    private final SparseBitSet records;

    public abstract void handleRecord(int i, byte[] bArr) throws XSSFBParseException;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public XSSFBParser(InputStream is) {
        this.is = new LittleEndianInputStream(is);
        this.records = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFBParser(InputStream is, SparseBitSet bitSet) {
        this.is = new LittleEndianInputStream(is);
        this.records = bitSet;
    }

    public void parse() throws IOException {
        while (true) {
            int bInt = this.is.read();
            if (bInt == -1) {
                return;
            } else {
                readNext((byte) bInt);
            }
        }
    }

    private void readNext(byte b1) throws IOException {
        int recordId;
        if (((b1 >> 7) & 1) == 1) {
            byte b2 = this.is.readByte();
            recordId = (((byte) (b2 & ByteCompanionObject.MAX_VALUE)) << 7) + ((byte) (b1 & ByteCompanionObject.MAX_VALUE));
        } else {
            recordId = b1;
        }
        long recordLength = 0;
        boolean halt = false;
        for (int i = 0; i < 4 && !halt; i++) {
            byte b = this.is.readByte();
            halt = ((b >> 7) & 1) == 0;
            recordLength += ((byte) (b & ByteCompanionObject.MAX_VALUE)) << (i * 7);
        }
        if (this.records == null || this.records.get(recordId)) {
            byte[] buff = IOUtils.safelyAllocate(recordLength, getMaxRecordLength());
            this.is.readFully(buff);
            handleRecord(recordId, buff);
        } else {
            long length = IOUtils.skipFully(this.is, recordLength);
            if (length != recordLength) {
                throw new XSSFBParseException("End of file reached before expected.\tTried to skip " + recordLength + ", but only skipped " + length);
            }
        }
    }
}
