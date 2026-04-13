package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

/* loaded from: classes10.dex */
public enum AxisPosition {
    BOTTOM(STAxPos.B),
    LEFT(STAxPos.L),
    RIGHT(STAxPos.R),
    TOP(STAxPos.T);

    private static final HashMap<STAxPos.Enum, AxisPosition> reverse = new HashMap<>();
    final STAxPos.Enum underlying;

    static {
        for (AxisPosition value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisPosition(STAxPos.Enum position) {
        this.underlying = position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisPosition valueOf(STAxPos.Enum position) {
        return reverse.get(position);
    }
}
