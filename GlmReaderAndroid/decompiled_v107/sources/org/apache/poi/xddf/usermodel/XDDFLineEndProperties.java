package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;

/* loaded from: classes10.dex */
public class XDDFLineEndProperties {
    private CTLineEndProperties props;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFLineEndProperties(CTLineEndProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLineEndProperties getXmlObject() {
        return this.props;
    }

    public LineEndLength getLength() {
        return LineEndLength.valueOf(this.props.getLen());
    }

    public void setLength(LineEndLength length) {
        this.props.setLen(length.underlying);
    }

    public LineEndType getType() {
        return LineEndType.valueOf(this.props.getType());
    }

    public void setType(LineEndType type) {
        this.props.setType(type.underlying);
    }

    public LineEndWidth getWidth() {
        return LineEndWidth.valueOf(this.props.getW());
    }

    public void setWidth(LineEndWidth width) {
        this.props.setW(width.underlying);
    }
}
