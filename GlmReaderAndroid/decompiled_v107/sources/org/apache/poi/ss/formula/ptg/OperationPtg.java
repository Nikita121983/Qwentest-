package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;

/* loaded from: classes10.dex */
public abstract class OperationPtg extends Ptg {
    public static final int TYPE_BINARY = 1;
    public static final int TYPE_FUNCTION = 2;
    public static final int TYPE_UNARY = 0;

    public abstract int getNumberOfOperands();

    public abstract String toFormulaString(String[] strArr);

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 32;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }
}
