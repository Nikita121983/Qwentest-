package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;

/* loaded from: classes10.dex */
public enum PresetLineDash {
    DASH(STPresetLineDashVal.DASH),
    DASH_DOT(STPresetLineDashVal.DASH_DOT),
    DOT(STPresetLineDashVal.DOT),
    LARGE_DASH(STPresetLineDashVal.LG_DASH),
    LARGE_DASH_DOT(STPresetLineDashVal.LG_DASH_DOT),
    LARGE_DASH_DOT_DOT(STPresetLineDashVal.LG_DASH_DOT_DOT),
    SOLID(STPresetLineDashVal.SOLID),
    SYSTEM_DASH(STPresetLineDashVal.SYS_DASH),
    SYSTEM_DASH_DOT(STPresetLineDashVal.SYS_DASH_DOT),
    SYSTEM_DASH_DOT_DOT(STPresetLineDashVal.SYS_DASH_DOT_DOT),
    SYSTEM_DOT(STPresetLineDashVal.SYS_DOT);

    private static final HashMap<STPresetLineDashVal.Enum, PresetLineDash> reverse = new HashMap<>();
    final STPresetLineDashVal.Enum underlying;

    static {
        for (PresetLineDash value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    PresetLineDash(STPresetLineDashVal.Enum dash) {
        this.underlying = dash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PresetLineDash valueOf(STPresetLineDashVal.Enum dash) {
        return reverse.get(dash);
    }
}
