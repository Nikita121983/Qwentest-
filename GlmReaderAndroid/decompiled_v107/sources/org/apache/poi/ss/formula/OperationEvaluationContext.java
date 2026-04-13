package org.apache.poi.ss.formula;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public final class OperationEvaluationContext {
    public static final FreeRefFunction UDF = UserDefinedFunction.instance;
    private final WorkbookEvaluator _bookEvaluator;
    private final int _columnIndex;
    private boolean _isInArrayContext;
    private final boolean _isSingleValue;
    private final int _rowIndex;
    private final int _sheetIndex;
    private final EvaluationTracker _tracker;
    private final EvaluationWorkbook _workbook;

    public OperationEvaluationContext(WorkbookEvaluator bookEvaluator, EvaluationWorkbook workbook, int sheetIndex, int srcRowNum, int srcColNum, EvaluationTracker tracker) {
        this(bookEvaluator, workbook, sheetIndex, srcRowNum, srcColNum, tracker, true);
    }

    public OperationEvaluationContext(WorkbookEvaluator bookEvaluator, EvaluationWorkbook workbook, int sheetIndex, int srcRowNum, int srcColNum, EvaluationTracker tracker, boolean isSingleValue) {
        this._bookEvaluator = bookEvaluator;
        this._workbook = workbook;
        this._sheetIndex = sheetIndex;
        this._rowIndex = srcRowNum;
        this._columnIndex = srcColNum;
        this._tracker = tracker;
        this._isSingleValue = isSingleValue;
    }

    public boolean isArraymode() {
        return this._isInArrayContext;
    }

    public void setArrayMode(boolean value) {
        this._isInArrayContext = value;
    }

    public EvaluationWorkbook getWorkbook() {
        return this._workbook;
    }

    public int getRowIndex() {
        return this._rowIndex;
    }

    public int getColumnIndex() {
        return this._columnIndex;
    }

    SheetRangeEvaluator createExternSheetRefEvaluator(ExternSheetReferenceToken ptg) {
        return createExternSheetRefEvaluator(ptg.getExternSheetIndex());
    }

    SheetRangeEvaluator createExternSheetRefEvaluator(String firstSheetName, String lastSheetName, int externalWorkbookNumber) {
        EvaluationWorkbook.ExternalSheet externalSheet = this._workbook.getExternalSheet(firstSheetName, lastSheetName, externalWorkbookNumber);
        return createExternSheetRefEvaluator(externalSheet);
    }

    SheetRangeEvaluator createExternSheetRefEvaluator(int externSheetIndex) {
        EvaluationWorkbook.ExternalSheet externalSheet = this._workbook.getExternalSheet(externSheetIndex);
        return createExternSheetRefEvaluator(externalSheet);
    }

    SheetRangeEvaluator createExternSheetRefEvaluator(EvaluationWorkbook.ExternalSheet externalSheet) {
        WorkbookEvaluator targetEvaluator;
        int otherFirstSheetIndex;
        int otherLastSheetIndex = -1;
        if (externalSheet == null || externalSheet.getWorkbookName() == null) {
            targetEvaluator = this._bookEvaluator;
            if (externalSheet == null) {
                otherFirstSheetIndex = 0;
            } else {
                otherFirstSheetIndex = this._workbook.getSheetIndex(externalSheet.getSheetName());
            }
            if (externalSheet instanceof EvaluationWorkbook.ExternalSheetRange) {
                String lastSheetName = ((EvaluationWorkbook.ExternalSheetRange) externalSheet).getLastSheetName();
                otherLastSheetIndex = this._workbook.getSheetIndex(lastSheetName);
            }
        } else {
            String workbookName = externalSheet.getWorkbookName();
            try {
                targetEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(workbookName);
                otherFirstSheetIndex = targetEvaluator.getSheetIndex(externalSheet.getSheetName());
                if (externalSheet instanceof EvaluationWorkbook.ExternalSheetRange) {
                    String lastSheetName2 = ((EvaluationWorkbook.ExternalSheetRange) externalSheet).getLastSheetName();
                    otherLastSheetIndex = targetEvaluator.getSheetIndex(lastSheetName2);
                }
                if (otherFirstSheetIndex < 0) {
                    throw new IllegalStateException("Invalid sheet name '" + externalSheet.getSheetName() + "' in bool '" + workbookName + "'.");
                }
            } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        if (otherLastSheetIndex == -1) {
            otherLastSheetIndex = otherFirstSheetIndex;
        }
        SheetRefEvaluator[] evals = new SheetRefEvaluator[(otherLastSheetIndex - otherFirstSheetIndex) + 1];
        for (int i = 0; i < evals.length; i++) {
            int otherSheetIndex = i + otherFirstSheetIndex;
            evals[i] = new SheetRefEvaluator(targetEvaluator, this._tracker, otherSheetIndex);
        }
        return new SheetRangeEvaluator(otherFirstSheetIndex, otherLastSheetIndex, evals);
    }

    private SheetRefEvaluator createExternSheetRefEvaluator(String workbookName, String sheetName) {
        WorkbookEvaluator targetEvaluator;
        if (workbookName == null) {
            targetEvaluator = this._bookEvaluator;
        } else {
            if (sheetName == null) {
                throw new IllegalArgumentException("sheetName must not be null if workbookName is provided");
            }
            try {
                targetEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(workbookName);
            } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException e) {
                return null;
            }
        }
        int otherSheetIndex = sheetName == null ? this._sheetIndex : targetEvaluator.getSheetIndex(sheetName);
        if (otherSheetIndex < 0) {
            return null;
        }
        return new SheetRefEvaluator(targetEvaluator, this._tracker, otherSheetIndex);
    }

    public SheetRangeEvaluator getRefEvaluatorForCurrentSheet() {
        SheetRefEvaluator sre = new SheetRefEvaluator(this._bookEvaluator, this._tracker, this._sheetIndex);
        return new SheetRangeEvaluator(this._sheetIndex, sre);
    }

    public ValueEval getDynamicReference(String workbookName, String sheetName, String refStrPart1, String refStrPart2, boolean isA1Style) {
        int firstCol;
        int lastCol;
        int lastCol2;
        int firstCol2;
        CellReference cr;
        int absoluteC;
        int absoluteR;
        SheetRefEvaluator se = createExternSheetRefEvaluator(workbookName, sheetName);
        if (se == null) {
            return ErrorEval.REF_INVALID;
        }
        SheetRangeEvaluator sre = new SheetRangeEvaluator(this._sheetIndex, se);
        SpreadsheetVersion ssVersion = this._workbook.getSpreadsheetVersion();
        CellReference.NameType part1refType = isA1Style ? classifyCellReference(refStrPart1, ssVersion) : getR1C1CellType(refStrPart1);
        switch (part1refType) {
            case BAD_CELL_OR_NAMED_RANGE:
                return ErrorEval.REF_INVALID;
            case NAMED_RANGE:
                EvaluationName nm = this._workbook.getName(refStrPart1, this._sheetIndex);
                if (nm == null) {
                    throw new IllegalStateException("Specified name '" + refStrPart1 + "' is not found in the workbook (sheetIndex=" + this._sheetIndex + ").");
                }
                if (!nm.isRange()) {
                    throw new IllegalStateException("Specified name '" + refStrPart1 + "' is not a range as expected.");
                }
                return this._bookEvaluator.evaluateNameFormula(nm.getNameDefinition(), this);
            default:
                if (refStrPart2 == null) {
                    switch (part1refType) {
                        case COLUMN:
                            if (!isA1Style) {
                                try {
                                    String upRef = refStrPart1.toUpperCase(LocaleUtil.getUserLocale());
                                    int cpos = upRef.indexOf(67);
                                    String cval = refStrPart1.substring(cpos + 1).trim();
                                    if (cval.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX) && cval.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX)) {
                                        int relativeC = Integer.parseInt(cval.substring(1, cval.length() - 1).trim());
                                        int absoluteC2 = getColumnIndex() + relativeC;
                                        absoluteC = absoluteC2;
                                    } else if (!cval.isEmpty()) {
                                        absoluteC = Integer.parseInt(cval) - 1;
                                    } else {
                                        return ErrorEval.REF_INVALID;
                                    }
                                    return new LazyAreaEval(0, absoluteC, ssVersion.getLastRowIndex(), absoluteC, sre);
                                } catch (Exception e) {
                                    return ErrorEval.REF_INVALID;
                                }
                            }
                            return ErrorEval.REF_INVALID;
                        case ROW:
                            if (!isA1Style) {
                                try {
                                    String upRef2 = refStrPart1.toUpperCase(LocaleUtil.getUserLocale());
                                    int rpos = upRef2.indexOf(82);
                                    String rval = refStrPart1.substring(rpos + 1).trim();
                                    if (rval.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX) && rval.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX)) {
                                        int relativeR = Integer.parseInt(rval.substring(1, rval.length() - 1).trim());
                                        absoluteR = getRowIndex() + relativeR;
                                    } else if (!rval.isEmpty()) {
                                        absoluteR = Integer.parseInt(rval) - 1;
                                    } else {
                                        return ErrorEval.REF_INVALID;
                                    }
                                    return new LazyAreaEval(absoluteR, 0, absoluteR, ssVersion.getLastColumnIndex(), sre);
                                } catch (Exception e2) {
                                    return ErrorEval.REF_INVALID;
                                }
                            }
                            return ErrorEval.REF_INVALID;
                        case CELL:
                            CellReference cr2 = isA1Style ? new CellReference(refStrPart1) : applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), refStrPart1);
                            return new LazyRefEval(cr2.getRow(), cr2.getCol(), sre);
                        default:
                            throw new IllegalStateException("Unexpected reference classification of '" + refStrPart1 + "'.");
                    }
                }
                CellReference.NameType part2refType = isA1Style ? classifyCellReference(refStrPart2, ssVersion) : getR1C1CellType(refStrPart2);
                switch (part2refType) {
                    case BAD_CELL_OR_NAMED_RANGE:
                        return ErrorEval.REF_INVALID;
                    case NAMED_RANGE:
                        throw new IllegalStateException("Cannot evaluate '" + refStrPart1 + "'. Indirect evaluation of defined names not supported yet");
                    default:
                        if (part2refType != part1refType) {
                            return ErrorEval.REF_INVALID;
                        }
                        switch (part1refType) {
                            case COLUMN:
                                int lastRow = ssVersion.getLastRowIndex();
                                int firstCol3 = parseRowRef(refStrPart1);
                                int lastCol3 = parseRowRef(refStrPart2);
                                firstCol = firstCol3;
                                lastCol = lastCol3;
                                lastCol2 = lastRow;
                                firstCol2 = 0;
                                break;
                            case ROW:
                                int lastCol4 = ssVersion.getLastColumnIndex();
                                int firstRow = parseColRef(refStrPart1);
                                int lastRow2 = parseColRef(refStrPart2);
                                firstCol = 0;
                                lastCol = lastCol4;
                                lastCol2 = lastRow2;
                                firstCol2 = firstRow;
                                break;
                            case CELL:
                                CellReference cr3 = isA1Style ? new CellReference(refStrPart1) : applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), refStrPart1);
                                int firstRow2 = cr3.getRow();
                                int firstCol4 = cr3.getCol();
                                if (isA1Style) {
                                    cr = new CellReference(refStrPart2);
                                } else {
                                    cr = applyR1C1Reference(new CellReference(getRowIndex(), getColumnIndex()), refStrPart2);
                                }
                                int lastRow3 = cr.getRow();
                                int lastCol5 = cr.getCol();
                                firstCol = firstCol4;
                                lastCol = lastCol5;
                                lastCol2 = lastRow3;
                                firstCol2 = firstRow2;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected reference classification of '" + refStrPart1 + "'.");
                        }
                        return new LazyAreaEval(firstCol2, firstCol, lastCol2, lastCol, sre);
                }
        }
    }

    private static int parseRowRef(String refStrPart) {
        return CellReference.convertColStringToIndex(refStrPart);
    }

    private static int parseColRef(String refStrPart) {
        return Integer.parseInt(refStrPart) - 1;
    }

    private static CellReference.NameType classifyCellReference(String str, SpreadsheetVersion ssVersion) {
        int len = str.length();
        if (len < 1) {
            return CellReference.NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return CellReference.classifyCellReference(str, ssVersion);
    }

    public FreeRefFunction findUserDefinedFunction(String functionName) {
        return this._bookEvaluator.findUserDefinedFunction(functionName);
    }

    public ValueEval getRefEval(int rowIndex, int columnIndex) {
        SheetRangeEvaluator sre = getRefEvaluatorForCurrentSheet();
        return new LazyRefEval(rowIndex, columnIndex, sre);
    }

    public ValueEval getRef3DEval(Ref3DPtg rptg) {
        SheetRangeEvaluator sre = createExternSheetRefEvaluator(rptg.getExternSheetIndex());
        return new LazyRefEval(rptg.getRow(), rptg.getColumn(), sre);
    }

    public ValueEval getRef3DEval(Ref3DPxg rptg) {
        SheetRangeEvaluator sre = createExternSheetRefEvaluator(rptg.getSheetName(), rptg.getLastSheetName(), rptg.getExternalWorkbookNumber());
        return new LazyRefEval(rptg.getRow(), rptg.getColumn(), sre);
    }

    public ValueEval getAreaEval(int firstRowIndex, int firstColumnIndex, int lastRowIndex, int lastColumnIndex) {
        SheetRangeEvaluator sre = getRefEvaluatorForCurrentSheet();
        return new LazyAreaEval(firstRowIndex, firstColumnIndex, lastRowIndex, lastColumnIndex, sre);
    }

    public ValueEval getArea3DEval(Area3DPtg aptg) {
        SheetRangeEvaluator sre = createExternSheetRefEvaluator(aptg.getExternSheetIndex());
        return new LazyAreaEval(aptg.getFirstRow(), aptg.getFirstColumn(), aptg.getLastRow(), aptg.getLastColumn(), sre);
    }

    public ValueEval getArea3DEval(Area3DPxg aptg) {
        SheetRangeEvaluator sre = createExternSheetRefEvaluator(aptg.getSheetName(), aptg.getLastSheetName(), aptg.getExternalWorkbookNumber());
        return new LazyAreaEval(aptg.getFirstRow(), aptg.getFirstColumn(), aptg.getLastRow(), aptg.getLastColumn(), sre);
    }

    public ValueEval getAreaValueEval(int firstRowIndex, int firstColumnIndex, int lastRowIndex, int lastColumnIndex, Object[][] tokens) {
        ValueEval[] values = new ValueEval[tokens.length * tokens[0].length];
        int index = 0;
        for (Object[] token : tokens) {
            int idx = 0;
            while (idx < tokens[0].length) {
                values[index] = convertObjectEval(token[idx]);
                idx++;
                index++;
            }
        }
        return new CacheAreaEval(firstRowIndex, firstColumnIndex, lastRowIndex, lastColumnIndex, values);
    }

    private ValueEval convertObjectEval(Object token) {
        if (token == null) {
            throw new IllegalStateException("Array item cannot be null");
        }
        if (token instanceof String) {
            return new StringEval((String) token);
        }
        if (token instanceof Double) {
            return new NumberEval(((Double) token).doubleValue());
        }
        if (token instanceof Boolean) {
            return BoolEval.valueOf(((Boolean) token).booleanValue());
        }
        if (token instanceof ErrorConstant) {
            return ErrorEval.valueOf(((ErrorConstant) token).getErrorCode());
        }
        throw new IllegalArgumentException("Unexpected constant class (" + token.getClass().getName() + ")");
    }

    public ValueEval getNameXEval(NameXPtg nameXPtg) {
        EvaluationWorkbook.ExternalSheet externSheet = this._workbook.getExternalSheet(nameXPtg.getSheetRefIndex());
        if (externSheet == null || externSheet.getWorkbookName() == null) {
            return getLocalNameXEval(nameXPtg);
        }
        String workbookName = externSheet.getWorkbookName();
        EvaluationWorkbook.ExternalName externName = this._workbook.getExternalName(nameXPtg.getSheetRefIndex(), nameXPtg.getNameIndex());
        return getExternalNameXEval(externName, workbookName);
    }

    public ValueEval getNameXEval(NameXPxg nameXPxg) {
        EvaluationWorkbook.ExternalSheet externSheet = this._workbook.getExternalSheet(nameXPxg.getSheetName(), null, nameXPxg.getExternalWorkbookNumber());
        if (externSheet == null || externSheet.getWorkbookName() == null) {
            return getLocalNameXEval(nameXPxg);
        }
        String workbookName = externSheet.getWorkbookName();
        EvaluationWorkbook.ExternalName externName = this._workbook.getExternalName(nameXPxg.getNameName(), nameXPxg.getSheetName(), nameXPxg.getExternalWorkbookNumber());
        return getExternalNameXEval(externName, workbookName);
    }

    private ValueEval getLocalNameXEval(NameXPxg nameXPxg) {
        int sIdx = -1;
        if (nameXPxg.getSheetName() != null) {
            sIdx = this._workbook.getSheetIndex(nameXPxg.getSheetName());
        }
        String name = nameXPxg.getNameName();
        EvaluationName evalName = this._workbook.getName(name, sIdx);
        if (evalName != null) {
            return new ExternalNameEval(evalName);
        }
        return new FunctionNameEval(name);
    }

    private ValueEval getLocalNameXEval(NameXPtg nameXPtg) {
        EvaluationName evalName;
        String name = this._workbook.resolveNameXText(nameXPtg);
        int sheetNameAt = name.indexOf(33);
        if (sheetNameAt <= -1) {
            evalName = this._workbook.getName(name, -1);
        } else {
            String sheetName = name.substring(0, sheetNameAt);
            String nameName = name.substring(sheetNameAt + 1);
            evalName = this._workbook.getName(nameName, this._workbook.getSheetIndex(sheetName));
        }
        if (evalName != null) {
            return new ExternalNameEval(evalName);
        }
        return new FunctionNameEval(name);
    }

    public int getSheetIndex() {
        return this._sheetIndex;
    }

    public boolean isSingleValue() {
        return this._isSingleValue;
    }

    private ValueEval getExternalNameXEval(EvaluationWorkbook.ExternalName externName, String workbookName) {
        try {
            WorkbookEvaluator refWorkbookEvaluator = this._bookEvaluator.getOtherWorkbookEvaluator(workbookName);
            EvaluationName evaluationName = refWorkbookEvaluator.getName(externName.getName(), externName.getIx() - 1);
            if (evaluationName != null && evaluationName.hasFormula()) {
                if (evaluationName.getNameDefinition().length > 1) {
                    throw new IllegalStateException("Complex name formulas not supported yet");
                }
                OperationEvaluationContext refWorkbookContext = new OperationEvaluationContext(refWorkbookEvaluator, refWorkbookEvaluator.getWorkbook(), -1, -1, -1, this._tracker);
                Ptg ptg = evaluationName.getNameDefinition()[0];
                if (ptg instanceof Ref3DPtg) {
                    Ref3DPtg ref3D = (Ref3DPtg) ptg;
                    return refWorkbookContext.getRef3DEval(ref3D);
                }
                if (ptg instanceof Ref3DPxg) {
                    Ref3DPxg ref3D2 = (Ref3DPxg) ptg;
                    return refWorkbookContext.getRef3DEval(ref3D2);
                }
                if (ptg instanceof Area3DPtg) {
                    Area3DPtg area3D = (Area3DPtg) ptg;
                    return refWorkbookContext.getArea3DEval(area3D);
                }
                if (ptg instanceof Area3DPxg) {
                    Area3DPxg area3D2 = (Area3DPxg) ptg;
                    return refWorkbookContext.getArea3DEval(area3D2);
                }
            }
            return ErrorEval.REF_INVALID;
        } catch (CollaboratingWorkbooksEnvironment.WorkbookNotFoundException e) {
            return ErrorEval.REF_INVALID;
        }
    }

    public static CellReference applyR1C1Reference(CellReference anchorReference, String relativeReference) {
        int newR;
        int newC;
        String upRef = relativeReference.toUpperCase(LocaleUtil.getUserLocale());
        int rpos = upRef.indexOf(82);
        int cpos = upRef.indexOf(67);
        if (rpos >= 0 && cpos > rpos) {
            String rval = relativeReference.substring(rpos + 1, cpos).trim();
            String cval = relativeReference.substring(cpos + 1).trim();
            int absoluteR = -1;
            int relativeR = 0;
            if (rval.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX) && rval.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX)) {
                relativeR = Integer.parseInt(rval.substring(1, rval.length() - 1).trim());
            } else if (!rval.isEmpty()) {
                absoluteR = Integer.parseInt(rval);
            }
            int absoluteC = -1;
            int relativeC = 0;
            if (cval.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX) && cval.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX)) {
                relativeC = Integer.parseInt(cval.substring(1, cval.length() - 1).trim());
            } else if (!cval.isEmpty()) {
                absoluteC = Integer.parseInt(cval);
            }
            if (absoluteR >= 0) {
                newR = absoluteR - 1;
            } else {
                int newR2 = anchorReference.getRow();
                newR = newR2 + relativeR;
            }
            if (absoluteC >= 0) {
                newC = absoluteC - 1;
            } else {
                int newC2 = anchorReference.getCol();
                newC = newC2 + relativeC;
            }
            return new CellReference(newR, newC);
        }
        throw new IllegalArgumentException(relativeReference + " is not a valid R1C1 reference");
    }

    private static CellReference.NameType getR1C1CellType(String str) {
        String upRef = str.toUpperCase(LocaleUtil.getUserLocale());
        int rpos = upRef.indexOf(82);
        int cpos = upRef.indexOf(67);
        if (rpos != -1) {
            if (cpos == -1) {
                return CellReference.NameType.ROW;
            }
            return CellReference.NameType.CELL;
        }
        if (cpos == -1) {
            return CellReference.NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return CellReference.NameType.COLUMN;
    }
}
