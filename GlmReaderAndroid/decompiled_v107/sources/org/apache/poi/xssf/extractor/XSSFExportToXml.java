package org.apache.poi.xssf.extractor;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public class XSSFExportToXml implements Comparator<String> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFExportToXml.class);
    private final HashMap<String, Integer> indexMap = new HashMap<>();
    private XSSFMap map;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    private interface SecurityFeature {
        void accept(String str) throws SAXException;
    }

    public XSSFExportToXml(XSSFMap map) {
        this.map = map;
    }

    public void exportToXML(OutputStream os, boolean validate) throws SAXException, TransformerException {
        exportToXML(os, "UTF-8", validate);
    }

    public void exportToXML(OutputStream os, String encoding, boolean validate) throws SAXException, TransformerException {
        Element root;
        List<XSSFSingleXmlCell> singleXMLCells;
        List<XSSFTable> tables;
        XSSFRow row;
        Iterator<XSSFTableColumn> it;
        String str;
        List<XSSFSingleXmlCell> singleXMLCells2 = this.map.getRelatedSingleXMLCell();
        List<XSSFTable> tables2 = this.map.getRelatedTables();
        String rootElement = this.map.getCtMap().getRootElement();
        Document doc = DocumentHelper.createDocument();
        String str2 = "";
        if (isNamespaceDeclared()) {
            root = doc.createElementNS(getNamespace(), rootElement);
        } else {
            root = doc.createElementNS("", rootElement);
        }
        doc.appendChild(root);
        List<String> xpaths = new Vector<>();
        Map<String, XSSFSingleXmlCell> singleXmlCellsMappings = new HashMap<>();
        Map<String, XSSFTable> tableMappings = new HashMap<>();
        for (XSSFSingleXmlCell simpleXmlCell : singleXMLCells2) {
            xpaths.add(simpleXmlCell.getXpath());
            singleXmlCellsMappings.put(simpleXmlCell.getXpath(), simpleXmlCell);
        }
        for (XSSFTable table : tables2) {
            String commonXPath = table.getCommonXpath();
            xpaths.add(commonXPath);
            tableMappings.put(commonXPath, table);
        }
        this.indexMap.clear();
        xpaths.sort(this);
        this.indexMap.clear();
        for (String xpath : xpaths) {
            XSSFSingleXmlCell simpleXmlCell2 = singleXmlCellsMappings.get(xpath);
            XSSFTable table2 = tableMappings.get(xpath);
            if (xpath.matches(".*\\[.*")) {
                singleXMLCells = singleXMLCells2;
                tables = tables2;
            } else {
                if (simpleXmlCell2 == null) {
                    singleXMLCells = singleXMLCells2;
                } else {
                    XSSFCell cell = simpleXmlCell2.getReferencedCell();
                    if (cell == null) {
                        singleXMLCells = singleXMLCells2;
                    } else {
                        singleXMLCells = singleXMLCells2;
                        Node currentNode = getNodeByXPath(xpath, doc.getFirstChild(), doc, false);
                        mapCellOnNode(cell, currentNode);
                        if (str2.equals(currentNode.getTextContent()) && currentNode.getParentNode() != null) {
                            currentNode.getParentNode().removeChild(currentNode);
                        }
                    }
                }
                if (table2 == null) {
                    tables = tables2;
                } else {
                    List<XSSFTableColumn> tableColumns = table2.getColumns();
                    XSSFSheet sheet = table2.getXSSFSheet();
                    int startRow = table2.getStartCellReference().getRow() + table2.getHeaderRowCount();
                    int endRow = table2.getEndCellReference().getRow();
                    tables = tables2;
                    int i = startRow;
                    while (i <= endRow) {
                        int endRow2 = endRow;
                        XSSFRow row2 = sheet.getRow(i);
                        int i2 = i;
                        String rootElement2 = rootElement;
                        Element root2 = root;
                        Node tableRootNode = getNodeByXPath(table2.getCommonXpath(), doc.getFirstChild(), doc, true);
                        short startColumnIndex = table2.getStartCellReference().getCol();
                        Iterator<XSSFTableColumn> it2 = tableColumns.iterator();
                        while (it2.hasNext()) {
                            XSSFTableColumn tableColumn = it2.next();
                            short startColumnIndex2 = startColumnIndex;
                            XSSFCell cell2 = row2.getCell(startColumnIndex2 + tableColumn.getColumnIndex());
                            if (cell2 == null) {
                                row = row2;
                                it = it2;
                                str = str2;
                            } else {
                                XSSFXmlColumnPr xmlColumnPr = tableColumn.getXmlColumnPr();
                                if (xmlColumnPr == null) {
                                    row = row2;
                                    it = it2;
                                    str = str2;
                                } else {
                                    row = row2;
                                    String localXPath = xmlColumnPr.getLocalXPath();
                                    it = it2;
                                    str = str2;
                                    mapCellOnNode(cell2, getNodeByXPath(localXPath, tableRootNode, doc, false));
                                }
                            }
                            str2 = str;
                            startColumnIndex = startColumnIndex2;
                            row2 = row;
                            it2 = it;
                        }
                        i = i2 + 1;
                        endRow = endRow2;
                        rootElement = rootElement2;
                        root = root2;
                    }
                }
            }
            singleXMLCells2 = singleXMLCells;
            str2 = str2;
            tables2 = tables;
            rootElement = rootElement;
            root = root;
        }
        boolean isValid = validate ? isValid(doc) : true;
        if (isValid) {
            Transformer trans = XMLHelper.newTransformer();
            trans.setOutputProperty("omit-xml-declaration", BooleanUtils.YES);
            trans.setOutputProperty("indent", BooleanUtils.YES);
            trans.setOutputProperty("encoding", encoding);
            StreamResult result = new StreamResult(os);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
        }
    }

    private boolean isValid(Document xml) throws SAXException {
        try {
            SchemaFactory factory = XMLHelper.getSchemaFactory();
            Source source = new DOMSource(this.map.getSchema());
            Schema schema = factory.newSchema(source);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(xml));
            return true;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("document is not valid");
            return false;
        }
    }

    private void mapCellOnNode(XSSFCell cell, Node node) {
        String value = "";
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = "" + cell.getBooleanCellValue();
                break;
            case ERROR:
                value = cell.getErrorCellString();
                break;
            case FORMULA:
                if (cell.getCachedFormulaResultType() == CellType.STRING) {
                    value = cell.getStringCellValue();
                    break;
                } else if (cell.getCachedFormulaResultType() == CellType.BOOLEAN) {
                    value = "" + cell.getBooleanCellValue();
                    break;
                } else if (cell.getCachedFormulaResultType() == CellType.ERROR) {
                    value = cell.getErrorCellString();
                    break;
                } else if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
                    if (!DateUtil.isCellDateFormatted(cell)) {
                        value = "" + cell.getNumericCellValue();
                        break;
                    } else {
                        value = getFormattedDate(cell);
                        break;
                    }
                }
                break;
            case NUMERIC:
                if (!DateUtil.isCellDateFormatted(cell)) {
                    value = "" + cell.getRawValue();
                    break;
                } else {
                    value = getFormattedDate(cell);
                    break;
                }
        }
        if (node instanceof Element) {
            Element currentElement = (Element) node;
            currentElement.setTextContent(value);
        } else {
            node.setNodeValue(value);
        }
    }

    private String removeNamespace(String elementName) {
        return elementName.matches(".*:.*") ? elementName.split(":")[1] : elementName;
    }

    private String getFormattedDate(XSSFCell cell) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        sdf.setTimeZone(LocaleUtil.getUserTimeZone());
        return sdf.format(cell.getDateCellValue());
    }

    private Node getNodeByXPath(String xpath, Node rootNode, Document doc, boolean createMultipleInstances) {
        String[] xpathTokens = xpath.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        Node currentNode = rootNode;
        for (int i = 2; i < xpathTokens.length; i++) {
            String axisName = removeNamespace(xpathTokens[i]);
            if (!axisName.startsWith("@")) {
                NodeList list = currentNode.getChildNodes();
                Node selectedNode = null;
                if (!createMultipleInstances || i != xpathTokens.length - 1) {
                    selectedNode = selectNode(axisName, list);
                }
                if (selectedNode == null) {
                    selectedNode = createElement(doc, currentNode, axisName);
                }
                currentNode = selectedNode;
            } else {
                currentNode = createAttribute(doc, currentNode, axisName);
            }
        }
        return currentNode;
    }

    private Node createAttribute(Document doc, Node currentNode, String axisName) {
        String attributeName = axisName.substring(1);
        NamedNodeMap attributesMap = currentNode.getAttributes();
        Node attribute = attributesMap.getNamedItem(attributeName);
        if (attribute == null) {
            Node attribute2 = doc.createAttributeNS("", attributeName);
            attributesMap.setNamedItem(attribute2);
            return attribute2;
        }
        return attribute;
    }

    private Node createElement(Document doc, Node currentNode, String axisName) {
        Node selectedNode;
        if (isNamespaceDeclared()) {
            selectedNode = doc.createElementNS(getNamespace(), axisName);
        } else {
            selectedNode = doc.createElementNS("", axisName);
        }
        currentNode.appendChild(selectedNode);
        return selectedNode;
    }

    private Node selectNode(String axisName, NodeList list) {
        for (int j = 0; j < list.getLength(); j++) {
            Node node = list.item(j);
            if (node.getNodeName().equals(axisName)) {
                return node;
            }
        }
        return null;
    }

    private boolean isNamespaceDeclared() {
        String schemaNamespace = getNamespace();
        return (schemaNamespace == null || schemaNamespace.isEmpty()) ? false : true;
    }

    private String getNamespace() {
        return this.map.getCTSchema().getNamespace();
    }

    @Override // java.util.Comparator
    public int compare(String leftXpath, String rightXpath) {
        Node xmlSchema = this.map.getSchema();
        String[] leftTokens = leftXpath.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        String[] rightTokens = rightXpath.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        String samePath = "";
        int minLength = Math.min(leftTokens.length, rightTokens.length);
        Node localComplexTypeRootNode = xmlSchema;
        for (int i = 1; i < minLength; i++) {
            String leftElementName = leftTokens[i];
            String rightElementName = rightTokens[i];
            if (leftElementName.equals(rightElementName)) {
                samePath = samePath + PackagingURIHelper.FORWARD_SLASH_STRING + leftElementName;
                localComplexTypeRootNode = getComplexTypeForElement(leftElementName, xmlSchema, localComplexTypeRootNode);
            } else {
                return indexOfElementInComplexType(samePath, leftElementName, rightElementName, localComplexTypeRootNode);
            }
        }
        return 0;
    }

    private int indexOfElementInComplexType(String samePath, String leftElementName, String rightElementName, Node complexType) {
        if (complexType == null) {
            return 0;
        }
        int i = 0;
        String leftWithoutNamespace = removeNamespace(leftElementName);
        int leftIndexOf = getAndStoreIndex(samePath, leftWithoutNamespace);
        String rightWithoutNamespace = removeNamespace(rightElementName);
        int rightIndexOf = getAndStoreIndex(samePath, rightWithoutNamespace);
        for (Node node = complexType.getFirstChild(); node != null && (rightIndexOf == -1 || leftIndexOf == -1); node = node.getNextSibling()) {
            if ((node instanceof Element) && "element".equals(node.getLocalName())) {
                String elementValue = getNameOrRefElement(node).getNodeValue();
                if (elementValue.equals(leftWithoutNamespace)) {
                    leftIndexOf = i;
                    this.indexMap.put(samePath + PackagingURIHelper.FORWARD_SLASH_STRING + leftWithoutNamespace, Integer.valueOf(leftIndexOf));
                }
                if (elementValue.equals(rightWithoutNamespace)) {
                    rightIndexOf = i;
                    this.indexMap.put(samePath + PackagingURIHelper.FORWARD_SLASH_STRING + rightWithoutNamespace, Integer.valueOf(rightIndexOf));
                }
            }
            i++;
        }
        if (leftIndexOf == -1 || rightIndexOf == -1) {
            return 0;
        }
        return Integer.compare(leftIndexOf, rightIndexOf);
    }

    private int getAndStoreIndex(String samePath, String withoutNamespace) {
        String withPath = samePath + PackagingURIHelper.FORWARD_SLASH_STRING + withoutNamespace;
        return this.indexMap.getOrDefault(withPath, -1).intValue();
    }

    private Node getNameOrRefElement(Node node) {
        Node returnNode = node.getAttributes().getNamedItem("ref");
        if (returnNode != null) {
            return returnNode;
        }
        return node.getAttributes().getNamedItem("name");
    }

    private Node getComplexTypeForElement(String elementName, Node xmlSchema, Node localComplexTypeRootNode) {
        String elementNameWithoutNamespace = removeNamespace(elementName);
        String complexTypeName = getComplexTypeNameFromChildren(localComplexTypeRootNode, elementNameWithoutNamespace);
        if ("".equals(complexTypeName)) {
            return null;
        }
        Node complexTypeNode = getComplexTypeNodeFromSchemaChildren(xmlSchema, null, complexTypeName);
        return complexTypeNode;
    }

    private String getComplexTypeNameFromChildren(Node localComplexTypeRootNode, String elementNameWithoutNamespace) {
        Node complexTypeAttribute;
        if (localComplexTypeRootNode == null) {
            return "";
        }
        for (Node node = localComplexTypeRootNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if ((node instanceof Element) && "element".equals(node.getLocalName())) {
                Node nameAttribute = getNameOrRefElement(node);
                if (nameAttribute.getNodeValue().equals(elementNameWithoutNamespace) && (complexTypeAttribute = node.getAttributes().getNamedItem(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)) != null) {
                    String complexTypeName = complexTypeAttribute.getNodeValue();
                    return complexTypeName;
                }
            }
        }
        return "";
    }

    private Node getComplexTypeNodeFromSchemaChildren(Node xmlSchema, Node complexTypeNode, String complexTypeName) {
        for (Node node = xmlSchema.getFirstChild(); node != null; node = node.getNextSibling()) {
            if ((node instanceof Element) && "complexType".equals(node.getLocalName())) {
                Node nameAttribute = getNameOrRefElement(node);
                if (nameAttribute.getNodeValue().equals(complexTypeName)) {
                    for (Node sequence = node.getFirstChild(); sequence != null; sequence = sequence.getNextSibling()) {
                        if (sequence instanceof Element) {
                            String localName = sequence.getLocalName();
                            if ("sequence".equals(localName) || "all".equals(localName)) {
                                complexTypeNode = sequence;
                                break;
                            }
                        }
                    }
                    if (complexTypeNode != null) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return complexTypeNode;
    }

    private static void trySet(String name, SecurityFeature securityFeature) {
        try {
            securityFeature.accept(name);
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set SchemaFactory feature ({}) because outdated XML parser in classpath", name);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SchemaFactory feature ({}) unsupported", name);
        }
    }
}
