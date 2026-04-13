package org.apache.poi.xddf.usermodel.text;

import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;

/* loaded from: classes10.dex */
public class XDDFNormalAutoFit implements XDDFAutoFit {
    private CTTextNormalAutofit autofit;

    public XDDFNormalAutoFit() {
        this(CTTextNormalAutofit.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFNormalAutoFit(CTTextNormalAutofit autofit) {
        this.autofit = autofit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextNormalAutofit getXmlObject() {
        return this.autofit;
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFAutoFit
    public int getFontScale() {
        if (this.autofit.isSetFontScale()) {
            return POIXMLUnits.parsePercent(this.autofit.xgetFontScale());
        }
        return BZip2Constants.BASEBLOCKSIZE;
    }

    public void setFontScale(Integer value) {
        if (value == null) {
            if (this.autofit.isSetFontScale()) {
                this.autofit.unsetFontScale();
                return;
            }
            return;
        }
        this.autofit.setFontScale(value);
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFAutoFit
    public int getLineSpaceReduction() {
        if (this.autofit.isSetLnSpcReduction()) {
            return POIXMLUnits.parsePercent(this.autofit.xgetLnSpcReduction());
        }
        return 0;
    }

    public void setLineSpaceReduction(Integer value) {
        if (value == null) {
            if (this.autofit.isSetLnSpcReduction()) {
                this.autofit.unsetLnSpcReduction();
                return;
            }
            return;
        }
        this.autofit.setLnSpcReduction(value);
    }
}
