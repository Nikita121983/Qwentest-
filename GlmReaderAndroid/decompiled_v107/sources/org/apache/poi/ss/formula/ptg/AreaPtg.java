package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.LittleEndianInput;

/* loaded from: classes10.dex */
public final class AreaPtg extends Area2DPtgBase {
    public static final short sid = 37;

    public AreaPtg(int firstRow, int lastRow, int firstColumn, int lastColumn, boolean firstRowRelative, boolean lastRowRelative, boolean firstColRelative, boolean lastColRelative) {
        super(firstRow, lastRow, firstColumn, lastColumn, firstRowRelative, lastRowRelative, firstColRelative, lastColRelative);
    }

    public AreaPtg(AreaPtg other) {
        super(other);
    }

    public AreaPtg(LittleEndianInput in) {
        super(in);
    }

    public AreaPtg(AreaReference arearef) {
        super(arearef);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 37;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public AreaPtg copy() {
        return new AreaPtg(this);
    }
}
