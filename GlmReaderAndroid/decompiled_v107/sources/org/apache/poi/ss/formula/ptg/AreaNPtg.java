package org.apache.poi.ss.formula.ptg;

import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public final class AreaNPtg extends Area2DPtgBase {
    public static final short sid = 45;

    public AreaNPtg(AreaNPtg other) {
        super(other);
    }

    public AreaNPtg(LittleEndianInput in) {
        super(in);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 45;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public AreaNPtg copy() {
        return new AreaNPtg(this);
    }
}
