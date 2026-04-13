package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

/* loaded from: classes10.dex */
public enum MarkerStyle {
    CIRCLE(STMarkerStyle.CIRCLE),
    DASH(STMarkerStyle.DASH),
    DIAMOND(STMarkerStyle.DIAMOND),
    DOT(STMarkerStyle.DOT),
    NONE(STMarkerStyle.NONE),
    PICTURE(STMarkerStyle.PICTURE),
    PLUS(STMarkerStyle.PLUS),
    SQUARE(STMarkerStyle.SQUARE),
    STAR(STMarkerStyle.STAR),
    TRIANGLE(STMarkerStyle.TRIANGLE),
    X(STMarkerStyle.X);

    private static final HashMap<STMarkerStyle.Enum, MarkerStyle> reverse = new HashMap<>();
    final STMarkerStyle.Enum underlying;

    static {
        for (MarkerStyle value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    MarkerStyle(STMarkerStyle.Enum style) {
        this.underlying = style;
    }

    static MarkerStyle valueOf(STMarkerStyle.Enum style) {
        return reverse.get(style);
    }
}
