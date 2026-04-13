package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;

/* loaded from: classes10.dex */
public enum SchemeColor {
    ACCENT_1(STSchemeColorVal.ACCENT_1),
    ACCENT_2(STSchemeColorVal.ACCENT_2),
    ACCENT_3(STSchemeColorVal.ACCENT_3),
    ACCENT_4(STSchemeColorVal.ACCENT_4),
    ACCENT_5(STSchemeColorVal.ACCENT_5),
    ACCENT_6(STSchemeColorVal.ACCENT_6),
    BACKGROUND_1(STSchemeColorVal.BG_1),
    BACKGROUND_2(STSchemeColorVal.BG_2),
    DARK_1(STSchemeColorVal.DK_1),
    DARK_2(STSchemeColorVal.DK_2),
    FOLLOWED_LINK(STSchemeColorVal.FOL_HLINK),
    LINK(STSchemeColorVal.HLINK),
    LIGHT_1(STSchemeColorVal.LT_1),
    LIGHT_2(STSchemeColorVal.LT_2),
    PLACEHOLDER(STSchemeColorVal.PH_CLR),
    TEXT_1(STSchemeColorVal.TX_1),
    TEXT_2(STSchemeColorVal.TX_2);

    private static final HashMap<STSchemeColorVal.Enum, SchemeColor> reverse = new HashMap<>();
    final STSchemeColorVal.Enum underlying;

    static {
        for (SchemeColor value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    SchemeColor(STSchemeColorVal.Enum color) {
        this.underlying = color;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SchemeColor valueOf(STSchemeColorVal.Enum color) {
        return reverse.get(color);
    }
}
