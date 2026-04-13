package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* loaded from: classes9.dex */
public final class UnsynchronizedByteArrayOutputStream extends AbstractByteArrayOutputStream<UnsynchronizedByteArrayOutputStream> {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UnsynchronizedByteArrayOutputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UnsynchronizedByteArrayOutputStream get() {
            return new UnsynchronizedByteArrayOutputStream(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static InputStream toBufferedInputStream(InputStream input) throws IOException {
        return toBufferedInputStream(input, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
        UnsynchronizedByteArrayOutputStream output = builder().setBufferSize(size).get();
        try {
            output.write(input);
            InputStream inputStream = output.toInputStream();
            if (output != null) {
                output.close();
            }
            return inputStream;
        } catch (Throwable th) {
            if (output != null) {
                try {
                    output.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Deprecated
    public UnsynchronizedByteArrayOutputStream() {
        this(1024);
    }

    private UnsynchronizedByteArrayOutputStream(Builder builder) {
        this(builder.getBufferSize());
    }

    @Deprecated
    public UnsynchronizedByteArrayOutputStream(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        needNewBuffer(size);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void reset() {
        resetImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int size() {
        return this.count;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public byte[] toByteArray() {
        return toByteArrayImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public InputStream toInputStream() {
        return toInputStream(new AbstractByteArrayOutputStream.InputStreamConstructor() { // from class: org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream.InputStreamConstructor
            public final InputStream construct(byte[] bArr, int i, int i2) {
                return UnsynchronizedByteArrayOutputStream.lambda$toInputStream$1(bArr, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ UnsynchronizedByteArrayInputStream lambda$toInputStream$1(final byte[] buffer, final int offset, final int length) {
        return (UnsynchronizedByteArrayInputStream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                UnsynchronizedByteArrayInputStream unsynchronizedByteArrayInputStream;
                byte[] bArr = buffer;
                unsynchronizedByteArrayInputStream = UnsynchronizedByteArrayInputStream.builder().setByteArray(bArr).setOffset(offset).setLength(length).get();
                return unsynchronizedByteArrayInputStream;
            }
        });
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) {
        IOUtils.checkFromIndexSize(b, off, len);
        if (len == 0) {
            return;
        }
        writeImpl(b, off, len);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int write(InputStream in) throws IOException {
        return writeImpl(in);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(int b) {
        writeImpl(b);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void writeTo(OutputStream out) throws IOException {
        writeToImpl(out);
    }
}
