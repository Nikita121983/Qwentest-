package org.apache.poi.sl.draw.geom;

import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/* loaded from: classes10.dex */
public interface ArcToCommandIf extends PathCommand {
    String getHR();

    String getStAng();

    String getSwAng();

    String getWR();

    void setHR(String str);

    void setStAng(String str);

    void setSwAng(String str);

    void setWR(String str);

    @Override // org.apache.poi.sl.draw.geom.PathCommand
    default void execute(Path2D.Double path, Context ctx) {
        double rx = ctx.getValue(getWR());
        double ry = ctx.getValue(getHR());
        double ooStart = ctx.getValue(getStAng()) / 60000.0d;
        double ooExtent = ctx.getValue(getSwAng()) / 60000.0d;
        double awtStart = ArcToCommand.convertOoxml2AwtAngle(ooStart, rx, ry);
        double awtSweep = ArcToCommand.convertOoxml2AwtAngle(ooStart + ooExtent, rx, ry) - awtStart;
        double radStart = Math.toRadians(ooStart);
        double invStart = Math.atan2(Math.sin(radStart) * rx, Math.cos(radStart) * ry);
        Point2D pt = path.getCurrentPoint();
        double x0 = (pt.getX() - (Math.cos(invStart) * rx)) - rx;
        double y0 = (pt.getY() - (Math.sin(invStart) * ry)) - ry;
        path.append(new Arc2D.Double(x0, y0, rx * 2.0d, 2.0d * ry, awtStart, awtSweep, 0), true);
    }
}
