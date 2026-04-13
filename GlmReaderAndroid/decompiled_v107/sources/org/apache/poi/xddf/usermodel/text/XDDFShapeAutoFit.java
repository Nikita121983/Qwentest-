package org.apache.poi.xddf.usermodel.text;

import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextShapeAutofit;

/* loaded from: classes10.dex */
public class XDDFShapeAutoFit implements XDDFAutoFit {
    private CTTextShapeAutofit autofit;

    public XDDFShapeAutoFit() {
        this(CTTextShapeAutofit.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFShapeAutoFit(CTTextShapeAutofit autofit) {
        this.autofit = autofit;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextShapeAutofit getXmlObject() {
        return this.autofit;
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFAutoFit
    public int getFontScale() {
        return BZip2Constants.BASEBLOCKSIZE;
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFAutoFit
    public int getLineSpaceReduction() {
        return 0;
    }
}
