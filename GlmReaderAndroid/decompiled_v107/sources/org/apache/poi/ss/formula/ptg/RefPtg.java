package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RefPtg extends Ref2DPtgBase {
    public static final byte sid = 36;

    @Override // org.apache.poi.ss.formula.ptg.Ref2DPtgBase, org.apache.poi.ss.formula.ptg.Ptg
    public /* bridge */ /* synthetic */ void write(LittleEndianOutput littleEndianOutput) {
        super.write(littleEndianOutput);
    }

    public RefPtg(String cellref) {
        super(new CellReference(cellref));
    }

    public RefPtg(RefPtg other) {
        super(other);
    }

    public RefPtg(int row, int column, boolean isRowRelative, boolean isColumnRelative) {
        super(row, column, isRowRelative, isColumnRelative);
    }

    public RefPtg(LittleEndianInput in) {
        super(in);
    }

    public RefPtg(CellReference cr) {
        super(cr);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public RefPtg copy() {
        return new RefPtg(this);
    }
}
