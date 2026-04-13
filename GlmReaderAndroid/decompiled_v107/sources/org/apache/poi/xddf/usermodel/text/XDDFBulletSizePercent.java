package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;

/* loaded from: classes10.dex */
public class XDDFBulletSizePercent implements XDDFBulletSize {
    private CTTextBulletSizePercent percent;
    private Double scale;

    public XDDFBulletSizePercent(double value) {
        this(CTTextBulletSizePercent.Factory.newInstance(), null);
        setPercent(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFBulletSizePercent(CTTextBulletSizePercent percent, Double scale) {
        this.percent = percent;
        this.scale = Double.valueOf(scale != null ? 0.001d * scale.doubleValue() : 0.001d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTTextBulletSizePercent getXmlObject() {
        return this.percent;
    }

    public double getPercent() {
        return POIXMLUnits.parsePercent(this.percent.xgetVal()) * this.scale.doubleValue();
    }

    public void setPercent(double value) {
        this.percent.setVal(Long.toString(Math.round(1000.0d * value)));
    }
}
