package org.apache.poi.ss.usermodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public interface Cell {
    CellAddress getAddress();

    CellRangeAddress getArrayFormulaRange();

    boolean getBooleanCellValue();

    CellType getCachedFormulaResultType();

    Comment getCellComment();

    String getCellFormula();

    CellStyle getCellStyle();

    CellType getCellType();

    int getColumnIndex();

    Date getDateCellValue();

    byte getErrorCellValue();

    Hyperlink getHyperlink();

    LocalDateTime getLocalDateTimeCellValue();

    double getNumericCellValue();

    RichTextString getRichStringCellValue();

    Row getRow();

    int getRowIndex();

    Sheet getSheet();

    String getStringCellValue();

    boolean isPartOfArrayFormulaGroup();

    void removeCellComment();

    void removeFormula() throws IllegalStateException;

    void removeHyperlink();

    void setAsActiveCell();

    void setBlank();

    void setCellComment(Comment comment);

    void setCellErrorValue(byte b);

    void setCellFormula(String str) throws FormulaParseException, IllegalStateException;

    void setCellStyle(CellStyle cellStyle);

    @Removal(version = "5.0")
    @Deprecated
    void setCellType(CellType cellType);

    void setCellValue(double d);

    void setCellValue(String str);

    void setCellValue(LocalDateTime localDateTime);

    void setCellValue(Calendar calendar);

    void setCellValue(Date date);

    void setCellValue(RichTextString richTextString);

    void setCellValue(boolean z);

    void setHyperlink(Hyperlink hyperlink);

    default void setCellValue(LocalDate value) {
        setCellValue(value == null ? null : value.atStartOfDay());
    }
}
