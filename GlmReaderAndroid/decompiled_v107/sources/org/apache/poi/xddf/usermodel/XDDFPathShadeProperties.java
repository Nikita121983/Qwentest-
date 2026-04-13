package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;

/* loaded from: classes10.dex */
public class XDDFPathShadeProperties {
    private CTPathShadeProperties props;

    public XDDFPathShadeProperties() {
        this(CTPathShadeProperties.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPathShadeProperties(CTPathShadeProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTPathShadeProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillToRectangle() {
        if (this.props.isSetFillToRect()) {
            return new XDDFRelativeRectangle(this.props.getFillToRect());
        }
        return null;
    }

    public void setFillToRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetFillToRect()) {
                this.props.unsetFillToRect();
                return;
            }
            return;
        }
        this.props.setFillToRect(rectangle.getXmlObject());
    }

    public PathShadeType getPathShadeType() {
        if (this.props.isSetPath()) {
            return PathShadeType.valueOf(this.props.getPath());
        }
        return null;
    }

    public void setPathShadeType(PathShadeType path) {
        if (path == null) {
            if (this.props.isSetPath()) {
                this.props.unsetPath();
                return;
            }
            return;
        }
        this.props.setPath(path.underlying);
    }
}
