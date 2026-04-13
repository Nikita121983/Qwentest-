package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.SignaturePart;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.XmlException;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class SignaturePart {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SignaturePart.class);
    private static final String XMLSEC_VALIDATE_MANIFEST = "org.jcp.xml.dsig.validateManifests";
    private static final String XMLSEC_VALIDATE_SECURE = "org.apache.jcp.xml.dsig.secureValidation";
    private List<X509Certificate> certChain;
    private final SignatureInfo signatureInfo;
    private final PackagePart signaturePart;
    private X509Certificate signer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignaturePart(PackagePart signaturePart, SignatureInfo signatureInfo) {
        this.signaturePart = signaturePart;
        this.signatureInfo = signatureInfo;
    }

    public PackagePart getPackagePart() {
        return this.signaturePart;
    }

    public X509Certificate getSigner() {
        return this.signer;
    }

    public List<X509Certificate> getCertChain() {
        return this.certChain;
    }

    public SignatureDocument getSignatureDocument() throws IOException, XmlException {
        InputStream stream = this.signaturePart.getInputStream();
        try {
            SignatureDocument parse = SignatureDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            if (stream != null) {
                stream.close();
            }
            return parse;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public boolean validate() {
        KeyInfoKeySelector keySelector = new KeyInfoKeySelector();
        XPath xpath = XPathHelper.getFactory().newXPath();
        xpath.setNamespaceContext(new XPathNSContext());
        try {
            InputStream stream = this.signaturePart.getInputStream();
            try {
                Document doc = DocumentHelper.readDocument(stream);
                if (stream != null) {
                    stream.close();
                }
                NodeList nl = (NodeList) xpath.compile("//*[@Id]").evaluate(doc, XPathConstants.NODESET);
                int length = nl.getLength();
                for (int i = 0; i < length; i++) {
                    ((Element) nl.item(i)).setIdAttribute(PackageRelationship.ID_ATTRIBUTE_NAME, true);
                }
                DOMValidateContext domValidateContext = new DOMValidateContext(keySelector, doc);
                domValidateContext.setProperty(XMLSEC_VALIDATE_MANIFEST, Boolean.TRUE);
                domValidateContext.setProperty(XMLSEC_VALIDATE_SECURE, Boolean.valueOf(this.signatureInfo.getSignatureConfig().isSecureValidation()));
                URIDereferencer uriDereferencer = this.signatureInfo.getUriDereferencer();
                domValidateContext.setURIDereferencer(uriDereferencer);
                XMLSignatureFactory xmlSignatureFactory = this.signatureInfo.getSignatureFactory();
                XMLSignature xmlSignature = xmlSignatureFactory.unmarshalXMLSignature(domValidateContext);
                boolean valid = xmlSignature.validate(domValidateContext);
                if (valid) {
                    this.signer = keySelector.getSigner();
                    this.certChain = keySelector.getCertChain();
                    extractConfig(doc, xmlSignature);
                }
                return valid;
            } finally {
            }
        } catch (XMLSignatureException e) {
            LOG.atError().withThrowable(e).log("error in validating the signature");
            throw new EncryptedDocumentException("error in validating the signature", e);
        } catch (MarshalException e2) {
            LOG.atError().withThrowable(e2).log("error in unmarshalling the signature");
            throw new EncryptedDocumentException("error in unmarshalling the signature", e2);
        } catch (IOException e3) {
            LOG.atError().withThrowable(e3).log("error in reading document");
            throw new EncryptedDocumentException("error in reading document", e3);
        } catch (XPathExpressionException e4) {
            LOG.atError().withThrowable(e4).log("error in searching document with xpath expression");
            throw new EncryptedDocumentException("error in searching document with xpath expression", e4);
        } catch (SAXException e5) {
            LOG.atError().withThrowable(e5).log("error in parsing document");
            throw new EncryptedDocumentException("error in parsing document", e5);
        }
    }

    private void extractConfig(Document doc, XMLSignature xmlSignature) throws XPathExpressionException {
        final SignatureConfig signatureConfig = this.signatureInfo.getSignatureConfig();
        if (!signatureConfig.isUpdateConfigOnValidate()) {
            return;
        }
        signatureConfig.setSigningCertificateChain(this.certChain);
        signatureConfig.setSignatureMethodFromUri(xmlSignature.getSignedInfo().getSignatureMethod().getAlgorithm());
        XPath xpath = XPathHelper.getFactory().newXPath();
        xpath.setNamespaceContext(new XPathNSContext());
        Map<String, Consumer<String>> m = new HashMap<>();
        signatureConfig.getClass();
        m.put("//mdssi:SignatureTime/mdssi:Value", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setExecutionTime((String) obj);
            }
        });
        signatureConfig.getClass();
        m.put("//xd:ClaimedRole", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setXadesRole((String) obj);
            }
        });
        signatureConfig.getClass();
        m.put("//dsss:SignatureComments", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setSignatureDescription((String) obj);
            }
        });
        signatureConfig.getClass();
        m.put("//xd:QualifyingProperties//xd:SignedSignatureProperties//ds:DigestMethod/@Algorithm", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setXadesDigestAlgo((String) obj);
            }
        });
        signatureConfig.getClass();
        m.put("//ds:CanonicalizationMethod", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setCanonicalizationMethod((String) obj);
            }
        });
        signatureConfig.getClass();
        m.put("//xd:CommitmentTypeId/xd:Description", new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureConfig.this.setCommitmentType((String) obj);
            }
        });
        for (Map.Entry<String, Consumer<String>> me : m.entrySet()) {
            String val = (String) xpath.compile(me.getKey()).evaluate(doc, XPathConstants.STRING);
            me.getValue().accept(val);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class XPathNSContext implements NamespaceContext {
        final Map<String, String> nsMap;

        private XPathNSContext() {
            this.nsMap = new HashMap();
            SignaturePart.this.signatureInfo.getSignatureConfig().getNamespacePrefixes().forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignaturePart$XPathNSContext$$ExternalSyntheticLambda0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignaturePart.XPathNSContext.this.m2503x87e19c8d((String) obj, (String) obj2);
                }
            });
            this.nsMap.put("dsss", SignatureFacet.MS_DIGSIG_NS);
            this.nsMap.put("ds", SignatureFacet.XML_DIGSIG_NS);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$org-apache-poi-poifs-crypt-dsig-SignaturePart$XPathNSContext, reason: not valid java name */
        public /* synthetic */ void m2503x87e19c8d(String k, String v) {
            this.nsMap.put(v, k);
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String prefix) {
            return this.nsMap.get(prefix);
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator getPrefixes(String val) {
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String uri) {
            return null;
        }
    }
}
