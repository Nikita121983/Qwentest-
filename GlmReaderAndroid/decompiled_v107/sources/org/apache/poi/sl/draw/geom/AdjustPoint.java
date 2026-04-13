package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/* loaded from: classes10.dex */
public class AdjustPoint implements AdjustPointIf {
    private String x;
    private String y;

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public String getX() {
        return this.x;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public void setX(String value) {
        this.x = value;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public boolean isSetX() {
        return this.x != null;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public String getY() {
        return this.y;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public void setY(String value) {
        this.y = value;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public boolean isSetY() {
        return this.y != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdjustPoint)) {
            return false;
        }
        AdjustPoint that = (AdjustPoint) o;
        return Objects.equals(this.x, that.x) && Objects.equals(this.y, that.y);
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
