package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.jcp.xml.dsig.internal.dom.DOMSignedInfo;
import org.apache.jcp.xml.dsig.internal.dom.DOMSubTreeData;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RelationshipTransformService;
import org.apache.poi.util.NotImplemented;
import org.apache.xml.security.Init;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xmlbeans.XmlOptions;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MutationEvent;

/* loaded from: classes10.dex */
public class SignatureInfo {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SignatureInfo.class);
    private KeyInfoFactory keyInfoFactory;
    private OPCPackage opcPackage;
    private Provider provider;
    private SignatureConfig signatureConfig;
    private XMLSignatureFactory signatureFactory;
    private URIDereferencer uriDereferencer;

    public SignatureConfig getSignatureConfig() {
        return this.signatureConfig;
    }

    public void setSignatureConfig(SignatureConfig signatureConfig) {
        this.signatureConfig = signatureConfig;
    }

    public void setOpcPackage(OPCPackage opcPackage) {
        this.opcPackage = opcPackage;
    }

    public OPCPackage getOpcPackage() {
        return this.opcPackage;
    }

    public URIDereferencer getUriDereferencer() {
        return this.uriDereferencer;
    }

    public void setUriDereferencer(URIDereferencer uriDereferencer) {
        this.uriDereferencer = uriDereferencer;
    }

    public boolean verifySignature() {
        initXmlProvider();
        Iterator<SignaturePart> iter = getSignatureParts().iterator();
        return iter.hasNext() && iter.next().validate();
    }

    public void confirmSignature() throws XMLSignatureException, MarshalException {
        initXmlProvider();
        Document document = DocumentHelper.createDocument();
        DOMSignContext xmlSignContext = createXMLSignContext(document);
        DOMSignedInfo signedInfo = preSign(xmlSignContext);
        String signatureValue = signDigest(xmlSignContext, signedInfo);
        postSign(xmlSignContext, signatureValue);
    }

    public DOMSignContext createXMLSignContext(Document document) {
        initXmlProvider();
        return new DOMSignContext(this.signatureConfig.getKey(), document);
    }

    public String signDigest(DOMSignContext xmlSignContext, DOMSignedInfo signedInfo) {
        initXmlProvider();
        PrivateKey key = this.signatureConfig.getKey();
        HashAlgorithm algo = this.signatureConfig.getDigestAlgo();
        if ((algo.hashSize * 4) / 3 > 76 && !XMLUtils.ignoreLineBreaks()) {
            throw new EncryptedDocumentException("The hash size of the chosen hash algorithm (" + algo + " = " + algo.hashSize + " bytes), will motivate XmlSec to add linebreaks to the generated digest, which results in an invalid signature (... at least for Office) - please persuade it otherwise by adding '-Dorg.apache.xml.security.ignoreLineBreaks=true' to the JVM system properties.");
        }
        try {
            DigestOutputStream dos = getDigestStream(algo, key);
            try {
                dos.init();
                Document document = (Document) xmlSignContext.getParent();
                Element el = getDsigElement(document, "SignedInfo");
                DOMSubTreeData subTree = new DOMSubTreeData(el, true);
                signedInfo.getCanonicalizationMethod().transform(subTree, xmlSignContext, dos);
                String encodeToString = Base64.getEncoder().encodeToString(dos.sign());
                if (dos != null) {
                    dos.close();
                }
                return encodeToString;
            } finally {
            }
        } catch (IOException | GeneralSecurityException | TransformException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    private static DigestOutputStream getDigestStream(HashAlgorithm algo, PrivateKey key) {
        switch (algo) {
            case md2:
            case md5:
            case sha1:
            case sha256:
            case sha384:
            case sha512:
                return new SignatureOutputStream(algo, key);
            default:
                return new DigestOutputStream(algo, key);
        }
    }

    public Iterable<SignaturePart> getSignatureParts() {
        initXmlProvider();
        return new Iterable() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda3
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return SignatureInfo.this.m2498x4964401d();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getSignatureParts$0$org-apache-poi-poifs-crypt-dsig-SignatureInfo, reason: not valid java name */
    public /* synthetic */ Iterator m2498x4964401d() {
        return new SignaturePartIterator();
    }

    /* loaded from: classes10.dex */
    private final class SignaturePartIterator implements Iterator<SignaturePart> {
        Iterator<PackageRelationship> sigOrigRels;
        private PackagePart sigPart;
        private Iterator<PackageRelationship> sigRels;

        private SignaturePartIterator() {
            this.sigOrigRels = SignatureInfo.this.opcPackage.getRelationshipsByType(PackageRelationshipTypes.DIGITAL_SIGNATURE_ORIGIN).iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                if (this.sigRels == null || !this.sigRels.hasNext()) {
                    if (this.sigOrigRels.hasNext()) {
                        this.sigPart = SignatureInfo.this.opcPackage.getPart(this.sigOrigRels.next());
                        SignatureInfo.LOG.atDebug().log("Digital Signature Origin part: {}", this.sigPart);
                        try {
                            this.sigRels = this.sigPart.getRelationshipsByType(PackageRelationshipTypes.DIGITAL_SIGNATURE).iterator();
                        } catch (InvalidFormatException e) {
                            SignatureInfo.LOG.atWarn().withThrowable(e).log("Reference to signature is invalid.");
                        }
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }

        @Override // java.util.Iterator
        public SignaturePart next() {
            PackagePart sigRelPart = null;
            do {
                try {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                        break;
                    }
                    sigRelPart = this.sigPart.getRelatedPart(this.sigRels.next());
                    SignatureInfo.LOG.atDebug().log("XML Signature part: {}", sigRelPart);
                } catch (InvalidFormatException e) {
                    SignatureInfo.LOG.atWarn().withThrowable(e).log("Reference to signature is invalid.");
                }
            } while (sigRelPart == null);
            return new SignaturePart(sigRelPart, SignatureInfo.this);
        }

        @Override // java.util.Iterator
        @NotImplemented
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public DOMSignedInfo preSign(final DOMSignContext xmlSignContext) throws XMLSignatureException, MarshalException {
        Document document = (Document) xmlSignContext.getParent();
        registerEventListener(document);
        if (this.uriDereferencer != null) {
            xmlSignContext.setURIDereferencer(this.uriDereferencer);
        }
        Map<String, String> namespacePrefixes = this.signatureConfig.getNamespacePrefixes();
        xmlSignContext.getClass();
        namespacePrefixes.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                xmlSignContext.putNamespacePrefix((String) obj, (String) obj2);
            }
        });
        xmlSignContext.setDefaultNamespacePrefix("");
        List<Reference> references = new ArrayList<>();
        List<XMLObject> objects = new ArrayList<>();
        for (SignatureFacet signatureFacet : this.signatureConfig.getSignatureFacets()) {
            LOG.atDebug().log("invoking signature facet: {}", signatureFacet.getClass().getSimpleName());
            signatureFacet.preSign(this, document, references, objects);
        }
        try {
            SignatureMethod signatureMethod = this.signatureFactory.newSignatureMethod(this.signatureConfig.getSignatureMethodUri(), (SignatureMethodParameterSpec) null);
            CanonicalizationMethod canonicalizationMethod = this.signatureFactory.newCanonicalizationMethod(this.signatureConfig.getCanonicalizationMethod(), (C14NMethodParameterSpec) null);
            DOMSignedInfo newSignedInfo = this.signatureFactory.newSignedInfo(canonicalizationMethod, signatureMethod, references);
            String signatureValueId = this.signatureConfig.getPackageSignatureId() + "-signature-value";
            XMLSignature xmlSignature = this.signatureFactory.newXMLSignature(newSignedInfo, (KeyInfo) null, objects, this.signatureConfig.getPackageSignatureId(), signatureValueId);
            xmlSignature.sign(xmlSignContext);
            for (XMLObject object : objects) {
                LOG.atDebug().log("object java type: {}", object.getClass().getName());
                List<XMLStructure> objectContentList = object.getContent();
                for (XMLStructure xMLStructure : objectContentList) {
                    LOG.atDebug().log("object content java type: {}", xMLStructure.getClass().getName());
                    if (xMLStructure instanceof Manifest) {
                        Manifest manifest = (Manifest) xMLStructure;
                        List<Reference> manifestReferences = manifest.getReferences();
                        for (Reference reference : manifestReferences) {
                            if (reference.getDigestValue() == null) {
                                XMLSignature xmlSignature2 = xmlSignature;
                                DOMReference manifestDOMReference = (DOMReference) reference;
                                manifestDOMReference.digest(xmlSignContext);
                                xmlSignature = xmlSignature2;
                            }
                        }
                    }
                }
            }
            List<Reference> signedInfoReferences = newSignedInfo.getReferences();
            Iterator<Reference> it = signedInfoReferences.iterator();
            while (it.hasNext()) {
                DOMReference domReference = (DOMReference) it.next();
                if (domReference.getDigestValue() == null) {
                    domReference.digest(xmlSignContext);
                }
            }
            return newSignedInfo;
        } catch (GeneralSecurityException e) {
            throw new XMLSignatureException(e);
        }
    }

    protected void registerEventListener(final Document document) {
        final SignatureMarshalListener sml = this.signatureConfig.getSignatureMarshalListener();
        if (sml == null) {
            return;
        }
        final EventListener[] el = {null};
        final EventTarget eventTarget = (EventTarget) document;
        el[0] = new EventListener() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda1
            public final void handleEvent(Event event) {
                SignatureInfo.this.m2499x69d1c06b(eventTarget, el, sml, document, event);
            }
        };
        eventTarget.addEventListener("DOMSubtreeModified", el[0], false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$registerEventListener$1$org-apache-poi-poifs-crypt-dsig-SignatureInfo, reason: not valid java name */
    public /* synthetic */ void m2499x69d1c06b(EventTarget eventTarget, EventListener[] el, SignatureMarshalListener sml, Document document, Event e) {
        if ((e instanceof MutationEvent) && (e.getTarget() instanceof Document)) {
            eventTarget.removeEventListener("DOMSubtreeModified", el[0], false);
            sml.handleElement(this, document, eventTarget, el[0]);
            eventTarget.addEventListener("DOMSubtreeModified", el[0], false);
        }
    }

    public void postSign(DOMSignContext xmlSignContext, String signatureValue) throws MarshalException {
        LOG.atDebug().log("postSign");
        Document document = (Document) xmlSignContext.getParent();
        String signatureId = this.signatureConfig.getPackageSignatureId();
        if (!signatureId.equals(document.getDocumentElement().getAttribute(PackageRelationship.ID_ATTRIBUTE_NAME))) {
            throw new IllegalStateException("ds:Signature not found for @Id: " + signatureId);
        }
        Element signatureNode = getDsigElement(document, "SignatureValue");
        if (signatureNode == null) {
            throw new IllegalStateException("preSign has to be called before postSign");
        }
        signatureNode.setTextContent(signatureValue);
        for (SignatureFacet signatureFacet : this.signatureConfig.getSignatureFacets()) {
            signatureFacet.postSign(this, document);
        }
        writeDocument(document);
    }

    protected void writeDocument(Document document) throws MarshalException {
        XmlOptions xo = new XmlOptions();
        final Map<String, String> namespaceMap = new HashMap<>();
        this.signatureConfig.getNamespacePrefixes().forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SignatureInfo.lambda$writeDocument$2(namespaceMap, (String) obj, (String) obj2);
            }
        });
        xo.setSaveSuggestedPrefixes(namespaceMap);
        xo.setUseDefaultNamespace();
        LOG.atDebug().log("output signed Office OpenXML document");
        try {
            DSigRelation originDesc = DSigRelation.ORIGIN_SIGS;
            PackagePartName originPartName = PackagingURIHelper.createPartName(originDesc.getFileName(0));
            PackagePart originPart = this.opcPackage.getPart(originPartName);
            if (originPart == null) {
                originPart = this.opcPackage.createPart(originPartName, originDesc.getContentType());
                this.opcPackage.addRelationship(originPartName, TargetMode.INTERNAL, originDesc.getRelation());
            }
            DSigRelation sigDesc = DSigRelation.SIG;
            int nextSigIdx = this.opcPackage.getUnusedPartIndex(sigDesc.getDefaultFileName());
            if (!this.signatureConfig.isAllowMultipleSignatures()) {
                PackageRelationshipCollection prc = originPart.getRelationshipsByType(sigDesc.getRelation());
                for (int i = 2; i < nextSigIdx; i++) {
                    PackagePartName pn = PackagingURIHelper.createPartName(sigDesc.getFileName(i));
                    Iterator<PackageRelationship> it = prc.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            PackageRelationship rel = it.next();
                            PackagePart pp = originPart.getRelatedPart(rel);
                            if (pp.getPartName().equals(pn)) {
                                originPart.removeRelationship(rel.getId());
                                prc.removeRelationship(rel.getId());
                                break;
                            }
                        }
                    }
                    this.opcPackage.removePart(this.opcPackage.getPart(pn));
                }
                nextSigIdx = 1;
            }
            PackagePartName sigPartName = PackagingURIHelper.createPartName(sigDesc.getFileName(nextSigIdx));
            PackagePart sigPart = this.opcPackage.getPart(sigPartName);
            if (sigPart == null) {
                sigPart = this.opcPackage.createPart(sigPartName, sigDesc.getContentType());
                originPart.addRelationship(sigPartName, TargetMode.INTERNAL, sigDesc.getRelation());
            } else {
                sigPart.clear();
            }
            OutputStream os = sigPart.getOutputStream();
            try {
                SignatureDocument sigDoc = SignatureDocument.Factory.parse(document, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                sigDoc.save(os, xo);
                if (os != null) {
                    os.close();
                }
            } finally {
            }
        } catch (Exception e) {
            throw new MarshalException("Unable to write signature document", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$writeDocument$2(Map namespaceMap, String k, String v) {
    }

    private Element getDsigElement(Document document, String localName) {
        NodeList sigValNl = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, localName);
        if (sigValNl.getLength() == 1) {
            return (Element) sigValNl.item(0);
        }
        LOG.atWarn().log("Signature element '{}' was {}", localName, sigValNl.getLength() == 0 ? "not found" : "multiple times");
        return null;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setSignatureFactory(XMLSignatureFactory signatureFactory) {
        this.signatureFactory = signatureFactory;
    }

    public XMLSignatureFactory getSignatureFactory() {
        return this.signatureFactory;
    }

    public void setKeyInfoFactory(KeyInfoFactory keyInfoFactory) {
        this.keyInfoFactory = keyInfoFactory;
    }

    public KeyInfoFactory getKeyInfoFactory() {
        return this.keyInfoFactory;
    }

    protected void initXmlProvider() {
        if (this.provider == null) {
            this.provider = XmlProviderInitSingleton.getInstance().findProvider();
        }
        if (this.signatureFactory == null) {
            this.signatureFactory = XMLSignatureFactory.getInstance("DOM", this.provider);
        }
        if (this.keyInfoFactory == null) {
            this.keyInfoFactory = KeyInfoFactory.getInstance("DOM", this.provider);
        }
        if (this.uriDereferencer == null) {
            this.uriDereferencer = new OOXMLURIDereferencer();
        }
        if (this.uriDereferencer instanceof OOXMLURIDereferencer) {
            ((OOXMLURIDereferencer) this.uriDereferencer).setSignatureInfo(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class XmlProviderInitSingleton {

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes10.dex */
        public static class SingletonHelper {
            private static final XmlProviderInitSingleton INSTANCE = new XmlProviderInitSingleton();

            private SingletonHelper() {
            }
        }

        public static XmlProviderInitSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }

        private XmlProviderInitSingleton() {
            try {
                Init.init();
                RelationshipTransformService.registerDsigProvider();
                CryptoFunctions.registerBouncyCastle();
            } catch (Exception e) {
                throw new IllegalStateException("Xml & BouncyCastle-Provider initialization failed", e);
            }
        }

        public Provider findProvider() {
            return (Provider) Stream.of((Object[]) SignatureConfig.getProviderNames()).map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Provider provider;
                    provider = SignatureInfo.XmlProviderInitSingleton.this.getProvider((String) obj);
                    return provider;
                }
            }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean nonNull;
                    nonNull = Objects.nonNull((Provider) obj);
                    return nonNull;
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureInfo$XmlProviderInitSingleton$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    RuntimeException providerNotFound;
                    providerNotFound = SignatureInfo.XmlProviderInitSingleton.this.providerNotFound();
                    return providerNotFound;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Provider getProvider(String className) {
            try {
                return (Provider) Class.forName(className).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e) {
                SignatureInfo.LOG.atDebug().log("XMLDsig-Provider '{}' can't be found - trying next.", className);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RuntimeException providerNotFound() {
            return new IllegalStateException("JRE doesn't support default xml signature provider - set jsr105Provider system property!");
        }
    }
}
