package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/* loaded from: classes10.dex */
public class RevocationData {
    private final List<byte[]> crls = new ArrayList();
    private final List<byte[]> ocsps = new ArrayList();
    private final List<X509Certificate> x509chain = new ArrayList();

    public void addCRL(final byte[] encodedCrl) {
        if (this.crls.stream().noneMatch(new Predicate() { // from class: org.apache.poi.poifs.crypt.dsig.services.RevocationData$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Arrays.equals((byte[]) obj, encodedCrl);
                return equals;
            }
        })) {
            this.crls.add(encodedCrl);
        }
    }

    public void addCRL(X509CRL crl) {
        try {
            addCRL(crl.getEncoded());
        } catch (CRLException e) {
            throw new IllegalArgumentException("CRL coding error: " + e.getMessage(), e);
        }
    }

    public void addOCSP(byte[] encodedOcsp) {
        this.ocsps.add(encodedOcsp);
    }

    public void addCertificate(X509Certificate x509) {
        this.x509chain.add(x509);
    }

    public List<byte[]> getCRLs() {
        return this.crls;
    }

    public List<byte[]> getOCSPs() {
        return this.ocsps;
    }

    public boolean hasOCSPs() {
        return !this.ocsps.isEmpty();
    }

    public boolean hasCRLs() {
        return !this.crls.isEmpty();
    }

    public boolean hasRevocationDataEntries() {
        return hasOCSPs() || hasCRLs();
    }

    public List<X509Certificate> getX509chain() {
        return this.x509chain;
    }
}
