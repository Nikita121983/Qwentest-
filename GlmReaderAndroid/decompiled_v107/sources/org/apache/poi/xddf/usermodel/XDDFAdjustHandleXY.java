package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

/* loaded from: classes10.dex */
public class XDDFAdjustHandleXY {
    private CTXYAdjustHandle handle;

    @Internal
    public XDDFAdjustHandleXY(CTXYAdjustHandle handle) {
        this.handle = handle;
    }

    @Internal
    public CTXYAdjustHandle getXmlObject() {
        return this.handle;
    }
}
