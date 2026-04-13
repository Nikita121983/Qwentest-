package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class Vector1D implements Vector<Euclidean1D> {
    private static final long serialVersionUID = 7556674948671647925L;
    private final double x;
    public static final Vector1D ZERO = new Vector1D(0.0d);
    public static final Vector1D ONE = new Vector1D(1.0d);
    public static final Vector1D NaN = new Vector1D(Double.NaN);
    public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);
    public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);

    public Vector1D(double x) {
        this.x = x;
    }

    public Vector1D(double a, Vector1D u) {
        this.x = u.x * a;
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2) {
        this.x = (u1.x * a1) + (u2.x * a2);
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3) {
        this.x = (u1.x * a1) + (u2.x * a2) + (u3.x * a3);
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3, double a4, Vector1D u4) {
        this.x = (u1.x * a1) + (u2.x * a2) + (u3.x * a3) + (u4.x * a4);
    }

    public double getX() {
        return this.x;
    }

    @Override // org.apache.commons.math3.geometry.Point
    public Space getSpace() {
        return Euclidean1D.getInstance();
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> getZero() {
        return ZERO;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm1() {
        return FastMath.abs(this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm() {
        return FastMath.abs(this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormSq() {
        return this.x * this.x;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormInf() {
        return FastMath.abs(this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> add(Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(this.x + v1.getX());
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> add(double factor, Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(this.x + (v1.getX() * factor));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> subtract(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D) p;
        return new Vector1D(this.x - p3.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> subtract(double factor, Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D) v;
        return new Vector1D(this.x - (v1.getX() * factor));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> normalize() throws MathArithmeticException {
        double s = getNorm();
        if (s == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply(1.0d / s);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> negate() {
        return new Vector1D(-this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean1D> scalarMultiply(double a) {
        return new Vector1D(this.x * a);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public boolean isNaN() {
        return Double.isNaN(this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public boolean isInfinite() {
        return !isNaN() && Double.isInfinite(this.x);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance1(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D) p;
        double dx = FastMath.abs(p3.x - this.x);
        return dx;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    @Deprecated
    public double distance(Vector<Euclidean1D> p) {
        return distance((Point<Euclidean1D>) p);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public double distance(Point<Euclidean1D> p) {
        Vector1D p3 = (Vector1D) p;
        double dx = p3.x - this.x;
        return FastMath.abs(dx);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceInf(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D) p;
        double dx = FastMath.abs(p3.x - this.x);
        return dx;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceSq(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D) p;
        double dx = p3.x - this.x;
        return dx * dx;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double dotProduct(Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D) v;
        return this.x * v1.x;
    }

    public static double distance(Vector1D p1, Vector1D p2) {
        return p1.distance((Vector<Euclidean1D>) p2);
    }

    public static double distanceInf(Vector1D p1, Vector1D p2) {
        return p1.distanceInf(p2);
    }

    public static double distanceSq(Vector1D p1, Vector1D p2) {
        return p1.distanceSq(p2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector1D)) {
            return false;
        }
        Vector1D rhs = (Vector1D) other;
        if (rhs.isNaN()) {
            return isNaN();
        }
        return this.x == rhs.x;
    }

    public int hashCode() {
        if (isNaN()) {
            return 7785;
        }
        return MathUtils.hash(this.x) * 997;
    }

    public String toString() {
        return Vector1DFormat.getInstance().format(this);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public String toString(NumberFormat format) {
        return new Vector1DFormat(format).format(this);
    }
}
