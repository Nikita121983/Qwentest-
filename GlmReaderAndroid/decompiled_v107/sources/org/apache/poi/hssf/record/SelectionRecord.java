package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SelectionRecord extends StandardRecord {
    public static final short sid = 29;
    private byte field_1_pane;
    private int field_2_row_active_cell;
    private int field_3_col_active_cell;
    private int field_4_active_cell_ref_index;
    private CellRangeAddress8Bit[] field_6_refs;

    public SelectionRecord(SelectionRecord other) {
        super(other);
        this.field_1_pane = other.field_1_pane;
        this.field_2_row_active_cell = other.field_2_row_active_cell;
        this.field_3_col_active_cell = other.field_3_col_active_cell;
        this.field_4_active_cell_ref_index = other.field_4_active_cell_ref_index;
        this.field_6_refs = other.field_6_refs == null ? null : (CellRangeAddress8Bit[]) Stream.of((Object[]) other.field_6_refs).map(new Function() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CellRangeAddress8Bit) obj).copy();
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SelectionRecord.lambda$new$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CellRangeAddress8Bit[] lambda$new$0(int x$0) {
        return new CellRangeAddress8Bit[x$0];
    }

    public SelectionRecord(int activeCellRow, int activeCellCol) {
        this.field_1_pane = (byte) 3;
        this.field_2_row_active_cell = activeCellRow;
        this.field_3_col_active_cell = activeCellCol;
        this.field_4_active_cell_ref_index = 0;
        this.field_6_refs = new CellRangeAddress8Bit[]{new CellRangeAddress8Bit(activeCellRow, activeCellRow, activeCellCol, activeCellCol)};
    }

    public SelectionRecord(RecordInputStream in) {
        this.field_1_pane = in.readByte();
        this.field_2_row_active_cell = in.readUShort();
        this.field_3_col_active_cell = in.readShort();
        this.field_4_active_cell_ref_index = in.readShort();
        int field_5_num_refs = in.readUShort();
        this.field_6_refs = new CellRangeAddress8Bit[field_5_num_refs];
        for (int i = 0; i < this.field_6_refs.length; i++) {
            this.field_6_refs[i] = new CellRangeAddress8Bit(in);
        }
    }

    public void setPane(byte pane) {
        this.field_1_pane = pane;
    }

    public void setActiveCellRow(int row) {
        this.field_2_row_active_cell = row;
        resetField6();
    }

    public void setActiveCellCol(short col) {
        this.field_3_col_active_cell = col;
        resetField6();
    }

    private void resetField6() {
        this.field_6_refs = new CellRangeAddress8Bit[]{new CellRangeAddress8Bit(this.field_2_row_active_cell, this.field_2_row_active_cell, this.field_3_col_active_cell, this.field_3_col_active_cell)};
    }

    public void setActiveCellRef(short ref) {
        this.field_4_active_cell_ref_index = ref;
    }

    public byte getPane() {
        return this.field_1_pane;
    }

    public int getActiveCellRow() {
        return this.field_2_row_active_cell;
    }

    public int getActiveCellCol() {
        return this.field_3_col_active_cell;
    }

    public int getActiveCellRef() {
        return this.field_4_active_cell_ref_index;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return CellRangeAddress8Bit.getEncodedSize(this.field_6_refs.length) + 9;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeByte(getPane());
        out.writeShort(getActiveCellRow());
        out.writeShort(getActiveCellCol());
        out.writeShort(getActiveCellRef());
        int nRefs = this.field_6_refs.length;
        out.writeShort(nRefs);
        for (CellRangeAddress8Bit field_6_ref : this.field_6_refs) {
            field_6_ref.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 29;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SelectionRecord copy() {
        return new SelectionRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SELECTION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("pane", new Supplier() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(SelectionRecord.this.getPane());
            }
        }, "activeCellRow", new Supplier() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SelectionRecord.this.getActiveCellRow());
            }
        }, "activeCellCol", new Supplier() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SelectionRecord.this.getActiveCellCol());
            }
        }, "activeCellRef", new Supplier() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SelectionRecord.this.getActiveCellRef());
            }
        }, "refs", new Supplier() { // from class: org.apache.poi.hssf.record.SelectionRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return SelectionRecord.this.m2369xbbc4dc9d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SelectionRecord, reason: not valid java name */
    public /* synthetic */ Object m2369xbbc4dc9d() {
        return this.field_6_refs;
    }
}
