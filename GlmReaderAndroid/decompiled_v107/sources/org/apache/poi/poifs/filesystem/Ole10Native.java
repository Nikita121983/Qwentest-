package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class Ole10Native {
    private static final int DEFAULT_MAX_STRING_LENGTH = 1024;
    public static final String OLE10_NATIVE = "\u0001Ole10Native";
    private static final String OLE_MARKER_NAME = "\u0001Ole";
    private String command;
    private String command2;
    private byte[] dataBuffer;
    private String fileName;
    private String fileName2;
    private short flags1;
    private short flags2;
    private String label;
    private String label2;
    private EncodingMode mode;
    private int totalSize;
    private short unknown1;
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    private static int MAX_STRING_LENGTH = 1024;
    private static final byte[] OLE_MARKER_BYTES = {1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum EncodingMode {
        parsed,
        unparsed,
        compact
    }

    public static Ole10Native createFromEmbeddedOleObject(POIFSFileSystem poifs) throws IOException, Ole10NativeException {
        return createFromEmbeddedOleObject(poifs.getRoot());
    }

    public static Ole10Native createFromEmbeddedOleObject(DirectoryNode directory) throws IOException, Ole10NativeException {
        DocumentEntry nativeEntry = (DocumentEntry) directory.getEntryCaseInsensitive(OLE10_NATIVE);
        DocumentInputStream dis = directory.createDocumentInputStream(nativeEntry);
        try {
            byte[] data = IOUtils.toByteArray((InputStream) dis, nativeEntry.getSize(), MAX_RECORD_LENGTH);
            Ole10Native ole10Native = new Ole10Native(data, 0);
            if (dis != null) {
                dis.close();
            }
            return ole10Native;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public static void setMaxStringLength(int length) {
        MAX_STRING_LENGTH = length;
    }

    public static int getMaxStringLength() {
        return MAX_STRING_LENGTH;
    }

    public Ole10Native(String label, String filename, String command, byte[] data) {
        this.flags1 = (short) 2;
        this.unknown1 = (short) 3;
        setLabel(label);
        setFileName(filename);
        setCommand(command);
        this.command2 = command;
        setDataBuffer(data);
        this.mode = EncodingMode.parsed;
    }

    public Ole10Native(byte[] data, int offset) throws Ole10NativeException {
        this.flags1 = (short) 2;
        this.unknown1 = (short) 3;
        LittleEndianByteArrayInputStream leis = new LittleEndianByteArrayInputStream(data, offset);
        this.totalSize = leis.readInt();
        leis.limit(this.totalSize + 4);
        leis.mark(0);
        try {
            this.flags1 = leis.readShort();
            if (this.flags1 == 2) {
                leis.mark(0);
                boolean validFileName = Character.isISOControl(leis.readByte()) ? false : true;
                leis.reset();
                if (validFileName) {
                    readParsed(leis);
                } else {
                    readCompact(leis);
                }
                return;
            }
            leis.reset();
            readUnparsed(leis);
        } catch (IOException e) {
            throw new Ole10NativeException("Invalid Ole10Native", e);
        }
    }

    private void readParsed(LittleEndianByteArrayInputStream leis) throws Ole10NativeException, IOException {
        this.mode = EncodingMode.parsed;
        this.label = readAsciiZ(leis);
        this.fileName = readAsciiZ(leis);
        this.flags2 = leis.readShort();
        this.unknown1 = leis.readShort();
        this.command = readAsciiLen(leis);
        this.dataBuffer = IOUtils.toByteArray((InputStream) leis, leis.readInt(), MAX_RECORD_LENGTH);
        leis.mark(0);
        short lowSize = leis.readShort();
        if (lowSize != 0) {
            leis.reset();
            this.command2 = readUtf16(leis);
            this.label2 = readUtf16(leis);
            this.fileName2 = readUtf16(leis);
        }
    }

    private void readCompact(LittleEndianByteArrayInputStream leis) throws IOException {
        this.mode = EncodingMode.compact;
        this.dataBuffer = IOUtils.toByteArray((InputStream) leis, this.totalSize - 2, MAX_RECORD_LENGTH);
    }

    private void readUnparsed(LittleEndianByteArrayInputStream leis) throws IOException {
        this.mode = EncodingMode.unparsed;
        this.dataBuffer = IOUtils.toByteArray((InputStream) leis, this.totalSize, MAX_RECORD_LENGTH);
    }

    public static void createOleMarkerEntry(DirectoryEntry parent) throws IOException {
        if (!parent.hasEntryCaseInsensitive(OLE_MARKER_NAME)) {
            parent.createDocument(OLE_MARKER_NAME, UnsynchronizedByteArrayInputStream.builder().setByteArray(OLE_MARKER_BYTES).get());
        }
    }

    public static void createOleMarkerEntry(POIFSFileSystem poifs) throws IOException {
        createOleMarkerEntry(poifs.getRoot());
    }

    private static String readAsciiZ(LittleEndianInput is) throws Ole10NativeException {
        byte[] buf = new byte[MAX_STRING_LENGTH];
        for (int i = 0; i < buf.length; i++) {
            byte readByte = is.readByte();
            buf[i] = readByte;
            if (readByte == 0) {
                return StringUtil.getFromCompressedUTF8(buf, 0, i);
            }
        }
        throw new Ole10NativeException("AsciiZ string was not null terminated after " + MAX_STRING_LENGTH + " bytes - Exiting.");
    }

    private static String readAsciiLen(LittleEndianByteArrayInputStream leis) throws IOException {
        int size = leis.readInt();
        byte[] buf = IOUtils.toByteArray((InputStream) leis, size, MAX_STRING_LENGTH);
        return buf.length == 0 ? "" : StringUtil.getFromCompressedUnicode(buf, 0, size - 1);
    }

    private static String readUtf16(LittleEndianByteArrayInputStream leis) throws IOException {
        int size = leis.readInt();
        byte[] buf = IOUtils.toByteArray((InputStream) leis, size * 2, MAX_STRING_LENGTH);
        return StringUtil.getFromUnicodeLE(buf, 0, size);
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public short getFlags1() {
        return this.flags1;
    }

    public String getLabel() {
        return this.label;
    }

    public String getFileName() {
        return this.fileName;
    }

    public short getFlags2() {
        return this.flags2;
    }

    public short getUnknown1() {
        return this.unknown1;
    }

    public String getCommand() {
        return this.command;
    }

    public int getDataSize() {
        return this.dataBuffer.length;
    }

    public byte[] getDataBuffer() {
        return this.dataBuffer;
    }

    public void writeOut(OutputStream out) throws IOException {
        LittleEndianOutputStream leosOut = new LittleEndianOutputStream(out);
        switch (this.mode) {
            case parsed:
                UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
                LittleEndianOutputStream leos = new LittleEndianOutputStream(bos);
                try {
                    leos.writeShort(getFlags1());
                    leos.write(getLabel().getBytes(UTF8));
                    leos.write(0);
                    leos.write(getFileName().getBytes(UTF8));
                    leos.write(0);
                    leos.writeShort(getFlags2());
                    leos.writeShort(getUnknown1());
                    leos.writeInt(getCommand().length() + 1);
                    leos.write(getCommand().getBytes(UTF8));
                    leos.write(0);
                    leos.writeInt(getDataSize());
                    leos.write(getDataBuffer());
                    if (this.command2 != null && this.label2 != null && this.fileName2 != null) {
                        leos.writeUInt(this.command2.length());
                        leos.write(StringUtil.getToUnicodeLE(this.command2));
                        leos.writeUInt(this.label2.length());
                        leos.write(StringUtil.getToUnicodeLE(this.label2));
                        leos.writeUInt(this.fileName2.length());
                        leos.write(StringUtil.getToUnicodeLE(this.fileName2));
                        leos.close();
                        leosOut.writeInt(bos.size());
                        bos.writeTo(out);
                        return;
                    }
                    leos.writeShort(0);
                    leos.close();
                    leosOut.writeInt(bos.size());
                    bos.writeTo(out);
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            leos.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            case compact:
                leosOut.writeInt(getDataSize() + 2);
                leosOut.writeShort(getFlags1());
                out.write(getDataBuffer());
                return;
            default:
                leosOut.writeInt(getDataSize());
                out.write(getDataBuffer());
                return;
        }
    }

    public void setFlags1(short flags1) {
        this.flags1 = flags1;
    }

    public void setFlags2(short flags2) {
        this.flags2 = flags2;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setUnknown1(short unknown1) {
        this.unknown1 = unknown1;
    }

    public void setDataBuffer(byte[] dataBuffer) {
        this.dataBuffer = (byte[]) dataBuffer.clone();
    }

    public String getCommand2() {
        return this.command2;
    }

    public void setCommand2(String command2) {
        this.command2 = command2;
    }

    public String getLabel2() {
        return this.label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getFileName2() {
        return this.fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }
}
