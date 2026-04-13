package org.apache.poi.xslf.usermodel;

import androidx.core.text.util.LocalePreferences;
import java.awt.Color;
import javax.xml.namespace.QName;
import kotlin.UByte;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.PresetColor;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

@Internal
/* loaded from: classes10.dex */
public class XSLFColor {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) XSLFColor.class);
    private static final QName VAL_ATTR = new QName("val");
    private final Color _color;
    private final CTSchemeColor _phClr;
    private final XSLFSheet _sheet;
    private final XmlObject _xmlObject;

    public XSLFColor(XmlObject obj, XSLFTheme theme, CTSchemeColor phClr, XSLFSheet sheet) {
        this._xmlObject = obj;
        this._phClr = phClr;
        this._sheet = sheet;
        this._color = toColor(obj, theme);
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._xmlObject;
    }

    public Color getColor() {
        return DrawPaint.applyColorTransform(getColorStyle());
    }

    public ColorStyle getColorStyle() {
        return new XSLFColorStyle(this._xmlObject, this._color, this._phClr);
    }

    private Color toColor(CTHslColor hsl) {
        return DrawPaint.HSL2RGB(hsl.getHue2() / 60000.0d, POIXMLUnits.parsePercent(hsl.xgetSat2()) / 1000.0d, POIXMLUnits.parsePercent(hsl.xgetLum2()) / 1000.0d, 1.0d);
    }

    private Color toColor(CTPresetColor prst) {
        String colorName = prst.getVal().toString();
        PresetColor pc = PresetColor.valueOfOoxmlId(colorName);
        if (pc != null) {
            return pc.color;
        }
        return null;
    }

    private Color toColor(CTSchemeColor schemeColor, XSLFTheme theme) {
        String colorRef = schemeColor.getVal().toString();
        if (this._phClr != null) {
            colorRef = this._phClr.getVal().toString();
        }
        CTColor ctColor = theme == null ? null : theme.getCTColor(this._sheet.mapSchemeColor(colorRef));
        if (ctColor != null) {
            return toColor(ctColor, (XSLFTheme) null);
        }
        return null;
    }

    private Color toColor(CTScRgbColor scrgb) {
        return DrawPaint.SCRGB2RGB(POIXMLUnits.parsePercent(scrgb.xgetR()) / 100000.0d, POIXMLUnits.parsePercent(scrgb.xgetG()) / 100000.0d, POIXMLUnits.parsePercent(scrgb.xgetB()) / 100000.0d);
    }

    private Color toColor(CTSRgbColor srgb) {
        byte[] val = srgb.getVal();
        return new Color(val[0] & UByte.MAX_VALUE, val[1] & UByte.MAX_VALUE, val[2] & UByte.MAX_VALUE);
    }

    private Color toColor(CTSystemColor sys) {
        if (sys.isSetLastClr()) {
            byte[] val = sys.getLastClr();
            return new Color(val[0] & UByte.MAX_VALUE, val[1] & UByte.MAX_VALUE, val[2] & UByte.MAX_VALUE);
        }
        String colorName = sys.getVal().toString();
        PresetColor pc = PresetColor.valueOfOoxmlId(colorName);
        return (pc == null || pc.color == null) ? Color.black : pc.color;
    }

    private Color toColor(XmlObject obj, XSLFTheme theme) {
        if (obj == null) {
            if (this._phClr == null) {
                return null;
            }
            return toColor(this._phClr, theme);
        }
        Color color = null;
        XmlCursor cur = obj.newCursor();
        int idx = 0;
        while (color == null) {
            try {
                XmlObject ch = nextObject(obj, cur, idx);
                if (ch == null) {
                    break;
                }
                if (ch instanceof CTHslColor) {
                    color = toColor((CTHslColor) ch);
                } else if (ch instanceof CTPresetColor) {
                    color = toColor((CTPresetColor) ch);
                } else if (ch instanceof CTSchemeColor) {
                    color = toColor((CTSchemeColor) ch, theme);
                } else if (ch instanceof CTScRgbColor) {
                    color = toColor((CTScRgbColor) ch);
                } else if (ch instanceof CTSRgbColor) {
                    color = toColor((CTSRgbColor) ch);
                } else if (ch instanceof CTSystemColor) {
                    color = toColor((CTSystemColor) ch);
                } else if (!(ch instanceof CTFontReference) && idx > 0) {
                    throw new IllegalArgumentException("Unexpected color choice: " + ch.getClass());
                }
                idx++;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (cur != null) {
                        try {
                            cur.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return color;
    }

    private static XmlObject nextObject(XmlObject obj, XmlCursor cur, int idx) {
        switch (idx) {
            case 0:
                return obj;
            case 1:
                if (cur.toFirstChild()) {
                    return cur.getObject();
                }
                return null;
            default:
                if (cur.toNextSibling()) {
                    return cur.getObject();
                }
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public void setColor(Color color) {
        if (!(this._xmlObject instanceof CTSolidColorFillProperties)) {
            LOGGER.atError().log("XSLFColor.setColor currently only supports CTSolidColorFillProperties");
            return;
        }
        CTSolidColorFillProperties fill = (CTSolidColorFillProperties) this._xmlObject;
        if (fill.isSetSrgbClr()) {
            fill.unsetSrgbClr();
        }
        if (fill.isSetScrgbClr()) {
            fill.unsetScrgbClr();
        }
        if (fill.isSetHslClr()) {
            fill.unsetHslClr();
        }
        if (fill.isSetPrstClr()) {
            fill.unsetPrstClr();
        }
        if (fill.isSetSchemeClr()) {
            fill.unsetSchemeClr();
        }
        if (fill.isSetSysClr()) {
            fill.unsetSysClr();
        }
        CTPositiveFixedPercentage alphaPct = null;
        float[] rgbaf = color.getRGBComponents((float[]) null);
        boolean addAlpha = rgbaf.length == 4 && rgbaf[3] < 1.0f;
        if (isInt(rgbaf[0]) && isInt(rgbaf[1]) && isInt(rgbaf[2])) {
            CTSRgbColor rgb = fill.addNewSrgbClr();
            byte[] rgbBytes = {(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()};
            rgb.setVal(rgbBytes);
            if (addAlpha) {
                alphaPct = rgb.addNewAlpha();
            }
        } else {
            CTScRgbColor rgb2 = fill.addNewScrgbClr();
            double[] scRGB = DrawPaint.RGB2SCRGB(color);
            rgb2.setR(Integer.valueOf((int) Math.rint(scRGB[0] * 100000.0d)));
            rgb2.setG(Integer.valueOf((int) Math.rint(scRGB[1] * 100000.0d)));
            rgb2.setB(Integer.valueOf((int) Math.rint(scRGB[2] * 100000.0d)));
            if (addAlpha) {
                alphaPct = rgb2.addNewAlpha();
            }
        }
        if (alphaPct != null) {
            alphaPct.setVal(Integer.valueOf((int) Math.rint(rgbaf[3] * 100000.0f)));
        }
    }

    private static boolean isInt(float f) {
        return Math.abs((((double) f) * 255.0d) - Math.rint(((double) f) * 255.0d)) < 1.0E-5d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getRawValue(CTSchemeColor phClr, XmlObject xmlObject, String elem) {
        XmlObject[] xmlObjectArr = {xmlObject, phClr};
        for (int i = 0; i < 2; i++) {
            XmlObject obj = xmlObjectArr[i];
            if (obj != null) {
                XmlCursor cur = obj.newCursor();
                try {
                    if (cur.toChild(XSSFRelation.NS_DRAWINGML, elem) || (cur.toFirstChild() && cur.toChild(XSSFRelation.NS_DRAWINGML, elem))) {
                        String str = cur.getAttributeText(VAL_ATTR);
                        if (str != null && !"".equals(str)) {
                            int parseInt = Integer.parseInt(str);
                            if (cur != null) {
                                cur.close();
                            }
                            return parseInt;
                        }
                        if (cur == null) {
                        }
                        cur.close();
                    } else {
                        if (cur == null) {
                        }
                        cur.close();
                    }
                } finally {
                }
            }
        }
        return -1;
    }

    private int getPercentageValue(String elem) {
        int val = getRawValue(this._phClr, this._xmlObject, elem);
        return val == -1 ? val : val / 1000;
    }

    int getAlpha() {
        return getPercentageValue("alpha");
    }

    int getAlphaMod() {
        return getPercentageValue("alphaMod");
    }

    int getAlphaOff() {
        return getPercentageValue("alphaOff");
    }

    int getHue() {
        int val = getRawValue(this._phClr, this._xmlObject, "hue");
        return val == -1 ? val : val / Angles.OOXML_DEGREE;
    }

    int getHueMod() {
        return getPercentageValue("hueMod");
    }

    int getHueOff() {
        return getPercentageValue("hueOff");
    }

    int getLum() {
        return getPercentageValue("lum");
    }

    int getLumMod() {
        return getPercentageValue("lumMod");
    }

    int getLumOff() {
        return getPercentageValue("lumOff");
    }

    int getSat() {
        return getPercentageValue(LocalePreferences.FirstDayOfWeek.SATURDAY);
    }

    int getSatMod() {
        return getPercentageValue("satMod");
    }

    int getSatOff() {
        return getPercentageValue("satOff");
    }

    int getRed() {
        return getPercentageValue("red");
    }

    int getRedMod() {
        return getPercentageValue("redMod");
    }

    int getRedOff() {
        return getPercentageValue("redOff");
    }

    int getGreen() {
        return getPercentageValue("green");
    }

    int getGreenMod() {
        return getPercentageValue("greenMod");
    }

    int getGreenOff() {
        return getPercentageValue("greenOff");
    }

    int getBlue() {
        return getPercentageValue("blue");
    }

    int getBlueMod() {
        return getPercentageValue("blueMod");
    }

    int getBlueOff() {
        return getPercentageValue("blueOff");
    }

    public int getShade() {
        return getPercentageValue("shade");
    }

    public int getTint() {
        return getPercentageValue("tint");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class XSLFColorStyle extends AbstractColorStyle {
        private final Color color;
        private final CTSchemeColor phClr;
        private final XmlObject xmlObject;

        XSLFColorStyle(XmlObject xmlObject, Color color, CTSchemeColor phClr) {
            this.xmlObject = xmlObject;
            this.color = color;
            this.phClr = phClr;
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public Color getColor() {
            return this.color;
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getAlpha() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "alpha");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getHueOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueOff");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getHueMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueMod");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getSatOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satOff");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getSatMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satMod");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getLumOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumOff");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getLumMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumMod");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getShade() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "shade");
        }

        @Override // org.apache.poi.sl.usermodel.ColorStyle
        public int getTint() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "tint");
        }
    }
}
