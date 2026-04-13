package org.apache.commons.math3.geometry.euclidean.twod;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class Vector2D implements Vector<Euclidean2D> {
    private static final long serialVersionUID = 266938651998679754L;
    private final double x;
    private final double y;
    public static final Vector2D ZERO = new Vector2D(0.0d, 0.0d);
    public static final Vector2D NaN = new Vector2D(Double.NaN, Double.NaN);
    public static final Vector2D POSITIVE_INFINITY = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector2D NEGATIVE_INFINITY = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double[] v) throws DimensionMismatchException {
        if (v.length != 2) {
            throw new DimensionMismatchException(v.length, 2);
        }
        this.x = v[0];
        this.y = v[1];
    }

    public Vector2D(double a, Vector2D u) {
        this.x = u.x * a;
        this.y = u.y * a;
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2) {
        this.x = (u1.x * a1) + (u2.x * a2);
        this.y = (u1.y * a1) + (u2.y * a2);
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3) {
        this.x = (u1.x * a1) + (u2.x * a2) + (u3.x * a3);
        this.y = (u1.y * a1) + (u2.y * a2) + (u3.y * a3);
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3, double a4, Vector2D u4) {
        this.x = (u1.x * a1) + (u2.x * a2) + (u3.x * a3) + (u4.x * a4);
        this.y = (u1.y * a1) + (u2.y * a2) + (u3.y * a3) + (u4.y * a4);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y};
    }

    @Override // org.apache.commons.math3.geometry.Point
    public Space getSpace() {
        return Euclidean2D.getInstance();
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> getZero() {
        return ZERO;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm() {
        return FastMath.sqrt((this.x * this.x) + (this.y * this.y));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormSq() {
        return (this.x * this.x) + (this.y * this.y);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormInf() {
        return FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> add(Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(this.x + v2.getX(), this.y + v2.getY());
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> add(double factor, Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(this.x + (v2.getX() * factor), this.y + (v2.getY() * factor));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> subtract(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D) p;
        return new Vector2D(this.x - p3.x, this.y - p3.y);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> subtract(double factor, Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D) v;
        return new Vector2D(this.x - (v2.getX() * factor), this.y - (v2.getY() * factor));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> normalize() throws MathArithmeticException {
        double s = getNorm();
        if (s == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply(1.0d / s);
    }

    public static double angle(Vector2D v1, Vector2D v2) throws MathArithmeticException {
        double normProduct = v1.getNorm() * v2.getNorm();
        if (normProduct == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double dot = v1.dotProduct(v2);
        double threshold = 0.9999d * normProduct;
        if (dot < (-threshold) || dot > threshold) {
            double n = FastMath.abs(MathArrays.linearCombination(v1.x, v2.y, -v1.y, v2.x));
            if (dot >= 0.0d) {
                return FastMath.asin(n / normProduct);
            }
            return 3.141592653589793d - FastMath.asin(n / normProduct);
        }
        return FastMath.acos(dot / normProduct);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> negate() {
        return new Vector2D(-this.x, -this.y);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean2D> scalarMultiply(double a) {
        return new Vector2D(this.x * a, this.y * a);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance1(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D) p;
        double dx = FastMath.abs(p3.x - this.x);
        double dy = FastMath.abs(p3.y - this.y);
        return dx + dy;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance(Vector<Euclidean2D> p) {
        return distance((Point<Euclidean2D>) p);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public double distance(Point<Euclidean2D> p) {
        Vector2D p3 = (Vector2D) p;
        double dx = p3.x - this.x;
        double dy = p3.y - this.y;
        return FastMath.sqrt((dx * dx) + (dy * dy));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceInf(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D) p;
        double dx = FastMath.abs(p3.x - this.x);
        double dy = FastMath.abs(p3.y - this.y);
        return FastMath.max(dx, dy);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceSq(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D) p;
        double dx = p3.x - this.x;
        double dy = p3.y - this.y;
        return (dx * dx) + (dy * dy);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double dotProduct(Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D) v;
        return MathArrays.linearCombination(this.x, v2.x, this.y, v2.y);
    }

    public double crossProduct(Vector2D p1, Vector2D p2) {
        double x1 = p2.getX() - p1.getX();
        double y1 = getY() - p1.getY();
        double x2 = getX() - p1.getX();
        double y2 = p2.getY() - p1.getY();
        return MathArrays.linearCombination(x1, y1, -x2, y2);
    }

    public static double distance(Vector2D p1, Vector2D p2) {
        return p1.distance((Vector<Euclidean2D>) p2);
    }

    public static double distanceInf(Vector2D p1, Vector2D p2) {
        return p1.distanceInf(p2);
    }

    public static double distanceSq(Vector2D p1, Vector2D p2) {
        return p1.distanceSq(p2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2D)) {
            return false;
        }
        Vector2D rhs = (Vector2D) other;
        if (rhs.isNaN()) {
            return isNaN();
        }
        return this.x == rhs.x && this.y == rhs.y;
    }

    public int hashCode() {
        if (isNaN()) {
            return 542;
        }
        return ((MathUtils.hash(this.x) * 76) + MathUtils.hash(this.y)) * 122;
    }

    public String toString() {
        return Vector2DFormat.getInstance().format(this);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public String toString(NumberFormat format) {
        return new Vector2DFormat(format).format(this);
    }
}
