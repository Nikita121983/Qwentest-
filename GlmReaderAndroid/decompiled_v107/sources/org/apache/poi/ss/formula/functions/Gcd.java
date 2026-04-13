package org.apache.poi.ss.formula.functions;

import java.util.ArrayList;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Gcd implements FreeRefFunction {
    public static final Gcd instance = new Gcd();
    private static final long MAX_INPUT = (long) Math.pow(2.0d, 53.0d);

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        if (args.length == 1) {
            try {
                ValueEval v1 = OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
                double d = OperandResolver.coerceValueToDouble(v1);
                if (isInvalidInput(d)) {
                    return ErrorEval.NUM_ERROR;
                }
                return new NumberEval((long) d);
            } catch (EvaluationException e) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        try {
            ArrayList<Long> evals = new ArrayList<>();
            for (ValueEval arg : args) {
                ValueEval ve = OperandResolver.getSingleValue(arg, ec.getRowIndex(), ec.getColumnIndex());
                double d2 = OperandResolver.coerceValueToDouble(ve);
                if (isInvalidInput(d2)) {
                    return ErrorEval.NUM_ERROR;
                }
                evals.add(Long.valueOf((long) d2));
            }
            long result = evals.get(0).longValue();
            for (int i = 1; i < evals.size(); i++) {
                result = ArithmeticUtils.gcd(result, evals.get(i).longValue());
            }
            return new NumberEval(result);
        } catch (EvaluationException e2) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    private boolean isInvalidInput(double d) {
        return d < 0.0d || d > ((double) MAX_INPUT);
    }
}
