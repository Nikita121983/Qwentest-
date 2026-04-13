package org.apache.poi.poifs.crypt.standard;

import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSWriterEvent;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public class StandardEncryptor extends Encryptor {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) StandardEncryptor.class);

    /* JADX INFO: Access modifiers changed from: protected */
    public StandardEncryptor() {
    }

    protected StandardEncryptor(StandardEncryptor other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password) {
        SecureRandom r = RandomSingleton.getInstance();
        byte[] salt = new byte[16];
        byte[] verifier = new byte[16];
        r.nextBytes(salt);
        r.nextBytes(verifier);
        confirmPassword(password, null, null, salt, verifier, null);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        StandardEncryptionVerifier ver = (StandardEncryptionVerifier) getEncryptionInfo().getVerifier();
        ver.setSalt(verifierSalt);
        SecretKey secretKey = StandardDecryptor.generateSecretKey(password, ver, getKeySizeInBytes());
        setSecretKey(secretKey);
        Cipher cipher = getCipher(secretKey, null);
        try {
            byte[] encryptedVerifier = cipher.doFinal(verifier);
            MessageDigest hashAlgo = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
            byte[] calcVerifierHash = hashAlgo.digest(verifier);
            int encVerHashSize = ver.getCipherAlgorithm().encryptedVerifierHashLength;
            byte[] encryptedVerifierHash = cipher.doFinal(Arrays.copyOf(calcVerifierHash, encVerHashSize));
            ver.setEncryptedVerifier(encryptedVerifier);
            ver.setEncryptedVerifierHash(encryptedVerifierHash);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cipher getCipher(SecretKey key, String padding) {
        EncryptionVerifier ver = getEncryptionInfo().getVerifier();
        return CryptoFunctions.getCipher(key, ver.getCipherAlgorithm(), ver.getChainingMode(), null, 1, padding);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        createEncryptionInfoEntry(dir);
        DataSpaceMapUtils.addDefaultDataSpace(dir);
        return new StandardCipherOutputStream(this, dir);
    }

    /* loaded from: classes10.dex */
    protected class StandardCipherOutputStream extends FilterOutputStream implements POIFSWriterListener {
        protected long countBytes;
        protected final boolean deleteFile;
        protected final DirectoryNode dir;
        protected final File fileOut;

        private StandardCipherOutputStream(DirectoryNode dir, File fileOut, boolean deleteFile) throws IOException {
            super(new CipherOutputStream(Files.newOutputStream(fileOut.toPath(), new OpenOption[0]), StandardEncryptor.this.getCipher(StandardEncryptor.this.getSecretKey(), "PKCS5Padding")));
            this.deleteFile = deleteFile;
            this.fileOut = fileOut;
            this.dir = dir;
        }

        protected StandardCipherOutputStream(StandardEncryptor this$0, DirectoryNode dir) throws IOException {
            this(dir, TempFile.createTempFile("encrypted_package", "crypt"), true);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            this.out.write(b, off, len);
            this.countBytes += len;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int b) throws IOException {
            this.out.write(b);
            this.countBytes++;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            writeToPOIFS();
            if (this.deleteFile && this.fileOut != null) {
                this.fileOut.delete();
            }
        }

        void writeToPOIFS() throws IOException {
            int oleStreamSize = (int) (this.fileOut.length() + 8);
            this.dir.createDocument(Decryptor.DEFAULT_POIFS_ENTRY, oleStreamSize, this);
        }

        @Override // org.apache.poi.poifs.filesystem.POIFSWriterListener
        public void processPOIFSWriterEvent(POIFSWriterEvent event) {
            try {
                LittleEndianOutputStream leos = new LittleEndianOutputStream(event.getStream());
                leos.writeLong(this.countBytes);
                InputStream fis = Files.newInputStream(this.fileOut.toPath(), new OpenOption[0]);
                try {
                    IOUtils.copy(fis, leos);
                    if (fis != null) {
                        fis.close();
                    }
                    if (!this.fileOut.delete()) {
                        StandardEncryptor.LOG.atError().log("Can't delete temporary encryption file: {}", this.fileOut);
                    }
                    leos.close();
                } finally {
                }
            } catch (IOException e) {
                throw new EncryptedDocumentException(e);
            }
        }
    }

    protected int getKeySizeInBytes() {
        return getEncryptionInfo().getHeader().getKeySize() / 8;
    }

    protected void createEncryptionInfoEntry(DirectoryNode dir) throws IOException {
        final EncryptionInfo info = getEncryptionInfo();
        final StandardEncryptionHeader header = (StandardEncryptionHeader) info.getHeader();
        final StandardEncryptionVerifier verifier = (StandardEncryptionVerifier) info.getVerifier();
        EncryptionRecord er = new EncryptionRecord() { // from class: org.apache.poi.poifs.crypt.standard.StandardEncryptor$$ExternalSyntheticLambda0
            @Override // org.apache.poi.poifs.crypt.standard.EncryptionRecord
            public final void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
                StandardEncryptor.lambda$createEncryptionInfoEntry$0(EncryptionInfo.this, header, verifier, littleEndianByteArrayOutputStream);
            }
        };
        DataSpaceMapUtils.createEncryptionEntry(dir, EncryptionInfo.ENCRYPTION_INFO_ENTRY, er);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createEncryptionInfoEntry$0(EncryptionInfo info, StandardEncryptionHeader header, StandardEncryptionVerifier verifier, LittleEndianByteArrayOutputStream bos) {
        bos.writeShort(info.getVersionMajor());
        bos.writeShort(info.getVersionMinor());
        bos.writeInt(info.getEncryptionFlags());
        header.write(bos);
        verifier.write(bos);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public StandardEncryptor copy() {
        return new StandardEncryptor(this);
    }
}
