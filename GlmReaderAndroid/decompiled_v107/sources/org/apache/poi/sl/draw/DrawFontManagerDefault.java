package org.apache.poi.sl.draw;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntUnaryOperator;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class DrawFontManagerDefault implements DrawFontManager {
    protected final Set<String> knownSymbolFonts = new TreeSet(String.CASE_INSENSITIVE_ORDER);

    public DrawFontManagerDefault() {
        this.knownSymbolFonts.add("Wingdings");
        this.knownSymbolFonts.add("Symbol");
    }

    @Override // org.apache.poi.sl.draw.DrawFontManager
    public FontInfo getMappedFont(Graphics2D graphics, FontInfo fontInfo) {
        return getFontWithFallback(graphics, Drawable.FONT_MAP, fontInfo);
    }

    @Override // org.apache.poi.sl.draw.DrawFontManager
    public FontInfo getFallbackFont(Graphics2D graphics, FontInfo fontInfo) {
        FontInfo fi = getFontWithFallback(graphics, Drawable.FONT_FALLBACK, fontInfo);
        if (fi == null) {
            return new DrawFontInfo("SansSerif");
        }
        return fi;
    }

    @Override // org.apache.poi.sl.draw.DrawFontManager
    public String mapFontCharset(Graphics2D graphics, FontInfo fontInfo, String text) {
        if (fontInfo == null || text == null || text.isEmpty()) {
            return text;
        }
        String typeface = fontInfo.getTypeface();
        if (fontInfo.getCharset() == FontCharset.SYMBOL || this.knownSymbolFonts.contains(typeface)) {
            int[] cps = text.codePoints().map(new IntUnaryOperator() { // from class: org.apache.poi.sl.draw.DrawFontManagerDefault$$ExternalSyntheticLambda0
                @Override // java.util.function.IntUnaryOperator
                public final int applyAsInt(int i) {
                    int mapSymbolChar;
                    mapSymbolChar = DrawFontManagerDefault.mapSymbolChar(i);
                    return mapSymbolChar;
                }
            }).toArray();
            String ret = new String(cps, 0, cps.length);
            String[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            boolean hasFont = Arrays.asList(allFonts).contains(typeface);
            return hasFont ? ret : StringUtil.mapMsCodepointString(ret);
        }
        return text;
    }

    @Override // org.apache.poi.sl.draw.DrawFontManager
    public Font createAWTFont(Graphics2D graphics2D, FontInfo fontInfo, double d, boolean z, boolean z2) {
        int i = (z2 ? 2 : 0) | (z ? 1 : 0);
        Font font = new Font(fontInfo.getTypeface(), i, 12);
        if ("Dialog".equals(font.getFamily())) {
            font = new Font("SansSerif", i, 12);
        }
        return font.deriveFont((float) d);
    }

    private FontInfo getFontWithFallback(Graphics2D graphics, Drawable.DrawableHint hint, FontInfo fontInfo) {
        Map<String, String> fontMap = (Map) graphics.getRenderingHint(hint);
        if (fontMap == null) {
            return fontInfo;
        }
        String f = fontInfo != null ? fontInfo.getTypeface() : null;
        String mappedTypeface = null;
        if (fontMap.containsKey(f)) {
            String mappedTypeface2 = fontMap.get(f);
            mappedTypeface = mappedTypeface2;
        } else if (fontMap.containsKey("*")) {
            String mappedTypeface3 = fontMap.get("*");
            mappedTypeface = mappedTypeface3;
        }
        return mappedTypeface != null ? new DrawFontInfo(mappedTypeface) : fontInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mapSymbolChar(int cp) {
        return ((32 > cp || cp > 127) && (160 > cp || cp > 255)) ? cp : 61440 | cp;
    }
}
