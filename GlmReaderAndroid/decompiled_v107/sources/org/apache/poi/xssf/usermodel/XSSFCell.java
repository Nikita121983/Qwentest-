package org.apache.poi.xssf.usermodel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SharedFormula;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.ExceptionUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

/* loaded from: classes10.dex */
public final class XSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String FALSE = "FALSE";
    private static final String FALSE_AS_STRING = "0";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFCell.class);
    private static final String TRUE = "TRUE";
    private static final String TRUE_AS_STRING = "1";
    private CTCell _cell;
    private int _cellNum;
    private final XSSFRow _row;
    private final SharedStringsTable _sharedStringSource;
    private final StylesTable _stylesSource;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFCell(XSSFRow row, CTCell cell) {
        this._cell = cell;
        this._row = row;
        String rval = cell.getR();
        if (rval != null) {
            this._cellNum = new CellReference(rval).getCol();
        } else {
            int prevNum = row.getLastCellNum();
            if (prevNum != -1) {
                this._cellNum = row.getCell(prevNum - 1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).getColumnIndex() + 1;
            }
        }
        this._sharedStringSource = row.getSheet().getWorkbook().getSharedStringSource();
        this._stylesSource = row.getSheet().getWorkbook().getStylesSource();
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    public void copyCellFrom(Cell srcCell, CellCopyPolicy policy) {
        CellUtil.copyCell(srcCell, this, policy, null);
    }

    protected SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    protected StylesTable getStylesSource() {
        return this._stylesSource;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFSheet getSheet() {
        return getRow().getSheet();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFRow getRow() {
        return this._row;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public boolean getBooleanCellValue() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return false;
            case BOOLEAN:
                return this._cell.isSetV() && TRUE_AS_STRING.equals(this._cell.getV());
            case FORMULA:
                return this._cell.isSetV() && TRUE_AS_STRING.equals(this._cell.getV());
            default:
                throw typeMismatch(CellType.BOOLEAN, cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(boolean value) {
        this._cell.setT(STCellType.B);
        this._cell.setV(value ? TRUE_AS_STRING : FALSE_AS_STRING);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public double getNumericCellValue() {
        CellType valueType = isFormulaCell() ? getCachedFormulaResultType() : getCellType();
        switch (valueType) {
            case BLANK:
                return 0.0d;
            case BOOLEAN:
            default:
                throw typeMismatch(CellType.NUMERIC, valueType, false);
            case FORMULA:
                throw new AssertionError();
            case NUMERIC:
                if (!this._cell.isSetV()) {
                    return 0.0d;
                }
                String v = this._cell.getV();
                if (v.isEmpty()) {
                    return 0.0d;
                }
                try {
                    return parseDouble(v);
                } catch (NumberFormatException e) {
                    throw typeMismatch(CellType.NUMERIC, CellType.STRING, false);
                }
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    public void setCellValueImpl(double value) {
        this._cell.setT(STCellType.N);
        this._cell.setV(String.valueOf(value));
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getStringCellValue() {
        return getRichStringCellValue().getString();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFRichTextString getRichStringCellValue() {
        XSSFRichTextString rt;
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                rt = new XSSFRichTextString("");
                break;
            case BOOLEAN:
            case NUMERIC:
            default:
                throw typeMismatch(CellType.STRING, cellType, false);
            case FORMULA:
                CellType cachedValueType = getBaseCellType(false);
                if (cachedValueType != CellType.STRING) {
                    throw typeMismatch(CellType.STRING, cachedValueType, true);
                }
                XSSFRichTextString rt2 = findStringValue();
                rt = rt2;
                break;
            case STRING:
                rt = findStringValue();
                break;
        }
        rt.setStylesTableReference(this._stylesSource);
        return rt;
    }

    private XSSFRichTextString findStringValue() {
        STCellType.Enum xmlbeanCellType = this._cell.getT();
        if (xmlbeanCellType == STCellType.INLINE_STR) {
            if (this._cell.isSetIs()) {
                XSSFRichTextString rt = new XSSFRichTextString(this._cell.getIs());
                return rt;
            }
            if (this._cell.isSetV()) {
                XSSFRichTextString rt2 = new XSSFRichTextString(this._cell.getV());
                return rt2;
            }
            XSSFRichTextString rt3 = new XSSFRichTextString("");
            return rt3;
        }
        if (xmlbeanCellType == STCellType.STR) {
            XSSFRichTextString rt4 = new XSSFRichTextString(this._cell.isSetV() ? this._cell.getV() : "");
            return rt4;
        }
        if (this._cell.isSetV()) {
            try {
                int idx = parseInt(this._cell.getV());
                XSSFRichTextString rt5 = (XSSFRichTextString) this._sharedStringSource.getItemAt(idx);
                return rt5;
            } catch (Throwable t) {
                if (ExceptionUtil.isFatal(t)) {
                    ExceptionUtil.rethrow(t);
                }
                LOG.atError().withThrowable(t).log("Failed to parse SST index '{}'", this._cell.getV());
                XSSFRichTextString rt6 = new XSSFRichTextString("");
                return rt6;
            }
        }
        XSSFRichTextString rt7 = new XSSFRichTextString("");
        return rt7;
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(String value) {
        setCellValueImpl(new XSSFRichTextString(value));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(RichTextString str) {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            this._cell.setV(str.getString());
            this._cell.setT(STCellType.STR);
            return;
        }
        if (this._cell.getT() == STCellType.INLINE_STR) {
            this._cell.setV(str.getString());
            return;
        }
        if (str instanceof XSSFRichTextString) {
            this._cell.setT(STCellType.S);
            XSSFRichTextString rt = (XSSFRichTextString) str;
            rt.setStylesTableReference(this._stylesSource);
            int sRef = this._sharedStringSource.addSharedStringItem(rt);
            this._cell.setV(Integer.toString(sRef));
            return;
        }
        this._cell.setT(STCellType.S);
        XSSFRichTextString rt2 = new XSSFRichTextString(str.getString());
        rt2.setStylesTableReference(this._stylesSource);
        int sRef2 = this._sharedStringSource.addSharedStringItem(rt2);
        this._cell.setV(Integer.toString(sRef2));
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getCellFormula() {
        return getCellFormula(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCellFormula(BaseXSSFEvaluationWorkbook fpb) {
        CellType cellType = getCellType();
        if (cellType != CellType.FORMULA) {
            throw typeMismatch(CellType.FORMULA, cellType, false);
        }
        CTCellFormula f = this._cell.getF();
        if (isPartOfArrayFormulaGroup() && (f == null || f.getStringValue().isEmpty())) {
            XSSFCell cell = getSheet().getFirstCellInArrayFormula(this);
            return cell.getCellFormula(fpb);
        }
        if (f == null) {
            return null;
        }
        if (f.getT() == STCellFormulaType.SHARED) {
            return convertSharedFormula(Math.toIntExact(f.getSi()), fpb == null ? XSSFEvaluationWorkbook.create(getSheet().getWorkbook()) : fpb);
        }
        return f.getStringValue();
    }

    private String convertSharedFormula(int si, BaseXSSFEvaluationWorkbook fpb) {
        XSSFSheet sheet = getSheet();
        CTCellFormula f = sheet.getSharedFormula(si);
        if (f == null) {
            throw new IllegalStateException("Master cell of a shared formula with sid=" + si + " was not found");
        }
        String sharedFormula = f.getStringValue();
        String sharedFormulaRange = f.getRef();
        CellRangeAddress ref = CellRangeAddress.valueOf(sharedFormulaRange);
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        SharedFormula sf = new SharedFormula(SpreadsheetVersion.EXCEL2007);
        Ptg[] ptgs = FormulaParser.parse(sharedFormula, fpb, FormulaType.CELL, sheetIndex, getRowIndex());
        Ptg[] fmla = sf.convertSharedFormulas(ptgs, getRowIndex() - ref.getFirstRow(), getColumnIndex() - ref.getFirstColumn());
        return FormulaRenderer.toFormulaString(fpb, fmla);
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellFormulaImpl(String formula) {
        if (formula == null) {
            throw new AssertionError();
        }
        setFormula(formula, FormulaType.CELL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCellArrayFormula(String formula, CellRangeAddress range) {
        setFormula(formula, FormulaType.ARRAY);
        CTCellFormula cellFormula = this._cell.getF();
        cellFormula.setT(STCellFormulaType.ARRAY);
        cellFormula.setRef(range.formatAsString());
    }

    private void setFormula(String formula, FormulaType formulaType) {
        XSSFWorkbook wb = this._row.getSheet().getWorkbook();
        if (formulaType == FormulaType.ARRAY && formula == null) {
            removeFormulaImpl();
            return;
        }
        if (wb.getCellFormulaValidation()) {
            XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(wb);
            FormulaParser.parse(formula, fpb, formulaType, wb.getSheetIndex(getSheet()), getRowIndex());
        }
        if (this._cell.isSetF()) {
            CTCellFormula f = this._cell.getF();
            f.setStringValue(formula);
            if (f.getT() == STCellFormulaType.SHARED) {
                getRow().getSheet().onReadCell(this);
                return;
            }
            return;
        }
        CTCellFormula f2 = CTCellFormula.Factory.newInstance();
        f2.setStringValue(formula);
        this._cell.setF(f2);
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void removeFormulaImpl() {
        this._row.getSheet().getWorkbook().onDeleteFormula(this);
        if (this._cell.isSetF()) {
            this._row.getSheet().onDeleteFormula(this, null);
            this._cell.unsetF();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getColumnIndex() {
        return this._cellNum;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getRowIndex() {
        return this._row.getRowNum();
    }

    public String getReference() {
        String ref = this._cell.getR();
        if (ref == null) {
            return getAddress().formatAsString();
        }
        return ref;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFCellStyle getCellStyle() {
        XSSFCellStyle style = getExplicitCellStyle();
        if (style == null) {
            return getDefaultCellStyleFromColumn();
        }
        return style;
    }

    private XSSFCellStyle getExplicitCellStyle() {
        if (this._stylesSource.getNumCellStyles() <= 0 || !this._cell.isSetS()) {
            return null;
        }
        long idx = this._cell.getS();
        XSSFCellStyle style = this._stylesSource.getStyleAt(Math.toIntExact(idx));
        return style;
    }

    private XSSFCellStyle getDefaultCellStyleFromColumn() {
        XSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        XSSFCellStyle style = (XSSFCellStyle) sheet.getColumnStyle(getColumnIndex());
        return style;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyDefaultCellStyleIfNecessary() {
        XSSFCellStyle defaultStyle;
        XSSFCellStyle style = getExplicitCellStyle();
        if (style == null) {
            XSSFSheet sheet = getSheet();
            if (sheet != null && (defaultStyle = getDefaultCellStyleFromColumn()) != null) {
                setCellStyle(defaultStyle);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellStyle(CellStyle style) {
        if (style == null) {
            if (this._cell.isSetS()) {
                this._cell.unsetS();
            }
        } else {
            XSSFCellStyle xStyle = (XSSFCellStyle) style;
            xStyle.verifyBelongsToStylesSource(this._stylesSource);
            long idx = this._stylesSource.putStyle(xStyle);
            this._cell.setS(idx);
        }
    }

    private boolean isFormulaCell() {
        return (this._cell.isSetF() && this._cell.getF().getT() != STCellFormulaType.DATA_TABLE) || getSheet().isCellInArrayFormulaContext(this);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCellType() {
        if (isFormulaCell()) {
            return CellType.FORMULA;
        }
        return getBaseCellType(true);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCachedFormulaResultType() {
        if (!isFormulaCell()) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        return getBaseCellType(false);
    }

    private CellType getBaseCellType(boolean blankCells) {
        switch (this._cell.getT().intValue()) {
            case 1:
                return CellType.BOOLEAN;
            case 2:
                if (!this._cell.isSetV() && blankCells) {
                    return CellType.BLANK;
                }
                return CellType.NUMERIC;
            case 3:
                return CellType.ERROR;
            case 4:
            case 5:
            case 6:
                return CellType.STRING;
            default:
                throw new IllegalStateException("Illegal cell type: " + this._cell.getT());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Date getDateCellValue() {
        if (getCellType() == CellType.BLANK) {
            return null;
        }
        double value = getNumericCellValue();
        boolean date1904 = getSheet().getWorkbook().isDate1904();
        return DateUtil.getJavaDate(value, date1904);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public LocalDateTime getLocalDateTimeCellValue() {
        if (getCellType() == CellType.BLANK) {
            return null;
        }
        double value = getNumericCellValue();
        boolean date1904 = getSheet().getWorkbook().isDate1904();
        return DateUtil.getLocalDateTime(value, date1904);
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(Date value) {
        boolean date1904 = getSheet().getWorkbook().isDate1904();
        setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(LocalDateTime value) {
        boolean date1904 = getSheet().getWorkbook().isDate1904();
        setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(Calendar value) {
        boolean date1904 = getSheet().getWorkbook().isDate1904();
        setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    public String getErrorCellString() throws IllegalStateException {
        CellType cellType = getBaseCellType(true);
        if (cellType != CellType.ERROR) {
            throw typeMismatch(CellType.ERROR, cellType, false);
        }
        return this._cell.getV();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public byte getErrorCellValue() throws IllegalStateException {
        String code = getErrorCellString();
        if (code == null) {
            return (byte) 0;
        }
        try {
            return FormulaError.forString(code).getCode();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unexpected error code", e);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellErrorValue(byte errorCode) {
        FormulaError error = FormulaError.forInt(errorCode);
        setCellErrorValue(error);
    }

    public void setCellErrorValue(FormulaError error) {
        this._cell.setT(STCellType.E);
        this._cell.setV(error.getString());
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setAsActiveCell() {
        getSheet().setActiveCell(getAddress());
    }

    private void setBlankPrivate() {
        CTCell blank = CTCell.Factory.newInstance();
        blank.setR(this._cell.getR());
        if (this._cell.isSetS()) {
            blank.setS(this._cell.getS());
        }
        this._cell.set(blank);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCellNum(int num) {
        checkBounds(num);
        this._cellNum = num;
        String ref = new CellReference(getRowIndex(), getColumnIndex()).formatAsString();
        this._cell.setR(ref);
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellTypeImpl(CellType cellType) {
        setCellType(cellType, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCellType(CellType cellType, BaseXSSFEvaluationWorkbook evalWb) {
        CellType prevType = getCellType();
        if (prevType == CellType.FORMULA && cellType != CellType.FORMULA) {
            if (this._cell.isSetF()) {
                this._row.getSheet().onDeleteFormula(this, evalWb);
            }
            getSheet().getWorkbook().onDeleteFormula(this);
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
        String newVal = FALSE_AS_STRING;
        switch (i) {
            case 1:
                setBlankPrivate();
                break;
            case 2:
                if (convertCellValueToBoolean()) {
                    newVal = TRUE_AS_STRING;
                }
                this._cell.setT(STCellType.B);
                this._cell.setV(newVal);
                break;
            case 3:
                if (!this._cell.isSetF()) {
                    CTCellFormula f = CTCellFormula.Factory.newInstance();
                    f.setStringValue(FALSE_AS_STRING);
                    this._cell.setF(f);
                    if (this._cell.isSetT()) {
                        this._cell.unsetT();
                        break;
                    }
                }
                break;
            case 4:
                this._cell.setT(STCellType.N);
                break;
            case 5:
                if (prevType != CellType.STRING) {
                    String str = convertCellValueToString();
                    XSSFRichTextString rt = new XSSFRichTextString(str);
                    rt.setStylesTableReference(this._stylesSource);
                    int sRef = this._sharedStringSource.addSharedStringItem(rt);
                    this._cell.setV(Integer.toString(sRef));
                }
                this._cell.setT(STCellType.S);
                break;
            case 6:
                this._cell.setT(STCellType.E);
                break;
            default:
                throw new IllegalArgumentException("Illegal cell type: " + cellType);
        }
        if (cellType != CellType.FORMULA && this._cell.isSetF()) {
            this._cell.unsetF();
        }
    }

    public String toString() {
        switch (getCellType()) {
            case BLANK:
                return "";
            case BOOLEAN:
                return getBooleanCellValue() ? TRUE : FALSE;
            case FORMULA:
                return getCellFormula();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(this)) {
                    DataFormatter df = new DataFormatter();
                    df.setUseCachedValuesForFormulaCells(true);
                    return df.formatCellValue(this);
                }
                return Double.toString(getNumericCellValue());
            case STRING:
                return getRichStringCellValue().toString();
            case ERROR:
                return ErrorEval.getText(getErrorCellValue());
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    public String getRawValue() {
        return this._cell.getV();
    }

    private static RuntimeException typeMismatch(CellType expectedType, CellType actualType, boolean isFormulaCell) {
        String msg = "Cannot get a " + expectedType + " value from a " + actualType + StringUtils.SPACE + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private static void checkBounds(int cellIndex) {
        SpreadsheetVersion v = SpreadsheetVersion.EXCEL2007;
        int maxcol = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        if (cellIndex < 0 || cellIndex > maxcol) {
            throw new IllegalArgumentException("Invalid column index (" + cellIndex + ").  Allowable column range for " + v.name() + " is (0.." + maxcol + ") or ('A'..'" + v.getLastColumnName() + "')");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFComment getCellComment() {
        return getSheet().getCellComment(new CellAddress(this));
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellComment(Comment comment) {
        if (comment == null) {
            removeCellComment();
        } else {
            comment.setAddress(getRowIndex(), getColumnIndex());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeCellComment() {
        XSSFComment comment = getCellComment();
        if (comment != null) {
            CellAddress ref = new CellAddress(getReference());
            XSSFSheet sh = getSheet();
            sh.getCommentsTable(false).removeComment(ref);
            sh.getVMLDrawing(false).removeCommentShape(getRowIndex(), getColumnIndex());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public XSSFHyperlink getHyperlink() {
        return getSheet().getHyperlink(this._row.getRowNum(), this._cellNum);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setHyperlink(Hyperlink hyperlink) {
        XSSFHyperlink link;
        if (hyperlink == null) {
            removeHyperlink();
            return;
        }
        if (hyperlink instanceof XSSFHyperlink) {
            link = (XSSFHyperlink) hyperlink;
        } else {
            link = new XSSFHyperlink(hyperlink);
        }
        link.setCellReference(new CellReference(this._row.getRowNum(), this._cellNum).formatAsString());
        getSheet().addHyperlink(link);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeHyperlink() {
        getSheet().removeHyperlink(this._row.getRowNum(), this._cellNum);
    }

    @Internal
    public CTCell getCTCell() {
        return this._cell;
    }

    @Internal
    public void setCTCell(CTCell cell) {
        this._cell = cell;
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = getBaseCellType(false);
        }
        switch (cellType) {
            case BLANK:
            case ERROR:
                return false;
            case BOOLEAN:
                return TRUE_AS_STRING.equals(this._cell.getV());
            case FORMULA:
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
            case NUMERIC:
                return parseDouble(this._cell.getV()) != 0.0d;
            case STRING:
                int sstIndex = parseInt(this._cell.getV());
                RichTextString rt = this._sharedStringSource.getItemAt(sstIndex);
                String text = rt.getString();
                return Boolean.parseBoolean(text);
        }
    }

    private String convertCellValueToString() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return "";
            case BOOLEAN:
                return TRUE_AS_STRING.equals(this._cell.getV()) ? TRUE : FALSE;
            case FORMULA:
                CellType cellType2 = getBaseCellType(false);
                String textValue = this._cell.getV();
                switch (cellType2) {
                    case BOOLEAN:
                        if (TRUE_AS_STRING.equals(textValue)) {
                            return TRUE;
                        }
                        if (FALSE_AS_STRING.equals(textValue)) {
                            return FALSE;
                        }
                        throw new IllegalStateException("Unexpected boolean cached formula value '" + textValue + "'.");
                    case FORMULA:
                    default:
                        throw new IllegalStateException("Unexpected formula result type (" + cellType2 + ")");
                    case NUMERIC:
                    case STRING:
                    case ERROR:
                        return textValue;
                }
            case NUMERIC:
            case ERROR:
                return this._cell.getV();
            case STRING:
                try {
                    int sstIndex = parseInt(this._cell.getV());
                    RichTextString rt = this._sharedStringSource.getItemAt(sstIndex);
                    return rt.getString();
                } catch (Throwable t) {
                    if (ExceptionUtil.isFatal(t)) {
                        ExceptionUtil.rethrow(t);
                    }
                    LOG.atError().withThrowable(t).log("Failed to parse SST index '{}'", this._cell.getV());
                    return "";
                }
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellRangeAddress getArrayFormulaRange() {
        XSSFCell cell = getSheet().getFirstCellInArrayFormula(this);
        if (cell == null) {
            throw new IllegalStateException("Cell " + new CellReference(this).formatAsString() + " is not part of an array formula.");
        }
        String formulaRef = cell._cell.getF().getRef();
        return CellRangeAddress.valueOf(formulaRef);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public boolean isPartOfArrayFormulaGroup() {
        return getSheet().isCellInArrayFormulaContext(this);
    }

    public void updateCellReferencesForShifting(String msg) {
        if (isPartOfArrayFormulaGroup()) {
            tryToDeleteArrayFormula(msg);
        }
        CalculationChain calcChain = getSheet().getWorkbook().getCalculationChain();
        int sheetId = Math.toIntExact(getSheet().sheet.getSheetId());
        if (calcChain != null) {
            calcChain.removeItem(sheetId, getReference());
        }
        CTCell ctCell = getCTCell();
        String r = new CellReference(getRowIndex(), getColumnIndex()).formatAsString();
        ctCell.setR(r);
    }

    private static int parseInt(String value) throws NumberFormatException {
        return Integer.parseInt(value.trim());
    }

    private static double parseDouble(String value) throws NumberFormatException {
        return Double.parseDouble(value.trim());
    }
}
