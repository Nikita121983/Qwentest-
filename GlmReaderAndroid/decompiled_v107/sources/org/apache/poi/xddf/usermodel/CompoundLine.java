package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;

/* loaded from: classes10.dex */
public enum CompoundLine {
    DOUBLE(STCompoundLine.DBL),
    SINGLE(STCompoundLine.SNG),
    THICK_THIN(STCompoundLine.THICK_THIN),
    THIN_THICK(STCompoundLine.THIN_THICK),
    TRIPLE(STCompoundLine.TRI);

    private static final HashMap<STCompoundLine.Enum, CompoundLine> reverse = new HashMap<>();
    final STCompoundLine.Enum underlying;

    static {
        for (CompoundLine value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    CompoundLine(STCompoundLine.Enum line) {
        this.underlying = line;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CompoundLine valueOf(STCompoundLine.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
