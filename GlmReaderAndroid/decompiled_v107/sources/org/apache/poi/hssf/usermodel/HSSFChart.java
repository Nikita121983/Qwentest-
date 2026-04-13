package org.apache.poi.hssf.usermodel;

import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.HCenterRecord;
import org.apache.poi.hssf.record.HeaderRecord;
import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.hssf.record.ProtectRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.VCenterRecord;
import org.apache.poi.hssf.record.chart.AreaFormatRecord;
import org.apache.poi.hssf.record.chart.AreaRecord;
import org.apache.poi.hssf.record.chart.AxisLineFormatRecord;
import org.apache.poi.hssf.record.chart.AxisOptionsRecord;
import org.apache.poi.hssf.record.chart.AxisParentRecord;
import org.apache.poi.hssf.record.chart.AxisRecord;
import org.apache.poi.hssf.record.chart.AxisUsedRecord;
import org.apache.poi.hssf.record.chart.BarRecord;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.apache.poi.hssf.record.chart.ChartFormatRecord;
import org.apache.poi.hssf.record.chart.ChartRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;
import org.apache.poi.hssf.record.chart.EndRecord;
import org.apache.poi.hssf.record.chart.FontBasisRecord;
import org.apache.poi.hssf.record.chart.FontIndexRecord;
import org.apache.poi.hssf.record.chart.FrameRecord;
import org.apache.poi.hssf.record.chart.LegendRecord;
import org.apache.poi.hssf.record.chart.LineFormatRecord;
import org.apache.poi.hssf.record.chart.LinkedDataRecord;
import org.apache.poi.hssf.record.chart.PlotAreaRecord;
import org.apache.poi.hssf.record.chart.PlotGrowthRecord;
import org.apache.poi.hssf.record.chart.SeriesChartGroupIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.apache.poi.hssf.record.chart.SeriesTextRecord;
import org.apache.poi.hssf.record.chart.SheetPropertiesRecord;
import org.apache.poi.hssf.record.chart.TextRecord;
import org.apache.poi.hssf.record.chart.TickRecord;
import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.apache.poi.hssf.record.chart.ValueRangeRecord;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressBase;

/* loaded from: classes10.dex */
public final class HSSFChart {
    private ChartRecord chartRecord;
    private ChartTitleFormatRecord chartTitleFormat;
    private SeriesTextRecord chartTitleText;
    private LegendRecord legendRecord;
    private HSSFSheet sheet;
    private List<ValueRangeRecord> valueRanges = new ArrayList();
    private HSSFChartType type = HSSFChartType.Unknown;
    private List<HSSFSeries> series = new ArrayList();

