package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;

/* loaded from: classes10.dex */
public class XDDFEffectList {
    private CTEffectList list;

    @Internal
    public XDDFEffectList(CTEffectList list) {
        this.list = list;
    }

    @Internal
    public CTEffectList getXmlObject() {
        return this.list;
    }
}
