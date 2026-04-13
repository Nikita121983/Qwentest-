package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

/* loaded from: classes10.dex */
public interface QuadToCommandIf extends PathCommand {
    AdjustPointIf getPt1();

    AdjustPointIf getPt2();

    void setPt1(AdjustPointIf adjustPointIf);

    void setPt2(AdjustPointIf adjustPointIf);

    @Override // org.apache.poi.sl.draw.geom.PathCommand
    default void execute(Path2D.Double path, Context ctx) {
        AdjustPointIf pt1 = getPt1();
        double x1 = ctx.getValue(pt1.getX());
        double y1 = ctx.getValue(pt1.getY());
        AdjustPointIf pt2 = getPt2();
        double x2 = ctx.getValue(pt2.getX());
        double y2 = ctx.getValue(pt2.getY());
        path.quadTo(x1, y1, x2, y2);
    }
}
