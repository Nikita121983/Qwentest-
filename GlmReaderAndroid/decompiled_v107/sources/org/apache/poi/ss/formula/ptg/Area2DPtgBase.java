package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class Area2DPtgBase extends AreaPtgBase {
    private static final int SIZE = 9;

    /* JADX INFO: Access modifiers changed from: protected */
    public Area2DPtgBase(int firstRow, int lastRow, int firstColumn, int lastColumn, boolean firstRowRelative, boolean lastRowRelative, boolean firstColRelative, boolean lastColRelative) {
        super(firstRow, lastRow, firstColumn, lastColumn, firstRowRelative, lastRowRelative, firstColRelative, lastColRelative);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Area2DPtgBase(Area2DPtgBase other) {
        super(other);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Area2DPtgBase(AreaReference ar) {
        super(ar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Area2DPtgBase(LittleEndianInput in) {
        readCoordinates(in);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final void write(LittleEndianOutput out) {
        out.writeByte(getSid() + getPtgClass());
        writeCoordinates(out);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final int getSize() {
        return 9;
    }

    @Override // org.apache.poi.ss.formula.ptg.AreaPtgBase, org.apache.poi.ss.formula.ptg.Ptg
    public final String toFormulaString() {
        return formatReferenceAsString();
    }
}
