package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public class ZipArchiveInputStream extends ArchiveInputStream<ZipArchiveEntry> implements InputStreamStatistics {
    private static final int CFH_LEN = 46;
    private static final int LFH_LEN = 30;
    public static final int PREAMBLE_GARBAGE_MAX_SIZE = 4096;
    private static final long TWO_EXP_32 = 4294967296L;
    private static final String USE_ZIPFILE_INSTEAD_OF_STREAM_DISCLAIMER = " while reading a stored entry using data descriptor. Either the archive is broken or it cannot be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See https://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile";
    private final boolean allowStoredEntriesWithDataDescriptor;
    private final ByteBuffer buf;
    private boolean closed;
    private CurrentEntry current;
    private int entriesRead;
    private boolean hitCentralDirectory;
    private final Inflater inf;
    private ByteArrayInputStream lastStoredEntry;
    private final byte[] lfhBuf;
    private final byte[] shortBuf;
    private final byte[] skipBuf;
    private final boolean skipSplitSig;
    private final byte[] twoDwordBuf;
    private long uncompressedCount;
    private final boolean useUnicodeExtraFields;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;
    private static final byte[] LFH = ZipLong.LFH_SIG.getBytes();
    private static final byte[] CFH = ZipLong.CFH_SIG.getBytes();
    private static final byte[] DD = ZipLong.DD_SIG.getBytes();
    private static final byte[] APK_SIGNING_BLOCK_MAGIC = {65, 80, TarConstants.LF_GNUTYPE_LONGLINK, 32, TarConstants.LF_GNUTYPE_SPARSE, 105, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 32, 66, 108, 111, 99, 107, 32, TarConstants.LF_BLK, TarConstants.LF_SYMLINK};
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class BoundCountInputStream extends BoundedInputStream {
        BoundCountInputStream(InputStream in, long max) {
            super(in, max);
        }

        private boolean atMaxLength() {
            return getMaxCount() >= 0 && getCount() >= getMaxCount();
        }

        @Override // org.apache.commons.io.input.BoundedInputStream, org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (atMaxLength()) {
                return -1;
            }
            int result = super.read();
            if (result != -1) {
                readCount(1);
            }
            return result;
        }

        @Override // org.apache.commons.io.input.BoundedInputStream, org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            if (atMaxLength()) {
                return -1;
            }
            long maxRead = getMaxCount() >= 0 ? Math.min(len, getMaxCount() - getCount()) : len;
            return readCount(super.read(b, off, (int) maxRead));
        }

        private int readCount(int bytesRead) {
            if (bytesRead != -1) {
                ZipArchiveInputStream.this.count(bytesRead);
                CurrentEntry.access$214(ZipArchiveInputStream.this.current, bytesRead);
            }
            return bytesRead;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CurrentEntry {
        private long bytesRead;
        private long bytesReadFromStream;
        private final CRC32 crc;
        private final ZipArchiveEntry entry;
        private boolean hasDataDescriptor;
        private InputStream inputStream;
        private boolean usesZip64;

        private CurrentEntry() {
            this.entry = new ZipArchiveEntry();
            this.crc = new CRC32();
        }

        static /* synthetic */ long access$214(CurrentEntry x0, long x1) {
            long j = x0.bytesReadFromStream + x1;
            x0.bytesReadFromStream = j;
            return j;
        }

        static /* synthetic */ long access$222(CurrentEntry x0, long x1) {
            long j = x0.bytesReadFromStream - x1;
            x0.bytesReadFromStream = j;
            return j;
        }

        static /* synthetic */ long access$414(CurrentEntry x0, long x1) {
            long j = x0.bytesRead + x1;
            x0.bytesRead = j;
            return j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends InputStream> T checkInputStream() {
            return (T) Objects.requireNonNull(this.inputStream, "inputStream");
        }
    }

    private static boolean checkSig(byte[] expected, byte[] signature) {
        for (int i = 0; i < expected.length; i++) {
            if (signature[i] != expected[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < ZipArchiveOutputStream.LFH_SIG.length) {
            return false;
        }
        return checkSig(ZipArchiveOutputStream.LFH_SIG, signature) || checkSig(ZipArchiveOutputStream.EOCD_SIG, signature) || checkSig(ZipArchiveOutputStream.DD_SIG, signature) || checkSig(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.getBytes(), signature);
    }

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, StandardCharsets.UTF_8.name());
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding) {
        this(inputStream, encoding, true);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields) {
        this(inputStream, encoding, useUnicodeExtraFields, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor) {
        this(inputStream, encoding, useUnicodeExtraFields, allowStoredEntriesWithDataDescriptor, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor, boolean skipSplitSig) {
        super(inputStream, encoding);
        this.inf = new Inflater(true);
        this.buf = ByteBuffer.allocate(512);
        this.lfhBuf = new byte[30];
        this.skipBuf = new byte[1024];
        this.shortBuf = new byte[2];
        this.wordBuf = new byte[4];
        this.twoDwordBuf = new byte[16];
        this.in = new PushbackInputStream(inputStream, this.buf.capacity());
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.useUnicodeExtraFields = useUnicodeExtraFields;
        this.allowStoredEntriesWithDataDescriptor = allowStoredEntriesWithDataDescriptor;
        this.skipSplitSig = skipSplitSig;
        this.buf.limit(0);
    }

    private boolean bufferContainsSignature(ByteArrayOutputStream bos, int offset, int lastRead, int expectedDDLen) throws IOException {
        boolean done = false;
        for (int i = 0; !done && i < (offset + lastRead) - 4; i++) {
            if (this.buf.array()[i] == LFH[0] && this.buf.array()[i + 1] == LFH[1]) {
                int expectDDPos = i;
                if ((i >= expectedDDLen && this.buf.array()[i + 2] == LFH[2] && this.buf.array()[i + 3] == LFH[3]) || (this.buf.array()[i + 2] == CFH[2] && this.buf.array()[i + 3] == CFH[3])) {
                    expectDDPos = i - expectedDDLen;
                    done = true;
                } else if (this.buf.array()[i + 2] == DD[2] && this.buf.array()[i + 3] == DD[3]) {
                    done = true;
                }
                if (done) {
                    pushback(this.buf.array(), expectDDPos, (offset + lastRead) - expectDDPos);
                    bos.write(this.buf.array(), 0, expectDDPos);
                    readDataDescriptor();
                }
            }
        }
        return done;
    }

    private int cacheBytesRead(ByteArrayOutputStream bos, int offset, int lastRead, int expectedDDLen) {
        int cacheable = ((offset + lastRead) - expectedDDLen) - 3;
        if (cacheable > 0) {
            bos.write(this.buf.array(), 0, cacheable);
            System.arraycopy(this.buf.array(), cacheable, this.buf.array(), 0, expectedDDLen + 3);
            return expectedDDLen + 3;
        }
        return offset + lastRead;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry ae) {
        if (!(ae instanceof ZipArchiveEntry)) {
            return false;
        }
        ZipArchiveEntry ze = (ZipArchiveEntry) ae;
        return ZipUtil.canHandleEntryData(ze) && supportsDataDescriptorFor(ze) && supportsCompressedSizeFor(ze);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                this.in.close();
            } finally {
                this.inf.end();
            }
        }
    }

    private void closeEntry() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return;
        }
        if (currentEntryHasOutstandingBytes()) {
            drainCurrentEntryData();
        } else if (skip(Long.MAX_VALUE) >= 0) {
            long inB = this.current.entry.getMethod() == 8 ? getBytesInflated() : this.current.bytesRead;
            int diff = (int) (this.current.bytesReadFromStream - inB);
            if (diff > 0) {
                pushback(this.buf.array(), this.buf.limit() - diff, diff);
                CurrentEntry.access$222(this.current, diff);
            }
            if (currentEntryHasOutstandingBytes()) {
                drainCurrentEntryData();
            }
        } else {
            throw new IllegalStateException("Can't read the remainder of the stream");
        }
        if (this.lastStoredEntry == null && this.current.hasDataDescriptor) {
            readDataDescriptor();
        }
        this.inf.reset();
        this.buf.clear().flip();
        this.current = null;
        this.lastStoredEntry = null;
    }

    protected InputStream createZstdInputStream(InputStream in) throws IOException {
        return new ZstdCompressorInputStream(in);
    }

    private boolean currentEntryHasOutstandingBytes() {
        return this.current.bytesReadFromStream <= this.current.entry.getCompressedSize() && !this.current.hasDataDescriptor;
    }

    private void drainCurrentEntryData() throws IOException {
        long remaining = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream;
        while (remaining > 0) {
            long n = this.in.read(this.buf.array(), 0, (int) Math.min(this.buf.capacity(), remaining));
            if (n < 0) {
                throw new EOFException("Truncated ZIP entry: " + ArchiveUtils.sanitize(this.current.entry.getName()));
            }
            count(n);
            remaining -= n;
        }
    }

    private int fill() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        int length = this.in.read(this.buf.array());
        if (length > 0) {
            this.buf.limit(length);
            count(this.buf.limit());
            this.inf.setInput(this.buf.array(), 0, this.buf.limit());
        }
        return length;
    }

    private boolean findEocdRecord() throws IOException {
        int currentByte = -1;
        boolean skipReadCall = false;
        while (true) {
            if (!skipReadCall) {
                int readOneByte = readOneByte();
                currentByte = readOneByte;
                if (readOneByte <= -1) {
                    return false;
                }
            }
            skipReadCall = false;
            if (isFirstByteOfEocdSig(currentByte)) {
                currentByte = readOneByte();
                if (currentByte != ZipArchiveOutputStream.EOCD_SIG[1]) {
                    if (currentByte != -1) {
                        skipReadCall = isFirstByteOfEocdSig(currentByte);
                    } else {
                        return false;
                    }
                } else {
                    currentByte = readOneByte();
                    if (currentByte != ZipArchiveOutputStream.EOCD_SIG[2]) {
                        if (currentByte != -1) {
                            skipReadCall = isFirstByteOfEocdSig(currentByte);
                        } else {
                            return false;
                        }
                    } else {
                        currentByte = readOneByte();
                        if (currentByte != -1) {
                            if (currentByte == ZipArchiveOutputStream.EOCD_SIG[3]) {
                                return true;
                            }
                            skipReadCall = isFirstByteOfEocdSig(currentByte);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }

    private long getBytesInflated() {
        long inB = this.inf.getBytesRead();
        if (this.current.bytesReadFromStream >= TWO_EXP_32) {
            while (inB + TWO_EXP_32 <= this.current.bytesReadFromStream) {
                inB += TWO_EXP_32;
            }
        }
        return inB;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        if (this.current == null) {
            return -1L;
        }
        int method = this.current.entry.getMethod();
        if (method == 0) {
            return this.current.bytesRead;
        }
        if (method == 8) {
            return getBytesInflated();
        }
        if (method == ZipMethod.UNSHRINKING.getCode() || method == ZipMethod.IMPLODING.getCode() || method == ZipMethod.ENHANCED_DEFLATED.getCode() || method == ZipMethod.BZIP2.getCode()) {
            return ((InputStreamStatistics) this.current.checkInputStream()).getCompressedCount();
        }
        return -1L;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ZipArchiveEntry getNextEntry() throws IOException {
        return getNextZipEntry();
    }

    @Deprecated
    public ZipArchiveEntry getNextZipEntry() throws IOException {
        boolean firstEntry;
        int off;
        this.uncompressedCount = 0L;
        if (!this.closed && !this.hitCentralDirectory) {
            if (this.current == null) {
                firstEntry = true;
            } else {
                closeEntry();
                firstEntry = false;
            }
            long currentHeaderOffset = getBytesRead();
            if (!firstEntry) {
                try {
                    readFully(this.lfhBuf);
                } catch (EOFException e) {
                    return null;
                }
            } else {
                try {
                    if (!readFirstLocalFileHeader()) {
                        this.hitCentralDirectory = true;
                        skipRemainderOfArchive();
                        return null;
                    }
                } catch (EOFException e2) {
                    return null;
                }
            }
            ZipLong sig = new ZipLong(this.lfhBuf);
            if (sig.equals(ZipLong.LFH_SIG)) {
                this.current = new CurrentEntry();
                int versionMadeBy = ZipShort.getValue(this.lfhBuf, 4);
                int off2 = 4 + 2;
                this.current.entry.setPlatform(ZipFile.toPlatform(versionMadeBy));
                GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(this.lfhBuf, off2);
                boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
                ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.ZIP_ENCODING_UTF_8 : this.zipEncoding;
                this.current.hasDataDescriptor = gpFlag.usesDataDescriptor();
                this.current.entry.setGeneralPurposeBit(gpFlag);
                int off3 = off2 + 2;
                this.current.entry.setMethod(ZipShort.getValue(this.lfhBuf, off3));
                int off4 = off3 + 2;
                long time = ZipUtil.dosToJavaTime(ZipLong.getValue(this.lfhBuf, off4));
                this.current.entry.setTime(time);
                int off5 = off4 + 4;
                ZipLong size = null;
                ZipLong cSize = null;
                if (this.current.hasDataDescriptor) {
                    off = off5 + 12;
                } else {
                    this.current.entry.setCrc(ZipLong.getValue(this.lfhBuf, off5));
                    int off6 = off5 + 4;
                    cSize = new ZipLong(this.lfhBuf, off6);
                    int off7 = off6 + 4;
                    size = new ZipLong(this.lfhBuf, off7);
                    off = off7 + 4;
                }
                int fileNameLen = ZipShort.getValue(this.lfhBuf, off);
                int off8 = off + 2;
                int extraLen = ZipShort.getValue(this.lfhBuf, off8);
                int i = off8 + 2;
                byte[] fileName = readRange(fileNameLen);
                this.current.entry.setName(entryEncoding.decode(fileName), fileName);
                if (hasUTF8Flag) {
                    this.current.entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
                }
                byte[] extraData = readRange(extraLen);
                try {
                    this.current.entry.setExtra(extraData);
                    if (!hasUTF8Flag && this.useUnicodeExtraFields) {
                        ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, fileName, null);
                    }
                    processZip64Extra(size, cSize);
                    this.current.entry.setLocalHeaderOffset(currentHeaderOffset);
                    this.current.entry.setDataOffset(getBytesRead());
                    this.current.entry.setStreamContiguous(true);
                    ZipMethod m = ZipMethod.getMethodByCode(this.current.entry.getMethod());
                    if (this.current.entry.getCompressedSize() != -1) {
                        if (ZipUtil.canHandleEntryData(this.current.entry) && m != ZipMethod.STORED && m != ZipMethod.DEFLATED) {
                            InputStream bis = new BoundCountInputStream(this.in, this.current.entry.getCompressedSize());
                            switch (m) {
                                case UNSHRINKING:
                                    this.current.inputStream = new UnshrinkingInputStream(bis);
                                    break;
                                case IMPLODING:
                                    try {
                                    } catch (IllegalArgumentException e3) {
                                        ex = e3;
                                    }
                                    try {
                                        this.current.inputStream = new ExplodingInputStream(this.current.entry.getGeneralPurposeBit().getSlidingDictionarySize(), this.current.entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), bis);
                                        break;
                                    } catch (IllegalArgumentException e4) {
                                        ex = e4;
                                        throw new IOException("bad IMPLODE data", ex);
                                    }
                                case BZIP2:
                                    this.current.inputStream = new BZip2CompressorInputStream(bis);
                                    break;
                                case ENHANCED_DEFLATED:
                                    this.current.inputStream = new Deflate64CompressorInputStream(bis);
                                    break;
                                case ZSTD:
                                case ZSTD_DEPRECATED:
                                    this.current.inputStream = createZstdInputStream(bis);
                                    break;
                            }
                        }
                    } else if (m == ZipMethod.ENHANCED_DEFLATED) {
                        this.current.inputStream = new Deflate64CompressorInputStream(this.in);
                    }
                    this.entriesRead++;
                    return this.current.entry;
                } catch (RuntimeException ex) {
                    throw ZipUtil.newZipException("Invalid extra data in entry " + this.current.entry.getName(), ex);
                }
            }
            if (sig.equals(ZipLong.CFH_SIG) || sig.equals(ZipLong.AED_SIG) || isApkSigningBlock(this.lfhBuf)) {
                this.hitCentralDirectory = true;
                skipRemainderOfArchive();
                return null;
            }
            throw new ZipException(String.format("Unexpected record signature: 0x%x", Long.valueOf(sig.getValue())));
        }
        return null;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    private boolean isApkSigningBlock(byte[] suspectLocalFileHeader) throws IOException {
        BigInteger len = ZipEightByteInteger.getValue(suspectLocalFileHeader);
        BigInteger toSkip = len.add(BigInteger.valueOf((8 - suspectLocalFileHeader.length) - APK_SIGNING_BLOCK_MAGIC.length));
        byte[] magic = new byte[APK_SIGNING_BLOCK_MAGIC.length];
        try {
            if (toSkip.signum() < 0) {
                int off = suspectLocalFileHeader.length + toSkip.intValue();
                if (off < 8) {
                    return false;
                }
                int bytesInBuffer = Math.abs(toSkip.intValue());
                System.arraycopy(suspectLocalFileHeader, off, magic, 0, Math.min(bytesInBuffer, magic.length));
                if (bytesInBuffer < magic.length) {
                    readFully(magic, bytesInBuffer);
                }
            } else {
                while (toSkip.compareTo(LONG_MAX) > 0) {
                    realSkip(Long.MAX_VALUE);
                    toSkip = toSkip.add(LONG_MAX.negate());
                }
                realSkip(toSkip.longValue());
                readFully(magic);
            }
            return Arrays.equals(magic, APK_SIGNING_BLOCK_MAGIC);
        } catch (EOFException e) {
            return false;
        }
    }

    private boolean isFirstByteOfEocdSig(int b) {
        return b == ZipArchiveOutputStream.EOCD_SIG[0];
    }

    private void processZip64Extra(ZipLong size, ZipLong cSize) throws ZipException {
        ZipExtraField extra = this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extra != null && !(extra instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField) extra;
        this.current.usesZip64 = z64 != null;
        if (!this.current.hasDataDescriptor) {
            if (z64 != null && (ZipLong.ZIP64_MAGIC.equals(cSize) || ZipLong.ZIP64_MAGIC.equals(size))) {
                if (z64.getCompressedSize() == null || z64.getSize() == null) {
                    throw new ZipException("archive contains corrupted zip64 extra field");
                }
                long s = z64.getCompressedSize().getLongValue();
                if (s >= 0) {
                    this.current.entry.setCompressedSize(s);
                    long s2 = z64.getSize().getLongValue();
                    if (s2 >= 0) {
                        this.current.entry.setSize(s2);
                        return;
                    }
                    throw new ZipException("broken archive, entry with negative size");
                }
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            if (cSize == null || size == null) {
                return;
            }
            if (cSize.getValue() >= 0) {
                this.current.entry.setCompressedSize(cSize.getValue());
                if (size.getValue() >= 0) {
                    this.current.entry.setSize(size.getValue());
                    return;
                }
                throw new ZipException("broken archive, entry with negative size");
            }
            throw new ZipException("broken archive, entry with negative compressed size");
        }
    }

    private void pushback(byte[] buf, int offset, int length) throws IOException {
        if (offset < 0) {
            throw new IOException(String.format("Negative offset %,d into buffer", Integer.valueOf(offset)));
        }
        ((PushbackInputStream) this.in).unread(buf, offset, length);
        pushedBackBytes(length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int read;
        if (length == 0) {
            return 0;
        }
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return -1;
        }
        if (offset <= buffer.length && length >= 0 && offset >= 0 && buffer.length - offset >= length) {
            ZipUtil.checkRequestedFeatures(this.current.entry);
            if (supportsDataDescriptorFor(this.current.entry)) {
                if (supportsCompressedSizeFor(this.current.entry)) {
                    int method = this.current.entry.getMethod();
                    if (method == 0) {
                        read = readStored(buffer, offset, length);
                    } else if (method == 8) {
                        read = readDeflated(buffer, offset, length);
                    } else if (method == ZipMethod.UNSHRINKING.getCode() || method == ZipMethod.IMPLODING.getCode() || method == ZipMethod.ENHANCED_DEFLATED.getCode() || method == ZipMethod.BZIP2.getCode() || ZipMethod.isZstd(method) || method == ZipMethod.XZ.getCode()) {
                        read = this.current.inputStream.read(buffer, offset, length);
                    } else {
                        throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(method), this.current.entry);
                    }
                    if (read >= 0) {
                        this.current.crc.update(buffer, offset, read);
                        this.uncompressedCount += read;
                    }
                    return read;
                }
                throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.UNKNOWN_COMPRESSED_SIZE, this.current.entry);
            }
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private void readDataDescriptor() throws IOException {
        readFully(this.wordBuf);
        ZipLong val = new ZipLong(this.wordBuf);
        if (ZipLong.DD_SIG.equals(val)) {
            readFully(this.wordBuf);
            val = new ZipLong(this.wordBuf);
        }
        this.current.entry.setCrc(val.getValue());
        readFully(this.twoDwordBuf);
        ZipLong potentialSig = new ZipLong(this.twoDwordBuf, 8);
        if (potentialSig.equals(ZipLong.CFH_SIG) || potentialSig.equals(ZipLong.LFH_SIG)) {
            pushback(this.twoDwordBuf, 8, 8);
            long size = ZipLong.getValue(this.twoDwordBuf);
            if (size >= 0) {
                this.current.entry.setCompressedSize(size);
                long size2 = ZipLong.getValue(this.twoDwordBuf, 4);
                if (size2 >= 0) {
                    this.current.entry.setSize(size2);
                    return;
                }
                throw new ZipException("broken archive, entry with negative size");
            }
            throw new ZipException("broken archive, entry with negative compressed size");
        }
        long size3 = ZipEightByteInteger.getLongValue(this.twoDwordBuf);
        if (size3 >= 0) {
            this.current.entry.setCompressedSize(size3);
            long size4 = ZipEightByteInteger.getLongValue(this.twoDwordBuf, 8);
            if (size4 >= 0) {
                this.current.entry.setSize(size4);
                return;
            }
            throw new ZipException("broken archive, entry with negative size");
        }
        throw new ZipException("broken archive, entry with negative compressed size");
    }

    private int readDeflated(byte[] buffer, int offset, int length) throws IOException {
        int read = readFromInflater(buffer, offset, length);
        if (read <= 0) {
            if (this.inf.finished()) {
                return -1;
            }
            if (this.inf.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            }
            if (read == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        return read;
    }

    private boolean readFirstLocalFileHeader() throws IOException {
        int j;
        byte[] header = new byte[Math.min(30, 22)];
        readFully(header);
        int i = 0;
        loop0: while (true) {
            j = 0;
            while (i <= 4092) {
                try {
                    if (j > header.length - 4) {
                        break;
                    }
                    ZipLong sig = new ZipLong(header, j);
                    if (sig.equals(ZipLong.LFH_SIG) || sig.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER) || sig.equals(ZipLong.DD_SIG)) {
                        break loop0;
                    }
                    if (!sig.equals(new ZipLong(ZipArchiveOutputStream.EOCD_SIG))) {
                        j++;
                        i++;
                    } else {
                        pushback(header, j, header.length - j);
                        return false;
                    }
                } catch (EOFException e) {
                    throw new ZipException("Cannot find zip signature within the file");
                }
            }
            if (i >= 4092) {
                throw new ZipException("Cannot find zip signature within the first 4096 bytes");
            }
            System.arraycopy(header, header.length - 3, header, 0, 3);
            readFully(header, 3);
        }
        System.arraycopy(header, j, header, 0, header.length - j);
        readFully(header, header.length - j);
        System.arraycopy(header, 0, this.lfhBuf, 0, header.length);
        readFully(this.lfhBuf, header.length);
        ZipLong sig2 = new ZipLong(this.lfhBuf);
        if (!this.skipSplitSig && sig2.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        }
        if (sig2.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER) || sig2.equals(ZipLong.DD_SIG)) {
            System.arraycopy(this.lfhBuf, 4, this.lfhBuf, 0, this.lfhBuf.length - 4);
            readFully(this.lfhBuf, this.lfhBuf.length - 4);
            return true;
        }
        return true;
    }

    private int readFromInflater(byte[] buffer, int offset, int length) throws IOException {
        int read = 0;
        while (true) {
            if (this.inf.needsInput()) {
                int l = fill();
                if (l > 0) {
                    CurrentEntry.access$214(this.current, this.buf.limit());
                } else if (l == -1) {
                    return -1;
                }
            }
            try {
                read = this.inf.inflate(buffer, offset, length);
                if (read != 0 || !this.inf.needsInput()) {
                    break;
                }
            } catch (DataFormatException e) {
                throw ZipUtil.newZipException(e.getMessage(), e);
            }
        }
        return read;
    }

    private void readFully(byte[] b) throws IOException {
        readFully(b, 0);
    }

    private void readFully(byte[] b, int off) throws IOException {
        int len = b.length - off;
        int count = IOUtils.readFully(this.in, b, off, len);
        count(count);
        if (count < len) {
            throw new EOFException();
        }
    }

    private int readOneByte() throws IOException {
        int b = this.in.read();
        if (b != -1) {
            count(1);
        }
        return b;
    }

    private byte[] readRange(int len) throws IOException {
        byte[] ret = IOUtils.readRange(this.in, len);
        count(ret.length);
        if (ret.length < len) {
            throw new EOFException();
        }
        return ret;
    }

    private int readStored(byte[] buffer, int offset, int length) throws IOException {
        if (!this.current.hasDataDescriptor) {
            long csize = this.current.entry.getSize();
            if (this.current.bytesRead >= csize) {
                return -1;
            }
            if (this.buf.position() >= this.buf.limit()) {
                this.buf.position(0);
                int l = this.in.read(this.buf.array());
                if (l == -1) {
                    this.buf.limit(0);
                    throw new IOException("Truncated ZIP file");
                }
                this.buf.limit(l);
                count(l);
                CurrentEntry.access$214(this.current, l);
            }
            int toRead = Math.min(this.buf.remaining(), length);
            if (csize - this.current.bytesRead < toRead) {
                toRead = (int) (csize - this.current.bytesRead);
            }
            this.buf.get(buffer, offset, toRead);
            CurrentEntry.access$414(this.current, toRead);
            return toRead;
        }
        if (this.lastStoredEntry == null) {
            readStoredEntry();
        }
        return this.lastStoredEntry.read(buffer, offset, length);
    }

    private void readStoredEntry() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int off = 0;
        boolean done = false;
        int ddLen = this.current.usesZip64 ? 20 : 12;
        while (!done) {
            int r = this.in.read(this.buf.array(), off, 512 - off);
            if (r <= 0) {
                throw new IOException("Truncated ZIP file");
            }
            if (r + off < 4) {
                off += r;
            } else {
                done = bufferContainsSignature(bos, off, r, ddLen);
                if (!done) {
                    off = cacheBytesRead(bos, off, r, ddLen);
                }
            }
        }
        if (this.current.entry.getCompressedSize() != this.current.entry.getSize()) {
            throw new ZipException("compressed and uncompressed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it cannot be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See https://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        byte[] b = bos.toByteArray();
        if (b.length != this.current.entry.getSize()) {
            throw new ZipException("actual and claimed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it cannot be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See https://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        this.lastStoredEntry = new ByteArrayInputStream(b);
    }

    private void realSkip(long value) throws IOException {
        if (value >= 0) {
            long skipped = 0;
            while (skipped < value) {
                long rem = value - skipped;
                int x = this.in.read(this.skipBuf, 0, (int) (((long) this.skipBuf.length) > rem ? rem : this.skipBuf.length));
                if (x == -1) {
                    return;
                }
                count(x);
                skipped += x;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public ZipArchiveInputStream setExtraFieldSupport(Function<ZipShort, ZipExtraField> extraFieldSupport) {
        return this;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long value) throws IOException {
        if (value >= 0) {
            long skipped = 0;
            while (skipped < value) {
                long rem = value - skipped;
                int x = read(this.skipBuf, 0, (int) (((long) this.skipBuf.length) > rem ? rem : this.skipBuf.length));
                if (x == -1) {
                    return skipped;
                }
                skipped += x;
            }
            return skipped;
        }
        throw new IllegalArgumentException("Negative skip value");
    }

    private void skipRemainderOfArchive() throws IOException {
        if (this.entriesRead > 0) {
            realSkip((this.entriesRead * 46) - 30);
        }
        boolean foundEocd = findEocdRecord();
        if (foundEocd) {
            realSkip(16L);
            readFully(this.shortBuf);
            int commentLen = ZipShort.getValue(this.shortBuf);
            if (commentLen >= 0) {
                realSkip(commentLen);
                return;
            }
        }
        throw new IOException("Truncated ZIP file");
    }

    private boolean supportsCompressedSizeFor(ZipArchiveEntry entry) {
        int method = entry.getMethod();
        return entry.getCompressedSize() != -1 || method == 8 || method == ZipMethod.ENHANCED_DEFLATED.getCode() || (entry.getGeneralPurposeBit().usesDataDescriptor() && this.allowStoredEntriesWithDataDescriptor && method == 0) || ZipMethod.isZstd(method) || method == ZipMethod.XZ.getCode();
    }

    private boolean supportsDataDescriptorFor(ZipArchiveEntry entry) {
        int method = entry.getMethod();
        return !entry.getGeneralPurposeBit().usesDataDescriptor() || (this.allowStoredEntriesWithDataDescriptor && method == 0) || method == 8 || method == ZipMethod.ENHANCED_DEFLATED.getCode() || ZipMethod.isZstd(method) || method == ZipMethod.XZ.getCode();
    }
}
