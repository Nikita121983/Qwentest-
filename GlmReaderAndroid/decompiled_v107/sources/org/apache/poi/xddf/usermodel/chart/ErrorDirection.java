package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir;

/* loaded from: classes10.dex */
public enum ErrorDirection {
    X(STErrDir.X),
    Y(STErrDir.Y);

    private static final HashMap<STErrDir.Enum, ErrorDirection> reverse = new HashMap<>();
    final STErrDir.Enum underlying;

    static {
        for (ErrorDirection value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    ErrorDirection(STErrDir.Enum direction) {
        this.underlying = direction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ErrorDirection valueOf(STErrDir.Enum direction) {
        return reverse.get(direction);
    }
}
