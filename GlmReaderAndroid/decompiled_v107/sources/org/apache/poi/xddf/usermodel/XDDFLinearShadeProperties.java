package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;

/* loaded from: classes10.dex */
public class XDDFLinearShadeProperties {
    private CTLinearShadeProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFLinearShadeProperties(CTLinearShadeProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLinearShadeProperties getXmlObject() {
        return this.props;
    }

    public Double getAngle() {
        if (this.props.isSetAng()) {
            return Double.valueOf(Angles.attributeToDegrees(this.props.getAng()));
        }
        return null;
    }

    public void setAngle(Double angle) {
        if (angle == null) {
            if (this.props.isSetAng()) {
                this.props.unsetAng();
            }
        } else {
            if (angle.doubleValue() < 0.0d || 360.0d <= angle.doubleValue()) {
                throw new IllegalArgumentException("angle must be in the range [0, 360).");
            }
            this.props.setAng(Angles.degreesToAttribute(angle.doubleValue()));
        }
    }

    public Boolean isScaled() {
        if (this.props.isSetScaled()) {
            return Boolean.valueOf(this.props.getScaled());
        }
        return false;
    }

    public void setScaled(Boolean scaled) {
        if (scaled == null) {
            if (this.props.isSetScaled()) {
                this.props.unsetScaled();
                return;
            }
            return;
        }
        this.props.setScaled(scaled.booleanValue());
    }
}
