package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class TarArchiveInputStream extends ArchiveInputStream<TarArchiveEntry> {
    private static final int SMALL_BUFFER_SIZE = 256;
    private static final String VERSION_AIX = "0 ";
    private boolean atEof;
    private final int blockSize;
    private TarArchiveEntry currEntry;
    private int currentSparseInputStreamIndex;
    private long entryOffset;
    private long entrySize;
    private Map<String, String> globalPaxHeaders;
    private final List<TarArchiveStructSparse> globalSparseHeaders;
    private final boolean lenient;
    private final byte[] recordBuffer;
    private final byte[] smallBuf;
    private List<InputStream> sparseInputStreams;
    private final ZipEncoding zipEncoding;

    public static boolean matches(byte[] signature, int length) {
        if (length < 265) {
            return false;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_POSIX, signature, TarConstants.VERSION_OFFSET, 2)) {
            return true;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer(VERSION_AIX, signature, TarConstants.VERSION_OFFSET, 2)) {
            return true;
        }
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, signature, 257, 6) && (ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_SPACE, signature, TarConstants.VERSION_OFFSET, 2) || ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_ZERO, signature, TarConstants.VERSION_OFFSET, 2))) {
            return true;
        }
        return ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_ANT, signature, TarConstants.VERSION_OFFSET, 2);
    }

    public TarArchiveInputStream(InputStream inputStream) {
        this(inputStream, TarConstants.DEFAULT_BLKSIZE, 512);
    }

    public TarArchiveInputStream(InputStream inputStream, boolean lenient) {
        this(inputStream, TarConstants.DEFAULT_BLKSIZE, 512, null, lenient);
    }

    public TarArchiveInputStream(InputStream inputStream, int blockSize) {
        this(inputStream, blockSize, 512);
    }

    public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize) {
        this(inputStream, blockSize, recordSize, null);
    }

    public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize, String encoding) {
        this(inputStream, blockSize, recordSize, encoding, false);
    }

    public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize, String encoding, boolean lenient) {
        super(inputStream, encoding);
        this.smallBuf = new byte[256];
        this.globalPaxHeaders = new HashMap();
        this.globalSparseHeaders = new ArrayList();
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.recordBuffer = new byte[recordSize];
        this.blockSize = blockSize;
        this.lenient = lenient;
    }

    public TarArchiveInputStream(InputStream inputStream, int blockSize, String encoding) {
        this(inputStream, blockSize, 512, encoding);
    }

    public TarArchiveInputStream(InputStream inputStream, String encoding) {
        this(inputStream, TarConstants.DEFAULT_BLKSIZE, 512, encoding);
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> headers, List<TarArchiveStructSparse> sparseHeaders) throws IOException {
        this.currEntry.updateEntryFromPaxHeaders(headers);
        this.currEntry.setSparseHeaders(sparseHeaders);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (isDirectory()) {
            return 0;
        }
        long available = this.currEntry.getRealSize() - this.entryOffset;
        if (available > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) available;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void buildSparseInputStreams() throws IOException {
        this.currentSparseInputStreamIndex = -1;
        this.sparseInputStreams = new ArrayList();
        List<TarArchiveStructSparse> sparseHeaders = this.currEntry.getOrderedSparseHeaders();
        InputStream zeroInputStream = new TarArchiveSparseZeroInputStream();
        long offset = 0;
        for (TarArchiveStructSparse sparseHeader : sparseHeaders) {
            long zeroBlockSize = sparseHeader.getOffset() - offset;
            if (zeroBlockSize < 0) {
                throw new IOException("Corrupted struct sparse detected");
            }
            if (zeroBlockSize > 0) {
                this.sparseInputStreams.add(((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(zeroInputStream)).setMaxCount(sparseHeader.getOffset() - offset)).get());
            }
            if (sparseHeader.getNumbytes() > 0) {
                this.sparseInputStreams.add(((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.in)).setMaxCount(sparseHeader.getNumbytes())).get());
            }
            offset = sparseHeader.getOffset() + sparseHeader.getNumbytes();
        }
        if (!this.sparseInputStreams.isEmpty()) {
            this.currentSparseInputStreamIndex = 0;
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return archiveEntry instanceof TarArchiveEntry;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.sparseInputStreams != null) {
            for (InputStream inputStream : this.sparseInputStreams) {
                inputStream.close();
            }
        }
        this.in.close();
    }

    private void consumeRemainderOfLastBlock() throws IOException {
        long bytesReadOfLastBlock = getBytesRead() % this.blockSize;
        if (bytesReadOfLastBlock > 0) {
            count(IOUtils.skip(this.in, this.blockSize - bytesReadOfLastBlock));
        }
    }

    private long getActuallySkipped(long available, long skipped, long expected) throws IOException {
        long actuallySkipped = skipped;
        if (this.in instanceof FileInputStream) {
            actuallySkipped = Math.min(skipped, available);
        }
        if (actuallySkipped != expected) {
            throw new IOException("Truncated TAR archive");
        }
        return actuallySkipped;
    }

    public TarArchiveEntry getCurrentEntry() {
        return this.currEntry;
    }

    protected byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream longName = new ByteArrayOutputStream();
        while (true) {
            int length = read(this.smallBuf);
            if (length < 0) {
                break;
            }
            longName.write(this.smallBuf, 0, length);
        }
        getNextEntry();
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

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public TarArchiveEntry getNextEntry() throws IOException {
        return getNextTarEntry();
    }

    @Deprecated
    public TarArchiveEntry getNextTarEntry() throws IOException {
        if (isAtEOF()) {
            return null;
        }
        if (this.currEntry != null) {
            IOUtils.skip(this, Long.MAX_VALUE);
            skipRecordPadding();
        }
        byte[] headerBuf = getRecord();
        if (headerBuf == null) {
            this.currEntry = null;
            return null;
        }
        try {
            this.currEntry = new TarArchiveEntry(this.globalPaxHeaders, headerBuf, this.zipEncoding, this.lenient);
            this.entryOffset = 0L;
            this.entrySize = this.currEntry.getSize();
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
                this.entrySize = this.currEntry.getSize();
                return this.currEntry;
            } catch (NumberFormatException e) {
                throw new IOException("Error detected parsing the pax header", e);
            }
        } catch (IllegalArgumentException e2) {
            throw new IOException("Error detected parsing the header", e2);
        }
    }

    private byte[] getRecord() throws IOException {
        byte[] headerBuf = readRecord();
        setAtEOF(isEOFRecord(headerBuf));
        if (isAtEOF() && headerBuf != null) {
            tryToConsumeSecondEOFRecord();
            consumeRemainderOfLastBlock();
            return null;
        }
        return headerBuf;
    }

    public int getRecordSize() {
        return this.recordBuffer.length;
    }

    protected final boolean isAtEOF() {
        return this.atEof;
    }

    private boolean isDirectory() {
        return this.currEntry != null && this.currEntry.isDirectory();
    }

    protected boolean isEOFRecord(byte[] record) {
        return record == null || ArchiveUtils.isArrayZero(record, getRecordSize());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int markLimit) {
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    private void paxHeaders() throws IOException {
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        Map<String, String> headers = TarUtils.parsePaxHeaders(this, sparseHeaders, this.globalPaxHeaders, this.entrySize);
        if (headers.containsKey("GNU.sparse.map")) {
            sparseHeaders = new ArrayList<>(TarUtils.parseFromPAX01SparseHeaders(headers.get("GNU.sparse.map")));
        }
        getNextEntry();
        if (this.currEntry == null) {
            throw new IOException("premature end of tar archive. Didn't find any entry after PAX header.");
        }
        applyPaxHeadersToCurrentEntry(headers, sparseHeaders);
        if (this.currEntry.isPaxGNU1XSparse()) {
            this.currEntry.setSparseHeaders(TarUtils.parsePAX1XSparseHeaders(this.in, getRecordSize()));
        }
        buildSparseInputStreams();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buf, int offset, int numToRead) throws IOException {
        int totalRead;
        if (numToRead == 0) {
            return 0;
        }
        if (isAtEOF() || isDirectory()) {
            return -1;
        }
        if (this.currEntry == null) {
            throw new IllegalStateException("No current tar entry");
        }
        if (this.entryOffset >= this.currEntry.getRealSize()) {
            return -1;
        }
        int numToRead2 = Math.min(numToRead, available());
        if (this.currEntry.isSparse()) {
            totalRead = readSparse(buf, offset, numToRead2);
        } else {
            totalRead = this.in.read(buf, offset, numToRead2);
        }
        if (totalRead == -1) {
            if (numToRead2 > 0) {
                throw new IOException("Truncated TAR archive");
            }
            setAtEOF(true);
        } else {
            count(totalRead);
            this.entryOffset += totalRead;
        }
        return totalRead;
    }

    private void readGlobalPaxHeaders() throws IOException {
        this.globalPaxHeaders = TarUtils.parsePaxHeaders(this, this.globalSparseHeaders, this.globalPaxHeaders, this.entrySize);
        getNextEntry();
        if (this.currEntry == null) {
            throw new IOException("Error detected parsing the pax header");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        throw new java.io.IOException("premature end of tar archive. Didn't find extended_header after header with extended flag.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
    
        buildSparseInputStreams();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0006, code lost:
    
        if (r4.currEntry.isExtended() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0008, code lost:
    
        r0 = getRecord();
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
    
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        r1 = new org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry(r0);
        r4.currEntry.getSparseHeaders().addAll(r1.getSparseHeaders());
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0024, code lost:
    
        if (r1.isExtended() != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readOldGNUSparse() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r0 = r4.currEntry
            boolean r0 = r0.isExtended()
            if (r0 == 0) goto L2f
        L8:
            byte[] r0 = r4.getRecord()
            if (r0 == 0) goto L27
            org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry r1 = new org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry
            r1.<init>(r0)
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r2 = r4.currEntry
            java.util.List r2 = r2.getSparseHeaders()
            java.util.List r3 = r1.getSparseHeaders()
            r2.addAll(r3)
            boolean r0 = r1.isExtended()
            if (r0 != 0) goto L8
            goto L2f
        L27:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "premature end of tar archive. Didn't find extended_header after header with extended flag."
            r1.<init>(r2)
            throw r1
        L2f:
            r4.buildSparseInputStreams()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveInputStream.readOldGNUSparse():void");
    }

    protected byte[] readRecord() throws IOException {
        int readCount = IOUtils.readFully(this.in, this.recordBuffer);
        count(readCount);
        if (readCount != getRecordSize()) {
            return null;
        }
        return this.recordBuffer;
    }

    private int readSparse(byte[] buf, int offset, int numToRead) throws IOException {
        if (this.sparseInputStreams == null || this.sparseInputStreams.isEmpty()) {
            InputStream currentInputStream = this.in;
            return currentInputStream.read(buf, offset, numToRead);
        }
        if (this.currentSparseInputStreamIndex >= this.sparseInputStreams.size()) {
            return -1;
        }
        InputStream currentInputStream2 = this.sparseInputStreams.get(this.currentSparseInputStreamIndex);
        int readLen = currentInputStream2.read(buf, offset, numToRead);
        if (this.currentSparseInputStreamIndex == this.sparseInputStreams.size() - 1) {
            return readLen;
        }
        if (readLen == -1) {
            this.currentSparseInputStreamIndex++;
            return readSparse(buf, offset, numToRead);
        }
        if (readLen < numToRead) {
            this.currentSparseInputStreamIndex++;
            int readLenOfNext = readSparse(buf, offset + readLen, numToRead - readLen);
            if (readLenOfNext == -1) {
                return readLen;
            }
            return readLen + readLenOfNext;
        }
        return readLen;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
    }

    protected final void setAtEOF(boolean atEof) {
        this.atEof = atEof;
    }

    protected final void setCurrentEntry(TarArchiveEntry currEntry) {
        this.currEntry = currEntry;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        long skipped;
        if (n <= 0 || isDirectory()) {
            return 0L;
        }
        long availableOfInputStream = this.in.available();
        long available = this.currEntry.getRealSize() - this.entryOffset;
        long numToSkip = Math.min(n, available);
        if (this.currEntry.isSparse()) {
            skipped = skipSparse(numToSkip);
        } else {
            long skipped2 = IOUtils.skip(this.in, numToSkip);
            skipped = getActuallySkipped(availableOfInputStream, skipped2, numToSkip);
        }
        count(skipped);
        this.entryOffset += skipped;
        return skipped;
    }

    private void skipRecordPadding() throws IOException {
        if (!isDirectory() && this.entrySize > 0 && this.entrySize % getRecordSize() != 0) {
            long available = this.in.available();
            long numRecords = (this.entrySize / getRecordSize()) + 1;
            long padding = (getRecordSize() * numRecords) - this.entrySize;
            long skipped = IOUtils.skip(this.in, padding);
            count(getActuallySkipped(available, skipped, padding));
        }
    }

    private long skipSparse(long n) throws IOException {
        if (this.sparseInputStreams == null || this.sparseInputStreams.isEmpty()) {
            return this.in.skip(n);
        }
        long bytesSkipped = 0;
        while (bytesSkipped < n && this.currentSparseInputStreamIndex < this.sparseInputStreams.size()) {
            InputStream currentInputStream = this.sparseInputStreams.get(this.currentSparseInputStreamIndex);
            bytesSkipped += currentInputStream.skip(n - bytesSkipped);
            if (bytesSkipped < n) {
                this.currentSparseInputStreamIndex++;
            }
        }
        return bytesSkipped;
    }

    private void tryToConsumeSecondEOFRecord() throws IOException {
        boolean shouldReset = true;
        boolean marked = this.in.markSupported();
        if (marked) {
            this.in.mark(getRecordSize());
        }
        try {
            shouldReset = !isEOFRecord(readRecord());
        } finally {
            if (shouldReset && marked) {
                pushedBackBytes(getRecordSize());
                this.in.reset();
            }
        }
    }
}
