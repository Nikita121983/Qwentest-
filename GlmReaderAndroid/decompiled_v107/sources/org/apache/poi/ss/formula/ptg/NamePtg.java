package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NamePtg extends OperandPtg implements WorkbookDependentFormula {
    private static final int SIZE = 5;
    public static final short sid = 35;
    private int field_1_label_index;
    private short field_2_zero;

    public NamePtg(int nameIndex) {
        this.field_1_label_index = nameIndex + 1;
    }

    public NamePtg(NamePtg other) {
        super(other);
        this.field_1_label_index = other.field_1_label_index;
        this.field_2_zero = other.field_2_zero;
    }

    public NamePtg(LittleEndianInput in) {
        this.field_1_label_index = in.readUShort();
        this.field_2_zero = in.readShort();
    }

    public int getIndex() {
        return this.field_1_label_index - 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 35);
        out.writeShort(this.field_1_label_index);
        out.writeShort(this.field_2_zero);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 35;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 5;
    }

    @Override // org.apache.poi.ss.formula.WorkbookDependentFormula
    public String toFormulaString(FormulaRenderingWorkbook book) {
        return book.getNameText(this);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        throw new IllegalStateException("3D references need a workbook to determine formula text");
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 0;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public NamePtg copy() {
        return new NamePtg(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("index", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NamePtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NamePtg.this.getIndex());
            }
        });
    }
}
