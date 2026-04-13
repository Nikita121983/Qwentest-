package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class TDist extends Fixed3ArgFunction implements FreeRefFunction {
    public static final TDist instance = new TDist();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double tdistOneTail(double x, int degreesOfFreedom) {
        TDistribution tdist = new TDistribution((RandomGenerator) null, degreesOfFreedom);
        return 1.0d - tdist.cumulativeProbability(x);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double tdistTwoTails(double x, int degreesOfFreedom) {
        return tdistOneTail(x, degreesOfFreedom) * 2.0d;
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        try {
            Double number1 = evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (number1 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (number1.doubleValue() < 0.0d) {
                return ErrorEval.NUM_ERROR;
            }
            Double number2 = evaluateValue(arg2, srcRowIndex, srcColumnIndex);
            if (number2 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int degreesOfFreedom = number2.intValue();
            if (degreesOfFreedom < 1) {
                return ErrorEval.NUM_ERROR;
            }
            Double number3 = evaluateValue(arg3, srcRowIndex, srcColumnIndex);
            if (number3 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int tails = number3.intValue();
            if (tails != 1 && tails != 2) {
                return ErrorEval.NUM_ERROR;
            }
            if (tails == 2) {
                return new NumberEval(tdistTwoTails(number1.doubleValue(), degreesOfFreedom));
            }
            return new NumberEval(tdistOneTail(number1.doubleValue(), degreesOfFreedom));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 3) {
            return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1], args[2]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
