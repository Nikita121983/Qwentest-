package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/* loaded from: classes10.dex */
class OpcZipArchiveOutputStream extends ZipArchiveOutputStream {
    private final OpcOutputStream out;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpcZipArchiveOutputStream(OutputStream out) {
        super(out);
        this.out = new OpcOutputStream(out);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
    public void setLevel(int level) {
        this.out.setLevel(level);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ZipArchiveEntry archiveEntry) throws IOException {
        this.out.putNextEntry(archiveEntry.getName());
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        this.out.closeEntry();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        this.out.finish();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.out.write(b);
    }
}
