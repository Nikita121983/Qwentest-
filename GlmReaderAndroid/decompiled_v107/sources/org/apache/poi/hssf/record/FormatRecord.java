package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class FormatRecord extends StandardRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) FormatRecord.class);
    public static final short sid = 1054;
    private final int field_1_index_code;
    private final boolean field_3_hasMultibyte;
    private final String field_4_formatstring;

    private FormatRecord(FormatRecord other) {
        super(other);
        this.field_1_index_code = other.field_1_index_code;
        this.field_3_hasMultibyte = other.field_3_hasMultibyte;
        this.field_4_formatstring = other.field_4_formatstring;
    }

    public FormatRecord(int indexCode, String fs) {
        this.field_1_index_code = indexCode;
        this.field_4_formatstring = fs;
        this.field_3_hasMultibyte = StringUtil.hasMultibyte(fs);
    }

    public FormatRecord(RecordInputStream in) {
        this.field_1_index_code = in.readShort();
        int field_3_unicode_len = in.readUShort();
        this.field_3_hasMultibyte = (in.readByte() & 1) != 0;
        if (this.field_3_hasMultibyte) {
            this.field_4_formatstring = readStringCommon(in, field_3_unicode_len, false);
        } else {
            this.field_4_formatstring = readStringCommon(in, field_3_unicode_len, true);
        }
    }

    public int getIndexCode() {
        return this.field_1_index_code;
    }

    public String getFormatString() {
        return this.field_4_formatstring;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        String formatString = getFormatString();
        littleEndianOutput.writeShort(getIndexCode());
        littleEndianOutput.writeShort(formatString.length());
        littleEndianOutput.writeByte(this.field_3_hasMultibyte ? 1 : 0);
        if (this.field_3_hasMultibyte) {
            StringUtil.putUnicodeLE(formatString, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(formatString, littleEndianOutput);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (getFormatString().length() * (this.field_3_hasMultibyte ? 2 : 1)) + 5;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FormatRecord copy() {
        return new FormatRecord(this);
    }

    private static String readStringCommon(RecordInputStream ris, int requestedLength, boolean pIsCompressedEncoding) {
        char[] buf;
        int readShort;
        if (requestedLength < 0 || requestedLength > 1048576) {
            throw new IllegalArgumentException("Bad requested string length (" + requestedLength + ")");
        }
        int availableChars = ris.remaining();
        if (!pIsCompressedEncoding) {
            availableChars /= 2;
        }
        if (requestedLength == availableChars) {
            buf = new char[requestedLength];
        } else {
            buf = new char[availableChars];
        }
        for (int i = 0; i < buf.length; i++) {
            if (pIsCompressedEncoding) {
                readShort = ris.readUByte();
            } else {
                readShort = ris.readShort();
            }
            char ch = (char) readShort;
            buf[i] = ch;
        }
        int i2 = ris.available();
        if (i2 == 1) {
            char[] tmp = Arrays.copyOf(buf, buf.length + 1);
            tmp[buf.length] = (char) ris.readUByte();
            buf = tmp;
        }
        if (ris.available() > 0) {
            LOG.atInfo().log("FormatRecord has {} unexplained bytes. Silently skipping", Unbox.box(ris.available()));
            while (ris.available() > 0) {
                ris.readByte();
            }
        }
        return new String(buf);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("indexCode", new Supplier() { // from class: org.apache.poi.hssf.record.FormatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FormatRecord.this.getIndexCode());
            }
        }, "unicode", new Supplier() { // from class: org.apache.poi.hssf.record.FormatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormatRecord.this.m2307x36684887();
            }
        }, "formatString", new Supplier() { // from class: org.apache.poi.hssf.record.FormatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormatRecord.this.getFormatString();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FormatRecord, reason: not valid java name */
    public /* synthetic */ Object m2307x36684887() {
        return Boolean.valueOf(this.field_3_hasMultibyte);
    }
}
