package org.apache.poi.openxml4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

/* loaded from: classes10.dex */
public class ZipInputStreamZipEntrySource implements ZipEntrySource {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private InputStream streamToClose;
    private final Map<String, ZipArchiveFakeEntry> zipEntries = new HashMap();
    private static int thresholdForTempFiles = -1;
    private static boolean encryptTempFiles = false;

    public static void setThresholdBytesForTempFiles(int thresholdBytes) {
        thresholdForTempFiles = thresholdBytes;
    }

    public static int getThresholdBytesForTempFiles() {
        return thresholdForTempFiles;
    }

    public static void setEncryptTempFiles(boolean encrypt) {
        encryptTempFiles = encrypt;
    }

    public static boolean shouldEncryptTempFiles() {
        return encryptTempFiles;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
    
        throw new org.apache.poi.openxml4j.opc.internal.InvalidZipException("Input file contains an entry with an empty name");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ZipInputStreamZipEntrySource(org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream r7) throws java.io.IOException {
        /*
            r6 = this;
            r6.<init>()
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r6.zipEntries = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
        Lf:
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = r7.getNextEntry()
            if (r1 != 0) goto L19
        L16:
            r6.streamToClose = r7
            return
        L19:
            java.lang.String r2 = r1.getName()
            if (r2 == 0) goto L5c
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L5c
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r2 = r2.toLowerCase(r3)
            boolean r3 = r0.contains(r2)
            if (r3 != 0) goto L3f
            r0.add(r2)
            java.util.Map<java.lang.String, org.apache.poi.openxml4j.util.ZipArchiveFakeEntry> r3 = r6.zipEntries
            org.apache.poi.openxml4j.util.ZipArchiveFakeEntry r4 = new org.apache.poi.openxml4j.util.ZipArchiveFakeEntry
            r4.<init>(r1, r7)
            r3.put(r2, r4)
            goto Lf
        L3f:
            org.apache.poi.openxml4j.opc.internal.InvalidZipException r3 = new org.apache.poi.openxml4j.opc.internal.InvalidZipException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Input file contains more than 1 entry with the name "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = r1.getName()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L5c:
            org.apache.poi.openxml4j.opc.internal.InvalidZipException r3 = new org.apache.poi.openxml4j.opc.internal.InvalidZipException
            java.lang.String r4 = "Input file contains an entry with an empty name"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource.<init>(org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream):void");
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.zipEntries.values());
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public InputStream getInputStream(ZipArchiveEntry zipEntry) throws IOException {
        if (!(zipEntry instanceof ZipArchiveFakeEntry)) {
            throw new AssertionError();
        }
        return ((ZipArchiveFakeEntry) zipEntry).getInputStream();
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (ZipArchiveFakeEntry entry : this.zipEntries.values()) {
            entry.close();
        }
        this.zipEntries.clear();
        this.streamToClose.close();
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public boolean isClosed() {
        return this.zipEntries.isEmpty();
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public ZipArchiveEntry getEntry(String path) {
        String normalizedPath = path.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        ZipArchiveEntry ze = this.zipEntries.get(normalizedPath);
        if (ze != null) {
            return ze;
        }
        for (Map.Entry<String, ZipArchiveFakeEntry> fze : this.zipEntries.entrySet()) {
            if (normalizedPath.equalsIgnoreCase(fze.getKey())) {
                return fze.getValue();
            }
        }
        return null;
    }
}
