package org.apache.poi.ss.formula.functions;

import java.util.function.BiFunction;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public interface ArrayFunction {
    ValueEval evaluateArray(ValueEval[] valueEvalArr, int i, int i2);

    default ValueEval evaluateTwoArrayArgs(ValueEval arg0, ValueEval arg1, int srcRowIndex, int srcColumnIndex, BiFunction<ValueEval, ValueEval, ValueEval> evalFunc) {
        return _evaluateTwoArrayArgs(arg0, arg1, srcRowIndex, srcColumnIndex, evalFunc);
    }

    default ValueEval evaluateOneArrayArg(ValueEval arg0, int srcRowIndex, int srcColumnIndex, java.util.function.Function<ValueEval, ValueEval> evalFunc) {
        return _evaluateOneArrayArg(arg0, srcRowIndex, srcColumnIndex, evalFunc);
    }

    static ValueEval _evaluateTwoArrayArgs(ValueEval arg0, ValueEval arg1, int srcRowIndex, int srcColumnIndex, BiFunction<ValueEval, ValueEval, ValueEval> evalFunc) {
        int w1;
        int h1;
        int a1FirstRow;
        int a1FirstRow2;
        int w2;
        int h2;
        int a2FirstRow;
        int a2FirstRow2;
        ValueEval vA;
        ValueEval vA2;
        ValueEval vB;
        ValueEval valueEval = arg0;
        if (valueEval instanceof AreaEval) {
            AreaEval ae = (AreaEval) valueEval;
            w1 = ae.getWidth();
            h1 = ae.getHeight();
            int a1FirstCol = ae.getFirstColumn();
            int a1FirstRow3 = ae.getFirstRow();
            a1FirstRow = a1FirstRow3;
            a1FirstRow2 = a1FirstCol;
        } else if (valueEval instanceof RefEval) {
            RefEval ref = (RefEval) valueEval;
            w1 = 1;
            h1 = 1;
            int a1FirstCol2 = ref.getColumn();
            int a1FirstRow4 = ref.getRow();
            a1FirstRow = a1FirstRow4;
            a1FirstRow2 = a1FirstCol2;
        } else {
            w1 = 1;
            h1 = 1;
            a1FirstRow = 0;
            a1FirstRow2 = 0;
        }
        if (arg1 instanceof AreaEval) {
            AreaEval ae2 = (AreaEval) arg1;
            w2 = ae2.getWidth();
            h2 = ae2.getHeight();
            int a2FirstCol = ae2.getFirstColumn();
            int a2FirstRow3 = ae2.getFirstRow();
            a2FirstRow = a2FirstRow3;
            a2FirstRow2 = a2FirstCol;
        } else if (arg1 instanceof RefEval) {
            RefEval ref2 = (RefEval) arg1;
            w2 = 1;
            h2 = 1;
            int a2FirstCol2 = ref2.getColumn();
            int a2FirstRow4 = ref2.getRow();
            a2FirstRow = a2FirstRow4;
            a2FirstRow2 = a2FirstCol2;
        } else {
            w2 = 1;
            h2 = 1;
            a2FirstRow = 0;
            a2FirstRow2 = 0;
        }
        int width = Math.max(w1, w2);
        int height = Math.max(h1, h2);
        ValueEval[] vals = new ValueEval[height * width];
        int idx = 0;
        int i = 0;
        while (i < height) {
            int a1FirstCol3 = a1FirstRow2;
            int a1FirstCol4 = 0;
            int idx2 = idx;
            while (a1FirstCol4 < width) {
                int j = a1FirstCol4;
                try {
                    vA = OperandResolver.getSingleValue(valueEval, a1FirstRow + i, a1FirstCol3 + j);
                } catch (FormulaParseException e) {
                    vA = ErrorEval.NAME_INVALID;
                } catch (RuntimeException e2) {
                    if (e2.getMessage().startsWith("Don't know how to evaluate name")) {
                        vA2 = ErrorEval.NAME_INVALID;
                    } else {
                        throw e2;
                    }
                } catch (EvaluationException e3) {
                    vA = e3.getErrorEval();
                }
                vA2 = vA;
                try {
                    vB = OperandResolver.getSingleValue(arg1, a2FirstRow + i, a2FirstRow2 + j);
                } catch (FormulaParseException e4) {
                    vB = ErrorEval.NAME_INVALID;
                } catch (RuntimeException e5) {
                    if (e5.getMessage().startsWith("Don't know how to evaluate name")) {
                        vB = ErrorEval.NAME_INVALID;
                    } else {
                        throw e5;
                    }
                } catch (EvaluationException e6) {
                    vB = e6.getErrorEval();
                }
                if (vA2 instanceof ErrorEval) {
                    vals[idx2] = vA2;
                    idx2++;
                } else if (vB instanceof ErrorEval) {
                    vals[idx2] = vB;
                    idx2++;
                } else {
                    vals[idx2] = evalFunc.apply(vA2, vB);
                    idx2++;
                }
                a1FirstCol4 = j + 1;
                valueEval = arg0;
            }
            i++;
            valueEval = arg0;
            idx = idx2;
            a1FirstRow2 = a1FirstCol3;
        }
        if (vals.length == 1) {
            return vals[0];
        }
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, (srcRowIndex + height) - 1, (srcColumnIndex + width) - 1, vals);
    }

    static ValueEval _evaluateOneArrayArg(ValueEval arg0, int srcRowIndex, int srcColumnIndex, java.util.function.Function<ValueEval, ValueEval> evalFunc) {
        int w1;
        int h1;
        int a1FirstRow;
        int a1FirstRow2;
        ValueEval vA;
        if (!(arg0 instanceof AreaEval)) {
            if (arg0 instanceof RefEval) {
                RefEval ref = (RefEval) arg0;
                w1 = 1;
                h1 = 1;
                int a1FirstCol = ref.getColumn();
                int a1FirstRow3 = ref.getRow();
                a1FirstRow = a1FirstRow3;
                a1FirstRow2 = a1FirstCol;
            } else {
                w1 = 1;
                h1 = 1;
                a1FirstRow = 0;
                a1FirstRow2 = 0;
            }
        } else {
            AreaEval ae = (AreaEval) arg0;
            w1 = ae.getWidth();
            h1 = ae.getHeight();
            int a1FirstCol2 = ae.getFirstColumn();
            int a1FirstRow4 = ae.getFirstRow();
            a1FirstRow = a1FirstRow4;
            a1FirstRow2 = a1FirstCol2;
        }
        int width = Math.max(w1, 1);
        int height = Math.max(h1, 1);
        ValueEval[] vals = new ValueEval[height * width];
        int idx = 0;
        int i = 0;
        while (i < height) {
            int j = 0;
            int idx2 = idx;
            while (j < width) {
                try {
                    vA = OperandResolver.getSingleValue(arg0, a1FirstRow + i, a1FirstRow2 + j);
                } catch (FormulaParseException e) {
                    vA = ErrorEval.NAME_INVALID;
                } catch (RuntimeException e2) {
                    if (e2.getMessage().startsWith("Don't know how to evaluate name")) {
                        vA = ErrorEval.NAME_INVALID;
                    } else {
                        throw e2;
                    }
                } catch (EvaluationException e3) {
                    vA = e3.getErrorEval();
                }
                vals[idx2] = evalFunc.apply(vA);
                j++;
                idx2++;
            }
            i++;
            idx = idx2;
        }
        int i2 = vals.length;
        if (i2 == 1) {
            return vals[0];
        }
        return new CacheAreaEval(srcRowIndex, srcColumnIndex, (srcRowIndex + height) - 1, (srcColumnIndex + width) - 1, vals);
    }
}
