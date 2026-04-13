package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.poi.sl.usermodel.PaintStyle;

/* loaded from: classes10.dex */
public final class Path implements PathIf {
    private final List<PathCommand> commands = new ArrayList();
    private PaintStyle.PaintModifier fill = PaintStyle.PaintModifier.NORM;
    private boolean stroke = true;
    private boolean extrusionOk = false;
    private long w = -1;
    private long h = -1;

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void addCommand(PathCommand cmd) {
        this.commands.add(cmd);
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public Path2D.Double getPath(Context ctx) {
        Path2D.Double path = new Path2D.Double();
        for (PathCommand cmd : this.commands) {
            cmd.execute(path, ctx);
        }
        return path;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isStroked() {
        return this.stroke;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isFilled() {
        return this.fill != PaintStyle.PaintModifier.NONE;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public PaintStyle.PaintModifier getFill() {
        return this.fill;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setFill(PaintStyle.PaintModifier fill) {
        this.fill = fill;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public long getW() {
        return this.w;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setW(long w) {
        this.w = w;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public long getH() {
        return this.h;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setH(long h) {
        this.h = h;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isExtrusionOk() {
        return this.extrusionOk;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setExtrusionOk(boolean extrusionOk) {
        this.extrusionOk = extrusionOk;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Path)) {
            return false;
        }
        Path ctPath2D = (Path) o;
        return Objects.equals(this.commands, ctPath2D.commands) && Objects.equals(Long.valueOf(this.w), Long.valueOf(ctPath2D.w)) && Objects.equals(Long.valueOf(this.h), Long.valueOf(ctPath2D.h)) && this.fill == ctPath2D.fill && Objects.equals(Boolean.valueOf(this.stroke), Boolean.valueOf(ctPath2D.stroke)) && Objects.equals(Boolean.valueOf(this.extrusionOk), Boolean.valueOf(ctPath2D.extrusionOk));
    }

    public int hashCode() {
        return Objects.hash(this.commands, Long.valueOf(this.w), Long.valueOf(this.h), Integer.valueOf(this.fill.ordinal()), Boolean.valueOf(this.stroke), Boolean.valueOf(this.extrusionOk));
    }
}
