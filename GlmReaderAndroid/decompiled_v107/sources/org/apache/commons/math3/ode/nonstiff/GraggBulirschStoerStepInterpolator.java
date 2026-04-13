package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
    private static final long serialVersionUID = 20110928;
    private int currentDegree;
    private double[] errfac;
    private double[][] polynomials;
    private double[] y0Dot;
    private double[] y1;
    private double[] y1Dot;
    private double[][] yMidDots;

    public GraggBulirschStoerStepInterpolator() {
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        this.yMidDots = null;
        resetTables(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GraggBulirschStoerStepInterpolator(double[] y, double[] y0Dot, double[] y1, double[] y1Dot, double[][] yMidDots, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
        super(y, forward, primaryMapper, secondaryMappers);
        this.y0Dot = y0Dot;
        this.y1 = y1;
        this.y1Dot = y1Dot;
        this.yMidDots = yMidDots;
        resetTables(yMidDots.length + 4);
    }

    GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator interpolator) {
        super(interpolator);
        int dimension = this.currentState.length;
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        this.yMidDots = null;
        if (interpolator.polynomials == null) {
            this.polynomials = null;
            this.currentDegree = -1;
            return;
        }
        resetTables(interpolator.currentDegree);
        for (int i = 0; i < this.polynomials.length; i++) {
            this.polynomials[i] = new double[dimension];
            System.arraycopy(interpolator.polynomials[i], 0, this.polynomials[i], 0, dimension);
        }
        int i2 = interpolator.currentDegree;
        this.currentDegree = i2;
    }

    private void resetTables(int maxDegree) {
        if (maxDegree < 0) {
            this.polynomials = null;
            this.errfac = null;
            this.currentDegree = -1;
            return;
        }
        double[][] newPols = new double[maxDegree + 1];
        if (this.polynomials != null) {
            System.arraycopy(this.polynomials, 0, newPols, 0, this.polynomials.length);
            for (int i = this.polynomials.length; i < newPols.length; i++) {
                newPols[i] = new double[this.currentState.length];
            }
        } else {
            for (int i2 = 0; i2 < newPols.length; i2++) {
                newPols[i2] = new double[this.currentState.length];
            }
        }
        this.polynomials = newPols;
        if (maxDegree <= 4) {
            this.errfac = null;
        } else {
            this.errfac = new double[maxDegree - 4];
            for (int i3 = 0; i3 < this.errfac.length; i3++) {
                int ip5 = i3 + 5;
                this.errfac[i3] = 1.0d / (ip5 * ip5);
                double e = FastMath.sqrt((i3 + 1) / ip5) * 0.5d;
                for (int j = 0; j <= i3; j++) {
                    double[] dArr = this.errfac;
                    dArr[i3] = dArr[i3] * (e / (j + 1));
                }
            }
        }
        this.currentDegree = 0;
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new GraggBulirschStoerStepInterpolator(this);
    }

    public void computeCoefficients(int mu, double h) {
        int i;
        int i2 = mu;
        if (this.polynomials == null || this.polynomials.length <= i2 + 4) {
            resetTables(i2 + 4);
        }
        this.currentDegree = i2 + 4;
        int i3 = 0;
        while (i3 < this.currentState.length) {
            double yp0 = this.y0Dot[i3] * h;
            double yp1 = this.y1Dot[i3] * h;
            double ydiff = this.y1[i3] - this.currentState[i3];
            double aspl = ydiff - yp1;
            double bspl = yp0 - ydiff;
            this.polynomials[0][i3] = this.currentState[i3];
            this.polynomials[1][i3] = ydiff;
            this.polynomials[2][i3] = aspl;
            this.polynomials[3][i3] = bspl;
            if (i2 >= 0) {
                double ph0 = ((this.currentState[i3] + this.y1[i3]) * 0.5d) + ((aspl + bspl) * 0.125d);
                this.polynomials[4][i3] = (this.yMidDots[0][i3] - ph0) * 16.0d;
                if (i2 <= 0) {
                    i = i3;
                } else {
                    double ph1 = ydiff + ((aspl - bspl) * 0.25d);
                    this.polynomials[5][i3] = (this.yMidDots[1][i3] - ph1) * 16.0d;
                    if (i2 <= 1) {
                        i = i3;
                    } else {
                        double ph2 = yp1 - yp0;
                        i = i3;
                        this.polynomials[6][i] = ((this.yMidDots[2][i] - ph2) + this.polynomials[4][i]) * 16.0d;
                        if (i2 > 2) {
                            double ph3 = (bspl - aspl) * 6.0d;
                            this.polynomials[7][i] = ((this.yMidDots[3][i] - ph3) + (this.polynomials[5][i] * 3.0d)) * 16.0d;
                            int j = 4;
                            while (j <= i2) {
                                int j2 = j;
                                double fac1 = j * 0.5d * (j - 1);
                                double fac2 = (j2 - 3) * (j2 - 2) * 2.0d * fac1;
                                this.polynomials[j2 + 4][i] = ((this.yMidDots[j2][i] + (this.polynomials[j2 + 2][i] * fac1)) - (fac2 * this.polynomials[j2][i])) * 16.0d;
                                j = j2 + 1;
                                i2 = mu;
                                yp0 = yp0;
                            }
                        }
                    }
                }
                i3 = i + 1;
                i2 = mu;
            } else {
                return;
            }
        }
    }

    public double estimateError(double[] scale) {
        double error = 0.0d;
        if (this.currentDegree < 5) {
            return 0.0d;
        }
        for (int i = 0; i < scale.length; i++) {
            double e = this.polynomials[this.currentDegree][i] / scale[i];
            error += e * e;
        }
        int i2 = scale.length;
        return FastMath.sqrt(error / i2) * this.errfac[this.currentDegree - 5];
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        int dimension = this.currentState.length;
        double oneMinusTheta = 1.0d - theta;
        double theta05 = theta - 0.5d;
        double tOmT = theta * oneMinusTheta;
        double t4 = tOmT * tOmT;
        double t4Dot = tOmT * 2.0d * (1.0d - (theta * 2.0d));
        double dot1 = 1.0d / this.h;
        double dot12 = this.h;
        double dot2 = ((2.0d - (theta * 3.0d)) * theta) / dot12;
        double dot3 = ((((3.0d * theta) - 4.0d) * theta) + 1.0d) / this.h;
        int i = 0;
        while (i < dimension) {
            double p0 = this.polynomials[0][i];
            double p1 = this.polynomials[1][i];
            double p2 = this.polynomials[2][i];
            int i2 = i;
            double p3 = this.polynomials[3][i2];
            this.interpolatedState[i2] = p0 + ((p1 + (((p2 * theta) + (p3 * oneMinusTheta)) * oneMinusTheta)) * theta);
            this.interpolatedDerivatives[i2] = (dot1 * p1) + (dot2 * p2) + (dot3 * p3);
            if (this.currentDegree > 3) {
                double cDot = 0.0d;
                double c = this.polynomials[this.currentDegree][i2];
                int j = this.currentDegree - 1;
                while (j > 3) {
                    int j2 = j;
                    double d = 1.0d / (j - 3);
                    cDot = d * ((theta05 * cDot) + c);
                    c = this.polynomials[j2][i2] + (c * d * theta05);
                    j = j2 - 1;
                }
                double[] dArr = this.interpolatedState;
                dArr[i2] = dArr[i2] + (t4 * c);
                double[] dArr2 = this.interpolatedDerivatives;
                dArr2[i2] = dArr2[i2] + (((t4 * cDot) + (t4Dot * c)) / this.h);
            }
            i = i2 + 1;
        }
        if (this.h == 0.0d) {
            System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, dimension);
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        int dimension = this.currentState == null ? -1 : this.currentState.length;
        writeBaseExternal(out);
        out.writeInt(this.currentDegree);
        for (int k = 0; k <= this.currentDegree; k++) {
            for (int l = 0; l < dimension; l++) {
                out.writeDouble(this.polynomials[k][l]);
            }
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, java.io.Externalizable
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        double t = readBaseExternal(in);
        int dimension = this.currentState == null ? -1 : this.currentState.length;
        int degree = in.readInt();
        resetTables(degree);
        this.currentDegree = degree;
        for (int k = 0; k <= this.currentDegree; k++) {
            for (int l = 0; l < dimension; l++) {
                this.polynomials[k][l] = in.readDouble();
            }
        }
        setInterpolatedTime(t);
    }
}
