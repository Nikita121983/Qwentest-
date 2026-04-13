package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class UnixLineEndingInputStream extends InputStream {
    private boolean atEos;
    private boolean atSlashCr;
    private boolean atSlashLf;
    private final InputStream in;
    private final boolean lineFeedAtEndOfFile;

    public UnixLineEndingInputStream(InputStream inputStream, boolean ensureLineFeedAtEndOfFile) {
        this.in = inputStream;
        this.lineFeedAtEndOfFile = ensureLineFeedAtEndOfFile;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.in.close();
    }

    private int handleEos(boolean previousWasSlashCr) {
        if (previousWasSlashCr || !this.lineFeedAtEndOfFile || this.atSlashLf) {
            return -1;
        }
        this.atSlashLf = true;
        return 10;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readLimit) {
        throw UnsupportedOperationExceptions.mark();
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        boolean previousWasSlashR = this.atSlashCr;
        if (this.atEos) {
            return handleEos(previousWasSlashR);
        }
        int target = readWithUpdate();
        if (this.atEos) {
            return handleEos(previousWasSlashR);
        }
        if (this.atSlashCr) {
            return 10;
        }
        if (!previousWasSlashR || !this.atSlashLf) {
            return target;
        }
        return read();
    }

    private int readWithUpdate() throws IOException {
        int target = this.in.read();
        this.atEos = target == -1;
        if (this.atEos) {
            return target;
        }
        this.atSlashCr = target == 13;
        this.atSlashLf = target == 10;
        return target;
    }
}
