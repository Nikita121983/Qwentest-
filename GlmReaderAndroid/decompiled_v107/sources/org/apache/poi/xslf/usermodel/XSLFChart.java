package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

/* loaded from: classes10.dex */
public final class XSLFChart extends XDDFChart {
    private static String CHART_URI = XSSFRelation.NS_CHART;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFChart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFChart(PackagePart part) throws IOException, XmlException {
        super(part);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartRelation() {
        return XSLFRelation.CHART;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartWorkbookRelation() {
        return XSLFRelation.WORKBOOK;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLFactory getChartFactory() {
        return XSLFFactory.getInstance();
    }

    public XSLFTextShape getTitleShape() {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        final CTTitle title = getCTChart().getTitle();
        XSLFSheet xSLFSheet = null;
        if (title.getTx() != null && title.getTx().isSetRich()) {
            return new XSLFTextShape(title, xSLFSheet) { // from class: org.apache.poi.xslf.usermodel.XSLFChart.1
                @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
                protected CTTextBody getTextBody(boolean create) {
                    return title.getTx().getRich();
                }
            };
        }
        return new XSLFTextShape(title, xSLFSheet) { // from class: org.apache.poi.xslf.usermodel.XSLFChart.2
            @Override // org.apache.poi.xslf.usermodel.XSLFTextShape
            protected CTTextBody getTextBody(boolean create) {
                return title.getTxPr();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTGraphicalObjectFrame prototype(int shapeId, String rID, Rectangle2D anchor) {
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Chart " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        nvGr.addNewNvPr();
        CTTransform2D xfrm = frame.addNewXfrm();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(Integer.valueOf((int) anchor.getX()));
        off.setY(Integer.valueOf((int) anchor.getY()));
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx((int) anchor.getWidth());
        ext.setCy((int) anchor.getHeight());
        xfrm.setExt(ext);
        xfrm.setOff(off);
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        XmlCursor grCur = gr.newCursor();
        try {
            grCur.toNextToken();
            grCur.beginElement(new QName(CHART_URI, "chart"));
            grCur.insertAttributeWithValue("id", PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, rID);
            if (grCur != null) {
                grCur.close();
            }
            gr.setUri(CHART_URI);
            return frame;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (grCur != null) {
                    try {
                        grCur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
