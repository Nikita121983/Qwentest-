package org.apache.poi.openxml4j.util;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class ZipArchiveThresholdInputStream extends FilterInputStream {
    private static final String MAX_ENTRY_SIZE_MSG = "Zip bomb detected! The file would exceed the max size of the expanded data in the zip-file.\nThis may indicates that the file is used to inflate memory usage and thus could pose a security risk.\nYou can adjust this limit via ZipSecureFile.setMaxEntrySize() if you need to work with files which are very large.\nUncompressed size: %d, Raw/compressed size: %d\nLimits: MAX_ENTRY_SIZE: %d, Entry: %s";
    private static final String MIN_INFLATE_RATIO_MSG = "Zip bomb detected! The file would exceed the max. ratio of compressed file size to the size of the expanded data.\nThis may indicate that the file is used to inflate memory usage and thus could pose a security risk.\nYou can adjust this limit via ZipSecureFile.setMinInflateRatio() if you need to work with files which exceed this limit.\nUncompressed size: %d, Raw/compressed size: %d, ratio: %f\nLimits: MIN_INFLATE_RATIO: %f, Entry: %s";
    private ZipArchiveEntry entry;
    private long entryCount;
    private boolean guardState;

    public ZipArchiveThresholdInputStream(InputStream is) {
        super(is);
        this.guardState = true;
        if (!(is instanceof InputStreamStatistics)) {
            throw new IllegalArgumentException("InputStream of class " + is.getClass() + " is not implementing InputStreamStatistics.");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int b = super.read();
        if (b > -1) {
            checkThreshold();
        }
        return b;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int cnt = super.read(b, off, len);
        if (cnt > -1) {
            checkThreshold();
        }
        return cnt;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        long cnt = IOUtils.skipFully(((FilterInputStream) this).in, n);
        if (cnt > 0) {
            checkThreshold();
        }
        return cnt;
    }

    public void setGuardState(boolean guardState) {
        this.guardState = guardState;
    }

    private void checkThreshold() throws IOException {
        long rawSize;
        if (!this.guardState) {
            return;
        }
        InputStreamStatistics stats = (InputStreamStatistics) this.in;
        long payloadSize = stats.getUncompressedCount();
        try {
            rawSize = stats.getCompressedCount();
        } catch (NullPointerException e) {
            rawSize = 0;
        }
        String entryName = this.entry == null ? "not set" : this.entry.getName();
        if (payloadSize > ZipSecureFile.getMaxEntrySize()) {
            throw new IOException(String.format(Locale.ROOT, MAX_ENTRY_SIZE_MSG, Long.valueOf(payloadSize), Long.valueOf(rawSize), Long.valueOf(ZipSecureFile.getMaxEntrySize()), entryName));
        }
        if (payloadSize <= ZipSecureFile.GRACE_ENTRY_SIZE) {
            return;
        }
        double ratio = rawSize / payloadSize;
        if (ratio >= ZipSecureFile.MIN_INFLATE_RATIO) {
        } else {
            throw new IOException(String.format(Locale.ROOT, MIN_INFLATE_RATIO_MSG, Long.valueOf(payloadSize), Long.valueOf(rawSize), Double.valueOf(ratio), Double.valueOf(ZipSecureFile.MIN_INFLATE_RATIO), entryName));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipArchiveEntry getNextEntry() throws IOException {
        if (!(this.in instanceof ZipArchiveInputStream)) {
            throw new IllegalStateException("getNextEntry() is only allowed for stream based zip processing.");
        }
        try {
            this.entry = ((ZipArchiveInputStream) this.in).getNextEntry();
            if (this.guardState && this.entry != null) {
                long j = this.entryCount + 1;
                this.entryCount = j;
                if (j > ZipSecureFile.MAX_FILE_COUNT) {
                    throw new IOException(String.format(Locale.ROOT, ZipSecureFile.MAX_FILE_COUNT_MSG, Long.valueOf(ZipSecureFile.MAX_FILE_COUNT)));
                }
            }
            return this.entry;
        } catch (EOFException e) {
            return null;
        } catch (ZipException ze) {
            String msg = ze.getMessage();
            if (msg.startsWith("Unexpected record signature") || msg.startsWith("Cannot find zip signature within the file")) {
                throw new NotOfficeXmlFileException("No valid entries or contents found, this is not a valid OOXML (Office Open XML) file", ze);
            }
            throw ze;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEntry(ZipArchiveEntry entry) {
        this.entry = entry;
    }
}
