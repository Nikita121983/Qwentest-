package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

/* loaded from: classes10.dex */
public class XSSFEvenFooter extends XSSFHeaderFooter implements Footer {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFEvenFooter(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentOddEven(true);
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public String getText() {
        return getHeaderFooter().getEvenFooter();
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public void setText(String text) {
        if (text == null) {
            getHeaderFooter().unsetEvenFooter();
            if (!getHeaderFooter().isSetEvenHeader()) {
                getHeaderFooter().unsetDifferentOddEven();
                return;
            }
            return;
        }
        getHeaderFooter().setEvenFooter(text);
    }
}
