package org.apache.poi.xddf.usermodel.chart;

import java.util.List;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

/* loaded from: classes10.dex */
public class XDDFPie3DChartData extends XDDFChartData {
    private CTPie3DChart chart;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFPie3DChartData(XDDFChart parent, CTPie3DChart chart) {
        super(parent);
        this.chart = chart;
        for (CTPieSer series : chart.getSerList()) {
            this.series.add(new Series(series, series.getCat(), series.getVal()));
        }
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

    @Override // org.apache.poi.xddf.usermodel.chart.XDDFChartData
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTPieSer ctSer = this.chart.addNewSer();
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
        private CTPieSer series;

        protected Series(CTPieSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            super(category, values);
            this.series = series;
        }

        protected Series(CTPieSer series, CTAxDataSource category, CTNumDataSource values) {
            super(XDDFDataSourcesFactory.fromDataSource(category), XDDFDataSourcesFactory.fromDataSource(values));
            this.series = series;
        }

        public CTPieSer getCTPieSer() {
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

        public Long getExplosion() {
            if (this.series.isSetExplosion()) {
                return Long.valueOf(this.series.getExplosion().getVal());
            }
            return null;
        }

        public void setExplosion(Long explosion) {
            if (explosion == null) {
                if (this.series.isSetExplosion()) {
                    this.series.unsetExplosion();
                }
            } else if (this.series.isSetExplosion()) {
                this.series.getExplosion().setVal(explosion.longValue());
            } else {
                this.series.addNewExplosion().setVal(explosion.longValue());
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
