package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

/* loaded from: classes10.dex */
public enum AxisTickLabelPosition {
    HIGH(STTickLblPos.HIGH),
    LOW(STTickLblPos.LOW),
    NEXT_TO(STTickLblPos.NEXT_TO),
    NONE(STTickLblPos.NONE);

    private static final HashMap<STTickLblPos.Enum, AxisTickLabelPosition> reverse = new HashMap<>();
    final STTickLblPos.Enum underlying;

    static {
        for (AxisTickLabelPosition value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisTickLabelPosition(STTickLblPos.Enum position) {
        this.underlying = position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisTickLabelPosition valueOf(STTickLblPos.Enum position) {
        return reverse.get(position);
    }
}
