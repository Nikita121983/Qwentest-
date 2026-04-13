package org.apache.poi.poifs.nio;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.IdentityHashMap;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public class FileBackedDataSource extends DataSource implements Closeable {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) FileBackedDataSource.class);
    private final IdentityHashMap<ByteBuffer, ByteBuffer> buffersToClean;
    private final FileChannel channel;
    private Long channelSize;
    private final boolean closeChannelOnClose;
    private final RandomAccessFile srcFile;
    private final boolean writable;

    public FileBackedDataSource(File file) throws FileNotFoundException {
        this(newSrcFile(file, "r"), true);
    }

    public FileBackedDataSource(File file, boolean readOnly) throws FileNotFoundException {
        this(newSrcFile(file, readOnly ? "r" : "rw"), readOnly);
    }

    public FileBackedDataSource(RandomAccessFile srcFile, boolean readOnly) {
        this(srcFile, srcFile.getChannel(), readOnly, false);
    }

    public FileBackedDataSource(FileChannel channel, boolean readOnly) {
        this(channel, readOnly, true);
    }

    public FileBackedDataSource(FileChannel channel, boolean readOnly, boolean closeChannelOnClose) {
        this(null, channel, readOnly, closeChannelOnClose);
    }

    private FileBackedDataSource(RandomAccessFile srcFile, FileChannel channel, boolean readOnly, boolean closeChannelOnClose) {
        this.buffersToClean = new IdentityHashMap<>();
        this.srcFile = srcFile;
        this.channel = channel;
        this.writable = !readOnly;
        this.closeChannelOnClose = closeChannelOnClose;
    }

    public boolean isWriteable() {
        return this.writable;
    }

    public FileChannel getChannel() {
        return this.channel;
    }

    @Override // org.apache.poi.poifs.nio.DataSource
    public ByteBuffer read(int length, long position) throws IOException {
        ByteBuffer dst;
        if (position >= size()) {
            throw new IndexOutOfBoundsException("Position " + position + " past the end of the file");
        }
        if (this.writable) {
            dst = this.channel.map(FileChannel.MapMode.READ_WRITE, position, length);
            this.buffersToClean.put(dst, dst);
        } else {
            this.channel.position(position);
            dst = ByteBuffer.allocate(length);
            int worked = IOUtils.readFully(this.channel, dst);
            if (worked == -1) {
                throw new IndexOutOfBoundsException("Position " + position + " past the end of the file");
            }
        }
        dst.position(0);
        return dst;
    }

    @Override // org.apache.poi.poifs.nio.DataSource
    public void write(ByteBuffer src, long position) throws IOException {
        this.channel.write(src, position);
        if (this.channelSize != null && position >= this.channelSize.longValue()) {
            this.channelSize = null;
        }
    }

    @Override // org.apache.poi.poifs.nio.DataSource
    public void copyTo(OutputStream stream) throws IOException {
        WritableByteChannel out = Channels.newChannel(stream);
        try {
            this.channel.transferTo(0L, this.channel.size(), out);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    @Override // org.apache.poi.poifs.nio.DataSource
    public long size() throws IOException {
        if (this.channelSize == null) {
            this.channelSize = Long.valueOf(this.channel.size());
        }
        return this.channelSize.longValue();
    }

    public void releaseBuffer(ByteBuffer buffer) {
        ByteBuffer previous = this.buffersToClean.remove(buffer);
        if (previous != null) {
            unmap(previous);
        }
    }

    @Override // org.apache.poi.poifs.nio.DataSource
    public void close() throws IOException {
        this.buffersToClean.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.nio.FileBackedDataSource$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FileBackedDataSource.unmap((ByteBuffer) obj2);
            }
        });
        this.buffersToClean.clear();
        if (this.srcFile != null) {
            this.srcFile.close();
        } else if (this.closeChannelOnClose) {
            this.channel.close();
        }
    }

    private static RandomAccessFile newSrcFile(File file, String mode) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        return new RandomAccessFile(file, mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void unmap(ByteBuffer buffer) {
        if (buffer.getClass().getName().endsWith("HeapByteBuffer")) {
            return;
        }
        if (CleanerUtil.UNMAP_SUPPORTED) {
            try {
                CleanerUtil.getCleaner().freeBuffer(buffer);
                return;
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("Failed to unmap the buffer");
                return;
            }
        }
        LOG.atDebug().log(CleanerUtil.UNMAP_NOT_SUPPORTED_REASON);
    }
}
