package org.apache.commons.compress.archivers.cpio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.io.file.attribute.FileTimes;

/* loaded from: classes9.dex */
public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
    private final int alignmentBoundary;
    private long chksum;
    private final short fileFormat;
    private long fileSize;
    private long gid;
    private final int headerSize;
    private long inode;
    private long maj;
    private long min;
    private long mode;
    private long mtime;
    private String name;
    private long nlink;
    private long rmaj;
    private long rmin;
    private long uid;

    public CpioArchiveEntry(File inputFile, String entryName) {
        this((short) 1, inputFile, entryName);
    }

    public CpioArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        this((short) 1, inputPath, entryName, options);
    }

    public CpioArchiveEntry(short format) {
        switch (format) {
            case 1:
                this.headerSize = 110;
                this.alignmentBoundary = 4;
                break;
            case 2:
                this.headerSize = 110;
                this.alignmentBoundary = 4;
                break;
            case 4:
                this.headerSize = 76;
                this.alignmentBoundary = 0;
                break;
            case 8:
                this.headerSize = 26;
                this.alignmentBoundary = 2;
                break;
            default:
                throw new IllegalArgumentException("Unknown header type " + ((int) format));
        }
        this.fileFormat = format;
    }

    public CpioArchiveEntry(short format, File inputFile, String entryName) {
        this(format, entryName, inputFile.isFile() ? inputFile.length() : 0L);
        if (inputFile.isDirectory()) {
            setMode(16384L);
        } else if (inputFile.isFile()) {
            setMode(32768L);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + inputFile.getName());
        }
        setTime(inputFile.lastModified() / 1000);
    }

    public CpioArchiveEntry(short format, Path inputPath, String entryName, LinkOption... options) throws IOException {
        this(format, entryName, Files.isRegularFile(inputPath, options) ? Files.size(inputPath) : 0L);
        if (Files.isDirectory(inputPath, options)) {
            setMode(16384L);
        } else if (Files.isRegularFile(inputPath, options)) {
            setMode(32768L);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + inputPath);
        }
        setTime(Files.getLastModifiedTime(inputPath, options));
    }

    public CpioArchiveEntry(short format, String name) {
        this(format);
        this.name = name;
    }

    public CpioArchiveEntry(short format, String name, long size) {
        this(format, name);
        setSize(size);
    }

    public CpioArchiveEntry(String name) {
        this((short) 1, name);
    }

    public CpioArchiveEntry(String name, long size) {
        this(name);
        setSize(size);
    }

    private void checkNewFormat() {
        if ((this.fileFormat & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    private void checkOldFormat() {
        if ((this.fileFormat & 12) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CpioArchiveEntry other = (CpioArchiveEntry) obj;
        return Objects.equals(this.name, other.name);
    }

    public int getAlignmentBoundary() {
        return this.alignmentBoundary;
    }

    public long getChksum() {
        checkNewFormat();
        return this.chksum & 4294967295L;
    }

    public int getDataPadCount() {
        if (this.alignmentBoundary == 0) {
            return 0;
        }
        long size = this.fileSize;
        int remain = (int) (size % this.alignmentBoundary);
        if (remain > 0) {
            return this.alignmentBoundary - remain;
        }
        return 0;
    }

    public long getDevice() {
        checkOldFormat();
        return this.min;
    }

    public long getDeviceMaj() {
        checkNewFormat();
        return this.maj;
    }

    public long getDeviceMin() {
        checkNewFormat();
        return this.min;
    }

    public short getFormat() {
        return this.fileFormat;
    }

    public long getGID() {
        return this.gid;
    }

    @Deprecated
    public int getHeaderPadCount() {
        return getHeaderPadCount((Charset) null);
    }

    public int getHeaderPadCount(Charset charset) {
        if (this.name == null) {
            return 0;
        }
        if (charset == null) {
            return getHeaderPadCount(this.name.length());
        }
        return getHeaderPadCount(this.name.getBytes(charset).length);
    }

    public int getHeaderPadCount(long nameSize) {
        if (this.alignmentBoundary == 0) {
            return 0;
        }
        int size = this.headerSize + 1;
        if (this.name != null) {
            size = ExactMath.add(size, nameSize);
        }
        int remain = size % this.alignmentBoundary;
        if (remain > 0) {
            return this.alignmentBoundary - remain;
        }
        return 0;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public long getInode() {
        return this.inode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return new Date(getTime() * 1000);
    }

    public long getMode() {
        if (this.mode != 0 || CpioConstants.CPIO_TRAILER.equals(this.name)) {
            return this.mode;
        }
        return 32768L;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name;
    }

    public long getNumberOfLinks() {
        return this.nlink == 0 ? isDirectory() ? 2L : 1L : this.nlink;
    }

    public long getRemoteDevice() {
        checkOldFormat();
        return this.rmin;
    }

    public long getRemoteDeviceMaj() {
        checkNewFormat();
        return this.rmaj;
    }

    public long getRemoteDeviceMin() {
        checkNewFormat();
        return this.rmin;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.fileSize;
    }

    public long getTime() {
        return this.mtime;
    }

    public long getUID() {
        return this.uid;
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    public boolean isBlockDevice() {
        return CpioUtil.fileType(this.mode) == 24576;
    }

    public boolean isCharacterDevice() {
        return CpioUtil.fileType(this.mode) == 8192;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        return CpioUtil.fileType(this.mode) == 16384;
    }

    public boolean isNetwork() {
        return CpioUtil.fileType(this.mode) == 36864;
    }

    public boolean isPipe() {
        return CpioUtil.fileType(this.mode) == 4096;
    }

    public boolean isRegularFile() {
        return CpioUtil.fileType(this.mode) == 32768;
    }

    public boolean isSocket() {
        return CpioUtil.fileType(this.mode) == 49152;
    }

    public boolean isSymbolicLink() {
        return CpioUtil.fileType(this.mode) == 40960;
    }

    public void setChksum(long chksum) {
        checkNewFormat();
        this.chksum = 4294967295L & chksum;
    }

    public void setDevice(long device) {
        checkOldFormat();
        this.min = device;
    }

    public void setDeviceMaj(long maj) {
        checkNewFormat();
        this.maj = maj;
    }

    public void setDeviceMin(long min) {
        checkNewFormat();
        this.min = min;
    }

    public void setGID(long gid) {
        this.gid = gid;
    }

    public void setInode(long inode) {
        this.inode = inode;
    }

    public void setMode(long mode) {
        long maskedMode = 61440 & mode;
        switch ((int) maskedMode) {
            case 4096:
            case 8192:
            case 16384:
            case CpioConstants.C_ISBLK /* 24576 */:
            case 32768:
            case CpioConstants.C_ISNWK /* 36864 */:
            case 40960:
            case CpioConstants.C_ISSOCK /* 49152 */:
                this.mode = mode;
                return;
            default:
                throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(mode) + " Masked: " + Long.toHexString(maskedMode));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfLinks(long nlink) {
        this.nlink = nlink;
    }

    public void setRemoteDevice(long device) {
        checkOldFormat();
        this.rmin = device;
    }

    public void setRemoteDeviceMaj(long rmaj) {
        checkNewFormat();
        this.rmaj = rmaj;
    }

    public void setRemoteDeviceMin(long rmin) {
        checkNewFormat();
        this.rmin = rmin;
    }

    public void setSize(long size) {
        if (size < 0 || size > 4294967295L) {
            throw new IllegalArgumentException("Invalid entry size <" + size + ">");
        }
        this.fileSize = size;
    }

    public void setTime(FileTime time) {
        this.mtime = FileTimes.toUnixTime(time);
    }

    public void setTime(long time) {
        this.mtime = time;
    }

    public void setUID(long uid) {
        this.uid = uid;
    }
}
