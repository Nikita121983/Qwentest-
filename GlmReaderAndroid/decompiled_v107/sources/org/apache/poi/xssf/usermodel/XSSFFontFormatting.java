package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

/* loaded from: classes10.dex */
public class XSSFFontFormatting implements FontFormatting {
    private IndexedColorMap _colorMap;
    private CTFont _font;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFFontFormatting(CTFont font, IndexedColorMap colorMap) {
        this._font = font;
        this._colorMap = colorMap;
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public short getEscapementType() {
        if (this._font.sizeOfVertAlignArray() == 0) {
            return (short) 0;
        }
        CTVerticalAlignFontProperty prop = this._font.getVertAlignArray(0);
        return (short) (prop.getVal().intValue() - 1);
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setEscapementType(short escapementType) {
        this._font.setVertAlignArray(null);
        if (escapementType != 0) {
            this._font.addNewVertAlign().setVal(STVerticalAlignRun.Enum.forInt(escapementType + 1));
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public boolean isStruckout() {
        return this._font.sizeOfStrikeArray() > 0 && this._font.getStrikeArray(0).getVal();
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public short getFontColorIndex() {
        if (this._font.sizeOfColorArray() == 0) {
            return (short) -1;
        }
        CTColor color = this._font.getColorArray(0);
        int idx = color.isSetIndexed() ? (int) color.getIndexed() : 0;
        return (short) idx;
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setFontColorIndex(short color) {
        this._font.setColorArray(null);
        if (color != -1) {
            this._font.addNewColor().setIndexed(color);
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public XSSFColor getFontColor() {
        if (this._font.sizeOfColorArray() == 0) {
            return null;
        }
        return XSSFColor.from(this._font.getColorArray(0), this._colorMap);
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setFontColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this._font.getColorList().clear();
        } else if (this._font.sizeOfColorArray() == 0) {
            this._font.addNewColor().setRgb(xcolor.getRGB());
        } else {
            this._font.setColorArray(0, xcolor.getCTColor());
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public int getFontHeight() {
        if (this._font.sizeOfSzArray() == 0) {
            return -1;
        }
        CTFontSize sz = this._font.getSzArray(0);
        return (int) (sz.getVal() * 20.0d);
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setFontHeight(int height) {
        this._font.setSzArray(null);
        if (height != -1) {
            this._font.addNewSz().setVal(height / 20.0d);
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public short getUnderlineType() {
        if (this._font.sizeOfUArray() == 0) {
            return (short) 0;
        }
        CTUnderlineProperty u = this._font.getUArray(0);
        switch (u.getVal().intValue()) {
            case 1:
                return (short) 1;
            case 2:
                return (short) 2;
            case 3:
                return (short) 33;
            case 4:
                return (short) 34;
            default:
                return (short) 0;
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setUnderlineType(short underlineType) {
        this._font.setUArray(null);
        if (underlineType != 0) {
            FontUnderline fenum = FontUnderline.valueOf(underlineType);
            STUnderlineValues.Enum val = STUnderlineValues.Enum.forInt(fenum.getValue());
            this._font.addNewU().setVal(val);
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public boolean isBold() {
        return this._font.sizeOfBArray() == 1 && this._font.getBArray(0).getVal();
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public boolean isItalic() {
        return this._font.sizeOfIArray() == 1 && this._font.getIArray(0).getVal();
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void setFontStyle(boolean italic, boolean bold) {
        this._font.setIArray(null);
        this._font.setBArray(null);
        if (italic) {
            this._font.addNewI().setVal(true);
        }
        if (bold) {
            this._font.addNewB().setVal(true);
        }
    }

    @Override // org.apache.poi.ss.usermodel.FontFormatting
    public void resetFontStyle() {
        this._font.set(CTFont.Factory.newInstance());
    }
}
