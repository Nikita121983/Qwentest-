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
public final class TDistLt extends Fixed3ArgFunction implements FreeRefFunction {
    public static final TDistLt instance = new TDistLt();

    private static double tdistCumulative(double x, int degreesOfFreedom) {
        TDistribution tdist = new TDistribution((RandomGenerator) null, degreesOfFreedom);
        return tdist.cumulativeProbability(x);
    }

    private static double tdistDensity(double x, int degreesOfFreedom) {
        TDistribution tdist = new TDistribution((RandomGenerator) null, degreesOfFreedom);
        return tdist.density(x);
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        try {
            Double number1 = evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (number1 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            Double number2 = evaluateValue(arg2, srcRowIndex, srcColumnIndex);
            if (number2 == null) {
                return ErrorEval.VALUE_INVALID;
            }
            int degreesOfFreedom = number2.intValue();
            if (degreesOfFreedom < 1) {
                return ErrorEval.NUM_ERROR;
            }
            Boolean cumulativeFlag = evaluateBoolean(arg3, srcRowIndex, srcColumnIndex);
            if (cumulativeFlag == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (cumulativeFlag.booleanValue()) {
                return new NumberEval(tdistCumulative(number1.doubleValue(), degreesOfFreedom));
            }
            return new NumberEval(tdistDensity(number1.doubleValue(), degreesOfFreedom));
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

    private static Boolean evaluateBoolean(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        return OperandResolver.coerceValueToBoolean(veText, false);
    }
}
