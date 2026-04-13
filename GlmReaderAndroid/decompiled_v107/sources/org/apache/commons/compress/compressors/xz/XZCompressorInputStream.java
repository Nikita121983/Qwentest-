package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BoundedInputStream;
import org.tukaani.xz.MemoryLimitException;
import org.tukaani.xz.SingleXZInputStream;
import org.tukaani.xz.XZ;
import org.tukaani.xz.XZInputStream;

/* loaded from: classes9.dex */
public class XZCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private final BoundedInputStream countingStream;
    private final InputStream in;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<XZCompressorInputStream, Builder> {
        private boolean decompressConcatenated;
        private int memoryLimitKiB = -1;

        @Override // org.apache.commons.io.function.IOSupplier
        public XZCompressorInputStream get() throws IOException {
            return new XZCompressorInputStream(this);
        }

        public Builder setDecompressConcatenated(boolean decompressConcatenated) {
            this.decompressConcatenated = decompressConcatenated;
            return this;
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
        if (length < XZ.HEADER_MAGIC.length) {
            return false;
        }
        for (int i = 0; i < XZ.HEADER_MAGIC.length; i++) {
            if (signature[i] != XZ.HEADER_MAGIC[i]) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private XZCompressorInputStream(Builder builder) throws IOException {
        this.countingStream = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(builder.getInputStream())).get();
        if (builder.decompressConcatenated) {
            this.in = new XZInputStream(this.countingStream, builder.memoryLimitKiB);
        } else {
            this.in = new SingleXZInputStream(this.countingStream, builder.memoryLimitKiB);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XZCompressorInputStream(InputStream inputStream) throws IOException {
        this((Builder) builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public XZCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
        this(((Builder) builder().setInputStream(inputStream)).setDecompressConcatenated(decompressConcatenated));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public XZCompressorInputStream(InputStream inputStream, boolean decompressConcatenated, int memoryLimitKiB) throws IOException {
        this(((Builder) builder().setInputStream(inputStream)).setDecompressConcatenated(decompressConcatenated).setMemoryLimitKiB(memoryLimitKiB));
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
        try {
            int ret = this.in.read();
            int i = -1;
            if (ret != -1) {
                i = 1;
            }
            count(i);
            return ret;
        } catch (MemoryLimitException e) {
            throw new org.apache.commons.compress.MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), (Throwable) e);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        try {
            int ret = this.in.read(buf, off, len);
            count(ret);
            return ret;
        } catch (MemoryLimitException e) {
            throw new org.apache.commons.compress.MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), (Throwable) e);
        }
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        try {
            return IOUtils.skip(this.in, n);
        } catch (MemoryLimitException e) {
            throw new org.apache.commons.compress.MemoryLimitException(e.getMemoryNeeded(), e.getMemoryLimit(), (Throwable) e);
        }
    }
}
