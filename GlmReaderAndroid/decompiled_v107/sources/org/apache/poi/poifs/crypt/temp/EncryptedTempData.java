package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public class EncryptedTempData {
    private static final String PADDING = "PKCS5Padding";
    private final byte[] ivBytes = new byte[16];
    private CountingOutputStream outputStream;
    private final SecretKeySpec skeySpec;
    private final File tempFile;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) EncryptedTempData.class);
    private static final CipherAlgorithm cipherAlgorithm = CipherAlgorithm.aes128;

    public EncryptedTempData() throws IOException {
        byte[] keyBytes = new byte[16];
        RandomSingleton.getInstance().nextBytes(this.ivBytes);
        RandomSingleton.getInstance().nextBytes(keyBytes);
        this.skeySpec = new SecretKeySpec(keyBytes, cipherAlgorithm.jceId);
        this.tempFile = TempFile.createTempFile("poi-temp-data", ".tmp");
    }

    public OutputStream getOutputStream() throws IOException {
        Cipher ciEnc = CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 1, PADDING);
        this.outputStream = new CountingOutputStream(new CipherOutputStream(Files.newOutputStream(this.tempFile.toPath(), new OpenOption[0]), ciEnc));
        return this.outputStream;
    }

    public InputStream getInputStream() throws IOException {
        Cipher ciDec = CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 2, PADDING);
        return new CipherInputStream(Files.newInputStream(this.tempFile.toPath(), new OpenOption[0]), ciDec);
    }

    public long getByteCount() {
        if (this.outputStream == null) {
            return 0L;
        }
        return this.outputStream.getByteCount();
    }

    public void dispose() {
        if (!this.tempFile.delete()) {
            LogBuilder atWarn = LOG.atWarn();
            final File file = this.tempFile;
            file.getClass();
            atWarn.log("{} can't be removed (or was already removed).", new Supplier() { // from class: org.apache.poi.poifs.crypt.temp.EncryptedTempData$$ExternalSyntheticLambda0
                @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                public final Object get() {
                    String absolutePath;
                    absolutePath = file.getAbsolutePath();
                    return absolutePath;
                }
            });
        }
    }
}
