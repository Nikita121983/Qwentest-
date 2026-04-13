package org.apache.poi.poifs.crypt.dsig;

import java.security.Key;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;

/* loaded from: classes10.dex */
public class KeyInfoKeySelector extends KeySelector implements KeySelectorResult {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) KeyInfoKeySelector.class);
    private final List<X509Certificate> certChain = new ArrayList();

    public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {
        LOG.atDebug().log("select key");
        if (keyInfo == null) {
            throw new KeySelectorException("no ds:KeyInfo present");
        }
        List<XMLStructure> keyInfoContent = keyInfo.getContent();
        this.certChain.clear();
        for (XMLStructure xMLStructure : keyInfoContent) {
            if (xMLStructure instanceof X509Data) {
                X509Data x509Data = (X509Data) xMLStructure;
                List<?> x509DataList = x509Data.getContent();
                for (Object x509DataObject : x509DataList) {
                    if (x509DataObject instanceof X509Certificate) {
                        X509Certificate certificate = (X509Certificate) x509DataObject;
                        LOG.atDebug().log("certificate: {}", certificate.getSubjectX500Principal());
                        this.certChain.add(certificate);
                    }
                }
            }
        }
        if (this.certChain.isEmpty()) {
            throw new KeySelectorException("No key found!");
        }
        return this;
    }

    public Key getKey() {
        if (this.certChain.isEmpty()) {
            return null;
        }
        return this.certChain.get(0).getPublicKey();
    }

    public X509Certificate getSigner() {
        if (this.certChain.isEmpty()) {
            return null;
        }
        return this.certChain.get(0);
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }
}
