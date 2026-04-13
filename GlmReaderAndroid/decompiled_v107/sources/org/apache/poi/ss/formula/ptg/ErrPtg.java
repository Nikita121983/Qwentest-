package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ErrPtg extends ScalarConstantPtg {
    private static final int SIZE = 2;
    public static final short sid = 28;
    private final int field_1_error_code;
    public static final ErrPtg NULL_INTERSECTION = new ErrPtg(FormulaError.NULL.getCode());
    public static final ErrPtg DIV_ZERO = new ErrPtg(FormulaError.DIV0.getCode());
    public static final ErrPtg VALUE_INVALID = new ErrPtg(FormulaError.VALUE.getCode());
    public static final ErrPtg REF_INVALID = new ErrPtg(FormulaError.REF.getCode());
    public static final ErrPtg NAME_INVALID = new ErrPtg(FormulaError.NAME.getCode());
    public static final ErrPtg NUM_ERROR = new ErrPtg(FormulaError.NUM.getCode());
    public static final ErrPtg N_A = new ErrPtg(FormulaError.NA.getCode());

    private ErrPtg(int errorCode) {
        if (!FormulaError.isValidCode(errorCode)) {
            throw new IllegalArgumentException("Invalid error code (" + errorCode + ")");
        }
        this.field_1_error_code = errorCode;
    }

    public static ErrPtg read(LittleEndianInput in) {
        return valueOf(in.readByte());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 28);
        out.writeByte(this.field_1_error_code);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return FormulaError.forInt(this.field_1_error_code).getString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 28;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 2;
    }

    public int getErrorCode() {
        return this.field_1_error_code;
    }

    public static ErrPtg valueOf(int code) {
        switch (FormulaError.forInt(code)) {
            case DIV0:
                return DIV_ZERO;
            case NA:
                return N_A;
            case NAME:
                return NAME_INVALID;
            case NULL:
                return NULL_INTERSECTION;
            case NUM:
                return NUM_ERROR;
            case REF:
                return REF_INVALID;
            case VALUE:
                return VALUE_INVALID;
            default:
                throw new IllegalStateException("Unexpected error code (" + code + ")");
        }
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public ErrPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("errorCode", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ErrPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ErrPtg.this.getErrorCode());
            }
        });
    }
}
