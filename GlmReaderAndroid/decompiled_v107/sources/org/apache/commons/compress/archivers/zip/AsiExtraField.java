package org.apache.commons.compress.archivers.zip;

import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* loaded from: classes9.dex */
public class AsiExtraField implements ZipExtraField, UnixStat, Cloneable {
    static final ZipShort HEADER_ID = new ZipShort(30062);
    private static final int MIN_SIZE = 14;
    private boolean dirFlag;
    private int gid;
    private int mode;
    private int uid;
    private String link = "";
    private CRC32 crc = new CRC32();

    public Object clone() {
        try {
            AsiExtraField cloned = (AsiExtraField) super.clone();
            cloned.crc = new CRC32();
            return cloned;
        } catch (CloneNotSupportedException cnfe) {
            throw new UnsupportedOperationException(cnfe);
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public int getGroupId() {
        return this.gid;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    public String getLinkedFile() {
        return this.link;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[getLocalFileDataLength().getValue() - 4];
        System.arraycopy(ZipShort.getBytes(getMode()), 0, data, 0, 2);
        byte[] linkArray = getLinkedFile().getBytes(Charset.defaultCharset());
        System.arraycopy(ZipLong.getBytes(linkArray.length), 0, data, 2, 4);
        System.arraycopy(ZipShort.getBytes(getUserId()), 0, data, 6, 2);
        System.arraycopy(ZipShort.getBytes(getGroupId()), 0, data, 8, 2);
        System.arraycopy(linkArray, 0, data, 10, linkArray.length);
        this.crc.reset();
        this.crc.update(data);
        long checksum = this.crc.getValue();
        byte[] result = new byte[data.length + 4];
        System.arraycopy(ZipLong.getBytes(checksum), 0, result, 0, 4);
        System.arraycopy(data, 0, result, 4, data.length);
        return result;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(getLinkedFile().getBytes(Charset.defaultCharset()).length + 14);
    }

    public int getMode() {
        return this.mode;
    }

    protected int getMode(int mode) {
        int type = 32768;
        if (isLink()) {
            type = 40960;
        } else if (isDirectory()) {
            type = 16384;
        }
        return (mode & 4095) | type;
    }

    public int getUserId() {
        return this.uid;
    }

    public boolean isDirectory() {
        return this.dirFlag && !isLink();
    }

    public boolean isLink() {
        return !getLinkedFile().isEmpty();
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        parseFromLocalFileData(buffer, offset, length);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        if (length < 14) {
            throw new ZipException("The length is too short, only " + length + " bytes, expected at least 14");
        }
        long givenChecksum = ZipLong.getValue(data, offset);
        byte[] tmp = new byte[length - 4];
        System.arraycopy(data, offset + 4, tmp, 0, length - 4);
        this.crc.reset();
        this.crc.update(tmp);
        long realChecksum = this.crc.getValue();
        if (givenChecksum != realChecksum) {
            throw new ZipException("Bad CRC checksum, expected " + Long.toHexString(givenChecksum) + " instead of " + Long.toHexString(realChecksum));
        }
        int newMode = ZipShort.getValue(tmp, 0);
        int linkArrayLength = (int) ZipLong.getValue(tmp, 2);
        if (linkArrayLength < 0 || linkArrayLength > tmp.length - 10) {
            throw new ZipException("Bad symbolic link name length " + linkArrayLength + " in ASI extra field");
        }
        this.uid = ZipShort.getValue(tmp, 6);
        this.gid = ZipShort.getValue(tmp, 8);
        if (linkArrayLength == 0) {
            this.link = "";
        } else {
            byte[] linkArray = new byte[linkArrayLength];
            System.arraycopy(tmp, 10, linkArray, 0, linkArrayLength);
            this.link = new String(linkArray, Charset.defaultCharset());
        }
        setDirectory((newMode & 16384) != 0);
        setMode(newMode);
    }

    public void setDirectory(boolean dirFlag) {
        this.dirFlag = dirFlag;
        this.mode = getMode(this.mode);
    }

    public void setGroupId(int gid) {
        this.gid = gid;
    }

    public void setLinkedFile(String name) {
        this.link = name;
        this.mode = getMode(this.mode);
    }

    public void setMode(int mode) {
        this.mode = getMode(mode);
    }

    public void setUserId(int uid) {
        this.uid = uid;
    }
}
