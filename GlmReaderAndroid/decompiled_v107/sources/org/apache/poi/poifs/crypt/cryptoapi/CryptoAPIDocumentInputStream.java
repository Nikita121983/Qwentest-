package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import kotlin.UByte;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
class CryptoAPIDocumentInputStream extends ByteArrayInputStream {
    private Cipher cipher;
    private final CryptoAPIDecryptor decryptor;
    private byte[] oneByte;

    public void seek(int newpos) {
        if (newpos > this.count) {
            throw new ArrayIndexOutOfBoundsException(newpos);
        }
        this.pos = newpos;
        this.mark = newpos;
    }

    public void setBlock(int block) throws GeneralSecurityException {
        this.cipher = this.decryptor.initCipherForBlock(this.cipher, block);
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public synchronized int read() {
        int ch = super.read();
        if (ch == -1) {
            return -1;
        }
        this.oneByte[0] = (byte) ch;
        try {
            this.cipher.update(this.oneByte, 0, 1, this.oneByte);
            return this.oneByte[0] & UByte.MAX_VALUE;
        } catch (ShortBufferException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override // java.io.ByteArrayInputStream, java.io.InputStream
    public synchronized int read(byte[] b, int off, int len) {
        ShortBufferException e;
        int readLen = super.read(b, off, len);
        if (readLen == -1) {
            return -1;
        }
        try {
        } catch (ShortBufferException e2) {
            e = e2;
        }
        try {
            this.cipher.update(b, off, readLen, b, off);
            return readLen;
        } catch (ShortBufferException e3) {
            e = e3;
            throw new EncryptedDocumentException(e);
        }
    }

    public CryptoAPIDocumentInputStream(CryptoAPIDecryptor decryptor, byte[] buf) throws GeneralSecurityException {
        super(buf);
        this.oneByte = new byte[]{0};
        this.decryptor = decryptor;
        this.cipher = decryptor.initCipherForBlock(null, 0);
    }
}
