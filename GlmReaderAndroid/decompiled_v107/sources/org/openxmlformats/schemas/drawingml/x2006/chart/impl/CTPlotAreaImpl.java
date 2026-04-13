package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBubbleChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDTable;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStockChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

/* loaded from: classes11.dex */
public class CTPlotAreaImpl extends XmlComplexContentImpl implements CTPlotArea {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "layout"), new QName(XSSFRelation.NS_CHART, "areaChart"), new QName(XSSFRelation.NS_CHART, "area3DChart"), new QName(XSSFRelation.NS_CHART, "lineChart"), new QName(XSSFRelation.NS_CHART, "line3DChart"), new QName(XSSFRelation.NS_CHART, "stockChart"), new QName(XSSFRelation.NS_CHART, "radarChart"), new QName(XSSFRelation.NS_CHART, "scatterChart"), new QName(XSSFRelation.NS_CHART, "pieChart"), new QName(XSSFRelation.NS_CHART, "pie3DChart"), new QName(XSSFRelation.NS_CHART, "doughnutChart"), new QName(XSSFRelation.NS_CHART, "barChart"), new QName(XSSFRelation.NS_CHART, "bar3DChart"), new QName(XSSFRelation.NS_CHART, "ofPieChart"), new QName(XSSFRelation.NS_CHART, "surfaceChart"), new QName(XSSFRelation.NS_CHART, "surface3DChart"), new QName(XSSFRelation.NS_CHART, "bubbleChart"), new QName(XSSFRelation.NS_CHART, "valAx"), new QName(XSSFRelation.NS_CHART, "catAx"), new QName(XSSFRelation.NS_CHART, "dateAx"), new QName(XSSFRelation.NS_CHART, "serAx"), new QName(XSSFRelation.NS_CHART, "dTable"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTPlotAreaImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLayout getLayout() {
        CTLayout cTLayout;
        synchronized (monitor()) {
            check_orphaned();
            CTLayout target = (CTLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLayout = target == null ? null : target;
        }
        return cTLayout;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public boolean isSetLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setLayout(CTLayout layout) {
        generatedSetterHelperImpl(layout, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLayout addNewLayout() {
        CTLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void unsetLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTAreaChart> getAreaChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getAreaChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setAreaChartArray(((Integer) obj).intValue(), (CTAreaChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewAreaChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeAreaChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfAreaChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTAreaChart[] getAreaChartArray() {
        return (CTAreaChart[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTAreaChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTAreaChart getAreaChartArray(int i) {
        CTAreaChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAreaChart) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfAreaChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setAreaChartArray(CTAreaChart[] areaChartArray) {
        check_orphaned();
        arraySetterHelper(areaChartArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setAreaChartArray(int i, CTAreaChart areaChart) {
        generatedSetterHelperImpl(areaChart, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTAreaChart insertNewAreaChart(int i) {
        CTAreaChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAreaChart) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTAreaChart addNewAreaChart() {
        CTAreaChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAreaChart) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeAreaChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTArea3DChart> getArea3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getArea3DChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setArea3DChartArray(((Integer) obj).intValue(), (CTArea3DChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewArea3DChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeArea3DChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfArea3DChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTArea3DChart[] getArea3DChartArray() {
        return (CTArea3DChart[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTArea3DChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTArea3DChart getArea3DChartArray(int i) {
        CTArea3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTArea3DChart) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfArea3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setArea3DChartArray(CTArea3DChart[] area3DChartArray) {
        check_orphaned();
        arraySetterHelper(area3DChartArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setArea3DChartArray(int i, CTArea3DChart area3DChart) {
        generatedSetterHelperImpl(area3DChart, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTArea3DChart insertNewArea3DChart(int i) {
        CTArea3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTArea3DChart) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTArea3DChart addNewArea3DChart() {
        CTArea3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTArea3DChart) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeArea3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTLineChart> getLineChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getLineChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setLineChartArray(((Integer) obj).intValue(), (CTLineChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewLineChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeLineChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfLineChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLineChart[] getLineChartArray() {
        return (CTLineChart[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTLineChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLineChart getLineChartArray(int i) {
        CTLineChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineChart) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfLineChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setLineChartArray(CTLineChart[] lineChartArray) {
        check_orphaned();
        arraySetterHelper(lineChartArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setLineChartArray(int i, CTLineChart lineChart) {
        generatedSetterHelperImpl(lineChart, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLineChart insertNewLineChart(int i) {
        CTLineChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineChart) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLineChart addNewLineChart() {
        CTLineChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineChart) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeLineChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTLine3DChart> getLine3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getLine3DChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setLine3DChartArray(((Integer) obj).intValue(), (CTLine3DChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewLine3DChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeLine3DChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfLine3DChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLine3DChart[] getLine3DChartArray() {
        return (CTLine3DChart[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTLine3DChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLine3DChart getLine3DChartArray(int i) {
        CTLine3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine3DChart) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfLine3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setLine3DChartArray(CTLine3DChart[] line3DChartArray) {
        check_orphaned();
        arraySetterHelper(line3DChartArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setLine3DChartArray(int i, CTLine3DChart line3DChart) {
        generatedSetterHelperImpl(line3DChart, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLine3DChart insertNewLine3DChart(int i) {
        CTLine3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine3DChart) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTLine3DChart addNewLine3DChart() {
        CTLine3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine3DChart) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeLine3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTStockChart> getStockChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getStockChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setStockChartArray(((Integer) obj).intValue(), (CTStockChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewStockChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeStockChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfStockChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTStockChart[] getStockChartArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (XmlObject[]) new CTStockChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTStockChart getStockChartArray(int i) {
        CTStockChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfStockChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setStockChartArray(CTStockChart[] stockChartArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) stockChartArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setStockChartArray(int i, CTStockChart stockChart) {
        generatedSetterHelperImpl(stockChart, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTStockChart insertNewStockChart(int i) {
        CTStockChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTStockChart addNewStockChart() {
        CTStockChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeStockChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTRadarChart> getRadarChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getRadarChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setRadarChartArray(((Integer) obj).intValue(), (CTRadarChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewRadarChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeRadarChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfRadarChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTRadarChart[] getRadarChartArray() {
        return (CTRadarChart[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTRadarChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTRadarChart getRadarChartArray(int i) {
        CTRadarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRadarChart) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfRadarChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setRadarChartArray(CTRadarChart[] radarChartArray) {
        check_orphaned();
        arraySetterHelper(radarChartArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setRadarChartArray(int i, CTRadarChart radarChart) {
        generatedSetterHelperImpl(radarChart, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTRadarChart insertNewRadarChart(int i) {
        CTRadarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRadarChart) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTRadarChart addNewRadarChart() {
        CTRadarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRadarChart) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeRadarChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTScatterChart> getScatterChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getScatterChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setScatterChartArray(((Integer) obj).intValue(), (CTScatterChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewScatterChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeScatterChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfScatterChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTScatterChart[] getScatterChartArray() {
        return (CTScatterChart[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTScatterChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTScatterChart getScatterChartArray(int i) {
        CTScatterChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScatterChart) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfScatterChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setScatterChartArray(CTScatterChart[] scatterChartArray) {
        check_orphaned();
        arraySetterHelper(scatterChartArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setScatterChartArray(int i, CTScatterChart scatterChart) {
        generatedSetterHelperImpl(scatterChart, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTScatterChart insertNewScatterChart(int i) {
        CTScatterChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScatterChart) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTScatterChart addNewScatterChart() {
        CTScatterChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScatterChart) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeScatterChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTPieChart> getPieChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getPieChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setPieChartArray(((Integer) obj).intValue(), (CTPieChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewPieChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removePieChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfPieChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPieChart[] getPieChartArray() {
        return (CTPieChart[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTPieChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPieChart getPieChartArray(int i) {
        CTPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieChart) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfPieChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setPieChartArray(CTPieChart[] pieChartArray) {
        check_orphaned();
        arraySetterHelper(pieChartArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setPieChartArray(int i, CTPieChart pieChart) {
        generatedSetterHelperImpl(pieChart, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPieChart insertNewPieChart(int i) {
        CTPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieChart) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPieChart addNewPieChart() {
        CTPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieChart) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removePieChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTPie3DChart> getPie3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getPie3DChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setPie3DChartArray(((Integer) obj).intValue(), (CTPie3DChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewPie3DChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removePie3DChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfPie3DChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPie3DChart[] getPie3DChartArray() {
        return (CTPie3DChart[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTPie3DChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPie3DChart getPie3DChartArray(int i) {
        CTPie3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPie3DChart) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfPie3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setPie3DChartArray(CTPie3DChart[] pie3DChartArray) {
        check_orphaned();
        arraySetterHelper(pie3DChartArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setPie3DChartArray(int i, CTPie3DChart pie3DChart) {
        generatedSetterHelperImpl(pie3DChart, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPie3DChart insertNewPie3DChart(int i) {
        CTPie3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPie3DChart) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTPie3DChart addNewPie3DChart() {
        CTPie3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPie3DChart) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removePie3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTDoughnutChart> getDoughnutChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getDoughnutChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setDoughnutChartArray(((Integer) obj).intValue(), (CTDoughnutChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewDoughnutChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeDoughnutChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfDoughnutChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDoughnutChart[] getDoughnutChartArray() {
        return (CTDoughnutChart[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTDoughnutChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDoughnutChart getDoughnutChartArray(int i) {
        CTDoughnutChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDoughnutChart) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfDoughnutChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setDoughnutChartArray(CTDoughnutChart[] doughnutChartArray) {
        check_orphaned();
        arraySetterHelper(doughnutChartArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setDoughnutChartArray(int i, CTDoughnutChart doughnutChart) {
        generatedSetterHelperImpl(doughnutChart, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDoughnutChart insertNewDoughnutChart(int i) {
        CTDoughnutChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDoughnutChart) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDoughnutChart addNewDoughnutChart() {
        CTDoughnutChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDoughnutChart) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeDoughnutChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTBarChart> getBarChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getBarChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setBarChartArray(((Integer) obj).intValue(), (CTBarChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewBarChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeBarChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfBarChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBarChart[] getBarChartArray() {
        return (CTBarChart[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTBarChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBarChart getBarChartArray(int i) {
        CTBarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBarChart) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfBarChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBarChartArray(CTBarChart[] barChartArray) {
        check_orphaned();
        arraySetterHelper(barChartArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBarChartArray(int i, CTBarChart barChart) {
        generatedSetterHelperImpl(barChart, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBarChart insertNewBarChart(int i) {
        CTBarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBarChart) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBarChart addNewBarChart() {
        CTBarChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBarChart) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeBarChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTBar3DChart> getBar3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getBar3DChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setBar3DChartArray(((Integer) obj).intValue(), (CTBar3DChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewBar3DChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeBar3DChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfBar3DChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBar3DChart[] getBar3DChartArray() {
        return (CTBar3DChart[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTBar3DChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBar3DChart getBar3DChartArray(int i) {
        CTBar3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBar3DChart) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfBar3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBar3DChartArray(CTBar3DChart[] bar3DChartArray) {
        check_orphaned();
        arraySetterHelper(bar3DChartArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBar3DChartArray(int i, CTBar3DChart bar3DChart) {
        generatedSetterHelperImpl(bar3DChart, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBar3DChart insertNewBar3DChart(int i) {
        CTBar3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBar3DChart) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBar3DChart addNewBar3DChart() {
        CTBar3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBar3DChart) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeBar3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTOfPieChart> getOfPieChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getOfPieChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setOfPieChartArray(((Integer) obj).intValue(), (CTOfPieChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewOfPieChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeOfPieChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfOfPieChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTOfPieChart[] getOfPieChartArray() {
        return (CTOfPieChart[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTOfPieChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTOfPieChart getOfPieChartArray(int i) {
        CTOfPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfPieChart) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfOfPieChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setOfPieChartArray(CTOfPieChart[] ofPieChartArray) {
        check_orphaned();
        arraySetterHelper(ofPieChartArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setOfPieChartArray(int i, CTOfPieChart ofPieChart) {
        generatedSetterHelperImpl(ofPieChart, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTOfPieChart insertNewOfPieChart(int i) {
        CTOfPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfPieChart) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTOfPieChart addNewOfPieChart() {
        CTOfPieChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfPieChart) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeOfPieChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTSurfaceChart> getSurfaceChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getSurfaceChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setSurfaceChartArray(((Integer) obj).intValue(), (CTSurfaceChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewSurfaceChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeSurfaceChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfSurfaceChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurfaceChart[] getSurfaceChartArray() {
        return (CTSurfaceChart[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTSurfaceChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurfaceChart getSurfaceChartArray(int i) {
        CTSurfaceChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurfaceChart) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfSurfaceChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSurfaceChartArray(CTSurfaceChart[] surfaceChartArray) {
        check_orphaned();
        arraySetterHelper(surfaceChartArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSurfaceChartArray(int i, CTSurfaceChart surfaceChart) {
        generatedSetterHelperImpl(surfaceChart, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurfaceChart insertNewSurfaceChart(int i) {
        CTSurfaceChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurfaceChart) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurfaceChart addNewSurfaceChart() {
        CTSurfaceChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurfaceChart) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeSurfaceChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTSurface3DChart> getSurface3DChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getSurface3DChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setSurface3DChartArray(((Integer) obj).intValue(), (CTSurface3DChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewSurface3DChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeSurface3DChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfSurface3DChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurface3DChart[] getSurface3DChartArray() {
        return (CTSurface3DChart[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTSurface3DChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurface3DChart getSurface3DChartArray(int i) {
        CTSurface3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface3DChart) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfSurface3DChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSurface3DChartArray(CTSurface3DChart[] surface3DChartArray) {
        check_orphaned();
        arraySetterHelper(surface3DChartArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSurface3DChartArray(int i, CTSurface3DChart surface3DChart) {
        generatedSetterHelperImpl(surface3DChart, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurface3DChart insertNewSurface3DChart(int i) {
        CTSurface3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface3DChart) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSurface3DChart addNewSurface3DChart() {
        CTSurface3DChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface3DChart) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeSurface3DChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTBubbleChart> getBubbleChartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getBubbleChartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setBubbleChartArray(((Integer) obj).intValue(), (CTBubbleChart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewBubbleChart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeBubbleChart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfBubbleChartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBubbleChart[] getBubbleChartArray() {
        return (CTBubbleChart[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTBubbleChart[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBubbleChart getBubbleChartArray(int i) {
        CTBubbleChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBubbleChart) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfBubbleChartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBubbleChartArray(CTBubbleChart[] bubbleChartArray) {
        check_orphaned();
        arraySetterHelper(bubbleChartArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setBubbleChartArray(int i, CTBubbleChart bubbleChart) {
        generatedSetterHelperImpl(bubbleChart, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBubbleChart insertNewBubbleChart(int i) {
        CTBubbleChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBubbleChart) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTBubbleChart addNewBubbleChart() {
        CTBubbleChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBubbleChart) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeBubbleChart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTValAx> getValAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getValAxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setValAxArray(((Integer) obj).intValue(), (CTValAx) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewValAx(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeValAx(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfValAxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTValAx[] getValAxArray() {
        return (CTValAx[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTValAx[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTValAx getValAxArray(int i) {
        CTValAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTValAx) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfValAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setValAxArray(CTValAx[] valAxArray) {
        check_orphaned();
        arraySetterHelper(valAxArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setValAxArray(int i, CTValAx valAx) {
        generatedSetterHelperImpl(valAx, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTValAx insertNewValAx(int i) {
        CTValAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTValAx) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTValAx addNewValAx() {
        CTValAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTValAx) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeValAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTCatAx> getCatAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getCatAxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setCatAxArray(((Integer) obj).intValue(), (CTCatAx) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewCatAx(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeCatAx(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfCatAxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTCatAx[] getCatAxArray() {
        return (CTCatAx[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTCatAx[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTCatAx getCatAxArray(int i) {
        CTCatAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCatAx) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfCatAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setCatAxArray(CTCatAx[] catAxArray) {
        check_orphaned();
        arraySetterHelper(catAxArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setCatAxArray(int i, CTCatAx catAx) {
        generatedSetterHelperImpl(catAx, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTCatAx insertNewCatAx(int i) {
        CTCatAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCatAx) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTCatAx addNewCatAx() {
        CTCatAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCatAx) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeCatAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTDateAx> getDateAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getDateAxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setDateAxArray(((Integer) obj).intValue(), (CTDateAx) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewDateAx(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeDateAx(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfDateAxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDateAx[] getDateAxArray() {
        return (CTDateAx[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTDateAx[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDateAx getDateAxArray(int i) {
        CTDateAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDateAx) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfDateAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setDateAxArray(CTDateAx[] dateAxArray) {
        check_orphaned();
        arraySetterHelper(dateAxArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setDateAxArray(int i, CTDateAx dateAx) {
        generatedSetterHelperImpl(dateAx, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDateAx insertNewDateAx(int i) {
        CTDateAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDateAx) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDateAx addNewDateAx() {
        CTDateAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDateAx) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeDateAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public List<CTSerAx> getSerAxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.getSerAxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPlotAreaImpl.this.setSerAxArray(((Integer) obj).intValue(), (CTSerAx) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPlotAreaImpl.this.insertNewSerAx(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPlotAreaImpl.this.removeSerAx(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPlotAreaImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPlotAreaImpl.this.sizeOfSerAxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSerAx[] getSerAxArray() {
        return (CTSerAx[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTSerAx[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSerAx getSerAxArray(int i) {
        CTSerAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSerAx) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public int sizeOfSerAxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSerAxArray(CTSerAx[] serAxArray) {
        check_orphaned();
        arraySetterHelper(serAxArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSerAxArray(int i, CTSerAx serAx) {
        generatedSetterHelperImpl(serAx, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSerAx insertNewSerAx(int i) {
        CTSerAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSerAx) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTSerAx addNewSerAx() {
        CTSerAx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSerAx) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void removeSerAx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDTable getDTable() {
        CTDTable cTDTable;
        synchronized (monitor()) {
            check_orphaned();
            CTDTable target = get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTDTable = target == null ? null : target;
        }
        return cTDTable;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public boolean isSetDTable() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setDTable(CTDTable dTable) {
        generatedSetterHelperImpl(dTable, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTDTable addNewDTable() {
        CTDTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void unsetDTable() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }
}
