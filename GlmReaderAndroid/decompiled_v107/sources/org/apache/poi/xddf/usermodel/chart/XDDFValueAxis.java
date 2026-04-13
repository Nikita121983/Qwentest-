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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes10.dex */
public class XDDFValueAxis extends XDDFChartAxis {
    private CTValAx ctValAx;

    public XDDFValueAxis(CTPlotArea plotArea, AxisPosition position) {
        initializeAxis(plotArea, position);
    }

    public XDDFValueAxis(CTValAx ctValAx) {
        this.ctValAx = ctValAx;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines;
        if (this.ctValAx.isSetMajorGridlines()) {
            majorGridlines = this.ctValAx.getMajorGridlines();
        } else {
            majorGridlines = this.ctValAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(majorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines;
        if (this.ctValAx.isSetMinorGridlines()) {
            minorGridlines = this.ctValAx.getMinorGridlines();
        } else {
            minorGridlines = this.ctValAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(minorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.HasShapeProperties
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties;
        if (this.ctValAx.isSetSpPr()) {
            properties = this.ctValAx.getSpPr();
        } else {
            properties = this.ctValAx.addNewSpPr();
        }
        return new XDDFShapeProperties(properties);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text;
        if (this.ctValAx.isSetTxPr()) {
            text = this.ctValAx.getTxPr();
        } else {
            text = this.ctValAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(text));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setTitle(String text) {
        if (!this.ctValAx.isSetTitle()) {
            this.ctValAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctValAx.getTitle());
        title.setOverlay(false);
        title.setText(text);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMinorUnit() {
        return this.ctValAx.isSetMinorUnit();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMinorUnit(double minor) {
        if (Double.isNaN(minor)) {
            if (this.ctValAx.isSetMinorUnit()) {
                this.ctValAx.unsetMinorUnit();
            }
        } else if (this.ctValAx.isSetMinorUnit()) {
            this.ctValAx.getMinorUnit().setVal(minor);
        } else {
            this.ctValAx.addNewMinorUnit().setVal(minor);
        }
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMinorUnit() {
        if (this.ctValAx.isSetMinorUnit()) {
            return this.ctValAx.getMinorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMajorUnit() {
        return this.ctValAx.isSetMajorUnit();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMajorUnit(double major) {
        if (Double.isNaN(major)) {
            if (this.ctValAx.isSetMajorUnit()) {
                this.ctValAx.unsetMajorUnit();
            }
        } else if (this.ctValAx.isSetMajorUnit()) {
            this.ctValAx.getMajorUnit().setVal(major);
        } else {
            this.ctValAx.addNewMajorUnit().setVal(major);
        }
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMajorUnit() {
        if (this.ctValAx.isSetMajorUnit()) {
            return this.ctValAx.getMajorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void crossAxis(XDDFChartAxis axis) {
        this.ctValAx.getCrossAx().setVal(axis.getId());
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTUnsignedInt getCTAxId() {
        return this.ctValAx.getAxId();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTAxPos getCTAxPos() {
        return this.ctValAx.getAxPos();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean hasNumberFormat() {
        return this.ctValAx.isSetNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTNumFmt getCTNumFmt() {
        if (this.ctValAx.isSetNumFmt()) {
            return this.ctValAx.getNumFmt();
        }
        return this.ctValAx.addNewNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTScaling getCTScaling() {
        return this.ctValAx.getScaling();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctValAx.getCrosses();
        if (crosses == null) {
            return this.ctValAx.addNewCrosses();
        }
        return crosses;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTBoolean getDelete() {
        return this.ctValAx.getDelete();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMajorCTTickMark() {
        return this.ctValAx.getMajorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMinorCTTickMark() {
        return this.ctValAx.getMinorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctValAx.getTickLblPos();
    }

    public AxisCrossBetween getCrossBetween() {
        return AxisCrossBetween.valueOf(this.ctValAx.getCrossBetween().getVal());
    }

    public void setCrossBetween(AxisCrossBetween crossBetween) {
        this.ctValAx.getCrossBetween().setVal(crossBetween.underlying);
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = getNextAxId(plotArea);
        this.ctValAx = plotArea.addNewValAx();
        this.ctValAx.addNewAxId().setVal(id);
        this.ctValAx.addNewAxPos();
        this.ctValAx.addNewScaling();
        this.ctValAx.addNewCrossBetween();
        this.ctValAx.addNewCrosses();
        this.ctValAx.addNewCrossAx();
        this.ctValAx.addNewTickLblPos();
        this.ctValAx.addNewDelete();
        this.ctValAx.addNewMajorTickMark();
        this.ctValAx.addNewMinorTickMark();
        setPosition(position);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrossBetween(AxisCrossBetween.MIDPOINT_CATEGORY);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
