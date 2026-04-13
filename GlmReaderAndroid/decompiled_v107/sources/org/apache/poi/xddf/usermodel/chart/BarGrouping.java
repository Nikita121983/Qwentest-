package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

/* loaded from: classes10.dex */
public enum BarGrouping {
    STANDARD(STBarGrouping.STANDARD),
    CLUSTERED(STBarGrouping.CLUSTERED),
    STACKED(STBarGrouping.STACKED),
    PERCENT_STACKED(STBarGrouping.PERCENT_STACKED);

    private static final HashMap<STBarGrouping.Enum, BarGrouping> reverse = new HashMap<>();
    final STBarGrouping.Enum underlying;

    static {
        for (BarGrouping value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    BarGrouping(STBarGrouping.Enum grouping) {
        this.underlying = grouping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BarGrouping valueOf(STBarGrouping.Enum grouping) {
        return reverse.get(grouping);
    }
}
