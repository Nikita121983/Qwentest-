package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException;

/* loaded from: classes10.dex */
public class XWPFLatentStyles {
    private CTLatentStyles latentStyles;
    protected XWPFStyles styles;

    protected XWPFLatentStyles() {
    }

    protected XWPFLatentStyles(CTLatentStyles latentStyles) {
        this(latentStyles, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XWPFLatentStyles(CTLatentStyles latentStyles, XWPFStyles styles) {
        this.latentStyles = latentStyles;
        this.styles = styles;
    }

    public int getNumberOfStyles() {
        return this.latentStyles.sizeOfLsdExceptionArray();
    }

    public boolean isLatentStyle(String latentStyleName) {
        for (CTLsdException lsd : this.latentStyles.getLsdExceptionArray()) {
            if (lsd.getName().equals(latentStyleName)) {
                return true;
            }
        }
        return false;
    }
}
