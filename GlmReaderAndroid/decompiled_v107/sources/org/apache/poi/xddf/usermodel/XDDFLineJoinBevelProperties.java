package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;

/* loaded from: classes10.dex */
public class XDDFLineJoinBevelProperties implements XDDFLineJoinProperties {
    private CTLineJoinBevel join;

    public XDDFLineJoinBevelProperties() {
        this(CTLineJoinBevel.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFLineJoinBevelProperties(CTLineJoinBevel join) {
        this.join = join;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLineJoinBevel getXmlObject() {
        return this.join;
    }
}
