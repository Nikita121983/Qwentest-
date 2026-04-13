package org.apache.poi.xssf.usermodel.helpers;

import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/* loaded from: classes10.dex */
public final class XSSFRowShifter extends RowShifter {
    public XSSFRowShifter(XSSFSheet sh) {
        super(sh);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    public void updateNamedRanges(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateNamedRanges(this.sheet, formulaShifter);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    public void updateFormulas(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateFormulas(this.sheet, formulaShifter);
    }

    @Internal(since = "3.15 beta 2")
    public void updateRowFormulas(XSSFRow row, FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateRowFormulas(row, formulaShifter);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    public void updateConditionalFormatting(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateConditionalFormatting(this.sheet, formulaShifter);
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    public void updateHyperlinks(FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateHyperlinks(this.sheet, formulaShifter);
    }
}
