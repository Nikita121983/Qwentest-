package org.apache.poi.poifs.crypt.xor;

import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public class XOREncryptionVerifier extends EncryptionVerifier implements EncryptionRecord {
    /* JADX INFO: Access modifiers changed from: protected */
    public XOREncryptionVerifier() {
        setEncryptedKey(new byte[2]);
        setEncryptedVerifier(new byte[2]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XOREncryptionVerifier(LittleEndianInput is) {
        byte[] key = new byte[2];
        is.readFully(key);
        setEncryptedKey(key);
        byte[] verifier = new byte[2];
        is.readFully(verifier);
        setEncryptedVerifier(verifier);
    }

    protected XOREncryptionVerifier(XOREncryptionVerifier other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.standard.EncryptionRecord
    public void write(LittleEndianByteArrayOutputStream bos) {
        bos.write(getEncryptedKey());
        bos.write(getEncryptedVerifier());
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier, org.apache.poi.common.Duplicatable
    public XOREncryptionVerifier copy() {
        return new XOREncryptionVerifier(this);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public final void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionVerifier
    public final void setEncryptedKey(byte[] encryptedKey) {
        super.setEncryptedKey(encryptedKey);
    }
}
