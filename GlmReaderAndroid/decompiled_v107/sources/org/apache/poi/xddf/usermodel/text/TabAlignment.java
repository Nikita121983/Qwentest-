package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

/* loaded from: classes10.dex */
public enum TabAlignment {
    CENTER(STTextTabAlignType.CTR),
    DECIMAL(STTextTabAlignType.DEC),
    LEFT(STTextTabAlignType.L),
    RIGHT(STTextTabAlignType.R);

    private static final HashMap<STTextTabAlignType.Enum, TabAlignment> reverse = new HashMap<>();
    final STTextTabAlignType.Enum underlying;

    static {
        for (TabAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    TabAlignment(STTextTabAlignType.Enum align) {
        this.underlying = align;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TabAlignment valueOf(STTextTabAlignType.Enum align) {
        return reverse.get(align);
    }
}
