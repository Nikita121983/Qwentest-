package org.apache.poi.sl.usermodel;

import java.awt.geom.Path2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

/* loaded from: classes10.dex */
public interface FreeformShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends AutoShape<S, P> {
    /* renamed from: getPath */
    Path2D mo2575getPath();

    int setPath(Path2D path2D);
}
