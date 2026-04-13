package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

/* loaded from: classes10.dex */
public enum AxisTickMark {
    CROSS(STTickMark.CROSS),
    IN(STTickMark.IN),
    NONE(STTickMark.NONE),
    OUT(STTickMark.OUT);

    private static final HashMap<STTickMark.Enum, AxisTickMark> reverse = new HashMap<>();
    final STTickMark.Enum underlying;

    static {
        for (AxisTickMark value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisTickMark(STTickMark.Enum tickMark) {
        this.underlying = tickMark;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisTickMark valueOf(STTickMark.Enum tickMark) {
        return reverse.get(tickMark);
    }
}
