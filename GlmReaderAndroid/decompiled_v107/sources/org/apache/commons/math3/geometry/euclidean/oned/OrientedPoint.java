package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes10.dex */
public class OrientedPoint implements Hyperplane<Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private boolean direct;
    private Vector1D location;
    private final double tolerance;

    public OrientedPoint(Vector1D location, boolean direct, double tolerance) {
        this.location = location;
        this.direct = direct;
        this.tolerance = tolerance;
    }

    @Deprecated
    public OrientedPoint(Vector1D location, boolean direct) {
        this(location, direct, 1.0E-10d);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Hyperplane<Euclidean1D> copySelf() {
        return this;
    }

    public double getOffset(Vector<Euclidean1D> vector) {
        return getOffset((Point<Euclidean1D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Euclidean1D> point) {
        double delta = ((Vector1D) point).getX() - this.location.getX();
        return this.direct ? delta : -delta;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubHyperplane<Euclidean1D> wholeHyperplane() {
        return new SubOrientedPoint(this, null);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Region<Euclidean1D> wholeSpace() {
        return new IntervalsSet(this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Euclidean1D> other) {
        return !(this.direct ^ ((OrientedPoint) other).direct);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Point<Euclidean1D> project(Point<Euclidean1D> point) {
        return this.location;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getTolerance() {
        return this.tolerance;
    }

    public Vector1D getLocation() {
        return this.location;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void revertSelf() {
        this.direct = !this.direct;
    }
}
