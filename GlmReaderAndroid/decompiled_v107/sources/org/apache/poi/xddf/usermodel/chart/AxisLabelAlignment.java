package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

/* loaded from: classes10.dex */
public enum AxisLabelAlignment {
    CENTER(STLblAlgn.CTR),
    LEFT(STLblAlgn.L),
    RIGHT(STLblAlgn.R);

    private static final HashMap<STLblAlgn.Enum, AxisLabelAlignment> reverse = new HashMap<>();
    final STLblAlgn.Enum underlying;

    static {
        for (AxisLabelAlignment value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    AxisLabelAlignment(STLblAlgn.Enum alignment) {
        this.underlying = alignment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AxisLabelAlignment valueOf(STLblAlgn.Enum alignment) {
        return reverse.get(alignment);
    }
}
