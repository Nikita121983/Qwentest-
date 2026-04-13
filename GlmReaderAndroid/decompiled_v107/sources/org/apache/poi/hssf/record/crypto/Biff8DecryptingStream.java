package org.apache.poi.hssf.record.crypto;

import java.io.InputStream;
import java.io.PushbackInputStream;
import kotlin.UByte;
import org.apache.poi.hssf.record.BiffHeaderInput;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class Biff8DecryptingStream implements BiffHeaderInput, LittleEndianInput {
    public static final int RC4_REKEYING_INTERVAL = 1024;
    private final byte[] buffer = new byte[8];
    private final ChunkedCipherInputStream ccis;
    private boolean shouldSkipEncryptionOnCurrentRecord;

    public Biff8DecryptingStream(InputStream in, int initialOffset, EncryptionInfo info) throws RecordFormatException {
        InputStream stream;
        try {
            byte[] initialBuf = IOUtils.safelyAllocate(initialOffset, HSSFWorkbook.getMaxRecordLength());
            if (initialOffset == 0) {
                stream = in;
            } else {
                stream = new PushbackInputStream(in, initialOffset);
                ((PushbackInputStream) stream).unread(initialBuf);
            }
            Decryptor dec = info.getDecryptor();
            dec.setChunkSize(1024);
            this.ccis = (ChunkedCipherInputStream) dec.getDataStream(stream, Integer.MAX_VALUE, 0);
            if (initialOffset > 0) {
                this.ccis.readFully(initialBuf);
            }
        } catch (Exception e) {
            throw new RecordFormatException(e);
        }
    }

    @Override // org.apache.poi.hssf.record.BiffHeaderInput
    public int available() {
        return this.ccis.available();
    }

    @Override // org.apache.poi.hssf.record.BiffHeaderInput
    public int readRecordSID() {
        readPlain(this.buffer, 0, 2);
        int sid = LittleEndian.getUShort(this.buffer, 0);
        this.shouldSkipEncryptionOnCurrentRecord = isNeverEncryptedRecord(sid);
        return sid;
    }

    @Override // org.apache.poi.hssf.record.BiffHeaderInput
    public int readDataSize() {
        readPlain(this.buffer, 0, 2);
        int dataSize = LittleEndian.getUShort(this.buffer, 0);
        this.ccis.setNextRecordSize(dataSize);
        return dataSize;
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public double readDouble() {
        long valueLongBits = readLong();
        double result = Double.longBitsToDouble(valueLongBits);
        if (Double.isNaN(result)) {
            throw new IllegalStateException("Did not expect to read NaN");
        }
        return result;
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public void readFully(byte[] buf) {
        readFully(buf, 0, buf.length);
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public void readFully(byte[] buf, int off, int len) {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(buf, off, buf.length);
        } else {
            this.ccis.readFully(buf, off, len);
        }
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readUByte() {
        return readByte() & UByte.MAX_VALUE;
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public byte readByte() {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(this.buffer, 0, 1);
            return this.buffer[0];
        }
        return this.ccis.readByte();
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readUShort() {
        return readShort() & 65535;
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public short readShort() {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(this.buffer, 0, 2);
            return LittleEndian.getShort(this.buffer);
        }
        return this.ccis.readShort();
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readInt() {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(this.buffer, 0, 4);
            return LittleEndian.getInt(this.buffer);
        }
        return this.ccis.readInt();
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public long readLong() {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(this.buffer, 0, 8);
            return LittleEndian.getLong(this.buffer);
        }
        return this.ccis.readLong();
    }

    public long getPosition() {
        return this.ccis.getPos();
    }

    public static boolean isNeverEncryptedRecord(int sid) {
        switch (sid) {
            case 47:
            case 225:
            case 2057:
                return true;
            default:
                return false;
        }
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public void readPlain(byte[] b, int off, int len) {
        this.ccis.readPlain(b, off, len);
    }

    @Internal
    public boolean isCurrentRecordEncrypted() {
        return !this.shouldSkipEncryptionOnCurrentRecord;
    }
}
