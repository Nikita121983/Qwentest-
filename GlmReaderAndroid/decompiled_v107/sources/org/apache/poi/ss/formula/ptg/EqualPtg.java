package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class EqualPtg extends ValueOperatorPtg {
    public static final EqualPtg instance = new EqualPtg();
    public static final byte sid = 11;

    private EqualPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 11;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + "=" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public EqualPtg copy() {
        return instance;
    }
}
