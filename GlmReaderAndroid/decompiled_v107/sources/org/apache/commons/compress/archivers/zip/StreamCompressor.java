package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

/* loaded from: classes9.dex */
public abstract class StreamCompressor implements Closeable {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFLATER_BLOCK_SIZE = 8192;
    private final Deflater deflater;
    private long sourcePayloadLength;
    private long totalWrittenToOutputStream;
    private long writtenToOutputStreamForLastEntry;
    private final CRC32 crc = new CRC32();
    private final byte[] outputBuffer = new byte[4096];
    private final byte[] readerBuf = new byte[4096];

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void writeOut(byte[] bArr, int i, int i2) throws IOException;

    /* loaded from: classes9.dex */
    private static final class DataOutputCompressor extends StreamCompressor {
        private final DataOutput raf;

        DataOutputCompressor(Deflater deflater, DataOutput raf) {
            super(deflater);
            this.raf = raf;
        }

        @Override // org.apache.commons.compress.archivers.zip.StreamCompressor
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.raf.write(data, offset, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class OutputStreamCompressor extends StreamCompressor {
        private final OutputStream os;

        OutputStreamCompressor(Deflater deflater, OutputStream os) {
            super(deflater);
            this.os = os;
        }

        @Override // org.apache.commons.compress.archivers.zip.StreamCompressor
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.os.write(data, offset, length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ScatterGatherBackingStoreCompressor extends StreamCompressor {
        private final ScatterGatherBackingStore bs;

        ScatterGatherBackingStoreCompressor(Deflater deflater, ScatterGatherBackingStore bs) {
            super(deflater);
            this.bs = bs;
        }

        @Override // org.apache.commons.compress.archivers.zip.StreamCompressor
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.bs.writeOut(data, offset, length);
        }
    }

    /* loaded from: classes9.dex */
    private static final class SeekableByteChannelCompressor extends StreamCompressor {
        private final SeekableByteChannel channel;

        SeekableByteChannelCompressor(Deflater deflater, SeekableByteChannel channel) {
            super(deflater);
            this.channel = channel;
        }

        @Override // org.apache.commons.compress.archivers.zip.StreamCompressor
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.channel.write(ByteBuffer.wrap(data, offset, length));
        }
    }

    static StreamCompressor create(DataOutput os, Deflater deflater) {
        return new DataOutputCompressor(deflater, os);
    }

    public static StreamCompressor create(int compressionLevel, ScatterGatherBackingStore bs) {
        Deflater deflater = new Deflater(compressionLevel, true);
        return new ScatterGatherBackingStoreCompressor(deflater, bs);
    }

    static StreamCompressor create(OutputStream os) {
        return create(os, new Deflater(-1, true));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StreamCompressor create(OutputStream os, Deflater deflater) {
        return new OutputStreamCompressor(deflater, os);
    }

    public static StreamCompressor create(ScatterGatherBackingStore bs) {
        return create(-1, bs);
    }

    static StreamCompressor create(SeekableByteChannel os, Deflater deflater) {
        return new SeekableByteChannelCompressor(deflater, os);
    }

    StreamCompressor(Deflater deflater) {
        this.deflater = deflater;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.deflater.end();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deflate() throws IOException {
        int len = this.deflater.deflate(this.outputBuffer, 0, this.outputBuffer.length);
        if (len > 0) {
            writeCounted(this.outputBuffer, 0, len);
        }
    }

    public void deflate(InputStream source, int method) throws IOException {
        reset();
        while (true) {
            int length = source.read(this.readerBuf, 0, this.readerBuf.length);
            if (length < 0) {
                break;
            } else {
                write(this.readerBuf, 0, length, method);
            }
        }
        if (method == 8) {
            flushDeflater();
        }
    }

    private void deflateUntilInputIsNeeded() throws IOException {
        while (!this.deflater.needsInput()) {
            deflate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushDeflater() throws IOException {
        this.deflater.finish();
        while (!this.deflater.finished()) {
            deflate();
        }
    }

    public long getBytesRead() {
        return this.sourcePayloadLength;
    }

    public long getBytesWrittenForLastEntry() {
        return this.writtenToOutputStreamForLastEntry;
    }

    public long getCrc32() {
        return this.crc.getValue();
    }

    public long getTotalBytesWritten() {
        return this.totalWrittenToOutputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.crc.reset();
        this.deflater.reset();
        this.sourcePayloadLength = 0L;
        this.writtenToOutputStreamForLastEntry = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long write(byte[] b, int offset, int length, int method) throws IOException {
        long current = this.writtenToOutputStreamForLastEntry;
        this.crc.update(b, offset, length);
        if (method == 8) {
            writeDeflated(b, offset, length);
        } else {
            writeCounted(b, offset, length);
        }
        this.sourcePayloadLength += length;
        return this.writtenToOutputStreamForLastEntry - current;
    }

    public void writeCounted(byte[] data) throws IOException {
        writeCounted(data, 0, data.length);
    }

    public void writeCounted(byte[] data, int offset, int length) throws IOException {
        writeOut(data, offset, length);
        this.writtenToOutputStreamForLastEntry += length;
        this.totalWrittenToOutputStream += length;
    }

    private void writeDeflated(byte[] b, int offset, int length) throws IOException {
        if (length > 0 && !this.deflater.finished()) {
            if (length <= 8192) {
                this.deflater.setInput(b, offset, length);
                deflateUntilInputIsNeeded();
                return;
            }
            int fullblocks = length / 8192;
            for (int i = 0; i < fullblocks; i++) {
                this.deflater.setInput(b, (i * 8192) + offset, 8192);
                deflateUntilInputIsNeeded();
            }
            int done = fullblocks * 8192;
            if (done < length) {
                this.deflater.setInput(b, offset + done, length - done);
                deflateUntilInputIsNeeded();
            }
        }
    }
}
