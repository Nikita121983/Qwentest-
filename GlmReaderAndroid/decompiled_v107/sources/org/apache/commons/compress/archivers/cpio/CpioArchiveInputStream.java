package org.apache.commons.compress.archivers.cpio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.ParsingUtils;

/* loaded from: classes9.dex */
public class CpioArchiveInputStream extends ArchiveInputStream<CpioArchiveEntry> implements CpioConstants {
    private final int blockSize;
    private final byte[] buffer2;
    private final byte[] buffer4;
    private final byte[] buffer6;
    private boolean closed;
    private long crc;
    private CpioArchiveEntry entry;
    private long entryBytesRead;
    private boolean entryEOF;
    private final byte[] tmpBuf;
    private final ZipEncoding zipEncoding;

    public static boolean matches(byte[] signature, int length) {
        if (length < 6) {
            return false;
        }
        if ((signature[0] == 113 && (signature[1] & UByte.MAX_VALUE) == 199) || (signature[1] == 113 && (signature[0] & UByte.MAX_VALUE) == 199)) {
            return true;
        }
        if (signature[0] == 48 && signature[1] == 55 && signature[2] == 48 && signature[3] == 55 && signature[4] == 48) {
            return signature[5] == 49 || signature[5] == 50 || signature[5] == 55;
        }
        return false;
    }

    public CpioArchiveInputStream(InputStream in) {
        this(in, 512, CpioUtil.DEFAULT_CHARSET_NAME);
    }

    public CpioArchiveInputStream(InputStream in, int blockSize) {
        this(in, blockSize, CpioUtil.DEFAULT_CHARSET_NAME);
    }

    public CpioArchiveInputStream(InputStream in, int blockSize, String encoding) {
        super(in, encoding);
        this.tmpBuf = new byte[4096];
        this.buffer2 = new byte[2];
        this.buffer4 = new byte[4];
        this.buffer6 = new byte[6];
        this.in = in;
        if (blockSize <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        this.blockSize = blockSize;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
    }

    public CpioArchiveInputStream(InputStream in, String encoding) {
        this(in, 512, encoding);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        ensureOpen();
        if (this.entryEOF) {
            return 0;
        }
        return 1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.in.close();
            this.closed = true;
        }
    }

