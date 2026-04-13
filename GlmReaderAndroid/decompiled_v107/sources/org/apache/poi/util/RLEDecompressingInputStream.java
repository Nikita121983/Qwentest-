package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import kotlin.UByte;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

/* loaded from: classes10.dex */
public class RLEDecompressingInputStream extends InputStream {
    private static final int[] POWER2 = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
    private final InputStream in;
    private int len;
    private final byte[] buf = new byte[4096];
    private int pos = 0;

    public RLEDecompressingInputStream(InputStream in) throws IOException {
        this.in = in;
        int header = in.read();
        if (header != 1) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Header byte 0x01 expected, received 0x%02X", Integer.valueOf(header & 255)));
        }
        this.len = readChunk();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.len == -1) {
            return -1;
        }
        if (this.pos >= this.len) {
            int readChunk = readChunk();
            this.len = readChunk;
            if (readChunk == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int l) throws IOException {
        if (this.len == -1) {
            return -1;
        }
        int offset = off;
        int length = l;
        while (length > 0) {
            if (this.pos >= this.len) {
                int readChunk = readChunk();
                this.len = readChunk;
                if (readChunk == -1) {
                    if (offset > off) {
                        return offset - off;
                    }
                    return -1;
                }
            }
            int c = Math.min(length, this.len - this.pos);
            System.arraycopy(this.buf, this.pos, b, offset, c);
            this.pos += c;
            length -= c;
            offset += c;
        }
        return l;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        long length = n;
        while (length > 0) {
            if (this.pos >= this.len) {
                int readChunk = readChunk();
                this.len = readChunk;
                if (readChunk == -1) {
                    return -1L;
                }
            }
            int c = (int) Math.min(n, this.len - this.pos);
            this.pos += c;
            length -= c;
        }
        return n;
    }

    @Override // java.io.InputStream
    public int available() {
        if (this.len > 0) {
            return this.len - this.pos;
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    private int readChunk() throws IOException {
        int i;
        this.pos = 0;
        int w = readShort(this.in);
        int i2 = -1;
        if (w != -1 && w != 0) {
            int i3 = 1;
            int chunkSize = (w & 4095) + 1;
            if ((w & 28672) != 12288) {
                throw new IllegalArgumentException(String.format(Locale.ROOT, "Chunksize header A should be 0x3000, received 0x%04X", Integer.valueOf(57344 & w)));
            }
            boolean rawChunk = (32768 & w) == 0;
            if (rawChunk) {
                if (IOUtils.readFully(this.in, this.buf, 0, chunkSize) < chunkSize) {
                    throw new IllegalStateException(String.format(Locale.ROOT, "Not enough bytes read, expected %d", Integer.valueOf(chunkSize)));
                }
                return chunkSize;
            }
            int inOffset = 0;
            int outOffset = 0;
            while (inOffset < chunkSize) {
                int tokenFlags = this.in.read();
                inOffset++;
                if (tokenFlags == i2) {
                    break;
                }
                int n = 0;
                while (n < 8 && inOffset < chunkSize) {
                    if ((POWER2[n] & tokenFlags) != 0) {
                        int token = readShort(this.in);
                        if (token == i2) {
                            return i2;
                        }
                        inOffset += 2;
                        int copyLenBits = getCopyLenBits(outOffset - 1);
                        int copyOffset = (token >> copyLenBits) + i3;
                        int copyLen = ((POWER2[copyLenBits] - i3) & token) + 3;
                        int startPos = outOffset - copyOffset;
                        int endPos = startPos + copyLen;
                        i = i2;
                        int i4 = startPos;
                        while (i4 < endPos) {
                            this.buf[outOffset] = this.buf[i4];
                            i4++;
                            outOffset++;
                            inOffset = inOffset;
                        }
                    } else {
                        int b = this.in.read();
                        if (b == i2) {
                            return i2;
                        }
                        this.buf[outOffset] = (byte) b;
                        inOffset++;
                        i = i2;
                        outOffset++;
                    }
                    n++;
                    i2 = i;
                    i3 = 1;
                }
                i2 = i2;
                i3 = 1;
            }
            return outOffset;
        }
        return -1;
    }

    static int getCopyLenBits(int offset) {
        for (int n = 11; n >= 4; n--) {
            if ((POWER2[n] & offset) != 0) {
                return 15 - n;
            }
        }
        return 12;
    }

    public int readShort() throws IOException {
        return readShort(this);
    }

    public int readInt() throws IOException {
        return readInt(this);
    }

    private int readShort(InputStream stream) throws IOException {
        int b1;
        int b0 = stream.read();
        if (b0 == -1 || (b1 = stream.read()) == -1) {
            return -1;
        }
        return (b0 & 255) | ((b1 & 255) << 8);
    }

    private int readInt(InputStream stream) throws IOException {
        int b1;
        int b2;
        int b3;
        int b0 = stream.read();
        if (b0 == -1 || (b1 = stream.read()) == -1 || (b2 = stream.read()) == -1 || (b3 = stream.read()) == -1) {
            return -1;
        }
        return (b0 & 255) | ((b1 & 255) << 8) | ((b2 & 255) << 16) | ((b3 & 255) << 24);
    }

    public static byte[] decompress(byte[] compressed) throws IOException {
        return decompress(compressed, 0, compressed.length);
    }

    public static byte[] decompress(byte[] compressed, int offset, int length) throws IOException {
        UnsynchronizedByteArrayOutputStream out = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            InputStream instream = UnsynchronizedByteArrayInputStream.builder().setByteArray(compressed).setOffset(offset).setLength(length).get();
            try {
                InputStream stream = new RLEDecompressingInputStream(instream);
                try {
                    IOUtils.copy(stream, out);
                    byte[] byteArray = out.toByteArray();
                    stream.close();
                    if (instream != null) {
                        instream.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    return byteArray;
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
