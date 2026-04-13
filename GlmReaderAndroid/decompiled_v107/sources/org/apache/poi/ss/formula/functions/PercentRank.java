package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public final class PercentRank implements Function {
    public static final Function instance = new PercentRank();

    private PercentRank() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        int significance;
        if (args.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval ev = OperandResolver.getSingleValue(args[1], srcRowIndex, srcColumnIndex);
            double x = OperandResolver.coerceValueToDouble(ev);
            ArrayList<Double> numbers = new ArrayList<>();
            try {
                List<ValueEval> values = getValues(args[0], srcRowIndex, srcColumnIndex);
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
                return interpolate(x, closestMatchBelow, closestMatchAbove, (NumberEval) belowRank, (NumberEval) aboveRank, significance);
            }
            return aboveRank;
        }
        int lessThanCount = 0;
        int greaterThanCount = 0;
        for (Double d2 : numbers) {
            if (d2.doubleValue() < x) {
                lessThanCount++;
            } else if (d2.doubleValue() > x) {
                greaterThanCount++;
            }
        }
        if (greaterThanCount == numbers.size() || lessThanCount == numbers.size()) {
            return ErrorEval.NA;
        }
        if (lessThanCount + greaterThanCount == 0) {
            return new NumberEval(0.0d);
        }
        BigDecimal result = BigDecimal.valueOf(lessThanCount / (lessThanCount + greaterThanCount));
        return new NumberEval(round(result, significance));
    }

    @Internal
    public static NumberEval interpolate(double x, double closestMatchBelow, double closestMatchAbove, NumberEval belowRank, NumberEval aboveRank, int significance) {
        double diff = closestMatchAbove - closestMatchBelow;
        double pos = x - closestMatchBelow;
        BigDecimal rankDiff = new BigDecimal(NumberToTextConverter.toText(aboveRank.getNumberValue() - belowRank.getNumberValue()));
        BigDecimal result = BigDecimal.valueOf(belowRank.getNumberValue()).add(rankDiff.multiply(BigDecimal.valueOf(pos / diff)));
        return new NumberEval(round(result, significance));
    }

    @Internal
    public static double round(BigDecimal bd, int significance) {
        BigDecimal bd2 = bd.setScale(significance + 3, RoundingMode.HALF_UP);
        return bd2.setScale(significance, RoundingMode.DOWN).doubleValue();
    }

    @Internal
    public static List<ValueEval> getValues(ValueEval eval, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        if (eval instanceof AreaEval) {
            AreaEval ae = (AreaEval) eval;
            List<ValueEval> list = new ArrayList<>();
            for (int r = ae.getFirstRow(); r <= ae.getLastRow(); r++) {
                for (int c = ae.getFirstColumn(); c <= ae.getLastColumn(); c++) {
                    list.add(OperandResolver.getSingleValue(ae.getAbsoluteValue(r, c), r, c));
                }
            }
            return list;
        }
        return Collections.singletonList(OperandResolver.getSingleValue(eval, srcRowIndex, srcColumnIndex));
    }
}
