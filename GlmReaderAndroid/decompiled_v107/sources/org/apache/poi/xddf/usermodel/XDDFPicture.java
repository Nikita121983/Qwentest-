package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;

/* loaded from: classes10.dex */
public class XDDFPicture {
    private CTBlip blip;

    @Internal
    public XDDFPicture(CTBlip blip) {
        this.blip = blip;
    }

    @Internal
    public CTBlip getXmlObject() {
        return this.blip;
    }
}
