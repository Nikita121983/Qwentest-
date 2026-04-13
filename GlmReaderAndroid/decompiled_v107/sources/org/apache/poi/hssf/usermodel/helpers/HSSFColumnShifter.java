package org.apache.poi.hssf.usermodel.helpers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.helpers.ColumnShifter;
import org.apache.poi.util.NotImplemented;

/* loaded from: classes10.dex */
public final class HSSFColumnShifter extends ColumnShifter {
    public HSSFColumnShifter(HSSFSheet sh) {
        super(sh);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    @NotImplemented
    public void updateNamedRanges(FormulaShifter formulaShifter) {
        throw new NotImplementedException("HSSFColumnShifter.updateNamedRanges");
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
}
