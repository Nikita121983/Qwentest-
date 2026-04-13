package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

/* loaded from: classes10.dex */
public class XSLFGraphicFrame extends XSLFShape implements GraphicalFrame<XSLFShape, XSLFTextParagraph> {
    private static final String DRAWINGML_CHART_URI = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFGraphicFrame.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFGraphicFrame(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    @NotImplemented
    public ShapeType getShapeType() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.poi.sl.usermodel.Shape, org.apache.poi.sl.usermodel.PlaceableShape
    public Rectangle2D getAnchor() {
        CTTransform2D xfrm = ((CTGraphicalObjectFrame) getXmlObject()).getXfrm();
        if (xfrm == null) {
            throw new IllegalArgumentException("Could not retrieve an Xfrm from the XML object");
        }
        CTPoint2D off = xfrm.getOff();
        if (off == null || off.getX() == null || off.getY() == null) {
            throw new IllegalArgumentException("Could not retrieve Off from the XML object");
        }
        double x = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double y = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getExt();
        if (ext == null) {
            throw new IllegalArgumentException("Could not retrieve Ext from the XML object");
        }
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setAnchor(Rectangle2D anchor) {
        CTTransform2D xfrm = ((CTGraphicalObjectFrame) getXmlObject()).getXfrm();
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static XSLFGraphicFrame create(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        char c;
        String uri = getUri(shape);
        String str = uri == null ? "" : uri;
        switch (str.hashCode()) {
            case -844661481:
                if (str.equals("http://schemas.openxmlformats.org/presentationml/2006/ole")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -809269002:
                if (str.equals(XSLFDiagram.DRAWINGML_DIAGRAM_URI)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -603062095:
                if (str.equals("http://schemas.openxmlformats.org/drawingml/2006/table")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new XSLFTable(shape, sheet);
            case 1:
                return new XSLFObjectShape(shape, sheet);
            case 2:
                return new XSLFDiagram(shape, sheet);
            default:
                return new XSLFGraphicFrame(shape, sheet);
        }
    }

    private static String getUri(CTGraphicalObjectFrame shape) {
        CTGraphicalObjectData gd;
        CTGraphicalObject g = shape.getGraphic();
        if (g == null || (gd = g.getGraphicData()) == null) {
            return null;
        }
        return gd.getUri();
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setRotation(double theta) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public double getRotation() {
        return 0.0d;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setFlipHorizontal(boolean flip) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public void setFlipVertical(boolean flip) {
        throw new IllegalArgumentException("Operation not supported");
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public boolean getFlipHorizontal() {
        return false;
    }

    @Override // org.apache.poi.sl.usermodel.PlaceableShape
    public boolean getFlipVertical() {
        return false;
    }

    public boolean hasChart() {
        String uri = getGraphicalData().getUri();
        return uri.equals("http://schemas.openxmlformats.org/drawingml/2006/chart");
    }

    public boolean hasDiagram() {
        String uri = getGraphicalData().getUri();
        return uri.equals(XSLFDiagram.DRAWINGML_DIAGRAM_URI);
    }

    private CTGraphicalObjectData getGraphicalData() {
        return ((CTGraphicalObjectFrame) getXmlObject()).getGraphic().getGraphicData();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public XSLFChart getChart() {
        if (!hasChart()) {
            return null;
        }
        String id = null;
        XmlObject[] obj = getGraphicalData().selectPath("declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart");
        if (obj != null && obj.length == 1) {
            XmlCursor c = obj[0].newCursor();
            try {
                QName idQualifiedName = new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id");
                id = c.getAttributeText(idQualifiedName);
                if (c != null) {
                    c.close();
                }
            } finally {
            }
        }
        if (id == null) {
            return null;
        }
        return (XSLFChart) getSheet().getRelationById(id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFShape
    public void copy(XSLFShape sh) {
        super.copy(sh);
        CTGraphicalObjectData data = getGraphicalData();
        String uri = data.getUri();
        if (uri.equals(XSLFDiagram.DRAWINGML_DIAGRAM_URI)) {
            copyDiagram(data, (XSLFGraphicFrame) sh);
        }
        if (uri.equals("http://schemas.openxmlformats.org/drawingml/2006/chart")) {
            copyChart(data, (XSLFGraphicFrame) sh);
        }
    }

    /* JADX WARN: Type inference failed for: r14v0, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    /* JADX WARN: Type inference failed for: r2v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    private void copyChart(CTGraphicalObjectData objData, XSLFGraphicFrame srcShape) {
        XSLFPropertiesDelegate.XSLFFillProperties fp;
        XSLFSlide slide = (XSLFSlide) getSheet();
        ?? sheet = srcShape.getSheet();
        XmlObject[] obj = objData.selectPath("declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart");
        if (obj != null && obj.length == 1) {
            try {
                XmlCursor c = obj[0].newCursor();
                try {
                    QName idQualifiedName = new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id");
                    String id = c.getAttributeText(idQualifiedName);
                    XSLFChart srcChart = (XSLFChart) sheet.getRelationById(id);
                    XSLFChart chartCopy = slide.getSlideShow().createChart(slide);
                    chartCopy.importContent(srcChart);
                    chartCopy.setWorkbook(srcChart.getWorkbook());
                    c.setAttributeText(idQualifiedName, slide.getRelationId(chartCopy));
                    CTChartSpace chartSpaceCopy = chartCopy.getCTChartSpace();
                    if (chartSpaceCopy != null && (fp = XSLFPropertiesDelegate.getFillDelegate(chartSpaceCopy.getSpPr())) != null && fp.isSetBlipFill()) {
                        CTBlip blip = fp.getBlipFill().getBlip();
                        String blipId = blip.getEmbed();
                        String relId = slide.getSlideShow().importBlip(blipId, srcChart, chartCopy);
                        blip.setEmbed(relId);
                    }
                    if (c != null) {
                        c.close();
                    }
                } finally {
                }
            } catch (IOException | InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r12v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r15v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r4v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    private void copyDiagram(CTGraphicalObjectData objData, XSLFGraphicFrame srcShape) {
        Throwable th;
        XmlObject[] obj = objData.selectPath("declare namespace dgm='http://schemas.openxmlformats.org/drawingml/2006/diagram' $this//dgm:relIds");
        if (obj != null && obj.length == 1) {
            ?? sheet = srcShape.getSheet();
            try {
                try {
                    XmlCursor c = obj[0].newCursor();
                    try {
                        String dm = c.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "dm"));
                        PackageRelationship dmRel = sheet.getPackagePart().getRelationship(dm);
                        PackagePart dmPart = sheet.getPackagePart().getRelatedPart(dmRel);
                        getSheet().importPart(dmRel, dmPart);
                        String lo = c.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "lo"));
                        PackageRelationship loRel = sheet.getPackagePart().getRelationship(lo);
                        PackagePart loPart = sheet.getPackagePart().getRelatedPart(loRel);
                        getSheet().importPart(loRel, loPart);
                        String qs = c.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "qs"));
                        PackageRelationship qsRel = sheet.getPackagePart().getRelationship(qs);
                        PackagePart qsPart = sheet.getPackagePart().getRelatedPart(qsRel);
                        getSheet().importPart(qsRel, qsPart);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        String cs = c.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "cs"));
                        PackageRelationship csRel = sheet.getPackagePart().getRelationship(cs);
                        PackagePart csPart = sheet.getPackagePart().getRelatedPart(csRel);
                        getSheet().importPart(csRel, csPart);
                        if (c != null) {
                            c.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (InvalidFormatException e) {
                    e = e;
                    throw new POIXMLException(e);
                }
            } catch (InvalidFormatException e2) {
                e = e2;
                throw new POIXMLException(e);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.GraphicalFrame
    public PictureShape<XSLFShape, XSLFTextParagraph> getFallbackPicture() {
        XmlObject xo = selectProperty(XmlObject.class, "declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main'; declare namespace mc='http://schemas.openxmlformats.org/markup-compatibility/2006' .//mc:Fallback/*/p:pic");
        if (xo == null) {
            return null;
        }
        try {
            CTGroupShape gs = CTGroupShape.Factory.parse(xo.newDomNode());
            if (gs.sizeOfPicArray() == 0) {
                return null;
            }
            return new XSLFPictureShape(gs.getPicArray(0), getSheet());
        } catch (XmlException e) {
            LOG.atWarn().withThrowable(e).log("Can't parse fallback picture stream of graphical frame");
            return null;
        }
    }
}
