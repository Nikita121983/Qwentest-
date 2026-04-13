package org.apache.poi.xddf.usermodel.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

/* loaded from: classes10.dex */
public abstract class XDDFChartData {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) XDDFChartData.class);
    private XDDFCategoryAxis categoryAxis;
    protected XDDFChart parent;
    protected List<Series> series = new ArrayList();
    private List<XDDFValueAxis> valueAxes;

    public abstract Series addSeries(XDDFDataSource<?> xDDFDataSource, XDDFNumericalDataSource<? extends Number> xDDFNumericalDataSource);

    @Internal
    protected abstract void removeCTSeries(int i);

    public abstract void setVaryColors(Boolean bool);

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFChartData(XDDFChart chart) {
        this.parent = chart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void defineAxes(CTUnsignedInt[] axes, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        List<XDDFValueAxis> list = new ArrayList<>(axes.length);
        for (CTUnsignedInt axe : axes) {
            Long axisId = Long.valueOf(axe.getVal());
            XDDFChartAxis category = categories.get(axisId);
            if (category == null) {
                XDDFValueAxis axis = values.get(axisId);
                if (axis != null) {
                    list.add(axis);
                }
            } else if (category instanceof XDDFCategoryAxis) {
                this.categoryAxis = (XDDFCategoryAxis) category;
            }
        }
        this.valueAxes = Collections.unmodifiableList(list);
    }

    public XDDFCategoryAxis getCategoryAxis() {
        return this.categoryAxis;
    }

    public List<XDDFValueAxis> getValueAxes() {
        return this.valueAxes;
    }

    public final int getSeriesCount() {
        return this.series.size();
    }

    public final Series getSeries(int n) {
        return this.series.get(n);
    }

    public final void removeSeries(int n) {
        if (n < 0 || this.series.size() <= n) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "%s(%d): illegal index", "removeSeries", Integer.valueOf(n)));
        }
        this.series.remove(n);
        removeCTSeries(n);
    }

    /* loaded from: classes10.dex */
    public static abstract class Series {
        protected XDDFDataSource<?> categoryData;
        protected XDDFNumericalDataSource<? extends Number> valuesData;

        protected abstract CTAxDataSource getAxDS();

        protected abstract List<CTDPt> getDPtList();

        protected abstract CTNumDataSource getNumDS();

        protected abstract CTSerTx getSeriesText();

        public abstract XDDFShapeProperties getShapeProperties();

        protected abstract void setIndex(long j);

        protected abstract void setOrder(long j);

        public abstract void setShapeProperties(XDDFShapeProperties xDDFShapeProperties);

        public abstract void setShowLeaderLines(boolean z);

        /* JADX INFO: Access modifiers changed from: protected */
        public Series(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            replaceData(category, values);
        }

        public void replaceData(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            int numOfPoints;
            if (this.categoryData != null && values != null && (numOfPoints = category.getPointCount()) != values.getPointCount()) {
                XDDFChartData.LOGGER.warn("Category and values must have the same point count, but had {} categories and {} values.", Integer.valueOf(numOfPoints), Integer.valueOf(values.getPointCount()));
            }
            this.categoryData = category;
            this.valuesData = values;
        }

        public void setTitle(String title) {
            setTitle(title, null);
        }

        public void setTitle(String title, CellReference titleRef) {
            CTStrRef ref;
            CTStrData cache;
            if (titleRef == null) {
                getSeriesText().setV(title);
                return;
            }
            if (getSeriesText().isSetStrRef()) {
                ref = getSeriesText().getStrRef();
            } else {
                ref = getSeriesText().addNewStrRef();
            }
            ref.setF(titleRef.formatAsString());
            if (title != null) {
                if (ref.isSetStrCache()) {
                    cache = ref.getStrCache();
                } else {
                    cache = ref.addNewStrCache();
                }
                if (cache.sizeOfPtArray() < 1) {
                    cache.addNewPtCount().setVal(1L);
                    cache.addNewPt().setIdx(0L);
                }
                cache.getPtArray(0).setV(title);
            }
        }

        public XDDFDataSource<?> getCategoryData() {
            return this.categoryData;
        }

        public XDDFNumericalDataSource<? extends Number> getValuesData() {
            return this.valuesData;
        }

        public void plot() {
            if (this.categoryData != null) {
                if (this.categoryData.isNumeric()) {
                    CTNumData cache = retrieveNumCache(getAxDS(), this.categoryData);
                    this.categoryData.fillNumericalCache(cache);
                } else {
                    CTStrData cache2 = retrieveStrCache(getAxDS(), this.categoryData);
                    this.categoryData.fillStringCache(cache2);
                }
            }
            if (this.valuesData != null) {
                CTNumData cache3 = retrieveNumCache(getNumDS(), this.valuesData);
                this.valuesData.fillNumericalCache(cache3);
            }
        }

        public void setFillProperties(XDDFFillProperties fill) {
            XDDFShapeProperties properties = getShapeProperties();
            if (properties == null) {
                properties = new XDDFShapeProperties();
            }
            properties.setFillProperties(fill);
            setShapeProperties(properties);
        }

        public void setLineProperties(XDDFLineProperties line) {
            XDDFShapeProperties properties = getShapeProperties();
            if (properties == null) {
                properties = new XDDFShapeProperties();
            }
            properties.setLineProperties(line);
            setShapeProperties(properties);
        }

        public void clearDataPoint(long index) {
            List<CTDPt> points = getDPtList();
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).getIdx().getVal() == index) {
                    points.remove(i);
                    return;
                }
            }
        }

        public XDDFDataPoint getDataPoint(long index) {
            List<CTDPt> points = getDPtList();
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).getIdx().getVal() == index) {
                    return new XDDFDataPoint(points.get(i));
                }
                if (points.get(i).getIdx().getVal() > index) {
                    points.add(i, CTDPt.Factory.newInstance());
                    CTDPt point = points.get(i);
                    point.addNewIdx().setVal(index);
                    return new XDDFDataPoint(point);
                }
            }
            points.add(CTDPt.Factory.newInstance());
            CTDPt point2 = points.get(points.size() - 1);
            point2.addNewIdx().setVal(index);
            return new XDDFDataPoint(point2);
        }

        private CTNumData retrieveNumCache(CTAxDataSource axDataSource, XDDFDataSource<?> data) {
            CTNumData numCache;
            CTNumRef numRef;
            if (data.isReference()) {
                if (axDataSource.isSetNumRef()) {
                    numRef = axDataSource.getNumRef();
                } else {
                    numRef = axDataSource.addNewNumRef();
                }
                if (numRef.isSetNumCache()) {
                    numCache = numRef.getNumCache();
                } else {
                    numCache = numRef.addNewNumCache();
                }
                numRef.setF(data.getDataRangeReference());
                if (axDataSource.isSetNumLit()) {
                    axDataSource.unsetNumLit();
                }
            } else {
                if (axDataSource.isSetNumLit()) {
                    numCache = axDataSource.getNumLit();
                } else {
                    CTNumData numCache2 = axDataSource.addNewNumLit();
                    numCache = numCache2;
                }
                if (axDataSource.isSetNumRef()) {
                    axDataSource.unsetNumRef();
                }
            }
            return numCache;
        }

        private CTStrData retrieveStrCache(CTAxDataSource axDataSource, XDDFDataSource<?> data) {
            CTStrData strCache;
            CTStrRef strRef;
            if (data.isReference()) {
                if (axDataSource.isSetStrRef()) {
                    strRef = axDataSource.getStrRef();
                } else {
                    strRef = axDataSource.addNewStrRef();
                }
                if (strRef.isSetStrCache()) {
                    strCache = strRef.getStrCache();
                } else {
                    strCache = strRef.addNewStrCache();
                }
                strRef.setF(data.getDataRangeReference());
                if (axDataSource.isSetStrLit()) {
                    axDataSource.unsetStrLit();
                }
            } else {
                if (axDataSource.isSetStrLit()) {
                    strCache = axDataSource.getStrLit();
                } else {
                    CTStrData strCache2 = axDataSource.addNewStrLit();
                    strCache = strCache2;
                }
                if (axDataSource.isSetStrRef()) {
                    axDataSource.unsetStrRef();
                }
            }
            return strCache;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public CTNumData retrieveNumCache(CTNumDataSource numDataSource, XDDFDataSource<?> data) {
            CTNumData numCache;
            CTNumRef numRef;
            if (data.isReference()) {
                if (numDataSource.isSetNumRef()) {
                    numRef = numDataSource.getNumRef();
                } else {
                    numRef = numDataSource.addNewNumRef();
                }
                if (numRef.isSetNumCache()) {
                    numCache = numRef.getNumCache();
                } else {
                    numCache = numRef.addNewNumCache();
                }
                numRef.setF(data.getDataRangeReference());
                if (numDataSource.isSetNumLit()) {
                    numDataSource.unsetNumLit();
                }
            } else {
                if (numDataSource.isSetNumLit()) {
                    numCache = numDataSource.getNumLit();
                } else {
                    CTNumData numCache2 = numDataSource.addNewNumLit();
                    numCache = numCache2;
                }
                if (numDataSource.isSetNumRef()) {
                    numDataSource.unsetNumRef();
                }
            }
            return numCache;
        }
    }
}
