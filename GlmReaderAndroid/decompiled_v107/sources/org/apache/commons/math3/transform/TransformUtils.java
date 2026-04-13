package org.apache.commons.math3.transform;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class TransformUtils {
    private static final int[] POWERS_OF_TWO = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, 134217728, 268435456, 536870912, BasicMeasure.EXACTLY};

    private TransformUtils() {
    }

    public static double[] scaleArray(double[] f, double d) {
        for (int i = 0; i < f.length; i++) {
            f[i] = f[i] * d;
        }
        return f;
    }

    public static Complex[] scaleArray(Complex[] f, double d) {
        for (int i = 0; i < f.length; i++) {
            f[i] = new Complex(f[i].getReal() * d, f[i].getImaginary() * d);
        }
        return f;
    }

    public static double[][] createRealImaginaryArray(Complex[] dataC) {
        double[][] dataRI = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 2, dataC.length);
        double[] dataR = dataRI[0];
        double[] dataI = dataRI[1];
        for (int i = 0; i < dataC.length; i++) {
            Complex c = dataC[i];
            dataR[i] = c.getReal();
            dataI[i] = c.getImaginary();
        }
        return dataRI;
    }

    public static Complex[] createComplexArray(double[][] dataRI) throws DimensionMismatchException {
        if (dataRI.length != 2) {
            throw new DimensionMismatchException(dataRI.length, 2);
        }
        double[] dataR = dataRI[0];
        double[] dataI = dataRI[1];
        if (dataR.length != dataI.length) {
            throw new DimensionMismatchException(dataI.length, dataR.length);
        }
        int n = dataR.length;
        Complex[] c = new Complex[n];
        for (int i = 0; i < n; i++) {
            c[i] = new Complex(dataR[i], dataI[i]);
        }
        return c;
    }

    public static int exactLog2(int n) throws MathIllegalArgumentException {
        int index = Arrays.binarySearch(POWERS_OF_TWO, n);
        if (index < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(n));
        }
        return index;
    }
}
