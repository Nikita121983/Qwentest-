package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import org.apache.poi.poifs.crypt.HashAlgorithm;

/* loaded from: classes10.dex */
class SignatureOutputStream extends DigestOutputStream {
    Signature signature;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureOutputStream(HashAlgorithm algo, PrivateKey key) {
        super(algo, key);
    }

    @Override // org.apache.poi.poifs.crypt.dsig.DigestOutputStream
    public void init() throws GeneralSecurityException {
        String provider = isMSCapi(this.key) ? "SunMSCAPI" : "SunRsaSign";
        if (Security.getProvider(provider) != null) {
            this.signature = Signature.getInstance(this.algo.ecmaString + "withRSA", provider);
        } else {
            this.signature = Signature.getInstance(this.algo.ecmaString + "withRSA");
        }
        this.signature.initSign(this.key);
    }

    @Override // org.apache.poi.poifs.crypt.dsig.DigestOutputStream
    public byte[] sign() throws SignatureException {
        return this.signature.sign();
    }

    @Override // org.apache.poi.poifs.crypt.dsig.DigestOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        try {
            this.signature.update((byte) b);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }

    @Override // org.apache.poi.poifs.crypt.dsig.DigestOutputStream, java.io.OutputStream
    public void write(byte[] data, int off, int len) throws IOException {
        try {
            this.signature.update(data, off, len);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }
}
