package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;

/* loaded from: classes10.dex */
public class XDDFBulletSizeFollowText implements XDDFBulletSize {
    private CTTextBulletSizeFollowText follow;

    public XDDFBulletSizeFollowText() {
        this(CTTextBulletSizeFollowText.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletSizeFollowText(CTTextBulletSizeFollowText follow) {
        this.follow = follow;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizeFollowText getXmlObject() {
        return this.follow;
    }
}
