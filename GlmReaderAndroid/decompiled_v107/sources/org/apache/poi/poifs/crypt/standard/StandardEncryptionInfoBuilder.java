package org.apache.poi.poifs.crypt.standard;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public class StandardEncryptionInfoBuilder implements EncryptionInfoBuilder {
    @Override // org.apache.poi.poifs.crypt.EncryptionInfoBuilder
    public void initialize(EncryptionInfo info, LittleEndianInput dis) throws IOException {
        dis.readInt();
        StandardEncryptionHeader header = new StandardEncryptionHeader(dis);
        info.setHeader(header);
        info.setVerifier(new StandardEncryptionVerifier(dis, header));
        if (info.getVersionMinor() == 2) {
            if (info.getVersionMajor() == 3 || info.getVersionMajor() == 4) {
                StandardDecryptor dec = new StandardDecryptor();
                dec.setEncryptionInfo(info);
                info.setDecryptor(dec);
            }
        }
    }

    @Override // org.apache.poi.poifs.crypt.EncryptionInfoBuilder
    public void initialize(EncryptionInfo info, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        CipherAlgorithm cipherAlgorithm2;
        HashAlgorithm hashAlgorithm2;
        ChainingMode chainingMode2;
        int keyBits2;
        int blockSize2;
        if (cipherAlgorithm != null) {
            cipherAlgorithm2 = cipherAlgorithm;
        } else {
            cipherAlgorithm2 = CipherAlgorithm.aes128;
        }
        if (cipherAlgorithm2 != CipherAlgorithm.aes128 && cipherAlgorithm2 != CipherAlgorithm.aes192 && cipherAlgorithm2 != CipherAlgorithm.aes256) {
            throw new EncryptedDocumentException("Standard encryption only supports AES128/192/256.");
        }
        if (hashAlgorithm != null) {
            hashAlgorithm2 = hashAlgorithm;
        } else {
            hashAlgorithm2 = HashAlgorithm.sha1;
        }
        if (hashAlgorithm2 != HashAlgorithm.sha1) {
            throw new EncryptedDocumentException("Standard encryption only supports SHA-1.");
        }
        if (chainingMode != null) {
            chainingMode2 = chainingMode;
        } else {
            chainingMode2 = ChainingMode.ecb;
        }
        if (chainingMode2 != ChainingMode.ecb) {
            throw new EncryptedDocumentException("Standard encryption only supports ECB chaining.");
        }
        if (keyBits != -1) {
            keyBits2 = keyBits;
        } else {
            keyBits2 = cipherAlgorithm2.defaultKeySize;
        }
        if (blockSize != -1) {
            blockSize2 = blockSize;
        } else {
            blockSize2 = cipherAlgorithm2.blockSize;
        }
        boolean found = false;
        int[] iArr = cipherAlgorithm2.allowedKeySize;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int ks = iArr[i];
            found |= ks == keyBits2;
        }
        if (!found) {
            throw new EncryptedDocumentException("KeySize " + keyBits2 + " not allowed for Cipher " + cipherAlgorithm2);
        }
        info.setHeader(new StandardEncryptionHeader(cipherAlgorithm2, hashAlgorithm2, keyBits2, blockSize2, chainingMode2));
        info.setVerifier(new StandardEncryptionVerifier(cipherAlgorithm2, hashAlgorithm2, keyBits2, blockSize2, chainingMode2));
        StandardDecryptor dec = new StandardDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        StandardEncryptor enc = new StandardEncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }
}
