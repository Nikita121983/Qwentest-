package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STShape;

/* loaded from: classes10.dex */
public enum Shape {
    BOX(STShape.BOX),
    CONE(STShape.CONE),
    CONE_TO_MAX(STShape.CONE_TO_MAX),
    CYLINDER(STShape.CYLINDER),
    PYRAMID(STShape.PYRAMID),
    PYRAMID_TO_MAX(STShape.PYRAMID_TO_MAX);

    private static final HashMap<STShape.Enum, Shape> reverse = new HashMap<>();
    final STShape.Enum underlying;

    static {
        for (Shape value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    Shape(STShape.Enum grouping) {
        this.underlying = grouping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Shape valueOf(STShape.Enum grouping) {
        return reverse.get(grouping);
    }
}
