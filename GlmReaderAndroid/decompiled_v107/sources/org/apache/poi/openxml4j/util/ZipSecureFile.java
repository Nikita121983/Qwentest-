package org.apache.poi.openxml4j.util;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.internal.InvalidZipException;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

@Internal
/* loaded from: classes10.dex */
public class ZipSecureFile extends ZipFile {
    static final long DEFAULT_MAX_FILE_COUNT = 1000;
    public static final String MAX_FILE_COUNT_MSG = "The file appears to be potentially malicious. This file embeds more internal file entries than expected.\nThis may indicates that the file could pose a security risk.\nYou can adjust this limit via ZipSecureFile.setMaxFileCount() if you need to work with files which are very large.\nLimits: MAX_FILE_COUNT: %d";
    private final String fileName;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ZipSecureFile.class);
    static double MIN_INFLATE_RATIO = 0.01d;
    static final long DEFAULT_MAX_ENTRY_SIZE = 4294967295L;
    static long MAX_ENTRY_SIZE = DEFAULT_MAX_ENTRY_SIZE;
    static long MAX_FILE_COUNT = 1000;
    static final long DEFAULT_GRACE_ENTRY_SIZE = 102400;
    static long GRACE_ENTRY_SIZE = DEFAULT_GRACE_ENTRY_SIZE;
    static final long DEFAULT_MAX_TEXT_SIZE = 10485760;
    private static long MAX_TEXT_SIZE = DEFAULT_MAX_TEXT_SIZE;

    public static void setMinInflateRatio(double ratio) {
        MIN_INFLATE_RATIO = ratio;
    }

    public static double getMinInflateRatio() {
        return MIN_INFLATE_RATIO;
    }

    public static long getMaxFileCount() {
        return MAX_FILE_COUNT;
    }

    public static void setMaxFileCount(long maxFileCount) {
        MAX_FILE_COUNT = maxFileCount;
    }

    public static void setMaxEntrySize(long maxEntrySize) {
        if (maxEntrySize < 0) {
            throw new IllegalArgumentException("Max entry size must be greater than or equal to zero");
        }
        if (maxEntrySize > DEFAULT_MAX_ENTRY_SIZE) {
            LOG.warn("setting max entry size greater than 4Gb can be risky; set to {} bytes", Long.valueOf(maxEntrySize));
        }
        MAX_ENTRY_SIZE = maxEntrySize;
    }

    public static long getMaxEntrySize() {
        return MAX_ENTRY_SIZE;
    }

    public static void setGraceEntrySize(long graceEntrySize) {
        if (graceEntrySize < 0) {
            throw new IllegalArgumentException("Grace entry size must be greater than or equal to zero");
        }
        GRACE_ENTRY_SIZE = graceEntrySize;
    }

    public static long getGraceEntrySize() {
        return GRACE_ENTRY_SIZE;
    }

    public static void setMaxTextSize(long maxTextSize) {
        if (maxTextSize < 0) {
            throw new IllegalArgumentException("Max text size must be greater than or equal to zero");
        }
        if (maxTextSize > DEFAULT_MAX_TEXT_SIZE) {
            LOG.warn("setting max text size greater than {} can be risky; set to {} chars", Long.valueOf(DEFAULT_MAX_TEXT_SIZE), Long.valueOf(maxTextSize));
        }
        MAX_TEXT_SIZE = maxTextSize;
    }

    public static long getMaxTextSize() {
        return MAX_TEXT_SIZE;
    }

    public ZipSecureFile(File file) throws IOException {
        super(file);
        this.fileName = file.getAbsolutePath();
        validateEntryNames();
    }

    public ZipSecureFile(String name) throws IOException {
        super(name);
        this.fileName = new File(name).getAbsolutePath();
        validateEntryNames();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipFile
    public ZipArchiveThresholdInputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        ZipArchiveThresholdInputStream zatis = new ZipArchiveThresholdInputStream(super.getInputStream(entry));
        zatis.setEntry(entry);
        return zatis;
    }

    @Removal(version = "7.0.0")
    public String getName() {
        return this.fileName;
    }

    private void validateEntryNames() throws IOException {
        Enumeration<ZipArchiveEntry> en = getEntries();
        Set<String> filenames = new HashSet<>();
        while (en.hasMoreElements()) {
            ZipArchiveEntry entry = en.nextElement();
            String name = entry.getName();
            if (name == null || name.isEmpty()) {
                throw new InvalidZipException("Input file contains an entry with an empty name");
            }
            String name2 = name.toLowerCase(Locale.ROOT);
            if (filenames.contains(name2)) {
                throw new InvalidZipException("Input file contains more than 1 entry with the name " + entry.getName());
            }
            filenames.add(name2);
        }
    }
}
