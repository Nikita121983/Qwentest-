package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import java.util.function.IntFunction;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MatrixFunction;

/* loaded from: classes10.dex */
public class Frequency extends Fixed2ArgFunction {
    public static final Function instance = new Frequency();

    private Frequency() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        MatrixFunction.MutableValueCollector collector = new MatrixFunction.MutableValueCollector(false, false);
        try {
            double[] values = collector.collectValues(arg0);
            double[] bins = collector.collectValues(arg1);
            int[] histogram = histogram(values, bins);
            NumberEval[] result = (NumberEval[]) Arrays.stream(histogram).boxed().map(new java.util.function.Function() { // from class: org.apache.poi.ss.formula.functions.Frequency$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new NumberEval(((Integer) obj).intValue());
                }
            }).toArray(new IntFunction() { // from class: org.apache.poi.ss.formula.functions.Frequency$$ExternalSyntheticLambda1
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Frequency.lambda$evaluate$0(i);
                }
            });
            return new CacheAreaEval(srcRowIndex, srcColumnIndex, (result.length + srcRowIndex) - 1, srcColumnIndex, result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ NumberEval[] lambda$evaluate$0(int x$0) {
        return new NumberEval[x$0];
    }

    static int findBin(double value, double[] bins) {
        int idx = Arrays.binarySearch(bins, value);
        return idx >= 0 ? idx + 1 : -idx;
    }

    static int[] histogram(double[] values, double[] bins) {
        int[] histogram = new int[bins.length + 1];
        for (double val : values) {
            int findBin = findBin(val, bins) - 1;
            histogram[findBin] = histogram[findBin] + 1;
        }
        return histogram;
    }
}
