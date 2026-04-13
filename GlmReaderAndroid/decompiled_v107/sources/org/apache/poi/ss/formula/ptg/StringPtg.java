package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class StringPtg extends ScalarConstantPtg {
    private static final char FORMULA_DELIMITER = '\"';
    public static final byte sid = 23;
    private final boolean _is16bitUnicode;
    private final String field_3_string;

    public StringPtg(LittleEndianInput in) {
        int nChars = in.readUByte();
        this._is16bitUnicode = (in.readByte() & 1) != 0;
        if (this._is16bitUnicode) {
            this.field_3_string = StringUtil.readUnicodeLE(in, nChars);
        } else {
            this.field_3_string = StringUtil.readCompressedUnicode(in, nChars);
        }
    }

    public StringPtg(String value) {
        if (value.length() > 255) {
            throw new IllegalArgumentException("String literals in formulas can't be bigger than 255 characters ASCII");
        }
        this._is16bitUnicode = StringUtil.hasMultibyte(value);
        this.field_3_string = value;
    }

    public String getValue() {
        return this.field_3_string;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeByte(this.field_3_string.length());
        littleEndianOutput.writeByte(this._is16bitUnicode ? 1 : 0);
        if (this._is16bitUnicode) {
            StringUtil.putUnicodeLE(this.field_3_string, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_3_string, littleEndianOutput);
        }
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return (this.field_3_string.length() * (this._is16bitUnicode ? 2 : 1)) + 3;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        String value = this.field_3_string;
        int len = value.length();
        StringBuilder sb = new StringBuilder(len + 4);
        sb.append('\"');
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if (c == '\"') {
                sb.append('\"');
            }
            sb.append(c);
        }
        sb.append('\"');
        return sb.toString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public StringPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.StringPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return StringPtg.this.getValue();
            }
        });
    }
}
