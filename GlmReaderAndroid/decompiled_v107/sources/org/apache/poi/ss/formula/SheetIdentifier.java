package org.apache.poi.ss.formula;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes10.dex */
public class SheetIdentifier {
    private final String _bookName;
    private final NameIdentifier _sheetIdentifier;

    public SheetIdentifier(String bookName, NameIdentifier sheetIdentifier) {
        this._bookName = bookName;
        this._sheetIdentifier = sheetIdentifier;
    }

    public String getBookName() {
        return this._bookName;
    }

    public NameIdentifier getSheetIdentifier() {
        return this._sheetIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void asFormulaString(StringBuilder sb) {
        if (this._bookName != null) {
            sb.append(" [").append(this._sheetIdentifier.getName()).append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        if (this._sheetIdentifier.isQuoted()) {
            sb.append(Chars.QUOTE).append(this._sheetIdentifier.getName()).append("'");
        } else {
            sb.append(this._sheetIdentifier.getName());
        }
    }

    public String asFormulaString() {
        StringBuilder sb = new StringBuilder(32);
        asFormulaString(sb);
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " [" + asFormulaString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
