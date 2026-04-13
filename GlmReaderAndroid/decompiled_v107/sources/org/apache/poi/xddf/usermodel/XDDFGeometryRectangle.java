package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;

/* loaded from: classes10.dex */
public class XDDFGeometryRectangle {
    private CTGeomRect rectangle;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFGeometryRectangle(CTGeomRect rectangle) {
        this.rectangle = rectangle;
    }

    @Internal
    public CTGeomRect getXmlObject() {
        return this.rectangle;
    }
}
