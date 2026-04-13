package org.apache.commons.compress.archivers.arj;

import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class ArjArchiveEntry implements ArchiveEntry {
    private final LocalFileHeader localFileHeader;

    /* loaded from: classes9.dex */
    public static class HostOs {
        public static final int AMIGA = 3;
        public static final int APPLE_GS = 6;
        public static final int ATARI_ST = 7;
        public static final int DOS = 0;
        public static final int MAC_OS = 4;
        public static final int NEXT = 8;
        public static final int OS_2 = 5;
        public static final int PRIMOS = 1;
        public static final int UNIX = 2;
        public static final int VAX_VMS = 9;
        public static final int WIN32 = 11;
        public static final int WIN95 = 10;

        @Deprecated
        public HostOs() {
        }
    }

    public ArjArchiveEntry() {
        this.localFileHeader = new LocalFileHeader();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArjArchiveEntry(LocalFileHeader localFileHeader) {
        this.localFileHeader = localFileHeader;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArjArchiveEntry other = (ArjArchiveEntry) obj;
        return this.localFileHeader.equals(other.localFileHeader);
    }

    public int getHostOs() {
        return this.localFileHeader.hostOS;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        long ts = isHostOsUnix() ? this.localFileHeader.dateTimeModified * 1000 : ZipUtil.dosToJavaTime(this.localFileHeader.dateTimeModified & 4294967295L);
        return new Date(ts);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMethod() {
        return this.localFileHeader.method;
    }

    public int getMode() {
        return this.localFileHeader.fileAccessMode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        if ((this.localFileHeader.arjFlags & 16) != 0) {
            return this.localFileHeader.name.replace(PackagingURIHelper.FORWARD_SLASH_STRING, File.separator);
        }
        return this.localFileHeader.name;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.localFileHeader.originalSize;
    }

    public int getUnixMode() {
        if (isHostOsUnix()) {
            return getMode();
        }
        return 0;
    }

    public int hashCode() {
        String name = getName();
        if (name == null) {
            return 0;
        }
        return name.hashCode();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        return this.localFileHeader.fileType == 3;
    }

    public boolean isHostOsUnix() {
        return getHostOs() == 2 || getHostOs() == 8;
    }
}
