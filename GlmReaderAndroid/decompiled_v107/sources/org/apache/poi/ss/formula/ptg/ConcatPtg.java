package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public final class ConcatPtg extends ValueOperatorPtg {
    private static final String CONCAT = "&";
    public static final ConcatPtg instance = new ConcatPtg();
    public static final byte sid = 8;

    private ConcatPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 8;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + CONCAT + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public ConcatPtg copy() {
        return instance;
    }
}
