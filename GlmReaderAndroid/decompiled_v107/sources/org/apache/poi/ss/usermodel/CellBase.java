package org.apache.poi.ss.usermodel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

/* loaded from: classes10.dex */
public abstract class CellBase implements Cell {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    protected abstract SpreadsheetVersion getSpreadsheetVersion();

    protected abstract void removeFormulaImpl();

    protected abstract void setCellFormulaImpl(String str);

    protected abstract void setCellTypeImpl(CellType cellType);

    protected abstract void setCellValueImpl(double d);

    protected abstract void setCellValueImpl(String str);

    protected abstract void setCellValueImpl(LocalDateTime localDateTime);

    protected abstract void setCellValueImpl(Calendar calendar);

    protected abstract void setCellValueImpl(Date date);

    protected abstract void setCellValueImpl(RichTextString richTextString);

    @Override // org.apache.poi.ss.usermodel.Cell
    public final void setCellType(CellType cellType) {
        if (cellType == null || cellType == CellType._NONE) {
            throw new IllegalArgumentException("cellType shall not be null nor _NONE");
        }
        if (cellType == CellType.FORMULA) {
            if (getCellType() != CellType.FORMULA) {
                throw new IllegalArgumentException("Calling Cell.setCellType(CellType.FORMULA) is illegal. Use setCellFormula(String) directly.");
            }
        } else {
            tryToDeleteArrayFormulaIfSet();
            setCellTypeImpl(cellType);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setBlank() {
        setCellType(CellType.BLANK);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellAddress getAddress() {
        return new CellAddress(this);
    }

    public final void tryToDeleteArrayFormula(String message) {
        if (!isPartOfArrayFormulaGroup()) {
            throw new AssertionError();
        }
        CellRangeAddress arrayFormulaRange = getArrayFormulaRange();
        if (arrayFormulaRange.getNumberOfCells() > 1) {
            if (message == null) {
                message = "Cell " + new CellReference(this).formatAsString() + " is part of a multi-cell array formula. You cannot change part of an array.";
            }
            throw new IllegalStateException(message);
        }
        getRow().getSheet().removeArrayFormula(this);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public final void setCellFormula(String formula) throws FormulaParseException, IllegalStateException {
        tryToDeleteArrayFormulaIfSet();
        if (formula == null) {
            removeFormula();
        } else {
            setCellFormulaImpl(formula);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CellType getValueType() {
        CellType type = getCellType();
        if (type != CellType.FORMULA) {
            return type;
        }
        return getCachedFormulaResultType();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public final void removeFormula() {
        if (getCellType() == CellType.BLANK) {
            return;
        }
        if (isPartOfArrayFormulaGroup()) {
            tryToDeleteArrayFormula(null);
        } else {
            removeFormulaImpl();
        }
    }

    private void tryToDeleteArrayFormulaIfSet() {
        if (isPartOfArrayFormulaGroup()) {
            tryToDeleteArrayFormula(null);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(double value) {
        if (Double.isInfinite(value)) {
            setCellErrorValue(FormulaError.DIV0.getCode());
        } else if (Double.isNaN(value)) {
            setCellErrorValue(FormulaError.NUM.getCode());
        } else {
            setCellValueImpl(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(Date value) {
        if (value == null) {
            setBlank();
        } else {
            setCellValueImpl(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(LocalDateTime value) {
        if (value == null) {
            setBlank();
        } else {
            setCellValueImpl(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(Calendar value) {
        if (value == null) {
            setBlank();
        } else {
            setCellValueImpl(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(String value) {
        if (value == null) {
            setBlank();
        } else {
            checkLength(value);
            setCellValueImpl(value);
        }
    }

    private void checkLength(String value) {
        if (value.length() > getSpreadsheetVersion().getMaxTextLength()) {
            String message = String.format(Locale.ROOT, "The maximum length of cell contents (text) is %d characters", Integer.valueOf(getSpreadsheetVersion().getMaxTextLength()));
            throw new IllegalArgumentException(message);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(RichTextString value) {
        if (value == null || value.getString() == null) {
            setBlank();
        } else {
            checkLength(value.getString());
            setCellValueImpl(value);
        }
    }
}
