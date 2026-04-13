package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class GreaterEqualPtg extends ValueOperatorPtg {
    public static final int SIZE = 1;
    public static final GreaterEqualPtg instance = new GreaterEqualPtg();
    public static final byte sid = 12;

    private GreaterEqualPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 12;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + ">=" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public GreaterEqualPtg copy() {
        return instance;
    }
}
