package org.apache.poi.ss.formula.functions;

import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MultiOperandNumericFunction;

/* loaded from: classes10.dex */
public abstract class AggregateFunction extends MultiOperandNumericFunction {
    public static final Function AVEDEV = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            return StatsLib.avedev(values);
        }
    };
    public static final Function AVERAGE = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return MathX.average(values);
        }
    };
    public static final Function AVERAGEA = new AggregateFunctionA() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return MathX.average(values);
        }
    };
    public static final Function DEVSQ = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            return StatsLib.devsq(values);
        }
    };
    public static final Function LARGE = new LargeSmall(true);
    public static final Function MAX = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.6
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            if (values.length > 0) {
                return MathX.max(values);
            }
            return 0.0d;
        }
    };
    public static final Function MEDIAN = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.7
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            return StatsLib.median(values);
        }
    };
    public static final Function MIN = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.8
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            if (values.length > 0) {
                return MathX.min(values);
            }
            return 0.0d;
        }
    };
    public static final Function PERCENTILE = new Percentile();
    public static final Function PRODUCT = new Product();
    public static final Function SMALL = new LargeSmall(false);
    public static final Function STDEV = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.9
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.stdev(values);
        }
    };
    public static final Function STDEVP = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.10
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.stdevp(values);
        }
    };
    public static final Function STDEVA = new AggregateFunctionA() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.11
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.stdev(values);
        }
    };
    public static final Function STDEVPA = new AggregateFunctionA() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.12
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.stdevp(values);
        }
    };
    public static final Function SUM = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.13
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            return MathX.sum(values);
        }
    };
    public static final Function SUMSQ = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.14
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            return MathX.sumsq(values);
        }
    };
    public static final Function VAR = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.15
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.var(values);
        }
    };
    public static final Function VARP = new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.16
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.varp(values);
        }
    };
    public static final Function VARA = new AggregateFunctionA() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.17
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.var(values);
        }
    };
    public static final Function VARPA = new AggregateFunctionA() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.18
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            if (values.length < 1) {
                throw new EvaluationException(ErrorEval.DIV_ZERO);
            }
            return StatsLib.varp(values);
        }
    };
    public static final Function GEOMEAN = new Geomean();

    /* loaded from: classes10.dex */
    private static final class LargeSmall extends Fixed2ArgFunction {
        private final boolean _isLarge;

        protected LargeSmall(boolean isLarge) {
            this._isLarge = isLarge;
        }

        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            try {
                ValueEval ve1 = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
                double dn = OperandResolver.coerceValueToDouble(ve1);
                if (dn < 1.0d) {
                    return ErrorEval.NUM_ERROR;
                }
                int k = (int) Math.ceil(dn);
                try {
                    double[] ds = ValueCollector.collectValues(arg0);
                    if (k > ds.length) {
                        return ErrorEval.NUM_ERROR;
                    }
                    double result = this._isLarge ? StatsLib.kthLargest(ds, k) : StatsLib.kthSmallest(ds, k);
                    NumericFunction.checkValue(result);
                    return new NumberEval(result);
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } catch (EvaluationException e2) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    }

    /* loaded from: classes10.dex */
    private static final class Percentile extends Fixed2ArgFunction {
        protected Percentile() {
        }

        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            double d;
            try {
                ValueEval ve1 = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
                double dn = OperandResolver.coerceValueToDouble(ve1);
                if (dn < 0.0d || dn > 1.0d) {
                    return ErrorEval.NUM_ERROR;
                }
                try {
                    double[] ds = ValueCollector.collectValues(arg0);
                    int N = ds.length;
                    if (N != 0 && N <= 8191) {
                        double n = ((N - 1) * dn) + 1.0d;
                        if (n == 1.0d) {
                            d = StatsLib.kthSmallest(ds, 1);
                        } else {
                            double result = N;
                            if (Double.compare(n, result) == 0) {
                                d = StatsLib.kthLargest(ds, 1);
                            } else {
                                int k = (int) n;
                                double d2 = n - k;
                                d = StatsLib.kthSmallest(ds, k) + ((StatsLib.kthSmallest(ds, k + 1) - StatsLib.kthSmallest(ds, k)) * d2);
                            }
                        }
                        NumericFunction.checkValue(d);
                        return new NumberEval(d);
                    }
                    return ErrorEval.NUM_ERROR;
                } catch (EvaluationException e) {
                    return e.getErrorEval();
                }
            } catch (EvaluationException e2) {
                return ErrorEval.VALUE_INVALID;
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class ValueCollector extends MultiOperandNumericFunction {
        private static final ValueCollector instance = new ValueCollector();

        public ValueCollector() {
            super(false, false);
        }

        public static double[] collectValues(ValueEval... operands) throws EvaluationException {
            return instance.getNumberArray(operands);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) {
            throw new IllegalStateException("should not be called");
        }
    }

    protected AggregateFunction() {
        this(false);
    }

    protected AggregateFunction(boolean isReferenceBoolCounted) {
        super(isReferenceBoolCounted, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function subtotalInstance(Function func, final boolean countHiddenRows) {
        AggregateFunction arg = (AggregateFunction) func;
        return new AggregateFunction() { // from class: org.apache.poi.ss.formula.functions.AggregateFunction.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
            public double evaluate(double[] values) throws EvaluationException {
                return AggregateFunction.this.evaluate(values);
            }

            @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
            public boolean isSubtotalCounted() {
                return false;
            }

            @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
            public boolean isHiddenRowCounted() {
                return countHiddenRows;
            }
        };
    }

    /* loaded from: classes10.dex */
    static abstract class AggregateFunctionA extends AggregateFunction {
        protected AggregateFunctionA() {
            super(true);
        }

        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        protected boolean treatStringsAsZero() {
            return true;
        }
    }

    /* loaded from: classes10.dex */
    private static class Product extends AggregateFunction {
        Product() {
            setMissingArgPolicy(MultiOperandNumericFunction.Policy.SKIP);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            return MathX.product(values);
        }
    }

    /* loaded from: classes10.dex */
    private static class Geomean extends AggregateFunction {
        Geomean() {
            setMissingArgPolicy(MultiOperandNumericFunction.Policy.COERCE);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.poi.ss.formula.functions.MultiOperandNumericFunction
        public double evaluate(double[] values) throws EvaluationException {
            for (double value : values) {
                if (value <= 0.0d) {
                    throw new EvaluationException(ErrorEval.NUM_ERROR);
                }
            }
            return new GeometricMean().evaluate(values, 0, values.length);
        }
    }
}
