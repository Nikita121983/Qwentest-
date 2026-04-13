package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class PowerPtg extends ValueOperatorPtg {
    public static final PowerPtg instance = new PowerPtg();
    public static final byte sid = 7;

    private PowerPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 7;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + "^" + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public PowerPtg copy() {
        return instance;
    }
}
