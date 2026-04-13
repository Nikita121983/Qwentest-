package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public abstract class AdaptiveStepsizeFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> {
    private T initialStep;
    protected int mainSetDimension;
    private T maxStep;
    private T minStep;
    protected double scalAbsoluteTolerance;
    protected double scalRelativeTolerance;
    protected double[] vecAbsoluteTolerance;
    protected double[] vecRelativeTolerance;

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String name, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(field, name);
        setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        resetInternalState();
    }

    public AdaptiveStepsizeFieldIntegrator(Field<T> field, String name, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, name);
        setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        resetInternalState();
    }

    public void setStepSizeControl(double minimalStep, double maximalStep, double absoluteTolerance, double relativeTolerance) {
        this.minStep = (T) getField().getZero().add(FastMath.abs(minimalStep));
        this.maxStep = (T) getField().getZero().add(FastMath.abs(maximalStep));
        this.initialStep = (T) getField().getOne().negate();
        this.scalAbsoluteTolerance = absoluteTolerance;
        this.scalRelativeTolerance = relativeTolerance;
        this.vecAbsoluteTolerance = null;
        this.vecRelativeTolerance = null;
    }

    public void setStepSizeControl(double minimalStep, double maximalStep, double[] absoluteTolerance, double[] relativeTolerance) {
        this.minStep = (T) getField().getZero().add(FastMath.abs(minimalStep));
        this.maxStep = (T) getField().getZero().add(FastMath.abs(maximalStep));
        this.initialStep = (T) getField().getOne().negate();
        this.scalAbsoluteTolerance = 0.0d;
        this.scalRelativeTolerance = 0.0d;
        this.vecAbsoluteTolerance = (double[]) absoluteTolerance.clone();
        this.vecRelativeTolerance = (double[]) relativeTolerance.clone();
    }

    public void setInitialStepSize(T initialStepSize) {
        if (((RealFieldElement) initialStepSize.subtract(this.minStep)).getReal() < 0.0d || ((RealFieldElement) initialStepSize.subtract(this.maxStep)).getReal() > 0.0d) {
            this.initialStep = (T) getField().getOne().negate();
        } else {
            this.initialStep = initialStepSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.AbstractFieldIntegrator
    public void sanityChecks(FieldODEState<T> eqn, T t) throws DimensionMismatchException, NumberIsTooSmallException {
        super.sanityChecks(eqn, t);
        this.mainSetDimension = eqn.getStateDimension();
        if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length);
        }
        if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension) {
            throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T initializeStep(boolean z, int i, T[] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldEquationsMapper<T> fieldEquationsMapper) throws MaxCountExceededException, DimensionMismatchException {
        if (this.initialStep.getReal() > 0.0d) {
            T t = this.initialStep;
            return z ? t : (T) t.negate();
        }
        T[] mapState = fieldEquationsMapper.mapState(fieldODEStateAndDerivative);
        T[] mapDerivative = fieldEquationsMapper.mapDerivative(fieldODEStateAndDerivative);
        RealFieldElement realFieldElement = (RealFieldElement) getField().getZero();
        RealFieldElement realFieldElement2 = (RealFieldElement) getField().getZero();
        for (int i2 = 0; i2 < tArr.length; i2++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) mapState[i2].divide(tArr[i2]);
            realFieldElement = (RealFieldElement) realFieldElement.add((RealFieldElement) realFieldElement3.multiply(realFieldElement3));
            RealFieldElement realFieldElement4 = (RealFieldElement) mapDerivative[i2].divide(tArr[i2]);
            realFieldElement2 = (RealFieldElement) realFieldElement2.add((RealFieldElement) realFieldElement4.multiply(realFieldElement4));
        }
        RealFieldElement realFieldElement5 = (RealFieldElement) ((realFieldElement.getReal() < 1.0E-10d || realFieldElement2.getReal() < 1.0E-10d) ? ((RealFieldElement) getField().getZero()).add(1.0E-6d) : ((RealFieldElement) ((RealFieldElement) realFieldElement.divide(realFieldElement2)).sqrt()).multiply(0.01d));
        if (!z) {
            realFieldElement5 = (RealFieldElement) realFieldElement5.negate();
        }
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(getField(), mapState.length);
        for (int i3 = 0; i3 < mapState.length; i3++) {
            realFieldElementArr[i3] = (RealFieldElement) mapState[i3].add(mapDerivative[i3].multiply(realFieldElement5));
        }
        RealFieldElement[] computeDerivatives = computeDerivatives((RealFieldElement) fieldODEStateAndDerivative.getTime().add(realFieldElement5), realFieldElementArr);
        RealFieldElement realFieldElement6 = (RealFieldElement) getField().getZero();
        for (int i4 = 0; i4 < tArr.length; i4++) {
            RealFieldElement realFieldElement7 = (RealFieldElement) ((RealFieldElement) computeDerivatives[i4].subtract(mapDerivative[i4])).divide(tArr[i4]);
            realFieldElement6 = (RealFieldElement) realFieldElement6.add((RealFieldElement) realFieldElement7.multiply(realFieldElement7));
        }
        RealFieldElement max = MathUtils.max((RealFieldElement) realFieldElement2.sqrt(), (RealFieldElement) ((RealFieldElement) realFieldElement6.sqrt()).divide(realFieldElement5));
        T t2 = (T) MathUtils.max(this.minStep, MathUtils.min(this.maxStep, MathUtils.max(MathUtils.min((RealFieldElement) ((RealFieldElement) realFieldElement5.abs()).multiply(100), max.getReal() < 1.0E-15d ? MathUtils.max((RealFieldElement) ((RealFieldElement) getField().getZero()).add(1.0E-6d), (RealFieldElement) ((RealFieldElement) realFieldElement5.abs()).multiply(0.001d)) : (RealFieldElement) ((RealFieldElement) ((RealFieldElement) max.multiply(100)).reciprocal()).pow(1.0d / i)), (RealFieldElement) ((RealFieldElement) fieldODEStateAndDerivative.getTime().abs()).multiply(1.0E-12d))));
        if (!z) {
            return (T) t2.negate();
        }
        return t2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T filterStep(T h, boolean forward, boolean acceptSmall) throws NumberIsTooSmallException {
        T filteredH = h;
        if (((RealFieldElement) ((RealFieldElement) h.abs()).subtract(this.minStep)).getReal() < 0.0d) {
            if (acceptSmall) {
                T t = this.minStep;
                if (!forward) {
                    t = (T) t.negate();
                }
                filteredH = t;
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(((RealFieldElement) h.abs()).getReal()), Double.valueOf(this.minStep.getReal()), true);
            }
        }
        if (((RealFieldElement) filteredH.subtract(this.maxStep)).getReal() > 0.0d) {
            T filteredH2 = this.maxStep;
            return filteredH2;
        }
        if (((RealFieldElement) filteredH.add(this.maxStep)).getReal() < 0.0d) {
            return (T) this.maxStep.negate();
        }
        return filteredH;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void resetInternalState() {
        setStepStart(null);
        setStepSize((RealFieldElement) ((RealFieldElement) this.minStep.multiply(this.maxStep)).sqrt());
    }

    public T getMinStep() {
        return this.minStep;
    }

    public T getMaxStep() {
        return this.maxStep;
    }
}
