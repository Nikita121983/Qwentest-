package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.compress.CompressFilterOutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* loaded from: classes9.dex */
public abstract class ArchiveOutputStream<E extends ArchiveEntry> extends CompressFilterOutputStream<OutputStream> {
    static final int BYTE_MASK = 255;
    private long bytesWritten;
    private final byte[] oneByte;

    public abstract void closeArchiveEntry() throws IOException;

    public abstract E createArchiveEntry(File file, String str) throws IOException;

    public abstract void putArchiveEntry(E e) throws IOException;

    public ArchiveOutputStream() {
        this.oneByte = new byte[1];
    }

    public ArchiveOutputStream(OutputStream out) {
        super(out);
        this.oneByte = new byte[1];
    }

    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkFinished() throws IOException {
        if (isFinished()) {
            throw new IOException("Stream has already been finished.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(int written) {
        count(written);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(long written) {
        if (written != -1) {
            this.bytesWritten += written;
        }
    }

    public E createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        return createArchiveEntry(inputPath.toFile(), entryName);
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesWritten;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) (b & 255);
        write(this.oneByte, 0, 1);
    }
}
