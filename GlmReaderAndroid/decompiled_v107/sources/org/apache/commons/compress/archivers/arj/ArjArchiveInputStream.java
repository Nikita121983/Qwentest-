package org.apache.commons.compress.archivers.arj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.CRC32;
import kotlin.UByte;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.ChecksumInputStream;

/* loaded from: classes9.dex */
public class ArjArchiveInputStream extends ArchiveInputStream<ArjArchiveEntry> {
    private static final int ARJ_MAGIC_1 = 96;
    private static final int ARJ_MAGIC_2 = 234;
    private static final String ENCODING_NAME = "CP437";
    private InputStream currentInputStream;
    private LocalFileHeader currentLocalFileHeader;
    private final DataInputStream dis;
    private final MainHeader mainHeader;

    public static boolean matches(byte[] signature, int length) {
        return length >= 2 && (signature[0] & UByte.MAX_VALUE) == 96 && (signature[1] & UByte.MAX_VALUE) == ARJ_MAGIC_2;
    }

    public ArjArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, ENCODING_NAME);
    }

    public ArjArchiveInputStream(InputStream inputStream, String charsetName) throws ArchiveException {
        super(inputStream, charsetName);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.dis = dataInputStream;
        this.in = dataInputStream;
        try {
            this.mainHeader = readMainHeader();
            if ((this.mainHeader.arjFlags & 1) != 0) {
                throw new ArchiveException("Encrypted ARJ files are unsupported");
            }
            if ((this.mainHeader.arjFlags & 4) != 0) {
                throw new ArchiveException("Multi-volume ARJ files are unsupported");
            }
        } catch (IOException e) {
            throw new ArchiveException(e.getMessage(), (Throwable) e);
        }
    }

    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public boolean canReadEntryData(ArchiveEntry ae) {
        return (ae instanceof ArjArchiveEntry) && ((ArjArchiveEntry) ae).getMethod() == 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.dis.close();
    }

    public String getArchiveComment() {
        return this.mainHeader.comment;
    }

    public String getArchiveName() {
        return this.mainHeader.name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.compress.archivers.ArchiveInputStream
    public ArjArchiveEntry getNextEntry() throws IOException {
        if (this.currentInputStream != null) {
            InputStream input = this.currentInputStream;
            IOUtils.skip(input, Long.MAX_VALUE);
            this.currentInputStream.close();
            this.currentLocalFileHeader = null;
            this.currentInputStream = null;
        }
        this.currentLocalFileHeader = readLocalFileHeader();
        if (this.currentLocalFileHeader != null) {
            this.currentInputStream = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.dis)).setMaxCount(this.currentLocalFileHeader.compressedSize)).setPropagateClose(false)).get();
            if (this.currentLocalFileHeader.method == 0) {
                this.currentInputStream = ((ChecksumInputStream.Builder) ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(this.currentInputStream)).setCountThreshold(this.currentLocalFileHeader.originalSize).setExpectedChecksumValue(this.currentLocalFileHeader.originalCrc32).get();
            }
            return new ArjArchiveEntry(this.currentLocalFileHeader);
        }
        this.currentInputStream = null;
        return null;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        if (this.currentLocalFileHeader == null) {
            throw new IllegalStateException("No current arj entry");
        }
        if (this.currentLocalFileHeader.method != 0) {
            throw new IOException("Unsupported compression method " + this.currentLocalFileHeader.method);
        }
        return this.currentInputStream.read(b, off, len);
    }

    private int read16(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedShort();
        count(2);
        return Integer.reverseBytes(value) >>> 16;
    }

    private int read32(DataInputStream dataIn) throws IOException {
        int value = dataIn.readInt();
        count(4);
        return Integer.reverseBytes(value);
    }

    private int read8(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedByte();
        count(1);
        return value;
    }

    private void readExtraData(int firstHeaderSize, DataInputStream firstHeader, LocalFileHeader localFileHeader) throws IOException {
        if (firstHeaderSize >= 33) {
            localFileHeader.extendedFilePosition = read32(firstHeader);
            if (firstHeaderSize >= 45) {
                localFileHeader.dateTimeAccessed = read32(firstHeader);
                localFileHeader.dateTimeCreated = read32(firstHeader);
                localFileHeader.originalSizeEvenForVolumes = read32(firstHeader);
                pushedBackBytes(12L);
            }
            pushedBackBytes(4L);
        }
    }

    private byte[] readHeader() throws IOException {
        boolean found = false;
        byte[] basicHeaderBytes = null;
        do {
            int second = read8(this.dis);
            do {
                int first = second;
                second = read8(this.dis);
                if (first == 96) {
                    break;
                }
            } while (second != ARJ_MAGIC_2);
            int basicHeaderSize = read16(this.dis);
            if (basicHeaderSize == 0) {
                return null;
            }
            if (basicHeaderSize <= 2600) {
                basicHeaderBytes = readRange(this.dis, basicHeaderSize);
                long basicHeaderCrc32 = read32(this.dis) & 4294967295L;
                CRC32 crc32 = new CRC32();
                crc32.update(basicHeaderBytes);
                if (basicHeaderCrc32 == crc32.getValue()) {
                    found = true;
                }
            }
        } while (!found);
        return basicHeaderBytes;
    }

    private LocalFileHeader readLocalFileHeader() throws IOException {
        byte[] basicHeaderBytes = readHeader();
        if (basicHeaderBytes == null) {
            return null;
        }
        DataInputStream basicHeader = new DataInputStream(new ByteArrayInputStream(basicHeaderBytes));
        try {
            int firstHeaderSize = basicHeader.readUnsignedByte();
            byte[] firstHeaderBytes = readRange(basicHeader, firstHeaderSize - 1);
            pushedBackBytes(firstHeaderBytes.length);
            DataInputStream firstHeader = new DataInputStream(new ByteArrayInputStream(firstHeaderBytes));
            try {
                LocalFileHeader localFileHeader = new LocalFileHeader();
                localFileHeader.archiverVersionNumber = firstHeader.readUnsignedByte();
                localFileHeader.minVersionToExtract = firstHeader.readUnsignedByte();
                localFileHeader.hostOS = firstHeader.readUnsignedByte();
                localFileHeader.arjFlags = firstHeader.readUnsignedByte();
                localFileHeader.method = firstHeader.readUnsignedByte();
                localFileHeader.fileType = firstHeader.readUnsignedByte();
                localFileHeader.reserved = firstHeader.readUnsignedByte();
                localFileHeader.dateTimeModified = read32(firstHeader);
                localFileHeader.compressedSize = read32(firstHeader) & 4294967295L;
                localFileHeader.originalSize = read32(firstHeader) & 4294967295L;
                localFileHeader.originalCrc32 = read32(firstHeader) & 4294967295L;
                localFileHeader.fileSpecPosition = read16(firstHeader);
                localFileHeader.fileAccessMode = read16(firstHeader);
                pushedBackBytes(20L);
                localFileHeader.firstChapter = firstHeader.readUnsignedByte();
                localFileHeader.lastChapter = firstHeader.readUnsignedByte();
                readExtraData(firstHeaderSize, firstHeader, localFileHeader);
                localFileHeader.name = readString(basicHeader);
                localFileHeader.comment = readString(basicHeader);
                ArrayList<byte[]> extendedHeaders = new ArrayList<>();
                while (true) {
                    int extendedHeaderSize = read16(this.dis);
                    if (extendedHeaderSize > 0) {
                        byte[] extendedHeaderBytes = readRange(this.dis, extendedHeaderSize);
                        long extendedHeaderCrc32 = read32(this.dis) & 4294967295L;
                        CRC32 crc32 = new CRC32();
                        crc32.update(extendedHeaderBytes);
                        if (extendedHeaderCrc32 != crc32.getValue()) {
                            throw new IOException("Extended header CRC32 verification failure");
                        }
                        extendedHeaders.add(extendedHeaderBytes);
                    } else {
                        localFileHeader.extendedHeaders = (byte[][]) extendedHeaders.toArray(new byte[0]);
                        firstHeader.close();
                        basicHeader.close();
                        return localFileHeader;
                    }
                }
            } finally {
            }
        } finally {
        }
    }

    private MainHeader readMainHeader() throws IOException {
        byte[] basicHeaderBytes = readHeader();
        if (basicHeaderBytes == null) {
            throw new IOException("Archive ends without any headers");
        }
        DataInputStream basicHeader = new DataInputStream(new ByteArrayInputStream(basicHeaderBytes));
        int firstHeaderSize = basicHeader.readUnsignedByte();
        byte[] firstHeaderBytes = readRange(basicHeader, firstHeaderSize - 1);
        pushedBackBytes(firstHeaderBytes.length);
        DataInputStream firstHeader = new DataInputStream(new ByteArrayInputStream(firstHeaderBytes));
        MainHeader header = new MainHeader();
        header.archiverVersionNumber = firstHeader.readUnsignedByte();
        header.minVersionToExtract = firstHeader.readUnsignedByte();
        header.hostOS = firstHeader.readUnsignedByte();
        header.arjFlags = firstHeader.readUnsignedByte();
        header.securityVersion = firstHeader.readUnsignedByte();
        header.fileType = firstHeader.readUnsignedByte();
        header.reserved = firstHeader.readUnsignedByte();
        header.dateTimeCreated = read32(firstHeader);
        header.dateTimeModified = read32(firstHeader);
        header.archiveSize = read32(firstHeader) & 4294967295L;
        header.securityEnvelopeFilePosition = read32(firstHeader);
        header.fileSpecPosition = read16(firstHeader);
        header.securityEnvelopeLength = read16(firstHeader);
        pushedBackBytes(20L);
        header.encryptionVersion = firstHeader.readUnsignedByte();
        header.lastChapter = firstHeader.readUnsignedByte();
        if (firstHeaderSize >= 33) {
            header.arjProtectionFactor = firstHeader.readUnsignedByte();
            header.arjFlags2 = firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
        }
        header.name = readString(basicHeader);
        header.comment = readString(basicHeader);
        int extendedHeaderSize = read16(this.dis);
        if (extendedHeaderSize > 0) {
            header.extendedHeaderBytes = readRange(this.dis, extendedHeaderSize);
            long extendedHeaderCrc32 = read32(this.dis) & 4294967295L;
            CRC32 crc32 = new CRC32();
            crc32.update(header.extendedHeaderBytes);
            if (extendedHeaderCrc32 != crc32.getValue()) {
                throw new IOException("Extended header CRC32 verification failure");
            }
        }
        return header;
    }

    private byte[] readRange(InputStream in, int len) throws IOException {
        byte[] b = org.apache.commons.compress.utils.IOUtils.readRange(in, len);
        count(b.length);
        if (b.length < len) {
            throw new EOFException();
        }
        return b;
    }

    private String readString(DataInputStream dataIn) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        while (true) {
            try {
                int nextByte = dataIn.readUnsignedByte();
                if (nextByte != 0) {
                    buffer.write(nextByte);
                } else {
                    String byteArrayOutputStream = buffer.toString(getCharset().name());
                    buffer.close();
                    return byteArrayOutputStream;
                }
            } catch (Throwable th) {
                try {
                    buffer.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }
}
