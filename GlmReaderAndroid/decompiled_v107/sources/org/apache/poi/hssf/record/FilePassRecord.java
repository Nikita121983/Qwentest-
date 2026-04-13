package org.apache.poi.hssf.record;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionHeader;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionVerifier;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionHeader;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionVerifier;
import org.apache.poi.poifs.crypt.xor.XOREncryptionHeader;
import org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.LittleEndianOutputStream;

/* loaded from: classes10.dex */
public final class FilePassRecord extends StandardRecord {
    private static final int ENCRYPTION_OTHER = 1;
    private static final int ENCRYPTION_XOR = 0;
    public static final short sid = 47;
    private final EncryptionInfo encryptionInfo;
    private final int encryptionType;

    private FilePassRecord(FilePassRecord other) {
        super(other);
        this.encryptionType = other.encryptionType;
        this.encryptionInfo = other.encryptionInfo.copy();
    }

    public FilePassRecord(EncryptionMode encryptionMode) {
        this.encryptionType = encryptionMode == EncryptionMode.xor ? 0 : 1;
        this.encryptionInfo = new EncryptionInfo(encryptionMode);
    }

    public FilePassRecord(RecordInputStream in) {
        EncryptionMode preferredMode;
        this.encryptionType = in.readUShort();
        switch (this.encryptionType) {
            case 0:
                preferredMode = EncryptionMode.xor;
                break;
            case 1:
                preferredMode = EncryptionMode.cryptoAPI;
                break;
            default:
                throw new EncryptedDocumentException("invalid encryption type");
        }
        try {
            this.encryptionInfo = new EncryptionInfo(in, preferredMode);
        } catch (IOException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.encryptionType);
        byte[] data = new byte[1024];
        try {
            LittleEndianByteArrayOutputStream bos = new LittleEndianByteArrayOutputStream(data, 0);
            try {
                switch (this.encryptionInfo.getEncryptionMode()) {
                    case xor:
                        ((XOREncryptionHeader) this.encryptionInfo.getHeader()).write(bos);
                        ((XOREncryptionVerifier) this.encryptionInfo.getVerifier()).write(bos);
                        break;
                    case binaryRC4:
                        out.writeShort(this.encryptionInfo.getVersionMajor());
                        out.writeShort(this.encryptionInfo.getVersionMinor());
                        ((BinaryRC4EncryptionHeader) this.encryptionInfo.getHeader()).write(bos);
                        ((BinaryRC4EncryptionVerifier) this.encryptionInfo.getVerifier()).write(bos);
                        break;
                    case cryptoAPI:
                        out.writeShort(this.encryptionInfo.getVersionMajor());
                        out.writeShort(this.encryptionInfo.getVersionMinor());
                        out.writeInt(this.encryptionInfo.getEncryptionFlags());
                        ((CryptoAPIEncryptionHeader) this.encryptionInfo.getHeader()).write(bos);
                        ((CryptoAPIEncryptionVerifier) this.encryptionInfo.getVerifier()).write(bos);
                        break;
                    default:
                        throw new EncryptedDocumentException("not supported");
                }
                out.write(data, 0, bos.getWriteIndex());
                bos.close();
            } finally {
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        LittleEndianOutputStream leos = new LittleEndianOutputStream(bos);
        serialize(leos);
        return bos.size();
    }

    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 47;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FilePassRecord copy() {
        return new FilePassRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FILE_PASS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.FilePassRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FilePassRecord.this.m2306xc22a03fd();
            }
        }, "encryptionInfo", new Supplier() { // from class: org.apache.poi.hssf.record.FilePassRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FilePassRecord.this.getEncryptionInfo();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FilePassRecord, reason: not valid java name */
    public /* synthetic */ Object m2306xc22a03fd() {
        return Integer.valueOf(this.encryptionType);
    }
}
