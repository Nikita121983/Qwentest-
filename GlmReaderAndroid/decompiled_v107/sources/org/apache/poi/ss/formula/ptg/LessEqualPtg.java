package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class LessEqualPtg extends ValueOperatorPtg {
    public static final LessEqualPtg instance = new LessEqualPtg();
    public static final byte sid = 10;

    private LessEqualPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 10;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + "<=" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public LessEqualPtg copy() {
        return instance;
    }
}
