package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

/* loaded from: classes10.dex */
public enum ErrorBarType {
    BOTH(STErrBarType.BOTH),
    MINUS(STErrBarType.MINUS),
    PLUS(STErrBarType.PLUS);

    private static final HashMap<STErrBarType.Enum, ErrorBarType> reverse = new HashMap<>();
    final STErrBarType.Enum underlying;

    static {
        for (ErrorBarType value : values()) {
            reverse.put(value.underlying, value);
        }
    }

    ErrorBarType(STErrBarType.Enum barType) {
        this.underlying = barType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ErrorBarType valueOf(STErrBarType.Enum barType) {
        return reverse.get(barType);
    }
}
