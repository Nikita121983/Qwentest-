package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public abstract class RefPtgBase extends OperandPtg {
    private int field_1_row;
    private int field_2_col;
    private static final BitField column = BitFieldFactory.getInstance(16383);
    private static final BitField rowRelative = BitFieldFactory.getInstance(32768);
    private static final BitField colRelative = BitFieldFactory.getInstance(16384);

    /* JADX INFO: Access modifiers changed from: protected */
    public RefPtgBase() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RefPtgBase(RefPtgBase other) {
        super(other);
        this.field_1_row = other.field_1_row;
        this.field_2_col = other.field_2_col;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RefPtgBase(CellReference c) {
        setRow(c.getRow());
        setColumn(c.getCol());
        setColRelative(!c.isColAbsolute());
        setRowRelative(!c.isRowAbsolute());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void readCoordinates(LittleEndianInput in) {
        this.field_1_row = in.readUShort();
        this.field_2_col = in.readUShort();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void writeCoordinates(LittleEndianOutput out) {
        out.writeShort(this.field_1_row);
        out.writeShort(this.field_2_col);
    }

    public final void setRow(int rowIndex) {
        this.field_1_row = rowIndex;
    }

    public final int getRow() {
        return this.field_1_row;
    }

    public final boolean isRowRelative() {
        return rowRelative.isSet(this.field_2_col);
    }

    public final void setRowRelative(boolean rel) {
        this.field_2_col = rowRelative.setBoolean(this.field_2_col, rel);
    }

    public final boolean isColRelative() {
        return colRelative.isSet(this.field_2_col);
    }

    public final void setColRelative(boolean rel) {
        this.field_2_col = colRelative.setBoolean(this.field_2_col, rel);
    }

    public final void setColumn(int col) {
        this.field_2_col = column.setValue(this.field_2_col, col);
    }

    public final int getColumn() {
        return column.getValue(this.field_2_col);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String formatReferenceAsString() {
        CellReference cr = new CellReference(getRow(), getColumn(), !isRowRelative(), !isColRelative());
        return cr.formatAsString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final byte getDefaultOperandClass() {
        return (byte) 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefPtgBase$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(RefPtgBase.this.getRow());
            }
        }, "rowRelative", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefPtgBase$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(RefPtgBase.this.isRowRelative());
            }
        }, "column", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefPtgBase$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(RefPtgBase.this.getColumn());
            }
        }, "colRelative", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefPtgBase$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(RefPtgBase.this.isColRelative());
            }
        }, "formatReference", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefPtgBase$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return RefPtgBase.this.formatReferenceAsString();
            }
        });
    }
}
