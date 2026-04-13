package org.apache.poi.ss.formula.functions;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class Trend implements Function {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class TrendResults {
        private final int resultHeight;
        private final int resultWidth;
        private final double[] vals;

        public TrendResults(double[] vals, int resultWidth, int resultHeight) {
            this.vals = vals;
            this.resultWidth = resultWidth;
            this.resultHeight = resultHeight;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length >= 1 && args.length <= 4) {
            try {
                TrendResults tr = getNewY(args);
                ValueEval[] vals = new ValueEval[tr.vals.length];
                for (int i = 0; i < tr.vals.length; i++) {
                    try {
                        vals[i] = new NumberEval(tr.vals[i]);
                    } catch (EvaluationException e) {
                        e = e;
                        return e.getErrorEval();
                    }
                }
                if (tr.vals.length == 1) {
                    return vals[0];
                }
                try {
                    return new CacheAreaEval(srcRowIndex, srcColumnIndex, (tr.resultHeight + srcRowIndex) - 1, (tr.resultWidth + srcColumnIndex) - 1, vals);
                } catch (EvaluationException e2) {
                    e = e2;
                    return e.getErrorEval();
                }
            } catch (EvaluationException e3) {
                e = e3;
            }
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static double[][] evalToArray(ValueEval arg) throws EvaluationException {
        ValueEval eval;
        if (arg instanceof MissingArgEval) {
            return (double[][]) Array.newInstance((Class<?>) Double.TYPE, 0, 0);
        }
        if (arg instanceof RefEval) {
            RefEval re = (RefEval) arg;
            if (re.getNumberOfSheets() > 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            eval = re.getInnerValueEval(re.getFirstSheetIndex());
        } else {
            eval = arg;
        }
        if (eval == null) {
            throw new IllegalStateException("Parameter may not be null.");
        }
        if (!(eval instanceof AreaEval)) {
            if (eval instanceof NumericValueEval) {
                double[][] ar = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 1, 1);
                ar[0][0] = ((NumericValueEval) eval).getNumberValue();
                return ar;
            }
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        AreaEval ae = (AreaEval) eval;
        int w = ae.getWidth();
        int h = ae.getHeight();
        double[][] ar2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, h, w);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                ValueEval ve = ae.getRelativeValue(i, j);
                if (!(ve instanceof NumericValueEval)) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                ar2[i][j] = ((NumericValueEval) ve).getNumberValue();
            }
        }
        return ar2;
    }

    private static double[][] getDefaultArrayOneD(int w) {
        double[][] array = (double[][]) Array.newInstance((Class<?>) Double.TYPE, w, 1);
        for (int i = 0; i < w; i++) {
            array[i][0] = i + 1.0d;
        }
        return array;
    }

    private static double[] flattenArray(double[][] twoD) {
        if (twoD.length < 1) {
            return new double[0];
        }
        double[] oneD = new double[twoD.length * twoD[0].length];
        for (int i = 0; i < twoD.length; i++) {
            System.arraycopy(twoD[i], 0, oneD, twoD[0].length * i, twoD[0].length);
        }
        return oneD;
    }

    private static double[][] flattenArrayToRow(double[][] twoD) {
        if (twoD.length < 1) {
            return (double[][]) Array.newInstance((Class<?>) Double.TYPE, 0, 0);
        }
        double[][] oneD = (double[][]) Array.newInstance((Class<?>) Double.TYPE, twoD.length * twoD[0].length, 1);
        for (int i = 0; i < twoD.length; i++) {
            for (int j = 0; j < twoD[0].length; j++) {
                oneD[(twoD[0].length * i) + j][0] = twoD[i][j];
            }
        }
        return oneD;
    }

    private static double[][] switchRowsColumns(double[][] array) {
        double[][] newArray = (double[][]) Array.newInstance((Class<?>) Double.TYPE, array[0].length, array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                newArray[j][i] = array[i][j];
            }
        }
        return newArray;
    }

    private static boolean isAllColumnsSame(double[][] matrix) {
        if (matrix.length == 0) {
            return false;
        }
        boolean[] cols = new boolean[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            double prev = Double.NaN;
            int i = 0;
            while (true) {
                if (i < matrix.length) {
                    double v = matrix[i][j];
                    if (i > 0 && v != prev) {
                        cols[j] = true;
                        break;
                    }
                    prev = v;
                    i++;
                }
            }
        }
        for (boolean x : cols) {
            if (x) {
                return false;
            }
        }
        return true;
    }

    private static TrendResults getNewY(ValueEval[] args) throws EvaluationException {
        double[][] newXOrig;
        double[][] newXOrig2;
        double[][] xOrig;
        boolean passThroughOrigin;
        double[][] x;
        double[][] newX;
        char c;
        OLSMultipleLinearRegression reg;
        switch (args.length) {
            case 1:
                double[][] yOrig = evalToArray(args[0]);
                double[][] xOrig2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 0, 0);
                double[][] newXOrig3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 0, 0);
                newXOrig = newXOrig3;
                newXOrig2 = xOrig2;
                xOrig = yOrig;
                passThroughOrigin = false;
                break;
            case 2:
                double[][] yOrig2 = evalToArray(args[0]);
                double[][] xOrig3 = evalToArray(args[1]);
                double[][] newXOrig4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 0, 0);
                newXOrig = newXOrig4;
                newXOrig2 = xOrig3;
                xOrig = yOrig2;
                passThroughOrigin = false;
                break;
            case 3:
                double[][] yOrig3 = evalToArray(args[0]);
                double[][] xOrig4 = evalToArray(args[1]);
                double[][] newXOrig5 = evalToArray(args[2]);
                newXOrig = newXOrig5;
                newXOrig2 = xOrig4;
                xOrig = yOrig3;
                passThroughOrigin = false;
                break;
            case 4:
                double[][] yOrig4 = evalToArray(args[0]);
                double[][] xOrig5 = evalToArray(args[1]);
                double[][] newXOrig6 = evalToArray(args[2]);
                if (!(args[3] instanceof BoolEval)) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                boolean passThroughOrigin2 = !((BoolEval) args[3]).getBooleanValue();
                newXOrig = newXOrig6;
                newXOrig2 = xOrig5;
                xOrig = yOrig4;
                passThroughOrigin = passThroughOrigin2;
                break;
            default:
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        if (xOrig.length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[] y = flattenArray(xOrig);
        double[][] newX2 = newXOrig;
        double[][] resultSize = newXOrig.length > 0 ? newXOrig : (double[][]) Array.newInstance((Class<?>) Double.TYPE, 1, 1);
        if (y.length == 1) {
            throw new NotImplementedException("Sample size too small");
        }
        if (xOrig.length == 1 || xOrig[0].length == 1) {
            if (newXOrig2.length < 1) {
                x = getDefaultArrayOneD(y.length);
                if (newXOrig.length < 1) {
                    resultSize = xOrig;
                }
            } else {
                x = newXOrig2;
                if (newXOrig2[0].length > 1 && xOrig.length == 1) {
                    x = switchRowsColumns(x);
                }
                if (newXOrig.length < 1) {
                    resultSize = newXOrig2;
                }
            }
            if (newXOrig.length > 0 && (x.length == 1 || x[0].length == 1)) {
                newX2 = flattenArrayToRow(newXOrig);
            }
        } else {
            if (newXOrig2.length < 1) {
                x = getDefaultArrayOneD(y.length);
                if (newXOrig.length < 1) {
                    resultSize = xOrig;
                }
            } else {
                x = flattenArrayToRow(newXOrig2);
                if (newXOrig.length < 1) {
                    resultSize = newXOrig2;
                }
            }
            if (newXOrig.length > 0) {
                newX2 = flattenArrayToRow(newXOrig);
            }
            if (y.length != x.length || xOrig.length != newXOrig2.length) {
                throw new EvaluationException(ErrorEval.REF_INVALID);
            }
        }
        if (newXOrig.length < 1) {
            newX = x;
        } else if (newXOrig.length == 1 && newXOrig[0].length > 1 && newXOrig2.length > 1 && newXOrig2[0].length == 1) {
            newX = switchRowsColumns(newXOrig);
        } else {
            newX = newX2;
        }
        if (newX[0].length != x[0].length) {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        }
        if (x[0].length >= x.length) {
            throw new NotImplementedException("Sample size too small");
        }
        int resultHeight = resultSize.length;
        int resultWidth = resultSize[0].length;
        if (isAllColumnsSame(x)) {
            double[] result = new double[newX.length];
            double avg = Arrays.stream(y).average().orElse(0.0d);
            Arrays.fill(result, avg);
            return new TrendResults(result, resultWidth, resultHeight);
        }
        OLSMultipleLinearRegression reg2 = new OLSMultipleLinearRegression();
        if (!passThroughOrigin) {
            c = 0;
            reg = reg2;
        } else {
            c = 0;
            reg = reg2;
            reg.setNoIntercept(true);
        }
        try {
            reg.newSampleData(y, x);
            try {
                double[] par = reg.estimateRegressionParameters();
                double[] result2 = new double[newX.length];
                for (int i = 0; i < newX.length; i++) {
                    result2[i] = 0.0d;
                    if (passThroughOrigin) {
                        for (int j = 0; j < par.length; j++) {
                            result2[i] = result2[i] + (par[j] * newX[i][j]);
                        }
                    } else {
                        result2[i] = par[c];
                        for (int j2 = 1; j2 < par.length; j2++) {
                            result2[i] = result2[i] + (par[j2] * newX[i][j2 - 1]);
                        }
                    }
                }
                return new TrendResults(result2, resultWidth, resultHeight);
            } catch (SingularMatrixException e) {
                throw new NotImplementedException("Singular matrix in input");
            }
        } catch (IllegalArgumentException e2) {
            throw new EvaluationException(ErrorEval.REF_INVALID);
        }
    }
}
