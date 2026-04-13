package org.apache.poi.ss.formula.constant;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.usermodel.FormulaError;

/* loaded from: classes10.dex */
public final class ErrorConstant {
    private final int _errorCode;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ErrorConstant.class);
    private static final ErrorConstant NULL = new ErrorConstant(FormulaError.NULL.getCode());
    private static final ErrorConstant DIV_0 = new ErrorConstant(FormulaError.DIV0.getCode());
    private static final ErrorConstant VALUE = new ErrorConstant(FormulaError.VALUE.getCode());
    private static final ErrorConstant REF = new ErrorConstant(FormulaError.REF.getCode());
    private static final ErrorConstant NAME = new ErrorConstant(FormulaError.NAME.getCode());
    private static final ErrorConstant NUM = new ErrorConstant(FormulaError.NUM.getCode());
    private static final ErrorConstant NA = new ErrorConstant(FormulaError.NA.getCode());

    private ErrorConstant(int errorCode) {
        this._errorCode = errorCode;
    }

    public int getErrorCode() {
        return this._errorCode;
    }

    public String getText() {
        if (FormulaError.isValidCode(this._errorCode)) {
            return FormulaError.forInt(this._errorCode).getString();
        }
        return "unknown error code (" + this._errorCode + ")";
    }

    public static ErrorConstant valueOf(int errorCode) {
        if (FormulaError.isValidCode(errorCode)) {
            switch (FormulaError.forInt(errorCode)) {
                case NULL:
                    return NULL;
                case DIV0:
                    return DIV_0;
                case VALUE:
                    return VALUE;
                case REF:
                    return REF;
                case NAME:
                    return NAME;
                case NUM:
                    return NUM;
                case NA:
                    return NA;
            }
        }
        LOG.atWarn().log("Warning - unexpected error code ({})", Unbox.box(errorCode));
        return new ErrorConstant(errorCode);
    }

    public String toString() {
        return getClass().getName() + " [" + getText() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
