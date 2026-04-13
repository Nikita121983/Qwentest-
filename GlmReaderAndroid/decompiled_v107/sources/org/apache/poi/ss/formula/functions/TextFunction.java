package org.apache.poi.ss.formula.functions;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DataFormatter;

/* loaded from: classes10.dex */
public abstract class TextFunction implements Function {
    protected static final DataFormatter formatter = new DataFormatter();
    public static final Function CHAR = new Fixed1ArgFunction() { // from class: org.apache.poi.ss.formula.functions.TextFunction.1
        @Override // org.apache.poi.ss.formula.functions.Function1Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            try {
                int arg = TextFunction.evaluateIntArg(arg0, srcRowIndex, srcColumnIndex);
                if (arg < 0 || arg >= 256) {
                    throw new EvaluationException(ErrorEval.VALUE_INVALID);
                }
                return new StringEval(String.valueOf((char) arg));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function LEN = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.2
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String arg) {
            return new NumberEval(arg.length());
        }
    };
    public static final Function LOWER = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.3
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.toLowerCase(Locale.ROOT));
        }
    };
    public static final Function UPPER = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.4
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.toUpperCase(Locale.ROOT));
        }
    };
    public static final Function PROPER = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.5
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String text) {
            StringBuilder sb = new StringBuilder();
            boolean shouldMakeUppercase = true;
            for (char ch : text.toCharArray()) {
                if (shouldMakeUppercase) {
                    sb.append(String.valueOf(ch).toUpperCase(Locale.ROOT));
                } else {
                    sb.append(String.valueOf(ch).toLowerCase(Locale.ROOT));
                }
                shouldMakeUppercase = !Character.isLetter(ch);
            }
            return new StringEval(sb.toString());
        }
    };
    public static final Function TRIM = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.6
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String arg) {
            return new StringEval(arg.trim().replaceAll(" +", StringUtils.SPACE));
        }
    };
    public static final Function CLEAN = new SingleArgTextFunc() { // from class: org.apache.poi.ss.formula.functions.TextFunction.7
        @Override // org.apache.poi.ss.formula.functions.TextFunction.SingleArgTextFunc
        protected ValueEval evaluate(String arg) {
            StringBuilder result = new StringBuilder();
            for (char c : arg.toCharArray()) {
                if (isPrintable(c)) {
                    result.append(c);
                }
            }
            return new StringEval(result.toString());
        }

        private boolean isPrintable(char c) {
            return c >= ' ';
        }
    };
    public static final Function MID = new Fixed3ArgFunction() { // from class: org.apache.poi.ss.formula.functions.TextFunction.8
        @Override // org.apache.poi.ss.formula.functions.Function3Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
            try {
                String text = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                int startCharNum = TextFunction.evaluateIntArg(arg1, srcRowIndex, srcColumnIndex);
                int numChars = TextFunction.evaluateIntArg(arg2, srcRowIndex, srcColumnIndex);
                int startIx = startCharNum - 1;
                if (startIx < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                if (numChars < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                int len = text.length();
                if (startIx > len) {
                    return new StringEval("");
                }
                int endIx = Math.min(startIx + numChars, len);
                String result = text.substring(startIx, endIx);
                return new StringEval(result);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function LEFT = new LeftRight(true);
    public static final Function RIGHT = new LeftRight(false);
    public static final FreeRefFunction CONCAT = new FreeRefFunction() { // from class: org.apache.poi.ss.formula.functions.TextFunction$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
        public final ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
            return TextFunction.lambda$static$0(valueEvalArr, operationEvaluationContext);
        }
    };
    public static final Function CONCATENATE = new Function() { // from class: org.apache.poi.ss.formula.functions.TextFunction$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            return TextFunction.lambda$static$1(valueEvalArr, i, i2);
        }
    };
    public static final Function EXACT = new Fixed2ArgFunction() { // from class: org.apache.poi.ss.formula.functions.TextFunction.9
        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            try {
                String s0 = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                String s1 = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
                return BoolEval.valueOf(s0.equals(s1));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    };
    public static final Function TEXT = new Fixed2ArgFunction() { // from class: org.apache.poi.ss.formula.functions.TextFunction.10
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0038, code lost:
        
            r3 = ((org.apache.poi.ss.formula.eval.StringEval) r0).getStringValue();
         */
        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public org.apache.poi.ss.formula.eval.ValueEval evaluate(int r10, int r11, org.apache.poi.ss.formula.eval.ValueEval r12, org.apache.poi.ss.formula.eval.ValueEval r13) {
            /*
                r9 = this;
                org.apache.poi.ss.formula.eval.ValueEval r0 = org.apache.poi.ss.formula.eval.OperandResolver.getSingleValue(r12, r10, r11)     // Catch: org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                org.apache.poi.ss.formula.eval.ValueEval r1 = org.apache.poi.ss.formula.eval.OperandResolver.getSingleValue(r13, r10, r11)     // Catch: org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r2 = 0
                r3 = 0
                org.apache.poi.ss.formula.eval.BlankEval r4 = org.apache.poi.ss.formula.eval.BlankEval.instance     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                if (r0 != r4) goto L16
                r4 = 0
                java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r2 = r4
                goto L4e
            L16:
                boolean r4 = r0 instanceof org.apache.poi.ss.formula.eval.BoolEval     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                if (r4 == 0) goto L23
                r4 = r0
                org.apache.poi.ss.formula.eval.BoolEval r4 = (org.apache.poi.ss.formula.eval.BoolEval) r4     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                java.lang.String r4 = r4.getStringValue()     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r3 = r4
                goto L4e
            L23:
                boolean r4 = r0 instanceof org.apache.poi.ss.formula.eval.NumericValueEval     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                if (r4 == 0) goto L34
                r4 = r0
                org.apache.poi.ss.formula.eval.NumericValueEval r4 = (org.apache.poi.ss.formula.eval.NumericValueEval) r4     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                double r4 = r4.getNumberValue()     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r2 = r4
                goto L4e
            L34:
                boolean r4 = r0 instanceof org.apache.poi.ss.formula.eval.StringEval     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                if (r4 == 0) goto L4e
                r4 = r0
                org.apache.poi.ss.formula.eval.StringEval r4 = (org.apache.poi.ss.formula.eval.StringEval) r4     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                java.lang.String r4 = r4.getStringValue()     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r3 = r4
                java.lang.Double r4 = org.apache.poi.ss.formula.eval.OperandResolver.parseDouble(r3)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r2 = r4
                if (r2 != 0) goto L4e
                java.lang.Double r4 = org.apache.poi.ss.usermodel.DateUtil.parseDateTime(r3)     // Catch: java.lang.Exception -> L4d org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r2 = r4
                goto L4e
            L4d:
                r4 = move-exception
            L4e:
                if (r2 == 0) goto L60
                java.lang.String r4 = r9.formatPatternValueEval2String(r1)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                org.apache.poi.ss.usermodel.DataFormatter r5 = org.apache.poi.ss.formula.functions.TextFunction.formatter     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                double r6 = r2.doubleValue()     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r8 = -1
                java.lang.String r5 = r5.formatRawCellContents(r6, r8, r4)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r3 = r5
            L60:
                org.apache.poi.ss.formula.eval.StringEval r4 = new org.apache.poi.ss.formula.eval.StringEval     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r4.<init>(r3)     // Catch: java.lang.Exception -> L66 org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                goto L6a
            L66:
                r2 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r3 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID     // Catch: org.apache.poi.ss.formula.eval.EvaluationException -> L6b
                r4 = r3
            L6a:
                goto L70
            L6b:
                r0 = move-exception
                org.apache.poi.ss.formula.eval.ErrorEval r4 = r0.getErrorEval()
            L70:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.TextFunction.AnonymousClass10.evaluate(int, int, org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.eval.ValueEval):org.apache.poi.ss.formula.eval.ValueEval");
        }

        private String formatPatternValueEval2String(ValueEval ve) {
            if ((ve instanceof BoolEval) || !(ve instanceof StringValueEval)) {
                if (ve == BlankEval.instance) {
                    return "";
                }
                throw new IllegalArgumentException("Unexpected eval class (" + ve.getClass().getName() + ")");
            }
            StringValueEval sve = (StringValueEval) ve;
            String format = sve.getStringValue();
            return format;
        }
    };
    public static final Function FIND = new SearchFind(true);
    public static final Function SEARCH = new SearchFind(false);

    protected abstract ValueEval evaluateFunc(ValueEval[] valueEvalArr, int i, int i2) throws EvaluationException;

    /* JADX INFO: Access modifiers changed from: protected */
    public static String evaluateStringArg(ValueEval eval, int srcRow, int srcCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(eval, srcRow, srcCol);
        return OperandResolver.coerceValueToString(ve);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int evaluateIntArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToInt(ve);
    }

    protected static double evaluateDoubleArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        return OperandResolver.coerceValueToDouble(ve);
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public final ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
        try {
            return evaluateFunc(args, srcCellRow, srcCellCol);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* loaded from: classes10.dex */
    private static abstract class SingleArgTextFunc extends Fixed1ArgFunction {
        protected abstract ValueEval evaluate(String str);

        protected SingleArgTextFunc() {
        }

        @Override // org.apache.poi.ss.formula.functions.Function1Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            try {
                String arg = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                return evaluate(arg);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    }

    /* loaded from: classes10.dex */
    private static final class LeftRight extends Var1or2ArgFunction {
        private static final ValueEval DEFAULT_ARG1 = new NumberEval(1.0d);
        private final boolean _isLeft;

        protected LeftRight(boolean isLeft) {
            this._isLeft = isLeft;
        }

        @Override // org.apache.poi.ss.formula.functions.Function1Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            return evaluate(srcRowIndex, srcColumnIndex, arg0, DEFAULT_ARG1);
        }

        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            String result;
            try {
                String arg = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                int index = TextFunction.evaluateIntArg(arg1, srcRowIndex, srcColumnIndex);
                if (index < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                if (this._isLeft) {
                    result = arg.substring(0, Math.min(arg.length(), index));
                } else {
                    result = arg.substring(Math.max(0, arg.length() - index));
                }
                return new StringEval(result);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ValueEval lambda$static$0(ValueEval[] args, OperationEvaluationContext ec) {
        StringBuilder sb = new StringBuilder();
        for (ValueEval arg : args) {
            try {
                if (arg instanceof AreaEval) {
                    AreaEval area = (AreaEval) arg;
                    for (int rn = 0; rn < area.getHeight(); rn++) {
                        for (int cn = 0; cn < area.getWidth(); cn++) {
                            ValueEval ve = area.getRelativeValue(rn, cn);
                            sb.append(evaluateStringArg(ve, ec.getRowIndex(), ec.getColumnIndex()));
                        }
                    }
                } else {
                    sb.append(evaluateStringArg(arg, ec.getRowIndex(), ec.getColumnIndex()));
                }
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        return new StringEval(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ValueEval lambda$static$1(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        StringBuilder sb = new StringBuilder();
        for (ValueEval arg : args) {
            try {
                sb.append(evaluateStringArg(arg, srcRowIndex, srcColumnIndex));
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        return new StringEval(sb.toString());
    }

    /* loaded from: classes10.dex */
    private static final class SearchFind extends Var2or3ArgFunction {
        private final boolean _isCaseSensitive;

        public SearchFind(boolean isCaseSensitive) {
            this._isCaseSensitive = isCaseSensitive;
        }

        @Override // org.apache.poi.ss.formula.functions.Function2Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
            try {
                String needle = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                String haystack = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
                return eval(haystack, needle, 0);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        @Override // org.apache.poi.ss.formula.functions.Function3Arg
        public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
            try {
                String needle = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
                String haystack = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
                int startpos = TextFunction.evaluateIntArg(arg2, srcRowIndex, srcColumnIndex) - 1;
                if (startpos < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                return eval(haystack, needle, startpos);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }

        private ValueEval eval(String haystack, String needle, int startIndex) {
            int result;
            if (this._isCaseSensitive) {
                result = haystack.indexOf(needle, startIndex);
            } else {
                result = haystack.toUpperCase(Locale.ROOT).indexOf(needle.toUpperCase(Locale.ROOT), startIndex);
            }
            if (result == -1) {
                return ErrorEval.VALUE_INVALID;
            }
            return new NumberEval(result + 1.0d);
        }
    }
}
