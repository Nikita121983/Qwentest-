package org.apache.poi.xslf.usermodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.draw.geom.XSLFCustomGeometry;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

/* loaded from: classes10.dex */
public class XSLFFreeformShape extends XSLFAutoShape implements FreeformShape<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFFreeformShape.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFFreeformShape(CTShape shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    @Override // org.apache.poi.sl.usermodel.FreeformShape
    public int setPath(Path2D path) {
        CTAdjPoint2D[] points;
        CTPath2D ctPath = CTPath2D.Factory.newInstance();
        Rectangle2D bounds = path.getBounds2D();
        int x0 = Units.toEMU(bounds.getX());
        int y0 = Units.toEMU(bounds.getY());
        PathIterator it = path.getPathIterator(new AffineTransform());
        int numPoints = 0;
        ctPath.setH(Units.toEMU(bounds.getHeight()));
        ctPath.setW(Units.toEMU(bounds.getWidth()));
        double[] vals = new double[6];
        while (true) {
            if (!it.isDone()) {
                int type = it.currentSegment(vals);
                switch (type) {
                    case 0:
                        points = addMoveTo(ctPath);
                        break;
                    case 1:
                        points = addLineTo(ctPath);
                        break;
                    case 2:
                        points = addQuadBezierTo(ctPath);
                        break;
                    case 3:
                        points = addCubicBezierTo(ctPath);
                        break;
                    case 4:
                        points = addClosePath(ctPath);
                        break;
                    default:
                        throw new IllegalStateException("Unrecognized path segment type: " + type);
                }
                int i = 0;
                for (CTAdjPoint2D point : points) {
                    int i2 = i + 1;
                    point.setX(Integer.valueOf(Units.toEMU(vals[i]) - x0));
                    i = i2 + 1;
                    point.setY(Integer.valueOf(Units.toEMU(vals[i2]) - y0));
                }
                numPoints += Math.max(points.length, 1);
                it.next();
            } else {
                XmlObject xo = getShapeProperties();
                if (!(xo instanceof CTShapeProperties)) {
                    return -1;
                }
                ((CTShapeProperties) xo).getCustGeom().getPathLst().setPathArray(new CTPath2D[]{ctPath});
                setAnchor(bounds);
                return numPoints;
            }
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.sl.usermodel.SimpleShape
    public CustomGeometry getGeometry() {
        XmlObject xo = getShapeProperties();
        if (!(xo instanceof CTShapeProperties)) {
            return null;
        }
        return XSLFCustomGeometry.convertCustomGeometry(((CTShapeProperties) xo).getCustGeom());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r8.toFirstChild() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r9 = r8.getObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if ((r9 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo) == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        addMoveTo(r0, (org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo) r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x007b, code lost:
    
        if (r8.toNextSibling() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
    
        if ((r9 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        addLineTo(r0, (org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo) r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        if ((r9 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
    
        addQuadBezierTo(r0, (org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo) r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0057, code lost:
    
        if ((r9 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0059, code lost:
    
        addCubicBezierTo(r0, (org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo) r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
    
        if ((r9 instanceof org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose) == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
    
        addClosePath((java.awt.geom.Path2D) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        org.apache.poi.xslf.usermodel.XSLFFreeformShape.LOG.atWarn().log("can't handle path of type {}", r2.getClass());
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007d, code lost:
    
        if (r8 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007f, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0082, code lost:
    
        r6 = r6 + 1;
     */
    @Override // org.apache.poi.sl.usermodel.FreeformShape
    /* renamed from: getPath, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.awt.geom.Path2D.Double mo2575getPath() {
        /*
            Method dump skipped, instructions count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFFreeformShape.mo2575getPath():java.awt.geom.Path2D$Double");
    }

    private static CTAdjPoint2D[] addMoveTo(CTPath2D path) {
        return new CTAdjPoint2D[]{path.addNewMoveTo().addNewPt()};
    }

    private static void addMoveTo(Path2D path, CTPath2DMoveTo xo) {
        CTAdjPoint2D pt = xo.getPt();
        path.moveTo(((Long) pt.getX()).longValue(), ((Long) pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addLineTo(CTPath2D path) {
        return new CTAdjPoint2D[]{path.addNewLnTo().addNewPt()};
    }

    private static void addLineTo(Path2D path, CTPath2DLineTo xo) {
        CTAdjPoint2D pt = xo.getPt();
        path.lineTo(((Long) pt.getX()).longValue(), ((Long) pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addQuadBezierTo(CTPath2D path) {
        CTPath2DQuadBezierTo bez = path.addNewQuadBezTo();
        return new CTAdjPoint2D[]{bez.addNewPt(), bez.addNewPt()};
    }

    private static void addQuadBezierTo(Path2D path, CTPath2DQuadBezierTo xo) {
        CTAdjPoint2D pt1 = xo.getPtArray(0);
        CTAdjPoint2D pt2 = xo.getPtArray(1);
        path.quadTo(((Long) pt1.getX()).longValue(), ((Long) pt1.getY()).longValue(), ((Long) pt2.getX()).longValue(), ((Long) pt2.getY()).longValue());
    }

    private static CTAdjPoint2D[] addCubicBezierTo(CTPath2D path) {
        CTPath2DCubicBezierTo bez = path.addNewCubicBezTo();
        return new CTAdjPoint2D[]{bez.addNewPt(), bez.addNewPt(), bez.addNewPt()};
    }

    private static void addCubicBezierTo(Path2D path, CTPath2DCubicBezierTo xo) {
        CTAdjPoint2D pt1 = xo.getPtArray(0);
        CTAdjPoint2D pt2 = xo.getPtArray(1);
        CTAdjPoint2D pt3 = xo.getPtArray(2);
        path.curveTo(((Long) pt1.getX()).longValue(), ((Long) pt1.getY()).longValue(), ((Long) pt2.getX()).longValue(), ((Long) pt2.getY()).longValue(), ((Long) pt3.getX()).longValue(), ((Long) pt3.getY()).longValue());
    }

    private static CTAdjPoint2D[] addClosePath(CTPath2D path) {
        path.addNewClose();
        return new CTAdjPoint2D[0];
    }

    private static void addClosePath(Path2D path) {
        path.closePath();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTShape prototype(int shapeId) {
        CTShape ct = CTShape.Factory.newInstance();
        CTShapeNonVisual nvSpPr = ct.addNewNvSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Freeform " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvSpPr();
        nvSpPr.addNewNvPr();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTCustomGeometry2D geom = spPr.addNewCustGeom();
        geom.addNewAvLst();
        geom.addNewGdLst();
        geom.addNewAhLst();
        geom.addNewCxnLst();
        CTGeomRect rect = geom.addNewRect();
        rect.setR("r");
        rect.setB("b");
        rect.setT("t");
        rect.setL("l");
        geom.addNewPathLst();
        return ct;
    }
}
