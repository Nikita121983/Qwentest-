package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class MultiplyPtg extends ValueOperatorPtg {
    public static final MultiplyPtg instance = new MultiplyPtg();
    public static final byte sid = 5;

    private MultiplyPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 5;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + "*" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public MultiplyPtg copy() {
        return instance;
    }
}
