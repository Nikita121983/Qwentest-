package org.apache.poi.sl.usermodel;

import java.awt.Color;

/* loaded from: classes10.dex */
public interface HighlightColorSupport {
    PaintStyle getHighlightColor();

    void setHighlightColor(Color color);

    void setHighlightColor(PaintStyle paintStyle);
}
