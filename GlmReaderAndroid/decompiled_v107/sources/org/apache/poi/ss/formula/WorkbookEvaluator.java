package org.apache.poi.ss.formula;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Supplier;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.atp.AnalysisToolPak;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayMode;
import org.apache.poi.ss.formula.functions.Choose;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.UnknownPtg;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public final class WorkbookEvaluator {
    private EvaluationCache _cache;
    private CollaboratingWorkbooksEnvironment _collaboratingWorkbookEnvironment;
    private final IEvaluationListener _evaluationListener;
    private boolean _ignoreMissingWorkbooks;
    private final Map<String, Integer> _sheetIndexesByName;
    private final Map<EvaluationSheet, Integer> _sheetIndexesBySheet;
    private final IStabilityClassifier _stabilityClassifier;
    private final AggregatingUDFFinder _udfFinder;
    private final EvaluationWorkbook _workbook;
    private int _workbookIx;
    private boolean dbgEvaluationOutputForNextEval;
    private int dbgEvaluationOutputIndent;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) WorkbookEvaluator.class);
    private static final Logger EVAL_LOG = PoiLogManager.getLogger("POI.FormulaEval");

    public WorkbookEvaluator(EvaluationWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this(workbook, null, stabilityClassifier, udfFinder);
    }

    WorkbookEvaluator(EvaluationWorkbook workbook, IEvaluationListener evaluationListener, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this.dbgEvaluationOutputIndent = -1;
        this._workbook = workbook;
        this._evaluationListener = evaluationListener;
        this._cache = new EvaluationCache(evaluationListener);
        this._sheetIndexesBySheet = new IdentityHashMap();
        this._sheetIndexesByName = new IdentityHashMap();
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._workbookIx = 0;
        this._stabilityClassifier = stabilityClassifier;
        AggregatingUDFFinder defaultToolkit = workbook == null ? null : (AggregatingUDFFinder) workbook.getUDFFinder();
        if (defaultToolkit != null && udfFinder != null) {
            defaultToolkit.add(udfFinder);
        }
        this._udfFinder = defaultToolkit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSheetName(int sheetIndex) {
        return this._workbook.getSheetName(sheetIndex);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EvaluationSheet getSheet(int sheetIndex) {
        return this._workbook.getSheet(sheetIndex);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EvaluationWorkbook getWorkbook() {
        return this._workbook;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EvaluationName getName(String name, int sheetIndex) {
        return this._workbook.getName(name, sheetIndex);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void attachToEnvironment(CollaboratingWorkbooksEnvironment collaboratingWorkbooksEnvironment, EvaluationCache cache, int workbookIx) {
        this._collaboratingWorkbookEnvironment = collaboratingWorkbooksEnvironment;
        this._cache = cache;
        this._workbookIx = workbookIx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CollaboratingWorkbooksEnvironment getEnvironment() {
        return this._collaboratingWorkbookEnvironment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void detachFromEnvironment() {
        this._collaboratingWorkbookEnvironment = CollaboratingWorkbooksEnvironment.EMPTY;
        this._cache = new EvaluationCache(this._evaluationListener);
        this._workbookIx = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WorkbookEvaluator getOtherWorkbookEvaluator(String workbookName) throws CollaboratingWorkbooksEnvironment.WorkbookNotFoundException {
        return this._collaboratingWorkbookEnvironment.getWorkbookEvaluator(workbookName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IEvaluationListener getEvaluationListener() {
        return this._evaluationListener;
    }

    public void clearAllCachedResultValues() {
        this._cache.clear();
        this._sheetIndexesBySheet.clear();
        this._workbook.clearAllCachedResultValues();
    }

    public void notifyUpdateCell(EvaluationCell cell) {
        int sheetIndex = getSheetIndex(cell.getSheet());
        this._cache.notifyUpdateCell(this._workbookIx, sheetIndex, cell);
    }

    public void notifyDeleteCell(EvaluationCell cell) {
        int sheetIndex = getSheetIndex(cell.getSheet());
        this._cache.notifyDeleteCell(this._workbookIx, sheetIndex, cell);
    }

    private int getSheetIndex(EvaluationSheet sheet) {
        Integer result = this._sheetIndexesBySheet.get(sheet);
        if (result == null) {
            int sheetIndex = this._workbook.getSheetIndex(sheet);
            if (sheetIndex < 0) {
                throw new IllegalStateException("Specified sheet from a different book");
            }
            result = Integer.valueOf(sheetIndex);
            this._sheetIndexesBySheet.put(sheet, result);
        }
        return result.intValue();
    }

    public ValueEval evaluate(EvaluationCell srcCell) {
        int sheetIndex = getSheetIndex(srcCell.getSheet());
        return evaluateAny(srcCell, sheetIndex, srcCell.getRowIndex(), srcCell.getColumnIndex(), new EvaluationTracker(this._cache));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSheetIndex(String sheetName) {
        Integer result = this._sheetIndexesByName.get(sheetName);
        if (result == null) {
            int sheetIndex = this._workbook.getSheetIndex(sheetName);
            if (sheetIndex < 0) {
                return -1;
            }
            result = Integer.valueOf(sheetIndex);
            this._sheetIndexesByName.put(sheetName, result);
        }
        return result.intValue();
    }

    int getSheetIndexByExternIndex(int externSheetIndex) {
        return this._workbook.convertFromExternSheetIndex(externSheetIndex);
    }

    private ValueEval evaluateAny(EvaluationCell srcCell, final int sheetIndex, final int rowIndex, final int columnIndex, EvaluationTracker tracker) {
        int i;
        ValueEval result;
        int i2;
        int i3 = rowIndex;
        int i4 = columnIndex;
        boolean shouldCellDependencyBeRecorded = this._stabilityClassifier == null || !this._stabilityClassifier.isCellFinal(sheetIndex, i3, i4);
        if (srcCell == null) {
            i = sheetIndex;
        } else {
            if (srcCell.getCellType() == CellType.FORMULA) {
                FormulaCellCacheEntry cce = this._cache.getOrCreateFormulaCellEntry(srcCell);
                if (shouldCellDependencyBeRecorded || cce.isInputSensitive()) {
                    tracker.acceptFormulaDependency(cce);
                }
                IEvaluationListener evalListener = this._evaluationListener;
                if (cce.getValue() == null) {
                    try {
                        try {
                            if (!tracker.startEvaluate(cce)) {
                                return ErrorEval.CIRCULAR_REF_ERROR;
                            }
                            try {
                                try {
                                } catch (NotImplementedException e) {
                                    e = e;
                                    i2 = sheetIndex;
                                }
                            } catch (RuntimeException re) {
                                if (!(re.getCause() instanceof CollaboratingWorkbooksEnvironment.WorkbookNotFoundException) || !this._ignoreMissingWorkbooks) {
                                    throw re;
                                }
                                LOG.atInfo().log("{} - Continuing with cached value!", re.getCause().getMessage());
                                switch (srcCell.getCachedFormulaResultType()) {
                                    case NUMERIC:
                                        result = new NumberEval(srcCell.getNumericCellValue());
                                        break;
                                    case STRING:
                                        result = new StringEval(srcCell.getStringCellValue());
                                        break;
                                    case BLANK:
                                        result = BlankEval.instance;
                                        break;
                                    case BOOLEAN:
                                        result = BoolEval.valueOf(srcCell.getBooleanCellValue());
                                        break;
                                    case ERROR:
                                        result = ErrorEval.valueOf(srcCell.getErrorCellValue());
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected cell type '" + srcCell.getCellType() + "' found!");
                                }
                            }
                            try {
                                Ptg[] ptgs = this._workbook.getFormulaTokens(srcCell);
                                OperationEvaluationContext ec = new OperationEvaluationContext(this, this._workbook, sheetIndex, i3, i4, tracker);
                                if (evalListener == null) {
                                    result = evaluateFormula(ec, ptgs);
                                } else {
                                    evalListener.onStartEvaluate(srcCell, cce);
                                    result = evaluateFormula(ec, ptgs);
                                    evalListener.onEndEvaluate(cce, result);
                                }
                                tracker.updateCacheResult(result);
                                tracker.endEvaluate(cce);
                                final ValueEval result2 = result;
                                LOG.atDebug().log(new Supplier() { // from class: org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda0
                                    @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                                    public final Object get() {
                                        return WorkbookEvaluator.this.m2521lambda$evaluateAny$0$orgapachepoissformulaWorkbookEvaluator(sheetIndex, rowIndex, columnIndex, result2);
                                    }
                                });
                                return result2;
                            } catch (NotImplementedException e2) {
                                e = e2;
                                i2 = sheetIndex;
                                i3 = rowIndex;
                                i4 = columnIndex;
                                throw addExceptionInfo(e, i2, i3, i4);
                            }
                        } catch (Throwable th) {
                            e = th;
                            tracker.endEvaluate(cce);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        tracker.endEvaluate(cce);
                        throw e;
                    }
                }
                if (evalListener != null) {
                    evalListener.onCacheHit(sheetIndex, i3, i4, cce.getValue());
                }
                return cce.getValue();
            }
            i = sheetIndex;
        }
        ValueEval result3 = getValueFromNonFormulaCell(srcCell);
        if (shouldCellDependencyBeRecorded) {
            tracker.acceptPlainValueDependency(this._workbook, this._workbookIx, i, i3, i4, result3);
        }
        return result3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateAny$0$org-apache-poi-ss-formula-WorkbookEvaluator, reason: not valid java name */
    public /* synthetic */ Message m2521lambda$evaluateAny$0$orgapachepoissformulaWorkbookEvaluator(int sheetIndex, int rowIndex, int columnIndex, ValueEval resultForLogging) {
        String sheetName = getSheetName(sheetIndex);
        CellReference cr = new CellReference(rowIndex, columnIndex);
        return new SimpleMessage("Evaluated " + sheetName + "!" + cr.formatAsString() + " to " + resultForLogging);
    }

    private NotImplementedException addExceptionInfo(NotImplementedException inner, int sheetIndex, int rowIndex, int columnIndex) {
        Exception e;
        String sheetName;
        try {
            sheetName = this._workbook.getSheetName(sheetIndex);
        } catch (Exception e2) {
            e = e2;
        }
        try {
            CellReference cr = new CellReference(sheetName, rowIndex, columnIndex, false, false);
            String msg = "Error evaluating cell " + cr.formatAsString();
            return new NotImplementedException(msg, inner);
        } catch (Exception e3) {
            e = e3;
            LOG.atError().withThrowable(e).log("Can't add exception info");
            return inner;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ValueEval getValueFromNonFormulaCell(EvaluationCell cell) {
        if (cell == null) {
            return BlankEval.instance;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                return new NumberEval(cell.getNumericCellValue());
            case STRING:
                return new StringEval(cell.getStringCellValue());
            case BLANK:
                return BlankEval.instance;
            case BOOLEAN:
                return BoolEval.valueOf(cell.getBooleanCellValue());
            case ERROR:
                return ErrorEval.valueOf(cell.getErrorCellValue());
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    @Internal
    ValueEval evaluateFormula(final OperationEvaluationContext ec, Ptg[] ptgs) {
        String dbgIndentStr;
        ValueEval result;
        EvaluationSheet evalSheet;
        boolean z;
        ValueEval opResult;
        int dist;
        int switchIndex;
        final Ptg[] ptgArr = ptgs;
        if (this.dbgEvaluationOutputForNextEval) {
            this.dbgEvaluationOutputIndent = 1;
            this.dbgEvaluationOutputForNextEval = true;
        }
        if (this.dbgEvaluationOutputIndent <= 0) {
            dbgIndentStr = "";
        } else {
            final String dbgIndentStr2 = "                                                                                                    ".substring(0, Math.min("                                                                                                    ".length(), this.dbgEvaluationOutputIndent * 2));
            EVAL_LOG.atWarn().log(new Supplier() { // from class: org.apache.poi.ss.formula.WorkbookEvaluator$$ExternalSyntheticLambda1
                @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
                public final Object get() {
                    return WorkbookEvaluator.lambda$evaluateFormula$1(dbgIndentStr2, ec, ptgArr);
                }
            });
            this.dbgEvaluationOutputIndent++;
            dbgIndentStr = dbgIndentStr2;
        }
        EvaluationSheet evalSheet2 = ec.getWorkbook().getSheet(ec.getSheetIndex());
        EvaluationCell evalCell = evalSheet2.getCell(ec.getRowIndex(), ec.getColumnIndex());
        Stack<ValueEval> stack = new Stack<>();
        int iSize = ptgArr.length;
        int i = 0;
        while (i < iSize) {
            Ptg ptg = ptgArr[i];
            if (this.dbgEvaluationOutputIndent > 0) {
                EVAL_LOG.atInfo().log("{}  * ptg {}: {}, stack: {}", dbgIndentStr, Unbox.box(i), ptg, stack);
            }
            if (!(ptg instanceof AttrPtg)) {
                evalSheet = evalSheet2;
            } else {
                AttrPtg attrPtg = (AttrPtg) ptg;
                if (attrPtg.isSum()) {
                    ptg = FuncVarPtg.SUM;
                }
                if (attrPtg.isOptimizedChoose()) {
                    ValueEval arg0 = stack.pop();
                    int[] jumpTable = attrPtg.getJumpTable();
                    int nChoices = jumpTable.length;
                    try {
                        switchIndex = Choose.evaluateFirstArg(arg0, ec.getRowIndex(), ec.getColumnIndex());
                    } catch (EvaluationException e) {
                        stack.push(e.getErrorEval());
                        dist = attrPtg.getChooseFuncOffset() + 4;
                    }
                    if (switchIndex >= 1 && switchIndex <= nChoices) {
                        dist = jumpTable[switchIndex - 1];
                        i += countTokensToBeSkipped(ptgArr, i, dist - ((nChoices * 2) + 2));
                        evalSheet = evalSheet2;
                        z = false;
                    }
                    stack.push(ErrorEval.VALUE_INVALID);
                    dist = attrPtg.getChooseFuncOffset() + 4;
                    i += countTokensToBeSkipped(ptgArr, i, dist - ((nChoices * 2) + 2));
                    evalSheet = evalSheet2;
                    z = false;
                } else if (attrPtg.isOptimizedIf()) {
                    if (evalCell.isPartOfArrayFormulaGroup()) {
                        evalSheet = evalSheet2;
                        z = false;
                    } else {
                        ValueEval arg02 = stack.pop();
                        try {
                            boolean evaluatedPredicate = IfFunc.evaluateFirstArg(arg02, ec.getRowIndex(), ec.getColumnIndex());
                            if (evaluatedPredicate) {
                                evalSheet = evalSheet2;
                            } else {
                                int dist2 = attrPtg.getData();
                                i += countTokensToBeSkipped(ptgArr, i, dist2);
                                Ptg nextPtg = ptgArr[i + 1];
                                if (!(ptgArr[i] instanceof AttrPtg) || !(nextPtg instanceof FuncVarPtg)) {
                                    evalSheet = evalSheet2;
                                } else {
                                    evalSheet = evalSheet2;
                                    if (((FuncVarPtg) nextPtg).getFunctionIndex() == 1) {
                                        stack.push(arg02);
                                        stack.push(BoolEval.FALSE);
                                    }
                                }
                            }
                            z = false;
                        } catch (EvaluationException e2) {
                            evalSheet = evalSheet2;
                            stack.push(e2.getErrorEval());
                            int dist3 = attrPtg.getData();
                            int i2 = i + countTokensToBeSkipped(ptgArr, i, dist3);
                            int dist4 = ((AttrPtg) ptgArr[i2]).getData() + 1;
                            i = i2 + countTokensToBeSkipped(ptgArr, i2, dist4);
                            z = false;
                        }
                    }
                } else {
                    evalSheet = evalSheet2;
                    if (attrPtg.isSkip() && !evalCell.isPartOfArrayFormulaGroup()) {
                        int dist5 = attrPtg.getData() + 1;
                        i += countTokensToBeSkipped(ptgArr, i, dist5);
                        if (stack.peek() == MissingArgEval.instance) {
                            stack.pop();
                            stack.push(BlankEval.instance);
                        }
                        z = false;
                    }
                }
                i++;
                evalSheet2 = evalSheet;
                ptgArr = ptgs;
            }
            if (ptg instanceof ControlPtg) {
                z = false;
            } else if (ptg instanceof MemFuncPtg) {
                z = false;
            } else if (ptg instanceof MemAreaPtg) {
                z = false;
            } else if (ptg instanceof MemErrPtg) {
                z = false;
            } else if (ptg instanceof UnionPtg) {
                ValueEval v2 = stack.pop();
                ValueEval v1 = stack.pop();
                stack.push(new RefListEval(v1, v2));
                z = false;
            } else {
                if (ptg instanceof OperationPtg) {
                    OperationPtg optg = (OperationPtg) ptg;
                    int numops = optg.getNumberOfOperands();
                    ValueEval[] ops = new ValueEval[numops];
                    boolean areaArg = false;
                    for (int j = numops - 1; j >= 0; j--) {
                        ValueEval p = stack.pop();
                        ops[j] = p;
                        if (p instanceof AreaEval) {
                            areaArg = true;
                        }
                    }
                    boolean arrayMode = false;
                    if (areaArg) {
                        int ii = i;
                        while (true) {
                            if (ii >= iSize) {
                                break;
                            }
                            if (!(ptgArr[ii] instanceof FuncVarPtg)) {
                                ii++;
                                ptgArr = ptgs;
                            } else {
                                FuncVarPtg f = (FuncVarPtg) ptgArr[ii];
                                try {
                                    Function func = FunctionEval.getBasicFunction(f.getFunctionIndex());
                                    if (func instanceof ArrayMode) {
                                        arrayMode = true;
                                    }
                                } catch (NotImplementedException e3) {
                                }
                            }
                        }
                    }
                    ec.setArrayMode(arrayMode);
                    opResult = OperationEvaluatorFactory.evaluate(optg, ops, ec);
                    z = false;
                    ec.setArrayMode(false);
                } else {
                    z = false;
                    opResult = getEvalForPtg(ptg, ec);
                }
                if (opResult == null) {
                    throw new IllegalStateException("Evaluation result must not be null");
                }
                stack.push(opResult);
                if (this.dbgEvaluationOutputIndent > 0) {
                    EVAL_LOG.atInfo().log("{}    = {}", dbgIndentStr, opResult);
                }
            }
            i++;
            evalSheet2 = evalSheet;
            ptgArr = ptgs;
        }
        ValueEval value = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalStateException("evaluation stack not empty");
        }
        if (ec.isSingleValue()) {
            result = dereferenceResult(value, ec);
        } else {
            result = value;
        }
        if (this.dbgEvaluationOutputIndent > 0) {
            EVAL_LOG.atInfo().log("{}finished eval of {}: {}", dbgIndentStr, new CellReference(ec.getRowIndex(), ec.getColumnIndex()).formatAsString(), result);
            this.dbgEvaluationOutputIndent--;
            if (this.dbgEvaluationOutputIndent == 1) {
                this.dbgEvaluationOutputIndent = -1;
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Message lambda$evaluateFormula$1(String finalDbgIndentStr, OperationEvaluationContext ec, Ptg[] ptgs) {
        String message = finalDbgIndentStr + "- evaluateFormula('" + ec.getRefEvaluatorForCurrentSheet().getSheetNameRange() + "'/" + new CellReference(ec.getRowIndex(), ec.getColumnIndex()).formatAsString() + "): " + Arrays.toString(ptgs).replace("\\Qorg.apache.poi.ss.formula.ptg.\\E", "");
        return new SimpleMessage(message);
    }

    private static int countTokensToBeSkipped(Ptg[] ptgs, int startIndex, int distInBytes) {
        int remBytes = distInBytes;
        int index = startIndex;
        while (remBytes != 0) {
            index++;
            if (index >= ptgs.length) {
                throw new IllegalStateException("Skip distance too far (ran out of formula tokens).");
            }
            remBytes -= ptgs[index].getSize();
            if (remBytes < 0) {
                throw new IllegalStateException("Bad skip distance (wrong token size calculation).");
            }
        }
        return index - startIndex;
    }

    private static ValueEval dereferenceResult(ValueEval evaluationResult, OperationEvaluationContext ec) {
        ValueEval value;
        if (ec == null) {
            throw new IllegalArgumentException("OperationEvaluationContext ec is null");
        }
        if (ec.getWorkbook() == null) {
            throw new IllegalArgumentException("OperationEvaluationContext ec.getWorkbook() is null");
        }
        EvaluationSheet evalSheet = ec.getWorkbook().getSheet(ec.getSheetIndex());
        EvaluationCell evalCell = evalSheet.getCell(ec.getRowIndex(), ec.getColumnIndex());
        if (evalCell != null && evalCell.isPartOfArrayFormulaGroup() && (evaluationResult instanceof AreaEval)) {
            value = OperandResolver.getElementFromArray((AreaEval) evaluationResult, evalCell);
        } else {
            value = dereferenceResult(evaluationResult, ec.getRowIndex(), ec.getColumnIndex());
        }
        if (value == BlankEval.instance) {
            return NumberEval.ZERO;
        }
        return value;
    }

    public static ValueEval dereferenceResult(ValueEval evaluationResult, int srcRowNum, int srcColNum) {
        try {
            ValueEval value = OperandResolver.getSingleValue(evaluationResult, srcRowNum, srcColNum);
            if (value == BlankEval.instance) {
                return NumberEval.ZERO;
            }
            return value;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private ValueEval getEvalForPtg(Ptg ptg, OperationEvaluationContext ec) {
        if (ptg instanceof NamePtg) {
            NamePtg namePtg = (NamePtg) ptg;
            EvaluationName nameRecord = this._workbook.getName(namePtg);
            return getEvalForNameRecord(nameRecord, ec);
        }
        if (ptg instanceof NameXPtg) {
            return processNameEval(ec.getNameXEval((NameXPtg) ptg), ec);
        }
        if (ptg instanceof NameXPxg) {
            return processNameEval(ec.getNameXEval((NameXPxg) ptg), ec);
        }
        if (ptg instanceof IntPtg) {
            return new NumberEval(((IntPtg) ptg).getValue());
        }
        if (ptg instanceof NumberPtg) {
            return new NumberEval(((NumberPtg) ptg).getValue());
        }
        if (ptg instanceof StringPtg) {
            return new StringEval(((StringPtg) ptg).getValue());
        }
        if (ptg instanceof BoolPtg) {
            return BoolEval.valueOf(((BoolPtg) ptg).getValue());
        }
        if (ptg instanceof ErrPtg) {
            return ErrorEval.valueOf(((ErrPtg) ptg).getErrorCode());
        }
        if (ptg instanceof MissingArgPtg) {
            return MissingArgEval.instance;
        }
        if (!(ptg instanceof AreaErrPtg) && !(ptg instanceof RefErrorPtg) && !(ptg instanceof DeletedArea3DPtg)) {
            if (!(ptg instanceof DeletedRef3DPtg)) {
                if (ptg instanceof Ref3DPtg) {
                    return ec.getRef3DEval((Ref3DPtg) ptg);
                }
                if (ptg instanceof Ref3DPxg) {
                    return ec.getRef3DEval((Ref3DPxg) ptg);
                }
                if (ptg instanceof Area3DPtg) {
                    return ec.getArea3DEval((Area3DPtg) ptg);
                }
                if (ptg instanceof Area3DPxg) {
                    return ec.getArea3DEval((Area3DPxg) ptg);
                }
                if (ptg instanceof RefPtg) {
                    RefPtg rptg = (RefPtg) ptg;
                    return ec.getRefEval(rptg.getRow(), rptg.getColumn());
                }
                if (ptg instanceof AreaPtg) {
                    AreaPtg aptg = (AreaPtg) ptg;
                    return ec.getAreaEval(aptg.getFirstRow(), aptg.getFirstColumn(), aptg.getLastRow(), aptg.getLastColumn());
                }
                if (ptg instanceof ArrayPtg) {
                    return ec.getAreaValueEval(0, 0, r0.getRowCount() - 1, r0.getColumnCount() - 1, ((ArrayPtg) ptg).getTokenArrayValues());
                }
                if (ptg instanceof UnknownPtg) {
                    throw new IllegalStateException("UnknownPtg not allowed");
                }
                if (ptg instanceof ExpPtg) {
                    throw new IllegalStateException("ExpPtg currently not supported");
                }
                throw new IllegalStateException("Unexpected ptg class (" + ptg.getClass().getName() + ")");
            }
        }
        return ErrorEval.REF_INVALID;
    }

    private ValueEval processNameEval(ValueEval eval, OperationEvaluationContext ec) {
        if (eval instanceof ExternalNameEval) {
            EvaluationName name = ((ExternalNameEval) eval).getName();
            return getEvalForNameRecord(name, ec);
        }
        return eval;
    }

    private ValueEval getEvalForNameRecord(EvaluationName nameRecord, OperationEvaluationContext ec) {
        if (nameRecord.isFunctionName()) {
            return new FunctionNameEval(nameRecord.getNameText());
        }
        if (nameRecord.hasFormula()) {
            return evaluateNameFormula(nameRecord.getNameDefinition(), ec);
        }
        throw new IllegalStateException("Don't know how to evaluate name '" + nameRecord.getNameText() + "'");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValueEval evaluateNameFormula(Ptg[] ptgs, OperationEvaluationContext ec) {
        if (ptgs.length == 1 && !(ptgs[0] instanceof FuncVarPtg)) {
            return getEvalForPtg(ptgs[0], ec);
        }
        OperationEvaluationContext anyValueContext = new OperationEvaluationContext(this, ec.getWorkbook(), ec.getSheetIndex(), ec.getRowIndex(), ec.getColumnIndex(), new EvaluationTracker(this._cache), false);
        return evaluateFormula(anyValueContext, ptgs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValueEval evaluateReference(EvaluationSheet sheet, int sheetIndex, int rowIndex, int columnIndex, EvaluationTracker tracker) {
        EvaluationCell cell = sheet.getCell(rowIndex, columnIndex);
        return evaluateAny(cell, sheetIndex, rowIndex, columnIndex, tracker);
    }

    public FreeRefFunction findUserDefinedFunction(String functionName) {
        return this._udfFinder.findFunction(functionName);
    }

    public ValueEval evaluate(String formula, CellReference ref) {
        int sheetIndex;
        String sheetName = ref == null ? null : ref.getSheetName();
        if (sheetName == null) {
            sheetIndex = -1;
        } else {
            sheetIndex = getWorkbook().getSheetIndex(sheetName);
        }
        int rowIndex = ref == null ? -1 : ref.getRow();
        short colIndex = ref != null ? ref.getCol() : (short) -1;
        OperationEvaluationContext ec = new OperationEvaluationContext(this, getWorkbook(), sheetIndex, rowIndex, colIndex, new EvaluationTracker(this._cache));
        Ptg[] ptgs = FormulaParser.parse(formula, (FormulaParsingWorkbook) getWorkbook(), FormulaType.CELL, sheetIndex, rowIndex);
        return evaluateNameFormula(ptgs, ec);
    }

    public ValueEval evaluate(String formula, CellReference target, CellRangeAddressBase region) {
        return evaluate(formula, target, region, FormulaType.CELL);
    }

    public ValueEval evaluateList(String formula, CellReference target, CellRangeAddressBase region) {
        return evaluate(formula, target, region, FormulaType.DATAVALIDATION_LIST);
    }

    private ValueEval evaluate(String formula, CellReference target, CellRangeAddressBase region, FormulaType formulaType) {
        String sheetName = target == null ? null : target.getSheetName();
        if (sheetName == null) {
            throw new IllegalArgumentException("Sheet name is required");
        }
        int sheetIndex = getWorkbook().getSheetIndex(sheetName);
        Ptg[] ptgs = FormulaParser.parse(formula, (FormulaParsingWorkbook) getWorkbook(), formulaType, sheetIndex, target.getRow());
        adjustRegionRelativeReference(ptgs, target, region);
        OperationEvaluationContext ec = new OperationEvaluationContext(this, getWorkbook(), sheetIndex, target.getRow(), target.getCol(), new EvaluationTracker(this._cache), formulaType.isSingleValue());
        return evaluateNameFormula(ptgs, ec);
    }

    private boolean adjustRegionRelativeReference(Ptg[] ptgs, CellReference target, CellRangeAddressBase region) {
        int deltaRow = target.getRow() - region.getFirstRow();
        int deltaColumn = target.getCol() - region.getFirstColumn();
        boolean shifted = false;
        for (Ptg ptg : ptgs) {
            if (ptg instanceof RefPtgBase) {
                RefPtgBase ref = (RefPtgBase) ptg;
                SpreadsheetVersion version = this._workbook.getSpreadsheetVersion();
                if (ref.isRowRelative() && deltaRow > 0) {
                    int rowIndex = ref.getRow() + deltaRow;
                    if (rowIndex > version.getMaxRows()) {
                        throw new IndexOutOfBoundsException(version.name() + " files can only have " + version.getMaxRows() + " rows, but row " + rowIndex + " was requested.");
                    }
                    ref.setRow(rowIndex);
                    shifted = true;
                }
                if (ref.isColRelative() && deltaColumn > 0) {
                    int colIndex = ref.getColumn() + deltaColumn;
                    if (colIndex > version.getMaxColumns()) {
                        throw new IndexOutOfBoundsException(version.name() + " files can only have " + version.getMaxColumns() + " columns, but column " + colIndex + " was requested.");
                    }
                    ref.setColumn(colIndex);
                    shifted = true;
                }
            }
        }
        return shifted;
    }

    public void setIgnoreMissingWorkbooks(boolean ignore) {
        this._ignoreMissingWorkbooks = ignore;
    }

    public boolean isIgnoreMissingWorkbooks() {
        return this._ignoreMissingWorkbooks;
    }

    public static Collection<String> getSupportedFunctionNames() {
        Collection<String> lst = new TreeSet<>();
        lst.addAll(FunctionEval.getSupportedFunctionNames());
        lst.addAll(AnalysisToolPak.getSupportedFunctionNames());
        return Collections.unmodifiableCollection(lst);
    }

    public static Collection<String> getNotSupportedFunctionNames() {
        Collection<String> lst = new TreeSet<>();
        lst.addAll(FunctionEval.getNotSupportedFunctionNames());
        lst.addAll(AnalysisToolPak.getNotSupportedFunctionNames());
        return Collections.unmodifiableCollection(lst);
    }

    public static void registerFunction(String name, FreeRefFunction func) {
        AnalysisToolPak.registerFunction(name, func);
    }

    public static void registerFunction(String name, Function func) {
        FunctionEval.registerFunction(name, func);
    }

    public void setDebugEvaluationOutputForNextEval(boolean value) {
        this.dbgEvaluationOutputForNextEval = value;
    }

    public boolean isDebugEvaluationOutputForNextEval() {
        return this.dbgEvaluationOutputForNextEval;
    }
}
