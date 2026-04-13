package org.apache.poi.xddf.usermodel;

import java.util.Locale;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

/* loaded from: classes10.dex */
public class XDDFColorRgbPercent extends XDDFColor {
    private final CTScRgbColor color;

    public XDDFColorRgbPercent(int red, int green, int blue) {
        this(CTScRgbColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorRgbPercent(CTScRgbColor color) {
        this(color, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorRgbPercent(CTScRgbColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override // org.apache.poi.xddf.usermodel.XDDFColor
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public int getRed() {
        return POIXMLUnits.parsePercent(this.color.xgetR());
    }

    public void setRed(int red) {
        this.color.setR(Integer.valueOf(normalize(red)));
    }

    public int getGreen() {
        return POIXMLUnits.parsePercent(this.color.xgetG());
    }

    public void setGreen(int green) {
        this.color.setG(Integer.valueOf(normalize(green)));
    }

    public int getBlue() {
        return POIXMLUnits.parsePercent(this.color.xgetB());
    }

    public void setBlue(int blue) {
        this.color.setB(Integer.valueOf(normalize(blue)));
    }

    private int normalize(int value) {
        if (value < 0) {
            return 0;
        }
        return Math.min(BZip2Constants.BASEBLOCKSIZE, value);
    }

    public String toRGBHex() {
        int c = 0;
        STPercentage[] sTPercentageArr = {this.color.xgetR(), this.color.xgetG(), this.color.xgetB()};
        for (int i = 0; i < 3; i++) {
            STPercentage pct = sTPercentageArr[i];
            c = (c << 8) | (((POIXMLUnits.parsePercent(pct) * 255) / BZip2Constants.BASEBLOCKSIZE) & 255);
        }
        return String.format(Locale.ROOT, "%06X", Integer.valueOf(c));
    }
}
