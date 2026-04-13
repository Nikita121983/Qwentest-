package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

/* loaded from: classes10.dex */
public enum Grouping {
    STANDARD(STGrouping.STANDARD),
    STACKED(STGrouping.STACKED),
    PERCENT_STACKED(STGrouping.PERCENT_STACKED);

    private static final HashMap<STGrouping.Enum, Grouping> reverse = new HashMap<>();
    final STGrouping.Enum underlying;

    static {
        for (Grouping value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    Grouping(STGrouping.Enum grouping) {
        this.underlying = grouping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Grouping valueOf(STGrouping.Enum grouping) {
        return reverse.get(grouping);
    }
}
