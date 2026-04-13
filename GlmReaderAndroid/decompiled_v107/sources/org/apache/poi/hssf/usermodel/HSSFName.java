package org.apache.poi.hssf.usermodel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellReference;

/* loaded from: classes10.dex */
public final class HSSFName implements Name {
    private final HSSFWorkbook _book;
    private final NameCommentRecord _commentRec;
    private final NameRecord _definedNameRec;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSSFName(HSSFWorkbook book, NameRecord name) {
        this(book, name, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSSFName(HSSFWorkbook book, NameRecord name, NameCommentRecord comment) {
        this._book = book;
        this._definedNameRec = name;
        this._commentRec = comment;
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public String getSheetName() {
        int indexToExternSheet = this._definedNameRec.getExternSheetNumber();
        return this._book.getWorkbook().findSheetFirstNameFromExternSheet(indexToExternSheet);
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public String getNameName() {
        return this._definedNameRec.getNameText();
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public void setNameName(String nameName) {
        validateName(nameName);
        InternalWorkbook wb = this._book.getWorkbook();
        this._definedNameRec.setNameText(nameName);
        int sheetNumber = this._definedNameRec.getSheetNumber();
        int lastNameIndex = wb.getNumNames() - 1;
        for (int i = lastNameIndex; i >= 0; i--) {
            NameRecord rec = wb.getNameRecord(i);
            if (rec != this._definedNameRec && rec.getNameText().equalsIgnoreCase(nameName) && sheetNumber == rec.getSheetNumber()) {
                String msg = "The " + (sheetNumber == 0 ? "workbook" : "sheet") + " already contains this name: " + nameName;
                this._definedNameRec.setNameText(nameName + "(2)");
                throw new IllegalArgumentException(msg);
            }
        }
        if (this._commentRec != null) {
            this._commentRec.setNameText(nameName);
            this._book.getWorkbook().updateNameCommentRecordCache(this._commentRec);
        }
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot exceed 255 characters in length");
        }
        if (name.equalsIgnoreCase("R") || name.equalsIgnoreCase("C")) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be special shorthand R or C");
        }
        char c = name.charAt(0);
        boolean characterIsValid = Character.isLetter(c) || "_\\".indexOf(c) != -1;
        if (!characterIsValid) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': first character must be underscore or a letter");
        }
        for (char ch : name.toCharArray()) {
            boolean characterIsValid2 = Character.isLetterOrDigit(ch) || "_.\\".indexOf(ch) != -1;
            if (!characterIsValid2) {
                throw new IllegalArgumentException("Invalid name: '" + name + "': name must be letter, digit, period, or underscore");
            }
        }
        if (name.matches("[A-Za-z]+\\d+")) {
            String col = name.replaceAll("\\d", "");
            String row = name.replaceAll("[A-Za-z]", "");
            if (CellReference.cellReferenceIsWithinRange(col, row, SpreadsheetVersion.EXCEL97)) {
                throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be $A$1-style cell reference");
            }
        }
        if (name.matches("[Rr]\\d+[Cc]\\d+")) {
            throw new IllegalArgumentException("Invalid name: '" + name + "': cannot be R1C1-style cell reference");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public void setRefersToFormula(String formulaText) {
        Ptg[] ptgs = HSSFFormulaParser.parse(formulaText, this._book, FormulaType.NAMEDRANGE, getSheetIndex());
        this._definedNameRec.setNameDefinition(ptgs);
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public String getRefersToFormula() {
        if (this._definedNameRec.isFunctionName()) {
            throw new IllegalStateException("Only applicable to named ranges");
        }
        Ptg[] ptgs = this._definedNameRec.getNameDefinition();
        if (ptgs.length < 1) {
            return null;
        }
        return HSSFFormulaParser.toFormulaString(this._book, ptgs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNameDefinition(Ptg[] ptgs) {
        this._definedNameRec.setNameDefinition(ptgs);
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public boolean isDeleted() {
        Ptg[] ptgs = this._definedNameRec.getNameDefinition();
        return Ptg.doesFormulaReferToDeletedCell(ptgs);
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public boolean isFunctionName() {
        return this._definedNameRec.isFunctionName();
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public boolean isHidden() {
        return this._definedNameRec.isHiddenName();
    }

    public String toString() {
        return getClass().getName() + " [" + this._definedNameRec.getNameText() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public void setSheetIndex(int index) {
        int lastSheetIx = this._book.getNumberOfSheets() - 1;
        if (index < -1 || index > lastSheetIx) {
            throw new IllegalArgumentException("Sheet index (" + index + ") is out of range" + (lastSheetIx == -1 ? "" : " (0.." + lastSheetIx + ")"));
        }
        this._definedNameRec.setSheetNumber(index + 1);
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public int getSheetIndex() {
        return this._definedNameRec.getSheetNumber() - 1;
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public String getComment() {
        if (this._commentRec != null && this._commentRec.getCommentText() != null && this._commentRec.getCommentText().length() > 0) {
            return this._commentRec.getCommentText();
        }
        return this._definedNameRec.getDescriptionText();
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public void setComment(String comment) {
        this._definedNameRec.setDescriptionText(comment);
        if (this._commentRec != null) {
            this._commentRec.setCommentText(comment);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Name
    public void setFunction(boolean value) {
        this._definedNameRec.setFunction(value);
    }
}
