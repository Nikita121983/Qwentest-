package org.apache.commons.math3.stat.regression;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class AbstractMultipleLinearRegression implements MultipleLinearRegression {
    private boolean noIntercept = false;
    private RealMatrix xMatrix;
    private RealVector yVector;

    protected abstract RealVector calculateBeta();

    protected abstract RealMatrix calculateBetaVariance();

    /* JADX INFO: Access modifiers changed from: protected */
    public RealMatrix getX() {
        return this.xMatrix;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RealVector getY() {
        return this.yVector;
    }

    public boolean isNoIntercept() {
        return this.noIntercept;
    }

    public void setNoIntercept(boolean noIntercept) {
        this.noIntercept = noIntercept;
    }

    public void newSampleData(double[] dArr, int i, int i2) {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        if (dArr.length != (i2 + 1) * i) {
            throw new DimensionMismatchException(dArr.length, (i2 + 1) * i);
        }
        if (i <= i2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(i), Integer.valueOf(i2 + 1));
        }
        double[] dArr2 = new double[i];
        int i3 = this.noIntercept ? i2 : i2 + 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i, i3);
        int i4 = 0;
        int i5 = 0;
        while (i5 < i) {
            int i6 = i4 + 1;
            dArr2[i5] = dArr[i4];
            if (!this.noIntercept) {
                dArr3[i5][0] = 1.0d;
            }
            int i7 = !this.noIntercept ? 1 : 0;
            while (i7 < i3) {
                dArr3[i5][i7] = dArr[i6];
                i7++;
                i6++;
            }
            i5++;
            i4 = i6;
        }
        this.xMatrix = new Array2DRowRealMatrix(dArr3);
        this.yVector = new ArrayRealVector(dArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void newYSampleData(double[] y) {
        if (y == null) {
            throw new NullArgumentException();
        }
        if (y.length == 0) {
            throw new NoDataException();
        }
        this.yVector = new ArrayRealVector(y);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void newXSampleData(double[][] x) {
        if (x == null) {
            throw new NullArgumentException();
        }
        if (x.length == 0) {
            throw new NoDataException();
        }
        if (this.noIntercept) {
            this.xMatrix = new Array2DRowRealMatrix(x, true);
            return;
        }
        int nVars = x[0].length;
        double[][] xAug = (double[][]) Array.newInstance((Class<?>) Double.TYPE, x.length, nVars + 1);
        for (int i = 0; i < x.length; i++) {
            if (x[i].length == nVars) {
                xAug[i][0] = 1.0d;
                System.arraycopy(x[i], 0, xAug[i], 1, nVars);
            } else {
                throw new DimensionMismatchException(x[i].length, nVars);
            }
        }
        this.xMatrix = new Array2DRowRealMatrix(xAug, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateSampleData(double[][] x, double[] y) throws MathIllegalArgumentException {
        if (x == null || y == null) {
            throw new NullArgumentException();
        }
        if (x.length != y.length) {
            throw new DimensionMismatchException(y.length, x.length);
        }
        if (x.length == 0) {
            throw new NoDataException();
        }
        if (x[0].length + 1 > x.length) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(x.length), Integer.valueOf(x[0].length));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateCovarianceData(double[][] x, double[][] covariance) {
        if (x.length != covariance.length) {
            throw new DimensionMismatchException(x.length, covariance.length);
        }
        if (covariance.length > 0 && covariance.length != covariance[0].length) {
            throw new NonSquareMatrixException(covariance.length, covariance[0].length);
        }
    }

    @Override // org.apache.commons.math3.stat.regression.MultipleLinearRegression
    public double[] estimateRegressionParameters() {
        RealVector b = calculateBeta();
        return b.toArray();
    }

    @Override // org.apache.commons.math3.stat.regression.MultipleLinearRegression
    public double[] estimateResiduals() {
        RealVector b = calculateBeta();
        RealVector e = this.yVector.subtract(this.xMatrix.operate(b));
        return e.toArray();
    }

    @Override // org.apache.commons.math3.stat.regression.MultipleLinearRegression
    public double[][] estimateRegressionParametersVariance() {
        return calculateBetaVariance().getData();
    }

    @Override // org.apache.commons.math3.stat.regression.MultipleLinearRegression
    public double[] estimateRegressionParametersStandardErrors() {
        double[][] betaVariance = estimateRegressionParametersVariance();
        double sigma = calculateErrorVariance();
        int length = betaVariance[0].length;
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = FastMath.sqrt(betaVariance[i][i] * sigma);
        }
        return result;
    }

    @Override // org.apache.commons.math3.stat.regression.MultipleLinearRegression
    public double estimateRegressandVariance() {
        return calculateYVariance();
    }

    public double estimateErrorVariance() {
        return calculateErrorVariance();
    }

    public double estimateRegressionStandardError() {
        return FastMath.sqrt(estimateErrorVariance());
    }

    protected double calculateYVariance() {
        return new Variance().evaluate(this.yVector.toArray());
    }

    protected double calculateErrorVariance() {
        RealVector residuals = calculateResiduals();
        return residuals.dotProduct(residuals) / (this.xMatrix.getRowDimension() - this.xMatrix.getColumnDimension());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RealVector calculateResiduals() {
        RealVector b = calculateBeta();
        return this.yVector.subtract(this.xMatrix.operate(b));
    }
}