    private void closeEntry() throws IOException {
        do {
        } while (skip(2147483647L) == 2147483647L);
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Deprecated
    public CpioArchiveEntry getNextCPIOEntry() throws IOException {
        char c;
        ensureOpen();
        if (this.entry != null) {
            closeEntry();
        }
        readFully(this.buffer2, 0, this.buffer2.length);
        if (CpioUtil.byteArray2long(this.buffer2, false) == 29127) {
            this.entry = readOldBinaryEntry(false);
        } else if (CpioUtil.byteArray2long(this.buffer2, true) == 29127) {
            this.entry = readOldBinaryEntry(true);
        } else {
            System.arraycopy(this.buffer2, 0, this.buffer6, 0, this.buffer2.length);
            readFully(this.buffer6, this.buffer2.length, this.buffer4.length);
            String magicString = ArchiveUtils.toAsciiString(this.buffer6);
            switch (magicString.hashCode()) {
                case 1426477263:
                    if (magicString.equals(CpioConstants.MAGIC_NEW)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1426477264:
                    if (magicString.equals(CpioConstants.MAGIC_NEW_CRC)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1426477269:
                    if (magicString.equals(CpioConstants.MAGIC_OLD_ASCII)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    this.entry = readNewEntry(false);
                    break;
                case 1:
                    this.entry = readNewEntry(true);
                    break;
                case 2:
                    this.entry = readOldAsciiEntry();
                    break;
                default:
                    throw new IOException("Unknown magic [" + magicString + "]. Occurred at byte: " + getBytesRead());
            }
        }
        this.entryBytesRead = 0L;
        this.entryEOF = false;
        this.crc = 0L;
        if (this.entry.getName().equals(CpioConstants.CPIO_TRAILER)) {
            this.entryEOF = true;
            skipRemainderOfLastBlock();
            return null;
        }
        return this.entry;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public CpioArchiveEntry getNextEntry() throws IOException {
        return getNextCPIOEntry();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.entry == null || this.entryEOF) {
            return -1;
        }
        if (this.entryBytesRead == this.entry.getSize()) {
            int dataPadCount = this.entry.getDataPadCount();
            if (skip(dataPadCount) != dataPadCount) {
                throw new IOException("Data pad count missmatch.");
            }
            this.entryEOF = true;
            if (this.entry.getFormat() != 2 || this.crc == this.entry.getChksum()) {
                return -1;
            }
            throw new IOException("CRC Error. Occurred at byte: " + getBytesRead());
        }
        int tmplength = (int) Math.min(len, this.entry.getSize() - this.entryBytesRead);
        if (tmplength < 0) {
            return -1;
        }
        int tmpread = readFully(b, off, tmplength);
        if (this.entry.getFormat() == 2) {
            for (int pos = 0; pos < tmpread; pos++) {
                this.crc += b[pos] & UByte.MAX_VALUE;
                this.crc &= 4294967295L;
            }
        }
        if (tmpread > 0) {
            this.entryBytesRead += tmpread;
        }
        return tmpread;
    }

    private long readAsciiLong(int length, int radix) throws IOException {
        byte[] tmpBuffer = readRange(length);
        return ParsingUtils.parseLongValue(ArchiveUtils.toAsciiString(tmpBuffer), radix);
    }

    private long readBinaryLong(int length, boolean swapHalfWord) throws IOException {
        byte[] tmp = readRange(length);
        return CpioUtil.byteArray2long(tmp, swapHalfWord);
    }

    private String readCString(int length) throws IOException {
        byte[] tmpBuffer = readRange(length - 1);
        if (this.in.read() == -1) {
            throw new EOFException();
        }
        return this.zipEncoding.decode(tmpBuffer);
    }

    private int readFully(byte[] b, int off, int len) throws IOException {
        int count = IOUtils.readFully(this.in, b, off, len);
        count(count);
        if (count < len) {
            throw new EOFException();
        }
        return count;
    }

    private CpioArchiveEntry readNewEntry(boolean hasCrc) throws IOException {
        CpioArchiveEntry newEntry;
        if (hasCrc) {
            newEntry = new CpioArchiveEntry((short) 2);
        } else {
            newEntry = new CpioArchiveEntry((short) 1);
        }
        newEntry.setInode(readAsciiLong(8, 16));
        long mode = readAsciiLong(8, 16);
        if (CpioUtil.fileType(mode) != 0) {
            newEntry.setMode(mode);
        }
        newEntry.setUID(readAsciiLong(8, 16));
        newEntry.setGID(readAsciiLong(8, 16));
        newEntry.setNumberOfLinks(readAsciiLong(8, 16));
        newEntry.setTime(readAsciiLong(8, 16));
        newEntry.setSize(readAsciiLong(8, 16));
        if (newEntry.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        newEntry.setDeviceMaj(readAsciiLong(8, 16));
        newEntry.setDeviceMin(readAsciiLong(8, 16));
        newEntry.setRemoteDeviceMaj(readAsciiLong(8, 16));
        newEntry.setRemoteDeviceMin(readAsciiLong(8, 16));
        long namesize = readAsciiLong(8, 16);
        if (namesize < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        newEntry.setChksum(readAsciiLong(8, 16));
        String name = readCString((int) namesize);
        newEntry.setName(name);
        if (CpioUtil.fileType(mode) == 0 && !name.equals(CpioConstants.CPIO_TRAILER)) {
            throw new IOException("Mode 0 only allowed in the trailer. Found entry name: " + ArchiveUtils.sanitize(name) + " Occurred at byte: " + getBytesRead());
        }
        int headerPadCount = newEntry.getHeaderPadCount(namesize - 1);
        if (skip(headerPadCount) != headerPadCount) {
            throw new IOException("Header pad count mismatch.");
        }
        return newEntry;
    }

    private CpioArchiveEntry readOldAsciiEntry() throws IOException {
        CpioArchiveEntry ret = new CpioArchiveEntry((short) 4);
        ret.setDevice(readAsciiLong(6, 8));
        ret.setInode(readAsciiLong(6, 8));
        long mode = readAsciiLong(6, 8);
        if (CpioUtil.fileType(mode) != 0) {
            ret.setMode(mode);
        }
        ret.setUID(readAsciiLong(6, 8));
        ret.setGID(readAsciiLong(6, 8));
        ret.setNumberOfLinks(readAsciiLong(6, 8));
        ret.setRemoteDevice(readAsciiLong(6, 8));
        ret.setTime(readAsciiLong(11, 8));
        long namesize = readAsciiLong(6, 8);
        if (namesize < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        ret.setSize(readAsciiLong(11, 8));
        if (ret.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        String name = readCString((int) namesize);
        ret.setName(name);
        if (CpioUtil.fileType(mode) == 0 && !name.equals(CpioConstants.CPIO_TRAILER)) {
            throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.sanitize(name) + " Occurred at byte: " + getBytesRead());
        }
        return ret;
    }

    private CpioArchiveEntry readOldBinaryEntry(boolean swapHalfWord) throws IOException {
        CpioArchiveEntry oldEntry = new CpioArchiveEntry((short) 8);
        oldEntry.setDevice(readBinaryLong(2, swapHalfWord));
        oldEntry.setInode(readBinaryLong(2, swapHalfWord));
        long mode = readBinaryLong(2, swapHalfWord);
        if (CpioUtil.fileType(mode) != 0) {
            oldEntry.setMode(mode);
        }
        oldEntry.setUID(readBinaryLong(2, swapHalfWord));
        oldEntry.setGID(readBinaryLong(2, swapHalfWord));
        oldEntry.setNumberOfLinks(readBinaryLong(2, swapHalfWord));
        oldEntry.setRemoteDevice(readBinaryLong(2, swapHalfWord));
        oldEntry.setTime(readBinaryLong(4, swapHalfWord));
        long namesize = readBinaryLong(2, swapHalfWord);
        if (namesize < 0) {
            throw new IOException("Found illegal entry with negative name length");
        }
        oldEntry.setSize(readBinaryLong(4, swapHalfWord));
        if (oldEntry.getSize() < 0) {
            throw new IOException("Found illegal entry with negative length");
        }
        String name = readCString((int) namesize);
        oldEntry.setName(name);
        if (CpioUtil.fileType(mode) == 0 && !name.equals(CpioConstants.CPIO_TRAILER)) {
            throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.sanitize(name) + "Occurred at byte: " + getBytesRead());
        }
        int headerPadCount = oldEntry.getHeaderPadCount(namesize - 1);
        if (skip(headerPadCount) != headerPadCount) {
            throw new IOException("Header pad count mismatch.");
        }
        return oldEntry;
    }

    private byte[] readRange(int len) throws IOException {
        byte[] b = IOUtils.readRange(this.in, len);
        count(b.length);
        if (b.length < len) {
            throw new EOFException();
        }
        return b;
    }

    private int skip(int length) throws IOException {
        if (length > 0) {
            return readFully(this.buffer4, 0, length);
        }
        return 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        if (n < 0) {
            throw new IllegalArgumentException("Negative skip length");
        }
        ensureOpen();
        int max = (int) Math.min(n, 2147483647L);
        int total = 0;
        while (true) {
            if (total >= max) {
                break;
            }
            int len = max - total;
            if (len > this.tmpBuf.length) {
                len = this.tmpBuf.length;
            }
            int len2 = read(this.tmpBuf, 0, len);
            if (len2 == -1) {
                this.entryEOF = true;
                break;
            }
            total += len2;
        }
        return total;
    }

    private void skipRemainderOfLastBlock() throws IOException {
        long readFromLastBlock = getBytesRead() % this.blockSize;
        long remainingBytes = readFromLastBlock == 0 ? 0L : this.blockSize - readFromLastBlock;
        while (remainingBytes > 0) {
            long skipped = skip(this.blockSize - readFromLastBlock);
            if (skipped > 0) {
                remainingBytes -= skipped;
            } else {
                return;
            }
        }
    }
}
