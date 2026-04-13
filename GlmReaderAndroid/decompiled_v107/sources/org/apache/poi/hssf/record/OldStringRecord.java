package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class OldStringRecord implements GenericRecord {
    public static final short biff2_sid = 7;
    public static final short biff345_sid = 519;
    private CodepageRecord codepage;
    private short field_1_string_len;
    private byte[] field_2_bytes;
    private short sid;

    public OldStringRecord(RecordInputStream in) {
        this.sid = in.getSid();
        if (in.getSid() == 7) {
            this.field_1_string_len = (short) in.readUByte();
        } else {
            this.field_1_string_len = in.readShort();
        }
        this.field_2_bytes = IOUtils.safelyAllocate(this.field_1_string_len, HSSFWorkbook.getMaxRecordLength());
        in.read(this.field_2_bytes, 0, this.field_1_string_len);
    }

    public boolean isBiff2() {
        return this.sid == 7;
    }

    public short getSid() {
        return this.sid;
    }

    public void setCodePage(CodepageRecord codepage) {
        this.codepage = codepage;
    }

    public String getString() {
        return getString(this.field_2_bytes, this.codepage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getString(byte[] data, CodepageRecord codepage) {
        int cp = 1252;
        if (codepage != null) {
            cp = codepage.getCodepage() & 65535;
        }
        try {
            return CodePageUtil.getStringFromCodePage(data, cp);
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("Unsupported codepage requested: " + cp, uee);
        }
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STRING;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties(TypedValues.Custom.S_STRING, new Supplier() { // from class: org.apache.poi.hssf.record.OldStringRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldStringRecord.this.getString();
            }
        });
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
