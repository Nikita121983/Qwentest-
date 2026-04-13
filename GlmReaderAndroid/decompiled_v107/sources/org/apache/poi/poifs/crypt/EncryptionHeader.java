package org.apache.poi.poifs.crypt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;

/* loaded from: classes10.dex */
public abstract class EncryptionHeader implements GenericRecord, Duplicatable {
    private int blockSize;
    private ChainingMode chainingMode;
    private CipherAlgorithm cipherAlgorithm;
    private String cspName;
    private int flags;
    private HashAlgorithm hashAlgorithm;
    private int keyBits;
    private byte[] keySalt;
    private CipherProvider providerType;
    private int sizeExtra;

    @Override // org.apache.poi.common.Duplicatable
    public abstract EncryptionHeader copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public EncryptionHeader() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EncryptionHeader(EncryptionHeader other) {
        this.flags = other.flags;
        this.sizeExtra = other.sizeExtra;
        this.cipherAlgorithm = other.cipherAlgorithm;
        this.hashAlgorithm = other.hashAlgorithm;
        this.keyBits = other.keyBits;
        this.blockSize = other.blockSize;
        this.providerType = other.providerType;
        this.chainingMode = other.chainingMode;
        this.keySalt = other.keySalt == null ? null : (byte[]) other.keySalt.clone();
        this.cspName = other.cspName;
    }

    public ChainingMode getChainingMode() {
        return this.chainingMode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setChainingMode(ChainingMode chainingMode) {
        this.chainingMode = chainingMode;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getSizeExtra() {
        return this.sizeExtra;
    }

    public void setSizeExtra(int sizeExtra) {
        this.sizeExtra = sizeExtra;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
        if (cipherAlgorithm.allowedKeySize.length == 1) {
            setKeySize(cipherAlgorithm.defaultKeySize);
        }
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public int getKeySize() {
        return this.keyBits;
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

    public int getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public byte[] getKeySalt() {
        return this.keySalt;
    }

    public void setKeySalt(byte[] salt) {
        this.keySalt = salt == null ? null : (byte[]) salt.clone();
    }

    public CipherProvider getCipherProvider() {
        return this.providerType;
    }

    public void setCipherProvider(CipherProvider providerType) {
        this.providerType = providerType;
    }

    public String getCspName() {
        return this.cspName;
    }

    public void setCspName(String cspName) {
        this.cspName = cspName;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("flags", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionHeader.this.getFlags());
            }
        });
        m.put("sizeExtra", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionHeader.this.getSizeExtra());
            }
        });
        m.put("cipherAlgorithm", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getCipherAlgorithm();
            }
        });
        m.put("hashAlgorithm", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getHashAlgorithm();
            }
        });
        m.put("keyBits", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionHeader.this.getKeySize());
            }
        });
        m.put("blockSize", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EncryptionHeader.this.getBlockSize());
            }
        });
        m.put("providerType", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getCipherProvider();
            }
        });
        m.put("chainingMode", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getChainingMode();
            }
        });
        m.put("keySalt", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getKeySalt();
            }
        });
        m.put("cspName", new Supplier() { // from class: org.apache.poi.poifs.crypt.EncryptionHeader$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return EncryptionHeader.this.getCspName();
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
