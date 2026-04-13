package org.apache.poi.xslf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class XSLFLineBreak extends XSLFTextRun {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFLineBreak(CTTextLineBreak r, XSLFTextParagraph p) {
        super(r, p);
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFTextRun, org.apache.poi.sl.usermodel.TextRun
    public void setText(String text) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}
