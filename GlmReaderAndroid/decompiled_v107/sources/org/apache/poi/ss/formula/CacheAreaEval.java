package org.apache.poi.ss.formula;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public final class CacheAreaEval extends AreaEvalBase {
    private final ValueEval[] _values;

    public CacheAreaEval(AreaI ptg, ValueEval[] values) {
        super(ptg);
        this._values = values;
    }

    public CacheAreaEval(int firstRow, int firstColumn, int lastRow, int lastColumn, ValueEval[] values) {
        super(firstRow, firstColumn, lastRow, lastColumn);
        this._values = values;
    }

    @Override // org.apache.poi.ss.formula.eval.AreaEvalBase, org.apache.poi.ss.formula.eval.AreaEval
    public ValueEval getRelativeValue(int relativeRowIndex, int relativeColumnIndex) {
        return getRelativeValue(-1, relativeRowIndex, relativeColumnIndex);
    }

    @Override // org.apache.poi.ss.formula.eval.AreaEvalBase
    public ValueEval getRelativeValue(int sheetIndex, int relativeRowIndex, int relativeColumnIndex) {
        int oneDimensionalIndex = (getWidth() * relativeRowIndex) + relativeColumnIndex;
        return this._values[oneDimensionalIndex];
    }

    @Override // org.apache.poi.ss.formula.eval.AreaEval
    public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
        ValueEval temp;
        AreaI area = new AreaI.OffsetArea(getFirstRow(), getFirstColumn(), relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);
        int height = (area.getLastRow() - area.getFirstRow()) + 1;
        int width = (area.getLastColumn() - area.getFirstColumn()) + 1;
        ValueEval[] newVals = new ValueEval[height * width];
        int startRow = area.getFirstRow() - getFirstRow();
        int startCol = area.getFirstColumn() - getFirstColumn();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (startRow + j > getLastRow() || startCol + i > getLastColumn()) {
                    temp = BlankEval.instance;
                } else {
                    temp = this._values[((startRow + j) * getWidth()) + startCol + i];
                }
                newVals[(j * width) + i] = temp;
            }
        }
        return new CacheAreaEval(area, newVals);
    }

    @Override // org.apache.poi.ss.formula.TwoDEval
    public TwoDEval getRow(int rowIndex) {
        if (rowIndex >= getHeight()) {
            throw new IllegalArgumentException("Invalid rowIndex " + rowIndex + ".  Allowable range is (0.." + getHeight() + ").");
        }
        int absRowIndex = getFirstRow() + rowIndex;
        ValueEval[] values = new ValueEval[getWidth()];
        for (int i = 0; i < values.length; i++) {
            values[i] = getRelativeValue(rowIndex, i);
        }
        return new CacheAreaEval(absRowIndex, getFirstColumn(), absRowIndex, getLastColumn(), values);
    }

    @Override // org.apache.poi.ss.formula.TwoDEval
    public TwoDEval getColumn(int columnIndex) {
        if (columnIndex >= getWidth()) {
            throw new IllegalArgumentException("Invalid columnIndex " + columnIndex + ".  Allowable range is (0.." + getWidth() + ").");
        }
        int absColIndex = getFirstColumn() + columnIndex;
        ValueEval[] values = new ValueEval[getHeight()];
        for (int i = 0; i < values.length; i++) {
            values[i] = getRelativeValue(i, columnIndex);
        }
        return new CacheAreaEval(getFirstRow(), absColIndex, getLastRow(), absColIndex, values);
    }

    public String toString() {
        CellReference crA = new CellReference(getFirstRow(), getFirstColumn());
        CellReference crB = new CellReference(getLastRow(), getLastColumn());
        return getClass().getName() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + crA.formatAsString() + NameUtil.COLON + crB.formatAsString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
