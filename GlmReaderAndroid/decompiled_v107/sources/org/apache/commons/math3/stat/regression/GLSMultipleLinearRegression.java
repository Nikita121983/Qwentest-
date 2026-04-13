package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/* loaded from: classes10.dex */
public class GLSMultipleLinearRegression extends AbstractMultipleLinearRegression {
    private RealMatrix Omega;
    private RealMatrix OmegaInverse;

    public void newSampleData(double[] y, double[][] x, double[][] covariance) {
        validateSampleData(x, y);
        newYSampleData(y);
        newXSampleData(x);
        validateCovarianceData(x, covariance);
        newCovarianceData(covariance);
    }

    protected void newCovarianceData(double[][] omega) {
        this.Omega = new Array2DRowRealMatrix(omega);
        this.OmegaInverse = null;
    }

    protected RealMatrix getOmegaInverse() {
        if (this.OmegaInverse == null) {
            this.OmegaInverse = new LUDecomposition(this.Omega).getSolver().getInverse();
        }
        return this.OmegaInverse;
    }

    @Override // org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression
    protected RealVector calculateBeta() {
        RealMatrix OI = getOmegaInverse();
        RealMatrix XT = getX().transpose();
        RealMatrix XTOIX = XT.multiply(OI).multiply(getX());
        RealMatrix inverse = new LUDecomposition(XTOIX).getSolver().getInverse();
        return inverse.multiply(XT).multiply(OI).operate(getY());
    }

    @Override // org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression
    protected RealMatrix calculateBetaVariance() {
        RealMatrix OI = getOmegaInverse();
        RealMatrix XTOIX = getX().transpose().multiply(OI).multiply(getX());
        return new LUDecomposition(XTOIX).getSolver().getInverse();
    }

    @Override // org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression
    protected double calculateErrorVariance() {
        RealVector residuals = calculateResiduals();
        double t = residuals.dotProduct(getOmegaInverse().operate(residuals));
        return t / (getX().getRowDimension() - getX().getColumnDimension());
    }
}
