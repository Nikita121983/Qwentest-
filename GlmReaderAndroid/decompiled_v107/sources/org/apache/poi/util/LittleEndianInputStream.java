package org.apache.poi.util;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes10.dex */
public class LittleEndianInputStream extends FilterInputStream implements LittleEndianInput {
    private static final int BUFFERED_SIZE = 8096;
    private static final int EOF = -1;
    private int markIndex;
    private int readIndex;

    public LittleEndianInputStream(InputStream is) {
        super(is.markSupported() ? is : new BufferedInputStream(is, BUFFERED_SIZE));
        this.readIndex = 0;
        this.markIndex = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, org.apache.poi.util.LittleEndianInput
    public int available() {
        try {
            return super.available();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public byte readByte() {
        return (byte) readUByte();
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readUByte() {
        byte[] buf = new byte[1];
        try {
            checkEOF(read(buf), 1);
            return LittleEndian.getUByte(buf);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readInt() {
        byte[] buf = new byte[4];
        try {
            checkEOF(read(buf), buf.length);
            return LittleEndian.getInt(buf);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public long readUInt() {
        long retNum = readInt();
        return 4294967295L & retNum;
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public long readLong() {
        byte[] buf = new byte[8];
        try {
            checkEOF(read(buf), 8);
            return LittleEndian.getLong(buf);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public short readShort() {
        return (short) readUShort();
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public int readUShort() {
        byte[] buf = new byte[2];
        try {
            checkEOF(read(buf), 2);
            return LittleEndian.getUShort(buf);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void checkEOF(int actualBytes, int expectedBytes) {
        if (expectedBytes != 0) {
            if (actualBytes == -1 || actualBytes != expectedBytes) {
                throw new IllegalStateException("Unexpected end-of-file");
            }
        }
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public void readFully(byte[] buf) {
        readFully(buf, 0, buf.length);
    }

    @Override // org.apache.poi.util.LittleEndianInput
    public void readFully(byte[] buf, int off, int len) {
        try {
            checkEOF(_read(buf, off, len), len);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int readBytes = super.read(b, off, len);
        this.readIndex += Math.max(0, readBytes);
        return readBytes;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
        this.markIndex = this.readIndex;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        if (this.markIndex > -1) {
            this.readIndex = this.markIndex;
            this.markIndex = -1;
        }
    }

    public int getReadIndex() {
        return this.readIndex;
    }

    private int _read(byte[] buffer, int offset, int length) throws IOException {
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = read(buffer, offset + location, remaining);
            if (-1 == count) {
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    public void readPlain(byte[] buf, int off, int len) {
        readFully(buf, off, len);
    }

    public void skipFully(int len) throws IOException {
        if (len == 0) {
            return;
        }
        long skipped = IOUtils.skipFully(this, len);
        if (skipped > 2147483647L) {
            throw new IOException("can't skip further than 2147483647");
        }
        checkEOF((int) skipped, len);
    }
}
