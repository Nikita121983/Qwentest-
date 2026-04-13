package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class CryptoAPIDecryptor extends Decryptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int chunkSize;
    private long length;

    /* loaded from: classes10.dex */
    static class StreamDescriptorEntry {
        static final BitField flagStream = BitFieldFactory.getInstance(1);
        int block;
        int flags;
        int reserved2;
        String streamName;
        int streamOffset;
        int streamSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CryptoAPIDecryptor() {
        this.length = -1L;
        this.chunkSize = -1;
    }

    protected CryptoAPIDecryptor(CryptoAPIDecryptor other) {
        super(other);
        this.length = -1L;
        this.chunkSize = -1;
        this.length = other.length;
        this.chunkSize = other.chunkSize;
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public boolean verifyPassword(String password) {
        EncryptionVerifier ver = getEncryptionInfo().getVerifier();
        SecretKey skey = generateSecretKey(password, ver);
        try {
            Cipher cipher = initCipherForBlock(null, 0, getEncryptionInfo(), skey, 2);
            byte[] encryptedVerifier = ver.getEncryptedVerifier();
            byte[] verifier = new byte[encryptedVerifier.length];
            cipher.update(encryptedVerifier, 0, encryptedVerifier.length, verifier);
            setVerifier(verifier);
            byte[] encryptedVerifierHash = ver.getEncryptedVerifierHash();
            byte[] verifierHash = cipher.doFinal(encryptedVerifierHash);
            HashAlgorithm hashAlgo = ver.getHashAlgorithm();
            MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
            byte[] calcVerifierHash = hashAlg.digest(verifier);
            if (!Arrays.equals(calcVerifierHash, verifierHash)) {
                return false;
            }
            setSecretKey(skey);
            return true;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
        EncryptionInfo ei = getEncryptionInfo();
        SecretKey sk = getSecretKey();
        return initCipherForBlock(cipher, block, ei, sk, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Cipher initCipherForBlock(Cipher cipher, int block, EncryptionInfo encryptionInfo, SecretKey skey, int encryptMode) throws GeneralSecurityException {
        EncryptionVerifier ver = encryptionInfo.getVerifier();
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        byte[] blockKey = new byte[4];
        LittleEndian.putUInt(blockKey, 0, block);
        MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
        hashAlg.update(skey.getEncoded());
        byte[] encKey = hashAlg.digest(blockKey);
        EncryptionHeader header = encryptionInfo.getHeader();
        int keyBits = header.getKeySize();
        byte[] encKey2 = CryptoFunctions.getBlock0(encKey, keyBits / 8);
        if (keyBits == 40) {
            encKey2 = CryptoFunctions.getBlock0(encKey2, 16);
        }
        SecretKey key = new SecretKeySpec(encKey2, skey.getAlgorithm());
        if (cipher == null) {
            return CryptoFunctions.getCipher(key, header.getCipherAlgorithm(), null, null, encryptMode);
        }
        cipher.init(encryptMode, key);
        return cipher;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static SecretKey generateSecretKey(String password, EncryptionVerifier ver) {
        if (password == null) {
            throw new IllegalArgumentException("Did not receive a password");
        }
        if (password.length() > 255) {
            password = password.substring(0, 255);
        }
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
        hashAlg.update(ver.getSalt());
        byte[] hash = hashAlg.digest(StringUtil.getToUnicodeLE(password));
        return new SecretKeySpec(hash, ver.getCipherAlgorithm().jceId);
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public ChunkedCipherInputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        throw new IOException("not supported");
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public ChunkedCipherInputStream getDataStream(InputStream stream, int size, int initialPos) throws IOException, GeneralSecurityException {
        return new CryptoAPICipherInputStream(stream, size, initialPos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0163  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.poi.poifs.filesystem.POIFSFileSystem getSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode r18, java.lang.String r19) throws java.io.IOException, java.security.GeneralSecurityException {
        /*
            Method dump skipped, instructions count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor.getSummaryEntries(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String):org.apache.poi.poifs.filesystem.POIFSFileSystem");
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public long getLength() {
        if (this.length == -1) {
            throw new IllegalStateException("Decryptor.getDataStream() was not called");
        }
        return this.length;
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public CryptoAPIDecryptor copy() {
        return new CryptoAPIDecryptor(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class CryptoAPICipherInputStream extends ChunkedCipherInputStream {
        @Override // org.apache.poi.poifs.crypt.ChunkedCipherInputStream
        protected Cipher initCipherForBlock(Cipher existing, int block) throws GeneralSecurityException {
            return CryptoAPIDecryptor.this.initCipherForBlock(existing, block);
        }

        public CryptoAPICipherInputStream(InputStream stream, long size, int initialPos) throws GeneralSecurityException {
            super(stream, size, CryptoAPIDecryptor.this.chunkSize, initialPos);
        }
    }
}
