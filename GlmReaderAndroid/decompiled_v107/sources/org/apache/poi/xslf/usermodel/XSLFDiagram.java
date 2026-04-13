package org.apache.poi.xslf.usermodel;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape;
import com.microsoft.schemas.office.drawing.x2008.diagram.CTShape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

/* loaded from: classes10.dex */
public class XSLFDiagram extends XSLFGraphicFrame {
    public static final String DRAWINGML_DIAGRAM_URI = "http://schemas.openxmlformats.org/drawingml/2006/diagram";
    private final XSLFDiagramDrawing _drawing;
    private final XSLFDiagramGroupShape _groupShape;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFDiagram(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
        this._drawing = readDiagramDrawing(shape, sheet);
        this._groupShape = initGroupShape();
    }

    private static boolean hasTextContent(CTShape msShapeCt) {
        if (msShapeCt.getTxBody() == null || msShapeCt.getTxXfrm() == null) {
            return false;
        }
        List<CTTextParagraph> paragraphs = msShapeCt.getTxBody().getPList();
        return paragraphs.stream().flatMap(new Function() { // from class: org.apache.poi.xslf.usermodel.XSLFDiagram$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream stream;
                stream = ((CTTextParagraph) obj).getRList().stream();
                return stream;
            }
        }).anyMatch(new Predicate() { // from class: org.apache.poi.xslf.usermodel.XSLFDiagram$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XSLFDiagram.lambda$hasTextContent$1((CTRegularTextRun) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$hasTextContent$1(CTRegularTextRun run) {
        return (run.getT() == null || run.getT().trim().isEmpty()) ? false : true;
    }

    private static XSLFDiagramDrawing readDiagramDrawing(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        CTGraphicalObjectData graphicData = shape.getGraphic().getGraphicData();
        XmlObject[] children = graphicData.selectChildren(new QName(DRAWINGML_DIAGRAM_URI, "relIds"));
        if (children.length == 0) {
            return null;
        }
        CTRelIds relIds = (CTRelIds) children[0];
        POIXMLDocumentPart dataModelPart = sheet.getRelationById(relIds.getDm());
        if (dataModelPart == null) {
            return null;
        }
        String dataPartName = dataModelPart.getPackagePart().getPartName().getName();
        String drawingPartName = dataPartName.replace("data", "drawing");
        for (POIXMLDocumentPart.RelationPart rp : sheet.getRelationParts()) {
            if (drawingPartName.equals(rp.getDocumentPart().getPackagePart().getPartName().getName()) && (rp.getDocumentPart() instanceof XSLFDiagramDrawing)) {
                return (XSLFDiagramDrawing) rp.getDocumentPart();
            }
        }
        return null;
    }

    public XSLFDiagramDrawing getDiagramDrawing() {
        return this._drawing;
    }

    public XSLFDiagramGroupShape getGroupShape() {
        return this._groupShape;
    }

    private List<org.openxmlformats.schemas.presentationml.x2006.main.CTShape> convertShape(CTShape msShapeCt) {
        org.openxmlformats.schemas.presentationml.x2006.main.CTShape shapeCt = org.openxmlformats.schemas.presentationml.x2006.main.CTShape.Factory.newInstance();
        shapeCt.setStyle(msShapeCt.getStyle());
        shapeCt.setSpPr(msShapeCt.getSpPr());
        CTShapeNonVisual nonVisualCt = shapeCt.addNewNvSpPr();
        nonVisualCt.setCNvPr(msShapeCt.getNvSpPr().getCNvPr());
        nonVisualCt.setCNvSpPr(msShapeCt.getNvSpPr().getCNvSpPr());
        nonVisualCt.setNvPr(CTApplicationNonVisualDrawingProps.Factory.newInstance());
        shapeCt.setNvSpPr(nonVisualCt);
        ArrayList<org.openxmlformats.schemas.presentationml.x2006.main.CTShape> shapes = new ArrayList<>();
        shapes.add(shapeCt);
        if (hasTextContent(msShapeCt)) {
            org.openxmlformats.schemas.presentationml.x2006.main.CTShape textShapeCT = convertText(msShapeCt, nonVisualCt);
            shapes.add(textShapeCT);
        }
        return shapes;
    }

    private org.openxmlformats.schemas.presentationml.x2006.main.CTShape convertText(CTShape msShapeCt, CTShapeNonVisual nonVisualCt) {
        org.openxmlformats.schemas.presentationml.x2006.main.CTShape textShapeCT = org.openxmlformats.schemas.presentationml.x2006.main.CTShape.Factory.newInstance();
        CTShapeProperties textShapeProps = textShapeCT.addNewSpPr();
        textShapeCT.setTxBody(msShapeCt.getTxBody());
        textShapeCT.setStyle(msShapeCt.getStyle());
        textShapeCT.setNvSpPr((CTShapeNonVisual) nonVisualCt.copy());
        textShapeCT.getNvSpPr().getCNvSpPr().setTxBox(true);
        textShapeProps.setXfrm(msShapeCt.getTxXfrm());
        int shapeRotation = msShapeCt.getSpPr().getXfrm().getRot();
        int textRotation = msShapeCt.getTxXfrm().getRot();
        if (textRotation != 0) {
            int resolvedRotation = shapeRotation + textRotation;
            textShapeProps.getXfrm().setRot(resolvedRotation);
        }
        return textShapeCT;
    }

    private XSLFDiagramGroupShape initGroupShape() {
        CTGroupShape msGroupShapeCt;
        XSLFDiagramDrawing drawing = getDiagramDrawing();
        if (drawing == null || drawing.getDrawingDocument() == null || (msGroupShapeCt = drawing.getDrawingDocument().getDrawing().getSpTree()) == null || msGroupShapeCt.getSpList().isEmpty()) {
            return null;
        }
        return convertMsGroupToGroupShape(msGroupShapeCt, drawing);
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    private XSLFDiagramGroupShape convertMsGroupToGroupShape(CTGroupShape msGroupShapeCt, XSLFDiagramDrawing drawing) {
        org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape groupShapeCt = org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape.Factory.newInstance();
        groupShapeCt.addNewGrpSpPr();
        CTGroupShapeNonVisual groupShapeNonVisualCt = groupShapeCt.addNewNvGrpSpPr();
        com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual nvGrpSpPr = msGroupShapeCt.getNvGrpSpPr();
        if (nvGrpSpPr != null) {
            groupShapeNonVisualCt.setCNvPr(nvGrpSpPr.getCNvPr());
            groupShapeNonVisualCt.setCNvGrpSpPr(nvGrpSpPr.getCNvGrpSpPr());
        }
        groupShapeNonVisualCt.setNvPr(CTApplicationNonVisualDrawingProps.Factory.newInstance());
        for (CTShape msShapeCt : msGroupShapeCt.getSpList()) {
            List<org.openxmlformats.schemas.presentationml.x2006.main.CTShape> shapes = convertShape(msShapeCt);
            groupShapeCt.getSpList().addAll(shapes);
        }
        Rectangle2D anchor = super.getAnchor();
        Rectangle2D interiorAnchor = new Rectangle2D.Double(0.0d, 0.0d, anchor.getWidth(), anchor.getHeight());
        XSLFDiagramGroupShape groupShape = new XSLFDiagramGroupShape(groupShapeCt, getSheet(), drawing);
        groupShape.setAnchor(anchor);
        groupShape.setInteriorAnchor(interiorAnchor);
        groupShape.setRotation(super.getRotation());
        return groupShape;
    }

    /* loaded from: classes10.dex */
    public static class XSLFDiagramGroupShape extends XSLFGroupShape {
        private XSLFDiagramDrawing diagramDrawing;

        protected XSLFDiagramGroupShape(org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape shape, XSLFSheet sheet) {
            super(shape, sheet);
        }

        private XSLFDiagramGroupShape(org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape shape, XSLFSheet sheet, XSLFDiagramDrawing diagramDrawing) {
            super(shape, sheet);
            this.diagramDrawing = diagramDrawing;
        }

        public POIXMLDocumentPart getRelationById(String id) {
            return this.diagramDrawing.getRelationById(id);
        }
    }
}
