package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public abstract class EmbeddedRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a;
    private final T[] b;
    private final T[] c;
    private final T exp;
    private final int fsal;
    private T maxGrowth;
    private T minReduction;
    private T safety;

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    protected abstract T estimateError(T[][] tArr, T[] tArr2, T[] tArr3, T t);

    public abstract int getOrder();

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double d3, double d4) {
        super(field, str, d, d2, d3, d4);
        this.fsal = i;
        this.c = (T[]) getC();
        this.a = (T[][]) getA();
        this.b = (T[]) getB();
        this.exp = (T) field.getOne().divide(-getOrder());
        setSafety((RealFieldElement) field.getZero().add(0.9d));
        setMinReduction((RealFieldElement) field.getZero().add(0.2d));
        setMaxGrowth((RealFieldElement) field.getZero().add(10.0d));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public EmbeddedRungeKuttaFieldIntegrator(Field<T> field, String str, int i, double d, double d2, double[] dArr, double[] dArr2) {
        super(field, str, d, d2, dArr, dArr2);
        this.fsal = i;
        this.c = (T[]) getC();
        this.a = (T[][]) getA();
        this.b = (T[]) getB();
        this.exp = (T) field.getOne().divide(-getOrder());
        setSafety((RealFieldElement) field.getZero().add(0.9d));
        setMinReduction((RealFieldElement) field.getZero().add(0.2d));
        setMaxGrowth((RealFieldElement) field.getZero().add(10.0d));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T fraction(int p, int q) {
        return (T) ((RealFieldElement) getField().getOne().multiply(p)).divide(q);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T fraction(double p, double q) {
        return (T) ((RealFieldElement) getField().getOne().multiply(p)).divide(q);
    }

    public T getSafety() {
        return this.safety;
    }

    public void setSafety(T safety) {
        this.safety = safety;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v49, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v8, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r3v16, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r3v61, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r5v11, types: [org.apache.commons.math3.RealFieldElement] */
    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> fieldExpandableODE, FieldODEState<T> fieldODEState, T t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        RealFieldElement realFieldElement;
        RealFieldElement[][] realFieldElementArr;
        RealFieldElement[][] realFieldElementArr2;
        RealFieldElement[] realFieldElementArr3;
        RealFieldElement realFieldElement2;
        RealFieldElement[] realFieldElementArr4;
        boolean z;
        RealFieldElement[] realFieldElementArr5;
        EmbeddedRungeKuttaFieldIntegrator embeddedRungeKuttaFieldIntegrator = this;
        embeddedRungeKuttaFieldIntegrator.sanityChecks(fieldODEState, t);
        T time = fieldODEState.getTime();
        T[] mapState = fieldExpandableODE.getMapper().mapState(fieldODEState);
        embeddedRungeKuttaFieldIntegrator.setStepStart(embeddedRungeKuttaFieldIntegrator.initIntegration(fieldExpandableODE, time, mapState, t));
        double d = 0.0d;
        boolean z2 = false;
        boolean z3 = ((RealFieldElement) t.subtract(fieldODEState.getTime())).getReal() > 0.0d;
        int length = embeddedRungeKuttaFieldIntegrator.c.length + 1;
        T[] tArr = mapState;
        RealFieldElement[][] realFieldElementArr6 = (RealFieldElement[][]) MathArrays.buildArray(embeddedRungeKuttaFieldIntegrator.getField(), length, -1);
        RealFieldElement[] realFieldElementArr7 = (RealFieldElement[]) MathArrays.buildArray(embeddedRungeKuttaFieldIntegrator.getField(), mapState.length);
        RealFieldElement realFieldElement3 = (RealFieldElement) embeddedRungeKuttaFieldIntegrator.getField().getZero();
        boolean z4 = true;
        embeddedRungeKuttaFieldIntegrator.setIsLastStep(false);
        EmbeddedRungeKuttaFieldIntegrator embeddedRungeKuttaFieldIntegrator2 = embeddedRungeKuttaFieldIntegrator;
        while (true) {
            double d2 = d;
            boolean z5 = z2;
            RealFieldElement realFieldElement4 = realFieldElement3;
            RealFieldElement realFieldElement5 = (RealFieldElement) ((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getField().getZero()).add(10.0d);
            T[] tArr2 = tArr;
            while (true) {
                boolean z6 = z5;
                realFieldElement = realFieldElement4;
                if (((RealFieldElement) realFieldElement5.subtract(1.0d)).getReal() < d2) {
                    break;
                }
                tArr2 = fieldExpandableODE.getMapper().mapState(embeddedRungeKuttaFieldIntegrator2.getStepStart());
                realFieldElementArr6[z6 ? 1 : 0] = fieldExpandableODE.getMapper().mapDerivative(embeddedRungeKuttaFieldIntegrator2.getStepStart());
                if (!z4) {
                    realFieldElementArr2 = realFieldElementArr6;
                    realFieldElementArr3 = realFieldElementArr7;
                    realFieldElement2 = realFieldElement;
                } else {
                    RealFieldElement[] realFieldElementArr8 = (RealFieldElement[]) MathArrays.buildArray(embeddedRungeKuttaFieldIntegrator2.getField(), embeddedRungeKuttaFieldIntegrator2.mainSetDimension);
                    if (embeddedRungeKuttaFieldIntegrator2.vecAbsoluteTolerance == null) {
                        int i = 0;
                        while (i < realFieldElementArr8.length) {
                            int i2 = i;
                            realFieldElementArr8[i2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr2[i].abs()).multiply(embeddedRungeKuttaFieldIntegrator2.scalRelativeTolerance)).add(embeddedRungeKuttaFieldIntegrator2.scalAbsoluteTolerance);
                            i = i2 + 1;
                            realFieldElementArr6 = realFieldElementArr6;
                            realFieldElementArr7 = realFieldElementArr7;
                        }
                        realFieldElementArr2 = realFieldElementArr6;
                        realFieldElementArr4 = realFieldElementArr7;
                        z = z3;
                        realFieldElementArr5 = realFieldElementArr8;
                    } else {
                        realFieldElementArr2 = realFieldElementArr6;
                        realFieldElementArr4 = realFieldElementArr7;
                        int i3 = 0;
                        while (i3 < realFieldElementArr8.length) {
                            RealFieldElement[] realFieldElementArr9 = realFieldElementArr8;
                            realFieldElementArr9[i3] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr2[i3].abs()).multiply(embeddedRungeKuttaFieldIntegrator2.vecRelativeTolerance[i3])).add(embeddedRungeKuttaFieldIntegrator2.vecAbsoluteTolerance[i3]);
                            i3++;
                            z3 = z3;
                            realFieldElementArr8 = realFieldElementArr9;
                        }
                        z = z3;
                        realFieldElementArr5 = realFieldElementArr8;
                    }
                    z3 = z;
                    realFieldElementArr3 = realFieldElementArr4;
                    realFieldElement2 = embeddedRungeKuttaFieldIntegrator2.initializeStep(z3, embeddedRungeKuttaFieldIntegrator2.getOrder(), realFieldElementArr5, embeddedRungeKuttaFieldIntegrator2.getStepStart(), fieldExpandableODE.getMapper());
                    z4 = false;
                }
                embeddedRungeKuttaFieldIntegrator2.setStepSize(realFieldElement2);
                if (z3) {
                    if (((RealFieldElement) ((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(embeddedRungeKuttaFieldIntegrator2.getStepSize())).subtract(t)).getReal() >= d2) {
                        embeddedRungeKuttaFieldIntegrator2.setStepSize((RealFieldElement) t.subtract(embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime()));
                    }
                } else if (((RealFieldElement) ((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(embeddedRungeKuttaFieldIntegrator2.getStepSize())).subtract(t)).getReal() <= d2) {
                    embeddedRungeKuttaFieldIntegrator2.setStepSize((RealFieldElement) t.subtract(embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime()));
                }
                int i4 = 1;
                while (i4 < length) {
                    int i5 = 0;
                    while (i5 < mapState.length) {
                        RealFieldElement realFieldElement6 = realFieldElement2;
                        RealFieldElement realFieldElement7 = (RealFieldElement) realFieldElementArr2[z6 ? 1 : 0][i5].multiply(embeddedRungeKuttaFieldIntegrator2.a[i4 - 1][z6 ? 1 : 0]);
                        int i6 = 1;
                        while (i6 < i4) {
                            int i7 = i4;
                            realFieldElement7 = (RealFieldElement) realFieldElement7.add((RealFieldElement) realFieldElementArr2[i6][i5].multiply(embeddedRungeKuttaFieldIntegrator2.a[i7 - 1][i6]));
                            i6++;
                            i5 = i5;
                            i4 = i7;
                        }
                        int i8 = i5;
                        realFieldElementArr3[i8] = (RealFieldElement) tArr2[i8].add(embeddedRungeKuttaFieldIntegrator2.getStepSize().multiply(realFieldElement7));
                        i5 = i8 + 1;
                        realFieldElement2 = realFieldElement6;
                        i4 = i4;
                    }
                    int i9 = i4;
                    realFieldElementArr2[i9] = embeddedRungeKuttaFieldIntegrator2.computeDerivatives((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(embeddedRungeKuttaFieldIntegrator2.getStepSize().multiply(embeddedRungeKuttaFieldIntegrator2.c[i9 - 1])), realFieldElementArr3);
                    i4 = i9 + 1;
                    realFieldElement2 = realFieldElement2;
                }
                RealFieldElement realFieldElement8 = realFieldElement2;
                int i10 = 0;
                while (i10 < mapState.length) {
                    RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElementArr2[z6 ? 1 : 0][i10].multiply(embeddedRungeKuttaFieldIntegrator2.b[z6 ? 1 : 0]);
                    int i11 = 1;
                    while (i11 < length) {
                        realFieldElement9 = (RealFieldElement) realFieldElement9.add((RealFieldElement) realFieldElementArr2[i11][i10].multiply(embeddedRungeKuttaFieldIntegrator2.b[i11]));
                        i11++;
                        i10 = i10;
                    }
                    int i12 = i10;
                    realFieldElementArr3[i12] = (RealFieldElement) tArr2[i12].add(embeddedRungeKuttaFieldIntegrator2.getStepSize().multiply(realFieldElement9));
                    i10 = i12 + 1;
                }
                realFieldElement5 = embeddedRungeKuttaFieldIntegrator2.estimateError(realFieldElementArr2, tArr2, realFieldElementArr3, embeddedRungeKuttaFieldIntegrator2.getStepSize());
                if (((RealFieldElement) realFieldElement5.subtract(1.0d)).getReal() < d2) {
                    realFieldElementArr7 = realFieldElementArr3;
                    realFieldElementArr6 = realFieldElementArr2;
                    realFieldElement4 = realFieldElement8;
                    z5 = false;
                } else {
                    realFieldElementArr7 = realFieldElementArr3;
                    realFieldElementArr6 = realFieldElementArr2;
                    z5 = false;
                    realFieldElement4 = embeddedRungeKuttaFieldIntegrator2.filterStep((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepSize().multiply(MathUtils.min(embeddedRungeKuttaFieldIntegrator2.maxGrowth, MathUtils.max(embeddedRungeKuttaFieldIntegrator2.minReduction, (RealFieldElement) embeddedRungeKuttaFieldIntegrator2.safety.multiply(realFieldElement5.pow(embeddedRungeKuttaFieldIntegrator2.exp))))), z3, z6);
                }
            }
            RealFieldElement[][] realFieldElementArr10 = realFieldElementArr6;
            RealFieldElement[] realFieldElementArr11 = realFieldElementArr7;
            RealFieldElement realFieldElement10 = (RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(embeddedRungeKuttaFieldIntegrator2.getStepSize());
            FieldODEStateAndDerivative fieldODEStateAndDerivative = new FieldODEStateAndDerivative(realFieldElement10, realFieldElementArr11, embeddedRungeKuttaFieldIntegrator2.fsal >= 0 ? realFieldElementArr10[embeddedRungeKuttaFieldIntegrator2.fsal] : embeddedRungeKuttaFieldIntegrator2.computeDerivatives(realFieldElement10, realFieldElementArr11));
            System.arraycopy(realFieldElementArr11, 0, tArr2, 0, mapState.length);
            embeddedRungeKuttaFieldIntegrator2.setStepStart(embeddedRungeKuttaFieldIntegrator2.acceptStep(embeddedRungeKuttaFieldIntegrator2.createInterpolator(z3, realFieldElementArr10, embeddedRungeKuttaFieldIntegrator2.getStepStart(), fieldODEStateAndDerivative, fieldExpandableODE.getMapper()), t));
            if (embeddedRungeKuttaFieldIntegrator2.isLastStep()) {
                realFieldElementArr = realFieldElementArr10;
                realFieldElement3 = realFieldElement;
            } else {
                realFieldElementArr = realFieldElementArr10;
                RealFieldElement realFieldElement11 = (RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepSize().multiply(MathUtils.min(embeddedRungeKuttaFieldIntegrator2.maxGrowth, MathUtils.max(embeddedRungeKuttaFieldIntegrator2.minReduction, (RealFieldElement) embeddedRungeKuttaFieldIntegrator2.safety.multiply(realFieldElement5.pow(embeddedRungeKuttaFieldIntegrator2.exp)))));
                double real = ((RealFieldElement) ((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(realFieldElement11)).subtract(t)).getReal();
                RealFieldElement filterStep = embeddedRungeKuttaFieldIntegrator2.filterStep(realFieldElement11, z3, !z3 ? real > d2 : real < d2);
                double real2 = ((RealFieldElement) ((RealFieldElement) embeddedRungeKuttaFieldIntegrator2.getStepStart().getTime().add(filterStep)).subtract(t)).getReal();
                realFieldElement3 = !z3 ? (real2 > d2 ? 1 : (real2 == d2 ? 0 : -1)) <= 0 : (real2 > d2 ? 1 : (real2 == d2 ? 0 : -1)) >= 0 ? (RealFieldElement) t.subtract(getStepStart().getTime()) : filterStep;
            }
            if (!isLastStep()) {
                embeddedRungeKuttaFieldIntegrator2 = this;
                tArr = tArr2;
                realFieldElementArr7 = realFieldElementArr11;
                d = d2;
                realFieldElementArr6 = realFieldElementArr;
                z2 = false;
            } else {
                FieldODEStateAndDerivative<T> stepStart = getStepStart();
                resetInternalState();
                return stepStart;
            }
        }
    }

    public T getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(T minReduction) {
        this.minReduction = minReduction;
    }

    public T getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(T maxGrowth) {
        this.maxGrowth = maxGrowth;
    }
}
