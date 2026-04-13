package org.apache.poi.hssf.usermodel;

import androidx.core.view.InputDeviceCompat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;

/* loaded from: classes10.dex */
public class HSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final short ENCODING_COMPRESSED_UNICODE = 0;
    public static final short ENCODING_UNCHANGED = -1;
    public static final short ENCODING_UTF_16 = 1;
    private static final String FILE_FORMAT_NAME = "BIFF8";
    private final HSSFWorkbook _book;
    private CellType _cellType;
    private HSSFComment _comment;
    private CellValueRecordInterface _record;
    private final HSSFSheet _sheet;
    private HSSFRichTextString _stringValue;
    public static final int LAST_COLUMN_NUMBER = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
    private static final String LAST_COLUMN_NAME = SpreadsheetVersion.EXCEL97.getLastColumnName();

    protected HSSFCell(HSSFWorkbook book, HSSFSheet sheet, int row, short col) {
        checkBounds(col);
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        short xfindex = sheet.getSheet().getXFIndexForColAt(col);
        setCellType(CellType.BLANK, false, row, col, xfindex);
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFSheet getSheet() {
        return this._sheet;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFRow getRow() {
        int rowIndex = getRowIndex();
        return this._sheet.getRow(rowIndex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFCell(HSSFWorkbook book, HSSFSheet sheet, int row, short col, CellType type) {
        checkBounds(col);
        this._cellType = CellType._NONE;
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        short xfindex = sheet.getSheet().getXFIndexForColAt(col);
        setCellType(type, false, row, col, xfindex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFCell(HSSFWorkbook book, HSSFSheet sheet, CellValueRecordInterface cval) {
        this._record = cval;
        this._cellType = determineType(cval);
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        switch (this._cellType) {
            case STRING:
                this._stringValue = new HSSFRichTextString(book.getWorkbook(), (LabelSSTRecord) cval);
                return;
            case FORMULA:
                this._stringValue = new HSSFRichTextString(((FormulaRecordAggregate) cval).getStringValue());
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static CellType determineType(CellValueRecordInterface cellValueRecordInterface) {
        if (cellValueRecordInterface instanceof FormulaRecordAggregate) {
            return CellType.FORMULA;
        }
        Record record = (Record) cellValueRecordInterface;
        switch (record.getSid()) {
            case 253:
                return CellType.STRING;
            case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                return CellType.BLANK;
            case 515:
                return CellType.NUMERIC;
            case 517:
                BoolErrRecord boolErrRecord = (BoolErrRecord) record;
                return boolErrRecord.isBoolean() ? CellType.BOOLEAN : CellType.ERROR;
            default:
                throw new IllegalStateException("Bad cell value rec (" + cellValueRecordInterface.getClass().getName() + ")");
        }
    }

    protected InternalWorkbook getBoundWorkbook() {
        return this._book.getWorkbook();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getRowIndex() {
        return this._record.getRow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateCellNum(short num) {
        this._record.setColumn(num);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getColumnIndex() {
        return this._record.getColumn() & 65535;
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellTypeImpl(CellType cellType) {
        notifyFormulaChanging();
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        setCellType(cellType, true, row, col, styleIndex);
    }

    private void setCellType(CellType cellType, boolean setValue, int row, short col, short styleIndex) {
        LabelSSTRecord lrec;
        HSSFCell hSSFCell;
        FormulaRecordAggregate frec;
        BlankRecord brec;
        NumberRecord nrec;
        BoolErrRecord boolRec;
        BoolErrRecord errRec;
        switch (cellType) {
            case STRING:
                if (cellType == this._cellType) {
                    lrec = (LabelSSTRecord) this._record;
                } else {
                    lrec = new LabelSSTRecord();
                    lrec.setColumn(col);
                    lrec.setRow(row);
                    lrec.setXFIndex(styleIndex);
                }
                if (!setValue) {
                    hSSFCell = this;
                } else {
                    String str = convertCellValueToString();
                    if (str == null) {
                        setCellType(CellType.BLANK, false, row, col, styleIndex);
                        return;
                    }
                    hSSFCell = this;
                    int sstIndex = hSSFCell._book.getWorkbook().addSSTString(new UnicodeString(str));
                    lrec.setSSTIndex(sstIndex);
                    UnicodeString us = hSSFCell._book.getWorkbook().getSSTString(sstIndex);
                    hSSFCell._stringValue = new HSSFRichTextString();
                    hSSFCell._stringValue.setUnicodeString(us);
                }
                hSSFCell._record = lrec;
                break;
            case FORMULA:
                if (cellType != this._cellType) {
                    frec = this._sheet.getSheet().getRowsAggregate().createFormula(row, col);
                } else {
                    frec = (FormulaRecordAggregate) this._record;
                    frec.setRow(row);
                    frec.setColumn(col);
                }
                if (getCellType() == CellType.BLANK) {
                    frec.getFormulaRecord().setValue(0.0d);
                }
                frec.setXFIndex(styleIndex);
                this._record = frec;
                hSSFCell = this;
                break;
            case BLANK:
                if (cellType != this._cellType) {
                    brec = new BlankRecord();
                } else {
                    brec = (BlankRecord) this._record;
                }
                brec.setColumn(col);
                brec.setXFIndex(styleIndex);
                brec.setRow(row);
                this._record = brec;
                hSSFCell = this;
                break;
            case NUMERIC:
                if (cellType != this._cellType) {
                    nrec = new NumberRecord();
                } else {
                    nrec = (NumberRecord) this._record;
                }
                nrec.setColumn(col);
                if (setValue) {
                    nrec.setValue(getNumericCellValue());
                }
                nrec.setXFIndex(styleIndex);
                nrec.setRow(row);
                this._record = nrec;
                hSSFCell = this;
                break;
            case BOOLEAN:
                if (cellType != this._cellType) {
                    boolRec = new BoolErrRecord();
                } else {
                    boolRec = (BoolErrRecord) this._record;
                }
                boolRec.setColumn(col);
                if (setValue) {
                    boolRec.setValue(convertCellValueToBoolean());
                }
                boolRec.setXFIndex(styleIndex);
                boolRec.setRow(row);
                this._record = boolRec;
                hSSFCell = this;
                break;
            case ERROR:
                if (cellType != this._cellType) {
                    errRec = new BoolErrRecord();
                } else {
                    errRec = (BoolErrRecord) this._record;
                }
                errRec.setColumn(col);
                if (setValue) {
                    errRec.setValue(FormulaError.VALUE.getCode());
                }
                errRec.setXFIndex(styleIndex);
                errRec.setRow(row);
                this._record = errRec;
                hSSFCell = this;
                break;
            default:
                throw new IllegalStateException("Invalid cell type: " + cellType);
        }
        if (cellType != hSSFCell._cellType && hSSFCell._cellType != CellType._NONE) {
            hSSFCell._sheet.getSheet().replaceValueRecord(hSSFCell._record);
        }
        hSSFCell._cellType = cellType;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCellType() {
        return this._cellType;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(double value) {
        HSSFCell hSSFCell;
        switch (this._cellType) {
            case FORMULA:
                ((FormulaRecordAggregate) this._record).setCachedDoubleResult(value);
                return;
            case BLANK:
            default:
                hSSFCell = this;
                hSSFCell.setCellType(CellType.NUMERIC, false, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
                ((NumberRecord) hSSFCell._record).setValue(value);
                return;
            case NUMERIC:
                hSSFCell = this;
                ((NumberRecord) hSSFCell._record).setValue(value);
                return;
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(Date value) {
        setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(LocalDateTime value) {
        setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(Calendar value) {
        setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(String value) {
        setCellValueImpl(new HSSFRichTextString(value));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(RichTextString value) {
        HSSFCell hSSFCell;
        if (this._cellType == CellType.FORMULA) {
            FormulaRecordAggregate fr = (FormulaRecordAggregate) this._record;
            fr.setCachedStringResult(value.getString());
            this._stringValue = new HSSFRichTextString(value.getString());
            return;
        }
        if (this._cellType == CellType.STRING) {
            hSSFCell = this;
        } else {
            int row = this._record.getRow();
            short col = this._record.getColumn();
            short styleIndex = this._record.getXFIndex();
            hSSFCell = this;
            hSSFCell.setCellType(CellType.STRING, false, row, col, styleIndex);
        }
        if (value instanceof HSSFRichTextString) {
            HSSFRichTextString hvalue = (HSSFRichTextString) value;
            UnicodeString str = hvalue.getUnicodeString();
            int index = hSSFCell._book.getWorkbook().addSSTString(str);
            ((LabelSSTRecord) hSSFCell._record).setSSTIndex(index);
            hSSFCell._stringValue = hvalue;
            hSSFCell._stringValue.setWorkbookReferences(hSSFCell._book.getWorkbook(), (LabelSSTRecord) hSSFCell._record);
            hSSFCell._stringValue.setUnicodeString(hSSFCell._book.getWorkbook().getSSTString(index));
            return;
        }
        HSSFRichTextString hvalue2 = new HSSFRichTextString(value.getString());
        UnicodeString str2 = hvalue2.getUnicodeString();
        int index2 = hSSFCell._book.getWorkbook().addSSTString(str2);
        ((LabelSSTRecord) hSSFCell._record).setSSTIndex(index2);
        hSSFCell._stringValue = hvalue2;
        hSSFCell._stringValue.setWorkbookReferences(hSSFCell._book.getWorkbook(), (LabelSSTRecord) hSSFCell._record);
        hSSFCell._stringValue.setUnicodeString(hSSFCell._book.getWorkbook().getSSTString(index2));
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellFormulaImpl(String formula) {
        if (getValueType() == CellType.BLANK) {
            setCellValue(0.0d);
        }
        if (formula == null) {
            throw new AssertionError();
        }
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        CellValue savedValue = readValue();
        int sheetIndex = this._book.getSheetIndex(this._sheet);
        Ptg[] ptgs = HSSFFormulaParser.parse(formula, this._book, FormulaType.CELL, sheetIndex);
        setCellType(CellType.FORMULA, false, row, col, styleIndex);
        FormulaRecordAggregate agg = (FormulaRecordAggregate) this._record;
        FormulaRecord frec = agg.getFormulaRecord();
        frec.setOptions((short) 2);
        if (agg.getXFIndex() == 0) {
            agg.setXFIndex((short) 15);
        }
        agg.setParsedExpression(ptgs);
        restoreValue(savedValue);
    }

    private CellValue readValue() {
        CellType valueType = getCellType() == CellType.FORMULA ? getCachedFormulaResultType() : getCellType();
        switch (valueType) {
            case STRING:
                return new CellValue(getStringCellValue());
            case FORMULA:
            case BLANK:
            default:
                throw new IllegalStateException("Unexpected cell-type " + valueType);
            case NUMERIC:
                return new CellValue(getNumericCellValue());
            case BOOLEAN:
                return CellValue.valueOf(getBooleanCellValue());
            case ERROR:
                return CellValue.getError(getErrorCellValue());
        }
    }

    private void restoreValue(CellValue value) {
        switch (value.getCellType()) {
            case STRING:
                setCellValue(value.getStringValue());
                return;
            case FORMULA:
            case BLANK:
            default:
                throw new IllegalStateException("Unexpected cell-type " + value.getCellType() + " for cell-value: " + value);
            case NUMERIC:
                setCellValue(value.getNumberValue());
                return;
            case BOOLEAN:
                setCellValue(value.getBooleanValue());
                return;
            case ERROR:
                setCellErrorValue(FormulaError.forInt(value.getErrorValue()));
                return;
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void removeFormulaImpl() {
        if (getCellType() != CellType.FORMULA) {
            throw new AssertionError();
        }
        notifyFormulaChanging();
        switch (getCachedFormulaResultType()) {
            case STRING:
                this._record = new NumberRecord();
                ((NumberRecord) this._record).setValue(0.0d);
                this._cellType = CellType.STRING;
                return;
            case FORMULA:
            case BLANK:
            default:
                throw new AssertionError();
            case NUMERIC:
                double numericValue = ((FormulaRecordAggregate) this._record).getFormulaRecord().getValue();
                this._record = new NumberRecord();
                ((NumberRecord) this._record).setValue(numericValue);
                this._cellType = CellType.NUMERIC;
                return;
            case BOOLEAN:
                boolean booleanValue = ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedBooleanValue();
                this._record = new BoolErrRecord();
                ((BoolErrRecord) this._record).setValue(booleanValue);
                this._cellType = CellType.BOOLEAN;
                return;
            case ERROR:
                byte errorValue = (byte) ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedErrorValue();
                this._record = new BoolErrRecord();
                try {
                    ((BoolErrRecord) this._record).setValue(errorValue);
                } catch (IllegalArgumentException e) {
                    ((BoolErrRecord) this._record).setValue((byte) ErrorEval.REF_INVALID.getErrorCode());
                }
                this._cellType = CellType.ERROR;
                return;
        }
    }

    private void notifyFormulaChanging() {
        if (this._record instanceof FormulaRecordAggregate) {
            ((FormulaRecordAggregate) this._record).notifyFormulaChanging();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getCellFormula() {
        if (!(this._record instanceof FormulaRecordAggregate)) {
            throw typeMismatch(CellType.FORMULA, this._cellType, true);
        }
        return HSSFFormulaParser.toFormulaString(this._book, ((FormulaRecordAggregate) this._record).getFormulaTokens());
    }

    private static RuntimeException typeMismatch(CellType expectedTypeCode, CellType actualTypeCode, boolean isFormulaCell) {
        String msg = "Cannot get a " + expectedTypeCode + " value from a " + actualTypeCode + StringUtils.SPACE + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private static void checkFormulaCachedValueType(CellType expectedTypeCode, FormulaRecord fr) {
        CellType cachedValueType = fr.getCachedResultTypeEnum();
        if (cachedValueType != expectedTypeCode) {
            throw typeMismatch(expectedTypeCode, cachedValueType, true);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public double getNumericCellValue() {
        switch (this._cellType) {
            case FORMULA:
                FormulaRecord fr = ((FormulaRecordAggregate) this._record).getFormulaRecord();
                checkFormulaCachedValueType(CellType.NUMERIC, fr);
                return fr.getValue();
            case BLANK:
                return 0.0d;
            case NUMERIC:
                return ((NumberRecord) this._record).getValue();
            default:
                throw typeMismatch(CellType.NUMERIC, this._cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Date getDateCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double value = getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getJavaDate(value, true);
        }
        return DateUtil.getJavaDate(value, false);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public LocalDateTime getLocalDateTimeCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double value = getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getLocalDateTime(value, true);
        }
        return DateUtil.getLocalDateTime(value, false);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getStringCellValue() {
        HSSFRichTextString str = getRichStringCellValue();
        return str.getString();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFRichTextString getRichStringCellValue() {
        switch (this._cellType) {
            case STRING:
                return this._stringValue;
            case FORMULA:
                FormulaRecordAggregate fra = (FormulaRecordAggregate) this._record;
                checkFormulaCachedValueType(CellType.STRING, fra.getFormulaRecord());
                String strVal = fra.getStringValue();
                return new HSSFRichTextString(strVal != null ? strVal : "");
            case BLANK:
                return new HSSFRichTextString("");
            default:
                throw typeMismatch(CellType.STRING, this._cellType, false);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001c. Please report as an issue. */
    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(boolean value) {
        HSSFCell hSSFCell;
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        switch (this._cellType) {
            case FORMULA:
                ((FormulaRecordAggregate) this._record).setCachedBooleanResult(value);
                return;
            case BOOLEAN:
                hSSFCell = this;
                ((BoolErrRecord) hSSFCell._record).setValue(value);
                return;
            default:
                hSSFCell = this;
                hSSFCell.setCellType(CellType.BOOLEAN, false, row, col, styleIndex);
                ((BoolErrRecord) hSSFCell._record).setValue(value);
                return;
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    @Deprecated
    public void setCellErrorValue(byte errorCode) {
        FormulaError error = FormulaError.forInt(errorCode);
        setCellErrorValue(error);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001c. Please report as an issue. */
    public void setCellErrorValue(FormulaError error) {
        HSSFCell hSSFCell;
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        switch (this._cellType) {
            case FORMULA:
                ((FormulaRecordAggregate) this._record).setCachedErrorResult(error.getCode());
                return;
            case ERROR:
                hSSFCell = this;
                ((BoolErrRecord) hSSFCell._record).setValue(error);
                return;
            default:
                hSSFCell = this;
                hSSFCell.setCellType(CellType.ERROR, false, row, col, styleIndex);
                ((BoolErrRecord) hSSFCell._record).setValue(error);
                return;
        }
    }

    private boolean convertCellValueToBoolean() {
        switch (this._cellType) {
            case STRING:
                int sstIndex = ((LabelSSTRecord) this._record).getSSTIndex();
                String text = this._book.getWorkbook().getSSTString(sstIndex).getString();
                return Boolean.parseBoolean(text);
            case FORMULA:
                FormulaRecord fr = ((FormulaRecordAggregate) this._record).getFormulaRecord();
                checkFormulaCachedValueType(CellType.BOOLEAN, fr);
                return fr.getCachedBooleanValue();
            case BLANK:
            case ERROR:
                return false;
            case NUMERIC:
                return ((NumberRecord) this._record).getValue() != 0.0d;
            case BOOLEAN:
                return ((BoolErrRecord) this._record).getBooleanValue();
            default:
                throw new IllegalStateException("Unexpected cell type (" + this._cellType + ")");
        }
    }

    private String convertCellValueToString() {
        switch (this._cellType) {
            case STRING:
                int sstIndex = ((LabelSSTRecord) this._record).getSSTIndex();
                return this._book.getWorkbook().getSSTString(sstIndex).getString();
            case FORMULA:
                FormulaRecordAggregate fra = (FormulaRecordAggregate) this._record;
                FormulaRecord fr = fra.getFormulaRecord();
                switch (fr.getCachedResultTypeEnum()) {
                    case STRING:
                        return fra.getStringValue();
                    case FORMULA:
                    case BLANK:
                    default:
                        throw new IllegalStateException("Unexpected formula result type (" + this._cellType + ")");
                    case NUMERIC:
                        return NumberToTextConverter.toText(fr.getValue());
                    case BOOLEAN:
                        return fr.getCachedBooleanValue() ? "TRUE" : "FALSE";
                    case ERROR:
                        return FormulaError.forInt(fr.getCachedErrorValue()).getString();
                }
            case BLANK:
                return "";
            case NUMERIC:
                return NumberToTextConverter.toText(((NumberRecord) this._record).getValue());
            case BOOLEAN:
                return ((BoolErrRecord) this._record).getBooleanValue() ? "TRUE" : "FALSE";
            case ERROR:
                return FormulaError.forInt(((BoolErrRecord) this._record).getErrorValue()).getString();
            default:
                throw new IllegalStateException("Unexpected cell type (" + this._cellType + ")");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public boolean getBooleanCellValue() {
        switch (this._cellType) {
            case FORMULA:
                FormulaRecord fr = ((FormulaRecordAggregate) this._record).getFormulaRecord();
                checkFormulaCachedValueType(CellType.BOOLEAN, fr);
                return fr.getCachedBooleanValue();
            case BLANK:
                return false;
            case NUMERIC:
            default:
                throw typeMismatch(CellType.BOOLEAN, this._cellType, false);
            case BOOLEAN:
                return ((BoolErrRecord) this._record).getBooleanValue();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public byte getErrorCellValue() {
        switch (this._cellType) {
            case FORMULA:
                FormulaRecord fr = ((FormulaRecordAggregate) this._record).getFormulaRecord();
                checkFormulaCachedValueType(CellType.ERROR, fr);
                return (byte) fr.getCachedErrorValue();
            case ERROR:
                return ((BoolErrRecord) this._record).getErrorValue();
            default:
                throw typeMismatch(CellType.ERROR, this._cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellStyle(CellStyle style) {
        setCellStyle((HSSFCellStyle) style);
    }

    public void setCellStyle(HSSFCellStyle style) {
        short styleIndex;
        if (style == null) {
            this._record.setXFIndex((short) 15);
            return;
        }
        style.verifyBelongsToWorkbook(this._book);
        if (style.getUserStyleName() != null) {
            styleIndex = applyUserCellStyle(style);
        } else {
            styleIndex = style.getIndex();
        }
        this._record.setXFIndex(styleIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFCellStyle getCellStyle() {
        short styleIndex = this._record.getXFIndex();
        ExtendedFormatRecord xf = this._book.getWorkbook().getExFormatAt(styleIndex);
        return new HSSFCellStyle(styleIndex, xf, this._book);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CellValueRecordInterface getCellValueRecord() {
        return this._record;
    }

    private static void checkBounds(int cellIndex) {
        if (cellIndex < 0 || cellIndex > LAST_COLUMN_NUMBER) {
            throw new IllegalArgumentException("Invalid column index (" + cellIndex + ").  Allowable column range for " + FILE_FORMAT_NAME + " is (0.." + LAST_COLUMN_NUMBER + ") or ('A'..'" + LAST_COLUMN_NAME + "')");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setAsActiveCell() {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        this._sheet.getSheet().setActiveCellRow(row);
        this._sheet.getSheet().setActiveCellCol(col);
    }

    public String toString() {
        switch (getCellType()) {
            case STRING:
                return getRichStringCellValue().toString();
            case FORMULA:
                return getCellFormula();
            case BLANK:
                return "";
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(this)) {
                    DataFormatter df = new DataFormatter();
                    df.setUseCachedValuesForFormulaCells(true);
                    return df.formatCellValue(this);
                }
                return Double.toString(getNumericCellValue());
            case BOOLEAN:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case ERROR:
                return ErrorEval.getText(((BoolErrRecord) this._record).getErrorValue());
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellComment(Comment comment) {
        if (comment == null) {
            removeCellComment();
            return;
        }
        comment.setRow(this._record.getRow());
        comment.setColumn(this._record.getColumn());
        this._comment = (HSSFComment) comment;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFComment getCellComment() {
        if (this._comment == null) {
            this._comment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        }
        return this._comment;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeCellComment() {
        HSSFComment comment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        this._comment = null;
        if (comment == null) {
            return;
        }
        this._sheet.getDrawingPatriarch().removeShape(comment);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public HSSFHyperlink getHyperlink() {
        return this._sheet.getHyperlink(this._record.getRow(), (int) this._record.getColumn());
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setHyperlink(Hyperlink hyperlink) {
        HSSFHyperlink link;
        if (hyperlink == null) {
            removeHyperlink();
            return;
        }
        if (hyperlink instanceof HSSFHyperlink) {
            link = (HSSFHyperlink) hyperlink;
        } else {
            link = new HSSFHyperlink(hyperlink);
        }
        link.setFirstRow(this._record.getRow());
        link.setLastRow(this._record.getRow());
        link.setFirstColumn(this._record.getColumn());
        link.setLastColumn(this._record.getColumn());
        switch (link.getType()) {
            case EMAIL:
            case URL:
                link.setLabel("url");
                break;
            case FILE:
                link.setLabel("file");
                break;
            case DOCUMENT:
                link.setLabel("place");
                break;
        }
        List<RecordBase> records = this._sheet.getSheet().getRecords();
        int eofLoc = records.size() - 1;
        records.add(eofLoc, link.record);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeHyperlink() {
        Iterator<RecordBase> it = this._sheet.getSheet().getRecords().iterator();
        while (it.hasNext()) {
            RecordBase rec = it.next();
            if (rec instanceof HyperlinkRecord) {
                HyperlinkRecord link = (HyperlinkRecord) rec;
                if (link.getFirstColumn() == this._record.getColumn() && link.getFirstRow() == this._record.getRow()) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCachedFormulaResultType() {
        if (this._cellType != CellType.FORMULA) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        return ((FormulaRecordAggregate) this._record).getFormulaRecord().getCachedResultTypeEnum();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCellArrayFormula(CellRangeAddress range) {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        setCellType(CellType.FORMULA, false, row, col, styleIndex);
        Ptg[] ptgsForCell = {new ExpPtg(range.getFirstRow(), range.getFirstColumn())};
        FormulaRecordAggregate agg = (FormulaRecordAggregate) this._record;
        agg.setParsedExpression(ptgsForCell);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellRangeAddress getArrayFormulaRange() {
        if (this._cellType != CellType.FORMULA) {
            String ref = new CellReference(this).formatAsString();
            throw new IllegalStateException("Cell " + ref + " is not part of an array formula.");
        }
        return ((FormulaRecordAggregate) this._record).getArrayFormulaRange();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public boolean isPartOfArrayFormulaGroup() {
        return this._cellType == CellType.FORMULA && ((FormulaRecordAggregate) this._record).isPartOfArrayFormula();
    }

    private short applyUserCellStyle(HSSFCellStyle style) {
        if (style.getUserStyleName() == null) {
            throw new IllegalArgumentException("Expected user-defined style");
        }
        InternalWorkbook iwb = this._book.getWorkbook();
        short userXf = -1;
        int numfmt = iwb.getNumExFormats();
        short i = 0;
        while (true) {
            if (i >= numfmt) {
                break;
            }
            ExtendedFormatRecord xf = iwb.getExFormatAt(i);
            if (xf.getXFType() != 0 || xf.getParentIndex() != style.getIndex()) {
                i = (short) (i + 1);
            } else {
                userXf = i;
                break;
            }
        }
        if (userXf == -1) {
            ExtendedFormatRecord xfr = iwb.createCellXF();
            xfr.cloneStyleFrom(iwb.getExFormatAt(style.getIndex()));
            xfr.setIndentionOptions((short) 0);
            xfr.setXFType((short) 0);
            xfr.setParentIndex(style.getIndex());
            short styleIndex = (short) numfmt;
            return styleIndex;
        }
        short styleIndex2 = userXf;
        return styleIndex2;
    }
}
