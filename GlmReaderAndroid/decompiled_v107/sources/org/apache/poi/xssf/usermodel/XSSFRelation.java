package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.model.ExternalLinksTable;
import org.apache.poi.xssf.model.MapInfo;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.SingleXmlCells;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;

/* loaded from: classes10.dex */
public final class XSSFRelation extends POIXMLRelation {
    public static final String NS_CHART = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    public static final String NS_DRAWINGML = "http://schemas.openxmlformats.org/drawingml/2006/main";
    public static final String NS_PRESENTATIONML = "http://schemas.openxmlformats.org/presentationml/2006/main";
    public static final String NS_SPREADSHEETML = "http://schemas.openxmlformats.org/spreadsheetml/2006/main";
    public static final String NS_WORDPROCESSINGML = "http://schemas.openxmlformats.org/wordprocessingml/2006/main";
    private static final Map<String, XSSFRelation> _table = new HashMap();
    public static final XSSFRelation WORKBOOK = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/workbook", "/xl/workbook.xml");
    public static final XSSFRelation MACROS_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.sheet.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation TEMPLATE_WORKBOOK = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.template.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation MACRO_TEMPLATE_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.template.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation MACRO_ADDIN_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.addin.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation XLSB_BINARY_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.sheet.binary.macroEnabled.main", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.bin");
    public static final XSSFRelation WORKSHEET = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet", "/xl/worksheets/sheet#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFSheet();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFSheet(packagePart);
        }
    });
    public static final XSSFRelation CHARTSHEET = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.chartsheet+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chartsheet", "/xl/chartsheets/sheet#.xml", null, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFChartSheet(packagePart);
        }
    });
    public static final XSSFRelation CHART_EX = new XSSFRelation("application/vnd.ms-office.chartex+xml", "http://schemas.microsoft.com/office/2014/relationships/chartEx", "/xl/charts/chartEx#.xml");
    public static final XSSFRelation SHARED_STRINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sharedStrings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "/xl/sharedStrings.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new SharedStringsTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda12
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new SharedStringsTable(packagePart);
        }
    });
    public static final XSSFRelation STYLES = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml", PackageRelationshipTypes.STYLE_PART, "/xl/styles.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda24
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new StylesTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda26
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new StylesTable(packagePart);
        }
    });
    public static final XSSFRelation DRAWINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.drawing+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/drawing", "/xl/drawings/drawing#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda27
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFDrawing();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda28
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFDrawing(packagePart);
        }
    });
    public static final XSSFRelation VML_DRAWINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.vmlDrawing", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/vmlDrawing", "/xl/drawings/vmlDrawing#.vml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda29
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFVMLDrawing();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda11
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFVMLDrawing(packagePart);
        }
    });
    public static final XSSFRelation CHART = new XSSFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/xl/charts/chart#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda22
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFChart();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda30
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFChart(packagePart);
        }
    });
    public static final XSSFRelation CUSTOM_XML_MAPPINGS = new XSSFRelation(ContentTypes.PLAIN_OLD_XML, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/xmlMaps", "/xl/xmlMaps.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda31
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new MapInfo();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda32
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new MapInfo(packagePart);
        }
    });
    public static final XSSFRelation SINGLE_XML_CELLS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.tableSingleCells+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/tableSingleCells", "/xl/tables/tableSingleCells#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda33
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new SingleXmlCells();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda34
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new SingleXmlCells(packagePart);
        }
    });
    public static final XSSFRelation TABLE = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.table+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/table", "/xl/tables/table#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda35
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda36
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFTable(packagePart);
        }
    });
    public static final XSSFRelation IMAGES = new XSSFRelation(null, PackageRelationshipTypes.IMAGE_PART, null, new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_EMF = new XSSFRelation(PictureData.PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.emf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_WMF = new XSSFRelation(PictureData.PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.wmf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_PICT = new XSSFRelation(PictureData.PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.pict", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_JPEG = new XSSFRelation(PictureData.PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.jpeg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_PNG = new XSSFRelation(PictureData.PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.png", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_DIB = new XSSFRelation(PictureData.PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.dib", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_GIF = new XSSFRelation(PictureData.PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.gif", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_TIFF = new XSSFRelation(PictureData.PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.tiff", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_EPS = new XSSFRelation(PictureData.PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.eps", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_BMP = new XSSFRelation(PictureData.PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.bmp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation IMAGE_WPG = new XSSFRelation(PictureData.PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.wpg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation HDPHOTO_WDP = new XSSFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.HDPHOTO_PART, "/xl/media/hdphoto#.wdp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPictureData(packagePart);
        }
    });
    public static final XSSFRelation SHEET_COMMENTS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/xl/comments#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new CommentsTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda7
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new CommentsTable(packagePart);
        }
    });
    public static final XSSFRelation SHEET_HYPERLINKS = new XSSFRelation(null, PackageRelationshipTypes.HYPERLINK_PART, null);
    public static final XSSFRelation OLEEMBEDDINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.oleObject", POIXMLDocument.OLE_OBJECT_REL_TYPE, "/xl/embeddings/oleObject#.bin");
    public static final XSSFRelation PACKEMBEDDINGS = new XSSFRelation(null, POIXMLDocument.PACK_OBJECT_REL_TYPE, null);
    public static final XSSFRelation VBA_MACROS = new XSSFRelation("application/vnd.ms-office.vbaProject", "http://schemas.microsoft.com/office/2006/relationships/vbaProject", "/xl/vbaProject.bin", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda8
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFVBAPart();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda9
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFVBAPart(packagePart);
        }
    });
    public static final XSSFRelation ACTIVEX_CONTROLS = new XSSFRelation("application/vnd.ms-office.activeX+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/control", "/xl/activeX/activeX#.xml");
    public static final XSSFRelation ACTIVEX_BINS = new XSSFRelation("application/vnd.ms-office.activeX", "http://schemas.microsoft.com/office/2006/relationships/activeXControlBinary", "/xl/activeX/activeX#.bin");
    public static final XSSFRelation MACRO_SHEET_BIN = new XSSFRelation("application/vnd.ms-excel.macrosheet", "http://schemas.microsoft.com/office/2006/relationships/xlMacrosheet", "/xl/macroSheets/sheet#.bin");
    public static final XSSFRelation MACRO_SHEET_XML = new XSSFRelation("application/vnd.ms-excel.macrosheet+xml", "http://schemas.microsoft.com/office/2006/relationships/xlMacrosheet", "/xl/macroSheets/sheet#.xml");
    public static final XSSFRelation INTL_MACRO_SHEET_BIN = new XSSFRelation("application/vnd.ms-excel.intlmacrosheet", "http://schemas.microsoft.com/office/2006/relationships/xlIntlMacrosheet", "/xl/macroSheets/sheet#.bin");
    public static final XSSFRelation INTL_MACRO_SHEET_XML = new XSSFRelation("application/vnd.ms-excel.intlmacrosheet+xml", "http://schemas.microsoft.com/office/2006/relationships/xlIntlMacrosheet", "/xl/macroSheets/sheet#.xml");
    public static final XSSFRelation DIALOG_SHEET_BIN = new XSSFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/dialogsheet", "/xl/dialogSheets/sheet#.bin");
    public static final XSSFRelation THEME = new XSSFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/xl/theme/theme#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda10
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new ThemesTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda13
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new ThemesTable(packagePart);
        }
    });
    public static final XSSFRelation CALC_CHAIN = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.calcChain+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/calcChain", "/xl/calcChain.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda14
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new CalculationChain();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda15
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new CalculationChain(packagePart);
        }
    });
    public static final XSSFRelation EXTERNAL_LINKS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.externalLink+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/externalLink", "/xl/externalLinks/externalLink#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda16
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new ExternalLinksTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda17
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new ExternalLinksTable(packagePart);
        }
    });
    public static final XSSFRelation PRINTER_SETTINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.printerSettings", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/printerSettings", "/xl/printerSettings/printerSettings#.bin");
    public static final XSSFRelation PIVOT_TABLE = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotTable+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotTable", "/xl/pivotTables/pivotTable#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda18
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPivotTable();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda19
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPivotTable(packagePart);
        }
    });
    public static final XSSFRelation PIVOT_CACHE_DEFINITION = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotCacheDefinition+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotCacheDefinition", "/xl/pivotCache/pivotCacheDefinition#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda20
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPivotCacheDefinition();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda21
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPivotCacheDefinition(packagePart);
        }
    });
    public static final XSSFRelation PIVOT_CACHE_RECORDS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotCacheRecords+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotCacheRecords", "/xl/pivotCache/pivotCacheRecords#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda23
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSSFPivotCacheRecords();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xssf.usermodel.XSSFRelation$$ExternalSyntheticLambda25
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSSFPivotCacheRecords(packagePart);
        }
    });
    public static final XSSFRelation CTRL_PROP_RECORDS = new XSSFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/ctrlProp", "/xl/ctrlProps/ctrlProp#.xml");
    public static final XSSFRelation CUSTOM_PROPERTIES = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.customProperty", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/customProperty", "/xl/customProperty#.bin");

    private XSSFRelation(String type, String rel, String defaultName) {
        this(type, rel, defaultName, null, null);
    }

    private XSSFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(type, rel, defaultName, noArgConstructor, packagePartConstructor, null);
        _table.put(rel, this);
    }

    public static XSSFRelation getInstance(String rel) {
        return _table.get(rel);
    }
}
