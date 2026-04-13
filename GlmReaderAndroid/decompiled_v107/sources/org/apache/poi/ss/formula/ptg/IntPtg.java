package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class IntPtg extends ScalarConstantPtg {
    private static final int MAX_VALUE = 65535;
    private static final int MIN_VALUE = 0;
    public static final int SIZE = 3;
    public static final byte sid = 30;
    private final int field_1_value;

    public static boolean isInRange(int i) {
        return i >= 0 && i <= 65535;
    }

    public IntPtg(LittleEndianInput in) {
        this(in.readUShort());
    }

    public IntPtg(int value) {
        if (!isInRange(value)) {
            throw new IllegalArgumentException("value is out of range: " + value);
        }
        this.field_1_value = value;
    }

    public int getValue() {
        return this.field_1_value;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
        out.writeShort(getValue());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 3;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return String.valueOf(getValue());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public IntPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.IntPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(IntPtg.this.getValue());
            }
        });
    }
}
