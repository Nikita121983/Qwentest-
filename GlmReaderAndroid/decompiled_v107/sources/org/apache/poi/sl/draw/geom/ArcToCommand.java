package org.apache.poi.sl.draw.geom;

import java.util.Objects;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class ArcToCommand implements ArcToCommandIf {
    private String hr;
    private String stAng;
    private String swAng;
    private String wr;

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setHR(String hr) {
        this.hr = hr;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getHR() {
        return this.hr;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getStAng() {
        return this.stAng;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getWR() {
        return this.wr;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setWR(String wr) {
        this.wr = wr;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setStAng(String stAng) {
        this.stAng = stAng;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getSwAng() {
        return this.swAng;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setSwAng(String swAng) {
        this.swAng = swAng;
    }

    @Internal
    public static double convertOoxml2AwtAngle(double ooAngle, double width, double height) {
        double aspect = height / width;
        double awtAngle = -ooAngle;
        double awtAngle2 = awtAngle % 360.0d;
        double awtAngle3 = awtAngle - awtAngle2;
        switch ((int) (awtAngle2 / 90.0d)) {
            case -3:
                awtAngle3 -= 360.0d;
                awtAngle2 += 360.0d;
                break;
            case -2:
            case -1:
                awtAngle3 -= 180.0d;
                awtAngle2 += 180.0d;
                break;
            case 1:
            case 2:
                awtAngle3 += 180.0d;
                awtAngle2 -= 180.0d;
                break;
            case 3:
                awtAngle3 += 360.0d;
                awtAngle2 -= 360.0d;
                break;
        }
        return Math.toDegrees(Math.atan2(Math.tan(Math.toRadians(awtAngle2)), aspect)) + awtAngle3;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArcToCommand)) {
            return false;
        }
        ArcToCommand that = (ArcToCommand) o;
        return Objects.equals(this.wr, that.wr) && Objects.equals(this.hr, that.hr) && Objects.equals(this.stAng, that.stAng) && Objects.equals(this.swAng, that.swAng);
    }

    public int hashCode() {
        return Objects.hash(this.wr, this.hr, this.stAng, this.swAng);
    }
}
