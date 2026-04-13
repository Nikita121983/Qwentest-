package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes10.dex */
public class XDDFCategoryAxis extends XDDFChartAxis {
    private CTCatAx ctCatAx;

    public XDDFCategoryAxis(CTPlotArea plotArea, AxisPosition position) {
        initializeAxis(plotArea, position);
    }

    public XDDFCategoryAxis(CTCatAx ctCatAx) {
        this.ctCatAx = ctCatAx;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines;
        if (this.ctCatAx.isSetMajorGridlines()) {
            majorGridlines = this.ctCatAx.getMajorGridlines();
        } else {
            majorGridlines = this.ctCatAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(majorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines;
        if (this.ctCatAx.isSetMinorGridlines()) {
            minorGridlines = this.ctCatAx.getMinorGridlines();
        } else {
            minorGridlines = this.ctCatAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(minorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.HasShapeProperties
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties;
        if (this.ctCatAx.isSetSpPr()) {
            properties = this.ctCatAx.getSpPr();
        } else {
            properties = this.ctCatAx.addNewSpPr();
        }
        return new XDDFShapeProperties(properties);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text;
        if (this.ctCatAx.isSetTxPr()) {
            text = this.ctCatAx.getTxPr();
        } else {
            text = this.ctCatAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(text));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setTitle(String text) {
        if (!this.ctCatAx.isSetTitle()) {
            this.ctCatAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctCatAx.getTitle());
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
        this.ctCatAx.getCrossAx().setVal(axis.getId());
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTUnsignedInt getCTAxId() {
        return this.ctCatAx.getAxId();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTAxPos getCTAxPos() {
        return this.ctCatAx.getAxPos();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean hasNumberFormat() {
        return this.ctCatAx.isSetNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTNumFmt getCTNumFmt() {
        if (this.ctCatAx.isSetNumFmt()) {
            return this.ctCatAx.getNumFmt();
        }
        return this.ctCatAx.addNewNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTScaling getCTScaling() {
        return this.ctCatAx.getScaling();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctCatAx.getCrosses();
        if (crosses == null) {
            return this.ctCatAx.addNewCrosses();
        }
        return crosses;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTBoolean getDelete() {
        return this.ctCatAx.getDelete();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMajorCTTickMark() {
        return this.ctCatAx.getMajorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMinorCTTickMark() {
        return this.ctCatAx.getMinorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctCatAx.getTickLblPos();
    }

    public AxisLabelAlignment getLabelAlignment() {
        return AxisLabelAlignment.valueOf(this.ctCatAx.getLblAlgn().getVal());
    }

    public void setLabelAlignment(AxisLabelAlignment labelAlignment) {
        this.ctCatAx.getLblAlgn().setVal(labelAlignment.underlying);
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = getNextAxId(plotArea);
        this.ctCatAx = plotArea.addNewCatAx();
        this.ctCatAx.addNewAxId().setVal(id);
        this.ctCatAx.addNewAuto().setVal(false);
        this.ctCatAx.addNewAxPos();
        this.ctCatAx.addNewScaling();
        this.ctCatAx.addNewCrosses();
        this.ctCatAx.addNewCrossAx();
        this.ctCatAx.addNewTickLblPos();
        this.ctCatAx.addNewDelete();
        this.ctCatAx.addNewMajorTickMark();
        this.ctCatAx.addNewMinorTickMark();
        setPosition(position);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
