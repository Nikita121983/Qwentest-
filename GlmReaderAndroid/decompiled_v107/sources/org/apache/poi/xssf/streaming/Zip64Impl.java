package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/* loaded from: classes10.dex */
class Zip64Impl {
    private static final int DATA_DESCRIPTOR_USED = 8;
    private static final long MAX32 = 4294967295L;
    private static final long PK0102 = 33639248;
    private static final long PK0304 = 67324752;
    private static final long PK0506 = 101010256;
    private static final long PK0708 = 134695760;
    private static final int VERSION_20 = 20;
    private static final int VERSION_45 = 45;
    private static final int ZIP64_FIELD = 1;
    private final OutputStream out;
    private int written = 0;

    /* loaded from: classes10.dex */
    static class Entry {
        long compressedSize;
        long crc;
        final String filename;
        long offset;
        long size;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Entry(String filename) {
            this.filename = filename;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Zip64Impl(OutputStream out) {
        this.out = out;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeLFH(Entry entry) throws IOException {
        this.written = 0;
        writeInt(PK0304);
        writeShort(45);
        writeShort(8);
        writeShort(8);
        writeInt(0L);
        writeInt(entry.crc);
        writeInt(0L);
        writeInt(0L);
        writeShort(entry.filename.length());
        writeShort(0);
        byte[] filenameBytes = entry.filename.getBytes(StandardCharsets.US_ASCII);
        this.out.write(filenameBytes);
        return this.written + filenameBytes.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeDAT(Entry entry) throws IOException {
        this.written = 0;
        writeInt(PK0708);
        writeInt(entry.crc);
        writeLong(entry.compressedSize);
        writeLong(entry.size);
        return this.written;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeCEN(Entry entry) throws IOException {
        this.written = 0;
        long j = entry.size;
        long j2 = MAX32;
        boolean useZip64 = j > MAX32;
        writeInt(PK0102);
        writeShort(45);
        writeShort(useZip64 ? 45 : 20);
        writeShort(8);
        writeShort(8);
        writeInt(0L);
        writeInt(entry.crc);
        writeInt(entry.compressedSize);
        if (!useZip64) {
            j2 = entry.size;
        }
        writeInt(j2);
        writeShort(entry.filename.length());
        writeShort(useZip64 ? 12 : 0);
        writeShort(0);
        writeShort(0);
        writeShort(0);
        writeInt(0L);
        writeInt(entry.offset);
        byte[] filenameBytes = entry.filename.getBytes(StandardCharsets.US_ASCII);
        this.out.write(filenameBytes);
        if (useZip64) {
            writeShort(1);
            writeShort(8);
            writeLong(entry.size);
        }
        return this.written + filenameBytes.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int writeEND(int entriesCount, long offset, long length) throws IOException {
        this.written = 0;
        writeInt(PK0506);
        writeShort(0);
        writeShort(0);
        writeShort(entriesCount);
        writeShort(entriesCount);
        writeInt(length);
        writeInt(offset);
        writeShort(0);
        return this.written;
    }

    private void writeShort(int v) throws IOException {
        this.out.write(new byte[]{(byte) ((v >>> 0) & 255), (byte) ((v >>> 8) & 255)});
        this.written += 2;
    }

    private void writeInt(long v) throws IOException {
        this.out.write(new byte[]{(byte) ((v >>> 0) & 255), (byte) ((v >>> 8) & 255), (byte) ((v >>> 16) & 255), (byte) (255 & (v >>> 24))});
        this.written += 4;
    }

    private void writeLong(long v) throws IOException {
        this.out.write(new byte[]{(byte) ((v >>> 0) & 255), (byte) ((v >>> 8) & 255), (byte) ((v >>> 16) & 255), (byte) ((v >>> 24) & 255), (byte) ((v >>> 32) & 255), (byte) ((v >>> 40) & 255), (byte) ((v >>> 48) & 255), (byte) (255 & (v >>> 56))});
        this.written += 8;
    }
}
