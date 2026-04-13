package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* loaded from: classes10.dex */
public class XSSFEventBasedExcelExtractor implements POIXMLTextExtractor, ExcelExtractor {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) XSSFEventBasedExcelExtractor.class);
    protected boolean concatenatePhoneticRuns;
    protected final OPCPackage container;
    private boolean doCloseFilesystem;
    protected boolean formulasNotResults;
    protected boolean includeCellComments;
    protected boolean includeHeadersFooters;
    protected boolean includeSheetNames;
    protected boolean includeTextBoxes;
    protected Locale locale;
    protected final POIXMLProperties properties;

    public XSSFEventBasedExcelExtractor(String path) throws XmlException, OpenXML4JException, IOException {
        this(OPCPackage.open(path));
    }

    public XSSFEventBasedExcelExtractor(OPCPackage container) throws XmlException, OpenXML4JException, IOException {
        this.includeTextBoxes = true;
        this.includeSheetNames = true;
        this.includeHeadersFooters = true;
        this.concatenatePhoneticRuns = true;
        this.doCloseFilesystem = true;
        this.container = container;
        this.properties = new POIXMLProperties(container);
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this.includeSheetNames = includeSheetNames;
    }

    public boolean getIncludeSheetNames() {
        return this.includeSheetNames;
    }

    public void setFormulasNotResults(boolean formulasNotResults) {
        this.formulasNotResults = formulasNotResults;
    }

    public boolean getFormulasNotResults() {
        return this.formulasNotResults;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        this.includeHeadersFooters = includeHeadersFooters;
    }

    public boolean getIncludeHeadersFooters() {
        return this.includeHeadersFooters;
    }

    public void setIncludeTextBoxes(boolean includeTextBoxes) {
        this.includeTextBoxes = includeTextBoxes;
    }

    public boolean getIncludeTextBoxes() {
        return this.includeTextBoxes;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeCellComments(boolean includeCellComments) {
        this.includeCellComments = includeCellComments;
    }

    public boolean getIncludeCellComments() {
        return this.includeCellComments;
    }

    public void setConcatenatePhoneticRuns(boolean concatenatePhoneticRuns) {
        this.concatenatePhoneticRuns = concatenatePhoneticRuns;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor
    public OPCPackage getPackage() {
        return this.container;
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor
    public POIXMLProperties.CoreProperties getCoreProperties() {
        return this.properties.getCoreProperties();
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor
    public POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return this.properties.getExtendedProperties();
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor
    public POIXMLProperties.CustomProperties getCustomProperties() {
        return this.properties.getCustomProperties();
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsExtractor, Styles styles, Comments comments, SharedStrings strings, InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter;
        ParserConfigurationException e;
        XMLReader sheetParser;
        if (this.locale == null) {
            formatter = new DataFormatter();
        } else {
            formatter = new DataFormatter(this.locale);
        }
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            sheetParser = XMLHelper.newXMLReader();
        } catch (ParserConfigurationException e2) {
            e = e2;
        }
        try {
            ContentHandler handler = new XSSFSheetXMLHandler(styles, comments, strings, sheetContentsExtractor, formatter, this.formulasNotResults);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch (ParserConfigurationException e3) {
            e = e3;
            throw new IllegalStateException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    protected SharedStrings createSharedStringsTable(XSSFReader xssfReader, OPCPackage container) throws IOException, SAXException {
        return new ReadOnlySharedStringsTable(container, this.concatenatePhoneticRuns);
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        try {
            XSSFReader xssfReader = new XSSFReader(this.container);
            SharedStrings strings = createSharedStringsTable(xssfReader, this.container);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            StringBuilder text = new StringBuilder(64);
            SheetTextExtractor sheetExtractor = new SheetTextExtractor();
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                try {
                    if (this.includeSheetNames) {
                        text.append(iter.getSheetName());
                        text.append('\n');
                    }
                    Comments comments = this.includeCellComments ? iter.getSheetComments() : null;
                    try {
                        processSheet(sheetExtractor, styles, comments, strings, stream);
                        if (this.includeHeadersFooters) {
                            sheetExtractor.appendHeaderText(text);
                        }
                        sheetExtractor.appendCellText(text);
                        if (this.includeTextBoxes) {
                            processShapes(iter.getShapes(), text);
                        }
                        if (this.includeHeadersFooters) {
                            sheetExtractor.appendFooterText(text);
                        }
                        sheetExtractor.reset();
                        if (stream != null) {
                            try {
                                stream.close();
                            } catch (IOException e) {
                                e = e;
                                LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                return "";
                            } catch (NumberFormatException e2) {
                                e = e2;
                                LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                return "";
                            } catch (OpenXML4JException e3) {
                                e = e3;
                                LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                return "";
                            } catch (SAXException e4) {
                                e = e4;
                                LOGGER.atWarn().withThrowable(e).log("Failed to load text");
                                return "";
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            return text.toString();
        } catch (IOException | NumberFormatException | OpenXML4JException | SAXException e5) {
            e = e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processShapes(List<XSSFShape> shapes, StringBuilder text) {
        String sText;
        if (shapes == null) {
            return;
        }
        for (XSSFShape shape : shapes) {
            if ((shape instanceof XSSFSimpleShape) && (sText = ((XSSFSimpleShape) shape).getText()) != null && !sText.isEmpty()) {
                text.append(sText).append('\n');
            }
        }
    }

    @Override // org.apache.poi.ooxml.extractor.POIXMLTextExtractor, org.apache.poi.extractor.POITextExtractor
    public POIXMLDocument getDocument() {
        return null;
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
    public OPCPackage getFilesystem() {
        return this.container;
    }

    /* loaded from: classes10.dex */
    protected class SheetTextExtractor implements XSSFSheetXMLHandler.SheetContentsHandler {
        private final Map<String, String> headerFooterMap;
        private final StringBuilder output = new StringBuilder(64);
        private boolean firstCellOfRow = true;

        /* JADX INFO: Access modifiers changed from: protected */
        public SheetTextExtractor() {
            this.headerFooterMap = XSSFEventBasedExcelExtractor.this.includeHeadersFooters ? new HashMap() : null;
        }

        @Override // org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler
        public void startRow(int rowNum) {
            this.firstCellOfRow = true;
        }

        @Override // org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler
        public void endRow(int rowNum) {
            this.output.append('\n');
        }

        @Override // org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler
        public void cell(String cellRef, String formattedValue, XSSFComment comment) {
            if (this.firstCellOfRow) {
                this.firstCellOfRow = false;
            } else {
                this.output.append('\t');
            }
            if (formattedValue != null) {
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, formattedValue);
                this.output.append(formattedValue);
            }
            if (XSSFEventBasedExcelExtractor.this.includeCellComments && comment != null) {
                String commentText = comment.getString().getString().replace('\n', Chars.SPACE);
                this.output.append(formattedValue != null ? " Comment by " : "Comment by ");
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, commentText);
                if (commentText.startsWith(comment.getAuthor() + ": ")) {
                    this.output.append(commentText);
                } else {
                    this.output.append(comment.getAuthor()).append(": ").append(commentText);
                }
            }
        }

        @Override // org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler
        public void headerFooter(String text, boolean isHeader, String tagName) {
            if (this.headerFooterMap != null) {
                this.headerFooterMap.put(tagName, text);
            }
        }

        private void appendHeaderFooterText(StringBuilder buffer, String name) {
            String text = this.headerFooterMap.get(name);
            if (text != null && !text.isEmpty()) {
                buffer.append(handleHeaderFooterDelimiter(handleHeaderFooterDelimiter(handleHeaderFooterDelimiter(text, "&L"), "&C"), "&R")).append('\n');
            }
        }

        private String handleHeaderFooterDelimiter(String text, String delimiter) {
            int index = text.indexOf(delimiter);
            if (index == 0) {
                return text.substring(2);
            }
            if (index > 0) {
                return text.substring(0, index) + "\t" + text.substring(index + 2);
            }
            return text;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendHeaderText(StringBuilder buffer) {
            appendHeaderFooterText(buffer, "firstHeader");
            appendHeaderFooterText(buffer, "oddHeader");
            appendHeaderFooterText(buffer, "evenHeader");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendFooterText(StringBuilder buffer) {
            appendHeaderFooterText(buffer, "firstFooter");
            appendHeaderFooterText(buffer, "oddFooter");
            appendHeaderFooterText(buffer, "evenFooter");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendCellText(StringBuilder buffer) {
            XSSFEventBasedExcelExtractor.this.checkMaxTextSize(buffer, this.output.toString());
            buffer.append((CharSequence) this.output);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void reset() {
            this.output.setLength(0);
            this.firstCellOfRow = true;
            if (this.headerFooterMap != null) {
                this.headerFooterMap.clear();
            }
        }
    }
}
