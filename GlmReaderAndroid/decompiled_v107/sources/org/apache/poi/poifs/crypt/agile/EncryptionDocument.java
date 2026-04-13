package org.apache.poi.poifs.crypt.agile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public class EncryptionDocument {
    static final String ENC_NS = "http://schemas.microsoft.com/office/2006/encryption";
    private DataIntegrity dataIntegrity;
    private KeyData keyData;
    private final List<KeyEncryptor> keyEncryptors = new ArrayList();

    public void parse(Document doc) {
        Element encryption = doc.getDocumentElement();
        if (!ENC_NS.equals(encryption.getNamespaceURI()) || !"encryption".equals(encryption.getLocalName())) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        this.keyData = new KeyData(encryption);
        this.dataIntegrity = new DataIntegrity(encryption);
        Element keyEncryptors = getTag(encryption, ENC_NS, "keyEncryptors");
        if (keyEncryptors == null) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        NodeList ke = keyEncryptors.getElementsByTagNameNS(ENC_NS, "keyEncryptor");
        for (int i = 0; i < ke.getLength(); i++) {
            this.keyEncryptors.add(new KeyEncryptor((Element) ke.item(i)));
        }
    }

    public void write(Document doc) {
        doc.setXmlStandalone(true);
        Element encryption = (Element) doc.appendChild(doc.createElementNS(ENC_NS, "encryption"));
        if (this.keyData != null) {
            this.keyData.write(encryption);
        }
        if (this.dataIntegrity != null) {
            this.dataIntegrity.write(encryption);
        }
        Element keyEncryptors = (Element) encryption.appendChild(doc.createElementNS(ENC_NS, "keyEncryptors"));
        boolean hasPass = false;
        boolean hasCert = false;
        for (KeyEncryptor ke : this.keyEncryptors) {
            ke.write(keyEncryptors);
            boolean z = false;
            hasPass |= ke.getPasswordKeyEncryptor() != null;
            if (ke.getCertificateKeyEncryptor() != null) {
                z = true;
            }
            hasCert |= z;
        }
        if (hasPass) {
            encryption.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:p", "http://schemas.microsoft.com/office/2006/keyEncryptor/password");
        }
        if (hasCert) {
            encryption.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:c", "http://schemas.microsoft.com/office/2006/keyEncryptor/certificate");
        }
    }

    public KeyData getKeyData() {
        return this.keyData;
    }

    public void setKeyData(KeyData keyData) {
        this.keyData = keyData;
    }

    public DataIntegrity getDataIntegrity() {
        return this.dataIntegrity;
    }

    public void setDataIntegrity(DataIntegrity dataIntegrity) {
        this.dataIntegrity = dataIntegrity;
    }

    public List<KeyEncryptor> getKeyEncryptors() {
        return this.keyEncryptors;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Element getTag(Element el, String ns, String name) {
        if (el == null) {
            return null;
        }
        NodeList nl = el.getElementsByTagNameNS(ns, name);
        if (nl.getLength() > 0) {
            return (Element) nl.item(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Integer getIntAttr(Element el, String name) {
        String at = el.getAttribute(name);
        if (at.isEmpty()) {
            return null;
        }
        return Integer.valueOf(at);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getBinAttr(Element el, String name) {
        String at = el.getAttribute(name);
        if (at.isEmpty()) {
            return null;
        }
        return Base64.getDecoder().decode(at);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setIntAttr(Element el, String name, Integer val) {
        setAttr(el, name, val == null ? null : val.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAttr(Element el, String name, String val) {
        if (val != null) {
            el.setAttribute(name, val);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setBinAttr(Element el, String name, byte[] val) {
        if (val != null) {
            setAttr(el, name, Base64.getEncoder().encodeToString(val));
        }
    }
}
