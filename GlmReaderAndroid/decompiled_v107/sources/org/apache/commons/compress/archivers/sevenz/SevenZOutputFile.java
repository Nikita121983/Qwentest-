package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.IntToLongFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.output.CountingOutputStream;

/* loaded from: classes9.dex */
public class SevenZOutputFile implements Closeable {
    private CountingOutputStream[] additionalCountingStreams;
    private final Map<SevenZArchiveEntry, long[]> additionalSizes;
    private AES256Options aes256Options;
    private final SeekableByteChannel channel;
    private final CRC32 compressedCrc32;
    private Iterable<? extends SevenZMethodConfiguration> contentMethods;
    private final CRC32 crc32;
    private CountingOutputStream currentOutputStream;
    private long fileBytesWritten;
    private final List<SevenZArchiveEntry> files;
    private boolean finished;
    private int numNonEmptyStreams;

    static /* synthetic */ long access$208(SevenZOutputFile x0) {
        long j = x0.fileBytesWritten;
        x0.fileBytesWritten = 1 + j;
        return j;
    }

    static /* synthetic */ long access$214(SevenZOutputFile x0, long x1) {
        long j = x0.fileBytesWritten + x1;
        x0.fileBytesWritten = j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class OutputStreamWrapper extends OutputStream {
        private static final int BUF_SIZE = 8192;
        private final ByteBuffer buffer;

        private OutputStreamWrapper() {
            this.buffer = ByteBuffer.allocate(8192);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // java.io.OutputStream
        public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            if (len > 8192) {
                SevenZOutputFile.this.channel.write(ByteBuffer.wrap(b, off, len));
            } else {
                this.buffer.clear();
                this.buffer.put(b, off, len).flip();
                SevenZOutputFile.this.channel.write(this.buffer);
            }
            SevenZOutputFile.this.compressedCrc32.update(b, off, len);
            SevenZOutputFile.access$214(SevenZOutputFile.this, len);
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            this.buffer.clear();
            this.buffer.put((byte) b).flip();
            SevenZOutputFile.this.channel.write(this.buffer);
            SevenZOutputFile.this.compressedCrc32.update(b);
            SevenZOutputFile.access$208(SevenZOutputFile.this);
        }
    }

    private static <T> Iterable<T> reverse(Iterable<T> i) {
        LinkedList<T> l = new LinkedList<>();
        for (T t : i) {
            l.addFirst(t);
        }
        return l;
    }

    public SevenZOutputFile(File fileName) throws IOException {
        this(fileName, (char[]) null);
    }

    public SevenZOutputFile(File fileName, char[] password) throws IOException {
        this(Files.newByteChannel(fileName.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]), password);
    }

    public SevenZOutputFile(SeekableByteChannel channel) throws IOException {
        this(channel, (char[]) null);
    }

    public SevenZOutputFile(SeekableByteChannel channel, char[] password) throws IOException {
        this.files = new ArrayList();
        this.crc32 = new CRC32();
        this.compressedCrc32 = new CRC32();
        this.contentMethods = Collections.singletonList(new SevenZMethodConfiguration(SevenZMethod.LZMA2));
        this.additionalSizes = new HashMap();
        this.channel = channel;
        channel.position(32L);
        if (password != null) {
            this.aes256Options = new AES256Options(password);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            this.channel.close();
        }
    }

