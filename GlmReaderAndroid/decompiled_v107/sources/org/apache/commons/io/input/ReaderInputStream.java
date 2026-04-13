package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
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
import org.apache.commons.io.input.ReaderInputStream;

/* loaded from: classes9.dex */
public class ReaderInputStream extends AbstractInputStream {
    private final CharsetEncoder charsetEncoder;
    private final CharBuffer encoderIn;
    private final ByteBuffer encoderOut;
    private boolean endOfInput;
    private CoderResult lastCoderResult;
    private final Reader reader;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<ReaderInputStream, Builder> {
        private CharsetEncoder charsetEncoder = ReaderInputStream.newEncoder(getCharset());

        @Override // org.apache.commons.io.function.IOSupplier
        public ReaderInputStream get() throws IOException {
            return new ReaderInputStream(this);
        }

        CharsetEncoder getCharsetEncoder() {
            return this.charsetEncoder;
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(Charset charset) {
            super.setCharset(charset);
            this.charsetEncoder = ReaderInputStream.newEncoder(getCharset());
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$setCharsetEncoder$0$org-apache-commons-io-input-ReaderInputStream$Builder, reason: not valid java name */
        public /* synthetic */ CharsetEncoder m2129x5a2c7bac() {
            return ReaderInputStream.newEncoder(getCharsetDefault());
        }

        public Builder setCharsetEncoder(CharsetEncoder newEncoder) {
            this.charsetEncoder = CharsetEncoders.toCharsetEncoder(newEncoder, new Supplier() { // from class: org.apache.commons.io.input.ReaderInputStream$Builder$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ReaderInputStream.Builder.this.m2129x5a2c7bac();
                }
            });
            super.setCharset(this.charsetEncoder.charset());
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkMinBufferSize(CharsetEncoder charsetEncoder, int bufferSize) {
        float minRequired = minBufferSize(charsetEncoder);
        if (bufferSize < minRequired) {
            throw new IllegalArgumentException(String.format("Buffer size %,d must be at least %s for a CharsetEncoder %s.", Integer.valueOf(bufferSize), Float.valueOf(minRequired), charsetEncoder.charset().displayName()));
        }
        return bufferSize;
    }

    static float minBufferSize(CharsetEncoder charsetEncoder) {
        return charsetEncoder.maxBytesPerChar() * 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharsetEncoder newEncoder(Charset charset) {
        return Charsets.toCharset(charset).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    private ReaderInputStream(Builder builder) throws IOException {
        this(builder.getReader(), builder.charsetEncoder, builder.getBufferSize());
    }

    @Deprecated
    public ReaderInputStream(Reader reader) {
        this(reader, Charset.defaultCharset());
    }

    @Deprecated
    public ReaderInputStream(Reader reader, Charset charset) {
        this(reader, charset, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, Charset charset, int bufferSize) {
        this(reader, Charsets.toCharset(charset).newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), bufferSize);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder) {
        this(reader, charsetEncoder, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int bufferSize) {
        this.reader = reader;
        this.charsetEncoder = CharsetEncoders.toCharsetEncoder(charsetEncoder);
        this.encoderIn = CharBuffer.allocate(checkMinBufferSize(this.charsetEncoder, bufferSize));
        this.encoderIn.flip();
        this.encoderOut = ByteBuffer.allocate(128);
        this.encoderOut.flip();
    }

    @Deprecated
    public ReaderInputStream(Reader reader, String charsetName) {
        this(reader, charsetName, 8192);
    }

    @Deprecated
    public ReaderInputStream(Reader reader, String charsetName, int bufferSize) {
        this(reader, Charsets.toCharset(charsetName), bufferSize);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.encoderOut.hasRemaining()) {
            return this.encoderOut.remaining();
        }
        return 0;
    }

    @Override // org.apache.commons.io.input.AbstractInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
        super.close();
    }

    private void fillBuffer() throws IOException {
        if (this.endOfInput) {
            return;
        }
        if (!this.endOfInput && (this.lastCoderResult == null || this.lastCoderResult.isUnderflow())) {
            this.encoderIn.compact();
            int position = this.encoderIn.position();
            int c = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
            if (c == -1) {
                this.endOfInput = true;
            } else {
                this.encoderIn.position(position + c);
            }
            this.encoderIn.flip();
        }
        this.encoderOut.compact();
        this.lastCoderResult = this.charsetEncoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
        if (this.endOfInput) {
            this.lastCoderResult = this.charsetEncoder.flush(this.encoderOut);
        }
        if (this.lastCoderResult.isError()) {
            this.lastCoderResult.throwException();
        }
        this.encoderOut.flip();
    }

    CharsetEncoder getCharsetEncoder() {
        return this.charsetEncoder;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        checkOpen();
        while (!this.encoderOut.hasRemaining()) {
            fillBuffer();
            if (this.endOfInput && !this.encoderOut.hasRemaining()) {
                return -1;
            }
        }
        return this.encoderOut.get() & UByte.MAX_VALUE;
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
        int read = 0;
        while (len > 0) {
            if (this.encoderOut.hasRemaining()) {
                int c = Math.min(this.encoderOut.remaining(), len);
                this.encoderOut.get(array, off, c);
                off += c;
                len -= c;
                read += c;
            } else {
                if (this.endOfInput) {
                    break;
                }
                fillBuffer();
            }
        }
        if (read == 0 && this.endOfInput) {
            return -1;
        }
        return read;
    }
}
