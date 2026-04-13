package org.apache.poi.ss.formula.functions;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Roman extends Fixed2ArgFunction {
    private static final int[] VALUES = {1000, TypedValues.Custom.TYPE_INT, 500, FontHeader.REGULAR_WEIGHT, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final String[][] REPLACEMENTS = {new String[]{"XLV", "VL", "XCV", "VC", "CDL", "LD", "CML", "LM", "CMVC", "LMVL"}, new String[]{"CDXC", "LDXL", "CDVC", "LDVL", "CMXC", "LMXL", "XCIX", "VCIV", "XLIX", "VLIV"}, new String[]{"XLIX", "IL", "XCIX", "IC", "CDXC", "XD", "CDVC", "XDV", "CDIC", "XDIX", "LMVL", "XMV", "CMIC", "XMIX", "CMXC", "XM"}, new String[]{"XDV", "VD", "XDIX", "VDIV", "XMV", "VM", "XMIX", "VMIV"}, new String[]{"VDIV", "ID", "VMIV", "IM"}};

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval numberVE, ValueEval formVE) {
        try {
            ValueEval ve = OperandResolver.getSingleValue(numberVE, srcRowIndex, srcColumnIndex);
            int number = OperandResolver.coerceValueToInt(ve);
            if (number < 0) {
                return ErrorEval.VALUE_INVALID;
            }
            if (number > 3999) {
                return ErrorEval.VALUE_INVALID;
            }
            if (number == 0) {
                return new StringEval("");
            }
            try {
                ValueEval ve2 = OperandResolver.getSingleValue(formVE, srcRowIndex, srcColumnIndex);
                int form = OperandResolver.coerceValueToInt(ve2);
                if (form > 4 || form < 0) {
                    return ErrorEval.VALUE_INVALID;
                }
                String result = integerToRoman(number);
                if (form == 0) {
                    return new StringEval(result);
                }
                return new StringEval(makeConcise(result, form));
            } catch (EvaluationException e) {
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException e2) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    private String integerToRoman(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            while (number >= VALUES[i]) {
                number -= VALUES[i];
                result.append(ROMAN[i]);
            }
        }
        return result.toString();
    }

    public String makeConcise(String input, int form) {
        String result = input;
        for (int i = 0; i <= form && i <= 4 && form > 0; i++) {
            if (i != 1 || form <= 1) {
                String[] repl = REPLACEMENTS[i];
                for (int j = 0; j < repl.length - 1; j += 2) {
                    result = result.replace(repl[j], repl[j + 1]);
                }
            }
        }
        return result;
    }
}
