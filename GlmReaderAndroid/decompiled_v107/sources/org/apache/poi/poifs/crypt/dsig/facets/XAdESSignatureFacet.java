package org.apache.poi.poifs.crypt.dsig.facets;

import java.security.MessageDigest;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.BiConsumer;
import javax.security.auth.x500.X500Principal;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.namespace.QName;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.SignerRoleType;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* loaded from: classes10.dex */
public class XAdESSignatureFacet implements SignatureFacet {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XAdESSignatureFacet.class);
    private static final String XADES_TYPE = "http://uri.etsi.org/01903#SignedProperties";
    private final Map<String, String> dataObjectFormatMimeTypes = new HashMap();

    @Override // org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet
    public void preSign(SignatureInfo signatureInfo, Document document, List<Reference> references, List<XMLObject> objects) throws XMLSignatureException {
        LOG.atDebug().log("preSign");
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        QualifyingPropertiesDocument qualDoc = QualifyingPropertiesDocument.Factory.newInstance();
        QualifyingPropertiesType qualifyingProperties = qualDoc.addNewQualifyingProperties();
        qualifyingProperties.setTarget("#" + signatureConfig.getPackageSignatureId());
        createSignedProperties(signatureInfo, qualifyingProperties);
        objects.add(addXadesObject(signatureInfo, document, qualifyingProperties));
        references.add(addXadesReference(signatureInfo));
    }

    protected SignedPropertiesType createSignedProperties(SignatureInfo signatureInfo, QualifyingPropertiesType qualifyingProperties) {
        SignedPropertiesType signedProperties = qualifyingProperties.addNewSignedProperties();
        signedProperties.setId(signatureInfo.getSignatureConfig().getXadesSignatureId());
        SignedSignaturePropertiesType signedSignatureProperties = signedProperties.addNewSignedSignatureProperties();
        addSigningTime(signatureInfo, signedSignatureProperties);
        addCertificate(signatureInfo, signedSignatureProperties);
        addXadesRole(signatureInfo, signedSignatureProperties);
        addPolicy(signatureInfo, signedSignatureProperties);
        addMimeTypes(signatureInfo, signedProperties);
        addCommitmentType(signatureInfo, signedProperties);
        return signedProperties;
    }

    protected void addSigningTime(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignatureProperties) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        Calendar xmlGregorianCalendar = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
        xmlGregorianCalendar.setTime(signatureConfig.getExecutionTime());
        xmlGregorianCalendar.clear(14);
        signedSignatureProperties.setSigningTime(xmlGregorianCalendar);
    }

    protected void addCertificate(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignatureProperties) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        List<X509Certificate> chain = signatureConfig.getSigningCertificateChain();
        if (chain == null || chain.isEmpty()) {
            throw new IllegalStateException("no signing certificate chain available");
        }
        CertIDListType signingCertificates = signedSignatureProperties.addNewSigningCertificate();
        CertIDType certId = signingCertificates.addNewCert();
        setCertID(certId, signatureConfig, signatureConfig.isXadesIssuerNameNoReverseOrder(), chain.get(0));
    }

    protected void addXadesRole(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignatureProperties) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        String role = signatureConfig.getXadesRole();
        if (role == null || role.isEmpty()) {
            return;
        }
        SignerRoleType signerRole = signedSignatureProperties.addNewSignerRole();
        signedSignatureProperties.setSignerRole(signerRole);
        ClaimedRolesListType claimedRolesList = signerRole.addNewClaimedRoles();
        AnyType claimedRole = claimedRolesList.addNewClaimedRole();
        XmlString roleString = XmlString.Factory.newInstance();
        roleString.setStringValue(role);
        insertXChild(claimedRole, roleString);
    }

    protected void addPolicy(SignatureInfo signatureInfo, SignedSignaturePropertiesType signedSignatureProperties) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        SignaturePolicyService policyService = signatureConfig.getSignaturePolicyService();
        if (policyService == null) {
            if (signatureConfig.isXadesSignaturePolicyImplied()) {
                signedSignatureProperties.addNewSignaturePolicyIdentifier().addNewSignaturePolicyImplied();
                return;
            }
            return;
        }
        SignaturePolicyIdentifierType policyId = signedSignatureProperties.addNewSignaturePolicyIdentifier();
        SignaturePolicyIdType signaturePolicyId = policyId.addNewSignaturePolicyId();
        ObjectIdentifierType oit = signaturePolicyId.addNewSigPolicyId();
        oit.setDescription(policyService.getSignaturePolicyDescription());
        oit.addNewIdentifier().setStringValue(policyService.getSignaturePolicyIdentifier());
        byte[] signaturePolicyDocumentData = policyService.getSignaturePolicyDocument();
        DigestAlgAndValueType sigPolicyHash = signaturePolicyId.addNewSigPolicyHash();
        setDigestAlgAndValue(sigPolicyHash, signaturePolicyDocumentData, signatureConfig.getDigestAlgo());
        String signaturePolicyDownloadUrl = policyService.getSignaturePolicyDownloadUrl();
        if (signaturePolicyDownloadUrl == null) {
            return;
        }
        AnyType sigPolicyQualifier = signaturePolicyId.addNewSigPolicyQualifiers().addNewSigPolicyQualifier();
        XmlString spUriElement = XmlString.Factory.newInstance();
        spUriElement.setStringValue(signaturePolicyDownloadUrl);
        insertXChild(sigPolicyQualifier, spUriElement);
    }

    protected void addMimeTypes(SignatureInfo signatureInfo, SignedPropertiesType signedProperties) {
        if (this.dataObjectFormatMimeTypes.isEmpty()) {
            return;
        }
        final List<DataObjectFormatType> dataObjectFormats = signedProperties.addNewSignedDataObjectProperties().getDataObjectFormatList();
        this.dataObjectFormatMimeTypes.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.facets.XAdESSignatureFacet$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XAdESSignatureFacet.lambda$addMimeTypes$0(dataObjectFormats, (String) obj, (String) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addMimeTypes$0(List dataObjectFormats, String key, String value) {
        DataObjectFormatType dof = DataObjectFormatType.Factory.newInstance();
        dof.setObjectReference("#" + key);
        dof.setMimeType(value);
        dataObjectFormats.add(dof);
    }

    protected XMLObject addXadesObject(SignatureInfo signatureInfo, Document document, QualifyingPropertiesType qualifyingProperties) {
        Element qualDocEl = importNode(document, qualifyingProperties);
        List<XMLStructure> xadesObjectContent = Collections.singletonList(new DOMStructure(qualDocEl));
        return signatureInfo.getSignatureFactory().newXMLObject(xadesObjectContent, (String) null, (String) null, (String) null);
    }

    protected void addCommitmentType(SignatureInfo signatureInfo, SignedPropertiesType signedProperties) {
        SignedDataObjectPropertiesType dopt;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        String desc = signatureConfig.getSignatureDescription();
        String commit = signatureConfig.getCommitmentType();
        if (desc == null && commit == null) {
            return;
        }
        if (signedProperties.isSetSignedDataObjectProperties()) {
            dopt = signedProperties.getSignedDataObjectProperties();
        } else {
            dopt = signedProperties.addNewSignedDataObjectProperties();
        }
        CommitmentTypeIndicationType cti = dopt.addNewCommitmentTypeIndication();
        if (commit != null) {
            ObjectIdentifierType ctid = cti.addNewCommitmentTypeId();
            ctid.addNewIdentifier().setStringValue("http://uri.etsi.org/01903/v1.2.2#ProofOfOrigin");
            ctid.setDescription(signatureConfig.getCommitmentType());
        }
        if (desc != null) {
            cti.addNewAllSignedDataObjects();
            AnyType ctq = cti.addNewCommitmentTypeQualifiers().addNewCommitmentTypeQualifier();
            ctq.set(XmlString.Factory.newValue(desc));
        }
    }

    protected Reference addXadesReference(SignatureInfo signatureInfo) throws XMLSignatureException {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        List<Transform> transforms = Collections.singletonList(SignatureFacetHelper.newTransform(signatureInfo, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315"));
        return SignatureFacetHelper.newReference(signatureInfo, "#" + signatureConfig.getXadesSignatureId(), transforms, XADES_TYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValue, byte[] data, HashAlgorithm digestAlgo) {
        DigestMethodType digestMethod = digestAlgAndValue.addNewDigestMethod();
        digestMethod.setAlgorithm(SignatureConfig.getDigestMethodUri(digestAlgo));
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(digestAlgo);
        byte[] digestValue = messageDigest.digest(data);
        digestAlgAndValue.setDigestValue(digestValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setCertID(CertIDType certId, SignatureConfig signatureConfig, boolean issuerNameNoReverseOrder, X509Certificate certificate) {
        String issuerName;
        X509IssuerSerialType issuerSerial = certId.addNewIssuerSerial();
        X500Principal issuerPrincipal = certificate.getIssuerX500Principal();
        if (issuerNameNoReverseOrder) {
            issuerName = issuerPrincipal.getName().replace(CollectionUtils.COMMA, ", ");
        } else {
            issuerName = issuerPrincipal.toString();
        }
        issuerSerial.setX509IssuerName(issuerName);
        issuerSerial.setX509SerialNumber(certificate.getSerialNumber());
        try {
            byte[] encodedCertificate = certificate.getEncoded();
            DigestAlgAndValueType certDigest = certId.addNewCertDigest();
            setDigestAlgAndValue(certDigest, encodedCertificate, signatureConfig.getXadesDigestAlgo());
        } catch (CertificateEncodingException e) {
            throw new IllegalStateException("certificate encoding error: " + e.getMessage(), e);
        }
    }

    public void addMimeType(String dsReferenceUri, String mimetype) {
        this.dataObjectFormatMimeTypes.put(dsReferenceUri, mimetype);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void insertXChild(XmlObject root, XmlObject child) {
        XmlCursor rootCursor = root.newCursor();
        try {
            rootCursor.toEndToken();
            XmlCursor childCursor = child.newCursor();
            try {
                childCursor.toNextToken();
                childCursor.moveXml(rootCursor);
                if (childCursor != null) {
                    childCursor.close();
                }
                if (rootCursor != null) {
                    rootCursor.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (rootCursor != null) {
                    try {
                        rootCursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static Element importNode(Document document, XmlObject xo) {
        XmlCursor cur = xo.newCursor();
        try {
            QName elName = cur.getName();
            Element lastNode = document.createElementNS(elName.getNamespaceURI(), elName.getLocalPart());
            while (cur.hasNextToken()) {
                XmlCursor.TokenType nextToken = cur.toNextToken();
                switch (nextToken.intValue()) {
                    case 3:
                        QName name = cur.getName();
                        Element el = document.createElementNS(name.getNamespaceURI(), name.getLocalPart());
                        lastNode = (Element) lastNode.appendChild(el);
                        break;
                    case 4:
                        Element parent = (Element) lastNode.getParentNode();
                        if (parent == null) {
                            break;
                        } else {
                            lastNode = parent;
                            break;
                        }
                    case 5:
                        lastNode.appendChild(document.createTextNode(cur.getTextValue()));
                        break;
                    case 6:
                        QName name2 = cur.getName();
                        lastNode.setAttributeNS(name2.getNamespaceURI(), name2.getLocalPart(), cur.getTextValue());
                        if (!PackageRelationship.ID_ATTRIBUTE_NAME.equals(name2.getLocalPart())) {
                            break;
                        } else {
                            lastNode.setIdAttribute(PackageRelationship.ID_ATTRIBUTE_NAME, true);
                            break;
                        }
                    case 7:
                        QName name3 = cur.getName();
                        lastNode.setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_STRING + name3.getPrefix(), name3.getNamespaceURI());
                        break;
                    case 8:
                        lastNode.appendChild(document.createComment(cur.getTextValue()));
                        break;
                }
            }
            if (cur != null) {
                cur.close();
            }
            return lastNode;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
