package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public class X0017_StrongEncryptionHeader extends PKWareExtraHeader {
    static final ZipShort HEADER_ID = new ZipShort(23);
    private PKWareExtraHeader.EncryptionAlgorithm algId;
    private int bitlen;
    private byte[] erdData;
    private int flags;
    private int format;
    private PKWareExtraHeader.HashAlgorithm hashAlg;
    private int hashSize;
    private byte[] ivData;
    private byte[] keyBlob;
    private long rcount;
    private byte[] recipientKeyHash;
    private byte[] vCRC32;
    private byte[] vData;

    public X0017_StrongEncryptionHeader() {
        super(HEADER_ID);
    }

    private void assertDynamicLengthFits(String what, int dynamicLength, int prefixLength, int length) throws ZipException {
        if (prefixLength + dynamicLength > length) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: " + what + StringUtils.SPACE + dynamicLength + " doesn't fit into " + length + " bytes of data at position " + prefixLength);
        }
    }

    public PKWareExtraHeader.EncryptionAlgorithm getEncryptionAlgorithm() {
        return this.algId;
    }

    public PKWareExtraHeader.HashAlgorithm getHashAlgorithm() {
        return this.hashAlg;
    }

    public long getRecordCount() {
        return this.rcount;
    }

    public void parseCentralDirectoryFormat(byte[] data, int offset, int length) throws ZipException {
        assertMinimalLength(12, length);
        this.format = ZipShort.getValue(data, offset);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + 2));
        this.bitlen = ZipShort.getValue(data, offset + 4);
        this.flags = ZipShort.getValue(data, offset + 6);
        this.rcount = ZipLong.getValue(data, offset + 8);
        if (this.rcount > 0) {
            assertMinimalLength(16, length);
            this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + 12));
            this.hashSize = ZipShort.getValue(data, offset + 14);
        }
    }

    public void parseFileFormat(byte[] data, int offset, int length) throws ZipException {
        assertMinimalLength(4, length);
        int ivSize = ZipShort.getValue(data, offset);
        assertDynamicLengthFits("ivSize", ivSize, 4, length);
        assertMinimalLength(offset + 4, ivSize);
        this.ivData = Arrays.copyOfRange(data, offset + 4, ivSize);
        assertMinimalLength(ivSize + 16, length);
        this.format = ZipShort.getValue(data, offset + ivSize + 6);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + ivSize + 8));
        this.bitlen = ZipShort.getValue(data, offset + ivSize + 10);
        this.flags = ZipShort.getValue(data, offset + ivSize + 12);
        int erdSize = ZipShort.getValue(data, offset + ivSize + 14);
        assertDynamicLengthFits("erdSize", erdSize, ivSize + 16, length);
        assertMinimalLength(offset + ivSize + 16, erdSize);
        this.erdData = Arrays.copyOfRange(data, offset + ivSize + 16, erdSize);
        assertMinimalLength(ivSize + 20 + erdSize, length);
        this.rcount = ZipLong.getValue(data, offset + ivSize + 16 + erdSize);
        if (this.rcount == 0) {
            assertMinimalLength(ivSize + 20 + erdSize + 2, length);
            int vSize = ZipShort.getValue(data, offset + ivSize + 20 + erdSize);
            assertDynamicLengthFits("vSize", vSize, ivSize + 22 + erdSize, length);
            if (vSize < 4) {
                throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + vSize + " is too small to hold CRC");
            }
            assertMinimalLength(offset + ivSize + 22 + erdSize, vSize - 4);
            this.vData = Arrays.copyOfRange(data, offset + ivSize + 22 + erdSize, vSize - 4);
            assertMinimalLength(((((offset + ivSize) + 22) + erdSize) + vSize) - 4, 4);
            this.vCRC32 = Arrays.copyOfRange(data, ((((offset + ivSize) + 22) + erdSize) + vSize) - 4, 4);
            return;
        }
        assertMinimalLength(ivSize + 20 + erdSize + 6, length);
        this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + ivSize + 20 + erdSize));
        this.hashSize = ZipShort.getValue(data, offset + ivSize + 22 + erdSize);
        int resize = ZipShort.getValue(data, offset + ivSize + 24 + erdSize);
        if (resize < this.hashSize) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: resize " + resize + " is too small to hold hashSize" + this.hashSize);
        }
        assertDynamicLengthFits("resize", resize, ivSize + 24 + erdSize, length);
        this.recipientKeyHash = Arrays.copyOfRange(data, offset + ivSize + 24 + erdSize, this.hashSize);
        this.keyBlob = Arrays.copyOfRange(data, offset + ivSize + 24 + erdSize + this.hashSize, resize - this.hashSize);
        assertMinimalLength(ivSize + 26 + erdSize + resize + 2, length);
        int vSize2 = ZipShort.getValue(data, offset + ivSize + 26 + erdSize + resize);
        if (vSize2 < 4) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + vSize2 + " is too small to hold CRC");
        }
        assertDynamicLengthFits("vSize", vSize2, ivSize + 22 + erdSize + resize, length);
        this.vData = Arrays.copyOfRange(data, offset + ivSize + 22 + erdSize + resize, vSize2 - 4);
        this.vCRC32 = Arrays.copyOfRange(data, (((((offset + ivSize) + 22) + erdSize) + resize) + vSize2) - 4, 4);
    }

    @Override // org.apache.commons.compress.archivers.zip.PKWareExtraHeader, org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] data, int offset, int length) throws ZipException {
        super.parseFromCentralDirectoryData(data, offset, length);
        parseCentralDirectoryFormat(data, offset, length);
    }

    @Override // org.apache.commons.compress.archivers.zip.PKWareExtraHeader, org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        super.parseFromLocalFileData(data, offset, length);
        parseFileFormat(data, offset, length);
    }
}
