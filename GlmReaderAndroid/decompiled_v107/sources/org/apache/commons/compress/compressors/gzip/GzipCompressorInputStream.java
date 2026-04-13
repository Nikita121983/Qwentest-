package org.apache.commons.compress.compressors.gzip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public class GzipCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private static final IOConsumer<GzipCompressorInputStream> NOOP = IOConsumer.noop();
    private final byte[] buf;
    private int bufUsed;
    private final BoundedInputStream countingStream;
    private final CRC32 crc;
    private final boolean decompressConcatenated;
    private boolean endReached;
    private final Charset fileNameCharset;
    private final InputStream in;
    private Inflater inflater;
    private final IOConsumer<GzipCompressorInputStream> onMemberEnd;
    private final IOConsumer<GzipCompressorInputStream> onMemberStart;
    private final byte[] oneByte;
    private GzipParameters parameters;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<GzipCompressorInputStream, Builder> {
        private boolean decompressConcatenated;
        private Charset fileNameCharset = GzipUtils.GZIP_ENCODING;
        private IOConsumer<GzipCompressorInputStream> onMemberEnd;
        private IOConsumer<GzipCompressorInputStream> onMemberStart;

        @Override // org.apache.commons.io.function.IOSupplier
        public GzipCompressorInputStream get() throws IOException {
            return new GzipCompressorInputStream(this);
        }

        public Builder setDecompressConcatenated(boolean decompressConcatenated) {
            this.decompressConcatenated = decompressConcatenated;
            return this;
        }

        public Builder setFileNameCharset(Charset fileNameCharset) {
            this.fileNameCharset = fileNameCharset;
            return this;
        }

        public Builder setOnMemberEnd(IOConsumer<GzipCompressorInputStream> onMemberEnd) {
            this.onMemberEnd = onMemberEnd;
            return this;
        }

        public Builder setOnMemberStart(IOConsumer<GzipCompressorInputStream> onMemberStart) {
            this.onMemberStart = onMemberStart;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static boolean matches(byte[] signature, int length) {
        return length >= 2 && signature[0] == 31 && signature[1] == -117;
    }

    private static byte[] readToNull(DataInput inData) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (true) {
            try {
                int b = inData.readUnsignedByte();
                if (b != 0) {
                    bos.write(b);
                } else {
                    byte[] byteArray = bos.toByteArray();
                    bos.close();
                    return byteArray;
                }
            } catch (Throwable th) {
                try {
                    bos.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private GzipCompressorInputStream(Builder builder) throws IOException {
        this.buf = new byte[8192];
        this.crc = new CRC32();
        this.inflater = new Inflater(true);
        this.oneByte = new byte[1];
        this.countingStream = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(builder.getInputStream())).get();
        this.in = this.countingStream.markSupported() ? this.countingStream : new BufferedInputStream(this.countingStream);
        this.decompressConcatenated = builder.decompressConcatenated;
        this.fileNameCharset = builder.fileNameCharset;
        this.onMemberStart = builder.onMemberStart != null ? builder.onMemberStart : NOOP;
        this.onMemberEnd = builder.onMemberEnd != null ? builder.onMemberEnd : NOOP;
        init(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GzipCompressorInputStream(InputStream inputStream) throws IOException {
        this((Builder) builder().setInputStream(inputStream));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public GzipCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
        this(((Builder) builder().setInputStream(inputStream)).setDecompressConcatenated(decompressConcatenated));
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.inflater != null) {
            this.inflater.end();
            this.inflater = null;
        }
        if (this.in != System.in) {
            this.in.close();
        }
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getCount();
    }

    public GzipParameters getMetaData() {
        return this.parameters;
    }

    private boolean init(boolean isFirstMember) throws IOException {
        if (!isFirstMember && !this.decompressConcatenated) {
            throw new IllegalStateException("Unexpected: isFirstMember and decompressConcatenated are both false.");
        }
        int magic0 = this.in.read();
        if (magic0 == -1 && !isFirstMember) {
            return false;
        }
        if (magic0 != 31 || this.in.read() != 139) {
            throw new IOException(isFirstMember ? "Input is not in the .gz format." : "Unexpected data after a valid .gz stream.");
        }
        this.parameters = new GzipParameters();
        this.parameters.setFileNameCharset(this.fileNameCharset);
        DataInput inData = new DataInputStream(this.in);
        int method = inData.readUnsignedByte();
        if (method != 8) {
            throw new IOException("Unsupported compression method " + method + " in the .gz header");
        }
        int flg = inData.readUnsignedByte();
        if ((flg & 224) != 0) {
            throw new IOException("Reserved flags are set in the .gz header.");
        }
        this.parameters.setModificationTime(ByteUtils.fromLittleEndian(inData, 4));
        switch (inData.readUnsignedByte()) {
            case 2:
                this.parameters.setCompressionLevel(9);
                break;
            case 3:
            default:
                this.parameters.setCompressionLevel(-1);
                break;
            case 4:
                this.parameters.setCompressionLevel(1);
                break;
        }
        this.parameters.setOperatingSystem(inData.readUnsignedByte());
        if ((flg & 4) != 0) {
            int xlen = inData.readUnsignedByte();
            byte[] extra = new byte[xlen | (inData.readUnsignedByte() << 8)];
            inData.readFully(extra);
            this.parameters.setExtraField(ExtraField.fromBytes(extra));
        }
        int xlen2 = flg & 8;
        if (xlen2 != 0) {
            this.parameters.setFileName(new String(readToNull(inData), this.parameters.getFileNameCharset()));
        }
        if ((flg & 16) != 0) {
            this.parameters.setComment(new String(readToNull(inData), this.parameters.getFileNameCharset()));
        }
        if ((flg & 2) != 0) {
            this.parameters.setHeaderCRC(true);
            inData.readShort();
        }
        this.inflater.reset();
        this.crc.reset();
        this.onMemberStart.accept(this);
        return true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        if (this.endReached) {
            return -1;
        }
        int len2 = len;
        int size = 0;
        int off2 = off;
        while (len2 > 0) {
            if (this.inflater.needsInput()) {
                this.in.mark(this.buf.length);
                this.bufUsed = this.in.read(this.buf);
                if (this.bufUsed != -1) {
                    this.inflater.setInput(this.buf, 0, this.bufUsed);
                } else {
                    throw new EOFException();
                }
            }
            try {
                int ret = this.inflater.inflate(b, off2, len2);
                this.crc.update(b, off2, ret);
                off2 += ret;
                len2 -= ret;
                size += ret;
                count(ret);
                if (this.inflater.finished()) {
                    this.in.reset();
                    int skipAmount = this.bufUsed - this.inflater.getRemaining();
                    if (IOUtils.skip(this.in, skipAmount) != skipAmount) {
                        throw new IOException();
                    }
                    this.bufUsed = 0;
                    DataInput inData = new DataInputStream(this.in);
                    long trailerCrc = ByteUtils.fromLittleEndian(inData, 4);
                    if (trailerCrc != this.crc.getValue()) {
                        throw new IOException("Gzip-compressed data is corrupt (CRC32 error).");
                    }
                    long iSize = ByteUtils.fromLittleEndian(inData, 4);
                    if (iSize != (this.inflater.getBytesWritten() & 4294967295L)) {
                        throw new IOException("Gzip-compressed data is corrupt (uncompressed size mismatch).");
                    }
                    this.parameters.setTrailerCrc(trailerCrc);
                    this.parameters.setTrailerISize(iSize);
                    this.onMemberEnd.accept(this);
                    if (!this.decompressConcatenated || !init(false)) {
                        this.inflater.end();
                        this.inflater = null;
                        this.endReached = true;
                        if (size == 0) {
                            return -1;
                        }
                        return size;
                    }
                }
            } catch (DataFormatException e) {
                throw new IOException("Gzip-compressed data is corrupt.", e);
            }
        }
        return size;
    }
}
