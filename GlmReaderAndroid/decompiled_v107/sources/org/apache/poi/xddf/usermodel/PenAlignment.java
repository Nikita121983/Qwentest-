package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

/* loaded from: classes10.dex */
public enum PenAlignment {
    CENTER(STPenAlignment.CTR),
    IN(STPenAlignment.IN);

    private static final HashMap<STPenAlignment.Enum, PenAlignment> reverse = new HashMap<>();
    final STPenAlignment.Enum underlying;

    static {
        for (PenAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    PenAlignment(STPenAlignment.Enum alignment) {
        this.underlying = alignment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PenAlignment valueOf(STPenAlignment.Enum LineEndWidth) {
        return reverse.get(LineEndWidth);
    }
}
