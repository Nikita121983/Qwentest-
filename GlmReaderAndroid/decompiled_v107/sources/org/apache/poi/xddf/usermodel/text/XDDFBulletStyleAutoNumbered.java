package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;

/* loaded from: classes10.dex */
public class XDDFBulletStyleAutoNumbered implements XDDFBulletStyle {
    private CTTextAutonumberBullet style;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletStyleAutoNumbered(CTTextAutonumberBullet style) {
        this.style = style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextAutonumberBullet getXmlObject() {
        return this.style;
    }

    public AutonumberScheme getType() {
        return AutonumberScheme.valueOf(this.style.getType());
    }

    public void setType(AutonumberScheme scheme) {
        this.style.setType(scheme.underlying);
    }

    public int getStartAt() {
        if (this.style.isSetStartAt()) {
            return this.style.getStartAt();
        }
        return 1;
    }

    public void setStartAt(Integer value) {
        if (value == null) {
            if (this.style.isSetStartAt()) {
                this.style.unsetStartAt();
                return;
            }
            return;
        }
        this.style.setStartAt(value.intValue());
    }
}
