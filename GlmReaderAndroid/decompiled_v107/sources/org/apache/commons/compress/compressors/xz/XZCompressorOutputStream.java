package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

/* loaded from: classes9.dex */
public class XZCompressorOutputStream extends CompressorOutputStream<XZOutputStream> {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<XZCompressorOutputStream, Builder> {
        private LZMA2Options lzma2Options = new LZMA2Options();

        @Override // org.apache.commons.io.function.IOSupplier
        public XZCompressorOutputStream get() throws IOException {
            return new XZCompressorOutputStream(this);
        }

        public Builder setLzma2Options(LZMA2Options lzma2Options) {
            this.lzma2Options = lzma2Options != null ? lzma2Options : new LZMA2Options();
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private XZCompressorOutputStream(Builder builder) throws IOException {
        super(new XZOutputStream(builder.getOutputStream(), builder.lzma2Options));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XZCompressorOutputStream(OutputStream outputStream) throws IOException {
        this((Builder) builder().setOutputStream(outputStream));
    }

    @Deprecated
    public XZCompressorOutputStream(OutputStream outputStream, int preset) throws IOException {
        super(new XZOutputStream(outputStream, new LZMA2Options(preset)));
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        ((XZOutputStream) out()).finish();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        this.out.write(buf, off, len);
    }
}
