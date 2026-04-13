package org.apache.poi.poifs.crypt.dsig.facets;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.xml.crypto.MarshalException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.RevocationData;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.RespID;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.QualifyingPropertiesDocument;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.ResponderIDType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;
import org.etsi.uri.x01903.v14.TimeStampValidationDataDocument;
import org.etsi.uri.x01903.v14.ValidationDataType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public class XAdESXLSignatureFacet implements SignatureFacet {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XAdESXLSignatureFacet.class);
    private final CertificateFactory certificateFactory;

    public XAdESXLSignatureFacet() {
        try {
            this.certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new IllegalStateException("X509 JCA error: " + e.getMessage(), e);
        }
    }

    @Override // org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet
    public void postSign(SignatureInfo signatureInfo, Document document) throws MarshalException {
        int i;
        boolean z;
        LOG.atDebug().log("XAdES-X-L post sign phase");
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        NodeList qualNl = document.getElementsByTagNameNS(SignatureFacet.XADES_132_NS, "QualifyingProperties");
        final QualifyingPropertiesType qualProps = getQualProps(qualNl);
        Optional ofNullable = Optional.ofNullable(qualProps.getUnsignedProperties());
        qualProps.getClass();
        final UnsignedPropertiesType unsignedProps = (UnsignedPropertiesType) ofNullable.orElseGet(new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.facets.XAdESXLSignatureFacet$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return QualifyingPropertiesType.this.addNewUnsignedProperties();
            }
        });
        Optional ofNullable2 = Optional.ofNullable(unsignedProps.getUnsignedSignatureProperties());
        unsignedProps.getClass();
        UnsignedSignaturePropertiesType unsignedSigProps = (UnsignedSignaturePropertiesType) ofNullable2.orElseGet(new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.facets.XAdESXLSignatureFacet$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnsignedPropertiesType.this.addNewUnsignedSignatureProperties();
            }
        });
        NodeList nlSigVal = document.getElementsByTagNameNS(SignatureFacet.XML_DIGSIG_NS, "SignatureValue");
        if (nlSigVal.getLength() != 1) {
            throw new IllegalArgumentException("SignatureValue is not set.");
        }
        Element sigVal = (Element) nlSigVal.item(0);
        RevocationDataService revDataSvc = signatureConfig.getRevocationDataService();
        if (revDataSvc != null) {
            addCertificateValues(unsignedSigProps, signatureConfig);
        }
        LOG.atDebug().log("creating XAdES-T time-stamp");
        try {
            RevocationData tsaRevocationDataXadesT = new RevocationData();
            XAdESTimeStampType signatureTimeStamp = createXAdESTimeStamp(signatureInfo, tsaRevocationDataXadesT, sigVal);
            unsignedSigProps.addNewSignatureTimeStamp().set(signatureTimeStamp);
            if (tsaRevocationDataXadesT.hasRevocationDataEntries()) {
                try {
                    TimeStampValidationDataDocument validationData = createValidationData(tsaRevocationDataXadesT);
                    XAdESSignatureFacet.insertXChild(unsignedSigProps, validationData);
                } catch (CertificateEncodingException e) {
                    e = e;
                    throw new MarshalException("unable to create XAdES signature", e);
                }
            }
            if (revDataSvc == null) {
                i = 0;
                z = true;
            } else {
                CompleteCertificateRefsType completeCertificateRefs = completeCertificateRefs(unsignedSigProps, signatureConfig);
                RevocationData revocationData = revDataSvc.getRevocationData(signatureConfig.getSigningCertificateChain());
                i = 0;
                CompleteRevocationRefsType completeRevocationRefs = unsignedSigProps.addNewCompleteRevocationRefs();
                addRevocationCRL(completeRevocationRefs, signatureConfig, revocationData);
                addRevocationOCSP(completeRevocationRefs, signatureConfig, revocationData);
                z = true;
                RevocationValuesType revocationValues = unsignedSigProps.addNewRevocationValues();
                createRevocationValues(revocationValues, revocationData);
                LOG.atDebug().log("creating XAdES-X time-stamp");
                XAdESTimeStampType timeStampXadesX1 = createXAdESTimeStamp(signatureInfo, new RevocationData(), sigVal, signatureTimeStamp.getDomNode(), completeCertificateRefs.getDomNode(), completeRevocationRefs.getDomNode());
                unsignedSigProps.addNewSigAndRefsTimeStamp().set(timeStampXadesX1);
            }
            Element n = (Element) document.importNode(qualProps.getDomNode(), z);
            NodeList nl = n.getElementsByTagName("TimeStampValidationData");
            for (int i2 = 0; i2 < nl.getLength(); i2++) {
                ((Element) nl.item(i2)).setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX, SignatureFacet.XADES_141_NS);
            }
            Node qualNL0 = qualNl.item(i);
            qualNL0.getParentNode().replaceChild(n, qualNL0);
        } catch (CertificateEncodingException e2) {
            e = e2;
        }
    }

    private QualifyingPropertiesType getQualProps(NodeList qualNl) throws MarshalException {
        if (qualNl.getLength() != 1) {
            throw new MarshalException("no XAdES-BES extension present");
        }
        try {
            Node first = qualNl.item(0);
            QualifyingPropertiesDocument qualDoc = QualifyingPropertiesDocument.Factory.parse(first, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            return qualDoc.getQualifyingProperties();
        } catch (XmlException e) {
            throw new MarshalException(e);
        }
    }

    private CompleteCertificateRefsType completeCertificateRefs(UnsignedSignaturePropertiesType unsignedSigProps, final SignatureConfig signatureConfig) {
        CompleteCertificateRefsType completeCertificateRefs = unsignedSigProps.addNewCompleteCertificateRefs();
        final CertIDListType certIdList = completeCertificateRefs.addNewCertRefs();
        List<X509Certificate> certChain = signatureConfig.getSigningCertificateChain();
        certChain.stream().skip(1L).forEachOrdered(new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.facets.XAdESXLSignatureFacet$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XAdESSignatureFacet.setCertID(CertIDListType.this.addNewCert(), signatureConfig, false, (X509Certificate) obj);
            }
        });
        return completeCertificateRefs;
    }

    private void addRevocationCRL(CompleteRevocationRefsType completeRevocationRefs, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLRefsType crlRefs = completeRevocationRefs.addNewCRLRefs();
            completeRevocationRefs.setCRLRefs(crlRefs);
            for (byte[] encodedCrl : revocationData.getCRLs()) {
                CRLRefType crlRef = crlRefs.addNewCRLRef();
                try {
                    X509CRL crl = (X509CRL) this.certificateFactory.generateCRL(UnsynchronizedByteArrayInputStream.builder().setByteArray(encodedCrl).get());
                    CRLIdentifierType crlIdentifier = crlRef.addNewCRLIdentifier();
                    String issuerName = crl.getIssuerX500Principal().getName().replace(CollectionUtils.COMMA, ", ");
                    crlIdentifier.setIssuer(issuerName);
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    cal.setTime(crl.getThisUpdate());
                    crlIdentifier.setIssueTime(cal);
                    crlIdentifier.setNumber(getCrlNumber(crl));
                    DigestAlgAndValueType digestAlgAndValue = crlRef.addNewDigestAlgAndValue();
                    XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, encodedCrl, signatureConfig.getDigestAlgo());
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                } catch (CRLException e2) {
                    throw new IllegalStateException("CRL parse error: " + e2.getMessage(), e2);
                }
            }
        }
    }

    private void addRevocationOCSP(CompleteRevocationRefsType completeRevocationRefs, SignatureConfig signatureConfig, RevocationData revocationData) {
        if (revocationData.hasOCSPs()) {
            OCSPRefsType ocspRefs = completeRevocationRefs.addNewOCSPRefs();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                try {
                    OCSPRefType ocspRef = ocspRefs.addNewOCSPRef();
                    DigestAlgAndValueType digestAlgAndValue = ocspRef.addNewDigestAlgAndValue();
                    XAdESSignatureFacet.setDigestAlgAndValue(digestAlgAndValue, ocsp, signatureConfig.getDigestAlgo());
                    OCSPIdentifierType ocspIdentifier = ocspRef.addNewOCSPIdentifier();
                    OCSPResp ocspResp = new OCSPResp(ocsp);
                    BasicOCSPResp basicOcspResp = (BasicOCSPResp) ocspResp.getResponseObject();
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Z"), Locale.ROOT);
                    cal.setTime(basicOcspResp.getProducedAt());
                    ocspIdentifier.setProducedAt(cal);
                    ResponderIDType responderId = ocspIdentifier.addNewResponderID();
                    RespID respId = basicOcspResp.getResponderId();
                    ResponderID ocspResponderId = respId.toASN1Primitive();
                    DERTaggedObject derTaggedObject = ocspResponderId.toASN1Primitive();
                    if (2 == derTaggedObject.getTagNo()) {
                        ASN1OctetString keyHashOctetString = derTaggedObject.getBaseObject();
                        byte[] key = keyHashOctetString.getOctets();
                        responderId.setByKey(key);
                    } else {
                        X500Name name = X500Name.getInstance(derTaggedObject.getBaseObject());
                        String nameStr = name.toString();
                        responderId.setByName(nameStr);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("OCSP decoding error: " + e.getMessage(), e);
                }
            }
        }
    }

    private void addCertificateValues(UnsignedSignaturePropertiesType unsignedSigProps, SignatureConfig signatureConfig) {
        List<X509Certificate> chain = signatureConfig.getSigningCertificateChain();
        if (chain.size() < 2) {
            return;
        }
        CertificateValuesType certificateValues = unsignedSigProps.addNewCertificateValues();
        try {
            for (X509Certificate certificate : chain.subList(1, chain.size())) {
                certificateValues.addNewEncapsulatedX509Certificate().setByteArrayValue(certificate.getEncoded());
            }
        } catch (CertificateEncodingException e) {
            throw new IllegalStateException("certificate encoding error: " + e.getMessage(), e);
        }
    }

    private static byte[] getC14nValue(List<Node> nodeList, String c14nAlgoId) {
        try {
            UnsynchronizedByteArrayOutputStream c14nValue = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                for (Node node : nodeList) {
                    Canonicalizer c14n = Canonicalizer.getInstance(c14nAlgoId);
                    c14n.canonicalizeSubtree(node, c14nValue);
                }
                byte[] byteArray = c14nValue.toByteArray();
                if (c14nValue != null) {
                    c14nValue.close();
                }
                return byteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (c14nValue != null) {
                        try {
                            c14nValue.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("c14n error: " + e2.getMessage(), e2);
        }
    }

    private BigInteger getCrlNumber(X509CRL crl) {
        byte[] crlNumberExtensionValue = crl.getExtensionValue(Extension.cRLNumber.getId());
        if (crlNumberExtensionValue == null) {
            return null;
        }
        try {
            ASN1InputStream asn1IS1 = new ASN1InputStream(crlNumberExtensionValue);
            try {
                ASN1OctetString octetString = asn1IS1.readObject();
                byte[] octets = octetString.getOctets();
                ASN1InputStream asn1IS2 = new ASN1InputStream(octets);
                try {
                    ASN1Integer integer = asn1IS2.readObject();
                    BigInteger positiveValue = integer.getPositiveValue();
                    asn1IS2.close();
                    asn1IS1.close();
                    return positiveValue;
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException("I/O error: " + e.getMessage(), e);
        }
    }

    private XAdESTimeStampType createXAdESTimeStamp(SignatureInfo signatureInfo, RevocationData revocationData, Node... nodes) {
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        byte[] c14nSignatureValueElement = getC14nValue(Arrays.asList(nodes), signatureConfig.getXadesCanonicalizationMethod());
        try {
            byte[] timeStampToken = signatureConfig.getTspService().timeStamp(signatureInfo, c14nSignatureValueElement, revocationData);
            XAdESTimeStampType xadesTimeStamp = XAdESTimeStampType.Factory.newInstance();
            CanonicalizationMethodType c14nMethod = xadesTimeStamp.addNewCanonicalizationMethod();
            c14nMethod.setAlgorithm(signatureConfig.getXadesCanonicalizationMethod());
            EncapsulatedPKIDataType encapsulatedTimeStamp = xadesTimeStamp.addNewEncapsulatedTimeStamp();
            encapsulatedTimeStamp.setByteArrayValue(timeStampToken);
            return xadesTimeStamp;
        } catch (Exception e) {
            throw new IllegalStateException("error while creating a time-stamp: " + e.getMessage(), e);
        }
    }

    private TimeStampValidationDataDocument createValidationData(RevocationData revocationData) throws CertificateEncodingException {
        TimeStampValidationDataDocument doc = TimeStampValidationDataDocument.Factory.newInstance();
        ValidationDataType validationData = doc.addNewTimeStampValidationData();
        List<X509Certificate> tspChain = revocationData.getX509chain();
        if (tspChain.size() > 1) {
            CertificateValuesType cvals = validationData.addNewCertificateValues();
            for (X509Certificate x509 : tspChain.subList(1, tspChain.size())) {
                byte[] encoded = x509.getEncoded();
                cvals.addNewEncapsulatedX509Certificate().setByteArrayValue(encoded);
            }
        }
        RevocationValuesType revocationValues = validationData.addNewRevocationValues();
        createRevocationValues(revocationValues, revocationData);
        return doc;
    }

    private void createRevocationValues(RevocationValuesType revocationValues, RevocationData revocationData) {
        if (revocationData.hasCRLs()) {
            CRLValuesType crlValues = revocationValues.addNewCRLValues();
            for (byte[] crl : revocationData.getCRLs()) {
                EncapsulatedPKIDataType encapsulatedCrlValue = crlValues.addNewEncapsulatedCRLValue();
                encapsulatedCrlValue.setByteArrayValue(crl);
            }
        }
        if (revocationData.hasOCSPs()) {
            OCSPValuesType ocspValues = revocationValues.addNewOCSPValues();
            for (byte[] ocsp : revocationData.getOCSPs()) {
                EncapsulatedPKIDataType encapsulatedOcspValue = ocspValues.addNewEncapsulatedOCSPValue();
                encapsulatedOcspValue.setByteArrayValue(ocsp);
            }
        }
    }
}
