package org.apache.commons.compress.compressors.deflate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

/* loaded from: classes9.dex */
public class DeflateCompressorOutputStream extends CompressorOutputStream<DeflaterOutputStream> {
    private final Deflater deflater;

    public DeflateCompressorOutputStream(OutputStream outputStream) {
        this(outputStream, new DeflateParameters());
    }

    public DeflateCompressorOutputStream(OutputStream outputStream, DeflateParameters parameters) {
        this.deflater = new Deflater(parameters.getCompressionLevel(), !parameters.withZlibHeader());
        this.out = new DeflaterOutputStream(outputStream, this.deflater);
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            this.deflater.end();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        ((DeflaterOutputStream) out()).finish();
        super.finish();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        this.out.write(buf, off, len);
    }
}
