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
public class FastSineTransformer implements RealTransformer, Serializable {
    static final long serialVersionUID = 20120211;
    private final DstNormalization normalization;

    public FastSineTransformer(DstNormalization normalization) {
        this.normalization = normalization;
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(double[] f, TransformType type) {
        if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
            double s = FastMath.sqrt(2.0d / f.length);
            return TransformUtils.scaleArray(fst(f), s);
        }
        if (type == TransformType.FORWARD) {
            return fst(f);
        }
        double s2 = 2.0d / f.length;
        return TransformUtils.scaleArray(fst(f), s2);
    }

    @Override // org.apache.commons.math3.transform.RealTransformer
    public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
        double[] data = FunctionUtils.sample(f, min, max, n);
        data[0] = 0.0d;
        return transform(data, type);
    }

    protected double[] fst(double[] f) throws MathIllegalArgumentException {
        double[] transformed = new double[f.length];
        if (!ArithmeticUtils.isPowerOfTwo(f.length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(f.length));
        }
        if (f[0] != 0.0d) {
            throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, Double.valueOf(f[0]));
        }
        int n = f.length;
        if (n == 1) {
            transformed[0] = 0.0d;
            return transformed;
        }
        double[] x = new double[n];
        x[0] = 0.0d;
        x[n >> 1] = f[n >> 1] * 2.0d;
        for (int i = 1; i < (n >> 1); i++) {
            double a = FastMath.sin((i * 3.141592653589793d) / n) * (f[i] + f[n - i]);
            double b = (f[i] - f[n - i]) * 0.5d;
            x[i] = a + b;
            x[n - i] = a - b;
        }
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] y = transformer.transform(x, TransformType.FORWARD);
        transformed[0] = 0.0d;
        transformed[1] = y[0].getReal() * 0.5d;
        for (int i2 = 1; i2 < (n >> 1); i2++) {
            transformed[i2 * 2] = -y[i2].getImaginary();
            transformed[(i2 * 2) + 1] = y[i2].getReal() + transformed[(i2 * 2) - 1];
        }
        return transformed;
    }
}
