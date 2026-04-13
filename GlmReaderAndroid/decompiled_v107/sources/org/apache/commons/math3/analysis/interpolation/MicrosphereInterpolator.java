package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;

@Deprecated
/* loaded from: classes10.dex */
public class MicrosphereInterpolator implements MultivariateInterpolator {
    public static final int DEFAULT_BRIGHTNESS_EXPONENT = 2;
    public static final int DEFAULT_MICROSPHERE_ELEMENTS = 2000;
    private final int brightnessExponent;
    private final int microsphereElements;

    public MicrosphereInterpolator() {
        this(2000, 2);
    }

    public MicrosphereInterpolator(int elements, int exponent) throws NotPositiveException, NotStrictlyPositiveException {
        if (exponent < 0) {
            throw new NotPositiveException(Integer.valueOf(exponent));
        }
        if (elements <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(elements));
        }
        this.microsphereElements = elements;
        this.brightnessExponent = exponent;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.MultivariateInterpolator
    public MultivariateFunction interpolate(double[][] xval, double[] yval) throws DimensionMismatchException, NoDataException, NullArgumentException {
        UnitSphereRandomVectorGenerator rand = new UnitSphereRandomVectorGenerator(xval[0].length);
        return new MicrosphereInterpolatingFunction(xval, yval, this.brightnessExponent, this.microsphereElements, rand);
    }
}
