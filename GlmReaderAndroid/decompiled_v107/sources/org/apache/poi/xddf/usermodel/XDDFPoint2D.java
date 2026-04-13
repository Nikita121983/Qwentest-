package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;

/* loaded from: classes10.dex */
public class XDDFPoint2D {
    private CTPoint2D point;
    private long x;
    private long y;

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPoint2D(CTPoint2D point) {
        this.point = point;
    }

    public XDDFPoint2D(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        if (this.point == null) {
            return this.x;
        }
        return POIXMLUnits.parseLength(this.point.xgetX());
    }

    public long getY() {
        if (this.point == null) {
            return this.y;
        }
        return POIXMLUnits.parseLength(this.point.xgetY());
    }
}
