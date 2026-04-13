package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

/* loaded from: classes9.dex */
public class JarArchiveInputStream extends ZipArchiveInputStream {
    public static boolean matches(byte[] signature, int length) {
        return ZipArchiveInputStream.matches(signature, length);
    }

    public JarArchiveInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public JarArchiveInputStream(InputStream inputStream, String encoding) {
        super(inputStream, encoding);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipArchiveInputStream, org.apache.commons.compress.archivers.ArchiveInputStream
    public ZipArchiveEntry getNextEntry() throws IOException {
        return getNextJarEntry();
    }

    @Deprecated
    public JarArchiveEntry getNextJarEntry() throws IOException {
        ZipArchiveEntry entry = getNextZipEntry();
        if (entry == null) {
            return null;
        }
        return new JarArchiveEntry(entry);
    }
}
