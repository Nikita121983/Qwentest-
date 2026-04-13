package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.ss.usermodel.Footer;

/* loaded from: classes10.dex */
public final class HSSFFooter extends HeaderFooter implements Footer {
    private final PageSettingsBlock _psb;

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFFooter(PageSettingsBlock psb) {
        this._psb = psb;
    }

    @Override // org.apache.poi.hssf.usermodel.HeaderFooter
    protected String getRawText() {
        FooterRecord hf = this._psb.getFooter();
        if (hf == null) {
            return "";
        }
        return hf.getText();
    }

    @Override // org.apache.poi.hssf.usermodel.HeaderFooter
    protected void setHeaderFooterText(String text) {
        FooterRecord hfr = this._psb.getFooter();
        if (hfr == null) {
            this._psb.setFooter(new FooterRecord(text));
        } else {
            hfr.setText(text);
        }
    }
}
