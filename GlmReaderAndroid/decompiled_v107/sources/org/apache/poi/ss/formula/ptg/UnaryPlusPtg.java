package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class UnaryPlusPtg extends ValueOperatorPtg {
    private static final String ADD = "+";
    public static final UnaryPlusPtg instance = new UnaryPlusPtg();
    public static final byte sid = 18;

    private UnaryPlusPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return ADD + operands[0];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public UnaryPlusPtg copy() {
        return instance;
    }
}
