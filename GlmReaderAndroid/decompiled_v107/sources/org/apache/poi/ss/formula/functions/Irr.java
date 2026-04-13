package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;

/* loaded from: classes10.dex */
public final class Irr implements Function {
    private static final double ABSOLUTE_ACCURACY = 1.0E-7d;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) Irr.class);
    private static final int MAX_ITERATION_COUNT = 1000;

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        double guess;
        if (args.length == 0 || args.length > 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double[] values = AggregateFunction.ValueCollector.collectValues(args[0]);
            if (args.length == 2) {
                guess = NumericFunction.singleOperandEvaluate(args[1], srcRowIndex, srcColumnIndex);
            } else {
                guess = 0.1d;
            }
            double result = irr(values, guess);
            NumericFunction.checkValue(result);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public static double irr(double[] income) {
        return irr(income, 0.1d);
    }

    public static double irr(double[] values, double guess) {
        double d;
        double x0 = guess;
        int i = 0;
        while (true) {
            double d2 = Double.NaN;
            if (i < 1000) {
                double factor = 1.0d + x0;
                double denominator = factor;
                double d3 = 0.0d;
                if (denominator != 0.0d) {
                    double fValue = values[0];
                    double fDerivative = 0.0d;
                    int k = 1;
                    while (true) {
                        d = d2;
                        if (k >= values.length) {
                            break;
                        }
                        double value = values[k];
                        fValue += value / denominator;
                        denominator *= factor;
                        fDerivative -= (k * value) / denominator;
                        k++;
                        d2 = d;
                        d3 = d3;
                    }
                    if (fDerivative == d3) {
                        LOGGER.atWarn().log("Returning NaN because IRR has found an fDerivative of 0");
                        return d;
                    }
                    double x1 = x0 - (fValue / fDerivative);
                    if (Math.abs(x1 - x0) <= ABSOLUTE_ACCURACY) {
                        return x1;
                    }
                    x0 = x1;
                    i++;
                } else {
                    LOGGER.atWarn().log("Returning NaN because IRR has found an denominator of 0");
                    return Double.NaN;
                }
            } else {
                LOGGER.atWarn().log("Returning NaN because IRR has reached max number of iterations allowed: {}", (Object) 1000);
                return Double.NaN;
            }
        }
    }
}
