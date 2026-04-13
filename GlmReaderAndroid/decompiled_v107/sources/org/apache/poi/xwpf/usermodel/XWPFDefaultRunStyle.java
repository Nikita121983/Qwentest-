package org.apache.poi.xwpf.usermodel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Removal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

/* loaded from: classes10.dex */
public class XWPFDefaultRunStyle {
    private CTRPr rpr;

    public XWPFDefaultRunStyle(CTRPr rpr) {
        this.rpr = rpr;
    }

    public CTRPr getRPr() {
        return this.rpr;
    }

    @Removal(version = "6.0.0")
    @Deprecated
    public int getFontSize() {
        BigDecimal bd = getFontSizeAsBigDecimal(0);
        if (bd == null) {
            return -1;
        }
        return bd.intValue();
    }

    public Double getFontSizeAsDouble() {
        BigDecimal bd = getFontSizeAsBigDecimal(1);
        if (bd == null) {
            return null;
        }
        return Double.valueOf(bd.doubleValue());
    }

    private BigDecimal getFontSizeAsBigDecimal(int scale) {
        if (this.rpr == null || this.rpr.sizeOfSzArray() <= 0) {
            return null;
        }
        return BigDecimal.valueOf(Units.toPoints(POIXMLUnits.parseLength(this.rpr.getSzArray(0).xgetVal()))).divide(BigDecimal.valueOf(4L), scale, RoundingMode.HALF_UP);
    }
}
