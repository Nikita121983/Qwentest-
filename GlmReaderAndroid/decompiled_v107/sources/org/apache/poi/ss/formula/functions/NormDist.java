package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class NormDist extends Fixed4ArgFunction implements FreeRefFunction {
    public static final NormDist instance = new NormDist();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double probability(double x, double mean, double stdev, boolean cumulative) {
        NormalDistribution normalDistribution = new NormalDistribution(mean, stdev);
        return cumulative ? normalDistribution.cumulativeProbability(x) : normalDistribution.density(x);
    }

    private NormDist() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function4Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg1, ValueEval arg2, ValueEval arg3, ValueEval arg4) {
        try {
            Double xval = evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (xval != null) {
                try {
                    Double mean = evaluateValue(arg2, srcRowIndex, srcColumnIndex);
                    if (mean != null) {
                        try {
                            Double stdev = evaluateValue(arg3, srcRowIndex, srcColumnIndex);
                            if (stdev == null) {
                                return ErrorEval.VALUE_INVALID;
                            }
                            if (stdev.doubleValue() <= 0.0d) {
                                return ErrorEval.NUM_ERROR;
                            }
                            try {
                                Boolean cumulative = OperandResolver.coerceValueToBoolean(arg4, false);
                                if (cumulative == null) {
                                    return ErrorEval.VALUE_INVALID;
                                }
                                return new NumberEval(probability(xval.doubleValue(), mean.doubleValue(), stdev.doubleValue(), cumulative.booleanValue()));
                            } catch (EvaluationException e) {
                                e = e;
                                return e.getErrorEval();
                            }
                        } catch (EvaluationException e2) {
                            e = e2;
                            return e.getErrorEval();
                        }
                    }
                    return ErrorEval.VALUE_INVALID;
                } catch (EvaluationException e3) {
                    e = e3;
                    return e.getErrorEval();
                }
            }
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e4) {
            e = e4;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 4) {
            return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1], args[2], args[3]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
