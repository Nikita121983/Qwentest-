package org.apache.commons.compress.archivers.cpio;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.UByte;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

/* loaded from: classes9.dex */
public class CpioArchiveOutputStream extends ArchiveOutputStream<CpioArchiveEntry> implements CpioConstants {
    private static final char NUL = 0;
    private final int blockSize;
    final String charsetName;
    private long crc;
    private CpioArchiveEntry entry;
    private final short entryFormat;
    private final HashMap<String, CpioArchiveEntry> names;
    private long nextArtificalDeviceAndInode;
    private long written;
    private final ZipEncoding zipEncoding;

    public CpioArchiveOutputStream(OutputStream out) {
        this(out, (short) 1);
    }

    public CpioArchiveOutputStream(OutputStream out, short format) {
        this(out, format, 512, CpioUtil.DEFAULT_CHARSET_NAME);
    }

    public CpioArchiveOutputStream(OutputStream out, short format, int blockSize) {
        this(out, format, blockSize, CpioUtil.DEFAULT_CHARSET_NAME);
    }

    public CpioArchiveOutputStream(OutputStream out, short format, int blockSize, String encoding) {
        super(out);
        this.names = new HashMap<>();
        this.nextArtificalDeviceAndInode = 1L;
        switch (format) {
            case 1:
            case 2:
            case 4:
            case 8:
                this.entryFormat = format;
                this.blockSize = blockSize;
                this.charsetName = encoding;
                this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
                return;
            default:
                throw new IllegalArgumentException("Unknown format: " + ((int) format));
        }
    }

    public CpioArchiveOutputStream(OutputStream out, String encoding) {
        this(out, (short) 1, 512, encoding);
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
        checkOpen();
        if (this.entry == null) {
            throw new IOException("Trying to close non-existent entry");
        }
        if (this.entry.getSize() != this.written) {
            throw new IOException("Invalid entry size (expected " + this.entry.getSize() + " but got " + this.written + " bytes)");
        }
        pad(this.entry.getDataPadCount());
        if (this.entry.getFormat() == 2 && this.crc != this.entry.getChksum()) {
            throw new IOException("CRC Error");
        }
        this.entry = null;
        this.crc = 0L;
        this.written = 0L;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public CpioArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        checkFinished();
        return new CpioArchiveEntry(inputFile, entryName);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public CpioArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
        checkFinished();
        return new CpioArchiveEntry(inputPath, entryName, options);
    }

    private byte[] encode(String str) throws IOException {
        ByteBuffer buf = this.zipEncoding.encode(str);
        int len = buf.limit() - buf.position();
        return Arrays.copyOfRange(buf.array(), buf.arrayOffset(), buf.arrayOffset() + len);
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        checkOpen();
        checkFinished();
        if (this.entry != null) {
            throw new IOException("This archive contains unclosed entries.");
        }
        this.entry = new CpioArchiveEntry(this.entryFormat);
        this.entry.setName(CpioConstants.CPIO_TRAILER);
        this.entry.setNumberOfLinks(1L);
        writeHeader(this.entry);
        closeArchiveEntry();
        int lengthOfLastBlock = (int) (getBytesWritten() % this.blockSize);
        if (lengthOfLastBlock != 0) {
            pad(this.blockSize - lengthOfLastBlock);
        }
        super.finish();
    }

