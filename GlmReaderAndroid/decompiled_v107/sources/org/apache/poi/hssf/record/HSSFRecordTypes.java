package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.hssf.record.chart.AreaFormatRecord;
import org.apache.poi.hssf.record.chart.AreaRecord;
import org.apache.poi.hssf.record.chart.AxisLineFormatRecord;
import org.apache.poi.hssf.record.chart.AxisOptionsRecord;
import org.apache.poi.hssf.record.chart.AxisParentRecord;
import org.apache.poi.hssf.record.chart.AxisRecord;
import org.apache.poi.hssf.record.chart.AxisUsedRecord;
import org.apache.poi.hssf.record.chart.BarRecord;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.record.chart.CatLabRecord;
import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.apache.poi.hssf.record.chart.ChartEndBlockRecord;
import org.apache.poi.hssf.record.chart.ChartEndObjectRecord;
import org.apache.poi.hssf.record.chart.ChartFRTInfoRecord;
import org.apache.poi.hssf.record.chart.ChartFormatRecord;
import org.apache.poi.hssf.record.chart.ChartRecord;
import org.apache.poi.hssf.record.chart.ChartStartBlockRecord;
import org.apache.poi.hssf.record.chart.ChartStartObjectRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.hssf.record.chart.DatRecord;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.record.chart.DataLabelExtensionRecord;
import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;
import org.apache.poi.hssf.record.chart.EndRecord;
import org.apache.poi.hssf.record.chart.FontBasisRecord;
import org.apache.poi.hssf.record.chart.FontIndexRecord;
import org.apache.poi.hssf.record.chart.FrameRecord;
import org.apache.poi.hssf.record.chart.LegendRecord;
import org.apache.poi.hssf.record.chart.LineFormatRecord;
import org.apache.poi.hssf.record.chart.LinkedDataRecord;
import org.apache.poi.hssf.record.chart.NumberFormatIndexRecord;
import org.apache.poi.hssf.record.chart.ObjectLinkRecord;
import org.apache.poi.hssf.record.chart.PlotAreaRecord;
import org.apache.poi.hssf.record.chart.PlotGrowthRecord;
import org.apache.poi.hssf.record.chart.SeriesChartGroupIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesLabelsRecord;
import org.apache.poi.hssf.record.chart.SeriesListRecord;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.apache.poi.hssf.record.chart.SeriesTextRecord;
import org.apache.poi.hssf.record.chart.SheetPropertiesRecord;
import org.apache.poi.hssf.record.chart.TextRecord;
import org.apache.poi.hssf.record.chart.TickRecord;
import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.apache.poi.hssf.record.chart.ValueRangeRecord;
import org.apache.poi.hssf.record.pivottable.DataItemRecord;
import org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;
import org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord;
import org.apache.poi.hssf.record.pivottable.ViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.ViewSourceRecord;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;