    public void closeArchiveEntry() throws IOException {
        if (this.currentOutputStream != null) {
            this.currentOutputStream.flush();
            this.currentOutputStream.close();
        }
        SevenZArchiveEntry entry = this.files.get(this.files.size() - 1);
        if (this.fileBytesWritten > 0) {
            entry.setHasStream(true);
            this.numNonEmptyStreams++;
            entry.setSize(this.currentOutputStream.getByteCount());
            entry.setCompressedSize(this.fileBytesWritten);
            entry.setCrcValue(this.crc32.getValue());
            entry.setCompressedCrcValue(this.compressedCrc32.getValue());
            entry.setHasCrc(true);
            if (this.additionalCountingStreams != null) {
                long[] sizes = new long[this.additionalCountingStreams.length];
                Arrays.setAll(sizes, new IntToLongFunction() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZOutputFile$$ExternalSyntheticLambda1
                    @Override // java.util.function.IntToLongFunction
                    public final long applyAsLong(int i) {
                        return SevenZOutputFile.this.m2057x1b9a4843(i);
                    }
                });
                this.additionalSizes.put(entry, sizes);
            }
        } else {
            entry.setHasStream(false);
            entry.setSize(0L);
            entry.setCompressedSize(0L);
            entry.setHasCrc(false);
        }
        this.currentOutputStream = null;
        this.additionalCountingStreams = null;
        this.crc32.reset();
        this.compressedCrc32.reset();
        this.fileBytesWritten = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$closeArchiveEntry$0$org-apache-commons-compress-archivers-sevenz-SevenZOutputFile, reason: not valid java name */
    public /* synthetic */ long m2057x1b9a4843(int i) {
        return this.additionalCountingStreams[i].getByteCount();
    }

    public SevenZArchiveEntry createArchiveEntry(File inputFile, String entryName) {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setDirectory(inputFile.isDirectory());
        entry.setName(entryName);
        try {
            fillDates(inputFile.toPath(), entry, new LinkOption[0]);
        } catch (IOException e) {
            entry.setLastModifiedDate(new Date(inputFile.lastModified()));
        }
        return entry;
    }

