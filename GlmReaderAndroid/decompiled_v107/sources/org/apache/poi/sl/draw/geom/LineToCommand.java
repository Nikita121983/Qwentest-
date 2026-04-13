package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/* loaded from: classes10.dex */
public final class LineToCommand implements LineToCommandIf {
    private final AdjustPoint pt = new AdjustPoint();

    @Override // org.apache.poi.sl.draw.geom.LineToCommandIf
    public AdjustPoint getPt() {
        return this.pt;
    }

    @Override // org.apache.poi.sl.draw.geom.LineToCommandIf
    public void setPt(AdjustPointIf pt) {
        if (pt != null) {
            this.pt.setX(pt.getX());
            this.pt.setY(pt.getY());
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineToCommand)) {
            return false;
        }
        LineToCommand that = (LineToCommand) o;
        return Objects.equals(this.pt, that.pt);
    }

    public int hashCode() {
        return Objects.hash(this.pt);
    }
}
