package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

/* loaded from: classes10.dex */
public enum PathShadeType {
    CIRCLE(STPathShadeType.CIRCLE),
    RECTANGLE(STPathShadeType.RECT),
    SHAPE(STPathShadeType.SHAPE);

    private static final HashMap<STPathShadeType.Enum, PathShadeType> reverse = new HashMap<>();
    final STPathShadeType.Enum underlying;

    static {
        for (PathShadeType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    PathShadeType(STPathShadeType.Enum pathShadeType) {
        this.underlying = pathShadeType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PathShadeType valueOf(STPathShadeType.Enum pathShadeType) {
        return reverse.get(pathShadeType);
    }
}
