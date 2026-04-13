package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XSSFExcelExtractor implements POIXMLTextExtractor, ExcelExtractor {
    public static final List<XSSFRelation> SUPPORTED_TYPES = Collections.unmodifiableList(Arrays.asList(XSSFRelation.WORKBOOK, XSSFRelation.MACRO_TEMPLATE_WORKBOOK, XSSFRelation.MACRO_ADDIN_WORKBOOK, XSSFRelation.TEMPLATE_WORKBOOK, XSSFRelation.MACROS_WORKBOOK));
    private final DataFormatter dataFormatter;
    private boolean doCloseFilesystem;
    private boolean formulasNotResults;
    private boolean includeCellComments;
    private boolean includeHeadersFooters;
    private boolean includeSheetNames;
    private boolean includeTextBoxes;
    private Locale locale;
    private final XSSFWorkbook workbook;

    public XSSFExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        this(new XSSFWorkbook(container));
    }

    public XSSFExcelExtractor(XSSFWorkbook workbook) {
        this.includeSheetNames = true;
        this.includeHeadersFooters = true;
        this.includeTextBoxes = true;
        this.doCloseFilesystem = true;
        this.workbook = workbook;
        this.dataFormatter = new DataFormatter();
        this.dataFormatter.setUseCachedValuesForFormulaCells(true);
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this.includeSheetNames = includeSheetNames;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setFormulasNotResults(boolean formulasNotResults) {
        this.formulasNotResults = formulasNotResults;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeCellComments(boolean includeCellComments) {
        this.includeCellComments = includeCellComments;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        this.includeHeadersFooters = includeHeadersFooters;
    }

    public void setIncludeTextBoxes(boolean includeTextBoxes) {
        this.includeTextBoxes = includeTextBoxes;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        DataFormatter formatter;
        XSSFDrawing drawing;
        if (this.locale == null) {
            formatter = new DataFormatter();
        } else {
            formatter = new DataFormatter(this.locale);
        }
        StringBuilder text = new StringBuilder(64);
        Iterator<Sheet> it = this.workbook.iterator();
        while (it.hasNext()) {
            Sheet sh = it.next();
            XSSFSheet sheet = (XSSFSheet) sh;
            if (this.includeSheetNames) {
                text.append(sheet.getSheetName()).append(StringUtils.LF);
            }
            if (this.includeHeadersFooters) {
                text.append(extractHeaderFooter(sheet.getFirstHeader()));
                text.append(extractHeaderFooter(sheet.getOddHeader()));
                text.append(extractHeaderFooter(sheet.getEvenHeader()));
            }
            Iterator<Row> it2 = sheet.iterator();
            while (it2.hasNext()) {
                Row row = it2.next();
                Iterator<Cell> ri = row.cellIterator();
                while (ri.hasNext()) {
                    Cell cell = ri.next();
                    if (cell.getCellType() == CellType.FORMULA) {
                        if (this.formulasNotResults) {
                            String contents = cell.getCellFormula();
                            checkMaxTextSize(text, contents);
                            text.append(contents);
                        } else if (cell.getCachedFormulaResultType() == CellType.STRING) {
                            handleStringCell(text, cell);
                        } else {
                            handleNonStringCell(text, cell, formatter);
                        }
                    } else if (cell.getCellType() == CellType.STRING) {
                        handleStringCell(text, cell);
                    } else {
                        handleNonStringCell(text, cell, formatter);
                    }
                    Comment comment = cell.getCellComment();
                    if (this.includeCellComments && comment != null) {
                        String commentText = comment.getString().getString().replace('\n', Chars.SPACE);
                        checkMaxTextSize(text, commentText);
                        text.append(" Comment by ").append(comment.getAuthor()).append(": ").append(commentText);
                    }
                    if (ri.hasNext()) {
                        text.append("\t");
                    }
                }
                text.append(StringUtils.LF);
            }
            if (this.includeTextBoxes && (drawing = sheet.getDrawingPatriarch()) != null) {
                for (XSSFShape shape : drawing.getShapes()) {
                    if (shape instanceof XSSFSimpleShape) {
                        String boxText = ((XSSFSimpleShape) shape).getText();
                        if (!boxText.isEmpty()) {
                            text.append(boxText);
                            text.append('\n');
                        }
                    }
                }
            }
            if (this.includeHeadersFooters) {
                text.append(extractHeaderFooter(sheet.getFirstFooter()));
                text.append(extractHeaderFooter(sheet.getOddFooter()));
                text.append(extractHeaderFooter(sheet.getEvenFooter()));
            }
        }
        return text.toString();
    }

    private void handleStringCell(StringBuilder text, Cell cell) {
        String contents = cell.getRichStringCellValue().getString();
        checkMaxTextSize(text, contents);
        text.append(contents);
    }

    private void handleNonStringCell(StringBuilder text, Cell cell, DataFormatter formatter) {
        CellStyle cs;
        CellType type = cell.getCellType();
        if (type == CellType.FORMULA && (type = cell.getCachedFormulaResultType()) == CellType.STRING) {
            handleStringCell(text, cell);
            return;
        }
        if (type == CellType.NUMERIC && (cs = cell.getCellStyle()) != null && cs.getDataFormatString() != null) {
            String contents = formatter.formatRawCellContents(cell.getNumericCellValue(), cs.getDataFormat(), cs.getDataFormatString());
            checkMaxTextSize(text, contents);
            text.append(contents);
            return;
        }
        String contents2 = this.dataFormatter.formatCellValue(cell);
        if (contents2 != null) {
            if (type == CellType.ERROR) {
                contents2 = "ERROR:" + contents2;
            }
            checkMaxTextSize(text, contents2);
            text.append(contents2);
        }
    }

    private String extractHeaderFooter(HeaderFooter hf) {
        return org.apache.poi.hssf.extractor.ExcelExtractor._extractHeaderFooter(hf);
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor, org.apache.poi.extractor.POITextExtractor
    public XSSFWorkbook getDocument() {
        return this.workbook;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public XSSFWorkbook getFilesystem() {
        return this.workbook;
    }
}
