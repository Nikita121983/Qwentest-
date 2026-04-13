package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipException;

/* loaded from: classes9.dex */
public abstract class PKWareExtraHeader implements ZipExtraField {
    private byte[] centralData;
    private final ZipShort headerId;
    private byte[] localData;

    /* loaded from: classes9.dex */
    public enum EncryptionAlgorithm {
        DES(26113),
        RC2pre52(26114),
        TripleDES168(26115),
        TripleDES192(26121),
        AES128(26126),
        AES192(26127),
        AES256(26128),
        RC2(26370),
        RC4(26625),
        UNKNOWN(65535);

        private static final Map<Integer, EncryptionAlgorithm> codeToEnum;
        private final int code;

        static {
            Map<Integer, EncryptionAlgorithm> cte = new HashMap<>();
            for (EncryptionAlgorithm method : values()) {
                cte.put(Integer.valueOf(method.getCode()), method);
            }
            codeToEnum = Collections.unmodifiableMap(cte);
        }

        public static EncryptionAlgorithm getAlgorithmByCode(int code) {
            return codeToEnum.get(Integer.valueOf(code));
        }

        EncryptionAlgorithm(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    /* loaded from: classes9.dex */
    public enum HashAlgorithm {
        NONE(0),
        CRC32(1),
        MD5(32771),
        SHA1(32772),
        RIPEND160(32775),
        SHA256(32780),
        SHA384(32781),
        SHA512(32782);

        private static final Map<Integer, HashAlgorithm> codeToEnum;
        private final int code;

        static {
            Map<Integer, HashAlgorithm> cte = new HashMap<>();
            for (HashAlgorithm method : values()) {
                cte.put(Integer.valueOf(method.getCode()), method);
            }
            codeToEnum = Collections.unmodifiableMap(cte);
        }

        public static HashAlgorithm getAlgorithmByCode(int code) {
            return codeToEnum.get(Integer.valueOf(code));
        }

        HashAlgorithm(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PKWareExtraHeader(ZipShort headerId) {
        this.headerId = headerId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void assertMinimalLength(int minimum, int length) throws ZipException {
        if (length < minimum) {
            throw new ZipException(getClass().getName() + " is too short, only " + length + " bytes, expected at least " + minimum);
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        if (this.centralData != null) {
            return ZipUtil.copy(this.centralData);
        }
        return getLocalFileDataData();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        if (this.centralData != null) {
            return new ZipShort(this.centralData.length);
        }
        return getLocalFileDataLength();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return this.headerId;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        return ZipUtil.copy(this.localData);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return ZipShort.lengthOf(this.localData);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] data, int offset, int length) throws ZipException {
        byte[] tmp = Arrays.copyOfRange(data, offset, offset + length);
        setCentralDirectoryData(tmp);
        if (this.localData == null) {
            setLocalFileDataData(tmp);
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        setLocalFileDataData(Arrays.copyOfRange(data, offset, offset + length));
    }

    public void setCentralDirectoryData(byte[] data) {
        this.centralData = ZipUtil.copy(data);
    }

    public void setLocalFileDataData(byte[] data) {
        this.localData = ZipUtil.copy(data);
    }
}
