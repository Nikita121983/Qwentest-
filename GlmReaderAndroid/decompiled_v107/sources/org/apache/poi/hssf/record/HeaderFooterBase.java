package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public abstract class HeaderFooterBase extends StandardRecord {
    private boolean field_2_hasMultibyte;
    private String field_3_text;

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract HeaderFooterBase copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public HeaderFooterBase(String text) {
        setText(text);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HeaderFooterBase(HeaderFooterBase other) {
        super(other);
        this.field_2_hasMultibyte = other.field_2_hasMultibyte;
        this.field_3_text = other.field_3_text;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HeaderFooterBase(RecordInputStream in) {
        if (in.remaining() > 0) {
            int field_1_footer_len = in.readShort();
            if (field_1_footer_len == 0) {
                this.field_3_text = "";
                if (in.remaining() == 0) {
                    return;
                }
            }
            this.field_2_hasMultibyte = in.readByte() != 0;
            if (this.field_2_hasMultibyte) {
                this.field_3_text = in.readUnicodeLEString(field_1_footer_len);
                return;
            } else {
                this.field_3_text = in.readCompressedUnicode(field_1_footer_len);
                return;
            }
        }
        this.field_3_text = "";
    }

    public final void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        this.field_2_hasMultibyte = StringUtil.hasMultibyte(text);
        this.field_3_text = text;
        if (getDataSize() > 8224) {
            throw new IllegalArgumentException("Header/Footer string too long (limit is 8224 bytes)");
        }
    }

    private int getTextLength() {
        return this.field_3_text.length();
    }

    public final String getText() {
        return this.field_3_text;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public final void serialize(LittleEndianOutput littleEndianOutput) {
        if (getTextLength() > 0) {
            littleEndianOutput.writeShort(getTextLength());
            littleEndianOutput.writeByte(this.field_2_hasMultibyte ? 1 : 0);
            if (this.field_2_hasMultibyte) {
                StringUtil.putUnicodeLE(this.field_3_text, littleEndianOutput);
            } else {
                StringUtil.putCompressedUnicode(this.field_3_text, littleEndianOutput);
            }
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected final int getDataSize() {
        if (getTextLength() < 1) {
            return 0;
        }
        return (getTextLength() * (this.field_2_hasMultibyte ? 2 : 1)) + 3;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("text", new Supplier() { // from class: org.apache.poi.hssf.record.HeaderFooterBase$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return HeaderFooterBase.this.getText();
            }
        });
    }
}
