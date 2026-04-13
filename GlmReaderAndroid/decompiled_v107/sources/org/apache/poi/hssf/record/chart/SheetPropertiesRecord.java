package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SheetPropertiesRecord extends StandardRecord {
    public static final byte EMPTY_INTERPOLATED = 2;
    public static final byte EMPTY_NOT_PLOTTED = 0;
    public static final byte EMPTY_ZERO = 1;
    public static final short sid = 4164;
    private int field_1_flags;
    private int field_2_empty;
    private static final BitField chartTypeManuallyFormatted = BitFieldFactory.getInstance(1);
    private static final BitField plotVisibleOnly = BitFieldFactory.getInstance(2);
    private static final BitField doNotSizeWithWindow = BitFieldFactory.getInstance(4);
    private static final BitField defaultPlotDimensions = BitFieldFactory.getInstance(8);
    private static final BitField autoPlotArea = BitFieldFactory.getInstance(16);

    public SheetPropertiesRecord() {
    }

    public SheetPropertiesRecord(SheetPropertiesRecord other) {
        super(other);
        this.field_1_flags = other.field_1_flags;
        this.field_2_empty = other.field_2_empty;
    }

    public SheetPropertiesRecord(RecordInputStream in) {
        this.field_1_flags = in.readUShort();
        this.field_2_empty = in.readUShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_flags);
        out.writeShort(this.field_2_empty);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SheetPropertiesRecord copy() {
        return new SheetPropertiesRecord(this);
    }

    public int getFlags() {
        return this.field_1_flags;
    }

    public int getEmpty() {
        return this.field_2_empty;
    }

    public void setEmpty(byte empty) {
        this.field_2_empty = empty;
    }

    public void setChartTypeManuallyFormatted(boolean value) {
        this.field_1_flags = chartTypeManuallyFormatted.setBoolean(this.field_1_flags, value);
    }

    public boolean isChartTypeManuallyFormatted() {
        return chartTypeManuallyFormatted.isSet(this.field_1_flags);
    }

    public void setPlotVisibleOnly(boolean value) {
        this.field_1_flags = plotVisibleOnly.setBoolean(this.field_1_flags, value);
    }

    public boolean isPlotVisibleOnly() {
        return plotVisibleOnly.isSet(this.field_1_flags);
    }

    public void setDoNotSizeWithWindow(boolean value) {
        this.field_1_flags = doNotSizeWithWindow.setBoolean(this.field_1_flags, value);
    }

    public boolean isDoNotSizeWithWindow() {
        return doNotSizeWithWindow.isSet(this.field_1_flags);
    }

    public void setDefaultPlotDimensions(boolean value) {
        this.field_1_flags = defaultPlotDimensions.setBoolean(this.field_1_flags, value);
    }

    public boolean isDefaultPlotDimensions() {
        return defaultPlotDimensions.isSet(this.field_1_flags);
    }

    public void setAutoPlotArea(boolean value) {
        this.field_1_flags = autoPlotArea.setBoolean(this.field_1_flags, value);
    }

    public boolean isAutoPlotArea() {
        return autoPlotArea.isSet(this.field_1_flags);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SHEET_PROPERTIES;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.chart.SheetPropertiesRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SheetPropertiesRecord.this.getFlags());
            }
        }, new BitField[]{chartTypeManuallyFormatted, plotVisibleOnly, doNotSizeWithWindow, defaultPlotDimensions, autoPlotArea}, new String[]{"CHART_TYPE_MANUALLY_FORMATTED", "PLOT_VISIBLE_ONLY", "DO_NOT_SIZE_WITH_WINDOW", "DEFAULT_PLOT_DIMENSIONS", "AUTO_PLOT_AREA"}), "empty", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.chart.SheetPropertiesRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SheetPropertiesRecord.this.getEmpty());
            }
        }, new int[]{0, 1, 2}, new String[]{"EMPTY_NOT_PLOTTED", "EMPTY_ZERO", "EMPTY_INTERPOLATED"}));
    }
}
