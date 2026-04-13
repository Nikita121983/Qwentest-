package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

/* loaded from: classes9.dex */
public class ArArchiveOutputStream extends ArchiveOutputStream<ArArchiveEntry> {
    public static final int LONGFILE_BSD = 1;
    public static final int LONGFILE_ERROR = 0;
    private static final char PAD = '\n';
    private static final char SPACE = ' ';
    private long entryOffset;
    private int headerPlus;
    private int longFileMode;
    private ArArchiveEntry prevEntry;
    private boolean prevEntryOpen;

    public ArArchiveOutputStream(OutputStream out) {
        super(out);
        this.longFileMode = 0;
    }

    private String checkLength(String value, int max, String name) throws IOException {
        if (value.length() > max) {
            throw new IOException(name + " too long");
        }
        return value;
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!isFinished()) {
                finish();
            }
        } finally {
            this.prevEntry = null;
            super.close();
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        checkFinished();
        if (this.prevEntry == null || !this.prevEntryOpen) {
            throw new IOException("No current entry to close");
        }
        if ((this.headerPlus + this.entryOffset) % 2 != 0) {
            this.out.write(10);
        }
        this.prevEntryOpen = false;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        checkFinished();
        return new ArArchiveEntry(inputFile, entryName);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public ArArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        checkFinished();
        return new ArArchiveEntry(inputPath, entryName, options);
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        if (this.prevEntryOpen) {
            throw new IOException("This archive contains unclosed entries.");
        }
        checkFinished();
        super.finish();
    }

    private int pad(int offset, int newOffset, char fill) throws IOException {
        int diff = newOffset - offset;
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                write(fill);
            }
        }
        return newOffset;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(ArArchiveEntry entry) throws IOException {
        checkFinished();
        if (this.prevEntry == null) {
            writeArchiveHeader();
        } else {
            if (this.prevEntry.getLength() != this.entryOffset) {
                throw new IOException("Length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset);
            }
            if (this.prevEntryOpen) {
                closeArchiveEntry();
            }
        }
        this.prevEntry = entry;
        this.headerPlus = writeEntryHeader(entry);
        this.entryOffset = 0L;
        this.prevEntryOpen = true;
    }

    public void setLongFileMode(int longFileMode) {
        this.longFileMode = longFileMode;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        count(len);
        this.entryOffset += len;
    }

    private int write(String data) throws IOException {
        return writeUsAscii(data).length;
    }

    private byte[] writeArchiveHeader() throws IOException {
        return writeUsAscii(ArArchiveEntry.HEADER);
    }

    private int writeEntryHeader(ArArchiveEntry entry) throws IOException {
        int offset;
        boolean appendName = false;
        String eName = entry.getName();
        int nLength = eName.length();
        if (this.longFileMode == 0 && nLength > 16) {
            throw new IOException("File name too long, > 16 chars: " + eName);
        }
        if (1 != this.longFileMode || (nLength <= 16 && eName.indexOf(32) <= -1)) {
            offset = 0 + write(eName);
        } else {
            appendName = true;
            String fileNameLen = "#1/" + nLength;
            if (fileNameLen.length() <= 16) {
                offset = 0 + write(fileNameLen);
            } else {
                throw new IOException("File length too long, > 16 chars: " + eName);
            }
        }
        int offset2 = pad(pad(pad(pad(pad(pad(offset, 16, ' ') + write(checkLength(String.valueOf(entry.getLastModified()), 12, "Last modified")), 28, ' ') + write(checkLength(String.valueOf(entry.getUserId()), 6, "User ID")), 34, ' ') + write(checkLength(String.valueOf(entry.getGroupId()), 6, "Group ID")), 40, ' ') + write(checkLength(String.valueOf(Integer.toString(entry.getMode(), 8)), 8, "File mode")), 48, ' ') + write(checkLength(String.valueOf(entry.getLength() + (appendName ? nLength : 0)), 10, "Size")), 58, ' ') + write(ArArchiveEntry.TRAILER);
        if (appendName) {
            return offset2 + write(eName);
        }
        return offset2;
    }
}
