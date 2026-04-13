package org.apache.poi.xddf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;

/* loaded from: classes10.dex */
public class XDDFPositiveSize2D {
    private CTPositiveSize2D size;
    private long x;
    private long y;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPositiveSize2D(CTPositiveSize2D size) {
        this.size = size;
    }

    public XDDFPositiveSize2D(long x, long y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x and y must be positive");
        }
        this.x = x;
        this.y = y;
    }

    public long getX() {
        if (this.size == null) {
            return this.x;
        }
        return this.size.getCx();
    }

    public long getY() {
        if (this.size == null) {
            return this.y;
        }
        return this.size.getCy();
    }
}
