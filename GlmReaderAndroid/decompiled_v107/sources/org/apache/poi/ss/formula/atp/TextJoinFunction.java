package org.apache.poi.ss.formula.atp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class TextJoinFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new TextJoinFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private TextJoinFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length < 3 || args.length > 254) {
            return ErrorEval.VALUE_INVALID;
        }
        int srcRowIndex = ec.getRowIndex();
        int srcColumnIndex = ec.getColumnIndex();
        try {
            List<ValueEval> delimiterArgs = getValues(args[0], srcRowIndex, srcColumnIndex, true);
            ValueEval ignoreEmptyArg = OperandResolver.getSingleValue(args[1], srcRowIndex, srcColumnIndex);
            boolean ignoreEmpty = OperandResolver.coerceValueToBoolean(ignoreEmptyArg, false).booleanValue();
            ArrayList<String> textValues = new ArrayList<>();
            for (int i = 2; i < args.length; i++) {
                List<ValueEval> textArgs = getValues(args[i], srcRowIndex, srcColumnIndex, false);
                for (ValueEval textArg : textArgs) {
                    String textValue = OperandResolver.coerceValueToString(textArg);
                    if (!ignoreEmpty || (textValue != null && !textValue.isEmpty())) {
                        textValues.add(textValue);
                    }
                }
            }
            if (delimiterArgs.isEmpty()) {
                return new StringEval(String.join("", textValues));
            }
            if (delimiterArgs.size() == 1) {
                String delimiter = laxValueToString(delimiterArgs.get(0));
                return new StringEval(String.join(delimiter, textValues));
            }
            List<String> delimiters = new ArrayList<>();
            for (ValueEval delimiterArg : delimiterArgs) {
                delimiters.add(laxValueToString(delimiterArg));
            }
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < textValues.size(); i2++) {
                if (i2 > 0) {
                    int delimiterIndex = (i2 - 1) % delimiters.size();
                    sb.append(delimiters.get(delimiterIndex));
                }
                sb.append(textValues.get(i2));
            }
            return new StringEval(sb.toString());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private String laxValueToString(ValueEval eval) {
        return eval instanceof MissingArgEval ? "" : OperandResolver.coerceValueToString(eval);
    }

    private List<ValueEval> getValues(ValueEval eval, int srcRowIndex, int srcColumnIndex, boolean lastRowOnly) throws EvaluationException {
        if (eval instanceof AreaEval) {
            AreaEval ae = (AreaEval) eval;
            List<ValueEval> list = new ArrayList<>();
            int startRow = lastRowOnly ? ae.getLastRow() : ae.getFirstRow();
            for (int r = startRow; r <= ae.getLastRow(); r++) {
                for (int c = ae.getFirstColumn(); c <= ae.getLastColumn(); c++) {
                    list.add(OperandResolver.getSingleValue(ae.getAbsoluteValue(r, c), r, c));
                }
            }
            return list;
        }
        return Collections.singletonList(OperandResolver.getSingleValue(eval, srcRowIndex, srcColumnIndex));
    }
}
