package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class FieldVector3D<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T x;
    private final T y;
    private final T z;

    public FieldVector3D(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FieldVector3D(T[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    public FieldVector3D(T alpha, T delta) {
        RealFieldElement realFieldElement = (RealFieldElement) delta.cos();
        this.x = (T) ((RealFieldElement) alpha.cos()).multiply(realFieldElement);
        this.y = (T) ((RealFieldElement) alpha.sin()).multiply(realFieldElement);
        this.z = (T) delta.sin();
    }

    public FieldVector3D(T a, FieldVector3D<T> u) {
        this.x = (T) a.multiply(u.x);
        this.y = (T) a.multiply(u.y);
        this.z = (T) a.multiply(u.z);
    }

    public FieldVector3D(T a, Vector3D u) {
        this.x = (T) a.multiply(u.getX());
        this.y = (T) a.multiply(u.getY());
        this.z = (T) a.multiply(u.getZ());
    }

    public FieldVector3D(double a, FieldVector3D<T> u) {
        this.x = (T) u.x.multiply(a);
        this.y = (T) u.y.multiply(a);
        this.z = (T) u.z.multiply(a);
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2) {
        this.x = (T) a1.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = (T) a1.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = (T) a1.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2) {
        this.x = (T) a1.linearCombination(u1.getX(), a1, u2.getX(), a2);
        this.y = (T) a1.linearCombination(u1.getY(), a1, u2.getY(), a2);
        this.z = (T) a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2) {
        T prototype = u1.getX();
        this.x = (T) prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = (T) prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = (T) prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2, T a3, FieldVector3D<T> u3) {
        this.x = (T) a1.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = (T) a1.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = (T) a1.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2, T a3, Vector3D u3) {
        this.x = (T) a1.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
        this.y = (T) a1.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
        this.z = (T) a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2, double a3, FieldVector3D<T> u3) {
        T prototype = u1.getX();
        this.x = (T) prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = (T) prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = (T) prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2, T a3, FieldVector3D<T> u3, T a4, FieldVector3D<T> u4) {
        this.x = (T) a1.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = (T) a1.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = (T) a1.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2, T a3, Vector3D u3, T a4, Vector3D u4) {
        this.x = (T) a1.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
        this.y = (T) a1.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
        this.z = (T) a1.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2, double a3, FieldVector3D<T> u3, double a4, FieldVector3D<T> u4) {
        T prototype = u1.getX();
        this.x = (T) prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = (T) prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = (T) prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    public T getX() {
        return this.x;
    }

    public T getY() {
        return this.y;
    }

    public T getZ() {
        return this.z;
    }

    public T[] toArray() {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(this.x.getField(), 3));
        tArr[0] = this.x;
        tArr[1] = this.y;
        tArr[2] = this.z;
        return tArr;
    }

    public Vector3D toVector3D() {
        return new Vector3D(this.x.getReal(), this.y.getReal(), this.z.getReal());
    }

    public T getNorm1() {
        return (T) ((RealFieldElement) ((RealFieldElement) this.x.abs()).add((RealFieldElement) this.y.abs())).add((RealFieldElement) this.z.abs());
    }

    public T getNorm() {
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.x.multiply(this.x)).add((RealFieldElement) this.y.multiply(this.y))).add((RealFieldElement) this.z.multiply(this.z))).sqrt();
    }

    public T getNormSq() {
        return (T) ((RealFieldElement) ((RealFieldElement) this.x.multiply(this.x)).add((RealFieldElement) this.y.multiply(this.y))).add((RealFieldElement) this.z.multiply(this.z));
    }

    public T getNormInf() {
        T t = (T) this.x.abs();
        T t2 = (T) this.y.abs();
        T t3 = (T) this.z.abs();
        if (t.getReal() <= t2.getReal()) {
            if (t2.getReal() <= t3.getReal()) {
                return t3;
            }
            return t2;
        }
        if (t.getReal() <= t3.getReal()) {
            return t3;
        }
        return t;
    }

    public T getAlpha() {
        return (T) this.y.atan2(this.x);
    }

    public T getDelta() {
        return (T) ((RealFieldElement) this.z.divide(getNorm())).asin();
    }

    public FieldVector3D<T> add(FieldVector3D<T> v) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(v.x), (RealFieldElement) this.y.add(v.y), (RealFieldElement) this.z.add(v.z));
    }

    public FieldVector3D<T> add(Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(v.getX()), (RealFieldElement) this.y.add(v.getY()), (RealFieldElement) this.z.add(v.getZ()));
    }

    public FieldVector3D<T> add(T factor, FieldVector3D<T> v) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, factor, v);
    }

    public FieldVector3D<T> add(T factor, Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(factor.multiply(v.getX())), (RealFieldElement) this.y.add(factor.multiply(v.getY())), (RealFieldElement) this.z.add(factor.multiply(v.getZ())));
    }

    public FieldVector3D<T> add(double factor, FieldVector3D<T> v) {
        return new FieldVector3D<>(1.0d, this, factor, v);
    }

    public FieldVector3D<T> add(double factor, Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.add(v.getX() * factor), (RealFieldElement) this.y.add(v.getY() * factor), (RealFieldElement) this.z.add(v.getZ() * factor));
    }

    public FieldVector3D<T> subtract(FieldVector3D<T> v) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(v.x), (RealFieldElement) this.y.subtract(v.y), (RealFieldElement) this.z.subtract(v.z));
    }

    public FieldVector3D<T> subtract(Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(v.getX()), (RealFieldElement) this.y.subtract(v.getY()), (RealFieldElement) this.z.subtract(v.getZ()));
    }

    public FieldVector3D<T> subtract(T factor, FieldVector3D<T> v) {
        return new FieldVector3D<>((RealFieldElement) this.x.getField().getOne(), this, (RealFieldElement) factor.negate(), v);
    }

    public FieldVector3D<T> subtract(T factor, Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(factor.multiply(v.getX())), (RealFieldElement) this.y.subtract(factor.multiply(v.getY())), (RealFieldElement) this.z.subtract(factor.multiply(v.getZ())));
    }

    public FieldVector3D<T> subtract(double factor, FieldVector3D<T> v) {
        return new FieldVector3D<>(1.0d, this, -factor, v);
    }

    public FieldVector3D<T> subtract(double factor, Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.subtract(v.getX() * factor), (RealFieldElement) this.y.subtract(v.getY() * factor), (RealFieldElement) this.z.subtract(v.getZ() * factor));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldVector3D<T> normalize() throws MathArithmeticException {
        RealFieldElement norm = getNorm();
        if (norm.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return scalarMultiply((FieldVector3D<T>) norm.reciprocal());
    }

    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        double threshold = getNorm().getReal() * 0.6d;
        if (threshold == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x.getReal()) <= threshold) {
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.y.multiply(this.y)).add((RealFieldElement) this.z.multiply(this.z))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) realFieldElement.getField().getZero(), (RealFieldElement) realFieldElement.multiply(this.z), (RealFieldElement) ((RealFieldElement) realFieldElement.multiply(this.y)).negate());
        }
        T inverse = this.y;
        if (FastMath.abs(inverse.getReal()) <= threshold) {
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.x.multiply(this.x)).add((RealFieldElement) this.z.multiply(this.z))).sqrt()).reciprocal();
            return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(this.z)).negate(), (RealFieldElement) realFieldElement2.getField().getZero(), (RealFieldElement) realFieldElement2.multiply(this.x));
        }
        T inverse2 = this.x;
        RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) inverse2.multiply(this.x)).add((RealFieldElement) this.y.multiply(this.y))).sqrt()).reciprocal();
        return new FieldVector3D<>((RealFieldElement) realFieldElement3.multiply(this.y), (RealFieldElement) ((RealFieldElement) realFieldElement3.multiply(this.x)).negate(), (RealFieldElement) realFieldElement3.getField().getZero());
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> v1, FieldVector3D<T> v2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) v1.getNorm().multiply(v2.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement dotProduct = dotProduct(v1, v2);
        double threshold = realFieldElement.getReal() * 0.9999d;
        if (dotProduct.getReal() < (-threshold) || dotProduct.getReal() > threshold) {
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            if (dotProduct.getReal() >= 0.0d) {
                return (T) ((RealFieldElement) v3.getNorm().divide(realFieldElement)).asin();
            }
            return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) v3.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        return (T) ((RealFieldElement) dotProduct.divide(realFieldElement)).acos();
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> v1, Vector3D v2) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) v1.getNorm().multiply(v2.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement dotProduct = dotProduct(v1, v2);
        double threshold = realFieldElement.getReal() * 0.9999d;
        if (dotProduct.getReal() < (-threshold) || dotProduct.getReal() > threshold) {
            FieldVector3D<T> v3 = crossProduct(v1, v2);
            if (dotProduct.getReal() >= 0.0d) {
                return (T) ((RealFieldElement) v3.getNorm().divide(realFieldElement)).asin();
            }
            return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) v3.getNorm().divide(realFieldElement)).asin()).subtract(3.141592653589793d)).negate();
        }
        return (T) ((RealFieldElement) dotProduct.divide(realFieldElement)).acos();
    }

    public static <T extends RealFieldElement<T>> T angle(Vector3D vector3D, FieldVector3D<T> fieldVector3D) throws MathArithmeticException {
        return (T) angle(fieldVector3D, vector3D);
    }

    public FieldVector3D<T> negate() {
        return new FieldVector3D<>((RealFieldElement) this.x.negate(), (RealFieldElement) this.y.negate(), (RealFieldElement) this.z.negate());
    }

    public FieldVector3D<T> scalarMultiply(T a) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(a), (RealFieldElement) this.y.multiply(a), (RealFieldElement) this.z.multiply(a));
    }

    public FieldVector3D<T> scalarMultiply(double a) {
        return new FieldVector3D<>((RealFieldElement) this.x.multiply(a), (RealFieldElement) this.y.multiply(a), (RealFieldElement) this.z.multiply(a));
    }

    public boolean isNaN() {
        return Double.isNaN(this.x.getReal()) || Double.isNaN(this.y.getReal()) || Double.isNaN(this.z.getReal());
    }

    public boolean isInfinite() {
        return !isNaN() && (Double.isInfinite(this.x.getReal()) || Double.isInfinite(this.y.getReal()) || Double.isInfinite(this.z.getReal()));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FieldVector3D)) {
            return false;
        }
        FieldVector3D<T> rhs = (FieldVector3D) other;
        if (rhs.isNaN()) {
            return isNaN();
        }
        return this.x.equals(rhs.x) && this.y.equals(rhs.y) && this.z.equals(rhs.z);
    }

    public int hashCode() {
        if (isNaN()) {
            return 409;
        }
        return ((this.x.hashCode() * 107) + (this.y.hashCode() * 83) + this.z.hashCode()) * 311;
    }

    public T dotProduct(FieldVector3D<T> v) {
        return (T) this.x.linearCombination(this.x, v.x, this.y, v.y, this.z, v.z);
    }

    public T dotProduct(Vector3D v) {
        return (T) this.x.linearCombination(v.getX(), this.x, v.getY(), this.y, v.getZ(), this.z);
    }

    public FieldVector3D<T> crossProduct(FieldVector3D<T> v) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(this.y, v.z, this.z.negate(), v.y), (RealFieldElement) this.y.linearCombination(this.z, v.x, this.x.negate(), v.z), (RealFieldElement) this.z.linearCombination(this.x, v.y, this.y.negate(), v.x));
    }

    public FieldVector3D<T> crossProduct(Vector3D v) {
        return new FieldVector3D<>((RealFieldElement) this.x.linearCombination(v.getZ(), this.y, -v.getY(), this.z), (RealFieldElement) this.y.linearCombination(v.getX(), this.z, -v.getZ(), this.x), (RealFieldElement) this.z.linearCombination(v.getY(), this.x, -v.getX(), this.y));
    }

    public T distance1(FieldVector3D<T> v) {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) v.x.subtract(this.x)).abs();
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) v.y.subtract(this.y)).abs();
        return (T) ((RealFieldElement) realFieldElement.add(realFieldElement2)).add((RealFieldElement) ((RealFieldElement) v.z.subtract(this.z)).abs());
    }

    public T distance1(Vector3D v) {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) this.x.subtract(v.getX())).abs();
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) this.y.subtract(v.getY())).abs();
        return (T) ((RealFieldElement) realFieldElement.add(realFieldElement2)).add((RealFieldElement) ((RealFieldElement) this.z.subtract(v.getZ())).abs());
    }

    public T distance(FieldVector3D<T> v) {
        RealFieldElement realFieldElement = (RealFieldElement) v.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) v.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) v.z.subtract(this.z);
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distance(Vector3D v) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(v.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(v.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(v.getZ());
        return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3))).sqrt();
    }

    public T distanceInf(FieldVector3D<T> v) {
        T t = (T) ((RealFieldElement) v.x.subtract(this.x)).abs();
        T t2 = (T) ((RealFieldElement) v.y.subtract(this.y)).abs();
        T t3 = (T) ((RealFieldElement) v.z.subtract(this.z)).abs();
        if (t.getReal() <= t2.getReal()) {
            if (t2.getReal() <= t3.getReal()) {
                return t3;
            }
            return t2;
        }
        if (t.getReal() <= t3.getReal()) {
            return t3;
        }
        return t;
    }

    public T distanceInf(Vector3D v) {
        T t = (T) ((RealFieldElement) this.x.subtract(v.getX())).abs();
        T t2 = (T) ((RealFieldElement) this.y.subtract(v.getY())).abs();
        T t3 = (T) ((RealFieldElement) this.z.subtract(v.getZ())).abs();
        if (t.getReal() <= t2.getReal()) {
            if (t2.getReal() <= t3.getReal()) {
                return t3;
            }
            return t2;
        }
        if (t.getReal() <= t3.getReal()) {
            return t3;
        }
        return t;
    }

    public T distanceSq(FieldVector3D<T> v) {
        RealFieldElement realFieldElement = (RealFieldElement) v.x.subtract(this.x);
        RealFieldElement realFieldElement2 = (RealFieldElement) v.y.subtract(this.y);
        RealFieldElement realFieldElement3 = (RealFieldElement) v.z.subtract(this.z);
        return (T) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3));
    }

    public T distanceSq(Vector3D v) {
        RealFieldElement realFieldElement = (RealFieldElement) this.x.subtract(v.getX());
        RealFieldElement realFieldElement2 = (RealFieldElement) this.y.subtract(v.getY());
        RealFieldElement realFieldElement3 = (RealFieldElement) this.z.subtract(v.getZ());
        return (T) ((RealFieldElement) ((RealFieldElement) realFieldElement.multiply(realFieldElement)).add((RealFieldElement) realFieldElement2.multiply(realFieldElement2))).add((RealFieldElement) realFieldElement3.multiply(realFieldElement3));
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.dotProduct(v2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(Vector3D v1, FieldVector3D<T> v2) {
        return v2.dotProduct(v1);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.crossProduct(v2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> v1, Vector3D v2) {
        return v1.crossProduct(v2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(Vector3D v1, FieldVector3D<T> v2) {
        return new FieldVector3D<>((RealFieldElement) ((FieldVector3D) v2).x.linearCombination(v1.getY(), ((FieldVector3D) v2).z, -v1.getZ(), ((FieldVector3D) v2).y), (RealFieldElement) ((FieldVector3D) v2).y.linearCombination(v1.getZ(), ((FieldVector3D) v2).x, -v1.getX(), ((FieldVector3D) v2).z), (RealFieldElement) ((FieldVector3D) v2).z.linearCombination(v1.getX(), ((FieldVector3D) v2).y, -v1.getY(), ((FieldVector3D) v2).x));
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distance1(v2);
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distance1(v2);
    }

    public static <T extends RealFieldElement<T>> T distance1(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distance1(v1);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distance(v2);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distance(v2);
    }

    public static <T extends RealFieldElement<T>> T distance(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distance(v1);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distanceInf(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distanceInf(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distanceInf(v1);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distanceSq(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distanceSq(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distanceSq(v1);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(toVector3D());
    }

    public String toString(NumberFormat format) {
        return new Vector3DFormat(format).format(toVector3D());
    }
}
