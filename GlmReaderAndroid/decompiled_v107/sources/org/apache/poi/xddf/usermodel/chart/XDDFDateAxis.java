package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes10.dex */
public class XDDFDateAxis extends XDDFChartAxis {
    private CTDateAx ctDateAx;

    public XDDFDateAxis(CTPlotArea plotArea, AxisPosition position) {
        initializeAxis(plotArea, position);
    }

    public XDDFDateAxis(CTDateAx ctDateAx) {
        this.ctDateAx = ctDateAx;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines;
        if (this.ctDateAx.isSetMajorGridlines()) {
            majorGridlines = this.ctDateAx.getMajorGridlines();
        } else {
            majorGridlines = this.ctDateAx.addNewMajorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(majorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines;
        if (this.ctDateAx.isSetMinorGridlines()) {
            minorGridlines = this.ctDateAx.getMinorGridlines();
        } else {
            minorGridlines = this.ctDateAx.addNewMinorGridlines();
        }
        return new XDDFShapeProperties(getOrAddLinesProperties(minorGridlines));
    }

    @Override // org.apache.poi.xddf.usermodel.HasShapeProperties
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties;
        if (this.ctDateAx.isSetSpPr()) {
            properties = this.ctDateAx.getSpPr();
        } else {
            properties = this.ctDateAx.addNewSpPr();
        }
        return new XDDFShapeProperties(properties);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text;
        if (this.ctDateAx.isSetTxPr()) {
            text = this.ctDateAx.getTxPr();
        } else {
            text = this.ctDateAx.addNewTxPr();
        }
        return new XDDFRunProperties(getOrAddTextProperties(text));
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setTitle(String text) {
        if (!this.ctDateAx.isSetTitle()) {
            this.ctDateAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctDateAx.getTitle());
        title.setOverlay(false);
        title.setText(text);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMinorUnit() {
        return this.ctDateAx.isSetMinorUnit();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMinorUnit(double minor) {
        if (Double.isNaN(minor)) {
            if (this.ctDateAx.isSetMinorUnit()) {
                this.ctDateAx.unsetMinorUnit();
            }
        } else if (this.ctDateAx.isSetMinorUnit()) {
            this.ctDateAx.getMinorUnit().setVal(minor);
        } else {
            this.ctDateAx.addNewMinorUnit().setVal(minor);
        }
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMinorUnit() {
        if (this.ctDateAx.isSetMinorUnit()) {
            return this.ctDateAx.getMinorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean isSetMajorUnit() {
        return this.ctDateAx.isSetMajorUnit();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void setMajorUnit(double major) {
        if (Double.isNaN(major)) {
            if (this.ctDateAx.isSetMajorUnit()) {
                this.ctDateAx.unsetMajorUnit();
            }
        } else if (this.ctDateAx.isSetMajorUnit()) {
            this.ctDateAx.getMajorUnit().setVal(major);
        } else {
            this.ctDateAx.addNewMajorUnit().setVal(major);
        }
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public double getMajorUnit() {
        if (this.ctDateAx.isSetMajorUnit()) {
            return this.ctDateAx.getMajorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public void crossAxis(XDDFChartAxis axis) {
        this.ctDateAx.getCrossAx().setVal(axis.getId());
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTUnsignedInt getCTAxId() {
        return this.ctDateAx.getAxId();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTAxPos getCTAxPos() {
        return this.ctDateAx.getAxPos();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    public boolean hasNumberFormat() {
        return this.ctDateAx.isSetNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTNumFmt getCTNumFmt() {
        if (this.ctDateAx.isSetNumFmt()) {
            return this.ctDateAx.getNumFmt();
        }
        return this.ctDateAx.addNewNumFmt();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTScaling getCTScaling() {
        return this.ctDateAx.getScaling();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctDateAx.getCrosses();
        if (crosses == null) {
            return this.ctDateAx.addNewCrosses();
        }
        return crosses;
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTBoolean getDelete() {
        return this.ctDateAx.getDelete();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMajorCTTickMark() {
        return this.ctDateAx.getMajorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickMark getMinorCTTickMark() {
        return this.ctDateAx.getMinorTickMark();
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartAxis
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctDateAx.getTickLblPos();
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = getNextAxId(plotArea);
        this.ctDateAx = plotArea.addNewDateAx();
        this.ctDateAx.addNewAxId().setVal(id);
        this.ctDateAx.addNewAuto().setVal(false);
        this.ctDateAx.addNewAxPos();
        this.ctDateAx.addNewScaling();
        this.ctDateAx.addNewCrosses();
        this.ctDateAx.addNewCrossAx();
        this.ctDateAx.addNewTickLblPos();
        this.ctDateAx.addNewDelete();
        this.ctDateAx.addNewMajorTickMark();
        this.ctDateAx.addNewMinorTickMark();
        this.ctDateAx.addNewNumFmt().setSourceLinked(true);
        this.ctDateAx.getNumFmt().setFormatCode("");
        setPosition(position);
        setOrientation(AxisOrientation.MIN_MAX);
        setCrosses(AxisCrosses.AUTO_ZERO);
        setVisible(true);
        setMajorTickMark(AxisTickMark.CROSS);
        setMinorTickMark(AxisTickMark.NONE);
        setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}
