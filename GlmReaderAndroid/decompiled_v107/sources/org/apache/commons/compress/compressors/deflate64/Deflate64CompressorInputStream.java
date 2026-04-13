package org.apache.commons.compress.compressors.deflate64;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class Deflate64CompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private long compressedBytesRead;
    private HuffmanDecoder decoder;
    private final byte[] oneByte;
    private InputStream originalStream;

    Deflate64CompressorInputStream(HuffmanDecoder decoder) {
        this.oneByte = new byte[1];
        this.decoder = decoder;
    }

    public Deflate64CompressorInputStream(InputStream in) {
        this(new HuffmanDecoder(in));
        this.originalStream = in;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.decoder != null) {
            return this.decoder.available();
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            closeDecoder();
        } finally {
            IOUtils.close(this.originalStream);
            this.originalStream = null;
        }
    }

    private void closeDecoder() {
        Closeable c = this.decoder;
        IOUtils.closeQuietly(c);
        this.decoder = null;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.compressedBytesRead;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            int r = read(this.oneByte);
            switch (r) {
                case -1:
                    return -1;
                case 0:
                case 1:
                    return this.oneByte[0] & UByte.MAX_VALUE;
                default:
                    throw new IllegalStateException("Invalid return value from read: " + r);
            }
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int read = -1;
        if (this.decoder != null) {
            try {
                read = this.decoder.decode(b, off, len);
                this.compressedBytesRead = this.decoder.getBytesRead();
                count(read);
                if (read == -1) {
                    closeDecoder();
                }
            } catch (RuntimeException ex) {
                throw new IOException("Invalid Deflate64 input", ex);
            }
        }
        return read;
    }
}
