package org.apache.commons.compress.archivers.dump;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

/* loaded from: classes9.dex */
public class DumpArchiveInputStream extends ArchiveInputStream<DumpArchiveEntry> {
    private static final String CURRENT_PATH_SEGMENT = ".";
    private static final String PARENT_PATH_SEGMENT = "..";
    private DumpArchiveEntry active;
    private byte[] blockBuffer;
    private long entryOffset;
    private long entrySize;
    private long filepos;
    private boolean hasHitEOF;
    private boolean isClosed;
    private final Map<Integer, Dirent> names;
    private final Map<Integer, DumpArchiveEntry> pending;
    private final Queue<DumpArchiveEntry> queue;
    protected TapeInputStream raw;
    private final byte[] readBuf;
    private int readIdx;
    private int recordOffset;
    private final DumpArchiveSummary summary;
    private final ZipEncoding zipEncoding;

    public static boolean matches(byte[] buffer, int length) {
        if (length < 32) {
            return false;
        }
        if (length >= 1024) {
            return DumpArchiveUtil.verify(buffer);
        }
        return 60012 == DumpArchiveUtil.convert32(buffer, 24);
    }

    public DumpArchiveInputStream(InputStream is) throws ArchiveException {
        this(is, null);
    }

    public DumpArchiveInputStream(InputStream is, String encoding) throws ArchiveException {
        super(is, encoding);
        this.readBuf = new byte[1024];
        this.names = new HashMap();
        this.pending = new HashMap();
        this.raw = new TapeInputStream(is);
        this.hasHitEOF = false;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        try {
            byte[] headerBytes = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(headerBytes)) {
                throw new UnrecognizedFormatException();
            }
            this.summary = new DumpArchiveSummary(headerBytes, this.zipEncoding);
            this.raw.resetBlockSize(this.summary.getNTRec(), this.summary.isCompressed());
            this.blockBuffer = new byte[4096];
            readCLRI();
            readBITS();
            Dirent root = new Dirent(2, 2, 4, CURRENT_PATH_SEGMENT);
            this.names.put(2, root);
            this.queue = new PriorityQueue(10, new Comparator() { // from class: org.apache.commons.compress.archivers.dump.DumpArchiveInputStream$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return DumpArchiveInputStream.lambda$new$0((DumpArchiveEntry) obj, (DumpArchiveEntry) obj2);
                }
            });
        } catch (IOException e) {
            throw new ArchiveException(e.getMessage(), (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(DumpArchiveEntry p, DumpArchiveEntry q) {
        if (p.getOriginalName() == null || q.getOriginalName() == null) {
            return Integer.MAX_VALUE;
        }
        return p.getOriginalName().compareTo(q.getOriginalName());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.isClosed) {
            this.isClosed = true;
            this.raw.close();
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public long getBytesRead() {
        return this.raw.getBytesRead();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    @Deprecated
    public int getCount() {
        return (int) getBytesRead();
    }

    @Deprecated
    public DumpArchiveEntry getNextDumpEntry() throws IOException {
        return getNextEntry();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public DumpArchiveEntry getNextEntry() throws IOException {
        DumpArchiveEntry entry = null;
        String path = null;
        if (!this.queue.isEmpty()) {
            return this.queue.remove();
        }
        while (entry == null) {
            if (this.hasHitEOF) {
                return null;
            }
            while (this.readIdx < this.active.getHeaderCount()) {
                DumpArchiveEntry dumpArchiveEntry = this.active;
                int i = this.readIdx;
                this.readIdx = i + 1;
                if (!dumpArchiveEntry.isSparseRecord(i) && this.raw.skip(FileUtils.ONE_KB) == -1) {
                    throw new EOFException();
                }
            }
            this.readIdx = 0;
            this.filepos = this.raw.getBytesRead();
            byte[] headerBytes = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(headerBytes)) {
                throw new InvalidFormatException();
            }
            this.active = DumpArchiveEntry.parse(headerBytes);
            while (DumpArchiveConstants.SEGMENT_TYPE.ADDR == this.active.getHeaderType()) {
                if (this.raw.skip((this.active.getHeaderCount() - this.active.getHeaderHoles()) * FileUtils.ONE_KB) == -1) {
                    throw new EOFException();
                }
                this.filepos = this.raw.getBytesRead();
                byte[] headerBytes2 = this.raw.readRecord();
                if (!DumpArchiveUtil.verify(headerBytes2)) {
                    throw new InvalidFormatException();
                }
                this.active = DumpArchiveEntry.parse(headerBytes2);
            }
            if (DumpArchiveConstants.SEGMENT_TYPE.END == this.active.getHeaderType()) {
                this.hasHitEOF = true;
                return null;
            }
            entry = this.active;
            if (entry.isDirectory()) {
                readDirectoryEntry(this.active);
                this.entryOffset = 0L;
                this.entrySize = 0L;
                this.readIdx = this.active.getHeaderCount();
            } else {
                this.entryOffset = 0L;
                this.entrySize = this.active.getEntrySize();
                this.readIdx = 0;
            }
            this.recordOffset = this.readBuf.length;
            path = getPath(entry);
            if (path == null) {
                entry = null;
            }
        }
        entry.setName(path);
        entry.setSimpleName(this.names.get(Integer.valueOf(entry.getIno())).getName());
        entry.setOffset(this.filepos);
        return entry;
    }

    private String getPath(DumpArchiveEntry entry) throws DumpArchiveException {
        Stack<String> elements = new Stack<>();
        BitSet visited = new BitSet();
        int i = entry.getIno();
        while (true) {
            if (!this.names.containsKey(Integer.valueOf(i))) {
                elements.clear();
                break;
            }
            if (visited.get(i)) {
                throw new DumpArchiveException("Duplicate node " + i);
            }
            Dirent dirent = this.names.get(Integer.valueOf(i));
            visited.set(i);
            elements.push(dirent.getName());
            if (dirent.getIno() == dirent.getParentIno()) {
                break;
            }
            i = dirent.getParentIno();
        }
        if (elements.isEmpty()) {
            this.pending.put(Integer.valueOf(entry.getIno()), entry);
            return null;
        }
        StringBuilder sb = new StringBuilder(elements.pop());
        while (!elements.isEmpty()) {
            sb.append('/');
            sb.append(elements.pop());
        }
        return sb.toString();
    }

    public DumpArchiveSummary getSummary() {
        return this.summary;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int totalRead = 0;
        if (this.hasHitEOF || this.isClosed || this.entryOffset >= this.entrySize) {
            return -1;
        }
        if (this.active == null) {
            throw new IllegalStateException("No current dump entry");
        }
        if (len + this.entryOffset > this.entrySize) {
            len = (int) (this.entrySize - this.entryOffset);
        }
        while (len > 0) {
            int sz = Math.min(len, this.readBuf.length - this.recordOffset);
            if (this.recordOffset + sz <= this.readBuf.length) {
                System.arraycopy(this.readBuf, this.recordOffset, buf, off, sz);
                totalRead += sz;
                this.recordOffset += sz;
                len -= sz;
                off += sz;
            }
            if (len > 0) {
                if (this.readIdx >= 512) {
                    byte[] headerBytes = this.raw.readRecord();
                    if (!DumpArchiveUtil.verify(headerBytes)) {
                        throw new InvalidFormatException();
                    }
                    this.active = DumpArchiveEntry.parse(headerBytes);
                    this.readIdx = 0;
                }
                DumpArchiveEntry dumpArchiveEntry = this.active;
                int i = this.readIdx;
                this.readIdx = i + 1;
                if (!dumpArchiveEntry.isSparseRecord(i)) {
                    int r = this.raw.read(this.readBuf, 0, this.readBuf.length);
                    if (r != this.readBuf.length) {
                        throw new EOFException();
                    }
                } else {
                    Arrays.fill(this.readBuf, (byte) 0);
                }
                this.recordOffset = 0;
            }
        }
        this.entryOffset += totalRead;
        return totalRead;
    }

    private void readBITS() throws IOException {
        byte[] buffer = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(buffer)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(buffer);
        if (DumpArchiveConstants.SEGMENT_TYPE.BITS != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(this.active.getHeaderCount() * FileUtils.ONE_KB) == -1) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    private void readCLRI() throws IOException {
        byte[] buffer = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(buffer)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(buffer);
        if (DumpArchiveConstants.SEGMENT_TYPE.CLRI != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(this.active.getHeaderCount() * FileUtils.ONE_KB) == -1) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    private void readDirectoryEntry(DumpArchiveEntry entry) throws IOException {
        DumpArchiveEntry entry2;
        long size;
        DumpArchiveEntry entry3;
        long size2;
        long size3 = entry.getEntrySize();
        boolean first = true;
        long size4 = size3;
        DumpArchiveEntry entry4 = entry;
        while (true) {
            if (first || DumpArchiveConstants.SEGMENT_TYPE.ADDR == entry4.getHeaderType()) {
                if (!first) {
                    this.raw.readRecord();
                }
                if (!this.names.containsKey(Integer.valueOf(entry4.getIno())) && DumpArchiveConstants.SEGMENT_TYPE.INODE == entry4.getHeaderType()) {
                    this.pending.put(Integer.valueOf(entry4.getIno()), entry4);
                }
                int datalen = entry4.getHeaderCount() * 1024;
                if (this.blockBuffer.length < datalen) {
                    this.blockBuffer = IOUtils.readRange(this.raw, datalen);
                    if (this.blockBuffer.length != datalen) {
                        throw new EOFException();
                    }
                } else if (this.raw.read(this.blockBuffer, 0, datalen) != datalen) {
                    throw new EOFException();
                }
                int i = 0;
                while (i < datalen - 8 && i < size4 - 8) {
                    int ino = DumpArchiveUtil.convert32(this.blockBuffer, i);
                    int reclen = DumpArchiveUtil.convert16(this.blockBuffer, i + 4);
                    if (reclen == 0) {
                        throw new DumpArchiveException("reclen cannot be 0");
                    }
                    byte type = this.blockBuffer[i + 6];
                    String name = DumpArchiveUtil.decode(this.zipEncoding, this.blockBuffer, i + 8, this.blockBuffer[i + 7]);
                    if (CURRENT_PATH_SEGMENT.equals(name)) {
                        entry2 = entry4;
                        size = size4;
                    } else if (PARENT_PATH_SEGMENT.equals(name)) {
                        entry2 = entry4;
                        size = size4;
                    } else {
                        Dirent d = new Dirent(ino, entry4.getIno(), type, name);
                        this.names.put(Integer.valueOf(ino), d);
                        for (Map.Entry<Integer, DumpArchiveEntry> mapEntry : this.pending.entrySet()) {
                            DumpArchiveEntry v = mapEntry.getValue();
                            String path = getPath(v);
                            if (path == null) {
                                entry3 = entry4;
                                size2 = size4;
                            } else {
                                v.setName(path);
                                entry3 = entry4;
                                size2 = size4;
                                v.setSimpleName(this.names.get(mapEntry.getKey()).getName());
                                this.queue.add(v);
                            }
                            entry4 = entry3;
                            size4 = size2;
                        }
                        entry2 = entry4;
                        size = size4;
                        this.queue.forEach(new Consumer() { // from class: org.apache.commons.compress.archivers.dump.DumpArchiveInputStream$$ExternalSyntheticLambda1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                DumpArchiveInputStream.this.m2056x293e921c((DumpArchiveEntry) obj);
                            }
                        });
                    }
                    i += reclen;
                    entry4 = entry2;
                    size4 = size;
                }
                long size5 = size4;
                byte[] peekBytes = this.raw.peek();
                if (!DumpArchiveUtil.verify(peekBytes)) {
                    throw new InvalidFormatException();
                }
                first = false;
                entry4 = DumpArchiveEntry.parse(peekBytes);
                size4 = size5 - FileUtils.ONE_KB;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readDirectoryEntry$1$org-apache-commons-compress-archivers-dump-DumpArchiveInputStream, reason: not valid java name */
    public /* synthetic */ void m2056x293e921c(DumpArchiveEntry e) {
        this.pending.remove(Integer.valueOf(e.getIno()));
    }
}
