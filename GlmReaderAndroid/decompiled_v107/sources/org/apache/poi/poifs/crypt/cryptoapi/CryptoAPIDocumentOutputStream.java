package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public class CryptoAPIDocumentOutputStream extends ByteArrayOutputStream {
    private final Cipher cipher;
    private final CryptoAPIEncryptor encryptor;
    private final byte[] oneByte = {0};

    public CryptoAPIDocumentOutputStream(CryptoAPIEncryptor encryptor) throws GeneralSecurityException {
        this.encryptor = encryptor;
        this.cipher = encryptor.initCipherForBlock(null, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InputStream toInputStream(long maxSize) {
        try {
            return ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(toInputStream())).setMaxCount(maxSize)).get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void setSize(int count) {
        this.count = count;
    }

    public void setBlock(int block) throws GeneralSecurityException {
        this.encryptor.initCipherForBlock(this.cipher, block);
    }

    @Override // org.apache.commons.io.output.ByteArrayOutputStream, org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int b) {
        try {
            this.oneByte[0] = (byte) b;
            this.cipher.update(this.oneByte, 0, 1, this.oneByte, 0);
            super.write(this.oneByte);
        } catch (Exception e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override // org.apache.commons.io.output.ByteArrayOutputStream, org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) {
        Exception e;
        try {
        } catch (Exception e2) {
            e = e2;
        }
        try {
            this.cipher.update(b, off, len, b, off);
            super.write(b, off, len);
        } catch (Exception e3) {
            e = e3;
            throw new EncryptedDocumentException(e);
        }
    }
}
