package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class Line implements Embedding<Euclidean3D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private Vector3D direction;
    private final double tolerance;
    private Vector3D zero;

    public Line(Vector3D p1, Vector3D p2, double tolerance) throws MathIllegalArgumentException {
        reset(p1, p2);
        this.tolerance = tolerance;
    }

    public Line(Line line) {
        this.direction = line.direction;
        this.zero = line.zero;
        this.tolerance = line.tolerance;
    }

    @Deprecated
    public Line(Vector3D p1, Vector3D p2) throws MathIllegalArgumentException {
        this(p1, p2, 1.0E-10d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [org.apache.commons.math3.geometry.Vector, org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public void reset(Vector3D vector3D, Vector3D p2) throws MathIllegalArgumentException {
        ?? subtract = p2.subtract((Vector<Euclidean3D>) vector3D);
        double norm2 = subtract.getNormSq();
        if (norm2 == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        this.direction = new Vector3D(1.0d / FastMath.sqrt(norm2), (Vector3D) subtract);
        this.zero = new Vector3D(1.0d, vector3D, (-vector3D.dotProduct(subtract)) / norm2, subtract);
    }

    public double getTolerance() {
        return this.tolerance;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public Line revert() {
        Line reverted = new Line(this);
        reverted.direction = reverted.direction.negate();
        return reverted;
    }

    public Vector3D getDirection() {
        return this.direction;
    }

    public Vector3D getOrigin() {
        return this.zero;
    }

    public double getAbscissa(Vector3D point) {
        return point.subtract((Vector<Euclidean3D>) this.zero).dotProduct(this.direction);
    }

    public Vector3D pointAt(double abscissa) {
        return new Vector3D(1.0d, this.zero, abscissa, this.direction);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    public Vector1D toSubSpace(Vector<Euclidean3D> vector) {
        return toSubSpace((Point<Euclidean3D>) vector);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public Vector3D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point<Euclidean1D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Euclidean1D> toSubSpace(Point<Euclidean3D> point) {
        return new Vector1D(getAbscissa((Vector3D) point));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Euclidean3D> toSpace(Point<Euclidean1D> point) {
        return pointAt(((Vector1D) point).getX());
    }

    public boolean isSimilarTo(Line line) {
        double angle = Vector3D.angle(this.direction, line.direction);
        return (angle < this.tolerance || angle > 3.141592653589793d - this.tolerance) && contains(line.zero);
    }

    public boolean contains(Vector3D p) {
        return distance(p) < this.tolerance;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public double distance(Vector3D p) {
        ?? subtract = p.subtract((Vector<Euclidean3D>) this.zero);
        Vector3D n = new Vector3D(1.0d, subtract, -subtract.dotProduct(this.direction), this.direction);
        return n.getNorm();
    }

    public double distance(Line line) {
        Vector3D normal = Vector3D.crossProduct(this.direction, line.direction);
        double n = normal.getNorm();
        if (n < Precision.SAFE_MIN) {
            return distance(line.zero);
        }
        double offset = line.zero.subtract((Vector<Euclidean3D>) this.zero).dotProduct(normal) / n;
        return FastMath.abs(offset);
    }

    public Vector3D closestPoint(Line line) {
        double cos = this.direction.dotProduct(line.direction);
        double n = 1.0d - (cos * cos);
        if (n >= Precision.EPSILON) {
            Vector<Euclidean3D> subtract = line.zero.subtract((Vector<Euclidean3D>) this.zero);
            double a = subtract.dotProduct(this.direction);
            double b = subtract.dotProduct(line.direction);
            return new Vector3D(1.0d, this.zero, (a - (b * cos)) / n, this.direction);
        }
        return this.zero;
    }

    public Vector3D intersection(Line line) {
        Vector3D closest = closestPoint(line);
        if (line.contains(closest)) {
            return closest;
        }
        return null;
    }

    public SubLine wholeLine() {
        return new SubLine(this, new IntervalsSet(this.tolerance));
    }
}
