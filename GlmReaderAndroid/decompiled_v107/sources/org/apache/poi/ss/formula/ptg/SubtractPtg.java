package org.apache.poi.ss.formula.ptg;

import org.apache.logging.log4j.util.ProcessIdUtil;

/* loaded from: classes10.dex */
public final class SubtractPtg extends ValueOperatorPtg {
    public static final SubtractPtg instance = new SubtractPtg();
    public static final byte sid = 4;

    private SubtractPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 4;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + ProcessIdUtil.DEFAULT_PROCESSID + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public SubtractPtg copy() {
        return instance;
    }
}
