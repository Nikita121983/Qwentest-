package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.io.file.PathUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class ZipSplitOutputStream extends RandomAccessOutputStream {
    private static final long ZIP_SEGMENT_MAX_SIZE = 4294967295L;
    private static final long ZIP_SEGMENT_MIN_SIZE = 65536;
    private FileChannel currentChannel;
    private long currentSplitSegmentBytesWritten;
    private int currentSplitSegmentIndex;
    private final List<Long> diskToPosition;
    private boolean finished;
    private FileRandomAccessOutputStream outputStream;
    private final TreeMap<Long, Path> positionToFiles;
    private final byte[] singleByte;
    private final long splitSize;
    private long totalPosition;
    private Path zipFile;

    ZipSplitOutputStream(File zipFile, long splitSize) throws IllegalArgumentException, IOException {
        this(zipFile.toPath(), splitSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipSplitOutputStream(Path zipFile, long splitSize) throws IllegalArgumentException, IOException {
        this.singleByte = new byte[1];
        this.diskToPosition = new ArrayList();
        this.positionToFiles = new TreeMap<>();
        if (splitSize < ZIP_SEGMENT_MIN_SIZE || splitSize > ZIP_SEGMENT_MAX_SIZE) {
            throw new IllegalArgumentException("Zip split segment size should between 64K and 4,294,967,295");
        }
        this.zipFile = zipFile;
        this.splitSize = splitSize;
        this.outputStream = new FileRandomAccessOutputStream(zipFile);
        this.currentChannel = this.outputStream.channel();
        this.positionToFiles.put(0L, this.zipFile);
        this.diskToPosition.add(0L);
        writeZipSplitSignature();
    }

    public long calculateDiskPosition(long disk, long localOffset) throws IOException {
        if (disk >= 2147483647L) {
            throw new IOException("Disk number exceeded internal limits: limit=2147483647 requested=" + disk);
        }
        return this.diskToPosition.get((int) disk).longValue() + localOffset;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
    }

    private Path createNewSplitSegmentFile(Integer zipSplitSegmentSuffixIndex) throws IOException {
        Path newFile = getSplitSegmentFileName(zipSplitSegmentSuffixIndex);
        if (Files.exists(newFile, new LinkOption[0])) {
            throw new IOException("split ZIP segment " + newFile + " already exists");
        }
        return newFile;
    }

    private void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        Path path = this.zipFile;
        String zipFileBaseName = PathUtils.getBaseName(path);
        this.outputStream.close();
        Files.move(this.zipFile, this.zipFile.resolveSibling(zipFileBaseName + ".zip"), StandardCopyOption.ATOMIC_MOVE);
        this.finished = true;
    }

    public long getCurrentSplitSegmentBytesWritten() {
        return this.currentSplitSegmentBytesWritten;
    }

    public int getCurrentSplitSegmentIndex() {
        return this.currentSplitSegmentIndex;
    }

    private Path getSplitSegmentFileName(Integer zipSplitSegmentSuffixIndex) {
        int newZipSplitSegmentSuffixIndex = zipSplitSegmentSuffixIndex == null ? this.currentSplitSegmentIndex + 2 : zipSplitSegmentSuffixIndex.intValue();
        Path path = this.zipFile;
        String baseName = PathUtils.getBaseName(path);
        StringBuilder extension = new StringBuilder(".z");
        if (newZipSplitSegmentSuffixIndex <= 9) {
            extension.append("0").append(newZipSplitSegmentSuffixIndex);
        } else {
            extension.append(newZipSplitSegmentSuffixIndex);
        }
        Path parent = this.zipFile.getParent();
        Objects.nonNull(parent);
        String dir = parent != null ? parent.toAbsolutePath().toString() : ".";
        return this.zipFile.getFileSystem().getPath(dir, baseName + extension.toString());
    }

    private void openNewSplitSegment() throws IOException {
        if (this.currentSplitSegmentIndex == 0) {
            this.outputStream.close();
            Path newFile = createNewSplitSegmentFile(1);
            Files.move(this.zipFile, newFile, StandardCopyOption.ATOMIC_MOVE);
            this.positionToFiles.put(0L, newFile);
        }
        Path newFile2 = createNewSplitSegmentFile(null);
        this.outputStream.close();
        this.outputStream = new FileRandomAccessOutputStream(newFile2);
        this.currentChannel = this.outputStream.channel();
        this.currentSplitSegmentBytesWritten = 0L;
        this.zipFile = newFile2;
        this.currentSplitSegmentIndex++;
        this.diskToPosition.add(Long.valueOf(this.totalPosition));
        this.positionToFiles.put(Long.valueOf(this.totalPosition), newFile2);
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public long position() {
        return this.totalPosition;
    }

    public void prepareToWriteUnsplittableContent(long unsplittableContentSize) throws IllegalArgumentException, IOException {
        if (unsplittableContentSize > this.splitSize) {
            throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
        }
        long bytesRemainingInThisSegment = this.splitSize - this.currentSplitSegmentBytesWritten;
        if (bytesRemainingInThisSegment < unsplittableContentSize) {
            openNewSplitSegment();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if (len <= 0) {
            return;
        }
        if (this.currentSplitSegmentBytesWritten >= this.splitSize) {
            openNewSplitSegment();
            write(b, off, len);
        } else {
            if (this.currentSplitSegmentBytesWritten + len > this.splitSize) {
                int bytesToWriteForThisSegment = ((int) this.splitSize) - ((int) this.currentSplitSegmentBytesWritten);
                write(b, off, bytesToWriteForThisSegment);
                openNewSplitSegment();
                write(b, off + bytesToWriteForThisSegment, len - bytesToWriteForThisSegment);
                return;
            }
            this.outputStream.write(b, off, len);
            this.currentSplitSegmentBytesWritten += len;
            this.totalPosition += len;
        }
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        this.singleByte[0] = (byte) (i & 255);
        write(this.singleByte);
    }

    @Override // org.apache.commons.compress.archivers.zip.RandomAccessOutputStream
    public void writeAll(byte[] b, int off, int len, long atPosition) throws IOException {
        long remainingPosition = atPosition;
        int remainingOff = off;
        int toWrite = len;
        while (toWrite > 0) {
            Map.Entry<Long, Path> segment = this.positionToFiles.floorEntry(Long.valueOf(remainingPosition));
            Long segmentEnd = this.positionToFiles.higherKey(Long.valueOf(remainingPosition));
            if (segmentEnd == null) {
                ZipIoUtil.writeAll(this.currentChannel, ByteBuffer.wrap(b, remainingOff, toWrite), remainingPosition - segment.getKey().longValue());
                remainingPosition += toWrite;
                remainingOff += toWrite;
                toWrite = 0;
            } else if (toWrite + remainingPosition <= segmentEnd.longValue()) {
                writeToSegment(segment.getValue(), remainingPosition - segment.getKey().longValue(), b, remainingOff, toWrite);
                int remainingLen = toWrite;
                remainingPosition += remainingLen;
                remainingOff += remainingLen;
                toWrite = 0;
            } else {
                int remainingLen2 = toWrite;
                int toWrite2 = Math.toIntExact(segmentEnd.longValue() - remainingPosition);
                writeToSegment(segment.getValue(), remainingPosition - segment.getKey().longValue(), b, remainingOff, toWrite2);
                remainingPosition += toWrite2;
                remainingOff += toWrite2;
                toWrite = remainingLen2 - toWrite2;
            }
        }
    }

    private void writeToSegment(Path segment, long position, byte[] b, int off, int len) throws IOException {
        FileChannel channel = FileChannel.open(segment, StandardOpenOption.WRITE);
        try {
            ZipIoUtil.writeAll(channel, ByteBuffer.wrap(b, off, len), position);
            if (channel != null) {
                channel.close();
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void writeZipSplitSignature() throws IOException {
        this.outputStream.write(ZipArchiveOutputStream.DD_SIG);
        this.currentSplitSegmentBytesWritten += ZipArchiveOutputStream.DD_SIG.length;
        this.totalPosition += ZipArchiveOutputStream.DD_SIG.length;
    }
}
