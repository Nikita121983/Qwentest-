package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes9.dex */
abstract class RandomAccessOutputStream extends OutputStream {
    abstract long position() throws IOException;

    abstract void writeAll(byte[] bArr, int i, int i2, long j) throws IOException;

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeAll(byte[] bytes, long position) throws IOException {
        writeAll(bytes, 0, bytes.length, position);
    }
}
