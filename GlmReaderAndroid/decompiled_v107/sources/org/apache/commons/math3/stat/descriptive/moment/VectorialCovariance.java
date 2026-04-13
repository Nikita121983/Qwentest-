package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes10.dex */
public class VectorialCovariance implements Serializable {
    private static final long serialVersionUID = 4118372414238930270L;
    private final boolean isBiasCorrected;
    private long n = 0;
    private final double[] productsSums;
    private final double[] sums;

    public VectorialCovariance(int dimension, boolean isBiasCorrected) {
        this.sums = new double[dimension];
        this.productsSums = new double[((dimension + 1) * dimension) / 2];
        this.isBiasCorrected = isBiasCorrected;
    }

    public void increment(double[] v) throws DimensionMismatchException {
        if (v.length != this.sums.length) {
            throw new DimensionMismatchException(v.length, this.sums.length);
        }
        int k = 0;
        for (int i = 0; i < v.length; i++) {
            double[] dArr = this.sums;
            dArr[i] = dArr[i] + v[i];
            int j = 0;
            while (j <= i) {
                double[] dArr2 = this.productsSums;
                dArr2[k] = dArr2[k] + (v[i] * v[j]);
                j++;
                k++;
            }
        }
        this.n++;
    }

    public RealMatrix getResult() {
        int dimension = this.sums.length;
        RealMatrix result = MatrixUtils.createRealMatrix(dimension, dimension);
        if (this.n > 1) {
            double c = 1.0d / (this.n * (this.isBiasCorrected ? this.n - 1 : this.n));
            int k = 0;
            for (int i = 0; i < dimension; i++) {
                int j = 0;
                while (j <= i) {
                    double e = ((this.n * this.productsSums[k]) - (this.sums[i] * this.sums[j])) * c;
                    result.setEntry(i, j, e);
                    result.setEntry(j, i, e);
                    j++;
                    k++;
                }
            }
        }
        return result;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.n = 0L;
        Arrays.fill(this.sums, 0.0d);
        Arrays.fill(this.productsSums, 0.0d);
    }

    public int hashCode() {
        int result = (1 * 31) + (this.isBiasCorrected ? 1231 : 1237);
        return (((((result * 31) + ((int) (this.n ^ (this.n >>> 32)))) * 31) + Arrays.hashCode(this.productsSums)) * 31) + Arrays.hashCode(this.sums);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance other = (VectorialCovariance) obj;
        return this.isBiasCorrected == other.isBiasCorrected && this.n == other.n && Arrays.equals(this.productsSums, other.productsSums) && Arrays.equals(this.sums, other.sums);
    }
}
