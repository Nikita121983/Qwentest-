package org.apache.poi.xssf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* loaded from: classes10.dex */
class XSSFLineBreak extends XSSFTextRun {
    private final CTTextCharacterProperties _brProps;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFLineBreak(CTRegularTextRun r, XSSFTextParagraph p, CTTextCharacterProperties brProps) {
        super(r, p);
        this._brProps = brProps;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xssf.usermodel.XSSFTextRun
    public CTTextCharacterProperties getRPr() {
        return this._brProps;
    }

    @Override // org.apache.poi.xssf.usermodel.XSSFTextRun
    public void setText(String text) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}
