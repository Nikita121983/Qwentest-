package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* loaded from: classes9.dex */
public class ByteArrayOutputStream extends AbstractByteArrayOutputStream<ByteArrayOutputStream> {
    /* renamed from: $r8$lambda$6y4jQ-FsQ6NEye1Y_s5VEmsevM0, reason: not valid java name */
    public static /* synthetic */ ByteArrayInputStream m2163$r8$lambda$6y4jQFsQ6NEye1Y_s5VEmsevM0(byte[] bArr, int i, int i2) {
        return new ByteArrayInputStream(bArr, i, i2);
    }

    public static InputStream toBufferedInputStream(InputStream input) throws IOException {
        return toBufferedInputStream(input, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(size);
        try {
            output.write(input);
            InputStream inputStream = output.toInputStream();
            output.close();
            return inputStream;
        } catch (Throwable th) {
            try {
                output.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        synchronized (this) {
            needNewBuffer(size);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized void reset() {
        resetImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized int size() {
        return this.count;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized byte[] toByteArray() {
        return toByteArrayImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized InputStream toInputStream() {
        return toInputStream(new AbstractByteArrayOutputStream.InputStreamConstructor() { // from class: org.apache.commons.io.output.ByteArrayOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream.InputStreamConstructor
            public final InputStream construct(byte[] bArr, int i, int i2) {
                return ByteArrayOutputStream.m2163$r8$lambda$6y4jQFsQ6NEye1Y_s5VEmsevM0(bArr, i, i2);
            }
        });
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) {
        IOUtils.checkFromIndexSize(b, off, len);
        if (len == 0) {
            return;
        }
        synchronized (this) {
            writeImpl(b, off, len);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized int write(InputStream in) throws IOException {
        return writeImpl(in);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int b) {
        writeImpl(b);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized void writeTo(OutputStream out) throws IOException {
        writeToImpl(out);
    }
}
