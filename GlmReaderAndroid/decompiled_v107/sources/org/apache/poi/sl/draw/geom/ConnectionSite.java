package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/* loaded from: classes10.dex */
public final class ConnectionSite implements ConnectionSiteIf {
    private String ang;
    private final AdjustPoint pos = new AdjustPoint();

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public AdjustPoint getPos() {
        return this.pos;
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public void setPos(AdjustPointIf pos) {
        if (pos != null) {
            this.pos.setX(pos.getX());
            this.pos.setY(pos.getY());
        }
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public String getAng() {
        return this.ang;
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public void setAng(String value) {
        this.ang = value;
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public boolean isSetAng() {
        return this.ang != null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionSite)) {
            return false;
        }
        ConnectionSite that = (ConnectionSite) o;
        return Objects.equals(this.pos, that.pos) && Objects.equals(this.ang, that.ang);
    }

    public int hashCode() {
        return Objects.hash(this.pos, this.ang);
    }
}
