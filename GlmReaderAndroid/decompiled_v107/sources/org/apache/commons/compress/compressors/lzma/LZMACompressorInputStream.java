package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BoundedInputStream;
import org.tukaani.xz.LZMAInputStream;
import org.tukaani.xz.MemoryLimitException;

/* loaded from: classes9.dex */
public class LZMACompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final BoundedInputStream countingStream;
    private final InputStream in;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<LZMACompressorInputStream, Builder> {
        private int memoryLimitKiB = -1;

        @Override // org.apache.commons.io.function.IOSupplier
        public LZMACompressorInputStream get() throws IOException {
            return new LZMACompressorInputStream(this);
        }

        public Builder setMemoryLimitKiB(int memoryLimitKiB) {
            this.memoryLimitKiB = memoryLimitKiB;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static boolean matches(byte[] signature, int length) {
        return signature != null && length >= 3 && signature[0] == 93 && signature[1] == 0 && signature[2] == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LZMACompressorInputStream(Builder builder) throws IOException {
        try {
            BoundedInputStream boundedInputStream = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(builder.getInputStream())).get();
            this.countingStream = boundedInputStream;
            this.in = new LZMAInputStream(boundedInputStream, builder.memoryLimitKiB);
        } catch (MemoryLimitException e) {
            throw new org.apache.commons.compress.MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), (Throwable) e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LZMACompressorInputStream(InputStream inputStream) throws IOException {
        this((Builder) builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public LZMACompressorInputStream(InputStream inputStream, int memoryLimitKiB) throws IOException {
        this(((Builder) builder().setInputStream(inputStream)).setMemoryLimitKiB(memoryLimitKiB));
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getCount();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int ret = this.in.read();
        count(ret == -1 ? 0 : 1);
        return ret;
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        int ret = this.in.read(buf, off, len);
        count(ret);
        return ret;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        return IOUtils.skip(this.in, n);
    }
}
