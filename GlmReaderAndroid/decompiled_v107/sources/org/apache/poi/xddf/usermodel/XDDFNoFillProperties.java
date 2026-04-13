package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;

/* loaded from: classes10.dex */
public class XDDFNoFillProperties implements XDDFFillProperties {
    private CTNoFillProperties props;

    public XDDFNoFillProperties() {
        this(CTNoFillProperties.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFNoFillProperties(CTNoFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTNoFillProperties getXmlObject() {
        return this.props;
    }
}
