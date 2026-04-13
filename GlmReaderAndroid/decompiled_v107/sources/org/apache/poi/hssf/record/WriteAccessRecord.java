package org.apache.poi.hssf.record;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class WriteAccessRecord extends StandardRecord {
    private static final int DATA_SIZE = 112;
    private static final byte PAD_CHAR = 32;
    private static final int STRING_SIZE = 109;
    public static final short sid = 92;
    private String field_1_username;
    private static final BitField UTF16FLAG = BitFieldFactory.getInstance(1);
    private static final byte[] PADDING = new byte[109];

    static {
        Arrays.fill(PADDING, (byte) 32);
    }

    public WriteAccessRecord() {
        setUsername("");
    }

    public WriteAccessRecord(WriteAccessRecord other) {
        super(other);
        this.field_1_username = other.field_1_username;
    }

    public WriteAccessRecord(RecordInputStream in) {
        byte[] data;
        int byteCnt;
        Charset charset;
        if (in.remaining() > 112) {
            throw new RecordFormatException("Expected data size (112) but got (" + in.remaining() + ")");
        }
        int nChars = in.readUShort();
        int is16BitFlag = in.readUByte();
        if (nChars > 109 || (is16BitFlag & 254) != 0) {
            if (in.isEncrypted()) {
                data = IOUtils.safelyAllocate(in.remaining(), 109);
                in.readPlain(data, 0, data.length);
                int i = data.length;
                while (i > 0 && data[i - 1] == 32) {
                    i--;
                }
                byteCnt = i;
                charset = (data.length <= 1 || data[1] != 0) ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_16LE;
            } else {
                int byteCnt2 = in.remaining() + 3;
                data = IOUtils.safelyAllocate(byteCnt2, 112);
                LittleEndian.putUShort(data, 0, nChars);
                LittleEndian.putByte(data, 2, is16BitFlag);
                in.readFully(data, 3, byteCnt2 - 3);
                byteCnt = byteCnt2;
                charset = StandardCharsets.UTF_8;
            }
        } else {
            data = IOUtils.safelyAllocate(in.remaining(), 109);
            in.readFully(data);
            if (UTF16FLAG.isSet(is16BitFlag)) {
                int min = Math.min(nChars * 2, data.length);
                byteCnt = min - (min % 2);
                charset = StandardCharsets.UTF_16LE;
            } else {
                byteCnt = Math.min(nChars, data.length);
                charset = StandardCharsets.ISO_8859_1;
            }
        }
        String rawValue = new String(data, 0, byteCnt, charset);
        setUsername(rawValue.trim());
    }

    public void setUsername(String username) {
        boolean is16bit = StringUtil.hasMultibyte(username);
        int encodedByteCount = username.length() * (is16bit ? 2 : 1);
        if (encodedByteCount > 109) {
            throw new IllegalArgumentException("Name is too long, expecting up to 109 bytes, but had: " + encodedByteCount + " bytes: " + username);
        }
        this.field_1_username = username;
    }

    public String getUsername() {
        return this.field_1_username;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        String username = getUsername();
        boolean hasMultibyte = StringUtil.hasMultibyte(username);
        littleEndianOutput.writeShort(username.length());
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        byte[] bArr = (byte[]) PADDING.clone();
        if (hasMultibyte) {
            StringUtil.putUnicodeLE(username, bArr, 0);
        } else {
            StringUtil.putCompressedUnicode(username, bArr, 0);
        }
        littleEndianOutput.write(bArr);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 112;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 92;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public WriteAccessRecord copy() {
        return new WriteAccessRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WRITE_ACCESS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("username", new Supplier() { // from class: org.apache.poi.hssf.record.WriteAccessRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return WriteAccessRecord.this.getUsername();
            }
        });
    }
}
