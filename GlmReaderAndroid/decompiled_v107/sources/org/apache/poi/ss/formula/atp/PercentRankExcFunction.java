package org.apache.poi.ss.formula.atp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.PercentRank;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class PercentRankExcFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new PercentRankExcFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private PercentRankExcFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        return evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
    }

    private ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        int significance;
        if (args.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval ev = OperandResolver.getSingleValue(args[1], srcRowIndex, srcColumnIndex);
            double x = OperandResolver.coerceValueToDouble(ev);
            ArrayList<Double> numbers = new ArrayList<>();
            try {
                List<ValueEval> values = PercentRank.getValues(args[0], srcRowIndex, srcColumnIndex);
                for (ValueEval ev2 : values) {
                    if (!(ev2 instanceof BlankEval) && !(ev2 instanceof MissingArgEval)) {
                        numbers.add(Double.valueOf(OperandResolver.coerceValueToDouble(ev2)));
                    }
                }
                if (numbers.isEmpty()) {
                    return ErrorEval.NUM_ERROR;
                }
                if (args.length <= 2) {
                    significance = 3;
                } else {
                    try {
                        ValueEval ev3 = OperandResolver.getSingleValue(args[2], srcRowIndex, srcColumnIndex);
                        int significance2 = OperandResolver.coerceValueToInt(ev3);
                        if (significance2 < 1) {
                            return ErrorEval.NUM_ERROR;
                        }
                        significance = significance2;
                    } catch (EvaluationException e) {
                        return e.getErrorEval();
                    }
                }
                return calculateRank(numbers, x, significance, true);
            } catch (EvaluationException e2) {
                ValueEval error = e2.getErrorEval();
                if (error != ErrorEval.NA) {
                    return error;
                }
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException e3) {
            ValueEval error2 = e3.getErrorEval();
            if (error2 == ErrorEval.NUM_ERROR) {
                return error2;
            }
            return ErrorEval.NUM_ERROR;
        }
    }

    private ValueEval calculateRank(List<Double> numbers, double x, int significance, boolean recurse) {
        double closestMatchBelow;
        double closestMatchBelow2 = Double.MIN_VALUE;
        double closestMatchAbove = Double.MAX_VALUE;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        if (!recurse) {
            closestMatchBelow = Double.MIN_VALUE;
        } else {
            for (Double d : numbers) {
                if (d.doubleValue() <= x && d.doubleValue() > closestMatchBelow2) {
                    closestMatchBelow2 = d.doubleValue();
                }
                if (d.doubleValue() > x && d.doubleValue() < closestMatchAbove) {
                    closestMatchAbove = d.doubleValue();
                }
                if (d.doubleValue() < min) {
                    min = d.doubleValue();
                }
                if (d.doubleValue() > max) {
                    max = d.doubleValue();
                }
            }
            if (x < min || x > max) {
                return ErrorEval.NA;
            }
            closestMatchBelow = closestMatchBelow2;
        }
        if (recurse && closestMatchBelow != x && closestMatchAbove != x) {
            int intermediateSignificance = significance < 5 ? 8 : significance + 3;
            ValueEval belowRank = calculateRank(numbers, closestMatchBelow, intermediateSignificance, false);
            if (!(belowRank instanceof NumberEval)) {
                return belowRank;
            }
            ValueEval aboveRank = calculateRank(numbers, closestMatchAbove, intermediateSignificance, false);
            if (aboveRank instanceof NumberEval) {
                return PercentRank.interpolate(x, closestMatchBelow, closestMatchAbove, (NumberEval) belowRank, (NumberEval) aboveRank, significance);
            }
            return aboveRank;
        }
        int lessThanCount = 0;
        Iterator<Double> it = numbers.iterator();
        while (it.hasNext()) {
            if (it.next().doubleValue() < x) {
                lessThanCount++;
            }
        }
        BigDecimal result = BigDecimal.valueOf((lessThanCount + 1) / (numbers.size() + 1));
        return new NumberEval(PercentRank.round(result, significance));
    }
}
