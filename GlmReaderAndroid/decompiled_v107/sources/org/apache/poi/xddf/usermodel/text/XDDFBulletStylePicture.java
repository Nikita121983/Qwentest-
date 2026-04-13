package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFPicture;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;

/* loaded from: classes10.dex */
public class XDDFBulletStylePicture implements XDDFBulletStyle {
    private CTTextBlipBullet style;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletStylePicture(CTTextBlipBullet style) {
        this.style = style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextBlipBullet getXmlObject() {
        return this.style;
    }

    public XDDFPicture getPicture() {
        return new XDDFPicture(this.style.getBlip());
    }

    public void setPicture(XDDFPicture picture) {
        if (picture != null) {
            this.style.setBlip(picture.getXmlObject());
        }
    }
}
