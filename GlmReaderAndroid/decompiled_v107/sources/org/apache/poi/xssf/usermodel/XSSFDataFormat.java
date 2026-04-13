package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.model.StylesTable;

/* loaded from: classes10.dex */
public class XSSFDataFormat implements DataFormat {
    private final StylesTable stylesSource;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFDataFormat(StylesTable stylesSource) {
        this.stylesSource = stylesSource;
    }

    @Override // org.apache.poi.ss.usermodel.DataFormat
    public short getFormat(String format) {
        int idx = BuiltinFormats.getBuiltinFormat(format);
        if (idx == -1) {
            idx = this.stylesSource.putNumberFormat(format);
        }
        return (short) idx;
    }

    @Override // org.apache.poi.ss.usermodel.DataFormat
    public String getFormat(short index) {
        String fmt = this.stylesSource.getNumberFormatAt(index);
        return fmt == null ? BuiltinFormats.getBuiltinFormat(index) : fmt;
    }

    public void putFormat(short index, String format) {
        this.stylesSource.putNumberFormat(index, format);
    }
}
