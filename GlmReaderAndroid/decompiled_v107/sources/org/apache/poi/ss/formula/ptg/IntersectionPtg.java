package org.apache.poi.ss.formula.ptg;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class IntersectionPtg extends OperationPtg {
    public static final IntersectionPtg instance = new IntersectionPtg();
    public static final byte sid = 15;

    private IntersectionPtg() {
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final boolean isBaseToken() {
        return true;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return StringUtils.SPACE;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + StringUtils.SPACE + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public IntersectionPtg copy() {
        return instance;
    }
}
