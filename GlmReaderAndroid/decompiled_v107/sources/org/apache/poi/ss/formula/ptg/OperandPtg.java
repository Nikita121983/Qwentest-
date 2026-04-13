package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public abstract class OperandPtg extends Ptg {
    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public abstract OperandPtg copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public OperandPtg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperandPtg(OperandPtg other) {
        super(other);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final boolean isBaseToken() {
        return false;
    }
}
