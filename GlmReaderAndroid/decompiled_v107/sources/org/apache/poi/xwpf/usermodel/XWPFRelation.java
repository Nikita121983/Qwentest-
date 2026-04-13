package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda23;
import org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda24;

/* loaded from: classes10.dex */
public final class XWPFRelation extends POIXMLRelation {
    static final String NS_DRAWINGML = "http://schemas.openxmlformats.org/drawingml/2006/main";
    private static final Map<String, XWPFRelation> _table = new HashMap();
    public static final XWPFRelation DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation TEMPLATE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation MACRO_DOCUMENT = new XWPFRelation("application/vnd.ms-word.document.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation MACRO_TEMPLATE_DOCUMENT = new XWPFRelation("application/vnd.ms-word.template.macroEnabledTemplate.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation GLOSSARY_DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.glossary+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/glossaryDocument", "/word/glossary/document.xml");
    public static final XWPFRelation NUMBERING = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.numbering+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/numbering", "/word/numbering.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFNumbering();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFNumbering(packagePart);
        }
    });
    public static final XWPFRelation FONT_TABLE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/fontTable", "/word/fontTable.xml");
    public static final XWPFRelation SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/settings", "/word/settings.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda7
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFSettings();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda8
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFSettings(packagePart);
        }
    });
    public static final XWPFRelation STYLES = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml", PackageRelationshipTypes.STYLE_PART, "/word/styles.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda9
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFStyles();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda10
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFStyles(packagePart);
        }
    });
    public static final XWPFRelation WEB_SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/webSettings", "/word/webSettings.xml");
    public static final XWPFRelation HEADER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.header+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/header", "/word/header#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda12
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFHeader();
        }
    }, new POIXMLRelation.ParentPartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda13
        @Override // org.apache.poi.ooxml.POIXMLRelation.ParentPartConstructor
        public final POIXMLDocumentPart init(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
            return new XWPFHeader(pOIXMLDocumentPart, packagePart);
        }
    });
    public static final XWPFRelation FOOTER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footer+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footer", "/word/footer#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda14
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFFooter();
        }
    }, new POIXMLRelation.ParentPartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda15
        @Override // org.apache.poi.ooxml.POIXMLRelation.ParentPartConstructor
        public final POIXMLDocumentPart init(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
            return new XWPFFooter(pOIXMLDocumentPart, packagePart);
        }
    });
    public static final XWPFRelation THEME = new XWPFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/word/theme/theme#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda11
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFTheme();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda16
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFTheme(packagePart);
        }
    });
    public static final XWPFRelation WORKBOOK = new XWPFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", POIXMLDocument.PACK_OBJECT_REL_TYPE, "/word/embeddings/Microsoft_Excel_Worksheet#.xlsx", new XSLFRelation$$ExternalSyntheticLambda23(), new XSLFRelation$$ExternalSyntheticLambda24());
    public static final XWPFRelation CHART = new XWPFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/word/charts/chart#.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda17
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFChart();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda18
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFChart(packagePart);
        }
    });
    public static final XWPFRelation HYPERLINK = new XWPFRelation(null, PackageRelationshipTypes.HYPERLINK_PART, null);
    public static final XWPFRelation COMMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/word/comments.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda19
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFComments();
        }
    }, new POIXMLRelation.ParentPartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda20
        @Override // org.apache.poi.ooxml.POIXMLRelation.ParentPartConstructor
        public final POIXMLDocumentPart init(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
            return new XWPFComments(pOIXMLDocumentPart, packagePart);
        }
    });
    public static final XWPFRelation FOOTNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footnotes", "/word/footnotes.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda21
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFFootnotes();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFFootnotes(packagePart);
        }
    });
    public static final XWPFRelation ENDNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.endnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/endnotes", "/word/endnotes.xml", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda3
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFEndnotes();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda4
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFEndnotes(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_EMF = new XWPFRelation(PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.emf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_WMF = new XWPFRelation(PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.wmf", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_PICT = new XWPFRelation(PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.pict", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_JPEG = new XWPFRelation(PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.jpeg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_PNG = new XWPFRelation(PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.png", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_DIB = new XWPFRelation(PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.dib", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_GIF = new XWPFRelation(PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.gif", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_TIFF = new XWPFRelation(PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.tiff", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_EPS = new XWPFRelation(PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.eps", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_BMP = new XWPFRelation(PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.bmp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_WPG = new XWPFRelation(PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.wpg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation HDPHOTO_WDP = new XWPFRelation(PictureType.WDP.contentType, PackageRelationshipTypes.HDPHOTO_PART, "/ppt/media/hdphoto#.wdp", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGE_SVG = new XWPFRelation(PictureType.SVG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.svg", new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });
    public static final XWPFRelation IMAGES = new XWPFRelation((String) null, PackageRelationshipTypes.IMAGE_PART, (String) null, new POIXMLRelation.NoArgConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda5
        @Override // org.apache.poi.ooxml.POIXMLRelation.NoArgConstructor
        public final POIXMLDocumentPart init() {
            return new XWPFPictureData();
        }
    }, new POIXMLRelation.PackagePartConstructor() { // from class: org.apache.poi.xwpf.usermodel.XWPFRelation$$ExternalSyntheticLambda6
        @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
        public final POIXMLDocumentPart init(PackagePart packagePart) {
            return new XWPFPictureData(packagePart);
        }
    });

    private XWPFRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
        _table.put(rel, this);
    }

    private XWPFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(type, rel, defaultName, noArgConstructor, packagePartConstructor, null);
        _table.put(rel, this);
    }

    private XWPFRelation(String type, String rel, String defaultName, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.ParentPartConstructor parentPartConstructor) {
        super(type, rel, defaultName, noArgConstructor, null, parentPartConstructor);
        _table.put(rel, this);
    }

    public static XWPFRelation getInstance(String rel) {
        return _table.get(rel);
    }

    public String toString() {
        return "XWPFRelation{" + getContentType() + PackagingURIHelper.FORWARD_SLASH_STRING + getDefaultFileName() + VectorFormat.DEFAULT_SUFFIX;
    }
}
