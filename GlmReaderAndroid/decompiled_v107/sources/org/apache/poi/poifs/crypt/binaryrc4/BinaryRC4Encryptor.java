package org.apache.poi.poifs.crypt.binaryrc4;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RandomSingleton;

/* loaded from: classes10.dex */
public class BinaryRC4Encryptor extends Encryptor {
    private int chunkSize;

    /* JADX INFO: Access modifiers changed from: protected */
    public BinaryRC4Encryptor() {
        this.chunkSize = 512;
    }

    protected BinaryRC4Encryptor(BinaryRC4Encryptor other) {
        super(other);
        this.chunkSize = 512;
        this.chunkSize = other.chunkSize;
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password) {
        SecureRandom r = RandomSingleton.getInstance();
        byte[] salt = new byte[16];
        byte[] verifier = new byte[16];
        r.nextBytes(salt);
        r.nextBytes(verifier);
        confirmPassword(password, null, null, verifier, salt, null);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        BinaryRC4EncryptionVerifier ver = (BinaryRC4EncryptionVerifier) getEncryptionInfo().getVerifier();
        ver.setSalt(verifierSalt);
        SecretKey skey = BinaryRC4Decryptor.generateSecretKey(password, ver);
        setSecretKey(skey);
        try {
            Cipher cipher = BinaryRC4Decryptor.initCipherForBlock(null, 0, getEncryptionInfo(), skey, 1);
            byte[] encryptedVerifier = new byte[16];
            cipher.update(verifier, 0, 16, encryptedVerifier);
            ver.setEncryptedVerifier(encryptedVerifier);
            HashAlgorithm hashAlgo = ver.getHashAlgorithm();
            MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
            byte[] calcVerifierHash = hashAlg.digest(verifier);
            byte[] encryptedVerifierHash = cipher.doFinal(calcVerifierHash);
            ver.setEncryptedVerifierHash(encryptedVerifierHash);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        return new BinaryRC4CipherOutputStream(dir);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public BinaryRC4CipherOutputStream getDataStream(OutputStream stream, int initialOffset) throws IOException, GeneralSecurityException {
        return new BinaryRC4CipherOutputStream(stream);
    }

    protected int getKeySizeInBytes() {
        return getEncryptionInfo().getHeader().getKeySize() / 8;
    }

    protected void createEncryptionInfoEntry(DirectoryNode dir) throws IOException {
        DataSpaceMapUtils.addDefaultDataSpace(dir);
        final EncryptionInfo info = getEncryptionInfo();
        final BinaryRC4EncryptionHeader header = (BinaryRC4EncryptionHeader) info.getHeader();
        final BinaryRC4EncryptionVerifier verifier = (BinaryRC4EncryptionVerifier) info.getVerifier();
        EncryptionRecord er = new EncryptionRecord() { // from class: org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4Encryptor$$ExternalSyntheticLambda0
            @Override // org.apache.poi.poifs.crypt.standard.EncryptionRecord
            public final void write(LittleEndianByteArrayOutputStream littleEndianByteArrayOutputStream) {
                BinaryRC4Encryptor.lambda$createEncryptionInfoEntry$0(EncryptionInfo.this, header, verifier, littleEndianByteArrayOutputStream);
            }
        };
        DataSpaceMapUtils.createEncryptionEntry(dir, EncryptionInfo.ENCRYPTION_INFO_ENTRY, er);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createEncryptionInfoEntry$0(EncryptionInfo info, BinaryRC4EncryptionHeader header, BinaryRC4EncryptionVerifier verifier, LittleEndianByteArrayOutputStream bos) {
        bos.writeShort(info.getVersionMajor());
        bos.writeShort(info.getVersionMinor());
        header.write(bos);
        verifier.write(bos);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public BinaryRC4Encryptor copy() {
        return new BinaryRC4Encryptor(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public class BinaryRC4CipherOutputStream extends ChunkedCipherOutputStream {
        public BinaryRC4CipherOutputStream(OutputStream stream) throws IOException, GeneralSecurityException {
            super(stream, BinaryRC4Encryptor.this.chunkSize);
        }

        public BinaryRC4CipherOutputStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
            super(dir, BinaryRC4Encryptor.this.chunkSize);
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected Cipher initCipherForBlock(Cipher cipher, int block, boolean lastChunk) throws GeneralSecurityException {
            return BinaryRC4Decryptor.initCipherForBlock(cipher, block, BinaryRC4Encryptor.this.getEncryptionInfo(), BinaryRC4Encryptor.this.getSecretKey(), 1);
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected void calculateChecksum(File file, int i) {
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) throws IOException, GeneralSecurityException {
            BinaryRC4Encryptor.this.createEncryptionInfoEntry(dir);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            writeChunk(false);
            super.flush();
        }
    }
}
