package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.output.CloseShieldOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public final class AesZipFileZipEntrySource implements ZipEntrySource {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) AesZipFileZipEntrySource.class);
    private static final String PADDING = "PKCS5Padding";
    private final Cipher ci;
    private boolean closed = false;
    private final File tmpFile;
    private final ZipFile zipFile;

    /* JADX WARN: Multi-variable type inference failed */
    private AesZipFileZipEntrySource(File tmpFile, Cipher ci) throws IOException {
        this.tmpFile = tmpFile;
        this.zipFile = ((ZipFile.Builder) ZipFile.builder().setFile(tmpFile)).get();
        this.ci = ci;
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return this.zipFile.getEntries();
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public ZipArchiveEntry getEntry(String path) {
        return this.zipFile.getEntry(path);
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        InputStream is = this.zipFile.getInputStream(entry);
        return new CipherInputStream(is, this.ci);
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.zipFile.close();
            if (!this.tmpFile.delete()) {
                LOG.atWarn().log("{} can't be removed (or was already removed).", this.tmpFile.getAbsolutePath());
            }
        }
        this.closed = true;
    }

    @Override // org.apache.poi.openxml4j.util.ZipEntrySource
    public boolean isClosed() {
        return this.closed;
    }

    public static AesZipFileZipEntrySource createZipEntrySource(InputStream is) throws IOException {
        try {
            byte[] ivBytes = new byte[16];
            byte[] keyBytes = new byte[16];
            RandomSingleton.getInstance().nextBytes(ivBytes);
            RandomSingleton.getInstance().nextBytes(keyBytes);
            File tmpFile = TempFile.createTempFile("protectedXlsx", ".zip");
            try {
                copyToFile(is, tmpFile, keyBytes, ivBytes);
                return fileToSource(tmpFile, keyBytes, ivBytes);
            } catch (IOException | RuntimeException e) {
                if (!tmpFile.delete()) {
                    LOG.atInfo().log("Temp file was not deleted, may already have been deleted by another method.");
                }
                throw e;
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    private static void copyToFile(InputStream is, File tmpFile, byte[] keyBytes, byte[] ivBytes) throws IOException {
        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, CipherAlgorithm.aes128.jceId);
        Cipher ciEnc = CryptoFunctions.getCipher(skeySpec, CipherAlgorithm.aes128, ChainingMode.cbc, ivBytes, 1, PADDING);
        ZipArchiveInputStream zis = new ZipArchiveInputStream(is);
        try {
            OutputStream fos = Files.newOutputStream(tmpFile.toPath(), new OpenOption[0]);
            try {
                ZipArchiveOutputStream zos = new ZipArchiveOutputStream(fos);
                while (true) {
                    try {
                        ZipArchiveEntry ze = zis.getNextEntry();
                        if (ze == null) {
                            break;
                        }
                        ZipArchiveEntry zeNew = new ZipArchiveEntry(ze.getName());
                        zeNew.setComment(ze.getComment());
                        zeNew.setExtra(ze.getExtra());
                        zeNew.setTime(ze.getTime());
                        zos.putArchiveEntry(zeNew);
                        CipherOutputStream cos = new CipherOutputStream(CloseShieldOutputStream.wrap(zos), ciEnc);
                        try {
                            IOUtils.copy(zis, cos);
                            cos.close();
                            zos.closeArchiveEntry();
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                try {
                                    cos.close();
                                    throw th2;
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                    throw th2;
                                }
                            }
                        }
                    } finally {
                    }
                }
                zos.close();
                if (fos != null) {
                    fos.close();
                }
                zis.close();
            } finally {
            }
        } finally {
        }
    }

    private static AesZipFileZipEntrySource fileToSource(File tmpFile, byte[] keyBytes, byte[] ivBytes) throws IOException {
        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, CipherAlgorithm.aes128.jceId);
        Cipher ciDec = CryptoFunctions.getCipher(skeySpec, CipherAlgorithm.aes128, ChainingMode.cbc, ivBytes, 2, PADDING);
        return new AesZipFileZipEntrySource(tmpFile, ciDec);
    }
}
