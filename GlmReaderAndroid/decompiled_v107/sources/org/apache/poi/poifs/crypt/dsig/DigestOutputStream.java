package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

/* loaded from: classes10.dex */
class DigestOutputStream extends OutputStream {
    final HashAlgorithm algo;
    final PrivateKey key;
    private MessageDigest md;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestOutputStream(HashAlgorithm algo, PrivateKey key) {
        this.algo = algo;
        this.key = key;
    }

    public void init() throws GeneralSecurityException {
        if (isMSCapi(this.key)) {
            throw new EncryptedDocumentException("Windows keystore entries can't be signed with the " + this.algo + " hash. Please use one digest algorithm of sha1 / sha256 / sha384 / sha512.");
        }
        this.md = CryptoFunctions.getMessageDigest(this.algo);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.md.update((byte) b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] data, int off, int len) throws IOException {
        this.md.update(data, off, len);
    }

    public byte[] sign() throws IOException, GeneralSecurityException {
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            bos.write(getHashMagic());
            bos.write(this.md.digest());
            Cipher cipher = CryptoFunctions.getCipher(this.key, CipherAlgorithm.rsa, ChainingMode.ecb, null, 1, "PKCS1Padding");
            byte[] doFinal = cipher.doFinal(bos.toByteArray());
            if (bos != null) {
                bos.close();
            }
            return doFinal;
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMSCapi(PrivateKey key) {
        return key != null && key.getClass().getName().contains("mscapi");
    }

    byte[] getHashMagic() {
        try {
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                byte[] oidBytes = new Oid(this.algo.rsaOid).getDER();
                bos.write(48);
                bos.write(this.algo.hashSize + oidBytes.length + 6);
                bos.write(48);
                bos.write(oidBytes.length + 2);
                bos.write(oidBytes);
                bos.write(new byte[]{5, 0, 4});
                bos.write(this.algo.hashSize);
                byte[] byteArray = bos.toByteArray();
                if (bos != null) {
                    bos.close();
                }
                return byteArray;
            } finally {
            }
        } catch (IOException | GSSException e) {
            throw new IllegalStateException(e);
        }
    }
}
