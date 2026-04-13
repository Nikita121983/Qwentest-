package org.apache.poi.poifs.crypt.agile;

import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;

/* loaded from: classes10.dex */
public class AgileEncryptionVerifier extends EncryptionVerifier {
    private int blockSize;
    private int keyBits;

    public AgileEncryptionVerifier(String descriptor) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(descriptor));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AgileEncryptionVerifier(EncryptionDocument ed) {
        this.keyBits = -1;
        this.blockSize = -1;
        PasswordKeyEncryptor keyData = null;
        for (KeyEncryptor ke : ed.getKeyEncryptors()) {
            keyData = ke.getPasswordKeyEncryptor();
            if (keyData != null) {
                break;
            }
        }
        if (keyData == null || keyData.getHashSize() == null) {
            throw new IllegalArgumentException("encryptedKey not set");
        }
        setCipherAlgorithm(keyData.getCipherAlgorithm());
        setKeySize(keyData.getKeyBits().intValue());
        Integer blockSize = keyData.getBlockSize();
        if (blockSize == null) {
            throw new IllegalArgumentException("blockSize not set");
        }
        setBlockSize(blockSize.intValue());
        Integer hashSize = keyData.getHashSize();
        if (hashSize == null) {
            throw new IllegalArgumentException("hashSize not set");
        }
        HashAlgorithm ha = keyData.getHashAlgorithm();
        setHashAlgorithm(ha);
        if (getHashAlgorithm().hashSize != hashSize.intValue()) {
            throw new EncryptedDocumentException("Unsupported hash algorithm: " + keyData.getHashAlgorithm() + " @ " + hashSize + " bytes");
        }
        Integer spinCount = keyData.getSpinCount();
        if (spinCount != null) {
            setSpinCount(spinCount.intValue());
        }
        setEncryptedVerifier(keyData.getEncryptedVerifierHashInput());
        setSalt(keyData.getSaltValue());
        setEncryptedKey(keyData.getEncryptedKeyValue());
        setEncryptedVerifierHash(keyData.getEncryptedVerifierHashValue());
        Integer saltSize = keyData.getSaltSize();
        if (saltSize == null || saltSize.intValue() != getSalt().length) {
            throw new EncryptedDocumentException("Invalid salt size");
        }
        setChainingMode(keyData.getCipherChaining());
        if (keyData.getCipherChaining() != ChainingMode.cbc && keyData.getCipherChaining() != ChainingMode.cfb) {
            throw new EncryptedDocumentException("Unsupported chaining mode - " + keyData.getCipherChaining());
        }
    }

    public AgileEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        this.keyBits = -1;
        this.blockSize = -1;
        setCipherAlgorithm(cipherAlgorithm);
        setHashAlgorithm(hashAlgorithm);
        setChainingMode(chainingMode);
        setKeySize(keyBits);
        setBlockSize(blockSize);
        setSpinCount(BZip2Constants.BASEBLOCKSIZE);
    }

    public AgileEncryptionVerifier(AgileEncryptionVerifier other) {
        super(other);
        this.keyBits = -1;
        this.blockSize = -1;
        this.keyBits = other.keyBits;
        this.blockSize = other.blockSize;
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setSalt(byte[] salt) {
        if (salt == null || salt.length != getCipherAlgorithm().blockSize) {
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

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public void setEncryptedKey(byte[] encryptedKey) {
        super.setEncryptedKey(encryptedKey);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier, org.apache.poi.common.Duplicatable
    public AgileEncryptionVerifier copy() {
        return new AgileEncryptionVerifier(this);
    }

    public int getKeySize() {
        return this.keyBits;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public void setKeySize(int keyBits) {
        this.keyBits = keyBits;
        for (int allowedBits : getCipherAlgorithm().allowedKeySize) {
            if (allowedBits == keyBits) {
                return;
            }
        }
        throw new EncryptedDocumentException("KeySize " + keyBits + " not allowed for cipher " + getCipherAlgorithm());
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public final void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        super.setCipherAlgorithm(cipherAlgorithm);
        if (cipherAlgorithm.allowedKeySize.length == 1) {
            setKeySize(cipherAlgorithm.defaultKeySize);
        }
    }
}
