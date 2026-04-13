package org.apache.commons.compress.archivers.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOStream;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public class ZipFile implements Closeable {
    static final int BYTE_SHIFT = 8;
    private static final int CFD_DISK_OFFSET = 6;
    private static final int CFD_LENGTH_OFFSET = 12;
    private static final int CFD_LOCATOR_RELATIVE_OFFSET = 8;
    private static final int CFH_LEN = 42;
    private static final int HASH_SIZE = 509;
    private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26;
    private static final int MAX_EOCD_SIZE = 65557;
    static final int MIN_EOCD_SIZE = 22;
    static final int NIBLET_MASK = 15;
    private static final int POS_0 = 0;
    private static final int POS_1 = 1;
    private static final int POS_2 = 2;
    private static final int POS_3 = 3;
    private static final int ZIP64_EOCDL_LENGTH = 20;
    private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
    private static final int ZIP64_EOCD_CFD_DISK_OFFSET = 20;
    private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
    private static final int ZIP64_EOCD_CFD_LOCATOR_RELATIVE_OFFSET = 24;
    private final SeekableByteChannel archive;
    private long centralDirectoryStartDiskNumber;
    private long centralDirectoryStartOffset;
    private long centralDirectoryStartRelativeOffset;
    private final ByteBuffer cfhBbuf;
    private final byte[] cfhBuf;
    private volatile boolean closed;
    private final ByteBuffer dwordBbuf;
    private final byte[] dwordBuf;
    private final Charset encoding;
    private final List<ZipArchiveEntry> entries;
    private long firstLocalFileHeaderOffset;
    private final boolean isSplitZipArchive;
    private final Map<String, LinkedList<ZipArchiveEntry>> nameMap;
    private final ByteBuffer shortBbuf;
    private final byte[] shortBuf;
    private final boolean useUnicodeExtraFields;
    private final ByteBuffer wordBbuf;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;
    private final IOFunction<InputStream, InputStream> zstdInputStreamFactory;
    private static final String DEFAULT_CHARSET_NAME = StandardCharsets.UTF_8.name();
    private static final EnumSet<StandardOpenOption> READ = EnumSet.of(StandardOpenOption.READ);
    private static final byte[] ONE_ZERO_BYTE = new byte[1];
    private static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
    private static final Comparator<ZipArchiveEntry> offsetComparator = Comparator.comparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda3
        @Override // java.util.function.ToLongFunction
        public final long applyAsLong(Object obj) {
            return ((ZipArchiveEntry) obj).getDiskNumberStart();
        }
    }).thenComparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda4
        @Override // java.util.function.ToLongFunction
        public final long applyAsLong(Object obj) {
            return ((ZipArchiveEntry) obj).getLocalHeaderOffset();
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class BoundedFileChannelInputStream extends BoundedArchiveInputStream {
        private final FileChannel archive;

        BoundedFileChannelInputStream(long start, long remaining, FileChannel archive) {
            super(start, remaining);
            this.archive = archive;
        }

        @Override // org.apache.commons.compress.utils.BoundedArchiveInputStream
        protected int read(long pos, ByteBuffer buf) throws IOException {
            int read = this.archive.read(buf, pos);
            buf.flip();
            return read;
        }
    }

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<ZipFile, Builder> {
        static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
        private boolean ignoreLocalFileHeader;
        private SeekableByteChannel seekableByteChannel;
        private IOFunction<InputStream, InputStream> zstdInputStreamFactory;
        private boolean useUnicodeExtraFields = true;
        private long maxNumberOfDisks = 1;

        public Builder() {
            setCharset(DEFAULT_CHARSET);
            setCharsetDefault(DEFAULT_CHARSET);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public ZipFile get() throws IOException {
            SeekableByteChannel actualChannel;
            String actualDescription;
            if (this.seekableByteChannel != null) {
                SeekableByteChannel actualChannel2 = this.seekableByteChannel;
                actualChannel = actualChannel2;
                actualDescription = actualChannel2.getClass().getSimpleName();
            } else if (checkOrigin() instanceof AbstractOrigin.ByteArrayOrigin) {
                SeekableByteChannel actualChannel3 = new SeekableInMemoryByteChannel(checkOrigin().getByteArray());
                actualChannel = actualChannel3;
                actualDescription = actualChannel3.getClass().getSimpleName();
            } else {
                OpenOption[] openOptions = getOpenOptions();
                if (openOptions.length == 0) {
                    openOptions = new OpenOption[]{StandardOpenOption.READ};
                }
                Path path = getPath();
                SeekableByteChannel actualChannel4 = ZipFile.openZipChannel(path, this.maxNumberOfDisks, openOptions);
                actualChannel = actualChannel4;
                actualDescription = path.toString();
            }
            boolean closeOnError = this.seekableByteChannel != null;
            return new ZipFile(actualChannel, actualDescription, getCharset(), this.useUnicodeExtraFields, closeOnError, this.ignoreLocalFileHeader, this.zstdInputStreamFactory);
        }

        public Builder setIgnoreLocalFileHeader(boolean ignoreLocalFileHeader) {
            this.ignoreLocalFileHeader = ignoreLocalFileHeader;
            return this;
        }

        public Builder setMaxNumberOfDisks(long maxNumberOfDisks) {
            this.maxNumberOfDisks = maxNumberOfDisks;
            return this;
        }

        public Builder setSeekableByteChannel(SeekableByteChannel seekableByteChannel) {
            this.seekableByteChannel = seekableByteChannel;
            return this;
        }

        public Builder setUseUnicodeExtraFields(boolean useUnicodeExtraFields) {
            this.useUnicodeExtraFields = useUnicodeExtraFields;
            return this;
        }

        public Builder setZstdInputStreamFactory(IOFunction<InputStream, InputStream> zstdInpStreamFactory) {
            this.zstdInputStreamFactory = zstdInpStreamFactory;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Entry extends ZipArchiveEntry {
        private Entry() {
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry
        public boolean equals(Object other) {
            if (!super.equals(other)) {
                return false;
            }
            Entry otherEntry = (Entry) other;
            return getLocalHeaderOffset() == otherEntry.getLocalHeaderOffset() && super.getDataOffset() == otherEntry.getDataOffset() && super.getDiskNumberStart() == otherEntry.getDiskNumberStart();
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry, java.util.zip.ZipEntry
        public int hashCode() {
            return (super.hashCode() * 3) + ((int) getLocalHeaderOffset()) + ((int) (getLocalHeaderOffset() >> 32));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class NameAndComment {
        private final byte[] comment;
        private final byte[] name;

        private NameAndComment(byte[] name, byte[] comment) {
            this.name = name;
            this.comment = comment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class StoredStatisticsStream extends BoundedInputStream implements InputStreamStatistics {
        StoredStatisticsStream(InputStream in) {
            super(in);
        }

        @Override // org.apache.commons.compress.utils.InputStreamStatistics
        public long getCompressedCount() {
            return super.getCount();
        }

        @Override // org.apache.commons.compress.utils.InputStreamStatistics
        public long getUncompressedCount() {
            return getCompressedCount();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void closeQuietly(ZipFile zipFile) {
        IOUtils.closeQuietly(zipFile);
    }

    private static SeekableByteChannel newReadByteChannel(Path path) throws IOException {
        return Files.newByteChannel(path, READ, new FileAttribute[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SeekableByteChannel openZipChannel(final Path path, long maxNumberOfDisks, OpenOption[] openOptions) throws IOException {
        final long numberOfDisks;
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        try {
            boolean is64 = positionAtEndOfCentralDirectoryRecord(channel);
            if (is64) {
                try {
                    channel.position(channel.position() + 4 + 4 + 8);
                    ByteBuffer buf = ByteBuffer.allocate(4);
                    buf.order(ByteOrder.LITTLE_ENDIAN);
                    org.apache.commons.compress.utils.IOUtils.readFully(channel, buf);
                    buf.flip();
                    long numberOfDisks2 = buf.getInt() & 4294967295L;
                    numberOfDisks = numberOfDisks2;
                } catch (Throwable th) {
                    ex = th;
                    IOUtils.closeQuietly(channel);
                    throw ex;
                }
            } else {
                channel.position(channel.position() + 4);
                ByteBuffer buf2 = ByteBuffer.allocate(2);
                buf2.order(ByteOrder.LITTLE_ENDIAN);
                org.apache.commons.compress.utils.IOUtils.readFully(channel, buf2);
                buf2.flip();
                numberOfDisks = (buf2.getShort() & 65535) + 1;
            }
            try {
                if (numberOfDisks > Math.min(maxNumberOfDisks, 2147483647L)) {
                    throw new IOException("Too many disks for zip archive, max=" + Math.min(maxNumberOfDisks, 2147483647L) + " actual=" + numberOfDisks);
                }
                if (numberOfDisks <= 1) {
                    return channel;
                }
                channel.close();
                final Path parent = path.getParent();
                final String basename = FilenameUtils.removeExtension(Objects.toString(path.getFileName(), null));
                return ZipSplitReadOnlySeekableByteChannel.forPaths((List<Path>) IntStream.range(0, (int) numberOfDisks).mapToObj(new IntFunction() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda2
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i) {
                        return ZipFile.lambda$openZipChannel$0(numberOfDisks, path, parent, basename, i);
                    }
                }).collect(Collectors.toList()), openOptions);
            } catch (Throwable th2) {
                ex = th2;
                IOUtils.closeQuietly(channel);
                throw ex;
            }
        } catch (Throwable th3) {
            ex = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Path lambda$openZipChannel$0(long numberOfDisks, Path path, Path parent, String basename, int i) {
        if (i == numberOfDisks - 1) {
            return path;
        }
        Path lowercase = parent.resolve(String.format("%s.z%02d", basename, Integer.valueOf(i + 1)));
        if (Files.exists(lowercase, new LinkOption[0])) {
            return lowercase;
        }
        Path uppercase = parent.resolve(String.format("%s.Z%02d", basename, Integer.valueOf(i + 1)));
        if (Files.exists(uppercase, new LinkOption[0])) {
            return uppercase;
        }
        return lowercase;
    }

    private static boolean positionAtEndOfCentralDirectoryRecord(SeekableByteChannel channel) throws IOException {
        boolean found = tryToLocateSignature(channel, 22L, 65557L, ZipArchiveOutputStream.EOCD_SIG);
        if (!found) {
            throw new ZipException("Archive is not a ZIP archive");
        }
        boolean found64 = false;
        long position = channel.position();
        if (position > 20) {
            ByteBuffer wordBuf = ByteBuffer.allocate(4);
            channel.position(channel.position() - 20);
            wordBuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(channel, wordBuf);
            wordBuf.flip();
            found64 = wordBuf.equals(ByteBuffer.wrap(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG));
            if (!found64) {
                channel.position(position);
            } else {
                channel.position(channel.position() - 4);
            }
        }
        return found64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toPlatform(int versionMadeBy) {
        return (versionMadeBy >> 8) & 15;
    }

    private static boolean tryToLocateSignature(SeekableByteChannel channel, long minDistanceFromEnd, long maxDistanceFromEnd, byte[] sig) throws IOException {
        ByteBuffer wordBuf = ByteBuffer.allocate(4);
        boolean found = false;
        long off = channel.size() - minDistanceFromEnd;
        long stopSearching = Math.max(0L, channel.size() - maxDistanceFromEnd);
        if (off >= 0) {
            while (true) {
                if (off < stopSearching) {
                    break;
                }
                channel.position(off);
                try {
                    wordBuf.rewind();
                    org.apache.commons.compress.utils.IOUtils.readFully(channel, wordBuf);
                    wordBuf.flip();
                    int curr = wordBuf.get();
                    if (curr == sig[0]) {
                        int curr2 = wordBuf.get();
                        if (curr2 == sig[1]) {
                            int curr3 = wordBuf.get();
                            if (curr3 == sig[2]) {
                                int curr4 = wordBuf.get();
                                if (curr4 == sig[3]) {
                                    found = true;
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                    off--;
                } catch (EOFException e) {
                }
            }
        }
        if (found) {
            channel.position(off);
        }
        return found;
    }

    @Deprecated
    public ZipFile(File file) throws IOException {
        this(file, DEFAULT_CHARSET_NAME);
    }

    @Deprecated
    public ZipFile(File file, String encoding) throws IOException {
        this(file.toPath(), encoding, true);
    }

    @Deprecated
    public ZipFile(File file, String encoding, boolean useUnicodeExtraFields) throws IOException {
        this(file.toPath(), encoding, useUnicodeExtraFields, false);
    }

    @Deprecated
    public ZipFile(File file, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
        this(newReadByteChannel(file.toPath()), file.getAbsolutePath(), encoding, useUnicodeExtraFields, true, ignoreLocalFileHeader);
    }

    @Deprecated
    public ZipFile(Path path) throws IOException {
        this(path, DEFAULT_CHARSET_NAME);
    }

    @Deprecated
    public ZipFile(Path path, String encoding) throws IOException {
        this(path, encoding, true);
    }

    @Deprecated
    public ZipFile(Path path, String encoding, boolean useUnicodeExtraFields) throws IOException {
        this(path, encoding, useUnicodeExtraFields, false);
    }

    @Deprecated
    public ZipFile(Path path, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
        this(newReadByteChannel(path), path.toAbsolutePath().toString(), encoding, useUnicodeExtraFields, true, ignoreLocalFileHeader);
    }

    @Deprecated
    public ZipFile(SeekableByteChannel channel) throws IOException {
        this(channel, "a SeekableByteChannel", DEFAULT_CHARSET_NAME, true);
    }

    @Deprecated
    public ZipFile(SeekableByteChannel channel, String encoding) throws IOException {
        this(channel, "a SeekableByteChannel", encoding, true);
    }

    private ZipFile(SeekableByteChannel channel, String channelDescription, Charset encoding, boolean useUnicodeExtraFields, boolean closeOnError, boolean ignoreLocalFileHeader, IOFunction<InputStream, InputStream> zstdInputStream) throws IOException {
        this.entries = new LinkedList();
        this.nameMap = new HashMap(509);
        this.closed = true;
        this.dwordBuf = new byte[8];
        this.wordBuf = new byte[4];
        this.cfhBuf = new byte[42];
        this.shortBuf = new byte[2];
        this.dwordBbuf = ByteBuffer.wrap(this.dwordBuf);
        this.wordBbuf = ByteBuffer.wrap(this.wordBuf);
        this.cfhBbuf = ByteBuffer.wrap(this.cfhBuf);
        this.shortBbuf = ByteBuffer.wrap(this.shortBuf);
        this.isSplitZipArchive = channel instanceof ZipSplitReadOnlySeekableByteChannel;
        this.encoding = Charsets.toCharset(encoding, Builder.DEFAULT_CHARSET);
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.useUnicodeExtraFields = useUnicodeExtraFields;
        this.archive = channel;
        this.zstdInputStreamFactory = zstdInputStream;
        boolean success = false;
        try {
            try {
                Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag = populateFromCentralDirectory();
                if (!ignoreLocalFileHeader) {
                    resolveLocalFileHeaderData(entriesWithoutUTF8Flag);
                }
                fillNameMap();
                success = true;
            } catch (IOException e) {
                throw new IOException("Error reading Zip content from " + channelDescription, e);
            }
        } finally {
            this.closed = !success;
            if (!success && closeOnError) {
                IOUtils.closeQuietly(this.archive);
            }
        }
    }

    @Deprecated
    public ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields) throws IOException {
        this(channel, channelDescription, encoding, useUnicodeExtraFields, false, false);
    }

    @Deprecated
    public ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
        this(channel, channelDescription, encoding, useUnicodeExtraFields, false, ignoreLocalFileHeader);
    }

    private ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields, boolean closeOnError, boolean ignoreLocalFileHeader) throws IOException {
        this(channel, channelDescription, Charsets.toCharset(encoding), useUnicodeExtraFields, closeOnError, ignoreLocalFileHeader, null);
    }

    @Deprecated
    public ZipFile(String name) throws IOException {
        this(new File(name).toPath(), DEFAULT_CHARSET_NAME);
    }

    @Deprecated
    public ZipFile(String name, String encoding) throws IOException {
        this(new File(name).toPath(), encoding, true);
    }

    public boolean canReadEntryData(ZipArchiveEntry entry) {
        return ZipUtil.canHandleEntryData(entry);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        this.archive.close();
    }

    public void copyRawEntries(ZipArchiveOutputStream target, ZipArchiveEntryPredicate predicate) throws IOException {
        Enumeration<ZipArchiveEntry> src = getEntriesInPhysicalOrder();
        while (src.hasMoreElements()) {
            ZipArchiveEntry entry = src.nextElement();
            if (predicate.test(entry)) {
                target.addRawArchiveEntry(entry, getRawInputStream(entry));
            }
        }
    }

    private BoundedArchiveInputStream createBoundedInputStream(long start, long remaining) {
        if (start < 0 || remaining < 0 || start + remaining < start) {
            throw new IllegalArgumentException("Corrupted archive, stream boundaries are out of range");
        }
        return this.archive instanceof FileChannel ? new BoundedFileChannelInputStream(start, remaining, (FileChannel) this.archive) : new BoundedSeekableByteChannelInputStream(start, remaining, this.archive);
    }

    InputStream createZstdInputStream(InputStream in) throws IOException {
        return this.zstdInputStreamFactory != null ? this.zstdInputStreamFactory.apply(in) : new ZstdCompressorInputStream(in);
    }

    private void fillNameMap() {
        this.entries.forEach(new Consumer() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ZipFile.this.m2061xc4ffbf12((ZipArchiveEntry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fillNameMap$2$org-apache-commons-compress-archivers-zip-ZipFile, reason: not valid java name */
    public /* synthetic */ void m2061xc4ffbf12(ZipArchiveEntry ze) {
        String name = ze.getName();
        LinkedList<ZipArchiveEntry> entriesOfThatName = this.nameMap.computeIfAbsent(name, new Function() { // from class: org.apache.commons.compress.archivers.zip.ZipFile$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ZipFile.lambda$fillNameMap$1((String) obj);
            }
        });
        entriesOfThatName.addLast(ze);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LinkedList lambda$fillNameMap$1(String k) {
        return new LinkedList();
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.closed) {
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public InputStream getContentBeforeFirstLocalFileHeader() {
        if (this.firstLocalFileHeaderOffset == 0) {
            return null;
        }
        return createBoundedInputStream(0L, this.firstLocalFileHeaderOffset);
    }

    private long getDataOffset(ZipArchiveEntry ze) throws IOException {
        long s = ze.getDataOffset();
        if (s == -1) {
            setDataOffset(ze);
            return ze.getDataOffset();
        }
        return s;
    }

    public String getEncoding() {
        return this.encoding.name();
    }

    public Enumeration<ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.entries);
    }

    public Iterable<ZipArchiveEntry> getEntries(String name) {
        return this.nameMap.getOrDefault(name, ZipArchiveEntry.EMPTY_LINKED_LIST);
    }

    public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
        ZipArchiveEntry[] allEntries = (ZipArchiveEntry[]) this.entries.toArray(ZipArchiveEntry.EMPTY_ARRAY);
        return Collections.enumeration(Arrays.asList(sortByOffset(allEntries)));
    }

    public Iterable<ZipArchiveEntry> getEntriesInPhysicalOrder(String name) {
        LinkedList<ZipArchiveEntry> linkedList = this.nameMap.getOrDefault(name, ZipArchiveEntry.EMPTY_LINKED_LIST);
        return Arrays.asList(sortByOffset((ZipArchiveEntry[]) linkedList.toArray(ZipArchiveEntry.EMPTY_ARRAY)));
    }

    public ZipArchiveEntry getEntry(String name) {
        LinkedList<ZipArchiveEntry> entries = this.nameMap.get(name);
        if (entries != null) {
            return entries.getFirst();
        }
        return null;
    }

    public long getFirstLocalFileHeaderOffset() {
        return this.firstLocalFileHeaderOffset;
    }

    public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        if (!(entry instanceof Entry)) {
            return null;
        }
        ZipUtil.checkRequestedFeatures(entry);
        InputStream is = new BufferedInputStream(getRawInputStream(entry));
        switch (ZipMethod.getMethodByCode(entry.getMethod())) {
            case STORED:
                return new StoredStatisticsStream(is);
            case UNSHRINKING:
                return new UnshrinkingInputStream(is);
            case IMPLODING:
                try {
                    return new ExplodingInputStream(entry.getGeneralPurposeBit().getSlidingDictionarySize(), entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), is);
                } catch (IllegalArgumentException ex) {
                    throw new IOException("bad IMPLODE data", ex);
                }
            case DEFLATED:
                final Inflater inflater = new Inflater(true);
                return new InflaterInputStreamWithStatistics(new SequenceInputStream(is, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater) { // from class: org.apache.commons.compress.archivers.zip.ZipFile.1
                    @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        try {
                            super.close();
                        } finally {
                            inflater.end();
                        }
                    }
                };
            case BZIP2:
                return new BZip2CompressorInputStream(is);
            case ENHANCED_DEFLATED:
                return new Deflate64CompressorInputStream(is);
            case ZSTD:
            case ZSTD_DEPRECATED:
                return createZstdInputStream(is);
            case XZ:
                return new XZCompressorInputStream(is);
            default:
                throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(entry.getMethod()), entry);
        }
    }

    public InputStream getRawInputStream(ZipArchiveEntry entry) throws IOException {
        if (!(entry instanceof Entry)) {
            return null;
        }
        long start = getDataOffset(entry);
        if (start == -1) {
            return null;
        }
        return createBoundedInputStream(start, entry.getCompressedSize());
    }

    public String getUnixSymlink(ZipArchiveEntry entry) throws IOException {
        if (entry != null && entry.isUnixSymlink()) {
            InputStream in = getInputStream(entry);
            try {
                String decode = this.zipEncoding.decode(IOUtils.toByteArray(in));
                if (in != null) {
                    in.close();
                }
                return decode;
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
        return null;
    }

    private Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
        HashMap<ZipArchiveEntry, NameAndComment> noUTF8Flag = new HashMap<>();
        positionAtCentralDirectory();
        this.centralDirectoryStartOffset = this.archive.position();
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        long sig = ZipLong.getValue(this.wordBuf);
        if (sig != CFH_SIG && startsWithLocalFileHeader()) {
            throw new IOException("Central directory is empty, can't expand corrupt archive.");
        }
        while (sig == CFH_SIG) {
            readCentralDirectoryEntry(noUTF8Flag);
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
            sig = ZipLong.getValue(this.wordBuf);
        }
        return noUTF8Flag;
    }

    private void positionAtCentralDirectory() throws IOException {
        boolean is64 = positionAtEndOfCentralDirectoryRecord(this.archive);
        if (!is64) {
            positionAtCentralDirectory32();
        } else {
            positionAtCentralDirectory64();
        }
    }

    private void positionAtCentralDirectory32() throws IOException {
        long endOfCentralDirectoryRecordOffset = this.archive.position();
        if (this.isSplitZipArchive) {
            skipBytes(6);
            this.shortBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.shortBbuf);
            this.centralDirectoryStartDiskNumber = ZipShort.getValue(this.shortBuf);
            skipBytes(8);
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
            this.centralDirectoryStartRelativeOffset = ZipLong.getValue(this.wordBuf);
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, this.centralDirectoryStartRelativeOffset);
            return;
        }
        skipBytes(12);
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        long centralDirectoryLength = ZipLong.getValue(this.wordBuf);
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        this.centralDirectoryStartDiskNumber = 0L;
        this.centralDirectoryStartRelativeOffset = ZipLong.getValue(this.wordBuf);
        this.firstLocalFileHeaderOffset = Long.max((endOfCentralDirectoryRecordOffset - centralDirectoryLength) - this.centralDirectoryStartRelativeOffset, 0L);
        this.archive.position(this.centralDirectoryStartRelativeOffset + this.firstLocalFileHeaderOffset);
    }

    private void positionAtCentralDirectory64() throws IOException {
        skipBytes(4);
        if (this.isSplitZipArchive) {
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
            long diskNumberOfEOCD = ZipLong.getValue(this.wordBuf);
            this.dwordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.dwordBbuf);
            long relativeOffsetOfEOCD = ZipEightByteInteger.getLongValue(this.dwordBuf);
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(diskNumberOfEOCD, relativeOffsetOfEOCD);
        } else {
            skipBytes(4);
            this.dwordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.dwordBbuf);
            this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
        }
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        if (!Arrays.equals(this.wordBuf, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
            throw new ZipException("Archive's ZIP64 end of central directory locator is corrupt.");
        }
        if (this.isSplitZipArchive) {
            skipBytes(16);
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
            this.centralDirectoryStartDiskNumber = ZipLong.getValue(this.wordBuf);
            skipBytes(24);
            this.dwordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.dwordBbuf);
            this.centralDirectoryStartRelativeOffset = ZipEightByteInteger.getLongValue(this.dwordBuf);
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, this.centralDirectoryStartRelativeOffset);
            return;
        }
        skipBytes(44);
        this.dwordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.dwordBbuf);
        this.centralDirectoryStartDiskNumber = 0L;
        this.centralDirectoryStartRelativeOffset = ZipEightByteInteger.getLongValue(this.dwordBuf);
        this.archive.position(this.centralDirectoryStartRelativeOffset);
    }

    private void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> noUTF8Flag) throws IOException {
        this.cfhBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.cfhBbuf);
        Entry ze = new Entry();
        int versionMadeBy = ZipShort.getValue(this.cfhBuf, 0);
        int off = 0 + 2;
        ze.setVersionMadeBy(versionMadeBy);
        ze.setPlatform(toPlatform(versionMadeBy));
        ze.setVersionRequired(ZipShort.getValue(this.cfhBuf, off));
        int off2 = off + 2;
        GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(this.cfhBuf, off2);
        boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
        ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.ZIP_ENCODING_UTF_8 : this.zipEncoding;
        if (hasUTF8Flag) {
            ze.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
        }
        ze.setGeneralPurposeBit(gpFlag);
        ze.setRawFlag(ZipShort.getValue(this.cfhBuf, off2));
        int off3 = off2 + 2;
        ze.setMethod(ZipShort.getValue(this.cfhBuf, off3));
        int off4 = off3 + 2;
        long time = ZipUtil.dosToJavaTime(ZipLong.getValue(this.cfhBuf, off4));
        ze.setTime(time);
        int off5 = off4 + 4;
        ze.setCrc(ZipLong.getValue(this.cfhBuf, off5));
        int off6 = off5 + 4;
        long size = ZipLong.getValue(this.cfhBuf, off6);
        if (size < 0) {
            throw new IOException("broken archive, entry with negative compressed size");
        }
        ze.setCompressedSize(size);
        int off7 = off6 + 4;
        long size2 = ZipLong.getValue(this.cfhBuf, off7);
        if (size2 < 0) {
            throw new IOException("broken archive, entry with negative size");
        }
        ze.setSize(size2);
        int off8 = off7 + 4;
        int fileNameLen = ZipShort.getValue(this.cfhBuf, off8);
        int off9 = off8 + 2;
        if (fileNameLen < 0) {
            throw new IOException("broken archive, entry with negative fileNameLen");
        }
        int extraLen = ZipShort.getValue(this.cfhBuf, off9);
        int off10 = off9 + 2;
        if (extraLen < 0) {
            throw new IOException("broken archive, entry with negative extraLen");
        }
        int commentLen = ZipShort.getValue(this.cfhBuf, off10);
        int off11 = off10 + 2;
        if (commentLen < 0) {
            throw new IOException("broken archive, entry with negative commentLen");
        }
        ze.setDiskNumberStart(ZipShort.getValue(this.cfhBuf, off11));
        int off12 = off11 + 2;
        ze.setInternalAttributes(ZipShort.getValue(this.cfhBuf, off12));
        int off13 = off12 + 2;
        ze.setExternalAttributes(ZipLong.getValue(this.cfhBuf, off13));
        int off14 = off13 + 4;
        byte[] fileName = org.apache.commons.compress.utils.IOUtils.readRange(this.archive, fileNameLen);
        if (fileName.length < fileNameLen) {
            throw new EOFException();
        }
        ze.setName(entryEncoding.decode(fileName), fileName);
        ze.setLocalHeaderOffset(ZipLong.getValue(this.cfhBuf, off14) + this.firstLocalFileHeaderOffset);
        this.entries.add(ze);
        byte[] cdExtraData = org.apache.commons.compress.utils.IOUtils.readRange(this.archive, extraLen);
        if (cdExtraData.length < extraLen) {
            throw new EOFException();
        }
        try {
            ze.setCentralDirectoryExtra(cdExtraData);
            setSizesAndOffsetFromZip64Extra(ze);
            sanityCheckLFHOffset(ze);
            byte[] comment = org.apache.commons.compress.utils.IOUtils.readRange(this.archive, commentLen);
            if (comment.length < commentLen) {
                throw new EOFException();
            }
            ze.setComment(entryEncoding.decode(comment));
            if (!hasUTF8Flag && this.useUnicodeExtraFields) {
                noUTF8Flag.put(ze, new NameAndComment(fileName, comment));
            }
            ze.setStreamContiguous(true);
        } catch (RuntimeException e) {
            throw ZipUtil.newZipException("Invalid extra data in entry " + ze.getName(), e);
        }
    }

    private void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag) throws IOException {
        for (ZipArchiveEntry zipArchiveEntry : this.entries) {
            Entry ze = (Entry) zipArchiveEntry;
            int[] lens = setDataOffset(ze);
            int fileNameLen = lens[0];
            int extraFieldLen = lens[1];
            skipBytes(fileNameLen);
            byte[] localExtraData = org.apache.commons.compress.utils.IOUtils.readRange(this.archive, extraFieldLen);
            if (localExtraData.length < extraFieldLen) {
                throw new EOFException();
            }
            try {
                ze.setExtra(localExtraData);
                if (entriesWithoutUTF8Flag.containsKey(ze)) {
                    NameAndComment nc = entriesWithoutUTF8Flag.get(ze);
                    ZipUtil.setNameAndCommentFromExtraFields(ze, nc.name, nc.comment);
                }
            } catch (RuntimeException e) {
                throw ZipUtil.newZipException("Invalid extra data in entry " + ze.getName(), e);
            }
        }
    }

    private void sanityCheckLFHOffset(ZipArchiveEntry entry) throws IOException {
        if (entry.getDiskNumberStart() < 0) {
            throw new IOException("broken archive, entry with negative disk number");
        }
        if (entry.getLocalHeaderOffset() < 0) {
            throw new IOException("broken archive, entry with negative local file header offset");
        }
        if (!this.isSplitZipArchive) {
            if (entry.getLocalHeaderOffset() > this.centralDirectoryStartOffset) {
                throw new IOException("local file header for " + entry.getName() + " starts after central directory");
            }
        } else {
            if (entry.getDiskNumberStart() > this.centralDirectoryStartDiskNumber) {
                throw new IOException("local file header for " + entry.getName() + " starts on a later disk than central directory");
            }
            if (entry.getDiskNumberStart() == this.centralDirectoryStartDiskNumber && entry.getLocalHeaderOffset() > this.centralDirectoryStartRelativeOffset) {
                throw new IOException("local file header for " + entry.getName() + " starts after central directory");
            }
        }
    }

    private int[] setDataOffset(ZipArchiveEntry entry) throws IOException {
        long offset = entry.getLocalHeaderOffset();
        if (this.isSplitZipArchive) {
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(entry.getDiskNumberStart(), offset + LFH_OFFSET_FOR_FILENAME_LENGTH);
            offset = this.archive.position() - LFH_OFFSET_FOR_FILENAME_LENGTH;
        } else {
            this.archive.position(offset + LFH_OFFSET_FOR_FILENAME_LENGTH);
        }
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        this.wordBbuf.flip();
        this.wordBbuf.get(this.shortBuf);
        int fileNameLen = ZipShort.getValue(this.shortBuf);
        this.wordBbuf.get(this.shortBuf);
        int extraFieldLen = ZipShort.getValue(this.shortBuf);
        entry.setDataOffset(LFH_OFFSET_FOR_FILENAME_LENGTH + offset + 2 + 2 + fileNameLen + extraFieldLen);
        if (entry.getDataOffset() + entry.getCompressedSize() > this.centralDirectoryStartOffset) {
            throw new IOException("data for " + entry.getName() + " overlaps with central directory.");
        }
        return new int[]{fileNameLen, extraFieldLen};
    }

    private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry entry) throws IOException {
        ZipExtraField extra = entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extra != null && !(extra instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField) extra;
        if (z64 != null) {
            boolean hasUncompressedSize = entry.getSize() == 4294967295L;
            boolean hasCompressedSize = entry.getCompressedSize() == 4294967295L;
            boolean hasRelativeHeaderOffset = entry.getLocalHeaderOffset() == 4294967295L;
            boolean hasDiskStart = entry.getDiskNumberStart() == 65535;
            z64.reparseCentralDirectoryData(hasUncompressedSize, hasCompressedSize, hasRelativeHeaderOffset, hasDiskStart);
            if (hasUncompressedSize) {
                long size = z64.getSize().getLongValue();
                if (size < 0) {
                    throw new IOException("broken archive, entry with negative size");
                }
                entry.setSize(size);
            } else if (hasCompressedSize) {
                z64.setSize(new ZipEightByteInteger(entry.getSize()));
            }
            if (hasCompressedSize) {
                long size2 = z64.getCompressedSize().getLongValue();
                if (size2 < 0) {
                    throw new IOException("broken archive, entry with negative compressed size");
                }
                entry.setCompressedSize(size2);
            } else if (hasUncompressedSize) {
                z64.setCompressedSize(new ZipEightByteInteger(entry.getCompressedSize()));
            }
            if (hasRelativeHeaderOffset) {
                entry.setLocalHeaderOffset(z64.getRelativeHeaderOffset().getLongValue());
            }
            if (hasDiskStart) {
                entry.setDiskNumberStart(z64.getDiskStartNumber().getValue());
            }
        }
    }

    private void skipBytes(int count) throws IOException {
        long currentPosition = this.archive.position();
        long newPosition = count + currentPosition;
        if (newPosition > this.archive.size()) {
            throw new EOFException();
        }
        this.archive.position(newPosition);
    }

    private ZipArchiveEntry[] sortByOffset(ZipArchiveEntry[] allEntries) {
        Arrays.sort(allEntries, offsetComparator);
        return allEntries;
    }

    private boolean startsWithLocalFileHeader() throws IOException {
        this.archive.position(this.firstLocalFileHeaderOffset);
        this.wordBbuf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.archive, this.wordBbuf);
        return Arrays.equals(this.wordBuf, ZipArchiveOutputStream.LFH_SIG);
    }

    public IOStream<? extends ZipArchiveEntry> stream() {
        return IOStream.adapt(this.entries.stream());
    }
}
