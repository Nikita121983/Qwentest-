package org.apache.poi.openxml4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;

/* loaded from: classes10.dex */
public class ZipFileZipEntrySource implements ZipEntrySource {
    private ZipFile zipArchive;

    public ZipFileZipEntrySource(ZipFile zipFile) {
        this.zipArchive = zipFile;
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.zipArchive != null) {
            this.zipArchive.close();
        }
        this.zipArchive = null;
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public boolean isClosed() {
        return this.zipArchive == null;
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        if (this.zipArchive == null) {
            throw new IllegalStateException("Zip File is closed");
        }
        return this.zipArchive.getEntries();
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        if (this.zipArchive == null) {
            throw new IllegalStateException("Zip File is closed");
        }
        return this.zipArchive.getInputStream(entry);
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public ZipArchiveEntry getEntry(String path) {
        String normalizedPath = path.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        ZipArchiveEntry entry = this.zipArchive.getEntry(normalizedPath);
        if (entry != null) {
            return entry;
        }
        Enumeration<ZipArchiveEntry> zipArchiveEntryEnumeration = this.zipArchive.getEntries();
        while (zipArchiveEntryEnumeration.hasMoreElements()) {
            ZipArchiveEntry ze = zipArchiveEntryEnumeration.nextElement();
            if (normalizedPath.equalsIgnoreCase(ze.getName().replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/'))) {
                return ze;
            }
        }
        return null;
    }
}
