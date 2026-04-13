package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

/* loaded from: classes10.dex */
public enum LineEndWidth {
    LARGE(STLineEndWidth.LG),
    MEDIUM(STLineEndWidth.MED),
    SMALL(STLineEndWidth.SM);

    private static final HashMap<STLineEndWidth.Enum, LineEndWidth> reverse = new HashMap<>();
    final STLineEndWidth.Enum underlying;

    static {
        for (LineEndWidth value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LineEndWidth(STLineEndWidth.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LineEndWidth valueOf(STLineEndWidth.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
