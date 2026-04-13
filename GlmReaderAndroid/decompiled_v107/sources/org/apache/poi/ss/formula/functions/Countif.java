package org.apache.poi.ss.formula.functions;

import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.usermodel.FormulaError;

/* loaded from: classes10.dex */
public final class Countif extends Fixed2ArgFunction {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CmpOp {
        public static final int EQ = 1;
        public static final int GE = 6;
        public static final int GT = 5;
        public static final int LE = 3;
        public static final int LT = 4;
        public static final int NE = 2;
        public static final int NONE = 0;
        private final int _code;
        private final String _representation;
        public static final CmpOp OP_NONE = op("", 0);
        public static final CmpOp OP_EQ = op("=", 1);
        public static final CmpOp OP_NE = op("<>", 2);
        public static final CmpOp OP_LE = op("<=", 3);
        public static final CmpOp OP_LT = op("<", 4);
        public static final CmpOp OP_GT = op(">", 5);
        public static final CmpOp OP_GE = op(">=", 6);

        private static CmpOp op(String rep, int code) {
            return new CmpOp(rep, code);
        }

        private CmpOp(String representation, int code) {
            this._representation = representation;
            this._code = code;
        }

        public int getLength() {
            return this._representation.length();
        }

        public int getCode() {
            return this._code;
        }

        public static CmpOp getOperator(String value) {
            int len = value.length();
            if (len < 1) {
                return OP_NONE;
            }
            char firstChar = value.charAt(0);
            switch (firstChar) {
                case '<':
                    if (len > 1) {
                        switch (value.charAt(1)) {
                            case '=':
                                return OP_LE;
                            case '>':
                                return OP_NE;
                        }
                    }
                    return OP_LT;
                case '=':
                    return OP_EQ;
                case '>':
                    if (len > 1) {
                        switch (value.charAt(1)) {
                            case '=':
                                return OP_GE;
                        }
                    }
                    return OP_GT;
                default:
                    return OP_NONE;
            }
        }

        public boolean evaluate(boolean cmpResult) {
            switch (this._code) {
                case 0:
                case 1:
                    return cmpResult;
                case 2:
                    return !cmpResult;
                default:
                    throw new IllegalStateException("Cannot call boolean evaluate on non-equality operator '" + this._representation + "'");
            }
        }

        public boolean evaluate(int cmpResult) {
            switch (this._code) {
                case 0:
                case 1:
                    return cmpResult == 0;
                case 2:
                    return cmpResult != 0;
                case 3:
                    return cmpResult <= 0;
                case 4:
                    return cmpResult < 0;
                case 5:
                    return cmpResult > 0;
                case 6:
                    return cmpResult >= 0;
                default:
                    throw new IllegalStateException("Cannot call boolean evaluate on non-equality operator '" + this._representation + "'");
            }
        }

        public String toString() {
            return getClass().getName() + " [" + this._representation + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }

        public String getRepresentation() {
            return this._representation;
        }
    }

    /* loaded from: classes10.dex */
    private static abstract class MatcherBase implements CountUtils.I_MatchPredicate {
        private final CmpOp _operator;

        protected abstract String getValueText();

        MatcherBase(CmpOp operator) {
            this._operator = operator;
        }

        protected final int getCode() {
            return this._operator.getCode();
        }

        protected final boolean evaluate(int cmpResult) {
            return this._operator.evaluate(cmpResult);
        }

        protected final boolean evaluate(boolean cmpResult) {
            return this._operator.evaluate(cmpResult);
        }

