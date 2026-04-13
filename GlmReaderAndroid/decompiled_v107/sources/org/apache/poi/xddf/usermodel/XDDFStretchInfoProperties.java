package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;

/* loaded from: classes10.dex */
public class XDDFStretchInfoProperties {
    private CTStretchInfoProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFStretchInfoProperties(CTStretchInfoProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTStretchInfoProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillRectangle() {
        if (this.props.isSetFillRect()) {
            return new XDDFRelativeRectangle(this.props.getFillRect());
        }
        return null;
    }

    public void setFillRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetFillRect()) {
                this.props.unsetFillRect();
                return;
            }
            return;
        }
        this.props.setFillRect(rectangle.getXmlObject());
    }
}
