package org.apache.poi.poifs.crypt.xor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class XOREncryptor extends Encryptor {
    /* JADX INFO: Access modifiers changed from: protected */
    public XOREncryptor() {
    }

    protected XOREncryptor(XOREncryptor other) {
        super(other);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password) {
        int keyComp = CryptoFunctions.createXorKey1(password);
        int verifierComp = CryptoFunctions.createXorVerifier1(password);
        byte[] xorArray = CryptoFunctions.createXorArray1(password);
        byte[] shortBuf = new byte[2];
        XOREncryptionVerifier ver = (XOREncryptionVerifier) getEncryptionInfo().getVerifier();
        LittleEndian.putUShort(shortBuf, 0, keyComp);
        ver.setEncryptedKey(shortBuf);
        LittleEndian.putUShort(shortBuf, 0, verifierComp);
        ver.setEncryptedVerifier(shortBuf);
        setSecretKey(new SecretKeySpec(xorArray, "XOR"));
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        confirmPassword(password);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(dir);
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public XORCipherOutputStream getDataStream(OutputStream stream, int initialOffset) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(stream, initialOffset);
    }

    protected int getKeySizeInBytes() {
        return -1;
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public void setChunkSize(int chunkSize) {
    }

    @Override // org.apache.poi.poifs.crypt.Encryptor
    public XOREncryptor copy() {
        return new XOREncryptor(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class XORCipherOutputStream extends ChunkedCipherOutputStream {
        private int recordEnd;
        private int recordStart;

        public XORCipherOutputStream(OutputStream stream, int initialPos) throws IOException, GeneralSecurityException {
            super(stream, -1);
        }

        public XORCipherOutputStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
            super(dir, -1);
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected Cipher initCipherForBlock(Cipher cipher, int block, boolean lastChunk) throws GeneralSecurityException {
            return XORDecryptor.initCipherForBlock(cipher, block, XOREncryptor.this.getEncryptionInfo(), XOREncryptor.this.getSecretKey(), 1);
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected void calculateChecksum(File file, int i) {
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) {
            throw new EncryptedDocumentException("createEncryptionInfoEntry not supported");
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        public void setNextRecordSize(int recordSize, boolean isPlain) {
            if (this.recordEnd > 0 && !isPlain) {
                invokeCipher((int) getPos(), true);
            }
            this.recordStart = ((int) getTotalPos()) + 4;
            this.recordEnd = this.recordStart + recordSize;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            setNextRecordSize(0, true);
            super.flush();
        }

        @Override // org.apache.poi.poifs.crypt.ChunkedCipherOutputStream
        protected int invokeCipher(int posInChunk, boolean doFinal) {
            if (posInChunk == 0) {
                return 0;
            }
            int start = Math.max(posInChunk - (this.recordEnd - this.recordStart), 0);
            SparseBitSet plainBytes = getPlainByteFlags();
            byte[] xorArray = XOREncryptor.this.getEncryptionInfo().getEncryptor().getSecretKey().getEncoded();
            byte[] chunk = getChunk();
            byte[] plain = plainBytes.isEmpty() ? null : (byte[]) chunk.clone();
            int xorArrayIndex = this.recordEnd + (start - this.recordStart);
            int i = start;
            while (i < posInChunk) {
                byte value = chunk[i];
                chunk[i] = rotateLeft((byte) (xorArray[xorArrayIndex & 15] ^ value), 5);
                i++;
                xorArrayIndex++;
            }
            if (plain != null) {
                for (int i2 = plainBytes.nextSetBit(start); i2 >= 0 && i2 < posInChunk; i2 = plainBytes.nextSetBit(i2 + 1)) {
                    chunk[i2] = plain[i2];
                }
            }
            return posInChunk;
        }

        private byte rotateLeft(byte bits, int shift) {
            return (byte) (((bits & 255) << shift) | ((bits & 255) >>> (8 - shift)));
        }
    }
}
