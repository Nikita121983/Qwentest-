package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.Key;
import java.security.KeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import org.apache.jcp.xml.dsig.internal.dom.DOMKeyInfo;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public class KeyInfoSignatureFacet implements SignatureFacet {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) KeyInfoSignatureFacet.class);

    /* JADX WARN: Type inference failed for: r0v15, types: [boolean, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v17, types: [boolean, java.util.List] */
    @Override // org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet
    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        List<X509Certificate> list;
        LOG.atDebug().log("postSign");
        NodeList elementsByTagNameNS = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "Object");
        Node item = elementsByTagNameNS.getLength() == 0 ? null : elementsByTagNameNS.item(0);
        KeyInfoFactory keyInfoFactory = signatureInfo.getKeyInfoFactory();
        new ArrayList();
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        X509Certificate x509Certificate = signatureConfig.getSigningCertificateChain().get(0);
        new ArrayList();
        if (signatureConfig.isIncludeKeyValue()) {
            try {
                KeyValue newKeyValue = keyInfoFactory.newKeyValue(x509Certificate.getPublicKey());
                newKeyValue.add(newKeyValue);
            } catch (KeyException e) {
                throw new IllegalStateException("key exception: " + e.getMessage(), e);
            }
        }
        if (signatureConfig.isIncludeIssuerSerial()) {
            X509IssuerSerial newX509IssuerSerial = keyInfoFactory.newX509IssuerSerial(x509Certificate.getIssuerX500Principal().toString(), x509Certificate.getSerialNumber());
            newX509IssuerSerial.add(newX509IssuerSerial);
        }
        ?? isIncludeEntireCertificateChain = signatureConfig.isIncludeEntireCertificateChain();
        if (isIncludeEntireCertificateChain != 0) {
            List<X509Certificate> signingCertificateChain = signatureConfig.getSigningCertificateChain();
            signingCertificateChain.addAll(signingCertificateChain);
            list = signingCertificateChain;
        } else {
            isIncludeEntireCertificateChain.add(x509Certificate);
            list = isIncludeEntireCertificateChain;
        }
        ?? isEmpty = list.isEmpty();
        List list2 = isEmpty;
        if (isEmpty == 0) {
            List newX509Data = keyInfoFactory.newX509Data((List) isEmpty);
            newX509Data.add(newX509Data);
            list2 = newX509Data;
        }
        DOMKeyInfo newKeyInfo = keyInfoFactory.newKeyInfo(list2);
        Key key = new Key() { // from class: org.apache.poi.poifs.crypt.dsig.facets.KeyInfoSignatureFacet.1
            private static final long serialVersionUID = 1;

            @Override // java.security.Key
            public String getAlgorithm() {
                return null;
            }

            @Override // java.security.Key
            public byte[] getEncoded() {
                return null;
            }

            @Override // java.security.Key
            public String getFormat() {
                return null;
            }
        };
        Element documentElement = document.getDocumentElement();
        final DOMSignContext dOMSignContext = item == null ? new DOMSignContext(key, documentElement) : new DOMSignContext(key, documentElement, item);
        Map<String, String> namespacePrefixes = signatureConfig.getNamespacePrefixes();
        dOMSignContext.getClass();
        namespacePrefixes.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.facets.KeyInfoSignatureFacet$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                dOMSignContext.putNamespacePrefix((String) obj, (String) obj2);
            }
        });
        newKeyInfo.marshal(new DOMStructure(documentElement), dOMSignContext);
        if (item != null) {
            NodeList elementsByTagNameNS2 = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "KeyInfo");
            if (elementsByTagNameNS2.getLength() != 1) {
                throw new IllegalStateException("KeyInfo wasn't set");
            }
            item.getParentNode().insertBefore(elementsByTagNameNS2.item(0), item);
        }
    }
}
