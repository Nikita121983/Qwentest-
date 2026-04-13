package org.apache.commons.math3.ode.nonstiff;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final double exp;
    private final boolean fsal;
    private double maxGrowth;
    private double minReduction;
    private final RungeKuttaStepInterpolator prototype;
    private double safety;

    protected abstract double estimateError(double[][] dArr, double[] dArr2, double[] dArr3, double d);

    public abstract int getOrder();

    /* JADX INFO: Access modifiers changed from: protected */
    public EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.exp = (-1.0d) / getOrder();
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.exp = (-1.0d) / getOrder();
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r23v10 */
    /* JADX WARN: Type inference failed for: r23v4 */
    /* JADX WARN: Type inference failed for: r23v5 */
    @Override // org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double[] dArr;
        int i;
        double[] dArr2;
        ?? r21;
        double[] dArr3;
        double[] dArr4;
        boolean z;
        int i2;
        double[] dArr5;
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator;
        double d2;
        double[] dArr6;
        int i3;
        char c;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z2 = true;
        char c2 = 0;
        boolean z3 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr7 = (double[]) completeState.clone();
        int length = this.c.length + 1;
        double[][] dArr8 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, dArr7.length);
        double[] dArr9 = (double[]) completeState.clone();
        double[] dArr10 = new double[dArr7.length];
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = (RungeKuttaStepInterpolator) this.prototype.copy();
        boolean z4 = z3;
        rungeKuttaStepInterpolator2.reinitialize(this, dArr9, dArr8, z4, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        double[] dArr11 = dArr9;
        double[][] dArr12 = dArr8;
        boolean z5 = z4;
        rungeKuttaStepInterpolator2.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        double d3 = 0.0d;
        boolean z6 = true;
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator2;
        double[] dArr13 = completeState;
        initIntegration(expandableStatefulODE.getTime(), dArr13, d);
        this.isLastStep = false;
        while (true) {
            rungeKuttaStepInterpolator3.shift();
            double d4 = 10.0d;
            while (d4 >= 1.0d) {
                if (z6 || !this.fsal) {
                    r21 = z2;
                    computeDerivatives(this.stepStart, dArr7, dArr12[c2]);
                } else {
                    r21 = z2;
                }
                if (z6) {
                    double[] dArr14 = new double[this.mainSetDimension];
                    if (this.vecAbsoluteTolerance == null) {
                        int i4 = 0;
                        while (i4 < dArr14.length) {
                            dArr14[i4] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.abs(dArr7[i4]));
                            i4++;
                            length = length;
                            c2 = c2;
                        }
                        c = c2;
                        i2 = length;
                    } else {
                        c = c2;
                        i2 = length;
                        for (int i5 = 0; i5 < dArr14.length; i5++) {
                            dArr14[i5] = this.vecAbsoluteTolerance[i5] + (this.vecRelativeTolerance[i5] * FastMath.abs(dArr7[i5]));
                        }
                    }
                    rungeKuttaStepInterpolator = rungeKuttaStepInterpolator3;
                    boolean z7 = z5;
                    double[] dArr15 = dArr7;
                    dArr4 = dArr11;
                    dArr5 = dArr13;
                    double initializeStep = initializeStep(z7, getOrder(), dArr14, this.stepStart, dArr15, dArr12[c == true ? 1 : 0], dArr4, dArr12[r21]);
                    dArr3 = dArr15;
                    z5 = z7;
                    z6 = false;
                    d2 = initializeStep;
                    z = c;
                } else {
                    dArr3 = dArr7;
                    dArr4 = dArr11;
                    z = c2;
                    i2 = length;
                    dArr5 = dArr13;
                    rungeKuttaStepInterpolator = rungeKuttaStepInterpolator3;
                    d2 = d3;
                }
                this.stepSize = d2;
                if (z5) {
                    dArr6 = dArr3;
                    if (this.stepStart + this.stepSize >= d) {
                        this.stepSize = d - this.stepStart;
                    }
                } else {
                    dArr6 = dArr3;
                    if (this.stepStart + this.stepSize <= d) {
                        this.stepSize = d - this.stepStart;
                    }
                }
                int i6 = 1;
                while (true) {
                    i3 = i2;
                    if (i6 >= i3) {
                        break;
                    }
                    int i7 = 0;
                    while (i7 < dArr5.length) {
                        double d5 = this.a[i6 - 1][z] * dArr12[z][i7];
                        int i8 = 1;
                        while (i8 < i6) {
                            int i9 = i6;
                            d5 += this.a[i9 - 1][i8] * dArr12[i8][i7];
                            i8++;
                            i6 = i9;
                        }
                        int i10 = i7;
                        dArr4[i10] = dArr6[i7] + (this.stepSize * d5);
                        i7 = i10 + 1;
                        i6 = i6;
                    }
                    int i11 = i6;
                    computeDerivatives(this.stepStart + (this.c[i11 - 1] * this.stepSize), dArr4, dArr12[i11]);
                    i6 = i11 + 1;
                    i2 = i3;
                }
                int i12 = 0;
                while (i12 < dArr5.length) {
                    double d6 = this.b[z] * dArr12[z][i12];
                    for (int i13 = 1; i13 < i3; i13++) {
                        d6 += this.b[i13] * dArr12[i13][i12];
                    }
                    int i14 = i12;
                    dArr4[i14] = dArr6[i12] + (this.stepSize * d6);
                    i12 = i14 + 1;
                }
                d3 = d2;
                double[] dArr16 = dArr6;
                double estimateError = estimateError(dArr12, dArr16, dArr4, this.stepSize);
                if (estimateError >= 1.0d) {
                    d3 = filterStep(this.stepSize * FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, FastMath.pow(estimateError, this.exp) * this.safety)), z5, z);
                    RungeKuttaStepInterpolator rungeKuttaStepInterpolator4 = rungeKuttaStepInterpolator;
                    length = i3;
                    rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator4;
                    dArr10 = dArr10;
                    d4 = estimateError;
                    dArr11 = dArr4;
                    dArr13 = dArr5;
                    dArr12 = dArr12;
                    dArr7 = dArr16;
                    z2 = r21;
                    c2 = 0;
                } else {
                    RungeKuttaStepInterpolator rungeKuttaStepInterpolator5 = rungeKuttaStepInterpolator;
                    length = i3;
                    rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator5;
                    d4 = estimateError;
                    dArr11 = dArr4;
                    dArr13 = dArr5;
                    dArr7 = dArr16;
                    z2 = r21;
                    c2 = 0;
                }
            }
            int i15 = length;
            RungeKuttaStepInterpolator rungeKuttaStepInterpolator6 = rungeKuttaStepInterpolator3;
            double[] dArr17 = dArr13;
            double[] dArr18 = dArr7;
            double[] dArr19 = dArr11;
            boolean z8 = z2;
            double[] dArr20 = dArr10;
            double[][] dArr21 = dArr12;
            double d7 = d4;
            rungeKuttaStepInterpolator6.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr19, 0, dArr18, 0, dArr17.length);
            System.arraycopy(dArr21[i15 - 1], 0, dArr20, 0, dArr17.length);
            this.stepStart = acceptStep(rungeKuttaStepInterpolator6, dArr18, dArr20, d);
            System.arraycopy(dArr18, 0, dArr19, 0, dArr18.length);
            if (this.isLastStep) {
                dArr = dArr20;
                i = i15;
                dArr2 = dArr19;
            } else {
                rungeKuttaStepInterpolator6.storeTime(this.stepStart);
                if (this.fsal) {
                    System.arraycopy(dArr20, 0, dArr21[0], 0, dArr17.length);
                }
                i = i15;
                dArr2 = dArr19;
                double min = this.stepSize * FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, FastMath.pow(d7, this.exp) * this.safety));
                double d8 = this.stepStart + min;
                d3 = filterStep(min, z5, (!z5 ? d8 <= d : d8 >= d) ? false : z8);
                dArr = dArr20;
                double d9 = this.stepStart + d3;
                if ((!z5 ? d9 <= d : d9 >= d) ? false : z8) {
                    d3 = d - this.stepStart;
                }
            }
            if (this.isLastStep) {
                expandableStatefulODE.setTime(this.stepStart);
                expandableStatefulODE.setCompleteState(dArr18);
                resetInternalState();
                return;
            }
            rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator6;
            dArr7 = dArr18;
            dArr13 = dArr17;
            dArr11 = dArr2;
            dArr12 = dArr21;
            dArr10 = dArr;
            z2 = z8;
            length = i;
            c2 = 0;
        }
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double minReduction) {
        this.minReduction = minReduction;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }
}
