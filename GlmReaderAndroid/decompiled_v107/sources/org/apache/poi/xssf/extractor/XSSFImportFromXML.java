package org.apache.poi.xssf.extractor;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class XSSFImportFromXML {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFImportFromXML.class);
    private final XSSFMap _map;

    public XSSFImportFromXML(XSSFMap map) {
        this._map = map;
    }

    public void importFromXML(String xmlInputString) throws SAXException, XPathExpressionException, IOException {
        DocumentBuilder builder = DocumentHelper.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlInputString.trim())));
        List<XSSFSingleXmlCell> singleXmlCells = this._map.getRelatedSingleXMLCell();
        List<XSSFTable> tables = this._map.getRelatedTables();
        XPath xpath = XPathHelper.getFactory().newXPath();
        xpath.setNamespaceContext(new DefaultNamespaceContext(doc));
        for (XSSFSingleXmlCell singleXmlCell : singleXmlCells) {
            String xmlDataType = singleXmlCell.getXmlDataType();
            String xpathString = singleXmlCell.getXpath();
            Node result = (Node) xpath.evaluate(xpathString, doc, XPathConstants.NODE);
            if (result != null) {
                String textContent = result.getTextContent();
                LOG.atDebug().log("Extracting with xpath {} : value is '{}'", xpathString, textContent);
                XSSFCell cell = singleXmlCell.getReferencedCell();
                LOG.atDebug().log("Setting '{}' to cell {}-{} in sheet {}", textContent, Unbox.box(cell.getColumnIndex()), Unbox.box(cell.getRowIndex()), cell.getSheet().getSheetName());
                setCellValue(textContent, cell, xmlDataType);
            }
        }
        for (XSSFTable table : tables) {
            String commonXPath = table.getCommonXpath();
            NodeList result2 = (NodeList) xpath.evaluate(commonXPath, doc, XPathConstants.NODESET);
            int rowOffset = table.getStartCellReference().getRow() + table.getHeaderRowCount();
            int columnOffset = table.getStartCellReference().getCol();
            table.setDataRowCount(result2.getLength());
            for (int i = 0; i < result2.getLength(); i++) {
                Node singleNode = result2.item(i).cloneNode(true);
                for (XSSFTableColumn tableColumn : table.getColumns()) {
                    XSSFXmlColumnPr xmlColumnPr = tableColumn.getXmlColumnPr();
                    if (xmlColumnPr != null) {
                        int rowId = rowOffset + i;
                        DocumentBuilder builder2 = builder;
                        int columnId = columnOffset + tableColumn.getColumnIndex();
                        Document doc2 = doc;
                        String localXPath = xmlColumnPr.getLocalXPath();
                        List<XSSFSingleXmlCell> singleXmlCells2 = singleXmlCells;
                        List<XSSFTable> tables2 = tables;
                        String localXPath2 = localXPath.substring(localXPath.indexOf(47, 1) + 1);
                        String value = (String) xpath.evaluate(localXPath2, singleNode, XPathConstants.STRING);
                        LOG.atDebug().log("Extracting with xpath {} : value is '{}'", localXPath2, value);
                        XSSFRow row = table.getXSSFSheet().getRow(rowId);
                        if (row == null) {
                            row = table.getXSSFSheet().createRow(rowId);
                        }
                        XSSFCell cell2 = row.getCell(columnId);
                        if (cell2 == null) {
                            cell2 = row.createCell(columnId);
                        }
                        LOG.atDebug().log("Setting '{}' to cell {}-{} in sheet {}", value, Unbox.box(cell2.getColumnIndex()), Unbox.box(cell2.getRowIndex()), table.getXSSFSheet().getSheetName());
                        setCellValue(value, cell2, xmlColumnPr.getXmlDataType());
                        doc = doc2;
                        builder = builder2;
                        singleXmlCells = singleXmlCells2;
                        tables = tables2;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum DataType {
        BOOLEAN("boolean"),
        DOUBLE(XmlErrorCodes.DOUBLE),
        INTEGER(XmlErrorCodes.INT, "unsignedInt", "integer"),
        STRING(TypedValues.Custom.S_STRING),
        DATE(XmlErrorCodes.DATE);

        private Set<String> xmlDataTypes;

        DataType(String... xmlDataTypes) {
            this.xmlDataTypes = new HashSet(Arrays.asList(xmlDataTypes));
        }

        public static DataType getDataType(String xmlDataType) {
            for (DataType dataType : values()) {
                if (dataType.xmlDataTypes.contains(xmlDataType)) {
                    return dataType;
                }
            }
            return null;
        }
    }

    private void setCellValue(String value, XSSFCell cell, String xmlDataType) {
        DataType type = DataType.getDataType(xmlDataType);
        try {
            if (!value.isEmpty() && type != null) {
                switch (type) {
                    case BOOLEAN:
                        cell.setCellValue(Boolean.parseBoolean(value));
                        return;
                    case DOUBLE:
                        cell.setCellValue(Double.parseDouble(value));
                        return;
                    case INTEGER:
                        cell.setCellValue(Integer.parseInt(value));
                        return;
                    case DATE:
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", LocaleUtil.getUserLocale());
                        Date date = sdf.parse(value);
                        cell.setCellValue(date);
                        if (!DateUtil.isValidExcelDate(cell.getNumericCellValue())) {
                            cell.setCellValue(value);
                            return;
                        }
                        return;
                    default:
                        cell.setCellValue(value.trim());
                        return;
                }
            }
            cell.setCellValue((String) null);
        } catch (IllegalArgumentException | ParseException e) {
            throw new IllegalArgumentException(String.format(LocaleUtil.getUserLocale(), "Unable to format value '%s' as %s for cell %s", value, type, new CellReference(cell).formatAsString()));
        }
    }

    /* loaded from: classes10.dex */
    private static final class DefaultNamespaceContext implements NamespaceContext {
        private final Element _docElem;

        public DefaultNamespaceContext(Document doc) {
            this._docElem = doc.getDocumentElement();
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String prefix) {
            return getNamespaceForPrefix(prefix);
        }

        private String getNamespaceForPrefix(String prefix) {
            if (prefix.equals("xml")) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            Node parent = this._docElem;
            while (parent != null) {
                int type = parent.getNodeType();
                if (type == 1) {
                    if (parent.getNodeName().startsWith(prefix + ":")) {
                        return parent.getNamespaceURI();
                    }
                    NamedNodeMap nnm = parent.getAttributes();
                    for (int i = 0; i < nnm.getLength(); i++) {
                        Node attr = nnm.item(i);
                        String aname = attr.getNodeName();
                        boolean isPrefix = aname.startsWith(Sax2Dom.XMLNS_STRING);
                        if (isPrefix || aname.equals(Sax2Dom.XMLNS_PREFIX)) {
                            int index = aname.indexOf(58);
                            String p = isPrefix ? aname.substring(index + 1) : "";
                            if (p.equals(prefix)) {
                                return attr.getNodeValue();
                            }
                        }
                    }
                    parent = parent.getParentNode();
                } else if (type != 5) {
                    return null;
                }
            }
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator<String> getPrefixes(String val) {
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String uri) {
            return null;
        }
    }
}
