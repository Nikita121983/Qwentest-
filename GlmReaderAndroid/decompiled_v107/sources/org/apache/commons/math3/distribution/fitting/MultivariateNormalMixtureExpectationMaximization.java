package org.apache.commons.math3.distribution.fitting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes10.dex */
public class MultivariateNormalMixtureExpectationMaximization {
    private static final int DEFAULT_MAX_ITERATIONS = 1000;
    private static final double DEFAULT_THRESHOLD = 1.0E-5d;
    private final double[][] data;
    private MixtureMultivariateNormalDistribution fittedModel;
    private double logLikelihood = 0.0d;

    public MultivariateNormalMixtureExpectationMaximization(double[][] data) throws NotStrictlyPositiveException, DimensionMismatchException, NumberIsTooSmallException {
        if (data.length < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(data.length));
        }
        this.data = (double[][]) Array.newInstance((Class<?>) Double.TYPE, data.length, data[0].length);
        for (int i = 0; i < data.length; i++) {
            if (data[i].length != data[0].length) {
                throw new DimensionMismatchException(data[i].length, data[0].length);
            }
            if (data[i].length < 2) {
                throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(data[i].length), 2, true);
            }
            this.data[i] = MathArrays.copyOf(data[i], data[i].length);
        }
    }

    public void fit(MixtureMultivariateNormalDistribution mixtureMultivariateNormalDistribution, int i, double d) throws SingularMatrixException, NotStrictlyPositiveException, DimensionMismatchException {
        int i2 = i;
        boolean z = true;
        if (i2 < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        if (d < Double.MIN_VALUE) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
        int length = this.data.length;
        boolean z2 = false;
        int length2 = this.data[0].length;
        int size = mixtureMultivariateNormalDistribution.getComponents().size();
        int length3 = mixtureMultivariateNormalDistribution.getComponents().get(0).getSecond().getMeans().length;
        if (length3 != length2) {
            throw new DimensionMismatchException(length3, length2);
        }
        int i3 = 0;
        double d2 = 0.0d;
        this.logLikelihood = Double.NEGATIVE_INFINITY;
        this.fittedModel = new MixtureMultivariateNormalDistribution(mixtureMultivariateNormalDistribution.getComponents());
        while (true) {
            int i4 = i3 + 1;
            if (i3 > i2 || FastMath.abs(d2 - this.logLikelihood) <= d) {
                break;
            }
            double d3 = this.logLikelihood;
            double d4 = 0.0d;
            List<Pair<Double, MultivariateNormalDistribution>> components = this.fittedModel.getComponents();
            double[] dArr = new double[size];
            MultivariateNormalDistribution[] multivariateNormalDistributionArr = new MultivariateNormalDistribution[size];
            boolean z3 = z;
            for (int i5 = 0; i5 < size; i5++) {
                dArr[i5] = components.get(i5).getFirst().doubleValue();
                multivariateNormalDistributionArr[i5] = components.get(i5).getSecond();
            }
            boolean z4 = z2;
            int[] iArr = new int[2];
            iArr[z3 ? 1 : 0] = size;
            iArr[z4 ? 1 : 0] = length;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
            double[] dArr3 = new double[size];
            int[] iArr2 = new int[2];
            iArr2[z3 ? 1 : 0] = length2;
            iArr2[z4 ? 1 : 0] = size;
            double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr2);
            int i6 = 0;
            while (i6 < length) {
                double[][] dArr5 = dArr4;
                int i7 = i6;
                double density = this.fittedModel.density(this.data[i7]);
                d4 += FastMath.log(density);
                int i8 = 0;
                while (i8 < size) {
                    int i9 = i8;
                    dArr2[i7][i9] = (dArr[i8] * multivariateNormalDistributionArr[i9].density(this.data[i7])) / density;
                    dArr3[i9] = dArr3[i9] + dArr2[i7][i9];
                    int i10 = 0;
                    while (i10 < length2) {
                        double[] dArr6 = dArr5[i9];
                        int i11 = i10;
                        dArr6[i11] = dArr6[i10] + (dArr2[i7][i9] * this.data[i7][i11]);
                        i10 = i11 + 1;
                    }
                    i8 = i9 + 1;
                }
                i6 = i7 + 1;
                dArr4 = dArr5;
            }
            double[][] dArr7 = dArr4;
            this.logLikelihood = d4 / length;
            double[] dArr8 = new double[size];
            int[] iArr3 = new int[2];
            iArr3[z3 ? 1 : 0] = length2;
            iArr3[z4 ? 1 : 0] = size;
            double[][] dArr9 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr3);
            int i12 = 0;
            while (i12 < size) {
                double d5 = d3;
                List<Pair<Double, MultivariateNormalDistribution>> list = components;
                dArr8[i12] = dArr3[i12] / length;
                for (int i13 = 0; i13 < length2; i13++) {
                    dArr9[i12][i13] = dArr7[i12][i13] / dArr3[i12];
                }
                i12++;
                components = list;
                d3 = d5;
            }
            double d6 = d3;
            List<Pair<Double, MultivariateNormalDistribution>> list2 = components;
            RealMatrix[] realMatrixArr = new RealMatrix[size];
            for (int i14 = 0; i14 < size; i14++) {
                realMatrixArr[i14] = new Array2DRowRealMatrix(length2, length2);
            }
            int i15 = 0;
            while (i15 < length) {
                int i16 = 0;
                while (i16 < size) {
                    int i17 = length;
                    RealMatrix[] realMatrixArr2 = realMatrixArr;
                    int i18 = i15;
                    Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(MathArrays.ebeSubtract(this.data[i15], dArr9[i16]));
                    int i19 = i16;
                    realMatrixArr2[i19] = realMatrixArr2[i19].add(array2DRowRealMatrix.multiply(array2DRowRealMatrix.transpose()).scalarMultiply(dArr2[i18][i19]));
                    i16 = i19 + 1;
                    length = i17;
                    realMatrixArr = realMatrixArr2;
                    i15 = i18;
                    list2 = list2;
                }
                i15++;
            }
            int i20 = length;
            RealMatrix[] realMatrixArr3 = realMatrixArr;
            int[] iArr4 = new int[3];
            iArr4[2] = length2;
            iArr4[z3 ? 1 : 0] = length2;
            iArr4[z4 ? 1 : 0] = size;
            double[][][] dArr10 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr4);
            for (int i21 = 0; i21 < size; i21++) {
                realMatrixArr3[i21] = realMatrixArr3[i21].scalarMultiply(1.0d / dArr3[i21]);
                dArr10[i21] = realMatrixArr3[i21].getData();
            }
            this.fittedModel = new MixtureMultivariateNormalDistribution(dArr8, dArr9, dArr10);
            i2 = i;
            i3 = i4;
            z2 = z4 ? 1 : 0;
            z = z3 ? 1 : 0;
            length = i20;
            d2 = d6;
        }
        if (FastMath.abs(d2 - this.logLikelihood) > d) {
            throw new ConvergenceException();
        }
    }

    public void fit(MixtureMultivariateNormalDistribution initialMixture) throws SingularMatrixException, NotStrictlyPositiveException {
        fit(initialMixture, 1000, 1.0E-5d);
    }

    public static MixtureMultivariateNormalDistribution estimate(double[][] dArr, int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        int i2 = i;
        int i3 = 2;
        if (dArr.length < 2) {
            throw new NotStrictlyPositiveException(Integer.valueOf(dArr.length));
        }
        char c = 1;
        if (i2 < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        }
        if (i2 > dArr.length) {
            throw new NumberIsTooLargeException(Integer.valueOf(i), Integer.valueOf(dArr.length), true);
        }
        int length = dArr.length;
        boolean z = false;
        int length2 = dArr[0].length;
        DataRow[] dataRowArr = new DataRow[length];
        for (int i4 = 0; i4 < length; i4++) {
            dataRowArr[i4] = new DataRow(dArr[i4]);
        }
        Arrays.sort(dataRowArr);
        double d = 1.0d;
        double d2 = 1.0d / i2;
        ArrayList arrayList = new ArrayList(i2);
        int i5 = 0;
        while (i5 < i2) {
            int i6 = (i5 * length) / i2;
            int i7 = ((i5 + 1) * length) / i2;
            boolean z2 = z;
            int i8 = i7 - i6;
            double d3 = d;
            int[] iArr = new int[i3];
            iArr[c] = length2;
            iArr[z2 ? 1 : 0] = i8;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
            double[] dArr3 = new double[length2];
            int i9 = 0;
            int i10 = i3;
            int i11 = i6;
            while (i11 < i7) {
                for (int i12 = 0; i12 < length2; i12++) {
                    double d4 = dataRowArr[i11].getRow()[i12];
                    dArr3[i12] = dArr3[i12] + d4;
                    dArr2[i9][i12] = d4;
                }
                i11++;
                i9++;
            }
            MathArrays.scaleInPlace(d3 / i8, dArr3);
            arrayList.add(new Pair(Double.valueOf(d2), new MultivariateNormalDistribution(dArr3, new Covariance(dArr2).getCovarianceMatrix().getData())));
            i5++;
            i2 = i;
            z = z2 ? 1 : 0;
            d = d3;
            i3 = i10;
            c = 1;
        }
        return new MixtureMultivariateNormalDistribution(arrayList);
    }

    public double getLogLikelihood() {
        return this.logLikelihood;
    }

    public MixtureMultivariateNormalDistribution getFittedModel() {
        return new MixtureMultivariateNormalDistribution(this.fittedModel.getComponents());
    }

    /* loaded from: classes10.dex */
    private static class DataRow implements Comparable<DataRow> {
        private Double mean;
        private final double[] row;

        DataRow(double[] data) {
            this.row = data;
            this.mean = Double.valueOf(0.0d);
            for (double d : data) {
                this.mean = Double.valueOf(this.mean.doubleValue() + d);
            }
            this.mean = Double.valueOf(this.mean.doubleValue() / data.length);
        }

        @Override // java.lang.Comparable
        public int compareTo(DataRow other) {
            return this.mean.compareTo(other.mean);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof DataRow) {
                return MathArrays.equals(this.row, ((DataRow) other).row);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.row);
        }

        public double[] getRow() {
            return this.row;
        }
    }
}
