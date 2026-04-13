package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

/* loaded from: classes10.dex */
public enum TileFlipMode {
    NONE(STTileFlipMode.NONE),
    X(STTileFlipMode.X),
    XY(STTileFlipMode.XY),
    Y(STTileFlipMode.Y);

    private static final HashMap<STTileFlipMode.Enum, TileFlipMode> reverse = new HashMap<>();
    final STTileFlipMode.Enum underlying;

    static {
        for (TileFlipMode value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    TileFlipMode(STTileFlipMode.Enum mode) {
        this.underlying = mode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TileFlipMode valueOf(STTileFlipMode.Enum mode) {
        return reverse.get(mode);
    }
}