    public SevenZArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setDirectory(Files.isDirectory(inputPath, options));
        entry.setName(entryName);
        fillDates(inputPath, entry, options);
        return entry;
    }

    private void fillDates(Path inputPath, SevenZArchiveEntry entry, LinkOption... options) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(inputPath, (Class<BasicFileAttributes>) BasicFileAttributes.class, options);
        entry.setLastModifiedTime(attributes.lastModifiedTime());
        entry.setCreationTime(attributes.creationTime());
        entry.setAccessTime(attributes.lastAccessTime());
    }

    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        this.finished = true;
        long headerPosition = this.channel.position();
        ByteArrayOutputStream headerBaos = new ByteArrayOutputStream();
        DataOutputStream header = new DataOutputStream(headerBaos);
        writeHeader(header);
        header.flush();
        byte[] headerBytes = headerBaos.toByteArray();
        this.channel.write(ByteBuffer.wrap(headerBytes));
        CRC32 crc32 = new CRC32();
        crc32.update(headerBytes);
        ByteBuffer bb = ByteBuffer.allocate(SevenZFile.sevenZSignature.length + 2 + 4 + 8 + 8 + 4).order(ByteOrder.LITTLE_ENDIAN);
        this.channel.position(0L);
        bb.put(SevenZFile.sevenZSignature);
        bb.put((byte) 0).put((byte) 2);
        bb.putInt(0);
        bb.putLong(headerPosition - 32).putLong(headerBytes.length & 4294967295L).putInt((int) crc32.getValue());
        crc32.reset();
        crc32.update(bb.array(), SevenZFile.sevenZSignature.length + 6, 20);
        bb.putInt(SevenZFile.sevenZSignature.length + 2, (int) crc32.getValue());
        bb.flip();
        this.channel.write(bb);
    }

    private Iterable<? extends SevenZMethodConfiguration> getContentMethods(SevenZArchiveEntry entry) {
        Iterable<? extends SevenZMethodConfiguration> ms = entry.getContentMethods();
        Iterable<? extends SevenZMethodConfiguration> iter = ms == null ? this.contentMethods : ms;
        if (this.aes256Options != null) {
            return (Iterable) Stream.concat(Stream.of(new SevenZMethodConfiguration(SevenZMethod.AES256SHA256, this.aes256Options)), StreamSupport.stream(iter.spliterator(), false)).collect(Collectors.toList());
        }
        return iter;
    }

    private OutputStream getCurrentOutputStream() throws IOException {
        if (this.currentOutputStream == null) {
            this.currentOutputStream = setupFileOutputStream();
        }
        return this.currentOutputStream;
    }

    @Deprecated
    public void putArchiveEntry(ArchiveEntry archiveEntry) {
        putArchiveEntry((SevenZArchiveEntry) archiveEntry);
    }

    public void putArchiveEntry(SevenZArchiveEntry archiveEntry) {
        this.files.add(archiveEntry);
    }

    public void setContentCompression(SevenZMethod method) {
        setContentMethods(Collections.singletonList(new SevenZMethodConfiguration(method)));
    }

    public void setContentMethods(Iterable<? extends SevenZMethodConfiguration> methods) {
        this.contentMethods = reverse(methods);
    }

    private CountingOutputStream setupFileOutputStream() throws IOException {
        if (this.files.isEmpty()) {
            throw new IllegalStateException("No current 7z entry");
        }
        OutputStream out = new OutputStreamWrapper();
        ArrayList<CountingOutputStream> moreStreams = new ArrayList<>();
        boolean first = true;
        for (SevenZMethodConfiguration m : getContentMethods(this.files.get(this.files.size() - 1))) {
            if (!first) {
                CountingOutputStream cos = new CountingOutputStream(out);
                moreStreams.add(cos);
                out = cos;
            }
            out = Coders.addEncoder(out, m.getMethod(), m.getOptions());
            first = false;
        }
        if (!moreStreams.isEmpty()) {
            this.additionalCountingStreams = (CountingOutputStream[]) moreStreams.toArray(new CountingOutputStream[0]);
        }
        return new CountingOutputStream(out) { // from class: org.apache.commons.compress.archivers.sevenz.SevenZOutputFile.1
            @Override // org.apache.commons.io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] b) throws IOException {
                super.write(b);
                SevenZOutputFile.this.crc32.update(b);
            }

            @Override // org.apache.commons.io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] b, int off, int len) throws IOException {
                super.write(b, off, len);
                SevenZOutputFile.this.crc32.update(b, off, len);
            }

            @Override // org.apache.commons.io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(int b) throws IOException {
                super.write(b);
                SevenZOutputFile.this.crc32.update(b);
            }
        };
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (len > 0) {
            getCurrentOutputStream().write(b, off, len);
        }
    }

    public void write(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[8024];
        while (true) {
            int n = inputStream.read(buffer);
            if (-1 != n) {
                write(buffer, 0, n);
            } else {
                return;
            }
        }
    }

    public void write(int b) throws IOException {
        getCurrentOutputStream().write(b);
    }

    public void write(Path path, OpenOption... options) throws IOException {
        InputStream in = new BufferedInputStream(Files.newInputStream(path, options));
        try {
            write(in);
            in.close();
        } catch (Throwable th) {
            try {
                in.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void writeBits(DataOutput dataOutput, BitSet bitSet, int i) throws IOException {
        int i2 = 0;
        int i3 = 7;
        for (int i4 = 0; i4 < i; i4++) {
            i2 |= (bitSet.get(i4) ? 1 : 0) << i3;
            i3--;
            if (i3 < 0) {
                dataOutput.write(i2);
                i3 = 7;
                i2 = 0;
            }
        }
        if (i3 != 7) {
            dataOutput.write(i2);
        }
    }

    private void writeFileAntiItems(DataOutput header) throws IOException {
        boolean hasAntiItems = false;
        BitSet antiItems = new BitSet(0);
        int antiItemCounter = 0;
        for (SevenZArchiveEntry file1 : this.files) {
            if (file1.isEmptyStream()) {
                boolean isAnti = file1.isAntiItem();
                antiItems.set(antiItemCounter, isAnti);
                hasAntiItems |= isAnti;
                antiItemCounter++;
            }
        }
        if (hasAntiItems) {
            header.write(16);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            writeBits(out, antiItems, antiItemCounter);
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileATimes(DataOutput header) throws IOException {
        int numAccessDates = 0;
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (it.hasNext()) {
            if (it.next().getHasAccessDate()) {
                numAccessDates++;
            }
        }
        if (numAccessDates > 0) {
            header.write(19);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numAccessDates != this.files.size()) {
                out.write(0);
                BitSet aTimes = new BitSet(this.files.size());
                for (int i = 0; i < this.files.size(); i++) {
                    aTimes.set(i, this.files.get(i).getHasAccessDate());
                }
                writeBits(out, aTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (entry.getHasAccessDate()) {
                    long ntfsTime = FileTimes.toNtfsTime(entry.getAccessTime());
                    out.writeLong(Long.reverseBytes(ntfsTime));
                }
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileCTimes(DataOutput header) throws IOException {
        int numCreationDates = 0;
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (it.hasNext()) {
            if (it.next().getHasCreationDate()) {
                numCreationDates++;
            }
        }
        if (numCreationDates > 0) {
            header.write(18);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numCreationDates != this.files.size()) {
                out.write(0);
                BitSet cTimes = new BitSet(this.files.size());
                for (int i = 0; i < this.files.size(); i++) {
                    cTimes.set(i, this.files.get(i).getHasCreationDate());
                }
                writeBits(out, cTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (entry.getHasCreationDate()) {
                    long ntfsTime = FileTimes.toNtfsTime(entry.getCreationTime());
                    out.writeLong(Long.reverseBytes(ntfsTime));
                }
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileEmptyFiles(DataOutput header) throws IOException {
        boolean hasEmptyFiles = false;
        int emptyStreamCounter = 0;
        BitSet emptyFiles = new BitSet(0);
        for (SevenZArchiveEntry file1 : this.files) {
            if (file1.isEmptyStream()) {
                boolean isDir = file1.isDirectory();
                emptyFiles.set(emptyStreamCounter, !isDir);
                hasEmptyFiles |= !isDir;
                emptyStreamCounter++;
            }
        }
        if (hasEmptyFiles) {
            header.write(15);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            writeBits(out, emptyFiles, emptyStreamCounter);
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileEmptyStreams(DataOutput header) throws IOException {
        boolean hasEmptyStreams = this.files.stream().anyMatch(new Predicate() { // from class: org.apache.commons.compress.archivers.sevenz.SevenZOutputFile$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SevenZArchiveEntry) obj).isEmptyStream();
            }
        });
        if (hasEmptyStreams) {
            header.write(14);
            BitSet emptyStreams = new BitSet(this.files.size());
            for (int i = 0; i < this.files.size(); i++) {
                emptyStreams.set(i, this.files.get(i).isEmptyStream());
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            writeBits(out, emptyStreams, this.files.size());
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileMTimes(DataOutput header) throws IOException {
        int numLastModifiedDates = 0;
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (it.hasNext()) {
            if (it.next().getHasLastModifiedDate()) {
                numLastModifiedDates++;
            }
        }
        if (numLastModifiedDates > 0) {
            header.write(20);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numLastModifiedDates != this.files.size()) {
                out.write(0);
                BitSet mTimes = new BitSet(this.files.size());
                for (int i = 0; i < this.files.size(); i++) {
                    mTimes.set(i, this.files.get(i).getHasLastModifiedDate());
                }
                writeBits(out, mTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (entry.getHasLastModifiedDate()) {
                    long ntfsTime = FileTimes.toNtfsTime(entry.getLastModifiedTime());
                    out.writeLong(Long.reverseBytes(ntfsTime));
                }
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileNames(DataOutput header) throws IOException {
        header.write(17);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        out.write(0);
        for (SevenZArchiveEntry entry : this.files) {
            out.write(entry.getName().getBytes(StandardCharsets.UTF_16LE));
            out.writeShort(0);
        }
        out.flush();
        byte[] contents = baos.toByteArray();
        writeUint64(header, contents.length);
        header.write(contents);
    }

    private void writeFilesInfo(DataOutput header) throws IOException {
        header.write(5);
        writeUint64(header, this.files.size());
        writeFileEmptyStreams(header);
        writeFileEmptyFiles(header);
        writeFileAntiItems(header);
        writeFileNames(header);
        writeFileCTimes(header);
        writeFileATimes(header);
        writeFileMTimes(header);
        writeFileWindowsAttributes(header);
        header.write(0);
    }

    private void writeFileWindowsAttributes(DataOutput header) throws IOException {
        int numWindowsAttributes = 0;
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (it.hasNext()) {
            if (it.next().getHasWindowsAttributes()) {
                numWindowsAttributes++;
            }
        }
        if (numWindowsAttributes > 0) {
            header.write(21);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numWindowsAttributes != this.files.size()) {
                out.write(0);
                BitSet attributes = new BitSet(this.files.size());
                for (int i = 0; i < this.files.size(); i++) {
                    attributes.set(i, this.files.get(i).getHasWindowsAttributes());
                }
                writeBits(out, attributes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (entry.getHasWindowsAttributes()) {
                    out.writeInt(Integer.reverseBytes(entry.getWindowsAttributes()));
                }
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFolder(DataOutput header, SevenZArchiveEntry entry) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int numCoders = 0;
        for (SevenZMethodConfiguration m : getContentMethods(entry)) {
            numCoders++;
            writeSingleCodec(m, bos);
        }
        writeUint64(header, numCoders);
        header.write(bos.toByteArray());
        for (long i = 0; i < numCoders - 1; i++) {
            writeUint64(header, i + 1);
            writeUint64(header, i);
        }
    }

    private void writeHeader(DataOutput header) throws IOException {
        header.write(1);
        header.write(4);
        writeStreamsInfo(header);
        writeFilesInfo(header);
        header.write(0);
    }

    private void writePackInfo(DataOutput header) throws IOException {
        header.write(6);
        writeUint64(header, 0L);
        writeUint64(header, this.numNonEmptyStreams & 4294967295L);
        header.write(9);
        for (SevenZArchiveEntry entry : this.files) {
            if (entry.hasStream()) {
                writeUint64(header, entry.getCompressedSize());
            }
        }
        header.write(10);
        header.write(1);
        for (SevenZArchiveEntry entry2 : this.files) {
            if (entry2.hasStream()) {
                header.writeInt(Integer.reverseBytes((int) entry2.getCompressedCrcValue()));
            }
        }
        header.write(0);
    }

    private void writeSingleCodec(SevenZMethodConfiguration m, OutputStream bos) throws IOException {
        byte[] id = m.getMethod().getId();
        byte[] properties = Coders.findByMethod(m.getMethod()).getOptionsAsProperties(m.getOptions());
        int codecFlags = id.length;
        if (properties.length > 0) {
            codecFlags |= 32;
        }
        bos.write(codecFlags);
        bos.write(id);
        if (properties.length > 0) {
            bos.write(properties.length);
            bos.write(properties);
        }
    }

    private void writeStreamsInfo(DataOutput header) throws IOException {
        if (this.numNonEmptyStreams > 0) {
            writePackInfo(header);
            writeUnpackInfo(header);
        }
        writeSubStreamsInfo(header);
        header.write(0);
    }

    private void writeSubStreamsInfo(DataOutput header) throws IOException {
        header.write(8);
        header.write(0);
    }

    private void writeUint64(DataOutput header, long value) throws IOException {
        int firstByte = 0;
        int mask = 128;
        int i = 0;
        while (true) {
            if (i >= 8) {
                break;
            }
            if (value < (1 << ((i + 1) * 7))) {
                firstByte = (int) (firstByte | (value >>> (i * 8)));
                break;
            } else {
                firstByte |= mask;
                mask >>>= 1;
                i++;
            }
        }
        header.write(firstByte);
        while (i > 0) {
            header.write((int) (255 & value));
            value >>>= 8;
            i--;
        }
    }

    private void writeUnpackInfo(DataOutput header) throws IOException {
        header.write(7);
        header.write(11);
        writeUint64(header, this.numNonEmptyStreams);
        header.write(0);
        for (SevenZArchiveEntry entry : this.files) {
            if (entry.hasStream()) {
                writeFolder(header, entry);
            }
        }
        header.write(12);
        for (SevenZArchiveEntry entry2 : this.files) {
            if (entry2.hasStream()) {
                long[] moreSizes = this.additionalSizes.get(entry2);
                if (moreSizes != null) {
                    for (long s : moreSizes) {
                        writeUint64(header, s);
                    }
                }
                writeUint64(header, entry2.getSize());
            }
        }
        header.write(10);
        header.write(1);
        for (SevenZArchiveEntry entry3 : this.files) {
            if (entry3.hasStream()) {
                header.writeInt(Integer.reverseBytes((int) entry3.getCrcValue()));
            }
        }
        header.write(0);
    }
}
