package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

/* loaded from: classes10.dex */
public enum LayoutTarget {
    INNER(STLayoutTarget.INNER),
    OUTER(STLayoutTarget.OUTER);

    private static final HashMap<STLayoutTarget.Enum, LayoutTarget> reverse = new HashMap<>();
    final STLayoutTarget.Enum underlying;

    static {
        for (LayoutTarget value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LayoutTarget(STLayoutTarget.Enum layoutTarget) {
        this.underlying = layoutTarget;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LayoutTarget valueOf(STLayoutTarget.Enum layoutTarget) {
        return reverse.get(layoutTarget);
    }
}
