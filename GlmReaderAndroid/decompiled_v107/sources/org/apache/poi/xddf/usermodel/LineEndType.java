package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;

/* loaded from: classes10.dex */
public enum LineEndType {
    ARROW(STLineEndType.ARROW),
    DIAMOND(STLineEndType.DIAMOND),
    NONE(STLineEndType.NONE),
    OVAL(STLineEndType.OVAL),
    STEALTH(STLineEndType.STEALTH),
    TRIANGLE(STLineEndType.TRIANGLE);

    private static final HashMap<STLineEndType.Enum, LineEndType> reverse = new HashMap<>();
    final STLineEndType.Enum underlying;

    static {
        for (LineEndType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LineEndType(STLineEndType.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LineEndType valueOf(STLineEndType.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
