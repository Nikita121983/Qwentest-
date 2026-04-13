package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

/* loaded from: classes10.dex */
public enum LegendPosition {
    BOTTOM(STLegendPos.B),
    LEFT(STLegendPos.L),
    RIGHT(STLegendPos.R),
    TOP(STLegendPos.T),
    TOP_RIGHT(STLegendPos.TR);

    private static final HashMap<STLegendPos.Enum, LegendPosition> reverse = new HashMap<>();
    final STLegendPos.Enum underlying;

    static {
        for (LegendPosition value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LegendPosition(STLegendPos.Enum position) {
        this.underlying = position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LegendPosition valueOf(STLegendPos.Enum position) {
        return reverse.get(position);
    }
}
