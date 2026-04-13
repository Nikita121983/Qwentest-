package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

/* loaded from: classes10.dex */
public enum RadarStyle {
    FILLED(STRadarStyle.FILLED),
    MARKER(STRadarStyle.MARKER),
    STANDARD(STRadarStyle.STANDARD);

    private static final HashMap<STRadarStyle.Enum, RadarStyle> reverse = new HashMap<>();
    final STRadarStyle.Enum underlying;

    static {
        for (RadarStyle value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    RadarStyle(STRadarStyle.Enum style) {
        this.underlying = style;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RadarStyle valueOf(STRadarStyle.Enum style) {
        return reverse.get(style);
    }
}
