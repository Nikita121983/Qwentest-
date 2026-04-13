package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class LessThanPtg extends ValueOperatorPtg {
    private static final String LESSTHAN = "<";
    public static final LessThanPtg instance = new LessThanPtg();
    public static final byte sid = 9;

    private LessThanPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 9;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + LESSTHAN + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public LessThanPtg copy() {
        return instance;
    }
}
