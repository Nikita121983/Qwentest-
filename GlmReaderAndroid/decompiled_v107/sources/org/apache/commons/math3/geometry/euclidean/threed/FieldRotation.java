package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class FieldRotation<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20130224;
    private final T q0;
    private final T q1;
    private final T q2;
    private final T q3;

    public FieldRotation(T q0, T q1, T q2, T q3, boolean needsNormalization) {
        if (needsNormalization) {
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) q0.multiply(q0)).add((RealFieldElement) q1.multiply(q1))).add((RealFieldElement) q2.multiply(q2))).add((RealFieldElement) q3.multiply(q3))).sqrt()).reciprocal();
            this.q0 = (T) realFieldElement.multiply(q0);
            this.q1 = (T) realFieldElement.multiply(q1);
            this.q2 = (T) realFieldElement.multiply(q2);
            this.q3 = (T) realFieldElement.multiply(q3);
            return;
        }
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    @Deprecated
    public FieldRotation(FieldVector3D<T> axis, T angle) throws MathIllegalArgumentException {
        this(axis, angle, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation(FieldVector3D<T> axis, T angle, RotationConvention convention) throws MathIllegalArgumentException {
        T norm = axis.getNorm();
        if (norm.getReal() == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
        }
        RealFieldElement realFieldElement = (RealFieldElement) angle.multiply(convention == RotationConvention.VECTOR_OPERATOR ? -0.5d : 0.5d);
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) realFieldElement.sin()).divide(norm);
        this.q0 = (T) realFieldElement.cos();
        this.q1 = (T) realFieldElement2.multiply(axis.getX());
        this.q2 = (T) realFieldElement2.multiply(axis.getY());
        this.q3 = (T) realFieldElement2.multiply(axis.getZ());
    }

    public FieldRotation(T[][] m, double threshold) throws NotARotationMatrixException {
        if (m.length != 3 || m[0].length != 3 || m[1].length != 3 || m[2].length != 3) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(m.length), Integer.valueOf(m[0].length));
        }
        T[][] ort = orthogonalizeMatrix(m, threshold);
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ort[0][0].multiply((RealFieldElement) ((RealFieldElement) ort[1][1].multiply(ort[2][2])).subtract((RealFieldElement) ort[2][1].multiply(ort[1][2])))).subtract((RealFieldElement) ort[1][0].multiply((RealFieldElement) ((RealFieldElement) ort[0][1].multiply(ort[2][2])).subtract((RealFieldElement) ort[2][1].multiply(ort[0][2]))))).add((RealFieldElement) ort[2][0].multiply((RealFieldElement) ((RealFieldElement) ort[0][1].multiply(ort[1][2])).subtract((RealFieldElement) ort[1][1].multiply(ort[0][2]))));
        if (realFieldElement.getReal() < 0.0d) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, realFieldElement);
        }
        T[] quat = mat2quat(ort);
        this.q0 = quat[0];
        this.q1 = quat[1];
        this.q2 = quat[2];
        this.q3 = quat[3];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldRotation(FieldVector3D<T> fieldVector3D, FieldVector3D<T> fieldVector3D2, FieldVector3D<T> fieldVector3D3, FieldVector3D<T> fieldVector3D4) throws MathArithmeticException {
        FieldVector3D<T> normalize = FieldVector3D.crossProduct(fieldVector3D, fieldVector3D2).normalize();
        FieldVector3D<T> normalize2 = FieldVector3D.crossProduct(normalize, fieldVector3D).normalize();
        FieldVector3D<T> normalize3 = fieldVector3D.normalize();
        FieldVector3D<T> normalize4 = FieldVector3D.crossProduct(fieldVector3D3, fieldVector3D4).normalize();
        FieldVector3D<T> normalize5 = FieldVector3D.crossProduct(normalize4, fieldVector3D3).normalize();
        FieldVector3D<T> normalize6 = fieldVector3D3.normalize();
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(normalize3.getX().getField(), 3, 3);
        realFieldElementArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getX())).add((RealFieldElement) normalize2.getX().multiply(normalize5.getX()))).add((RealFieldElement) normalize.getX().multiply(normalize4.getX()));
        realFieldElementArr[0][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getX())).add((RealFieldElement) normalize2.getY().multiply(normalize5.getX()))).add((RealFieldElement) normalize.getY().multiply(normalize4.getX()));
        realFieldElementArr[0][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getX())).add((RealFieldElement) normalize2.getZ().multiply(normalize5.getX()))).add((RealFieldElement) normalize.getZ().multiply(normalize4.getX()));
        realFieldElementArr[1][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getY())).add((RealFieldElement) normalize2.getX().multiply(normalize5.getY()))).add((RealFieldElement) normalize.getX().multiply(normalize4.getY()));
        realFieldElementArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getY())).add((RealFieldElement) normalize2.getY().multiply(normalize5.getY()))).add((RealFieldElement) normalize.getY().multiply(normalize4.getY()));
        realFieldElementArr[1][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getY())).add((RealFieldElement) normalize2.getZ().multiply(normalize5.getY()))).add((RealFieldElement) normalize.getZ().multiply(normalize4.getY()));
        realFieldElementArr[2][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getX().multiply(normalize6.getZ())).add((RealFieldElement) normalize2.getX().multiply(normalize5.getZ()))).add((RealFieldElement) normalize.getX().multiply(normalize4.getZ()));
        realFieldElementArr[2][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getY().multiply(normalize6.getZ())).add((RealFieldElement) normalize2.getY().multiply(normalize5.getZ()))).add((RealFieldElement) normalize.getY().multiply(normalize4.getZ()));
        realFieldElementArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) normalize3.getZ().multiply(normalize6.getZ())).add((RealFieldElement) normalize2.getZ().multiply(normalize5.getZ()))).add((RealFieldElement) normalize.getZ().multiply(normalize4.getZ()));
        RealFieldElement[] mat2quat = mat2quat(realFieldElementArr);
        this.q0 = (T) mat2quat[0];
        this.q1 = (T) mat2quat[1];
        this.q2 = (T) mat2quat[2];
        this.q3 = (T) mat2quat[3];
    }

    public FieldRotation(FieldVector3D<T> u, FieldVector3D<T> v) throws MathArithmeticException {
        RealFieldElement realFieldElement = (RealFieldElement) u.getNorm().multiply(v.getNorm());
        if (realFieldElement.getReal() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
        }
        RealFieldElement dotProduct = FieldVector3D.dotProduct(u, v);
        if (dotProduct.getReal() < realFieldElement.getReal() * (-0.999999999999998d)) {
            FieldVector3D<T> w = u.orthogonal();
            this.q0 = (T) realFieldElement.getField().getZero();
            this.q1 = (T) w.getX().negate();
            this.q2 = (T) w.getY().negate();
            this.q3 = (T) w.getZ().negate();
            return;
        }
        this.q0 = (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) dotProduct.divide(realFieldElement)).add(1.0d)).multiply(0.5d)).sqrt();
        RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(realFieldElement)).multiply(2.0d)).reciprocal();
        FieldVector3D<T> q = FieldVector3D.crossProduct(v, u);
        this.q1 = (T) realFieldElement2.multiply(q.getX());
        this.q2 = (T) realFieldElement2.multiply(q.getY());
        this.q3 = (T) realFieldElement2.multiply(q.getZ());
    }

    @Deprecated
    public FieldRotation(RotationOrder order, T alpha1, T alpha2, T alpha3) {
        this(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3);
    }

    public FieldRotation(RotationOrder order, RotationConvention convention, T alpha1, T alpha2, T alpha3) {
        RealFieldElement realFieldElement = (RealFieldElement) alpha1.getField().getOne();
        FieldRotation<T> r1 = new FieldRotation<>(new FieldVector3D(realFieldElement, order.getA1()), alpha1, convention);
        FieldRotation<T> r2 = new FieldRotation<>(new FieldVector3D(realFieldElement, order.getA2()), alpha2, convention);
        FieldRotation<T> r3 = new FieldRotation<>(new FieldVector3D(realFieldElement, order.getA3()), alpha3, convention);
        FieldRotation<T> composed = r1.compose(r2.compose(r3, convention), convention);
        this.q0 = composed.q0;
        this.q1 = composed.q1;
        this.q2 = composed.q2;
        this.q3 = composed.q3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T[] mat2quat(T[][] tArr) {
        T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(tArr[0][0].getField(), 4));
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) tArr[0][0].add(tArr[1][1])).add(tArr[2][2]);
        if (realFieldElement.getReal() > -0.19d) {
            tArr2[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(1.0d)).sqrt()).multiply(0.5d);
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) tArr2[0].reciprocal()).multiply(0.25d);
            tArr2[1] = (RealFieldElement) realFieldElement2.multiply((RealFieldElement) tArr[1][2].subtract(tArr[2][1]));
            tArr2[2] = (RealFieldElement) realFieldElement2.multiply((RealFieldElement) tArr[2][0].subtract(tArr[0][2]));
            tArr2[3] = (RealFieldElement) realFieldElement2.multiply((RealFieldElement) tArr[0][1].subtract(tArr[1][0]));
        } else {
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) tArr[0][0].subtract(tArr[1][1])).subtract(tArr[2][2]);
            if (realFieldElement3.getReal() <= -0.19d) {
                RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) tArr[1][1].subtract(tArr[0][0])).subtract(tArr[2][2]);
                if (realFieldElement4.getReal() > -0.19d) {
                    tArr2[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement4.add(1.0d)).sqrt()).multiply(0.5d);
                    RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) tArr2[2].reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement5.multiply((RealFieldElement) tArr[2][0].subtract(tArr[0][2]));
                    tArr2[1] = (RealFieldElement) realFieldElement5.multiply((RealFieldElement) tArr[0][1].add(tArr[1][0]));
                    tArr2[3] = (RealFieldElement) realFieldElement5.multiply((RealFieldElement) tArr[2][1].add(tArr[1][2]));
                } else {
                    tArr2[3] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[2][2].subtract(tArr[0][0])).subtract(tArr[1][1])).add(1.0d)).sqrt()).multiply(0.5d);
                    RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) tArr2[3].reciprocal()).multiply(0.25d);
                    tArr2[0] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[0][1].subtract(tArr[1][0]));
                    tArr2[1] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[0][2].add(tArr[2][0]));
                    tArr2[2] = (RealFieldElement) realFieldElement6.multiply((RealFieldElement) tArr[2][1].add(tArr[1][2]));
                }
            } else {
                tArr2[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement3.add(1.0d)).sqrt()).multiply(0.5d);
                RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) tArr2[1].reciprocal()).multiply(0.25d);
                tArr2[0] = (RealFieldElement) realFieldElement7.multiply((RealFieldElement) tArr[1][2].subtract(tArr[2][1]));
                tArr2[2] = (RealFieldElement) realFieldElement7.multiply((RealFieldElement) tArr[0][1].add(tArr[1][0]));
                tArr2[3] = (RealFieldElement) realFieldElement7.multiply((RealFieldElement) tArr[0][2].add(tArr[2][0]));
            }
        }
        return tArr2;
    }

    public FieldRotation<T> revert() {
        return new FieldRotation<>((RealFieldElement) this.q0.negate(), (RealFieldElement) this.q1, (RealFieldElement) this.q2, (RealFieldElement) this.q3, false);
    }

    public T getQ0() {
        return this.q0;
    }

    public T getQ1() {
        return this.q1;
    }

    public T getQ2() {
        return this.q2;
    }

    public T getQ3() {
        return this.q3;
    }

    @Deprecated
    public FieldVector3D<T> getAxis() {
        return getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    public FieldVector3D<T> getAxis(RotationConvention convention) {
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(this.q1)).add((RealFieldElement) this.q2.multiply(this.q2))).add((RealFieldElement) this.q3.multiply(this.q3));
        if (realFieldElement.getReal() == 0.0d) {
            Field<T> field = realFieldElement.getField();
            return new FieldVector3D<>((RealFieldElement) (convention == RotationConvention.VECTOR_OPERATOR ? field.getOne() : ((RealFieldElement) field.getOne()).negate()), (RealFieldElement) field.getZero(), (RealFieldElement) field.getZero());
        }
        double sgn = convention == RotationConvention.VECTOR_OPERATOR ? 1.0d : -1.0d;
        if (this.q0.getReal() < 0.0d) {
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.sqrt()).reciprocal()).multiply(sgn);
            return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement2), (RealFieldElement) this.q2.multiply(realFieldElement2), (RealFieldElement) this.q3.multiply(realFieldElement2));
        }
        RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.sqrt()).reciprocal()).negate()).multiply(sgn);
        return new FieldVector3D<>((RealFieldElement) this.q1.multiply(realFieldElement3), (RealFieldElement) this.q2.multiply(realFieldElement3), (RealFieldElement) this.q3.multiply(realFieldElement3));
    }

    public T getAngle() {
        if (this.q0.getReal() < -0.1d || this.q0.getReal() > 0.1d) {
            return (T) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(this.q1)).add((RealFieldElement) this.q2.multiply(this.q2))).add((RealFieldElement) this.q3.multiply(this.q3))).sqrt()).asin()).multiply(2);
        }
        if (this.q0.getReal() < 0.0d) {
            return (T) ((RealFieldElement) ((RealFieldElement) this.q0.negate()).acos()).multiply(2);
        }
        return (T) ((RealFieldElement) this.q0.acos()).multiply(2);
    }

    @Deprecated
    public T[] getAngles(RotationOrder order) throws CardanEulerSingularityException {
        return getAngles(order, RotationConvention.VECTOR_OPERATOR);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] getAngles(RotationOrder rotationOrder, RotationConvention rotationConvention) throws CardanEulerSingularityException {
        if (rotationConvention == RotationConvention.VECTOR_OPERATOR) {
            if (rotationOrder != RotationOrder.XYZ) {
                if (rotationOrder != RotationOrder.XZY) {
                    if (rotationOrder != RotationOrder.YXZ) {
                        if (rotationOrder != RotationOrder.YZX) {
                            if (rotationOrder != RotationOrder.ZXY) {
                                if (rotationOrder != RotationOrder.ZYX) {
                                    if (rotationOrder != RotationOrder.XYX) {
                                        if (rotationOrder != RotationOrder.XZX) {
                                            if (rotationOrder != RotationOrder.YXY) {
                                                if (rotationOrder != RotationOrder.YZY) {
                                                    if (rotationOrder == RotationOrder.ZXZ) {
                                                        FieldVector3D applyTo = applyTo(vector(0.0d, 0.0d, 1.0d));
                                                        FieldVector3D applyInverseTo = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                                                        if (applyInverseTo.getZ().getReal() < -0.9999999999d || applyInverseTo.getZ().getReal() > 0.9999999999d) {
                                                            throw new CardanEulerSingularityException(false);
                                                        }
                                                        return (T[]) buildArray((RealFieldElement) applyTo.getX().atan2(applyTo.getY().negate()), (RealFieldElement) applyInverseTo.getZ().acos(), (RealFieldElement) applyInverseTo.getX().atan2(applyInverseTo.getY()));
                                                    }
                                                    FieldVector3D applyTo2 = applyTo(vector(0.0d, 0.0d, 1.0d));
                                                    FieldVector3D applyInverseTo2 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                                                    if (applyInverseTo2.getZ().getReal() < -0.9999999999d || applyInverseTo2.getZ().getReal() > 0.9999999999d) {
                                                        throw new CardanEulerSingularityException(false);
                                                    }
                                                    return (T[]) buildArray((RealFieldElement) applyTo2.getY().atan2(applyTo2.getX()), (RealFieldElement) applyInverseTo2.getZ().acos(), (RealFieldElement) applyInverseTo2.getY().atan2(applyInverseTo2.getX().negate()));
                                                }
                                                FieldVector3D applyTo3 = applyTo(vector(0.0d, 1.0d, 0.0d));
                                                FieldVector3D applyInverseTo3 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                                                if (applyInverseTo3.getY().getReal() < -0.9999999999d || applyInverseTo3.getY().getReal() > 0.9999999999d) {
                                                    throw new CardanEulerSingularityException(false);
                                                }
                                                return (T[]) buildArray((RealFieldElement) applyTo3.getZ().atan2(applyTo3.getX().negate()), (RealFieldElement) applyInverseTo3.getY().acos(), (RealFieldElement) applyInverseTo3.getZ().atan2(applyInverseTo3.getX()));
                                            }
                                            FieldVector3D applyTo4 = applyTo(vector(0.0d, 1.0d, 0.0d));
                                            FieldVector3D applyInverseTo4 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                                            if (applyInverseTo4.getY().getReal() < -0.9999999999d || applyInverseTo4.getY().getReal() > 0.9999999999d) {
                                                throw new CardanEulerSingularityException(false);
                                            }
                                            return (T[]) buildArray((RealFieldElement) applyTo4.getX().atan2(applyTo4.getZ()), (RealFieldElement) applyInverseTo4.getY().acos(), (RealFieldElement) applyInverseTo4.getX().atan2(applyInverseTo4.getZ().negate()));
                                        }
                                        FieldVector3D applyTo5 = applyTo(vector(1.0d, 0.0d, 0.0d));
                                        FieldVector3D applyInverseTo5 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                                        if (applyInverseTo5.getX().getReal() < -0.9999999999d || applyInverseTo5.getX().getReal() > 0.9999999999d) {
                                            throw new CardanEulerSingularityException(false);
                                        }
                                        return (T[]) buildArray((RealFieldElement) applyTo5.getZ().atan2(applyTo5.getY()), (RealFieldElement) applyInverseTo5.getX().acos(), (RealFieldElement) applyInverseTo5.getZ().atan2(applyInverseTo5.getY().negate()));
                                    }
                                    FieldVector3D applyTo6 = applyTo(vector(1.0d, 0.0d, 0.0d));
                                    FieldVector3D applyInverseTo6 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                                    if (applyInverseTo6.getX().getReal() < -0.9999999999d || applyInverseTo6.getX().getReal() > 0.9999999999d) {
                                        throw new CardanEulerSingularityException(false);
                                    }
                                    return (T[]) buildArray((RealFieldElement) applyTo6.getY().atan2(applyTo6.getZ().negate()), (RealFieldElement) applyInverseTo6.getX().acos(), (RealFieldElement) applyInverseTo6.getY().atan2(applyInverseTo6.getZ()));
                                }
                                FieldVector3D applyTo7 = applyTo(vector(1.0d, 0.0d, 0.0d));
                                FieldVector3D applyInverseTo7 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                                if (applyInverseTo7.getX().getReal() < -0.9999999999d || applyInverseTo7.getX().getReal() > 0.9999999999d) {
                                    throw new CardanEulerSingularityException(true);
                                }
                                return (T[]) buildArray((RealFieldElement) applyTo7.getY().atan2(applyTo7.getX()), (RealFieldElement) ((RealFieldElement) applyInverseTo7.getX().asin()).negate(), (RealFieldElement) applyInverseTo7.getY().atan2(applyInverseTo7.getZ()));
                            }
                            FieldVector3D applyTo8 = applyTo(vector(0.0d, 1.0d, 0.0d));
                            FieldVector3D applyInverseTo8 = applyInverseTo(vector(0.0d, 0.0d, 1.0d));
                            if (applyInverseTo8.getY().getReal() < -0.9999999999d || applyInverseTo8.getY().getReal() > 0.9999999999d) {
                                throw new CardanEulerSingularityException(true);
                            }
                            return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyTo8.getX().negate()).atan2(applyTo8.getY()), (RealFieldElement) applyInverseTo8.getY().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo8.getX().negate()).atan2(applyInverseTo8.getZ()));
                        }
                        FieldVector3D applyTo9 = applyTo(vector(1.0d, 0.0d, 0.0d));
                        FieldVector3D applyInverseTo9 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                        if (applyInverseTo9.getX().getReal() < -0.9999999999d || applyInverseTo9.getX().getReal() > 0.9999999999d) {
                            throw new CardanEulerSingularityException(true);
                        }
                        return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyTo9.getZ().negate()).atan2(applyTo9.getX()), (RealFieldElement) applyInverseTo9.getX().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo9.getZ().negate()).atan2(applyInverseTo9.getY()));
                    }
                    FieldVector3D applyTo10 = applyTo(vector(0.0d, 0.0d, 1.0d));
                    FieldVector3D applyInverseTo10 = applyInverseTo(vector(0.0d, 1.0d, 0.0d));
                    if (applyInverseTo10.getZ().getReal() < -0.9999999999d || applyInverseTo10.getZ().getReal() > 0.9999999999d) {
                        throw new CardanEulerSingularityException(true);
                    }
                    return (T[]) buildArray((RealFieldElement) applyTo10.getX().atan2(applyTo10.getZ()), (RealFieldElement) ((RealFieldElement) applyInverseTo10.getZ().asin()).negate(), (RealFieldElement) applyInverseTo10.getX().atan2(applyInverseTo10.getY()));
                }
                FieldVector3D applyTo11 = applyTo(vector(0.0d, 1.0d, 0.0d));
                FieldVector3D applyInverseTo11 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
                if (applyInverseTo11.getY().getReal() < -0.9999999999d || applyInverseTo11.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) applyTo11.getZ().atan2(applyTo11.getY()), (RealFieldElement) ((RealFieldElement) applyInverseTo11.getY().asin()).negate(), (RealFieldElement) applyInverseTo11.getZ().atan2(applyInverseTo11.getX()));
            }
            FieldVector3D applyTo12 = applyTo(vector(0.0d, 0.0d, 1.0d));
            FieldVector3D applyInverseTo12 = applyInverseTo(vector(1.0d, 0.0d, 0.0d));
            if (applyInverseTo12.getZ().getReal() < -0.9999999999d || applyInverseTo12.getZ().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyTo12.getY().negate()).atan2(applyTo12.getZ()), (RealFieldElement) applyInverseTo12.getZ().asin(), (RealFieldElement) ((RealFieldElement) applyInverseTo12.getY().negate()).atan2(applyInverseTo12.getX()));
        }
        if (rotationOrder != RotationOrder.XYZ) {
            if (rotationOrder != RotationOrder.XZY) {
                if (rotationOrder != RotationOrder.YXZ) {
                    if (rotationOrder != RotationOrder.YZX) {
                        if (rotationOrder != RotationOrder.ZXY) {
                            if (rotationOrder != RotationOrder.ZYX) {
                                if (rotationOrder != RotationOrder.XYX) {
                                    if (rotationOrder != RotationOrder.XZX) {
                                        if (rotationOrder != RotationOrder.YXY) {
                                            if (rotationOrder != RotationOrder.YZY) {
                                                if (rotationOrder == RotationOrder.ZXZ) {
                                                    FieldVector3D applyTo13 = applyTo(Vector3D.PLUS_K);
                                                    FieldVector3D applyInverseTo13 = applyInverseTo(Vector3D.PLUS_K);
                                                    if (applyInverseTo13.getZ().getReal() < -0.9999999999d || applyInverseTo13.getZ().getReal() > 0.9999999999d) {
                                                        throw new CardanEulerSingularityException(false);
                                                    }
                                                    return (T[]) buildArray((RealFieldElement) applyInverseTo13.getX().atan2(applyInverseTo13.getY().negate()), (RealFieldElement) applyInverseTo13.getZ().acos(), (RealFieldElement) applyTo13.getX().atan2(applyTo13.getY()));
                                                }
                                                FieldVector3D applyTo14 = applyTo(Vector3D.PLUS_K);
                                                FieldVector3D applyInverseTo14 = applyInverseTo(Vector3D.PLUS_K);
                                                if (applyInverseTo14.getZ().getReal() < -0.9999999999d || applyInverseTo14.getZ().getReal() > 0.9999999999d) {
                                                    throw new CardanEulerSingularityException(false);
                                                }
                                                return (T[]) buildArray((RealFieldElement) applyInverseTo14.getY().atan2(applyInverseTo14.getX()), (RealFieldElement) applyInverseTo14.getZ().acos(), (RealFieldElement) applyTo14.getY().atan2(applyTo14.getX().negate()));
                                            }
                                            FieldVector3D applyTo15 = applyTo(Vector3D.PLUS_J);
                                            FieldVector3D applyInverseTo15 = applyInverseTo(Vector3D.PLUS_J);
                                            if (applyInverseTo15.getY().getReal() < -0.9999999999d || applyInverseTo15.getY().getReal() > 0.9999999999d) {
                                                throw new CardanEulerSingularityException(false);
                                            }
                                            return (T[]) buildArray((RealFieldElement) applyInverseTo15.getZ().atan2(applyInverseTo15.getX().negate()), (RealFieldElement) applyInverseTo15.getY().acos(), (RealFieldElement) applyTo15.getZ().atan2(applyTo15.getX()));
                                        }
                                        FieldVector3D applyTo16 = applyTo(Vector3D.PLUS_J);
                                        FieldVector3D applyInverseTo16 = applyInverseTo(Vector3D.PLUS_J);
                                        if (applyInverseTo16.getY().getReal() < -0.9999999999d || applyInverseTo16.getY().getReal() > 0.9999999999d) {
                                            throw new CardanEulerSingularityException(false);
                                        }
                                        return (T[]) buildArray((RealFieldElement) applyInverseTo16.getX().atan2(applyInverseTo16.getZ()), (RealFieldElement) applyInverseTo16.getY().acos(), (RealFieldElement) applyTo16.getX().atan2(applyTo16.getZ().negate()));
                                    }
                                    FieldVector3D applyTo17 = applyTo(Vector3D.PLUS_I);
                                    FieldVector3D applyInverseTo17 = applyInverseTo(Vector3D.PLUS_I);
                                    if (applyInverseTo17.getX().getReal() < -0.9999999999d || applyInverseTo17.getX().getReal() > 0.9999999999d) {
                                        throw new CardanEulerSingularityException(false);
                                    }
                                    return (T[]) buildArray((RealFieldElement) applyInverseTo17.getZ().atan2(applyInverseTo17.getY()), (RealFieldElement) applyInverseTo17.getX().acos(), (RealFieldElement) applyTo17.getZ().atan2(applyTo17.getY().negate()));
                                }
                                FieldVector3D applyTo18 = applyTo(Vector3D.PLUS_I);
                                FieldVector3D applyInverseTo18 = applyInverseTo(Vector3D.PLUS_I);
                                if (applyInverseTo18.getX().getReal() < -0.9999999999d || applyInverseTo18.getX().getReal() > 0.9999999999d) {
                                    throw new CardanEulerSingularityException(false);
                                }
                                return (T[]) buildArray((RealFieldElement) applyInverseTo18.getY().atan2(applyInverseTo18.getZ().negate()), (RealFieldElement) applyInverseTo18.getX().acos(), (RealFieldElement) applyTo18.getY().atan2(applyTo18.getZ()));
                            }
                            FieldVector3D applyTo19 = applyTo(Vector3D.PLUS_K);
                            FieldVector3D applyInverseTo19 = applyInverseTo(Vector3D.PLUS_I);
                            if (applyInverseTo19.getZ().getReal() < -0.9999999999d || applyInverseTo19.getZ().getReal() > 0.9999999999d) {
                                throw new CardanEulerSingularityException(true);
                            }
                            return (T[]) buildArray((RealFieldElement) applyInverseTo19.getY().atan2(applyInverseTo19.getX()), (RealFieldElement) ((RealFieldElement) applyInverseTo19.getZ().asin()).negate(), (RealFieldElement) applyTo19.getY().atan2(applyTo19.getZ()));
                        }
                        FieldVector3D applyTo20 = applyTo(Vector3D.PLUS_K);
                        FieldVector3D applyInverseTo20 = applyInverseTo(Vector3D.PLUS_J);
                        if (applyInverseTo20.getZ().getReal() < -0.9999999999d || applyInverseTo20.getZ().getReal() > 0.9999999999d) {
                            throw new CardanEulerSingularityException(true);
                        }
                        return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyInverseTo20.getX().negate()).atan2(applyInverseTo20.getY()), (RealFieldElement) applyInverseTo20.getZ().asin(), (RealFieldElement) ((RealFieldElement) applyTo20.getX().negate()).atan2(applyTo20.getZ()));
                    }
                    FieldVector3D applyTo21 = applyTo(Vector3D.PLUS_J);
                    FieldVector3D applyInverseTo21 = applyInverseTo(Vector3D.PLUS_I);
                    if (applyInverseTo21.getY().getReal() < -0.9999999999d || applyInverseTo21.getY().getReal() > 0.9999999999d) {
                        throw new CardanEulerSingularityException(true);
                    }
                    return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyInverseTo21.getZ().negate()).atan2(applyInverseTo21.getX()), (RealFieldElement) applyInverseTo21.getY().asin(), (RealFieldElement) ((RealFieldElement) applyTo21.getZ().negate()).atan2(applyTo21.getY()));
                }
                FieldVector3D applyTo22 = applyTo(Vector3D.PLUS_J);
                FieldVector3D applyInverseTo22 = applyInverseTo(Vector3D.PLUS_K);
                if (applyInverseTo22.getY().getReal() < -0.9999999999d || applyInverseTo22.getY().getReal() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return (T[]) buildArray((RealFieldElement) applyInverseTo22.getX().atan2(applyInverseTo22.getZ()), (RealFieldElement) ((RealFieldElement) applyInverseTo22.getY().asin()).negate(), (RealFieldElement) applyTo22.getX().atan2(applyTo22.getY()));
            }
            FieldVector3D applyTo23 = applyTo(Vector3D.PLUS_I);
            FieldVector3D applyInverseTo23 = applyInverseTo(Vector3D.PLUS_J);
            if (applyInverseTo23.getX().getReal() < -0.9999999999d || applyInverseTo23.getX().getReal() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return (T[]) buildArray((RealFieldElement) applyInverseTo23.getZ().atan2(applyInverseTo23.getY()), (RealFieldElement) ((RealFieldElement) applyInverseTo23.getX().asin()).negate(), (RealFieldElement) applyTo23.getZ().atan2(applyTo23.getX()));
        }
        FieldVector3D applyTo24 = applyTo(Vector3D.PLUS_I);
        FieldVector3D applyInverseTo24 = applyInverseTo(Vector3D.PLUS_K);
        if (applyInverseTo24.getX().getReal() < -0.9999999999d || applyInverseTo24.getX().getReal() > 0.9999999999d) {
            throw new CardanEulerSingularityException(true);
        }
        return (T[]) buildArray((RealFieldElement) ((RealFieldElement) applyInverseTo24.getY().negate()).atan2(applyInverseTo24.getZ()), (RealFieldElement) applyInverseTo24.getX().asin(), (RealFieldElement) ((RealFieldElement) applyTo24.getY().negate()).atan2(applyTo24.getX()));
    }

    private T[] buildArray(T t, T t2, T t3) {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(t.getField(), 3));
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        return tArr;
    }

    private FieldVector3D<T> vector(double x, double y, double z) {
        RealFieldElement realFieldElement = (RealFieldElement) this.q0.getField().getZero();
        return new FieldVector3D<>((RealFieldElement) realFieldElement.add(x), (RealFieldElement) realFieldElement.add(y), (RealFieldElement) realFieldElement.add(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[][] getMatrix() {
        RealFieldElement realFieldElement = (RealFieldElement) this.q0.multiply(this.q0);
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.multiply(this.q1);
        RealFieldElement realFieldElement3 = (RealFieldElement) this.q0.multiply(this.q2);
        RealFieldElement realFieldElement4 = (RealFieldElement) this.q0.multiply(this.q3);
        RealFieldElement realFieldElement5 = (RealFieldElement) this.q1.multiply(this.q1);
        RealFieldElement realFieldElement6 = (RealFieldElement) this.q1.multiply(this.q2);
        RealFieldElement realFieldElement7 = (RealFieldElement) this.q1.multiply(this.q3);
        RealFieldElement realFieldElement8 = (RealFieldElement) this.q2.multiply(this.q2);
        RealFieldElement realFieldElement9 = (RealFieldElement) this.q2.multiply(this.q3);
        RealFieldElement realFieldElement10 = (RealFieldElement) this.q3.multiply(this.q3);
        T[][] tArr = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(this.q0.getField(), 3, 3));
        tArr[0][0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement5)).multiply(2)).subtract(1.0d);
        tArr[1][0] = (RealFieldElement) ((RealFieldElement) realFieldElement6.subtract(realFieldElement4)).multiply(2);
        tArr[2][0] = (RealFieldElement) ((RealFieldElement) realFieldElement7.add(realFieldElement3)).multiply(2);
        tArr[0][1] = (RealFieldElement) ((RealFieldElement) realFieldElement6.add(realFieldElement4)).multiply(2);
        tArr[1][1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement8)).multiply(2)).subtract(1.0d);
        tArr[2][1] = (RealFieldElement) ((RealFieldElement) realFieldElement9.subtract(realFieldElement2)).multiply(2);
        tArr[0][2] = (RealFieldElement) ((RealFieldElement) realFieldElement7.subtract(realFieldElement3)).multiply(2);
        tArr[1][2] = (RealFieldElement) ((RealFieldElement) realFieldElement9.add(realFieldElement2)).multiply(2);
        tArr[2][2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement.add(realFieldElement10)).multiply(2)).subtract(1.0d);
        return tArr;
    }

    public Rotation toRotation() {
        return new Rotation(this.q0.getReal(), this.q1.getReal(), this.q2.getReal(), this.q3.getReal(), false);
    }

    public FieldVector3D<T> applyTo(FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) x.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) y.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) z.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyTo(T[] in, T[] tArr) {
        T x = in[0];
        T y = in[1];
        T z = in[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) x.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) y.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) z.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public void applyTo(double[] in, T[] tArr) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(((RealFieldElement) this.q0.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyTo(Rotation r, FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(r.getQ1())).add((RealFieldElement) y.multiply(r.getQ2()))).add((RealFieldElement) z.multiply(r.getQ3()));
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(r.getQ0())).subtract((RealFieldElement) ((RealFieldElement) z.multiply(r.getQ2())).subtract((RealFieldElement) y.multiply(r.getQ3())))).multiply(r.getQ0())).add((RealFieldElement) realFieldElement.multiply(r.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(r.getQ0())).subtract((RealFieldElement) ((RealFieldElement) x.multiply(r.getQ3())).subtract((RealFieldElement) z.multiply(r.getQ1())))).multiply(r.getQ0())).add((RealFieldElement) realFieldElement.multiply(r.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(r.getQ0())).subtract((RealFieldElement) ((RealFieldElement) y.multiply(r.getQ1())).subtract((RealFieldElement) x.multiply(r.getQ2())))).multiply(r.getQ0())).add((RealFieldElement) realFieldElement.multiply(r.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) x.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) y.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) z.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyInverseTo(T[] in, T[] tArr) {
        T x = in[0];
        T y = in[1];
        T z = in[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) x.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) y.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) z.multiply(realFieldElement2)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public void applyInverseTo(double[] in, T[] tArr) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(x)).add((RealFieldElement) this.q2.multiply(y))).add((RealFieldElement) this.q3.multiply(z));
        RealFieldElement realFieldElement2 = (RealFieldElement) this.q0.negate();
        tArr[0] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(x)).subtract((RealFieldElement) ((RealFieldElement) this.q2.multiply(z)).subtract((RealFieldElement) this.q3.multiply(y))))).add((RealFieldElement) realFieldElement.multiply(this.q1))).multiply(2)).subtract(x);
        tArr[1] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(y)).subtract((RealFieldElement) ((RealFieldElement) this.q3.multiply(x)).subtract((RealFieldElement) this.q1.multiply(z))))).add((RealFieldElement) realFieldElement.multiply(this.q2))).multiply(2)).subtract(y);
        tArr[2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply((RealFieldElement) ((RealFieldElement) realFieldElement2.multiply(z)).subtract((RealFieldElement) ((RealFieldElement) this.q1.multiply(y)).subtract((RealFieldElement) this.q2.multiply(x))))).add((RealFieldElement) realFieldElement.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(Rotation r, FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(r.getQ1())).add((RealFieldElement) y.multiply(r.getQ2()))).add((RealFieldElement) z.multiply(r.getQ3()));
        double m0 = -r.getQ0();
        return new FieldVector3D<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) x.multiply(m0)).subtract((RealFieldElement) ((RealFieldElement) z.multiply(r.getQ2())).subtract((RealFieldElement) y.multiply(r.getQ3())))).multiply(m0)).add((RealFieldElement) realFieldElement.multiply(r.getQ1()))).multiply(2)).subtract(x), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) y.multiply(m0)).subtract((RealFieldElement) ((RealFieldElement) x.multiply(r.getQ3())).subtract((RealFieldElement) z.multiply(r.getQ1())))).multiply(m0)).add((RealFieldElement) realFieldElement.multiply(r.getQ2()))).multiply(2)).subtract(y), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) z.multiply(m0)).subtract((RealFieldElement) ((RealFieldElement) y.multiply(r.getQ1())).subtract((RealFieldElement) x.multiply(r.getQ2())))).multiply(m0)).add((RealFieldElement) realFieldElement.multiply(r.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldRotation<T> applyTo(FieldRotation<T> r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(FieldRotation<T> r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : r.composeInternal(this);
    }

    private FieldRotation<T> composeInternal(FieldRotation<T> r) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) r.q0.multiply(this.q0)).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q1.multiply(this.q1)).add((RealFieldElement) r.q2.multiply(this.q2))).add((RealFieldElement) r.q3.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q1.multiply(this.q0)).add((RealFieldElement) r.q0.multiply(this.q1))).add((RealFieldElement) ((RealFieldElement) r.q2.multiply(this.q3)).subtract((RealFieldElement) r.q3.multiply(this.q2))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q2.multiply(this.q0)).add((RealFieldElement) r.q0.multiply(this.q2))).add((RealFieldElement) ((RealFieldElement) r.q3.multiply(this.q1)).subtract((RealFieldElement) r.q1.multiply(this.q3))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q3.multiply(this.q0)).add((RealFieldElement) r.q0.multiply(this.q3))).add((RealFieldElement) ((RealFieldElement) r.q1.multiply(this.q2)).subtract((RealFieldElement) r.q2.multiply(this.q1))), false);
    }

    public FieldRotation<T> applyTo(Rotation r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : applyTo(r, this);
    }

    private FieldRotation<T> composeInternal(Rotation r) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) this.q0.multiply(r.getQ0())).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(r.getQ1())).add((RealFieldElement) this.q2.multiply(r.getQ2()))).add((RealFieldElement) this.q3.multiply(r.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(r.getQ1())).add((RealFieldElement) this.q1.multiply(r.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q3.multiply(r.getQ2())).subtract((RealFieldElement) this.q2.multiply(r.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(r.getQ2())).add((RealFieldElement) this.q2.multiply(r.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q1.multiply(r.getQ3())).subtract((RealFieldElement) this.q3.multiply(r.getQ1()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(r.getQ3())).add((RealFieldElement) this.q3.multiply(r.getQ0()))).add((RealFieldElement) ((RealFieldElement) this.q2.multiply(r.getQ1())).subtract((RealFieldElement) this.q1.multiply(r.getQ2()))), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyTo(Rotation r1, FieldRotation<T> rInner) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q0.multiply(r1.getQ0())).subtract((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q1.multiply(r1.getQ1())).add((RealFieldElement) ((FieldRotation) rInner).q2.multiply(r1.getQ2()))).add((RealFieldElement) ((FieldRotation) rInner).q3.multiply(r1.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q1.multiply(r1.getQ0())).add((RealFieldElement) ((FieldRotation) rInner).q0.multiply(r1.getQ1()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q2.multiply(r1.getQ3())).subtract((RealFieldElement) ((FieldRotation) rInner).q3.multiply(r1.getQ2()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q2.multiply(r1.getQ0())).add((RealFieldElement) ((FieldRotation) rInner).q0.multiply(r1.getQ2()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q3.multiply(r1.getQ1())).subtract((RealFieldElement) ((FieldRotation) rInner).q1.multiply(r1.getQ3()))), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q3.multiply(r1.getQ0())).add((RealFieldElement) ((FieldRotation) rInner).q0.multiply(r1.getQ3()))).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q1.multiply(r1.getQ2())).subtract((RealFieldElement) ((FieldRotation) rInner).q2.multiply(r1.getQ1()))), false);
    }

    public FieldRotation<T> applyInverseTo(FieldRotation<T> r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(FieldRotation<T> r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : r.composeInternal(revert());
    }

    private FieldRotation<T> composeInverseInternal(FieldRotation<T> r) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q0.multiply(this.q0)).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q1.multiply(this.q1)).add((RealFieldElement) r.q2.multiply(this.q2))).add((RealFieldElement) r.q3.multiply(this.q3)))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q0.multiply(this.q1)).add((RealFieldElement) ((RealFieldElement) r.q2.multiply(this.q3)).subtract((RealFieldElement) r.q3.multiply(this.q2)))).subtract((RealFieldElement) r.q1.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q0.multiply(this.q2)).add((RealFieldElement) ((RealFieldElement) r.q3.multiply(this.q1)).subtract((RealFieldElement) r.q1.multiply(this.q3)))).subtract((RealFieldElement) r.q2.multiply(this.q0)), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) r.q0.multiply(this.q3)).add((RealFieldElement) ((RealFieldElement) r.q1.multiply(this.q2)).subtract((RealFieldElement) r.q2.multiply(this.q1)))).subtract((RealFieldElement) r.q3.multiply(this.q0)), false);
    }

    public FieldRotation<T> applyInverseTo(Rotation r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : applyTo(r, revert());
    }

    private FieldRotation<T> composeInverseInternal(Rotation r) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q0.multiply(r.getQ0())).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(r.getQ1())).add((RealFieldElement) this.q2.multiply(r.getQ2()))).add((RealFieldElement) this.q3.multiply(r.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q1.multiply(r.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q3.multiply(r.getQ2())).subtract((RealFieldElement) this.q2.multiply(r.getQ3())))).subtract((RealFieldElement) this.q0.multiply(r.getQ1())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q2.multiply(r.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q1.multiply(r.getQ3())).subtract((RealFieldElement) this.q3.multiply(r.getQ1())))).subtract((RealFieldElement) this.q0.multiply(r.getQ2())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.q3.multiply(r.getQ0())).add((RealFieldElement) ((RealFieldElement) this.q2.multiply(r.getQ1())).subtract((RealFieldElement) this.q1.multiply(r.getQ2())))).subtract((RealFieldElement) this.q0.multiply(r.getQ3())), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyInverseTo(Rotation rOuter, FieldRotation<T> rInner) {
        return new FieldRotation<>((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q0.multiply(rOuter.getQ0())).add((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q1.multiply(rOuter.getQ1())).add((RealFieldElement) ((FieldRotation) rInner).q2.multiply(rOuter.getQ2()))).add((RealFieldElement) ((FieldRotation) rInner).q3.multiply(rOuter.getQ3())))).negate(), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q0.multiply(rOuter.getQ1())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q2.multiply(rOuter.getQ3())).subtract((RealFieldElement) ((FieldRotation) rInner).q3.multiply(rOuter.getQ2())))).subtract((RealFieldElement) ((FieldRotation) rInner).q1.multiply(rOuter.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q0.multiply(rOuter.getQ2())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q3.multiply(rOuter.getQ1())).subtract((RealFieldElement) ((FieldRotation) rInner).q1.multiply(rOuter.getQ3())))).subtract((RealFieldElement) ((FieldRotation) rInner).q2.multiply(rOuter.getQ0())), (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q0.multiply(rOuter.getQ3())).add((RealFieldElement) ((RealFieldElement) ((FieldRotation) rInner).q1.multiply(rOuter.getQ2())).subtract((RealFieldElement) ((FieldRotation) rInner).q2.multiply(rOuter.getQ1())))).subtract((RealFieldElement) ((FieldRotation) rInner).q3.multiply(rOuter.getQ0())), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r15v30 */
    /* JADX WARN: Type inference failed for: r15v43 */
    /* JADX WARN: Type inference failed for: r17v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r22v0, types: [T extends org.apache.commons.math3.RealFieldElement<T>[][]] */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r26v3 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r28v4 */
    /* JADX WARN: Type inference failed for: r29v3 */
    /* JADX WARN: Type inference failed for: r29v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r31v3 */
    /* JADX WARN: Type inference failed for: r31v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r33v3 */
    /* JADX WARN: Type inference failed for: r33v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r35v3 */
    /* JADX WARN: Type inference failed for: r35v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r37v3 */
    /* JADX WARN: Type inference failed for: r37v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r39v3 */
    /* JADX WARN: Type inference failed for: r39v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r41v3 */
    /* JADX WARN: Type inference failed for: r41v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r45v11 */
    /* JADX WARN: Type inference failed for: r45v12 */
    /* JADX WARN: Type inference failed for: r45v13 */
    /* JADX WARN: Type inference failed for: r45v14 */
    /* JADX WARN: Type inference failed for: r45v15 */
    /* JADX WARN: Type inference failed for: r45v16 */
    /* JADX WARN: Type inference failed for: r45v17 */
    /* JADX WARN: Type inference failed for: r45v18 */
    /* JADX WARN: Type inference failed for: r45v19 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r8v3 */
    private T[][] orthogonalizeMatrix(T[][] tArr, double d) throws NotARotationMatrixException {
        boolean z = false;
        T t = tArr[0][0];
        int i = 1;
        T t2 = tArr[0][1];
        char c = 2;
        T t3 = tArr[0][2];
        T t4 = tArr[1][0];
        T t5 = tArr[1][1];
        T t6 = tArr[1][2];
        T t7 = tArr[2][0];
        T t8 = tArr[2][1];
        T t9 = tArr[2][2];
        double d2 = 0.0d;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(tArr[0][0].getField(), 3, 3);
        int i2 = 0;
        T t10 = t;
        T t11 = t2;
        T t12 = t3;
        T t13 = t4;
        T t14 = t5;
        T t15 = t6;
        T t16 = t7;
        T t17 = t8;
        T t18 = t9;
        while (true) {
            int i3 = i2 + i;
            boolean z2 = z;
            if (i3 >= 11) {
                throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, Integer.valueOf(i3 - 1));
            }
            int i4 = i;
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][z2 ? 1 : 0].multiply(t10)).add((RealFieldElement) tArr[i][z2 ? 1 : 0].multiply(t13))).add((RealFieldElement) tArr[c][z2 ? 1 : 0].multiply(t16));
            char c2 = c;
            RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][i4].multiply(t10)).add((RealFieldElement) tArr[i4][i4].multiply(t13))).add((RealFieldElement) tArr[c2][i4].multiply(t16));
            double d3 = d2;
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][c2].multiply(t10)).add((RealFieldElement) tArr[i4][c2].multiply(t13))).add((RealFieldElement) tArr[c2][c2].multiply(t16));
            RealFieldElement realFieldElement4 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][z2 ? 1 : 0].multiply(t11)).add((RealFieldElement) tArr[i4][z2 ? 1 : 0].multiply(t14))).add((RealFieldElement) tArr[c2][z2 ? 1 : 0].multiply(t17));
            ?? r22 = (T[][]) realFieldElementArr;
            RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][i4].multiply(t11)).add((RealFieldElement) tArr[i4][i4].multiply(t14))).add((RealFieldElement) tArr[c2][i4].multiply(t17));
            RealFieldElement realFieldElement6 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][c2].multiply(t11)).add((RealFieldElement) tArr[i4][c2].multiply(t14))).add((RealFieldElement) tArr[c2][c2].multiply(t17));
            T t19 = t17;
            RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][z2 ? 1 : 0].multiply(t12)).add((RealFieldElement) tArr[i4][z2 ? 1 : 0].multiply(t15))).add((RealFieldElement) tArr[c2][z2 ? 1 : 0].multiply(t18));
            T t20 = t16;
            RealFieldElement realFieldElement8 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][i4].multiply(t12)).add((RealFieldElement) tArr[i4][i4].multiply(t15))).add((RealFieldElement) tArr[c2][i4].multiply(t18));
            T t21 = t14;
            RealFieldElement realFieldElement9 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[z2 ? 1 : 0][c2].multiply(t12)).add((RealFieldElement) tArr[i4][c2].multiply(t15))).add((RealFieldElement) tArr[c2][c2].multiply(t18));
            T t22 = t18;
            T t23 = t13;
            r22[z2 ? 1 : 0][z2 ? 1 : 0] = (RealFieldElement) t10.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t10.multiply(realFieldElement)).add((RealFieldElement) t11.multiply(realFieldElement2))).add((RealFieldElement) t12.multiply(realFieldElement3))).subtract(tArr[z2 ? 1 : 0][z2 ? 1 : 0])).multiply(0.5d));
            r22[z2 ? 1 : 0][i4] = (RealFieldElement) t11.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t10.multiply(realFieldElement4)).add((RealFieldElement) t11.multiply(realFieldElement5))).add((RealFieldElement) t12.multiply(realFieldElement6))).subtract(tArr[z2 ? 1 : 0][i4])).multiply(0.5d));
            r22[z2 ? 1 : 0][c2] = (RealFieldElement) t12.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t10.multiply(realFieldElement7)).add((RealFieldElement) t11.multiply(realFieldElement8))).add((RealFieldElement) t12.multiply(realFieldElement9))).subtract(tArr[z2 ? 1 : 0][c2])).multiply(0.5d));
            r22[i4][z2 ? 1 : 0] = (RealFieldElement) t23.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t23.multiply(realFieldElement)).add((RealFieldElement) t21.multiply(realFieldElement2))).add((RealFieldElement) t15.multiply(realFieldElement3))).subtract(tArr[i4][z2 ? 1 : 0])).multiply(0.5d));
            r22[i4][i4] = (RealFieldElement) t21.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t23.multiply(realFieldElement4)).add((RealFieldElement) t21.multiply(realFieldElement5))).add((RealFieldElement) t15.multiply(realFieldElement6))).subtract(tArr[i4][i4])).multiply(0.5d));
            r22[i4][c2] = (RealFieldElement) t15.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t23.multiply(realFieldElement7)).add((RealFieldElement) t21.multiply(realFieldElement8))).add((RealFieldElement) t15.multiply(realFieldElement9))).subtract(tArr[i4][c2])).multiply(0.5d));
            r22[c2][z2 ? 1 : 0] = (RealFieldElement) t20.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement)).add((RealFieldElement) t19.multiply(realFieldElement2))).add((RealFieldElement) t22.multiply(realFieldElement3))).subtract(tArr[c2][z2 ? 1 : 0])).multiply(0.5d));
            r22[c2][i4] = (RealFieldElement) t19.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement4)).add((RealFieldElement) t19.multiply(realFieldElement5))).add((RealFieldElement) t22.multiply(realFieldElement6))).subtract(tArr[c2][i4])).multiply(0.5d));
            r22[c2][c2] = (RealFieldElement) t22.subtract(((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t20.multiply(realFieldElement7)).add((RealFieldElement) t19.multiply(realFieldElement8))).add((RealFieldElement) t22.multiply(realFieldElement9))).subtract(tArr[c2][c2])).multiply(0.5d));
            double real = r22[z2 ? 1 : 0][z2 ? 1 : 0].getReal() - tArr[z2 ? 1 : 0][z2 ? 1 : 0].getReal();
            double real2 = r22[z2 ? 1 : 0][i4].getReal() - tArr[z2 ? 1 : 0][i4].getReal();
            double real3 = r22[z2 ? 1 : 0][c2].getReal() - tArr[z2 ? 1 : 0][c2].getReal();
            double real4 = r22[i4][z2 ? 1 : 0].getReal() - tArr[i4][z2 ? 1 : 0].getReal();
            double real5 = r22[i4][i4].getReal() - tArr[i4][i4].getReal();
            double real6 = r22[i4][c2].getReal() - tArr[i4][c2].getReal();
            double real7 = r22[c2][z2 ? 1 : 0].getReal() - tArr[c2][z2 ? 1 : 0].getReal();
            double real8 = r22[c2][i4].getReal() - tArr[c2][i4].getReal();
            double real9 = r22[c2][c2].getReal() - tArr[c2][c2].getReal();
            double d4 = (real * real) + (real2 * real2) + (real3 * real3) + (real4 * real4) + (real5 * real5) + (real6 * real6) + (real7 * real7) + (real8 * real8) + (real9 * real9);
            if (FastMath.abs(d4 - d3) <= d) {
                return r22;
            }
            ?? r26 = r22[z2 ? 1 : 0][z2 ? 1 : 0];
            ?? r27 = r22[z2 ? 1 : 0][i4];
            ?? r28 = r22[z2 ? 1 : 0][c2];
            ?? r15 = r22[i4][z2 ? 1 : 0];
            ?? r25 = r22[i4][i4];
            ?? r8 = r22[i4][c2];
            ?? r17 = r22[c2][z2 ? 1 : 0];
            ?? r5 = r22[c2][i4];
            d2 = d4;
            t18 = r22[c2][c2];
            t17 = r5;
            t13 = r15;
            z = z2 ? 1 : 0;
            t16 = r17;
            i = i4;
            c = c2;
            realFieldElementArr = r22;
            i2 = i3;
            t14 = r25;
            t10 = r26;
            t11 = r27;
            t12 = r28;
            t15 = r8;
        }
    }

    public static <T extends RealFieldElement<T>> T distance(FieldRotation<T> r1, FieldRotation<T> r2) {
        return r1.composeInverseInternal(r2).getAngle();
    }
}
