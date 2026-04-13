package org.apache.poi.xslf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;

/* loaded from: classes10.dex */
public final class XSLFRelation extends POIXMLRelation {
    static final String NS_DRAWINGML = "http://schemas.openxmlformats.org/drawingml/2006/main";
    private static final Map<String, XSLFRelation> _table = new HashMap();
    public static final XSLFRelation MAIN = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.presentation.main+xml");
    public static final XSLFRelation MACRO = new XSLFRelation("application/vnd.ms-powerpoint.slideshow.macroEnabled.main+xml");
    public static final XSLFRelation MACRO_TEMPLATE = new XSLFRelation("application/vnd.ms-powerpoint.template.macroEnabled.main+xml");
    public static final XSLFRelation PRESENTATIONML = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideshow.main+xml");
    public static final XSLFRelation PRESENTATIONML_TEMPLATE = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.template.main+xml");
    public static final XSLFRelation PRESENTATION_MACRO = new XSLFRelation("application/vnd.ms-powerpoint.presentation.macroEnabled.main+xml");
    public static final XSLFRelation THEME_MANAGER = new XSLFRelation("application/vnd.openxmlformats-officedocument.themeManager+xml");
    public static final XSLFRelation NOTES = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.notesSlide+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/notesSlide", "/ppt/notesSlides/notesSlide#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFNotes();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFNotes(packagePart);
        }
    });
    public static final XSLFRelation SLIDE = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slide+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slide", "/ppt/slides/slide#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFSlide();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFSlide(packagePart);
        }
    });
    public static final XSLFRelation SLIDE_LAYOUT = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideLayout+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideLayout", "/ppt/slideLayouts/slideLayout#.xml", null, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda7
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFSlideLayout(packagePart);
        }
    });
    public static final XSLFRelation SLIDE_MASTER = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideMaster+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideMaster", "/ppt/slideMasters/slideMaster#.xml", null, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda15
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFSlideMaster(packagePart);
        }
    });
    public static final XSLFRelation NOTES_MASTER = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.notesMaster+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/notesMaster", "/ppt/notesMasters/notesMaster#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda16
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFNotesMaster();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda17
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFNotesMaster(packagePart);
        }
    });
    public static final XSLFRelation COMMENTS = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/ppt/comments/comment#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda18
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFComments();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda19
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFComments(packagePart);
        }
    });
    public static final XSLFRelation COMMENT_AUTHORS = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.commentAuthors+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/commentAuthors", "/ppt/commentAuthors.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda11
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFCommentAuthors();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda20
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFCommentAuthors(packagePart);
        }
    });
    public static final XSLFRelation HYPERLINK = new XSLFRelation(null, PackageRelationshipTypes.HYPERLINK_PART, null);
    public static final XSLFRelation THEME = new XSLFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/ppt/theme/theme#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda21
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFTheme();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda22
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFTheme(packagePart);
        }
    });
    public static final XSLFRelation VML_DRAWING = new XSLFRelation("application/vnd.openxmlformats-officedocument.vmlDrawing", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/vmlDrawing", "/ppt/drawings/vmlDrawing#.vml");
    public static final XSLFRelation WORKBOOK = new XSLFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", POIXMLDocument.PACK_OBJECT_REL_TYPE, "/ppt/embeddings/Microsoft_Excel_Worksheet#.xlsx", new XSLFRelation$$ExternalSyntheticLambda23(), new XSLFRelation$$ExternalSyntheticLambda24());
    public static final XSLFRelation CHART = new XSLFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/ppt/charts/chart#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda25
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFChart();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda26
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFChart(packagePart);
        }
    });
    public static final XSLFRelation DIAGRAM_DRAWING = new XSLFRelation("application/vnd.ms-office.drawingml.diagramDrawing+xml", "http://schemas.microsoft.com/office/2007/relationships/diagramDrawing", "/ppt/diagrams/drawing#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda27
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFDiagramDrawing();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFDiagramDrawing(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_EMF = new XSLFRelation(PictureData.PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.emf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_WMF = new XSLFRelation(PictureData.PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wmf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_PICT = new XSLFRelation(PictureData.PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.pict", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_JPEG = new XSLFRelation(PictureData.PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.jpeg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_PNG = new XSLFRelation(PictureData.PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.png", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_DIB = new XSLFRelation(PictureData.PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.dib", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_GIF = new XSLFRelation(PictureData.PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.gif", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_TIFF = new XSLFRelation(PictureData.PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.tiff", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_EPS = new XSLFRelation(PictureData.PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.eps", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_BMP = new XSLFRelation(PictureData.PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.bmp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_WPG = new XSLFRelation(PictureData.PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wpg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_WDP = new XSLFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wdp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation HDPHOTO_WDP = new XSLFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.HDPHOTO_PART, "/ppt/media/hdphoto#.wdp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGE_SVG = new XSLFRelation(PictureData.PictureType.SVG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.svg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation IMAGES = new XSLFRelation(null, PackageRelationshipTypes.IMAGE_PART, null, new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFPictureData(packagePart);
        }
    });
    public static final XSLFRelation TABLE_STYLES = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.tableStyles+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/tableStyles", "/ppt/tableStyles.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda8
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFTableStyles();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda9
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFTableStyles(packagePart);
        }
    });
    public static final XSLFRelation OLE_OBJECT = new XSLFRelation("application/vnd.openxmlformats-officedocument.oleObject", POIXMLDocument.OLE_OBJECT_REL_TYPE, "/ppt/embeddings/oleObject#.bin", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda10
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFObjectData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda12
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFObjectData(packagePart);
        }
    });
    public static final XSLFRelation FONT = new XSLFRelation("application/x-fontdata", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/font", "/ppt/fonts/font#.fntdata", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda13
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XSLFFontData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda14
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XSLFFontData(packagePart);
        }
    });

    private XSLFRelation(String type) {
        this(type, null, null, null, null);
    }

    private XSLFRelation(String type, String rel, String defaultName) {
        this(type, rel, defaultName, null, null);
    }

    private XSLFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(type, rel, defaultName, noArgConstructor, packagePartConstructor, null);
        _table.put(rel, this);
    }

    public static XSLFRelation getInstance(String rel) {
        return _table.get(rel);
    }
}
