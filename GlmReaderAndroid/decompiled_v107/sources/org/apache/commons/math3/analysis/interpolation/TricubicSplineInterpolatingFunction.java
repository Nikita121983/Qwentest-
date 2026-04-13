package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class TricubicSplineInterpolatingFunction implements TrivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, -9.0d, 0.0d, 0.0d, -9.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 3.0d, 0.0d, 0.0d, -6.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 3.0d, -3.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 2.0d, 1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, -3.0d, 0.0d, 0.0d, 3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, -2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, -1.0d, -1.0d, 0.0d, 0.0d}, new double[]{9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 9.0d, 0.0d, -9.0d, 0.0d, -9.0d, 0.0d, 9.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 6.0d, 0.0d, 3.0d, 0.0d, -6.0d, 0.0d, -3.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, 1.0d, 0.0d}, new double[]{-27.0d, 27.0d, 27.0d, -27.0d, 27.0d, -27.0d, -27.0d, 27.0d, -18.0d, -9.0d, 18.0d, 9.0d, 18.0d, 9.0d, -18.0d, -9.0d, -18.0d, 18.0d, -9.0d, 9.0d, 18.0d, -18.0d, 9.0d, -9.0d, -18.0d, 18.0d, 18.0d, -18.0d, -9.0d, 9.0d, 9.0d, -9.0d, -12.0d, -6.0d, -6.0d, -3.0d, 12.0d, 6.0d, 6.0d, 3.0d, -12.0d, -6.0d, 12.0d, 6.0d, -6.0d, -3.0d, 6.0d, 3.0d, -12.0d, 12.0d, -6.0d, 6.0d, -6.0d, 6.0d, -3.0d, 3.0d, -8.0d, -4.0d, -4.0d, -2.0d, -4.0d, -2.0d, -2.0d, -1.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 9.0d, 9.0d, -9.0d, -9.0d, -9.0d, -9.0d, 9.0d, 9.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 3.0d, 3.0d, -6.0d, -6.0d, -3.0d, -3.0d, 6.0d, 6.0d, -6.0d, -6.0d, 3.0d, 3.0d, -3.0d, -3.0d, 8.0d, -8.0d, 4.0d, -4.0d, 4.0d, -4.0d, 2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, 1.0d, 1.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 3.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 9.0d, -9.0d, 9.0d, -9.0d, -9.0d, 9.0d, -9.0d, 9.0d, 12.0d, -12.0d, -12.0d, 12.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, 3.0d, 6.0d, 3.0d, -6.0d, -3.0d, -6.0d, -3.0d, 8.0d, 4.0d, -8.0d, -4.0d, 4.0d, 2.0d, -4.0d, -2.0d, 6.0d, -6.0d, 6.0d, -6.0d, 3.0d, -3.0d, 3.0d, -3.0d, 4.0d, 2.0d, 4.0d, 2.0d, 2.0d, 1.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -8.0d, 8.0d, 8.0d, -8.0d, -4.0d, 4.0d, 4.0d, -4.0d, -3.0d, -3.0d, -3.0d, -3.0d, 3.0d, 3.0d, 3.0d, 3.0d, -4.0d, -4.0d, 4.0d, 4.0d, -2.0d, -2.0d, 2.0d, 2.0d, -4.0d, 4.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 6.0d, 0.0d, 0.0d, 6.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, -2.0d, 0.0d, 0.0d, 4.0d, 2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, -4.0d, 0.0d, 0.0d, -4.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 2.0d, 0.0d, 0.0d, -2.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -6.0d, 0.0d, 6.0d, 0.0d, 6.0d, 0.0d, -6.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -4.0d, 0.0d, -2.0d, 0.0d, 4.0d, 0.0d, 2.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{18.0d, -18.0d, -18.0d, 18.0d, -18.0d, 18.0d, 18.0d, -18.0d, 12.0d, 6.0d, -12.0d, -6.0d, -12.0d, -6.0d, 12.0d, 6.0d, 12.0d, -12.0d, 6.0d, -6.0d, -12.0d, 12.0d, -6.0d, 6.0d, 9.0d, -9.0d, -9.0d, 9.0d, 9.0d, -9.0d, -9.0d, 9.0d, 8.0d, 4.0d, 4.0d, 2.0d, -8.0d, -4.0d, -4.0d, -2.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -6.0d, -6.0d, 6.0d, 6.0d, 6.0d, 6.0d, -6.0d, -6.0d, -8.0d, 8.0d, -4.0d, 4.0d, 8.0d, -8.0d, 4.0d, -4.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -4.0d, -2.0d, -2.0d, 4.0d, 4.0d, 2.0d, 2.0d, -3.0d, -3.0d, 3.0d, 3.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 4.0d, 0.0d, -4.0d, 0.0d, -4.0d, 0.0d, 4.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-12.0d, 12.0d, 12.0d, -12.0d, 12.0d, -12.0d, -12.0d, 12.0d, -8.0d, -4.0d, 8.0d, 4.0d, 8.0d, 4.0d, -8.0d, -4.0d, -6.0d, 6.0d, -6.0d, 6.0d, 6.0d, -6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, -4.0d, -2.0d, 4.0d, 2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{8.0d, -8.0d, -8.0d, 8.0d, -8.0d, 8.0d, 8.0d, -8.0d, 4.0d, 4.0d, -4.0d, -4.0d, -4.0d, -4.0d, 4.0d, 4.0d, 4.0d, -4.0d, 4.0d, -4.0d, -4.0d, 4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, 2.0d, 2.0d, -2.0d, -2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private final TricubicSplineFunction[][][] splines;
    private final double[] xval;
    private final double[] yval;
    private final double[] zval;

    public TricubicSplineInterpolatingFunction(double[] x, double[] y, double[] z, double[][][] f, double[][][] dFdX, double[][][] dFdY, double[][][] dFdZ, double[][][] d2FdXdY, double[][][] d2FdXdZ, double[][][] d2FdYdZ, double[][][] d3FdXdYdZ) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        int xLen = x.length;
        int yLen = y.length;
        int zLen = z.length;
        if (xLen == 0 || yLen == 0 || z.length == 0 || f.length == 0 || f[0].length == 0) {
            throw new NoDataException();
        }
        if (xLen != f.length) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (xLen != dFdX.length) {
            throw new DimensionMismatchException(xLen, dFdX.length);
        }
        if (xLen != dFdY.length) {
            throw new DimensionMismatchException(xLen, dFdY.length);
        }
        if (xLen != dFdZ.length) {
            throw new DimensionMismatchException(xLen, dFdZ.length);
        }
        if (xLen != d2FdXdY.length) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
        }
        if (xLen != d2FdXdZ.length) {
            throw new DimensionMismatchException(xLen, d2FdXdZ.length);
        }
        if (xLen != d2FdYdZ.length) {
            throw new DimensionMismatchException(xLen, d2FdYdZ.length);
        }
        if (xLen != d3FdXdYdZ.length) {
            throw new DimensionMismatchException(xLen, d3FdXdYdZ.length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        MathArrays.checkOrder(z);
        this.xval = (double[]) x.clone();
        this.yval = (double[]) y.clone();
        this.zval = (double[]) z.clone();
        int lastI = xLen - 1;
        int lastJ = yLen - 1;
        int lastK = zLen - 1;
        this.splines = (TricubicSplineFunction[][][]) Array.newInstance((Class<?>) TricubicSplineFunction.class, lastI, lastJ, lastK);
        int j = 0;
        while (j < lastI) {
            if (f[j].length != yLen) {
                int i = j;
                throw new DimensionMismatchException(f[i].length, yLen);
            }
            if (dFdX[j].length != yLen) {
                int i2 = j;
                throw new DimensionMismatchException(dFdX[i2].length, yLen);
            }
            if (dFdY[j].length != yLen) {
                int i3 = j;
                throw new DimensionMismatchException(dFdY[i3].length, yLen);
            }
            if (dFdZ[j].length != yLen) {
                int i4 = j;
                throw new DimensionMismatchException(dFdZ[i4].length, yLen);
            }
            if (d2FdXdY[j].length != yLen) {
                int i5 = j;
                throw new DimensionMismatchException(d2FdXdY[i5].length, yLen);
            }
            if (d2FdXdZ[j].length != yLen) {
                int i6 = j;
                throw new DimensionMismatchException(d2FdXdZ[i6].length, yLen);
            }
            if (d2FdYdZ[j].length != yLen) {
                int i7 = j;
                throw new DimensionMismatchException(d2FdYdZ[i7].length, yLen);
            }
            if (d3FdXdYdZ[j].length != yLen) {
                int i8 = j;
                throw new DimensionMismatchException(d3FdXdYdZ[i8].length, yLen);
            }
            int ip1 = j + 1;
            int i9 = j;
            int j2 = 0;
            while (j2 < lastJ) {
                int lastJ2 = lastJ;
                if (f[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(f[i9][j2].length, zLen);
                }
                if (dFdX[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(dFdX[i9][j2].length, zLen);
                }
                if (dFdY[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(dFdY[i9][j2].length, zLen);
                }
                if (dFdZ[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(dFdZ[i9][j2].length, zLen);
                }
                if (d2FdXdY[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(d2FdXdY[i9][j2].length, zLen);
                }
                if (d2FdXdZ[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(d2FdXdZ[i9][j2].length, zLen);
                }
                if (d2FdYdZ[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(d2FdYdZ[i9][j2].length, zLen);
                }
                if (d3FdXdYdZ[i9][j2].length != zLen) {
                    throw new DimensionMismatchException(d3FdXdYdZ[i9][j2].length, zLen);
                }
                int jp1 = j2 + 1;
                int jp12 = 0;
                while (jp12 < lastK) {
                    int kp1 = jp12 + 1;
                    int k = jp12;
                    double[] beta = {f[i9][j2][jp12], f[ip1][j2][jp12], f[i9][jp1][jp12], f[ip1][jp1][jp12], f[i9][j2][kp1], f[ip1][j2][kp1], f[i9][jp1][kp1], f[ip1][jp1][kp1], dFdX[i9][j2][jp12], dFdX[ip1][j2][jp12], dFdX[i9][jp1][jp12], dFdX[ip1][jp1][jp12], dFdX[i9][j2][kp1], dFdX[ip1][j2][kp1], dFdX[i9][jp1][kp1], dFdX[ip1][jp1][kp1], dFdY[i9][j2][jp12], dFdY[ip1][j2][jp12], dFdY[i9][jp1][jp12], dFdY[ip1][jp1][jp12], dFdY[i9][j2][kp1], dFdY[ip1][j2][kp1], dFdY[i9][jp1][kp1], dFdY[ip1][jp1][kp1], dFdZ[i9][j2][jp12], dFdZ[ip1][j2][jp12], dFdZ[i9][jp1][jp12], dFdZ[ip1][jp1][jp12], dFdZ[i9][j2][kp1], dFdZ[ip1][j2][kp1], dFdZ[i9][jp1][kp1], dFdZ[ip1][jp1][kp1], d2FdXdY[i9][j2][jp12], d2FdXdY[ip1][j2][jp12], d2FdXdY[i9][jp1][jp12], d2FdXdY[ip1][jp1][jp12], d2FdXdY[i9][j2][kp1], d2FdXdY[ip1][j2][kp1], d2FdXdY[i9][jp1][kp1], d2FdXdY[ip1][jp1][kp1], d2FdXdZ[i9][j2][jp12], d2FdXdZ[ip1][j2][jp12], d2FdXdZ[i9][jp1][jp12], d2FdXdZ[ip1][jp1][jp12], d2FdXdZ[i9][j2][kp1], d2FdXdZ[ip1][j2][kp1], d2FdXdZ[i9][jp1][kp1], d2FdXdZ[ip1][jp1][kp1], d2FdYdZ[i9][j2][jp12], d2FdYdZ[ip1][j2][jp12], d2FdYdZ[i9][jp1][jp12], d2FdYdZ[ip1][jp1][jp12], d2FdYdZ[i9][j2][kp1], d2FdYdZ[ip1][j2][kp1], d2FdYdZ[i9][jp1][kp1], d2FdYdZ[ip1][jp1][kp1], d3FdXdYdZ[i9][j2][jp12], d3FdXdYdZ[ip1][j2][jp12], d3FdXdYdZ[i9][jp1][jp12], d3FdXdYdZ[ip1][jp1][jp12], d3FdXdYdZ[i9][j2][kp1], d3FdXdYdZ[ip1][j2][kp1], d3FdXdYdZ[i9][jp1][kp1], d3FdXdYdZ[ip1][jp1][kp1]};
                    int j3 = j2;
                    this.splines[i9][j3][k] = new TricubicSplineFunction(computeSplineCoefficients(beta));
                    jp12 = k + 1;
                    j2 = j3;
                    lastK = lastK;
                }
                j2++;
                lastJ = lastJ2;
            }
            j = i9 + 1;
        }
    }

    @Override // org.apache.commons.math3.analysis.TrivariateFunction
    public double value(double x, double y, double z) throws OutOfRangeException {
        int i = searchIndex(x, this.xval);
        if (i != -1) {
            int j = searchIndex(y, this.yval);
            if (j != -1) {
                int k = searchIndex(z, this.zval);
                if (k != -1) {
                    double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
                    double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
                    double zN = (z - this.zval[k]) / (this.zval[k + 1] - this.zval[k]);
                    return this.splines[i][j][k].value(xN, yN, zN);
                }
                throw new OutOfRangeException(Double.valueOf(z), Double.valueOf(this.zval[0]), Double.valueOf(this.zval[this.zval.length - 1]));
            }
            throw new OutOfRangeException(Double.valueOf(y), Double.valueOf(this.yval[0]), Double.valueOf(this.yval[this.yval.length - 1]));
        }
        throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(this.xval[0]), Double.valueOf(this.xval[this.xval.length - 1]));
    }

    private int searchIndex(double c, double[] val) {
        if (c < val[0]) {
            return -1;
        }
        int max = val.length;
        for (int i = 1; i < max; i++) {
            if (c <= val[i]) {
                return i - 1;
            }
        }
        return -1;
    }

    private double[] computeSplineCoefficients(double[] beta) {
        double[] a = new double[64];
        for (int i = 0; i < 64; i++) {
            double result = 0.0d;
            double[] row = AINV[i];
            for (int j = 0; j < 64; j++) {
                result += row[j] * beta[j];
            }
            a[i] = result;
        }
        return a;
    }
}
