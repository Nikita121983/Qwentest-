package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STScatterStyle;

/* loaded from: classes10.dex */
public enum ScatterStyle {
    LINE(STScatterStyle.LINE),
    LINE_MARKER(STScatterStyle.LINE_MARKER),
    MARKER(STScatterStyle.MARKER),
    NONE(STScatterStyle.NONE),
    SMOOTH(STScatterStyle.SMOOTH),
    SMOOTH_MARKER(STScatterStyle.SMOOTH_MARKER);

    private static final HashMap<STScatterStyle.Enum, ScatterStyle> reverse = new HashMap<>();
    final STScatterStyle.Enum underlying;

    static {
        for (ScatterStyle value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    ScatterStyle(STScatterStyle.Enum style) {
        this.underlying = style;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScatterStyle valueOf(STScatterStyle.Enum style) {
        return reverse.get(style);
    }
}
