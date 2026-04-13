package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;

/* loaded from: classes10.dex */
public class XDDFScene3D {
    private CTScene3D scene;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFScene3D(CTScene3D scene) {
        this.scene = scene;
    }

    @Internal
    public CTScene3D getXmlObject() {
        return this.scene;
    }
}
