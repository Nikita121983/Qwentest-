package org.apache.poi.xssf.usermodel.extensions;

import java.util.Objects;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

/* loaded from: classes10.dex */
public class XSSFCellBorder {
    private final IndexedColorMap _indexedColorMap;
    private ThemesTable _theme;
    private final CTBorder border;

    /* loaded from: classes10.dex */
    public enum BorderSide {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT,
        DIAGONAL,
        VERTICAL,
        HORIZONTAL
    }

    public XSSFCellBorder(CTBorder border, ThemesTable theme, IndexedColorMap colorMap) {
        this.border = border;
        this._indexedColorMap = colorMap;
        this._theme = theme;
    }

    public XSSFCellBorder(CTBorder border) {
        this(border, null, null);
    }

    public XSSFCellBorder(CTBorder border, IndexedColorMap colorMap) {
        this(border, null, colorMap);
    }

    public XSSFCellBorder() {
        this(CTBorder.Factory.newInstance(), null, null);
    }

    public void setThemesTable(ThemesTable themes) {
        this._theme = themes;
    }

    @Internal
    public CTBorder getCTBorder() {
        return this.border;
    }

    public BorderStyle getBorderStyle(BorderSide side) {
        CTBorderPr ctBorder = getBorder(side);
        STBorderStyle.Enum border = ctBorder == null ? STBorderStyle.NONE : ctBorder.getStyle();
        return BorderStyle.values()[border.intValue() - 1];
    }

    public void setBorderStyle(BorderSide side, BorderStyle style) {
        getBorder(side, true).setStyle(STBorderStyle.Enum.forInt(style.ordinal() + 1));
    }

    public XSSFColor getBorderColor(BorderSide side) {
        CTBorderPr borderPr = getBorder(side);
        if (borderPr != null && borderPr.isSetColor()) {
            XSSFColor clr = XSSFColor.from(borderPr.getColor(), this._indexedColorMap);
            if (this._theme != null) {
                this._theme.inheritFromThemeAsRequired(clr);
            }
            return clr;
        }
        return null;
    }

    public void setBorderColor(BorderSide side, XSSFColor color) {
        CTBorderPr borderPr = getBorder(side, true);
        if (color != null) {
            borderPr.setColor(color.getCTColor());
        } else {
            borderPr.unsetColor();
        }
    }

    private CTBorderPr getBorder(BorderSide side) {
        return getBorder(side, false);
    }

    private CTBorderPr getBorder(BorderSide side, boolean ensure) {
        switch (side) {
            case TOP:
                CTBorderPr borderPr = this.border.getTop();
                return (ensure && borderPr == null) ? this.border.addNewTop() : borderPr;
            case RIGHT:
                CTBorderPr borderPr2 = this.border.getRight();
                return (ensure && borderPr2 == null) ? this.border.addNewRight() : borderPr2;
            case BOTTOM:
                CTBorderPr borderPr3 = this.border.getBottom();
                return (ensure && borderPr3 == null) ? this.border.addNewBottom() : borderPr3;
            case LEFT:
                CTBorderPr borderPr4 = this.border.getLeft();
                return (ensure && borderPr4 == null) ? this.border.addNewLeft() : borderPr4;
            case DIAGONAL:
                CTBorderPr borderPr5 = this.border.getDiagonal();
                return (ensure && borderPr5 == null) ? this.border.addNewDiagonal() : borderPr5;
            case VERTICAL:
                CTBorderPr borderPr6 = this.border.getVertical();
                return (ensure && borderPr6 == null) ? this.border.addNewVertical() : borderPr6;
            case HORIZONTAL:
                CTBorderPr borderPr7 = this.border.getHorizontal();
                return (ensure && borderPr7 == null) ? this.border.addNewHorizontal() : borderPr7;
            default:
                throw new IllegalArgumentException("No suitable side specified for the border, had " + side);
        }
    }

    public int hashCode() {
        return this.border.toString().hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof XSSFCellBorder)) {
            return false;
        }
        XSSFCellBorder cf = (XSSFCellBorder) o;
        boolean equal = true;
        for (BorderSide side : BorderSide.values()) {
            if (!Objects.equals(getBorderColor(side), cf.getBorderColor(side)) || !Objects.equals(getBorderStyle(side), cf.getBorderStyle(side))) {
                equal = false;
                break;
            }
        }
        if (!equal || this.border.isSetDiagonalUp() != cf.border.isSetDiagonalUp() || this.border.isSetDiagonalDown() != cf.border.isSetDiagonalDown() || this.border.isSetOutline() != cf.border.isSetOutline()) {
            return false;
        }
        if (this.border.isSetDiagonalUp() && this.border.getDiagonalUp() != cf.border.getDiagonalUp()) {
            return false;
        }
        if (!this.border.isSetDiagonalDown() || this.border.getDiagonalDown() == cf.border.getDiagonalDown()) {
            return !this.border.isSetOutline() || this.border.getOutline() == cf.border.getOutline();
        }
        return false;
    }
}
