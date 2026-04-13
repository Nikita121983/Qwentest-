package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class Substitute extends Var3or4ArgFunction {
    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            String oldStr = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            String searchStr = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            String newStr = TextFunction.evaluateStringArg(arg2, srcRowIndex, srcColumnIndex);
            String result = replaceAllOccurrences(oldStr, searchStr, newStr);
            return new StringEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function4Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        try {
            String oldStr = TextFunction.evaluateStringArg(arg0, srcRowIndex, srcColumnIndex);
            String searchStr = TextFunction.evaluateStringArg(arg1, srcRowIndex, srcColumnIndex);
            String newStr = TextFunction.evaluateStringArg(arg2, srcRowIndex, srcColumnIndex);
            int instanceNumber = TextFunction.evaluateIntArg(arg3, srcRowIndex, srcColumnIndex);
            if (instanceNumber < 1) {
                return ErrorEval.VALUE_INVALID;
            }
            String result = replaceOneOccurrence(oldStr, searchStr, newStr, instanceNumber);
            return new StringEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static String replaceAllOccurrences(String oldStr, String searchStr, String newStr) {
        if (searchStr.length() < 1) {
            return oldStr;
        }
        StringBuilder sb = new StringBuilder();
        int startIndex = 0;
        while (true) {
            int nextMatch = oldStr.indexOf(searchStr, startIndex);
            if (nextMatch < 0) {
                sb.append(oldStr.substring(startIndex));
                return sb.toString();
            }
            sb.append((CharSequence) oldStr, startIndex, nextMatch);
            sb.append(newStr);
            startIndex = nextMatch + searchStr.length();
        }
    }

    private static String replaceOneOccurrence(String oldStr, String searchStr, String newStr, int instanceNumber) {
        if (searchStr.length() < 1) {
            return oldStr;
        }
        int startIndex = 0;
        int count = 0;
        while (true) {
            int nextMatch = oldStr.indexOf(searchStr, startIndex);
            if (nextMatch < 0) {
                return oldStr;
            }
            count++;
            if (count == instanceNumber) {
                return oldStr.substring(0, nextMatch) + newStr + oldStr.substring(searchStr.length() + nextMatch);
            }
            startIndex = nextMatch + searchStr.length();
        }
    }
}
