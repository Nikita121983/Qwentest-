package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class SeriesTextRecord extends StandardRecord {
    private static final int MAX_LEN = 255;
    public static final short sid = 4109;
    private int field_1_id;
    private String field_4_text;
    private boolean is16bit;

    public SeriesTextRecord() {
        this.field_4_text = "";
        this.is16bit = false;
    }

    public SeriesTextRecord(SeriesTextRecord other) {
        super(other);
        this.field_1_id = other.field_1_id;
        this.is16bit = other.is16bit;
        this.field_4_text = other.field_4_text;
    }

    public SeriesTextRecord(RecordInputStream in) {
        this.field_1_id = in.readUShort();
        int field_2_textLength = in.readUByte();
        this.is16bit = (in.readUByte() & 1) != 0;
        if (this.is16bit) {
            this.field_4_text = in.readUnicodeLEString(field_2_textLength);
        } else {
            this.field_4_text = in.readCompressedUnicode(field_2_textLength);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_id);
        out.writeByte(this.field_4_text.length());
        if (this.is16bit) {
            out.writeByte(1);
            StringUtil.putUnicodeLE(this.field_4_text, out);
        } else {
            out.writeByte(0);
            StringUtil.putCompressedUnicode(this.field_4_text, out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.field_4_text.length() * (this.is16bit ? 2 : 1)) + 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SeriesTextRecord copy() {
        return new SeriesTextRecord(this);
    }

    public int getId() {
        return this.field_1_id;
    }

    public void setId(int id) {
        this.field_1_id = id;
    }

    public String getText() {
        return this.field_4_text;
    }

    public void setText(String text) {
        if (text.length() > 255) {
            throw new IllegalArgumentException("Text is too long (" + text.length() + ">255)");
        }
        this.field_4_text = text;
        this.is16bit = StringUtil.hasMultibyte(text);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_TEXT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("id", new Supplier() { // from class: org.apache.poi.hssf.record.chart.SeriesTextRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SeriesTextRecord.this.getId());
            }
        }, "bit16", new Supplier() { // from class: org.apache.poi.hssf.record.chart.SeriesTextRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return SeriesTextRecord.this.m2443xa90f9a63();
            }
        }, "text", new Supplier() { // from class: org.apache.poi.hssf.record.chart.SeriesTextRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return SeriesTextRecord.this.getText();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-SeriesTextRecord, reason: not valid java name */
    public /* synthetic */ Object m2443xa90f9a63() {
        return Boolean.valueOf(this.is16bit);
    }
}
