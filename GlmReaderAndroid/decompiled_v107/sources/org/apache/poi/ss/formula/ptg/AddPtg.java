package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class AddPtg extends ValueOperatorPtg {
    private static final String ADD = "+";
    public static final AddPtg instance = new AddPtg();
    public static final byte sid = 3;

    private AddPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 3;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + ADD + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public AddPtg copy() {
        return instance;
    }
}
