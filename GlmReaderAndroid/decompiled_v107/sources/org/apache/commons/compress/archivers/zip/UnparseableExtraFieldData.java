package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;

/* loaded from: classes9.dex */
public final class UnparseableExtraFieldData implements ZipExtraField {
    private static final ZipShort HEADER_ID = new ZipShort(44225);
    private byte[] centralDirectoryData;
    private byte[] localFileData;

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return this.centralDirectoryData == null ? getLocalFileDataData() : ZipUtil.copy(this.centralDirectoryData);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return this.centralDirectoryData == null ? getLocalFileDataLength() : new ZipShort(this.centralDirectoryData.length);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        return ZipUtil.copy(this.localFileData);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return ZipShort.lengthOf(this.localFileData);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) {
        this.centralDirectoryData = Arrays.copyOfRange(buffer, offset, offset + length);
        if (this.localFileData == null) {
            parseFromLocalFileData(buffer, offset, length);
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) {
        this.localFileData = Arrays.copyOfRange(buffer, offset, offset + length);
    }
}
