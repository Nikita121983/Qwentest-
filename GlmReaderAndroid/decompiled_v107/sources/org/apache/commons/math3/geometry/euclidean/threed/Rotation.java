package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class Rotation implements Serializable {
    public static final Rotation IDENTITY = new Rotation(1.0d, 0.0d, 0.0d, 0.0d, false);
    private static final long serialVersionUID = -2153622329907944313L;
    private final double q0;
    private final double q1;
    private final double q2;
    private final double q3;

    public Rotation(double q0, double q1, double q2, double q3, boolean needsNormalization) {
        if (needsNormalization) {
            double inv = 1.0d / FastMath.sqrt((((q0 * q0) + (q1 * q1)) + (q2 * q2)) + (q3 * q3));
            q0 *= inv;
            q1 *= inv;
            q2 *= inv;
            q3 *= inv;
        }
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    @Deprecated
    public Rotation(Vector3D axis, double angle) throws MathIllegalArgumentException {
        this(axis, angle, RotationConvention.VECTOR_OPERATOR);
    }

    public Rotation(Vector3D axis, double angle, RotationConvention convention) throws MathIllegalArgumentException {
        double norm = axis.getNorm();
        if (norm == 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
        }
        double halfAngle = (convention == RotationConvention.VECTOR_OPERATOR ? -0.5d : 0.5d) * angle;
        double coeff = FastMath.sin(halfAngle) / norm;
        this.q0 = FastMath.cos(halfAngle);
        this.q1 = axis.getX() * coeff;
        this.q2 = axis.getY() * coeff;
        this.q3 = axis.getZ() * coeff;
    }

    public Rotation(double[][] m, double threshold) throws NotARotationMatrixException {
        if (m.length != 3 || m[0].length != 3 || m[1].length != 3 || m[2].length != 3) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, Integer.valueOf(m.length), Integer.valueOf(m[0].length));
        }
        double[][] ort = orthogonalizeMatrix(m, threshold);
        double det = ((ort[0][0] * ((ort[1][1] * ort[2][2]) - (ort[2][1] * ort[1][2]))) - (ort[1][0] * ((ort[0][1] * ort[2][2]) - (ort[2][1] * ort[0][2])))) + (ort[2][0] * ((ort[0][1] * ort[1][2]) - (ort[1][1] * ort[0][2])));
        if (det < 0.0d) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, Double.valueOf(det));
        }
        double[] quat = mat2quat(ort);
        this.q0 = quat[0];
        this.q1 = quat[1];
        this.q2 = quat[2];
        this.q3 = quat[3];
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r2v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r3v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r4v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r5v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    /* JADX WARN: Type inference failed for: r6v1, types: [org.apache.commons.math3.geometry.euclidean.threed.Vector3D] */
    public Rotation(Vector3D u1, Vector3D u2, Vector3D v1, Vector3D v2) throws MathArithmeticException {
        ?? normalize = u1.crossProduct(u2).normalize();
        ?? normalize2 = normalize.crossProduct(u1).normalize();
        ?? normalize3 = u1.normalize();
        ?? normalize4 = v1.crossProduct(v2).normalize();
        ?? normalize5 = normalize4.crossProduct(v1).normalize();
        ?? normalize6 = v1.normalize();
        double[][] m = {new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getX(), normalize2.getX(), normalize5.getX(), normalize.getX(), normalize4.getX()), MathArrays.linearCombination(normalize3.getY(), normalize6.getX(), normalize2.getY(), normalize5.getX(), normalize.getY(), normalize4.getX()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getX(), normalize2.getZ(), normalize5.getX(), normalize.getZ(), normalize4.getX())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getY(), normalize2.getX(), normalize5.getY(), normalize.getX(), normalize4.getY()), MathArrays.linearCombination(normalize3.getY(), normalize6.getY(), normalize2.getY(), normalize5.getY(), normalize.getY(), normalize4.getY()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getY(), normalize2.getZ(), normalize5.getY(), normalize.getZ(), normalize4.getY())}, new double[]{MathArrays.linearCombination(normalize3.getX(), normalize6.getZ(), normalize2.getX(), normalize5.getZ(), normalize.getX(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getY(), normalize6.getZ(), normalize2.getY(), normalize5.getZ(), normalize.getY(), normalize4.getZ()), MathArrays.linearCombination(normalize3.getZ(), normalize6.getZ(), normalize2.getZ(), normalize5.getZ(), normalize.getZ(), normalize4.getZ())}};
        double[] quat = mat2quat(m);
        this.q0 = quat[0];
        this.q1 = quat[1];
        this.q2 = quat[2];
        this.q3 = quat[3];
    }

    public Rotation(Vector3D u, Vector3D v) throws MathArithmeticException {
        double normProduct = u.getNorm() * v.getNorm();
        if (normProduct == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
        }
        double dot = u.dotProduct(v);
        if (dot < (-0.999999999999998d) * normProduct) {
            Vector3D w = u.orthogonal();
            this.q0 = 0.0d;
            this.q1 = -w.getX();
            this.q2 = -w.getY();
            this.q3 = -w.getZ();
            return;
        }
        this.q0 = FastMath.sqrt(((dot / normProduct) + 1.0d) * 0.5d);
        double coeff = 1.0d / ((this.q0 * 2.0d) * normProduct);
        Vector3D q = v.crossProduct(u);
        this.q1 = q.getX() * coeff;
        this.q2 = q.getY() * coeff;
        this.q3 = q.getZ() * coeff;
    }

    @Deprecated
    public Rotation(RotationOrder order, double alpha1, double alpha2, double alpha3) {
        this(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3);
    }

    public Rotation(RotationOrder order, RotationConvention convention, double alpha1, double alpha2, double alpha3) {
        Rotation r1 = new Rotation(order.getA1(), alpha1, convention);
        Rotation r2 = new Rotation(order.getA2(), alpha2, convention);
        Rotation r3 = new Rotation(order.getA3(), alpha3, convention);
        Rotation composed = r1.compose(r2.compose(r3, convention), convention);
        this.q0 = composed.q0;
        this.q1 = composed.q1;
        this.q2 = composed.q2;
        this.q3 = composed.q3;
    }

    private static double[] mat2quat(double[][] ort) {
        double[] quat = new double[4];
        double s = ort[0][0] + ort[1][1] + ort[2][2];
        if (s > -0.19d) {
            quat[0] = FastMath.sqrt(1.0d + s) * 0.5d;
            double inv = 0.25d / quat[0];
            quat[1] = (ort[1][2] - ort[2][1]) * inv;
            quat[2] = (ort[2][0] - ort[0][2]) * inv;
            quat[3] = (ort[0][1] - ort[1][0]) * inv;
        } else {
            double s2 = (ort[0][0] - ort[1][1]) - ort[2][2];
            if (s2 <= -0.19d) {
                double s3 = (ort[1][1] - ort[0][0]) - ort[2][2];
                if (s3 > -0.19d) {
                    quat[2] = FastMath.sqrt(1.0d + s3) * 0.5d;
                    double inv2 = 0.25d / quat[2];
                    quat[0] = (ort[2][0] - ort[0][2]) * inv2;
                    quat[1] = (ort[0][1] + ort[1][0]) * inv2;
                    quat[3] = (ort[2][1] + ort[1][2]) * inv2;
                } else {
                    quat[3] = FastMath.sqrt(1.0d + ((ort[2][2] - ort[0][0]) - ort[1][1])) * 0.5d;
                    double inv3 = 0.25d / quat[3];
                    quat[0] = (ort[0][1] - ort[1][0]) * inv3;
                    quat[1] = (ort[0][2] + ort[2][0]) * inv3;
                    quat[2] = (ort[2][1] + ort[1][2]) * inv3;
                }
            } else {
                quat[1] = FastMath.sqrt(1.0d + s2) * 0.5d;
                double inv4 = 0.25d / quat[1];
                quat[0] = (ort[1][2] - ort[2][1]) * inv4;
                quat[2] = (ort[0][1] + ort[1][0]) * inv4;
                quat[3] = (ort[0][2] + ort[2][0]) * inv4;
            }
        }
        return quat;
    }

    public Rotation revert() {
        return new Rotation(-this.q0, this.q1, this.q2, this.q3, false);
    }

    public double getQ0() {
        return this.q0;
    }

    public double getQ1() {
        return this.q1;
    }

    public double getQ2() {
        return this.q2;
    }

    public double getQ3() {
        return this.q3;
    }

    @Deprecated
    public Vector3D getAxis() {
        return getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    public Vector3D getAxis(RotationConvention convention) {
        double squaredSine = (this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3);
        if (squaredSine == 0.0d) {
            return convention == RotationConvention.VECTOR_OPERATOR ? Vector3D.PLUS_I : Vector3D.MINUS_I;
        }
        double sgn = convention == RotationConvention.VECTOR_OPERATOR ? 1.0d : -1.0d;
        if (this.q0 < 0.0d) {
            double inverse = sgn / FastMath.sqrt(squaredSine);
            return new Vector3D(this.q1 * inverse, this.q2 * inverse, this.q3 * inverse);
        }
        double inverse2 = (-sgn) / FastMath.sqrt(squaredSine);
        return new Vector3D(this.q1 * inverse2, this.q2 * inverse2, this.q3 * inverse2);
    }

    public double getAngle() {
        if (this.q0 < -0.1d || this.q0 > 0.1d) {
            return FastMath.asin(FastMath.sqrt((this.q1 * this.q1) + (this.q2 * this.q2) + (this.q3 * this.q3))) * 2.0d;
        }
        if (this.q0 < 0.0d) {
            return FastMath.acos(-this.q0) * 2.0d;
        }
        return FastMath.acos(this.q0) * 2.0d;
    }

    @Deprecated
    public double[] getAngles(RotationOrder order) throws CardanEulerSingularityException {
        return getAngles(order, RotationConvention.VECTOR_OPERATOR);
    }

    public double[] getAngles(RotationOrder order, RotationConvention convention) throws CardanEulerSingularityException {
        if (convention == RotationConvention.VECTOR_OPERATOR) {
            if (order == RotationOrder.XYZ) {
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if (v2.getZ() < -0.9999999999d || v2.getZ() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(-v1.getY(), v1.getZ()), FastMath.asin(v2.getZ()), FastMath.atan2(-v2.getY(), v2.getX())};
            }
            if (order == RotationOrder.XZY) {
                Vector3D v12 = applyTo(Vector3D.PLUS_J);
                Vector3D v22 = applyInverseTo(Vector3D.PLUS_I);
                if (v22.getY() < -0.9999999999d || v22.getY() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(v12.getZ(), v12.getY()), -FastMath.asin(v22.getY()), FastMath.atan2(v22.getZ(), v22.getX())};
            }
            if (order == RotationOrder.YXZ) {
                Vector3D v13 = applyTo(Vector3D.PLUS_K);
                Vector3D v23 = applyInverseTo(Vector3D.PLUS_J);
                if (v23.getZ() < -0.9999999999d || v23.getZ() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(v13.getX(), v13.getZ()), -FastMath.asin(v23.getZ()), FastMath.atan2(v23.getX(), v23.getY())};
            }
            if (order == RotationOrder.YZX) {
                Vector3D v14 = applyTo(Vector3D.PLUS_I);
                Vector3D v24 = applyInverseTo(Vector3D.PLUS_J);
                if (v24.getX() < -0.9999999999d || v24.getX() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(-v14.getZ(), v14.getX()), FastMath.asin(v24.getX()), FastMath.atan2(-v24.getZ(), v24.getY())};
            }
            if (order == RotationOrder.ZXY) {
                Vector3D v15 = applyTo(Vector3D.PLUS_J);
                Vector3D v25 = applyInverseTo(Vector3D.PLUS_K);
                if (v25.getY() < -0.9999999999d || v25.getY() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(-v15.getX(), v15.getY()), FastMath.asin(v25.getY()), FastMath.atan2(-v25.getX(), v25.getZ())};
            }
            if (order == RotationOrder.ZYX) {
                Vector3D v16 = applyTo(Vector3D.PLUS_I);
                Vector3D v26 = applyInverseTo(Vector3D.PLUS_K);
                if (v26.getX() < -0.9999999999d || v26.getX() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[]{FastMath.atan2(v16.getY(), v16.getX()), -FastMath.asin(v26.getX()), FastMath.atan2(v26.getY(), v26.getZ())};
            }
            if (order == RotationOrder.XYX) {
                Vector3D v17 = applyTo(Vector3D.PLUS_I);
                Vector3D v27 = applyInverseTo(Vector3D.PLUS_I);
                if (v27.getX() < -0.9999999999d || v27.getX() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[]{FastMath.atan2(v17.getY(), -v17.getZ()), FastMath.acos(v27.getX()), FastMath.atan2(v27.getY(), v27.getZ())};
            }
            if (order == RotationOrder.XZX) {
                Vector3D v18 = applyTo(Vector3D.PLUS_I);
                Vector3D v28 = applyInverseTo(Vector3D.PLUS_I);
                if (v28.getX() < -0.9999999999d || v28.getX() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[]{FastMath.atan2(v18.getZ(), v18.getY()), FastMath.acos(v28.getX()), FastMath.atan2(v28.getZ(), -v28.getY())};
            }
            if (order == RotationOrder.YXY) {
                Vector3D v19 = applyTo(Vector3D.PLUS_J);
                Vector3D v29 = applyInverseTo(Vector3D.PLUS_J);
                if (v29.getY() < -0.9999999999d || v29.getY() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[]{FastMath.atan2(v19.getX(), v19.getZ()), FastMath.acos(v29.getY()), FastMath.atan2(v29.getX(), -v29.getZ())};
            }
            if (order == RotationOrder.YZY) {
                Vector3D v110 = applyTo(Vector3D.PLUS_J);
                Vector3D v210 = applyInverseTo(Vector3D.PLUS_J);
                if (v210.getY() < -0.9999999999d || v210.getY() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[]{FastMath.atan2(v110.getZ(), -v110.getX()), FastMath.acos(v210.getY()), FastMath.atan2(v210.getZ(), v210.getX())};
            }
            if (order == RotationOrder.ZXZ) {
                Vector3D v111 = applyTo(Vector3D.PLUS_K);
                Vector3D v211 = applyInverseTo(Vector3D.PLUS_K);
                if (v211.getZ() < -0.9999999999d || v211.getZ() > 0.9999999999d) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[]{FastMath.atan2(v111.getX(), -v111.getY()), FastMath.acos(v211.getZ()), FastMath.atan2(v211.getX(), v211.getY())};
            }
            Vector3D v112 = applyTo(Vector3D.PLUS_K);
            Vector3D v212 = applyInverseTo(Vector3D.PLUS_K);
            if (v212.getZ() < -0.9999999999d || v212.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v112.getY(), v112.getX()), FastMath.acos(v212.getZ()), FastMath.atan2(v212.getY(), -v212.getX())};
        }
        if (order == RotationOrder.XYZ) {
            Vector3D v113 = applyTo(Vector3D.PLUS_I);
            Vector3D v213 = applyInverseTo(Vector3D.PLUS_K);
            if (v213.getX() < -0.9999999999d || v213.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-v213.getY(), v213.getZ()), FastMath.asin(v213.getX()), FastMath.atan2(-v113.getY(), v113.getX())};
        }
        if (order == RotationOrder.XZY) {
            Vector3D v114 = applyTo(Vector3D.PLUS_I);
            Vector3D v214 = applyInverseTo(Vector3D.PLUS_J);
            if (v214.getX() < -0.9999999999d || v214.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(v214.getZ(), v214.getY()), -FastMath.asin(v214.getX()), FastMath.atan2(v114.getZ(), v114.getX())};
        }
        if (order == RotationOrder.YXZ) {
            Vector3D v115 = applyTo(Vector3D.PLUS_J);
            Vector3D v215 = applyInverseTo(Vector3D.PLUS_K);
            if (v215.getY() < -0.9999999999d || v215.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(v215.getX(), v215.getZ()), -FastMath.asin(v215.getY()), FastMath.atan2(v115.getX(), v115.getY())};
        }
        if (order == RotationOrder.YZX) {
            Vector3D v116 = applyTo(Vector3D.PLUS_J);
            Vector3D v216 = applyInverseTo(Vector3D.PLUS_I);
            if (v216.getY() < -0.9999999999d || v216.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-v216.getZ(), v216.getX()), FastMath.asin(v216.getY()), FastMath.atan2(-v116.getZ(), v116.getY())};
        }
        if (order == RotationOrder.ZXY) {
            Vector3D v117 = applyTo(Vector3D.PLUS_K);
            Vector3D v217 = applyInverseTo(Vector3D.PLUS_J);
            if (v217.getZ() < -0.9999999999d || v217.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(-v217.getX(), v217.getY()), FastMath.asin(v217.getZ()), FastMath.atan2(-v117.getX(), v117.getZ())};
        }
        if (order == RotationOrder.ZYX) {
            Vector3D v118 = applyTo(Vector3D.PLUS_K);
            Vector3D v218 = applyInverseTo(Vector3D.PLUS_I);
            if (v218.getZ() < -0.9999999999d || v218.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(true);
            }
            return new double[]{FastMath.atan2(v218.getY(), v218.getX()), -FastMath.asin(v218.getZ()), FastMath.atan2(v118.getY(), v118.getZ())};
        }
        if (order == RotationOrder.XYX) {
            Vector3D v119 = applyTo(Vector3D.PLUS_I);
            Vector3D v219 = applyInverseTo(Vector3D.PLUS_I);
            if (v219.getX() < -0.9999999999d || v219.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v219.getY(), -v219.getZ()), FastMath.acos(v219.getX()), FastMath.atan2(v119.getY(), v119.getZ())};
        }
        if (order == RotationOrder.XZX) {
            Vector3D v120 = applyTo(Vector3D.PLUS_I);
            Vector3D v220 = applyInverseTo(Vector3D.PLUS_I);
            if (v220.getX() < -0.9999999999d || v220.getX() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v220.getZ(), v220.getY()), FastMath.acos(v220.getX()), FastMath.atan2(v120.getZ(), -v120.getY())};
        }
        if (order == RotationOrder.YXY) {
            Vector3D v121 = applyTo(Vector3D.PLUS_J);
            Vector3D v221 = applyInverseTo(Vector3D.PLUS_J);
            if (v221.getY() < -0.9999999999d || v221.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v221.getX(), v221.getZ()), FastMath.acos(v221.getY()), FastMath.atan2(v121.getX(), -v121.getZ())};
        }
        if (order == RotationOrder.YZY) {
            Vector3D v122 = applyTo(Vector3D.PLUS_J);
            Vector3D v222 = applyInverseTo(Vector3D.PLUS_J);
            if (v222.getY() < -0.9999999999d || v222.getY() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v222.getZ(), -v222.getX()), FastMath.acos(v222.getY()), FastMath.atan2(v122.getZ(), v122.getX())};
        }
        if (order == RotationOrder.ZXZ) {
            Vector3D v123 = applyTo(Vector3D.PLUS_K);
            Vector3D v223 = applyInverseTo(Vector3D.PLUS_K);
            if (v223.getZ() < -0.9999999999d || v223.getZ() > 0.9999999999d) {
                throw new CardanEulerSingularityException(false);
            }
            return new double[]{FastMath.atan2(v223.getX(), -v223.getY()), FastMath.acos(v223.getZ()), FastMath.atan2(v123.getX(), v123.getY())};
        }
        Vector3D v124 = applyTo(Vector3D.PLUS_K);
        Vector3D v224 = applyInverseTo(Vector3D.PLUS_K);
        if (v224.getZ() < -0.9999999999d || v224.getZ() > 0.9999999999d) {
            throw new CardanEulerSingularityException(false);
        }
        return new double[]{FastMath.atan2(v224.getY(), v224.getX()), FastMath.acos(v224.getZ()), FastMath.atan2(v124.getY(), -v124.getX())};
    }

    public double[][] getMatrix() {
        double q0q0 = this.q0 * this.q0;
        double q0q1 = this.q0 * this.q1;
        double q0q2 = this.q0 * this.q2;
        double q0q3 = this.q0 * this.q3;
        double q1q1 = this.q1 * this.q1;
        double q1q2 = this.q1 * this.q2;
        double q1q3 = this.q1 * this.q3;
        double q2q2 = this.q2 * this.q2;
        double q2q22 = this.q2;
        double q2q3 = this.q3 * q2q22;
        double q3q3 = this.q3 * this.q3;
        double[][] m = {new double[3], new double[3], new double[3]};
        m[0][0] = ((q0q0 + q1q1) * 2.0d) - 1.0d;
        m[1][0] = (q1q2 - q0q3) * 2.0d;
        m[2][0] = (q1q3 + q0q2) * 2.0d;
        m[0][1] = (q1q2 + q0q3) * 2.0d;
        m[1][1] = ((q0q0 + q2q2) * 2.0d) - 1.0d;
        m[2][1] = (q2q3 - q0q1) * 2.0d;
        m[0][2] = (q1q3 - q0q2) * 2.0d;
        m[1][2] = (q2q3 + q0q1) * 2.0d;
        m[2][2] = ((q0q0 + q3q3) * 2.0d) - 1.0d;
        return m;
    }

    public Vector3D applyTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        double s = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        return new Vector3D((((this.q0 * ((this.q0 * x) - ((this.q2 * z) - (this.q3 * y)))) + (this.q1 * s)) * 2.0d) - x, (((this.q0 * ((this.q0 * y) - ((this.q3 * x) - (this.q1 * z)))) + (this.q2 * s)) * 2.0d) - y, (((this.q0 * ((this.q0 * z) - ((this.q1 * y) - (this.q2 * x)))) + (this.q3 * s)) * 2.0d) - z);
    }

    public void applyTo(double[] in, double[] out) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        double s = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        out[0] = (((this.q0 * ((this.q0 * x) - ((this.q2 * z) - (this.q3 * y)))) + (this.q1 * s)) * 2.0d) - x;
        out[1] = (((this.q0 * ((this.q0 * y) - ((this.q3 * x) - (this.q1 * z)))) + (this.q2 * s)) * 2.0d) - y;
        out[2] = (((this.q0 * ((this.q0 * z) - ((this.q1 * y) - (this.q2 * x)))) + (this.q3 * s)) * 2.0d) - z;
    }

    public Vector3D applyInverseTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        double s = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double m0 = -this.q0;
        return new Vector3D((((((x * m0) - ((this.q2 * z) - (this.q3 * y))) * m0) + (this.q1 * s)) * 2.0d) - x, (((((y * m0) - ((this.q3 * x) - (this.q1 * z))) * m0) + (this.q2 * s)) * 2.0d) - y, (((((z * m0) - ((this.q1 * y) - (this.q2 * x))) * m0) + (this.q3 * s)) * 2.0d) - z);
    }

    public void applyInverseTo(double[] in, double[] out) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        double s = (this.q1 * x) + (this.q2 * y) + (this.q3 * z);
        double m0 = -this.q0;
        out[0] = (((((x * m0) - ((this.q2 * z) - (this.q3 * y))) * m0) + (this.q1 * s)) * 2.0d) - x;
        out[1] = (((((y * m0) - ((this.q3 * x) - (this.q1 * z))) * m0) + (this.q2 * s)) * 2.0d) - y;
        out[2] = (((((z * m0) - ((this.q1 * y) - (this.q2 * x))) * m0) + (this.q3 * s)) * 2.0d) - z;
    }

    public Rotation applyTo(Rotation r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    public Rotation compose(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : r.composeInternal(this);
    }

    private Rotation composeInternal(Rotation r) {
        return new Rotation((r.q0 * this.q0) - (((r.q1 * this.q1) + (r.q2 * this.q2)) + (r.q3 * this.q3)), (r.q1 * this.q0) + (r.q0 * this.q1) + ((r.q2 * this.q3) - (r.q3 * this.q2)), (r.q2 * this.q0) + (r.q0 * this.q2) + ((r.q3 * this.q1) - (r.q1 * this.q3)), (r.q3 * this.q0) + (r.q0 * this.q3) + ((r.q1 * this.q2) - (r.q2 * this.q1)), false);
    }

    public Rotation applyInverseTo(Rotation r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    public Rotation composeInverse(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : r.composeInternal(revert());
    }

    private Rotation composeInverseInternal(Rotation r) {
        return new Rotation(((-r.q0) * this.q0) - (((r.q1 * this.q1) + (r.q2 * this.q2)) + (r.q3 * this.q3)), ((-r.q1) * this.q0) + (r.q0 * this.q1) + ((r.q2 * this.q3) - (r.q3 * this.q2)), ((-r.q2) * this.q0) + (r.q0 * this.q2) + ((r.q3 * this.q1) - (r.q1 * this.q3)), ((-r.q3) * this.q0) + (r.q0 * this.q3) + ((r.q1 * this.q2) - (r.q2 * this.q1)), false);
    }

    private double[][] orthogonalizeMatrix(double[][] m, double threshold) throws NotARotationMatrixException {
        double[] m0 = m[0];
        double[] m1 = m[1];
        int i = 2;
        double[] m2 = m[2];
        double x00 = m0[0];
        double x01 = m0[1];
        double x02 = m0[2];
        double x10 = m1[0];
        double x11 = m1[1];
        double x12 = m1[2];
        double x20 = m2[0];
        double x21 = m2[1];
        double x22 = m2[2];
        double fn = 0.0d;
        double[][] o = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 3, 3);
        double[] o0 = o[0];
        double[] o1 = o[1];
        double[] o2 = o[2];
        int i2 = 0;
        while (true) {
            int i3 = i;
            int i4 = i2 + 1;
            double[][] o3 = o;
            if (i4 < 11) {
                double mx00 = (m0[0] * x00) + (m1[0] * x10) + (m2[0] * x20);
                double mx10 = (m0[1] * x00) + (m1[1] * x10) + (m2[1] * x20);
                double mx20 = (m0[i3] * x00) + (m1[i3] * x10) + (m2[i3] * x20);
                double mx01 = (m0[0] * x01) + (m1[0] * x11) + (m2[0] * x21);
                double mx11 = (m0[1] * x01) + (m1[1] * x11) + (m2[1] * x21);
                double mx21 = (m0[i3] * x01) + (m1[i3] * x11) + (m2[i3] * x21);
                double mx02 = (m0[0] * x02) + (m1[0] * x12) + (m2[0] * x22);
                double mx12 = (m0[1] * x02) + (m1[1] * x12) + (m2[1] * x22);
                double mx22 = (m0[i3] * x02) + (m1[i3] * x12) + (m2[i3] * x22);
                o0[0] = x00 - (((((x00 * mx00) + (x01 * mx10)) + (x02 * mx20)) - m0[0]) * 0.5d);
                o0[1] = x01 - (((((x00 * mx01) + (x01 * mx11)) + (x02 * mx21)) - m0[1]) * 0.5d);
                o0[i3] = x02 - (((((x00 * mx02) + (x01 * mx12)) + (x02 * mx22)) - m0[i3]) * 0.5d);
                o1[0] = x10 - (((((x10 * mx00) + (x11 * mx10)) + (x12 * mx20)) - m1[0]) * 0.5d);
                o1[1] = x11 - (((((x10 * mx01) + (x11 * mx11)) + (x12 * mx21)) - m1[1]) * 0.5d);
                o1[i3] = x12 - (((((x10 * mx02) + (x11 * mx12)) + (x12 * mx22)) - m1[i3]) * 0.5d);
                o2[0] = x20 - (((((x20 * mx00) + (x21 * mx10)) + (x22 * mx20)) - m2[0]) * 0.5d);
                o2[1] = x21 - (((((x20 * mx01) + (x21 * mx11)) + (x22 * mx21)) - m2[1]) * 0.5d);
                o2[i3] = x22 - (((((x20 * mx02) + (x21 * mx12)) + (x22 * mx22)) - m2[i3]) * 0.5d);
                double corr00 = o0[0] - m0[0];
                double corr01 = o0[1] - m0[1];
                double corr02 = o0[i3] - m0[i3];
                double corr10 = o1[0] - m1[0];
                double corr11 = o1[1] - m1[1];
                double corr12 = o1[i3] - m1[i3];
                double corr20 = o2[0] - m2[0];
                double corr21 = o2[1] - m2[1];
                double corr22 = o2[i3] - m2[i3];
                double fn1 = (corr00 * corr00) + (corr01 * corr01) + (corr02 * corr02) + (corr10 * corr10) + (corr11 * corr11) + (corr12 * corr12) + (corr20 * corr20) + (corr21 * corr21) + (corr22 * corr22);
                if (FastMath.abs(fn1 - fn) <= threshold) {
                    return o3;
                }
                x00 = o0[0];
                x01 = o0[1];
                x02 = o0[i3];
                x10 = o1[0];
                x11 = o1[1];
                x12 = o1[i3];
                x20 = o2[0];
                x21 = o2[1];
                x22 = o2[i3];
                fn = fn1;
                o = o3;
                i2 = i4;
                i = i3;
            } else {
                throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, Integer.valueOf(i4 - 1));
            }
        }
    }

    public static double distance(Rotation r1, Rotation r2) {
        return r1.composeInverseInternal(r2).getAngle();
    }
}
