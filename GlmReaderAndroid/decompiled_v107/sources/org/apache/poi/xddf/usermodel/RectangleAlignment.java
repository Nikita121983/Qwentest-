package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;

/* loaded from: classes10.dex */
public enum RectangleAlignment {
    BOTTOM(STRectAlignment.B),
    BOTTOM_LEFT(STRectAlignment.BL),
    BOTTOM_RIGHT(STRectAlignment.BR),
    CENTER(STRectAlignment.CTR),
    LEFT(STRectAlignment.L),
    RIGHT(STRectAlignment.R),
    TOP(STRectAlignment.T),
    TOP_LEFT(STRectAlignment.TL),
    TOP_RIGHT(STRectAlignment.TR);

    private static final HashMap<STRectAlignment.Enum, RectangleAlignment> reverse = new HashMap<>();
    final STRectAlignment.Enum underlying;

    static {
        for (RectangleAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    RectangleAlignment(STRectAlignment.Enum alignment) {
        this.underlying = alignment;
    }

    static RectangleAlignment valueOf(STRectAlignment.Enum alignment) {
        return reverse.get(alignment);
    }
}
