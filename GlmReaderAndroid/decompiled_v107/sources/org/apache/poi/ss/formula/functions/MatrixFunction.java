package org.apache.poi.ss.formula.functions;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

/* loaded from: classes10.dex */
public abstract class MatrixFunction implements Function {
    public static final Function MINVERSE = new OneArrayArg() { // from class: org.apache.poi.ss.formula.functions.MatrixFunction.1
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if ((arg instanceof AreaEval) && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return values;
        }

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            if (d1.length != d1[0].length) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            return MatrixUtils.inverse(temp).getData();
        }
    };
    public static final Function TRANSPOSE = new OneArrayArg() { // from class: org.apache.poi.ss.formula.functions.MatrixFunction.2
        private final MutableValueCollector instance = new MutableValueCollector(false, true);

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            return this.instance.collectValues(arg);
        }

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            return temp.transpose().getData();
        }
    };
    public static final Function MDETERM = new Mdeterm();
    public static final Function MMULT = new TwoArrayArg() { // from class: org.apache.poi.ss.formula.functions.MatrixFunction.3
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.TwoArrayArg
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if ((arg instanceof AreaEval) && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return values;
        }

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.TwoArrayArg
        protected double[][] evaluate(double[][] d1, double[][] d2) throws EvaluationException {
            Array2DRowRealMatrix first = new Array2DRowRealMatrix(d1);
            Array2DRowRealMatrix second = new Array2DRowRealMatrix(d2);
            try {
                MatrixUtils.checkMultiplicationCompatible(first, second);
                return first.multiply(second).getData();
            } catch (DimensionMismatchException e) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        }
    };

    public static void checkValues(double[] results) throws EvaluationException {
        for (double result : results) {
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                throw new EvaluationException(ErrorEval.NUM_ERROR);
            }
        }
    }

    protected final double singleOperandEvaluate(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToDouble(ve);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[][] fillDoubleArray(double[] vector, int rows, int cols) throws EvaluationException {
        int i = 0;
        int j = 0;
        if (rows < 1 || cols < 1 || vector.length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[][] matrix = (double[][]) Array.newInstance((Class<?>) Double.TYPE, rows, cols);
        for (double aVector : vector) {
            if (j < matrix.length) {
                if (i == matrix[0].length) {
                    i = 0;
                    j++;
                }
                if (j < matrix.length) {
                    matrix[j][i] = aVector;
                    i++;
                }
            }
        }
        return matrix;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[] extractDoubleArray(double[][] matrix) throws EvaluationException {
        int idx = 0;
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
        double[] vector = new double[matrix.length * matrix[0].length];
        for (double[] aMatrix : matrix) {
            int i = 0;
            while (i < matrix[0].length) {
                vector[idx] = aMatrix[i];
                i++;
                idx++;
            }
        }
        return vector;
    }

    /* loaded from: classes10.dex */
    public static abstract class OneArrayArg extends Fixed1ArgFunction {
        protected abstract double[] collectValues(ValueEval valueEval) throws EvaluationException;

        protected abstract double[][] evaluate(double[][] dArr) throws EvaluationException;

        protected OneArrayArg() {
        }

        @Override // org.apache.poi.ss.formula.functions.Function1Arg
        /* renamed from: evaluate */
        public ValueEval m2524x1f30617e(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            if (arg0 instanceof AreaEval) {
                try {
                    double[] values = collectValues(arg0);
                    double[][] array = MatrixFunction.fillDoubleArray(values, ((AreaEval) arg0).getHeight(), ((AreaEval) arg0).getWidth());
                    double[][] resultArray = evaluate(array);
                    int width = resultArray[0].length;
                    int height = resultArray.length;
                    double[] result = MatrixFunction.extractDoubleArray(resultArray);
                    MatrixFunction.checkValues(result);
                    ValueEval[] vals = new ValueEval[result.length];
                    for (int idx = 0; idx < result.length; idx++) {
                        vals[idx] = new NumberEval(result[idx]);
                    }
                    int idx2 = result.length;
                    if (idx2 == 1) {
                        return vals[0];
                    }
                    return new CacheAreaEval(((AreaEval) arg0).getFirstRow(), ((AreaEval) arg0).getFirstColumn(), (((AreaEval) arg0).getFirstRow() + height) - 1, (((AreaEval) arg0).getFirstColumn() + width) - 1, vals);
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            }
            try {
                double value = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
                double[][] temp = {new double[]{value}};
                double[][] result2 = evaluate(temp);
                NumericFunction.checkValue(result2[0][0]);
                return new NumberEval(result2[0][0]);
            } catch (EvaluationException e2) {
                return e2.getErrorEval();
            }
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class TwoArrayArg extends Fixed2ArgFunction {
        protected abstract double[] collectValues(ValueEval valueEval) throws EvaluationException;

        protected abstract double[][] evaluate(double[][] dArr, double[][] dArr2) throws EvaluationException;

        protected TwoArrayArg() {
        }

        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            double[][] array0;
            double[][] array1;
            try {
                try {
                    if (arg0 instanceof AreaEval) {
                        try {
                            double[] values = collectValues(arg0);
                            array0 = MatrixFunction.fillDoubleArray(values, ((AreaEval) arg0).getHeight(), ((AreaEval) arg0).getWidth());
                        } catch (EvaluationException e) {
                            return e.getErrorEval();
                        }
                    } else {
                        try {
                            double value = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
                            double[][] array02 = {new double[]{value}};
                            array0 = array02;
                        } catch (EvaluationException e2) {
                            return e2.getErrorEval();
                        }
                    }
                    if (arg1 instanceof AreaEval) {
                        try {
                            double[] values2 = collectValues(arg1);
                            array1 = MatrixFunction.fillDoubleArray(values2, ((AreaEval) arg1).getHeight(), ((AreaEval) arg1).getWidth());
                        } catch (EvaluationException e3) {
                            return e3.getErrorEval();
                        }
                    } else {
                        try {
                            double value2 = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
                            double[][] array12 = {new double[]{value2}};
                            array1 = array12;
                        } catch (EvaluationException e4) {
                            return e4.getErrorEval();
                        }
                    }
                    double[][] resultArray = evaluate(array0, array1);
                    int width = resultArray[0].length;
                    int height = resultArray.length;
                    double[] result = MatrixFunction.extractDoubleArray(resultArray);
                    MatrixFunction.checkValues(result);
                    ValueEval[] vals = new ValueEval[result.length];
                    for (int idx = 0; idx < result.length; idx++) {
                        vals[idx] = new NumberEval(result[idx]);
                    }
                    int idx2 = result.length;
                    if (idx2 == 1) {
                        return vals[0];
                    }
                    return new CacheAreaEval(((AreaEval) arg0).getFirstRow(), ((AreaEval) arg0).getFirstColumn(), (((AreaEval) arg0).getFirstRow() + height) - 1, (((AreaEval) arg0).getFirstColumn() + width) - 1, vals);
                } catch (EvaluationException e5) {
                    return e5.getErrorEval();
                }
            } catch (IllegalArgumentException e6) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class MutableValueCollector extends MultiOperandNumericFunction {
        public MutableValueCollector(boolean isReferenceBoolCounted, boolean isBlankCounted) {
            super(isReferenceBoolCounted, isBlankCounted);
        }

        public double[] collectValues(ValueEval... operands) throws EvaluationException {
            return getNumberArray(operands);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            throw new IllegalStateException("should not be called");
        }
    }

    /* loaded from: classes10.dex */
    private static class Mdeterm extends OneArrayArg {
        private final MutableValueCollector instance = new MutableValueCollector(false, false);

        public Mdeterm() {
            this.instance.setBlankEvalPolicy(MultiOperandNumericFunction.Policy.ERROR);
        }

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[] collectValues(ValueEval arg) throws EvaluationException {
            double[] values = this.instance.collectValues(arg);
            if ((arg instanceof AreaEval) && values.length == 1) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            return this.instance.collectValues(arg);
        }

        @Override // org.apache.poi.ss.formula.functions.MatrixFunction.OneArrayArg
        protected double[][] evaluate(double[][] d1) throws EvaluationException {
            if (d1.length != d1[0].length) {
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
            double[][] result = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 1, 1);
            Array2DRowRealMatrix temp = new Array2DRowRealMatrix(d1);
            result[0][0] = new LUDecomposition(temp).getDeterminant();
            return result;
        }
    }
}
