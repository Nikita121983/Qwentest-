package org.apache.poi.xssf.streaming;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/* loaded from: classes10.dex */
public class SXSSFCell extends CellBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int _columnIndex;
    private Property _firstProperty;
    private final SXSSFRow _row;
    private CellStyle _style;
    private Value _value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface Value {
        CellType getType();
    }

    public SXSSFCell(SXSSFRow row, CellType cellType) {
        this._columnIndex = -1;
        this._row = row;
        this._value = new BlankValue();
        setType(cellType);
    }

    public SXSSFCell(SXSSFRow row, CellType cellType, int columnIndex) {
        this(row, cellType);
        this._columnIndex = columnIndex;
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getColumnIndex() {
        if (this._columnIndex >= 0) {
            return this._columnIndex;
        }
        return this._row.getCellIndex(this);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public int getRowIndex() {
        return this._row.getRowNum();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public SXSSFSheet getSheet() {
        return this._row.getSheet();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Row getRow() {
        return this._row;
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellTypeImpl(CellType cellType) {
        ensureType(cellType);
    }

    private boolean isFormulaCell() {
        return this._value instanceof FormulaValue;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCellType() {
        if (isFormulaCell()) {
            return CellType.FORMULA;
        }
        return this._value.getType();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellType getCachedFormulaResultType() {
        if (!isFormulaCell()) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        return ((FormulaValue) this._value).getFormulaType();
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    public void setCellValueImpl(double value) {
        ensureTypeOrFormulaType(CellType.NUMERIC);
        if (this._value.getType() == CellType.FORMULA) {
            ((NumericFormulaValue) this._value).setPreEvaluatedValue(value);
        } else {
            ((NumericValue) this._value).setValue(value);
        }
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

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(RichTextString value) {
        ensureRichTextStringType();
        if (this._value instanceof RichTextStringFormulaValue) {
            ((RichTextStringFormulaValue) this._value).setPreEvaluatedValue(value);
        } else {
            ((RichTextValue) this._value).setValue(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void setCellValueImpl(String value) {
        ensureTypeOrFormulaType(CellType.STRING);
        if (this._value.getType() == CellType.FORMULA) {
            ((StringFormulaValue) this._value).setPreEvaluatedValue(value);
        } else {
            ((PlainStringValue) this._value).setValue(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    public void setCellFormulaImpl(String formula) throws FormulaParseException {
        if (formula == null) {
            throw new AssertionError();
        }
        if (getCellType() == CellType.FORMULA) {
            ((FormulaValue) this._value).setValue(formula);
            return;
        }
        switch (getCellType()) {
            case BLANK:
            case NUMERIC:
                this._value = new NumericFormulaValue(formula, getNumericCellValue());
                return;
            case STRING:
                if (this._value instanceof PlainStringValue) {
                    this._value = new StringFormulaValue(formula, getStringCellValue());
                    return;
                } else {
                    if (!(this._value instanceof RichTextValue)) {
                        throw new AssertionError();
                    }
                    this._value = new RichTextStringFormulaValue(formula, ((RichTextValue) this._value).getValue());
                    return;
                }
            case BOOLEAN:
                this._value = new BooleanFormulaValue(formula, getBooleanCellValue());
                return;
            case ERROR:
                this._value = new ErrorFormulaValue(formula, getErrorCellValue());
                return;
            default:
                throw new FormulaParseException("Cannot set a formula for a cell of type " + getCellType());
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellBase
    protected void removeFormulaImpl() {
        if (getCellType() != CellType.FORMULA) {
            throw new AssertionError();
        }
        switch (getCachedFormulaResultType()) {
            case NUMERIC:
                double numericValue = ((NumericFormulaValue) this._value).getPreEvaluatedValue();
                this._value = new NumericValue();
                ((NumericValue) this._value).setValue(numericValue);
                return;
            case STRING:
                String stringValue = ((StringFormulaValue) this._value).getPreEvaluatedValue();
                this._value = new PlainStringValue();
                ((PlainStringValue) this._value).setValue(stringValue);
                return;
            case BOOLEAN:
                boolean booleanValue = ((BooleanFormulaValue) this._value).getPreEvaluatedValue();
                this._value = new BooleanValue();
                ((BooleanValue) this._value).setValue(booleanValue);
                return;
            case ERROR:
                byte errorValue = ((ErrorFormulaValue) this._value).getPreEvaluatedValue();
                this._value = new ErrorValue();
                ((ErrorValue) this._value).setValue(errorValue);
                return;
            default:
                throw new AssertionError();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getCellFormula() {
        if (this._value.getType() != CellType.FORMULA) {
            throw typeMismatch(CellType.FORMULA, this._value.getType(), false);
        }
        return ((FormulaValue) this._value).getValue();
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public double getNumericCellValue() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return 0.0d;
            case NUMERIC:
                return ((NumericValue) this._value).getValue();
            case FORMULA:
                FormulaValue fv = (FormulaValue) this._value;
                if (fv.getFormulaType() != CellType.NUMERIC) {
                    throw typeMismatch(CellType.NUMERIC, CellType.FORMULA, false);
                }
                return ((NumericFormulaValue) this._value).getPreEvaluatedValue();
            default:
                throw typeMismatch(CellType.NUMERIC, cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Date getDateCellValue() {
        CellType cellType = getCellType();
        if (cellType == CellType.BLANK) {
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

    @Override // org.apache.poi.ss.usermodel.Cell
    public RichTextString getRichStringCellValue() {
        CellType cellType = getCellType();
        if (getCellType() != CellType.STRING) {
            throw typeMismatch(CellType.STRING, cellType, false);
        }
        StringValue sval = (StringValue) this._value;
        if (sval.isRichText()) {
            return ((RichTextValue) this._value).getValue();
        }
        String plainText = getStringCellValue();
        return new XSSFRichTextString(plainText);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public String getStringCellValue() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return "";
            case STRING:
                if (((StringValue) this._value).isRichText()) {
                    return ((RichTextValue) this._value).getValue().getString();
                }
                return ((PlainStringValue) this._value).getValue();
            case FORMULA:
                FormulaValue fv = (FormulaValue) this._value;
                if (fv.getFormulaType() != CellType.STRING) {
                    throw typeMismatch(CellType.STRING, CellType.FORMULA, false);
                }
                if (this._value instanceof RichTextStringFormulaValue) {
                    return ((RichTextStringFormulaValue) this._value).getPreEvaluatedValue().getString();
                }
                return ((StringFormulaValue) this._value).getPreEvaluatedValue();
            default:
                throw typeMismatch(CellType.STRING, cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellValue(boolean value) {
        ensureTypeOrFormulaType(CellType.BOOLEAN);
        if (this._value.getType() == CellType.FORMULA) {
            ((BooleanFormulaValue) this._value).setPreEvaluatedValue(value);
        } else {
            ((BooleanValue) this._value).setValue(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellErrorValue(byte value) {
        if (this._value.getType() == CellType.FORMULA) {
            this._value = new ErrorFormulaValue(getCellFormula(), value);
        } else {
            this._value = new ErrorValue(value);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public boolean getBooleanCellValue() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return false;
            case BOOLEAN:
                return ((BooleanValue) this._value).getValue();
            case FORMULA:
                FormulaValue fv = (FormulaValue) this._value;
                if (fv.getFormulaType() != CellType.BOOLEAN) {
                    throw typeMismatch(CellType.BOOLEAN, CellType.FORMULA, false);
                }
                return ((BooleanFormulaValue) this._value).getPreEvaluatedValue();
            default:
                throw typeMismatch(CellType.BOOLEAN, cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public byte getErrorCellValue() {
        CellType cellType = getCellType();
        switch (cellType) {
            case BLANK:
                return (byte) 0;
            case ERROR:
                return ((ErrorValue) this._value).getValue();
            case FORMULA:
                FormulaValue fv = (FormulaValue) this._value;
                if (fv.getFormulaType() != CellType.ERROR) {
                    throw typeMismatch(CellType.ERROR, CellType.FORMULA, false);
                }
                return ((ErrorFormulaValue) this._value).getPreEvaluatedValue();
            default:
                throw typeMismatch(CellType.ERROR, cellType, false);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellStyle(CellStyle style) {
        this._style = style;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public CellStyle getCellStyle() {
        if (this._style == null) {
            CellStyle style = getDefaultCellStyleFromColumn();
            if (style == null) {
                SXSSFWorkbook wb = getSheet().getWorkbook();
                style = wb.getCellStyleAt(0);
            }
            this._style = style;
        }
        return this._style;
    }

    private CellStyle getDefaultCellStyleFromColumn() {
        SXSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        CellStyle style = sheet.getColumnStyle(getColumnIndex());
        return style;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setAsActiveCell() {
        getSheet().setActiveCell(getAddress());
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setCellComment(Comment comment) {
        setProperty(1, comment);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Comment getCellComment() {
        return (Comment) getPropertyValue(1);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeCellComment() {
        removeProperty(1);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public Hyperlink getHyperlink() {
        return (Hyperlink) getPropertyValue(2);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void setHyperlink(Hyperlink link) {
        if (link == null) {
            removeHyperlink();
            return;
        }
        setProperty(2, link);
        XSSFHyperlink xssfobj = (XSSFHyperlink) link;
        CellReference ref = new CellReference(getRowIndex(), getColumnIndex());
        xssfobj.setCellReference(ref);
        getSheet()._sh.addHyperlink(xssfobj);
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    public void removeHyperlink() {
        removeProperty(2);
        getSheet()._sh.removeHyperlink(getRowIndex(), getColumnIndex());
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    @NotImplemented
    public CellRangeAddress getArrayFormulaRange() {
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Cell
    @NotImplemented
    public boolean isPartOfArrayFormulaGroup() {
        return false;
    }

    public String toString() {
        switch (getCellType()) {
            case BLANK:
                return "";
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(this)) {
                    DataFormatter df = new DataFormatter();
                    df.setUseCachedValuesForFormulaCells(true);
                    return df.formatCellValue(this);
                }
                return Double.toString(getNumericCellValue());
            case STRING:
                return getRichStringCellValue().toString();
            case BOOLEAN:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case ERROR:
                return ErrorEval.getText(getErrorCellValue());
            case FORMULA:
                return getCellFormula();
            default:
                return "Unknown Cell Type: " + getCellType();
        }
    }

    void removeProperty(int type) {
        Property current = this._firstProperty;
        Property previous = null;
        while (current != null && current.getType() != type) {
            previous = current;
            current = current._next;
        }
        if (current != null) {
            if (previous != null) {
                previous._next = current._next;
            } else {
                this._firstProperty = current._next;
            }
        }
    }

    void setProperty(int type, Object value) {
        Property current;
        Property current2 = this._firstProperty;
        Property previous = null;
        while (current2 != null && current2.getType() != type) {
            previous = current2;
            current2 = current2._next;
        }
        if (current2 != null) {
            current2.setValue(value);
            return;
        }
        switch (type) {
            case 1:
                Property current3 = new CommentProperty(value);
                current = current3;
                break;
            case 2:
                Property current4 = new HyperlinkProperty(value);
                current = current4;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        if (previous != null) {
            previous._next = current;
        } else {
            this._firstProperty = current;
        }
    }

    Object getPropertyValue(int type) {
        return getPropertyValue(type, null);
    }

    Object getPropertyValue(int type, String defaultValue) {
        Property current = this._firstProperty;
        while (current != null && current.getType() != type) {
            current = current._next;
        }
        return current == null ? defaultValue : current.getValue();
    }

    void ensurePlainStringType() {
        if (this._value.getType() != CellType.STRING || ((StringValue) this._value).isRichText()) {
            this._value = new PlainStringValue();
        }
    }

    void ensureRichTextStringType() {
        if (this._value.getType() == CellType.FORMULA) {
            String formula = ((FormulaValue) this._value).getValue();
            this._value = new RichTextStringFormulaValue(formula, new XSSFRichTextString(""));
        } else if (this._value.getType() != CellType.STRING || !((StringValue) this._value).isRichText()) {
            this._value = new RichTextValue();
        }
    }

    void ensureType(CellType type) {
        if (this._value.getType() != type) {
            setType(type);
        }
    }

    void ensureTypeOrFormulaType(CellType type) {
        if (this._value.getType() == type) {
            if (type == CellType.STRING && ((StringValue) this._value).isRichText()) {
                setType(CellType.STRING);
                return;
            }
            return;
        }
        if (this._value.getType() == CellType.FORMULA) {
            if (((FormulaValue) this._value).getFormulaType() == type) {
                return;
            }
            switch (type) {
                case NUMERIC:
                    this._value = new NumericFormulaValue(getCellFormula(), 0.0d);
                    return;
                case STRING:
                    this._value = new StringFormulaValue(getCellFormula(), "");
                    return;
                case BOOLEAN:
                    this._value = new BooleanFormulaValue(getCellFormula(), false);
                    return;
                case ERROR:
                    this._value = new ErrorFormulaValue(getCellFormula(), FormulaError._NO_ERROR.getCode());
                    return;
                default:
                    throw new AssertionError();
            }
        }
        setType(type);
    }

    void setType(CellType type) {
        switch (type) {
            case BLANK:
                this._value = new BlankValue();
                return;
            case NUMERIC:
                this._value = new NumericValue();
                return;
            case STRING:
                PlainStringValue sval = new PlainStringValue();
                if (this._value != null) {
                    String str = convertCellValueToString();
                    sval.setValue(str);
                }
                this._value = sval;
                return;
            case BOOLEAN:
                BooleanValue bval = new BooleanValue();
                if (this._value != null) {
                    boolean val = convertCellValueToBoolean();
                    bval.setValue(val);
                }
                this._value = bval;
                return;
            case ERROR:
                this._value = new ErrorValue();
                return;
            case FORMULA:
                if (getCellType() == CellType.BLANK) {
                    this._value = new NumericFormulaValue("", 0.0d);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Illegal type " + type);
        }
    }

    private static IllegalStateException typeMismatch(CellType expectedTypeCode, CellType actualTypeCode, boolean isFormulaCell) {
        String msg = "Cannot get a " + expectedTypeCode + " value from a " + actualTypeCode + StringUtils.SPACE + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = getCachedFormulaResultType();
        }
        switch (cellType) {
            case BLANK:
            case ERROR:
                return false;
            case NUMERIC:
                return getNumericCellValue() != 0.0d;
            case STRING:
                String text = getStringCellValue();
                return Boolean.parseBoolean(text);
            case BOOLEAN:
                return getBooleanCellValue();
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    private String convertCellValueToString() {
        CellType cellType = getCellType();
        return convertCellValueToString(cellType);
    }

    private String convertCellValueToString(CellType cellType) {
        switch (cellType) {
            case BLANK:
                return "";
            case NUMERIC:
                return Double.toString(getNumericCellValue());
            case STRING:
                return getStringCellValue();
            case BOOLEAN:
                return getBooleanCellValue() ? "TRUE" : "FALSE";
            case ERROR:
                byte errVal = getErrorCellValue();
                return FormulaError.forInt(errVal).getString();
            case FORMULA:
                if (this._value != null) {
                    FormulaValue fv = (FormulaValue) this._value;
                    if (fv.getFormulaType() != CellType.FORMULA) {
                        return convertCellValueToString(fv.getFormulaType());
                    }
                }
                return "";
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static abstract class Property {
        static final int COMMENT = 1;
        static final int HYPERLINK = 2;
        Property _next;
        Object _value;

        abstract int getType();

        public Property(Object value) {
            this._value = value;
        }

        void setValue(Object value) {
            this._value = value;
        }

        Object getValue() {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class CommentProperty extends Property {
        public CommentProperty(Object value) {
            super(value);
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Property
        public int getType() {
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class HyperlinkProperty extends Property {
        public HyperlinkProperty(Object value) {
            super(value);
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Property
        public int getType() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class NumericValue implements Value {
        double _value;

        public NumericValue() {
            this._value = 0.0d;
        }

        public NumericValue(double _value) {
            this._value = _value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.NUMERIC;
        }

        void setValue(double value) {
            this._value = value;
        }

        double getValue() {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static abstract class StringValue implements Value {
        abstract boolean isRichText();

        StringValue() {
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.STRING;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class PlainStringValue extends StringValue {
        String _value;

        PlainStringValue() {
        }

        void setValue(String value) {
            this._value = value;
        }

        String getValue() {
            return this._value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.StringValue
        boolean isRichText() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class RichTextValue extends StringValue {
        RichTextString _value;

        RichTextValue() {
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.StringValue, org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.STRING;
        }

        void setValue(RichTextString value) {
            this._value = value;
        }

        RichTextString getValue() {
            return this._value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.StringValue
        boolean isRichText() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static abstract class FormulaValue implements Value {
        String _value;

        abstract CellType getFormulaType();

        public FormulaValue(String _value) {
            this._value = _value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.FORMULA;
        }

        void setValue(String value) {
            this._value = value;
        }

        String getValue() {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class NumericFormulaValue extends FormulaValue {
        double _preEvaluatedValue;

        public NumericFormulaValue(String formula, double _preEvaluatedValue) {
            super(formula);
            this._preEvaluatedValue = _preEvaluatedValue;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.FormulaValue
        CellType getFormulaType() {
            return CellType.NUMERIC;
        }

        void setPreEvaluatedValue(double value) {
            this._preEvaluatedValue = value;
        }

        double getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class StringFormulaValue extends FormulaValue {
        String _preEvaluatedValue;

        public StringFormulaValue(String formula, String value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.FormulaValue
        CellType getFormulaType() {
            return CellType.STRING;
        }

        void setPreEvaluatedValue(String value) {
            this._preEvaluatedValue = value;
        }

        String getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class RichTextStringFormulaValue extends FormulaValue {
        RichTextString _preEvaluatedValue;

        public RichTextStringFormulaValue(String formula, RichTextString value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.FormulaValue
        CellType getFormulaType() {
            return CellType.STRING;
        }

        void setPreEvaluatedValue(RichTextString value) {
            this._preEvaluatedValue = value;
        }

        RichTextString getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class BooleanFormulaValue extends FormulaValue {
        boolean _preEvaluatedValue;

        public BooleanFormulaValue(String formula, boolean value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.FormulaValue
        CellType getFormulaType() {
            return CellType.BOOLEAN;
        }

        void setPreEvaluatedValue(boolean value) {
            this._preEvaluatedValue = value;
        }

        boolean getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class ErrorFormulaValue extends FormulaValue {
        byte _preEvaluatedValue;

        public ErrorFormulaValue(String formula, byte value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.FormulaValue
        CellType getFormulaType() {
            return CellType.ERROR;
        }

        void setPreEvaluatedValue(byte value) {
            this._preEvaluatedValue = value;
        }

        byte getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class BlankValue implements Value {
        BlankValue() {
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.BLANK;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class BooleanValue implements Value {
        boolean _value;

        public BooleanValue() {
            this._value = false;
        }

        public BooleanValue(boolean _value) {
            this._value = _value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.BOOLEAN;
        }

        void setValue(boolean value) {
            this._value = value;
        }

        boolean getValue() {
            return this._value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class ErrorValue implements Value {
        byte _value;

        public ErrorValue() {
            this._value = FormulaError._NO_ERROR.getCode();
        }

        public ErrorValue(byte _value) {
            this._value = _value;
        }

        @Override // org.apache.poi.xssf.streaming.SXSSFCell.Value
        public CellType getType() {
            return CellType.ERROR;
        }

        void setValue(byte value) {
            this._value = value;
        }

        byte getValue() {
            return this._value;
        }
    }
}
