package org.apache.poi.sl.draw.geom;

import java.util.Objects;

/* loaded from: classes10.dex */
public class Guide implements GuideIf {
    private String fmla;
    private String name;

    @Override // org.apache.poi.sl.draw.geom.GuideIf
    public String getName() {
        return this.name;
    }

    @Override // org.apache.poi.sl.draw.geom.GuideIf
    public void setName(String name) {
        this.name = name;
    }

    @Override // org.apache.poi.sl.draw.geom.GuideIf
    public String getFmla() {
        return this.fmla;
    }

    @Override // org.apache.poi.sl.draw.geom.GuideIf
    public void setFmla(String fmla) {
        this.fmla = fmla;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guide guide = (Guide) o;
        if (Objects.equals(this.name, guide.name) && Objects.equals(this.fmla, guide.fmla)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.fmla);
    }
}
