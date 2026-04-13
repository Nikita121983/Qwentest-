package org.apache.poi.ss.formula.ptg;

/* loaded from: classes10.dex */
public abstract class ScalarConstantPtg extends Ptg {
    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final boolean isBaseToken() {
        return true;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final byte getDefaultOperandClass() {
        return (byte) 32;
    }
}
