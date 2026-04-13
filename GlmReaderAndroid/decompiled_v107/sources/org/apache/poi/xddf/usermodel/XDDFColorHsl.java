package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;

/* loaded from: classes10.dex */
public class XDDFColorHsl extends XDDFColor {
    private CTHslColor color;

    public XDDFColorHsl(int hue, int saturation, int luminance) {
        this(CTHslColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setHue(hue);
        setSaturation(saturation);
        setLuminance(luminance);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorHsl(CTHslColor color) {
        this(color, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorHsl(CTHslColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override // org.apache.poi.xddf.usermodel.XDDFColor
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public int getHue() {
        return this.color.getHue2();
    }

    public void setHue(int hue) {
        this.color.setHue2(hue);
    }

    public int getSaturation() {
        return POIXMLUnits.parsePercent(this.color.xgetSat2()) / 1000;
    }

    public void setSaturation(int saturation) {
        this.color.setSat2(Integer.valueOf(saturation));
    }

    public int getLuminance() {
        return POIXMLUnits.parsePercent(this.color.xgetLum2()) / 1000;
    }

    public void setLuminance(int lightness) {
        this.color.setLum2(Integer.valueOf(lightness));
    }
}
