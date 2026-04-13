package org.apache.commons.math3.stat.regression;

import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {
    private final double[] d;
    private final double epsilon;
    private boolean hasIntercept;
    private final boolean[] lindep;
    private long nobs;
    private final int nvars;
    private final double[] r;
    private final double[] rhs;
    private final double[] rss;
    private boolean rss_set;
    private double sserr;
    private double sumsqy;
    private double sumy;
    private final double[] tol;
    private boolean tol_set;
    private final int[] vorder;
    private final double[] work_sing;
    private final double[] work_tolset;
    private final double[] x_sing;

    private MillerUpdatingRegression() {
        this(-1, false, Double.NaN);
    }

    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant, double errorTolerance) throws ModelSpecificationException {
        this.nobs = 0L;
        this.sserr = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        if (numberOfVariables < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (includeConstant) {
            this.nvars = numberOfVariables + 1;
        } else {
            this.nvars = numberOfVariables;
        }
        this.hasIntercept = includeConstant;
        this.nobs = 0L;
        this.d = new double[this.nvars];
        this.rhs = new double[this.nvars];
        this.r = new double[(this.nvars * (this.nvars - 1)) / 2];
        this.tol = new double[this.nvars];
        this.rss = new double[this.nvars];
        this.vorder = new int[this.nvars];
        this.x_sing = new double[this.nvars];
        this.work_sing = new double[this.nvars];
        this.work_tolset = new double[this.nvars];
        this.lindep = new boolean[this.nvars];
        for (int i = 0; i < this.nvars; i++) {
            this.vorder[i] = i;
        }
        if (errorTolerance > 0.0d) {
            this.epsilon = errorTolerance;
        } else {
            this.epsilon = -errorTolerance;
        }
    }

    public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant) throws ModelSpecificationException {
        this(numberOfVariables, includeConstant, Precision.EPSILON);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public long getN() {
        return this.nobs;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservation(double[] x, double y) throws ModelSpecificationException {
        MillerUpdatingRegression millerUpdatingRegression;
        if ((!this.hasIntercept && x.length != this.nvars) || (this.hasIntercept && x.length + 1 != this.nvars)) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(x.length), Integer.valueOf(this.nvars));
        }
        if (!this.hasIntercept) {
            millerUpdatingRegression = this;
            millerUpdatingRegression.include(MathArrays.copyOf(x, x.length), 1.0d, y);
        } else {
            double[] tmp = new double[x.length + 1];
            System.arraycopy(x, 0, tmp, 1, x.length);
            tmp[0] = 1.0d;
            include(tmp, 1.0d, y);
            millerUpdatingRegression = this;
        }
        millerUpdatingRegression.nobs++;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void addObservations(double[][] x, double[] y) throws ModelSpecificationException {
        if (x == null || y == null || x.length != y.length) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(x == null ? 0 : x.length), Integer.valueOf(y != null ? y.length : 0));
        }
        if (x.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]);
        }
        if (x[0].length + 1 > x.length) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Integer.valueOf(x.length), Integer.valueOf(x[0].length));
        }
        for (int i = 0; i < x.length; i++) {
            addObservation(x[i], y[i]);
        }
    }

    private void include(double[] x, double wi, double yi) {
        double dpi;
        double[] dArr = x;
        int nextr = 0;
        double w = wi;
        double y = yi;
        this.rss_set = false;
        this.sumy = smartAdd(yi, this.sumy);
        this.sumsqy = smartAdd(this.sumsqy, yi * yi);
        int i = 0;
        while (i < dArr.length) {
            if (w == 0.0d) {
                return;
            }
            double xi = dArr[i];
            if (xi != 0.0d) {
                double d = 0.0d;
                double di = this.d[i];
                double wxi = w * xi;
                double _w = w;
                if (di != 0.0d) {
                    dpi = smartAdd(di, wxi * xi);
                    double tmp = (wxi * xi) / di;
                    if (FastMath.abs(tmp) > Precision.EPSILON) {
                        w = (di * w) / dpi;
                    }
                } else {
                    dpi = wxi * xi;
                    w = 0.0d;
                }
                this.d[i] = dpi;
                int k = i + 1;
                while (k < this.nvars) {
                    double dpi2 = dpi;
                    double dpi3 = x[k];
                    int k2 = k;
                    int nextr2 = nextr;
                    double d2 = d;
                    x[k2] = smartAdd(dpi3, (-xi) * this.r[nextr2]);
                    if (di != d2) {
                        double[] dArr2 = this.r;
                        double xk = di * this.r[nextr2];
                        dArr2[nextr2] = smartAdd(xk, (_w * xi) * dpi3) / dpi2;
                    } else {
                        this.r[nextr2] = dpi3 / xi;
                    }
                    nextr = nextr2 + 1;
                    k = k2 + 1;
                    dpi = dpi2;
                    d = d2;
                }
                double dpi4 = dpi;
                int nextr3 = nextr;
                double d3 = d;
                double dpi5 = y;
                double y2 = smartAdd(dpi5, (-xi) * this.rhs[i]);
                if (di != d3) {
                    double xk2 = wxi * dpi5;
                    this.rhs[i] = smartAdd(this.rhs[i] * di, xk2) / dpi4;
                } else {
                    this.rhs[i] = dpi5 / xi;
                }
                y = y2;
                nextr = nextr3;
            } else {
                nextr += (this.nvars - i) - 1;
            }
            i++;
            dArr = x;
        }
        this.sserr = smartAdd(this.sserr, w * y * y);
    }

    private double smartAdd(double a, double b) {
        double _a = FastMath.abs(a);
        double _b = FastMath.abs(b);
        if (_a > _b) {
            double eps = Precision.EPSILON * _a;
            if (_b > eps) {
                return a + b;
            }
            return a;
        }
        double eps2 = Precision.EPSILON;
        if (_a > eps2 * _b) {
            return a + b;
        }
        return b;
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public void clear() {
        Arrays.fill(this.d, 0.0d);
        Arrays.fill(this.rhs, 0.0d);
        Arrays.fill(this.r, 0.0d);
        Arrays.fill(this.tol, 0.0d);
        Arrays.fill(this.rss, 0.0d);
        Arrays.fill(this.work_tolset, 0.0d);
        Arrays.fill(this.work_sing, 0.0d);
        Arrays.fill(this.x_sing, 0.0d);
        Arrays.fill(this.lindep, false);
        for (int i = 0; i < this.nvars; i++) {
            this.vorder[i] = i;
        }
        this.nobs = 0L;
        this.sserr = 0.0d;
        this.sumy = 0.0d;
        this.sumsqy = 0.0d;
        this.rss_set = false;
        this.tol_set = false;
    }

    private void tolset() {
        double eps = this.epsilon;
        for (int i = 0; i < this.nvars; i++) {
            this.work_tolset[i] = FastMath.sqrt(this.d[i]);
        }
        this.tol[0] = this.work_tolset[0] * eps;
        for (int col = 1; col < this.nvars; col++) {
            int pos = col - 1;
            double total = this.work_tolset[col];
            for (int row = 0; row < col; row++) {
                total += FastMath.abs(this.r[pos]) * this.work_tolset[row];
                pos += (this.nvars - row) - 2;
            }
            this.tol[col] = eps * total;
        }
        this.tol_set = true;
    }

    private double[] regcf(int nreq) throws ModelSpecificationException {
        if (nreq < 1) {
            throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]);
        }
        if (nreq > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(nreq), Integer.valueOf(this.nvars));
        }
        if (!this.tol_set) {
            tolset();
        }
        double[] ret = new double[nreq];
        boolean rankProblem = false;
        for (int i = nreq - 1; i > -1; i--) {
            if (FastMath.sqrt(this.d[i]) < this.tol[i]) {
                ret[i] = 0.0d;
                this.d[i] = 0.0d;
                rankProblem = true;
            } else {
                ret[i] = this.rhs[i];
                int nextr = ((((this.nvars + this.nvars) - i) - 1) * i) / 2;
                for (int j = i + 1; j < nreq; j++) {
                    ret[i] = smartAdd(ret[i], (-this.r[nextr]) * ret[j]);
                    nextr++;
                }
            }
        }
        if (rankProblem) {
            for (int i2 = 0; i2 < nreq; i2++) {
                if (this.lindep[i2]) {
                    ret[i2] = Double.NaN;
                }
            }
        }
        return ret;
    }

    private void singcheck() {
        for (int i = 0; i < this.nvars; i++) {
            this.work_sing[i] = FastMath.sqrt(this.d[i]);
        }
        for (int col = 0; col < this.nvars; col++) {
            double temp = this.tol[col];
            int pos = col - 1;
            for (int row = 0; row < col - 1; row++) {
                if (FastMath.abs(this.r[pos]) * this.work_sing[row] < temp) {
                    this.r[pos] = 0.0d;
                }
                pos += (this.nvars - row) - 2;
            }
            this.lindep[col] = false;
            if (this.work_sing[col] < temp) {
                this.lindep[col] = true;
                if (col < this.nvars - 1) {
                    Arrays.fill(this.x_sing, 0.0d);
                    int _pi = ((((this.nvars + this.nvars) - col) - 1) * col) / 2;
                    int _xi = col + 1;
                    while (_xi < this.nvars) {
                        this.x_sing[_xi] = this.r[_pi];
                        this.r[_pi] = 0.0d;
                        _xi++;
                        _pi++;
                    }
                    double y = this.rhs[col];
                    double weight = this.d[col];
                    this.d[col] = 0.0d;
                    this.rhs[col] = 0.0d;
                    include(this.x_sing, weight, y);
                } else {
                    this.sserr += this.d[col] * this.rhs[col] * this.rhs[col];
                }
            }
        }
    }

    private void ss() {
        double total = this.sserr;
        this.rss[this.nvars - 1] = this.sserr;
        for (int i = this.nvars - 1; i > 0; i--) {
            total += this.d[i] * this.rhs[i] * this.rhs[i];
            this.rss[i - 1] = total;
        }
        this.rss_set = true;
    }

    private double[] cov(int nreq) {
        double d;
        double total;
        int i = nreq;
        if (this.nobs <= i) {
            return null;
        }
        double rnk = 0.0d;
        int i2 = 0;
        while (true) {
            d = 1.0d;
            if (i2 >= i) {
                break;
            }
            if (!this.lindep[i2]) {
                rnk += 1.0d;
            }
            i2++;
        }
        double var = this.rss[i - 1] / (this.nobs - rnk);
        double[] rinv = new double[((i - 1) * i) / 2];
        inverse(rinv, i);
        double[] covmat = new double[((i + 1) * i) / 2];
        Arrays.fill(covmat, Double.NaN);
        int start = 0;
        int row = 0;
        while (row < i) {
            int pos2 = start;
            if (!this.lindep[row]) {
                int col = row;
                while (col < i) {
                    double d2 = d;
                    if (!this.lindep[col]) {
                        int pos1 = (start + col) - row;
                        if (row != col) {
                            total = rinv[pos1 - 1] / this.d[col];
                        } else {
                            total = d2 / this.d[col];
                        }
                        int k = col + 1;
                        while (k < i) {
                            if (!this.lindep[k]) {
                                total += (rinv[pos1] * rinv[pos2]) / this.d[k];
                            }
                            pos1++;
                            pos2++;
                            k++;
                            i = nreq;
                        }
                        covmat[(((col + 1) * col) / 2) + row] = total * var;
                    } else {
                        pos2 += (nreq - col) - 1;
                    }
                    col++;
                    i = nreq;
                    d = d2;
                }
            }
            start += (nreq - row) - 1;
            row++;
            i = nreq;
            d = d;
        }
        return covmat;
    }

    private void inverse(double[] rinv, int nreq) {
        int pos = (((nreq - 1) * nreq) / 2) - 1;
        Arrays.fill(rinv, Double.NaN);
        for (int row = nreq - 1; row > 0; row--) {
            if (!this.lindep[row]) {
                int start = ((row - 1) * ((this.nvars + this.nvars) - row)) / 2;
                for (int col = nreq; col > row; col--) {
                    int pos1 = start;
                    int pos2 = pos;
                    double total = 0.0d;
                    for (int k = row; k < col - 1; k++) {
                        pos2 += (nreq - k) - 1;
                        if (!this.lindep[k]) {
                            total += (-this.r[pos1]) * rinv[pos2];
                        }
                        pos1++;
                    }
                    rinv[pos] = total - this.r[pos1];
                    pos--;
                }
            } else {
                pos -= nreq - row;
            }
        }
    }

    public double[] getPartialCorrelations(int in) {
        double[] output = new double[(((this.nvars - in) + 1) * (this.nvars - in)) / 2];
        int rms_off = -in;
        int wrk_off = -(in + 1);
        double[] rms = new double[this.nvars - in];
        double[] work = new double[(this.nvars - in) - 1];
        int offXX = ((this.nvars - in) * ((this.nvars - in) - 1)) / 2;
        if (in >= -1 && in < this.nvars) {
            int nvm = this.nvars - 1;
            int base_pos = this.r.length - (((nvm - in) * ((nvm - in) + 1)) / 2);
            double d = 1.0d;
            if (this.d[in] > 0.0d) {
                rms[in + rms_off] = 1.0d / FastMath.sqrt(this.d[in]);
            }
            int col = in + 1;
            while (col < this.nvars) {
                int pos = ((base_pos + col) - 1) - in;
                double d2 = d;
                double sumxx = this.d[col];
                for (int row = in; row < col; row++) {
                    sumxx += this.d[row] * this.r[pos] * this.r[pos];
                    pos += (this.nvars - row) - 2;
                }
                if (sumxx > 0.0d) {
                    rms[col + rms_off] = d2 / FastMath.sqrt(sumxx);
                } else {
                    rms[col + rms_off] = 0.0d;
                }
                col++;
                d = d2;
            }
            double d3 = d;
            double sumyy = this.sserr;
            for (int row2 = in; row2 < this.nvars; row2++) {
                sumyy += this.d[row2] * this.rhs[row2] * this.rhs[row2];
            }
            if (sumyy > 0.0d) {
                sumyy = d3 / FastMath.sqrt(sumyy);
            }
            int pos2 = 0;
            int col1 = in;
            while (col1 < this.nvars) {
                double sumxy = 0.0d;
                double[] output2 = output;
                Arrays.fill(work, 0.0d);
                int pos1 = ((base_pos + col1) - in) - 1;
                int row3 = in;
                while (row3 < col1) {
                    int pos22 = pos1 + 1;
                    int row4 = row3;
                    int row5 = col1 + 1;
                    while (true) {
                        int pos23 = pos22;
                        int pos24 = this.nvars;
                        if (row5 < pos24) {
                            int i = row5 + wrk_off;
                            int col2 = row5;
                            work[i] = work[i] + (this.d[row4] * this.r[pos1] * this.r[pos23]);
                            pos22 = pos23 + 1;
                            row5 = col2 + 1;
                        }
                    }
                    sumxy += this.d[row4] * this.r[pos1] * this.rhs[row4];
                    pos1 += (this.nvars - row4) - 2;
                    row3 = row4 + 1;
                }
                int pos25 = pos1 + 1;
                int col22 = col1 + 1;
                while (true) {
                    int pos26 = pos25;
                    int pos27 = this.nvars;
                    if (col22 < pos27) {
                        int i2 = col22 + wrk_off;
                        work[i2] = work[i2] + (this.d[col1] * this.r[pos26]);
                        pos25 = pos26 + 1;
                        int pos28 = col22 - 1;
                        output2[((((pos28 - in) * (col22 - in)) / 2) + col1) - in] = work[col22 + wrk_off] * rms[col1 + rms_off] * rms[col22 + rms_off];
                        pos2++;
                        col22++;
                    }
                }
                output2[col1 + rms_off + offXX] = rms[col1 + rms_off] * (sumxy + (this.d[col1] * this.rhs[col1])) * sumyy;
                col1++;
                output = output2;
            }
            return output;
        }
        return null;
    }

    private void vmove(int from, int to) {
        int first;
        int inc;
        int count;
        boolean bSkipTo40 = false;
        if (from == to) {
            return;
        }
        if (!this.rss_set) {
            ss();
        }
        if (from < to) {
            first = from;
            inc = 1;
            count = to - from;
        } else {
            first = from - 1;
            inc = -1;
            count = from - to;
        }
        int m = first;
        for (int idx = 0; idx < count; idx++) {
            int m1 = ((((this.nvars + this.nvars) - m) - 1) * m) / 2;
            int m2 = ((this.nvars + m1) - m) - 1;
            int mp1 = m + 1;
            double d1 = this.d[m];
            double d2 = this.d[mp1];
            if (d1 > this.epsilon || d2 > this.epsilon) {
                double X = this.r[m1];
                if (FastMath.abs(X) * FastMath.sqrt(d1) < this.tol[mp1]) {
                    X = 0.0d;
                }
                if (d1 < this.epsilon || FastMath.abs(X) < this.epsilon) {
                    this.d[m] = d2;
                    this.d[mp1] = d1;
                    this.r[m1] = 0.0d;
                    for (int col = m + 2; col < this.nvars; col++) {
                        m1++;
                        double X2 = this.r[m1];
                        this.r[m1] = this.r[m2];
                        this.r[m2] = X2;
                        m2++;
                    }
                    X = this.rhs[m];
                    this.rhs[m] = this.rhs[mp1];
                    this.rhs[mp1] = X;
                    bSkipTo40 = true;
                } else if (d2 < this.epsilon) {
                    this.d[m] = d1 * X * X;
                    this.r[m1] = 1.0d / X;
                    for (int _i = m1 + 1; _i < ((this.nvars + m1) - m) - 1; _i++) {
                        double[] dArr = this.r;
                        dArr[_i] = dArr[_i] / X;
                    }
                    double[] dArr2 = this.rhs;
                    dArr2[m] = dArr2[m] / X;
                    bSkipTo40 = true;
                }
                if (!bSkipTo40) {
                    double d1new = (d1 * X * X) + d2;
                    double cbar = d2 / d1new;
                    double sbar = (X * d1) / d1new;
                    double d2new = d1 * cbar;
                    this.d[m] = d1new;
                    this.d[mp1] = d2new;
                    this.r[m1] = sbar;
                    int col2 = m + 2;
                    while (true) {
                        double d1new2 = d1new;
                        if (col2 >= this.nvars) {
                            break;
                        }
                        m1++;
                        double Y = this.r[m1];
                        this.r[m1] = (this.r[m2] * cbar) + (sbar * Y);
                        this.r[m2] = Y - (this.r[m2] * X);
                        m2++;
                        col2++;
                        d1new = d1new2;
                    }
                    double Y2 = this.rhs[m];
                    this.rhs[m] = (this.rhs[mp1] * cbar) + (sbar * Y2);
                    this.rhs[mp1] = Y2 - (this.rhs[mp1] * X);
                }
            }
            if (m > 0) {
                int pos = m;
                for (int row = 0; row < m; row++) {
                    double X3 = this.r[pos];
                    int pos2 = pos;
                    this.r[pos2] = this.r[pos2 - 1];
                    this.r[pos2 - 1] = X3;
                    pos = pos2 + ((this.nvars - row) - 2);
                }
            }
            int m12 = this.vorder[m];
            this.vorder[m] = this.vorder[mp1];
            this.vorder[mp1] = m12;
            double X4 = this.tol[m];
            this.tol[m] = this.tol[mp1];
            this.tol[mp1] = X4;
            this.rss[m] = this.rss[mp1] + (this.d[mp1] * this.rhs[mp1] * this.rhs[mp1]);
            m += inc;
        }
    }

    private int reorderRegressors(int[] list, int pos1) {
        if (list.length < 1 || list.length > (this.nvars + 1) - pos1) {
            return -1;
        }
        int next = pos1;
        for (int i = pos1; i < this.nvars; i++) {
            int l = this.vorder[i];
            int j = 0;
            while (true) {
                if (j >= list.length) {
                    break;
                }
                if (l != list[j] || i <= next) {
                    j++;
                } else {
                    vmove(i, next);
                    next++;
                    if (next >= list.length + pos1) {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    public double getDiagonalOfHatMatrix(double[] row_data) {
        double[] xrow;
        double[] wk = new double[this.nvars];
        if (row_data.length > this.nvars) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            xrow = new double[row_data.length + 1];
            xrow[0] = 1.0d;
            System.arraycopy(row_data, 0, xrow, 1, row_data.length);
        } else {
            xrow = row_data;
        }
        double hii = 0.0d;
        for (int col = 0; col < xrow.length; col++) {
            if (FastMath.sqrt(this.d[col]) < this.tol[col]) {
                wk[col] = 0.0d;
            } else {
                int pos = col - 1;
                double total = xrow[col];
                for (int row = 0; row < col; row++) {
                    total = smartAdd(total, (-wk[row]) * this.r[pos]);
                    pos += (this.nvars - row) - 2;
                }
                wk[col] = total;
                hii = smartAdd(hii, (total * total) / this.d[col]);
            }
        }
        return hii;
    }

    public int[] getOrderOfRegressors() {
        return MathArrays.copyOf(this.vorder);
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress() throws ModelSpecificationException {
        return regress(this.nvars);
    }

    /* JADX WARN: Incorrect condition in loop: B:42:0x0089 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.stat.regression.RegressionResults regress(int r25) throws org.apache.commons.math3.stat.regression.ModelSpecificationException {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.regression.MillerUpdatingRegression.regress(int):org.apache.commons.math3.stat.regression.RegressionResults");
    }

    @Override // org.apache.commons.math3.stat.regression.UpdatingMultipleLinearRegression
    public RegressionResults regress(int[] variablesToInclude) throws ModelSpecificationException {
        int[] series;
        boolean needsReorder;
        int idx2;
        if (variablesToInclude.length > this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, Integer.valueOf(variablesToInclude.length), Integer.valueOf(this.nvars));
        }
        if (this.nobs <= this.nvars) {
            throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, Long.valueOf(this.nobs), Integer.valueOf(this.nvars));
        }
        Arrays.sort(variablesToInclude);
        int iExclude = 0;
        for (int i = 0; i < variablesToInclude.length; i++) {
            if (i >= this.nvars) {
                throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, Integer.valueOf(i), Integer.valueOf(this.nvars));
            }
            if (i > 0 && variablesToInclude[i] == variablesToInclude[i - 1]) {
                variablesToInclude[i] = -1;
                iExclude++;
            }
        }
        if (iExclude > 0) {
            int j = 0;
            series = new int[variablesToInclude.length - iExclude];
            for (int i2 = 0; i2 < variablesToInclude.length; i2++) {
                if (variablesToInclude[i2] > -1) {
                    series[j] = variablesToInclude[i2];
                    j++;
                }
            }
        } else {
            series = variablesToInclude;
        }
        reorderRegressors(series, 0);
        tolset();
        singcheck();
        double[] beta = regcf(series.length);
        ss();
        double[] cov = cov(series.length);
        int rnk = 0;
        for (int i3 = 0; i3 < this.lindep.length; i3++) {
            if (!this.lindep[i3]) {
                rnk++;
            }
        }
        int i4 = 0;
        while (true) {
            if (i4 >= this.nvars) {
                needsReorder = false;
                break;
            }
            if (this.vorder[i4] == series[i4]) {
                i4++;
            } else {
                needsReorder = true;
                break;
            }
        }
        if (!needsReorder) {
            return new RegressionResults(beta, new double[][]{cov}, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
        }
        int rnk2 = rnk;
        int iExclude2 = beta.length;
        double[] betaNew = new double[iExclude2];
        int[] newIndices = new int[beta.length];
        for (int i5 = 0; i5 < series.length; i5++) {
            for (int j2 = 0; j2 < this.vorder.length; j2++) {
                if (this.vorder[j2] == series[i5]) {
                    betaNew[i5] = beta[j2];
                    newIndices[i5] = j2;
                }
            }
        }
        int i6 = cov.length;
        double[] covNew = new double[i6];
        int idx1 = 0;
        for (int i7 = 0; i7 < beta.length; i7++) {
            int _i = newIndices[i7];
            int j3 = 0;
            while (j3 <= i7) {
                int _j = newIndices[j3];
                if (_i > _j) {
                    idx2 = (((_i + 1) * _i) / 2) + _j;
                } else {
                    int idx22 = _j + 1;
                    idx2 = ((idx22 * _j) / 2) + _i;
                }
                covNew[idx1] = cov[idx2];
                j3++;
                idx1++;
            }
        }
        return new RegressionResults(betaNew, new double[][]{covNew}, true, this.nobs, rnk2, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
    }
}
