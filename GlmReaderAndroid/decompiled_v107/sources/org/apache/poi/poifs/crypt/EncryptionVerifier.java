package org.apache.poi.poifs.crypt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;

/* loaded from: classes10.dex */
public abstract class EncryptionVerifier implements GenericRecord, Duplicatable {
    private ChainingMode chainingMode;
    private CipherAlgorithm cipherAlgorithm;
    private byte[] encryptedKey;
    private byte[] encryptedVerifier;
    private byte[] encryptedVerifierHash;
    private HashAlgorithm hashAlgorithm;
    private byte[] salt;
    private int spinCount;

    @Override // org.apache.poi.common.Duplicatable
    public abstract EncryptionVerifier copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public EncryptionVerifier() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EncryptionVerifier(EncryptionVerifier other) {
        this.salt = other.salt == null ? null : (byte[]) other.salt.clone();
        this.encryptedVerifier = other.encryptedVerifier == null ? null : (byte[]) other.encryptedVerifier.clone();
        this.encryptedVerifierHash = other.encryptedVerifierHash == null ? null : (byte[]) other.encryptedVerifierHash.clone();
        this.encryptedKey = other.encryptedKey != null ? (byte[]) other.encryptedKey.clone() : null;
        this.spinCount = other.spinCount;
        this.cipherAlgorithm = other.cipherAlgorithm;
        this.chainingMode = other.chainingMode;
        this.hashAlgorithm = other.hashAlgorithm;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public byte[] getEncryptedVerifier() {
        return this.encryptedVerifier;
    }

    public byte[] getEncryptedVerifierHash() {
        return this.encryptedVerifierHash;
    }

    public int getSpinCount() {
        return this.spinCount;
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public ChainingMode getChainingMode() {
        return this.chainingMode;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt == null ? null : (byte[]) salt.clone();
    }

    public void setEncryptedVerifier(byte[] encryptedVerifier) {
        this.encryptedVerifier = encryptedVerifier == null ? null : (byte[]) encryptedVerifier.clone();
    }

    public void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        this.encryptedVerifierHash = encryptedVerifierHash == null ? null : (byte[]) encryptedVerifierHash.clone();
    }

    public void setEncryptedKey(byte[] encryptedKey) {
        this.encryptedKey = encryptedKey == null ? null : (byte[]) encryptedKey.clone();
    }

    public void setSpinCount(int spinCount) {
        this.spinCount = spinCount;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public void setChainingMode(ChainingMode chainingMode) {
        this.chainingMode = chainingMode;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("salt", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getSalt();
            }
        });
        m.put("encryptedVerifier", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getEncryptedVerifier();
            }
        });
        m.put("encryptedVerifierHash", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getEncryptedVerifierHash();
            }
        });
        m.put("encryptedKey", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getEncryptedKey();
            }
        });
        m.put("spinCount", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionVerifier.this.getSpinCount());
            }
        });
        m.put("cipherAlgorithm", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getCipherAlgorithm();
            }
        });
        m.put("chainingMode", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getChainingMode();
            }
        });
        m.put("hashAlgorithm", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionVerifier$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionVerifier.this.getHashAlgorithm();
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
