package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBCommentsTable;
import org.apache.poi.xssf.binary.XSSFBHyperlinksTable;
import org.apache.poi.xssf.binary.XSSFBSharedStringsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFBReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class XSSFBEventBasedExcelExtractor extends XSSFEventBasedExcelExtractor {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) XSSFBEventBasedExcelExtractor.class);
    public static final List<XSSFRelation> SUPPORTED_TYPES = Collections.singletonList(XSSFRelation.XLSB_BINARY_WORKBOOK);
    private boolean handleHyperlinksInCells;

    public XSSFBEventBasedExcelExtractor(String path) throws XmlException, OpenXML4JException, IOException {
        super(path);
    }

    public XSSFBEventBasedExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        super(container);
    }

    public void setHandleHyperlinksInCells(boolean handleHyperlinksInCells) {
        this.handleHyperlinksInCells = handleHyperlinksInCells;
    }

    @Override // org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor, org.apache.poi.ss.extractor.ExcelExtractor
    public void setFormulasNotResults(boolean formulasNotResults) {
        throw new IllegalArgumentException("Not currently supported");
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsExtractor, XSSFBStylesTable styles, XSSFBCommentsTable comments, SharedStrings strings, InputStream sheetInputStream) throws IOException {
        DataFormatter formatter;
        if (getLocale() == null) {
            formatter = new DataFormatter();
        } else {
            formatter = new DataFormatter(getLocale());
        }
        XSSFBSheetHandler xssfbSheetHandler = new XSSFBSheetHandler(sheetInputStream, styles, comments, strings, sheetContentsExtractor, formatter, getFormulasNotResults());
        xssfbSheetHandler.parse();
    }

    @Override // org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor, org.apache.poi.extractor.POITextExtractor
    public String getText() {
        Throwable th;
        XSSFBCommentsTable xSSFBSheetComments;
        try {
            XSSFBSharedStringsTable strings = new XSSFBSharedStringsTable(getPackage());
            XSSFBReader xssfbReader = new XSSFBReader(getPackage());
            XSSFBStylesTable styles = xssfbReader.getXSSFBStylesTable();
            XSSFBReader.SheetIterator iter = (XSSFBReader.SheetIterator) xssfbReader.getSheetsData();
            StringBuilder text = new StringBuilder(64);
            XSSFEventBasedExcelExtractor.SheetTextExtractor sheetExtractor = new XSSFEventBasedExcelExtractor.SheetTextExtractor();
            XSSFBHyperlinksTable hyperlinksTable = null;
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                try {
                    if (getIncludeSheetNames()) {
                        try {
                            text.append(iter.getSheetName());
                            text.append('\n');
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } finally {
                            }
                        }
                    }
                    XSSFBHyperlinksTable hyperlinksTable2 = this.handleHyperlinksInCells ? new XSSFBHyperlinksTable(iter.getSheetPart()) : hyperlinksTable;
                    try {
                        if (getIncludeCellComments()) {
                            try {
                                xSSFBSheetComments = iter.getXSSFBSheetComments();
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        } else {
                            xSSFBSheetComments = null;
                        }
                        XSSFBCommentsTable comments = xSSFBSheetComments;
                        try {
                            processSheet(sheetExtractor, styles, comments, strings, stream);
                            XSSFEventBasedExcelExtractor.SheetTextExtractor sheetExtractor2 = sheetExtractor;
                            try {
                                if (getIncludeHeadersFooters()) {
                                    sheetExtractor2.appendHeaderText(text);
                                }
                                sheetExtractor2.appendCellText(text);
                                if (getIncludeTextBoxes()) {
                                    processShapes(iter.getShapes(), text);
                                }
                                if (getIncludeHeadersFooters()) {
                                    sheetExtractor2.appendFooterText(text);
                                }
                                sheetExtractor2.reset();
                                if (stream != null) {
                                    try {
                                        stream.close();
                                    } catch (IOException e) {
                                        e = e;
                                        LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                        return "";
                                    } catch (OpenXML4JException e2) {
                                        e = e2;
                                        LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                        return "";
                                    } catch (SAXException e3) {
                                        e = e3;
                                        LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                        return "";
                                    }
                                }
                                sheetExtractor = sheetExtractor2;
                                hyperlinksTable = hyperlinksTable2;
                            } catch (Throwable th4) {
                                th = th4;
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
            return text.toString();
        } catch (IOException | OpenXML4JException | SAXException e4) {
            e = e4;
        }
    }
}
