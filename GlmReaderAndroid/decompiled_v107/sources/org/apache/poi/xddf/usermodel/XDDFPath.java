package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;

/* loaded from: classes10.dex */
public class XDDFPath {
    private CTPath2D path;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFPath(CTPath2D path) {
        this.path = path;
    }

    @Internal
    public CTPath2D getXmlObject() {
        return this.path;
    }
}
