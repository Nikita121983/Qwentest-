package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
final class ArrayFunctionUtils {
    ArrayFunctionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<DoubleList> getNumberArrays(ValueEval operand0, ValueEval operand1) throws EvaluationException {
        double[] retval0 = collectValuesWithBlanks(operand0).toArray();
        double[] retval1 = collectValuesWithBlanks(operand1).toArray();
        if (retval0.length != retval1.length) {
            throw new EvaluationException(ErrorEval.NA);
        }
        DoubleList filtered0 = new DoubleList();
        DoubleList filtered1 = new DoubleList();
        int len = Math.min(retval0.length, retval1.length);
        for (int i = 0; i < len; i++) {
            if (!Double.isNaN(retval0[i]) && !Double.isNaN(retval1[i])) {
                filtered0.add(retval0[i]);
                filtered1.add(retval1[i]);
            }
        }
        return Arrays.asList(filtered0, filtered1);
    }

    private static DoubleList collectValuesWithBlanks(ValueEval operand) throws EvaluationException {
        DoubleList doubleList = new DoubleList();
        if (operand instanceof ThreeDEval) {
            ThreeDEval ae = (ThreeDEval) operand;
            for (int sIx = ae.getFirstSheetIndex(); sIx <= ae.getLastSheetIndex(); sIx++) {
                int width = ae.getWidth();
                int height = ae.getHeight();
                for (int rrIx = 0; rrIx < height; rrIx++) {
                    for (int rcIx = 0; rcIx < width; rcIx++) {
                        ValueEval ve = ae.getValue(sIx, rrIx, rcIx);
                        Double d = collectValue(ve);
                        if (d == null) {
                            doubleList.add(Double.NaN);
                        } else {
                            doubleList.add(d.doubleValue());
                        }
                    }
                }
            }
            return doubleList;
        }
        if (operand instanceof TwoDEval) {
            TwoDEval ae2 = (TwoDEval) operand;
            int width2 = ae2.getWidth();
            int height2 = ae2.getHeight();
            for (int rrIx2 = 0; rrIx2 < height2; rrIx2++) {
                for (int rcIx2 = 0; rcIx2 < width2; rcIx2++) {
                    ValueEval ve2 = ae2.getValue(rrIx2, rcIx2);
                    Double d2 = collectValue(ve2);
                    if (d2 == null) {
                        doubleList.add(Double.NaN);
                    } else {
                        doubleList.add(d2.doubleValue());
                    }
                }
            }
            return doubleList;
        }
        if (operand instanceof RefEval) {
            RefEval re = (RefEval) operand;
            for (int sIx2 = re.getFirstSheetIndex(); sIx2 <= re.getLastSheetIndex(); sIx2++) {
                Double d3 = collectValue(re.getInnerValueEval(sIx2));
                if (d3 == null) {
                    doubleList.add(Double.NaN);
                } else {
                    doubleList.add(d3.doubleValue());
                }
            }
            return doubleList;
        }
        Double d4 = collectValue(operand);
        if (d4 == null) {
            doubleList.add(Double.NaN);
        } else {
            doubleList.add(d4.doubleValue());
        }
        return doubleList;
    }

    private static Double collectValue(ValueEval ve) throws EvaluationException {
        if (ve == null) {
            throw new IllegalArgumentException("ve must not be null");
        }
        if (ve instanceof NumericValueEval) {
            NumericValueEval ne = (NumericValueEval) ve;
            return Double.valueOf(ne.getNumberValue());
        }
        if (ve instanceof StringValueEval) {
            String s = ((StringValueEval) ve).getStringValue().trim();
            return OperandResolver.parseDouble(s);
        }
        if (ve instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) ve);
        }
        if (ve == BlankEval.instance) {
            return null;
        }
        throw new IllegalStateException("Invalid ValueEval type passed for conversion: (" + ve.getClass() + ")");
    }
}
