package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public class SheetRangeIdentifier extends SheetIdentifier {
    private final NameIdentifier _lastSheetIdentifier;

    public SheetRangeIdentifier(String bookName, NameIdentifier firstSheetIdentifier, NameIdentifier lastSheetIdentifier) {
        super(bookName, firstSheetIdentifier);
        this._lastSheetIdentifier = lastSheetIdentifier;
    }

    public NameIdentifier getFirstSheetIdentifier() {
        return super.getSheetIdentifier();
    }

    public NameIdentifier getLastSheetIdentifier() {
        return this._lastSheetIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ss.formula.SheetIdentifier
    public void asFormulaString(StringBuilder sb) {
        super.asFormulaString(sb);
        sb.append(NameUtil.COLON);
        if (this._lastSheetIdentifier.isQuoted()) {
            sb.append(Chars.QUOTE).append(this._lastSheetIdentifier.getName()).append("'");
        } else {
            sb.append(this._lastSheetIdentifier.getName());
        }
    }
}
