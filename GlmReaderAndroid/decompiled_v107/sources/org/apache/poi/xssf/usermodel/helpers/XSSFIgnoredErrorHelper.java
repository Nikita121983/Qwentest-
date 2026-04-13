package org.apache.poi.xssf.usermodel.helpers;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;

/* loaded from: classes10.dex */
public class XSSFIgnoredErrorHelper {
    public static boolean isSet(IgnoredErrorType errorType, CTIgnoredError error) {
        switch (errorType) {
            case CALCULATED_COLUMN:
                return error.isSetCalculatedColumn();
            case EMPTY_CELL_REFERENCE:
                return error.isSetEmptyCellReference();
            case EVALUATION_ERROR:
                return error.isSetEvalError();
            case FORMULA:
                return error.isSetFormula();
            case FORMULA_RANGE:
                return error.isSetFormulaRange();
            case LIST_DATA_VALIDATION:
                return error.isSetListDataValidation();
            case NUMBER_STORED_AS_TEXT:
                return error.isSetNumberStoredAsText();
            case TWO_DIGIT_TEXT_YEAR:
                return error.isSetTwoDigitTextYear();
            case UNLOCKED_FORMULA:
                return error.isSetUnlockedFormula();
            default:
                throw new IllegalStateException();
        }
    }

    public static void set(IgnoredErrorType errorType, CTIgnoredError error) {
        switch (errorType) {
            case CALCULATED_COLUMN:
                error.setCalculatedColumn(true);
                return;
            case EMPTY_CELL_REFERENCE:
                error.setEmptyCellReference(true);
                return;
            case EVALUATION_ERROR:
                error.setEvalError(true);
                return;
            case FORMULA:
                error.setFormula(true);
                return;
            case FORMULA_RANGE:
                error.setFormulaRange(true);
                return;
            case LIST_DATA_VALIDATION:
                error.setListDataValidation(true);
                return;
            case NUMBER_STORED_AS_TEXT:
                error.setNumberStoredAsText(true);
                return;
            case TWO_DIGIT_TEXT_YEAR:
                error.setTwoDigitTextYear(true);
                return;
            case UNLOCKED_FORMULA:
                error.setUnlockedFormula(true);
                return;
            default:
                throw new IllegalStateException();
        }
    }

    public static void addIgnoredErrors(CTIgnoredError err, String ref, IgnoredErrorType... ignoredErrorTypes) {
        err.setSqref(Collections.singletonList(ref));
        for (IgnoredErrorType errType : ignoredErrorTypes) {
            set(errType, err);
        }
    }

    public static Set<IgnoredErrorType> getErrorTypes(CTIgnoredError err) {
        Set<IgnoredErrorType> result = new LinkedHashSet<>();
        for (IgnoredErrorType errType : IgnoredErrorType.values()) {
            if (isSet(errType, err)) {
                result.add(errType);
            }
        }
        return result;
    }
}
