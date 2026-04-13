package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

/* loaded from: classes10.dex */
public enum BlackWhiteMode {
    AUTO(STBlackWhiteMode.AUTO),
    BLACK(STBlackWhiteMode.BLACK),
    BLACK_GRAY(STBlackWhiteMode.BLACK_GRAY),
    BLACK_WHITE(STBlackWhiteMode.BLACK_WHITE);

    private static final HashMap<STBlackWhiteMode.Enum, BlackWhiteMode> reverse = new HashMap<>();
    final STBlackWhiteMode.Enum underlying;

    static {
        for (BlackWhiteMode value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    BlackWhiteMode(STBlackWhiteMode.Enum mode) {
        this.underlying = mode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BlackWhiteMode valueOf(STBlackWhiteMode.Enum mode) {
        return reverse.get(mode);
    }
}
