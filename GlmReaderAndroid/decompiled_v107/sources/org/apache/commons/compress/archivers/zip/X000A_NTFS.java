package org.apache.commons.compress.archivers.zip;

import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import java.util.zip.ZipException;
import org.apache.commons.io.file.attribute.FileTimes;

/* loaded from: classes9.dex */
public class X000A_NTFS implements ZipExtraField {
    public static final ZipShort HEADER_ID = new ZipShort(10);
    private static final ZipShort TIME_ATTR_TAG = new ZipShort(1);
    private static final ZipShort TIME_ATTR_SIZE = new ZipShort(24);
    private ZipEightByteInteger modifyTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger accessTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger createTime = ZipEightByteInteger.ZERO;

    private static ZipEightByteInteger dateToZip(Date d) {
        if (d == null) {
            return null;
        }
        return new ZipEightByteInteger(FileTimes.toNtfsTime(d));
    }

    private static ZipEightByteInteger fileTimeToZip(FileTime time) {
        if (time == null) {
            return null;
        }
        return new ZipEightByteInteger(FileTimes.toNtfsTime(time));
    }

    private static Date zipToDate(ZipEightByteInteger z) {
        if (z == null || ZipEightByteInteger.ZERO.equals(z)) {
            return null;
        }
        return FileTimes.ntfsTimeToDate(z.getLongValue());
    }

    private static FileTime zipToFileTime(ZipEightByteInteger z) {
        if (z == null || ZipEightByteInteger.ZERO.equals(z)) {
            return null;
        }
        return FileTimes.ntfsTimeToFileTime(z.getLongValue());
    }

    public boolean equals(Object o) {
        if (!(o instanceof X000A_NTFS)) {
            return false;
        }
        X000A_NTFS xf = (X000A_NTFS) o;
        return Objects.equals(this.modifyTime, xf.modifyTime) && Objects.equals(this.accessTime, xf.accessTime) && Objects.equals(this.createTime, xf.createTime);
    }

    public FileTime getAccessFileTime() {
        return zipToFileTime(this.accessTime);
    }

    public Date getAccessJavaTime() {
        return zipToDate(this.accessTime);
    }

    public ZipEightByteInteger getAccessTime() {
        return this.accessTime;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public FileTime getCreateFileTime() {
        return zipToFileTime(this.createTime);
    }

    public Date getCreateJavaTime() {
        return zipToDate(this.createTime);
    }

    public ZipEightByteInteger getCreateTime() {
        return this.createTime;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[getLocalFileDataLength().getValue()];
        System.arraycopy(TIME_ATTR_TAG.getBytes(), 0, data, 4, 2);
        int pos = 4 + 2;
        System.arraycopy(TIME_ATTR_SIZE.getBytes(), 0, data, pos, 2);
        int pos2 = pos + 2;
        System.arraycopy(this.modifyTime.getBytes(), 0, data, pos2, 8);
        int pos3 = pos2 + 8;
        System.arraycopy(this.accessTime.getBytes(), 0, data, pos3, 8);
        System.arraycopy(this.createTime.getBytes(), 0, data, pos3 + 8, 8);
        return data;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(32);
    }

    public FileTime getModifyFileTime() {
        return zipToFileTime(this.modifyTime);
    }

    public Date getModifyJavaTime() {
        return zipToDate(this.modifyTime);
    }

    public ZipEightByteInteger getModifyTime() {
        return this.modifyTime;
    }

    public int hashCode() {
        int hc = this.modifyTime != null ? (-123) ^ this.modifyTime.hashCode() : -123;
        if (this.accessTime != null) {
            hc ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
        }
        if (this.createTime != null) {
            return hc ^ Integer.rotateLeft(this.createTime.hashCode(), 22);
        }
        return hc;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        reset();
        parseFromLocalFileData(buffer, offset, length);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        int len = offset + length;
        int offset2 = offset + 4;
        while (offset2 + 4 <= len) {
            ZipShort tag = new ZipShort(data, offset2);
            int offset3 = offset2 + 2;
            if (tag.equals(TIME_ATTR_TAG)) {
                readTimeAttr(data, offset3, len - offset3);
                return;
            } else {
                ZipShort size = new ZipShort(data, offset3);
                offset2 = offset3 + size.getValue() + 2;
            }
        }
    }

    private void readTimeAttr(byte[] data, int offset, int length) {
        if (length >= 26) {
            ZipShort tagValueLength = new ZipShort(data, offset);
            if (TIME_ATTR_SIZE.equals(tagValueLength)) {
                int offset2 = offset + 2;
                this.modifyTime = new ZipEightByteInteger(data, offset2);
                int offset3 = offset2 + 8;
                this.accessTime = new ZipEightByteInteger(data, offset3);
                this.createTime = new ZipEightByteInteger(data, offset3 + 8);
            }
        }
    }

    private void reset() {
        this.modifyTime = ZipEightByteInteger.ZERO;
        this.accessTime = ZipEightByteInteger.ZERO;
        this.createTime = ZipEightByteInteger.ZERO;
    }

    public void setAccessFileTime(FileTime time) {
        setAccessTime(fileTimeToZip(time));
    }

    public void setAccessJavaTime(Date d) {
        setAccessTime(dateToZip(d));
    }

    public void setAccessTime(ZipEightByteInteger t) {
        this.accessTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public void setCreateFileTime(FileTime time) {
        setCreateTime(fileTimeToZip(time));
    }

    public void setCreateJavaTime(Date d) {
        setCreateTime(dateToZip(d));
    }

    public void setCreateTime(ZipEightByteInteger t) {
        this.createTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public void setModifyFileTime(FileTime time) {
        setModifyTime(fileTimeToZip(time));
    }

    public void setModifyJavaTime(Date d) {
        setModifyTime(dateToZip(d));
    }

    public void setModifyTime(ZipEightByteInteger t) {
        this.modifyTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public String toString() {
        return "0x000A Zip Extra Field: Modify:[" + getModifyFileTime() + "]  Access:[" + getAccessFileTime() + "]  Create:[" + getCreateFileTime() + "] ";
    }
}
