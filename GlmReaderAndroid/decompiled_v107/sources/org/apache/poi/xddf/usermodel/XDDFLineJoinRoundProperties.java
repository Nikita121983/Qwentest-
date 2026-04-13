package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;

/* loaded from: classes10.dex */
public class XDDFLineJoinRoundProperties implements XDDFLineJoinProperties {
    private CTLineJoinRound join;

    public XDDFLineJoinRoundProperties() {
        this(CTLineJoinRound.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFLineJoinRoundProperties(CTLineJoinRound join) {
        this.join = join;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLineJoinRound getXmlObject() {
        return this.join;
    }
}
