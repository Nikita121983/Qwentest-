package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes9.dex */
public final class BufferedFileChannelInputStream extends InputStream {
    private final ByteBuffer byteBuffer;
    private final FileChannel fileChannel;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<BufferedFileChannelInputStream, Builder> {
        private FileChannel fileChannel;

        @Override // org.apache.commons.io.function.IOSupplier
        public BufferedFileChannelInputStream get() throws IOException {
            return new BufferedFileChannelInputStream(this);
        }

        public Builder setFileChannel(FileChannel fileChannel) {
            this.fileChannel = fileChannel;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private BufferedFileChannelInputStream(Builder builder) throws IOException {
        this.fileChannel = builder.fileChannel != null ? builder.fileChannel : FileChannel.open(builder.getPath(), StandardOpenOption.READ);
        this.byteBuffer = ByteBuffer.allocateDirect(builder.getBufferSize());
        this.byteBuffer.flip();
    }

    @Deprecated
    public BufferedFileChannelInputStream(File file) throws IOException {
        this(file, 8192);
    }

    @Deprecated
    public BufferedFileChannelInputStream(File file, int bufferSize) throws IOException {
        this(file.toPath(), bufferSize);
    }

    @Deprecated
    public BufferedFileChannelInputStream(Path path) throws IOException {
        this(path, 8192);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public BufferedFileChannelInputStream(Path path, int bufferSize) throws IOException {
        this(((Builder) builder().setPath(path)).setBufferSize(bufferSize));
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        if (!this.fileChannel.isOpen()) {
            return 0;
        }
        if (!refill()) {
            return 0;
        }
        return this.byteBuffer.remaining();
    }

    private void clean(ByteBuffer buffer) {
        if (buffer.isDirect()) {
            cleanDirectBuffer(buffer);
        }
    }

    private void cleanDirectBuffer(ByteBuffer buffer) {
        if (ByteBufferCleaner.isSupported()) {
            ByteBufferCleaner.clean(buffer);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        try {
            this.fileChannel.close();
        } finally {
            clean(this.byteBuffer);
        }
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (!refill()) {
            return -1;
        }
        return this.byteBuffer.get() & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] b, int offset, int len) throws IOException {
        IOUtils.checkFromIndexSize(b, offset, len);
        if (len == 0) {
            return 0;
        }
        if (!refill()) {
            return -1;
        }
        int len2 = Math.min(len, this.byteBuffer.remaining());
        this.byteBuffer.get(b, offset, len2);
        return len2;
    }

    private boolean refill() throws IOException {
        Input.checkOpen(this.fileChannel.isOpen());
        if (this.byteBuffer.hasRemaining()) {
            return true;
        }
        this.byteBuffer.clear();
        int nRead = 0;
        while (nRead == 0) {
            nRead = this.fileChannel.read(this.byteBuffer);
        }
        this.byteBuffer.flip();
        return nRead >= 0;
    }

    @Override // java.io.InputStream
    public synchronized long skip(long n) throws IOException {
        if (n <= 0) {
            return 0L;
        }
        if (this.byteBuffer.remaining() >= n) {
            this.byteBuffer.position(this.byteBuffer.position() + ((int) n));
            return n;
        }
        long skippedFromBuffer = this.byteBuffer.remaining();
        long toSkipFromFileChannel = n - skippedFromBuffer;
        this.byteBuffer.position(0);
        this.byteBuffer.flip();
        return skipFromFileChannel(toSkipFromFileChannel) + skippedFromBuffer;
    }

    private long skipFromFileChannel(long n) throws IOException {
        long currentFilePosition = this.fileChannel.position();
        long size = this.fileChannel.size();
        if (n > size - currentFilePosition) {
            this.fileChannel.position(size);
            return size - currentFilePosition;
        }
        this.fileChannel.position(currentFilePosition + n);
        return n;
    }
}
