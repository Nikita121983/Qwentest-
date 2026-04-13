package org.apache.commons.io.input;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes9.dex */
public final class MemoryMappedFileInputStream extends AbstractInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 262144;
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.wrap(new byte[0]).asReadOnlyBuffer();
    private ByteBuffer buffer;
    private final int bufferSize;
    private final FileChannel channel;
    private long nextBufferPosition;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<MemoryMappedFileInputStream, Builder> {
        public Builder() {
            setBufferSizeDefault(262144);
            setBufferSize(262144);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public MemoryMappedFileInputStream get() throws IOException {
            return new MemoryMappedFileInputStream(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private MemoryMappedFileInputStream(Builder builder) throws IOException {
        this.buffer = EMPTY_BUFFER;
        this.bufferSize = builder.getBufferSize();
        this.channel = FileChannel.open(builder.getPath(), StandardOpenOption.READ);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.buffer.remaining();
    }

    private void cleanBuffer() {
        if (ByteBufferCleaner.isSupported() && this.buffer.isDirect()) {
            ByteBufferCleaner.clean(this.buffer);
        }
    }

    @Override // org.apache.commons.io.input.AbstractInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!isClosed()) {
            cleanBuffer();
            this.buffer = EMPTY_BUFFER;
            this.channel.close();
            super.close();
        }
    }

    int getBufferSize() {
        return this.bufferSize;
    }

    private void nextBuffer() throws IOException {
        long remainingInFile = this.channel.size() - this.nextBufferPosition;
        if (remainingInFile > 0) {
            long amountToMap = Math.min(remainingInFile, this.bufferSize);
            cleanBuffer();
            this.buffer = this.channel.map(FileChannel.MapMode.READ_ONLY, this.nextBufferPosition, amountToMap);
            this.nextBufferPosition += amountToMap;
            return;
        }
        this.buffer = EMPTY_BUFFER;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        checkOpen();
        if (!this.buffer.hasRemaining()) {
            nextBuffer();
            if (!this.buffer.hasRemaining()) {
                return -1;
            }
        }
        return Short.toUnsignedInt(this.buffer.get());
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(b, off, len);
        if (len == 0) {
            return 0;
        }
        checkOpen();
        if (!this.buffer.hasRemaining()) {
            nextBuffer();
            if (!this.buffer.hasRemaining()) {
                return -1;
            }
        }
        int numBytes = Math.min(this.buffer.remaining(), len);
        this.buffer.get(b, off, numBytes);
        return numBytes;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        checkOpen();
        if (n <= 0) {
            return 0L;
        }
        if (n <= this.buffer.remaining()) {
            this.buffer.position((int) (this.buffer.position() + n));
            return n;
        }
        long remainingInFile = this.channel.size() - this.nextBufferPosition;
        long skipped = this.buffer.remaining() + Math.min(remainingInFile, n - this.buffer.remaining());
        this.nextBufferPosition += skipped - this.buffer.remaining();
        nextBuffer();
        return skipped;
    }
}
