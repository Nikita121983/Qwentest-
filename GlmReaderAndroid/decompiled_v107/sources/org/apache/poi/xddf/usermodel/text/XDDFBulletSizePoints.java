package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;

/* loaded from: classes10.dex */
public class XDDFBulletSizePoints implements XDDFBulletSize {
    private CTTextBulletSizePoint points;

    public XDDFBulletSizePoints(double value) {
        this(CTTextBulletSizePoint.Factory.newInstance());
        setPoints(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletSizePoints(CTTextBulletSizePoint points) {
        this.points = points;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizePoint getXmlObject() {
        return this.points;
    }

    public double getPoints() {
        return this.points.getVal() * 0.01d;
    }

    public void setPoints(double value) {
        this.points.setVal(Math.toIntExact(Math.round(100.0d * value)));
    }
}
