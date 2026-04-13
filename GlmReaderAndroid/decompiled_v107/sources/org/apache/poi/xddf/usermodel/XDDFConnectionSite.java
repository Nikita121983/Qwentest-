package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;

/* loaded from: classes10.dex */
public class XDDFConnectionSite {
    private CTConnectionSite site;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFConnectionSite(CTConnectionSite site) {
        this.site = site;
    }

    @Internal
    public CTConnectionSite getXmlObject() {
        return this.site;
    }
}
