package org.apache.poi.poifs.crypt.dsig.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.security.auth.x500.X500Principal;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.SignatureInfo;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Selector;

/* loaded from: classes10.dex */
public class TSPTimeStampService implements TimeStampService {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) TSPTimeStampService.class);

    public ASN1ObjectIdentifier mapDigestAlgoToOID(HashAlgorithm digestAlgo) {
        switch (digestAlgo) {
            case sha1:
                return X509ObjectIdentifiers.id_SHA1;
            case sha256:
                return NISTObjectIdentifiers.id_sha256;
            case sha384:
                return NISTObjectIdentifiers.id_sha384;
            case sha512:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unsupported digest algo: " + digestAlgo);
        }
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampService
    public byte[] timeStamp(SignatureInfo signatureInfo, byte[] data, final RevocationData revocationData) throws Exception {
        Map<String, X509CertificateHolder> certificateMap;
        TSPTimeStampService tSPTimeStampService = this;
        SignatureConfig signatureConfig = signatureInfo.getSignatureConfig();
        MessageDigest messageDigest = CryptoFunctions.getMessageDigest(signatureConfig.getTspDigestAlgo());
        byte[] digest = messageDigest.digest(data);
        BigInteger nonce = new BigInteger(128, new SecureRandom());
        TimeStampRequestGenerator requestGenerator = new TimeStampRequestGenerator();
        requestGenerator.setCertReq(true);
        String requestPolicy = signatureConfig.getTspRequestPolicy();
        if (requestPolicy != null) {
            requestGenerator.setReqPolicy(new ASN1ObjectIdentifier(requestPolicy));
        }
        ASN1ObjectIdentifier digestAlgoOid = tSPTimeStampService.mapDigestAlgoToOID(signatureConfig.getTspDigestAlgo());
        TimeStampRequest request = requestGenerator.generate(digestAlgoOid, digest, nonce);
        TimeStampHttpClient httpClient = signatureConfig.getTspHttpClient();
        httpClient.init(signatureConfig);
        httpClient.setContentTypeIn(signatureConfig.isTspOldProtocol() ? "application/timestamp-request" : "application/timestamp-query");
        TimeStampHttpClient.TimeStampHttpClientResponse response = httpClient.post(signatureConfig.getTspUrl(), request.getEncoded());
        if (!response.isOK()) {
            throw new IOException("Requesting timestamp data failed");
        }
        byte[] responseBytes = response.getResponseBytes();
        if (responseBytes.length == 0) {
            throw new IllegalStateException("Content-Length is zero");
        }
        TimeStampResponse timeStampResponse = new TimeStampResponse(responseBytes);
        timeStampResponse.validate(request);
        if (timeStampResponse.getStatus() != 0) {
            LOG.atDebug().log("status: {}", Unbox.box(timeStampResponse.getStatus()));
            LOG.atDebug().log("status string: {}", timeStampResponse.getStatusString());
            PKIFailureInfo failInfo = timeStampResponse.getFailInfo();
            if (failInfo != null) {
                LOG.atDebug().log("fail info int value: {}", Unbox.box(failInfo.intValue()));
                if (256 == failInfo.intValue()) {
                    LOG.atDebug().log("unaccepted policy");
                }
            }
            throw new IllegalStateException("timestamp response status != 0: " + timeStampResponse.getStatus());
        }
        TimeStampToken timeStampToken = timeStampResponse.getTimeStampToken();
        SignerId signerId = timeStampToken.getSID();
        final BigInteger signerCertSerialNumber = signerId.getSerialNumber();
        final X500Name signerCertIssuer = signerId.getIssuer();
        LOG.atDebug().log("signer cert serial number: {}", signerCertSerialNumber);
        LOG.atDebug().log("signer cert issuer: {}", signerCertIssuer);
        Map<String, X509CertificateHolder> certificateMap2 = (Map) timeStampToken.getCertificates().getMatches((Selector) null).stream().collect(Collectors.toMap(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String x500Name;
                x500Name = ((X509CertificateHolder) obj).getSubject().toString();
                return x500Name;
            }
        }, Function.identity()));
        X509CertificateHolder signerCert = certificateMap2.values().stream().filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TSPTimeStampService.lambda$timeStamp$1(signerCertIssuer, signerCertSerialNumber, (X509CertificateHolder) obj);
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return TSPTimeStampService.lambda$timeStamp$2();
            }
        });
        JcaX509CertificateConverter x509converter = new JcaX509CertificateConverter();
        x509converter.setProvider("BC");
        X509Certificate child = x509converter.getCertificate(signerCert);
        while (true) {
            revocationData.addCertificate(child);
            X509Certificate child2 = child;
            X500Principal issuer = child2.getIssuerX500Principal();
            String requestPolicy2 = requestPolicy;
            if (child2.getSubjectX500Principal().equals(issuer)) {
                child = child2;
                break;
            }
            X509CertificateHolder parentHolder = certificateMap2.get(issuer.getName());
            if (parentHolder != null) {
                child = x509converter.getCertificate(parentHolder);
            } else {
                child = signatureConfig.getCachedCertificateByPrinicipal(issuer.getName());
            }
            if (child == null) {
                certificateMap = certificateMap2;
            } else {
                certificateMap = certificateMap2;
                List<byte[]> retrieveCRL = tSPTimeStampService.retrieveCRL(signatureConfig, child);
                revocationData.getClass();
                retrieveCRL.forEach(new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda11
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RevocationData.this.addCRL((byte[]) obj);
                    }
                });
            }
            if (child == null) {
                break;
            }
            tSPTimeStampService = this;
            certificateMap2 = certificateMap;
            requestPolicy = requestPolicy2;
        }
        BcRSASignerInfoVerifierBuilder verifierBuilder = new BcRSASignerInfoVerifierBuilder(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), new DefaultDigestAlgorithmIdentifierFinder(), new BcDigestCalculatorProvider());
        SignerInformationVerifier verifier = verifierBuilder.build(signerCert);
        timeStampToken.validate(verifier);
        if (signatureConfig.getTspValidator() != null) {
            signatureConfig.getTspValidator().validate(revocationData.getX509chain(), revocationData);
        }
        LOG.atDebug().log("time-stamp token time: {}", timeStampToken.getTimeStampInfo().getGenTime());
        return timeStampToken.getEncoded();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$timeStamp$1(X500Name signerCertIssuer, BigInteger signerCertSerialNumber, X509CertificateHolder h) {
        return signerCertIssuer.equals(h.getIssuer()) && signerCertSerialNumber.equals(h.getSerialNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IllegalStateException lambda$timeStamp$2() {
        return new IllegalStateException("TSP response token has no signer certificate");
    }

    protected List<byte[]> retrieveCRL(final SignatureConfig signatureConfig, final X509Certificate holder) throws IOException {
        final List<SignatureConfig.CRLEntry> crlEntries = signatureConfig.getCrlEntries();
        byte[] crlPoints = holder.getExtensionValue(Extension.cRLDistributionPoints.getId());
        if (crlPoints == null) {
            return Collections.emptyList();
        }
        ASN1Primitive extVal = JcaX509ExtensionUtils.parseExtensionValue(crlPoints);
        return (List) Stream.of((Object[]) CRLDistPoint.getInstance(extVal).getDistributionPoints()).map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                DistributionPointName distributionPoint;
                distributionPoint = ((DistributionPoint) obj).getDistributionPoint();
                return distributionPoint;
            }
        }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda13
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((DistributionPointName) obj);
                return nonNull;
            }
        }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda14
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TSPTimeStampService.lambda$retrieveCRL$3((DistributionPointName) obj);
            }
        }).flatMap(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream of;
                of = Stream.of((Object[]) GeneralNames.getInstance(((DistributionPointName) obj).getName()).getNames());
                return of;
            }
        }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TSPTimeStampService.lambda$retrieveCRL$5((GeneralName) obj);
            }
        }).map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String string;
                string = ASN1IA5String.getInstance(((GeneralName) obj).getName()).getString();
                return string;
            }
        }).flatMap(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TSPTimeStampService.this.m2507x95ba011e(crlEntries, holder, signatureConfig, (String) obj);
            }
        }).filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((byte[]) obj);
                return nonNull;
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$retrieveCRL$3(DistributionPointName dpn) {
        return dpn.getType() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$retrieveCRL$5(GeneralName genName) {
        return genName.getTagNo() == 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$retrieveCRL$9$org-apache-poi-poifs-crypt-dsig-services-TSPTimeStampService, reason: not valid java name */
    public /* synthetic */ Stream m2507x95ba011e(List crlEntries, final X509Certificate holder, SignatureConfig signatureConfig, final String url) {
        SignatureConfig.CRLEntry ce;
        List<SignatureConfig.CRLEntry> ul = (List) crlEntries.stream().filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TSPTimeStampService.this.m2505x10241840(holder, url, (SignatureConfig.CRLEntry) obj);
            }
        }).collect(Collectors.toList());
        Stream<SignatureConfig.CRLEntry> cl = crlEntries.stream().filter(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TSPTimeStampService.this.m2506xa46287df(holder, url, (SignatureConfig.CRLEntry) obj);
            }
        });
        if (ul.isEmpty() && (ce = downloadCRL(signatureConfig, url)) != null) {
            ul.add(ce);
        }
        return Stream.concat(ul.stream(), cl).map(new Function() { // from class: org.apache.poi.poifs.crypt.dsig.services.TSPTimeStampService$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SignatureConfig.CRLEntry) obj).getCrlBytes();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: matchCRLbyUrl, reason: merged with bridge method [inline-methods] */
    public boolean m2505x10241840(SignatureConfig.CRLEntry other, X509Certificate holder, String url) {
        return url.equals(other.getCrlURL());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: matchCRLbyCN, reason: merged with bridge method [inline-methods] */
    public boolean m2506xa46287df(SignatureConfig.CRLEntry other, X509Certificate holder, String url) {
        return holder.getSubjectX500Principal().getName().equals(other.getCertCN());
    }

    protected SignatureConfig.CRLEntry downloadCRL(SignatureConfig signatureConfig, String url) {
        if (!signatureConfig.isAllowCRLDownload()) {
            return null;
        }
        TimeStampHttpClient httpClient = signatureConfig.getTspHttpClient();
        httpClient.init(signatureConfig);
        httpClient.setBasicAuthentication(null, null);
        try {
            TimeStampHttpClient.TimeStampHttpClientResponse response = httpClient.get(url);
            if (!response.isOK()) {
                return null;
            }
            try {
                CertificateFactory certFact = CertificateFactory.getInstance("X.509");
                byte[] crlBytes = response.getResponseBytes();
                X509CRL crl = (X509CRL) certFact.generateCRL(new ByteArrayInputStream(crlBytes));
                return signatureConfig.addCRL(url, crl.getIssuerX500Principal().getName(), crlBytes);
            } catch (GeneralSecurityException e) {
                LOG.atWarn().withThrowable(e).log("CRL download failed from {}", url);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }
}
