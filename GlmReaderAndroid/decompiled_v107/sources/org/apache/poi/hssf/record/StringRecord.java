package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class StringRecord extends ContinuableRecord {
    public static final short sid = 519;
    private boolean _is16bitUnicode;
    private String _text;

    public StringRecord() {
    }

    public StringRecord(StringRecord other) {
        this._is16bitUnicode = other._is16bitUnicode;
        this._text = other._text;
    }

    public StringRecord(RecordInputStream in) {
        int field_1_string_length = in.readUShort();
        this._is16bitUnicode = in.readByte() != 0;
        if (this._is16bitUnicode) {
            this._text = in.readUnicodeLEString(field_1_string_length);
        } else {
            this._text = in.readCompressedUnicode(field_1_string_length);
        }
    }

    @Override // org.apache.poi.hssf.record.cont.ContinuableRecord
    protected void serialize(ContinuableRecordOutput out) {
        out.writeShort(this._text.length());
        out.writeStringData(this._text);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 519;
    }

    public String getString() {
        return this._text;
    }

    public void setString(String string) {
        this._text = string;
        this._is16bitUnicode = StringUtil.hasMultibyte(string);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public StringRecord copy() {
        return new StringRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STRING;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("is16bitUnicode", new Supplier() { // from class: org.apache.poi.hssf.record.StringRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return StringRecord.this.m2372xc891b521();
            }
        }, "text", new Supplier() { // from class: org.apache.poi.hssf.record.StringRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return StringRecord.this.getString();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-StringRecord, reason: not valid java name */
    public /* synthetic */ Object m2372xc891b521() {
        return Boolean.valueOf(this._is16bitUnicode);
    }
}
