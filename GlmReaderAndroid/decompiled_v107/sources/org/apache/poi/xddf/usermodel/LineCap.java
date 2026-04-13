package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;

/* loaded from: classes10.dex */
public enum LineCap {
    FLAT(STLineCap.FLAT),
    ROUND(STLineCap.RND),
    SQUARE(STLineCap.SQ);

    private static final HashMap<STLineCap.Enum, LineCap> reverse = new HashMap<>();
    final STLineCap.Enum underlying;

    static {
        for (LineCap value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LineCap(STLineCap.Enum line) {
        this.underlying = line;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LineCap valueOf(STLineCap.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
