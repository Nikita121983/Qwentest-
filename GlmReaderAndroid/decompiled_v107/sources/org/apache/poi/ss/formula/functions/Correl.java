package org.apache.poi.ss.formula.functions;

import java.util.List;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Correl extends Fixed2ArgFunction {
    public static final Correl instance = new Correl();

    private Correl() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            List<DoubleList> arrays = ArrayFunctionUtils.getNumberArrays(arg0, arg1);
            PearsonsCorrelation pc = new PearsonsCorrelation();
            double correl = pc.correlation(arrays.get(0).toArray(), arrays.get(1).toArray());
            return new NumberEval(correl);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        } catch (Exception e2) {
            return ErrorEval.NA;
        }
    }
}
