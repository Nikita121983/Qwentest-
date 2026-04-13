package org.apache.poi.ss.formula.functions;

import java.time.DateTimeException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateParser;

/* loaded from: classes10.dex */
public class TimeValue extends Fixed1ArgFunction {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) TimeValue.class);

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval dateTimeTextArg) {
        try {
            String dateTimeText = OperandResolver.coerceValueToString(OperandResolver.getSingleValue(dateTimeTextArg, srcRowIndex, srcColumnIndex));
            if (dateTimeText == null || dateTimeText.isEmpty()) {
                return BlankEval.instance;
            }
            try {
                return parseTimeFromDateTime(dateTimeText);
            } catch (Exception e) {
                try {
                    return parseTimeFromDateTime("1/01/2000 " + dateTimeText);
                } catch (Exception e2) {
                    DateParser.parseLocalDate(dateTimeText);
                    return new NumberEval(0.0d);
                }
            }
        } catch (DateTimeException dte) {
            LOG.atInfo().log("Failed to parse date/time", dte);
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e3) {
            return e3.getErrorEval();
        }
    }

    private NumberEval parseTimeFromDateTime(String dateTimeText) throws EvaluationException {
        double dateTimeValue = DateUtil.parseDateTime(dateTimeText).doubleValue();
        return new NumberEval(dateTimeValue - DateUtil.getExcelDate(DateParser.parseLocalDate(dateTimeText)));
    }
}
