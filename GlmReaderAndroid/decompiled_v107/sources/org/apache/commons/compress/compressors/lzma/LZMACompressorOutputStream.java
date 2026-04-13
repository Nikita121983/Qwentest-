package org.apache.commons.compress.compressors.lzma;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.LZMAOutputStream;

/* loaded from: classes9.dex */
public class LZMACompressorOutputStream extends CompressorOutputStream<LZMAOutputStream> {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<LZMACompressorOutputStream, Builder> {
        private LZMA2Options lzma2Options = new LZMA2Options();

        @Override // org.apache.commons.io.function.IOSupplier
        public LZMACompressorOutputStream get() throws IOException {
            return new LZMACompressorOutputStream(this);
        }

        public Builder setLzma2Options(LZMA2Options lzma2Options) {
            this.lzma2Options = lzma2Options != null ? lzma2Options : new LZMA2Options();
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private LZMACompressorOutputStream(Builder builder) throws IOException {
        super(new LZMAOutputStream(builder.getOutputStream(), builder.lzma2Options, -1L));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LZMACompressorOutputStream(OutputStream outputStream) throws IOException {
        this((Builder) builder().setOutputStream(outputStream));
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        ((LZMAOutputStream) out()).finish();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        this.out.write(buf, off, len);
    }
}
