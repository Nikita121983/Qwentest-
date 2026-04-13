package org.apache.poi.hssf.usermodel;

import androidx.core.view.InputDeviceCompat;
import java.util.Objects;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class HSSFFont implements Font {
    static final short BOLDWEIGHT_BOLD = 700;
    static final short BOLDWEIGHT_NORMAL = 400;
    public static final String FONT_ARIAL = "Arial";
    private final FontRecord font;
    private final int index;

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFFont(int index, FontRecord rec) {
        this.font = rec;
        this.index = index;
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setFontName(String name) {
        this.font.setFontName(name);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public String getFontName() {
        return this.font.getFontName();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public int getIndex() {
        return this.index;
    }

    @Override // org.apache.poi.ss.usermodel.Font
    @Removal(version = "6.0.0")
    @Deprecated
    public int getIndexAsInt() {
        return this.index;
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setFontHeight(short height) {
        this.font.setFontHeight(height);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setFontHeightInPoints(short height) {
        this.font.setFontHeight((short) (height * 20));
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public short getFontHeight() {
        return this.font.getFontHeight();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public short getFontHeightInPoints() {
        return (short) (this.font.getFontHeight() / 20);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setItalic(boolean italic) {
        this.font.setItalic(italic);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public boolean getItalic() {
        return this.font.isItalic();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setStrikeout(boolean strikeout) {
        this.font.setStrikeout(strikeout);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public boolean getStrikeout() {
        return this.font.isStruckout();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setColor(short color) {
        this.font.setColorPaletteIndex(color);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public short getColor() {
        return this.font.getColorPaletteIndex();
    }

    public HSSFColor getHSSFColor(HSSFWorkbook wb) {
        HSSFPalette pallette = wb.getCustomPalette();
        return pallette.getColor(getColor());
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setBold(boolean bold) {
        if (bold) {
            this.font.setBoldWeight(BOLDWEIGHT_BOLD);
        } else {
            this.font.setBoldWeight(BOLDWEIGHT_NORMAL);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public boolean getBold() {
        return this.font.getBoldWeight() == 700;
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setTypeOffset(short offset) {
        this.font.setSuperSubScript(offset);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public short getTypeOffset() {
        return this.font.getSuperSubScript();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setUnderline(byte underline) {
        this.font.setUnderline(underline);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public byte getUnderline() {
        return this.font.getUnderline();
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public int getCharSet() {
        byte charset = this.font.getCharset();
        if (charset >= 0) {
            return charset;
        }
        return charset + 256;
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setCharSet(int charset) {
        byte cs = (byte) charset;
        if (charset > 127) {
            cs = (byte) (charset + InputDeviceCompat.SOURCE_ANY);
        }
        setCharSet(cs);
    }

    @Override // org.apache.poi.ss.usermodel.Font
    public void setCharSet(byte charset) {
        this.font.setCharset(charset);
    }

    public String toString() {
        return "org.apache.poi.hssf.usermodel.HSSFFont{" + this.font + VectorFormat.DEFAULT_SUFFIX;
    }

    public int hashCode() {
        return Objects.hash(this.font, Integer.valueOf(this.index));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HSSFFont)) {
            return false;
        }
        HSSFFont other = (HSSFFont) obj;
        if (this.font == null) {
            if (other.font != null) {
                return false;
            }
        } else if (!this.font.equals(other.font)) {
            return false;
        }
        if (this.index == other.index) {
            return true;
        }
        return false;
    }
}
