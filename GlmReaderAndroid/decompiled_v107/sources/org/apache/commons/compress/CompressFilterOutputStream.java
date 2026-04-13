package org.apache.commons.compress;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public abstract class CompressFilterOutputStream<T extends OutputStream> extends FilterOutputStream {
    private final AtomicBoolean closed;
    private boolean finished;

    private static byte[] write(OutputStream os, String data, Charset charset) throws IOException {
        byte[] bytes = data.getBytes(charset);
        os.write(bytes);
        return bytes;
    }

    public CompressFilterOutputStream() {
        super(null);
        this.closed = new AtomicBoolean();
    }

    public CompressFilterOutputStream(T out) {
        super(out);
        this.closed = new AtomicBoolean();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkOpen() throws IOException {
        if (isClosed()) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed.compareAndSet(false, true)) {
            super.close();
        }
    }

    public void finish() throws IOException {
        this.finished = true;
    }

    public boolean isClosed() {
        return this.closed.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFinished() {
        return this.finished;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T out() {
        return (T) this.out;
    }

    public long write(File file) throws IOException {
        return write(file.toPath());
    }

    public long write(Path path) throws IOException {
        return Files.copy(path, this);
    }

    public byte[] writeUsAscii(String data) throws IOException {
        return write(this, data, StandardCharsets.US_ASCII);
    }

    public byte[] writeUsAsciiRaw(String data) throws IOException {
        return write(this.out, data, StandardCharsets.US_ASCII);
    }

    public byte[] writeUtf8(String data) throws IOException {
        return write(this, data, StandardCharsets.UTF_8);
    }
}
