package org.apache.poi.hssf.usermodel.helpers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;

/* loaded from: classes10.dex */
public final class HSSFRowShifter extends RowShifter {
    public HSSFRowShifter(HSSFSheet sh) {
        super(sh);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    @NotImplemented
    public void updateNamedRanges(FormulaShifter formulaShifter) {
        throw new NotImplementedException("HSSFRowShifter.updateNamedRanges");
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    @NotImplemented
    public void updateFormulas(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateFormulas");
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    @NotImplemented
    public void updateConditionalFormatting(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateConditionalFormatting");
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    @NotImplemented
    public void updateHyperlinks(FormulaShifter formulaShifter) {
        throw new NotImplementedException("updateHyperlinks");
    }

    @Internal(since = "5.1.0")
    public void updateRowFormulas(HSSFRow row, FormulaShifter formulaShifter) {
        HSSFRowColShifter.updateRowFormulas(row, formulaShifter);
    }
}
