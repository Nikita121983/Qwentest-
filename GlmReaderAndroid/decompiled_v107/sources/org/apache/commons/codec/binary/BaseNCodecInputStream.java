package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.codec.binary.BaseNCodecInputStream;
import org.apache.commons.codec.binary.BaseNCodecInputStream.AbstracBuilder;

/* loaded from: classes9.dex */
public class BaseNCodecInputStream<C extends BaseNCodec, T extends BaseNCodecInputStream<C, T, B>, B extends AbstracBuilder<T, C, B>> extends FilterInputStream {
    private final C baseNCodec;
    private final byte[] buf;
    private final BaseNCodec.Context context;
    private final boolean doEncode;
    private final byte[] singleByte;

    /* loaded from: classes9.dex */
    public static abstract class AbstracBuilder<T, C extends BaseNCodec, B extends AbstractBaseNCodecStreamBuilder<T, C, B>> extends AbstractBaseNCodecStreamBuilder<T, C, B> {
        private InputStream inputStream;

        protected InputStream getInputStream() {
            return this.inputStream;
        }

        public B setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodecInputStream(AbstracBuilder<T, C, B> builder) {
        super(builder.getInputStream());
        this.singleByte = new byte[1];
        this.context = new BaseNCodec.Context();
        this.baseNCodec = builder.getBaseNCodec();
        this.doEncode = builder.getEncode();
        this.buf = new byte[this.doEncode ? 4096 : 8192];
    }

    protected BaseNCodecInputStream(InputStream inputStream, C baseNCodec, boolean doEncode) {
        super(inputStream);
        this.singleByte = new byte[1];
        this.context = new BaseNCodec.Context();
        this.doEncode = doEncode;
        this.baseNCodec = baseNCodec;
        this.buf = new byte[doEncode ? 4096 : 8192];
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return !this.context.eof ? 1 : 0;
    }

    public boolean isStrictDecoding() {
        return this.baseNCodec.isStrictDecoding();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readLimit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int r = read(this.singleByte, 0, 1);
        while (r == 0) {
            r = read(this.singleByte, 0, 1);
        }
        if (r > 0) {
            byte b = this.singleByte[0];
            return b < 0 ? b + 256 : b;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] array, int offset, int len) throws IOException {
        Objects.requireNonNull(array, "array");
        if (offset < 0 || len < 0 || offset > array.length || offset + len > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        int readLen = 0;
        while (readLen < len) {
            if (!this.baseNCodec.hasData(this.context)) {
                int c = this.in.read(this.buf);
                if (this.doEncode) {
                    this.baseNCodec.encode(this.buf, 0, c, this.context);
                } else {
                    this.baseNCodec.decode(this.buf, 0, c, this.context);
                }
            }
            int read = this.baseNCodec.readResults(array, offset + readLen, len - readLen, this.context);
            if (read < 0) {
                if (readLen != 0) {
                    return readLen;
                }
                return -1;
            }
            readLen += read;
        }
        return readLen;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        int len;
        if (n < 0) {
            throw new IllegalArgumentException("Negative skip length: " + n);
        }
        byte[] b = new byte[512];
        long todo = n;
        while (todo > 0 && (len = read(b, 0, (int) Math.min(b.length, todo))) != -1) {
            todo -= len;
        }
        return n - todo;
    }
}