    /* loaded from: classes10.dex */
    public enum HSSFChartType {
        Area { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.1
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return AreaRecord.sid;
            }
        },
        Bar { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.2
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return BarRecord.sid;
            }
        },
        Line { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.3
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return (short) 4120;
            }
        },
        Pie { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.4
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return (short) 4121;
            }
        },
        Scatter { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.5
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return (short) 4123;
            }
        },
        Unknown { // from class: org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType.6
            @Override // org.apache.poi.hssf.usermodel.HSSFChart.HSSFChartType
            public short getSid() {
                return (short) 0;
            }
        };

        public abstract short getSid();
    }

    private HSSFChart(HSSFSheet sheet, ChartRecord chartRecord) {
        this.chartRecord = chartRecord;
        this.sheet = sheet;
    }

    public void createBarChart(HSSFWorkbook workbook, HSSFSheet parentSheet) {
        List<Record> records = new ArrayList<>();
        records.add(createMSDrawingObjectRecord());
        records.add(createOBJRecord());
        records.add(createBOFRecord());
        records.add(new HeaderRecord(""));
        records.add(new FooterRecord(""));
        records.add(createHCenterRecord());
        records.add(createVCenterRecord());
        records.add(createPrintSetupRecord());
        records.add(createFontBasisRecord1());
        records.add(createFontBasisRecord2());
        records.add(new ProtectRecord(false));
        records.add(createUnitsRecord());
        records.add(createChartRecord(0, 0, 30434904, 19031616));
        records.add(createBeginRecord());
        records.add(createSCLRecord((short) 1, (short) 1));
        records.add(createPlotGrowthRecord(65536, 65536));
        records.add(createFrameRecord1());
        records.add(createBeginRecord());
        records.add(createLineFormatRecord(true));
        records.add(createAreaFormatRecord1());
        records.add(createEndRecord());
        records.add(createSeriesRecord());
        records.add(createBeginRecord());
        records.add(createTitleLinkedDataRecord());
        records.add(createValuesLinkedDataRecord());
        records.add(createCategoriesLinkedDataRecord());
        records.add(createDataFormatRecord());
        records.add(createSeriesToChartGroupRecord());
        records.add(createEndRecord());
        records.add(createSheetPropsRecord());
        records.add(createDefaultTextRecord((short) 2));
        records.add(createAllTextRecord());
        records.add(createBeginRecord());
        records.add(createFontIndexRecord(5));
        records.add(createDirectLinkRecord());
        records.add(createEndRecord());
        records.add(createDefaultTextRecord((short) 3));
        records.add(createUnknownTextRecord());
        records.add(createBeginRecord());
        records.add(createFontIndexRecord(6));
        records.add(createDirectLinkRecord());
        records.add(createEndRecord());
        records.add(createAxisUsedRecord((short) 1));
        createAxisRecords(records);
        records.add(createEndRecord());
        records.add(createDimensionsRecord());
        records.add(createSeriesIndexRecord(2));
        records.add(createSeriesIndexRecord(1));
        records.add(createSeriesIndexRecord(3));
        records.add(EOFRecord.instance);
        parentSheet.insertChartRecords(records);
        workbook.insertChartRecord();
    }

    public static HSSFChart[] getSheetCharts(HSSFSheet sheet) {
        List<HSSFChart> charts = new ArrayList<>();
        HSSFChart lastChart = null;
        HSSFSeries lastSeries = null;
        List<RecordBase> records = sheet.getSheet().getRecords();
        Iterator<RecordBase> it = records.iterator();
        while (true) {
            int i = 0;
            if (it.hasNext()) {
                RecordBase r = it.next();
                if (r instanceof ChartRecord) {
                    lastSeries = null;
                    HSSFChart lastChart2 = new HSSFChart(sheet, (ChartRecord) r);
                    charts.add(lastChart2);
                    lastChart = lastChart2;
                } else if (r instanceof LinkedDataRecord) {
                    LinkedDataRecord linkedDataRecord = (LinkedDataRecord) r;
                    if (lastSeries != null) {
                        lastSeries.insertData(linkedDataRecord);
                    }
                }
                if (lastChart != null) {
                    if (r instanceof LegendRecord) {
                        lastChart.legendRecord = (LegendRecord) r;
                    } else if (r instanceof SeriesRecord) {
                        HSSFSeries series = new HSSFSeries((SeriesRecord) r);
                        lastChart.series.add(series);
                        lastSeries = series;
                    } else if (r instanceof ChartTitleFormatRecord) {
                        lastChart.chartTitleFormat = (ChartTitleFormatRecord) r;
                    } else if (r instanceof SeriesTextRecord) {
                        SeriesTextRecord str = (SeriesTextRecord) r;
                        if (lastChart.legendRecord != null || lastChart.series.isEmpty()) {
                            lastChart.chartTitleText = str;
                        } else {
                            lastChart.series.get(lastChart.series.size() - 1).seriesTitleText = str;
                        }
                    } else if (r instanceof ValueRangeRecord) {
                        lastChart.valueRanges.add((ValueRangeRecord) r);
                    } else if (r instanceof Record) {
                        Record record = (Record) r;
                        HSSFChartType[] values = HSSFChartType.values();
                        int length = values.length;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            HSSFChartType type = values[i];
                            if (type == HSSFChartType.Unknown || record.getSid() != type.getSid()) {
                                i++;
                            } else {
                                lastChart.type = type;
                                break;
                            }
                        }
                    }
                }
            } else {
                return (HSSFChart[]) charts.toArray(new HSSFChart[0]);
            }
        }
    }

    public int getChartX() {
        return this.chartRecord.getX();
    }

    public int getChartY() {
        return this.chartRecord.getY();
    }

    public int getChartWidth() {
        return this.chartRecord.getWidth();
    }

    public int getChartHeight() {
        return this.chartRecord.getHeight();
    }

    public void setChartX(int x) {
        this.chartRecord.setX(x);
    }

    public void setChartY(int y) {
        this.chartRecord.setY(y);
    }

    public void setChartWidth(int width) {
        this.chartRecord.setWidth(width);
    }

    public void setChartHeight(int height) {
        this.chartRecord.setHeight(height);
    }

    public HSSFSeries[] getSeries() {
        return (HSSFSeries[]) this.series.toArray(new HSSFSeries[0]);
    }

    public String getChartTitle() {
        if (this.chartTitleText != null) {
            return this.chartTitleText.getText();
        }
        return null;
    }

    public void setChartTitle(String title) {
        if (this.chartTitleText != null) {
            this.chartTitleText.setText(title);
            return;
        }
        throw new IllegalStateException("No chart title found to change");
    }

    public void setValueRange(int axisIndex, Double minimum, Double maximum, Double majorUnit, Double minorUnit) {
        ValueRangeRecord valueRange = this.valueRanges.get(axisIndex);
        if (valueRange == null) {
            return;
        }
        if (minimum != null) {
            valueRange.setAutomaticMinimum(minimum.isNaN());
            valueRange.setMinimumAxisValue(minimum.doubleValue());
        }
        if (maximum != null) {
            valueRange.setAutomaticMaximum(maximum.isNaN());
            valueRange.setMaximumAxisValue(maximum.doubleValue());
        }
        if (majorUnit != null) {
            valueRange.setAutomaticMajor(majorUnit.isNaN());
            valueRange.setMajorIncrement(majorUnit.doubleValue());
        }
        if (minorUnit != null) {
            valueRange.setAutomaticMinor(minorUnit.isNaN());
            valueRange.setMinorIncrement(minorUnit.doubleValue());
        }
    }

    private SeriesIndexRecord createSeriesIndexRecord(int index) {
        SeriesIndexRecord r = new SeriesIndexRecord();
        r.setIndex((short) index);
        return r;
    }

    private DimensionsRecord createDimensionsRecord() {
        DimensionsRecord r = new DimensionsRecord();
        r.setFirstRow(0);
        r.setLastRow(31);
        r.setFirstCol((short) 0);
        r.setLastCol((short) 1);
        return r;
    }

    private HCenterRecord createHCenterRecord() {
        HCenterRecord r = new HCenterRecord();
        r.setHCenter(false);
        return r;
    }

    private VCenterRecord createVCenterRecord() {
        VCenterRecord r = new VCenterRecord();
        r.setVCenter(false);
        return r;
    }

    private PrintSetupRecord createPrintSetupRecord() {
        PrintSetupRecord r = new PrintSetupRecord();
        r.setPaperSize((short) 0);
        r.setScale((short) 18);
        r.setPageStart((short) 1);
        r.setFitWidth((short) 1);
        r.setFitHeight((short) 1);
        r.setLeftToRight(false);
        r.setLandscape(false);
        r.setValidSettings(true);
        r.setNoColor(false);
        r.setDraft(false);
        r.setNotes(false);
        r.setNoOrientation(false);
        r.setUsePage(false);
        r.setHResolution((short) 0);
        r.setVResolution((short) 0);
        r.setHeaderMargin(0.5d);
        r.setFooterMargin(0.5d);
        r.setCopies((short) 15);
        return r;
    }

    private FontBasisRecord createFontBasisRecord1() {
        FontBasisRecord r = new FontBasisRecord();
        r.setXBasis((short) 9120);
        r.setYBasis((short) 5640);
        r.setHeightBasis(EscherAggregate.ST_ACTIONBUTTONMOVIE);
        r.setScale((short) 0);
        r.setIndexToFontTable((short) 5);
        return r;
    }

    private FontBasisRecord createFontBasisRecord2() {
        FontBasisRecord r = createFontBasisRecord1();
        r.setIndexToFontTable((short) 6);
        return r;
    }

    private BOFRecord createBOFRecord() {
        BOFRecord r = new BOFRecord();
        r.setVersion(600);
        r.setType(20);
        r.setBuild(7422);
        r.setBuildYear(1997);
        r.setHistoryBitMask(16585);
        r.setRequiredVersion(106);
        return r;
    }

    private UnknownRecord createOBJRecord() {
        byte[] data = {ParenthesisPtg.sid, 0, UnaryPlusPtg.sid, 0, 5, 0, 2, 0, RangePtg.sid, 96, 0, 0, 0, 0, -72, 3, -121, 3, 0, 0, 0, 0, 0, 0, 0, 0};
        return new UnknownRecord(93, data);
    }

    private UnknownRecord createMSDrawingObjectRecord() {
        byte[] data = {IntersectionPtg.sid, 0, 2, -16, -64, 0, 0, 0, UnionPtg.sid, 0, 8, -16, 8, 0, 0, 0, 2, 0, 0, 0, 2, 4, 0, 0, IntersectionPtg.sid, 0, 3, -16, -88, 0, 0, 0, IntersectionPtg.sid, 0, 4, -16, 40, 0, 0, 0, 1, 0, 9, -16, UnionPtg.sid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 10, -16, 8, 0, 0, 0, 0, 4, 0, 0, 5, 0, 0, 0, IntersectionPtg.sid, 0, 4, -16, 112, 0, 0, 0, -110, 12, 10, -16, 8, 0, 0, 0, 2, 4, 0, 0, 0, 10, 0, 0, -109, 0, 11, -16, TarConstants.LF_FIFO, 0, 0, 0, ByteCompanionObject.MAX_VALUE, 0, 4, 1, 4, 1, -65, 0, 8, 0, 8, 0, -127, 1, 78, 0, 0, 8, -125, 1, TarConstants.LF_MULTIVOLUME, 0, 0, 8, -65, 1, UnionPtg.sid, 0, RangePtg.sid, 0, -64, 1, TarConstants.LF_MULTIVOLUME, 0, 0, 8, -1, 1, 8, 0, 8, 0, 63, 2, 0, 0, 2, 0, -65, 3, 0, 0, 8, 0, 0, 0, UnionPtg.sid, -16, UnaryPlusPtg.sid, 0, 0, 0, 0, 0, 4, 0, -64, 2, 10, 0, -12, 0, NotEqualPtg.sid, 0, 102, 1, 32, 0, -23, 0, 0, 0, RangePtg.sid, -16, 0, 0, 0, 0};
        return new UnknownRecord(236, data);
    }

    private void createAxisRecords(List<Record> records) {
        records.add(createAxisParentRecord());
        records.add(createBeginRecord());
        records.add(createAxisRecord((short) 0));
        records.add(createBeginRecord());
        records.add(createCategorySeriesAxisRecord());
        records.add(createAxisOptionsRecord());
        records.add(createTickRecord1());
        records.add(createEndRecord());
        records.add(createAxisRecord((short) 1));
        records.add(createBeginRecord());
        records.add(createValueRangeRecord());
        records.add(createTickRecord2());
        records.add(createAxisLineFormatRecord((short) 1));
        records.add(createLineFormatRecord(false));
        records.add(createEndRecord());
        records.add(createPlotAreaRecord());
        records.add(createFrameRecord2());
        records.add(createBeginRecord());
        records.add(createLineFormatRecord2());
        records.add(createAreaFormatRecord2());
        records.add(createEndRecord());
        records.add(createChartFormatRecord());
        records.add(createBeginRecord());
        records.add(createBarRecord());
        records.add(createLegendRecord());
        records.add(createBeginRecord());
        records.add(createTextRecord());
        records.add(createBeginRecord());
        records.add(createLinkedDataRecord());
        records.add(createEndRecord());
        records.add(createEndRecord());
        records.add(createEndRecord());
        records.add(createEndRecord());
    }

    private LinkedDataRecord createLinkedDataRecord() {
        LinkedDataRecord r = new LinkedDataRecord();
        r.setLinkType((byte) 0);
        r.setReferenceType((byte) 1);
        r.setCustomNumberFormat(false);
        r.setIndexNumberFmtRecord((short) 0);
        r.setFormulaOfLink(null);
        return r;
    }

    private TextRecord createTextRecord() {
        TextRecord r = new TextRecord();
        r.setHorizontalAlignment((byte) 2);
        r.setVerticalAlignment((byte) 2);
        r.setDisplayMode((short) 1);
        r.setRgbColor(0);
        r.setX(-37);
        r.setY(-60);
        r.setWidth(0);
        r.setHeight(0);
        r.setAutoColor(true);
        r.setShowKey(false);
        r.setShowValue(false);
        r.setVertical(false);
        r.setAutoGeneratedText(true);
        r.setGenerated(true);
        r.setAutoLabelDeleted(false);
        r.setAutoBackground(true);
        r.setRotation((short) 0);
        r.setShowCategoryLabelAsPercentage(false);
        r.setShowValueAsPercentage(false);
        r.setShowBubbleSizes(false);
        r.setShowLabel(false);
        r.setIndexOfColorValue((short) 77);
        r.setDataLabelPlacement((short) 0);
        r.setTextRotation((short) 0);
        return r;
    }

    private LegendRecord createLegendRecord() {
        LegendRecord r = new LegendRecord();
        r.setXAxisUpperLeft(3542);
        r.setYAxisUpperLeft(1566);
        r.setXSize(437);
        r.setYSize(213);
        r.setType((byte) 3);
        r.setSpacing((byte) 1);
        r.setAutoPosition(true);
        r.setAutoSeries(true);
        r.setAutoXPositioning(true);
        r.setAutoYPositioning(true);
        r.setVertical(true);
        r.setDataTable(false);
        return r;
    }

    private BarRecord createBarRecord() {
        BarRecord r = new BarRecord();
        r.setBarSpace((short) 0);
        r.setCategorySpace(EscherAggregate.ST_TEXTCIRCLEPOUR);
        r.setHorizontal(false);
        r.setStacked(false);
        r.setDisplayAsPercentage(false);
        r.setShadow(false);
        return r;
    }

    private ChartFormatRecord createChartFormatRecord() {
        ChartFormatRecord r = new ChartFormatRecord();
        r.setXPosition(0);
        r.setYPosition(0);
        r.setWidth(0);
        r.setHeight(0);
        r.setVaryDisplayPattern(false);
        return r;
    }

    private PlotAreaRecord createPlotAreaRecord() {
        return new PlotAreaRecord();
    }

    private AxisLineFormatRecord createAxisLineFormatRecord(short format) {
        AxisLineFormatRecord r = new AxisLineFormatRecord();
        r.setAxisType(format);
        return r;
    }

    private ValueRangeRecord createValueRangeRecord() {
        ValueRangeRecord r = new ValueRangeRecord();
        r.setMinimumAxisValue(0.0d);
        r.setMaximumAxisValue(0.0d);
        r.setMajorIncrement(0.0d);
        r.setMinorIncrement(0.0d);
        r.setCategoryAxisCross(0.0d);
        r.setAutomaticMinimum(true);
        r.setAutomaticMaximum(true);
        r.setAutomaticMajor(true);
        r.setAutomaticMinor(true);
        r.setAutomaticCategoryCrossing(true);
        r.setLogarithmicScale(false);
        r.setValuesInReverse(false);
        r.setCrossCategoryAxisAtMaximum(false);
        r.setReserved(true);
        return r;
    }

    private TickRecord createTickRecord1() {
        TickRecord r = new TickRecord();
        r.setMajorTickType((byte) 2);
        r.setMinorTickType((byte) 0);
        r.setLabelPosition((byte) 3);
        r.setBackground((byte) 1);
        r.setLabelColorRgb(0);
        r.setZero1(0);
        r.setZero2(0);
        r.setZero3((short) 45);
        r.setAutorotate(true);
        r.setAutoTextBackground(true);
        r.setRotation((short) 0);
        r.setAutorotate(true);
        r.setTickColor((short) 77);
        return r;
    }

    private TickRecord createTickRecord2() {
        TickRecord r = createTickRecord1();
        r.setZero3((short) 0);
        return r;
    }

    private AxisOptionsRecord createAxisOptionsRecord() {
        AxisOptionsRecord r = new AxisOptionsRecord();
        r.setMinimumCategory((short) -28644);
        r.setMaximumCategory((short) -28715);
        r.setMajorUnitValue((short) 2);
        r.setMajorUnit((short) 0);
        r.setMinorUnitValue((short) 1);
        r.setMinorUnit((short) 0);
        r.setBaseUnit((short) 0);
        r.setCrossingPoint((short) -28644);
        r.setDefaultMinimum(true);
        r.setDefaultMaximum(true);
        r.setDefaultMajor(true);
        r.setDefaultMinorUnit(true);
        r.setIsDate(true);
        r.setDefaultBase(true);
        r.setDefaultCross(true);
        r.setDefaultDateSettings(true);
        return r;
    }

    private CategorySeriesAxisRecord createCategorySeriesAxisRecord() {
        CategorySeriesAxisRecord r = new CategorySeriesAxisRecord();
        r.setCrossingPoint((short) 1);
        r.setLabelFrequency((short) 1);
        r.setTickMarkFrequency((short) 1);
        r.setValueAxisCrossing(true);
        r.setCrossesFarRight(false);
        r.setReversed(false);
        return r;
    }

    private AxisRecord createAxisRecord(short axisType) {
        AxisRecord r = new AxisRecord();
        r.setAxisType(axisType);
        return r;
    }

    private AxisParentRecord createAxisParentRecord() {
        AxisParentRecord r = new AxisParentRecord();
        r.setAxisType((short) 0);
        r.setX(479);
        r.setY(221);
        r.setWidth(2995);
        r.setHeight(2902);
        return r;
    }

    private AxisUsedRecord createAxisUsedRecord(short numAxis) {
        AxisUsedRecord r = new AxisUsedRecord();
        r.setNumAxis(numAxis);
        return r;
    }

    private LinkedDataRecord createDirectLinkRecord() {
        LinkedDataRecord r = new LinkedDataRecord();
        r.setLinkType((byte) 0);
        r.setReferenceType((byte) 1);
        r.setCustomNumberFormat(false);
        r.setIndexNumberFmtRecord((short) 0);
        r.setFormulaOfLink(null);
        return r;
    }

    private FontIndexRecord createFontIndexRecord(int index) {
        FontIndexRecord r = new FontIndexRecord();
        r.setFontIndex((short) index);
        return r;
    }

    private TextRecord createAllTextRecord() {
        TextRecord r = new TextRecord();
        r.setHorizontalAlignment((byte) 2);
        r.setVerticalAlignment((byte) 2);
        r.setDisplayMode((short) 1);
        r.setRgbColor(0);
        r.setX(-37);
        r.setY(-60);
        r.setWidth(0);
        r.setHeight(0);
        r.setAutoColor(true);
        r.setShowKey(false);
        r.setShowValue(true);
        r.setVertical(false);
        r.setAutoGeneratedText(true);
        r.setGenerated(true);
        r.setAutoLabelDeleted(false);
        r.setAutoBackground(true);
        r.setRotation((short) 0);
        r.setShowCategoryLabelAsPercentage(false);
        r.setShowValueAsPercentage(false);
        r.setShowBubbleSizes(false);
        r.setShowLabel(false);
        r.setIndexOfColorValue((short) 77);
        r.setDataLabelPlacement((short) 0);
        r.setTextRotation((short) 0);
        return r;
    }

    private TextRecord createUnknownTextRecord() {
        TextRecord r = new TextRecord();
        r.setHorizontalAlignment((byte) 2);
        r.setVerticalAlignment((byte) 2);
        r.setDisplayMode((short) 1);
        r.setRgbColor(0);
        r.setX(-37);
        r.setY(-60);
        r.setWidth(0);
        r.setHeight(0);
        r.setAutoColor(true);
        r.setShowKey(false);
        r.setShowValue(false);
        r.setVertical(false);
        r.setAutoGeneratedText(true);
        r.setGenerated(true);
        r.setAutoLabelDeleted(false);
        r.setAutoBackground(true);
        r.setRotation((short) 0);
        r.setShowCategoryLabelAsPercentage(false);
        r.setShowValueAsPercentage(false);
        r.setShowBubbleSizes(false);
        r.setShowLabel(false);
        r.setIndexOfColorValue((short) 77);
        r.setDataLabelPlacement((short) 11088);
        r.setTextRotation((short) 0);
        return r;
    }

    private DefaultDataLabelTextPropertiesRecord createDefaultTextRecord(short categoryDataType) {
        DefaultDataLabelTextPropertiesRecord r = new DefaultDataLabelTextPropertiesRecord();
        r.setCategoryDataType(categoryDataType);
        return r;
    }

    private SheetPropertiesRecord createSheetPropsRecord() {
        SheetPropertiesRecord r = new SheetPropertiesRecord();
        r.setChartTypeManuallyFormatted(false);
        r.setPlotVisibleOnly(true);
        r.setDoNotSizeWithWindow(false);
        r.setDefaultPlotDimensions(true);
        r.setAutoPlotArea(false);
        return r;
    }

    private SeriesChartGroupIndexRecord createSeriesToChartGroupRecord() {
        return new SeriesChartGroupIndexRecord();
    }

    private DataFormatRecord createDataFormatRecord() {
        DataFormatRecord r = new DataFormatRecord();
        r.setPointNumber((short) -1);
        r.setSeriesIndex((short) 0);
        r.setSeriesNumber((short) 0);
        r.setUseExcel4Colors(false);
        return r;
    }

    private LinkedDataRecord createCategoriesLinkedDataRecord() {
        LinkedDataRecord r = new LinkedDataRecord();
        r.setLinkType((byte) 2);
        r.setReferenceType((byte) 2);
        r.setCustomNumberFormat(false);
        r.setIndexNumberFmtRecord((short) 0);
        Area3DPtg p = new Area3DPtg(0, 31, 1, 1, false, false, false, false, 0);
        r.setFormulaOfLink(new Ptg[]{p});
        return r;
    }

    private LinkedDataRecord createValuesLinkedDataRecord() {
        LinkedDataRecord r = new LinkedDataRecord();
        r.setLinkType((byte) 1);
        r.setReferenceType((byte) 2);
        r.setCustomNumberFormat(false);
        r.setIndexNumberFmtRecord((short) 0);
        Area3DPtg p = new Area3DPtg(0, 31, 0, 0, false, false, false, false, 0);
        r.setFormulaOfLink(new Ptg[]{p});
        return r;
    }

    private LinkedDataRecord createTitleLinkedDataRecord() {
        LinkedDataRecord r = new LinkedDataRecord();
        r.setLinkType((byte) 0);
        r.setReferenceType((byte) 1);
        r.setCustomNumberFormat(false);
        r.setIndexNumberFmtRecord((short) 0);
        r.setFormulaOfLink(null);
        return r;
    }

    private SeriesRecord createSeriesRecord() {
        SeriesRecord r = new SeriesRecord();
        r.setCategoryDataType((short) 1);
        r.setValuesDataType((short) 1);
        r.setNumCategories((short) 32);
        r.setNumValues((short) 31);
        r.setBubbleSeriesType((short) 1);
        r.setNumBubbleValues((short) 0);
        return r;
    }

    private EndRecord createEndRecord() {
        return new EndRecord();
    }

    private AreaFormatRecord createAreaFormatRecord1() {
        AreaFormatRecord r = new AreaFormatRecord();
        r.setForegroundColor(ViewCompat.MEASURED_SIZE_MASK);
        r.setBackgroundColor(0);
        r.setPattern((short) 1);
        r.setAutomatic(true);
        r.setInvert(false);
        r.setForecolorIndex((short) 78);
        r.setBackcolorIndex((short) 77);
        return r;
    }

    private AreaFormatRecord createAreaFormatRecord2() {
        AreaFormatRecord r = new AreaFormatRecord();
        r.setForegroundColor(12632256);
        r.setBackgroundColor(0);
        r.setPattern((short) 1);
        r.setAutomatic(false);
        r.setInvert(false);
        r.setForecolorIndex((short) 22);
        r.setBackcolorIndex((short) 79);
        return r;
    }

    private LineFormatRecord createLineFormatRecord(boolean drawTicks) {
        LineFormatRecord r = new LineFormatRecord();
        r.setLineColor(0);
        r.setLinePattern((short) 0);
        r.setWeight((short) -1);
        r.setAuto(true);
        r.setDrawTicks(drawTicks);
        r.setColourPaletteIndex((short) 77);
        return r;
    }

    private LineFormatRecord createLineFormatRecord2() {
        LineFormatRecord r = new LineFormatRecord();
        r.setLineColor(8421504);
        r.setLinePattern((short) 0);
        r.setWeight((short) 0);
        r.setAuto(false);
        r.setDrawTicks(false);
        r.setUnknown(false);
        r.setColourPaletteIndex((short) 23);
        return r;
    }

    private FrameRecord createFrameRecord1() {
        FrameRecord r = new FrameRecord();
        r.setBorderType((short) 0);
        r.setAutoSize(false);
        r.setAutoPosition(true);
        return r;
    }

    private FrameRecord createFrameRecord2() {
        FrameRecord r = new FrameRecord();
        r.setBorderType((short) 0);
        r.setAutoSize(true);
        r.setAutoPosition(true);
        return r;
    }

    private PlotGrowthRecord createPlotGrowthRecord(int horizScale, int vertScale) {
        PlotGrowthRecord r = new PlotGrowthRecord();
        r.setHorizontalScale(horizScale);
        r.setVerticalScale(vertScale);
        return r;
    }

    private SCLRecord createSCLRecord(short numerator, short denominator) {
        SCLRecord r = new SCLRecord();
        r.setDenominator(denominator);
        r.setNumerator(numerator);
        return r;
    }

    private BeginRecord createBeginRecord() {
        return new BeginRecord();
    }

    private ChartRecord createChartRecord(int x, int y, int width, int height) {
        ChartRecord r = new ChartRecord();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        return r;
    }

    private UnitsRecord createUnitsRecord() {
        UnitsRecord r = new UnitsRecord();
        r.setUnits((short) 0);
        return r;
    }

    /* loaded from: classes10.dex */
    public static class HSSFSeries {
        private LinkedDataRecord dataCategoryLabels;
        private LinkedDataRecord dataName;
        private LinkedDataRecord dataSecondaryCategoryLabels;
        private LinkedDataRecord dataValues;
        private SeriesRecord series;
        private SeriesTextRecord seriesTitleText;

        HSSFSeries(SeriesRecord series) {
            this.series = series;
        }

        void insertData(LinkedDataRecord data) {
            switch (data.getLinkType()) {
                case 0:
                    this.dataName = data;
                    return;
                case 1:
                    this.dataValues = data;
                    return;
                case 2:
                    this.dataCategoryLabels = data;
                    return;
                case 3:
                    this.dataSecondaryCategoryLabels = data;
                    return;
                default:
                    throw new IllegalStateException("Invalid link type: " + ((int) data.getLinkType()));
            }
        }

        void setSeriesTitleText(SeriesTextRecord seriesTitleText) {
            this.seriesTitleText = seriesTitleText;
        }

        public short getNumValues() {
            return this.series.getNumValues();
        }

        public short getValueType() {
            return this.series.getValuesDataType();
        }

        public String getSeriesTitle() {
            if (this.seriesTitleText != null) {
                return this.seriesTitleText.getText();
            }
            return null;
        }

        public void setSeriesTitle(String title) {
            if (this.seriesTitleText != null) {
                this.seriesTitleText.setText(title);
                return;
            }
            throw new IllegalStateException("No series title found to change");
        }

        public LinkedDataRecord getDataName() {
            return this.dataName;
        }

        public LinkedDataRecord getDataValues() {
            return this.dataValues;
        }

        public LinkedDataRecord getDataCategoryLabels() {
            return this.dataCategoryLabels;
        }

        public LinkedDataRecord getDataSecondaryCategoryLabels() {
            return this.dataSecondaryCategoryLabels;
        }

        public SeriesRecord getSeries() {
            return this.series;
        }

        private CellRangeAddressBase getCellRange(LinkedDataRecord linkedDataRecord) {
            if (linkedDataRecord == null) {
                return null;
            }
            int firstRow = 0;
            int lastRow = 0;
            int firstCol = 0;
            int lastCol = 0;
            for (Ptg ptg : linkedDataRecord.getFormulaOfLink()) {
                if (ptg instanceof AreaPtgBase) {
                    AreaPtgBase areaPtg = (AreaPtgBase) ptg;
                    firstRow = areaPtg.getFirstRow();
                    lastRow = areaPtg.getLastRow();
                    firstCol = areaPtg.getFirstColumn();
                    lastCol = areaPtg.getLastColumn();
                }
            }
            return new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        }

        public CellRangeAddressBase getValuesCellRange() {
            return getCellRange(this.dataValues);
        }

        public CellRangeAddressBase getCategoryLabelsCellRange() {
            return getCellRange(this.dataCategoryLabels);
        }

        private Integer setVerticalCellRange(LinkedDataRecord linkedDataRecord, CellRangeAddressBase range) {
            if (linkedDataRecord == null) {
                return null;
            }
            List<Ptg> ptgList = new ArrayList<>();
            int rowCount = (range.getLastRow() - range.getFirstRow()) + 1;
            int colCount = (range.getLastColumn() - range.getFirstColumn()) + 1;
            for (Ptg ptg : linkedDataRecord.getFormulaOfLink()) {
                if (ptg instanceof AreaPtgBase) {
                    AreaPtgBase areaPtg = (AreaPtgBase) ptg;
                    areaPtg.setFirstRow(range.getFirstRow());
                    areaPtg.setLastRow(range.getLastRow());
                    areaPtg.setFirstColumn(range.getFirstColumn());
                    areaPtg.setLastColumn(range.getLastColumn());
                    ptgList.add(areaPtg);
                }
            }
            linkedDataRecord.setFormulaOfLink((Ptg[]) ptgList.toArray(Ptg.EMPTY_PTG_ARRAY));
            return Integer.valueOf(rowCount * colCount);
        }

        public void setValuesCellRange(CellRangeAddressBase range) {
            Integer count = setVerticalCellRange(this.dataValues, range);
            if (count == null) {
                return;
            }
            this.series.setNumValues((short) count.intValue());
        }

        public void setCategoryLabelsCellRange(CellRangeAddressBase range) {
            Integer count = setVerticalCellRange(this.dataCategoryLabels, range);
            if (count == null) {
                return;
            }
            this.series.setNumCategories((short) count.intValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [org.apache.poi.hssf.record.chart.DataFormatRecord] */
    /* JADX WARN: Type inference failed for: r11v13, types: [org.apache.poi.hssf.record.chart.SeriesRecord] */
    /* JADX WARN: Type inference failed for: r11v14, types: [org.apache.poi.hssf.record.chart.EndRecord] */
    /* JADX WARN: Type inference failed for: r11v16, types: [org.apache.poi.hssf.record.chart.BeginRecord] */
    /* JADX WARN: Type inference failed for: r16v1, types: [org.apache.poi.hssf.record.Record] */
    public HSSFSeries createSeries() throws Exception {
        SeriesTextRecord seriesTextRecord;
        HSSFSeries hSSFSeries;
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = 0;
        List<RecordBase> records = this.sheet.getSheet().getRecords();
        for (RecordBase recordBase : records) {
            i++;
            if (recordBase instanceof BeginRecord) {
                i2++;
            } else if (recordBase instanceof EndRecord) {
                i2--;
                if (i5 == i2) {
                    i5 = -1;
                    i6 = i;
                    if (!z) {
                        arrayList.add(recordBase);
                        z = true;
                    }
                }
                if (i4 == i2) {
                    break;
                }
            }
            if (recordBase instanceof ChartRecord) {
                if (recordBase == this.chartRecord) {
                    i3 = i;
                    i4 = i2;
                }
            } else if ((recordBase instanceof SeriesRecord) && i3 != -1) {
                i7++;
                i5 = i2;
            }
            if (i5 != -1 && !z) {
                arrayList.add(recordBase);
            }
        }
        HSSFSeries hSSFSeries2 = null;
        if (i6 == -1) {
            return null;
        }
        int i8 = i6 + 1;
        HSSFSeries hSSFSeries3 = null;
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            RecordBase recordBase2 = (RecordBase) it.next();
            HSSFSeries hSSFSeries4 = hSSFSeries2;
            if (recordBase2 instanceof BeginRecord) {
                seriesTextRecord = new BeginRecord();
                hSSFSeries = hSSFSeries3;
            } else if (recordBase2 instanceof EndRecord) {
                seriesTextRecord = new EndRecord();
                hSSFSeries = hSSFSeries3;
            } else if (recordBase2 instanceof SeriesRecord) {
                ?? copy = ((SeriesRecord) recordBase2).copy();
                hSSFSeries = new HSSFSeries(copy);
                seriesTextRecord = copy;
            } else if (recordBase2 instanceof LinkedDataRecord) {
                LinkedDataRecord copy2 = ((LinkedDataRecord) recordBase2).copy();
                if (hSSFSeries3 != null) {
                    hSSFSeries3.insertData(copy2);
                }
                seriesTextRecord = copy2;
                hSSFSeries = hSSFSeries3;
            } else if (recordBase2 instanceof DataFormatRecord) {
                ?? copy3 = ((DataFormatRecord) recordBase2).copy();
                copy3.setSeriesIndex((short) i7);
                copy3.setSeriesNumber((short) i7);
                seriesTextRecord = copy3;
                hSSFSeries = hSSFSeries3;
            } else if (recordBase2 instanceof SeriesTextRecord) {
                SeriesTextRecord copy4 = ((SeriesTextRecord) recordBase2).copy();
                if (hSSFSeries3 != null) {
                    hSSFSeries3.setSeriesTitleText(copy4);
                }
                seriesTextRecord = copy4;
                hSSFSeries = hSSFSeries3;
            } else if (!(recordBase2 instanceof Record)) {
                seriesTextRecord = null;
                hSSFSeries = hSSFSeries3;
            } else {
                seriesTextRecord = ((Record) recordBase2).copy();
                hSSFSeries = hSSFSeries3;
            }
            if (seriesTextRecord != null) {
                arrayList2.add(seriesTextRecord);
            }
            hSSFSeries2 = hSSFSeries4;
            hSSFSeries3 = hSSFSeries;
        }
        HSSFSeries hSSFSeries5 = hSSFSeries2;
        if (hSSFSeries3 == null) {
            return hSSFSeries5;
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            records.add(i8, (RecordBase) it2.next());
            i8++;
        }
        return hSSFSeries3;
    }

    public boolean removeSeries(HSSFSeries remSeries) {
        int deep = 0;
        int chartDeep = -1;
        int lastSeriesDeep = -1;
        int seriesIdx = -1;
        boolean removeSeries = false;
        boolean chartEntered = false;
        boolean result = false;
        List<RecordBase> records = this.sheet.getSheet().getRecords();
        Iterator<RecordBase> iter = records.iterator();
        while (iter.hasNext()) {
            RecordBase record = iter.next();
            if (record instanceof BeginRecord) {
                deep++;
            } else if (record instanceof EndRecord) {
                deep--;
                if (lastSeriesDeep == deep) {
                    lastSeriesDeep = -1;
                    if (removeSeries) {
                        removeSeries = false;
                        result = true;
                        iter.remove();
                    }
                }
                if (chartDeep == deep) {
                    break;
                }
            }
            if (record instanceof ChartRecord) {
                if (record == this.chartRecord) {
                    chartDeep = deep;
                    chartEntered = true;
                }
            } else if (record instanceof SeriesRecord) {
                if (chartEntered) {
                    if (remSeries.series == record) {
                        lastSeriesDeep = deep;
                        removeSeries = true;
                    } else {
                        seriesIdx++;
                    }
                }
            } else if ((record instanceof DataFormatRecord) && chartEntered && !removeSeries) {
                DataFormatRecord dataFormatRecord = (DataFormatRecord) record;
                dataFormatRecord.setSeriesIndex((short) seriesIdx);
                dataFormatRecord.setSeriesNumber((short) seriesIdx);
            }
            if (removeSeries) {
                iter.remove();
            }
        }
        return result;
    }

    public HSSFChartType getType() {
        return this.type;
    }
}
