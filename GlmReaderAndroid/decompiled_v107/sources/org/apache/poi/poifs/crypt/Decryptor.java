package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public abstract class Decryptor implements GenericRecord {
    public static final String DEFAULT_PASSWORD = "VelvetSweatshop";
    public static final String DEFAULT_POIFS_ENTRY = "EncryptedPackage";
    protected EncryptionInfo encryptionInfo;
    private byte[] integrityHmacKey;
    private byte[] integrityHmacValue;
    private SecretKey secretKey;
    private byte[] verifier;

    public abstract Decryptor copy();

    public abstract InputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException;

    public abstract long getLength();

    public abstract boolean verifyPassword(String str) throws GeneralSecurityException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Decryptor() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Decryptor(Decryptor other) {
        this.encryptionInfo = other.encryptionInfo;
        this.secretKey = other.secretKey;
        this.verifier = other.verifier == null ? null : (byte[]) other.verifier.clone();
        this.integrityHmacKey = other.integrityHmacKey == null ? null : (byte[]) other.integrityHmacKey.clone();
        this.integrityHmacValue = other.integrityHmacValue != null ? (byte[]) other.integrityHmacValue.clone() : null;
    }

    public InputStream getDataStream(InputStream stream, int size, int initialPos) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support reading from a stream");
    }

    public void setChunkSize(int chunkSize) {
        throw new EncryptedDocumentException("this decryptor doesn't support changing the chunk size");
    }

    public Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support initCipherForBlock");
    }

    public static Decryptor getInstance(EncryptionInfo info) {
        Decryptor d = info.getDecryptor();
        if (d == null) {
            throw new EncryptedDocumentException("Unsupported version");
        }
        return d;
    }

    public InputStream getDataStream(POIFSFileSystem fs) throws IOException, GeneralSecurityException {
        return getDataStream(fs.getRoot());
    }

    public byte[] getVerifier() {
        return this.verifier;
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public byte[] getIntegrityHmacKey() {
        return this.integrityHmacKey;
    }

    public byte[] getIntegrityHmacValue() {
        return this.integrityHmacValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setVerifier(byte[] verifier) {
        this.verifier = verifier == null ? null : (byte[]) verifier.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIntegrityHmacKey(byte[] integrityHmacKey) {
        this.integrityHmacKey = integrityHmacKey == null ? null : (byte[]) integrityHmacKey.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIntegrityHmacValue(byte[] integrityHmacValue) {
        this.integrityHmacValue = integrityHmacValue == null ? null : (byte[]) integrityHmacValue.clone();
    }

    protected int getBlockSizeInBytes() {
        return this.encryptionInfo.getHeader().getBlockSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getKeySizeInBytes() {
        return this.encryptionInfo.getHeader().getKeySize() / 8;
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public void setEncryptionInfo(EncryptionInfo encryptionInfo) {
        this.encryptionInfo = encryptionInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0] */
    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Decryptor$$ExternalSyntheticLambda1 decryptor$$ExternalSyntheticLambda1;
        if (this.secretKey == null) {
            decryptor$$ExternalSyntheticLambda1 = new Supplier() { // from class: org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Decryptor.lambda$getGenericProperties$0();
                }
            };
        } else {
            SecretKey secretKey = this.secretKey;
            secretKey.getClass();
            decryptor$$ExternalSyntheticLambda1 = new Decryptor$$ExternalSyntheticLambda1(secretKey);
        }
        return GenericRecordUtil.getGenericProperties("secretKey", decryptor$$ExternalSyntheticLambda1, "verifier", new Supplier() { // from class: org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Decryptor.this.getVerifier();
            }
        }, "integrityHmacKey", new Supplier() { // from class: org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Decryptor.this.getIntegrityHmacKey();
            }
        }, "integrityHmacValue", new Supplier() { // from class: org.apache.poi.poifs.crypt.Decryptor$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Decryptor.this.getIntegrityHmacValue();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }
}
