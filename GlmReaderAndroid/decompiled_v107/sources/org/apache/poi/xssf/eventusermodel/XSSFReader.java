package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes10.dex */
public class XSSFReader {
    protected OPCPackage pkg;
    protected boolean useReadOnlySharedStringsTable;
    protected PackagePart workbookPart;
    private static final Set<String> WORKSHEET_RELS = Collections.unmodifiableSet(new HashSet(Arrays.asList(XSSFRelation.WORKSHEET.getRelation(), XSSFRelation.CHARTSHEET.getRelation(), XSSFRelation.MACRO_SHEET_XML.getRelation())));
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) XSSFReader.class);

    public XSSFReader(OPCPackage pkg) throws IOException, OpenXML4JException {
        this(pkg, false);
    }

    public XSSFReader(OPCPackage pkg, boolean allowStrictOoxmlFiles) throws IOException, OpenXML4JException {
        this.pkg = pkg;
        PackageRelationship coreDocRelationship = this.pkg.getRelationshipsByType(PackageRelationshipTypes.CORE_DOCUMENT).getRelationship(0);
        if (coreDocRelationship == null) {
            if (allowStrictOoxmlFiles) {
                coreDocRelationship = this.pkg.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0);
            } else if (this.pkg.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0) != null) {
                throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
            }
            if (coreDocRelationship == null) {
                throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
            }
        }
        this.workbookPart = this.pkg.getPart(coreDocRelationship);
    }

    public void setUseReadOnlySharedStringsTable(boolean useReadOnlySharedStringsTable) {
        this.useReadOnlySharedStringsTable = useReadOnlySharedStringsTable;
    }

    public boolean useReadOnlySharedStringsTable() {
        return this.useReadOnlySharedStringsTable;
    }

    public SharedStrings getSharedStringsTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> parts = this.pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
        try {
            if (parts.isEmpty()) {
                return null;
            }
            if (this.useReadOnlySharedStringsTable) {
                return new ReadOnlySharedStringsTable(parts.get(0));
            }
            return new SharedStringsTable(parts.get(0));
        } catch (SAXException se) {
            throw new InvalidFormatException("Failed to parse SharedStringsTable", se);
        }
    }

    public StylesTable getStylesTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> parts = this.pkg.getPartsByContentType(XSSFRelation.STYLES.getContentType());
        if (parts.isEmpty()) {
            return null;
        }
        StylesTable styles = new StylesTable(parts.get(0));
        ArrayList<PackagePart> parts2 = this.pkg.getPartsByContentType(XSSFRelation.THEME.getContentType());
        if (!parts2.isEmpty()) {
            styles.setTheme(new ThemesTable(parts2.get(0)));
        }
        return styles;
    }

    public InputStream getSharedStringsData() throws IOException, InvalidFormatException {
        return XSSFRelation.SHARED_STRINGS.getContents(this.workbookPart);
    }

    public InputStream getStylesData() throws IOException, InvalidFormatException {
        return XSSFRelation.STYLES.getContents(this.workbookPart);
    }

    public InputStream getThemesData() throws IOException, InvalidFormatException {
        return XSSFRelation.THEME.getContents(this.workbookPart);
    }

    public InputStream getWorkbookData() throws IOException, InvalidFormatException {
        return this.workbookPart.getInputStream();
    }

    public InputStream getSheet(String relId) throws IOException, InvalidFormatException {
        PackageRelationship rel = this.workbookPart.getRelationship(relId);
        if (rel == null) {
            throw new IllegalArgumentException("No Sheet found with r:id " + relId);
        }
        PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
        PackagePart sheet = this.pkg.getPart(relName);
        if (sheet == null) {
            throw new IllegalArgumentException("No data found for Sheet with r:id " + relId);
        }
        return sheet.getInputStream();
    }

    public Iterator<InputStream> getSheetsData() throws IOException, InvalidFormatException {
        return getSheetIterator();
    }

    public SheetIterator getSheetIterator() throws IOException, InvalidFormatException {
        return new SheetIterator(this.workbookPart);
    }

    /* loaded from: classes10.dex */
    public static class SheetIterator implements Iterator<InputStream> {
        protected final Iterator<XSSFSheetRef> sheetIterator;
        protected final Map<String, PackagePart> sheetMap;
        protected XSSFSheetRef xssfSheetRef;

        /* JADX INFO: Access modifiers changed from: protected */
        public SheetIterator(PackagePart wb) throws IOException, InvalidFormatException {
            if (wb == null) {
                throw new InvalidFormatException("Cannot create sheet-iterator with missing package part for workbook");
            }
            this.sheetMap = new HashMap();
            OPCPackage pkg = wb.getPackage();
            Set<String> worksheetRels = getSheetRelationships();
            Iterator<PackageRelationship> it = wb.getRelationships().iterator();
            while (it.hasNext()) {
                PackageRelationship rel = it.next();
                String relType = rel.getRelationshipType();
                if (worksheetRels.contains(relType)) {
                    PackagePartName relName = PackagingURIHelper.createPartName(rel.getTargetURI());
                    this.sheetMap.put(rel.getId(), pkg.getPart(relName));
                }
            }
            this.sheetIterator = createSheetIteratorFromWB(wb);
        }

        protected Iterator<XSSFSheetRef> createSheetIteratorFromWB(PackagePart wb) throws IOException {
            XMLSheetRefReader xmlSheetRefReader = new XMLSheetRefReader();
            try {
                XMLReader xmlReader = XMLHelper.newXMLReader();
                xmlReader.setContentHandler(xmlSheetRefReader);
                try {
                    InputStream stream = wb.getInputStream();
                    try {
                        xmlReader.parse(new InputSource(stream));
                        if (stream != null) {
                            stream.close();
                        }
                        List<XSSFSheetRef> validSheets = new ArrayList<>();
                        for (XSSFSheetRef xssfSheetRef : xmlSheetRefReader.getSheetRefs()) {
                            String sheetId = xssfSheetRef.getId();
                            if (sheetId != null && !sheetId.isEmpty()) {
                                validSheets.add(xssfSheetRef);
                            }
                        }
                        return validSheets.iterator();
                    } finally {
                    }
                } catch (SAXException e) {
                    throw new POIXMLException(e);
                }
            } catch (ParserConfigurationException | SAXException e2) {
                throw new POIXMLException(e2);
            }
        }

        protected Set<String> getSheetRelationships() {
            return XSSFReader.WORKSHEET_RELS;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.sheetIterator.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public InputStream next() {
            if (!this.sheetIterator.hasNext()) {
                throw new IllegalStateException("Cannot get next from iterator");
            }
            this.xssfSheetRef = this.sheetIterator.next();
            String sheetId = this.xssfSheetRef.getId();
            try {
                PackagePart sheetPkg = this.sheetMap.get(sheetId);
                if (sheetPkg == null) {
                    throw new POIXMLException("Failed to find sheet package for sheetId=" + sheetId);
                }
                return sheetPkg.getInputStream();
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        }

        public String getSheetName() {
            return this.xssfSheetRef.getName();
        }

        public Comments getSheetComments() {
            PackagePart sheetPkg = getSheetPart();
            try {
                PackageRelationshipCollection commentsList = sheetPkg.getRelationshipsByType(XSSFRelation.SHEET_COMMENTS.getRelation());
                if (commentsList.isEmpty()) {
                    return null;
                }
                PackageRelationship comments = commentsList.getRelationship(0);
                PackagePartName commentsName = PackagingURIHelper.createPartName(comments.getTargetURI());
                PackagePart commentsPart = sheetPkg.getPackage().getPart(commentsName);
                return parseComments(commentsPart);
            } catch (IOException | InvalidFormatException e) {
                XSSFReader.LOGGER.atWarn().withThrowable(e).log("Failed to load sheet comments");
                return null;
            }
        }

        protected Comments parseComments(PackagePart commentsPart) throws IOException {
            return new CommentsTable(commentsPart);
        }

        public List<XSSFShape> getShapes() {
            PackagePart sheetPkg = getSheetPart();
            List<XSSFShape> shapes = new LinkedList<>();
            try {
                PackageRelationshipCollection drawingsList = sheetPkg.getRelationshipsByType(XSSFRelation.DRAWINGS.getRelation());
                int drawingsSize = drawingsList.size();
                for (int i = 0; i < drawingsSize; i++) {
                    PackageRelationship drawings = drawingsList.getRelationship(i);
                    PackagePartName drawingsName = PackagingURIHelper.createPartName(drawings.getTargetURI());
                    PackagePart drawingsPart = sheetPkg.getPackage().getPart(drawingsName);
                    if (drawingsPart == null) {
                        XSSFReader.LOGGER.atWarn().log("Missing drawing: {}. Skipping it.", drawingsName);
                    } else {
                        XSSFDrawing drawing = new XSSFDrawing(drawingsPart);
                        shapes.addAll(drawing.getShapes());
                    }
                }
                return shapes;
            } catch (IOException | InvalidFormatException | XmlException e) {
                XSSFReader.LOGGER.atWarn().withThrowable(e).log("Failed to load shapes");
                return null;
            }
        }

        public PackagePart getSheetPart() {
            String sheetId = this.xssfSheetRef.getId();
            return this.sheetMap.get(sheetId);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException("Not supported");
        }
    }

    /* loaded from: classes10.dex */
    public static final class XSSFSheetRef {
        private final String id;
        private final String name;

        public XSSFSheetRef(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    /* loaded from: classes10.dex */
    public static class XMLSheetRefReader extends DefaultHandler {
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String SHEET = "sheet";
        private final List<XSSFSheetRef> sheetRefs = new LinkedList();

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
            if (localName.equalsIgnoreCase(SHEET)) {
                String name = null;
                String id = null;
                for (int i = 0; i < attrs.getLength(); i++) {
                    String attrName = attrs.getLocalName(i);
                    if (attrName.equalsIgnoreCase(NAME)) {
                        name = attrs.getValue(i);
                    } else if (attrName.equalsIgnoreCase(ID)) {
                        id = attrs.getValue(i);
                    }
                    if (name != null && id != null) {
                        this.sheetRefs.add(new XSSFSheetRef(id, name));
                        return;
                    }
                }
            }
        }

        public List<XSSFSheetRef> getSheetRefs() {
            return Collections.unmodifiableList(this.sheetRefs);
        }
    }
}
