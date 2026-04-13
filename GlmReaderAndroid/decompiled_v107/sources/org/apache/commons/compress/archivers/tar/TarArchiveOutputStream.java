package org.apache.commons.compress.archivers.tar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Map;
import java.util.function.BiConsumer;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.commons.lang3.ArrayFill;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public class TarArchiveOutputStream extends ArchiveOutputStream<TarArchiveEntry> {
    private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding(StandardCharsets.US_ASCII);
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_POSIX = 2;
    public static final int BIGNUMBER_STAR = 1;
    private static final int BLOCK_SIZE_UNSPECIFIED = -511;
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int LONGFILE_TRUNCATE = 1;
    private static final int RECORD_SIZE = 512;
    private boolean addPaxHeadersForNonAsciiNames;
    private int bigNumberMode;
    final String charsetName;
    private final CountingOutputStream countingOut;
    private long currBytes;
    private String currName;
    private long currSize;
    private boolean haveUnclosedEntry;
    private int longFileMode;
    private final byte[] recordBuf;
    private final int recordsPerBlock;
    private long recordsWritten;
    private final ZipEncoding zipEncoding;

    public TarArchiveOutputStream(OutputStream os) {
        this(os, BLOCK_SIZE_UNSPECIFIED);
    }

    public TarArchiveOutputStream(OutputStream os, int blockSize) {
        this(os, blockSize, (String) null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize) {
        this(os, blockSize, recordSize, null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize, String encoding) {
        this(os, blockSize, encoding);
        if (recordSize != 512) {
            throw new IllegalArgumentException("Tar record size must always be 512 bytes. Attempt to set size of " + recordSize);
        }
    }

    public TarArchiveOutputStream(OutputStream os, int blockSize, String charset) {
        super(os);
        int realBlockSize;
        this.longFileMode = 0;
        this.bigNumberMode = 0;
        if (BLOCK_SIZE_UNSPECIFIED == blockSize) {
            realBlockSize = 512;
        } else {
            realBlockSize = blockSize;
        }
        if (realBlockSize <= 0 || realBlockSize % 512 != 0) {
            throw new IllegalArgumentException("Block size must be a multiple of 512 bytes. Attempt to use set size of " + blockSize);
        }
        CountingOutputStream countingOutputStream = new CountingOutputStream(os);
        this.countingOut = countingOutputStream;
        this.out = new FixedLengthBlockOutputStream(countingOutputStream, 512);
        this.charsetName = Charsets.toCharset(charset).name();
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(charset);
        this.recordBuf = new byte[512];
        this.recordsPerBlock = realBlockSize / 512;
    }

    public TarArchiveOutputStream(OutputStream os, String charset) {
        this(os, BLOCK_SIZE_UNSPECIFIED, charset);
    }

    private void addFileTimePaxHeader(Map<String, String> paxHeaders, String header, FileTime value) {
        if (value != null) {
            Instant instant = value.toInstant();
            long seconds = instant.getEpochSecond();
            int nanos = instant.getNano();
            if (nanos == 0) {
                paxHeaders.put(header, String.valueOf(seconds));
            } else {
                addInstantPaxHeader(paxHeaders, header, seconds, nanos);
            }
        }
    }

    private void addFileTimePaxHeaderForBigNumber(Map<String, String> paxHeaders, String header, FileTime value, long maxValue) {
        if (value != null) {
            Instant instant = value.toInstant();
            long seconds = instant.getEpochSecond();
            int nanos = instant.getNano();
            if (nanos == 0) {
                addPaxHeaderForBigNumber(paxHeaders, header, seconds, maxValue);
            } else {
                addInstantPaxHeader(paxHeaders, header, seconds, nanos);
            }
        }
    }

    private void addInstantPaxHeader(Map<String, String> paxHeaders, String header, long seconds, int nanos) {
        BigDecimal bdSeconds = BigDecimal.valueOf(seconds);
        BigDecimal bdNanos = BigDecimal.valueOf(nanos).movePointLeft(9).setScale(7, RoundingMode.DOWN);
        BigDecimal timestamp = bdSeconds.add(bdNanos);
        paxHeaders.put(header, timestamp.toPlainString());
    }

    private void addPaxHeaderForBigNumber(Map<String, String> paxHeaders, String header, long value, long maxValue) {
        if (value < 0 || value > maxValue) {
            paxHeaders.put(header, String.valueOf(value));
        }
    }

    private void addPaxHeadersForBigNumbers(Map<String, String> paxHeaders, TarArchiveEntry entry) {
        addPaxHeaderForBigNumber(paxHeaders, "size", entry.getSize(), TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(paxHeaders, "gid", entry.getLongGroupId(), TarConstants.MAXID);
        addFileTimePaxHeaderForBigNumber(paxHeaders, "mtime", entry.getLastModifiedTime(), TarConstants.MAXSIZE);
        addFileTimePaxHeader(paxHeaders, "atime", entry.getLastAccessTime());
        if (entry.getStatusChangeTime() != null) {
            addFileTimePaxHeader(paxHeaders, "ctime", entry.getStatusChangeTime());
        } else {
            addFileTimePaxHeader(paxHeaders, "ctime", entry.getCreationTime());
        }
        addPaxHeaderForBigNumber(paxHeaders, "uid", entry.getLongUserId(), TarConstants.MAXID);
        addFileTimePaxHeader(paxHeaders, "LIBARCHIVE.creationtime", entry.getCreationTime());
        addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devmajor", entry.getDevMajor(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devminor", entry.getDevMinor(), TarConstants.MAXID);
        failForBigNumber("mode", entry.getMode(), TarConstants.MAXID);
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!isFinished()) {
                finish();
            }
        } finally {
            super.close();
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void closeArchiveEntry() throws IOException {
        checkFinished();
        if (!this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        ((FixedLengthBlockOutputStream) this.out).flushBlock();
        if (this.currBytes < this.currSize) {
            throw new IOException("Entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
        }
        this.recordsWritten += this.currSize / 512;
        if (0 != this.currSize % 512) {
            this.recordsWritten++;
        }
        this.haveUnclosedEntry = false;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public TarArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        checkFinished();
        return new TarArchiveEntry(inputFile, entryName);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public TarArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        checkFinished();
        return new TarArchiveEntry(inputPath, entryName, options);
    }

    private byte[] encodeExtendedPaxHeadersContents(Map<String, String> headers) {
        final StringWriter w = new StringWriter();
        headers.forEach(new BiConsumer() { // from class: org.apache.commons.compress.archivers.tar.TarArchiveOutputStream$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TarArchiveOutputStream.lambda$encodeExtendedPaxHeadersContents$0(w, (String) obj, (String) obj2);
            }
        });
        return w.toString().getBytes(StandardCharsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$encodeExtendedPaxHeadersContents$0(StringWriter w, String k, String v) {
        int len = k.length() + v.length() + 3 + 2;
        String line = len + StringUtils.SPACE + k + "=" + v + StringUtils.LF;
        int actualLength = line.getBytes(StandardCharsets.UTF_8).length;
        while (len != actualLength) {
            len = actualLength;
            line = len + StringUtils.SPACE + k + "=" + v + StringUtils.LF;
            actualLength = line.getBytes(StandardCharsets.UTF_8).length;
        }
        w.write(line);
    }

    private void failForBigNumber(String field, long value, long maxValue) {
        failForBigNumber(field, value, maxValue, "");
    }

    private void failForBigNumber(String field, long value, long maxValue, String additionalMsg) {
        if (value < 0 || value > maxValue) {
            throw new IllegalArgumentException(field + " '" + value + "' is too big ( > " + maxValue + " )." + additionalMsg);
        }
    }

    private void failForBigNumbers(TarArchiveEntry entry) {
        failForBigNumber("entry size", entry.getSize(), TarConstants.MAXSIZE);
        failForBigNumberWithPosixMessage("group id", entry.getLongGroupId(), TarConstants.MAXID);
        failForBigNumber("last modification time", FileTimes.toUnixTime(entry.getLastModifiedTime()), TarConstants.MAXSIZE);
        failForBigNumber("user id", entry.getLongUserId(), TarConstants.MAXID);
        failForBigNumber("mode", entry.getMode(), TarConstants.MAXID);
        failForBigNumber("major device number", entry.getDevMajor(), TarConstants.MAXID);
        failForBigNumber("minor device number", entry.getDevMinor(), TarConstants.MAXID);
    }

    private void failForBigNumberWithPosixMessage(String field, long value, long maxValue) {
        failForBigNumber(field, value, maxValue, " Use STAR or POSIX extensions to overcome this limit");
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        checkFinished();
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        }
        writeEOFRecord();
        writeEOFRecord();
        padAsNeeded();
        this.out.flush();
        super.finish();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public long getBytesWritten() {
        return this.countingOut.getByteCount();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    @Deprecated
    public int getCount() {
        return (int) getBytesWritten();
    }

    @Deprecated
    public int getRecordSize() {
        return 512;
    }

    private boolean handleLongName(TarArchiveEntry entry, String name, Map<String, String> paxHeaders, String paxHeaderName, byte linkType, String fieldName) throws IOException {
        ByteBuffer encodedName = this.zipEncoding.encode(name);
        int len = encodedName.limit() - encodedName.position();
        if (len >= 100) {
            if (this.longFileMode == 3) {
                paxHeaders.put(paxHeaderName, name);
                return true;
            }
            if (this.longFileMode == 2) {
                TarArchiveEntry longLinkEntry = new TarArchiveEntry(TarConstants.GNU_LONGLINK, linkType);
                longLinkEntry.setSize(len + 1);
                transferModTime(entry, longLinkEntry);
                putArchiveEntry(longLinkEntry);
                write(encodedName.array(), encodedName.arrayOffset(), len);
                write(0);
                closeArchiveEntry();
            } else if (this.longFileMode != 1) {
                throw new IllegalArgumentException(fieldName + " '" + name + "' is too long ( > 100 bytes)");
            }
        }
        return false;
    }

    private void padAsNeeded() throws IOException {
        int start = Math.toIntExact(this.recordsWritten % this.recordsPerBlock);
        if (start != 0) {
            for (int i = start; i < this.recordsPerBlock; i++) {
                writeEOFRecord();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void putArchiveEntry(org.apache.commons.compress.archivers.tar.TarArchiveEntry r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveOutputStream.putArchiveEntry(org.apache.commons.compress.archivers.tar.TarArchiveEntry):void");
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean b) {
        this.addPaxHeadersForNonAsciiNames = b;
    }

    public void setBigNumberMode(int bigNumberMode) {
        this.bigNumberMode = bigNumberMode;
    }

    public void setLongFileMode(int longFileMode) {
        this.longFileMode = longFileMode;
    }

    private boolean shouldBeReplaced(char c) {
        return c == 0 || c == '/' || c == '\\';
    }

    private String stripTo7Bits(String name) {
        int length = name.length();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char stripped = (char) (name.charAt(i) & 127);
            if (shouldBeReplaced(stripped)) {
                result.append("_");
            } else {
                result.append(stripped);
            }
        }
        return result.toString();
    }

    private void transferModTime(TarArchiveEntry from, TarArchiveEntry to) {
        long fromModTimeSeconds = FileTimes.toUnixTime(from.getLastModifiedTime());
        if (fromModTimeSeconds < 0 || fromModTimeSeconds > TarConstants.MAXSIZE) {
            fromModTimeSeconds = 0;
        }
        to.setLastModifiedTime(FileTimes.fromUnixTime(fromModTimeSeconds));
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] wBuf, int wOffset, int numToWrite) throws IOException {
        if (!this.haveUnclosedEntry) {
            throw new IllegalStateException("No current tar entry");
        }
        if (this.currBytes + numToWrite > this.currSize) {
            throw new IOException("Request to write '" + numToWrite + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
        }
        this.out.write(wBuf, wOffset, numToWrite);
        this.currBytes += numToWrite;
    }

    private void writeEOFRecord() throws IOException {
        writeRecord(ArrayFill.fill(this.recordBuf, (byte) 0));
    }

    void writePaxHeaders(TarArchiveEntry entry, String entryName, Map<String, String> headers) throws IOException {
        String name = "./PaxHeaders.X/" + stripTo7Bits(entryName);
        if (name.length() >= 100) {
            name = name.substring(0, 99);
        }
        TarArchiveEntry pex = new TarArchiveEntry(name, TarConstants.LF_PAX_EXTENDED_HEADER_LC);
        transferModTime(entry, pex);
        byte[] data = encodeExtendedPaxHeadersContents(headers);
        pex.setSize(data.length);
        putArchiveEntry(pex);
        write(data);
        closeArchiveEntry();
    }

    private void writeRecord(byte[] record) throws IOException {
        if (record.length != 512) {
            throw new IOException("Record to write has length '" + record.length + "' which is not the record size of '512'");
        }
        this.out.write(record);
        this.recordsWritten++;
    }
}
