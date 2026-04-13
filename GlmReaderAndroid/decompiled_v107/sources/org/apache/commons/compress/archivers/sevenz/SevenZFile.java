package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import kotlin.UByte;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.ChecksumInputStream;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
public class SevenZFile implements Closeable {
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    static final int SIGNATURE_HEADER_SIZE = 32;
    static final byte[] sevenZSignature = {TarConstants.LF_CONTIG, 122, -68, -81, 39, 28};
    private final Archive archive;
    private SeekableByteChannel channel;
    private long compressedBytesReadFromCurrentEntry;
    private int currentEntryIndex;
    private int currentFolderIndex;
    private InputStream currentFolderInputStream;
    private final ArrayList<InputStream> deferredBlockStreams;
    private final String fileName;
    private final int maxMemoryLimitKiB;
    private byte[] password;
    private final boolean tryToRecoverBrokenArchives;
    private long uncompressedBytesReadFromCurrentEntry;
    private final boolean useDefaultNameForUnnamedEntries;

    static /* synthetic */ long access$114(SevenZFile x0, long x1) {
        long j = x0.compressedBytesReadFromCurrentEntry + x1;
        x0.compressedBytesReadFromCurrentEntry = j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ArchiveStatistics {
        private BitSet folderHasCrc;
        private long numberOfCoders;
        private int numberOfEntries;
        private int numberOfEntriesWithStream;
        private int numberOfFolders;
        private long numberOfInStreams;
        private long numberOfOutStreams;
        private int numberOfPackedStreams;
        private long numberOfUnpackSubStreams;

        private ArchiveStatistics() {
        }

        static /* synthetic */ long access$614(ArchiveStatistics x0, long x1) {
            long j = x0.numberOfCoders + x1;
            x0.numberOfCoders = j;
            return j;
        }

        static /* synthetic */ long access$714(ArchiveStatistics x0, long x1) {
            long j = x0.numberOfOutStreams + x1;
            x0.numberOfOutStreams = j;
            return j;
        }

        static /* synthetic */ long access$814(ArchiveStatistics x0, long x1) {
            long j = x0.numberOfInStreams + x1;
            x0.numberOfInStreams = j;
            return j;
        }

        void assertValidity(int maxMemoryLimitKiB) throws IOException {
            if (this.numberOfEntriesWithStream > 0 && this.numberOfFolders == 0) {
                throw new IOException("archive with entries but no folders");
            }
            if (this.numberOfEntriesWithStream > this.numberOfUnpackSubStreams) {
                throw new IOException("archive doesn't contain enough substreams for entries");
            }
            long memoryNeededInKiB = estimateSize() / FileUtils.ONE_KB;
            if (maxMemoryLimitKiB < memoryNeededInKiB) {
                throw new MemoryLimitException(memoryNeededInKiB, maxMemoryLimitKiB);
            }
        }

        private long bindPairSize() {
            return 16L;
        }

        private long coderSize() {
            return 22L;
        }

        private long entrySize() {
            return 100L;
        }

        long estimateSize() {
            long lowerBound = (this.numberOfPackedStreams * 16) + (this.numberOfPackedStreams / 8) + (this.numberOfFolders * folderSize()) + (this.numberOfCoders * coderSize()) + ((this.numberOfOutStreams - this.numberOfFolders) * bindPairSize()) + (((this.numberOfInStreams - this.numberOfOutStreams) + this.numberOfFolders) * 8) + (this.numberOfOutStreams * 8) + (this.numberOfEntries * entrySize()) + streamMapSize();
            return 2 * lowerBound;
        }

        private long folderSize() {
            return 30L;
        }

        private long streamMapSize() {
            return (this.numberOfFolders * 8) + (this.numberOfPackedStreams * 8) + (this.numberOfEntries * 4);
        }

        public String toString() {
            return String.format("Archive with %,d entries in %,d folders, estimated size %,d KiB.", Integer.valueOf(this.numberOfEntries), Integer.valueOf(this.numberOfFolders), Long.valueOf(estimateSize() / FileUtils.ONE_KB));
        }
    }

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<SevenZFile, Builder> {
        static final int MEMORY_LIMIT_IN_KB = Integer.MAX_VALUE;
        static final boolean TRY_TO_RECOVER_BROKEN_ARCHIVES = false;
        static final boolean USE_DEFAULTNAME_FOR_UNNAMED_ENTRIES = false;
        private byte[] password;
        private SeekableByteChannel seekableByteChannel;
        private String defaultName = SevenZFile.DEFAULT_FILE_NAME;
        private int maxMemoryLimitKiB = Integer.MAX_VALUE;
        private boolean useDefaultNameForUnnamedEntries = false;
        private boolean tryToRecoverBrokenArchives = false;

        @Override // org.apache.commons.io.function.IOSupplier
        public SevenZFile get() throws IOException {
            SeekableByteChannel actualChannel;
            String actualDescription;
            if (this.seekableByteChannel != null) {
                SeekableByteChannel actualChannel2 = this.seekableByteChannel;
                actualChannel = actualChannel2;
                actualDescription = this.defaultName;
            } else if (checkOrigin() instanceof AbstractOrigin.ByteArrayOrigin) {
                SeekableByteChannel actualChannel3 = new SeekableInMemoryByteChannel(checkOrigin().getByteArray());
                actualChannel = actualChannel3;
                actualDescription = this.defaultName;
            } else {
                OpenOption[] openOptions = getOpenOptions();
                if (ArrayUtils.isEmpty(openOptions)) {
                    openOptions = new OpenOption[]{StandardOpenOption.READ};
                }
                Path path = getPath();
                SeekableByteChannel actualChannel4 = Files.newByteChannel(path, openOptions);
                actualChannel = actualChannel4;
                actualDescription = path.toAbsolutePath().toString();
            }
            boolean closeOnError = this.seekableByteChannel != null;
            return new SevenZFile(actualChannel, actualDescription, this.password, closeOnError, this.maxMemoryLimitKiB, this.useDefaultNameForUnnamedEntries, this.tryToRecoverBrokenArchives);
        }

        public Builder setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public Builder setMaxMemoryLimitKb(int maxMemoryLimitKiB) {
            this.maxMemoryLimitKiB = maxMemoryLimitKiB / 1024;
            return this;
        }

        public Builder setMaxMemoryLimitKiB(int maxMemoryLimitKiB) {
            this.maxMemoryLimitKiB = maxMemoryLimitKiB;
            return this;
        }

        public Builder setPassword(byte[] password) {
            this.password = password != null ? (byte[]) password.clone() : null;
            return this;
        }

        public Builder setPassword(char[] password) {
            this.password = password != null ? AES256SHA256Decoder.utf16Decode((char[]) password.clone()) : null;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password != null ? AES256SHA256Decoder.utf16Decode(password.toCharArray()) : null;
            return this;
        }

        public Builder setSeekableByteChannel(SeekableByteChannel seekableByteChannel) {
            this.seekableByteChannel = seekableByteChannel;
            return this;
        }

        public Builder setTryToRecoverBrokenArchives(boolean tryToRecoverBrokenArchives) {
            this.tryToRecoverBrokenArchives = tryToRecoverBrokenArchives;
            return this;
        }

        public Builder setUseDefaultNameForUnnamedEntries(boolean useDefaultNameForUnnamedEntries) {
            this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
            return this;
        }
    }

    private static int assertFitsIntoNonNegativeInt(String what, long value) throws IOException {
        if (value > 2147483647L || value < 0) {
            throw new IOException(String.format("Cannot handle % %,d", what, Long.valueOf(value)));
        }
        return (int) value;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static ByteBuffer checkEndOfFile(ByteBuffer buf, int expectRemaining) throws EOFException {
        int remaining = buf.remaining();
        if (remaining < expectRemaining) {
            throw new EOFException(String.format("remaining %,d < expectRemaining %,d", Integer.valueOf(remaining), Integer.valueOf(expectRemaining)));
        }
        return buf;
    }

    private static void get(ByteBuffer buf, byte[] to) throws EOFException {
        checkEndOfFile(buf, to.length).get(to);
    }

    private static char getChar(ByteBuffer buf) throws EOFException {
        return checkEndOfFile(buf, 2).getChar();
    }

    private static int getInt(ByteBuffer buf) throws EOFException {
        return checkEndOfFile(buf, 4).getInt();
    }

    private static long getLong(ByteBuffer buf) throws EOFException {
        return checkEndOfFile(buf, 8).getLong();
    }

    private static int getUnsignedByte(ByteBuffer buf) throws EOFException {
        if (!buf.hasRemaining()) {
            throw new EOFException();
        }
        return buf.get() & UByte.MAX_VALUE;
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < sevenZSignature.length) {
            return false;
        }
        for (int i = 0; i < sevenZSignature.length; i++) {
            if (signature[i] != sevenZSignature[i]) {
                return false;
            }
        }
        return true;
    }

    private static SeekableByteChannel newByteChannel(File file) throws IOException {
        return Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]);
    }

    private static long readUint64(ByteBuffer in) throws IOException {
        long firstByte = getUnsignedByte(in);
        int mask = 128;
        long value = 0;
        for (int i = 0; i < 8; i++) {
            if ((mask & firstByte) == 0) {
                return (((mask - 1) & firstByte) << (i * 8)) | value;
            }
            long nextByte = getUnsignedByte(in);
            value |= nextByte << (i * 8);
            mask >>>= 1;
        }
        return value;
    }

    private static long skipBytesFully(ByteBuffer input, long bytesToSkip) {
        if (bytesToSkip < 1) {
            return 0L;
        }
        int current = input.position();
        int maxSkip = input.remaining();
        if (maxSkip < bytesToSkip) {
            bytesToSkip = maxSkip;
        }
        input.position(((int) bytesToSkip) + current);
        return bytesToSkip;
    }

    @Deprecated
    public SevenZFile(File fileName) throws IOException {
        this(fileName, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(File file, byte[] password) throws IOException {
        this(newByteChannel(file), file.getAbsolutePath(), password, true, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(File file, char[] password) throws IOException {
        this(file, password, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(File file, char[] password, SevenZFileOptions options) throws IOException {
        this(newByteChannel(file), file.getAbsolutePath(), AES256SHA256Decoder.utf16Decode(password), true, options);
    }

    @Deprecated
    public SevenZFile(File file, SevenZFileOptions options) throws IOException {
        this(file, (char[]) null, options);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel) throws IOException {
        this(channel, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, byte[] password) throws IOException {
        this(channel, DEFAULT_FILE_NAME, password);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, char[] password) throws IOException {
        this(channel, password, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, char[] password, SevenZFileOptions options) throws IOException {
        this(channel, DEFAULT_FILE_NAME, password, options);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, SevenZFileOptions options) throws IOException {
        this(channel, DEFAULT_FILE_NAME, null, options);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, String fileName) throws IOException {
        this(channel, fileName, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, String fileName, byte[] password) throws IOException {
        this(channel, fileName, password, false, SevenZFileOptions.DEFAULT);
    }

    private SevenZFile(SeekableByteChannel channel, String fileName, byte[] password, boolean closeOnError, int maxMemoryLimitKiB, boolean useDefaultNameForUnnamedEntries, boolean tryToRecoverBrokenArchives) throws IOException {
        this.currentEntryIndex = -1;
        this.currentFolderIndex = -1;
        this.deferredBlockStreams = new ArrayList<>();
        boolean succeeded = false;
        this.channel = channel;
        this.fileName = fileName;
        this.maxMemoryLimitKiB = maxMemoryLimitKiB;
        this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
        this.tryToRecoverBrokenArchives = tryToRecoverBrokenArchives;
        try {
            this.archive = readHeaders(password);
            if (password != null) {
                this.password = Arrays.copyOf(password, password.length);
            } else {
                this.password = null;
            }
            succeeded = true;
        } finally {
            if (!succeeded && closeOnError) {
                this.channel.close();
            }
        }
    }

    @Deprecated
    private SevenZFile(SeekableByteChannel channel, String fileName, byte[] password, boolean closeOnError, SevenZFileOptions options) throws IOException {
        this(channel, fileName, password, closeOnError, options.getMaxMemoryLimitInKb(), options.getUseDefaultNameForUnnamedEntries(), options.getTryToRecoverBrokenArchives());
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, String fileName, char[] password) throws IOException {
        this(channel, fileName, password, SevenZFileOptions.DEFAULT);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, String fileName, char[] password, SevenZFileOptions options) throws IOException {
        this(channel, fileName, AES256SHA256Decoder.utf16Decode(password), false, options);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel channel, String fileName, SevenZFileOptions options) throws IOException {
        this(channel, fileName, null, false, options);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private InputStream buildDecoderStack(Folder folder, long folderOffset, int firstPackStreamIndex, SevenZArchiveEntry entry) throws IOException {
        this.channel.position(folderOffset);
        InputStream inputStreamStack = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[firstPackStreamIndex]))) { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.1
            private void count(int c) {
                SevenZFile.access$114(SevenZFile.this, c);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                int r = this.in.read();
                if (r >= 0) {
                    count(1);
                }
                return r;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] b) throws IOException {
                return read(b, 0, b.length);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] b, int off, int len) throws IOException {
                if (len == 0) {
                    return 0;
                }
                int r = this.in.read(b, off, len);
                if (r >= 0) {
                    count(r);
                }
                return r;
            }
        };
        LinkedList<SevenZMethodConfiguration> methods = new LinkedList<>();
        InputStream inputStreamStack2 = inputStreamStack;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            SevenZMethod method = SevenZMethod.byId(coder.decompressionMethodId);
            inputStreamStack2 = Coders.addDecoder(this.fileName, inputStreamStack2, folder.getUnpackSizeForCoder(coder), coder, this.password, this.maxMemoryLimitKiB);
            methods.addFirst(new SevenZMethodConfiguration(method, Coders.findByMethod(method).getOptionsFromCoder(coder, inputStreamStack2)));
        }
        entry.setContentMethods(methods);
        if (folder.hasCrc) {
            return ((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(inputStreamStack2)).setCountThreshold(folder.getUnpackSize()).setExpectedChecksumValue(folder.crc).get();
        }
        return inputStreamStack2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void buildDecodingStream(int entryIndex, boolean isRandomAccess) throws IOException {
        if (this.archive.streamMap == null) {
            throw new IOException("Archive doesn't contain stream information to read entries");
        }
        int folderIndex = this.archive.streamMap.fileFolderIndex[entryIndex];
        if (folderIndex < 0) {
            this.deferredBlockStreams.clear();
            return;
        }
        SevenZArchiveEntry file = this.archive.files[entryIndex];
        boolean isInSameFolder = false;
        if (this.currentFolderIndex == folderIndex) {
            if (entryIndex > 0) {
                file.setContentMethods(this.archive.files[entryIndex - 1].getContentMethods());
            }
            if (isRandomAccess && file.getContentMethods() == null) {
                int folderFirstFileIndex = this.archive.streamMap.folderFirstFileIndex[folderIndex];
                SevenZArchiveEntry folderFirstFile = this.archive.files[folderFirstFileIndex];
                file.setContentMethods(folderFirstFile.getContentMethods());
            }
            isInSameFolder = true;
        } else {
            this.currentFolderIndex = folderIndex;
            reopenFolderInputStream(folderIndex, file);
        }
        boolean haveSkippedEntries = false;
        if (isRandomAccess) {
            haveSkippedEntries = skipEntriesWhenNeeded(entryIndex, isInSameFolder, folderIndex);
        }
        if (isRandomAccess && this.currentEntryIndex == entryIndex && !haveSkippedEntries) {
            return;
        }
        InputStream fileStream = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.currentFolderInputStream)).setMaxCount(file.getSize())).setPropagateClose(false)).get();
        if (file.getHasCrc()) {
            fileStream = ((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(fileStream)).setExpectedChecksumValue(file.getCrcValue()).get();
        }
        this.deferredBlockStreams.add(fileStream);
    }

    private void calculateStreamMap(Archive archive) throws IOException {
        int nextFolderPackStreamIndex = 0;
        int numFolders = ArrayUtils.getLength(archive.folders);
        int[] folderFirstPackStreamIndex = new int[numFolders];
        for (int i = 0; i < numFolders; i++) {
            folderFirstPackStreamIndex[i] = nextFolderPackStreamIndex;
            nextFolderPackStreamIndex += archive.folders[i].packedStreams.length;
        }
        long nextPackStreamOffset = 0;
        int numPackSizes = archive.packSizes.length;
        long[] packStreamOffsets = new long[numPackSizes];
        for (int i2 = 0; i2 < numPackSizes; i2++) {
            packStreamOffsets[i2] = nextPackStreamOffset;
            nextPackStreamOffset += archive.packSizes[i2];
        }
        int[] folderFirstFileIndex = new int[numFolders];
        int[] fileFolderIndex = new int[archive.files.length];
        int nextFolderIndex = 0;
        int nextFolderUnpackStreamIndex = 0;
        for (int i3 = 0; i3 < archive.files.length; i3++) {
            if (archive.files[i3].isEmptyStream() && nextFolderUnpackStreamIndex == 0) {
                fileFolderIndex[i3] = -1;
            } else {
                if (nextFolderUnpackStreamIndex == 0) {
                    while (nextFolderIndex < archive.folders.length) {
                        folderFirstFileIndex[nextFolderIndex] = i3;
                        if (archive.folders[nextFolderIndex].numUnpackSubStreams > 0) {
                            break;
                        } else {
                            nextFolderIndex++;
                        }
                    }
                    if (nextFolderIndex >= archive.folders.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                fileFolderIndex[i3] = nextFolderIndex;
                if (!archive.files[i3].isEmptyStream() && (nextFolderUnpackStreamIndex = nextFolderUnpackStreamIndex + 1) >= archive.folders[nextFolderIndex].numUnpackSubStreams) {
                    nextFolderIndex++;
                    nextFolderUnpackStreamIndex = 0;
                }
            }
        }
        archive.streamMap = new StreamMap(folderFirstPackStreamIndex, packStreamOffsets, folderFirstFileIndex, fileFolderIndex);
    }

    private void checkEntryIsInitialized(Map<Integer, SevenZArchiveEntry> archiveEntries, int index) {
        archiveEntries.computeIfAbsent(Integer.valueOf(index), new Function() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SevenZFile.lambda$checkEntryIsInitialized$0((Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SevenZArchiveEntry lambda$checkEntryIsInitialized$0(Integer i) {
        return new SevenZArchiveEntry();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.channel != null) {
            try {
                this.channel.close();
            } finally {
                this.channel = null;
                if (this.password != null) {
                    Arrays.fill(this.password, (byte) 0);
                }
                this.password = null;
            }
        }
    }

    private InputStream getCurrentStream() throws IOException {
        if (this.archive.files[this.currentEntryIndex].getSize() == 0) {
            return new ByteArrayInputStream(ByteUtils.EMPTY_BYTE_ARRAY);
        }
        if (this.deferredBlockStreams.isEmpty()) {
            throw new IllegalStateException("No current 7z entry (call getNextEntry() first).");
        }
        while (this.deferredBlockStreams.size() > 1) {
            InputStream stream = this.deferredBlockStreams.remove(0);
            try {
                IOUtils.skip(stream, Long.MAX_VALUE, new Supplier() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        byte[] byteArray;
                        byteArray = IOUtils.byteArray();
                        return byteArray;
                    }
                });
                if (stream != null) {
                    stream.close();
                }
                this.compressedBytesReadFromCurrentEntry = 0L;
            } catch (Throwable th) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return this.deferredBlockStreams.get(0);
    }

    public String getDefaultName() {
        if (DEFAULT_FILE_NAME.equals(this.fileName) || this.fileName == null) {
            return null;
        }
        String lastSegment = new File(this.fileName).getName();
        int dotPos = lastSegment.lastIndexOf(".");
        if (dotPos > 0) {
            return lastSegment.substring(0, dotPos);
        }
        return lastSegment + "~";
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return new ArrayList(Arrays.asList(this.archive.files));
    }

    public InputStream getInputStream(SevenZArchiveEntry entry) throws IOException {
        int entryIndex = -1;
        int i = 0;
        while (true) {
            if (i >= this.archive.files.length) {
                break;
            }
            if (entry != this.archive.files[i]) {
                i++;
            } else {
                entryIndex = i;
                break;
            }
        }
        if (entryIndex < 0) {
            throw new IllegalArgumentException("Can not find " + entry.getName() + " in " + this.fileName);
        }
        buildDecodingStream(entryIndex, true);
        this.currentEntryIndex = entryIndex;
        this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[entryIndex];
        return getCurrentStream();
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        this.currentEntryIndex++;
        SevenZArchiveEntry entry = this.archive.files[this.currentEntryIndex];
        if (entry.getName() == null && this.useDefaultNameForUnnamedEntries) {
            entry.setName(getDefaultName());
        }
        buildDecodingStream(this.currentEntryIndex, false);
        this.compressedBytesReadFromCurrentEntry = 0L;
        this.uncompressedBytesReadFromCurrentEntry = 0L;
        return entry;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile.2
            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            @Override // org.apache.commons.compress.utils.InputStreamStatistics
            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private boolean hasCurrentEntryBeenRead() {
        if (this.deferredBlockStreams.isEmpty()) {
            return false;
        }
        InputStream currentEntryInputStream = this.deferredBlockStreams.get(this.deferredBlockStreams.size() - 1);
        if (currentEntryInputStream instanceof ChecksumInputStream) {
            boolean hasCurrentEntryBeenRead = ((ChecksumInputStream) currentEntryInputStream).getRemaining() != this.archive.files[this.currentEntryIndex].getSize();
            return hasCurrentEntryBeenRead;
        }
        if (!(currentEntryInputStream instanceof BoundedInputStream)) {
            return false;
        }
        boolean hasCurrentEntryBeenRead2 = ((BoundedInputStream) currentEntryInputStream).getRemaining() != this.archive.files[this.currentEntryIndex].getSize();
        return hasCurrentEntryBeenRead2;
    }

    private Archive initializeArchive(StartHeader startHeader, byte[] password, boolean verifyCrc) throws IOException {
        assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
        int nextHeaderSizeInt = (int) startHeader.nextHeaderSize;
        this.channel.position(startHeader.nextHeaderOffset + 32);
        if (verifyCrc) {
            long position = this.channel.position();
            CheckedInputStream cis = new CheckedInputStream(Channels.newInputStream(this.channel), new CRC32());
            if (cis.skip(nextHeaderSizeInt) != nextHeaderSizeInt) {
                throw new IOException("Problem computing NextHeader CRC-32");
            }
            if (startHeader.nextHeaderCrc != cis.getChecksum().getValue()) {
                throw new IOException("NextHeader CRC-32 mismatch");
            }
            this.channel.position(position);
        }
        Archive archive = new Archive();
        ByteBuffer buf = ByteBuffer.allocate(nextHeaderSizeInt).order(ByteOrder.LITTLE_ENDIAN);
        readFully(buf);
        int nid = getUnsignedByte(buf);
        if (nid == 23) {
            buf = readEncodedHeader(buf, archive, password);
            archive = new Archive();
            nid = getUnsignedByte(buf);
        }
        if (nid != 1) {
            throw new IOException("Broken or unsupported archive: no Header");
        }
        readHeader(buf, archive);
        archive.subStreamsInfo = null;
        return archive;
    }

    public int read() throws IOException {
        int b = getCurrentStream().read();
        if (b >= 0) {
            this.uncompressedBytesReadFromCurrentEntry++;
        }
        return b;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int current = getCurrentStream().read(b, off, len);
        if (current > 0) {
            this.uncompressedBytesReadFromCurrentEntry += current;
        }
        return current;
    }

    private BitSet readAllOrBits(ByteBuffer header, int size) throws IOException {
        int areAllDefined = getUnsignedByte(header);
        if (areAllDefined != 0) {
            BitSet bits = new BitSet(size);
            for (int i = 0; i < size; i++) {
                bits.set(i, true);
            }
            return bits;
        }
        return readBits(header, size);
    }

    private void readArchiveProperties(ByteBuffer input) throws IOException {
        long nid = readUint64(input);
        while (nid != 0) {
            long propertySize = readUint64(input);
            byte[] property = new byte[(int) propertySize];
            get(input, property);
            nid = readUint64(input);
        }
    }

    private BitSet readBits(ByteBuffer header, int size) throws IOException {
        BitSet bits = new BitSet(size);
        int mask = 0;
        int cache = 0;
        for (int i = 0; i < size; i++) {
            if (mask == 0) {
                mask = 128;
                cache = getUnsignedByte(header);
            }
            bits.set(i, (cache & mask) != 0);
            mask >>>= 1;
        }
        return bits;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ByteBuffer readEncodedHeader(ByteBuffer header, Archive archive, byte[] password) throws IOException {
        int pos = header.position();
        ArchiveStatistics stats = new ArchiveStatistics();
        sanityCheckStreamsInfo(header, stats);
        stats.assertValidity(this.maxMemoryLimitKiB);
        header.position(pos);
        readStreamsInfo(header, archive);
        if (ArrayUtils.isEmpty(archive.folders)) {
            throw new IOException("no folders, can't read encoded header");
        }
        if (ArrayUtils.isEmpty(archive.packSizes)) {
            throw new IOException("no packed streams, can't read encoded header");
        }
        Folder folder = archive.folders[0];
        long folderOffset = archive.packPos + 32 + 0;
        this.channel.position(folderOffset);
        InputStream inputStreamStack = new BoundedSeekableByteChannelInputStream(this.channel, archive.packSizes[0]);
        InputStream inputStreamStack2 = inputStreamStack;
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1 || coder.numOutStreams != 1) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            inputStreamStack2 = Coders.addDecoder(this.fileName, inputStreamStack2, folder.getUnpackSizeForCoder(coder), coder, password, this.maxMemoryLimitKiB);
        }
        if (folder.hasCrc) {
            inputStreamStack2 = ((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(inputStreamStack2)).setCountThreshold(folder.getUnpackSize()).setExpectedChecksumValue(folder.crc).get();
        }
        int unpackSize = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
        byte[] nextHeader = org.apache.commons.compress.utils.IOUtils.readRange(inputStreamStack2, unpackSize);
        if (nextHeader.length < unpackSize) {
            throw new IOException("premature end of stream");
        }
        inputStreamStack2.close();
        return ByteBuffer.wrap(nextHeader).order(ByteOrder.LITTLE_ENDIAN);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x00dc. Please report as an issue. */
    private void readFilesInfo(ByteBuffer header, final Archive archive) throws IOException {
        Map<Integer, SevenZArchiveEntry> fileMap;
        BitSet isEmptyFile;
        BitSet isAnti;
        Map<Integer, SevenZArchiveEntry> fileMap2;
        BitSet isEmptyFile2;
        BitSet isAnti2;
        int propertyType;
        int numFilesInt = (int) readUint64(header);
        Map<Integer, SevenZArchiveEntry> fileMap3 = new LinkedHashMap<>();
        BitSet isEmptyStream = null;
        BitSet isEmptyFile3 = null;
        BitSet isAnti3 = null;
        while (true) {
            int propertyType2 = getUnsignedByte(header);
            if (propertyType2 != 0) {
                long size = readUint64(header);
                switch (propertyType2) {
                    case 14:
                        fileMap = fileMap3;
                        BitSet isEmptyStream2 = readBits(header, numFilesInt);
                        isEmptyStream = isEmptyStream2;
                        fileMap3 = fileMap;
                    case 15:
                        fileMap = fileMap3;
                        BitSet isEmptyFile4 = readBits(header, ((BitSet) ArchiveException.requireNonNull(isEmptyStream, new Supplier() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda0
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return SevenZFile.lambda$readFilesInfo$1(Archive.this);
                            }
                        })).cardinality());
                        isEmptyFile3 = isEmptyFile4;
                        fileMap3 = fileMap;
                    case 16:
                        fileMap = fileMap3;
                        BitSet isAnti4 = readBits(header, ((BitSet) ArchiveException.requireNonNull(isEmptyStream, new Supplier() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda1
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return SevenZFile.lambda$readFilesInfo$2(Archive.this);
                            }
                        })).cardinality());
                        isAnti3 = isAnti4;
                        fileMap3 = fileMap;
                    case 17:
                        getUnsignedByte(header);
                        byte[] names = new byte[(int) (size - 1)];
                        int namesLength = names.length;
                        get(header, names);
                        int nextFile = 0;
                        int nextName = 0;
                        int i = 0;
                        while (i < namesLength) {
                            if (names[i] != 0 || names[i + 1] != 0) {
                                fileMap2 = fileMap3;
                                isEmptyFile2 = isEmptyFile3;
                                isAnti2 = isAnti3;
                                propertyType = propertyType2;
                            } else {
                                checkEntryIsInitialized(fileMap3, nextFile);
                                isEmptyFile2 = isEmptyFile3;
                                fileMap2 = fileMap3;
                                isAnti2 = isAnti3;
                                propertyType = propertyType2;
                                fileMap3.get(Integer.valueOf(nextFile)).setName(new String(names, nextName, i - nextName, StandardCharsets.UTF_16LE));
                                int nextName2 = i + 2;
                                nextFile++;
                                nextName = nextName2;
                            }
                            i += 2;
                            isEmptyFile3 = isEmptyFile2;
                            fileMap3 = fileMap2;
                            isAnti3 = isAnti2;
                            propertyType2 = propertyType;
                        }
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        if (nextName != namesLength || nextFile != numFilesInt) {
                        }
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                        break;
                    case 18:
                        BitSet timesDefined = readAllOrBits(header, numFilesInt);
                        getUnsignedByte(header);
                        for (int i2 = 0; i2 < numFilesInt; i2++) {
                            checkEntryIsInitialized(fileMap3, i2);
                            SevenZArchiveEntry entryAtIndex = fileMap3.get(Integer.valueOf(i2));
                            entryAtIndex.setHasCreationDate(timesDefined.get(i2));
                            if (entryAtIndex.getHasCreationDate()) {
                                entryAtIndex.setCreationDate(getLong(header));
                            }
                        }
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                    case 19:
                        BitSet timesDefined2 = readAllOrBits(header, numFilesInt);
                        getUnsignedByte(header);
                        for (int i3 = 0; i3 < numFilesInt; i3++) {
                            checkEntryIsInitialized(fileMap3, i3);
                            SevenZArchiveEntry entryAtIndex2 = fileMap3.get(Integer.valueOf(i3));
                            entryAtIndex2.setHasAccessDate(timesDefined2.get(i3));
                            if (entryAtIndex2.getHasAccessDate()) {
                                entryAtIndex2.setAccessDate(getLong(header));
                            }
                        }
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                    case 20:
                        BitSet timesDefined3 = readAllOrBits(header, numFilesInt);
                        getUnsignedByte(header);
                        for (int i4 = 0; i4 < numFilesInt; i4++) {
                            checkEntryIsInitialized(fileMap3, i4);
                            SevenZArchiveEntry entryAtIndex3 = fileMap3.get(Integer.valueOf(i4));
                            entryAtIndex3.setHasLastModifiedDate(timesDefined3.get(i4));
                            if (entryAtIndex3.getHasLastModifiedDate()) {
                                entryAtIndex3.setLastModifiedDate(getLong(header));
                            }
                        }
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                    case 21:
                        BitSet attributesDefined = readAllOrBits(header, numFilesInt);
                        getUnsignedByte(header);
                        for (int i5 = 0; i5 < numFilesInt; i5++) {
                            checkEntryIsInitialized(fileMap3, i5);
                            SevenZArchiveEntry entryAtIndex4 = fileMap3.get(Integer.valueOf(i5));
                            entryAtIndex4.setHasWindowsAttributes(attributesDefined.get(i5));
                            if (entryAtIndex4.getHasWindowsAttributes()) {
                                entryAtIndex4.setWindowsAttributes(getInt(header));
                            }
                        }
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                    case 22:
                    case 23:
                    case 24:
                    default:
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        skipBytesFully(header, size);
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                    case 25:
                        skipBytesFully(header, size);
                        fileMap = fileMap3;
                        isEmptyFile = isEmptyFile3;
                        isAnti = isAnti3;
                        isEmptyFile3 = isEmptyFile;
                        isAnti3 = isAnti;
                        fileMap3 = fileMap;
                }
            } else {
                int nonEmptyFileCounter = 0;
                int emptyFileCounter = 0;
                for (int i6 = 0; i6 < numFilesInt; i6++) {
                    SevenZArchiveEntry entryAtIndex5 = fileMap3.get(Integer.valueOf(i6));
                    if (entryAtIndex5 != null) {
                        entryAtIndex5.setHasStream(isEmptyStream == null || !isEmptyStream.get(i6));
                        if (!entryAtIndex5.hasStream()) {
                            entryAtIndex5.setDirectory(isEmptyFile3 == null || !isEmptyFile3.get(emptyFileCounter));
                            entryAtIndex5.setAntiItem(isAnti3 != null && isAnti3.get(emptyFileCounter));
                            entryAtIndex5.setHasCrc(false);
                            entryAtIndex5.setSize(0L);
                            emptyFileCounter++;
                        } else {
                            if (archive.subStreamsInfo == null) {
                                throw new IOException("Archive contains file with streams but no subStreamsInfo");
                            }
                            entryAtIndex5.setDirectory(false);
                            entryAtIndex5.setAntiItem(false);
                            entryAtIndex5.setHasCrc(archive.subStreamsInfo.hasCrc.get(nonEmptyFileCounter));
                            entryAtIndex5.setCrcValue(archive.subStreamsInfo.crcs[nonEmptyFileCounter]);
                            entryAtIndex5.setSize(archive.subStreamsInfo.unpackSizes[nonEmptyFileCounter]);
                            if (entryAtIndex5.getSize() < 0) {
                                throw new IOException("broken archive, entry with negative size");
                            }
                            nonEmptyFileCounter++;
                        }
                    }
                }
                archive.files = (SevenZArchiveEntry[]) fileMap3.values().stream().filter(new Predicate() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean nonNull;
                        nonNull = Objects.nonNull((SevenZArchiveEntry) obj);
                        return nonNull;
                    }
                }).toArray(new IntFunction() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda3
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i7) {
                        return SevenZFile.lambda$readFilesInfo$3(i7);
                    }
                });
                calculateStreamMap(archive);
                return;
            }
        }
        throw new IOException("Error parsing file names");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$readFilesInfo$1(Archive archive) {
        return "isEmptyStream for " + archive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$readFilesInfo$2(Archive archive) {
        return "isEmptyStream for " + archive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SevenZArchiveEntry[] lambda$readFilesInfo$3(int x$0) {
        return new SevenZArchiveEntry[x$0];
    }

    private Folder readFolder(ByteBuffer header) throws IOException {
        long numOutStreams;
        long numInStreams;
        long numCoders;
        long totalInStreams;
        byte[] properties;
        Folder folder = new Folder();
        long numCoders2 = readUint64(header);
        Coder[] coders = new Coder[(int) numCoders2];
        long totalInStreams2 = 0;
        long totalOutStreams = 0;
        int i = 0;
        while (i < coders.length) {
            int bits = getUnsignedByte(header);
            int idSize = bits & 15;
            boolean isSimple = (bits & 16) == 0;
            boolean hasAttributes = (bits & 32) != 0;
            boolean moreAlternativeMethods = (bits & 128) != 0;
            byte[] decompressionMethodId = new byte[idSize];
            get(header, decompressionMethodId);
            if (isSimple) {
                numOutStreams = 1;
                numInStreams = 1;
            } else {
                long numInStreams2 = readUint64(header);
                numOutStreams = readUint64(header);
                numInStreams = numInStreams2;
            }
            long totalInStreams3 = totalInStreams2 + numInStreams;
            totalOutStreams += numOutStreams;
            if (!hasAttributes) {
                numCoders = numCoders2;
                totalInStreams = totalInStreams3;
                properties = null;
            } else {
                numCoders = numCoders2;
                long propertiesSize = readUint64(header);
                totalInStreams = totalInStreams3;
                byte[] properties2 = new byte[(int) propertiesSize];
                get(header, properties2);
                properties = properties2;
            }
            if (moreAlternativeMethods) {
                throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }
            coders[i] = new Coder(decompressionMethodId, numInStreams, numOutStreams, properties);
            i++;
            numCoders2 = numCoders;
            totalInStreams2 = totalInStreams;
        }
        folder.coders = coders;
        folder.totalInputStreams = totalInStreams2;
        folder.totalOutputStreams = totalOutStreams;
        long j = 1;
        long numBindPairs = totalOutStreams - 1;
        BindPair[] bindPairs = new BindPair[(int) numBindPairs];
        int i2 = 0;
        while (i2 < bindPairs.length) {
            bindPairs[i2] = new BindPair(readUint64(header), readUint64(header));
            i2++;
            j = j;
        }
        long j2 = j;
        folder.bindPairs = bindPairs;
        long numPackedStreams = totalInStreams2 - numBindPairs;
        long[] packedStreams = new long[(int) numPackedStreams];
        if (numPackedStreams == j2) {
            int i3 = 0;
            while (i3 < ((int) totalInStreams2) && folder.findBindPairForInStream(i3) >= 0) {
                i3++;
            }
            packedStreams[0] = i3;
        } else {
            for (int i4 = 0; i4 < ((int) numPackedStreams); i4++) {
                packedStreams[i4] = readUint64(header);
            }
        }
        folder.packedStreams = packedStreams;
        return folder;
    }

    private void readFully(ByteBuffer buf) throws IOException {
        buf.rewind();
        org.apache.commons.compress.utils.IOUtils.readFully(this.channel, buf);
        buf.flip();
    }

    private void readHeader(ByteBuffer header, Archive archive) throws IOException {
        int pos = header.position();
        ArchiveStatistics stats = sanityCheckAndCollectStatistics(header);
        stats.assertValidity(this.maxMemoryLimitKiB);
        header.position(pos);
        int nid = getUnsignedByte(header);
        if (nid == 2) {
            readArchiveProperties(header);
            nid = getUnsignedByte(header);
        }
        if (nid == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (nid == 4) {
            readStreamsInfo(header, archive);
            nid = getUnsignedByte(header);
        }
        if (nid == 5) {
            readFilesInfo(header, archive);
            getUnsignedByte(header);
        }
    }

    private Archive readHeaders(byte[] password) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        readFully(buf);
        byte[] signature = new byte[6];
        buf.get(signature);
        if (!Arrays.equals(signature, sevenZSignature)) {
            throw new IOException("Bad 7z signature");
        }
        byte archiveVersionMajor = buf.get();
        byte archiveVersionMinor = buf.get();
        if (archiveVersionMajor != 0) {
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", Byte.valueOf(archiveVersionMajor), Byte.valueOf(archiveVersionMinor)));
        }
        boolean headerLooksValid = false;
        long startHeaderCrc = buf.getInt() & 4294967295L;
        if (startHeaderCrc == 0) {
            long currentPosition = this.channel.position();
            ByteBuffer peekBuf = ByteBuffer.allocate(20);
            readFully(peekBuf);
            this.channel.position(currentPosition);
            while (true) {
                if (!peekBuf.hasRemaining()) {
                    break;
                }
                if (peekBuf.get() != 0) {
                    headerLooksValid = true;
                    break;
                }
            }
        } else {
            headerLooksValid = true;
        }
        if (headerLooksValid) {
            return initializeArchive(readStartHeader(startHeaderCrc), password, true);
        }
        if (this.tryToRecoverBrokenArchives) {
            return tryToLocateEndHeader(password);
        }
        throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
    }

    private void readPackInfo(ByteBuffer header, Archive archive) throws IOException {
        archive.packPos = readUint64(header);
        int numPackStreamsInt = (int) readUint64(header);
        int nid = getUnsignedByte(header);
        if (nid == 9) {
            archive.packSizes = new long[numPackStreamsInt];
            for (int i = 0; i < archive.packSizes.length; i++) {
                archive.packSizes[i] = readUint64(header);
            }
            nid = getUnsignedByte(header);
        }
        if (nid == 10) {
            archive.packCrcsDefined = readAllOrBits(header, numPackStreamsInt);
            archive.packCrcs = new long[numPackStreamsInt];
            for (int i2 = 0; i2 < numPackStreamsInt; i2++) {
                if (archive.packCrcsDefined.get(i2)) {
                    archive.packCrcs[i2] = getInt(header) & 4294967295L;
                }
            }
            getUnsignedByte(header);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private StartHeader readStartHeader(long startHeaderCrc) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(new BoundedSeekableByteChannelInputStream(this.channel, 20L))).setCountThreshold(20L).setExpectedChecksumValue(startHeaderCrc).get());
        try {
            long nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
            if (nextHeaderOffset < 0 || nextHeaderOffset + 32 > this.channel.size()) {
                throw new IOException("nextHeaderOffset is out of bounds");
            }
            long nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
            long nextHeaderEnd = nextHeaderOffset + nextHeaderSize;
            if (nextHeaderEnd < nextHeaderOffset || 32 + nextHeaderEnd > this.channel.size()) {
                throw new IOException("nextHeaderSize is out of bounds");
            }
            long nextHeaderCrc = 4294967295L & Integer.reverseBytes(dataInputStream.readInt());
            StartHeader startHeader = new StartHeader(nextHeaderOffset, nextHeaderSize, nextHeaderCrc);
            dataInputStream.close();
            return startHeader;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    private void readStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
        int nid = getUnsignedByte(header);
        if (nid == 6) {
            readPackInfo(header, archive);
            nid = getUnsignedByte(header);
        }
        if (nid == 7) {
            readUnpackInfo(header, archive);
            nid = getUnsignedByte(header);
        } else {
            archive.folders = Folder.EMPTY_FOLDER_ARRAY;
        }
        if (nid == 8) {
            readSubStreamsInfo(header, archive);
            getUnsignedByte(header);
        }
    }

    private void readSubStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
        int nid;
        for (Folder folder : archive.folders) {
            folder.numUnpackSubStreams = 1;
        }
        long unpackStreamsCount = archive.folders.length;
        int nid2 = getUnsignedByte(header);
        if (nid2 == 13) {
            unpackStreamsCount = 0;
            for (Folder folder2 : archive.folders) {
                long numStreams = readUint64(header);
                folder2.numUnpackSubStreams = (int) numStreams;
                unpackStreamsCount += numStreams;
            }
            nid2 = getUnsignedByte(header);
        }
        int totalUnpackStreams = (int) unpackStreamsCount;
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo(totalUnpackStreams);
        int nextUnpackStream = 0;
        for (Folder folder3 : archive.folders) {
            if (folder3.numUnpackSubStreams != 0) {
                long sum = 0;
                if (nid2 == 9) {
                    int i = 0;
                    while (i < folder3.numUnpackSubStreams - 1) {
                        long size = readUint64(header);
                        subStreamsInfo.unpackSizes[nextUnpackStream] = size;
                        sum += size;
                        i++;
                        nextUnpackStream++;
                    }
                }
                if (sum > folder3.getUnpackSize()) {
                    throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
                }
                subStreamsInfo.unpackSizes[nextUnpackStream] = folder3.getUnpackSize() - sum;
                nextUnpackStream++;
            }
        }
        if (nid2 == 9) {
            nid2 = getUnsignedByte(header);
        }
        int numDigests = 0;
        for (Folder folder4 : archive.folders) {
            if (folder4.numUnpackSubStreams != 1 || !folder4.hasCrc) {
                numDigests += folder4.numUnpackSubStreams;
            }
        }
        if (nid2 == 10) {
            BitSet hasMissingCrc = readAllOrBits(header, numDigests);
            long[] missingCrcs = new long[numDigests];
            for (int i2 = 0; i2 < numDigests; i2++) {
                if (hasMissingCrc.get(i2)) {
                    missingCrcs[i2] = getInt(header) & 4294967295L;
                }
            }
            Folder[] folderArr = archive.folders;
            int length = folderArr.length;
            int nextMissingCrc = 0;
            int nextCrc = 0;
            int nextCrc2 = 0;
            while (nextCrc2 < length) {
                long unpackStreamsCount2 = unpackStreamsCount;
                Folder folder5 = folderArr[nextCrc2];
                int numDigests2 = numDigests;
                if (folder5.numUnpackSubStreams != 1 || !folder5.hasCrc) {
                    nid = nid2;
                    int i3 = 0;
                    int nextMissingCrc2 = nextMissingCrc;
                    while (i3 < folder5.numUnpackSubStreams) {
                        subStreamsInfo.hasCrc.set(nextCrc, hasMissingCrc.get(nextMissingCrc2));
                        subStreamsInfo.crcs[nextCrc] = missingCrcs[nextMissingCrc2];
                        nextCrc++;
                        nextMissingCrc2++;
                        i3++;
                        folder5 = folder5;
                    }
                    nextMissingCrc = nextMissingCrc2;
                } else {
                    subStreamsInfo.hasCrc.set(nextCrc, true);
                    nid = nid2;
                    subStreamsInfo.crcs[nextCrc] = folder5.crc;
                    nextCrc++;
                }
                nextCrc2++;
                nid2 = nid;
                unpackStreamsCount = unpackStreamsCount2;
                numDigests = numDigests2;
            }
            getUnsignedByte(header);
        }
        archive.subStreamsInfo = subStreamsInfo;
    }

    private void readUnpackInfo(ByteBuffer header, Archive archive) throws IOException {
        getUnsignedByte(header);
        int numFoldersInt = (int) readUint64(header);
        Folder[] folders = new Folder[numFoldersInt];
        archive.folders = folders;
        getUnsignedByte(header);
        for (int i = 0; i < numFoldersInt; i++) {
            folders[i] = readFolder(header);
        }
        getUnsignedByte(header);
        for (Folder folder : folders) {
            assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[(int) folder.totalOutputStreams];
            for (int i2 = 0; i2 < folder.totalOutputStreams; i2++) {
                folder.unpackSizes[i2] = readUint64(header);
            }
        }
        int nid = getUnsignedByte(header);
        if (nid == 10) {
            BitSet crcsDefined = readAllOrBits(header, numFoldersInt);
            for (int i3 = 0; i3 < numFoldersInt; i3++) {
                if (crcsDefined.get(i3)) {
                    folders[i3].hasCrc = true;
                    folders[i3].crc = getInt(header) & 4294967295L;
                } else {
                    folders[i3].hasCrc = false;
                }
            }
            getUnsignedByte(header);
        }
    }

    private void reopenFolderInputStream(int folderIndex, SevenZArchiveEntry file) throws IOException {
        this.deferredBlockStreams.clear();
        if (this.currentFolderInputStream != null) {
            this.currentFolderInputStream.close();
            this.currentFolderInputStream = null;
        }
        Folder folder = this.archive.folders[folderIndex];
        int firstPackStreamIndex = this.archive.streamMap.folderFirstPackStreamIndex[folderIndex];
        long folderOffset = this.archive.packPos + 32 + this.archive.streamMap.packStreamOffsets[firstPackStreamIndex];
        this.currentFolderInputStream = buildDecoderStack(folder, folderOffset, firstPackStreamIndex, file);
    }

    private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer header) throws IOException {
        ArchiveStatistics stats = new ArchiveStatistics();
        int nid = getUnsignedByte(header);
        if (nid == 2) {
            sanityCheckArchiveProperties(header);
            nid = getUnsignedByte(header);
        }
        if (nid == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (nid == 4) {
            sanityCheckStreamsInfo(header, stats);
            nid = getUnsignedByte(header);
        }
        if (nid == 5) {
            sanityCheckFilesInfo(header, stats);
            nid = getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated header, found " + nid);
        }
        return stats;
    }

    private void sanityCheckArchiveProperties(ByteBuffer header) throws IOException {
        long nid = readUint64(header);
        while (nid != 0) {
            int propertySize = assertFitsIntoNonNegativeInt("propertySize", readUint64(header));
            if (skipBytesFully(header, propertySize) < propertySize) {
                throw new IOException("invalid property size");
            }
            nid = readUint64(header);
        }
    }

    private void sanityCheckFilesInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        stats.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(header));
        int emptyStreams = -1;
        while (true) {
            int propertyType = getUnsignedByte(header);
            if (propertyType == 0) {
                stats.numberOfEntriesWithStream = stats.numberOfEntries - Math.max(emptyStreams, 0);
                return;
            }
            long size = readUint64(header);
            switch (propertyType) {
                case 14:
                    emptyStreams = readBits(header, stats.numberOfEntries).cardinality();
                    break;
                case 15:
                    if (emptyStreams == -1) {
                        throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                    }
                    readBits(header, emptyStreams);
                    break;
                case 16:
                    if (emptyStreams == -1) {
                        throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                    }
                    readBits(header, emptyStreams);
                    break;
                case 17:
                    int external = getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Not implemented");
                    }
                    int namesLength = assertFitsIntoNonNegativeInt("file names length", size - 1);
                    if ((namesLength & 1) != 0) {
                        throw new IOException("File names length invalid");
                    }
                    int filesSeen = 0;
                    for (int i = 0; i < namesLength; i += 2) {
                        char c = getChar(header);
                        if (c == 0) {
                            filesSeen++;
                        }
                    }
                    int i2 = stats.numberOfEntries;
                    if (filesSeen != i2) {
                        throw new IOException("Invalid number of file names (" + filesSeen + " instead of " + stats.numberOfEntries + ")");
                    }
                    break;
                case 18:
                    int timesDefined = readAllOrBits(header, stats.numberOfEntries).cardinality();
                    int external2 = getUnsignedByte(header);
                    if (external2 != 0) {
                        throw new IOException("Not implemented");
                    }
                    if (skipBytesFully(header, timesDefined * 8) < timesDefined * 8) {
                        throw new IOException("invalid creation dates size");
                    }
                    break;
                case 19:
                    int timesDefined2 = readAllOrBits(header, stats.numberOfEntries).cardinality();
                    int external3 = getUnsignedByte(header);
                    if (external3 != 0) {
                        throw new IOException("Not implemented");
                    }
                    if (skipBytesFully(header, timesDefined2 * 8) < timesDefined2 * 8) {
                        throw new IOException("invalid access dates size");
                    }
                    break;
                case 20:
                    int timesDefined3 = readAllOrBits(header, stats.numberOfEntries).cardinality();
                    int external4 = getUnsignedByte(header);
                    if (external4 != 0) {
                        throw new IOException("Not implemented");
                    }
                    if (skipBytesFully(header, timesDefined3 * 8) < timesDefined3 * 8) {
                        throw new IOException("invalid modification dates size");
                    }
                    break;
                case 21:
                    int attributesDefined = readAllOrBits(header, stats.numberOfEntries).cardinality();
                    int external5 = getUnsignedByte(header);
                    if (external5 != 0) {
                        throw new IOException("Not implemented");
                    }
                    if (skipBytesFully(header, attributesDefined * 4) < attributesDefined * 4) {
                        throw new IOException("invalid windows attributes size");
                    }
                    break;
                case 22:
                case 23:
                default:
                    if (skipBytesFully(header, size) < size) {
                        throw new IOException("Incomplete property of type " + propertyType);
                    }
                    break;
                case 24:
                    throw new IOException("kStartPos is unsupported, please report");
                case 25:
                    if (skipBytesFully(header, size) < size) {
                        throw new IOException("Incomplete kDummy property");
                    }
                    break;
            }
        }
    }

    private int sanityCheckFolder(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        long totalOutStreams;
        int numCoders;
        long totalOutStreams2;
        int numCoders2 = assertFitsIntoNonNegativeInt("numCoders", readUint64(header));
        if (numCoders2 == 0) {
            throw new IOException("Folder without coders");
        }
        ArchiveStatistics.access$614(stats, numCoders2);
        long totalOutStreams3 = 0;
        long totalInStreams = 0;
        int i = 0;
        while (true) {
            if (i < numCoders2) {
                int bits = getUnsignedByte(header);
                int idSize = bits & 15;
                get(header, new byte[idSize]);
                boolean isSimple = (bits & 16) == 0;
                boolean hasAttributes = (bits & 32) != 0;
                boolean moreAlternativeMethods = (bits & 128) != 0;
                if (moreAlternativeMethods) {
                    throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
                }
                if (isSimple) {
                    totalInStreams++;
                    totalOutStreams = totalOutStreams3 + 1;
                } else {
                    totalInStreams += assertFitsIntoNonNegativeInt("numInStreams", readUint64(header));
                    totalOutStreams = totalOutStreams3 + assertFitsIntoNonNegativeInt("numOutStreams", readUint64(header));
                }
                if (!hasAttributes) {
                    numCoders = numCoders2;
                    totalOutStreams2 = totalOutStreams;
                } else {
                    int propertiesSize = assertFitsIntoNonNegativeInt("propertiesSize", readUint64(header));
                    numCoders = numCoders2;
                    totalOutStreams2 = totalOutStreams;
                    if (skipBytesFully(header, propertiesSize) < propertiesSize) {
                        throw new IOException("invalid propertiesSize in folder");
                    }
                }
                i++;
                totalOutStreams3 = totalOutStreams2;
                numCoders2 = numCoders;
            } else {
                assertFitsIntoNonNegativeInt("totalInStreams", totalInStreams);
                assertFitsIntoNonNegativeInt("totalOutStreams", totalOutStreams3);
                ArchiveStatistics.access$714(stats, totalOutStreams3);
                ArchiveStatistics.access$814(stats, totalInStreams);
                if (totalOutStreams3 == 0) {
                    throw new IOException("Total output streams can't be 0");
                }
                int numBindPairs = assertFitsIntoNonNegativeInt("numBindPairs", totalOutStreams3 - 1);
                if (totalInStreams < numBindPairs) {
                    throw new IOException("Total input streams can't be less than the number of bind pairs");
                }
                BitSet inStreamsBound = new BitSet((int) totalInStreams);
                for (int i2 = 0; i2 < numBindPairs; i2++) {
                    int inIndex = assertFitsIntoNonNegativeInt("inIndex", readUint64(header));
                    if (totalInStreams <= inIndex) {
                        throw new IOException("inIndex is bigger than number of inStreams");
                    }
                    inStreamsBound.set(inIndex);
                    int outIndex = assertFitsIntoNonNegativeInt("outIndex", readUint64(header));
                    if (totalOutStreams3 <= outIndex) {
                        throw new IOException("outIndex is bigger than number of outStreams");
                    }
                }
                int numPackedStreams = assertFitsIntoNonNegativeInt("numPackedStreams", totalInStreams - numBindPairs);
                if (numPackedStreams == 1) {
                    if (inStreamsBound.nextClearBit(0) == -1) {
                        throw new IOException("Couldn't find stream's bind pair index");
                    }
                } else {
                    for (int i3 = 0; i3 < numPackedStreams; i3++) {
                        int packedStreamIndex = assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(header));
                        if (packedStreamIndex >= totalInStreams) {
                            throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                        }
                    }
                }
                int i4 = (int) totalOutStreams3;
                return i4;
            }
        }
    }

    private void sanityCheckPackInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        long packSize;
        long packPos = readUint64(header);
        long j = 0;
        if (packPos >= 0) {
            long j2 = 32;
            if (packPos + 32 <= this.channel.size() && packPos + 32 >= 0) {
                long numPackStreams = readUint64(header);
                stats.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", numPackStreams);
                int nid = getUnsignedByte(header);
                if (nid == 9) {
                    long totalPackSizes = 0;
                    int i = 0;
                    while (true) {
                        long j3 = j;
                        if (i < stats.numberOfPackedStreams) {
                            packSize = readUint64(header);
                            totalPackSizes += packSize;
                            long endOfPackStreams = packPos + j2 + totalPackSizes;
                            if (packSize < j3 || endOfPackStreams > this.channel.size() || endOfPackStreams < packPos) {
                                break;
                            }
                            i++;
                            j = j3;
                            j2 = 32;
                        } else {
                            nid = getUnsignedByte(header);
                            break;
                        }
                    }
                    throw new IOException("packSize (" + packSize + ") is out of range");
                }
                if (nid == 10) {
                    int crcsDefined = readAllOrBits(header, stats.numberOfPackedStreams).cardinality();
                    if (skipBytesFully(header, crcsDefined * 4) < crcsDefined * 4) {
                        throw new IOException("invalid number of CRCs in PackInfo");
                    }
                    nid = getUnsignedByte(header);
                }
                if (nid != 0) {
                    throw new IOException("Badly terminated PackInfo (" + nid + ")");
                }
                return;
            }
        }
        throw new IOException("packPos (" + packPos + ") is out of range");
    }

    private void sanityCheckStreamsInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        int nid = getUnsignedByte(header);
        if (nid == 6) {
            sanityCheckPackInfo(header, stats);
            nid = getUnsignedByte(header);
        }
        if (nid == 7) {
            sanityCheckUnpackInfo(header, stats);
            nid = getUnsignedByte(header);
        }
        if (nid == 8) {
            sanityCheckSubStreamsInfo(header, stats);
            nid = getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void sanityCheckSubStreamsInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        int folderIdx;
        int nid = getUnsignedByte(header);
        List<Integer> numUnpackSubStreamsPerFolder = new LinkedList<>();
        if (nid != 13) {
            stats.numberOfUnpackSubStreams = stats.numberOfFolders;
        } else {
            for (int i = 0; i < stats.numberOfFolders; i++) {
                numUnpackSubStreamsPerFolder.add(Integer.valueOf(assertFitsIntoNonNegativeInt("numStreams", readUint64(header))));
            }
            stats.numberOfUnpackSubStreams = numUnpackSubStreamsPerFolder.stream().mapToLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZFile$$ExternalSyntheticLambda6
                @Override // java.util.function.ToLongFunction
                public final long applyAsLong(Object obj) {
                    long longValue;
                    longValue = ((Integer) obj).longValue();
                    return longValue;
                }
            }).sum();
            nid = getUnsignedByte(header);
        }
        assertFitsIntoNonNegativeInt("totalUnpackStreams", stats.numberOfUnpackSubStreams);
        if (nid == 9) {
            Iterator<Integer> it = numUnpackSubStreamsPerFolder.iterator();
            while (it.hasNext()) {
                int numUnpackSubStreams = it.next().intValue();
                if (numUnpackSubStreams != 0) {
                    for (int i2 = 0; i2 < numUnpackSubStreams - 1; i2++) {
                        long size = readUint64(header);
                        if (size < 0) {
                            throw new IOException("negative unpackSize");
                        }
                    }
                }
            }
            nid = getUnsignedByte(header);
        }
        int numDigests = 0;
        if (numUnpackSubStreamsPerFolder.isEmpty()) {
            folderIdx = stats.folderHasCrc == null ? stats.numberOfFolders : stats.numberOfFolders - stats.folderHasCrc.cardinality();
        } else {
            int folderIdx2 = 0;
            Iterator<Integer> it2 = numUnpackSubStreamsPerFolder.iterator();
            while (it2.hasNext()) {
                int numUnpackSubStreams2 = it2.next().intValue();
                if (numUnpackSubStreams2 == 1 && stats.folderHasCrc != null) {
                    int folderIdx3 = folderIdx2 + 1;
                    if (stats.folderHasCrc.get(folderIdx2)) {
                        folderIdx2 = folderIdx3;
                    } else {
                        folderIdx2 = folderIdx3;
                    }
                }
                numDigests += numUnpackSubStreams2;
            }
            folderIdx = numDigests;
        }
        if (nid == 10) {
            assertFitsIntoNonNegativeInt("numDigests", folderIdx);
            int missingCrcs = readAllOrBits(header, folderIdx).cardinality();
            if (skipBytesFully(header, missingCrcs * 4) < missingCrcs * 4) {
                throw new IOException("invalid number of missing CRCs in SubStreamInfo");
            }
            nid = getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
    }

    private void sanityCheckUnpackInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
        int nid = getUnsignedByte(header);
        if (nid != 11) {
            throw new IOException("Expected kFolder, got " + nid);
        }
        long numFolders = readUint64(header);
        stats.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", numFolders);
        int external = getUnsignedByte(header);
        if (external != 0) {
            throw new IOException("External unsupported");
        }
        List<Integer> numberOfOutputStreamsPerFolder = new LinkedList<>();
        for (int i = 0; i < stats.numberOfFolders; i++) {
            numberOfOutputStreamsPerFolder.add(Integer.valueOf(sanityCheckFolder(header, stats)));
        }
        long totalNumberOfBindPairs = stats.numberOfOutStreams - stats.numberOfFolders;
        long packedStreamsRequiredByFolders = stats.numberOfInStreams - totalNumberOfBindPairs;
        if (packedStreamsRequiredByFolders < stats.numberOfPackedStreams) {
            throw new IOException("archive doesn't contain enough packed streams");
        }
        int nid2 = getUnsignedByte(header);
        if (nid2 != 12) {
            throw new IOException("Expected kCodersUnpackSize, got " + nid2);
        }
        Iterator<Integer> it = numberOfOutputStreamsPerFolder.iterator();
        while (it.hasNext()) {
            int numberOfOutputStreams = it.next().intValue();
            for (int i2 = 0; i2 < numberOfOutputStreams; i2++) {
                long unpackSize = readUint64(header);
                if (unpackSize < 0) {
                    throw new IllegalArgumentException("negative unpackSize");
                }
            }
        }
        int nid3 = getUnsignedByte(header);
        if (nid3 == 10) {
            stats.folderHasCrc = readAllOrBits(header, stats.numberOfFolders);
            int crcsDefined = stats.folderHasCrc.cardinality();
            if (skipBytesFully(header, crcsDefined * 4) < crcsDefined * 4) {
                throw new IOException("invalid number of CRCs in UnpackInfo");
            }
            nid3 = getUnsignedByte(header);
        }
        if (nid3 != 0) {
            throw new IOException("Badly terminated UnpackInfo");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean skipEntriesWhenNeeded(int entryIndex, boolean isInSameFolder, int folderIndex) throws IOException {
        SevenZArchiveEntry file = this.archive.files[entryIndex];
        if (this.currentEntryIndex == entryIndex && !hasCurrentEntryBeenRead()) {
            return false;
        }
        int filesToSkipStartIndex = this.archive.streamMap.folderFirstFileIndex[this.currentFolderIndex];
        if (isInSameFolder) {
            if (this.currentEntryIndex < entryIndex) {
                filesToSkipStartIndex = this.currentEntryIndex + 1;
            } else {
                reopenFolderInputStream(folderIndex, file);
            }
        }
        for (int i = filesToSkipStartIndex; i < entryIndex; i++) {
            SevenZArchiveEntry fileToSkip = this.archive.files[i];
            InputStream fileStreamToSkip = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.currentFolderInputStream)).setMaxCount(fileToSkip.getSize())).setPropagateClose(false)).get();
            if (fileToSkip.getHasCrc()) {
                fileStreamToSkip = ((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(fileStreamToSkip)).setCountThreshold(fileToSkip.getSize()).setExpectedChecksumValue(fileToSkip.getCrcValue()).get();
            }
            this.deferredBlockStreams.add(fileStreamToSkip);
            fileToSkip.setContentMethods(file.getContentMethods());
        }
        return true;
    }

    public String toString() {
        return this.archive.toString();
    }

    private Archive tryToLocateEndHeader(byte[] password) throws IOException {
        long minPos;
        int i = 1;
        ByteBuffer nidBuf = ByteBuffer.allocate(1);
        long previousDataSize = this.channel.position() + 20;
        if (this.channel.position() + FileUtils.ONE_MB > this.channel.size()) {
            minPos = this.channel.position();
        } else {
            minPos = this.channel.size() - FileUtils.ONE_MB;
        }
        long j = 1;
        long pos = this.channel.size() - 1;
        while (pos > minPos) {
            pos -= j;
            this.channel.position(pos);
            nidBuf.rewind();
            if (this.channel.read(nidBuf) < i) {
                throw new EOFException();
            }
            int nid = nidBuf.array()[0];
            if (nid == 23 || nid == i) {
                long nextHeaderOffset = pos - previousDataSize;
                try {
                    long nextHeaderSize = this.channel.size() - pos;
                    StartHeader startHeader = new StartHeader(nextHeaderOffset, nextHeaderSize, 0L);
                    try {
                        Archive result = initializeArchive(startHeader, password, false);
                        if (result.packSizes.length > 0 && result.files.length > 0) {
                            return result;
                        }
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                }
            }
            i = 1;
            j = 1;
        }
        throw new IOException("Start header corrupt and unable to guess end header");
    }
}
