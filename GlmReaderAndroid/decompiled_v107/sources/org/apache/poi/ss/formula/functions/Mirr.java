package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;

/* loaded from: classes10.dex */
public class Mirr extends MultiOperandNumericFunction {
    public Mirr() {
        super(false, false);
    }

    @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
    protected int getMaxNumOperands() {
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
    public double evaluate(double[] values) throws EvaluationException {
        double financeRate = values[values.length - 2];
        double reinvestRate = values[values.length - 1];
        double[] mirrValues = Arrays.copyOf(values, values.length - 2);
        boolean mirrValuesAreAllNegatives = true;
        boolean mirrValuesAreAllPositives = true;
        int length = mirrValues.length;
        for (int i = 0; i < length; i++) {
            double mirrValue = mirrValues[i];
            mirrValuesAreAllNegatives &= mirrValue < 0.0d;
            mirrValuesAreAllPositives &= mirrValue > 0.0d;
        }
        if (mirrValuesAreAllNegatives) {
            return -1.0d;
        }
        if (mirrValuesAreAllPositives) {
            throw new EvaluationException(ErrorEval.DIV_ZERO);
        }
        return mirr(mirrValues, financeRate, reinvestRate);
    }

    private static double mirr(double[] in, double financeRate, double reinvestRate) {
        double d;
        double value;
        double[] dArr = in;
        double value2 = 0.0d;
        double d2 = 1.0d;
        double numOfYears = dArr.length - 1.0d;
        double pv = 0.0d;
        double fv = 0.0d;
        int indexN = 0;
        int length = dArr.length;
        int i = 0;
        while (i < length) {
            double anIn = dArr[i];
            if (anIn > 0.0d) {
                d = d2;
                fv += Math.pow(reinvestRate + d2, numOfYears - indexN) * anIn;
                value = value2;
                indexN++;
            } else {
                d = d2;
                if (anIn >= 0.0d) {
                    value = value2;
                } else {
                    value = value2;
                    pv += anIn / Math.pow(financeRate + d, indexN);
                    indexN++;
                }
            }
            i++;
            dArr = in;
            d2 = d;
            value2 = value;
        }
        double value3 = value2;
        double d3 = d2;
        if (fv != 0.0d && pv != 0.0d) {
            double value4 = Math.pow((-fv) / pv, d3 / numOfYears) - d3;
            return value4;
        }
        return value3;
    }
}
