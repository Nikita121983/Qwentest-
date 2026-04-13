package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class TarFile implements Closeable {
    private static final int SMALL_BUFFER_SIZE = 256;
    private final SeekableByteChannel archive;
    private final int blockSize;
    private TarArchiveEntry currEntry;
    private final LinkedList<TarArchiveEntry> entries;
    private boolean eof;
    private Map<String, String> globalPaxHeaders;
    private final List<TarArchiveStructSparse> globalSparseHeaders;
    private final boolean lenient;
    private final ByteBuffer recordBuffer;
    private final int recordSize;
    private final byte[] smallBuf;
    private final Map<String, List<InputStream>> sparseInputStreams;
    private final ZipEncoding zipEncoding;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class BoundedTarEntryInputStream extends BoundedArchiveInputStream {
        private final SeekableByteChannel channel;
        private int currentSparseInputStreamIndex;
        private final TarArchiveEntry entry;
        private long entryOffset;

        BoundedTarEntryInputStream(TarArchiveEntry entry, SeekableByteChannel channel) throws IOException {
            super(entry.getDataOffset(), entry.getRealSize());
            if (channel.size() - entry.getSize() < entry.getDataOffset()) {
                throw new IOException("entry size exceeds archive size");
            }
            this.entry = entry;
            this.channel = channel;
        }

        @Override // org.apache.commons.compress.utils.BoundedArchiveInputStream
        protected int read(long pos, ByteBuffer buf) throws IOException {
            int totalRead;
            if (this.entryOffset >= this.entry.getRealSize()) {
                return -1;
            }
            if (this.entry.isSparse()) {
                totalRead = readSparse(this.entryOffset, buf, buf.limit());
            } else {
                totalRead = readArchive(pos, buf);
            }
            if (totalRead == -1) {
                if (buf.array().length > 0) {
                    throw new IOException("Truncated TAR archive");
                }
                TarFile.this.setAtEOF(true);
            } else {
                this.entryOffset += totalRead;
                buf.flip();
            }
            return totalRead;
        }

        private int readArchive(long pos, ByteBuffer buf) throws IOException {
            this.channel.position(pos);
            return this.channel.read(buf);
        }

        private int readSparse(long pos, ByteBuffer buf, int numToRead) throws IOException {
            List<InputStream> entrySparseInputStreams = (List) TarFile.this.sparseInputStreams.get(this.entry.getName());
            if (entrySparseInputStreams == null || entrySparseInputStreams.isEmpty()) {
                return readArchive(this.entry.getDataOffset() + pos, buf);
            }
            if (this.currentSparseInputStreamIndex >= entrySparseInputStreams.size()) {
                return -1;
            }
            InputStream currentInputStream = entrySparseInputStreams.get(this.currentSparseInputStreamIndex);
            byte[] bufArray = new byte[numToRead];
            int readLen = currentInputStream.read(bufArray);
            if (readLen != -1) {
                buf.put(bufArray, 0, readLen);
            }
            if (this.currentSparseInputStreamIndex == entrySparseInputStreams.size() - 1) {
                return readLen;
            }
            if (readLen == -1) {
                this.currentSparseInputStreamIndex++;
                return readSparse(pos, buf, numToRead);
            }
            if (readLen < numToRead) {
                this.currentSparseInputStreamIndex++;
                int readLenOfNext = readSparse(readLen + pos, buf, numToRead - readLen);
                if (readLenOfNext == -1) {
                    return readLen;
                }
                return readLen + readLenOfNext;
            }
            return readLen;
        }
    }

    public TarFile(byte[] content) throws IOException {
        this(new SeekableInMemoryByteChannel(content));
    }

    public TarFile(byte[] content, boolean lenient) throws IOException {
        this(new SeekableInMemoryByteChannel(content), TarConstants.DEFAULT_BLKSIZE, 512, null, lenient);
    }

    public TarFile(byte[] content, String encoding) throws IOException {
        this(new SeekableInMemoryByteChannel(content), TarConstants.DEFAULT_BLKSIZE, 512, encoding, false);
    }

    public TarFile(File archive) throws IOException {
        this(archive.toPath());
    }

    public TarFile(File archive, boolean lenient) throws IOException {
        this(archive.toPath(), lenient);
    }

    public TarFile(File archive, String encoding) throws IOException {
        this(archive.toPath(), encoding);
    }

    public TarFile(Path archivePath) throws IOException {
        this(Files.newByteChannel(archivePath, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, null, false);
    }

    public TarFile(Path archivePath, boolean lenient) throws IOException {
        this(Files.newByteChannel(archivePath, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, null, lenient);
    }

    public TarFile(Path archivePath, String encoding) throws IOException {
        this(Files.newByteChannel(archivePath, new OpenOption[0]), TarConstants.DEFAULT_BLKSIZE, 512, encoding, false);
    }

    public TarFile(SeekableByteChannel content) throws IOException {
        this(content, TarConstants.DEFAULT_BLKSIZE, 512, null, false);
    }

    public TarFile(SeekableByteChannel archive, int blockSize, int recordSize, String encoding, boolean lenient) throws IOException {
        this.smallBuf = new byte[256];
        this.entries = new LinkedList<>();
        this.globalSparseHeaders = new ArrayList();
        this.globalPaxHeaders = new HashMap();
        this.sparseInputStreams = new HashMap();
        this.archive = archive;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.recordSize = recordSize;
        this.recordBuffer = ByteBuffer.allocate(this.recordSize);
        this.blockSize = blockSize;
        this.lenient = lenient;
        while (true) {
            TarArchiveEntry entry = getNextTarEntry();
            if (entry != null) {
                this.entries.add(entry);
            } else {
                return;
            }
        }
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> headers, List<TarArchiveStructSparse> sparseHeaders) throws IOException {
        this.currEntry.updateEntryFromPaxHeaders(headers);
        this.currEntry.setSparseHeaders(sparseHeaders);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void buildSparseInputStreams() throws IOException {
        List<InputStream> streams = new ArrayList<>();
        List<TarArchiveStructSparse> sparseHeaders = this.currEntry.getOrderedSparseHeaders();
        InputStream zeroInputStream = new TarArchiveSparseZeroInputStream();
        long offset = 0;
        long numberOfZeroBytesInSparseEntry = 0;
        for (TarArchiveStructSparse sparseHeader : sparseHeaders) {
            long zeroBlockSize = sparseHeader.getOffset() - offset;
            if (zeroBlockSize < 0) {
                throw new IOException("Corrupted struct sparse detected");
            }
            if (zeroBlockSize > 0) {
                streams.add(((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(zeroInputStream)).setMaxCount(zeroBlockSize)).get());
                numberOfZeroBytesInSparseEntry += zeroBlockSize;
            }
            if (sparseHeader.getNumbytes() > 0) {
                long start = (this.currEntry.getDataOffset() + sparseHeader.getOffset()) - numberOfZeroBytesInSparseEntry;
                if (sparseHeader.getNumbytes() + start < start) {
                    throw new IOException("Unreadable TAR archive, sparse block offset or length too big");
                }
                streams.add(new BoundedSeekableByteChannelInputStream(start, sparseHeader.getNumbytes(), this.archive));
            }
            offset = sparseHeader.getOffset() + sparseHeader.getNumbytes();
        }
        this.sparseInputStreams.put(this.currEntry.getName(), streams);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.archive.close();
    }

    private void consumeRemainderOfLastBlock() throws IOException {
        long bytesReadOfLastBlock = this.archive.position() % this.blockSize;
        if (bytesReadOfLastBlock > 0) {
            repositionForwardBy(this.blockSize - bytesReadOfLastBlock);
        }
    }

    public List<TarArchiveEntry> getEntries() {
        return new ArrayList(this.entries);
    }

    public InputStream getInputStream(TarArchiveEntry entry) throws IOException {
        try {
            return new BoundedTarEntryInputStream(entry, this.archive);
        } catch (RuntimeException ex) {
            throw new IOException("Corrupted TAR archive. Can't read entry", ex);
        }
    }

    private byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream longName = new ByteArrayOutputStream();
        InputStream in = getInputStream(this.currEntry);
        while (true) {
            try {
                int length = in.read(this.smallBuf);
                if (length < 0) {
                    break;
                }
                longName.write(this.smallBuf, 0, length);
            } catch (Throwable th) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (in != null) {
            in.close();
        }
        getNextTarEntry();
        if (this.currEntry == null) {
            return null;
        }
        byte[] longNameData = longName.toByteArray();
        int length2 = longNameData.length;
        while (length2 > 0 && longNameData[length2 - 1] == 0) {
            length2--;
        }
        if (length2 != longNameData.length) {
            return Arrays.copyOf(longNameData, length2);
        }
        return longNameData;
    }

    private TarArchiveEntry getNextTarEntry() throws IOException {
        if (isAtEOF()) {
            return null;
        }
        if (this.currEntry != null) {
            repositionForwardTo(this.currEntry.getDataOffset() + this.currEntry.getSize());
            throwExceptionIfPositionIsNotInArchive();
            skipRecordPadding();
        }
        ByteBuffer headerBuf = getRecord();
        if (headerBuf == null) {
            this.currEntry = null;
            return null;
        }
        try {
            long position = this.archive.position();
            this.currEntry = new TarArchiveEntry(this.globalPaxHeaders, headerBuf.array(), this.zipEncoding, this.lenient, position);
            if (this.currEntry.isGNULongLinkEntry()) {
                byte[] longLinkData = getLongNameData();
                if (longLinkData == null) {
                    return null;
                }
                this.currEntry.setLinkName(this.zipEncoding.decode(longLinkData));
            }
            if (this.currEntry.isGNULongNameEntry()) {
                byte[] longNameData = getLongNameData();
                if (longNameData == null) {
                    return null;
                }
                String name = this.zipEncoding.decode(longNameData);
                this.currEntry.setName(name);
                if (this.currEntry.isDirectory() && !name.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    this.currEntry.setName(name + PackagingURIHelper.FORWARD_SLASH_STRING);
                }
            }
            if (this.currEntry.isGlobalPaxHeader()) {
                readGlobalPaxHeaders();
            }
            try {
                if (this.currEntry.isPaxHeader()) {
                    paxHeaders();
                } else if (!this.globalPaxHeaders.isEmpty()) {
                    applyPaxHeadersToCurrentEntry(this.globalPaxHeaders, this.globalSparseHeaders);
                }
                if (this.currEntry.isOldGNUSparse()) {
                    readOldGNUSparse();
                }
                return this.currEntry;
            } catch (NumberFormatException e) {
                throw new IOException("Error detected parsing the pax header", e);
            }
        } catch (IllegalArgumentException e2) {
            throw new IOException("Error detected parsing the header", e2);
        }
    }

    private ByteBuffer getRecord() throws IOException {
        ByteBuffer headerBuf = readRecord();
        setAtEOF(isEOFRecord(headerBuf));
        if (isAtEOF() && headerBuf != null) {
            tryToConsumeSecondEOFRecord();
            consumeRemainderOfLastBlock();
            return null;
        }
        return headerBuf;
    }

    protected final boolean isAtEOF() {
        return this.eof;
    }

    private boolean isDirectory() {
        return this.currEntry != null && this.currEntry.isDirectory();
    }

    private boolean isEOFRecord(ByteBuffer headerBuf) {
        return headerBuf == null || ArchiveUtils.isArrayZero(headerBuf.array(), this.recordSize);
    }

    private void paxHeaders() throws IOException {
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        InputStream input = getInputStream(this.currEntry);
        try {
            Map<String, String> headers = TarUtils.parsePaxHeaders(input, sparseHeaders, this.globalPaxHeaders, this.currEntry.getSize());
            if (input != null) {
                input.close();
            }
            if (headers.containsKey("GNU.sparse.map")) {
                sparseHeaders = new ArrayList<>(TarUtils.parseFromPAX01SparseHeaders(headers.get("GNU.sparse.map")));
            }
            getNextTarEntry();
            if (this.currEntry == null) {
                throw new IOException("premature end of tar archive. Didn't find any entry after PAX header.");
            }
            applyPaxHeadersToCurrentEntry(headers, sparseHeaders);
            if (this.currEntry.isPaxGNU1XSparse()) {
                input = getInputStream(this.currEntry);
                try {
                    List<TarArchiveStructSparse> sparseHeaders2 = TarUtils.parsePAX1XSparseHeaders(input, this.recordSize);
                    if (input != null) {
                        input.close();
                    }
                    this.currEntry.setSparseHeaders(sparseHeaders2);
                    this.currEntry.setDataOffset(this.currEntry.getDataOffset() + this.recordSize);
                } finally {
                }
            }
            buildSparseInputStreams();
        } finally {
        }
    }

    private void readGlobalPaxHeaders() throws IOException {
        InputStream input = getInputStream(this.currEntry);
        try {
            this.globalPaxHeaders = TarUtils.parsePaxHeaders(input, this.globalSparseHeaders, this.globalPaxHeaders, this.currEntry.getSize());
            if (input != null) {
                input.close();
            }
            getNextTarEntry();
            if (this.currEntry == null) {
                throw new IOException("Error detected parsing the pax header");
            }
        } catch (Throwable th) {
            if (input != null) {
                try {
                    input.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0041, code lost:
    
        throw new java.io.IOException("premature end of tar archive. Didn't find extended_header after header with extended flag.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0042, code lost:
    
        buildSparseInputStreams();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0006, code lost:
    
        if (r7.currEntry.isExtended() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
    
        r0 = getRecord();
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r1 = new org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry(r0.array());
        r7.currEntry.getSparseHeaders().addAll(r1.getSparseHeaders());
        r7.currEntry.setDataOffset(r7.currEntry.getDataOffset() + r7.recordSize);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0037, code lost:
    
        if (r1.isExtended() != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readOldGNUSparse() throws java.io.IOException {
        /*
            r7 = this;
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r0 = r7.currEntry
            boolean r0 = r0.isExtended()
            if (r0 == 0) goto L42
        L8:
            java.nio.ByteBuffer r0 = r7.getRecord()
            if (r0 == 0) goto L3a
            org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry r1 = new org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry
            byte[] r2 = r0.array()
            r1.<init>(r2)
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r2 = r7.currEntry
            java.util.List r2 = r2.getSparseHeaders()
            java.util.List r3 = r1.getSparseHeaders()
            r2.addAll(r3)
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r2 = r7.currEntry
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r3 = r7.currEntry
            long r3 = r3.getDataOffset()
            int r5 = r7.recordSize
            long r5 = (long) r5
            long r3 = r3 + r5
            r2.setDataOffset(r3)
            boolean r0 = r1.isExtended()
            if (r0 != 0) goto L8
            goto L42
        L3a:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "premature end of tar archive. Didn't find extended_header after header with extended flag."
            r1.<init>(r2)
            throw r1
        L42:
            r7.buildSparseInputStreams()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarFile.readOldGNUSparse():void");
    }

    private ByteBuffer readRecord() throws IOException {
        this.recordBuffer.rewind();
        int readNow = this.archive.read(this.recordBuffer);
        if (readNow != this.recordSize) {
            return null;
        }
        return this.recordBuffer;
    }

    private void repositionForwardBy(long offset) throws IOException {
        repositionForwardTo(this.archive.position() + offset);
    }

    private void repositionForwardTo(long newPosition) throws IOException {
        long currPosition = this.archive.position();
        if (newPosition < currPosition) {
            throw new IOException("trying to move backwards inside of the archive");
        }
        this.archive.position(newPosition);
    }

    protected final void setAtEOF(boolean eof) {
        this.eof = eof;
    }

    private void skipRecordPadding() throws IOException {
        if (!isDirectory() && this.currEntry.getSize() > 0 && this.currEntry.getSize() % this.recordSize != 0) {
            long numRecords = (this.currEntry.getSize() / this.recordSize) + 1;
            long padding = (this.recordSize * numRecords) - this.currEntry.getSize();
            repositionForwardBy(padding);
            throwExceptionIfPositionIsNotInArchive();
        }
    }

    private void throwExceptionIfPositionIsNotInArchive() throws IOException {
        if (this.archive.size() < this.archive.position()) {
            throw new IOException("Truncated TAR archive");
        }
    }

    private void tryToConsumeSecondEOFRecord() throws IOException {
        boolean shouldReset = true;
        try {
            shouldReset = !isEOFRecord(readRecord());
        } finally {
            if (shouldReset) {
                this.archive.position(this.archive.position() - this.recordSize);
            }
        }
    }
}
