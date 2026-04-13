package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

/* loaded from: classes10.dex */
public class XDDFRadarChartData extends XDDFChartData {
    private CTRadarChart chart;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFRadarChartData(XDDFChart parent, CTRadarChart chart, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        super(parent);
        this.chart = chart;
        for (CTRadarSer series : chart.getSerList()) {
            this.series.add(new Series(series, series.getCat(), series.getVal()));
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

    public RadarStyle getStyle() {
        return RadarStyle.valueOf(this.chart.getRadarStyle().getVal());
    }

    public void setStyle(RadarStyle style) {
        CTRadarStyle radarStyle = this.chart.getRadarStyle();
        if (radarStyle == null) {
            radarStyle = this.chart.addNewRadarStyle();
        }
        radarStyle.setVal(style.underlying);
    }

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTRadarSer ctSer = this.chart.addNewSer();
        ctSer.addNewCat();
        ctSer.addNewVal();
        ctSer.addNewIdx().setVal(index);
        ctSer.addNewOrder().setVal(index);
        Series added = new Series(ctSer, category, values);
        this.series.add(added);
        return added;
    }

    /* loaded from: classes10.dex */
    public class Series extends XDDFChartData.Series {
        private CTRadarSer series;

        protected Series(CTRadarSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            super(category, values);
            this.series = series;
        }

        protected Series(CTRadarSer series, CTAxDataSource category, CTNumDataSource values) {
            super(XDDFDataSourcesFactory.fromDataSource(category), XDDFDataSourcesFactory.fromDataSource(values));
            this.series = series;
        }

        public CTRadarSer getCTRadarSer() {
            return this.series;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
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
            return this.series.getCat();
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData.Series
        protected CTNumDataSource getNumDS() {
            return this.series.getVal();
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
