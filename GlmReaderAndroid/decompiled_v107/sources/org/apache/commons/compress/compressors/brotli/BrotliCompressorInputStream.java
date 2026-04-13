package org.apache.commons.compress.compressors.brotli;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.brotli.dec.BrotliInputStream;

/* loaded from: classes9.dex */
public class BrotliCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final BrotliInputStream brotliInputStream;
    private final BoundedInputStream countingInputStream;

    /* JADX WARN: Multi-variable type inference failed */
    public BrotliCompressorInputStream(InputStream inputStream) throws IOException {
        BoundedInputStream boundedInputStream = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(inputStream)).get();
        this.countingInputStream = boundedInputStream;
        this.brotliInputStream = new BrotliInputStream(boundedInputStream);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.brotliInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.brotliInputStream.close();
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingInputStream.getCount();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readLimit) {
        this.brotliInputStream.mark(readLimit);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.brotliInputStream.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int ret = this.brotliInputStream.read();
        count(ret == -1 ? 0 : 1);
        return ret;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return this.brotliInputStream.read(b);
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        int ret = this.brotliInputStream.read(buf, off, len);
        count(ret);
        return ret;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.brotliInputStream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        return IOUtils.skip((InputStream) this.brotliInputStream, n);
    }

    public String toString() {
        return this.brotliInputStream.toString();
    }
}
