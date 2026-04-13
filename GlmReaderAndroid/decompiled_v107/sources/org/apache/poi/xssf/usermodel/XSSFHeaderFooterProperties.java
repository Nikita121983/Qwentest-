package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

/* loaded from: classes10.dex */
public class XSSFHeaderFooterProperties {
    private final CTHeaderFooter headerFooter;

    public XSSFHeaderFooterProperties(CTHeaderFooter headerFooter) {
        this.headerFooter = headerFooter;
    }

    @Internal
    public CTHeaderFooter getHeaderFooter() {
        return this.headerFooter;
    }

    public boolean getAlignWithMargins() {
        return getHeaderFooter().isSetAlignWithMargins() && getHeaderFooter().getAlignWithMargins();
    }

    public boolean getDifferentFirst() {
        return getHeaderFooter().isSetDifferentFirst() && getHeaderFooter().getDifferentFirst();
    }

    public boolean getDifferentOddEven() {
        return getHeaderFooter().isSetDifferentOddEven() && getHeaderFooter().getDifferentOddEven();
    }

    public boolean getScaleWithDoc() {
        return getHeaderFooter().isSetScaleWithDoc() && getHeaderFooter().getScaleWithDoc();
    }

    public void setAlignWithMargins(boolean flag) {
        getHeaderFooter().setAlignWithMargins(flag);
    }

    public void setDifferentFirst(boolean flag) {
        getHeaderFooter().setDifferentFirst(flag);
    }

    public void setDifferentOddEven(boolean flag) {
        getHeaderFooter().setDifferentOddEven(flag);
    }

    public void setScaleWithDoc(boolean flag) {
        getHeaderFooter().setScaleWithDoc(flag);
    }

    public void removeAlignWithMargins() {
        if (getHeaderFooter().isSetAlignWithMargins()) {
            getHeaderFooter().unsetAlignWithMargins();
        }
    }

    public void removeDifferentFirst() {
        if (getHeaderFooter().isSetDifferentFirst()) {
            getHeaderFooter().unsetDifferentFirst();
        }
    }

    public void removeDifferentOddEven() {
        if (getHeaderFooter().isSetDifferentOddEven()) {
            getHeaderFooter().unsetDifferentOddEven();
        }
    }

    public void removeScaleWithDoc() {
        if (getHeaderFooter().isSetScaleWithDoc()) {
            getHeaderFooter().unsetScaleWithDoc();
        }
    }
}
