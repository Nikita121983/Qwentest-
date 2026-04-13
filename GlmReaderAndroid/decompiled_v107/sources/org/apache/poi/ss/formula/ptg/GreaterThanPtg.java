package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class GreaterThanPtg extends ValueOperatorPtg {
    private static final String GREATERTHAN = ">";
    public static final GreaterThanPtg instance = new GreaterThanPtg();
    public static final byte sid = 13;

    private GreaterThanPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 13;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + GREATERTHAN + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public GreaterThanPtg copy() {
        return instance;
    }
}
