package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;

/* loaded from: classes10.dex */
public enum AxisCrossBetween {
    BETWEEN(STCrossBetween.BETWEEN),
    MIDPOINT_CATEGORY(STCrossBetween.MID_CAT);

    private static final HashMap<STCrossBetween.Enum, AxisCrossBetween> reverse = new HashMap<>();
    final STCrossBetween.Enum underlying;

    static {
        for (AxisCrossBetween value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisCrossBetween(STCrossBetween.Enum crossBetween) {
        this.underlying = crossBetween;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisCrossBetween valueOf(STCrossBetween.Enum crossBetween) {
        return reverse.get(crossBetween);
    }
}
