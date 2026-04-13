package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NumberPtg extends ScalarConstantPtg {
    public static final int SIZE = 9;
    public static final byte sid = 31;
    private final double field_1_value;

    public NumberPtg(LittleEndianInput in) {
        this(in.readDouble());
    }

    public NumberPtg(String value) {
        this(Double.parseDouble(value));
    }

    public NumberPtg(double value) {
        this.field_1_value = value;
    }

    public double getValue() {
        return this.field_1_value;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
        out.writeDouble(getValue());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 9;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return NumberToTextConverter.toText(this.field_1_value);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public NumberPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NumberPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(NumberPtg.this.getValue());
            }
        });
    }
}
