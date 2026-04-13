package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes10.dex */
public class XDDFSeriesAxis extends XDDFChartAxis {
    private CTSerAx ctSerAx;

    public XDDFSeriesAxis(CTPlotArea plotArea, AxisPosition position) {
        initializeAxis(plotArea, position);
    }

    public XDDFSeriesAxis(CTSerAx ctSerAx) {
        this.ctSerAx = ctSerAx;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines;
        if (this.ctSerAx.isSetMajorGridlines()) {
            majorGridlines = this.ctSerAx.getMajorGridlines();
        } else {
            majorGridlines = this.ctSerAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(majorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines;
        if (this.ctSerAx.isSetMinorGridlines()) {
            minorGridlines = this.ctSerAx.getMinorGridlines();
        } else {
            minorGridlines = this.ctSerAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(minorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.HasShapeProperties
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties;
        if (this.ctSerAx.isSetSpPr()) {
            properties = this.ctSerAx.getSpPr();
        } else {
            properties = this.ctSerAx.addNewSpPr();
        }
        return new XDDFShapeProperties(properties);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text;
        if (this.ctSerAx.isSetTxPr()) {
            text = this.ctSerAx.getTxPr();
        } else {
            text = this.ctSerAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(text));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setTitle(String text) {
        if (!this.ctSerAx.isSetTitle()) {
            this.ctSerAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctSerAx.getTitle());
        title.setOverlay(false);
        title.setText(text);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMinorUnit() {
        return false;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMinorUnit(double minor) {
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMinorUnit() {
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMajorUnit() {
        return false;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMajorUnit(double major) {
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMajorUnit() {
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void crossAxis(XDDFChartAxis axis) {
        this.ctSerAx.getCrossAx().setVal(axis.getId());
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTUnsignedInt getCTAxId() {
        return this.ctSerAx.getAxId();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTAxPos getCTAxPos() {
        return this.ctSerAx.getAxPos();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean hasNumberFormat() {
        return this.ctSerAx.isSetNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTNumFmt getCTNumFmt() {
        if (this.ctSerAx.isSetNumFmt()) {
            return this.ctSerAx.getNumFmt();
        }
        return this.ctSerAx.addNewNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTScaling getCTScaling() {
        return this.ctSerAx.getScaling();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctSerAx.getCrosses();
        if (crosses == null) {
            return this.ctSerAx.addNewCrosses();
        }
        return crosses;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTBoolean getDelete() {
        return this.ctSerAx.getDelete();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMajorCTTickMark() {
        return this.ctSerAx.getMajorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMinorCTTickMark() {
        return this.ctSerAx.getMinorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctSerAx.getTickLblPos();
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = getNextAxId(plotArea);
        this.ctSerAx = plotArea.addNewSerAx();
        this.ctSerAx.addNewAxId().setVal(id);
        this.ctSerAx.addNewAxPos();
        this.ctSerAx.addNewScaling();
        this.ctSerAx.addNewCrosses();
        this.ctSerAx.addNewCrossAx();
        this.ctSerAx.addNewTickLblPos();
        this.ctSerAx.addNewDelete();
        this.ctSerAx.addNewMajorTickMark();
        this.ctSerAx.addNewMinorTickMark();
        setPosition(position);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
