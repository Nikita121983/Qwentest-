package org.apache.poi.poifs.crypt.dsig.services;

/* loaded from: classes10.dex */
public interface SignaturePolicyService {
    String getSignaturePolicyDescription();

    byte[] getSignaturePolicyDocument();

    String getSignaturePolicyDownloadUrl();

    String getSignaturePolicyIdentifier();
}
