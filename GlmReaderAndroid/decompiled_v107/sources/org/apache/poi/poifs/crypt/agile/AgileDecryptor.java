package org.apache.poi.poifs.crypt.agile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class AgileDecryptor extends Decryptor {
    private long _length;
    static final byte[] kVerifierInputBlock = longToBytes(-96877461722390919L);
    static final byte[] kHashedVerifierBlock = longToBytes(-2906493647876705202L);
    static final byte[] kCryptoKeyBlock = longToBytes(1472127217842311382L);
    static final byte[] kIntegrityKeyBlock = longToBytes(6895764199477731830L);
    static final byte[] kIntegrityValueBlock = longToBytes(-6888397455483960269L);

    /* JADX INFO: Access modifiers changed from: protected */
    public AgileDecryptor() {
        this._length = -1L;
    }

    protected AgileDecryptor(AgileDecryptor other) {
        super(other);
        this._length = -1L;
        this._length = other._length;
    }

    private static byte[] longToBytes(long l) {
        return ByteBuffer.allocate(8).putLong(l).array();
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public boolean verifyPassword(String password) throws GeneralSecurityException {
        AgileEncryptionVerifier ver = (AgileEncryptionVerifier) getEncryptionInfo().getVerifier();
        AgileEncryptionHeader header = (AgileEncryptionHeader) getEncryptionInfo().getHeader();
        int blockSize = header.getBlockSize();
        byte[] pwHash = CryptoFunctions.hashPassword(password, ver.getHashAlgorithm(), ver.getSalt(), ver.getSpinCount());
        byte[] verfierInputEnc = hashInput(ver, pwHash, kVerifierInputBlock, ver.getEncryptedVerifier(), 2);
        setVerifier(verfierInputEnc);
        MessageDigest hashMD = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
        byte[] verifierHash = hashMD.digest(verfierInputEnc);
        byte[] verifierHashDec = hashInput(ver, pwHash, kHashedVerifierBlock, ver.getEncryptedVerifierHash(), 2);
        byte[] verifierHashDec2 = CryptoFunctions.getBlock0(verifierHashDec, ver.getHashAlgorithm().hashSize);
        byte[] keyspec = hashInput(ver, pwHash, kCryptoKeyBlock, ver.getEncryptedKey(), 2);
        SecretKeySpec secretKey = new SecretKeySpec(CryptoFunctions.getBlock0(keyspec, header.getKeySize() / 8), header.getCipherAlgorithm().jceId);
        byte[] vec = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), kIntegrityKeyBlock, blockSize);
        CipherAlgorithm cipherAlgo = header.getCipherAlgorithm();
        Cipher cipher = CryptoFunctions.getCipher(secretKey, cipherAlgo, header.getChainingMode(), vec, 2);
        byte[] hmacKey = cipher.doFinal(header.getEncryptedHmacKey());
        byte[] hmacKey2 = CryptoFunctions.getBlock0(hmacKey, header.getHashAlgorithm().hashSize);
        byte[] vec2 = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), kIntegrityValueBlock, blockSize);
        Cipher cipher2 = CryptoFunctions.getCipher(secretKey, cipherAlgo, ver.getChainingMode(), vec2, 2);
        byte[] hmacValue = cipher2.doFinal(header.getEncryptedHmacValue());
        byte[] hmacValue2 = CryptoFunctions.getBlock0(hmacValue, header.getHashAlgorithm().hashSize);
        if (Arrays.equals(verifierHashDec2, verifierHash)) {
            setSecretKey(secretKey);
            setIntegrityHmacKey(hmacKey2);
            setIntegrityHmacValue(hmacValue2);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getNextBlockSize(int inputLen, int blockSize) {
        return ((int) Math.ceil(inputLen / blockSize)) * blockSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] hashInput(AgileEncryptionVerifier ver, byte[] pwHash, byte[] blockKey, byte[] inputKey, int cipherMode) {
        CipherAlgorithm cipherAlgo = ver.getCipherAlgorithm();
        ChainingMode chainMode = ver.getChainingMode();
        int keySize = ver.getKeySize() / 8;
        int blockSize = ver.getBlockSize();
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        byte[] intermedKey = CryptoFunctions.generateKey(pwHash, hashAlgo, blockKey, keySize);
        SecretKey skey = new SecretKeySpec(intermedKey, cipherAlgo.jceId);
        byte[] iv = CryptoFunctions.generateIv(hashAlgo, ver.getSalt(), null, blockSize);
        Cipher cipher = CryptoFunctions.getCipher(skey, cipherAlgo, chainMode, iv, cipherMode);
        if (inputKey == null) {
            throw new EncryptedDocumentException("Cannot has input without inputKey");
        }
        try {
            byte[] hashFinal = cipher.doFinal(CryptoFunctions.getBlock0(inputKey, getNextBlockSize(inputKey.length, blockSize)));
            return hashFinal;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public InputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        DocumentInputStream dis = dir.createDocumentInputStream(Decryptor.DEFAULT_POIFS_ENTRY);
        this._length = dis.readLong();
        return new AgileCipherInputStream(dis, this._length);
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public long getLength() {
        if (this._length == -1) {
            throw new IllegalStateException("EcmaDecryptor.getDataStream() was not called");
        }
        return this._length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Cipher initCipherForBlock(Cipher existing, int block, boolean lastChunk, EncryptionInfo encryptionInfo, SecretKey skey, int encryptionMode) throws GeneralSecurityException {
        SecretKey skey2;
        int encryptionMode2;
        AlgorithmParameterSpec aps;
        EncryptionHeader header = encryptionInfo.getHeader();
        String padding = lastChunk ? "PKCS5Padding" : "NoPadding";
        if (existing == null || !existing.getAlgorithm().endsWith(padding)) {
            skey2 = skey;
            encryptionMode2 = encryptionMode;
            existing = CryptoFunctions.getCipher(skey2, header.getCipherAlgorithm(), header.getChainingMode(), header.getKeySalt(), encryptionMode2, padding);
        } else {
            skey2 = skey;
            encryptionMode2 = encryptionMode;
        }
        byte[] blockKey = new byte[4];
        LittleEndian.putInt(blockKey, 0, block);
        byte[] iv = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), blockKey, header.getBlockSize());
        if (header.getCipherAlgorithm() == CipherAlgorithm.rc2) {
            aps = new RC2ParameterSpec(skey2.getEncoded().length * 8, iv);
        } else {
            aps = new IvParameterSpec(iv);
        }
        existing.init(encryptionMode2, skey2, aps);
        return existing;
    }

    /* loaded from: classes10.dex */
    private class AgileCipherInputStream extends ChunkedCipherInputStream {
        public AgileCipherInputStream(DocumentInputStream stream, long size) throws GeneralSecurityException {
            super(stream, size, 4096);
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherInputStream
        protected Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
            return AgileDecryptor.initCipherForBlock(cipher, block, false, AgileDecryptor.this.getEncryptionInfo(), AgileDecryptor.this.getSecretKey(), 2);
        }
    }

    @Override // org.apache.poi.poifs.crypt.Decryptor
    public AgileDecryptor copy() {
        return new AgileDecryptor(this);
    }
}
