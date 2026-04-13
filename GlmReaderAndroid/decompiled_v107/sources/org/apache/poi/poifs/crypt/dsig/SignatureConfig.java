package org.apache.poi.poifs.crypt.dsig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.crypto.URIDereferencer;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.facets.KeyInfoSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.OOXMLSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.Office2010SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.poi.poifs.crypt.dsig.facets.XAdESSignatureFacet;
import org.apache.poi.poifs.crypt.dsig.services.RevocationDataService;
import org.apache.poi.poifs.crypt.dsig.services.SignaturePolicyService;
import org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampServiceValidator;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public class SignatureConfig {
    private static final String DigestMethod_SHA224 = "http://www.w3.org/2001/04/xmldsig-more#sha224";
    private static final String DigestMethod_SHA384 = "http://www.w3.org/2001/04/xmldsig-more#sha384";
    public static final String SIGNATURE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String XMLSEC_JDK = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
    private static final String XMLSEC_SANTUARIO = "org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI";
    private boolean includeIssuerSerial;
    private boolean includeKeyValue;
    private PrivateKey key;
    private String proxyUrl;
    private RevocationDataService revocationDataService;
    private byte[] signatureImage;
    private byte[] signatureImageInvalid;
    private ClassID signatureImageSetupId;
    private byte[] signatureImageValid;
    private SignaturePolicyService signaturePolicyService;
    private List<X509Certificate> signingCertificateChain;
    private HashAlgorithm tspDigestAlgo;
    private boolean tspOldProtocol;
    private String tspPass;
    private String tspUrl;
    private String tspUser;
    private TimeStampServiceValidator tspValidator;
    private HashAlgorithm xadesDigestAlgo;
    private String xadesRole;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SignatureConfig.class);
    private static final List<Supplier<SignatureFacet>> DEFAULT_FACETS = Collections.unmodifiableList(Arrays.asList(new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda2
        @Override // java.util.function.Supplier
        public final Object get() {
            return new OOXMLSignatureFacet();
        }
    }, new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda3
        @Override // java.util.function.Supplier
        public final Object get() {
            return new KeyInfoSignatureFacet();
        }
    }, new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda4
        @Override // java.util.function.Supplier
        public final Object get() {
            return new XAdESSignatureFacet();
        }
    }, new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda5
        @Override // java.util.function.Supplier
        public final Object get() {
            return new Office2010SignatureFacet();
        }
    }));
    private List<SignatureFacet> signatureFacets = new ArrayList();
    private HashAlgorithm digestAlgo = HashAlgorithm.sha256;
    private Date executionTime = new Date();
    private URIDereferencer uriDereferencer = new OOXMLURIDereferencer();
    private String canonicalizationMethod = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
    private boolean includeEntireCertificateChain = true;
    private TimeStampService tspService = new TSPTimeStampService();
    private TimeStampHttpClient tspHttpClient = new TimeStampSimpleHttpClient();
    private String tspRequestPolicy = "1.3.6.1.4.1.13762.3";
    private String userAgent = "POI XmlSign Service TSP Client";
    private String xadesSignatureId = "idSignedProperties";
    private boolean xadesSignaturePolicyImplied = true;
    private String xadesCanonicalizationMethod = "http://www.w3.org/2001/10/xml-exc-c14n#";
    private boolean xadesIssuerNameNoReverseOrder = true;
    private String packageSignatureId = "idPackageSignature";
    private String signatureDescription = "Office OpenXML Document";
    private SignatureMarshalListener signatureMarshalListener = new SignatureMarshalDefaultListener();
    private final Map<String, String> namespacePrefixes = new HashMap();
    private boolean updateConfigOnValidate = false;
    private boolean allowMultipleSignatures = false;
    private boolean secureValidation = true;
    private String commitmentType = "Created and approved this document";
    private boolean allowCRLDownload = false;
    private final List<CRLEntry> crlEntries = new ArrayList();
    private final KeyStore keyStore = emptyKeyStore();

    /* loaded from: classes10.dex */
    public static class CRLEntry {
        private final String certCN;
        private final byte[] crlBytes;
        private final String crlURL;

        public CRLEntry(String crlURL, String certCN, byte[] crlBytes) {
            this.crlURL = crlURL;
            this.certCN = certCN;
            this.crlBytes = crlBytes;
        }

        public String getCrlURL() {
            return this.crlURL;
        }

        public String getCertCN() {
            return this.certCN;
        }

        public byte[] getCrlBytes() {
            return this.crlBytes;
        }
    }

    public SignatureConfig() {
        this.namespacePrefixes.put("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi");
        this.namespacePrefixes.put(SignatureFacet.XADES_132_NS, "xd");
    }

    public void addSignatureFacet(SignatureFacet signatureFacet) {
        this.signatureFacets.add(signatureFacet);
    }

    public List<SignatureFacet> getSignatureFacets() {
        if (this.signatureFacets.isEmpty()) {
            return (List) DEFAULT_FACETS.stream().map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (SignatureFacet) ((Supplier) obj).get();
                }
            }).collect(Collectors.toList());
        }
        return this.signatureFacets;
    }

    public void setSignatureFacets(List<SignatureFacet> signatureFacets) {
        this.signatureFacets = signatureFacets;
    }

    public HashAlgorithm getDigestAlgo() {
        return this.digestAlgo;
    }

    public void setDigestAlgo(HashAlgorithm digestAlgo) {
        this.digestAlgo = digestAlgo;
    }

    public PrivateKey getKey() {
        return this.key;
    }

    public void setKey(PrivateKey key) {
        this.key = key;
    }

    public List<X509Certificate> getSigningCertificateChain() {
        return this.signingCertificateChain;
    }

    public void setSigningCertificateChain(List<X509Certificate> signingCertificateChain) {
        this.signingCertificateChain = signingCertificateChain;
    }

    public Date getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public String formatExecutionTime() {
        DateFormat fmt = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
        fmt.setTimeZone(LocaleUtil.TIMEZONE_UTC);
        return fmt.format(getExecutionTime());
    }

    public void setExecutionTime(String executionTime) {
        if (executionTime != null && !executionTime.isEmpty()) {
            DateFormat fmt = new SimpleDateFormat(SIGNATURE_TIME_FORMAT, Locale.ROOT);
            fmt.setTimeZone(LocaleUtil.TIMEZONE_UTC);
            try {
                this.executionTime = fmt.parse(executionTime);
            } catch (ParseException e) {
                LOG.atWarn().log("Illegal execution time: {}. Must be formatted as yyyy-MM-dd'T'HH:mm:ss'Z'", executionTime);
            }
        }
    }

    public SignaturePolicyService getSignaturePolicyService() {
        return this.signaturePolicyService;
    }

    public void setSignaturePolicyService(SignaturePolicyService signaturePolicyService) {
        this.signaturePolicyService = signaturePolicyService;
    }

    @Removal(version = "5.0.0")
    @Deprecated
    public URIDereferencer getUriDereferencer() {
        return this.uriDereferencer;
    }

    @Removal(version = "5.0.0")
    @Deprecated
    public void setUriDereferencer(URIDereferencer uriDereferencer) {
        this.uriDereferencer = uriDereferencer;
    }

    public String getSignatureDescription() {
        return this.signatureDescription;
    }

    public void setSignatureDescription(String signatureDescription) {
        this.signatureDescription = signatureDescription;
    }

    public byte[] getSignatureImage() {
        return this.signatureImage;
    }

    public byte[] getSignatureImageValid() {
        return this.signatureImageValid;
    }

    public byte[] getSignatureImageInvalid() {
        return this.signatureImageInvalid;
    }

    public ClassID getSignatureImageSetupId() {
        return this.signatureImageSetupId;
    }

    public void setSignatureImageSetupId(ClassID signatureImageSetupId) {
        this.signatureImageSetupId = signatureImageSetupId;
    }

    public void setSignatureImage(byte[] signatureImage) {
        this.signatureImage = signatureImage == null ? null : (byte[]) signatureImage.clone();
    }

    public void setSignatureImageValid(byte[] signatureImageValid) {
        this.signatureImageValid = signatureImageValid == null ? null : (byte[]) signatureImageValid.clone();
    }

    public void setSignatureImageInvalid(byte[] signatureImageInvalid) {
        this.signatureImageInvalid = signatureImageInvalid == null ? null : (byte[]) signatureImageInvalid.clone();
    }

    public String getCanonicalizationMethod() {
        return this.canonicalizationMethod;
    }

    public void setCanonicalizationMethod(String canonicalizationMethod) {
        this.canonicalizationMethod = verifyCanonicalizationMethod(canonicalizationMethod, "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String verifyCanonicalizationMethod(String canonicalizationMethod, String defaultMethod) {
        char c;
        if (canonicalizationMethod == null || canonicalizationMethod.isEmpty()) {
            return defaultMethod;
        }
        switch (canonicalizationMethod.hashCode()) {
            case -2012395451:
                if (canonicalizationMethod.equals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -785330953:
                if (canonicalizationMethod.equals("http://www.w3.org/2000/09/xmldsig#enveloped-signature")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -549269964:
                if (canonicalizationMethod.equals("http://www.w3.org/2001/10/xml-exc-c14n#")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 246158456:
                if (canonicalizationMethod.equals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1783513390:
                if (canonicalizationMethod.equals("http://www.w3.org/2001/10/xml-exc-c14n#WithComments")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return canonicalizationMethod;
            default:
                throw new EncryptedDocumentException("Unknown CanonicalizationMethod: " + canonicalizationMethod);
        }
    }

    public String getPackageSignatureId() {
        return this.packageSignatureId;
    }

    public void setPackageSignatureId(String packageSignatureId) {
        this.packageSignatureId = (String) nvl(packageSignatureId, "xmldsig-" + UUID.randomUUID());
    }

    public String getTspUrl() {
        return this.tspUrl;
    }

    public void setTspUrl(String tspUrl) {
        this.tspUrl = tspUrl;
    }

    public boolean isTspOldProtocol() {
        return this.tspOldProtocol;
    }

    public void setTspOldProtocol(boolean tspOldProtocol) {
        this.tspOldProtocol = tspOldProtocol;
    }

    public HashAlgorithm getTspDigestAlgo() {
        return (HashAlgorithm) nvl(this.tspDigestAlgo, this.digestAlgo);
    }

    public void setTspDigestAlgo(HashAlgorithm tspDigestAlgo) {
        this.tspDigestAlgo = tspDigestAlgo;
    }

    public String getProxyUrl() {
        return this.proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public TimeStampService getTspService() {
        return this.tspService;
    }

    public void setTspService(TimeStampService tspService) {
        this.tspService = tspService;
    }

    public TimeStampHttpClient getTspHttpClient() {
        return this.tspHttpClient;
    }

    public void setTspHttpClient(TimeStampHttpClient tspHttpClient) {
        this.tspHttpClient = tspHttpClient;
    }

    public String getTspUser() {
        return this.tspUser;
    }

    public void setTspUser(String tspUser) {
        this.tspUser = tspUser;
    }

    public String getTspPass() {
        return this.tspPass;
    }

    public void setTspPass(String tspPass) {
        this.tspPass = tspPass;
    }

    public TimeStampServiceValidator getTspValidator() {
        return this.tspValidator;
    }

    public void setTspValidator(TimeStampServiceValidator tspValidator) {
        this.tspValidator = tspValidator;
    }

    public RevocationDataService getRevocationDataService() {
        return this.revocationDataService;
    }

    public void setRevocationDataService(RevocationDataService revocationDataService) {
        this.revocationDataService = revocationDataService;
    }

    public HashAlgorithm getXadesDigestAlgo() {
        return (HashAlgorithm) nvl(this.xadesDigestAlgo, this.digestAlgo);
    }

    public void setXadesDigestAlgo(HashAlgorithm xadesDigestAlgo) {
        this.xadesDigestAlgo = xadesDigestAlgo;
    }

    public void setXadesDigestAlgo(String xadesDigestAlgo) {
        this.xadesDigestAlgo = getDigestMethodAlgo(xadesDigestAlgo);
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getTspRequestPolicy() {
        return this.tspRequestPolicy;
    }

    public void setTspRequestPolicy(String tspRequestPolicy) {
        this.tspRequestPolicy = tspRequestPolicy;
    }

    public boolean isIncludeEntireCertificateChain() {
        return this.includeEntireCertificateChain;
    }

    public void setIncludeEntireCertificateChain(boolean includeEntireCertificateChain) {
        this.includeEntireCertificateChain = includeEntireCertificateChain;
    }

    public boolean isIncludeIssuerSerial() {
        return this.includeIssuerSerial;
    }

    public void setIncludeIssuerSerial(boolean includeIssuerSerial) {
        this.includeIssuerSerial = includeIssuerSerial;
    }

    public boolean isIncludeKeyValue() {
        return this.includeKeyValue;
    }

    public void setIncludeKeyValue(boolean includeKeyValue) {
        this.includeKeyValue = includeKeyValue;
    }

    public String getXadesRole() {
        return this.xadesRole;
    }

    public void setXadesRole(String xadesRole) {
        this.xadesRole = xadesRole;
    }

    public String getXadesSignatureId() {
        return (String) nvl(this.xadesSignatureId, "idSignedProperties");
    }

    public void setXadesSignatureId(String xadesSignatureId) {
        this.xadesSignatureId = xadesSignatureId;
    }

    public boolean isXadesSignaturePolicyImplied() {
        return this.xadesSignaturePolicyImplied;
    }

    public void setXadesSignaturePolicyImplied(boolean xadesSignaturePolicyImplied) {
        this.xadesSignaturePolicyImplied = xadesSignaturePolicyImplied;
    }

    public boolean isXadesIssuerNameNoReverseOrder() {
        return this.xadesIssuerNameNoReverseOrder;
    }

    public void setXadesIssuerNameNoReverseOrder(boolean xadesIssuerNameNoReverseOrder) {
        this.xadesIssuerNameNoReverseOrder = xadesIssuerNameNoReverseOrder;
    }

    public SignatureMarshalListener getSignatureMarshalListener() {
        return this.signatureMarshalListener;
    }

    public void setSignatureMarshalListener(SignatureMarshalListener signatureMarshalListener) {
        this.signatureMarshalListener = signatureMarshalListener;
    }

    public Map<String, String> getNamespacePrefixes() {
        return this.namespacePrefixes;
    }

    public void setNamespacePrefixes(Map<String, String> namespacePrefixes) {
        this.namespacePrefixes.clear();
        this.namespacePrefixes.putAll(namespacePrefixes);
    }

    private static <T> T nvl(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public String getSignatureMethodUri() {
        switch (getDigestAlgo()) {
            case sha1:
                return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
            case sha224:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha224";
            case sha256:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";
            case sha384:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384";
            case sha512:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512";
            case ripemd160:
                return "http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160";
            default:
                throw new EncryptedDocumentException("Hash algorithm " + getDigestAlgo() + " not supported for signing.");
        }
    }

    public String getDigestMethodUri() {
        return getDigestMethodUri(getDigestAlgo());
    }

    public static String getDigestMethodUri(HashAlgorithm digestAlgo) {
        switch (digestAlgo) {
            case sha1:
                return "http://www.w3.org/2000/09/xmldsig#sha1";
            case sha224:
                return DigestMethod_SHA224;
            case sha256:
                return "http://www.w3.org/2001/04/xmlenc#sha256";
            case sha384:
                return DigestMethod_SHA384;
            case sha512:
                return "http://www.w3.org/2001/04/xmlenc#sha512";
            case ripemd160:
                return "http://www.w3.org/2001/04/xmlenc#ripemd160";
            default:
                throw new EncryptedDocumentException("Hash algorithm " + digestAlgo + " not supported for signing.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static HashAlgorithm getDigestMethodAlgo(String digestMethodUri) {
        char c;
        if (digestMethodUri == null || digestMethodUri.isEmpty()) {
            return null;
        }
        switch (digestMethodUri.hashCode()) {
            case -1000393448:
                if (digestMethodUri.equals("http://www.w3.org/2001/04/xmlenc#sha256")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1000390693:
                if (digestMethodUri.equals("http://www.w3.org/2001/04/xmlenc#sha512")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1060036557:
                if (digestMethodUri.equals("http://www.w3.org/2000/09/xmldsig#sha1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1253031479:
                if (digestMethodUri.equals("http://www.w3.org/2001/04/xmlenc#ripemd160")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2029689854:
                if (digestMethodUri.equals(DigestMethod_SHA224)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2029691001:
                if (digestMethodUri.equals(DigestMethod_SHA384)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return HashAlgorithm.sha1;
            case 1:
                return HashAlgorithm.sha224;
            case 2:
                return HashAlgorithm.sha256;
            case 3:
                return HashAlgorithm.sha384;
            case 4:
                return HashAlgorithm.sha512;
            case 5:
                return HashAlgorithm.ripemd160;
            default:
                throw new EncryptedDocumentException("Hash algorithm " + digestMethodUri + " not supported for signing.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void setSignatureMethodFromUri(String signatureMethodUri) {
        char c;
        switch (signatureMethodUri.hashCode()) {
            case -871953275:
                if (signatureMethodUri.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-ripemd160")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -699582165:
                if (signatureMethodUri.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha224")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -699582070:
                if (signatureMethodUri.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -699581018:
                if (signatureMethodUri.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha384")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -699579315:
                if (signatureMethodUri.equals("http://www.w3.org/2001/04/xmldsig-more#rsa-sha512")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 670108474:
                if (signatureMethodUri.equals("http://www.w3.org/2000/09/xmldsig#rsa-sha1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                setDigestAlgo(HashAlgorithm.sha1);
                return;
            case 1:
                setDigestAlgo(HashAlgorithm.sha224);
                return;
            case 2:
                setDigestAlgo(HashAlgorithm.sha256);
                return;
            case 3:
                setDigestAlgo(HashAlgorithm.sha384);
                return;
            case 4:
                setDigestAlgo(HashAlgorithm.sha512);
                return;
            case 5:
                setDigestAlgo(HashAlgorithm.ripemd160);
                return;
            default:
                throw new EncryptedDocumentException("Hash algorithm " + signatureMethodUri + " not supported.");
        }
    }

    public static String[] getProviderNames() {
        String sysProp = System.getProperty("jsr105Provider");
        return (sysProp == null || sysProp.isEmpty()) ? new String[]{XMLSEC_SANTUARIO, XMLSEC_JDK} : new String[]{sysProp, XMLSEC_SANTUARIO, XMLSEC_JDK};
    }

    public String getXadesCanonicalizationMethod() {
        return this.xadesCanonicalizationMethod;
    }

    public void setXadesCanonicalizationMethod(String xadesCanonicalizationMethod) {
        this.xadesCanonicalizationMethod = verifyCanonicalizationMethod(xadesCanonicalizationMethod, "http://www.w3.org/2001/10/xml-exc-c14n#");
    }

    public boolean isUpdateConfigOnValidate() {
        return this.updateConfigOnValidate;
    }

    public void setUpdateConfigOnValidate(boolean updateConfigOnValidate) {
        this.updateConfigOnValidate = updateConfigOnValidate;
    }

    public boolean isAllowMultipleSignatures() {
        return this.allowMultipleSignatures;
    }

    public void setAllowMultipleSignatures(boolean allowMultipleSignatures) {
        this.allowMultipleSignatures = allowMultipleSignatures;
    }

    public boolean isSecureValidation() {
        return this.secureValidation;
    }

    public void setSecureValidation(boolean secureValidation) {
        this.secureValidation = secureValidation;
    }

    public String getCommitmentType() {
        return this.commitmentType;
    }

    public void setCommitmentType(String commitmentType) {
        this.commitmentType = commitmentType;
    }

    public CRLEntry addCRL(String crlURL, String certCN, byte[] crlBytes) {
        CRLEntry ce = new CRLEntry(crlURL, certCN, crlBytes);
        this.crlEntries.add(ce);
        return ce;
    }

    public List<CRLEntry> getCrlEntries() {
        return this.crlEntries;
    }

    public boolean isAllowCRLDownload() {
        return this.allowCRLDownload;
    }

    public void setAllowCRLDownload(boolean allowCRLDownload) {
        this.allowCRLDownload = allowCRLDownload;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void addCachedCertificate(String alias, X509Certificate x509) throws KeyStoreException {
        String lAlias = alias;
        if (lAlias == null) {
            lAlias = x509.getSubjectX500Principal().getName();
        }
        if (this.keyStore != null) {
            synchronized (this.keyStore) {
                this.keyStore.setCertificateEntry(lAlias, x509);
            }
        }
    }

    public void addCachedCertificate(String alias, byte[] x509Bytes) throws KeyStoreException, CertificateException {
        CertificateFactory certFact = CertificateFactory.getInstance("X.509");
        X509Certificate x509 = (X509Certificate) certFact.generateCertificate(new ByteArrayInputStream(x509Bytes));
        addCachedCertificate((String) null, x509);
    }

    public X509Certificate getCachedCertificateByPrinicipal(final String principalName) {
        if (this.keyStore == null) {
            return null;
        }
        try {
            Iterator it = Collections.list(this.keyStore.aliases()).iterator();
            while (it.hasNext()) {
                String a = (String) it.next();
                Certificate[] chain = this.keyStore.getCertificateChain(a);
                if (chain == null) {
                    Certificate cert = this.keyStore.getCertificate(a);
                    if (cert != null) {
                        chain = new Certificate[]{cert};
                    }
                }
                Stream of = Stream.of((Object[]) chain);
                final Class<X509Certificate> cls = X509Certificate.class;
                X509Certificate.class.getClass();
                Optional<X509Certificate> found = of.map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        Object cast;
                        cast = cls.cast((Certificate) obj);
                        return (X509Certificate) cast;
                    }
                }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureConfig$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean equalsIgnoreCase;
                        equalsIgnoreCase = principalName.equalsIgnoreCase(((X509Certificate) obj).getSubjectX500Principal().getName());
                        return equalsIgnoreCase;
                    }
                }).findFirst();
                if (found.isPresent()) {
                    return found.get();
                }
            }
            return null;
        } catch (KeyStoreException e) {
            return null;
        }
    }

    private static KeyStore emptyKeyStore() {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(null, null);
            return ks;
        } catch (IOException | GeneralSecurityException e) {
            LOG.atError().withThrowable(e).log("unable to create PKCS #12 keystore - XAdES certificate chain lookups disabled");
            return null;
        }
    }
}
