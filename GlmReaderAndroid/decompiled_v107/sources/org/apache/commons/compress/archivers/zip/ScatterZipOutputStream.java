package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public class ScatterZipOutputStream implements Closeable {
    private final ScatterGatherBackingStore backingStore;
    private final StreamCompressor streamCompressor;
    private ZipEntryWriter zipEntryWriter;
    private final Queue<CompressedEntry> items = new ConcurrentLinkedQueue();
    private final AtomicBoolean isClosed = new AtomicBoolean();

    /* loaded from: classes9.dex */
    private static final class CompressedEntry {
        final long compressedSize;
        final long crc;
        final long size;
        final ZipArchiveEntryRequest zipArchiveEntryRequest;

        CompressedEntry(ZipArchiveEntryRequest zipArchiveEntryRequest, long crc, long compressedSize, long size) {
            this.zipArchiveEntryRequest = zipArchiveEntryRequest;
            this.crc = crc;
            this.compressedSize = compressedSize;
            this.size = size;
        }

        public ZipArchiveEntry transferToArchiveEntry() {
            ZipArchiveEntry entry = this.zipArchiveEntryRequest.getZipArchiveEntry();
            entry.setCompressedSize(this.compressedSize);
            entry.setSize(this.size);
            entry.setCrc(this.crc);
            entry.setMethod(this.zipArchiveEntryRequest.getMethod());
            return entry;
        }
    }

    /* loaded from: classes9.dex */
    public static class ZipEntryWriter implements Closeable {
        private final InputStream inputStream;
        private final Iterator<CompressedEntry> itemsIterator;

        public ZipEntryWriter(ScatterZipOutputStream out) throws IOException {
            out.backingStore.closeForWriting();
            this.itemsIterator = out.items.iterator();
            this.inputStream = out.backingStore.getInputStream();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            IOUtils.close(this.inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void writeNextZipEntry(ZipArchiveOutputStream target) throws IOException {
            CompressedEntry compressedEntry = this.itemsIterator.next();
            BoundedInputStream rawStream = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.inputStream)).setMaxCount(compressedEntry.compressedSize)).setPropagateClose(false)).get();
            try {
                target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
                if (rawStream != null) {
                    rawStream.close();
                }
            } catch (Throwable th) {
                if (rawStream != null) {
                    try {
                        rawStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    public static ScatterZipOutputStream fileBased(File file) throws FileNotFoundException {
        return pathBased(file.toPath(), -1);
    }

    public static ScatterZipOutputStream fileBased(File file, int compressionLevel) throws FileNotFoundException {
        return pathBased(file.toPath(), compressionLevel);
    }

    public static ScatterZipOutputStream pathBased(Path path) throws FileNotFoundException {
        return pathBased(path, -1);
    }

    public static ScatterZipOutputStream pathBased(Path path, int compressionLevel) throws FileNotFoundException {
        ScatterGatherBackingStore bs = new FileBasedScatterGatherBackingStore(path);
        StreamCompressor sc = StreamCompressor.create(compressionLevel, bs);
        return new ScatterZipOutputStream(bs, sc);
    }

    public ScatterZipOutputStream(ScatterGatherBackingStore backingStore, StreamCompressor streamCompressor) {
        this.backingStore = backingStore;
        this.streamCompressor = streamCompressor;
    }

    public void addArchiveEntry(ZipArchiveEntryRequest zipArchiveEntryRequest) throws IOException {
        InputStream payloadStream = zipArchiveEntryRequest.getPayloadStream();
        try {
            this.streamCompressor.deflate(payloadStream, zipArchiveEntryRequest.getMethod());
            if (payloadStream != null) {
                payloadStream.close();
            }
            this.items.add(new CompressedEntry(zipArchiveEntryRequest, this.streamCompressor.getCrc32(), this.streamCompressor.getBytesWrittenForLastEntry(), this.streamCompressor.getBytesRead()));
        } catch (Throwable th) {
            if (payloadStream == null) {
                throw th;
            }
            try {
                payloadStream.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.isClosed.compareAndSet(false, true)) {
            return;
        }
        try {
            IOUtils.close(this.zipEntryWriter);
            this.backingStore.close();
        } finally {
            this.streamCompressor.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeTo(ZipArchiveOutputStream target) throws IOException {
        this.backingStore.closeForWriting();
        InputStream data = this.backingStore.getInputStream();
        try {
            for (CompressedEntry compressedEntry : this.items) {
                BoundedInputStream rawStream = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(data)).setMaxCount(compressedEntry.compressedSize)).setPropagateClose(false)).get();
                try {
                    target.addRawArchiveEntry(compressedEntry.transferToArchiveEntry(), rawStream);
                    if (rawStream != null) {
                        rawStream.close();
                    }
                } catch (Throwable th) {
                    if (rawStream != null) {
                        try {
                            rawStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (data != null) {
                data.close();
            }
        } catch (Throwable th3) {
            if (data != null) {
                try {
                    data.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }

    public ZipEntryWriter zipEntryWriter() throws IOException {
        if (this.zipEntryWriter == null) {
            this.zipEntryWriter = new ZipEntryWriter(this);
        }
        return this.zipEntryWriter;
    }
}
