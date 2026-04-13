package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

/* loaded from: classes10.dex */
public class XSSFFirstFooter extends XSSFHeaderFooter implements Footer {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFFirstFooter(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentFirst(true);
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public String getText() {
        return getHeaderFooter().getFirstFooter();
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public void setText(String text) {
        if (text == null) {
            getHeaderFooter().unsetFirstFooter();
            if (!getHeaderFooter().isSetFirstHeader()) {
                getHeaderFooter().unsetDifferentFirst();
                return;
            }
            return;
        }
        getHeaderFooter().setFirstFooter(text);
    }
}
