package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private double[][] coeff;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private int maxChecks;
    private int maxIter;
    private int maxOrder;
    private int mudif;
    private double[] optimalStep;
    private double orderControl1;
    private double orderControl2;
    private boolean performTest;
    private int[] sequence;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private boolean useInterpolationError;

    public GraggBulirschStoerIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setStabilityCheck(boolean performStabilityCheck, int maxNumIter, int maxNumChecks, double stepsizeReductionFactor) {
        this.performTest = performStabilityCheck;
        this.maxIter = maxNumIter <= 0 ? 2 : maxNumIter;
        this.maxChecks = maxNumChecks <= 0 ? 1 : maxNumChecks;
        if (stepsizeReductionFactor < 1.0E-4d || stepsizeReductionFactor > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = stepsizeReductionFactor;
        }
    }

    public void setControlFactors(double control1, double control2, double control3, double control4) {
        if (control1 < 1.0E-4d || control1 > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = control1;
        }
        if (control2 < 1.0E-4d || control2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = control2;
        }
        if (control3 < 1.0E-4d || control3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = control3;
        }
        if (control4 < 1.0001d || control4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = control4;
        }
    }

    public void setOrderControl(int maximalOrder, double control1, double control2) {
        if (maximalOrder <= 6 || maximalOrder % 2 != 0) {
            this.maxOrder = 18;
        }
        if (control1 < 1.0E-4d || control1 > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = control1;
        }
        if (control2 < 1.0E-4d || control2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = control2;
        }
        initializeArrays();
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator, org.apache.commons.math3.ode.ODEIntegrator
    public void addStepHandler(StepHandler handler) {
        super.addStepHandler(handler);
        initializeArrays();
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator, org.apache.commons.math3.ode.ODEIntegrator
    public void addEventHandler(EventHandler function, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
        super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount, solver);
        initializeArrays();
    }

    private void initializeArrays() {
        int size = this.maxOrder / 2;
        if (this.sequence == null || this.sequence.length != size) {
            this.sequence = new int[size];
            this.costPerStep = new int[size];
            this.coeff = new double[size];
            this.costPerTimeUnit = new double[size];
            this.optimalStep = new double[size];
        }
        for (int k = 0; k < size; k++) {
            this.sequence[k] = (k * 4) + 2;
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int k2 = 1; k2 < size; k2++) {
            this.costPerStep[k2] = this.costPerStep[k2 - 1] + this.sequence[k2];
        }
        int k3 = 0;
        while (k3 < size) {
            this.coeff[k3] = k3 > 0 ? new double[k3] : null;
            for (int l = 0; l < k3; l++) {
                double ratio = this.sequence[k3] / this.sequence[(k3 - l) - 1];
                this.coeff[k3][l] = 1.0d / ((ratio * ratio) - 1.0d);
            }
            k3++;
        }
    }

    public void setInterpolationControl(boolean useInterpolationErrorForControl, int mudifControlParameter) {
        this.useInterpolationError = useInterpolationErrorForControl;
        if (mudifControlParameter <= 0 || mudifControlParameter >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = mudifControlParameter;
        }
    }

    private void rescale(double[] y1, double[] y2, double[] scale) {
        if (this.vecAbsoluteTolerance == null) {
            for (int i = 0; i < scale.length; i++) {
                double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
                scale[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * yi);
            }
            return;
        }
        for (int i2 = 0; i2 < scale.length; i2++) {
            double yi2 = FastMath.max(FastMath.abs(y1[i2]), FastMath.abs(y2[i2]));
            scale[i2] = this.vecAbsoluteTolerance[i2] + (this.vecRelativeTolerance[i2] * yi2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean tryStep(double d, double[] dArr, double d2, int i, double[] dArr2, double[][] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) throws MaxCountExceededException, DimensionMismatchException {
        int i2;
        boolean z;
        boolean z2;
        GraggBulirschStoerIntegrator graggBulirschStoerIntegrator = this;
        int i3 = i;
        double[] dArr7 = dArr2;
        int i4 = graggBulirschStoerIntegrator.sequence[i3];
        double d3 = d2 / i4;
        double d4 = 2.0d * d3;
        double d5 = d + d3;
        int i5 = 0;
        while (true) {
            i2 = 0;
            if (i5 >= dArr.length) {
                break;
            }
            dArr6[i5] = dArr[i5];
            dArr5[i5] = dArr[i5] + (dArr3[0][i5] * d3);
            i5++;
        }
        boolean z3 = true;
        graggBulirschStoerIntegrator.computeDerivatives(d5, dArr5, dArr3[1]);
        int i6 = 1;
        while (i6 < i4) {
            if (i6 * 2 != i4) {
                z = z3;
            } else {
                z = z3;
                System.arraycopy(dArr5, i2, dArr4, i2, dArr.length);
            }
            d5 += d3;
            int i7 = 0;
            while (true) {
                z2 = i2;
                if (i7 >= dArr.length) {
                    break;
                }
                double d6 = dArr5[i7];
                dArr5[i7] = dArr6[i7] + (dArr3[i6][i7] * d4);
                dArr6[i7] = d6;
                i7++;
                i2 = z2 ? 1 : 0;
            }
            graggBulirschStoerIntegrator.computeDerivatives(d5, dArr5, dArr3[i6 + 1]);
            if (graggBulirschStoerIntegrator.performTest && i6 <= graggBulirschStoerIntegrator.maxChecks && i3 < graggBulirschStoerIntegrator.maxIter) {
                double d7 = 0.0d;
                for (int i8 = 0; i8 < dArr7.length; i8++) {
                    double d8 = dArr3[z2 ? 1 : 0][i8] / dArr7[i8];
                    d7 += d8 * d8;
                }
                double d9 = 0.0d;
                for (int i9 = 0; i9 < dArr7.length; i9++) {
                    double d10 = (dArr3[i6 + 1][i9] - dArr3[z2 ? 1 : 0][i9]) / dArr7[i9];
                    d9 += d10 * d10;
                }
                if (d9 > FastMath.max(1.0E-15d, d7) * 4.0d) {
                    return z2;
                }
            }
            i6++;
            graggBulirschStoerIntegrator = this;
            i3 = i;
            dArr7 = dArr2;
            z3 = z;
            i2 = z2 ? 1 : 0;
        }
        boolean z4 = z3;
        for (int i10 = 0; i10 < dArr.length; i10++) {
            dArr5[i10] = (dArr6[i10] + dArr5[i10] + (dArr3[i4][i10] * d3)) * 0.5d;
        }
        return z4;
    }

    private void extrapolate(int offset, int k, double[][] diag, double[] last) {
        for (int j = 1; j < k; j++) {
            for (int i = 0; i < last.length; i++) {
                diag[(k - j) - 1][i] = diag[k - j][i] + (this.coeff[k + offset][j - 1] * (diag[k - j][i] - diag[(k - j) - 1][i]));
            }
        }
        for (int i2 = 0; i2 < last.length; i2++) {
            last[i2] = diag[0][i2] + (this.coeff[k + offset][k - 1] * (diag[0][i2] - last[i2]));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:256:0x01c0, code lost:
    
        if ((r59.stepStart + r59.stepSize) < r61) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x01af, code lost:
    
        if ((r59.stepStart + r59.stepSize) <= r61) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x01c2, code lost:
    
        r59.stepSize = r61 - r59.stepStart;
     */
    /* JADX WARN: Removed duplicated region for block: B:195:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x06f5  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0712 A[LOOP:3: B:26:0x0150->B:211:0x0712, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0704 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x06dc  */
    @Override // org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r60, double r61) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 1852
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }
}
