package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;

/* loaded from: classes10.dex */
public enum StrikeType {
    DOUBLE_STRIKE(STTextStrikeType.DBL_STRIKE),
    NO_STRIKE(STTextStrikeType.NO_STRIKE),
    SINGLE_STRIKE(STTextStrikeType.SNG_STRIKE);

    private static final HashMap<STTextStrikeType.Enum, StrikeType> reverse = new HashMap<>();
    final STTextStrikeType.Enum underlying;

    static {
        for (StrikeType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    StrikeType(STTextStrikeType.Enum strike) {
        this.underlying = strike;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StrikeType valueOf(STTextStrikeType.Enum strike) {
        return reverse.get(strike);
    }
}
