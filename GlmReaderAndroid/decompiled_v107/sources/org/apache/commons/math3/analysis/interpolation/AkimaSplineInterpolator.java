package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class AkimaSplineInterpolator implements UnivariateInterpolator {
    private static final int MINIMUM_NUMBER_POINTS = 5;

    @Override // org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator
    public PolynomialSplineFunction interpolate(double[] xvals, double[] yvals) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (xvals == null || yvals == null) {
            throw new NullArgumentException();
        }
        if (xvals.length != yvals.length) {
            throw new DimensionMismatchException(xvals.length, yvals.length);
        }
        if (xvals.length < 5) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(xvals.length), 5, true);
        }
        MathArrays.checkOrder(xvals);
        int numberOfDiffAndWeightElements = xvals.length - 1;
        double[] differences = new double[numberOfDiffAndWeightElements];
        double[] weights = new double[numberOfDiffAndWeightElements];
        for (int i = 0; i < differences.length; i++) {
            differences[i] = (yvals[i + 1] - yvals[i]) / (xvals[i + 1] - xvals[i]);
        }
        for (int i2 = 1; i2 < weights.length; i2++) {
            weights[i2] = FastMath.abs(differences[i2] - differences[i2 - 1]);
        }
        int i3 = xvals.length;
        double[] firstDerivatives = new double[i3];
        for (int i4 = 2; i4 < firstDerivatives.length - 2; i4++) {
            double wP = weights[i4 + 1];
            double wM = weights[i4 - 1];
            if (Precision.equals(wP, 0.0d) && Precision.equals(wM, 0.0d)) {
                double xv = xvals[i4];
                double xvP = xvals[i4 + 1];
                double xvM = xvals[i4 - 1];
                firstDerivatives[i4] = (((xvP - xv) * differences[i4 - 1]) + ((xv - xvM) * differences[i4])) / (xvP - xvM);
            } else {
                firstDerivatives[i4] = ((differences[i4 - 1] * wP) + (differences[i4] * wM)) / (wP + wM);
            }
        }
        firstDerivatives[0] = differentiateThreePoint(xvals, yvals, 0, 0, 1, 2);
        firstDerivatives[1] = differentiateThreePoint(xvals, yvals, 1, 0, 1, 2);
        firstDerivatives[xvals.length - 2] = differentiateThreePoint(xvals, yvals, xvals.length - 2, xvals.length - 3, xvals.length - 2, xvals.length - 1);
        firstDerivatives[xvals.length - 1] = differentiateThreePoint(xvals, yvals, xvals.length - 1, xvals.length - 3, xvals.length - 2, xvals.length - 1);
        return interpolateHermiteSorted(xvals, yvals, firstDerivatives);
    }

    private double differentiateThreePoint(double[] xvals, double[] yvals, int indexOfDifferentiation, int indexOfFirstSample, int indexOfSecondsample, int indexOfThirdSample) {
        double x0 = yvals[indexOfFirstSample];
        double x1 = yvals[indexOfSecondsample];
        double x2 = yvals[indexOfThirdSample];
        double t = xvals[indexOfDifferentiation] - xvals[indexOfFirstSample];
        double t1 = xvals[indexOfSecondsample] - xvals[indexOfFirstSample];
        double t2 = xvals[indexOfThirdSample] - xvals[indexOfFirstSample];
        double a = ((x2 - x0) - ((t2 / t1) * (x1 - x0))) / ((t2 * t2) - (t1 * t2));
        double b = ((x1 - x0) - ((a * t1) * t1)) / t1;
        return (2.0d * a * t) + b;
    }

    private PolynomialSplineFunction interpolateHermiteSorted(double[] xvals, double[] yvals, double[] firstDerivatives) {
        if (xvals.length != yvals.length) {
            throw new DimensionMismatchException(xvals.length, yvals.length);
        }
        if (xvals.length != firstDerivatives.length) {
            throw new DimensionMismatchException(xvals.length, firstDerivatives.length);
        }
        char c = 2;
        if (xvals.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(xvals.length), 2, true);
        }
        int size = xvals.length - 1;
        PolynomialFunction[] polynomials = new PolynomialFunction[size];
        double[] coefficients = new double[4];
        int i = 0;
        while (i < polynomials.length) {
            double w = xvals[i + 1] - xvals[i];
            double w2 = w * w;
            double yv = yvals[i];
            double yvP = yvals[i + 1];
            double fd = firstDerivatives[i];
            double fdP = firstDerivatives[i + 1];
            coefficients[0] = yv;
            coefficients[1] = firstDerivatives[i];
            coefficients[c] = (((((yvP - yv) * 3.0d) / w) - (fd * 2.0d)) - fdP) / w;
            coefficients[3] = (((((yv - yvP) * 2.0d) / w) + fd) + fdP) / w2;
            polynomials[i] = new PolynomialFunction(coefficients);
            i++;
            c = c;
        }
        return new PolynomialSplineFunction(xvals, polynomials);
    }
}
