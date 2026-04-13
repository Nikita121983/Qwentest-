package org.apache.poi.ss.formula.ptg;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class UnionPtg extends OperationPtg {
    public static final UnionPtg instance = new UnionPtg();
    public static final byte sid = 16;

    private UnionPtg() {
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
        return CollectionUtils.COMMA;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        return operands[0] + CollectionUtils.COMMA + operands[1];
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public int getNumberOfOperands() {
        return 2;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public UnionPtg copy() {
        return instance;
    }
}
