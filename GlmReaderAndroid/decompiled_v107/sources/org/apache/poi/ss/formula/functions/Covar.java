package org.apache.poi.ss.formula.functions;

import java.util.List;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Covar extends Fixed2ArgFunction implements FreeRefFunction {
    public static final Covar instanceP = new Covar(false);
    public static final Covar instanceS = new Covar(true);
    private final boolean sampleBased;

    private Covar(boolean sampleBased) {
        this.sampleBased = sampleBased;
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            List<DoubleList> arrays = ArrayFunctionUtils.getNumberArrays(arg0, arg1);
            Covariance covar = new Covariance();
            double result = covar.covariance(arrays.get(0).toArray(), arrays.get(1).toArray(), this.sampleBased);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        } catch (Exception e2) {
            return ErrorEval.NA;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
    }
}
