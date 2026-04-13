package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/* loaded from: classes9.dex */
final class SeekableChannelRandomAccessOutputStream extends RandomAccessOutputStream {
    private final SeekableByteChannel channel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SeekableChannelRandomAccessOutputStream(SeekableByteChannel channel) {
        this.channel = channel;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.channel.close();
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public synchronized long position() throws IOException {
        return this.channel.position();
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        ZipIoUtil.writeAll(this.channel, ByteBuffer.wrap(b, off, len));
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public synchronized void writeAll(byte[] b, int off, int len, long position) throws IOException {
        long saved = this.channel.position();
        try {
            this.channel.position(position);
            ZipIoUtil.writeAll(this.channel, ByteBuffer.wrap(b, off, len));
        } finally {
            this.channel.position(saved);
        }
    }
}
