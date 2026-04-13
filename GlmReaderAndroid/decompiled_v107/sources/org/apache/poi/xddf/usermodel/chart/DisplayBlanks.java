package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

/* loaded from: classes10.dex */
public enum DisplayBlanks {
    GAP(STDispBlanksAs.GAP),
    SPAN(STDispBlanksAs.SPAN),
    ZERO(STDispBlanksAs.ZERO);

    private static final HashMap<STDispBlanksAs.Enum, DisplayBlanks> reverse = new HashMap<>();
    final STDispBlanksAs.Enum underlying;

    static {
        for (DisplayBlanks value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    DisplayBlanks(STDispBlanksAs.Enum mode) {
        this.underlying = mode;
    }

    static DisplayBlanks valueOf(STDispBlanksAs.Enum mode) {
        return reverse.get(mode);
    }
}
