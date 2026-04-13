package org.apache.poi.poifs.crypt.agile;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public class AgileEncryptionHeader extends EncryptionHeader {
    private byte[] encryptedHmacKey;
    private byte[] encryptedHmacValue;

    public AgileEncryptionHeader(String descriptor) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(descriptor));
    }

    public AgileEncryptionHeader(AgileEncryptionHeader other) {
        super(other);
        this.encryptedHmacKey = other.encryptedHmacKey == null ? null : (byte[]) other.encryptedHmacKey.clone();
        this.encryptedHmacValue = other.encryptedHmacValue != null ? (byte[]) other.encryptedHmacValue.clone() : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AgileEncryptionHeader(EncryptionDocument ed) {
        try {
            KeyData keyData = ed.getKeyData();
            if (keyData == null) {
                throw new NullPointerException("keyData not set");
            }
            int keyBits = keyData.getKeyBits().intValue();
            CipherAlgorithm ca = keyData.getCipherAlgorithm();
            setCipherAlgorithm(ca);
            setCipherProvider(ca.provider);
            setKeySize(keyBits);
            setFlags(0);
            setSizeExtra(0);
            setCspName(null);
            setBlockSize(keyData.getBlockSize() != null ? keyData.getBlockSize().intValue() : 0);
            setChainingMode(keyData.getCipherChaining());
            if (getChainingMode() != ChainingMode.cbc && getChainingMode() != ChainingMode.cfb) {
                throw new EncryptedDocumentException("Unsupported chaining mode - " + keyData.getCipherChaining());
            }
            Integer hashSizeObj = keyData.getHashSize();
            if (hashSizeObj == null) {
                throw new EncryptedDocumentException("Invalid hash size: " + hashSizeObj);
            }
            int hashSize = hashSizeObj.intValue();
            HashAlgorithm ha = keyData.getHashAlgorithm();
            setHashAlgorithm(ha);
            if (getHashAlgorithm().hashSize != hashSize) {
                throw new EncryptedDocumentException("Unsupported hash algorithm: " + keyData.getHashAlgorithm() + " @ " + hashSize + " bytes");
            }
            if (keyData.getSaltSize() == null) {
                throw new EncryptedDocumentException("Invalid salt length: " + keyData.getSaltSize());
            }
            int saltLength = keyData.getSaltSize().intValue();
            setKeySalt(keyData.getSaltValue());
            if (getKeySalt().length != saltLength) {
                throw new EncryptedDocumentException("Invalid salt length: " + getKeySalt().length + " and " + saltLength);
            }
            DataIntegrity di = ed.getDataIntegrity();
            setEncryptedHmacKey(di.getEncryptedHmacKey());
            setEncryptedHmacValue(di.getEncryptedHmacValue());
        } catch (Exception e) {
            throw new EncryptedDocumentException("Unable to parse keyData");
        }
    }

    public AgileEncryptionHeader(CipherAlgorithm algorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        setCipherAlgorithm(algorithm);
        setHashAlgorithm(hashAlgorithm);
        setKeySize(keyBits);
        setBlockSize(blockSize);
        setChainingMode(chainingMode);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionHeader
    public void setKeySalt(byte[] salt) {
        if (salt == null || salt.length != getBlockSize()) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setKeySalt(salt);
    }

    public byte[] getEncryptedHmacKey() {
        return this.encryptedHmacKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEncryptedHmacKey(byte[] encryptedHmacKey) {
        this.encryptedHmacKey = encryptedHmacKey == null ? null : (byte[]) encryptedHmacKey.clone();
    }

    public byte[] getEncryptedHmacValue() {
        return this.encryptedHmacValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEncryptedHmacValue(byte[] encryptedHmacValue) {
        this.encryptedHmacValue = encryptedHmacValue == null ? null : (byte[]) encryptedHmacValue.clone();
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionHeader, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return AgileEncryptionHeader.this.m2496x31742994();
            }
        }, "encryptedHmacKey", new Supplier() { // from class: org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return AgileEncryptionHeader.this.getEncryptedHmacKey();
            }
        }, "encryptedHmacValue", new Supplier() { // from class: org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return AgileEncryptionHeader.this.getEncryptedHmacValue();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-poifs-crypt-agile-AgileEncryptionHeader, reason: not valid java name */
    public /* synthetic */ Object m2496x31742994() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionHeader, org.apache.poi.common.Duplicatable
    public AgileEncryptionHeader copy() {
        return new AgileEncryptionHeader(this);
    }
}
