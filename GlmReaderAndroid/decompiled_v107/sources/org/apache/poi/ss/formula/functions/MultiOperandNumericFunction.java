package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

/* loaded from: classes10.dex */
public abstract class MultiOperandNumericFunction implements Function {
    private static final int DEFAULT_MAX_NUM_OPERANDS = SpreadsheetVersion.EXCEL2007.getMaxFunctionArgs();
    private EvalConsumer<BlankEval, DoubleList> blankConsumer;
    private EvalConsumer<BoolEval, DoubleList> boolByRefConsumer;
    private EvalConsumer<BoolEval, DoubleList> boolByValueConsumer;
    private EvalConsumer<MissingArgEval, DoubleList> missingArgConsumer = ConsumerFactory.createForMissingArg(Policy.SKIP);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface EvalConsumer<T, R> {
        void accept(T t, R r) throws EvaluationException;
    }

    /* loaded from: classes10.dex */
    public enum Policy {
        COERCE,
        SKIP,
        ERROR
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract double evaluate(double[] dArr) throws EvaluationException;

    /* JADX INFO: Access modifiers changed from: protected */
    public MultiOperandNumericFunction(boolean isReferenceBoolCounted, boolean isBlankCounted) {
        this.boolByRefConsumer = ConsumerFactory.createForBoolEval(isReferenceBoolCounted ? Policy.COERCE : Policy.SKIP);
        this.boolByValueConsumer = ConsumerFactory.createForBoolEval(Policy.COERCE);
        this.blankConsumer = ConsumerFactory.createForBlank(isBlankCounted ? Policy.COERCE : Policy.SKIP);
    }

    public void setMissingArgPolicy(Policy policy) {
        this.missingArgConsumer = ConsumerFactory.createForMissingArg(policy);
    }

    public void setBlankEvalPolicy(Policy policy) {
        this.blankConsumer = ConsumerFactory.createForBlank(policy);
    }