    private void pad(int count) throws IOException {
        if (count > 0) {
            this.out.write(new byte[count]);
            count(count);
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveOutputStream
    public void putArchiveEntry(CpioArchiveEntry entry) throws IOException {
        checkFinished();
        checkOpen();
        if (this.entry != null) {
            closeArchiveEntry();
        }
        if (entry.getTime() == -1) {
            entry.setTime(System.currentTimeMillis() / 1000);
        }
        short format = entry.getFormat();
        if (format != this.entryFormat) {
            throw new IOException("Header format: " + ((int) format) + " does not match existing format: " + ((int) this.entryFormat));
        }
        if (this.names.put(entry.getName(), entry) != null) {
            throw new IOException("Duplicate entry: " + entry.getName());
        }
        writeHeader(entry);
        this.entry = entry;
        this.written = 0L;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        checkOpen();
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }
        if (this.entry == null) {
            throw new IOException("No current CPIO entry");
        }
        if (this.written + len > this.entry.getSize()) {
            throw new IOException("Attempt to write past end of STORED entry");
        }
        this.out.write(b, off, len);
        this.written += len;
        if (this.entry.getFormat() == 2) {
            for (int pos = 0; pos < len; pos++) {
                this.crc += b[pos] & UByte.MAX_VALUE;
                this.crc &= 4294967295L;
            }
        }
        count(len);
    }

    private void writeAsciiLong(long number, int length, int radix) throws IOException {
        String tmpStr;
        StringBuilder tmp = new StringBuilder();
        if (radix == 16) {
            tmp.append(Long.toHexString(number));
        } else if (radix == 8) {
            tmp.append(Long.toOctalString(number));
        } else {
            tmp.append(number);
        }
        if (tmp.length() <= length) {
            int insertLength = length - tmp.length();
            for (int pos = 0; pos < insertLength; pos++) {
                tmp.insert(0, "0");
            }
            tmpStr = tmp.toString();
        } else {
            tmpStr = tmp.substring(tmp.length() - length);
        }
        byte[] b = writeUsAsciiRaw(tmpStr);
        count(b.length);
    }

    private void writeBinaryLong(long number, int length, boolean swapHalfWord) throws IOException {
        byte[] tmp = CpioUtil.long2byteArray(number, length, swapHalfWord);
        this.out.write(tmp);
        count(tmp.length);
    }

    private void writeCString(byte[] str) throws IOException {
        this.out.write(str);
        this.out.write(0);
        count(str.length + 1);
    }

    private void writeHeader(CpioArchiveEntry e) throws IOException {
        switch (e.getFormat()) {
            case 1:
                writeUsAsciiRaw(CpioConstants.MAGIC_NEW);
                count(6);
                writeNewEntry(e);
                return;
            case 2:
                writeUsAsciiRaw(CpioConstants.MAGIC_NEW_CRC);
                count(6);
                writeNewEntry(e);
                return;
            case 4:
                writeUsAsciiRaw(CpioConstants.MAGIC_OLD_ASCII);
                count(6);
                writeOldAsciiEntry(e);
                return;
            case 8:
                writeBinaryLong(29127L, 2, true);
                writeOldBinaryEntry(e, true);
                return;
            default:
                throw new IOException("Unknown format " + ((int) e.getFormat()));
        }
    }

    private void writeNewEntry(CpioArchiveEntry entry) throws IOException {
        long inode = entry.getInode();
        long devMin = entry.getDeviceMin();
        if (CpioConstants.CPIO_TRAILER.equals(entry.getName())) {
            devMin = 0;
            inode = 0;
        } else if (inode == 0 && devMin == 0) {
            inode = this.nextArtificalDeviceAndInode & (-1);
            long j = this.nextArtificalDeviceAndInode;
            this.nextArtificalDeviceAndInode = j + 1;
            devMin = (j >> 32) & (-1);
        } else {
            this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, (4294967296L * devMin) + inode) + 1;
        }
        writeAsciiLong(inode, 8, 16);
        writeAsciiLong(entry.getMode(), 8, 16);
        writeAsciiLong(entry.getUID(), 8, 16);
        writeAsciiLong(entry.getGID(), 8, 16);
        writeAsciiLong(entry.getNumberOfLinks(), 8, 16);
        writeAsciiLong(entry.getTime(), 8, 16);
        writeAsciiLong(entry.getSize(), 8, 16);
        writeAsciiLong(entry.getDeviceMaj(), 8, 16);
        writeAsciiLong(devMin, 8, 16);
        writeAsciiLong(entry.getRemoteDeviceMaj(), 8, 16);
        writeAsciiLong(entry.getRemoteDeviceMin(), 8, 16);
        byte[] name = encode(entry.getName());
        writeAsciiLong(name.length + 1, 8, 16);
        writeAsciiLong(entry.getChksum(), 8, 16);
        writeCString(name);
        pad(entry.getHeaderPadCount(name.length));
    }

    private void writeOldAsciiEntry(CpioArchiveEntry entry) throws IOException {
        long inode = entry.getInode();
        long device = entry.getDevice();
        if (CpioConstants.CPIO_TRAILER.equals(entry.getName())) {
            device = 0;
            inode = 0;
        } else if (inode == 0 && device == 0) {
            inode = this.nextArtificalDeviceAndInode & 262143;
            long j = this.nextArtificalDeviceAndInode;
            this.nextArtificalDeviceAndInode = j + 1;
            device = (j >> 18) & 262143;
        } else {
            this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, (262144 * device) + inode) + 1;
        }
        writeAsciiLong(device, 6, 8);
        writeAsciiLong(inode, 6, 8);
        writeAsciiLong(entry.getMode(), 6, 8);
        writeAsciiLong(entry.getUID(), 6, 8);
        writeAsciiLong(entry.getGID(), 6, 8);
        writeAsciiLong(entry.getNumberOfLinks(), 6, 8);
        writeAsciiLong(entry.getRemoteDevice(), 6, 8);
        writeAsciiLong(entry.getTime(), 11, 8);
        byte[] name = encode(entry.getName());
        writeAsciiLong(name.length + 1, 6, 8);
        writeAsciiLong(entry.getSize(), 11, 8);
        writeCString(name);
    }

    private void writeOldBinaryEntry(CpioArchiveEntry entry, boolean swapHalfWord) throws IOException {
        long inode = entry.getInode();
        long device = entry.getDevice();
        if (CpioConstants.CPIO_TRAILER.equals(entry.getName())) {
            device = 0;
            inode = 0;
        } else if (inode == 0 && device == 0) {
            inode = this.nextArtificalDeviceAndInode & 65535;
            long j = this.nextArtificalDeviceAndInode;
            this.nextArtificalDeviceAndInode = j + 1;
            device = (j >> 16) & 65535;
        } else {
            this.nextArtificalDeviceAndInode = Math.max(this.nextArtificalDeviceAndInode, (65536 * device) + inode) + 1;
        }
        writeBinaryLong(device, 2, swapHalfWord);
        writeBinaryLong(inode, 2, swapHalfWord);
        writeBinaryLong(entry.getMode(), 2, swapHalfWord);
        writeBinaryLong(entry.getUID(), 2, swapHalfWord);
        writeBinaryLong(entry.getGID(), 2, swapHalfWord);
        writeBinaryLong(entry.getNumberOfLinks(), 2, swapHalfWord);
        writeBinaryLong(entry.getRemoteDevice(), 2, swapHalfWord);
        writeBinaryLong(entry.getTime(), 4, swapHalfWord);
        byte[] name = encode(entry.getName());
        writeBinaryLong(name.length + 1, 2, swapHalfWord);
        writeBinaryLong(entry.getSize(), 4, swapHalfWord);
        writeCString(name);
        pad(entry.getHeaderPadCount(name.length));
    }
}
