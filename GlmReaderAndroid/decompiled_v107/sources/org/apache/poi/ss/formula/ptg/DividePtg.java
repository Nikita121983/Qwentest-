package org.apache.poi.ss.formula.ptg;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes10.dex */
public final class DividePtg extends ValueOperatorPtg {
    public static final DividePtg instance = new DividePtg();
    public static final byte sid = 6;

    private DividePtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 6;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + PackagingURIHelper.FORWARD_SLASH_STRING + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public DividePtg copy() {
        return instance;
    }
}
