package org.apache.commons.compress.compressors.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.poi.ss.formula.ptg.NumberPtg;

/* loaded from: classes9.dex */
public class GzipCompressorOutputStream extends CompressorOutputStream<OutputStream> {
    private final CRC32 crc;
    private final byte[] deflateBuffer;
    private final Deflater deflater;

    public GzipCompressorOutputStream(OutputStream out) throws IOException {
        this(out, new GzipParameters());
    }

    public GzipCompressorOutputStream(OutputStream out, GzipParameters parameters) throws IOException {
        super(out);
        this.crc = new CRC32();
        this.deflater = new Deflater(parameters.getCompressionLevel(), true);
        this.deflater.setStrategy(parameters.getDeflateStrategy());
        this.deflateBuffer = new byte[parameters.getBufferSize()];
        writeMemberHeader(parameters);
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!isClosed()) {
            try {
                finish();
            } finally {
                this.deflater.end();
                super.close();
            }
        }
    }

    private void deflate() throws IOException {
        int length = this.deflater.deflate(this.deflateBuffer, 0, this.deflateBuffer.length);
        if (length > 0) {
            this.out.write(this.deflateBuffer, 0, length);
        }
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        if (!this.deflater.finished()) {
            this.deflater.finish();
            while (!this.deflater.finished()) {
                deflate();
            }
            writeMemberTrailer();
            this.deflater.reset();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buffer) throws IOException {
        write(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buffer, int offset, int length) throws IOException {
        checkOpen();
        if (this.deflater.finished()) {
            throw new IOException("Cannot write more data, the end of the compressed data stream has been reached.");
        }
        if (length > 0) {
            this.deflater.setInput(buffer, offset, length);
            while (!this.deflater.needsInput()) {
                deflate();
            }
            this.crc.update(buffer, offset, length);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) (b & 255)}, 0, 1);
    }

    private void writeC(String value, Charset charset) throws IOException {
        if (value != null) {
            byte[] ba = value.getBytes(charset);
            this.out.write(ba);
            this.out.write(0);
            this.crc.update(ba);
            this.crc.update(0);
        }
    }

    private void writeMemberHeader(GzipParameters parameters) throws IOException {
        String fileName = parameters.getFileName();
        String comment = parameters.getComment();
        byte[] extra = parameters.getExtraField() != null ? parameters.getExtraField().toByteArray() : null;
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(NumberPtg.sid);
        buffer.put((byte) -117);
        buffer.put((byte) 8);
        buffer.put((byte) ((extra != null ? 4 : 0) | (fileName != null ? 8 : 0) | (comment != null ? 16 : 0) | (parameters.getHeaderCRC() ? 2 : 0)));
        buffer.putInt((int) parameters.getModificationInstant().getEpochSecond());
        int compressionLevel = parameters.getCompressionLevel();
        if (compressionLevel == 9) {
            buffer.put((byte) 2);
        } else if (compressionLevel == 1) {
            buffer.put((byte) 4);
        } else {
            buffer.put((byte) 0);
        }
        buffer.put((byte) parameters.getOperatingSystem());
        this.out.write(buffer.array());
        this.crc.update(buffer.array());
        if (extra != null) {
            this.out.write(extra.length & 255);
            this.out.write((extra.length >>> 8) & 255);
            this.out.write(extra);
            this.crc.update(extra.length & 255);
            this.crc.update((extra.length >>> 8) & 255);
            this.crc.update(extra);
        }
        writeC(fileName, parameters.getFileNameCharset());
        writeC(comment, parameters.getFileNameCharset());
        if (parameters.getHeaderCRC()) {
            int v = ((int) this.crc.getValue()) & 65535;
            this.out.write(v & 255);
            this.out.write((v >>> 8) & 255);
        }
        this.crc.reset();
    }

    private void writeMemberTrailer() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt((int) this.crc.getValue());
        buffer.putInt(this.deflater.getTotalIn());
        this.out.write(buffer.array());
    }
}
