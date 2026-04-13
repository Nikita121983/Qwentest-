package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.function.Supplier;
import kotlin.UByte;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.charset.CharsetEncoders;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.CharSequenceInputStream;

/* loaded from: classes9.dex */
public class CharSequenceInputStream extends InputStream {
    private static final int NO_MARK = -1;
    private final ByteBuffer bBuf;
    private int bBufMark;
    private final CharBuffer cBuf;
    private int cBufMark;
    private final CharsetEncoder charsetEncoder;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<CharSequenceInputStream, Builder> {
        private CharsetEncoder charsetEncoder = CharSequenceInputStream.newEncoder(getCharset());

        @Override // org.apache.commons.io.function.IOSupplier
        public CharSequenceInputStream get() {
            return (CharSequenceInputStream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.CharSequenceInputStream$Builder$$ExternalSyntheticLambda1
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    return CharSequenceInputStream.Builder.this.m2126xc0c8f29c();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$get$0$org-apache-commons-io-input-CharSequenceInputStream$Builder, reason: not valid java name */
        public /* synthetic */ CharSequenceInputStream m2126xc0c8f29c() throws IOException {
            return new CharSequenceInputStream(this);
        }

        CharsetEncoder getCharsetEncoder() {
            return this.charsetEncoder;
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(Charset charset) {
            super.setCharset(charset);
            this.charsetEncoder = CharSequenceInputStream.newEncoder(getCharset());
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$setCharsetEncoder$1$org-apache-commons-io-input-CharSequenceInputStream$Builder, reason: not valid java name */
        public /* synthetic */ CharsetEncoder m2127xabe124b9() {
            return CharSequenceInputStream.newEncoder(getCharsetDefault());
        }

        public Builder setCharsetEncoder(CharsetEncoder newEncoder) {
            this.charsetEncoder = CharsetEncoders.toCharsetEncoder(newEncoder, new Supplier() { // from class: org.apache.commons.io.input.CharSequenceInputStream$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return CharSequenceInputStream.Builder.this.m2127xabe124b9();
                }
            });
            super.setCharset(this.charsetEncoder.charset());
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharsetEncoder newEncoder(Charset charset) {
        return Charsets.toCharset(charset).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    private CharSequenceInputStream(final Builder builder) {
        this.charsetEncoder = builder.charsetEncoder;
        this.bBuf = ByteBuffer.allocate(ReaderInputStream.checkMinBufferSize(builder.charsetEncoder, builder.getBufferSize()));
        this.bBuf.flip();
        this.cBuf = CharBuffer.wrap((CharSequence) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.CharSequenceInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                CharSequence charSequence;
                charSequence = CharSequenceInputStream.Builder.this.getCharSequence();
                return charSequence;
            }
        }));
        this.cBufMark = -1;
        this.bBufMark = -1;
        try {
            fillBuffer();
        } catch (CharacterCodingException e) {
            this.bBuf.clear();
            this.bBuf.flip();
            this.cBuf.rewind();
        }
    }

    @Deprecated
    public CharSequenceInputStream(CharSequence cs, Charset charset) {
        this(cs, charset, 8192);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public CharSequenceInputStream(CharSequence cs, Charset charset, int bufferSize) {
        this(((Builder) builder().setCharSequence(cs)).setCharset(charset).setBufferSize(bufferSize));
    }

    @Deprecated
    public CharSequenceInputStream(CharSequence cs, String charset) {
        this(cs, charset, 8192);
    }

    @Deprecated
    public CharSequenceInputStream(CharSequence cs, String charset, int bufferSize) {
        this(cs, Charsets.toCharset(charset), bufferSize);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.bBuf.remaining();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.bBuf.position(this.bBuf.limit());
    }

    private void fillBuffer() throws CharacterCodingException {
        this.bBuf.compact();
        CoderResult result = this.charsetEncoder.encode(this.cBuf, this.bBuf, true);
        if (result.isError()) {
            result.throwException();
        }
        this.bBuf.flip();
    }

    CharsetEncoder getCharsetEncoder() {
        return this.charsetEncoder;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readLimit) {
        this.cBufMark = this.cBuf.position();
        this.bBufMark = this.bBuf.position();
        this.cBuf.mark();
        this.bBuf.mark();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (!this.bBuf.hasRemaining()) {
            fillBuffer();
            if (!this.bBuf.hasRemaining() && !this.cBuf.hasRemaining()) {
                return -1;
            }
        }
        return this.bBuf.get() & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] array, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(array, off, len);
        if (len == 0) {
            return 0;
        }
        if (!this.bBuf.hasRemaining() && !this.cBuf.hasRemaining()) {
            return -1;
        }
        int bytesRead = 0;
        while (len > 0) {
            if (this.bBuf.hasRemaining()) {
                int chunk = Math.min(this.bBuf.remaining(), len);
                this.bBuf.get(array, off, chunk);
                off += chunk;
                len -= chunk;
                bytesRead += chunk;
            } else {
                fillBuffer();
                if (!this.bBuf.hasRemaining() && !this.cBuf.hasRemaining()) {
                    break;
                }
            }
        }
        if (bytesRead != 0 || this.cBuf.hasRemaining()) {
            return bytesRead;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.cBufMark != -1) {
            if (this.cBuf.position() != 0) {
                this.charsetEncoder.reset();
                this.cBuf.rewind();
                this.bBuf.rewind();
                this.bBuf.limit(0);
                while (this.cBuf.position() < this.cBufMark) {
                    this.bBuf.rewind();
                    this.bBuf.limit(0);
                    fillBuffer();
                }
            }
            if (this.cBuf.position() != this.cBufMark) {
                throw new IllegalStateException("Unexpected CharBuffer position: actual=" + this.cBuf.position() + " expected=" + this.cBufMark);
            }
            this.bBuf.position(this.bBufMark);
            this.cBufMark = -1;
            this.bBufMark = -1;
        }
        mark(0);
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        long skipped = 0;
        while (n > 0 && available() > 0) {
            read();
            n--;
            skipped++;
        }
        return skipped;
    }
}
