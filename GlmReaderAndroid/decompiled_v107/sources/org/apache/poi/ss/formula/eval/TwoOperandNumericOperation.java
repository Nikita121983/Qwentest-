package org.apache.poi.ss.formula.eval;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.util.NumberToTextConverter;

/* loaded from: classes10.dex */
public abstract class TwoOperandNumericOperation extends Fixed2ArgFunction implements ArrayFunction {
    public static final Function AddEval = new TwoOperandNumericOperation() { // from class: org.apache.poi.ss.formula.eval.TwoOperandNumericOperation.1
        @Override // org.apache.poi.ss.formula.eval.TwoOperandNumericOperation
        protected double evaluate(double d0, double d1) {
            return d0 + d1;
        }
    };
    public static final Function DivideEval = new TwoOperandNumericOperation() { // from class: org.apache.poi.ss.formula.eval.TwoOperandNumericOperation.2
        @Override // org.apache.poi.ss.formula.eval.TwoOperandNumericOperation
        protected double evaluate(double d0, double d1) throws EvaluationException {
            if (d1 == 0.0d) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            BigDecimal bd0 = new BigDecimal(NumberToTextConverter.toText(d0));
            BigDecimal bd1 = new BigDecimal(NumberToTextConverter.toText(d1));
            return bd0.divide(bd1, MathContext.DECIMAL128).doubleValue();
        }
    };
    public static final Function MultiplyEval = new TwoOperandNumericOperation() { // from class: org.apache.poi.ss.formula.eval.TwoOperandNumericOperation.3
        @Override // org.apache.poi.ss.formula.eval.TwoOperandNumericOperation
        protected double evaluate(double d0, double d1) {
            BigDecimal bd0 = new BigDecimal(NumberToTextConverter.toText(d0));
            BigDecimal bd1 = new BigDecimal(NumberToTextConverter.toText(d1));
            return bd0.multiply(bd1).doubleValue();
        }
    };
    public static final Function PowerEval = new TwoOperandNumericOperation() { // from class: org.apache.poi.ss.formula.eval.TwoOperandNumericOperation.4
        @Override // org.apache.poi.ss.formula.eval.TwoOperandNumericOperation
        protected double evaluate(double d0, double d1) {
            if (d0 < 0.0d && Math.abs(d1) > 0.0d && Math.abs(d1) < 1.0d) {
                return Math.pow(d0 * (-1.0d), d1) * (-1.0d);
            }
            return Math.pow(d0, d1);
        }
    };
    public static final Function SubtractEval = new SubtractEvalClass();

    protected abstract double evaluate(double d, double d2) throws EvaluationException;

    protected final double singleOperandEvaluate(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToDouble(ve);
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateTwoArrayArgs(args[0], args[1], srcRowIndex, srcColumnIndex, new BiFunction() { // from class: org.apache.poi.ss.formula.eval.TwoOperandNumericOperation$$ExternalSyntheticLambda0
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TwoOperandNumericOperation.this.m2523xabf97b18((ValueEval) obj, (ValueEval) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-eval-TwoOperandNumericOperation, reason: not valid java name */
    public /* synthetic */ ValueEval m2523xabf97b18(ValueEval vA, ValueEval vB) {
        try {
            double d0 = OperandResolver.coerceValueToDouble(vA);
            double d1 = OperandResolver.coerceValueToDouble(vB);
            double result = evaluate(d0, d1);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            double d0 = singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            double d1 = singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            double result = evaluate(d0, d1);
            if (result == 0.0d && !(this instanceof SubtractEvalClass)) {
                return NumberEval.ZERO;
            }
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                return ErrorEval.NUM_ERROR;
            }
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* loaded from: classes10.dex */
    private static final class SubtractEvalClass extends TwoOperandNumericOperation {
        @Override // org.apache.poi.ss.formula.eval.TwoOperandNumericOperation
        protected double evaluate(double d0, double d1) {
            return d0 - d1;
        }
    }
}
