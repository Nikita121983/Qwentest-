package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

/* loaded from: classes10.dex */
public class XSSFEvenHeader extends XSSFHeaderFooter implements Header {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFEvenHeader(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentOddEven(true);
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public String getText() {
        return getHeaderFooter().getEvenHeader();
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public void setText(String text) {
        if (text == null) {
            getHeaderFooter().unsetEvenHeader();
            if (!getHeaderFooter().isSetEvenFooter()) {
                getHeaderFooter().unsetDifferentOddEven();
                return;
            }
            return;
        }
        getHeaderFooter().setEvenHeader(text);
    }
}
