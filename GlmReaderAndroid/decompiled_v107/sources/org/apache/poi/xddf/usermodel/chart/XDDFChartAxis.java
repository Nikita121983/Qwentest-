package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.HasShapeProperties;
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public abstract class XDDFChartAxis implements HasShapeProperties {
    private static final double MAX_LOG_BASE = 1000.0d;
    private static final double MIN_LOG_BASE = 2.0d;

    public abstract void crossAxis(XDDFChartAxis xDDFChartAxis);

    protected abstract CTUnsignedInt getCTAxId();

    protected abstract CTAxPos getCTAxPos();

    protected abstract CTCrosses getCTCrosses();

    protected abstract CTNumFmt getCTNumFmt();

    protected abstract CTScaling getCTScaling();

    protected abstract CTTickLblPos getCTTickLblPos();

    protected abstract CTBoolean getDelete();

    protected abstract CTTickMark getMajorCTTickMark();

    public abstract double getMajorUnit();

    protected abstract CTTickMark getMinorCTTickMark();

    public abstract double getMinorUnit();

    public abstract XDDFShapeProperties getOrAddMajorGridProperties();

    public abstract XDDFShapeProperties getOrAddMinorGridProperties();

    public abstract XDDFRunProperties getOrAddTextProperties();

    public abstract boolean hasNumberFormat();

    public abstract boolean isSetMajorUnit();

    public abstract boolean isSetMinorUnit();

    public abstract void setMajorUnit(double d);

    public abstract void setMinorUnit(double d);

    public abstract void setTitle(String str);

    public long getId() {
        return getCTAxId().getVal();
    }

    public AxisPosition getPosition() {
        return AxisPosition.valueOf(getCTAxPos().getVal());
    }

    public void setPosition(AxisPosition position) {
        getCTAxPos().setVal(position.underlying);
    }

    public void setNumberFormat(String format) {
        getCTNumFmt().setFormatCode(format);
        getCTNumFmt().setSourceLinked(true);
    }

    public String getNumberFormat() {
        if (hasNumberFormat()) {
            return getCTNumFmt().getFormatCode();
        }
        return null;
    }

    public boolean isSetLogBase() {
        return getCTScaling().isSetLogBase();
    }

    public void setLogBase(double logBase) {
        if (logBase < MIN_LOG_BASE || MAX_LOG_BASE < logBase) {
            throw new IllegalArgumentException("Axis log base must be between 2 and 1000 (inclusive), got: " + logBase);
        }
        CTScaling scaling = getCTScaling();
        if (scaling.isSetLogBase()) {
            scaling.getLogBase().setVal(logBase);
        } else {
            scaling.addNewLogBase().setVal(logBase);
        }
    }

    public double getLogBase() {
        CTScaling scaling = getCTScaling();
        if (scaling.isSetLogBase()) {
            return scaling.getLogBase().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMinimum() {
        return getCTScaling().isSetMin();
    }

    public void setMinimum(double min) {
        CTScaling scaling = getCTScaling();
        if (Double.isNaN(min)) {
            if (scaling.isSetMin()) {
                scaling.unsetMin();
            }
        } else if (scaling.isSetMin()) {
            scaling.getMin().setVal(min);
        } else {
            scaling.addNewMin().setVal(min);
        }
    }

    public double getMinimum() {
        CTScaling scaling = getCTScaling();
        if (scaling.isSetMin()) {
            return scaling.getMin().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMaximum() {
        return getCTScaling().isSetMax();
    }

    public void setMaximum(double max) {
        CTScaling scaling = getCTScaling();
        if (Double.isNaN(max)) {
            if (scaling.isSetMax()) {
                scaling.unsetMax();
            }
        } else if (scaling.isSetMax()) {
            scaling.getMax().setVal(max);
        } else {
            scaling.addNewMax().setVal(max);
        }
    }

    public double getMaximum() {
        CTScaling scaling = getCTScaling();
        if (scaling.isSetMax()) {
            return scaling.getMax().getVal();
        }
        return Double.NaN;
    }

    public AxisOrientation getOrientation() {
        return AxisOrientation.valueOf(getCTScaling().getOrientation().getVal());
    }

    public void setOrientation(AxisOrientation orientation) {
        CTScaling scaling = getCTScaling();
        if (scaling.isSetOrientation()) {
            scaling.getOrientation().setVal(orientation.underlying);
        } else {
            scaling.addNewOrientation().setVal(orientation.underlying);
        }
    }

    public AxisCrosses getCrosses() {
        return AxisCrosses.valueOf(getCTCrosses().getVal());
    }

    public void setCrosses(AxisCrosses crosses) {
        getCTCrosses().setVal(crosses.underlying);
    }

    public boolean isVisible() {
        return !getDelete().getVal();
    }

    public void setVisible(boolean value) {
        getDelete().setVal(!value);
    }

    public AxisTickMark getMajorTickMark() {
        return AxisTickMark.valueOf(getMajorCTTickMark().getVal());
    }

    public void setMajorTickMark(AxisTickMark tickMark) {
        getMajorCTTickMark().setVal(tickMark.underlying);
    }

    public AxisTickMark getMinorTickMark() {
        return AxisTickMark.valueOf(getMinorCTTickMark().getVal());
    }

    public void setMinorTickMark(AxisTickMark tickMark) {
        getMinorCTTickMark().setVal(tickMark.underlying);
    }

    public AxisTickLabelPosition getTickLabelPosition() {
        return AxisTickLabelPosition.valueOf(getCTTickLblPos().getVal());
    }

    public void setTickLabelPosition(AxisTickLabelPosition labelPosition) {
        getCTTickLblPos().setVal(labelPosition.underlying);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTTextCharacterProperties getOrAddTextProperties(CTTextBody body) {
        CTTextParagraph paragraph;
        CTTextParagraphProperties paraprops;
        if (body.getBodyPr() == null) {
            body.addNewBodyPr();
        }
        if (body.sizeOfPArray() > 0) {
            paragraph = body.getPArray(0);
        } else {
            paragraph = body.addNewP();
        }
        if (paragraph.isSetPPr()) {
            paraprops = paragraph.getPPr();
        } else {
            paraprops = paragraph.addNewPPr();
        }
        if (paraprops.isSetDefRPr()) {
            CTTextCharacterProperties properties = paraprops.getDefRPr();
            return properties;
        }
        CTTextCharacterProperties properties2 = paraprops.addNewDefRPr();
        return properties2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTShapeProperties getOrAddLinesProperties(CTChartLines gridlines) {
        if (gridlines.isSetSpPr()) {
            CTShapeProperties properties = gridlines.getSpPr();
            return properties;
        }
        CTShapeProperties properties2 = gridlines.addNewSpPr();
        return properties2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getNextAxId(CTPlotArea plotArea) {
        return plotArea.sizeOfValAxArray() + 0 + plotArea.sizeOfCatAxArray() + plotArea.sizeOfDateAxArray() + plotArea.sizeOfSerAxArray();
    }
}