    protected boolean treatStringsAsZero() {
        return false;
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public final ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
        try {
            double[] values = getNumberArray(args);
            double d = evaluate(values);
            if (!Double.isNaN(d) && !Double.isInfinite(d)) {
                return new NumberEval(d);
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    protected int getMaxNumOperands() {
        return DEFAULT_MAX_NUM_OPERANDS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double[] getNumberArray(ValueEval[] operands) throws EvaluationException {
        if (operands.length > getMaxNumOperands()) {
            throw EvaluationException.invalidValue();
        }
        DoubleList retval = new DoubleList();
        for (ValueEval operand : operands) {
            collectValues(operand, retval);
        }
        return retval.toArray();
    }

    public boolean isSubtotalCounted() {
        return true;
    }

    public boolean isHiddenRowCounted() {
        return true;
    }

    private void collectValues(ValueEval operand, DoubleList temp) throws EvaluationException {
        if (operand instanceof ThreeDEval) {
            ThreeDEval ae = (ThreeDEval) operand;
            for (int sIx = ae.getFirstSheetIndex(); sIx <= ae.getLastSheetIndex(); sIx++) {
                int width = ae.getWidth();
                int height = ae.getHeight();
                for (int rrIx = 0; rrIx < height; rrIx++) {
                    for (int rcIx = 0; rcIx < width; rcIx++) {
                        ValueEval ve = ae.getValue(sIx, rrIx, rcIx);
                        if ((isSubtotalCounted() || !ae.isSubTotal(rrIx, rcIx)) && (isHiddenRowCounted() || !ae.isRowHidden(rrIx))) {
                            collectValue(ve, !treatStringsAsZero(), temp);
                        }
                    }
                }
            }
            return;
        }
        if (operand instanceof TwoDEval) {
            TwoDEval ae2 = (TwoDEval) operand;
            int width2 = ae2.getWidth();
            int height2 = ae2.getHeight();
            for (int rrIx2 = 0; rrIx2 < height2; rrIx2++) {
                for (int rcIx2 = 0; rcIx2 < width2; rcIx2++) {
                    ValueEval ve2 = ae2.getValue(rrIx2, rcIx2);
                    if (isSubtotalCounted() || !ae2.isSubTotal(rrIx2, rcIx2)) {
                        collectValue(ve2, !treatStringsAsZero(), temp);
                    }
                }
            }
            return;
        }
        if (operand instanceof RefEval) {
            RefEval re = (RefEval) operand;
            for (int sIx2 = re.getFirstSheetIndex(); sIx2 <= re.getLastSheetIndex(); sIx2++) {
                collectValue(re.getInnerValueEval(sIx2), !treatStringsAsZero(), temp);
            }
            return;
        }
        collectValue(operand, false, temp);
    }

    private void collectValue(ValueEval ve, boolean isViaReference, DoubleList temp) throws EvaluationException {
        if (ve == null) {
            throw new IllegalArgumentException("ve must not be null");
        }
        if (ve instanceof BoolEval) {
            BoolEval boolEval = (BoolEval) ve;
            if (isViaReference) {
                this.boolByRefConsumer.accept(boolEval, temp);
                return;
            } else {
                this.boolByValueConsumer.accept(boolEval, temp);
                return;
            }
        }
        if (ve instanceof NumericValueEval) {
            NumericValueEval ne = (NumericValueEval) ve;
            temp.add(ne.getNumberValue());
            return;
        }
        if (ve instanceof StringValueEval) {
            if (isViaReference) {
                return;
            }
            if (treatStringsAsZero()) {
                temp.add(0.0d);
                return;
            }
            String s = ((StringValueEval) ve).getStringValue().trim();
            Double d = OperandResolver.parseDouble(s);
            if (d == null) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            temp.add(d.doubleValue());
            return;
        }
        if (ve instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) ve);
        }
        if (ve == BlankEval.instance) {
            this.blankConsumer.accept((BlankEval) ve, temp);
        } else {
            if (ve == MissingArgEval.instance) {
                this.missingArgConsumer.accept((MissingArgEval) ve, temp);
                return;
            }
            throw new IllegalStateException("Invalid ValueEval type passed for conversion: " + ve);
        }
    }

    /* loaded from: classes10.dex */
    private static class ConsumerFactory {
        private ConsumerFactory() {
        }

        static EvalConsumer<MissingArgEval, DoubleList> createForMissingArg(Policy policy) {
            EvalConsumer<MissingArgEval, DoubleList> coercer = new EvalConsumer() { // from class: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda2
                @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.EvalConsumer
                public final void accept(Object obj, Object obj2) {
                    ((DoubleList) obj2).add(0.0d);
                }
            };
            return createAny(coercer, policy);
        }

        static EvalConsumer<BoolEval, DoubleList> createForBoolEval(Policy policy) {
            EvalConsumer<BoolEval, DoubleList> coercer = new EvalConsumer() { // from class: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda1
                @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.EvalConsumer
                public final void accept(Object obj, Object obj2) {
                    ((DoubleList) obj2).add(((BoolEval) obj).getNumberValue());
                }
            };
            return createAny(coercer, policy);
        }

        static EvalConsumer<BlankEval, DoubleList> createForBlank(Policy policy) {
            EvalConsumer<BlankEval, DoubleList> coercer = new EvalConsumer() { // from class: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda3
                @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.EvalConsumer
                public final void accept(Object obj, Object obj2) {
                    ((DoubleList) obj2).add(0.0d);
                }
            };
            return createAny(coercer, policy);
        }

        private static <T> EvalConsumer<T, DoubleList> createAny(EvalConsumer<T, DoubleList> coercer, Policy policy) {
            switch (policy) {
                case COERCE:
                    return coercer;
                case SKIP:
                    return doNothing();
                case ERROR:
                    return throwValueInvalid();
                default:
                    throw new AssertionError();
            }
        }

        private static <T> EvalConsumer<T, DoubleList> doNothing() {
            return new EvalConsumer() { // from class: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda0
                @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.EvalConsumer
                public final void accept(Object obj, Object obj2) {
                    MultiOperandNumericFunction.ConsumerFactory.lambda$doNothing$3(obj, (DoubleList) obj2);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$doNothing$3(Object value, DoubleList receiver) throws EvaluationException {
        }

        private static <T> EvalConsumer<T, DoubleList> throwValueInvalid() {
            return new EvalConsumer() { // from class: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda4
                @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.EvalConsumer
                public final void accept(Object obj, Object obj2) {
                    MultiOperandNumericFunction.ConsumerFactory.lambda$throwValueInvalid$4(obj, (DoubleList) obj2);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$throwValueInvalid$4(Object value, DoubleList receiver) throws EvaluationException {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
    }
}
