package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;

/* loaded from: classes10.dex */
public enum AxisCrosses {
    AUTO_ZERO(STCrosses.AUTO_ZERO),
    MAX(STCrosses.MAX),
    MIN(STCrosses.MIN);

    private static final HashMap<STCrosses.Enum, AxisCrosses> reverse = new HashMap<>();
    final STCrosses.Enum underlying;

    static {
        for (AxisCrosses value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisCrosses(STCrosses.Enum crosses) {
        this.underlying = crosses;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisCrosses valueOf(STCrosses.Enum crosses) {
        return reverse.get(crosses);
    }
}
