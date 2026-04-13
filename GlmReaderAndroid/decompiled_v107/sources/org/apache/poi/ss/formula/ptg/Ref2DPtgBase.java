package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class Ref2DPtgBase extends RefPtgBase {
    private static final int SIZE = 5;

    /* JADX INFO: Access modifiers changed from: protected */
    public Ref2DPtgBase(int row, int column, boolean isRowRelative, boolean isColumnRelative) {
        setRow(row);
        setColumn(column);
        setRowRelative(isRowRelative);
        setColRelative(isColumnRelative);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Ref2DPtgBase(Ref2DPtgBase other) {
        super(other);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Ref2DPtgBase(LittleEndianInput in) {
        readCoordinates(in);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Ref2DPtgBase(CellReference cr) {
        super(cr);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getSid() + getPtgClass());
        writeCoordinates(out);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final String toFormulaString() {
        return formatReferenceAsString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final int getSize() {
        return 5;
    }
}
