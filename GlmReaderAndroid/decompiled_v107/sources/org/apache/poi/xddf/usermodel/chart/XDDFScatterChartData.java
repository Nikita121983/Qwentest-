package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

/* loaded from: classes10.dex */
public class XDDFScatterChartData extends XDDFChartData {
    private CTScatterChart chart;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFScatterChartData(XDDFChart parent, CTScatterChart chart, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        super(parent);
        this.chart = chart;
        for (CTScatterSer series : chart.getSerList()) {
            this.series.add(new Series(series, series.getXVal(), series.getYVal()));
        }
        defineAxes(categories, values);
    }

    private void defineAxes(Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        if (this.chart.sizeOfAxIdArray() == 0) {
            for (Long id : categories.keySet()) {
                this.chart.addNewAxId().setVal(id.longValue());
            }
            for (Long id2 : values.keySet()) {
                this.chart.addNewAxId().setVal(id2.longValue());
            }
        }
        defineAxes(this.chart.getAxIdArray(), categories, values);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData
    @Internal
    protected void removeCTSeries(int n) {
        this.chart.removeSer(n);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData
    public void setVaryColors(Boolean varyColors) {
        if (varyColors == null) {
            if (this.chart.isSetVaryColors()) {
                this.chart.unsetVaryColors();
            }
        } else if (this.chart.isSetVaryColors()) {
            this.chart.getVaryColors().setVal(varyColors.booleanValue());
        } else {
            this.chart.addNewVaryColors().setVal(varyColors.booleanValue());
        }
    }

    public ScatterStyle getStyle() {
        CTScatterStyle scatterStyle = this.chart.getScatterStyle();
        if (scatterStyle == null) {
            scatterStyle = this.chart.addNewScatterStyle();
            scatterStyle.setVal(ScatterStyle.LINE_MARKER.underlying);
        }
        return ScatterStyle.valueOf(scatterStyle.getVal());
    }

    public void setStyle(ScatterStyle style) {
        CTScatterStyle scatterStyle = this.chart.getScatterStyle();
        if (scatterStyle == null) {
            scatterStyle = this.chart.addNewScatterStyle();
        }
        scatterStyle.setVal(style.underlying);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTScatterSer ctSer = this.chart.addNewSer();
        ctSer.addNewXVal();
        ctSer.addNewYVal();
        ctSer.addNewIdx().setVal(index);
        ctSer.addNewOrder().setVal(index);
        Series added = new Series(ctSer, category, values);
        added.setMarkerStyle(MarkerStyle.NONE);
        this.series.add(added);
        return added;
    }

    /* loaded from: classes10.dex */
    public class Series extends XDDFChartData.Series {
        private CTScatterSer series;

        protected Series(CTScatterSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<?> values) {
            super(category, values);
            this.series = series;
        }

        protected Series(CTScatterSer series, CTAxDataSource category, CTNumDataSource values) {
            super(XDDFDataSourcesFactory.fromDataSource(category), XDDFDataSourcesFactory.fromDataSource(values));
            this.series = series;
        }

        public CTScatterSer getCTScatterSer() {
            return this.series;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
        }

        public Boolean isSmooth() {
            if (this.series.isSetSmooth()) {
                return Boolean.valueOf(this.series.getSmooth().getVal());
            }
            return false;
        }

        public void setSmooth(Boolean smooth) {
            if (smooth == null) {
                if (this.series.isSetSmooth()) {
                    this.series.unsetSmooth();
                }
            } else if (this.series.isSetSmooth()) {
                this.series.getSmooth().setVal(smooth.booleanValue());
            } else {
                this.series.addNewSmooth().setVal(smooth.booleanValue());
            }
        }

        public void setMarkerSize(short size) {
            if (size < 2 || 72 < size) {
                throw new IllegalArgumentException("Minimum inclusive: 2; Maximum inclusive: 72");
            }
            CTMarker marker = getMarker();
            if (marker.isSetSize()) {
                marker.getSize().setVal(size);
            } else {
                marker.addNewSize().setVal(size);
            }
        }

        public void setMarkerStyle(MarkerStyle style) {
            CTMarker marker = getMarker();
            if (marker.isSetSymbol()) {
                marker.getSymbol().setVal(style.underlying);
            } else {
                marker.addNewSymbol().setVal(style.underlying);
            }
        }

        private CTMarker getMarker() {
            if (this.series.isSetMarker()) {
                return this.series.getMarker();
            }
            return this.series.addNewMarker();
        }

        public int getErrorBarsCount() {
            return this.series.sizeOfErrBarsArray();
        }

        public XDDFErrorBars getErrorBars(int index) {
            return new XDDFErrorBars(this.series.getErrBarsArray(index));
        }

        public XDDFErrorBars addNewErrorBars() {
            return new XDDFErrorBars(this.series.addNewErrBars());
        }

        public XDDFErrorBars insertNewErrorBars(int index) {
            return new XDDFErrorBars(this.series.insertNewErrBars(index));
        }

        public void removeErrorBars(int index) {
            this.series.removeErrBars(index);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        public void setShowLeaderLines(boolean showLeaderLines) {
            if (!this.series.isSetDLbls()) {
                this.series.addNewDLbls();
            }
            if (this.series.getDLbls().isSetShowLeaderLines()) {
                this.series.getDLbls().getShowLeaderLines().setVal(showLeaderLines);
            } else {
                this.series.getDLbls().addNewShowLeaderLines().setVal(showLeaderLines);
            }
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        public XDDFShapeProperties getShapeProperties() {
            if (this.series.isSetSpPr()) {
                return new XDDFShapeProperties(this.series.getSpPr());
            }
            return null;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        public void setShapeProperties(XDDFShapeProperties properties) {
            if (properties == null) {
                if (this.series.isSetSpPr()) {
                    this.series.unsetSpPr();
                }
            } else if (this.series.isSetSpPr()) {
                this.series.setSpPr(properties.getXmlObject());
            } else {
                this.series.addNewSpPr().set(properties.getXmlObject());
            }
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected CTAxDataSource getAxDS() {
            return this.series.getXVal();
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected CTNumDataSource getNumDS() {
            return this.series.getYVal();
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected void setIndex(long val) {
            this.series.getIdx().setVal(val);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected void setOrder(long val) {
            this.series.getOrder().setVal(val);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected List<CTDPt> getDPtList() {
            return this.series.getDPtList();
        }
    }
}
