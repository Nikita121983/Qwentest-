package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class SparseGradient implements RealFieldElement<SparseGradient>, Serializable {
    private static final long serialVersionUID = 20131025;
    private final Map<Integer, Double> derivatives = new HashMap();
    private double value;

    private SparseGradient(double value, Map<Integer, Double> derivatives) {
        this.value = value;
        if (derivatives != null) {
            this.derivatives.putAll(derivatives);
        }
    }

    private SparseGradient(double value, double scale, Map<Integer, Double> derivatives) {
        this.value = value;
        if (derivatives != null) {
            for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
                this.derivatives.put(entry.getKey(), Double.valueOf(entry.getValue().doubleValue() * scale));
            }
        }
    }

    public static SparseGradient createConstant(double value) {
        return new SparseGradient(value, Collections.emptyMap());
    }

    public static SparseGradient createVariable(int idx, double value) {
        return new SparseGradient(value, Collections.singletonMap(Integer.valueOf(idx), Double.valueOf(1.0d)));
    }

    public int numVars() {
        return this.derivatives.size();
    }

    public double getDerivative(int index) {
        Double out = this.derivatives.get(Integer.valueOf(index));
        if (out == null) {
            return 0.0d;
        }
        return out.doubleValue();
    }

    public double getValue() {
        return this.value;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public double getReal() {
        return this.value;
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient add(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value + a.value, this.derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey().intValue();
            Double old = out.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                out.derivatives.put(Integer.valueOf(id), entry.getValue());
            } else {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() + entry.getValue().doubleValue()));
            }
        }
        return out;
    }

    public void addInPlace(SparseGradient a) {
        this.value += a.value;
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey().intValue();
            Double old = this.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                this.derivatives.put(Integer.valueOf(id), entry.getValue());
            } else {
                this.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() + entry.getValue().doubleValue()));
            }
        }
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient add(double c) {
        SparseGradient out = new SparseGradient(this.value + c, this.derivatives);
        return out;
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient subtract(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value - a.value, this.derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey().intValue();
            Double old = out.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(-entry.getValue().doubleValue()));
            } else {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() - entry.getValue().doubleValue()));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient subtract(double c) {
        return new SparseGradient(this.value - c, this.derivatives);
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient multiply(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value * a.value, Collections.emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), Double.valueOf(a.value * entry.getValue().doubleValue()));
        }
        for (Map.Entry<Integer, Double> entry2 : a.derivatives.entrySet()) {
            int id = entry2.getKey().intValue();
            Double old = out.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(this.value * entry2.getValue().doubleValue()));
            } else {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() + (this.value * entry2.getValue().doubleValue())));
            }
        }
        return out;
    }

    public void multiplyInPlace(SparseGradient a) {
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            this.derivatives.put(entry.getKey(), Double.valueOf(a.value * entry.getValue().doubleValue()));
        }
        for (Map.Entry<Integer, Double> entry2 : a.derivatives.entrySet()) {
            int id = entry2.getKey().intValue();
            Double old = this.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                this.derivatives.put(Integer.valueOf(id), Double.valueOf(this.value * entry2.getValue().doubleValue()));
            } else {
                this.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() + (this.value * entry2.getValue().doubleValue())));
            }
        }
        this.value *= a.value;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient multiply(double c) {
        return new SparseGradient(this.value * c, c, this.derivatives);
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient multiply(int n) {
        return new SparseGradient(this.value * n, n, this.derivatives);
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient divide(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value / a.value, Collections.emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), Double.valueOf(entry.getValue().doubleValue() / a.value));
        }
        for (Map.Entry<Integer, Double> entry2 : a.derivatives.entrySet()) {
            int id = entry2.getKey().intValue();
            Double old = out.derivatives.get(Integer.valueOf(id));
            if (old == null) {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(((-out.value) / a.value) * entry2.getValue().doubleValue()));
            } else {
                out.derivatives.put(Integer.valueOf(id), Double.valueOf(old.doubleValue() - ((out.value / a.value) * entry2.getValue().doubleValue())));
            }
        }
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient divide(double c) {
        return new SparseGradient(this.value / c, 1.0d / c, this.derivatives);
    }

    @Override // org.apache.commons.math3.FieldElement
    public SparseGradient negate() {
        return new SparseGradient(-this.value, -1.0d, this.derivatives);
    }

    @Override // org.apache.commons.math3.FieldElement
    public Field<SparseGradient> getField() {
        return new Field<SparseGradient>() { // from class: org.apache.commons.math3.analysis.differentiation.SparseGradient.1
            @Override // org.apache.commons.math3.Field
            public SparseGradient getZero() {
                return SparseGradient.createConstant(0.0d);
            }

            @Override // org.apache.commons.math3.Field
            public SparseGradient getOne() {
                return SparseGradient.createConstant(1.0d);
            }

            @Override // org.apache.commons.math3.Field
            public Class<? extends FieldElement<SparseGradient>> getRuntimeClass() {
                return SparseGradient.class;
            }
        };
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient remainder(double a) {
        return new SparseGradient(FastMath.IEEEremainder(this.value, a), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient remainder(SparseGradient a) {
        double rem = FastMath.IEEEremainder(this.value, a.value);
        double k = FastMath.rint((this.value - rem) / a.value);
        return subtract(a.multiply(k));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient abs() {
        if (Double.doubleToLongBits(this.value) < 0) {
            return negate();
        }
        return this;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient ceil() {
        return createConstant(FastMath.ceil(this.value));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient floor() {
        return createConstant(FastMath.floor(this.value));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient rint() {
        return createConstant(FastMath.rint(this.value));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public long round() {
        return FastMath.round(this.value);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient signum() {
        return createConstant(FastMath.signum(this.value));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient copySign(SparseGradient sign) {
        long m = Double.doubleToLongBits(this.value);
        long s = Double.doubleToLongBits(sign.value);
        if ((m >= 0 && s >= 0) || (m < 0 && s < 0)) {
            return this;
        }
        return negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient copySign(double sign) {
        long m = Double.doubleToLongBits(this.value);
        long s = Double.doubleToLongBits(sign);
        if ((m >= 0 && s >= 0) || (m < 0 && s < 0)) {
            return this;
        }
        return negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient scalb(int n) {
        SparseGradient out = new SparseGradient(FastMath.scalb(this.value, n), Collections.emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), Double.valueOf(FastMath.scalb(entry.getValue().doubleValue(), n)));
        }
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient hypot(SparseGradient y) {
        if (Double.isInfinite(this.value) || Double.isInfinite(y.value)) {
            return createConstant(Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(this.value) || Double.isNaN(y.value)) {
            return createConstant(Double.NaN);
        }
        int expX = FastMath.getExponent(this.value);
        int expY = FastMath.getExponent(y.value);
        if (expX > expY + 27) {
            return abs();
        }
        if (expY > expX + 27) {
            return y.abs();
        }
        int middleExp = (expX + expY) / 2;
        SparseGradient scaledX = scalb(-middleExp);
        SparseGradient scaledY = y.scalb(-middleExp);
        SparseGradient scaledH = scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).sqrt();
        return scaledH.scalb(middleExp);
    }

    public static SparseGradient hypot(SparseGradient x, SparseGradient y) {
        return x.hypot(y);
    }

    @Override // org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.FieldElement
    public SparseGradient reciprocal() {
        return new SparseGradient(1.0d / this.value, (-1.0d) / (this.value * this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient sqrt() {
        double sqrt = FastMath.sqrt(this.value);
        return new SparseGradient(sqrt, 0.5d / sqrt, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient cbrt() {
        double cbrt = FastMath.cbrt(this.value);
        return new SparseGradient(cbrt, 1.0d / ((3.0d * cbrt) * cbrt), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient rootN(int n) {
        if (n == 2) {
            return sqrt();
        }
        if (n == 3) {
            return cbrt();
        }
        double root = FastMath.pow(this.value, 1.0d / n);
        return new SparseGradient(root, 1.0d / (n * FastMath.pow(root, n - 1)), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient pow(double p) {
        return new SparseGradient(FastMath.pow(this.value, p), FastMath.pow(this.value, p - 1.0d) * p, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient pow(int n) {
        if (n == 0) {
            return getField().getOne();
        }
        double valueNm1 = FastMath.pow(this.value, n - 1);
        return new SparseGradient(this.value * valueNm1, n * valueNm1, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient pow(SparseGradient e) {
        return log().multiply(e).exp();
    }

    public static SparseGradient pow(double a, SparseGradient x) {
        if (a != 0.0d) {
            double ax = FastMath.pow(a, x.value);
            return new SparseGradient(ax, ax * FastMath.log(a), x.derivatives);
        }
        if (x.value == 0.0d) {
            return x.compose(1.0d, Double.NEGATIVE_INFINITY);
        }
        if (x.value < 0.0d) {
            return x.compose(Double.NaN, Double.NaN);
        }
        return x.getField().getZero();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient exp() {
        double e = FastMath.exp(this.value);
        return new SparseGradient(e, e, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient expm1() {
        return new SparseGradient(FastMath.expm1(this.value), FastMath.exp(this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient log() {
        return new SparseGradient(FastMath.log(this.value), 1.0d / this.value, this.derivatives);
    }

    public SparseGradient log10() {
        return new SparseGradient(FastMath.log10(this.value), 1.0d / (FastMath.log(10.0d) * this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient log1p() {
        return new SparseGradient(FastMath.log1p(this.value), 1.0d / (this.value + 1.0d), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient cos() {
        return new SparseGradient(FastMath.cos(this.value), -FastMath.sin(this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient sin() {
        return new SparseGradient(FastMath.sin(this.value), FastMath.cos(this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient tan() {
        double t = FastMath.tan(this.value);
        return new SparseGradient(t, (t * t) + 1.0d, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient acos() {
        return new SparseGradient(FastMath.acos(this.value), (-1.0d) / FastMath.sqrt(1.0d - (this.value * this.value)), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient asin() {
        return new SparseGradient(FastMath.asin(this.value), 1.0d / FastMath.sqrt(1.0d - (this.value * this.value)), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient atan() {
        return new SparseGradient(FastMath.atan(this.value), 1.0d / ((this.value * this.value) + 1.0d), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient atan2(SparseGradient x) {
        SparseGradient tmp;
        SparseGradient r = multiply(this).add(x.multiply(x)).sqrt();
        if (x.value >= 0.0d) {
            tmp = divide(r.add(x)).atan().multiply(2);
        } else {
            SparseGradient a = r.subtract(x);
            SparseGradient tmp2 = divide(a).atan().multiply(-2);
            tmp = tmp2.add(tmp2.value <= 0.0d ? -3.141592653589793d : 3.141592653589793d);
        }
        tmp.value = FastMath.atan2(this.value, x.value);
        return tmp;
    }

    public static SparseGradient atan2(SparseGradient y, SparseGradient x) {
        return y.atan2(x);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient cosh() {
        return new SparseGradient(FastMath.cosh(this.value), FastMath.sinh(this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient sinh() {
        return new SparseGradient(FastMath.sinh(this.value), FastMath.cosh(this.value), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient tanh() {
        double t = FastMath.tanh(this.value);
        return new SparseGradient(t, 1.0d - (t * t), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient acosh() {
        return new SparseGradient(FastMath.acosh(this.value), 1.0d / FastMath.sqrt((this.value * this.value) - 1.0d), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient asinh() {
        return new SparseGradient(FastMath.asinh(this.value), 1.0d / FastMath.sqrt((this.value * this.value) + 1.0d), this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient atanh() {
        return new SparseGradient(FastMath.atanh(this.value), 1.0d / (1.0d - (this.value * this.value)), this.derivatives);
    }

    public SparseGradient toDegrees() {
        return new SparseGradient(FastMath.toDegrees(this.value), FastMath.toDegrees(1.0d), this.derivatives);
    }

    public SparseGradient toRadians() {
        return new SparseGradient(FastMath.toRadians(this.value), FastMath.toRadians(1.0d), this.derivatives);
    }

    public double taylor(double... delta) {
        double y = this.value;
        for (int i = 0; i < delta.length; i++) {
            y += delta[i] * getDerivative(i);
        }
        return y;
    }

    public SparseGradient compose(double f0, double f1) {
        return new SparseGradient(f0, f1, this.derivatives);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(SparseGradient[] a, SparseGradient[] b) throws DimensionMismatchException {
        SparseGradient out = a[0].getField().getZero();
        for (int i = 0; i < a.length; i++) {
            out = out.add(a[i].multiply(b[i]));
        }
        int i2 = a.length;
        double[] aDouble = new double[i2];
        for (int i3 = 0; i3 < a.length; i3++) {
            aDouble[i3] = a[i3].getValue();
        }
        int i4 = b.length;
        double[] bDouble = new double[i4];
        for (int i5 = 0; i5 < b.length; i5++) {
            bDouble[i5] = b[i5].getValue();
        }
        out.value = MathArrays.linearCombination(aDouble, bDouble);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(double[] a, SparseGradient[] b) {
        SparseGradient out = b[0].getField().getZero();
        for (int i = 0; i < a.length; i++) {
            out = out.add(b[i].multiply(a[i]));
        }
        int i2 = b.length;
        double[] bDouble = new double[i2];
        for (int i3 = 0; i3 < b.length; i3++) {
            bDouble[i3] = b[i3].getValue();
        }
        out.value = MathArrays.linearCombination(a, bDouble);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2, SparseGradient a3, SparseGradient b3) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2, double a3, SparseGradient b3) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2, SparseGradient a3, SparseGradient b3, SparseGradient a4, SparseGradient b4) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value);
        return out;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2, double a3, SparseGradient b3, double a4, SparseGradient b4) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value);
        return out;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SparseGradient)) {
            return false;
        }
        SparseGradient rhs = (SparseGradient) other;
        if (!Precision.equals(this.value, rhs.value, 1) || this.derivatives.size() != rhs.derivatives.size()) {
            return false;
        }
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            if (!rhs.derivatives.containsKey(entry.getKey()) || !Precision.equals(entry.getValue().doubleValue(), rhs.derivatives.get(entry.getKey()).doubleValue(), 1)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return (MathUtils.hash(this.value) * 809) + 743 + (this.derivatives.hashCode() * 167);
    }
}
