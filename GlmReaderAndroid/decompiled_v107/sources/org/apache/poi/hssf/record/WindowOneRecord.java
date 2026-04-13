package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class WindowOneRecord extends StandardRecord {
    public static final short sid = 61;
    private short field_1_h_hold;
    private short field_2_v_hold;
    private short field_3_width;
    private short field_4_height;
    private short field_5_options;
    private int field_6_active_sheet;
    private int field_7_first_visible_tab;
    private short field_8_num_selected_tabs;
    private short field_9_tab_width_ratio;
    private static final BitField hidden = BitFieldFactory.getInstance(1);
    private static final BitField iconic = BitFieldFactory.getInstance(2);
    private static final BitField reserved = BitFieldFactory.getInstance(4);
    private static final BitField hscroll = BitFieldFactory.getInstance(8);
    private static final BitField vscroll = BitFieldFactory.getInstance(16);
    private static final BitField tabs = BitFieldFactory.getInstance(32);

    public WindowOneRecord() {
    }

    public WindowOneRecord(WindowOneRecord other) {
        super(other);
        this.field_1_h_hold = other.field_1_h_hold;
        this.field_2_v_hold = other.field_2_v_hold;
        this.field_3_width = other.field_3_width;
        this.field_4_height = other.field_4_height;
        this.field_5_options = other.field_5_options;
        this.field_6_active_sheet = other.field_6_active_sheet;
        this.field_7_first_visible_tab = other.field_7_first_visible_tab;
        this.field_8_num_selected_tabs = other.field_8_num_selected_tabs;
        this.field_9_tab_width_ratio = other.field_9_tab_width_ratio;
    }

    public WindowOneRecord(RecordInputStream in) {
        this.field_1_h_hold = in.readShort();
        this.field_2_v_hold = in.readShort();
        this.field_3_width = in.readShort();
        this.field_4_height = in.readShort();
        this.field_5_options = in.readShort();
        this.field_6_active_sheet = in.readShort();
        this.field_7_first_visible_tab = in.readShort();
        this.field_8_num_selected_tabs = in.readShort();
        this.field_9_tab_width_ratio = in.readShort();
    }

    public void setHorizontalHold(short h) {
        this.field_1_h_hold = h;
    }

    public void setVerticalHold(short v) {
        this.field_2_v_hold = v;
    }

    public void setWidth(short w) {
        this.field_3_width = w;
    }

    public void setHeight(short h) {
        this.field_4_height = h;
    }

    public void setOptions(short o) {
        this.field_5_options = o;
    }

    public void setHidden(boolean ishidden) {
        this.field_5_options = hidden.setShortBoolean(this.field_5_options, ishidden);
    }

    public void setIconic(boolean isiconic) {
        this.field_5_options = iconic.setShortBoolean(this.field_5_options, isiconic);
    }

    public void setDisplayHorizonalScrollbar(boolean scroll) {
        this.field_5_options = hscroll.setShortBoolean(this.field_5_options, scroll);
    }

    public void setDisplayVerticalScrollbar(boolean scroll) {
        this.field_5_options = vscroll.setShortBoolean(this.field_5_options, scroll);
    }

    public void setDisplayTabs(boolean disptabs) {
        this.field_5_options = tabs.setShortBoolean(this.field_5_options, disptabs);
    }

    public void setActiveSheetIndex(int index) {
        this.field_6_active_sheet = index;
    }

    public void setFirstVisibleTab(int t) {
        this.field_7_first_visible_tab = t;
    }

    public void setNumSelectedTabs(short n) {
        this.field_8_num_selected_tabs = n;
    }

    public void setTabWidthRatio(short r) {
        this.field_9_tab_width_ratio = r;
    }

    public short getHorizontalHold() {
        return this.field_1_h_hold;
    }

    public short getVerticalHold() {
        return this.field_2_v_hold;
    }

    public short getWidth() {
        return this.field_3_width;
    }

    public short getHeight() {
        return this.field_4_height;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public boolean getHidden() {
        return hidden.isSet(this.field_5_options);
    }

    public boolean getIconic() {
        return iconic.isSet(this.field_5_options);
    }

    public boolean getDisplayHorizontalScrollbar() {
        return hscroll.isSet(this.field_5_options);
    }

    public boolean getDisplayVerticalScrollbar() {
        return vscroll.isSet(this.field_5_options);
    }

    public boolean getDisplayTabs() {
        return tabs.isSet(this.field_5_options);
    }

    public int getActiveSheetIndex() {
        return this.field_6_active_sheet;
    }

    public int getFirstVisibleTab() {
        return this.field_7_first_visible_tab;
    }

    public short getNumSelectedTabs() {
        return this.field_8_num_selected_tabs;
    }

    public short getTabWidthRatio() {
        return this.field_9_tab_width_ratio;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getHorizontalHold());
        out.writeShort(getVerticalHold());
        out.writeShort(getWidth());
        out.writeShort(getHeight());
        out.writeShort(getOptions());
        out.writeShort(getActiveSheetIndex());
        out.writeShort(getFirstVisibleTab());
        out.writeShort(getNumSelectedTabs());
        out.writeShort(getTabWidthRatio());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 18;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 61;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public WindowOneRecord copy() {
        return new WindowOneRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WINDOW_ONE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("horizontalHold", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getHorizontalHold());
            }
        }, "verticalHold", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getVerticalHold());
            }
        }, "width", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getWidth());
            }
        }, "options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getOptions());
            }
        }, new BitField[]{hidden, iconic, reserved, hscroll, vscroll, tabs}, new String[]{"HIDDEN", "ICONIC", "RESERVED", "HSCROLL", "VSCROLL", "TABS"}), "activeSheetIndex", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(WindowOneRecord.this.getActiveSheetIndex());
            }
        }, "firstVisibleTab", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(WindowOneRecord.this.getFirstVisibleTab());
            }
        }, "numSelectedTabs", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getNumSelectedTabs());
            }
        }, "tabWidthRatio", new Supplier() { // from class: org.apache.poi.hssf.record.WindowOneRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(WindowOneRecord.this.getTabWidthRatio());
            }
        });
    }
}
