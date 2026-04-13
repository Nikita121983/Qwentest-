package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes10.dex */
class NestedLoops {
    private Vector2D[] loop;
    private boolean originalIsClockwise;
    private Region<Euclidean2D> polygon;
    private List<NestedLoops> surrounded;
    private final double tolerance;

    NestedLoops(double tolerance) {
        this.surrounded = new ArrayList();
        this.tolerance = tolerance;
    }

    /* JADX WARN: Type inference failed for: r3v9, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    private NestedLoops(Vector2D[] loop, double tolerance) throws MathIllegalArgumentException {
        if (loop[0] == null) {
            throw new MathIllegalArgumentException(LocalizedFormats.OUTLINE_BOUNDARY_LOOP_OPEN, new Object[0]);
        }
        this.loop = loop;
        this.surrounded = new ArrayList();
        this.tolerance = tolerance;
        ArrayList<SubHyperplane<Euclidean2D>> edges = new ArrayList<>();
        Vector2D current = loop[loop.length - 1];
        int i = 0;
        while (i < loop.length) {
            Vector2D previous = current;
            Vector2D current2 = loop[i];
            Line line = new Line(previous, current2, tolerance);
            IntervalsSet region = new IntervalsSet(line.toSubSpace((Point<Euclidean2D>) previous).getX(), line.toSubSpace((Point<Euclidean2D>) current2).getX(), tolerance);
            edges.add(new SubLine(line, region));
            i++;
            current = current2;
        }
        this.polygon = new PolygonsSet(edges, tolerance);
        if (Double.isInfinite(this.polygon.getSize())) {
            this.polygon = new RegionFactory().getComplement(this.polygon);
            this.originalIsClockwise = false;
        } else {
            this.originalIsClockwise = true;
        }
    }

    public void add(Vector2D[] bLoop) throws MathIllegalArgumentException {
        add(new NestedLoops(bLoop, this.tolerance));
    }

    private void add(NestedLoops node) throws MathIllegalArgumentException {
        for (NestedLoops child : this.surrounded) {
            if (child.polygon.contains(node.polygon)) {
                child.add(node);
                return;
            }
        }
        Iterator<NestedLoops> iterator = this.surrounded.iterator();
        while (iterator.hasNext()) {
            NestedLoops child2 = iterator.next();
            if (node.polygon.contains(child2.polygon)) {
                node.surrounded.add(child2);
                iterator.remove();
            }
        }
        RegionFactory<Euclidean2D> factory = new RegionFactory<>();
        Iterator i$ = this.surrounded.iterator();
        while (i$.hasNext()) {
            if (!factory.intersection(node.polygon, i$.next().polygon).isEmpty()) {
                throw new MathIllegalArgumentException(LocalizedFormats.CROSSING_BOUNDARY_LOOPS, new Object[0]);
            }
        }
        this.surrounded.add(node);
    }

    public void correctOrientation() {
        for (NestedLoops child : this.surrounded) {
            child.setClockWise(true);
        }
    }

    private void setClockWise(boolean clockwise) {
        if (this.originalIsClockwise ^ clockwise) {
            int min = -1;
            int max = this.loop.length;
            while (true) {
                min++;
                max--;
                if (min >= max) {
                    break;
                }
                Vector2D tmp = this.loop[min];
                this.loop[min] = this.loop[max];
                this.loop[max] = tmp;
            }
        }
        for (NestedLoops child : this.surrounded) {
            child.setClockWise(!clockwise);
        }
    }
}
