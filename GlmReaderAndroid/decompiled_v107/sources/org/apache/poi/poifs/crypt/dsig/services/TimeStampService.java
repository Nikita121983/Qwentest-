package org.apache.poi.poifs.crypt.dsig.services;

import org.apache.poi.poifs.crypt.dsig.SignatureInfo;

/* loaded from: classes10.dex */
public interface TimeStampService {
    byte[] timeStamp(SignatureInfo signatureInfo, byte[] bArr, RevocationData revocationData) throws Exception;
}
