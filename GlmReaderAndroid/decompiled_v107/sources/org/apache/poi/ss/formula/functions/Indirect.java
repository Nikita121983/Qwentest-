package org.apache.poi.ss.formula.functions;

import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.usermodel.Table;

/* loaded from: classes10.dex */
public final class Indirect implements FreeRefFunction {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) Indirect.class);
    public static final FreeRefFunction instance = new Indirect();

    private Indirect() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        boolean isA1style;
        if (args.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            ValueEval ve = OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
            String text = OperandResolver.coerceValueToString(ve);
            switch (args.length) {
                case 1:
                    isA1style = true;
                    break;
                case 2:
                    isA1style = evaluateBooleanArg(args[1], ec);
                    break;
                default:
                    return ErrorEval.VALUE_INVALID;
            }
            return evaluateIndirect(ec, text, isA1style);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static boolean evaluateBooleanArg(ValueEval arg, OperationEvaluationContext ec) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, ec.getRowIndex(), ec.getColumnIndex());
        if (ve == BlankEval.instance || ve == MissingArgEval.instance) {
            return false;
        }
        return OperandResolver.coerceValueToBoolean(ve, false).booleanValue();
    }

    private static ValueEval evaluateIndirect(OperationEvaluationContext ec, String text, boolean isA1style) {
        String workbookName;
        String sheetName;
        String sheetName2;
        String refStrPart1;
        String refStrPart2;
        int plingPos = text.lastIndexOf(33);
        if (plingPos < 0) {
            sheetName2 = text;
            workbookName = null;
            sheetName = null;
        } else {
            String[] parts = parseWorkbookAndSheetName(text.subSequence(0, plingPos));
            if (parts == null) {
                return ErrorEval.REF_INVALID;
            }
            String workbookName2 = parts[0];
            String sheetName3 = parts[1];
            workbookName = workbookName2;
            sheetName = sheetName3;
            sheetName2 = text.substring(plingPos + 1);
        }
        if (isA1style && Table.isStructuredReference.matcher(sheetName2).matches()) {
            try {
                Area3DPxg areaPtg = FormulaParser.parseStructuredReference(sheetName2, (FormulaParsingWorkbook) ec.getWorkbook(), ec.getRowIndex());
                return ec.getArea3DEval(areaPtg);
            } catch (FormulaParseException e) {
                return ErrorEval.REF_INVALID;
            }
        }
        int colonPos = sheetName2.indexOf(58);
        if (colonPos < 0) {
            String refStrPart12 = sheetName2.trim();
            refStrPart1 = refStrPart12;
            refStrPart2 = null;
        } else {
            String refStrPart13 = sheetName2.substring(0, colonPos);
            refStrPart1 = refStrPart13.trim();
            refStrPart2 = sheetName2.substring(colonPos + 1).trim();
        }
        try {
            return ec.getDynamicReference(workbookName, sheetName, refStrPart1, refStrPart2, isA1style);
        } catch (Exception e2) {
            LOGGER.atWarn().log("Indirect function: failed to parse reference {}", text, e2);
            return ErrorEval.REF_INVALID;
        }
    }

    private static String[] parseWorkbookAndSheetName(CharSequence text) {
        String wbName;
        int rbPos;
        int lastIx = text.length() - 1;
        if (lastIx < 0 || canTrim(text)) {
            return null;
        }
        char firstChar = text.charAt(0);
        if (Character.isWhitespace(firstChar)) {
            return null;
        }
        if (firstChar == '\'') {
            if (text.charAt(lastIx) != '\'') {
                return null;
            }
            char firstChar2 = text.charAt(1);
            if (Character.isWhitespace(firstChar2)) {
                return null;
            }
            if (firstChar2 == '[') {
                int rbPos2 = text.toString().lastIndexOf(93);
                if (rbPos2 < 0 || (wbName = unescapeString(text.subSequence(2, rbPos2))) == null || canTrim(wbName)) {
                    return null;
                }
                rbPos = rbPos2 + 1;
            } else {
                wbName = null;
                rbPos = 1;
            }
            String sheetName = unescapeString(text.subSequence(rbPos, lastIx));
            if (sheetName == null) {
                return null;
            }
            return new String[]{wbName, sheetName};
        }
        if (firstChar == '[') {
            int rbPos3 = text.toString().lastIndexOf(93);
            if (rbPos3 < 0) {
                return null;
            }
            CharSequence wbName2 = text.subSequence(1, rbPos3);
            if (canTrim(wbName2)) {
                return null;
            }
            CharSequence sheetName2 = text.subSequence(rbPos3 + 1, text.length());
            if (canTrim(sheetName2)) {
                return null;
            }
            return new String[]{wbName2.toString(), sheetName2.toString()};
        }
        return new String[]{null, text.toString()};
    }

    private static String unescapeString(CharSequence text) {
        int len = text.length();
        StringBuilder sb = new StringBuilder(len);
        int i = 0;
        while (i < len) {
            char ch = text.charAt(i);
            if (ch == '\'' && ((i = i + 1) >= len || (ch = text.charAt(i)) != '\'')) {
                return null;
            }
            sb.append(ch);
            i++;
        }
        return sb.toString();
    }

    private static boolean canTrim(CharSequence text) {
        int lastIx = text.length() - 1;
        if (lastIx < 0) {
            return false;
        }
        if (Character.isWhitespace(text.charAt(0))) {
            return true;
        }
        return Character.isWhitespace(text.charAt(lastIx));
    }
}
