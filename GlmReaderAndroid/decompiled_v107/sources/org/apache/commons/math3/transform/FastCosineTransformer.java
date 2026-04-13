package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class FastCosineTransformer implements RealTransformer, Serializable {
    static final long serialVersionUID = 20120212;
    private final DctNormalization normalization;

    public FastCosineTransformer(DctNormalization normalization) {
        this.normalization = normalization;
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(double[] f, TransformType type) throws MathIllegalArgumentException {
        double s1;
        if (type == TransformType.FORWARD) {
            if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
                double s = FastMath.sqrt(2.0d / (f.length - 1));
                return TransformUtils.scaleArray(fct(f), s);
            }
            return fct(f);
        }
        double s2 = 2.0d / (f.length - 1);
        if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
            s1 = FastMath.sqrt(s2);
        } else {
            s1 = s2;
        }
        return TransformUtils.scaleArray(fct(f), s1);
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) throws MathIllegalArgumentException {
        double[] data = FunctionUtils.sample(f, min, max, n);
        return transform(data, type);
    }

    protected double[] fct(double[] dArr) throws MathIllegalArgumentException {
        double[] dArr2 = new double[dArr.length];
        boolean z = true;
        int length = dArr.length - 1;
        if (!ArithmeticUtils.isPowerOfTwo(length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_PLUS_ONE, Integer.valueOf(dArr.length));
        }
        double d = 0.5d;
        boolean z2 = false;
        if (length == 1) {
            dArr2[0] = (dArr[0] + dArr[1]) * 0.5d;
            dArr2[1] = (dArr[0] - dArr[1]) * 0.5d;
            return dArr2;
        }
        double[] dArr3 = new double[length];
        dArr3[0] = (dArr[0] + dArr[length]) * 0.5d;
        dArr3[length >> 1] = dArr[length >> 1];
        double d2 = (dArr[0] - dArr[length]) * 0.5d;
        int i = 1;
        while (i < (length >> 1)) {
            double d3 = (dArr[i] + dArr[length - i]) * d;
            boolean z3 = z;
            double sin = FastMath.sin((i * 3.141592653589793d) / length) * (dArr[i] - dArr[length - i]);
            boolean z4 = z2;
            double[] dArr4 = dArr3;
            double cos = FastMath.cos((i * 3.141592653589793d) / length) * (dArr[i] - dArr[length - i]);
            dArr4[i] = d3 - sin;
            dArr4[length - i] = d3 + sin;
            d2 += cos;
            i++;
            z2 = z4;
            dArr3 = dArr4;
            z = z3;
            d = 0.5d;
        }
        boolean z5 = z2;
        Complex[] transform = new FastFourierTransformer(DftNormalization.STANDARD).transform(dArr3, TransformType.FORWARD);
        dArr2[z5 ? 1 : 0] = transform[z5 ? 1 : 0].getReal();
        dArr2[z ? 1 : 0] = d2;
        for (int i2 = 1; i2 < (length >> 1); i2++) {
            dArr2[i2 * 2] = transform[i2].getReal();
            dArr2[(i2 * 2) + 1] = dArr2[(i2 * 2) - 1] - transform[i2].getImaginary();
        }
        dArr2[length] = transform[length >> 1].getReal();
        return dArr2;
    }
}
