package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;

/* loaded from: classes10.dex */
public enum LayoutMode {
    EDGE(STLayoutMode.EDGE),
    FACTOR(STLayoutMode.FACTOR);

    private static final HashMap<STLayoutMode.Enum, LayoutMode> reverse = new HashMap<>();
    final STLayoutMode.Enum underlying;

    static {
        for (LayoutMode value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    LayoutMode(STLayoutMode.Enum layoutMode) {
        this.underlying = layoutMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LayoutMode valueOf(STLayoutMode.Enum layoutMode) {
        return reverse.get(layoutMode);
    }
}
