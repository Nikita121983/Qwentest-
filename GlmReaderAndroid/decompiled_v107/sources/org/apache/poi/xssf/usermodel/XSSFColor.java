package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

/* loaded from: classes10.dex */
public class XSSFColor extends ExtendedColor {
    private final CTColor ctColor;
    private final IndexedColorMap indexedColorMap;

    public static XSSFColor from(CTColor color, IndexedColorMap map) {
        if (color == null) {
            return null;
        }
        return new XSSFColor(color, map);
    }

    public static XSSFColor from(CTColor color) {
        if (color == null) {
            return null;
        }
        return new XSSFColor(color, (IndexedColorMap) null);
    }

    private XSSFColor(CTColor color, IndexedColorMap map) {
        this.ctColor = color;
        this.indexedColorMap = map;
    }

    public XSSFColor() {
        this(CTColor.Factory.newInstance(), (IndexedColorMap) null);
    }

    public XSSFColor(IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
    }

    public XSSFColor(Color clr, IndexedColorMap map) {
        this(map);
        setColor(clr);
    }

    public XSSFColor(byte[] rgb, IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
        this.ctColor.setRgb(rgb);
    }

    public XSSFColor(byte[] rgb) {
        this(rgb, (IndexedColorMap) null);
    }

    public XSSFColor(IndexedColors indexedColor, IndexedColorMap colorMap) {
        this(CTColor.Factory.newInstance(), colorMap);
        this.ctColor.setIndexed(indexedColor.index);
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public boolean isAuto() {
        return this.ctColor.getAuto();
    }

    public void setAuto(boolean auto) {
        this.ctColor.setAuto(auto);
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public boolean isIndexed() {
        return this.ctColor.isSetIndexed();
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public boolean isRGB() {
        return this.ctColor.isSetRgb();
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public boolean isThemed() {
        return this.ctColor.isSetTheme();
    }

    public boolean hasAlpha() {
        return this.ctColor.isSetRgb() && this.ctColor.getRgb().length == 4;
    }

    public boolean hasTint() {
        return this.ctColor.isSetTint() && this.ctColor.getTint() != 0.0d;
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public short getIndex() {
        return (short) this.ctColor.getIndexed();
    }

    public short getIndexed() {
        return getIndex();
    }

    public void setIndexed(int indexed) {
        this.ctColor.setIndexed(indexed);
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public byte[] getRGB() {
        byte[] rgb = getRGBOrARGB();
        if (rgb == null) {
            return null;
        }
        return rgb.length == 4 ? Arrays.copyOfRange(rgb, 1, 4) : rgb;
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public byte[] getARGB() {
        byte[] rgb = getRGBOrARGB();
        if (rgb == null) {
            return null;
        }
        if (rgb.length == 3) {
            byte[] tmp = new byte[4];
            tmp[0] = -1;
            System.arraycopy(rgb, 0, tmp, 1, 3);
            return tmp;
        }
        return rgb;
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    protected byte[] getStoredRGB() {
        return this.ctColor.getRgb();
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    protected byte[] getIndexedRGB() {
        if (isIndexed()) {
            return this.indexedColorMap != null ? this.indexedColorMap.getRGB(getIndex()) : DefaultIndexedColorMap.getDefaultRGB(getIndex());
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public void setRGB(byte[] rgb) {
        this.ctColor.setRgb(rgb);
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public int getTheme() {
        return (int) this.ctColor.getTheme();
    }

    public void setTheme(int theme) {
        this.ctColor.setTheme(theme);
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public double getTint() {
        return this.ctColor.getTint();
    }

    @Override // org.apache.poi.ss.usermodel.ExtendedColor
    public void setTint(double tint) {
        this.ctColor.setTint(tint);
    }

    @Internal
    public CTColor getCTColor() {
        return this.ctColor;
    }

    public static XSSFColor toXSSFColor(org.apache.poi.ss.usermodel.Color color) {
        if (color != null && !(color instanceof XSSFColor)) {
            throw new IllegalArgumentException("Only XSSFColor objects are supported, but had " + color.getClass());
        }
        return (XSSFColor) color;
    }

    public int hashCode() {
        return this.ctColor.toString().hashCode();
    }

    private boolean sameIndexed(XSSFColor other) {
        if (isIndexed() == other.isIndexed()) {
            return !isIndexed() || getIndexed() == other.getIndexed();
        }
        return false;
    }

    private boolean sameARGB(XSSFColor other) {
        if (isRGB() == other.isRGB()) {
            return !isRGB() || Arrays.equals(getARGB(), other.getARGB());
        }
        return false;
    }

    private boolean sameTheme(XSSFColor other) {
        if (isThemed() == other.isThemed()) {
            return !isThemed() || getTheme() == other.getTheme();
        }
        return false;
    }

    private boolean sameTint(XSSFColor other) {
        if (hasTint() == other.hasTint()) {
            return !hasTint() || getTint() == other.getTint();
        }
        return false;
    }

    private boolean sameAuto(XSSFColor other) {
        return isAuto() == other.isAuto();
    }

    public boolean equals(Object o) {
        if (!(o instanceof XSSFColor)) {
            return false;
        }
        XSSFColor other = (XSSFColor) o;
        return sameARGB(other) && sameTheme(other) && sameIndexed(other) && sameTint(other) && sameAuto(other);
    }
}
