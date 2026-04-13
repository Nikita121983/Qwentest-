package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;

/* loaded from: classes10.dex */
public enum BarDirection {
    BAR(STBarDir.BAR),
    COL(STBarDir.COL);

    private static final HashMap<STBarDir.Enum, BarDirection> reverse = new HashMap<>();
    final STBarDir.Enum underlying;

    static {
        for (BarDirection value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    BarDirection(STBarDir.Enum direction) {
        this.underlying = direction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BarDirection valueOf(STBarDir.Enum direction) {
        return reverse.get(direction);
    }
}
