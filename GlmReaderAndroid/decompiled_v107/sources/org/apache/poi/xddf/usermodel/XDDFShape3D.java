package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;

/* loaded from: classes10.dex */
public class XDDFShape3D {
    private CTShape3D shape;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFShape3D(CTShape3D shape) {
        this.shape = shape;
    }

    @Internal
    public CTShape3D getXmlObject() {
        return this.shape;
    }
}
