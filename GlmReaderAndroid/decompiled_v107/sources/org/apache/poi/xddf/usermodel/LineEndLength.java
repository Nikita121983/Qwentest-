package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;

/* loaded from: classes10.dex */
public enum LineEndLength {
    LARGE(STLineEndLength.LG),
    MEDIUM(STLineEndLength.MED),
    SMALL(STLineEndLength.SM);

    private static final HashMap<STLineEndLength.Enum, LineEndLength> reverse = new HashMap<>();
    final STLineEndLength.Enum underlying;

    static {
        for (LineEndLength value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LineEndLength(STLineEndLength.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LineEndLength valueOf(STLineEndLength.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
