package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class PercentPtg extends ValueOperatorPtg {
    private static final String PERCENT = "%";
    public static final int SIZE = 1;
    public static final PercentPtg instance = new PercentPtg();
    public static final byte sid = 20;

    private PercentPtg() {
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
        return operands[0] + PERCENT;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public PercentPtg copy() {
        return instance;
    }
}
