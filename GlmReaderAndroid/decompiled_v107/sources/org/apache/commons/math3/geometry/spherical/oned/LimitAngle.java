package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/* loaded from: classes10.dex */
public class LimitAngle implements Hyperplane<Sphere1D> {
    private boolean direct;
    private S1Point location;
    private final double tolerance;

    public LimitAngle(S1Point location, boolean direct, double tolerance) {
        this.location = location;
        this.direct = direct;
        this.tolerance = tolerance;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Hyperplane<Sphere1D> copySelf() {
        return this;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Sphere1D> point) {
        double delta = ((S1Point) point).getAlpha() - this.location.getAlpha();
        return this.direct ? delta : -delta;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public LimitAngle getReverse() {
        return new LimitAngle(this.location, !this.direct, this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubHyperplane<Sphere1D> wholeHyperplane() {
        return new SubLimitAngle(this, null);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Region<Sphere1D> wholeSpace() {
        return new ArcsSet(this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Sphere1D> other) {
        return !(this.direct ^ ((LimitAngle) other).direct);
    }

    public S1Point getLocation() {
        return this.location;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Point<Sphere1D> project(Point<Sphere1D> point) {
        return this.location;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getTolerance() {
        return this.tolerance;
    }
}
