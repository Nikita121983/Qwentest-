package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

/* loaded from: classes10.dex */
public enum AxisOrientation {
    MIN_MAX(STOrientation.MIN_MAX),
    MAX_MIN(STOrientation.MAX_MIN);

    private static final HashMap<STOrientation.Enum, AxisOrientation> reverse = new HashMap<>();
    final STOrientation.Enum underlying;

    static {
        for (AxisOrientation value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisOrientation(STOrientation.Enum orientation) {
        this.underlying = orientation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisOrientation valueOf(STOrientation.Enum orientation) {
        return reverse.get(orientation);
    }
}
