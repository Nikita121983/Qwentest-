package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.function.Supplier;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public abstract class Encryptor implements GenericRecord {
    protected static final String DEFAULT_POIFS_ENTRY = "EncryptedPackage";
    private EncryptionInfo encryptionInfo;
    private SecretKey secretKey;

    public abstract void confirmPassword(String str);

    public abstract void confirmPassword(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5);

    public abstract Encryptor copy();

    public abstract OutputStream getDataStream(DirectoryNode directoryNode) throws IOException, GeneralSecurityException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Encryptor() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Encryptor(Encryptor other) {
        this.encryptionInfo = other.encryptionInfo;
        this.secretKey = other.secretKey;
    }

    public static Encryptor getInstance(EncryptionInfo info) {
        return info.getEncryptor();
    }

    public OutputStream getDataStream(POIFSFileSystem fs) throws IOException, GeneralSecurityException {
        return getDataStream(fs.getRoot());
    }

    public ChunkedCipherOutputStream getDataStream(OutputStream stream, int initialOffset) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("this decryptor doesn't support writing directly to a stream");
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public void setEncryptionInfo(EncryptionInfo encryptionInfo) {
        this.encryptionInfo = encryptionInfo;
    }

    public void setChunkSize(int chunkSize) {
        throw new EncryptedDocumentException("this decryptor doesn't support changing the chunk size");
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Supplier decryptor$$ExternalSyntheticLambda1;
        if (this.secretKey == null) {
            decryptor$$ExternalSyntheticLambda1 = new Supplier() { // from class: org.apache.poi.poifs.crypt.Encryptor$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Encryptor.lambda$getGenericProperties$0();
                }
            };
        } else {
            SecretKey secretKey = this.secretKey;
            secretKey.getClass();
            decryptor$$ExternalSyntheticLambda1 = new Decryptor$$ExternalSyntheticLambda1(secretKey);
        }
        return GenericRecordUtil.getGenericProperties("secretKey", decryptor$$ExternalSyntheticLambda1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }
}
