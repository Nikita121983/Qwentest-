package org.apache.poi.hssf.record;

import androidx.core.view.MotionEventCompat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.compress.archivers.cpio.CpioConstants;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ExtendedFormatRecord extends StandardRecord {
    public static final short ALT_BARS = 3;
    public static final short BIG_SPOTS = 9;
    public static final short BRICKS = 10;
    public static final short CENTER = 2;
    public static final short CENTER_SELECTION = 6;
    public static final short DASHED = 3;
    public static final short DASH_DOT = 9;
    public static final short DASH_DOT_DOT = 11;
    public static final short DIAMONDS = 16;
    public static final short DOTTED = 4;
    public static final short DOUBLE = 6;
    public static final short FILL = 4;
    public static final short FINE_DOTS = 2;
    public static final short GENERAL = 0;
    public static final short HAIR = 7;
    public static final short JUSTIFY = 5;
    public static final short LEFT = 1;
    public static final short MEDIUM = 2;
    public static final short MEDIUM_DASHED = 8;
    public static final short MEDIUM_DASH_DOT = 10;
    public static final short MEDIUM_DASH_DOT_DOT = 12;
    public static final short NONE = 0;
    public static final short NO_FILL = 0;
    public static final short NULL = -16;
    public static final short RIGHT = 3;
    public static final short SLANTED_DASH_DOT = 13;
    public static final short SOLID_FILL = 1;
    public static final short SPARSE_DOTS = 4;
    public static final short SQUARES = 15;
    public static final short THICK = 5;
    public static final short THICK_BACKWARD_DIAG = 7;
    public static final short THICK_FORWARD_DIAG = 8;
    public static final short THICK_HORZ_BANDS = 5;
    public static final short THICK_VERT_BANDS = 6;
    public static final short THIN = 1;
    public static final short THIN_BACKWARD_DIAG = 13;
    public static final short THIN_FORWARD_DIAG = 14;
    public static final short THIN_HORZ_BANDS = 11;
    public static final short THIN_VERT_BANDS = 12;
    public static final short VERTICAL_BOTTOM = 2;
    public static final short VERTICAL_CENTER = 1;
    public static final short VERTICAL_JUSTIFY = 3;
    public static final short VERTICAL_TOP = 0;
    public static final short XF_CELL = 0;
    public static final short XF_STYLE = 1;
    public static final short sid = 224;
    private short field_1_font_index;
    private short field_2_format_index;
    private short field_3_cell_options;
    private short field_4_alignment_options;
    private short field_5_indention_options;
    private short field_6_border_options;
    private short field_7_palette_options;
    private int field_8_adtl_palette_options;
    private short field_9_fill_palette_options;
    private static final BitField _locked = bf(1);
    private static final BitField _hidden = bf(2);
    private static final BitField _xf_type = bf(4);
    private static final BitField _123_prefix = bf(8);
    private static final BitField _parent_index = bf(65520);
    private static final BitField _alignment = bf(7);
    private static final BitField _wrap_text = bf(8);
    private static final BitField _vertical_alignment = bf(112);
    private static final BitField _justify_last = bf(128);
    private static final BitField _rotation = bf(MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    private static final BitField _indent = bf(15);
    private static final BitField _shrink_to_fit = bf(16);
    private static final BitField _merge_cells = bf(32);
    private static final BitField _reading_order = bf(192);
    private static final BitField _indent_not_parent_format = bf(1024);
    private static final BitField _indent_not_parent_font = bf(2048);
    private static final BitField _indent_not_parent_alignment = bf(4096);
    private static final BitField _indent_not_parent_border = bf(8192);
    private static final BitField _indent_not_parent_pattern = bf(16384);
    private static final BitField _indent_not_parent_cell_options = bf(32768);
    private static final BitField _border_left = bf(15);
    private static final BitField _border_right = bf(240);
    private static final BitField _border_top = bf(3840);
    private static final BitField _border_bottom = bf(61440);
    private static final BitField _left_border_palette_idx = bf(127);
    private static final BitField _right_border_palette_idx = bf(16256);
    private static final BitField _diag = bf(CpioConstants.C_ISSOCK);
    private static final BitField _top_border_palette_idx = bf(127);
    private static final BitField _bottom_border_palette_idx = bf(16256);
    private static final BitField _adtl_diag = bf(2080768);
    private static final BitField _adtl_diag_line_style = bf(31457280);
    private static final BitField _adtl_fill_pattern = bf(-67108864);
    private static final BitField _fill_foreground = bf(127);
    private static final BitField _fill_background = bf(16256);

    private static BitField bf(int i) {
        return BitFieldFactory.getInstance(i);
    }

    public ExtendedFormatRecord() {
    }

    public ExtendedFormatRecord(ExtendedFormatRecord other) {
        super(other);
        this.field_1_font_index = other.field_1_font_index;
        this.field_2_format_index = other.field_2_format_index;
        this.field_3_cell_options = other.field_3_cell_options;
        this.field_4_alignment_options = other.field_4_alignment_options;
        this.field_5_indention_options = other.field_5_indention_options;
        this.field_6_border_options = other.field_6_border_options;
        this.field_7_palette_options = other.field_7_palette_options;
        this.field_8_adtl_palette_options = other.field_8_adtl_palette_options;
        this.field_9_fill_palette_options = other.field_9_fill_palette_options;
    }

    public ExtendedFormatRecord(RecordInputStream in) {
        this.field_1_font_index = in.readShort();
        this.field_2_format_index = in.readShort();
        this.field_3_cell_options = in.readShort();
        this.field_4_alignment_options = in.readShort();
        this.field_5_indention_options = in.readShort();
        this.field_6_border_options = in.readShort();
        this.field_7_palette_options = in.readShort();
        this.field_8_adtl_palette_options = in.readInt();
        this.field_9_fill_palette_options = in.readShort();
    }

    public void setFontIndex(short index) {
        this.field_1_font_index = index;
    }

    public void setFormatIndex(short index) {
        this.field_2_format_index = index;
    }

    public void setCellOptions(short options) {
        this.field_3_cell_options = options;
    }

    public void setLocked(boolean locked) {
        this.field_3_cell_options = _locked.setShortBoolean(this.field_3_cell_options, locked);
    }

    public void setHidden(boolean hidden) {
        this.field_3_cell_options = _hidden.setShortBoolean(this.field_3_cell_options, hidden);
    }

    public void setXFType(short type) {
        this.field_3_cell_options = _xf_type.setShortValue(this.field_3_cell_options, type);
    }

    public void set123Prefix(boolean prefix) {
        this.field_3_cell_options = _123_prefix.setShortBoolean(this.field_3_cell_options, prefix);
    }

    public void setParentIndex(short parent) {
        this.field_3_cell_options = _parent_index.setShortValue(this.field_3_cell_options, parent);
    }

    public void setAlignmentOptions(short options) {
        this.field_4_alignment_options = options;
    }

    public void setAlignment(short align) {
        this.field_4_alignment_options = _alignment.setShortValue(this.field_4_alignment_options, align);
    }

    public void setWrapText(boolean wrapped) {
        this.field_4_alignment_options = _wrap_text.setShortBoolean(this.field_4_alignment_options, wrapped);
    }

    public void setVerticalAlignment(short align) {
        this.field_4_alignment_options = _vertical_alignment.setShortValue(this.field_4_alignment_options, align);
    }

    public void setJustifyLast(short justify) {
        this.field_4_alignment_options = _justify_last.setShortValue(this.field_4_alignment_options, justify);
    }

    public void setRotation(short rotation) {
        this.field_4_alignment_options = _rotation.setShortValue(this.field_4_alignment_options, rotation);
    }

    public void setIndentionOptions(short options) {
        this.field_5_indention_options = options;
    }

    public void setIndent(short indent) {
        this.field_5_indention_options = _indent.setShortValue(this.field_5_indention_options, indent);
    }

    public void setShrinkToFit(boolean shrink) {
        this.field_5_indention_options = _shrink_to_fit.setShortBoolean(this.field_5_indention_options, shrink);
    }

    public void setMergeCells(boolean merge) {
        this.field_5_indention_options = _merge_cells.setShortBoolean(this.field_5_indention_options, merge);
    }

    public void setReadingOrder(short order) {
        this.field_5_indention_options = _reading_order.setShortValue(this.field_5_indention_options, order);
    }

    public void setIndentNotParentFormat(boolean parent) {
        this.field_5_indention_options = _indent_not_parent_format.setShortBoolean(this.field_5_indention_options, parent);
    }

    public void setIndentNotParentFont(boolean font) {
        this.field_5_indention_options = _indent_not_parent_font.setShortBoolean(this.field_5_indention_options, font);
    }

    public void setIndentNotParentAlignment(boolean alignment) {
        this.field_5_indention_options = _indent_not_parent_alignment.setShortBoolean(this.field_5_indention_options, alignment);
    }

    public void setIndentNotParentBorder(boolean border) {
        this.field_5_indention_options = _indent_not_parent_border.setShortBoolean(this.field_5_indention_options, border);
    }

    public void setIndentNotParentPattern(boolean pattern) {
        this.field_5_indention_options = _indent_not_parent_pattern.setShortBoolean(this.field_5_indention_options, pattern);
    }

    public void setIndentNotParentCellOptions(boolean options) {
        this.field_5_indention_options = _indent_not_parent_cell_options.setShortBoolean(this.field_5_indention_options, options);
    }

    public void setBorderOptions(short options) {
        this.field_6_border_options = options;
    }

    public void setBorderLeft(short border) {
        this.field_6_border_options = _border_left.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderRight(short border) {
        this.field_6_border_options = _border_right.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderTop(short border) {
        this.field_6_border_options = _border_top.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderBottom(short border) {
        this.field_6_border_options = _border_bottom.setShortValue(this.field_6_border_options, border);
    }

    public void setPaletteOptions(short options) {
        this.field_7_palette_options = options;
    }

    public void setLeftBorderPaletteIdx(short border) {
        this.field_7_palette_options = _left_border_palette_idx.setShortValue(this.field_7_palette_options, border);
    }

    public void setRightBorderPaletteIdx(short border) {
        this.field_7_palette_options = _right_border_palette_idx.setShortValue(this.field_7_palette_options, border);
    }

    public void setDiag(short diag) {
        this.field_7_palette_options = _diag.setShortValue(this.field_7_palette_options, diag);
    }

    public void setAdtlPaletteOptions(short options) {
        this.field_8_adtl_palette_options = options;
    }

    public void setTopBorderPaletteIdx(short border) {
        this.field_8_adtl_palette_options = _top_border_palette_idx.setValue(this.field_8_adtl_palette_options, border);
    }

    public void setBottomBorderPaletteIdx(short border) {
        this.field_8_adtl_palette_options = _bottom_border_palette_idx.setValue(this.field_8_adtl_palette_options, border);
    }

    public void setAdtlDiag(short diag) {
        this.field_8_adtl_palette_options = _adtl_diag.setValue(this.field_8_adtl_palette_options, diag);
    }

    public void setAdtlDiagLineStyle(short diag) {
        this.field_8_adtl_palette_options = _adtl_diag_line_style.setValue(this.field_8_adtl_palette_options, diag);
    }

    public void setAdtlFillPattern(short fill) {
        this.field_8_adtl_palette_options = _adtl_fill_pattern.setValue(this.field_8_adtl_palette_options, fill);
    }

    public void setFillPaletteOptions(short options) {
        this.field_9_fill_palette_options = options;
    }

    public void setFillForeground(short color) {
        this.field_9_fill_palette_options = _fill_foreground.setShortValue(this.field_9_fill_palette_options, color);
    }

    public void setFillBackground(short color) {
        this.field_9_fill_palette_options = _fill_background.setShortValue(this.field_9_fill_palette_options, color);
    }

    public short getFontIndex() {
        return this.field_1_font_index;
    }

    public short getFormatIndex() {
        return this.field_2_format_index;
    }

    public short getCellOptions() {
        return this.field_3_cell_options;
    }

    public boolean isLocked() {
        return _locked.isSet(this.field_3_cell_options);
    }

    public boolean isHidden() {
        return _hidden.isSet(this.field_3_cell_options);
    }

    public short getXFType() {
        return _xf_type.getShortValue(this.field_3_cell_options);
    }

    public boolean get123Prefix() {
        return _123_prefix.isSet(this.field_3_cell_options);
    }

    public short getParentIndex() {
        return _parent_index.getShortValue(this.field_3_cell_options);
    }

    public short getAlignmentOptions() {
        return this.field_4_alignment_options;
    }

    public short getAlignment() {
        return _alignment.getShortValue(this.field_4_alignment_options);
    }

    public boolean getWrapText() {
        return _wrap_text.isSet(this.field_4_alignment_options);
    }

    public short getVerticalAlignment() {
        return _vertical_alignment.getShortValue(this.field_4_alignment_options);
    }

    public short getJustifyLast() {
        return _justify_last.getShortValue(this.field_4_alignment_options);
    }

    public short getRotation() {
        return _rotation.getShortValue(this.field_4_alignment_options);
    }

    public short getIndentionOptions() {
        return this.field_5_indention_options;
    }

    public short getIndent() {
        return _indent.getShortValue(this.field_5_indention_options);
    }

    public boolean getShrinkToFit() {
        return _shrink_to_fit.isSet(this.field_5_indention_options);
    }

    public boolean getMergeCells() {
        return _merge_cells.isSet(this.field_5_indention_options);
    }

    public short getReadingOrder() {
        return _reading_order.getShortValue(this.field_5_indention_options);
    }

    public boolean isIndentNotParentFormat() {
        return _indent_not_parent_format.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentFont() {
        return _indent_not_parent_font.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentAlignment() {
        return _indent_not_parent_alignment.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentBorder() {
        return _indent_not_parent_border.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentPattern() {
        return _indent_not_parent_pattern.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentCellOptions() {
        return _indent_not_parent_cell_options.isSet(this.field_5_indention_options);
    }

    public short getBorderOptions() {
        return this.field_6_border_options;
    }

    public short getBorderLeft() {
        return _border_left.getShortValue(this.field_6_border_options);
    }

    public short getBorderRight() {
        return _border_right.getShortValue(this.field_6_border_options);
    }

    public short getBorderTop() {
        return _border_top.getShortValue(this.field_6_border_options);
    }

    public short getBorderBottom() {
        return _border_bottom.getShortValue(this.field_6_border_options);
    }

    public short getPaletteOptions() {
        return this.field_7_palette_options;
    }

    public short getLeftBorderPaletteIdx() {
        return _left_border_palette_idx.getShortValue(this.field_7_palette_options);
    }

    public short getRightBorderPaletteIdx() {
        return _right_border_palette_idx.getShortValue(this.field_7_palette_options);
    }

    public short getDiag() {
        return _diag.getShortValue(this.field_7_palette_options);
    }

    public int getAdtlPaletteOptions() {
        return this.field_8_adtl_palette_options;
    }

    public short getTopBorderPaletteIdx() {
        return (short) _top_border_palette_idx.getValue(this.field_8_adtl_palette_options);
    }

    public short getBottomBorderPaletteIdx() {
        return (short) _bottom_border_palette_idx.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlDiag() {
        return (short) _adtl_diag.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlDiagLineStyle() {
        return (short) _adtl_diag_line_style.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlFillPattern() {
        return (short) _adtl_fill_pattern.getValue(this.field_8_adtl_palette_options);
    }

    public short getFillPaletteOptions() {
        return this.field_9_fill_palette_options;
    }

    public short getFillForeground() {
        return _fill_foreground.getShortValue(this.field_9_fill_palette_options);
    }

    public short getFillBackground() {
        return _fill_background.getShortValue(this.field_9_fill_palette_options);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getFontIndex());
        out.writeShort(getFormatIndex());
        out.writeShort(getCellOptions());
        out.writeShort(getAlignmentOptions());
        out.writeShort(getIndentionOptions());
        out.writeShort(getBorderOptions());
        out.writeShort(getPaletteOptions());
        out.writeInt(getAdtlPaletteOptions());
        out.writeShort(getFillPaletteOptions());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 20;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public void cloneStyleFrom(ExtendedFormatRecord source) {
        this.field_1_font_index = source.field_1_font_index;
        this.field_2_format_index = source.field_2_format_index;
        this.field_3_cell_options = source.field_3_cell_options;
        this.field_4_alignment_options = source.field_4_alignment_options;
        this.field_5_indention_options = source.field_5_indention_options;
        this.field_6_border_options = source.field_6_border_options;
        this.field_7_palette_options = source.field_7_palette_options;
        this.field_8_adtl_palette_options = source.field_8_adtl_palette_options;
        this.field_9_fill_palette_options = source.field_9_fill_palette_options;
    }

    public int hashCode() {
        return Objects.hash(Short.valueOf(this.field_1_font_index), Short.valueOf(this.field_2_format_index), Short.valueOf(this.field_3_cell_options), Short.valueOf(this.field_4_alignment_options), Short.valueOf(this.field_5_indention_options), Short.valueOf(this.field_6_border_options), Short.valueOf(this.field_7_palette_options), Integer.valueOf(this.field_8_adtl_palette_options), Short.valueOf(this.field_9_fill_palette_options));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExtendedFormatRecord)) {
            return false;
        }
        ExtendedFormatRecord other = (ExtendedFormatRecord) obj;
        return Arrays.equals(stateSummary(), other.stateSummary());
    }

    public int[] stateSummary() {
        return new int[]{this.field_1_font_index, this.field_2_format_index, this.field_3_cell_options, this.field_4_alignment_options, this.field_5_indention_options, this.field_6_border_options, this.field_7_palette_options, this.field_8_adtl_palette_options, this.field_9_fill_palette_options};
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ExtendedFormatRecord copy() {
        return new ExtendedFormatRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTENDED_FORMAT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("xfType", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getXFType());
            }
        }, new int[]{0, 1}, new String[]{"CELL", "STYLE"}));
        m.put("fontIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getFontIndex());
            }
        });
        m.put("formatIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda14
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getFormatIndex());
            }
        });
        m.put("cellOptions", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda15
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getCellOptions());
            }
        }, new BitField[]{_locked, _hidden, _123_prefix}, new String[]{"LOCKED", "HIDDEN", "LOTUS_123_PREFIX"}));
        m.put("parentIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda16
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getParentIndex());
            }
        });
        m.put("alignmentOptions", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda17
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getAlignmentOptions());
            }
        }, new BitField[]{_wrap_text, _justify_last}, new String[]{"WRAP_TEXT", "JUSTIFY_LAST"}));
        m.put(CellUtil.ALIGNMENT, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda18
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getAlignment());
            }
        });
        m.put(CellUtil.VERTICAL_ALIGNMENT, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda19
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getVerticalAlignment());
            }
        });
        m.put("rotation", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda20
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getRotation());
            }
        });
        m.put("indentionOptions", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda21
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getIndentionOptions());
            }
        }, new BitField[]{_shrink_to_fit, _merge_cells, _indent_not_parent_format, _indent_not_parent_font, _indent_not_parent_alignment, _indent_not_parent_border, _indent_not_parent_pattern, _indent_not_parent_cell_options}, new String[]{"SHRINK_TO_FIT", "MERGE_CELLS", "NOT_PARENT_FORMAT", "NOT_PARENT_FONT", "NOT_PARENT_ALIGNMENT", "NOT_PARENT_BORDER", "NOT_PARENT_PATTERN", "NOT_PARENT_CELL_OPTIONS"}));
        m.put("indent", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getIndent());
            }
        });
        m.put("readingOrder", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda22
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getReadingOrder());
            }
        });
        m.put("borderOptions", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda23
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBorderOptions());
            }
        });
        m.put(CellUtil.BORDER_LEFT, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda24
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBorderLeft());
            }
        });
        m.put(CellUtil.BORDER_RIGHT, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda25
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBorderRight());
            }
        });
        m.put(CellUtil.BORDER_TOP, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda26
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBorderTop());
            }
        });
        m.put(CellUtil.BORDER_BOTTOM, new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda27
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBorderBottom());
            }
        });
        m.put("paletteOptions", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda28
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getPaletteOptions());
            }
        });
        m.put("leftBorderPaletteIdx", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda29
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getLeftBorderPaletteIdx());
            }
        });
        m.put("rightBorderPaletteIdx", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getRightBorderPaletteIdx());
            }
        });
        m.put("diag", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getDiag());
            }
        });
        m.put("adtlPaletteOptions", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ExtendedFormatRecord.this.getAdtlPaletteOptions());
            }
        });
        m.put("topBorderPaletteIdx", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getTopBorderPaletteIdx());
            }
        });
        m.put("bottomBorderPaletteIdx", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getBottomBorderPaletteIdx());
            }
        });
        m.put("adtlDiag", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getAdtlDiag());
            }
        });
        m.put("adtlDiagLineStyle", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getAdtlDiagLineStyle());
            }
        });
        m.put("adtlFillPattern", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getAdtlFillPattern());
            }
        });
        m.put("fillPaletteOptions", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getFillPaletteOptions());
            }
        });
        m.put("fillForeground", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda12
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getFillForeground());
            }
        });
        m.put("fillBackground", new Supplier() { // from class: org.apache.poi.hssf.record.ExtendedFormatRecord$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(ExtendedFormatRecord.this.getFillBackground());
            }
        });
        return Collections.unmodifiableMap(m);
    }
}
