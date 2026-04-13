package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

@Internal
/* loaded from: classes10.dex */
public final class FormulaSpecialCachedValue implements GenericRecord {
    private static final long BIT_MARKER = -281474976710656L;
    public static final int BOOLEAN = 1;
    private static final int DATA_INDEX = 2;
    public static final int EMPTY = 3;
    public static final int ERROR_CODE = 2;
    public static final int STRING = 0;
    private static final int VARIABLE_DATA_LENGTH = 6;
    private final byte[] _variableData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormulaSpecialCachedValue(FormulaSpecialCachedValue other) {
        this._variableData = other._variableData == null ? null : (byte[]) other._variableData.clone();
    }

    private FormulaSpecialCachedValue(byte[] data) {
        this._variableData = data;
    }

    public int getTypeCode() {
        return this._variableData[0];
    }

    public static FormulaSpecialCachedValue create(long valueLongBits) {
        if ((valueLongBits & BIT_MARKER) != BIT_MARKER) {
            return null;
        }
        byte[] result = new byte[6];
        long x = valueLongBits;
        for (int i = 0; i < 6; i++) {
            result[i] = (byte) x;
            x >>= 8;
        }
        switch (result[0]) {
            case 0:
            case 1:
            case 2:
            case 3:
                return new FormulaSpecialCachedValue(result);
            default:
                throw new RecordFormatException("Bad special value code (" + ((int) result[0]) + ")");
        }
    }

    public void serialize(LittleEndianOutput out) {
        out.write(this._variableData);
        out.writeShort(65535);
    }

    public String formatDebugString() {
        return formatValue() + Chars.SPACE + HexDump.toHex(this._variableData);
    }

    private String formatValue() {
        int typeCode = getTypeCode();
        switch (typeCode) {
            case 0:
                return "<string>";
            case 1:
                return getDataValue() == 0 ? "FALSE" : "TRUE";
            case 2:
                return ErrorEval.getText(getDataValue());
            case 3:
                return "<empty>";
            default:
                return "#error(type=" + typeCode + ")#";
        }
    }

    private int getDataValue() {
        return this._variableData[2];
    }

    public static FormulaSpecialCachedValue createCachedEmptyValue() {
        return create(3, 0);
    }

    public static FormulaSpecialCachedValue createForString() {
        return create(0, 0);
    }

    public static FormulaSpecialCachedValue createCachedBoolean(boolean z) {
        return create(1, z ? 1 : 0);
    }

    public static FormulaSpecialCachedValue createCachedErrorCode(int errorCode) {
        return create(2, errorCode);
    }

    private static FormulaSpecialCachedValue create(int code, int data) {
        byte[] vd = {(byte) code, 0, (byte) data, 0, 0, 0};
        return new FormulaSpecialCachedValue(vd);
    }

    public String toString() {
        return getClass().getName() + '[' + formatValue() + ']';
    }

    @Deprecated
    public int getValueType() {
        int typeCode = getTypeCode();
        switch (typeCode) {
            case 0:
            case 3:
                return CellType.STRING.getCode();
            case 1:
                return CellType.BOOLEAN.getCode();
            case 2:
                return CellType.ERROR.getCode();
            default:
                throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
        }
    }

    public CellType getValueTypeEnum() {
        int typeCode = getTypeCode();
        switch (typeCode) {
            case 0:
            case 3:
                return CellType.STRING;
            case 1:
                return CellType.BOOLEAN;
            case 2:
                return CellType.ERROR;
            default:
                throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
        }
    }

    public boolean getBooleanValue() {
        if (getTypeCode() == 1) {
            return getDataValue() != 0;
        }
        throw new IllegalStateException("Not a boolean cached value - " + formatValue());
    }

    public int getErrorValue() {
        if (getTypeCode() != 2) {
            throw new IllegalStateException("Not an error cached value - " + formatValue());
        }
        return getDataValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object getGenericValue() {
        int typeCode = getTypeCode();
        switch (typeCode) {
            case 0:
                return TypedValues.Custom.S_STRING;
            case 1:
                return Boolean.valueOf(getBooleanValue());
            case 2:
                return Integer.valueOf(getErrorValue());
            case 3:
                return null;
            default:
                throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
        }
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaSpecialCachedValue$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                Object genericValue;
                genericValue = FormulaSpecialCachedValue.this.getGenericValue();
                return genericValue;
            }
        }, "typeCode", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.FormulaSpecialCachedValue$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FormulaSpecialCachedValue.this.getTypeCode());
            }
        }, new int[]{0, 1, 2, 3}, new String[]{"STRING", "BOOLEAN", "ERROR_CODE", "EMPTY"}));
    }
}
