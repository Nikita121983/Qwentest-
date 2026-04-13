package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Rate implements Function {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) Rate.class);

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        double periods;
        double payment;
        double present_val;
        double future_val;
        double type;
        double estimate;
        if (args.length >= 3) {
            try {
                ValueEval v1 = OperandResolver.getSingleValue(args[0], srcRowIndex, srcColumnIndex);
                ValueEval v2 = OperandResolver.getSingleValue(args[1], srcRowIndex, srcColumnIndex);
                ValueEval v3 = OperandResolver.getSingleValue(args[2], srcRowIndex, srcColumnIndex);
                ValueEval v4 = null;
                if (args.length >= 4) {
                    v4 = OperandResolver.getSingleValue(args[3], srcRowIndex, srcColumnIndex);
                }
                ValueEval v5 = null;
                if (args.length >= 5) {
                    v5 = OperandResolver.getSingleValue(args[4], srcRowIndex, srcColumnIndex);
                }
                ValueEval v6 = null;
                if (args.length >= 6) {
                    v6 = OperandResolver.getSingleValue(args[5], srcRowIndex, srcColumnIndex);
                }
                periods = OperandResolver.coerceValueToDouble(v1);
                payment = OperandResolver.coerceValueToDouble(v2);
                present_val = OperandResolver.coerceValueToDouble(v3);
                if (args.length < 4) {
                    future_val = 0.0d;
                } else {
                    double future_val2 = OperandResolver.coerceValueToDouble(v4);
                    future_val = future_val2;
                }
                try {
                    if (args.length < 5) {
                        type = 0.0d;
                    } else {
                        double type2 = OperandResolver.coerceValueToDouble(v5);
                        type = type2;
                    }
                    try {
                        if (args.length < 6) {
                            estimate = 0.1d;
                        } else {
                            double estimate2 = OperandResolver.coerceValueToDouble(v6);
                            estimate = estimate2;
                        }
                    } catch (EvaluationException e) {
                        e = e;
                    }
                } catch (EvaluationException e2) {
                    e = e2;
                }
            } catch (EvaluationException e3) {
                e = e3;
            }
            try {
                double rate = calculateRate(periods, payment, present_val, future_val, type, estimate);
                checkValue(rate);
                return new NumberEval(rate);
            } catch (EvaluationException e4) {
                e = e4;
                LOG.atError().withThrowable(e).log("Can't evaluate rate function");
                return e.getErrorEval();
            }
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static double _g_div_gp(double r, double n, double p, double x, double y, double w) {
        double t1 = Math.pow(r + 1.0d, n);
        double t2 = Math.pow(r + 1.0d, n - 1.0d);
        return ((y + (t1 * x)) + ((((t1 - 1.0d) * p) * ((r * w) + 1.0d)) / r)) / (((n * t2) * x) - ((((t1 - 1.0d) * p) * ((r * w) + 1.0d)) / ((Math.pow(r, 2.0d) + ((((n * p) * t2) * ((r * w) + 1.0d)) / r)) + ((((t1 - 1.0d) * p) * w) / r))));
    }

    static double calculateRate(double nper, double pmt, double pv, double fv, double type, double guess) {
        double rn;
        double rnp1 = guess;
        int iter = 0;
        boolean close = false;
        while (true) {
            rn = rnp1;
            if (iter >= 100.0d || close) {
                break;
            }
            rnp1 = rn - _g_div_gp(rn, nper, pmt, pv, fv, type);
            double diff = Math.abs(rnp1 - rn);
            close = diff < 1.0E-8d;
            iter++;
        }
        if (!close) {
            return Double.NaN;
        }
        return rn;
    }

    static void checkValue(double result) throws EvaluationException {
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }
}
