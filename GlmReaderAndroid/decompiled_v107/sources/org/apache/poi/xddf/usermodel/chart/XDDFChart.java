package org.apache.poi.xddf.usermodel.chart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDoughnutChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;
import org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes10.dex */
public abstract class XDDFChart extends POIXMLDocumentPart implements TextContainer {
    public static final int DEFAULT_HEIGHT = 500000;
    public static final int DEFAULT_WIDTH = 500000;
    public static final int DEFAULT_X = 10;
    public static final int DEFAULT_Y = 10;
    protected List<XDDFChartAxis> axes;
    private int chartIndex;
    protected final CTChartSpace chartSpace;
    private long seriesCount;
    private XSSFWorkbook workbook;

    protected abstract POIXMLFactory getChartFactory();

    protected abstract POIXMLRelation getChartRelation();

    protected abstract POIXMLRelation getChartWorkbookRelation();

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFChart() {
        this.chartIndex = 0;
        this.axes = new ArrayList();
        this.seriesCount = 0L;
        this.chartSpace = CTChartSpace.Factory.newInstance();
        this.chartSpace.addNewChart().addNewPlotArea();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFChart(PackagePart part) throws IOException, XmlException {
        super(part);
        this.chartIndex = 0;
        this.axes = new ArrayList();
        this.seriesCount = 0L;
        InputStream stream = part.getInputStream();
        try {
            this.chartSpace = ChartSpaceDocument.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getChartSpace();
            if (stream != null) {
                stream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Internal
    public CTChartSpace getCTChartSpace() {
        return this.chartSpace;
    }

    @Internal
    public CTChart getCTChart() {
        return this.chartSpace.getChart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTPlotArea getCTPlotArea() {
        return getCTChart().getPlotArea();
    }

    public void clear() {
        this.axes.clear();
        this.seriesCount = 0L;
        if (this.workbook != null) {
            this.workbook.removeSheetAt(0);
            this.workbook.createSheet();
        }
        getCTChart().set(CTChart.Factory.newInstance());
        getCTChart().addNewPlotArea();
    }

    public boolean isPlotOnlyVisibleCells() {
        if (getCTChart().isSetPlotVisOnly()) {
            return getCTChart().getPlotVisOnly().getVal();
        }
        return false;
    }

    public void setPlotOnlyVisibleCells(boolean only) {
        if (!getCTChart().isSetPlotVisOnly()) {
            getCTChart().setPlotVisOnly(CTBoolean.Factory.newInstance());
        }
        getCTChart().getPlotVisOnly().setVal(only);
    }

    public void setFloor(int thickness) {
        if (!getCTChart().isSetFloor()) {
            getCTChart().setFloor(CTSurface.Factory.newInstance());
        }
        getCTChart().getFloor().getThickness().setVal(Integer.valueOf(thickness));
    }

    public void setBackWall(int thickness) {
        if (!getCTChart().isSetBackWall()) {
            getCTChart().setBackWall(CTSurface.Factory.newInstance());
        }
        getCTChart().getBackWall().getThickness().setVal(Integer.valueOf(thickness));
    }

    public void setSideWall(int thickness) {
        if (!getCTChart().isSetSideWall()) {
            getCTChart().setSideWall(CTSurface.Factory.newInstance());
        }
        getCTChart().getSideWall().getThickness().setVal(Integer.valueOf(thickness));
    }

    public void setAutoTitleDeleted(boolean deleted) {
        if (!getCTChart().isSetAutoTitleDeleted()) {
            getCTChart().setAutoTitleDeleted(CTBoolean.Factory.newInstance());
        }
        getCTChart().getAutoTitleDeleted().setVal(deleted);
        if (deleted && getCTChart().isSetTitle()) {
            getCTChart().unsetTitle();
        }
    }

    public void displayBlanksAs(DisplayBlanks as) {
        if (as == null) {
            if (getCTChart().isSetDispBlanksAs()) {
                getCTChart().unsetDispBlanksAs();
            }
        } else if (getCTChart().isSetDispBlanksAs()) {
            getCTChart().getDispBlanksAs().setVal(as.underlying);
        } else {
            getCTChart().addNewDispBlanksAs().setVal(as.underlying);
        }
    }

    public Boolean getTitleOverlay() {
        if (getCTChart().isSetTitle()) {
            CTTitle title = getCTChart().getTitle();
            if (title.isSetOverlay()) {
                return Boolean.valueOf(title.getOverlay().getVal());
            }
            return null;
        }
        return null;
    }

    public void setTitleOverlay(boolean overlay) {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        new XDDFTitle(this, getCTChart().getTitle()).setOverlay(Boolean.valueOf(overlay));
    }

    public void setTitleText(String text) {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        new XDDFTitle(this, getCTChart().getTitle()).setText(text);
    }

    public XDDFTitle getTitle() {
        if (getCTChart().isSetTitle()) {
            return new XDDFTitle(this, getCTChart().getTitle());
        }
        return null;
    }

    public void removeTitle() {
        setAutoTitleDeleted(true);
    }

    public XDDFView3D getOrAddView3D() {
        CTView3D view3D;
        if (getCTChart().isSetView3D()) {
            view3D = getCTChart().getView3D();
        } else {
            view3D = getCTChart().addNewView3D();
        }
        return new XDDFView3D(view3D);
    }

    public XDDFTextBody getFormattedTitle() {
        if (!getCTChart().isSetTitle()) {
            return null;
        }
        return new XDDFTitle(this, getCTChart().getTitle()).getBody();
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override // org.apache.poi.xddf.usermodel.text.TextContainer
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }

    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties;
        CTPlotArea plotArea = getCTPlotArea();
        if (plotArea.isSetSpPr()) {
            properties = plotArea.getSpPr();
        } else {
            properties = plotArea.addNewSpPr();
        }
        return new XDDFShapeProperties(properties);
    }

    public void deleteShapeProperties() {
        if (getCTPlotArea().isSetSpPr()) {
            getCTPlotArea().unsetSpPr();
        }
    }

    public XDDFChartLegend getOrAddLegend() {
        return new XDDFChartLegend(getCTChart());
    }

    public void deleteLegend() {
        if (getCTChart().isSetLegend()) {
            getCTChart().unsetLegend();
        }
    }

    public XDDFManualLayout getOrAddManualLayout() {
        return new XDDFManualLayout(getCTPlotArea());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long incrementSeriesCount() {
        long j = this.seriesCount;
        this.seriesCount = 1 + j;
        return j;
    }

    public void plot(XDDFChartData data) {
        XSSFSheet sheet = getSheet();
        for (int idx = 0; idx < data.getSeriesCount(); idx++) {
            XDDFChartData.Series series = data.getSeries(idx);
            series.plot();
            XDDFDataSource<?> categoryDS = series.getCategoryData();
            XDDFNumericalDataSource<? extends Number> valuesDS = series.getValuesData();
            if (categoryDS != null && !categoryDS.isCellRange() && !categoryDS.isLiteral() && valuesDS != null && !valuesDS.isCellRange() && !valuesDS.isLiteral()) {
                fillSheet(sheet, categoryDS, valuesDS);
            }
        }
    }

    public List<XDDFChartData> getChartSeries() {
        List<XDDFChartData> series = new LinkedList<>();
        CTPlotArea plotArea = getCTPlotArea();
        Map<Long, XDDFChartAxis> categories = getCategoryAxes();
        Map<Long, XDDFValueAxis> values = getValueAxes();
        for (int i = 0; i < plotArea.sizeOfAreaChartArray(); i++) {
            CTAreaChart areaChart = plotArea.getAreaChartArray(i);
            series.add(new XDDFAreaChartData(this, areaChart, categories, values));
        }
        for (int i2 = 0; i2 < plotArea.sizeOfArea3DChartArray(); i2++) {
            CTArea3DChart areaChart2 = plotArea.getArea3DChartArray(i2);
            series.add(new XDDFArea3DChartData(this, areaChart2, categories, values));
        }
        for (int i3 = 0; i3 < plotArea.sizeOfBarChartArray(); i3++) {
            CTBarChart barChart = plotArea.getBarChartArray(i3);
            series.add(new XDDFBarChartData(this, barChart, categories, values));
        }
        for (int i4 = 0; i4 < plotArea.sizeOfBar3DChartArray(); i4++) {
            CTBar3DChart barChart2 = plotArea.getBar3DChartArray(i4);
            series.add(new XDDFBar3DChartData(this, barChart2, categories, values));
        }
        for (int i5 = 0; i5 < plotArea.sizeOfDoughnutChartArray(); i5++) {
            CTDoughnutChart doughnutChart = plotArea.getDoughnutChartArray(i5);
            series.add(new XDDFDoughnutChartData(this, doughnutChart));
        }
        for (int i6 = 0; i6 < plotArea.sizeOfLineChartArray(); i6++) {
            CTLineChart lineChart = plotArea.getLineChartArray(i6);
            series.add(new XDDFLineChartData(this, lineChart, categories, values));
        }
        for (int i7 = 0; i7 < plotArea.sizeOfLine3DChartArray(); i7++) {
            CTLine3DChart lineChart2 = plotArea.getLine3DChartArray(i7);
            series.add(new XDDFLine3DChartData(this, lineChart2, categories, values));
        }
        for (int i8 = 0; i8 < plotArea.sizeOfPieChartArray(); i8++) {
            CTPieChart pieChart = plotArea.getPieChartArray(i8);
            series.add(new XDDFPieChartData(this, pieChart));
        }
        for (int i9 = 0; i9 < plotArea.sizeOfPie3DChartArray(); i9++) {
            CTPie3DChart pieChart2 = plotArea.getPie3DChartArray(i9);
            series.add(new XDDFPie3DChartData(this, pieChart2));
        }
        for (int i10 = 0; i10 < plotArea.sizeOfRadarChartArray(); i10++) {
            CTRadarChart radarChart = plotArea.getRadarChartArray(i10);
            series.add(new XDDFRadarChartData(this, radarChart, categories, values));
        }
        for (int i11 = 0; i11 < plotArea.sizeOfScatterChartArray(); i11++) {
            CTScatterChart scatterChart = plotArea.getScatterChartArray(i11);
            series.add(new XDDFScatterChartData(this, scatterChart, categories, values));
        }
        for (int i12 = 0; i12 < plotArea.sizeOfSurfaceChartArray(); i12++) {
            CTSurfaceChart surfaceChart = plotArea.getSurfaceChartArray(i12);
            series.add(new XDDFSurfaceChartData(this, surfaceChart, categories, values));
        }
        for (int i13 = 0; i13 < plotArea.sizeOfSurface3DChartArray(); i13++) {
            CTSurface3DChart surfaceChart2 = plotArea.getSurface3DChartArray(i13);
            series.add(new XDDFSurface3DChartData(this, surfaceChart2, categories, values));
        }
        int i14 = series.size();
        this.seriesCount = i14;
        return series;
    }

    public void clearChartSeries() {
        CTPlotArea plotArea = getCTPlotArea();
        for (int i = plotArea.sizeOfAreaChartArray(); i > 0; i--) {
            plotArea.removeAreaChart(i - 1);
        }
        for (int i2 = plotArea.sizeOfArea3DChartArray(); i2 > 0; i2--) {
            plotArea.removeArea3DChart(i2 - 1);
        }
        for (int i3 = plotArea.sizeOfBarChartArray(); i3 > 0; i3--) {
            plotArea.removeBarChart(i3 - 1);
        }
        for (int i4 = plotArea.sizeOfBar3DChartArray(); i4 > 0; i4--) {
            plotArea.removeBar3DChart(i4 - 1);
        }
        for (int i5 = plotArea.sizeOfBubbleChartArray(); i5 > 0; i5--) {
            plotArea.removeBubbleChart(i5 - 1);
        }
        for (int i6 = plotArea.sizeOfDoughnutChartArray(); i6 > 0; i6--) {
            plotArea.removeDoughnutChart(i6 - 1);
        }
        for (int i7 = plotArea.sizeOfLineChartArray(); i7 > 0; i7--) {
            plotArea.removeLineChart(i7 - 1);
        }
        for (int i8 = plotArea.sizeOfLine3DChartArray(); i8 > 0; i8--) {
            plotArea.removeLine3DChart(i8 - 1);
        }
        for (int i9 = plotArea.sizeOfOfPieChartArray(); i9 > 0; i9--) {
            plotArea.removeOfPieChart(i9 - 1);
        }
        for (int i10 = plotArea.sizeOfPieChartArray(); i10 > 0; i10--) {
            plotArea.removePieChart(i10 - 1);
        }
        for (int i11 = plotArea.sizeOfPie3DChartArray(); i11 > 0; i11--) {
            plotArea.removePie3DChart(i11 - 1);
        }
        for (int i12 = plotArea.sizeOfRadarChartArray(); i12 > 0; i12--) {
            plotArea.removeRadarChart(i12 - 1);
        }
        for (int i13 = plotArea.sizeOfScatterChartArray(); i13 > 0; i13--) {
            plotArea.removeScatterChart(i13 - 1);
        }
        for (int i14 = plotArea.sizeOfStockChartArray(); i14 > 0; i14--) {
            plotArea.removeStockChart(i14 - 1);
        }
        for (int i15 = plotArea.sizeOfSurfaceChartArray(); i15 > 0; i15--) {
            plotArea.removeSurfaceChart(i15 - 1);
        }
        for (int i16 = plotArea.sizeOfSurface3DChartArray(); i16 > 0; i16--) {
            plotArea.removeSurface3DChart(i16 - 1);
        }
    }

    private Map<Long, XDDFChartAxis> getCategoryAxes() {
        CTPlotArea plotArea = getCTPlotArea();
        int sizeOfArray = plotArea.sizeOfCatAxArray();
        Map<Long, XDDFChartAxis> axesMap = new HashMap<>(sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            CTCatAx category = plotArea.getCatAxArray(i);
            axesMap.put(Long.valueOf(category.getAxId().getVal()), new XDDFCategoryAxis(category));
        }
        return axesMap;
    }

    private Map<Long, XDDFValueAxis> getValueAxes() {
        CTPlotArea plotArea = getCTPlotArea();
        int sizeOfArray = plotArea.sizeOfValAxArray();
        Map<Long, XDDFValueAxis> axesMap = new HashMap<>(sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            CTValAx values = plotArea.getValAxArray(i);
            axesMap.put(Long.valueOf(values.getAxId().getVal()), new XDDFValueAxis(values));
        }
        return axesMap;
    }

    public XDDFValueAxis createValueAxis(AxisPosition pos) {
        XDDFValueAxis valueAxis = new XDDFValueAxis(getCTPlotArea(), pos);
        addAxis(valueAxis);
        return valueAxis;
    }

    public XDDFSeriesAxis createSeriesAxis(AxisPosition pos) {
        XDDFSeriesAxis seriesAxis = new XDDFSeriesAxis(getCTPlotArea(), pos);
        addAxis(seriesAxis);
        return seriesAxis;
    }

    public XDDFCategoryAxis createCategoryAxis(AxisPosition pos) {
        XDDFCategoryAxis categoryAxis = new XDDFCategoryAxis(getCTPlotArea(), pos);
        addAxis(categoryAxis);
        return categoryAxis;
    }

    public XDDFDateAxis createDateAxis(AxisPosition pos) {
        XDDFDateAxis dateAxis = new XDDFDateAxis(getCTPlotArea(), pos);
        addAxis(dateAxis);
        return dateAxis;
    }

    private void addAxis(XDDFChartAxis newAxis) {
        if (this.axes.size() == 1) {
            XDDFChartAxis axis = this.axes.get(0);
            axis.crossAxis(newAxis);
            newAxis.crossAxis(axis);
            axis.setCrosses(AxisCrosses.AUTO_ZERO);
            newAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        }
        this.axes.add(newAxis);
    }

    public XDDFChartData createData(ChartTypes type, XDDFChartAxis category, XDDFValueAxis values) {
        Map<Long, XDDFChartAxis> categories = null;
        Map<Long, XDDFValueAxis> mapValues = null;
        if (ChartTypes.PIE != type && ChartTypes.PIE3D != type && ChartTypes.DOUGHNUT != type) {
            categories = Collections.singletonMap(Long.valueOf(category.getId()), category);
            mapValues = Collections.singletonMap(Long.valueOf(values.getId()), values);
        }
        CTPlotArea plotArea = getCTPlotArea();
        switch (type) {
            case AREA:
                return new XDDFAreaChartData(this, plotArea.addNewAreaChart(), categories, mapValues);
            case AREA3D:
                return new XDDFArea3DChartData(this, plotArea.addNewArea3DChart(), categories, mapValues);
            case BAR:
                return new XDDFBarChartData(this, plotArea.addNewBarChart(), categories, mapValues);
            case BAR3D:
                return new XDDFBar3DChartData(this, plotArea.addNewBar3DChart(), categories, mapValues);
            case DOUGHNUT:
                return new XDDFDoughnutChartData(this, plotArea.addNewDoughnutChart());
            case LINE:
                return new XDDFLineChartData(this, plotArea.addNewLineChart(), categories, mapValues);
            case LINE3D:
                return new XDDFLine3DChartData(this, plotArea.addNewLine3DChart(), categories, mapValues);
            case PIE:
                return new XDDFPieChartData(this, plotArea.addNewPieChart());
            case PIE3D:
                return new XDDFPie3DChartData(this, plotArea.addNewPie3DChart());
            case RADAR:
                return new XDDFRadarChartData(this, plotArea.addNewRadarChart(), categories, mapValues);
            case SCATTER:
                return new XDDFScatterChartData(this, plotArea.addNewScatterChart(), categories, mapValues);
            case SURFACE:
                return new XDDFSurfaceChartData(this, plotArea.addNewSurfaceChart(), categories, mapValues);
            case SURFACE3D:
                return new XDDFSurface3DChartData(this, plotArea.addNewSurface3DChart(), categories, mapValues);
            default:
                return null;
        }
    }

    public List<? extends XDDFChartAxis> getAxes() {
        if (this.axes.isEmpty() && hasAxes()) {
            parseAxes();
        }
        return Collections.unmodifiableList(this.axes);
    }

    private boolean hasAxes() {
        CTPlotArea ctPlotArea = getCTPlotArea();
        int totalAxisCount = ctPlotArea.sizeOfValAxArray() + ctPlotArea.sizeOfCatAxArray() + ctPlotArea.sizeOfDateAxArray() + ctPlotArea.sizeOfSerAxArray();
        return totalAxisCount > 0;
    }

    private void parseAxes() {
        for (CTCatAx catAx : getCTPlotArea().getCatAxArray()) {
            this.axes.add(new XDDFCategoryAxis(catAx));
        }
        for (CTDateAx dateAx : getCTPlotArea().getDateAxArray()) {
            this.axes.add(new XDDFDateAxis(dateAx));
        }
        for (CTSerAx serAx : getCTPlotArea().getSerAxArray()) {
            this.axes.add(new XDDFSeriesAxis(serAx));
        }
        for (CTValAx valAx : getCTPlotArea().getValAxArray()) {
            this.axes.add(new XDDFValueAxis(valAx));
        }
    }

    public void setValueRange(int axisIndex, Double minimum, Double maximum, Double majorUnit, Double minorUnit) {
        XDDFChartAxis axis = getAxes().get(axisIndex);
        if (axis == null) {
            return;
        }
        if (minimum != null) {
            axis.setMinimum(minimum.doubleValue());
        }
        if (maximum != null) {
            axis.setMaximum(maximum.doubleValue());
        }
        if (majorUnit != null) {
            axis.setMajorUnit(majorUnit.doubleValue());
        }
        if (minorUnit != null) {
            axis.setMinorUnit(minorUnit.doubleValue());
        }
    }

    public PackageRelationship createRelationshipInChart(POIXMLRelation chartRelation, POIXMLFactory chartFactory, int chartIndex) {
        POIXMLDocumentPart documentPart = createRelationship(chartRelation, chartFactory, chartIndex, true).getDocumentPart();
        return addRelation(null, chartRelation, documentPart).getRelationship();
    }

    private PackagePart createWorksheetPart(POIXMLRelation chartWorkbookRelation, POIXMLFactory chartFactory) throws InvalidFormatException {
        PackageRelationship xlsx = createRelationshipInChart(chartWorkbookRelation, chartFactory, this.chartIndex);
        setExternalId(xlsx.getId());
        return getTargetPart(xlsx);
    }

    public void saveWorkbook(XSSFWorkbook workbook) throws IOException, InvalidFormatException {
        PackagePart worksheetPart = getWorksheetPart();
        if (worksheetPart == null) {
            POIXMLRelation chartWorkbookRelation = getChartWorkbookRelation();
            POIXMLFactory chartFactory = getChartFactory();
            if (chartWorkbookRelation != null && chartFactory != null) {
                worksheetPart = createWorksheetPart(chartWorkbookRelation, chartFactory);
            } else {
                throw new InvalidFormatException("unable to determine chart relations");
            }
        }
        OutputStream xlsOut = worksheetPart.getOutputStream();
        try {
            setWorksheetPartCommitted();
            workbook.write(xlsOut);
            if (xlsOut != null) {
                xlsOut.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (xlsOut != null) {
                    try {
                        xlsOut.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void fillSheet(XSSFSheet sheet, XDDFDataSource<?> categoryData, XDDFNumericalDataSource<?> valuesData) {
        int numOfPoints = categoryData.getPointCount();
        for (int i = 0; i < numOfPoints; i++) {
            XSSFRow row = getRow(sheet, i + 1);
            Object category = categoryData.getPointAt(i);
            if (category != null) {
                getCell(row, categoryData.getColIndex()).setCellValue(category.toString());
            }
            Number value = (Number) valuesData.getPointAt(i);
            if (value != null) {
                getCell(row, valuesData.getColIndex()).setCellValue(value.doubleValue());
            }
        }
    }

    private XSSFRow getRow(XSSFSheet sheet, int index) {
        XSSFRow row = sheet.getRow(index);
        if (row == null) {
            return sheet.createRow(index);
        }
        return row;
    }

    private XSSFCell getCell(XSSFRow row, int index) {
        XSSFCell cell = row.getCell(index);
        if (cell == null) {
            return row.createCell(index);
        }
        return cell;
    }

    public void importContent(XDDFChart other) {
        getCTChartSpace().set(other.getCTChartSpace());
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTChartSpace.type.getName().getNamespaceURI(), "chartSpace", "c"));
        if (this.workbook != null) {
            try {
                saveWorkbook(this.workbook);
            } catch (InvalidFormatException e) {
                throw new POIXMLException(e);
            }
        }
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.chartSpace.save(out, xmlOptions);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public CellReference setSheetTitle(String title, int column) {
        XSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        XSSFRow row = getRow(sheet, 0);
        XSSFCell cell = getCell(row, column);
        cell.setCellValue(title);
        return new CellReference(sheet.getSheetName(), 0, column, true, true);
    }

    public String formatRange(CellRangeAddress range) {
        XSSFSheet sheet = getSheet();
        if (sheet == null) {
            return null;
        }
        return range.formatAsString(sheet.getSheetName(), true);
    }

    private XSSFSheet getSheet() {
        try {
            return getWorkbook().getSheetAt(0);
        } catch (IOException | InvalidFormatException e) {
            return null;
        }
    }

    private PackagePart getWorksheetPart() throws InvalidFormatException {
        for (POIXMLDocumentPart.RelationPart part : getRelationParts()) {
            if (POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(part.getRelationship().getRelationshipType())) {
                return getTargetPart(part.getRelationship());
            }
        }
        return null;
    }

    private void setWorksheetPartCommitted() {
        for (POIXMLDocumentPart.RelationPart part : getRelationParts()) {
            if (POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(part.getRelationship().getRelationshipType())) {
                part.getDocumentPart().setCommitted(true);
                return;
            }
        }
    }

    public XSSFWorkbook getWorkbook() throws IOException, InvalidFormatException {
        if (this.workbook == null) {
            try {
                PackagePart worksheetPart = getWorksheetPart();
                if (worksheetPart == null) {
                    this.workbook = new XSSFWorkbook();
                    this.workbook.createSheet();
                } else {
                    InputStream stream = worksheetPart.getInputStream();
                    try {
                        this.workbook = new XSSFWorkbook(stream);
                        if (stream != null) {
                            stream.close();
                        }
                    } finally {
                    }
                }
            } catch (NotOfficeXmlFileException e) {
                this.workbook = new XSSFWorkbook();
                this.workbook.createSheet();
            }
        }
        return this.workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public void setExternalId(String id) {
        CTExternalData externalData;
        CTChartSpace ctChartSpace = getCTChartSpace();
        if (ctChartSpace.isSetExternalData()) {
            externalData = ctChartSpace.getExternalData();
        } else {
            externalData = ctChartSpace.addNewExternalData();
        }
        externalData.setId(id);
    }

    protected int getChartIndex() {
        return this.chartIndex;
    }

    public void setChartIndex(int chartIndex) {
        this.chartIndex = chartIndex;
    }

    public void replaceReferences(XSSFSheet newSheet) {
        XDDFDataSource<?> fromStringCellRange;
        for (XDDFChartData data : getChartSeries()) {
            for (XDDFChartData.Series series : data.series) {
                XDDFDataSource<?> newCategory = series.categoryData;
                XDDFNumericalDataSource<? extends Number> newValues = series.valuesData;
                try {
                    if (series.categoryData != null && series.categoryData.isReference()) {
                        String ref = series.categoryData.getDataRangeReference();
                        CellRangeAddress rangeAddress = CellRangeAddress.valueOf(ref.substring(ref.indexOf(33) + 1));
                        if (series.categoryData.isNumeric()) {
                            fromStringCellRange = XDDFDataSourcesFactory.fromNumericCellRange(newSheet, rangeAddress);
                        } else {
                            fromStringCellRange = XDDFDataSourcesFactory.fromStringCellRange(newSheet, rangeAddress);
                        }
                        newCategory = fromStringCellRange;
                        if (newCategory.isNumeric()) {
                            ((XDDFNumericalDataSource) newCategory).setFormatCode(series.categoryData.getFormatCode());
                        }
                    }
                    if (series.valuesData != null && series.valuesData.isReference()) {
                        String ref2 = series.valuesData.getDataRangeReference();
                        newValues = XDDFDataSourcesFactory.fromNumericCellRange(newSheet, CellRangeAddress.valueOf(ref2.substring(ref2.indexOf(33) + 1)));
                        newValues.setFormatCode(series.valuesData.getFormatCode());
                    }
                } catch (IllegalArgumentException e) {
                }
                series.replaceData(newCategory, newValues);
                series.plot();
            }
        }
    }
}
