package org.apache.poi.poifs.crypt.agile;

import org.apache.poi.EncryptedDocumentException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public class KeyEncryptor {
    static final String CERT_NS = "http://schemas.microsoft.com/office/2006/keyEncryptor/certificate";
    static final String PASS_NS = "http://schemas.microsoft.com/office/2006/keyEncryptor/password";
    private CertificateKeyEncryptor certificateKeyEncryptor;
    private PasswordKeyEncryptor passwordKeyEncryptor;

    public KeyEncryptor() {
    }

    public KeyEncryptor(Element keyEncryptor) {
        if (keyEncryptor == null) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor");
        }
        NodeList nl = keyEncryptor.getElementsByTagNameNS("*", "encryptedKey");
        for (int i = 0; i < nl.getLength(); i++) {
            Element el = (Element) nl.item(i);
            String nsUri = el.getNamespaceURI();
            if (PASS_NS.equals(nsUri)) {
                this.passwordKeyEncryptor = new PasswordKeyEncryptor(el);
            } else if (CERT_NS.equals(nsUri)) {
                this.certificateKeyEncryptor = new CertificateKeyEncryptor(el);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void write(Element keyEncryptors) {
        if (this.passwordKeyEncryptor != null) {
            this.passwordKeyEncryptor.write(keyEncryptors);
        } else if (this.certificateKeyEncryptor != null) {
            this.certificateKeyEncryptor.write(keyEncryptors);
        }
    }

    public PasswordKeyEncryptor getPasswordKeyEncryptor() {
        return this.passwordKeyEncryptor;
    }

    public void setPasswordKeyEncryptor(PasswordKeyEncryptor passwordKeyEncryptor) {
        this.passwordKeyEncryptor = passwordKeyEncryptor;
    }

    public CertificateKeyEncryptor getCertificateKeyEncryptor() {
        return this.certificateKeyEncryptor;
    }

    public void setCertificateKeyEncryptor(CertificateKeyEncryptor certificateKeyEncryptor) {
        this.certificateKeyEncryptor = certificateKeyEncryptor;
    }
}
