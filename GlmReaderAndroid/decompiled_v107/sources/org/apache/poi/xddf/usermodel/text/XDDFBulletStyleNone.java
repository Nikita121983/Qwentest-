package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoBullet;

/* loaded from: classes10.dex */
public class XDDFBulletStyleNone implements XDDFBulletStyle {
    private CTTextNoBullet style;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletStyleNone(CTTextNoBullet style) {
        this.style = style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextNoBullet getXmlObject() {
        return this.style;
    }
}
