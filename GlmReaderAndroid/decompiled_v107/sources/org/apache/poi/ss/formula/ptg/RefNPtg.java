package org.apache.poi.ss.formula.ptg;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RefNPtg extends Ref2DPtgBase {
    public static final byte sid = 44;

    @Override // org.apache.poi.ss.formula.ptg.Ref2DPtgBase, org.apache.poi.ss.formula.ptg.Ptg
    public /* bridge */ /* synthetic */ void write(LittleEndianOutput littleEndianOutput) {
        super.write(littleEndianOutput);
    }

    public RefNPtg(LittleEndianInput in) {
        super(in);
    }

    public RefNPtg(RefNPtg other) {
        super(other);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ss.formula.ptg.RefPtgBase
    public final String formatReferenceAsString() {
        StringBuilder builder = new StringBuilder();
        if (isRowRelative()) {
            builder.append("RowOffset: ").append(getRow()).append(StringUtils.SPACE);
        } else {
            builder.append(getRow() + 1);
        }
        if (isColRelative()) {
            builder.append(" ColOffset: ").append(getColumn());
        } else {
            builder.append(CellReference.convertNumToColString(getColumn()));
        }
        return builder.toString();
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public RefNPtg copy() {
        return new RefNPtg(this);
    }
}
