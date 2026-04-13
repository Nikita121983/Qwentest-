package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
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
public class Vector3D implements Serializable, Vector<Euclidean3D> {
    private static final long serialVersionUID = 1313493323784566947L;
    private final double x;
    private final double y;
    private final double z;
    public static final Vector3D ZERO = new Vector3D(0.0d, 0.0d, 0.0d);
    public static final Vector3D PLUS_I = new Vector3D(1.0d, 0.0d, 0.0d);
    public static final Vector3D MINUS_I = new Vector3D(-1.0d, 0.0d, 0.0d);
    public static final Vector3D PLUS_J = new Vector3D(0.0d, 1.0d, 0.0d);
    public static final Vector3D MINUS_J = new Vector3D(0.0d, -1.0d, 0.0d);
    public static final Vector3D PLUS_K = new Vector3D(0.0d, 0.0d, 1.0d);
    public static final Vector3D MINUS_K = new Vector3D(0.0d, 0.0d, -1.0d);
    public static final Vector3D NaN = new Vector3D(Double.NaN, Double.NaN, Double.NaN);
    public static final Vector3D POSITIVE_INFINITY = new Vector3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector3D NEGATIVE_INFINITY = new Vector3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    public Vector3D(double alpha, double delta) {
        double cosDelta = FastMath.cos(delta);
        this.x = FastMath.cos(alpha) * cosDelta;
        this.y = FastMath.sin(alpha) * cosDelta;
        this.z = FastMath.sin(delta);
    }

    public Vector3D(double a, Vector3D u) {
        this.x = u.x * a;
        this.y = u.y * a;
        this.z = u.z * a;
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z);
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z);
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3, double a4, Vector3D u4) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x, a4, u4.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y, a4, u4.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z, a4, u4.z);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y, this.z};
    }

    @Override // org.apache.commons.math3.geometry.Point
    public Space getSpace() {
        return Euclidean3D.getInstance();
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> getZero() {
        return ZERO;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y) + FastMath.abs(this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNorm() {
        return FastMath.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormSq() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double getNormInf() {
        return FastMath.max(FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y)), FastMath.abs(this.z));
    }

    public double getAlpha() {
        return FastMath.atan2(this.y, this.x);
    }

    public double getDelta() {
        return FastMath.asin(this.z / getNorm());
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> add(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        return new Vector3D(this.x + v3.x, this.y + v3.y, this.z + v3.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> add(double factor, Vector<Euclidean3D> v) {
        return new Vector3D(1.0d, this, factor, (Vector3D) v);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> subtract(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        return new Vector3D(this.x - v3.x, this.y - v3.y, this.z - v3.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> subtract(double factor, Vector<Euclidean3D> v) {
        return new Vector3D(1.0d, this, -factor, (Vector3D) v);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> normalize() throws MathArithmeticException {
        double s = getNorm();
        if (s == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply(1.0d / s);
    }

    public Vector3D orthogonal() throws MathArithmeticException {
        double threshold = getNorm() * 0.6d;
        if (threshold == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x) <= threshold) {
            double inverse = 1.0d / FastMath.sqrt((this.y * this.y) + (this.z * this.z));
            return new Vector3D(0.0d, inverse * this.z, (-inverse) * this.y);
        }
        if (FastMath.abs(this.y) <= threshold) {
            double inverse2 = 1.0d / FastMath.sqrt((this.x * this.x) + (this.z * this.z));
            return new Vector3D(this.z * (-inverse2), 0.0d, inverse2 * this.x);
        }
        double inverse3 = 1.0d / FastMath.sqrt((this.x * this.x) + (this.y * this.y));
        return new Vector3D(inverse3 * this.y, this.x * (-inverse3), 0.0d);
    }

    public static double angle(Vector3D v1, Vector3D v2) throws MathArithmeticException {
        double normProduct = v1.getNorm() * v2.getNorm();
        if (normProduct == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double dot = v1.dotProduct(v2);
        double threshold = 0.9999d * normProduct;
        if (dot < (-threshold) || dot > threshold) {
            Vector3D v3 = crossProduct(v1, v2);
            if (dot >= 0.0d) {
                return FastMath.asin(v3.getNorm() / normProduct);
            }
            return 3.141592653589793d - FastMath.asin(v3.getNorm() / normProduct);
        }
        return FastMath.acos(dot / normProduct);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> negate() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public Vector<Euclidean3D> scalarMultiply(double a) {
        return new Vector3D(this.x * a, this.y * a, this.z * a);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y) || Double.isInfinite(this.z));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector3D)) {
            return false;
        }
        Vector3D rhs = (Vector3D) other;
        if (rhs.isNaN()) {
            return isNaN();
        }
        return this.x == rhs.x && this.y == rhs.y && this.z == rhs.z;
    }

    public int hashCode() {
        if (isNaN()) {
            return 642;
        }
        return ((MathUtils.hash(this.x) * 164) + (MathUtils.hash(this.y) * 3) + MathUtils.hash(this.z)) * 643;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double dotProduct(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        return MathArrays.linearCombination(this.x, v3.x, this.y, v3.y, this.z, v3.z);
    }

    public Vector3D crossProduct(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        return new Vector3D(MathArrays.linearCombination(this.y, v3.z, -this.z, v3.y), MathArrays.linearCombination(this.z, v3.x, -this.x, v3.z), MathArrays.linearCombination(this.x, v3.y, -this.y, v3.x));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance1(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        double dx = FastMath.abs(v3.x - this.x);
        double dy = FastMath.abs(v3.y - this.y);
        double dz = FastMath.abs(v3.z - this.z);
        return dx + dy + dz;
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distance(Vector<Euclidean3D> v) {
        return distance((Point<Euclidean3D>) v);
    }

    @Override // org.apache.commons.math3.geometry.Point
    public double distance(Point<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        double dx = v3.x - this.x;
        double dy = v3.y - this.y;
        double dz = v3.z - this.z;
        return FastMath.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceInf(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        double dx = FastMath.abs(v3.x - this.x);
        double dy = FastMath.abs(v3.y - this.y);
        double dz = FastMath.abs(v3.z - this.z);
        return FastMath.max(FastMath.max(dx, dy), dz);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public double distanceSq(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D) v;
        double dx = v3.x - this.x;
        double dy = v3.y - this.y;
        double dz = v3.z - this.z;
        return (dx * dx) + (dy * dy) + (dz * dz);
    }

    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        return v1.crossProduct(v2);
    }

    public static double distance1(Vector3D v1, Vector3D v2) {
        return v1.distance1(v2);
    }

    public static double distance(Vector3D v1, Vector3D v2) {
        return v1.distance((Vector<Euclidean3D>) v2);
    }

    public static double distanceInf(Vector3D v1, Vector3D v2) {
        return v1.distanceInf(v2);
    }

    public static double distanceSq(Vector3D v1, Vector3D v2) {
        return v1.distanceSq(v2);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(this);
    }

    @Override // org.apache.commons.math3.geometry.Vector
    public String toString(NumberFormat format) {
        return new Vector3DFormat(format).format(this);
    }
}
