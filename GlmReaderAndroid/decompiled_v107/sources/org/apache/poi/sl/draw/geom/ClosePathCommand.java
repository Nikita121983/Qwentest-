package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;

/* loaded from: classes10.dex */
public class ClosePathCommand implements ClosePathCommandIf {
    @Override // org.apache.poi.sl.draw.geom.PathCommand
    public void execute(Path2D.Double path, Context ctx) {
        path.closePath();
    }

    public int hashCode() {
        return 790622;
    }

    public boolean equals(Object obj) {
        return obj instanceof ClosePathCommand;
    }
}
