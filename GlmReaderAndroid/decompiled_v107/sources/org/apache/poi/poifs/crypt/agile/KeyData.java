package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes10.dex */
public class KeyData {
    private Integer blockSize;
    private CipherAlgorithm cipherAlgorithm;
    private ChainingMode cipherChaining;
    private HashAlgorithm hashAlgorithm;
    private Integer hashSize;
    private Integer keyBits;
    private Integer saltSize;
    private byte[] saltValue;

    public KeyData() {
    }

    public KeyData(Element parent) {
        Element keyData = EncryptionDocument.getTag(parent, "http://schemas.microsoft.com/office/2006/encryption", "keyData");
        if (keyData == null) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        this.saltSize = EncryptionDocument.getIntAttr(keyData, "saltSize");
        this.blockSize = EncryptionDocument.getIntAttr(keyData, "blockSize");
        this.keyBits = EncryptionDocument.getIntAttr(keyData, "keyBits");
        this.hashSize = EncryptionDocument.getIntAttr(keyData, "hashSize");
        this.cipherAlgorithm = CipherAlgorithm.fromXmlId(keyData.getAttribute("cipherAlgorithm"), this.keyBits == null ? -1 : this.keyBits.intValue());
        this.cipherChaining = ChainingMode.fromXmlId(keyData.getAttribute("cipherChaining"));
        this.hashAlgorithm = HashAlgorithm.fromEcmaId(keyData.getAttribute("hashAlgorithm"));
        if (this.cipherAlgorithm == null || this.cipherChaining == null || this.hashAlgorithm == null) {
            throw new EncryptedDocumentException("Cipher algorithm, chaining mode or hash algorithm was null");
        }
        this.saltValue = EncryptionDocument.getBinAttr(keyData, "saltValue");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(Element encryption) {
        Document doc = encryption.getOwnerDocument();
        Element keyData = (Element) encryption.appendChild(doc.createElementNS("http://schemas.microsoft.com/office/2006/encryption", "keyData"));
        EncryptionDocument.setIntAttr(keyData, "saltSize", this.saltSize);
        EncryptionDocument.setIntAttr(keyData, "blockSize", this.blockSize);
        EncryptionDocument.setIntAttr(keyData, "keyBits", this.keyBits);
        EncryptionDocument.setIntAttr(keyData, "hashSize", this.hashSize);
        EncryptionDocument.setAttr(keyData, "cipherAlgorithm", this.cipherAlgorithm == null ? null : this.cipherAlgorithm.xmlId);
        EncryptionDocument.setAttr(keyData, "cipherChaining", this.cipherChaining == null ? null : this.cipherChaining.xmlId);
        EncryptionDocument.setAttr(keyData, "hashAlgorithm", this.hashAlgorithm != null ? this.hashAlgorithm.ecmaString : null);
        EncryptionDocument.setBinAttr(keyData, "saltValue", this.saltValue);
    }

    public Integer getSaltSize() {
        return this.saltSize;
    }

    public void setSaltSize(Integer saltSize) {
        this.saltSize = saltSize;
    }

    public Integer getBlockSize() {
        return this.blockSize;
    }

    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    public Integer getKeyBits() {
        return this.keyBits;
    }

    public void setKeyBits(Integer keyBits) {
        this.keyBits = keyBits;
    }

    public Integer getHashSize() {
        return this.hashSize;
    }

    public void setHashSize(Integer hashSize) {
        this.hashSize = hashSize;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    public ChainingMode getCipherChaining() {
        return this.cipherChaining;
    }

    public void setCipherChaining(ChainingMode cipherChaining) {
        this.cipherChaining = cipherChaining;
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public byte[] getSaltValue() {
        return this.saltValue;
    }

    public void setSaltValue(byte[] saltValue) {
        this.saltValue = saltValue == null ? null : (byte[]) saltValue.clone();
    }
}
