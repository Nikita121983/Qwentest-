package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;

/* loaded from: classes10.dex */
public enum AnchorType {
    BOTTOM(STTextAnchoringType.B),
    CENTER(STTextAnchoringType.CTR),
    DISTRIBUTED(STTextAnchoringType.DIST),
    JUSTIFIED(STTextAnchoringType.JUST),
    TOP(STTextAnchoringType.T);

    private static final HashMap<STTextAnchoringType.Enum, AnchorType> reverse = new HashMap<>();
    final STTextAnchoringType.Enum underlying;

    static {
        for (AnchorType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AnchorType(STTextAnchoringType.Enum caps) {
        this.underlying = caps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnchorType valueOf(STTextAnchoringType.Enum caps) {
        return reverse.get(caps);
    }
}
