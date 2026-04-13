package org.apache.poi.sl.draw.geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;

/* loaded from: classes10.dex */
public final class CustomGeometry implements Iterable<PathIf> {
    Path textBounds;
    final List<AdjustValueIf> adjusts = new ArrayList();
    final List<GuideIf> guides = new ArrayList();
    final List<PathIf> paths = new ArrayList();
    final List<AdjustHandle> handles = new ArrayList();
    final List<ConnectionSiteIf> connections = new ArrayList();

    public void addAdjustGuide(AdjustValueIf guide) {
        this.adjusts.add(guide);
    }

    public void addGeomGuide(GuideIf guide) {
        this.guides.add(guide);
    }

    public void addAdjustHandle(AdjustHandle handle) {
        this.handles.add(handle);
    }

    public void addConnectionSite(ConnectionSiteIf connection) {
        this.connections.add(connection);
    }

    public void addPath(PathIf path) {
        this.paths.add(path);
    }

    public void setTextBounds(String left, String top, String right, String bottom) {
        this.textBounds = new Path();
        this.textBounds.addCommand(moveTo(left, top));
        this.textBounds.addCommand(lineTo(right, top));
        this.textBounds.addCommand(lineTo(right, bottom));
        this.textBounds.addCommand(lineTo(left, bottom));
        this.textBounds.addCommand(new ClosePathCommand());
    }

    private static MoveToCommand moveTo(String x, String y) {
        AdjustPoint pt = new AdjustPoint();
        pt.setX(x);
        pt.setY(y);
        MoveToCommand cmd = new MoveToCommand();
        cmd.setPt(pt);
        return cmd;
    }

    private static LineToCommand lineTo(String x, String y) {
        AdjustPoint pt = new AdjustPoint();
        pt.setX(x);
        pt.setY(y);
        LineToCommand cmd = new LineToCommand();
        cmd.setPt(pt);
        return cmd;
    }

    @Override // java.lang.Iterable
    public Iterator<PathIf> iterator() {
        return this.paths.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<PathIf> spliterator() {
        return this.paths.spliterator();
    }

    public Path getTextBounds() {
        return this.textBounds;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomGeometry)) {
            return false;
        }
        CustomGeometry that = (CustomGeometry) o;
        return Objects.equals(this.adjusts, that.adjusts) && Objects.equals(this.guides, that.guides) && Objects.equals(this.handles, that.handles) && Objects.equals(this.connections, that.connections) && Objects.equals(this.textBounds, that.textBounds) && Objects.equals(this.paths, that.paths);
    }

    public int hashCode() {
        return Objects.hash(this.adjusts, this.guides, this.handles, this.connections, this.textBounds, this.paths);
    }
}
