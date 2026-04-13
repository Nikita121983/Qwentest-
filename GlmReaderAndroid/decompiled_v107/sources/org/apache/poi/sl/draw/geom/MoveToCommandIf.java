package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

/* loaded from: classes10.dex */
public interface MoveToCommandIf extends PathCommand {
    AdjustPointIf getPt();

    void setPt(AdjustPointIf adjustPointIf);

    @Override // org.apache.poi.sl.draw.geom.PathCommand
    default void execute(Path2D.Double path, Context ctx) {
        AdjustPointIf pt = getPt();
        double x = ctx.getValue(pt.getX());
        double y = ctx.getValue(pt.getY());
        path.moveTo(x, y);
    }
}
