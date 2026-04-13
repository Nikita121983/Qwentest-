package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes10.dex */
public class DataIntegrity {
    private byte[] encryptedHmacKey;
    private byte[] encryptedHmacValue;

    public DataIntegrity() {
    }

    public DataIntegrity(Element parent) {
        Element dataIntegrity = EncryptionDocument.getTag(parent, "http://schemas.microsoft.com/office/2006/encryption", "dataIntegrity");
        if (dataIntegrity == null) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        this.encryptedHmacKey = EncryptionDocument.getBinAttr(dataIntegrity, "encryptedHmacKey");
        this.encryptedHmacValue = EncryptionDocument.getBinAttr(dataIntegrity, "encryptedHmacValue");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(Element encryption) {
        Document doc = encryption.getOwnerDocument();
        Element dataIntegrity = (Element) encryption.appendChild(doc.createElementNS("http://schemas.microsoft.com/office/2006/encryption", "dataIntegrity"));
        EncryptionDocument.setBinAttr(dataIntegrity, "encryptedHmacKey", this.encryptedHmacKey);
        EncryptionDocument.setBinAttr(dataIntegrity, "encryptedHmacValue", this.encryptedHmacValue);
    }

    public byte[] getEncryptedHmacKey() {
        return this.encryptedHmacKey;
    }

    public void setEncryptedHmacKey(byte[] encryptedHmacKey) {
        this.encryptedHmacKey = encryptedHmacKey;
    }

    public byte[] getEncryptedHmacValue() {
        return this.encryptedHmacValue;
    }

    public void setEncryptedHmacValue(byte[] encryptedHmacValue) {
        this.encryptedHmacValue = encryptedHmacValue;
    }
}
