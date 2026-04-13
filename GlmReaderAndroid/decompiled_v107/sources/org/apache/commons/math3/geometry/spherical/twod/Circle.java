package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Circle implements Hyperplane<Sphere2D>, Embedding<Sphere2D, Sphere1D> {
    private Vector3D pole;
    private final double tolerance;
    private Vector3D x;
    private Vector3D y;

    public Circle(Vector3D pole, double tolerance) {
        reset(pole);
        this.tolerance = tolerance;
    }

    public Circle(S2Point first, S2Point second, double tolerance) {
        reset(first.getVector().crossProduct(second.getVector()));
        this.tolerance = tolerance;
    }

    private Circle(Vector3D pole, Vector3D x, Vector3D y, double tolerance) {
        this.pole = pole;
        this.x = x;
        this.y = y;
        this.tolerance = tolerance;
    }

    public Circle(Circle circle) {
        this(circle.pole, circle.x, circle.y, circle.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Hyperplane<Sphere2D> copySelf() {
        return new Circle(this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r0v4, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public void reset(Vector3D newPole) {
        this.pole = newPole.normalize();
        this.x = newPole.orthogonal();
        this.y = Vector3D.crossProduct(newPole, this.x).normalize();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public void revertSelf() {
        this.y = this.y.negate();
        this.pole = this.pole.negate();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r3v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public Circle getReverse() {
        return new Circle(this.pole.negate(), this.x, this.y.negate(), this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Point<Sphere2D> project(Point<Sphere2D> point) {
        return toSpace(toSubSpace(point));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getTolerance() {
        return this.tolerance;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Sphere1D> toSubSpace(Point<Sphere2D> point) {
        return new S1Point(getPhase(((S2Point) point).getVector()));
    }

    public double getPhase(Vector3D direction) {
        return FastMath.atan2(-direction.dotProduct(this.y), -direction.dotProduct(this.x)) + 3.141592653589793d;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Sphere2D> toSpace(Point<Sphere1D> point) {
        return new S2Point(getPointAt(((S1Point) point).getAlpha()));
    }

    public Vector3D getPointAt(double alpha) {
        return new Vector3D(FastMath.cos(alpha), this.x, FastMath.sin(alpha), this.y);
    }

    public Vector3D getXAxis() {
        return this.x;
    }

    public Vector3D getYAxis() {
        return this.y;
    }

    public Vector3D getPole() {
        return this.pole;
    }

    public Arc getInsideArc(Circle other) {
        double alpha = getPhase(other.pole);
        return new Arc(alpha - 1.5707963267948966d, 1.5707963267948966d + alpha, this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubHyperplane<Sphere2D> wholeHyperplane() {
        return new SubCircle(this, new ArcsSet(this.tolerance));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Region<Sphere2D> wholeSpace() {
        return new SphericalPolygonsSet(this.tolerance);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Sphere2D> point) {
        return getOffset(((S2Point) point).getVector());
    }

    public double getOffset(Vector3D direction) {
        return Vector3D.angle(this.pole, direction) - 1.5707963267948966d;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Sphere2D> other) {
        Circle otherC = (Circle) other;
        return Vector3D.dotProduct(this.pole, otherC.pole) >= 0.0d;
    }

    public static Transform<Sphere2D, Sphere1D> getTransform(Rotation rotation) {
        return new CircleTransform(rotation);
    }

    /* loaded from: classes10.dex */
    private static class CircleTransform implements Transform<Sphere2D, Sphere1D> {
        private final Rotation rotation;

        CircleTransform(Rotation rotation) {
            this.rotation = rotation;
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Point<Sphere2D> apply(Point<Sphere2D> point) {
            return new S2Point(this.rotation.applyTo(((S2Point) point).getVector()));
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Hyperplane<Sphere2D> apply(Hyperplane<Sphere2D> hyperplane) {
            Circle circle = (Circle) hyperplane;
            return new Circle(this.rotation.applyTo(circle.pole), this.rotation.applyTo(circle.x), this.rotation.applyTo(circle.y), circle.tolerance);
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public SubHyperplane<Sphere1D> apply(SubHyperplane<Sphere1D> sub, Hyperplane<Sphere2D> original, Hyperplane<Sphere2D> transformed) {
            return sub;
        }
    }
}
