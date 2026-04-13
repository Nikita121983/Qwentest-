package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

/* loaded from: classes10.dex */
public class XDDFTransform2D {
    private CTTransform2D transform;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFTransform2D(CTTransform2D transform) {
        this.transform = transform;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTransform2D getXmlObject() {
        return this.transform;
    }

    public Boolean getFlipHorizontal() {
        if (this.transform.isSetFlipH()) {
            return Boolean.valueOf(this.transform.getFlipH());
        }
        return null;
    }

    public void setFlipHorizontal(Boolean flip) {
        if (flip == null) {
            if (this.transform.isSetFlipH()) {
                this.transform.unsetFlipH();
                return;
            }
            return;
        }
        this.transform.setFlipH(flip.booleanValue());
    }

    public Boolean getFlipVertical() {
        if (this.transform.isSetFlipV()) {
            return Boolean.valueOf(this.transform.getFlipV());
        }
        return null;
    }

    public void setFlipVertical(Boolean flip) {
        if (flip == null) {
            if (this.transform.isSetFlipV()) {
                this.transform.unsetFlipV();
                return;
            }
            return;
        }
        this.transform.setFlipV(flip.booleanValue());
    }

    public XDDFPositiveSize2D getExtension() {
        if (this.transform.isSetExt()) {
            return new XDDFPositiveSize2D(this.transform.getExt());
        }
        return null;
    }

    public void setExtension(XDDFPositiveSize2D extension) {
        CTPositiveSize2D xformExt;
        if (extension == null) {
            if (this.transform.isSetExt()) {
                this.transform.unsetExt();
            }
        } else {
            if (this.transform.isSetExt()) {
                xformExt = this.transform.getExt();
            } else {
                xformExt = this.transform.addNewExt();
            }
            xformExt.setCx(extension.getX());
            xformExt.setCy(extension.getY());
        }
    }

    public XDDFPoint2D getOffset() {
        if (this.transform.isSetOff()) {
            return new XDDFPoint2D(this.transform.getOff());
        }
        return null;
    }

    public void setOffset(XDDFPoint2D offset) {
        CTPoint2D xformOff;
        if (offset == null) {
            if (this.transform.isSetOff()) {
                this.transform.unsetOff();
            }
        } else {
            if (this.transform.isSetOff()) {
                xformOff = this.transform.getOff();
            } else {
                xformOff = this.transform.addNewOff();
            }
            xformOff.setX(Long.valueOf(offset.getX()));
            xformOff.setY(Long.valueOf(offset.getY()));
        }
    }

    public Double getRotation() {
        if (this.transform.isSetRot()) {
            return Double.valueOf(Angles.attributeToDegrees(this.transform.getRot()));
        }
        return null;
    }

    public void setRotation(Double rotation) {
        if (rotation == null) {
            if (this.transform.isSetRot()) {
                this.transform.unsetRot();
                return;
            }
            return;
        }
        this.transform.setRot(Angles.degreesToAttribute(rotation.doubleValue()));
    }
}