        public final String toString() {
            return getClass().getName() + " [" + this._operator.getRepresentation() + getValueText() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class NumberMatcher extends MatcherBase {
        private final double _value;

        public NumberMatcher(double value, CmpOp operator) {
            super(operator);
            this._value = value;
        }

        @Override // org.apache.poi.ss.formula.functions.Countif.MatcherBase
        protected String getValueText() {
            return String.valueOf(this._value);
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval x) {
            if (x instanceof StringEval) {
                switch (getCode()) {
                    case 0:
                    case 1:
                        StringEval se = (StringEval) x;
                        Double val = OperandResolver.parseDouble(se.getStringValue());
                        return val != null && this._value == val.doubleValue();
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
            if (x instanceof NumberEval) {
                NumberEval ne = (NumberEval) x;
                double testValue = ne.getNumberValue();
                return evaluate(Double.compare(testValue, this._value));
            }
            if (!(x instanceof BlankEval)) {
                return false;
            }
            switch (getCode()) {
                case 2:
                    return true;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class BooleanMatcher extends MatcherBase {
        private final int _value;

        public BooleanMatcher(boolean value, CmpOp operator) {
            super(operator);
            this._value = boolToInt(value);
        }

        @Override // org.apache.poi.ss.formula.functions.Countif.MatcherBase
        protected String getValueText() {
            return this._value == 1 ? "TRUE" : "FALSE";
        }

        private static int boolToInt(boolean z) {
            return z ? 1 : 0;
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval x) {
            if (x instanceof StringEval) {
                return false;
            }
            if (x instanceof BoolEval) {
                BoolEval be = (BoolEval) x;
                int testValue = boolToInt(be.getBooleanValue());
                return evaluate(testValue - this._value);
            }
            if (x instanceof BlankEval) {
                switch (getCode()) {
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
            if (!(x instanceof NumberEval)) {
                return false;
            }
            switch (getCode()) {
                case 2:
                    return true;
                default:
                    return false;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class ErrorMatcher extends MatcherBase {
        private final int _value;

        public ErrorMatcher(int errorCode, CmpOp operator) {
            super(operator);
            this._value = errorCode;
        }

        @Override // org.apache.poi.ss.formula.functions.Countif.MatcherBase
        protected String getValueText() {
            return FormulaError.forInt(this._value).getString();
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval x) {
            if (x instanceof ErrorEval) {
                int testValue = ((ErrorEval) x).getErrorCode();
                return evaluate(testValue - this._value);
            }
            return false;
        }

        public int getValue() {
            return this._value;
        }
    }

    /* loaded from: classes10.dex */
    public static final class StringMatcher extends MatcherBase {
        private final Pattern _pattern;
        private final String _value;

        public StringMatcher(String value, CmpOp operator) {
            super(operator);
            this._value = value;
            switch (operator.getCode()) {
                case 0:
                case 1:
                case 2:
                    this._pattern = getWildCardPattern(value);
                    return;
                default:
                    this._pattern = null;
                    return;
            }
        }

        @Override // org.apache.poi.ss.formula.functions.Countif.MatcherBase
        protected String getValueText() {
            if (this._pattern == null) {
                return this._value;
            }
            return this._pattern.pattern();
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval x) {
            if (x instanceof BlankEval) {
                switch (getCode()) {
                    case 0:
                    case 1:
                        return this._value.isEmpty();
                    case 2:
                        return this._value.length() != 0;
                    default:
                        return false;
                }
            }
            if (!(x instanceof StringEval)) {
                return false;
            }
            String testedValue = ((StringEval) x).getStringValue();
            if (testedValue.length() >= 1 || this._value.length() >= 1) {
                if (this._pattern != null) {
                    return evaluate(this._pattern.matcher(testedValue).matches());
                }
                return evaluate(testedValue.compareToIgnoreCase(this._value));
            }
            switch (getCode()) {
                case 0:
                    return true;
                case 1:
                    return false;
                case 2:
                    return true;
                default:
                    return false;
            }
        }

        public static Pattern getWildCardPattern(String value) {
            int len = value.length();
            StringBuilder sb = new StringBuilder(len);
            boolean hasWildCard = false;
            int i = 0;
            while (i < len) {
                char ch = value.charAt(i);
                switch (ch) {
                    case '$':
                    case '(':
                    case ')':
                    case '.':
                    case '[':
                    case ']':
                    case '^':
                        sb.append("\\").append(ch);
                        break;
                    case '*':
                        hasWildCard = true;
                        sb.append(".*");
                        break;
                    case '?':
                        hasWildCard = true;
                        sb.append('.');
                        break;
                    case '~':
                        if (i + 1 < len) {
                            char ch2 = value.charAt(i + 1);
                            switch (ch2) {
                                case '*':
                                case '?':
                                    hasWildCard = true;
                                    sb.append('[').append(ch2).append(']');
                                    i++;
                                    break;
                            }
                        }
                        sb.append('~');
                        break;
                    default:
                        sb.append(ch);
                        break;
                }
                i++;
            }
            if (hasWildCard) {
                return Pattern.compile(sb.toString(), 2);
            }
            return null;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        CountUtils.I_MatchPredicate mp = createCriteriaPredicate(arg1, srcRowIndex, srcColumnIndex);
        if (mp == null) {
            return NumberEval.ZERO;
        }
        double result = countMatchingCellsInArea(arg0, mp);
        return new NumberEval(result);
    }

    private double countMatchingCellsInArea(ValueEval rangeArg, CountUtils.I_MatchPredicate criteriaPredicate) {
        if (rangeArg instanceof RefEval) {
            return CountUtils.countMatchingCellsInRef((RefEval) rangeArg, criteriaPredicate);
        }
        if (rangeArg instanceof ThreeDEval) {
            return CountUtils.countMatchingCellsInArea((ThreeDEval) rangeArg, criteriaPredicate);
        }
        throw new IllegalArgumentException("Bad range arg type (" + rangeArg.getClass().getName() + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CountUtils.I_MatchPredicate createCriteriaPredicate(ValueEval arg, int srcRowIndex, int srcColumnIndex) {
        ValueEval evaluatedCriteriaArg = evaluateCriteriaArg(arg, srcRowIndex, srcColumnIndex);
        if (evaluatedCriteriaArg instanceof NumberEval) {
            return new NumberMatcher(((NumberEval) evaluatedCriteriaArg).getNumberValue(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg instanceof BoolEval) {
            return new BooleanMatcher(((BoolEval) evaluatedCriteriaArg).getBooleanValue(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg instanceof StringEval) {
            return createGeneralMatchPredicate((StringEval) evaluatedCriteriaArg);
        }
        if (evaluatedCriteriaArg instanceof ErrorEval) {
            return new ErrorMatcher(((ErrorEval) evaluatedCriteriaArg).getErrorCode(), CmpOp.OP_NONE);
        }
        if (evaluatedCriteriaArg == BlankEval.instance) {
            return null;
        }
        throw new IllegalStateException("Unexpected type for criteria (" + evaluatedCriteriaArg.getClass().getName() + ")");
    }

    private static ValueEval evaluateCriteriaArg(ValueEval arg, int srcRowIndex, int srcColumnIndex) {
        try {
            return OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static CountUtils.I_MatchPredicate createGeneralMatchPredicate(StringEval stringEval) {
        String value = stringEval.getStringValue();
        CmpOp operator = CmpOp.getOperator(value);
        String value2 = value.substring(operator.getLength());
        Boolean booleanVal = parseBoolean(value2);
        if (booleanVal != null) {
            return new BooleanMatcher(booleanVal.booleanValue(), operator);
        }
        Double doubleVal = OperandResolver.parseDouble(value2);
        if (doubleVal != null) {
            return new NumberMatcher(doubleVal.doubleValue(), operator);
        }
        ErrorEval ee = parseError(value2);
        if (ee != null) {
            return new ErrorMatcher(ee.getErrorCode(), operator);
        }
        return new StringMatcher(value2, operator);
    }

    private static ErrorEval parseError(String value) {
        if (value.length() < 4 || value.charAt(0) != '#') {
            return null;
        }
        if (value.equals("#NULL!")) {
            return ErrorEval.NULL_INTERSECTION;
        }
        if (value.equals("#DIV/0!")) {
            return ErrorEval.DIV_ZERO;
        }
        if (value.equals("#VALUE!")) {
            return ErrorEval.VALUE_INVALID;
        }
        if (value.equals("#REF!")) {
            return ErrorEval.REF_INVALID;
        }
        if (value.equals("#NAME?")) {
            return ErrorEval.NAME_INVALID;
        }
        if (value.equals("#NUM!")) {
            return ErrorEval.NUM_ERROR;
        }
        if (value.equals("#N/A")) {
            return ErrorEval.NA;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Boolean parseBoolean(String strRep) {
        if (strRep.length() < 1) {
            return null;
        }
        switch (strRep.charAt(0)) {
            case 'F':
            case 'f':
                if ("FALSE".equalsIgnoreCase(strRep)) {
                    return Boolean.FALSE;
                }
                return null;
            case 'T':
            case 't':
                if ("TRUE".equalsIgnoreCase(strRep)) {
                    return Boolean.TRUE;
                }
                return null;
            default:
                return null;
        }
    }
}
