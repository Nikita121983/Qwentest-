package org.apache.poi.poifs.crypt.binaryrc4;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public class BinaryRC4EncryptionVerifier extends EncryptionVerifier implements EncryptionRecord {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public BinaryRC4EncryptionVerifier() {
        setSpinCount(-1);
        setCipherAlgorithm(CipherAlgorithm.rc4);
        setChainingMode(null);
        setEncryptedKey(null);
        setHashAlgorithm(HashAlgorithm.md5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BinaryRC4EncryptionVerifier(LittleEndianInput is) {
        byte[] salt = new byte[16];
        is.readFully(salt);
        setSalt(salt);
        byte[] encryptedVerifier = new byte[16];
        is.readFully(encryptedVerifier);
        setEncryptedVerifier(encryptedVerifier);
        byte[] encryptedVerifierHash = new byte[16];
        is.readFully(encryptedVerifierHash);
        setEncryptedVerifierHash(encryptedVerifierHash);
        setSpinCount(-1);
        setCipherAlgorithm(CipherAlgorithm.rc4);
        setChainingMode(null);
        setEncryptedKey(null);
        setHashAlgorithm(HashAlgorithm.md5);
    }

    protected BinaryRC4EncryptionVerifier(BinaryRC4EncryptionVerifier other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setSalt(byte[] salt) {
        if (salt == null || salt.length != 16) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setSalt(salt);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        super.setEncryptedVerifierHash(encryptedVerifierHash);
    }

    @Override // org.apache.poi.poifs.crypt.standard.EncryptionRecord
    public void write(LittleEndianByteArrayOutputStream bos) {
        byte[] salt = getSalt();
        if (salt.length != 16) {
            throw new AssertionError();
        }
        bos.write(salt);
        byte[] encryptedVerifier = getEncryptedVerifier();
        if (encryptedVerifier.length != 16) {
            throw new AssertionError();
        }
        bos.write(encryptedVerifier);
        byte[] encryptedVerifierHash = getEncryptedVerifierHash();
        if (encryptedVerifierHash.length != 16) {
            throw new AssertionError();
        }
        bos.write(encryptedVerifierHash);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier, org.apache.poi.common.Duplicatable
    public BinaryRC4EncryptionVerifier copy() {
        return new BinaryRC4EncryptionVerifier(this);
    }
}
