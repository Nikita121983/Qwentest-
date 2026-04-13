package org.apache.commons.math3.geometry.euclidean.twod;

import java.awt.geom.AffineTransform;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class Line implements Hyperplane<Euclidean2D>, Embedding<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10d;
    private double angle;
    private double cos;
    private double originOffset;
    private Line reverse;
    private double sin;
    private final double tolerance;

    public Line(Vector2D p1, Vector2D p2, double tolerance) {
        reset(p1, p2);
        this.tolerance = tolerance;
    }

    public Line(Vector2D p, double angle, double tolerance) {
        reset(p, angle);
        this.tolerance = tolerance;
    }

    private Line(double angle, double cos, double sin, double originOffset, double tolerance) {
        this.angle = angle;
        this.cos = cos;
        this.sin = sin;
        this.originOffset = originOffset;
        this.tolerance = tolerance;
        this.reverse = null;
    }

    @Deprecated
    public Line(Vector2D p1, Vector2D p2) {
        this(p1, p2, 1.0E-10d);
    }

    @Deprecated
    public Line(Vector2D p, double angle) {
        this(p, angle, 1.0E-10d);
    }

    public Line(Line line) {
        this.angle = MathUtils.normalizeAngle(line.angle, 3.141592653589793d);
        this.cos = line.cos;
        this.sin = line.sin;
        this.originOffset = line.originOffset;
        this.tolerance = line.tolerance;
        this.reverse = null;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Hyperplane<Euclidean2D> copySelf() {
        return new Line(this);
    }

    public void reset(Vector2D p1, Vector2D p2) {
        unlinkReverse();
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double d = FastMath.hypot(dx, dy);
        if (d != 0.0d) {
            this.angle = FastMath.atan2(-dy, -dx) + 3.141592653589793d;
            this.cos = dx / d;
            this.sin = dy / d;
            this.originOffset = MathArrays.linearCombination(p2.getX(), p1.getY(), -p1.getX(), p2.getY()) / d;
            return;
        }
        this.angle = 0.0d;
        this.cos = 1.0d;
        this.sin = 0.0d;
        this.originOffset = p1.getY();
    }

    public void reset(Vector2D p, double alpha) {
        unlinkReverse();
        this.angle = MathUtils.normalizeAngle(alpha, 3.141592653589793d);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = MathArrays.linearCombination(this.cos, p.getY(), -this.sin, p.getX());
    }

    public void revertSelf() {
        unlinkReverse();
        if (this.angle < 3.141592653589793d) {
            this.angle += 3.141592653589793d;
        } else {
            this.angle -= 3.141592653589793d;
        }
        this.cos = -this.cos;
        this.sin = -this.sin;
        this.originOffset = -this.originOffset;
    }

    private void unlinkReverse() {
        if (this.reverse != null) {
            this.reverse.reverse = null;
        }
        this.reverse = null;
    }

    public Line getReverse() {
        if (this.reverse == null) {
            double d = this.angle;
            double d2 = this.angle;
            this.reverse = new Line(d < 3.141592653589793d ? d2 + 3.141592653589793d : d2 - 3.141592653589793d, -this.cos, -this.sin, -this.originOffset, this.tolerance);
            this.reverse.reverse = this;
        }
        return this.reverse;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    public Vector1D toSubSpace(Vector<Euclidean2D> vector) {
        return toSubSpace((Point<Euclidean2D>) vector);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
    public Vector2D toSpace(Vector<Euclidean1D> vector) {
        return toSpace((Point<Euclidean1D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Euclidean1D> toSubSpace(Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
        return new Vector1D(MathArrays.linearCombination(this.cos, p2.getX(), this.sin, p2.getY()));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Embedding
    public Point<Euclidean2D> toSpace(Point<Euclidean1D> point) {
        double abscissa = ((Vector1D) point).getX();
        return new Vector2D(MathArrays.linearCombination(abscissa, this.cos, -this.originOffset, this.sin), MathArrays.linearCombination(abscissa, this.sin, this.originOffset, this.cos));
    }

    public Vector2D intersection(Line other) {
        double d = MathArrays.linearCombination(this.sin, other.cos, -other.sin, this.cos);
        if (FastMath.abs(d) < this.tolerance) {
            return null;
        }
        return new Vector2D(MathArrays.linearCombination(this.cos, other.originOffset, -other.cos, this.originOffset) / d, MathArrays.linearCombination(this.sin, other.originOffset, -other.sin, this.originOffset) / d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.commons.math3.geometry.Vector, org.apache.commons.math3.geometry.euclidean.oned.Vector1D] */
    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Point<Euclidean2D> project(Point<Euclidean2D> point) {
        return toSpace((Vector<Euclidean1D>) toSubSpace(point));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getTolerance() {
        return this.tolerance;
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public SubHyperplane<Euclidean2D> wholeHyperplane() {
        return new SubLine(this, new IntervalsSet(this.tolerance));
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public Region<Euclidean2D> wholeSpace() {
        return new PolygonsSet(this.tolerance);
    }

    public double getOffset(Line line) {
        return this.originOffset + (MathArrays.linearCombination(this.cos, line.cos, this.sin, line.sin) > 0.0d ? -line.originOffset : line.originOffset);
    }

    public double getOffset(Vector<Euclidean2D> vector) {
        return getOffset((Point<Euclidean2D>) vector);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public double getOffset(Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
        return MathArrays.linearCombination(this.sin, p2.getX(), -this.cos, p2.getY(), 1.0d, this.originOffset);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.Hyperplane
    public boolean sameOrientationAs(Hyperplane<Euclidean2D> other) {
        Line otherL = (Line) other;
        return MathArrays.linearCombination(this.sin, otherL.sin, this.cos, otherL.cos) >= 0.0d;
    }

    public Vector2D getPointAt(Vector1D abscissa, double offset) {
        double x = abscissa.getX();
        double dOffset = offset - this.originOffset;
        return new Vector2D(MathArrays.linearCombination(x, this.cos, dOffset, this.sin), MathArrays.linearCombination(x, this.sin, -dOffset, this.cos));
    }

    public boolean contains(Vector2D p) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) p)) < this.tolerance;
    }

    public double distance(Vector2D p) {
        return FastMath.abs(getOffset((Vector<Euclidean2D>) p));
    }

    public boolean isParallelTo(Line line) {
        return FastMath.abs(MathArrays.linearCombination(this.sin, line.cos, -this.cos, line.sin)) < this.tolerance;
    }

    public void translateToPoint(Vector2D p) {
        this.originOffset = MathArrays.linearCombination(this.cos, p.getY(), -this.sin, p.getX());
    }

    public double getAngle() {
        return MathUtils.normalizeAngle(this.angle, 3.141592653589793d);
    }

    public void setAngle(double angle) {
        unlinkReverse();
        this.angle = MathUtils.normalizeAngle(angle, 3.141592653589793d);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
    }

    public double getOriginOffset() {
        return this.originOffset;
    }

    public void setOriginOffset(double offset) {
        unlinkReverse();
        this.originOffset = offset;
    }

    @Deprecated
    public static Transform<Euclidean2D, Euclidean1D> getTransform(AffineTransform transform) throws MathIllegalArgumentException {
        double[] m = new double[6];
        transform.getMatrix(m);
        return new LineTransform(m[0], m[1], m[2], m[3], m[4], m[5]);
    }

    public static Transform<Euclidean2D, Euclidean1D> getTransform(double cXX, double cYX, double cXY, double cYY, double cX1, double cY1) throws MathIllegalArgumentException {
        return new LineTransform(cXX, cYX, cXY, cYY, cX1, cY1);
    }

    /* loaded from: classes10.dex */
    private static class LineTransform implements Transform<Euclidean2D, Euclidean1D> {
        private double c11;
        private double c1X;
        private double c1Y;
        private double cX1;
        private double cXX;
        private double cXY;
        private double cY1;
        private double cYX;
        private double cYY;

        LineTransform(double cXX, double cYX, double cXY, double cYY, double cX1, double cY1) throws MathIllegalArgumentException {
            this.cXX = cXX;
            this.cYX = cYX;
            this.cXY = cXY;
            this.cYY = cYY;
            this.cX1 = cX1;
            this.cY1 = cY1;
            this.c1Y = MathArrays.linearCombination(cXY, cY1, -cYY, cX1);
            this.c1X = MathArrays.linearCombination(cXX, cY1, -cYX, cX1);
            this.c11 = MathArrays.linearCombination(cXX, cYY, -cYX, cXY);
            if (FastMath.abs(this.c11) < 1.0E-20d) {
                throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM, new Object[0]);
            }
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Point<Euclidean2D> apply(Point<Euclidean2D> point) {
            Vector2D p2D = (Vector2D) point;
            double x = p2D.getX();
            double y = p2D.getY();
            return new Vector2D(MathArrays.linearCombination(this.cXX, x, this.cXY, y, this.cX1, 1.0d), MathArrays.linearCombination(this.cYX, x, this.cYY, y, this.cY1, 1.0d));
        }

        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public Hyperplane<Euclidean2D> apply(Hyperplane<Euclidean2D> hyperplane) {
            Line line = (Line) hyperplane;
            double rOffset = MathArrays.linearCombination(this.c1X, line.cos, this.c1Y, line.sin, this.c11, line.originOffset);
            double rCos = MathArrays.linearCombination(this.cXX, line.cos, this.cXY, line.sin);
            double rSin = MathArrays.linearCombination(this.cYX, line.cos, this.cYY, line.sin);
            double inv = 1.0d / FastMath.sqrt((rSin * rSin) + (rCos * rCos));
            return new Line(3.141592653589793d + FastMath.atan2(-rSin, -rCos), inv * rCos, inv * rSin, inv * rOffset, line.tolerance);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v2, types: [org.apache.commons.math3.geometry.Vector, org.apache.commons.math3.geometry.euclidean.twod.Vector2D] */
        @Override // org.apache.commons.math3.geometry.partitioning.Transform
        public SubHyperplane<Euclidean1D> apply(SubHyperplane<Euclidean1D> sub, Hyperplane<Euclidean2D> original, Hyperplane<Euclidean2D> transformed) {
            OrientedPoint op = (OrientedPoint) sub.getHyperplane();
            Line originalLine = (Line) original;
            Vector1D newLoc = ((Line) transformed).toSubSpace((Vector<Euclidean2D>) apply((Point<Euclidean2D>) originalLine.toSpace((Vector<Euclidean1D>) op.getLocation())));
            return new OrientedPoint(newLoc, op.isDirect(), originalLine.tolerance).wholeHyperplane();
        }
    }
}
