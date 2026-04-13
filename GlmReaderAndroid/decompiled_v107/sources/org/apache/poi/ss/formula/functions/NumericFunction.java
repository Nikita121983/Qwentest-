package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public abstract class NumericFunction implements Function {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long PARITY_MASK = -2;
    private static final double TEN = 10.0d;
    private static final double ZERO = 0.0d;
    private static final double LOG_10_TO_BASE_e = Math.log(10.0d);
    public static final Function ABS = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double abs;
            abs = Math.abs(d);
            return abs;
        }
    });
    public static final Function ACOS = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double acos;
            acos = Math.acos(d);
            return acos;
        }
    });
    public static final Function ACOSH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda14
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.acosh(d);
        }
    });
    public static final Function ASIN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda25
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double asin;
            asin = Math.asin(d);
            return asin;
        }
    });
    public static final Function ASINH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda26
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.asinh(d);
        }
    });
    public static final Function ATAN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda27
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double atan;
            atan = Math.atan(d);
            return atan;
        }
    });
    public static final Function ATANH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda28
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.atanh(d);
        }
    });
    public static final Function COS = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda29
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double cos;
            cos = Math.cos(d);
            return cos;
        }
    });
    public static final Function COSH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda30
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.cosh(d);
        }
    });
    public static final Function DEGREES = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda31
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double degrees;
            degrees = Math.toDegrees(d);
            return degrees;
        }
    });
    public static final Function DOLLAR = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda11
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateDollar;
            evaluateDollar = NumericFunction.evaluateDollar(valueEvalArr, i, i2);
            return evaluateDollar;
        }
    };
    public static final Function EXP = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda22
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double pow;
            pow = Math.pow(2.718281828459045d, d);
            return pow;
        }
    });
    public static final Function FACT = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda33
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.factorial(d);
        }
    });
    public static final Function INT = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda35
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v1 double, still in use, count: 1, list:
              (r1v1 double) from 0x0004: RETURN (r1v1 double)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:452)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double r1) {
            /*
                r0 = this;
                double r1 = org.apache.poi.ss.formula.functions.NumericFunction.lambda$static$1(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda35.apply(double):double");
        }
    });
    public static final Function LN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda36
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double log;
            log = Math.log(d);
            return log;
        }
    });
    public static final Function LOG10 = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda37
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return NumericFunction.lambda$static$2(d);
        }
    });
    public static final Function RADIANS = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda38
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double radians;
            radians = Math.toRadians(d);
            return radians;
        }
    });
    public static final Function SIGN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda39
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.sign(d);
        }
    });
    public static final Function SIN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda40
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double sin;
            sin = Math.sin(d);
            return sin;
        }
    });
    public static final Function SINH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.sinh(d);
        }
    });
    public static final Function SQRT = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double sqrt;
            sqrt = Math.sqrt(d);
            return sqrt;
        }
    });
    public static final Function TAN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double tan;
            tan = Math.tan(d);
            return tan;
        }
    });
    public static final Function TANH = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            return MathX.tanh(d);
        }
    });
    public static final Function ATAN2 = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return NumericFunction.lambda$static$3(d, d2);
        }
    });
    public static final Function CEILING = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda7
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return Double.valueOf(MathX.ceiling(d, d2));
        }
    });
    public static final Function COMBIN = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda8
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return NumericFunction.lambda$static$4(d, d2);
        }
    });
    public static final Function FLOOR = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda9
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return NumericFunction.lambda$static$5(d, d2);
        }
    });
    public static final Function MOD = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda10
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return NumericFunction.lambda$static$6(d, d2);
        }
    });
    public static final Function POWER = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda12
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            double pow;
            pow = Math.pow(d, d2);
            return Double.valueOf(pow);
        }
    });
    public static final Function ROUND = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda13
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return Double.valueOf(MathX.round(d, d2));
        }
    });
    public static final Function ROUNDDOWN = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda15
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return Double.valueOf(MathX.roundDown(d, d2));
        }
    });
    public static final Function ROUNDUP = twoDouble(new TwoDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda16
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.TwoDoubleIf
        public final Object apply(double d, double d2) {
            return Double.valueOf(MathX.roundUp(d, d2));
        }
    });
    public static final Function TRUNC = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda17
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateTrunc;
            evaluateTrunc = NumericFunction.evaluateTrunc(valueEvalArr, i, i2);
            return evaluateTrunc;
        }
    };
    public static final Function LOG = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda18
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            return Log.evaluate(valueEvalArr, i, i2);
        }
    };
    static final NumberEval PI_EVAL = new NumberEval(3.141592653589793d);
    public static final Function PI = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda19
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluatePI;
            evaluatePI = NumericFunction.evaluatePI(valueEvalArr, i, i2);
            return evaluatePI;
        }
    };
    public static final Function RAND = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda20
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateRand;
            evaluateRand = NumericFunction.evaluateRand(valueEvalArr, i, i2);
            return evaluateRand;
        }
    };
    public static final Function POISSON = new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda21
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            return Poisson.evaluate(valueEvalArr, i, i2);
        }
    };
    public static final Function ODD = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda23
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double evaluateOdd;
            evaluateOdd = NumericFunction.evaluateOdd(d);
            return evaluateOdd;
        }
    });
    public static final Function EVEN = oneDouble(new OneDoubleIf() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda24
        @Override // org.apache.poi.ss.formula.functions.NumericFunction.OneDoubleIf
        public final double apply(double d) {
            double evaluateEven;
            evaluateEven = NumericFunction.evaluateEven(d);
            return evaluateEven;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface OneDoubleIf {
        double apply(double d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface TwoDoubleIf {
        Object apply(double d, double d2);
    }

    protected abstract double eval(ValueEval[] valueEvalArr, int i, int i2) throws EvaluationException;

    /* JADX INFO: Access modifiers changed from: protected */
    public static double singleOperandEvaluate(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        if (arg == null) {
            throw new IllegalArgumentException("arg must not be null");
        }
        ValueEval ve = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        double result = OperandResolver.coerceValueToDouble(ve);
        checkValue(result);
        return result;
    }

    public static void checkValue(double result) throws EvaluationException {
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public final ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
        try {
            double result = eval(args, srcCellRow, srcCellCol);
            checkValue(result);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateDollar(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 1 && args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double val = singleOperandEvaluate(args[0], srcRowIndex, srcColumnIndex);
            double d1 = args.length == 1 ? 2.0d : singleOperandEvaluate(args[1], srcRowIndex, srcColumnIndex);
            int nPlaces = (int) d1;
            if (nPlaces > 127) {
                return ErrorEval.VALUE_INVALID;
            }
            if (nPlaces < 0) {
                BigDecimal divisor = BigDecimal.valueOf(Math.pow(10.0d, -nPlaces));
                BigInteger bigInt = BigDecimal.valueOf(val).divide(divisor, MathContext.DECIMAL128).toBigInteger().multiply(divisor.toBigInteger());
                val = bigInt.doubleValue();
            }
            DecimalFormat nf = (DecimalFormat) NumberFormat.getCurrencyInstance(LocaleUtil.getUserLocale());
            int decimalPlaces = Math.max(nPlaces, 0);
            if (LocaleUtil.getUserLocale().getCountry().equalsIgnoreCase("US")) {
                nf.setPositivePrefix("$");
                nf.setNegativePrefix("($");
                nf.setNegativeSuffix(")");
            }
            nf.setMinimumFractionDigits(decimalPlaces);
            nf.setMaximumFractionDigits(decimalPlaces);
            return new StringEval(nf.format(val).replace(" ", StringUtils.SPACE));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ double lambda$static$2(double d) {
        return Math.log(d) / LOG_10_TO_BASE_e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$3(double d0, double d1) {
        return (d0 == 0.0d && d1 == 0.0d) ? ErrorEval.DIV_ZERO : Double.valueOf(Math.atan2(d1, d0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$4(double d0, double d1) {
        return (d0 > 2.147483647E9d || d1 > 2.147483647E9d) ? ErrorEval.NUM_ERROR : Double.valueOf(MathX.nChooseK((int) d0, (int) d1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$5(double d0, double d1) {
        double d = 0.0d;
        if (d1 != 0.0d) {
            d = MathX.floor(d0, d1);
        } else if (d0 != 0.0d) {
            return ErrorEval.DIV_ZERO;
        }
        return Double.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$static$6(double d0, double d1) {
        return d1 == 0.0d ? ErrorEval.DIV_ZERO : Double.valueOf(MathX.mod(d0, d1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateTrunc(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 1 && args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double d0 = singleOperandEvaluate(args[0], srcRowIndex, srcColumnIndex);
            double d1 = args.length == 1 ? 0.0d : singleOperandEvaluate(args[1], srcRowIndex, srcColumnIndex);
            double result = MathX.roundDown(d0, d1);
            checkValue(result);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluatePI(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return args.length != 0 ? ErrorEval.VALUE_INVALID : PI_EVAL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateRand(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return args.length != 0 ? ErrorEval.VALUE_INVALID : new NumberEval(Math.random());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double evaluateOdd(double d) {
        if (d == 0.0d) {
            return 1.0d;
        }
        double dpm = Math.abs(d) + 1.0d;
        long x = ((long) dpm) & (-2);
        return MathX.sign(d) * (Double.compare((double) x, dpm) == 0 ? x - 1 : 1 + x);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double evaluateEven(double d) {
        if (d == 0.0d) {
            return 0.0d;
        }
        double dpm = Math.abs(d);
        long x = ((long) dpm) & (-2);
        return MathX.sign(d) * (Double.compare((double) x, dpm) == 0 ? x : 2 + x);
    }

    private static Function oneDouble(final OneDoubleIf doubleFun) {
        return new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda32
            @Override // org.apache.poi.ss.formula.functions.Function
            public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
                return NumericFunction.lambda$oneDouble$7(NumericFunction.OneDoubleIf.this, valueEvalArr, i, i2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ValueEval lambda$oneDouble$7(OneDoubleIf doubleFun, ValueEval[] args, int srcCellRow, int srcCellCol) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double d = singleOperandEvaluate(args[0], srcCellRow, srcCellCol);
            double res = doubleFun.apply(d);
            if (!Double.isNaN(res) && !Double.isInfinite(res)) {
                return new NumberEval(res);
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static Function twoDouble(final TwoDoubleIf doubleFun) {
        return new Function() { // from class: org.apache.poi.ss.formula.functions.NumericFunction$$ExternalSyntheticLambda34
            @Override // org.apache.poi.ss.formula.functions.Function
            public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
                return NumericFunction.lambda$twoDouble$8(NumericFunction.TwoDoubleIf.this, valueEvalArr, i, i2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ValueEval lambda$twoDouble$8(TwoDoubleIf doubleFun, ValueEval[] args, int srcCellRow, int srcCellCol) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double d1 = singleOperandEvaluate(args[0], srcCellRow, srcCellCol);
            double d2 = singleOperandEvaluate(args[1], srcCellRow, srcCellCol);
            Object res = doubleFun.apply(d1, d2);
            if (res instanceof ErrorEval) {
                return (ErrorEval) res;
            }
            if (!(res instanceof Double)) {
                throw new AssertionError();
            }
            double d = ((Double) res).doubleValue();
            if (!Double.isNaN(d) && !Double.isInfinite(d)) {
                return new NumberEval(d);
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
