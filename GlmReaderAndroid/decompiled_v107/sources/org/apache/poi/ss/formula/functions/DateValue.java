package org.apache.poi.ss.formula.functions;

import java.time.DateTimeException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateParser;

/* loaded from: classes10.dex */
public class DateValue extends Fixed1ArgFunction {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DateValue.class);

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval dateTextArg) {
        try {
            String dateText = OperandResolver.coerceValueToString(OperandResolver.getSingleValue(dateTextArg, srcRowIndex, srcColumnIndex));
            if (dateText != null && !dateText.isEmpty()) {
                return new NumberEval(DateUtil.getExcelDate(DateParser.parseLocalDate(dateText)));
            }
            return ErrorEval.VALUE_INVALID;
        } catch (DateTimeException dte) {
            LOG.atInfo().log("Failed to parse date", dte);
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
