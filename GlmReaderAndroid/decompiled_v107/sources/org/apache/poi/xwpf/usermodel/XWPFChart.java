package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

/* loaded from: classes10.dex */
public class XWPFChart extends XDDFChart {
    public static final int DEFAULT_HEIGHT = 500000;
    public static final int DEFAULT_WIDTH = 500000;
    private Long checksum;
    private CTInline ctInline;

    /* JADX INFO: Access modifiers changed from: protected */
    public XWPFChart() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XWPFChart(PackagePart part) throws IOException, XmlException {
        super(part);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartRelation() {
        return XWPFRelation.CHART;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLRelation getChartWorkbookRelation() {
        return XWPFRelation.WORKBOOK;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChart
    protected POIXMLFactory getChartFactory() {
        return XWPFFactory.getInstance();
    }

    public Long getChecksum() {
        if (this.checksum == null) {
            try {
                InputStream is = getPackagePart().getInputStream();
                try {
                    this.checksum = Long.valueOf(IOUtils.calculateChecksum(is));
                    if (is != null) {
                        is.close();
                    }
                } finally {
                }
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        }
        return this.checksum;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return getChecksum().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void attach(String chartRelId, XWPFRun run) throws InvalidFormatException, IOException {
        this.ctInline = run.addChart(chartRelId);
        this.ctInline.addNewExtent();
        setChartBoundingBox(500000L, 500000L);
    }

    public void setChartHeight(long height) {
        this.ctInline.getExtent().setCy(height);
    }

    public void setChartWidth(long width) {
        this.ctInline.getExtent().setCx(width);
    }

    public long getChartHeight() {
        return this.ctInline.getExtent().getCy();
    }

    public long getChartWidth() {
        return this.ctInline.getExtent().getCx();
    }

    public void setChartBoundingBox(long width, long height) {
        setChartWidth(width);
        setChartHeight(height);
    }

    public void setChartTopMargin(long margin) {
        this.ctInline.setDistT(margin);
    }

    public long getChartTopMargin(long margin) {
        return this.ctInline.getDistT();
    }

    public void setChartBottomMargin(long margin) {
        this.ctInline.setDistB(margin);
    }

    public long getChartBottomMargin(long margin) {
        return this.ctInline.getDistB();
    }

    public void setChartLeftMargin(long margin) {
        this.ctInline.setDistL(margin);
    }

    public long getChartLeftMargin(long margin) {
        return this.ctInline.getDistL();
    }

    public void setChartRightMargin(long margin) {
        this.ctInline.setDistR(margin);
    }

    public long getChartRightMargin(long margin) {
        return this.ctInline.getDistR();
    }

    public void setChartMargin(long top, long right, long bottom, long left) {
        setChartBottomMargin(bottom);
        setChartRightMargin(right);
        setChartLeftMargin(left);
        setChartRightMargin(right);
    }
}
