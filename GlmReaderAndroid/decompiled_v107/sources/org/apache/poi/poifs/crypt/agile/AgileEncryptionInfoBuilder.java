package org.apache.poi.poifs.crypt.agile;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class AgileEncryptionInfoBuilder implements EncryptionInfoBuilder {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.poi.poifs.crypt.EncryptionInfoBuilder
    public void initialize(EncryptionInfo info, LittleEndianInput littleEndianInput) throws IOException {
        if (!(littleEndianInput instanceof InputStream)) {
            throw new IllegalArgumentException("Had unexpected type of input: " + (littleEndianInput == 0 ? "<null>" : littleEndianInput.getClass()));
        }
        EncryptionDocument ed = parseDescriptor((InputStream) littleEndianInput);
        info.setHeader(new AgileEncryptionHeader(ed));
        info.setVerifier(new AgileEncryptionVerifier(ed));
        if (info.getVersionMajor() == EncryptionMode.agile.versionMajor && info.getVersionMinor() == EncryptionMode.agile.versionMinor) {
            AgileDecryptor dec = new AgileDecryptor();
            dec.setEncryptionInfo(info);
            info.setDecryptor(dec);
            AgileEncryptor enc = new AgileEncryptor();
            enc.setEncryptionInfo(info);
            info.setEncryptor(enc);
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
        if (cipherAlgorithm2 == CipherAlgorithm.rc4) {
            throw new EncryptedDocumentException("RC4 must not be used with agile encryption.");
        }
        if (hashAlgorithm != null) {
            hashAlgorithm2 = hashAlgorithm;
        } else {
            hashAlgorithm2 = HashAlgorithm.sha1;
        }
        if (chainingMode != null) {
            chainingMode2 = chainingMode;
        } else {
            chainingMode2 = ChainingMode.cbc;
        }
        if (chainingMode2 != ChainingMode.cbc && chainingMode2 != ChainingMode.cfb) {
            throw new EncryptedDocumentException("Agile encryption only supports CBC/CFB chaining.");
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
        info.setHeader(new AgileEncryptionHeader(cipherAlgorithm2, hashAlgorithm2, keyBits2, blockSize2, chainingMode2));
        info.setVerifier(new AgileEncryptionVerifier(cipherAlgorithm2, hashAlgorithm2, keyBits2, blockSize2, chainingMode2));
        AgileDecryptor dec = new AgileDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        AgileEncryptor enc = new AgileEncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static EncryptionDocument parseDescriptor(String descriptor) {
        return parseDescriptor(new InputSource(descriptor));
    }

    protected static EncryptionDocument parseDescriptor(InputStream descriptor) {
        return parseDescriptor(new InputSource(descriptor));
    }

    private static EncryptionDocument parseDescriptor(InputSource descriptor) {
        try {
            Document doc = XMLHelper.newDocumentBuilder().parse(descriptor);
            EncryptionDocument ed = new EncryptionDocument();
            ed.parse(doc);
            return ed;
        } catch (IOException | SAXException e) {
            throw new EncryptedDocumentException("Unable to parse encryption descriptor", e);
        }
    }
}
