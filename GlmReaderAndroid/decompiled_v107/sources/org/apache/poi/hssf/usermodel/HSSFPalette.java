package org.apache.poi.hssf.usermodel;

import java.util.Locale;
import kotlin.UByte;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class HSSFPalette {
    private PaletteRecord _palette;

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFPalette(PaletteRecord palette) {
        this._palette = palette;
    }

    public HSSFColor getColor(short index) {
        if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) {
            return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
        }
        byte[] b = this._palette.getColor(index);
        if (b == null) {
            return null;
        }
        return new CustomColor(index, b);
    }

    public HSSFColor getColor(int index) {
        return getColor((short) index);
    }

    public HSSFColor findColor(byte red, byte green, byte blue) {
        byte[] b = this._palette.getColor(8);
        short i = 8;
        while (b != null) {
            if (b[0] != red || b[1] != green || b[2] != blue) {
                i = (short) (i + 1);
                b = this._palette.getColor(i);
            } else {
                return new CustomColor(i, b);
            }
        }
        return null;
    }

    public HSSFColor findSimilarColor(byte red, byte green, byte blue) {
        return findSimilarColor(unsignedInt(red), unsignedInt(green), unsignedInt(blue));
    }

    public HSSFColor findSimilarColor(int red, int green, int blue) {
        HSSFColor result = null;
        int minColorDistance = Integer.MAX_VALUE;
        byte[] b = this._palette.getColor(8);
        short i = 8;
        while (b != null) {
            int colorDistance = Math.abs(red - unsignedInt(b[0])) + Math.abs(green - unsignedInt(b[1])) + Math.abs(blue - unsignedInt(b[2]));
            if (colorDistance < minColorDistance) {
                minColorDistance = colorDistance;
                result = getColor(i);
            }
            i = (short) (i + 1);
            b = this._palette.getColor(i);
        }
        return result;
    }

    private int unsignedInt(byte b) {
        return b & UByte.MAX_VALUE;
    }

    public void setColorAtIndex(short index, byte red, byte green, byte blue) {
        this._palette.setColor(index, red, green, blue);
    }

    public HSSFColor addColor(byte red, byte green, byte blue) {
        byte[] b = this._palette.getColor(8);
        short i = 8;
        while (i < 64) {
            if (b != null) {
                i = (short) (i + 1);
                b = this._palette.getColor(i);
            } else {
                setColorAtIndex(i, red, green, blue);
                return getColor(i);
            }
        }
        throw new IllegalStateException("Could not find free color index");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CustomColor extends HSSFColor {
        private byte _blue;
        private short _byteOffset;
        private byte _green;
        private byte _red;

        public CustomColor(short byteOffset, byte[] colors) {
            this(byteOffset, colors[0], colors[1], colors[2]);
        }

        private CustomColor(short byteOffset, byte red, byte green, byte blue) {
            this._byteOffset = byteOffset;
            this._red = red;
            this._green = green;
            this._blue = blue;
        }

        @Override // org.apache.poi.hssf.util.HSSFColor
        public short getIndex() {
            return this._byteOffset;
        }

        @Override // org.apache.poi.hssf.util.HSSFColor
        public short[] getTriplet() {
            return new short[]{(short) (this._red & UByte.MAX_VALUE), (short) (this._green & UByte.MAX_VALUE), (short) (this._blue & UByte.MAX_VALUE)};
        }

        @Override // org.apache.poi.hssf.util.HSSFColor
        public String getHexString() {
            return getGnumericPart(this._red) + ":" + getGnumericPart(this._green) + ":" + getGnumericPart(this._blue);
        }

        private String getGnumericPart(byte color) {
            StringBuilder s;
            if (color == 0) {
                s = new StringBuilder("0");
            } else {
                int c = color & UByte.MAX_VALUE;
                StringBuilder s2 = new StringBuilder(Integer.toHexString(c | (c << 8)).toUpperCase(Locale.ROOT));
                int need0count = 4 - s2.length();
                if (need0count > 0) {
                    s2.insert(0, StringUtil.repeat('0', need0count));
                }
                s = s2;
            }
            return s.toString();
        }
    }
}
