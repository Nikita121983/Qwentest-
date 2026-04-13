package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class NotEqualPtg extends ValueOperatorPtg {
    public static final NotEqualPtg instance = new NotEqualPtg();
    public static final byte sid = 14;

    private NotEqualPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + "<>" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public NotEqualPtg copy() {
        return instance;
    }
}
