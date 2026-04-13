package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;

/* loaded from: classes10.dex */
public enum FontAlignment {
    AUTOMATIC(STTextFontAlignType.AUTO),
    BOTTOM(STTextFontAlignType.B),
    BASELINE(STTextFontAlignType.BASE),
    CENTER(STTextFontAlignType.CTR),
    TOP(STTextFontAlignType.T);

    private static final HashMap<STTextFontAlignType.Enum, FontAlignment> reverse = new HashMap<>();
    final STTextFontAlignType.Enum underlying;

    static {
        for (FontAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    FontAlignment(STTextFontAlignType.Enum align) {
        this.underlying = align;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FontAlignment valueOf(STTextFontAlignType.Enum align) {
        return reverse.get(align);
    }
}
