package org.apache.commons.compress.archivers.sevenz;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.FlushShieldFilterOutputStream;
import org.tukaani.xz.ARMOptions;
import org.tukaani.xz.ARMThumbOptions;
import org.tukaani.xz.FilterOptions;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.IA64Options;
import org.tukaani.xz.PowerPCOptions;
import org.tukaani.xz.SPARCOptions;
import org.tukaani.xz.X86Options;

/* loaded from: classes9.dex */
final class Coders {
    private static final Map<SevenZMethod, AbstractCoder> CODER_MAP = new HashMap<SevenZMethod, AbstractCoder>() { // from class: org.apache.commons.compress.archivers.sevenz.Coders.1
        private static final long serialVersionUID = 1664829131806520867L;

        {
            put(SevenZMethod.COPY, new CopyDecoder());
            put(SevenZMethod.LZMA, new LZMADecoder());
            put(SevenZMethod.LZMA2, new LZMA2Decoder());
            put(SevenZMethod.DEFLATE, new DeflateDecoder());
            put(SevenZMethod.DEFLATE64, new Deflate64Decoder());
            put(SevenZMethod.BZIP2, new BZIP2Decoder());
            put(SevenZMethod.AES256SHA256, new AES256SHA256Decoder());
            put(SevenZMethod.BCJ_X86_FILTER, new BCJDecoder(new X86Options()));
            put(SevenZMethod.BCJ_PPC_FILTER, new BCJDecoder(new PowerPCOptions()));
            put(SevenZMethod.BCJ_IA64_FILTER, new BCJDecoder(new IA64Options()));
            put(SevenZMethod.BCJ_ARM_FILTER, new BCJDecoder(new ARMOptions()));
            put(SevenZMethod.BCJ_ARM_THUMB_FILTER, new BCJDecoder(new ARMThumbOptions()));
            put(SevenZMethod.BCJ_SPARC_FILTER, new BCJDecoder(new SPARCOptions()));
            put(SevenZMethod.DELTA_FILTER, new DeltaDecoder());
        }
    };

    Coders() {
    }

    /* loaded from: classes9.dex */
    static final class BCJDecoder extends AbstractCoder {
        private final FilterOptions opts;

        BCJDecoder(FilterOptions opts) {
            super(new Class[0]);
            this.opts = opts;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
            try {
                return this.opts.getInputStream(in);
            } catch (AssertionError e) {
                throw new IOException("BCJ filter used in " + archiveName + " needs XZ for Java > 1.4 - see https://commons.apache.org/proper/commons-compress/limitations.html#7Z", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public OutputStream encode(OutputStream out, Object options) {
            return new FlushShieldFilterOutputStream(this.opts.getOutputStream(new FinishableWrapperOutputStream(out)));
        }
    }

    /* loaded from: classes9.dex */
    static final class BZIP2Decoder extends AbstractCoder {
        BZIP2Decoder() {
            super(Number.class);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
            return new BZip2CompressorInputStream(in);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public OutputStream encode(OutputStream out, Object options) throws IOException {
            int blockSize = toInt(options, 9);
            return new BZip2CompressorOutputStream(out, blockSize);
        }
    }

    /* loaded from: classes9.dex */
    static final class CopyDecoder extends AbstractCoder {
        CopyDecoder() {
            super(new Class[0]);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
            return in;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public OutputStream encode(OutputStream out, Object options) {
            return out;
        }
    }

    /* loaded from: classes9.dex */
    static final class Deflate64Decoder extends AbstractCoder {
        Deflate64Decoder() {
            super(Number.class);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
            return new Deflate64CompressorInputStream(in);
        }
    }

    /* loaded from: classes9.dex */
    static final class DeflateDecoder extends AbstractCoder {
        private static final byte[] ONE_ZERO_BYTE = new byte[1];

        /* loaded from: classes9.dex */
        static final class DeflateDecoderInputStream extends FilterInputStream {
            Inflater inflater;

            DeflateDecoderInputStream(InflaterInputStream inflaterInputStream, Inflater inflater) {
                super(inflaterInputStream);
                this.inflater = inflater;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    this.inflater.end();
                }
            }
        }

        /* loaded from: classes9.dex */
        static final class DeflateDecoderOutputStream extends OutputStream {
            Deflater deflater;
            final DeflaterOutputStream deflaterOutputStream;

            DeflateDecoderOutputStream(DeflaterOutputStream deflaterOutputStream, Deflater deflater) {
                this.deflaterOutputStream = deflaterOutputStream;
                this.deflater = deflater;
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    this.deflaterOutputStream.close();
                } finally {
                    this.deflater.end();
                }
            }

            @Override // java.io.OutputStream
            public void write(byte[] b) throws IOException {
                this.deflaterOutputStream.write(b);
            }

            @Override // java.io.OutputStream
            public void write(byte[] b, int off, int len) throws IOException {
                this.deflaterOutputStream.write(b, off, len);
            }

            @Override // java.io.OutputStream
            public void write(int b) throws IOException {
                this.deflaterOutputStream.write(b);
            }
        }

        DeflateDecoder() {
            super(Number.class);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
            Inflater inflater = new Inflater(true);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new SequenceInputStream(in, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater);
            return new DeflateDecoderInputStream(inflaterInputStream, inflater);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
        public OutputStream encode(OutputStream out, Object options) {
            int level = toInt(options, 9);
            Deflater deflater = new Deflater(level, true);
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(out, deflater);
            return new DeflateDecoderOutputStream(deflaterOutputStream, deflater);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream addDecoder(String archiveName, InputStream is, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
        AbstractCoder cb = findByMethod(SevenZMethod.byId(coder.decompressionMethodId));
        if (cb == null) {
            throw new IOException("Unsupported compression method " + Arrays.toString(coder.decompressionMethodId) + " used in " + archiveName);
        }
        return cb.decode(archiveName, is, uncompressedLength, coder, password, maxMemoryLimitKiB);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputStream addEncoder(OutputStream out, SevenZMethod method, Object options) throws IOException {
        AbstractCoder cb = findByMethod(method);
        if (cb == null) {
            throw new IOException("Unsupported compression method " + method);
        }
        return cb.encode(out, options);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractCoder findByMethod(SevenZMethod method) {
        return CODER_MAP.get(method);
    }
}
