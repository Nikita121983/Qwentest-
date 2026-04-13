package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/* loaded from: classes10.dex */
public final class MoveToCommand implements MoveToCommandIf {
    private final AdjustPoint pt = new AdjustPoint();

    @Override // org.apache.poi.sl.draw.geom.MoveToCommandIf
    public AdjustPoint getPt() {
        return this.pt;
    }

    @Override // org.apache.poi.sl.draw.geom.MoveToCommandIf
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
        if (!(o instanceof MoveToCommand)) {
            return false;
        }
        MoveToCommand that = (MoveToCommand) o;
        return Objects.equals(this.pt, that.pt);
    }

    public int hashCode() {
        return Objects.hash(this.pt);
    }
}
