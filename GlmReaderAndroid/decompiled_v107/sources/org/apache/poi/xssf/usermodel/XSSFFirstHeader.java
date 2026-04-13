package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

/* loaded from: classes10.dex */
public class XSSFFirstHeader extends XSSFHeaderFooter implements Header {
    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFFirstHeader(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentFirst(true);
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public String getText() {
        return getHeaderFooter().getFirstHeader();
    }

    @Override // org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter
    public void setText(String text) {
        if (text == null) {
            getHeaderFooter().unsetFirstHeader();
            if (!getHeaderFooter().isSetFirstFooter()) {
                getHeaderFooter().unsetDifferentFirst();
                return;
            }
            return;
        }
        getHeaderFooter().setFirstHeader(text);
    }
}
