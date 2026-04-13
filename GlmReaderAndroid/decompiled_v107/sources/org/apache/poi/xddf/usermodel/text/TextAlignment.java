package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;

/* loaded from: classes10.dex */
public enum TextAlignment {
    CENTER(STTextAlignType.CTR),
    DISTRIBUTED(STTextAlignType.DIST),
    JUSTIFIED(STTextAlignType.JUST),
    JUSTIFIED_LOW(STTextAlignType.JUST_LOW),
    LEFT(STTextAlignType.L),
    RIGHT(STTextAlignType.R),
    THAI_DISTRIBUTED(STTextAlignType.THAI_DIST);

    private static final HashMap<STTextAlignType.Enum, TextAlignment> reverse = new HashMap<>();
    final STTextAlignType.Enum underlying;

    static {
        for (TextAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    TextAlignment(STTextAlignType.Enum align) {
        this.underlying = align;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TextAlignment valueOf(STTextAlignType.Enum align) {
        return reverse.get(align);
    }
}
