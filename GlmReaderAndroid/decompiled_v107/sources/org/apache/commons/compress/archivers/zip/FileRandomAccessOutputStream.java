package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/* loaded from: classes9.dex */
final class FileRandomAccessOutputStream extends RandomAccessOutputStream {
    private final FileChannel channel;
    private long position;

    FileRandomAccessOutputStream(FileChannel channel) {
        this.channel = (FileChannel) Objects.requireNonNull(channel, "channel");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileRandomAccessOutputStream(Path file) throws IOException {
        this(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileRandomAccessOutputStream(Path file, OpenOption... options) throws IOException {
        this(FileChannel.open(file, options));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileChannel channel() {
        return this.channel;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.channel.isOpen()) {
            this.channel.close();
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public synchronized long position() {
        return this.position;
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        ZipIoUtil.writeAll(this.channel, ByteBuffer.wrap(b, off, len));
        this.position += len;
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public void writeAll(byte[] b, int off, int len, long pos) throws IOException {
        ZipIoUtil.writeAll(this.channel, ByteBuffer.wrap(b, off, len), pos);
        this.position += len;
    }
}