/* loaded from: classes10.dex */
public enum HSSFRecordTypes {
    UNKNOWN(-1, UnknownRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda0
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UnknownRecord(recordInputStream);
        }
    }, false),
    FORMULA(6, FormulaRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda149
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FormulaRecord(recordInputStream);
        }
    }),
    EOF(10, EOFRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda161
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new EOFRecord(recordInputStream);
        }
    }),
    CALC_COUNT(12, CalcCountRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda4
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CalcCountRecord(recordInputStream);
        }
    }),
    CALC_MODE(13, CalcModeRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda16
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CalcModeRecord(recordInputStream);
        }
    }),
    PRECISION(14, PrecisionRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda28
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PrecisionRecord(recordInputStream);
        }
    }),
    REF_MODE(15, RefModeRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda40
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RefModeRecord(recordInputStream);
        }
    }),
    DELTA(16, DeltaRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda52
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DeltaRecord(recordInputStream);
        }
    }),
    ITERATION(17, IterationRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda64
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new IterationRecord(recordInputStream);
        }
    }),
    PROTECT(18, ProtectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda76
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ProtectRecord(recordInputStream);
        }
    }),
    PASSWORD(19, PasswordRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda22
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PasswordRecord(recordInputStream);
        }
    }),
    HEADER(20, HeaderRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda84
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HeaderRecord(recordInputStream);
        }
    }),
    FOOTER(21, FooterRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda96
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FooterRecord(recordInputStream);
        }
    }),
    EXTERN_SHEET(23, ExternSheetRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda108
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ExternSheetRecord(recordInputStream);
        }
    }),
    NAME(24, NameRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda120
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new NameRecord(recordInputStream);
        }
    }),
    WINDOW_PROTECT(25, WindowProtectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda132
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WindowProtectRecord(recordInputStream);
        }
    }),
    VERTICAL_PAGE_BREAK(26, VerticalPageBreakRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda144
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new VerticalPageBreakRecord(recordInputStream);
        }
    }),
    HORIZONTAL_PAGE_BREAK(27, HorizontalPageBreakRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda145
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HorizontalPageBreakRecord(recordInputStream);
        }
    }),
    NOTE(28, NoteRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda146
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new NoteRecord(recordInputStream);
        }
    }),
    SELECTION(29, SelectionRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda148
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SelectionRecord(recordInputStream);
        }
    }),
    DATE_WINDOW_1904(34, DateWindow1904Record.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda150
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DateWindow1904Record(recordInputStream);
        }
    }),
    EXTERNAL_NAME(35, ExternalNameRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda151
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ExternalNameRecord(recordInputStream);
        }
    }),
    LEFT_MARGIN(38, LeftMarginRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda152
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LeftMarginRecord(recordInputStream);
        }
    }),
    RIGHT_MARGIN(39, RightMarginRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda153
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RightMarginRecord(recordInputStream);
        }
    }),
    TOP_MARGIN(40, TopMarginRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda154
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TopMarginRecord(recordInputStream);
        }
    }),
    BOTTOM_MARGIN(41, BottomMarginRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda155
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BottomMarginRecord(recordInputStream);
        }
    }),
    PRINT_HEADERS(42, PrintHeadersRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda156
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PrintHeadersRecord(recordInputStream);
        }
    }),
    PRINT_GRIDLINES(43, PrintGridlinesRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda157
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PrintGridlinesRecord(recordInputStream);
        }
    }),
    FILE_PASS(47, FilePassRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda159
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FilePassRecord(recordInputStream);
        }
    }),
    FONT(49, FontRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda160
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FontRecord(recordInputStream);
        }
    }),
    CONTINUE(60, ContinueRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda162
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ContinueRecord(recordInputStream);
        }
    }),
    WINDOW_ONE(61, WindowOneRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda163
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WindowOneRecord(recordInputStream);
        }
    }),
    BACKUP(64, BackupRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda164
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BackupRecord(recordInputStream);
        }
    }),
    PANE(65, PaneRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda165
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PaneRecord(recordInputStream);
        }
    }),
    CODEPAGE(66, CodepageRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda166
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CodepageRecord(recordInputStream);
        }
    }),
    DCON_REF(81, DConRefRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda167
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DConRefRecord(recordInputStream);
        }
    }),
    DEFAULT_COL_WIDTH(85, DefaultColWidthRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda168
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DefaultColWidthRecord(recordInputStream);
        }
    }),
    CRN_COUNT(89, CRNCountRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda1
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CRNCountRecord(recordInputStream);
        }
    }),
    CRN(90, CRNRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda2
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CRNRecord(recordInputStream);
        }
    }),
    WRITE_ACCESS(92, WriteAccessRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda3
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WriteAccessRecord(recordInputStream);
        }
    }),
    FILE_SHARING(91, FileSharingRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda5
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FileSharingRecord(recordInputStream);
        }
    }),
    OBJ(93, ObjRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda6
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ObjRecord(recordInputStream);
        }
    }),
    UNCALCED(94, UncalcedRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda7
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UncalcedRecord(recordInputStream);
        }
    }),
    SAVE_RECALC(95, SaveRecalcRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda8
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SaveRecalcRecord(recordInputStream);
        }
    }),
    OBJECT_PROTECT(99, ObjectProtectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda9
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ObjectProtectRecord(recordInputStream);
        }
    }),
    COLUMN_INFO(125, ColumnInfoRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda10
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ColumnInfoRecord(recordInputStream);
        }
    }),
    GUTS(128, GutsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda12
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new GutsRecord(recordInputStream);
        }
    }),
    WS_BOOL(129, WSBoolRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda13
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WSBoolRecord(recordInputStream);
        }
    }),
    GRIDSET(130, GridsetRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda14
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new GridsetRecord(recordInputStream);
        }
    }),
    H_CENTER(131, HCenterRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda15
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HCenterRecord(recordInputStream);
        }
    }),
    V_CENTER(132, VCenterRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda17
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new VCenterRecord(recordInputStream);
        }
    }),
    BOUND_SHEET(133, BoundSheetRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda18
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BoundSheetRecord(recordInputStream);
        }
    }),
    WRITE_PROTECT(134, WriteProtectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda19
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WriteProtectRecord(recordInputStream);
        }
    }),
    COUNTRY(140, CountryRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda20
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CountryRecord(recordInputStream);
        }
    }),
    HIDE_OBJ(141, HideObjRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda21
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HideObjRecord(recordInputStream);
        }
    }),
    PALETTE(146, PaletteRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda23
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PaletteRecord(recordInputStream);
        }
    }),
    FN_GROUP_COUNT(156, FnGroupCountRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda24
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FnGroupCountRecord(recordInputStream);
        }
    }),
    AUTO_FILTER_INFO(157, AutoFilterInfoRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda25
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AutoFilterInfoRecord(recordInputStream);
        }
    }),
    SCL(160, SCLRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda26
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SCLRecord(recordInputStream);
        }
    }, false),
    PRINT_SETUP(161, PrintSetupRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda27
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PrintSetupRecord(recordInputStream);
        }
    }),
    VIEW_DEFINITION(176, ViewDefinitionRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda29
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ViewDefinitionRecord(recordInputStream);
        }
    }),
    VIEW_FIELDS(177, ViewFieldsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda30
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ViewFieldsRecord(recordInputStream);
        }
    }),
    PAGE_ITEM(182, PageItemRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda31
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PageItemRecord(recordInputStream);
        }
    }),
    MUL_BLANK(190, MulBlankRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda32
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new MulBlankRecord(recordInputStream);
        }
    }),
    MUL_RK(189, MulRKRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda34
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new MulRKRecord(recordInputStream);
        }
    }),
    MMS(193, MMSRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda35
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new MMSRecord(recordInputStream);
        }
    }),
    DATA_ITEM(HSSFShapeTypes.ActionButtonReturn, DataItemRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda36
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DataItemRecord(recordInputStream);
        }
    }),
    STREAM_ID(213, StreamIDRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda37
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new StreamIDRecord(recordInputStream);
        }
    }),
    DB_CELL(215, DBCellRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda38
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DBCellRecord(recordInputStream);
        }
    }),
    BOOK_BOOL(218, BookBoolRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda39
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BookBoolRecord(recordInputStream);
        }
    }),
    SCENARIO_PROTECT(221, ScenarioProtectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda41
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ScenarioProtectRecord(recordInputStream);
        }
    }),
    EXTENDED_FORMAT(224, ExtendedFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda42
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ExtendedFormatRecord(recordInputStream);
        }
    }),
    INTERFACE_HDR(225, InterfaceHdrRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda43
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new InterfaceHdrRecord(recordInputStream);
        }
    }),
    INTERFACE_END(226, InterfaceEndRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda45
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return InterfaceEndRecord.create(recordInputStream);
        }
    }),
    VIEW_SOURCE(227, ViewSourceRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda46
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ViewSourceRecord(recordInputStream);
        }
    }),
    MERGE_CELLS(229, MergeCellsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda47
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new MergeCellsRecord(recordInputStream);
        }
    }),
    DRAWING_GROUP(235, DrawingGroupRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda48
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DrawingGroupRecord(recordInputStream);
        }
    }),
    DRAWING(236, DrawingRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda49
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DrawingRecord(recordInputStream);
        }
    }),
    DRAWING_SELECTION(237, DrawingSelectionRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda50
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DrawingSelectionRecord(recordInputStream);
        }
    }),
    SST(252, SSTRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda51
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SSTRecord(recordInputStream);
        }
    }),
    LABEL_SST(253, LabelSSTRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda53
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LabelSSTRecord(recordInputStream);
        }
    }),
    EXT_SST(255, ExtSSTRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda54
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ExtSSTRecord(recordInputStream);
        }
    }),
    EXTENDED_PIVOT_TABLE_VIEW_FIELDS(256, ExtendedPivotTableViewFieldsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda56
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ExtendedPivotTableViewFieldsRecord(recordInputStream);
        }
    }),
    TAB_ID(TypedValues.AttributesType.TYPE_EASING, TabIdRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda57
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TabIdRecord(recordInputStream);
        }
    }),
    USE_SEL_FS(352, UseSelFSRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda58
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UseSelFSRecord(recordInputStream);
        }
    }),
    DSF(353, DSFRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda59
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DSFRecord(recordInputStream);
        }
    }),
    USER_SVIEW_BEGIN(426, UserSViewBegin.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda60
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UserSViewBegin(recordInputStream);
        }
    }),
    USER_SVIEW_END(427, UserSViewEnd.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda61
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UserSViewEnd(recordInputStream);
        }
    }),
    SUP_BOOK(430, SupBookRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda62
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SupBookRecord(recordInputStream);
        }
    }),
    PROTECTION_REV_4(431, ProtectionRev4Record.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda63
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ProtectionRev4Record(recordInputStream);
        }
    }),
    CF_HEADER(432, CFHeaderRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda65
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CFHeaderRecord(recordInputStream);
        }
    }),
    CF_RULE(433, CFRuleRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda67
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CFRuleRecord(recordInputStream);
        }
    }),
    DVAL(434, DVALRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda68
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DVALRecord(recordInputStream);
        }
    }),
    TEXT_OBJECT(438, TextObjectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda69
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TextObjectRecord(recordInputStream);
        }
    }),
    REFRESH_ALL(439, RefreshAllRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda70
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RefreshAllRecord(recordInputStream);
        }
    }),
    HYPERLINK(440, HyperlinkRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda71
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HyperlinkRecord(recordInputStream);
        }
    }),
    PASSWORD_REV_4(444, PasswordRev4Record.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda72
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PasswordRev4Record(recordInputStream);
        }
    }),
    DV(446, DVRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda73
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DVRecord(recordInputStream);
        }
    }),
    RECALC_ID(449, RecalcIdRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda74
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RecalcIdRecord(recordInputStream);
        }
    }),
    DIMENSIONS(512, DimensionsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda75
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DimensionsRecord(recordInputStream);
        }
    }),
    BLANK(InputDeviceCompat.SOURCE_DPAD, BlankRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda81
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BlankRecord(recordInputStream);
        }
    }),
    NUMBER(515, NumberRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda92
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new NumberRecord(recordInputStream);
        }
    }),
    LABEL(516, LabelRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda103
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LabelRecord(recordInputStream);
        }
    }),
    BOOL_ERR(517, BoolErrRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda114
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BoolErrRecord(recordInputStream);
        }
    }),
    STRING(519, StringRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda125
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new StringRecord(recordInputStream);
        }
    }),
    ROW(520, RowRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda136
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RowRecord(recordInputStream);
        }
    }),
    INDEX(523, IndexRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda147
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new IndexRecord(recordInputStream);
        }
    }),
    ARRAY(545, ArrayRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda158
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ArrayRecord(recordInputStream);
        }
    }),
    DEFAULT_ROW_HEIGHT(549, DefaultRowHeightRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda169
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DefaultRowHeightRecord(recordInputStream);
        }
    }),
    TABLE(566, TableRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda11
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TableRecord(recordInputStream);
        }
    }),
    WINDOW_TWO(574, WindowTwoRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda33
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new WindowTwoRecord(recordInputStream);
        }
    }),
    RK(638, RKRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda44
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new RKRecord(recordInputStream);
        }
    }),
    STYLE(659, StyleRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda55
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new StyleRecord(recordInputStream);
        }
    }),
    FORMAT(1054, FormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda66
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FormatRecord(recordInputStream);
        }
    }),
    SHARED_FORMULA(1212, SharedFormulaRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda77
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SharedFormulaRecord(recordInputStream);
        }
    }),
    BOF(2057, BOFRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda78
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BOFRecord(recordInputStream);
        }
    }),
    CHART_FRT_INFO(2128, ChartFRTInfoRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda79
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartFRTInfoRecord(recordInputStream);
        }
    }),
    CHART_START_BLOCK(2130, ChartStartBlockRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda80
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartStartBlockRecord(recordInputStream);
        }
    }),
    CHART_END_BLOCK(2131, ChartEndBlockRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda82
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartEndBlockRecord(recordInputStream);
        }
    }),
    CHART_START_OBJECT(2132, ChartStartObjectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda83
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartStartObjectRecord(recordInputStream);
        }
    }),
    CHART_END_OBJECT(2133, ChartEndObjectRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda85
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartEndObjectRecord(recordInputStream);
        }
    }),
    CAT_LAB(2134, CatLabRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda86
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CatLabRecord(recordInputStream);
        }
    }),
    FEAT_HDR(UnknownRecord.SHEETPROTECTION_0867, FeatHdrRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda87
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FeatHdrRecord(recordInputStream);
        }
    }),
    FEAT(2152, FeatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda88
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FeatRecord(recordInputStream);
        }
    }),
    DATA_LABEL_EXTENSION(2154, DataLabelExtensionRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda89
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DataLabelExtensionRecord(recordInputStream);
        }
    }, false),
    CF_HEADER_12(2169, CFHeader12Record.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda90
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CFHeader12Record(recordInputStream);
        }
    }),
    CF_RULE_12(2170, CFRule12Record.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda91
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CFRule12Record(recordInputStream);
        }
    }),
    TABLE_STYLES(2190, TableStylesRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda93
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TableStylesRecord(recordInputStream);
        }
    }),
    NAME_COMMENT(2196, NameCommentRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda94
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new NameCommentRecord(recordInputStream);
        }
    }),
    HEADER_FOOTER(UnknownRecord.HEADER_FOOTER_089C, HeaderFooterRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda95
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new HeaderFooterRecord(recordInputStream);
        }
    }),
    UNITS(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, UnitsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda97
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new UnitsRecord(recordInputStream);
        }
    }, false),
    CHART(InputDeviceCompat.SOURCE_TOUCHSCREEN, ChartRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda98
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartRecord(recordInputStream);
        }
    }),
    SERIES(FragmentTransaction.TRANSIT_FRAGMENT_FADE, SeriesRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda99
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesRecord(recordInputStream);
        }
    }),
    DATA_FORMAT(4102, DataFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda100
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DataFormatRecord(recordInputStream);
        }
    }),
    LINE_FORMAT(4103, LineFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda101
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LineFormatRecord(recordInputStream);
        }
    }, false),
    AREA_FORMAT(4106, AreaFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda102
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AreaFormatRecord(recordInputStream);
        }
    }, false),
    SERIES_LABELS(4108, SeriesLabelsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda104
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesLabelsRecord(recordInputStream);
        }
    }, false),
    SERIES_TEXT(4109, SeriesTextRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda105
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesTextRecord(recordInputStream);
        }
    }),
    CHART_FORMAT(4116, ChartFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda106
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartFormatRecord(recordInputStream);
        }
    }, false),
    LEGEND(4117, LegendRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda107
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LegendRecord(recordInputStream);
        }
    }),
    SERIES_LIST(4118, SeriesListRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda109
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesListRecord(recordInputStream);
        }
    }, false),
    BAR(4119, BarRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda110
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BarRecord(recordInputStream);
        }
    }, false),
    AREA(4122, AreaRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda111
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AreaRecord(recordInputStream);
        }
    }),
    AXIS(4125, AxisRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda112
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AxisRecord(recordInputStream);
        }
    }, false),
    TICK(4126, TickRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda113
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TickRecord(recordInputStream);
        }
    }, false),
    VALUE_RANGE(4127, ValueRangeRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda115
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ValueRangeRecord(recordInputStream);
        }
    }),
    CATEGORY_SERIES_AXIS(4128, CategorySeriesAxisRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda116
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new CategorySeriesAxisRecord(recordInputStream);
        }
    }, false),
    AXIS_LINE_FORMAT(4129, AxisLineFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda117
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AxisLineFormatRecord(recordInputStream);
        }
    }, false),
    DEFAULT_DATA_LABEL_TEXT_PROPERTIES(4132, DefaultDataLabelTextPropertiesRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda118
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DefaultDataLabelTextPropertiesRecord(recordInputStream);
        }
    }, false),
    TEXT(4133, TextRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda119
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new TextRecord(recordInputStream);
        }
    }, false),
    FONT_INDEX(4134, FontIndexRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda121
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FontIndexRecord(recordInputStream);
        }
    }, false),
    OBJECT_LINK(4135, ObjectLinkRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda122
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ObjectLinkRecord(recordInputStream);
        }
    }, false),
    FRAME(4146, FrameRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda123
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FrameRecord(recordInputStream);
        }
    }, false),
    BEGIN(4147, BeginRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda124
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new BeginRecord(recordInputStream);
        }
    }),
    END(4148, EndRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda126
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new EndRecord(recordInputStream);
        }
    }),
    PLOT_AREA(4149, PlotAreaRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda127
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PlotAreaRecord(recordInputStream);
        }
    }, false),
    AXIS_PARENT(4161, AxisParentRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda128
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AxisParentRecord(recordInputStream);
        }
    }, false),
    SHEET_PROPERTIES(4164, SheetPropertiesRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda129
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SheetPropertiesRecord(recordInputStream);
        }
    }, false),
    SERIES_CHART_GROUP_INDEX(4165, SeriesChartGroupIndexRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda130
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesChartGroupIndexRecord(recordInputStream);
        }
    }),
    AXIS_USED(4166, AxisUsedRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda131
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AxisUsedRecord(recordInputStream);
        }
    }, false),
    NUMBER_FORMAT_INDEX(4174, NumberFormatIndexRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda133
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new NumberFormatIndexRecord(recordInputStream);
        }
    }, false),
    CHART_TITLE_FORMAT(4176, ChartTitleFormatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda134
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new ChartTitleFormatRecord(recordInputStream);
        }
    }),
    LINKED_DATA(4177, LinkedDataRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda135
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new LinkedDataRecord(recordInputStream);
        }
    }),
    FONT_BASIS(4192, FontBasisRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda137
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new FontBasisRecord(recordInputStream);
        }
    }, false),
    AXIS_OPTIONS(4194, AxisOptionsRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda138
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new AxisOptionsRecord(recordInputStream);
        }
    }, false),
    DAT(4195, DatRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda139
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new DatRecord(recordInputStream);
        }
    }, false),
    PLOT_GROWTH(4196, PlotGrowthRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda140
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new PlotGrowthRecord(recordInputStream);
        }
    }, false),
    SERIES_INDEX(4197, SeriesIndexRecord.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda141
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return new SeriesIndexRecord(recordInputStream);
        }
    }, false),
    ESCHER_AGGREGATE(9876, EscherAggregate.class, new RecordConstructor() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda142
        @Override // org.apache.poi.hssf.record.HSSFRecordTypes.RecordConstructor
        public final Record apply(RecordInputStream recordInputStream) {
            return HSSFRecordTypes.lambda$static$0(recordInputStream);
        }
    });

    private static final Map<Short, HSSFRecordTypes> LOOKUP = Collections.unmodifiableMap((Map) Arrays.stream(values()).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.hssf.record.HSSFRecordTypes$$ExternalSyntheticLambda143
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Short.valueOf(((HSSFRecordTypes) obj).getSid());
        }
    }, Function.identity())));
    public final Class<? extends Record> clazz;
    public final boolean parse;
    public final RecordConstructor<? extends Record> recordConstructor;
    public final short sid;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface RecordConstructor<T extends Record> {
        T apply(RecordInputStream recordInputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Record lambda$static$0(RecordInputStream in) {
        return new EscherAggregate(true);
    }

    HSSFRecordTypes(int sid, Class cls, RecordConstructor recordConstructor) {
        this(sid, cls, recordConstructor, true);
    }

    HSSFRecordTypes(int sid, Class cls, RecordConstructor recordConstructor, boolean parse) {
        this.sid = (short) sid;
        this.clazz = cls;
        this.recordConstructor = recordConstructor;
        this.parse = parse;
    }

    public static HSSFRecordTypes forSID(int sid) {
        return LOOKUP.getOrDefault(Short.valueOf((short) sid), UNKNOWN);
    }

    public short getSid() {
        return this.sid;
    }

    public Class<? extends Record> getClazz() {
        return this.clazz;
    }

    public RecordConstructor<? extends Record> getRecordConstructor() {
        return this.recordConstructor;
    }

    public boolean isParseable() {
        return this.parse;
    }
}
