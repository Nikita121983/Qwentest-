package org.apache.poi.openxml4j.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public final class ZipArchiveFakeEntry extends ZipArchiveEntry implements Closeable {
    private byte[] data;
    private EncryptedTempData encryptedTempData;
    private File tempFile;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ZipArchiveFakeEntry.class);
    private static final int DEFAULT_MAX_ENTRY_SIZE = 100000000;
    private static int MAX_ENTRY_SIZE = DEFAULT_MAX_ENTRY_SIZE;

    public static void setMaxEntrySize(int maxEntrySize) {
        if (maxEntrySize < 0) {
            MAX_ENTRY_SIZE = DEFAULT_MAX_ENTRY_SIZE;
        } else {
            MAX_ENTRY_SIZE = maxEntrySize;
        }
    }

    public static int getMaxEntrySize() {
        int ioMaxSize = IOUtils.getByteArrayMaxOverride();
        int i = MAX_ENTRY_SIZE;
        return ioMaxSize < 0 ? i : Math.min(i, ioMaxSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipArchiveFakeEntry(ZipArchiveEntry entry, InputStream inp) throws IOException {
        super(entry.getName());
        long entrySize = entry.getSize();
        int threshold = ZipInputStreamZipEntrySource.getThresholdBytesForTempFiles();
        if (threshold < 0 || (entrySize < threshold && entrySize != -1)) {
            if (entrySize < -1 || entrySize >= 2147483647L) {
                throw new IOException("ZIP entry size is too large or invalid");
            }
            this.data = entrySize == -1 ? IOUtils.toByteArrayWithMaxLength(inp, getMaxEntrySize()) : IOUtils.toByteArray(inp, entrySize, getMaxEntrySize());
            return;
        }
        if (ZipInputStreamZipEntrySource.shouldEncryptTempFiles()) {
            this.encryptedTempData = new EncryptedTempData();
            OutputStream os = this.encryptedTempData.getOutputStream();
            try {
                IOUtils.copy(inp, os);
                if (os != null) {
                    os.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        this.tempFile = TempFile.createTempFile("poi-zip-entry", ".tmp");
        LOG.atInfo().log("Creating temp file {} for zip entry {} of size {} bytes", this.tempFile.getAbsolutePath(), entry.getName(), Long.valueOf(entrySize));
        IOUtils.copy(inp, this.tempFile);
    }

    public InputStream getInputStream() throws IOException {
        if (this.encryptedTempData != null) {
            try {
                return this.encryptedTempData.getInputStream();
            } catch (IOException e) {
                throw new IOException("failed to read from encrypted temp data", e);
            }
        }
        if (this.tempFile != null) {
            try {
                return Files.newInputStream(this.tempFile.toPath(), new OpenOption[0]);
            } catch (FileNotFoundException e2) {
                throw new IOException("temp file " + this.tempFile.getAbsolutePath() + " is missing");
            }
        }
        if (this.data != null) {
            return UnsynchronizedByteArrayInputStream.builder().setByteArray(this.data).get();
        }
        throw new IOException("Cannot retrieve data from Zip Entry, probably because the Zip Entry was closed before the data was requested.");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.data = null;
        if (this.encryptedTempData != null) {
            this.encryptedTempData.dispose();
        }
        if (this.tempFile != null && this.tempFile.exists() && !this.tempFile.delete()) {
            LOG.atDebug().log("temp file was already deleted (probably due to previous call to close this resource)");
        }
    }
}
