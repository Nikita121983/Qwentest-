package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;

/* loaded from: classes10.dex */
public class XDDFBulletStyleCharacter implements XDDFBulletStyle {
    private CTTextCharBullet style;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletStyleCharacter(CTTextCharBullet style) {
        this.style = style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextCharBullet getXmlObject() {
        return this.style;
    }

    public String getCharacter() {
        return this.style.getChar();
    }

    public void setCharacter(String value) {
        this.style.setChar(value);
    }
}
