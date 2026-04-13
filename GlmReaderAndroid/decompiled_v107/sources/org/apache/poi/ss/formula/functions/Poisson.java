package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Poisson implements FreeRefFunction {
    private static final double DEFAULT_RETURN_RESULT = 1.0d;
    public static final Poisson instance = new Poisson();

    private static boolean isDefaultResult(double x, double mean) {
        return x == 0.0d && mean == 0.0d;
    }

    private static void checkArgument(double aDouble) throws EvaluationException {
        NumericFunction.checkValue(aDouble);
        if (aDouble < 0.0d) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        return evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
    }

    public static ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        double result;
        if (args.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval arg0 = args[0];
        ValueEval arg1 = args[1];
        ValueEval arg2 = args[2];
        try {
            try {
                double x = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
                double mean = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
                if (isDefaultResult(x, mean)) {
                    return new NumberEval(1.0d);
                }
                checkArgument(x);
                checkArgument(mean);
                boolean cumulative = ((BoolEval) arg2).getBooleanValue();
                PoissonDistribution poissonDistribution = new PoissonDistribution(mean);
                if (cumulative) {
                    result = poissonDistribution.cumulativeProbability((int) x);
                } else {
                    result = poissonDistribution.probability((int) x);
                }
                NumericFunction.checkValue(result);
                return new NumberEval(result);
            } catch (EvaluationException e) {
                return ErrorEval.VALUE_INVALID;
            }
        } catch (EvaluationException e2) {
            return e2.getErrorEval();
        }
    }
}
