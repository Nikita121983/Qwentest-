package org.apache.poi.poifs.crypt.cryptoapi;

import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public class CryptoAPIEncryptionVerifier extends StandardEncryptionVerifier {
    /* JADX INFO: Access modifiers changed from: protected */
    public CryptoAPIEncryptionVerifier(LittleEndianInput is, CryptoAPIEncryptionHeader header) {
        super(is, header);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CryptoAPIEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        super(cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode);
    }

    protected CryptoAPIEncryptionVerifier(CryptoAPIEncryptionVerifier other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier, org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setSalt(byte[] salt) {
        super.setSalt(salt);
    }

    @Override // org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier, org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override // org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier, org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        super.setEncryptedVerifierHash(encryptedVerifierHash);
    }

    @Override // org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier, org.apache.poi.poifs.crypt.EncryptionVerifier, org.apache.poi.common.Duplicatable
    public CryptoAPIEncryptionVerifier copy() {
        return new CryptoAPIEncryptionVerifier(this);
    }
}
