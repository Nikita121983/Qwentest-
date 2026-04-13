package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class FieldBracketingNthOrderBrentSolver<T extends RealFieldElement<T>> implements BracketedRealFieldUnivariateSolver<T> {
    private static final int MAXIMAL_AGING = 2;
    private final T absoluteAccuracy;
    private IntegerSequence.Incrementor evaluations;
    private final Field<T> field;
    private final T functionValueAccuracy;
    private final int maximalOrder;
    private final T relativeAccuracy;

    public FieldBracketingNthOrderBrentSolver(T relativeAccuracy, T absoluteAccuracy, T functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
        if (maximalOrder < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), 2, true);
        }
        this.field = relativeAccuracy.getField();
        this.maximalOrder = maximalOrder;
        this.absoluteAccuracy = absoluteAccuracy;
        this.relativeAccuracy = relativeAccuracy;
        this.functionValueAccuracy = functionValueAccuracy;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T solve(int i, RealFieldUnivariateFunction<T> realFieldUnivariateFunction, T t, T t2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return (T) solve(i, realFieldUnivariateFunction, t, t2, (RealFieldElement) ((RealFieldElement) t.add(t2)).divide(2.0d), allowedSolution);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v39, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r0v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r16v1, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v14, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v20, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v23, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v31, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v34, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r21v0, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r34v0 */
    /* JADX WARN: Type inference failed for: r34v1, types: [T extends org.apache.commons.math3.RealFieldElement<T>] */
    /* JADX WARN: Type inference failed for: r34v2 */
    /* JADX WARN: Type inference failed for: r3v1, types: [org.apache.commons.math3.RealFieldElement[]] */
    /* JADX WARN: Type inference failed for: r3v18, types: [java.lang.Object, org.apache.commons.math3.RealFieldElement[]] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r44v0, types: [org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver<T extends org.apache.commons.math3.RealFieldElement<T>>, org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, org.apache.commons.math3.analysis.RealFieldUnivariateFunction] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [T extends org.apache.commons.math3.RealFieldElement<T>, java.lang.Object, org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    @Override // org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver
    public T solve(int i, RealFieldUnivariateFunction<T> realFieldUnivariateFunction, T t, T t2, T t3, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        int i2;
        int i3;
        T t4;
        RealFieldElement realFieldElement;
        T t5;
        T t6;
        RealFieldElement realFieldElement2;
        int i4;
        int i5;
        int i6;
        RealFieldElement realFieldElement3;
        RealFieldElement realFieldElement4;
        T guessX;
        T t7;
        RealFieldElement[] realFieldElementArr;
        int i7;
        int i8;
        FieldBracketingNthOrderBrentSolver fieldBracketingNthOrderBrentSolver = this;
        ?? r6 = realFieldUnivariateFunction;
        MathUtils.checkNotNull(r6);
        fieldBracketingNthOrderBrentSolver.evaluations = fieldBracketingNthOrderBrentSolver.evaluations.withMaximalCount(i).withStart(0);
        T zero = fieldBracketingNthOrderBrentSolver.field.getZero();
        RealFieldElement realFieldElement5 = (RealFieldElement) zero.add(Double.NaN);
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) MathArrays.buildArray(fieldBracketingNthOrderBrentSolver.field, fieldBracketingNthOrderBrentSolver.maximalOrder + 1);
        ?? r3 = (RealFieldElement[]) MathArrays.buildArray(fieldBracketingNthOrderBrentSolver.field, fieldBracketingNthOrderBrentSolver.maximalOrder + 1);
        realFieldElementArr2[0] = t;
        realFieldElementArr2[1] = t3;
        realFieldElementArr2[2] = t2;
        fieldBracketingNthOrderBrentSolver.evaluations.increment();
        r3[1] = r6.value(realFieldElementArr2[1]);
        if (Precision.equals(r3[1].getReal(), 0.0d, 1)) {
            return (T) realFieldElementArr2[1];
        }
        fieldBracketingNthOrderBrentSolver.evaluations.increment();
        r3[0] = r6.value(realFieldElementArr2[0]);
        if (Precision.equals(r3[0].getReal(), 0.0d, 1)) {
            return (T) realFieldElementArr2[0];
        }
        if (((RealFieldElement) r3[0].multiply(r3[1])).getReal() < 0.0d) {
            i2 = 2;
            i3 = 1;
        } else {
            fieldBracketingNthOrderBrentSolver.evaluations.increment();
            r3[2] = r6.value(realFieldElementArr2[2]);
            if (Precision.equals(r3[2].getReal(), 0.0d, 1)) {
                return (T) realFieldElementArr2[2];
            }
            if (((RealFieldElement) r3[1].multiply(r3[2])).getReal() < 0.0d) {
                i2 = 3;
                i3 = 2;
            } else {
                throw new NoBracketingException(realFieldElementArr2[0].getReal(), realFieldElementArr2[2].getReal(), r3[0].getReal(), r3[2].getReal());
            }
        }
        RealFieldElement[] realFieldElementArr3 = (RealFieldElement[]) MathArrays.buildArray(fieldBracketingNthOrderBrentSolver.field, realFieldElementArr2.length);
        RealFieldElement realFieldElement6 = realFieldElementArr2[i3 - 1];
        ?? r16 = r3[i3 - 1];
        RealFieldElement realFieldElement7 = (RealFieldElement) realFieldElement6.abs();
        RealFieldElement realFieldElement8 = (RealFieldElement) r16.abs();
        RealFieldElement realFieldElement9 = realFieldElementArr2[i3];
        ?? r21 = r3[i3];
        double d = 0.0d;
        RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement9.abs();
        RealFieldElement realFieldElement11 = (RealFieldElement) r21.abs();
        T t8 = r16;
        RealFieldElement realFieldElement12 = realFieldElement8;
        int i9 = 0;
        T t9 = realFieldElement9;
        T t10 = r21;
        int i10 = i2;
        RealFieldElement realFieldElement13 = realFieldElement6;
        RealFieldElement realFieldElement14 = realFieldElement11;
        int i11 = 0;
        RealFieldUnivariateFunction<T> realFieldUnivariateFunction2 = r6;
        while (true) {
            RealFieldElement realFieldElement15 = ((RealFieldElement) realFieldElement7.subtract(realFieldElement10)).getReal() < d ? realFieldElement10 : realFieldElement7;
            RealFieldElement realFieldElement16 = ((RealFieldElement) realFieldElement12.subtract(realFieldElement14)).getReal() < d ? realFieldElement14 : realFieldElement12;
            int i12 = i3;
            Object obj = r3;
            RealFieldElement realFieldElement17 = realFieldElement7;
            RealFieldElement realFieldElement18 = (RealFieldElement) fieldBracketingNthOrderBrentSolver.absoluteAccuracy.add(fieldBracketingNthOrderBrentSolver.relativeAccuracy.multiply(realFieldElement15));
            if (((RealFieldElement) ((RealFieldElement) t9.subtract(realFieldElement13)).subtract(realFieldElement18)).getReal() <= d) {
                t4 = realFieldElement13;
                realFieldElement = realFieldElement14;
                break;
            }
            RealFieldElement realFieldElement19 = realFieldElement16;
            if (((RealFieldElement) realFieldElement19.subtract(fieldBracketingNthOrderBrentSolver.functionValueAccuracy)).getReal() < d) {
                t4 = realFieldElement13;
                realFieldElement = realFieldElement14;
                break;
            }
            RealFieldElement realFieldElement20 = realFieldElement13;
            if (i9 >= 2) {
                t5 = (RealFieldElement) ((RealFieldElement) t10.divide(16.0d)).negate();
            } else if (i11 >= 2) {
                t5 = (RealFieldElement) ((RealFieldElement) t8.divide(16.0d)).negate();
            } else {
                t5 = zero;
            }
            int i13 = 0;
            int i14 = i10;
            while (true) {
                T t11 = t5;
                System.arraycopy(realFieldElementArr2, i13, realFieldElementArr3, i13, i14 - i13);
                t6 = t10;
                realFieldElement2 = realFieldElement14;
                i4 = i14;
                i5 = i12;
                RealFieldElement realFieldElement21 = realFieldElement19;
                i6 = i9;
                realFieldElement3 = realFieldElement20;
                realFieldElement4 = realFieldElement10;
                r3 = obj;
                RealFieldElement realFieldElement22 = realFieldElement18;
                RealFieldElement[] realFieldElementArr4 = realFieldElementArr3;
                int i15 = i13;
                guessX = guessX(t11, realFieldElementArr4, r3, i15, i4);
                if (((RealFieldElement) guessX.subtract(realFieldElement3)).getReal() <= d || ((RealFieldElement) guessX.subtract(t9)).getReal() >= d) {
                    t7 = t11;
                    realFieldElementArr = realFieldElementArr4;
                    if (i5 - i15 >= i4 - i5) {
                        i7 = i15 + 1;
                    } else {
                        i4--;
                        i7 = i15;
                    }
                    guessX = realFieldElement5;
                } else {
                    t7 = t11;
                    realFieldElementArr = realFieldElementArr4;
                    i7 = i15;
                }
                if (!Double.isNaN(guessX.getReal()) || i4 - i7 <= 1) {
                    break;
                }
                i14 = i4;
                realFieldElement14 = realFieldElement2;
                t10 = t6;
                i12 = i5;
                realFieldElement19 = realFieldElement21;
                i13 = i7;
                realFieldElement18 = realFieldElement22;
                realFieldElement10 = realFieldElement4;
                t5 = t7;
                realFieldElementArr3 = realFieldElementArr;
                obj = r3;
                realFieldElement20 = realFieldElement3;
                i9 = i6;
            }
            if (Double.isNaN(guessX.getReal())) {
                guessX = (T) realFieldElement3.add((RealFieldElement) ((RealFieldElement) t9.subtract(realFieldElement3)).divide(2.0d));
                i7 = i5 - 1;
                i4 = i5;
            }
            this.evaluations.increment();
            T value = realFieldUnivariateFunction2.value(guessX);
            int i16 = i4;
            if (Precision.equals(value.getReal(), d, 1)) {
                return (T) guessX;
            }
            int i17 = i10;
            if (i17 > 2 && i16 - i7 != i17) {
                i17 = i16 - i7;
                System.arraycopy(realFieldElementArr2, i7, realFieldElementArr2, 0, i17);
                System.arraycopy(r3, i7, r3, 0, i17);
                i8 = i5 - i7;
            } else if (i17 != realFieldElementArr2.length) {
                i8 = i5;
            } else {
                i17--;
                if (i5 < (realFieldElementArr2.length + 1) / 2) {
                    i8 = i5;
                } else {
                    System.arraycopy(realFieldElementArr2, 1, realFieldElementArr2, 0, i17);
                    System.arraycopy(r3, 1, r3, 0, i17);
                    i8 = i5 - 1;
                }
            }
            System.arraycopy(realFieldElementArr2, i8, realFieldElementArr2, i8 + 1, i17 - i8);
            realFieldElementArr2[i8] = guessX;
            System.arraycopy(r3, i8, r3, i8 + 1, i17 - i8);
            r3[i8] = value;
            i10 = i17 + 1;
            if (((RealFieldElement) value.multiply(t8)).getReal() <= 0.0d) {
                i9 = i6 + 1;
                t9 = guessX;
                t10 = value;
                realFieldElement14 = (RealFieldElement) value.abs();
                i11 = 0;
                realFieldElement13 = realFieldElement3;
            } else {
                i9 = 0;
                i11++;
                i8++;
                realFieldElement13 = guessX;
                t8 = value;
                realFieldElement12 = (RealFieldElement) value.abs();
                realFieldElement14 = realFieldElement2;
                t10 = t6;
            }
            i3 = i8;
            fieldBracketingNthOrderBrentSolver = this;
            realFieldUnivariateFunction2 = realFieldUnivariateFunction;
            realFieldElement7 = realFieldElement17;
            realFieldElement10 = realFieldElement4;
            realFieldElementArr3 = realFieldElementArr;
            d = 0.0d;
            t9 = t9;
        }
        switch (allowedSolution) {
            case ANY_SIDE:
                return ((RealFieldElement) realFieldElement12.subtract(realFieldElement)).getReal() < 0.0d ? t4 : t9;
            case LEFT_SIDE:
                return t4;
            case RIGHT_SIDE:
                return t9;
            case BELOW_SIDE:
                return t8.getReal() <= 0.0d ? t4 : t9;
            case ABOVE_SIDE:
                return t8.getReal() < 0.0d ? t9 : t4;
            default:
                throw new MathInternalError(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T guessX(T targetY, T[] tArr, T[] y, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            int delta = (i + 1) - start;
            for (int j = end - 1; j > i; j--) {
                tArr[j] = (RealFieldElement) ((RealFieldElement) tArr[j].subtract((Decimal64) tArr[j - 1])).divide((RealFieldElement) y[j].subtract(y[j - delta]));
            }
        }
        T zero = this.field.getZero();
        for (int j2 = end - 1; j2 >= start; j2--) {
            zero = (T) tArr[j2].add((Decimal64) zero.multiply(targetY.subtract(y[j2])));
        }
        return zero;
    }
}
