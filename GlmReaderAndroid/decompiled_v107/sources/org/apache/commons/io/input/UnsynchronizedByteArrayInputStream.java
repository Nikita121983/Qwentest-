package org.apache.commons.io.input;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes9.dex */
public class UnsynchronizedByteArrayInputStream extends InputStream {
    public static final int END_OF_STREAM = -1;
    private final byte[] data;
    private final int eod;
    private int markedOffset;
    private int offset;

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UnsynchronizedByteArrayInputStream, Builder> {
        private int length;
        private int offset;

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] checkOriginByteArray() throws IOException {
            return checkOrigin().getByteArray();
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public UnsynchronizedByteArrayInputStream get() throws IOException {
            return new UnsynchronizedByteArrayInputStream(this);
        }

        @Override // org.apache.commons.io.build.AbstractOriginSupplier
        public Builder setByteArray(byte[] origin) {
            this.length = ((byte[]) Objects.requireNonNull(origin, "origin")).length;
            return (Builder) super.setByteArray(origin);
        }

        public Builder setLength(int length) {
            if (length < 0) {
                throw new IllegalArgumentException("length cannot be negative");
            }
            this.length = length;
            return this;
        }

        public Builder setOffset(int offset) {
            if (offset < 0) {
                throw new IllegalArgumentException("offset cannot be negative");
            }
            this.offset = offset;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static int minPosLen(byte[] data, int defaultValue) {
        requireNonNegative(defaultValue, "defaultValue");
        return Math.min(defaultValue, data.length > 0 ? data.length : defaultValue);
    }

    private static int requireNonNegative(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative");
        }
        return value;
    }

    private UnsynchronizedByteArrayInputStream(Builder builder) throws IOException {
        this(builder.checkOriginByteArray(), builder.offset, builder.length);
    }

    @Deprecated
    public UnsynchronizedByteArrayInputStream(byte[] data) {
        this(data, data.length, 0, 0);
    }

    @Deprecated
    public UnsynchronizedByteArrayInputStream(byte[] data, int offset) {
        this(data, data.length, Math.min(requireNonNegative(offset, TypedValues.CycleType.S_WAVE_OFFSET), minPosLen(data, offset)), minPosLen(data, offset));
    }

    @Deprecated
    public UnsynchronizedByteArrayInputStream(byte[] data, int offset, int length) {
        requireNonNegative(offset, TypedValues.CycleType.S_WAVE_OFFSET);
        requireNonNegative(length, "length");
        this.data = (byte[]) Objects.requireNonNull(data, "data");
        this.eod = Math.min(minPosLen(data, offset) + length, data.length);
        this.offset = minPosLen(data, offset);
        this.markedOffset = minPosLen(data, offset);
    }

    private UnsynchronizedByteArrayInputStream(byte[] data, int eod, int offset, int markedOffset) {
        this.data = (byte[]) Objects.requireNonNull(data, "data");
        this.eod = eod;
        this.offset = offset;
        this.markedOffset = markedOffset;
    }

    @Override // java.io.InputStream
    public int available() {
        if (this.offset < this.eod) {
            return this.eod - this.offset;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public void mark(int readLimit) {
        this.markedOffset = this.offset;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.offset >= this.eod) {
            return -1;
        }
        byte[] bArr = this.data;
        int i = this.offset;
        this.offset = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] dest) {
        Objects.requireNonNull(dest, "dest");
        return read(dest, 0, dest.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] dest, int off, int len) {
        IOUtils.checkFromIndexSize(dest, off, len);
        if (len == 0) {
            return 0;
        }
        if (this.offset >= this.eod) {
            return -1;
        }
        int actualLen = this.eod - this.offset;
        if (len < actualLen) {
            actualLen = len;
        }
        if (actualLen <= 0) {
            return 0;
        }
        System.arraycopy(this.data, this.offset, dest, off, actualLen);
        this.offset += actualLen;
        return actualLen;
    }

    @Override // java.io.InputStream
    public void reset() {
        this.offset = this.markedOffset;
    }

    @Override // java.io.InputStream
    public long skip(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Skipping backward is not supported");
        }
        long actualSkip = this.eod - this.offset;
        if (n < actualSkip) {
            actualSkip = n;
        }
        this.offset = Math.addExact(this.offset, Math.toIntExact(n));
        return actualSkip;
    }
}
