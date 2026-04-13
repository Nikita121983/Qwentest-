package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

/* loaded from: classes10.dex */
public class XSLFGroupShape extends XSLFShape implements XSLFShapeContainer, GroupShape<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFGroupShape.class);
    private XSLFDrawing _drawing;
    private final CTGroupShapeProperties _grpSpPr;
    private final List<XSLFShape> _shapes;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFGroupShape(CTGroupShape shape, XSLFSheet sheet) {
        super(shape, sheet);
        this._shapes = XSLFSheet.buildShapes(shape, this);
        this._grpSpPr = shape.getGrpSpPr();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShape
    protected CTGroupShapeProperties getGrpSpPr() {
        return this._grpSpPr;
    }

    private CTGroupTransform2D getSafeXfrm() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm == null ? getGrpSpPr().addNewXfrm() : xfrm;
    }

    protected CTGroupTransform2D getXfrm() {
        return getGrpSpPr().getXfrm();
    }

    @Override // org.apache.poi.sl.usermodel.Shape, org.apache.poi.sl.usermodel.PlaceableShape
    public Rectangle2D getAnchor() {
        CTGroupTransform2D xfrm = getXfrm();
        CTPoint2D off = xfrm.getOff();
        double x = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double y = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getExt();
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setAnchor(Rectangle2D anchor) {
        CTGroupTransform2D xfrm = getSafeXfrm();
        CTPoint2D off = xfrm.isSetOff() ? xfrm.getOff() : xfrm.addNewOff();
        long x = Units.toEMU(anchor.getX());
        long y = Units.toEMU(anchor.getY());
        off.setX(Long.valueOf(x));
        off.setY(Long.valueOf(y));
        CTPositiveSize2D ext = xfrm.isSetExt() ? xfrm.getExt() : xfrm.addNewExt();
        long cx = Units.toEMU(anchor.getWidth());
        long cy = Units.toEMU(anchor.getHeight());
        ext.setCx(cx);
        ext.setCy(cy);
    }

    @Override // org.apache.poi.sl.usermodel.GroupShape
    public Rectangle2D getInteriorAnchor() {
        CTGroupTransform2D xfrm = getXfrm();
        CTPoint2D off = xfrm.getChOff();
        double x = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double y = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getChExt();
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override // org.apache.poi.sl.usermodel.GroupShape
    public void setInteriorAnchor(Rectangle2D anchor) {
        CTGroupTransform2D xfrm = getSafeXfrm();
        CTPoint2D off = xfrm.isSetChOff() ? xfrm.getChOff() : xfrm.addNewChOff();
        long x = Units.toEMU(anchor.getX());
        long y = Units.toEMU(anchor.getY());
        off.setX(Long.valueOf(x));
        off.setY(Long.valueOf(y));
        CTPositiveSize2D ext = xfrm.isSetChExt() ? xfrm.getChExt() : xfrm.addNewChExt();
        long cx = Units.toEMU(anchor.getWidth());
        long cy = Units.toEMU(anchor.getHeight());
        ext.setCx(cx);
        ext.setCy(cy);
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public List<XSLFShape> getShapes() {
        return this._shapes;
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFShape> iterator() {
        return this._shapes.iterator();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r3v5, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public boolean removeShape(XSLFShape xShape) {
        XmlObject obj = xShape.getXmlObject();
        CTGroupShape grpSp = (CTGroupShape) getXmlObject();
        getSheet().deregisterShapeId(xShape.getShapeId());
        if (obj instanceof CTShape) {
            grpSp.getSpList().remove(obj);
        } else if (obj instanceof CTGroupShape) {
            XSLFGroupShape gs = (XSLFGroupShape) xShape;
            ArrayList arrayList = new ArrayList(gs.getShapes());
            gs.getClass();
            arrayList.forEach(new XSLFGroupShape$$ExternalSyntheticLambda0(gs));
            grpSp.getGrpSpList().remove(obj);
        } else if (obj instanceof CTConnector) {
            grpSp.getCxnSpList().remove(obj);
        } else if (obj instanceof CTGraphicalObjectFrame) {
            grpSp.getGraphicFrameList().remove(obj);
        } else if (obj instanceof CTPicture) {
            XSLFPictureShape ps = (XSLFPictureShape) xShape;
            ?? sheet = getSheet();
            if (sheet != 0) {
                sheet.removePictureRelation(ps);
            }
            grpSp.getPicList().remove(obj);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xShape);
        }
        return this._shapes.remove(xShape);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTGroupShape prototype(int shapeId) {
        CTGroupShape ct = CTGroupShape.Factory.newInstance();
        CTGroupShapeNonVisual nvSpPr = ct.addNewNvGrpSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Group " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvGrpSpPr();
        nvSpPr.addNewNvPr();
        ct.addNewGrpSpPr();
        return ct;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    private XSLFDrawing getDrawing() {
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(getSheet(), (CTGroupShape) getXmlObject());
        }
        return this._drawing;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public AutoShape<XSLFShape, XSLFTextParagraph> createAutoShape() {
        XSLFAutoShape sh = getDrawing().createAutoShape();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public FreeformShape<XSLFShape, XSLFTextParagraph> createFreeform() {
        XSLFFreeformShape sh = getDrawing().createFreeform();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public TextBox<XSLFShape, XSLFTextParagraph> createTextBox() {
        XSLFTextBox sh = getDrawing().createTextBox();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public ConnectorShape<XSLFShape, XSLFTextParagraph> createConnector() {
        XSLFConnectorShape sh = getDrawing().createConnector();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public GroupShape<XSLFShape, XSLFTextParagraph> createGroup() {
        XSLFGroupShape sh = getDrawing().createGroup();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public PictureShape<XSLFShape, XSLFTextParagraph> createPicture(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = getSheet().addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData);
        XSLFPictureShape sh = getDrawing().createPicture(rp.getRelationship().getId());
        new DrawPictureShape(sh).resize();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = getSheet().addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData);
        XSLFObjectShape sh = getDrawing().createOleShape(rp.getRelationship().getId());
        CTOleObject oleObj = sh.getCTOleObject();
        Dimension dim = pictureData.getImageDimension();
        oleObj.setImgW(Units.toEMU(dim.getWidth()));
        oleObj.setImgH(Units.toEMU(dim.getHeight()));
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable() {
        XSLFTable sh = getDrawing().createTable();
        this._shapes.add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public TableShape<XSLFShape, XSLFTextParagraph> createTable(int numRows, int numCols) {
        if (numRows < 1 || numCols < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable sh = getDrawing().createTable();
        this._shapes.add(sh);
        sh.setParent(this);
        for (int r = 0; r < numRows; r++) {
            XSLFTableRow row = sh.addRow();
            for (int c = 0; c < numCols; c++) {
                row.addCell();
            }
        }
        return sh;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setFlipHorizontal(boolean flip) {
        getSafeXfrm().setFlipH(flip);
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setFlipVertical(boolean flip) {
        getSafeXfrm().setFlipV(flip);
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public boolean getFlipHorizontal() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm != null && xfrm.isSetFlipH() && xfrm.getFlipH();
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public boolean getFlipVertical() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm != null && xfrm.isSetFlipV() && xfrm.getFlipV();
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setRotation(double theta) {
        getSafeXfrm().setRot((int) (60000.0d * theta));
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public double getRotation() {
        CTGroupTransform2D xfrm = getXfrm();
        if (xfrm == null || !xfrm.isSetRot()) {
            return 0.0d;
        }
        return xfrm.getRot() / 60000.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFShape
    public void copy(XSLFShape xSLFShape) {
        XSLFTable xSLFTable;
        List<XSLFShape> shapes = getShapes();
        List<XSLFShape> shapes2 = ((XSLFGroupShape) xSLFShape).getShapes();
        if (shapes.size() == shapes2.size()) {
            for (int i = 0; i < shapes.size(); i++) {
                shapes.get(i).copy(shapes2.get(i));
            }
            return;
        }
        clear();
        for (XSLFShape xSLFShape2 : shapes2) {
            if (xSLFShape2 instanceof XSLFTextBox) {
                xSLFTable = createTextBox();
            } else if (xSLFShape2 instanceof XSLFFreeformShape) {
                xSLFTable = createFreeform();
            } else if (xSLFShape2 instanceof XSLFAutoShape) {
                xSLFTable = createAutoShape();
            } else if (xSLFShape2 instanceof XSLFConnectorShape) {
                xSLFTable = createConnector();
            } else if (xSLFShape2 instanceof XSLFPictureShape) {
                XSLFPictureData pictureData = ((XSLFPictureShape) xSLFShape2).getPictureData();
                xSLFTable = createPicture((PictureData) getSheet().getSlideShow().addPicture(pictureData.getData(), pictureData.getType()));
            } else if (xSLFShape2 instanceof XSLFGroupShape) {
                xSLFTable = createGroup();
            } else if (xSLFShape2 instanceof XSLFTable) {
                xSLFTable = createTable();
            } else {
                LOG.atWarn().log("copying of class {} not supported.", xSLFShape2.getClass());
            }
            xSLFTable.copy(xSLFShape2);
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer
    public void clear() {
        List<XSLFShape> shapes = new ArrayList<>(getShapes());
        for (XSLFShape shape : shapes) {
            removeShape(shape);
        }
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    @NotImplemented
    public void addShape(XSLFShape shape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch with XSLFGroupShape.create* methods");
    }
}
