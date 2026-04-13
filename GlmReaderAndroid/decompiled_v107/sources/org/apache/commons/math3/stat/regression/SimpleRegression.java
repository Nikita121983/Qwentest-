package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class SimpleRegression implements Serializable, UpdatingMultipleLinearRegression {
    private static final long serialVersionUID = -3004689053607543335L;
    private final boolean hasIntercept;
    private long n;
    private double sumX;
    private double sumXX;
    private double sumXY;
    private double sumY;
    private double sumYY;
    private double xbar;
    private double ybar;

    public SimpleRegression() {
        this(true);
    }

    public SimpleRegression(boolean includeIntercept) {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0L;
        this.xbar = 0.0d;
        this.ybar = 0.0d;
        this.hasIntercept = includeIntercept;
    }

    public void addData(double x, double y) {
        if (this.n == 0) {
            this.xbar = x;
            this.ybar = y;
        } else if (this.hasIntercept) {
            double fact1 = this.n + 1.0d;
            double fact2 = this.n / (this.n + 1.0d);
            double dx = x - this.xbar;
            double dy = y - this.ybar;
            this.sumXX += dx * dx * fact2;
            this.sumYY += dy * dy * fact2;
            this.sumXY += dx * dy * fact2;
            this.xbar += dx / fact1;
            this.ybar += dy / fact1;
        }
        if (!this.hasIntercept) {
            this.sumXX += x * x;
            this.sumYY += y * y;
            this.sumXY += x * y;
        }
        this.sumX += x;
        this.sumY += y;
        this.n++;
    }

    public void append(SimpleRegression reg) {
        if (this.n == 0) {
            this.xbar = reg.xbar;
            this.ybar = reg.ybar;
            this.sumXX = reg.sumXX;
            this.sumYY = reg.sumYY;
            this.sumXY = reg.sumXY;
        } else if (this.hasIntercept) {
            double fact1 = reg.n / (reg.n + this.n);
            double fact2 = (this.n * reg.n) / (reg.n + this.n);
            double dx = reg.xbar - this.xbar;
            double dy = reg.ybar - this.ybar;
            this.sumXX += reg.sumXX + (dx * dx * fact2);
            this.sumYY += reg.sumYY + (dy * dy * fact2);
            this.sumXY += reg.sumXY + (dx * dy * fact2);
            this.xbar += dx * fact1;
            this.ybar += dy * fact1;
        } else {
            this.sumXX += reg.sumXX;
            this.sumYY += reg.sumYY;
            this.sumXY += reg.sumXY;
        }
        this.sumX += reg.sumX;
        this.sumY += reg.sumY;
        this.n += reg.n;
    }

    public void removeData(double x, double y) {
        if (this.n > 0) {
            if (this.hasIntercept) {
                double fact1 = this.n - 1.0d;
                double fact2 = this.n / (this.n - 1.0d);
                double dx = x - this.xbar;
                double dy = y - this.ybar;
                this.sumXX -= (dx * dx) * fact2;
                this.sumYY -= (dy * dy) * fact2;
                this.sumXY -= (dx * dy) * fact2;
                this.xbar -= dx / fact1;
                this.ybar -= dy / fact1;
            } else {
                double fact12 = this.n - 1.0d;
                this.sumXX -= x * x;
                this.sumYY -= y * y;
                this.sumXY -= x * y;
                this.xbar -= x / fact12;
                this.ybar -= y / fact12;
            }
            this.sumX -= x;
            this.sumY -= y;
            this.n--;
        }
    }

    public void addData(double[][] data) throws ModelSpecificationException {
        for (int i = 0; i < data.length; i++) {
            if (data[i].length < 2) {
                throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(data[i].length), 2);
            }
            addData(data[i][0], data[i][1]);
        }
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservation(double[] x, double y) throws ModelSpecificationException {
        if (x == null || x.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(x != null ? x.length : 0), 1);
        }
        addData(x[0], y);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservations(double[][] x, double[] y) throws ModelSpecificationException {
        if (x == null || y == null || x.length != y.length) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(x == null ? 0 : x.length), Integer.valueOf(y != null ? y.length : 0));
        }
        boolean obsOk = true;
        for (int i = 0; i < x.length; i++) {
            if (x[i] == null || x[i].length == 0) {
                obsOk = false;
            }
        }
        if (!obsOk) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, 0, 1);
        }
        for (int i2 = 0; i2 < x.length; i2++) {
            addData(x[i2][0], y[i2]);
        }
    }

    public void removeData(double[][] data) {
        for (int i = 0; i < data.length && this.n > 0; i++) {
            removeData(data[i][0], data[i][1]);
        }
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void clear() {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0L;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public long getN() {
        return this.n;
    }

    public double predict(double x) {
        double b1 = getSlope();
        if (this.hasIntercept) {
            return getIntercept(b1) + (b1 * x);
        }
        return b1 * x;
    }

    public double getIntercept() {
        if (this.hasIntercept) {
            return getIntercept(getSlope());
        }
        return 0.0d;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public double getSlope() {
        if (this.n >= 2 && FastMath.abs(this.sumXX) >= 4.9E-323d) {
            return this.sumXY / this.sumXX;
        }
        return Double.NaN;
    }

    public double getSumSquaredErrors() {
        return FastMath.max(0.0d, this.sumYY - ((this.sumXY * this.sumXY) / this.sumXX));
    }

    public double getTotalSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumYY;
    }

    public double getXSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumXX;
    }

    public double getSumOfCrossProducts() {
        return this.sumXY;
    }

    public double getRegressionSumSquares() {
        return getRegressionSumSquares(getSlope());
    }

    public double getMeanSquareError() {
        double sumSquaredErrors;
        long j;
        long j2;
        if (this.n < 3) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            sumSquaredErrors = getSumSquaredErrors();
            j = this.n;
            j2 = 2;
        } else {
            sumSquaredErrors = getSumSquaredErrors();
            j = this.n;
            j2 = 1;
        }
        return sumSquaredErrors / (j - j2);
    }

    public double getR() {
        double b1 = getSlope();
        double result = FastMath.sqrt(getRSquare());
        if (b1 < 0.0d) {
            return -result;
        }
        return result;
    }

    public double getRSquare() {
        double ssto = getTotalSumSquares();
        return (ssto - getSumSquaredErrors()) / ssto;
    }

    public double getInterceptStdErr() {
        if (!this.hasIntercept) {
            return Double.NaN;
        }
        return FastMath.sqrt(getMeanSquareError() * ((1.0d / this.n) + ((this.xbar * this.xbar) / this.sumXX)));
    }

    public double getSlopeStdErr() {
        return FastMath.sqrt(getMeanSquareError() / this.sumXX);
    }

    public double getSlopeConfidenceInterval() throws OutOfRangeException {
        return getSlopeConfidenceInterval(0.05d);
    }

    public double getSlopeConfidenceInterval(double alpha) throws OutOfRangeException {
        if (this.n < 3) {
            return Double.NaN;
        }
        if (alpha >= 1.0d || alpha <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, 1);
        }
        TDistribution distribution = new TDistribution(this.n - 2);
        return getSlopeStdErr() * distribution.inverseCumulativeProbability(1.0d - (alpha / 2.0d));
    }

    public double getSignificance() {
        if (this.n < 3) {
            return Double.NaN;
        }
        TDistribution distribution = new TDistribution(this.n - 2);
        return (1.0d - distribution.cumulativeProbability(FastMath.abs(getSlope()) / getSlopeStdErr())) * 2.0d;
    }

    private double getIntercept(double slope) {
        if (this.hasIntercept) {
            return (this.sumY - (this.sumX * slope)) / this.n;
        }
        return 0.0d;
    }

    private double getRegressionSumSquares(double slope) {
        return slope * slope * this.sumXX;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress() throws ModelSpecificationException, NoDataException {
        if (!this.hasIntercept) {
            if (this.n < 2) {
                throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
            }
            if (!Double.isNaN(this.sumXX)) {
                double[] vcv = {getMeanSquareError() / this.sumXX};
                double[] params = {this.sumXY / this.sumXX};
                return new RegressionResults(params, new double[][]{vcv}, true, this.n, 1, this.sumY, this.sumYY, getSumSquaredErrors(), false, false);
            }
            double[] vcv2 = {Double.NaN};
            double[] params2 = {Double.NaN};
            return new RegressionResults(params2, new double[][]{vcv2}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
        }
        if (this.n < 3) {
            throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
        }
        if (FastMath.abs(this.sumXX) > Precision.SAFE_MIN) {
            double[] params3 = {getIntercept(), getSlope()};
            double mse = getMeanSquareError();
            double _syy = this.sumYY + ((this.sumY * this.sumY) / this.n);
            double[] vcv3 = {(((this.xbar * this.xbar) / this.sumXX) + (1.0d / this.n)) * mse, ((-this.xbar) * mse) / this.sumXX, mse / this.sumXX};
            return new RegressionResults(params3, new double[][]{vcv3}, true, this.n, 2, this.sumY, _syy, getSumSquaredErrors(), true, false);
        }
        double[] params4 = {this.sumY / this.n, Double.NaN};
        double[] vcv4 = {this.ybar / (this.n - 1.0d), Double.NaN, Double.NaN};
        return new RegressionResults(params4, new double[][]{vcv4}, true, this.n, 1, this.sumY, this.sumYY, getSumSquaredErrors(), true, false);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress(int[] variablesToInclude) throws MathIllegalArgumentException {
        if (variablesToInclude == null || variablesToInclude.length == 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARRAY_ZERO_LENGTH_OR_NULL_NOT_ALLOWED, new Object[0]);
        }
        int i = 2;
        if (variablesToInclude.length > 2 || (variablesToInclude.length > 1 && !this.hasIntercept)) {
            LocalizedFormats localizedFormats = LocalizedFormats.ARRAY_SIZE_EXCEEDS_MAX_VARIABLES;
            if (variablesToInclude.length > 1 && !this.hasIntercept) {
                i = 1;
            }
            throw new ModelSpecificationException(localizedFormats, Integer.valueOf(i));
        }
        if (this.hasIntercept) {
            if (variablesToInclude.length == 2) {
                if (variablesToInclude[0] == 1) {
                    throw new ModelSpecificationException(LocalizedFormats.NOT_INCREASING_SEQUENCE, new Object[0]);
                }
                if (variablesToInclude[0] != 0) {
                    throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), 0, 1);
                }
                if (variablesToInclude[1] != 1) {
                    throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), 0, 1);
                }
                return regress();
            }
            if (variablesToInclude[0] == 1 || variablesToInclude[0] == 0) {
                double _mean = (this.sumY * this.sumY) / this.n;
                double _syy = this.sumYY + _mean;
                if (variablesToInclude[0] == 0) {
                    double[] vcv = {this.sumYY / ((this.n - 1) * this.n)};
                    double[] params = {this.ybar};
                    return new RegressionResults(params, new double[][]{vcv}, true, this.n, 1, this.sumY, _syy + _mean, this.sumYY, true, false);
                }
                if (variablesToInclude[0] == 1) {
                    double _sxx = this.sumXX + ((this.sumX * this.sumX) / this.n);
                    double _sxy = this.sumXY + ((this.sumX * this.sumY) / this.n);
                    double _sse = FastMath.max(0.0d, _syy - ((_sxy * _sxy) / _sxx));
                    double _mse = _sse / (this.n - 1);
                    if (!Double.isNaN(_sxx)) {
                        double[] vcv2 = {_mse / _sxx};
                        double[] params2 = {_sxy / _sxx};
                        return new RegressionResults(params2, new double[][]{vcv2}, true, this.n, 1, this.sumY, _syy, _sse, false, false);
                    }
                    double[] vcv3 = {Double.NaN};
                    double[] params3 = {Double.NaN};
                    return new RegressionResults(params3, new double[][]{vcv3}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
                }
                return null;
            }
            throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), 0, 1);
        }
        if (variablesToInclude[0] != 0) {
            throw new OutOfRangeException(Integer.valueOf(variablesToInclude[0]), 0, 0);
        }
        return regress();
    }
}
