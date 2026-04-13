package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

/* loaded from: classes10.dex */
public abstract class XDDFSpacing {
    protected CTTextSpacing spacing;

    /* loaded from: classes10.dex */
    public enum Kind {
        PERCENT,
        POINTS
    }

    public abstract Kind getType();

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFSpacing(CTTextSpacing spacing) {
        this.spacing = spacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextSpacing getXmlObject() {
        return this.spacing;
    }
}
